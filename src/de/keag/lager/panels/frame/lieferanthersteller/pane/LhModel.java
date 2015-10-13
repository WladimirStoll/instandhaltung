package de.keag.lager.panels.frame.lieferanthersteller.pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.Model;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.LhBeanDB;
import de.keag.lager.db.LhKatalogBeanDB;
import de.keag.lager.db.LhZugriffsrechtBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.benutzer.model.BenutzerModelKnotenBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtModelKnotenBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogModelKnotenBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhModelKnotenBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhSuchBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhZugriffsrechtBean;



public class LhModel extends Model{
	// DB-Zugriff
	private LhBeanDB lhBeanDB;
	private LhKatalogBeanDB lhKatalogBeanDB;
	private LhZugriffsrechtBeanDB lhZugriffsrechtBeanDB;
	 

	protected LhModel() {
		super();
		initialize();
	}

	private void initialize() {
	}


	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new LhSuchBean();
		}
		return iSuchBean;
	}

	@Override
	protected BeanDB getBeanDB() {
		if (lhBeanDB == null) {
			lhBeanDB = new LhBeanDB();
		}
		return lhBeanDB;
	}

	
	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();
		try {
			LhBean lhBean= new LhBean();
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_LIEFERANT_HERSTELLER);
			lhBean.setId(neueId);
		//	lhBean.setStatus(AnforderungStatus.OFFEN);
			lhBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new LhModelKnotenBean(lhBean);
			lhBean.setModelKnotenBean(modelKnotenBean);
			getModelBeanList().add(0,modelKnotenBean);//Der neue Knoten wird an der ersten Position angezeigt.
