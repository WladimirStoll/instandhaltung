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
package de.keag.lager.panels.frame.benutzer.pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.Model;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerBeanDbLagerException;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.BenutzerBeanDB;
import de.keag.lager.db.BenutzerAbteilungBeanDB;
import de.keag.lager.db.BenutzerZugriffsrechtBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungModelKnotenBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerModelKnotenBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.tree.BaTreeView;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;



public class BenutzerModel extends Model{
	// DB-Zugriff
	private BenutzerBeanDB benutzerBeanDB;
	private BenutzerAbteilungBeanDB benutzerAbteilungBeanDB;
	private BenutzerZugriffsrechtBeanDB benutzerZugriffsrechtBeanDB;
	 

	protected BenutzerModel() {
		super();
		initialize();
	}

	private void initialize() {
	}


	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new BenutzerSuchBean();
		}
		return iSuchBean;
	}

	@Override
	protected BeanDB getBeanDB() {
		if (benutzerBeanDB == null) {
			benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
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
				try {
					// Datenbankzugriff: Wir suchen alle Positionen zu der
					// gewählten Anforderung.
					ArrayList<BenutzerAbteilungBean> benutzerAbteilungBeans = 
						getBenutzerAbteilungBeanDB().sucheAnhandBenutzerBean(
								((BenutzerModelKnotenBean) getAktiveTreeModelBean()).getIBean()
						);
					
					ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans = 
						getBenutzerZugriffsrechtBeanDB().sucheAnhandBenutzerBean(
								((BenutzerModelKnotenBean) getAktiveTreeModelBean()).getIBean()
						);
					
					
					setBenutzerAbteilungenUndZugriffsrechte(
							benutzerAbteilungBeans,
							benutzerZugriffsrechtBeans,
							(BenutzerModelKnotenBean) getAktiveTreeModelBean());
				} catch (BenutzerOberflacheLagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
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

	private void setBenutzerAbteilungenUndZugriffsrechte(
			ArrayList<BenutzerAbteilungBean> benutzerAbteilungPositionen,
			ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtPositionen,
			BenutzerModelKnotenBean benutzerModelKnotenBean)
			throws BenutzerOberflacheLagerException {
		// Alle Kinder werden rückwärts durchgelaufen.
		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
		int posAnzahl = 0;
		for (int i = benutzerModelKnotenBean.getKinderList().size() - 1; i >= 0; i--) {
			ModelKnotenBean modelKnotenBean = benutzerModelKnotenBean.getKinderList().get(i);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ABTEILUNG) {
				// Wenn aktuelle bestehende Position ausgetauscht werden soll
				// und kann
				if (i <= benutzerAbteilungPositionen.size() - 1) {
					//der Knoten wird nochmal verwendet. Initialisieren.
					modelKnotenBean.initialize();
					// nächste neue Bean wird ermittelt
					BenutzerAbteilungBean benutzerPosBean = benutzerAbteilungPositionen.get(posAnzahl);
					// laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
					posAnzahl++;
					// neue Bean wird übernommen.
					modelKnotenBean.setIBean(benutzerPosBean);
				} else {
					// löschen der alten ModelBeanPosition. Wir brauchen diese
					// nicht mehr.
					benutzerModelKnotenBean.getKinderList().remove(modelKnotenBean);
				}
			} else {
				//den Rest löschen
				benutzerModelKnotenBean.getKinderList().remove(modelKnotenBean);
			}
		}
		// Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
		// wurden.
		if (benutzerAbteilungPositionen !=null && posAnzahl < benutzerAbteilungPositionen.size()) {
			for (; posAnzahl < benutzerAbteilungPositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				BenutzerAbteilungBean benutzerPosBean = benutzerAbteilungPositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new BenutzerAbteilungModelKnotenBean(benutzerModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(benutzerPosBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				benutzerModelKnotenBean.getKinderList().add(modelKontenBean);
			}
		}
		
		//An dieser Stelle sind alle Abteilungen(BenutzerAbteilung)in der Liste  aufgenommen.
		
		//Jetzt müssen noch die Zugriffsreichte in die Liste aufgenommen werden.
		if (benutzerZugriffsrechtPositionen!=null){
			posAnzahl = 0;
			for (; posAnzahl < benutzerZugriffsrechtPositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				BenutzerZugriffsrechtBean benutzerZugriffsrechtBean = benutzerZugriffsrechtPositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new BenutzerZugriffsrechtModelKnotenBean(benutzerModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(benutzerZugriffsrechtBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				benutzerModelKnotenBean.getKinderList().add(modelKontenBean);
			}
			
		}
		
		
	}

	private BenutzerAbteilungBeanDB getBenutzerAbteilungBeanDB() {
		if (benutzerAbteilungBeanDB == null) {
			benutzerAbteilungBeanDB = new BenutzerAbteilungBeanDB();
		}
		return benutzerAbteilungBeanDB;
	}
	
	private BenutzerZugriffsrechtBeanDB getBenutzerZugriffsrechtBeanDB() {
		if (benutzerZugriffsrechtBeanDB == null) {
			benutzerZugriffsrechtBeanDB = new BenutzerZugriffsrechtBeanDB();
		}
		return benutzerZugriffsrechtBeanDB;
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
//			if (modelKnotenBean.getIBean() instanceof BenutzerBean){
//				return modelKnotenBean;
//			}else{
//				if (modelKnotenBean.getIBean() instanceof BenutzerAbteilungBean){
//					return modelKnotenBean.getVater();
//				}else{
//					Log.log().severe("falsche Klasse:"+modelKnotenBean.getIBean());
//					return null;
//				}
//			}
//		}
//	}

	
	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();
		
		
		try {
			BenutzerBean benutzerBean= new BenutzerBean();
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BENUTZER);
			benutzerBean.setId(neueId);
		//	benutzerBean.setStatus(AnforderungStatus.OFFEN);
			benutzerBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new BenutzerModelKnotenBean(benutzerBean);
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
				speichereBenutzerAbteilungen(bean);
				getBeanDB().saveBean((BenutzerBean)bean); //wichtigste Zeile in dieser Procedure
				loescheBeanAusModel(bean, false);
				break;
			case INSERT_DELETE:
				loescheBeanAusModel(bean, false);
				break;
			default:
				getBeanDB().saveBean((BenutzerBean)bean); //wichtigste Zeile in dieser Procedure
				speichereBenutzerAbteilungen(bean);
				leseSatzAnhandIDneuEin((BenutzerBean)bean);
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

	private void speichereBenutzerAbteilungen(Bean bean) throws Exception {
		//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
		//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
		while(iterator.hasNext()){
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
			try{
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof BenutzerAbteilungBean){
					BenutzerAbteilungBean benutzerAbteilungBean = (BenutzerAbteilungBean)ibean; 
					if (benutzerAbteilungBean.getBeanDBStatus() == BeanDBStatus.DELETE){
						//Löschen
						benutzerAbteilungBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
			        getBenutzerAbteilungBeanDB().saveBean(benutzerAbteilungBean);
				}
				if (ibean instanceof BenutzerZugriffsrechtBean){
					BenutzerZugriffsrechtBean benutzerZugriffsrechtBean = (BenutzerZugriffsrechtBean)ibean; 
					if (benutzerZugriffsrechtBean.getBeanDBStatus() == BeanDBStatus.DELETE){
						//Löschen
						benutzerZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
			        getBenutzerZugriffsrechtBeanDB().saveBean(benutzerZugriffsrechtBean);
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
		
		BenutzerBean benutzerBean = (BenutzerBean) bean;
		BenutzerBean benutzerBeanNeu = (BenutzerBean)getBeanDB().selectAnhandID(benutzerBean.getId(), 0, null); //Bestellanforderung wird neu aus DB gelesen
		benutzerBean.getModelKnotenBean().setIBean(benutzerBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
		
		//Die neue Bestellanforderung(frisch aus Datebank ersetzt die alte gespeicherte BenutzerBean)
		ArrayList<BenutzerAbteilungBean> benutzerAbteilungen = 
		((BenutzerAbteilungBeanDB)getBenutzerAbteilungBeanDB()).sucheAnhandBenutzerBean(benutzerBean);
		
		ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans = 
			((BenutzerZugriffsrechtBeanDB)getBenutzerZugriffsrechtBeanDB()).sucheAnhandBenutzerBean(benutzerBean);
		
		setBenutzerAbteilungenUndZugriffsrechte(benutzerAbteilungen,benutzerZugriffsrechtBeans,(BenutzerModelKnotenBean) getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
		
	}

	@Override
	public void loescheSatz(Bean bean)  {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		switch (bean.getBeanDBStatus()){
		case INSERT : {
			loescheBeanAusModel(bean, true);
			//LoescheObjektAusDerListe((BenutzerBean)bean);
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
					getBeanDB().saveBean((BenutzerBean)bean); //wichtigste Zeile in dieser Procedure
					loescheBeanAusModel(bean, true);
//					LoescheObjektAusDerListe((BenutzerBean)bean);
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

	

	
	public void erzeugeNeueBenutzerAbteilung()  {
		getFehlerList().clear();
		
		try {
		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
		BenutzerBean benutzerBean = (BenutzerBean)getSelectedListModelKnotenBean().getIBean();
		BenutzerAbteilungModelKnotenBean benutzerAbteilungModelKnotenBean = new BenutzerAbteilungModelKnotenBean(getSelectedListModelKnotenBean());
		BenutzerAbteilungBean benutzerAbteilungBean = new BenutzerAbteilungBean(); //Daten im Knoten
		benutzerAbteilungBean.setBenutzer(benutzerBean); //aktueller User aus der Liste
		benutzerAbteilungBean.setAbteilung(new AbteilungBean()); //neu
		benutzerAbteilungBean.setBeanDBStatus(BeanDBStatus.INSERT);
		benutzerAbteilungModelKnotenBean.setIBean(benutzerAbteilungBean);
		getSelectedListModelKnotenBean().getKinderList().add(0,benutzerAbteilungModelKnotenBean);
		benutzerBean.getBenutzerAbteilungBeans().add(0,benutzerAbteilungBean);
		//Beobachter werden benachrichtigt
		benachrichtigeDetailsBeobachter();
		benachrichtigeTreeBeobachter();
//		benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		}
	}

	public void loescheBenutzerAbteilung(BenutzerAbteilungBean zuLoeschendeBenutzerPosBean) {
		getFehlerList().clear();
		zuLoeschendeBenutzerPosBean.getFehlerList().clear();

		
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof BenutzerAbteilungBean && modelKnotenBean.getIBean().equals(zuLoeschendeBenutzerPosBean)){
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
	
	public void loescheBenutzerZugriffsrecht(BenutzerZugriffsrechtBean zuLoeschendeBenutzerZugriffsrechtBean) {
		getFehlerList().clear();
		zuLoeschendeBenutzerZugriffsrechtBean.getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof BenutzerZugriffsrechtBean && modelKnotenBean.getIBean().equals(zuLoeschendeBenutzerZugriffsrechtBean)){
				switch (zuLoeschendeBenutzerZugriffsrechtBean.getBeanDBStatus()){
				case INSERT : {
					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeBenutzerZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeBenutzerZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeBenutzerZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
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
//		if (getAktiveTreeModelBean() instanceof BenutzerAbteilungModelKnotenBean){
//			benachrichtigeDetailsBeobachter();
//		}else if(getAktiveTreeModelBean() instanceof BenutzerModelKnotenBean){
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
//			errors.add(0,new Fehler(25,FehlerTyp.FEHLER,"Fehler im Benutzersatz, id=" + ((BenutzerBean)getSelectedListModelKnotenBean().getIBean()).getId()));
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
			return new BenutzerModelKnotenBean((BenutzerBean)iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	public void erzeugeNeueBenutzerZugriffsrecht() {
		getFehlerList().clear();
		try {
			// neuer BestellanfoderungsPositions-Knoten wird erzeugt
			BenutzerBean benutzerBean = (BenutzerBean) getSelectedListModelKnotenBean()
					.getIBean();
			BenutzerZugriffsrechtModelKnotenBean benutzerZugriffsrectModelKnotenBean = 
				new BenutzerZugriffsrechtModelKnotenBean(getSelectedListModelKnotenBean());
			BenutzerZugriffsrechtBean benutzerZugriffsrechtBean = new BenutzerZugriffsrechtBean(); // Daten
																						// im
																						// Knoten
			benutzerZugriffsrechtBean.setBenutzer(benutzerBean); // aktueller User
																// aus der Liste
			benutzerZugriffsrechtBean.setZugriffsrecht(new ZugriffsrechtBean()); // neu
			benutzerZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.INSERT);
			benutzerZugriffsrectModelKnotenBean.setIBean(benutzerZugriffsrechtBean);
			getSelectedListModelKnotenBean().getKinderList().add(
					benutzerZugriffsrectModelKnotenBean);
			benutzerBean.getBenutzerZugriffsrechtBeans().add(benutzerZugriffsrechtBean);
			// Beobachter werden benachrichtigt
			benachrichtigeDetailsBeobachter();
			benachrichtigeTreeBeobachter();
			// benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		}
	}
	
	
//	@Override
//	public void addISuchBeobachters(ISuchBeobachter iBenutzerSuchPaneBeobachter) {
//		getiBenutzerSuchPaneBeobachters().add(iBenutzerSuchPaneBeobachter);
//		benachrichtigeSuchBeobachter();
//	}







}
