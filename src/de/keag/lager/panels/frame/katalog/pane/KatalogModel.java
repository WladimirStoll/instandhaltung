package de.keag.lager.panels.frame.katalog.pane;

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
import de.keag.lager.db.BestandspackstueckBeanDB;
import de.keag.lager.db.KatalogBeanDB;
import de.keag.lager.db.PositionBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungModelKnotenBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerModelKnotenBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckSuchBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.katalog.KatalogSuchBean;
import de.keag.lager.panels.frame.katalog.model.KatalogModelKnotenBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class KatalogModel extends Model {

	private PositionBeanDB positionBeanDB;
	private BestandspackstueckBeanDB bestandspackstueckBeanDB;

	protected KatalogModel() {
		super();
		initialize();
	}

	private void initialize() {
	}

	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new KatalogModelKnotenBean((KatalogBean) iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new KatalogSuchBean();
		}
		return iSuchBean;
	}

	@Override
	protected BeanDB getBeanDB() {
		if (beanDB == null) {
			beanDB = new KatalogBeanDB();
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
				setAktiveTreeModelBean(getModelBeanList().get(
						gewaehlteZeilenNummer));
				try {
					// Datenbankzugriff: Wir suchen alle Positionen zu der
					// gewählten Anforderung.
					KatalogBean einlagerungPosBean = ((KatalogModelKnotenBean) getAktiveTreeModelBean())
							.getIBean();
					einlagerungPosBean = (KatalogBean) ((KatalogBeanDB) getBeanDB())
							.selectAnhandID(einlagerungPosBean.getId(), 0, null);
					getAktiveTreeModelBean().setIBean(einlagerungPosBean);
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

	// private void setKatalogitionen(ArrayList<KatalogBean>
	// einlagerungPositionen,
	// KatalogModelKnotenBean einlagerungModelKnotenBean)
	// throws BenutzerOberflacheLagerException {
	// // Alle Kinder werden rückwärts durchgelaufen.
	// // Bereits bestehende Positionen werden durch neue Positionen ersetzt.
	// int posAnzahl = 0;
	// for (int i = einlagerungModelKnotenBean.getKinderList().size() - 1; i >=
	// 0; i--) {
	// ModelKnotenBean modelKnotenBean =
	// einlagerungModelKnotenBean.getKinderList()
	// .get(i);
	// if (modelKnotenBean.getSammelKnotenTypENUM() ==
	// ModelKnotenTyp.EINLAGERUNGPOSITION) {
	// // Wenn aktuelle bestehende Position ausgetauscht werden soll
	// // und kann
	// if (i <= einlagerungPositionen.size() - 1) {
	// //der Knoten wird nochmal verwendet. Initialisieren.
	// modelKnotenBean.initialize();
	// // nächste neue Bean wird ermittelt
	// KatalogBean einlagerungPosBean = einlagerungPositionen.get(posAnzahl);
	// // laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
	// posAnzahl++;
	// // neue Bean wird übernommen.
	// modelKnotenBean.setIBean(einlagerungPosBean);
	// } else {
	// // löschen der alten ModelBeanPosition. Wir brauchen diese
	// // nicht mehr.
	// einlagerungModelKnotenBean.getKinderList().remove(modelKnotenBean);
	// }
	// } else {
	// // andere Kinder interessieren uns nicht.
	// }
	// }
	// // Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
	// // wurden.
	// if (posAnzahl < einlagerungPositionen.size()) {
	// for (; posAnzahl < einlagerungPositionen.size(); posAnzahl++) {
	// // nächste nicht aufgenommene Position finden.
	// KatalogBean einlagerungPosBean = einlagerungPositionen.get(posAnzahl);
	// // neuer Knoten wird erstellt.
	// ModelKnotenBean modelKontenBean = new
	// KatalogModelKnotenBean(einlagerungModelKnotenBean);
	// // ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
	// modelKontenBean.setIBean(einlagerungPosBean);
	// // neuer Knoten(samt Inhalten) wird in die KinderListe
	// // übernommen.
	// einlagerungModelKnotenBean.getKinderList().add(modelKontenBean);
	// }
	// }
	// }

	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();

		try {
			KatalogBean einlagerungPosBean = new KatalogBean();
			Integer neueId = LagerConnection.getOneInstance().getID(
					Konstanten.ID_DB_KATALOG);
			einlagerungPosBean.setId(neueId);
			einlagerungPosBean.setBeanDBStatus(BeanDBStatus.INSERT); // als neu
																		// markieren
			ModelKnotenBean modelKnotenBean;
			// ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new KatalogModelKnotenBean(einlagerungPosBean);
			getModelBeanList().add(0, modelKnotenBean);// Der neue Knoten wird
														// an der ersten
														// Position angezeigt.
			// setAktiveModelBean(getModelBeanList().get(0));//Die erste
			// Position wird ausgewählt
			setAktiveTreeModelBean(getModelBeanList().get(0));

			// KatalogBean einlagerungPosBean=
			// (KatalogBean)getSelectedListModelKnotenBean().getIBean();
			// Integer neueId =
			// LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ENTNAHME_POS);
			// einlagerungPosBean.setId(neueId);
			// einlagerungPosBean.setStatus(KatalogStatus.OFFEN);
			// einlagerungPosBean.setBeanDBStatus(BeanDBStatus.INSERT); //als
			// neu markieren
			// ModelKnotenBean modelKnotenBean;
			// //ein neuer leerer Knoten wird erzeugt
			// modelKnotenBean = new KatalogModelKnotenBean(einlagerungPosBean);
			// getModelBeanList().add(0,modelKnotenBean);//Der neue Knoten wird
			// an der ersten Position angezeigt.
			// setAktiveTreeModelBean(getModelBeanList().get(0));//Die erste
			// Position wird ausgewählt
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(
					new Fehler(25, FehlerTyp.FEHLER,
							"Knoten kann nicht erzeugt werden:"
									+ e.getFehler().toString()));
			e.printStackTrace();
			Log.log().severe(e.getFehler().getMessage());
		} catch (IdLagerException e) {
			getFehlerList().add(
					new Fehler(25, FehlerTyp.FEHLER,
							"Knoten kann nicht erzeugt werden."
									+ e.getFehler().toString()));
			e.printStackTrace();
			Log.log().severe(e.getFehler().getMessage());
		} catch (SQLException e) {
			getFehlerList().add(
					new Fehler(25, FehlerTyp.FEHLER,
							"Knoten kann nicht erzeugt werden."
									+ e.getMessage()));
			e.printStackTrace();
			Log.log().severe(e.getMessage());
		}
		benachrichtigeBeobachter(); // alle Beobachter werden benachrichtig.
	}

	@Override
	public void speichereSatz(Bean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		try {
			LagerConnection.startTransaction(); // Mitteilung an die DB, dass ab
												// hier eine Transaktion
												// beginnt.
			
			
			switch (bean.getBeanDBStatus()) {
			case FEHLERHAFT:
				throw new LagerException(new Fehler(55));
			case DELETE:
//				speichereBenutzerAbteilungen(bean);
				((KatalogBeanDB) getBeanDB()).saveBean((KatalogBean) bean); // wichtigste
//				getBeanDB().saveBean((BenutzerBean)bean); //wichtigste Zeile in dieser Procedure
				loescheBeanAusModel(bean, false);
				break;
			case INSERT_DELETE:
				loescheBeanAusModel(bean, false);
				break;
			default:
				((KatalogBeanDB) getBeanDB()).saveBean((KatalogBean) bean); // wichtigste
//				getBeanDB().saveBean((BenutzerBean)bean); //wichtigste Zeile in dieser Procedure
//				speichereBenutzerAbteilungen(bean);
				leseSatzAnhandIDneuEin((KatalogBean)bean);
				break;
			}
			
																		// Zeile
																		// in
																		// dieser
																		// Procedure
			// Iterator ist eine Interface, welche das Durchsuche/Durchlaufe
			// einer Collection erlaubt.
			// Collection ist eine Sammlung von Daten(z.B. ein Array, oder
			// ArrayList)
			// Iterator iterator =
			// bean.getModelKnotenBean().getKinderList().iterator();
			// while(iterator.hasNext()){
			// ModelKnotenBean modelKnotenBean =
			// (ModelKnotenBean)iterator.next();
			// modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen,
			// vor Schreiben
			// KatalogBean einlagerungPosBean =
			// (KatalogBean)modelKnotenBean.getIBean();
			// try{
			// ((KatalogBeanDB)getBeanDB()).getKatalogBeanDB().saveBean(einlagerungPosBean);
			// }catch(Exception e){
			// Fehler fehler = new Fehler(25,
			// FehlerTyp.FEHLER,"Fehler:"+e.getMessage());
			// modelKnotenBean.getFehlerList().add(fehler); //Den Fehler in der
			// Position speichern.
			// e.printStackTrace();
			// throw e;
			// }
			// }

			// Daten neu einlesen

			// Erst jetzt speichern!
			LagerConnection.commit(); // transktion ist erfolgreich
										// abgeschlossen.
		} catch (Exception e) {
			try {
				LagerConnection.rollback();// abbruch der Verarbeitung.
											// DB-Änderungen werden
											// zurückgenommen.
			} catch (SQLException e1) {
				getFehlerList().add(
						new Fehler(25, FehlerTyp.FEHLER,
								"Fehler beim Rollback:" + e.getMessage()));
				e.printStackTrace();
				Log.log().severe(e.getMessage());
			}
			getFehlerList().add(
					new Fehler(25, FehlerTyp.FEHLER,
							"Bestellanforderung kann nicht gespeichert werden:"
									+ e.getMessage()));
			e.printStackTrace();
			Log.log().severe(e.getMessage());
		}
		// Anzeige auffrischen
		benachrichtigeBeobachter();
	}

	@Override
	protected void leseSatzAnhandIDneuEin(Bean bean) throws SQLException,
			LagerException, BenutzerOberflacheLagerException {
		// KatalogBean einlagerungBeanNeu =
		// ((KatalogBeanDB)getBeanDB()).sucheAnhandID(((KatalogBean)bean).getId());
		// //Bestellanforderung wird neu aus DB gelesen
		KatalogBean einlagerungPosBeanNeu = (KatalogBean) ((KatalogBeanDB) getBeanDB())
				.selectAnhandID(((KatalogBean) bean).getId(), 0, null); // Bestellanforderung
																// wird neu aus
																// DB gelesen
		bean.getModelKnotenBean().setIBean(einlagerungPosBeanNeu); // aktueller
																	// Knoten
																	// wird mit
																	// neuen
																	// Daten
																	// gefüllt.
		// //Die neue Bestellanforderung(frisch aus Dateeinlagerungnk ersetzt
		// die alte gespeicherte KatalogBean)
		// ArrayList<KatalogBean> einlagerungPositionen =
		// ((KatalogBeanDB)getBeanDB()).getKatalogBeanDB().sucheAnhandKatalogBean((KatalogBean)bean);
		// setKatalogitionen(einlagerungPositionen,(KatalogModelKnotenBean)
		// getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); // Am Schluss aufrufen

	}

	@Override
	public void loescheSatz(Bean bean) {
		getFehlerList().clear();

		switch (bean.getBeanDBStatus()) {
		case INSERT: {
			loescheBeanAusModel((KatalogBean) bean, true);
			break;
		}
		case FEHLERHAFT: {
			getFehlerList().add(
					new Fehler(25, FehlerTyp.FEHLER,
							"Falscher Status der Bean beim Löschen. Status:"
									+ BeanDBStatus.JavaToString(bean
											.getBeanDBStatus())));
			Log.log().severe("Falscher Status");
			break;
		}
		case SELECT:
			;
		case DELETE:
			;
		case UPDATE: {
			try {
				LagerConnection.startTransaction(); // Mitteilung an die DB,
													// dass ab hier eine
													// Transaktion beginnt.
				if (bean.getBeanDBStatus() != BeanDBStatus.INSERT) {
					bean.setBeanDBStatus(BeanDBStatus.UPDATE);
				}
				((KatalogBeanDB) getBeanDB()).saveBean((KatalogBean) bean); // wichtigste
																			// Zeile
																			// in
																			// dieser
																			// Procedure
				LagerConnection.commit(); // transktion ist erfolgreich
											// abgeschlossen.
				leseSatzAnhandIDneuEin(bean);
			} catch (Exception e) {
				try {
					LagerConnection.rollback();// abbruch der Verarbeitung.
												// DB-Änderungen werden
												// zurückgenommen.
				} catch (SQLException e1) {
					getFehlerList().add(
							new Fehler(25, FehlerTyp.FEHLER,
									"Fehler beim Rollback:" + e.getMessage()));
					e.printStackTrace();
					Log.log().severe(e.getMessage());
				}
				getFehlerList().add(
						new Fehler(25, FehlerTyp.FEHLER,
								"Bestellanforderung kann nicht gespeichert werden:"
										+ e.getMessage()));
				e.printStackTrace();
				Log.log().severe(e.getMessage());
			}
		}
			;
			break;
		default: {
			getFehlerList().add(
					new Fehler(25, FehlerTyp.FEHLER,
							"Falscher Status der baBean beim Löschen"));
			Log.log().severe("Falscher Status");
		}
		}
		;
		benachrichtigeBeobachter();
	}

	@Override
	protected void loescheBeanAusModel(Bean bean,
			Boolean benachrichtigeBeobachter) {
		for (int i = getModelBeanList().size() - 1; i >= 0; i--) {
			ModelKnotenBean modelKnotenBean = getModelBeanList().get(i);
			KatalogBean einlagerungBeanTemp = null;
			if (modelKnotenBean.getIBean() instanceof KatalogBean) {
				einlagerungBeanTemp = (KatalogBean) modelKnotenBean.getIBean();
			} else {
				einlagerungBeanTemp = null;
				Log.log().severe(
						"Fehler! Unerwartete Klasse:"
								+ modelKnotenBean.getIBean().getClass()
										.getName());
			}
			if (bean.equals(einlagerungBeanTemp)) {
				getModelBeanList().remove(i);
				selectKnoten(0);
				return;
			}
		}
	}

	private PositionBeanDB getPositionBeanDB() {
		if (positionBeanDB == null) {
			positionBeanDB = new PositionBeanDB();
		}
		return positionBeanDB;
	}

	public void vervollstaendigePosition(PositionBean positionBean)
			throws LagerException, SQLException {
		PositionBean newPositionBean = getPositionBeanDB()
				.lesePositionAnhandNummer(
						positionBean.getNummer(),
						positionBean.getEbeneBean().getNummer(),
						positionBean.getEbeneBean().getSaeuleBean().getNummer(),
						positionBean.getEbeneBean().getSaeuleBean()
								.getZeileBean().getNummer(),
						positionBean.getEbeneBean().getSaeuleBean()
								.getZeileBean().getHalleBean().getId());
		positionBean.setId(newPositionBean.getId());
		positionBean.setNummer(newPositionBean.getNummer());
		positionBean.setEbeneBean(newPositionBean.getEbeneBean());
	}

	// public void bestaetigeKatalogition() {
	// KatalogBean einlagerungPosBean = (KatalogBean)
	// getSelectedListModelKnotenBean().getIBean();
	// einlagerungPosBean.setStatus(KatalogStatus.BESTAETIGT);
	// benachrichtigeBeobachter();
	// }

	public BestandspackstueckBeanDB getBestandspackstueckBeanDB() {
		if (bestandspackstueckBeanDB == null) {
			bestandspackstueckBeanDB = new BestandspackstueckBeanDB();
		}
		return bestandspackstueckBeanDB;
	}

	public void setBestandspackstueckBeanDB(
			BestandspackstueckBeanDB bestandspackstueckBeanDB) {
		this.bestandspackstueckBeanDB = bestandspackstueckBeanDB;
	}

	// @Override
	// protected void abbrechenSatz(Bean bean) {
	// getFehlerList().clear();
	// try {
	// if (bean!=null){
	// switch (bean.getBeanDBStatus()){
	// case INSERT : {
	// loescheBeanAusModel(bean, true);
	// break;
	// }
	// default:
	// leseSatzAnhandIDneuEin(bean);
	// };
	//				
	// }
	// } catch (SQLException e) {
	// getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER, e.getMessage()));
	// e.printStackTrace();
	// } catch (BenutzerBeanDbLagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// } catch (LagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// }
	//		
	// selectKnoten(getSelectedListModelKnotenBean());
	// benachrichtigeBeobachter();
	// }

	// public void erzeugeNeueKatalogition() {
	// getFehlerList().clear();
	//		
	// try {
	// //neuer BestellanfoderungsPositions-Knoten wird erzeugt
	// KatalogModelKnotenBean einlagerungPosModelKnotenBean = new
	// KatalogModelKnotenBean(getSelectedListModelKnotenBean());
	// KatalogBean einlagerungPosBean = new KatalogBean(); //Daten im Knoten
	// Integer neueId =
	// LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ENTNAHME_POS);
	// einlagerungPosBean.setId(neueId);
	// einlagerungPosBean.setBeanDBStatus(BeanDBStatus.INSERT);
	// einlagerungPosBean.setKatalogBean((KatalogBean)getSelectedListModelKnotenBean().getIBean());
	// einlagerungPosModelKnotenBean.setIBean(einlagerungPosBean);
	// getSelectedListModelKnotenBean().getKinderList().add(einlagerungPosModelKnotenBean);
	// //Beobachter werden benachrichtigt
	// benachrichtigeDetailsBeobachter();
	// benachrichtigeTreeBeobachter();
	// } catch (BenutzerOberflacheLagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// } catch (IdLagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// } catch (SQLException e) {
	// getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
	// e.printStackTrace();
	// }
	// }
	//
	// public void
	// loescheKatalogition(de.keag.lager.panels.frame.einlagerung.model.KatalogBean
	// zuLoeschendeKatalogBean) {
	// getFehlerList().clear();
	//		
	// Iterator<ModelKnotenBean> iterator =
	// getSelectedListModelKnotenBean().getKinderList().iterator();
	//		
	// for(boolean abbruch = false; iterator.hasNext()&&!abbruch; ){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// de.keag.lager.panels.frame.einlagerung.model.KatalogBean
	// einlagerungPosBean =
	// (de.keag.lager.panels.frame.einlagerung.model.KatalogBean)modelKnotenBean.getIBean();
	// if(einlagerungPosBean.getId()==zuLoeschendeKatalogBean.getId()){
	// switch (zuLoeschendeKatalogBean.getBeanDBStatus()){
	// case INSERT : {
	// getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
	// break;
	// }
	// case SELECT :
	// zuLoeschendeKatalogBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// case UPDATE :
	// zuLoeschendeKatalogBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// default :
	// zuLoeschendeKatalogBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
	// }
	// abbruch = true;
	// }
	// }
	// benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	// }

}