//			setAktiveModelBean(getModelBeanList().get(0));//Die erste Position wird ausgewählt
			setAktiveTreeModelBean(getModelBeanList().get(0));
		} catch (LagerException e) {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Knoten kann nicht erzeugt werden:"+e.getFehler().toString()));
			e.printStackTrace();
			Log.log().severe(e.getFehler().getMessage());
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Knoten kann nicht erzeugt werden."+e.getMessage()));
			e.printStackTrace();
			Log.log().severe(e.getMessage());
		}
		benachrichtigeBeobachter(); //alle Beobachter werden benachrichtig.
	}

	@Override
	public void speichereSatz(Bean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		try {
			LagerConnection.startTransaction(); //Mitteilung an die DB, dass ab hier eine Transaktion beginnt.
			
			switch (bean.getBeanDBStatus()) {
			case FEHLERHAFT:
				throw new LagerException(new Fehler(55));
			case DELETE:
				speichereLhKatalogen(bean);
				getBeanDB().saveBean((LhBean)bean); //wichtigste Zeile in dieser Procedure
				loescheBeanAusModel(bean, false);
				break;
			case INSERT_DELETE:
				loescheBeanAusModel(bean, false);
				break;
			default:
				getBeanDB().saveBean((LhBean)bean); //wichtigste Zeile in dieser Procedure
				speichereLhKatalogen(bean);
				leseSatzAnhandIDneuEin((LhBean)bean);
				break;
			}
			
			LagerConnection.commit(); //transktion ist erfolgreich abgeschlossen.
		} catch (Exception e) {
			try {
				LagerConnection.rollback();//abbruch der Verarbeitung. DB-Änderungen werden zurückgenommen.
			} catch (SQLException e1) {
				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Fehler beim Rollback:"+e.getMessage()); 
				getFehlerList().add(fehler);
				bean.getFehlerList().add(fehler);
				e.printStackTrace();
				Log.log().severe(e.getMessage());
			}
			Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Satz kann nicht gespeichert werden:"+e.getMessage()); 
			getFehlerList().add(fehler);
			bean.getFehlerList().add(fehler);
			e.printStackTrace();
			Log.log().severe(e.getMessage());
		}
		//Anzeige auffrischen
		benachrichtigeBeobachter();
	}

	private void speichereLhKatalogen(Bean bean) throws Exception {
		//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
		//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
		while(iterator.hasNext()){
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
			try{
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof LhKatalogBean){
					LhKatalogBean lhKatalogBean = (LhKatalogBean)ibean; 
					if (((LhBean)bean).getBeanDBStatus() == BeanDBStatus.DELETE){
						//Löschen
						lhKatalogBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
			        getLhKatalogBeanDB().saveBean(lhKatalogBean);
				}
//				if (ibean instanceof LhZugriffsrechtBean){
//					LhZugriffsrechtBean lhZugriffsrechtBean = (LhZugriffsrechtBean)ibean; 
//					if (lhZugriffsrechtBean.getBeanDBStatus() == BeanDBStatus.DELETE){
//						//Löschen
//						lhZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);
//					}
//			        getLhZugriffsrechtBeanDB().saveBean(lhZugriffsrechtBean);
//				}
			}catch(Exception e){
				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Fehler:"+e.getMessage());
				modelKnotenBean.getFehlerList().add(fehler); //Den Fehler in der Position speichern.
				e.printStackTrace();
				throw e;
			}
		}
	}

	@Override
	protected void leseSatzAnhandIDneuEin(Bean bean) throws SQLException,	 
	LagerException, LagerException{ 
		if (bean !=null){
			LhBean lhBean = (LhBean) bean;
			LhBean lhBeanNeu = (LhBean)getBeanDB().selectAnhandID(lhBean.getId(), 0, null); //Bestellanforderung wird neu aus DB gelesen
			if (lhBean.getModelKnotenBean()!=null){
				lhBean.getModelKnotenBean().setIBean(lhBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
			}
		
//		Die neue Bestellanforderung(frisch aus Datebank ersetzt die alte gespeicherte LhBean)
		ArrayList<LhKatalogBean> lhKatalogen = 
				((LhKatalogBeanDB)getLhKatalogBeanDB()).sucheAnhandLhBean(lhBean);
		
//		ArrayList<LhZugriffsrechtBean> lhZugriffsrechtBeans = 
//			((LhZugriffsrechtBeanDB)getLhZugriffsrechtBeanDB()).sucheAnhandLhBean(lhBean);
//		
		setLhKatalogen(lhKatalogen,(LhModelKnotenBean) getSelectedListModelKnotenBean());
		}
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
		
	}
	
	private LhKatalogBeanDB getLhKatalogBeanDB() {
		if (lhKatalogBeanDB == null) {
			lhKatalogBeanDB = new LhKatalogBeanDB();
		}
		return lhKatalogBeanDB;
	}
	
	

	@Override
	public void loescheSatz(Bean bean)  {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		switch (bean.getBeanDBStatus()){
		case INSERT : {
			loescheBeanAusModel(bean, true);
			//LoescheObjektAusDerListe((LhBean)bean);
			break;
		}
		case FEHLERHAFT : {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Falscher Status der baBean beim Löschen. Status:"+BeanDBStatus.JavaToString(bean.getBeanDBStatus())));
			Log.log().severe("Falscher Status");
			break;
		} 
		case DELETE : ; 
		case SELECT : ; 
		case UPDATE : {
				try {
					LagerConnection.startTransaction(); //Mitteilung an die DB, dass ab hier eine Transaktion beginnt.
					bean.setBeanDBStatus(BeanDBStatus.DELETE);
					getBeanDB().saveBean((LhBean)bean); //wichtigste Zeile in dieser Procedure
					loescheBeanAusModel(bean, true);
//					LoescheObjektAusDerListe((LhBean)bean);
					LagerConnection.commit(); //transktion ist erfolgreich abgeschlossen.
				} catch (Exception e) {
					try {
						LagerConnection.rollback();//abbruch der Verarbeitung. DB-Änderungen werden zurückgenommen.
					} catch (SQLException e1) {
						getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Fehler beim Rollback:"+e.getMessage()));
						e.printStackTrace();
						Log.log().severe(e.getMessage());
					}
					getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Bestellanforderung kann nicht gespeichert werden:"+e.getMessage()));
					e.printStackTrace();
					Log.log().severe(e.getMessage());
				}	
		}; break;
		default:{
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Falscher Status der baBean beim Löschen"));
			Log.log().severe("Falscher Status");
		}
		};
		benachrichtigeBeobachter();
	}

	

//	
//	public void loescheLhZugriffsrecht(LhZugriffsrechtBean zuLoeschendeLhZugriffsrechtBean) {
//		getFehlerList().clear();
//		zuLoeschendeLhZugriffsrechtBean.getFehlerList().clear();
//		
//		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
//		
//		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
//			ModelKnotenBean modelKnotenBean = iterator.next();
//			if (modelKnotenBean.getIBean() instanceof LhZugriffsrechtBean && modelKnotenBean.getIBean().equals(zuLoeschendeLhZugriffsrechtBean)){
//				switch (zuLoeschendeLhZugriffsrechtBean.getBeanDBStatus()){
//				case INSERT : {
//					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
//					break;
//				}
//				case SELECT : zuLoeschendeLhZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
//				case UPDATE : zuLoeschendeLhZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
//				default : zuLoeschendeLhZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
//				}
//				abbruch = true;
//			}
//		}
//		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
//	}
//	
//	

	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new LhModelKnotenBean((LhBean)iBean);
		} catch (LagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

//	public void erzeugeNeueLhZugriffsrecht() {
//		getFehlerList().clear();
//		try {
//			// neuer BestellanfoderungsPositions-Knoten wird erzeugt
//			LhBean lhBean = (LhBean) getSelectedListModelKnotenBean()
//					.getIBean();
//			LhZugriffsrechtModelKnotenBean lhZugriffsrectModelKnotenBean = 
//				new LhZugriffsrechtModelKnotenBean(getSelectedListModelKnotenBean());
//			LhZugriffsrechtBean lhZugriffsrechtBean = new LhZugriffsrechtBean(); // Daten
//																						// im
//																						// Knoten
//			lhZugriffsrechtBean.setLh(lhBean); // aktueller User
//																// aus der Liste
//			lhZugriffsrechtBean.setZugriffsrecht(new ZugriffsrechtBean()); // neu
//			lhZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.INSERT);
//			lhZugriffsrectModelKnotenBean.setIBean(lhZugriffsrechtBean);
//			getSelectedListModelKnotenBean().getKinderList().add(
//					lhZugriffsrectModelKnotenBean);
//			lhBean.getLhZugriffsrechtBeans().add(benutzerZugriffsrechtBean);
//			// Beobachter werden benachrichtigt
//			benachrichtigeDetailsBeobachter();
//			benachrichtigeTreeBeobachter();
//			// benachrichtigeTreePaneBeobachter();
//		} catch (LhOberflacheLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		}
//	}

	@Override
	public ModelKnotenBean selectKnoten(int gewaehlteZeilenNummer) {
		if (gewaehlteZeilenNummer >= 0) {
			if (getModelBeanList().size() >= gewaehlteZeilenNummer) {
				setAktiveTreeModelBean(getModelBeanList()
						.get(gewaehlteZeilenNummer));
				try {
					
					// Datenbankzugriff: Wir suchen alle Positionen zu der
					// gewählten Anforderung.
					ArrayList<LhKatalogBean> lhKatalogBeans = 
						getLhKatalogBeanDB().sucheAnhandLhBean(
								((LhModelKnotenBean) getAktiveTreeModelBean()).getIBean()
						);

					
					setLhKatalogen(
							lhKatalogBeans,
							(LhModelKnotenBean) getAktiveTreeModelBean());
					
//					ArrayList<LhZugriffsrechtBean> benutzerZugriffsrechtBeans = 
//						getLhZugriffsrechtBeanDB().sucheAnhandLhBean(
//								((LhModelKnotenBean) getAktiveTreeModelBean()).getIBean()
//						);
					
					
//					setLhKatalogenUndZugriffsrechte(
//							benutzerKatalogBeans,
//							benutzerZugriffsrechtBeans,
//							(LhModelKnotenBean) getAktiveTreeModelBean());
				} catch (LagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
				} catch (SQLException e) {
					getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
					e.printStackTrace();
				}
			} else {
				getFehlerList().add(new Fehler(15, FehlerTyp.FEHLER));
			}
		} else {
			getFehlerList().add(new Fehler(14, FehlerTyp.INFO));
		}
		benachrichtigeTreeBeobachter();
		benachrichtigeDetailsBeobachter();
		return getSelectedListModelKnotenBean();
	}

	public void erzeugeNeueLhKatalog()  {
		getFehlerList().clear();
		try {
		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
		LhBean lhBean = (LhBean)getSelectedListModelKnotenBean().getIBean();
		LhKatalogModelKnotenBean lhKatalogModelKnotenBean = new LhKatalogModelKnotenBean(getSelectedListModelKnotenBean());
		LhKatalogBean lhKatalogBean = new LhKatalogBean(); //Daten im Knoten
		lhKatalogBean.setLh(lhBean); //aktueller User aus der Liste
		lhKatalogBean.setKatalog(new KatalogBean()); //neu
		lhKatalogBean.setBeanDBStatus(BeanDBStatus.INSERT);
		lhKatalogModelKnotenBean.setIBean(lhKatalogBean);
//		lhKatalogBean.setModelKnotenBean(lhKatalogModelKnotenBean);
		getSelectedListModelKnotenBean().getKinderList().add(0,lhKatalogModelKnotenBean);
		lhBean.getLhKatalogBeans().add(0,lhKatalogBean);
		//Beobachter werden benachrichtigt
		benachrichtigeDetailsBeobachter();
		benachrichtigeTreeBeobachter();
//		benachrichtigeTreePaneBeobachter();
		} catch (LagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		}
	}

	public void loescheLhKatalog(LhKatalogBean zuLoeschendeLhPosBean) {
	getFehlerList().clear();
	zuLoeschendeLhPosBean.getFehlerList().clear();
	
	Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
	
	for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
		ModelKnotenBean modelKnotenBean = iterator.next();
		if (modelKnotenBean.getIBean() instanceof LhKatalogBean && 
					(
							modelKnotenBean.getIBean().equals(zuLoeschendeLhPosBean)
							|| 
							(zuLoeschendeLhPosBean.getKatalog().getId().equals(0)&&
							zuLoeschendeLhPosBean==modelKnotenBean.getIBean()
							)
					)
			){
			switch (zuLoeschendeLhPosBean.getBeanDBStatus()){
			case INSERT : {
				getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
				break;
			}
			case SELECT : zuLoeschendeLhPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
			case UPDATE : zuLoeschendeLhPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
			default : zuLoeschendeLhPosBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
			}
			abbruch = true;
		}
	}
	benachrichtigeBeobachter();//alle Beobachter benachrichtigen
}
	
	
//	@Override
//	public void addISuchBeobachters(ISuchBeobachter iLhSuchPaneBeobachter) {
//		getiLhSuchPaneBeobachters().add(iLhSuchPaneBeobachter);
//		benachrichtigeSuchBeobachter();
//	}



	private void setLhKatalogen(
			ArrayList<LhKatalogBean> katalogKatalogPositionen,
			LhModelKnotenBean katalogModelKnotenBean)
			throws LagerException {
		// Alle Kinder werden rückwärts durchgelaufen.
		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
		int posAnzahl = 0;
		for (int i = katalogModelKnotenBean.getKinderList().size() - 1; i >= 0; i--) {
			ModelKnotenBean modelKnotenBean = katalogModelKnotenBean.getKinderList().get(i);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG) {
				// Wenn aktuelle bestehende Position ausgetauscht werden soll
				// und kann
				if (i <= katalogKatalogPositionen.size() - 1) {
					//der Knoten wird nochmal verwendet. Initialisieren.
					modelKnotenBean.initialize();
					// nächste neue Bean wird ermittelt
					LhKatalogBean katalogPosBean = katalogKatalogPositionen.get(posAnzahl);
					// laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
					posAnzahl++;
					// neue Bean wird übernommen.
					modelKnotenBean.setIBean(katalogPosBean);
				} else {
					// löschen der alten ModelBeanPosition. Wir brauchen diese
					// nicht mehr.
					katalogModelKnotenBean.getKinderList().remove(modelKnotenBean);
				}
			} else {
				//den Rest löschen
				katalogModelKnotenBean.getKinderList().remove(modelKnotenBean);
			}
		}
		// Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
		// wurden.
		if (katalogKatalogPositionen !=null && posAnzahl < katalogKatalogPositionen.size()) {
			for (; posAnzahl < katalogKatalogPositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				LhKatalogBean katalogPosBean = katalogKatalogPositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new LhKatalogModelKnotenBean(katalogModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(katalogPosBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				katalogModelKnotenBean.getKinderList().add(modelKontenBean);
			}
		}
		
		//An dieser Stelle sind alle Katalogen(LhKatalog)in der Liste  aufgenommen.
		
//		//Jetzt müssen noch die Zugriffsreichte in die Liste aufgenommen werden.
//		if (katalogZugriffsrechtPositionen!=null){
//			posAnzahl = 0;
//			for (; posAnzahl < katalogZugriffsrechtPositionen.size(); posAnzahl++) {
//				// nächste nicht aufgenommene Position finden.
//				BenutzerZugriffsrechtBean katalogZugriffsrechtBean = katalogZugriffsrechtPositionen.get(posAnzahl);
//				// neuer Knoten wird erstellt.
//				ModelKnotenBean modelKontenBean = new BenutzerZugriffsrechtModelKnotenBean(katalogModelKnotenBean);
//				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
//				modelKontenBean.setIBean(katalogZugriffsrechtBean);
//				// neuer Knoten(samt Inhalten) wird in die KinderListe
//				// übernommen.
//				katalogModelKnotenBean.getKinderList().add(modelKontenBean);
//			}
//			
//		}
		
		
	}




}
