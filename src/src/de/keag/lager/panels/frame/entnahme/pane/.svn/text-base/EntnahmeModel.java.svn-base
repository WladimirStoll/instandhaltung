package de.keag.lager.panels.frame.entnahme.pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.Model;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.BenutzerBeanDbLagerException;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.EntnahmeBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeModelKnotenBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosModelKnotenBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeStatus;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeSuchBean;


public class EntnahmeModel extends Model {
	


	protected EntnahmeModel() {
		super();
		initialize();
	}

	private void initialize() {
	}

	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new EntnahmeModelKnotenBean((EntnahmeBean)iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new EntnahmeSuchBean();
		}
		return iSuchBean;
	}



	@Override
	protected BeanDB getBeanDB() {
		if (beanDB == null) {
			beanDB = new EntnahmeBeanDB();
		}
		return beanDB;
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
			if (getModelBeanList().size() > gewaehlteZeilenNummer) {
				setAktiveTreeModelBean(getModelBeanList().get(gewaehlteZeilenNummer));
				try {
					// Datenbankzugriff: Wir suchen alle Positionen zu der
					// gewählten Anforderung.
					ArrayList<EntnahmePosBean> entnahmePositionen = 
					((EntnahmeBeanDB)getBeanDB()).
						getEntnahmePosBeanDB().
							sucheAnhandEntnahmeBean(((EntnahmeModelKnotenBean) getAktiveTreeModelBean()).getIBean()
					);
					setEntnahmePositionen(entnahmePositionen,(EntnahmeModelKnotenBean) getAktiveTreeModelBean());
				} catch (SQLException e) {
					getFehlerList().add(new Fehler(16, FehlerTyp.FEHLER));
					e.printStackTrace();
				} catch (BenutzerOberflacheLagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
				} catch (ArtikelBeanDbLagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
				} catch (MengenEinheitBeanDbLagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
				} catch (KostenstelleBeanDbLagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
				} catch (LagerException e) {
					getFehlerList().add(e.getFehler());
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

	private void setEntnahmePositionen(ArrayList<EntnahmePosBean> entnahmePositionen,
			EntnahmeModelKnotenBean entnahmeModelKnotenBean)
			throws BenutzerOberflacheLagerException {
		// Alle Kinder werden rückwärts durchgelaufen.
		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
		int posAnzahl = 0;
		for (int i = entnahmeModelKnotenBean.getKinderList().size() - 1; i >= 0; i--) {
			ModelKnotenBean modelKnotenBean = entnahmeModelKnotenBean.getKinderList()
					.get(i);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ENTNAHMENSPOSITION) {
				// Wenn aktuelle bestehende Position ausgetauscht werden soll
				// und kann
				if (i <= entnahmePositionen.size() - 1) {
					//der Knoten wird nochmal verwendet. Initialisieren.
					modelKnotenBean.initialize();
					// nächste neue Bean wird ermittelt
					EntnahmePosBean entnahmePosBean = entnahmePositionen.get(posAnzahl);
					// laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
					posAnzahl++;
					// neue Bean wird übernommen.
					modelKnotenBean.setIBean(entnahmePosBean);
				} else {
					// löschen der alten ModelBeanPosition. Wir brauchen diese
					// nicht mehr.
					entnahmeModelKnotenBean.getKinderList().remove(modelKnotenBean);
				}
			} else {
				// andere Kinder interessieren uns nicht.
			}
		}
		// Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
		// wurden.
		if (posAnzahl < entnahmePositionen.size()) {
			for (; posAnzahl < entnahmePositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				EntnahmePosBean entnahmePosBean = entnahmePositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new EntnahmePosModelKnotenBean(entnahmeModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(entnahmePosBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				entnahmeModelKnotenBean.getKinderList().add(modelKontenBean);
			}
		}
	}



	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();
		
		try {
			EntnahmeBean entnahmeBean= new EntnahmeBean();
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ENTNAHME);
			entnahmeBean.setId(neueId);
			entnahmeBean.setStatus(EntnahmeStatus.OFFEN);
			entnahmeBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new EntnahmeModelKnotenBean(entnahmeBean);
			getModelBeanList().add(0,modelKnotenBean);//Der neue Knoten wird an der ersten Position angezeigt.
			setAktiveTreeModelBean(getModelBeanList().get(0));//Die erste Position wird ausgewählt
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
			((EntnahmeBeanDB)getBeanDB()).saveBean((EntnahmeBean)bean); //wichtigste Zeile in dieser Procedure
			//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
			//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
			Iterator iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
			while(iterator.hasNext()){
				ModelKnotenBean modelKnotenBean = (ModelKnotenBean)iterator.next();
				modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
				EntnahmePosBean entnahmePosBean = (EntnahmePosBean)modelKnotenBean.getIBean();
				try{
					((EntnahmeBeanDB)getBeanDB()).getEntnahmePosBeanDB().saveBean(entnahmePosBean);
				}catch(Exception e){
					Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Fehler:"+e.getMessage());
					modelKnotenBean.getFehlerList().add(fehler); //Den Fehler in der Position speichern.
					e.printStackTrace();
					throw e;
				}
			}

			//Daten neu einlesen
			leseSatzAnhandIDneuEin((EntnahmeBean)bean);
			
			//Erst jetzt speichern!
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
		//Anzeige auffrischen
		benachrichtigeBeobachter();
	}

	@Override
	protected void leseSatzAnhandIDneuEin(Bean bean) throws SQLException,	 LagerException, BenutzerOberflacheLagerException {
//		EntnahmeBean entnahmeBeanNeu = ((EntnahmeBeanDB)getBeanDB()).sucheAnhandID(((EntnahmeBean)bean).getId()); //Bestellanforderung wird neu aus DB gelesen
		EntnahmeBean entnahmeBeanNeu = (EntnahmeBean)((EntnahmeBeanDB)getBeanDB()).selectAnhandID(((EntnahmeBean)bean).getId()); //Bestellanforderung wird neu aus DB gelesen
		bean.getModelKnotenBean().setIBean(entnahmeBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
		//Die neue Bestellanforderung(frisch aus Dateentnahmenk ersetzt die alte gespeicherte EntnahmeBean)
		ArrayList<EntnahmePosBean> entnahmePositionen = ((EntnahmeBeanDB)getBeanDB()).getEntnahmePosBeanDB().sucheAnhandEntnahmeBean((EntnahmeBean)bean);
		setEntnahmePositionen(entnahmePositionen,(EntnahmeModelKnotenBean) getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
		
	}


	@Override
	public void loescheSatz(Bean bean)  {
		getFehlerList().clear();
		
		if (bean!=null && ((EntnahmeBean)bean).getStatus()==EntnahmeStatus.GELOESCHT){
			getFehlerList().add(new Fehler(39,FehlerTyp.INFO));
			benachrichtigeListBeobachter();
			return;
		}
		
		switch (bean.getBeanDBStatus()){
		case INSERT : {
			loescheBeanAusModel((EntnahmeBean)bean, true);
			break;
		}
		case FEHLERHAFT : {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Falscher Status der baBean beim Löschen. Status:"+BeanDBStatus.JavaToString(bean.getBeanDBStatus())));
			Log.log().severe("Falscher Status");
			break;
		} 
		case SELECT : ; 
		case UPDATE : {
				try {
					LagerConnection.startTransaction(); //Mitteilung an die DB, dass ab hier eine Transaktion beginnt.
					((EntnahmeBean)bean).setStatus(EntnahmeStatus.GELOESCHT);//Logischer Status in der Anwendung
					if (bean.getBeanDBStatus()!=BeanDBStatus.INSERT){
						bean.setBeanDBStatus(BeanDBStatus.UPDATE);
					}
					((EntnahmeBeanDB)getBeanDB()).saveBean((EntnahmeBean)bean); //wichtigste Zeile in dieser Procedure
					LagerConnection.commit(); //transktion ist erfolgreich abgeschlossen.
					leseSatzAnhandIDneuEin(bean);
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

	@Override
	protected void loescheBeanAusModel(Bean bean, Boolean benachrichtigeBeobachter) {
		for(int i=getModelBeanList().size()-1; i>=0 ;i--){
			ModelKnotenBean modelKnotenBean = getModelBeanList().get(i);
			EntnahmeBean entnahmeBeanTemp = null;
			if(modelKnotenBean.getIBean() instanceof EntnahmeBean){
				entnahmeBeanTemp = (EntnahmeBean)modelKnotenBean.getIBean();
			}else{
				entnahmeBeanTemp = null;
				Log.log().severe("Fehler! Unerwartete Klasse:"+modelKnotenBean.getIBean().getClass().getName());
			}
			if (bean.equals(entnahmeBeanTemp)){
				getModelBeanList().remove(i);
				selectKnoten(0);
				return;
			}
		}
	}

//	@Override
//	protected void abbrechenSatz(Bean bean) {
//		getFehlerList().clear();
//		try {
//			if (bean!=null){
//				switch (bean.getBeanDBStatus()){
//				case INSERT : {
//					loescheBeanAusModel(bean, true);
//					break;
//					}
//				default:
//					leseSatzAnhandIDneuEin(bean);
//				};
//				
//			}
//		} catch (SQLException e) {
//			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER, e.getMessage()));
//			e.printStackTrace();
//		} catch (BenutzerBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (LagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		}
//		
//		selectKnoten(getSelectedListModelKnotenBean());
//		benachrichtigeBeobachter();
//	}

	public void erzeugeNeueEntnahmePosition()  {
		getFehlerList().clear();
		
		try {
		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
		EntnahmePosModelKnotenBean entnahmePosModelKnotenBean = new EntnahmePosModelKnotenBean(getSelectedListModelKnotenBean());
		EntnahmePosBean entnahmePosBean = new EntnahmePosBean(); //Daten im Knoten
		Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ENTNAHME_POS);
		entnahmePosBean.setId(neueId);
		entnahmePosBean.setBeanDBStatus(BeanDBStatus.INSERT);
		entnahmePosBean.setEntnahmeBean((EntnahmeBean)getSelectedListModelKnotenBean().getIBean());
		entnahmePosModelKnotenBean.setIBean(entnahmePosBean);
		getSelectedListModelKnotenBean().getKinderList().add(entnahmePosModelKnotenBean);
		//Beobachter werden benachrichtigt
		benachrichtigeDetailsBeobachter();
		benachrichtigeTreeBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (IdLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		}
	}

	public void loescheEntnahmePosition(de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean zuLoeschendeEntnahmePosBean) {
		getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean entnahmePosBean = (de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean)modelKnotenBean.getIBean();
			if(entnahmePosBean.getId()==zuLoeschendeEntnahmePosBean.getId()){
				switch (zuLoeschendeEntnahmePosBean.getBeanDBStatus()){
				case INSERT : {
					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeEntnahmePosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeEntnahmePosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeEntnahmePosBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}




}
