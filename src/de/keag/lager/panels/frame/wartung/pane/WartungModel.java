package de.keag.lager.panels.frame.wartung.pane;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import com.datetime.DateTimeEditor;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.Model;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.datum.DatumUhrzeit;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.WartungBeanDbLagerException;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.WartungBeanDB;
import de.keag.lager.db.WartungsMitarbeiterBeanDB;
import de.keag.lager.db.WartungsArtikelBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.wartung.model.WartungsMitarbeiterModelKnotenBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.model.WartungModelKnotenBean;
import de.keag.lager.panels.frame.wartung.model.WartungSuchBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean.WartungsArtIntervallFaehigEnum;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelModelKnotenBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.einzelberichte.wartung.WartungsBericht;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;
import de.keag.lager.panels.frame.bestellanforderung.pane.tree.BaTreeView;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;



public class WartungModel extends Model{
	// DB-Zugriff
	private WartungBeanDB wartungBeanDB;
	private WartungsMitarbeiterBeanDB wartungsMitarbeiterBeanDB;
	private WartungsArtikelBeanDB wartungsArtikelBeanDB;
	 

	protected WartungModel() {
		super();
		initialize();
	}

	private void initialize() {
	}


	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new WartungSuchBean();
		}
		return iSuchBean;
	}

	@Override
	protected BeanDB getBeanDB() {
		if (wartungBeanDB == null) {
			wartungBeanDB = new WartungBeanDB();
		}
		return wartungBeanDB;
	}


	/**
	 * Methode macht die aktuell vom Benutzer in der Liste ausgewählte
	 * Bestellanforderung zu "aktiveModelBean"
	 * 
	 * @param gewaehlteZeilenNummer
	 */
	@Override
	public ModelKnotenBean selectKnoten(int gewaehlteZeilenNummer) {
		if (gewaehlteZeilenNummer >= 0) {
			if (getModelBeanList().size() >= gewaehlteZeilenNummer) {
				setAktiveTreeModelBean(getModelBeanList()
						.get(gewaehlteZeilenNummer));
				if ((WartungModelKnotenBean) getAktiveTreeModelBean() != null) {
					try {
						// Datenbankzugriff: Wir suchen alle Positionen zu der
						// gewählten Anforderung.
						ArrayList<WartungsMitarbeiterBean> wartungsMitarbeiterBeans = getWartungsMitarbeiterBeanDB()
								.sucheAnhandWartungBean(
										((WartungModelKnotenBean) getAktiveTreeModelBean())
												.getIBean());

						ArrayList<WartungsArtikelBean> wartungsArtikelBeans = getWartungsArtikelBeanDB()
								.sucheAnhandWartungBean(
										((WartungModelKnotenBean) getAktiveTreeModelBean())
												.getIBean());

						setWartungsMitarbeiterUndArtikel(
								wartungsMitarbeiterBeans,
								wartungsArtikelBeans,
								(WartungModelKnotenBean) getAktiveTreeModelBean());
					} catch (BenutzerOberflacheLagerException e) {
						getFehlerList().add(e.getFehler());
						e.printStackTrace();
					} catch (LagerException e) {
						getFehlerList().add(e.getFehler());
						e.printStackTrace();
					} catch (SQLException e) {
						getFehlerList()
								.add(
										new Fehler(25, FehlerTyp.FEHLER, e
												.getMessage()));
						e.printStackTrace();
					}
				}else{
					getFehlerList().add(new Fehler("keine Daten gefunden"));
				}
			} else {
				getFehlerList().add(new Fehler(15, FehlerTyp.FEHLER));
			}
		} else {
			getFehlerList().add(new Fehler(14, FehlerTyp.INFO));
		}
		benachrichtigeTreeBeobachter();
		benachrichtigeDetailsBeobachter();
//		benachrichtigeSuchBeobachter();
		return getSelectedListModelKnotenBean();
	}

	private void setWartungsMitarbeiterUndArtikel(
			ArrayList<WartungsMitarbeiterBean> wartungsMitarbeiterPositionen,
			ArrayList<WartungsArtikelBean> benutzerZugriffsrechtPositionen,
			WartungModelKnotenBean wartungModelKnotenBean)
			throws BenutzerOberflacheLagerException {
		// Alle Kinder werden rückwärts durchgelaufen.
		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
		int posAnzahl = 0;
		for (int i = wartungModelKnotenBean.getKinderList().size() - 1; i >= 0; i--) {
			ModelKnotenBean modelKnotenBean = wartungModelKnotenBean.getKinderList().get(i);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ABTEILUNG) {
				// Wenn aktuelle bestehende Position ausgetauscht werden soll
				// und kann
				if (i <= wartungsMitarbeiterPositionen.size() - 1) {
					//der Knoten wird nochmal verwendet. Initialisieren.
					modelKnotenBean.initialize();
					// nächste neue Bean wird ermittelt
					WartungsMitarbeiterBean benutzerPosBean = wartungsMitarbeiterPositionen.get(posAnzahl);
					// laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
					posAnzahl++;
					// neue Bean wird übernommen.
					modelKnotenBean.setIBean(benutzerPosBean);
				} else {
					// löschen der alten ModelBeanPosition. Wir brauchen diese
					// nicht mehr.
					wartungModelKnotenBean.getKinderList().remove(modelKnotenBean);
				}
			} else {
				//den Rest löschen
				wartungModelKnotenBean.getKinderList().remove(modelKnotenBean);
			}
		}
		// Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
		// wurden.
		if (wartungsMitarbeiterPositionen !=null && posAnzahl < wartungsMitarbeiterPositionen.size()) {
			for (; posAnzahl < wartungsMitarbeiterPositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				WartungsMitarbeiterBean benutzerPosBean = wartungsMitarbeiterPositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new WartungsMitarbeiterModelKnotenBean(wartungModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(benutzerPosBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				wartungModelKnotenBean.getKinderList().add(modelKontenBean);
			}
		}
		
		//An dieser Stelle sind alle Abteilungen(WartungsMitarbeiter)in der Liste  aufgenommen.
		
		//Jetzt müssen noch die Zugriffsreichte in die Liste aufgenommen werden.
		if (benutzerZugriffsrechtPositionen!=null){
			posAnzahl = 0;
			for (; posAnzahl < benutzerZugriffsrechtPositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				WartungsArtikelBean wartungsArtikelBean = benutzerZugriffsrechtPositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new WartungsArtikelModelKnotenBean(wartungModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(wartungsArtikelBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				wartungModelKnotenBean.getKinderList().add(modelKontenBean);
			}
			
		}
		
		
	}

	private WartungsMitarbeiterBeanDB getWartungsMitarbeiterBeanDB() {
		if (wartungsMitarbeiterBeanDB == null) {
			wartungsMitarbeiterBeanDB = new WartungsMitarbeiterBeanDB();
		}
		return wartungsMitarbeiterBeanDB;
	}
	
	private WartungsArtikelBeanDB getWartungsArtikelBeanDB() {
		if (wartungsArtikelBeanDB == null) {
			wartungsArtikelBeanDB = new WartungsArtikelBeanDB();
		}
		return wartungsArtikelBeanDB;
	}
	

//	/**
//	 * Die Funktion liefert die gerade ausgewählte Bestellanforderungsklasse
//	 */
//	@Override
//	public ModelKnotenBean getSelectedListModelKnotenBean() {
//		ModelKnotenBean  modelKnotenBean = getAktiveTreeModelBean();
//		if (modelKnotenBean == null){
//			return null;
//		}else{
//			if (modelKnotenBean.getIBean() instanceof WartungBean){
//				return modelKnotenBean;
//			}else{
//				if (modelKnotenBean.getIBean() instanceof WartungsMitarbeiterBean){
//					return modelKnotenBean.getVater();
//				}else{
//					Log.log().severe("falsche Klasse:"+modelKnotenBean.getIBean());
//					return null;
//				}
//			}
//		}
//	}

	
	public void erstelleCopyNachIntervall(WartungBean originalWartungsBean) throws LagerException {
		if (originalWartungsBean==null
			||originalWartungsBean.getFk_wartungsart().getIntervallFaehig()!=WartungsArtIntervallFaehigEnum.JA
			||originalWartungsBean.getStatus()!=StatusEnum.ABGESCHLOSSEN
			){
			return;
		}
		
		WartungBean wartungBean= new WartungBean();
		try {
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_WARTUNG);
			wartungBean.setId(neueId);
			
			wartungBean.setStatus(StatusEnum.OFFEN);
			wartungBean.setTermin_soll(wartungBean.getAktuellesDatum());
			wartungBean.setTermin_ist(wartungBean.getLeeresDatum());
			
			wartungBean.setFk_baugruppe(originalWartungsBean.getFk_baugruppe());
			wartungBean.setFk_wartungsart(originalWartungsBean.getFk_wartungsart());
			wartungBean.setFk_wartungstyp(originalWartungsBean.getFk_wartungstyp());
			wartungBean.setBemerkung(originalWartungsBean.getBemerkung());
			wartungBean.setBeschreibung(originalWartungsBean.getBeschreibung());
			wartungBean.setKarteiid(originalWartungsBean.getKarteiid());
			wartungBean.setIntervall(originalWartungsBean.getIntervall());
			
			//neuer Solltermin wird berechnet (=istTermin+intervall) -- Anfang
//			java.sql.Date istTermin = originalWartungsBean.getTermin_ist();
//			GregorianCalendar istTerminGregorianCalender = new GregorianCalendar(
//					istTermin.getYear()+1900,istTermin.getMonth(),istTermin.getDay());
//			istTerminGregorianCalender.add(Calendar.DAY_OF_MONTH, originalWartungsBean.getIntervall());
//			wartungBean.setTermin_soll(new java.sql.Date(istTerminGregorianCalender.getTime().getTime()));
			
			java.sql.Date istTermin = originalWartungsBean.getTermin_ist();
			java.sql.Date neuerSollTermin = DatumUhrzeit.addDay(istTermin, originalWartungsBean.getIntervall());
			wartungBean.setTermin_soll(neuerSollTermin);
			//neuer Solltermin wird berechnet (=istTermin+intervall) -- Ene
			
			wartungBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new WartungModelKnotenBean(wartungBean);
			getModelBeanList().add(0,modelKnotenBean);//Der neue Knoten wird an der ersten Position angezeigt.
			
			//Original wird abgespeichert und neu gelesen
			getBeanDB().saveBean(wartungBean); 
			leseSatzAnhandIDneuEin(wartungBean);
			
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Knoten kann nicht erzeugt werden:"+e.getFehler().toString()));
			e.printStackTrace();
			Log.log().severe(e.getFehler().getMessage());
		} catch (IdLagerException e) {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Knoten kann nicht erzeugt werden."+e.getFehler().toString()));
			e.printStackTrace();
			Log.log().severe(e.getFehler().getMessage());
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Knoten kann nicht erzeugt werden."+e.getMessage()));
			e.printStackTrace();
			Log.log().severe(e.getMessage());
		}
//		return wartungBean;
	}
	
	
	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();
		
		
		try {
			WartungBean wartungBean= new WartungBean();
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_WARTUNG);
			wartungBean.setId(neueId);
			
			wartungBean.setStatus(StatusEnum.OFFEN);
			wartungBean.setTermin_soll(wartungBean.getAktuellesDatum());
			wartungBean.setTermin_ist(wartungBean.getLeeresDatum());
			
			wartungBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new WartungModelKnotenBean(wartungBean);
			getModelBeanList().add(0,modelKnotenBean);//Der neue Knoten wird an der ersten Position angezeigt.
//			setAktiveModelBean(getModelBeanList().get(0));//Die erste Position wird ausgewählt
			setAktiveTreeModelBean(getModelBeanList().get(0));
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Knoten kann nicht erzeugt werden:"+e.getFehler().toString()));
			e.printStackTrace();
			Log.log().severe(e.getFehler().getMessage());
		} catch (IdLagerException e) {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Knoten kann nicht erzeugt werden."+e.getFehler().toString()));
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
				speichereWartungsMitarbeiteren(bean);
				getBeanDB().saveBean((WartungBean)bean); //wichtigste Zeile in dieser Procedure
				loescheBeanAusModel(bean, false);
				break;
			case INSERT_DELETE:
				loescheBeanAusModel(bean, false);
				break;
			default: //insert, update
				getBeanDB().saveBean((WartungBean)bean); //wichtigste Zeile in dieser Procedure
				speichereWartungsMitarbeiteren(bean);
				erstelleCopyNachIntervall((WartungBean) bean);
				leseSatzAnhandIDneuEin((WartungBean)bean);
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

//	private void erstelleCopyLautIntervall(Bean bean) {
//		//falls der Satz abgeschlossen ist, wird eine Kopie von ihm erstellt.
//		//Es kann nur einmal passieren beim Abspeichern
//		//Satz wird kopiert, falls intervallfähig
//		WartungBean wartungBean = (WartungBean) bean;
//		if (wartungBean.getFk_wartungsart().getIntervallFaehig()==WartungsArtIntervallFaehigEnum.JA){
////			ModelKnotenBean alterKnoten = getSelectedListModelKnotenBean();
////			
//			//neuen Knoten erzeugen
//			erstelleNeuenSatz();
//			
////			//auf alten Knoten wieder positionieren
////			setAktiveTreeModelBean(alterKnoten); //aktullen Knoten wieder anzeigen.
//		}
//	}

	private void speichereWartungsMitarbeiteren(Bean bean) throws Exception {
		//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
		//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
		while(iterator.hasNext()){
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
			try{
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof WartungsMitarbeiterBean){
					WartungsMitarbeiterBean wartungsMitarbeiterBean = (WartungsMitarbeiterBean)ibean; 
					if (wartungsMitarbeiterBean.getBeanDBStatus() == BeanDBStatus.DELETE){
						//Löschen
						wartungsMitarbeiterBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
			        getWartungsMitarbeiterBeanDB().saveBean(wartungsMitarbeiterBean);
				}
				if (ibean instanceof WartungsArtikelBean){
					WartungsArtikelBean wartungsArtikelBean = (WartungsArtikelBean)ibean; 
					if (wartungsArtikelBean.getBeanDBStatus() == BeanDBStatus.DELETE){
						//Löschen
						wartungsArtikelBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
			        getWartungsArtikelBeanDB().saveBean(wartungsArtikelBean);
				}
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
	LagerException, BenutzerOberflacheLagerException{ 
		
		WartungBean wartungBean = (WartungBean) bean;
		WartungBean wartungBeanNeu = (WartungBean)getBeanDB().selectAnhandID(wartungBean.getId(), 0, null); //Bestellanforderung wird neu aus DB gelesen
		wartungBean.getModelKnotenBean().setIBean(wartungBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
		
		//Die neue Bestellanforderung(frisch aus Datebank ersetzt die alte gespeicherte WartungBean)
		ArrayList<WartungsMitarbeiterBean> wartungsMitarbeiteren = 
		((WartungsMitarbeiterBeanDB)getWartungsMitarbeiterBeanDB()).sucheAnhandWartungBean(wartungBean);
		
		ArrayList<WartungsArtikelBean> wartungsArtikelBeans = 
			((WartungsArtikelBeanDB)getWartungsArtikelBeanDB()).sucheAnhandWartungBean(wartungBean);
		
		setWartungsMitarbeiterUndArtikel(wartungsMitarbeiteren,wartungsArtikelBeans,(WartungModelKnotenBean) getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
		
	}

	@Override
	public void loescheSatz(Bean bean)  {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		switch (bean.getBeanDBStatus()){
		case INSERT : {
			loescheBeanAusModel(bean, true);
			//LoescheObjektAusDerListe((WartungBean)bean);
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
					getBeanDB().saveBean((WartungBean)bean); //wichtigste Zeile in dieser Procedure
					loescheBeanAusModel(bean, true);
//					LoescheObjektAusDerListe((WartungBean)bean);
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

	

	
	public void erzeugeNeueWartungsMitarbeiter()  {
		getFehlerList().clear();
		
		try {
		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
		WartungBean wartungBean = (WartungBean)getSelectedListModelKnotenBean().getIBean();
		WartungsMitarbeiterModelKnotenBean wartungsMitarbeiterModelKnotenBean = new WartungsMitarbeiterModelKnotenBean(getSelectedListModelKnotenBean());
		WartungsMitarbeiterBean wartungsMitarbeiterBean = new WartungsMitarbeiterBean(); //Daten im Knoten
		Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_WARTUNG_MITARBEITER);
		wartungsMitarbeiterBean.setId(neueId);
		wartungsMitarbeiterBean.setFk_wartung(wartungBean); //aktueller User aus der Liste
		wartungsMitarbeiterBean.setFk_benutzer(new BenutzerBean()); //neu leer
		wartungsMitarbeiterBean.setFk_herstellerlieferant(new LhBean()); //neu leer
		wartungsMitarbeiterBean.setBeanDBStatus(BeanDBStatus.INSERT);
		wartungsMitarbeiterModelKnotenBean.setIBean(wartungsMitarbeiterBean);
		getSelectedListModelKnotenBean().getKinderList().add(0,wartungsMitarbeiterModelKnotenBean);
		wartungBean.getWartungsMitarbeiterBeans().add(0,wartungsMitarbeiterBean);
		//Beobachter werden benachrichtigt
		benachrichtigeDetailsBeobachter();
		benachrichtigeTreeBeobachter();
//		benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (IdLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(e.getMessage()));
			e.printStackTrace();
		}
	}

	public void loescheWartungsMitarbeiter(WartungsMitarbeiterBean zuLoeschendeBenutzerPosBean) {
		getFehlerList().clear();
		zuLoeschendeBenutzerPosBean.getFehlerList().clear();

		
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof WartungsMitarbeiterBean && modelKnotenBean.getIBean().equals(zuLoeschendeBenutzerPosBean)){
				switch (zuLoeschendeBenutzerPosBean.getBeanDBStatus()){
				case INSERT : {
					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeBenutzerPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeBenutzerPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeBenutzerPosBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}
	
	public void loescheWartungsArtikel(WartungsArtikelBean zuLoeschendeWartungsArtikelBean) {
		getFehlerList().clear();
		zuLoeschendeWartungsArtikelBean.getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof WartungsArtikelBean && modelKnotenBean.getIBean().equals(zuLoeschendeWartungsArtikelBean)){
				switch (zuLoeschendeWartungsArtikelBean.getBeanDBStatus()){
				case INSERT : {
					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeWartungsArtikelBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeWartungsArtikelBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeWartungsArtikelBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}
	
	
	

//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	public void ausgewaehlterKnotenIstGeandert() {
//		ArrayList<Fehler> errors = getAktiveTreeModelBean().getIBean().pruefeEigeneDaten();
//		getAktiveTreeModelBean().setFehlerList(errors);
//		if (getAktiveTreeModelBean() instanceof WartungsMitarbeiterModelKnotenBean){
//			benachrichtigeDetailsBeobachter();
//		}else if(getAktiveTreeModelBean() instanceof WartungModelKnotenBean){
//			benachrichtigeDetailsBeobachter();
//		}else {
//			Log.log().severe("Fehler im Ablauf. Objekt-Klasse ist nicht vorgesehen.");
//		}
//		benachrichtigeTreeBeobachter();
//	}


//	/**
//	 * Ermittelt, an welcher Stelle in der Ba-Liste die aktuell selektierte Bestellanforderung steht.
//	 */
//	@Override
//	public int getSelectedListModelKnotenBeanLaufendeNummer() {
//		ModelKnotenBean modelKnotenBean =  getSelectedListModelKnotenBean();
//		for(int i = 0; i<getModelBeanList().size();i++){
//			if (getModelBeanList().get(i).equals(modelKnotenBean)){
//				return i;
//			}
//		}
//		return -1;
//	}

//	public ArrayList<Fehler> pruefeAktuelleBean() {
//		
//		getFehlerList().clear();
//		//Die aktuelle Bestellanforderung wird geprüft
//		ArrayList<Fehler> errors = getSelectedListModelKnotenBean().getIBean().pruefeEigeneDaten();
//		if (errors.size()>0){
//			errors.add(0,new Fehler(25,FehlerTyp.FEHLER,"Fehler im Benutzersatz, id=" + ((WartungBean)getSelectedListModelKnotenBean().getIBean()).getId()));
//		}
//		Iterator iterator = getSelectedListModelKnotenBean().getKinderList().iterator(); 
//		while(iterator.hasNext()){
//			//Jede Position der aktuellen Bestellanforderung wird geprüft
//			ModelKnotenBean modelKnotenBean = (ModelKnotenBean)iterator.next();
//			BaPosBean baPosBean = (BaPosBean)modelKnotenBean.getIBean();
//			ArrayList<Fehler> errorsPos = baPosBean.pruefeEigeneDaten();
//			if (errorsPos.size()>0){
//				errors.add(new Fehler(25,FehlerTyp.FEHLER,"Fehler in der Position(Abteilung), id=" + baPosBean.getId()));
//				for(int i=0;i<errorsPos.size();i++){
//					errors.add(errorsPos.get(i));
//				}
//			}
//		}
//		setFehlerList(errors);
//		return getFehlerList();
//	}


	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new WartungModelKnotenBean((WartungBean)iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	public void erzeugeNeueWartungsArtikel() {
		getFehlerList().clear();
		try {
			
			// neuer BestellanfoderungsPositions-Knoten wird erzeugt
			WartungBean wartungBean = (WartungBean) getSelectedListModelKnotenBean()
					.getIBean();
			WartungsArtikelModelKnotenBean benutzerZugriffsrectModelKnotenBean = 
				new WartungsArtikelModelKnotenBean(getSelectedListModelKnotenBean());
			WartungsArtikelBean wartungsArtikelBean = new WartungsArtikelBean(); // Daten
																						// im
																						// Knoten
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_WARTUNG_ARTIKEL);
			wartungsArtikelBean.setId(neueId);
			wartungsArtikelBean.setFk_wartung(wartungBean); // aktueller User
																// aus der Liste
			wartungsArtikelBean.setFk_artikel(new ArtikelBean()); // neu
			wartungsArtikelBean.setBeanDBStatus(BeanDBStatus.INSERT);
			benutzerZugriffsrectModelKnotenBean.setIBean(wartungsArtikelBean);
			getSelectedListModelKnotenBean().getKinderList().add(
					benutzerZugriffsrectModelKnotenBean);
			wartungBean.getWartungsArtikelBeans().add(wartungsArtikelBean);
			// Beobachter werden benachrichtigt
			benachrichtigeDetailsBeobachter();
			benachrichtigeTreeBeobachter();
			// benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (IdLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(e.getMessage()));
			e.printStackTrace();
		}
	}

	public void generiereWartungsKartenId() throws SQLException, LagerException {
		Integer neueKartenID = ((WartungBeanDB)getBeanDB()).generiereWartungsKartenId();
		IBean iBean = getSelectedListModelKnotenBean().getIBean();
		((WartungBean)iBean).setKarteiid(neueKartenID);
		benachrichtigeBeobachter();
	}

	
	public void changeStatus(StatusEnum statusEnum) {
		getFehlerList().clear();
		if (getSelectedListModelKnotenBean()!=null&&getSelectedListModelKnotenBean().getIBean()!=null){
			WartungBean wartungBean = (WartungBean) getSelectedListModelKnotenBean().getIBean();
			switch (wartungBean.getStatus()) {
			case OFFEN: //ist(war) offen
				switch (statusEnum) {
				case ABGESCHLOSSEN: //soll abgeschlossen
					wartungBean.setStatus(StatusEnum.ABGESCHLOSSEN);
					wartungBean.setTermin_ist(WartungBean.getAktuellesDatum());
					break;
				default:
					getFehlerList().add(new Fehler("Status kann nicht gesetzt werden. " +
							"Akutell:"+StatusEnum.JavaToView(wartungBean.getStatus()) +
							"Gewünscht:" +StatusEnum.JavaToView(statusEnum) 
							));
					break;
				}
				break;
			default:
				getFehlerList().add(new Fehler("Status kann nicht gesetzt werden.. " +
						"Akutell:"+StatusEnum.JavaToView(wartungBean.getStatus()) +
						"Gewünscht:" +StatusEnum.JavaToView(statusEnum) 
						));
				break;
			}
		}else{
			getFehlerList().add(new Fehler("Status kann nicht gesetzt werden..."));
		}
		
		benachrichtigeBeobachter();
	}

	public void druckeBericht(WartungBean bean,Map<String, String> druckParameter) throws Exception {
		if (getModelBeanList().size()<=0){
			throw new LagerException(new Fehler("Keine Daten zum Drucken"));
		}else{
			for(int i = 0;i<getModelBeanList().size();i++){
				//alle Wartungs-IDs ablegen
				WartungBean wartungBean =  (WartungBean) getModelBeanList().get(i).getIBean();
				druckParameter.put(WartungsBericht.WARTUNG_ID_+new Integer(i).toString(), wartungBean.getId().toString());
			}
			WartungSuchBean wartungSuchBean = (WartungSuchBean) getiSuchBean();
			druckParameter.put("termin_von",DatumUhrzeit.JavaToView(wartungSuchBean.getDatumVon(),DatumUhrzeit.DatumFormat.DATUM));
			druckParameter.put("termin_bis",DatumUhrzeit.JavaToView(wartungSuchBean.getDatumBis(),DatumUhrzeit.DatumFormat.DATUM));
			BerichtPaneController berichtPaneController = new BerichtPaneController();
			LagerConnection.getOneInstance().startTransaction();
			try{
				berichtPaneController.drucken(Berichttyp.WARTUNG, 0/*neu*/, Berichtart.PRINT,druckParameter);
				LagerConnection.getOneInstance().commit();
			}catch (Exception e) {
				LagerConnection.getOneInstance().rollback();
				throw e;
			}
		}
	}
	
	
//	@Override
//	public void addISuchBeobachters(ISuchBeobachter iWartungSuchPaneBeobachter) {
//		getiWartungSuchPaneBeobachters().add(iWartungSuchPaneBeobachter);
//		benachrichtigeSuchBeobachter();
//	}







}
