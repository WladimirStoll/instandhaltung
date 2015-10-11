/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
package de.keag.lager.panels.frame.einlagerung.pane;

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
import de.keag.lager.db.EinlagerungPosBeanDB;
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
//import de.keag.lager.panels.frame.einlagerung.model.EinlagerungModelKnotenBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosModelKnotenBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungStatus;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosSuchBean;
import de.keag.lager.panels.frame.position.model.PositionBean;


public class EinlagerungModel extends Model {
	


	private PositionBeanDB positionBeanDB;
	private BestandspackstueckBeanDB bestandspackstueckBeanDB;

	protected EinlagerungModel() {
		super();
		initialize();
	}

	private void initialize() {
	}

	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new EinlagerungPosModelKnotenBean((EinlagerungPosBean)iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new EinlagerungPosSuchBean();
		}
		return iSuchBean;
	}



	@Override
	protected BeanDB getBeanDB() {
		if (beanDB == null) {
			beanDB = new EinlagerungPosBeanDB();
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
					EinlagerungPosBean einlagerungPosBean = 
					((EinlagerungPosModelKnotenBean) getAktiveTreeModelBean()).getIBean();
					einlagerungPosBean = (EinlagerungPosBean)((EinlagerungPosBeanDB)getBeanDB()).selectAnhandID(einlagerungPosBean.getId(), 0, null);
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

//	private void setEinlagerungPositionen(ArrayList<EinlagerungPosBean> einlagerungPositionen,
//			EinlagerungModelKnotenBean einlagerungModelKnotenBean)
//			throws BenutzerOberflacheLagerException {
//		// Alle Kinder werden rückwärts durchgelaufen.
//		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
//		int posAnzahl = 0;
//		for (int i = einlagerungModelKnotenBean.getKinderList().size() - 1; i >= 0; i--) {
//			ModelKnotenBean modelKnotenBean = einlagerungModelKnotenBean.getKinderList()
//					.get(i);
//			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.EINLAGERUNGPOSITION) {
//				// Wenn aktuelle bestehende Position ausgetauscht werden soll
//				// und kann
//				if (i <= einlagerungPositionen.size() - 1) {
//					//der Knoten wird nochmal verwendet. Initialisieren.
//					modelKnotenBean.initialize();
//					// nächste neue Bean wird ermittelt
//					EinlagerungPosBean einlagerungPosBean = einlagerungPositionen.get(posAnzahl);
//					// laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
//					posAnzahl++;
//					// neue Bean wird übernommen.
//					modelKnotenBean.setIBean(einlagerungPosBean);
//				} else {
//					// löschen der alten ModelBeanPosition. Wir brauchen diese
//					// nicht mehr.
//					einlagerungModelKnotenBean.getKinderList().remove(modelKnotenBean);
//				}
//			} else {
//				// andere Kinder interessieren uns nicht.
//			}
//		}
//		// Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
//		// wurden.
//		if (posAnzahl < einlagerungPositionen.size()) {
//			for (; posAnzahl < einlagerungPositionen.size(); posAnzahl++) {
//				// nächste nicht aufgenommene Position finden.
//				EinlagerungPosBean einlagerungPosBean = einlagerungPositionen.get(posAnzahl);
//				// neuer Knoten wird erstellt.
//				ModelKnotenBean modelKontenBean = new EinlagerungPosModelKnotenBean(einlagerungModelKnotenBean);
//				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
//				modelKontenBean.setIBean(einlagerungPosBean);
//				// neuer Knoten(samt Inhalten) wird in die KinderListe
//				// übernommen.
//				einlagerungModelKnotenBean.getKinderList().add(modelKontenBean);
//			}
//		}
//	}



	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();
		
		try {
			EinlagerungPosBean einlagerungPosBean= new EinlagerungPosBean();
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_EINLAGERUNG_POS);
			einlagerungPosBean.setId(neueId);
			einlagerungPosBean.setStatus(EinlagerungStatus.OFFEN);
			einlagerungPosBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new EinlagerungPosModelKnotenBean(einlagerungPosBean);
			getModelBeanList().add(0,modelKnotenBean);//Der neue Knoten wird an der ersten Position angezeigt.
//			setAktiveModelBean(getModelBeanList().get(0));//Die erste Position wird ausgewählt
			setAktiveTreeModelBean(getModelBeanList().get(0));
			
			
//			EinlagerungPosBean einlagerungPosBean= (EinlagerungPosBean)getSelectedListModelKnotenBean().getIBean();
//			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ENTNAHME_POS);
//			einlagerungPosBean.setId(neueId);
//			einlagerungPosBean.setStatus(EinlagerungStatus.OFFEN);
//			einlagerungPosBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
//			ModelKnotenBean modelKnotenBean;
//			//ein neuer leerer Knoten wird erzeugt
//			modelKnotenBean = new EinlagerungPosModelKnotenBean(einlagerungPosBean);
//			getModelBeanList().add(0,modelKnotenBean);//Der neue Knoten wird an der ersten Position angezeigt.
//			setAktiveTreeModelBean(getModelBeanList().get(0));//Die erste Position wird ausgewählt
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
			((EinlagerungPosBeanDB)getBeanDB()).saveBean((EinlagerungPosBean)bean); //wichtigste Zeile in dieser Procedure
			//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
			//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
//			Iterator iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
//			while(iterator.hasNext()){
//				ModelKnotenBean modelKnotenBean = (ModelKnotenBean)iterator.next();
//				modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
//				EinlagerungPosBean einlagerungPosBean = (EinlagerungPosBean)modelKnotenBean.getIBean();
//				try{
//					((EinlagerungPosBeanDB)getBeanDB()).getEinlagerungPosBeanDB().saveBean(einlagerungPosBean);
//				}catch(Exception e){
//					Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Fehler:"+e.getMessage());
//					modelKnotenBean.getFehlerList().add(fehler); //Den Fehler in der Position speichern.
//					e.printStackTrace();
//					throw e;
//				}
//			}

			
			//Artikel-Menge wird von der Lagerposition abgebucht.
			if (((EinlagerungPosBean)bean).getStatus().equals(EinlagerungStatus.BESTAETIGT)){
				einlagerungBestaetigen((EinlagerungPosBean)bean);
			}
			
			//Daten neu einlesen
			leseSatzAnhandIDneuEin((EinlagerungPosBean)bean);
			
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
//		EinlagerungPosBean einlagerungBeanNeu = ((EinlagerungPosBeanDB)getBeanDB()).sucheAnhandID(((EinlagerungPosBean)bean).getId()); //Bestellanforderung wird neu aus DB gelesen
		EinlagerungPosBean einlagerungPosBeanNeu = (EinlagerungPosBean)((EinlagerungPosBeanDB)getBeanDB()).selectAnhandID(((EinlagerungPosBean)bean).getId(), 0, null); //Bestellanforderung wird neu aus DB gelesen
		bean.getModelKnotenBean().setIBean(einlagerungPosBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
//		//Die neue Bestellanforderung(frisch aus Dateeinlagerungnk ersetzt die alte gespeicherte EinlagerungPosBean)
//		ArrayList<EinlagerungPosBean> einlagerungPositionen = ((EinlagerungPosBeanDB)getBeanDB()).getEinlagerungPosBeanDB().sucheAnhandEinlagerungPosBean((EinlagerungPosBean)bean);
//		setEinlagerungPositionen(einlagerungPositionen,(EinlagerungModelKnotenBean) getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
		
	}


	@Override
	public void loescheSatz(Bean bean)  {
		getFehlerList().clear();
		
		if (bean!=null && ((EinlagerungPosBean)bean).getStatus()==EinlagerungStatus.GELOESCHT){
			getFehlerList().add(new Fehler(39,FehlerTyp.INFO));
			benachrichtigeListBeobachter();
			return;
		}
		
		switch (bean.getBeanDBStatus()){
		case INSERT : {
			loescheBeanAusModel((EinlagerungPosBean)bean, true);
			break;
		}
		case FEHLERHAFT : {
			getFehlerList().add(new Fehler(25, FehlerTyp.FEHLER,"Falscher Status der Bean beim Löschen. Status:"+BeanDBStatus.JavaToString(bean.getBeanDBStatus())));
			Log.log().severe("Falscher Status");
			break;
		} 
		case SELECT : ; 
		case UPDATE : {
				try {
					LagerConnection.startTransaction(); //Mitteilung an die DB, dass ab hier eine Transaktion beginnt.
					((EinlagerungPosBean)bean).setStatus(EinlagerungStatus.GELOESCHT);//Logischer Status in der Anwendung
					if (bean.getBeanDBStatus()!=BeanDBStatus.INSERT){
						bean.setBeanDBStatus(BeanDBStatus.UPDATE);
					}
					((EinlagerungPosBeanDB)getBeanDB()).saveBean((EinlagerungPosBean)bean); //wichtigste Zeile in dieser Procedure
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
			EinlagerungPosBean einlagerungBeanTemp = null;
			if(modelKnotenBean.getIBean() instanceof EinlagerungPosBean){
				einlagerungBeanTemp = (EinlagerungPosBean)modelKnotenBean.getIBean();
			}else{
				einlagerungBeanTemp = null;
				Log.log().severe("Fehler! Unerwartete Klasse:"+modelKnotenBean.getIBean().getClass().getName());
			}
			if (bean.equals(einlagerungBeanTemp)){
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

	public void bestaetigeEinlagerungPosition() {
		EinlagerungPosBean einlagerungPosBean = (EinlagerungPosBean) getSelectedListModelKnotenBean().getIBean();
		einlagerungPosBean.setStatus(EinlagerungStatus.BESTAETIGT);
		benachrichtigeBeobachter();
	}

	public BestandspackstueckBeanDB getBestandspackstueckBeanDB() {
		if(bestandspackstueckBeanDB==null){
			bestandspackstueckBeanDB=new BestandspackstueckBeanDB();
		}
		return bestandspackstueckBeanDB;
	}

	public void setBestandspackstueckBeanDB(
			BestandspackstueckBeanDB bestandspackstueckBeanDB) {
		this.bestandspackstueckBeanDB = bestandspackstueckBeanDB;
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

//	public void erzeugeNeueEinlagerungPosition()  {
//		getFehlerList().clear();
//		
//		try {
//		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
//		EinlagerungPosModelKnotenBean einlagerungPosModelKnotenBean = new EinlagerungPosModelKnotenBean(getSelectedListModelKnotenBean());
//		EinlagerungPosBean einlagerungPosBean = new EinlagerungPosBean(); //Daten im Knoten
//		Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ENTNAHME_POS);
//		einlagerungPosBean.setId(neueId);
//		einlagerungPosBean.setBeanDBStatus(BeanDBStatus.INSERT);
//		einlagerungPosBean.setEinlagerungPosBean((EinlagerungPosBean)getSelectedListModelKnotenBean().getIBean());
//		einlagerungPosModelKnotenBean.setIBean(einlagerungPosBean);
//		getSelectedListModelKnotenBean().getKinderList().add(einlagerungPosModelKnotenBean);
//		//Beobachter werden benachrichtigt
//		benachrichtigeDetailsBeobachter();
//		benachrichtigeTreeBeobachter();
//		} catch (BenutzerOberflacheLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (IdLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
//			e.printStackTrace();
//		}
//	}
//
//	public void loescheEinlagerungPosition(de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean zuLoeschendeEinlagerungPosBean) {
//		getFehlerList().clear();
//		
//		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
//		
//		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
//			ModelKnotenBean modelKnotenBean = iterator.next();
//			de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean einlagerungPosBean = (de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean)modelKnotenBean.getIBean();
//			if(einlagerungPosBean.getId()==zuLoeschendeEinlagerungPosBean.getId()){
//				switch (zuLoeschendeEinlagerungPosBean.getBeanDBStatus()){
//				case INSERT : {
//					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
//					break;
//				}
//				case SELECT : zuLoeschendeEinlagerungPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
//				case UPDATE : zuLoeschendeEinlagerungPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
//				default : zuLoeschendeEinlagerungPosBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
//				}
//				abbruch = true;
//			}
//		}
//		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
//	}


	private void einlagerungBestaetigen(EinlagerungPosBean bean) throws SQLException, LagerException {
		if (bean != null){
			//Einlagerung - Daten aus DB neu einlesen, aber 
			//nicht die Original-Bean
			EinlagerungPosBean einlagerungPosBean = (EinlagerungPosBean)((EinlagerungPosBeanDB)getBeanDB()).selectAnhandID(bean.getId(), 0, null); 
			BestandspackstueckSuchBean iBestandspackstueckSuchBean = new BestandspackstueckSuchBean();
			iBestandspackstueckSuchBean.setArtikelBean(einlagerungPosBean.getArtikelBean());
			iBestandspackstueckSuchBean.setPositionBean(einlagerungPosBean.getPositionBean());
			ArrayList<Bean> beans = getBestandspackstueckBeanDB().selectAnhandSuchBean(iBestandspackstueckSuchBean, 0, null);
			if (beans.size()!=0){
				BestandspackstueckBean bestandspackstueckBean = (BestandspackstueckBean)beans.get(0);
				Integer bMenge = bestandspackstueckBean.getMenge();
				Integer eMenge = einlagerungPosBean.getMenge();
				bestandspackstueckBean.setMenge(bMenge+eMenge);
				getBestandspackstueckBeanDB().saveBean(bestandspackstueckBean);
			}else{
				throw new LagerException("Einlagerung darf hier nicht verbucht werden. Bitte zuerst die Position anlegen!");
			}
//			for(IBean ibean : beans){
//				if (ibean instanceof BestandspackstueckBean){
//					BestandspackstueckBean bestandspackstueckBean = (BestandspackstueckBean)ibean;
//					if (bestandspackstueckBean.getMenge()>0){
//						Integer bMenge = bestandspackstueckBean.getMenge();
//						Integer eMenge = einlagerungPosBean.getMenge();
//						if (eMenge<=bMenge){
//							//normalfall
//							bestandspackstueckBean.setMenge(bMenge+eMenge);
//							getBestandspackstueckBeanDB().saveBean(bestandspackstueckBean);
//						}else{
//							//menge reicht nicht aus
//							throw new LagerException("Menge reicht nicht aus!");
//						}
//					}else{
//						//menge reicht nicht aus
//						throw new LagerException("Menge reicht nicht aus oder ist nicht vorhanden.");
//					}
//				}
//			}
		}else{
			new LagerException("Einlagerung kann nicht ausgeführt werden. Einlagerungbean = null");
		}
		
	}



}
