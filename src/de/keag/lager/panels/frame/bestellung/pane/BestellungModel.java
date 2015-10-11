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
package de.keag.lager.panels.frame.bestellung.pane;

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
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.BestellungBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.bestellung.BestellungStatus;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.bestellung.BestellungModelKnotenBean;
import de.keag.lager.panels.frame.bestellung.BestellungPosBean;
import de.keag.lager.panels.frame.bestellung.BestellungPosModelKnotenBean;
import de.keag.lager.panels.frame.bestellung.BestellungSuchBean;


public class BestellungModel extends Model {
	


	protected BestellungModel() {
		super();
		initialize();
	}

	private void initialize() {
	}

	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new BestellungModelKnotenBean((BestellungBean)iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new BestellungSuchBean();
		}
		return iSuchBean;
	}



	@Override
	protected BeanDB getBeanDB() {
		if (beanDB == null) {
			beanDB = new BestellungBeanDB();
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
					ArrayList<BestellungPosBean> baPositionen = 
					((BestellungBeanDB)getBeanDB()).
						getBestellungPosBeanDB().
							sucheAnhandBestellungBean(((BestellungModelKnotenBean) getAktiveTreeModelBean()).getIBean(), 0
					);
					setBaPositionen(baPositionen,(BestellungModelKnotenBean) getAktiveTreeModelBean());
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

	private void setBaPositionen(ArrayList<BestellungPosBean> baPositionen,
			BestellungModelKnotenBean baModelKnotenBean)
			throws BenutzerOberflacheLagerException {
		// Alle Kinder werden rückwärts durchgelaufen.
		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
		int posAnzahl = 0;
		for (int i = baModelKnotenBean.getKinderList().size() - 1; i >= 0; i--) {
			ModelKnotenBean modelKnotenBean = baModelKnotenBean.getKinderList()
					.get(i);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTELLUNGSPOSITION) {
				// Wenn aktuelle bestehende Position ausgetauscht werden soll
				// und kann
				if (i <= baPositionen.size() - 1) {
					//der Knoten wird nochmal verwendet. Initialisieren.
					modelKnotenBean.initialize();
					// nächste neue Bean wird ermittelt
					BestellungPosBean baPosBean = baPositionen.get(posAnzahl);
					// laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
					posAnzahl++;
					// neue Bean wird übernommen.
					modelKnotenBean.setIBean(baPosBean);
				} else {
					// löschen der alten ModelBeanPosition. Wir brauchen diese
					// nicht mehr.
					baModelKnotenBean.getKinderList().remove(modelKnotenBean);
				}
			} else {
				// andere Kinder interessieren uns nicht.
			}
		}
		// Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
		// wurden.
		if (posAnzahl < baPositionen.size()) {
			for (; posAnzahl < baPositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				BestellungPosBean baPosBean = baPositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new BestellungPosModelKnotenBean(baModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(baPosBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				baModelKnotenBean.getKinderList().add(modelKontenBean);
			}
		}
	}



	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();
		
		try {
			BestellungBean baBean= new BestellungBean();
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BESTELLUNG);
			baBean.setId(neueId);
			baBean.setStatus(BestellungStatus.OFFEN);
			baBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new BestellungModelKnotenBean(baBean);
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
			((BestellungBeanDB)getBeanDB()).saveBean((BestellungBean)bean); //wichtigste Zeile in dieser Procedure
			//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
			//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
			Iterator iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
			while(iterator.hasNext()){
				ModelKnotenBean modelKnotenBean = (ModelKnotenBean)iterator.next();
				modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
				BestellungPosBean baPosBean = (BestellungPosBean)modelKnotenBean.getIBean();
				try{
					((BestellungBeanDB)getBeanDB()).getBestellungPosBeanDB().saveBean(baPosBean);
				}catch(Exception e){
					Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Fehler:"+e.getMessage());
					modelKnotenBean.getFehlerList().add(fehler); //Den Fehler in der Position speichern.
					e.printStackTrace();
					throw e;
				}
			}

			//Daten neu einlesen
			leseSatzAnhandIDneuEin((BestellungBean)bean);
			
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
//		BaBean baBeanNeu = ((BestellungBeanDB)getBeanDB()).sucheAnhandID(((BaBean)bean).getId()); //Bestellanforderung wird neu aus DB gelesen
		BestellungBean baBeanNeu = (BestellungBean)((BestellungBeanDB)getBeanDB()).selectAnhandID(((BestellungBean)bean).getId(), 0, null); //Bestellanforderung wird neu aus DB gelesen
		bean.getModelKnotenBean().setIBean(baBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
		//Die neue Bestellanforderung(frisch aus Datebank ersetzt die alte gespeicherte BaBean)
		ArrayList<BestellungPosBean> baPositionen = ((BestellungBeanDB)getBeanDB()).getBestellungPosBeanDB().sucheAnhandBestellungBean((BestellungBean)bean, 0);
		setBaPositionen(baPositionen,(BestellungModelKnotenBean) getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
		
	}


	@Override
	public void loescheSatz(Bean bean)  {
		getFehlerList().clear();
		
		if (bean!=null && ((BestellungBean)bean).getStatus()==BestellungStatus.GELOESCHT){
			getFehlerList().add(new Fehler(39,FehlerTyp.INFO));
			benachrichtigeListBeobachter();
			return;
		}
		
		switch (bean.getBeanDBStatus()){
		case INSERT : {
			loescheBeanAusModel((BestellungBean)bean, true);
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
					((BestellungBean)bean).setStatus(BestellungStatus.GELOESCHT);//Logischer Status in der Anwendung
					if (bean.getBeanDBStatus()!=BeanDBStatus.INSERT){
						bean.setBeanDBStatus(BeanDBStatus.UPDATE);
					}
					((BestellungBeanDB)getBeanDB()).saveBean((BestellungBean)bean); //wichtigste Zeile in dieser Procedure
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
			BestellungBean baBeanTemp = null;
			if(modelKnotenBean.getIBean() instanceof BestellungBean){
				baBeanTemp = (BestellungBean)modelKnotenBean.getIBean();
			}else{
				baBeanTemp = null;
				Log.log().severe("Fehler! Unerwartete Klasse:"+modelKnotenBean.getIBean().getClass().getName());
			}
			if (bean.equals(baBeanTemp)){
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

	public void erzeugeNeueBaPosition()  {
		getFehlerList().clear();
		
		try {
		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
		BestellungPosModelKnotenBean baPosModelKnotenBean = new BestellungPosModelKnotenBean(getSelectedListModelKnotenBean());
		BestellungPosBean baPosBean = new BestellungPosBean(); //Daten im Knoten
		Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BESTELLUNG_POS);
		baPosBean.setId(neueId);
		baPosBean.setBeanDBStatus(BeanDBStatus.INSERT);
		baPosBean.setBestellungBean((BestellungBean)getSelectedListModelKnotenBean().getIBean());
		baPosModelKnotenBean.setIBean(baPosBean);
		getSelectedListModelKnotenBean().getKinderList().add(baPosModelKnotenBean);
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

	public void loescheBaPosition(BestellungPosBean zuLoeschendeBaPosBean) {
		getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			BestellungPosBean baPosBean = (BestellungPosBean)modelKnotenBean.getIBean();
			if(baPosBean.getId()==zuLoeschendeBaPosBean.getId()){
				switch (zuLoeschendeBaPosBean.getBeanDBStatus()){
				case INSERT : {
					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeBaPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeBaPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeBaPosBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}




}
