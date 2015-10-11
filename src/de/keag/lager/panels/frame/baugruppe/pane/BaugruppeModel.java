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
package de.keag.lager.panels.frame.baugruppe.pane;

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
import de.keag.lager.db.BaugruppeArtikelBeanDB;
import de.keag.lager.db.BaugruppeBeanDB;
import de.keag.lager.db.BenutzerZugriffsrechtBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.tree.BaTreeView;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;



public class BaugruppeModel extends Model{
	// DB-Zugriff
	private BaugruppeBeanDB baugruppeBeanDB;
	private BaugruppeArtikelBeanDB baugruppeArtikelBeanDB;
	 

	protected BaugruppeModel() {
		super();
		initialize();
	}

	private void initialize() {
	}


	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new BaugruppeSuchBean();
		}
		return iSuchBean;
	}

	@Override
	protected BeanDB getBeanDB() {
		if (baugruppeBeanDB == null) {
			baugruppeBeanDB = new BaugruppeBeanDB();
		}
		return baugruppeBeanDB;
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
					BaugruppeBean baugruppeBean = (BaugruppeBean)getSelectedListModelKnotenBean().getIBean();
					baugruppeBean = (BaugruppeBean)getBeanDB().selectAnhandID(baugruppeBean.getId(), 0, null);
					getSelectedListModelKnotenBean().setIBean(baugruppeBean);

					ArrayList<BaugruppeBean> kinderBaugruppeBeans = 
						((BaugruppeBeanDB)getBeanDB()).sucheKinderBaugruppenBeansAnhandId(
								((BaugruppeModelKnotenBean) getSelectedListModelKnotenBean()).getIBean().getId()		
//								((BaugruppeModelKnotenBean) getAktiveTreeModelBean()).getIBean().getId()		
						);
					
					
					ArrayList<BaugruppeArtikelBean> baugruppeArtikelBeans = 
						getBaugruppeArtikelBeanDB().sucheAnhandBaugruppeBean(
								((BaugruppeModelKnotenBean) getSelectedListModelKnotenBean()).getIBean()
//								((BaugruppeModelKnotenBean) getAktiveTreeModelBean()).getIBean()
						);
					
//					ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans = 
//						getBenutzerZugriffsrechtBeanDB().sucheAnhandBaugruppeBean(
//								((BaugruppeModelKnotenBean) getAktiveTreeModelBean()).getIBean()
//						);
					
					
					setKinderBaugruppenUndArtikelEbene2(
							baugruppeArtikelBeans,
							kinderBaugruppeBeans, 
							(BaugruppeModelKnotenBean) getSelectedListModelKnotenBean()
//							(BaugruppeModelKnotenBean) getAktiveTreeModelBean()
					);
					
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

	private void setKinderBaugruppenUndArtikelEbene2(
			ArrayList<BaugruppeArtikelBean> baugruppeArtikelBeans,
			ArrayList<BaugruppeBean> kinderBaugruppeBeans,
			BaugruppeModelKnotenBean baugruppeModelKnotenBean)
			throws SQLException, LagerException {
		// Alle Kinder werden rückwärts durchgelaufen.
		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
		int posAnzahl = 0;
		
		baugruppeModelKnotenBean.getKinderList().clear();
		
		//Alle Kinder-Baugruppen in die leere Liste aufnehmen
		for (int i = 0; i<kinderBaugruppeBeans.size();i++) {
			BaugruppeModelKnotenBean baugruppeModelKontenBean = new BaugruppeModelKnotenBean(kinderBaugruppeBeans.get(i),baugruppeModelKnotenBean);
			baugruppeModelKnotenBean.getKinderList().add(baugruppeModelKontenBean);
			
			ArrayList<BaugruppeBean> kinderBaugruppeBeansEbene3 = 
				((BaugruppeBeanDB)getBeanDB()).sucheKinderBaugruppenBeansAnhandId(
						kinderBaugruppeBeans.get(i).getId()
				);
			
			ArrayList<BaugruppeArtikelBean> baugruppeArtikelBeansEbene3 = 
				getBaugruppeArtikelBeanDB().sucheAnhandBaugruppeBean(
						kinderBaugruppeBeans.get(i)
				);
			
			setKinderBaugruppenUndArtikelEbene2(baugruppeArtikelBeansEbene3,
												kinderBaugruppeBeansEbene3,
												baugruppeModelKontenBean);
			
		}
		
		//Alle Artikel der Gruppe in die Liste auch aufnehmen
		for (int i = 0; i<baugruppeArtikelBeans.size();i++) {
			ModelKnotenBean modelKontenBean = new BaugruppeArtikelModelKnotenBean(baugruppeArtikelBeans.get(i),baugruppeModelKnotenBean);
			baugruppeModelKnotenBean.getKinderList().add(modelKontenBean);
		}
		
		
	}

	private BaugruppeArtikelBeanDB getBaugruppeArtikelBeanDB() {
		if (baugruppeArtikelBeanDB == null) {
			baugruppeArtikelBeanDB = new BaugruppeArtikelBeanDB();
		}
		return baugruppeArtikelBeanDB;
	}
	

	
	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();
		
		
		try {
			BaugruppeBean baugruppeBean= new BaugruppeBean();
			Integer neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BAUGRUPPE );
			baugruppeBean.setId(neueId);
		//	baugruppeBean.setStatus(AnforderungStatus.OFFEN);
			baugruppeBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new BaugruppeModelKnotenBean(baugruppeBean,null);
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
				getBeanDB().saveBean((BaugruppeBean)bean); //wichtigste Zeile in dieser Procedure
				loescheBeanAusModel(bean, false);
				break;
			default:
				getBeanDB().saveBean((BaugruppeBean)bean); //wichtigste Zeile in dieser Procedure
				speichereBaugruppeBaugruppe(bean);
				leseSatzAnhandIDneuEin((BaugruppeBean)bean);
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

//	private void speichereBaugruppeArtikel(Bean bean) throws Exception {
//		//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
//		//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
//		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
//		while(iterator.hasNext()){
//			ModelKnotenBean modelKnotenBean = iterator.next();
//			modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
//			try{
//				IBean ibean = modelKnotenBean.getIBean();
//				if (ibean instanceof BaugruppeArtikelBean){
//					BaugruppeArtikelBean baugruppeArtikelBean = (BaugruppeArtikelBean)ibean; 
//			        getBaugruppeArtikelBeanDB().saveBean(baugruppeArtikelBean);
//				}
//			}catch(Exception e){
//				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Fehler:"+e.getMessage());
//				modelKnotenBean.getFehlerList().add(fehler); //Den Fehler in der Position speichern.
//				e.printStackTrace();
//				throw e;
//			}
//		}
//	}
	
	private void speichereBaugruppeBaugruppe(Bean bean) throws Exception {
		//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
		//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
		while(iterator.hasNext()){
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
			try{
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof BaugruppeBean){
					BaugruppeBean baugruppeBaugruppeBean = (BaugruppeBean)ibean; 
					if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
						//Wenn Vater gelöscht werden soll, dann soll auch das Kind gelöscht werden.
						baugruppeBaugruppeBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
					if (baugruppeBaugruppeBean.getBeanDBStatus() == BeanDBStatus.DELETE){
						speichereBaugruppeBaugruppe(baugruppeBaugruppeBean); //Rekursives Löschen
						getBeanDB().saveBean(baugruppeBaugruppeBean); //Löschen des aktuellen Kindes
					}else{
						getBeanDB().saveBean(baugruppeBaugruppeBean);
						speichereBaugruppeBaugruppe(baugruppeBaugruppeBean); //Rekursives Speichern
					}
				}
				//Artikel
				if (ibean instanceof BaugruppeArtikelBean){
					BaugruppeArtikelBean baugruppeArtikelBean = (BaugruppeArtikelBean)ibean; 
					if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
						//Wenn Vater gelöscht werden soll, dann soll auch das Kind gelöscht werden.
						baugruppeArtikelBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
			        getBaugruppeArtikelBeanDB().saveBean(baugruppeArtikelBean);
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

		selectKnoten(getSelectedListModelKnotenBeanLaufendeNummer());

		
//		BaugruppeBean baugruppeBean = (BaugruppeBean) bean;
//		BaugruppeBean baugruppeBeanNeu = (BaugruppeBean)getBeanDB().selectAnhandID(baugruppeBean.getId()); //Bestellanforderung wird neu aus DB gelesen
//		baugruppeBean.getModelKnotenBean().setIBean(baugruppeBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
//		
//		//Die neue Bestellanforderung(frisch aus Datebank ersetzt die alte gespeicherte BaugruppeBean)
//		ArrayList<BaugruppeArtikelBean> baugruppeartikelen = 
//		((BaugruppeArtikelBeanDB)getBaugruppeArtikelBeanDB()).sucheAnhandBaugruppeBean(baugruppeBean);
//		
////		ArrayList<baugruppeZugriffsrechtBean> baugruppeZugriffsrechtBeans = 
////			((baugruppeZugriffsrechtBeanDB)getbaugruppeZugriffsrechtBeanDB()).sucheAnhandBaugruppeBean(baugruppeBean);
//		
////		setbaugruppeartikelenUndZugriffsrechte(baugruppeartikelen,baugruppeZugriffsrechtBeans,(BaugruppeModelKnotenBean) getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
	}

	@Override
	public void loescheSatz(Bean bean)  {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		switch (bean.getBeanDBStatus()){
		case INSERT : {
			loescheBeanAusModel(bean, true);
			//LoescheObjektAusDerListe((BaugruppeBean)bean);
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
					getBeanDB().saveBean((BaugruppeBean)bean); //wichtigste Zeile in dieser Procedure
					loescheBeanAusModel(bean, true);
//					LoescheObjektAusDerListe((BaugruppeBean)bean);
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

	

	

	public void loescheBaugruppeArtikel(BaugruppeArtikelBean zuLoeschendeBaugruppePosBean) {
		getFehlerList().clear();
		zuLoeschendeBaugruppePosBean.getFehlerList().clear();

		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof BaugruppeArtikelBean && modelKnotenBean.getIBean().equals(zuLoeschendeBaugruppePosBean)){
				switch (zuLoeschendeBaugruppePosBean.getBeanDBStatus()){
				case INSERT : {
					getAktiveTreeModelBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeBaugruppePosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeBaugruppePosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeBaugruppePosBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}
	
	public void loescheBaugruppeBaugruppe(BaugruppeBean zuLoeschendeBaugruppeBean) {
		getFehlerList().clear();
		zuLoeschendeBaugruppeBean.getFehlerList().clear();

		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof BaugruppeBean 
					&& modelKnotenBean.getIBean().equals(zuLoeschendeBaugruppeBean)){
				switch (zuLoeschendeBaugruppeBean.getBeanDBStatus()){
				case INSERT : {
					getAktiveTreeModelBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeBaugruppeBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeBaugruppeBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeBaugruppeBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}
	
	
//	public void loescheBaugruppeArtikel(BaugruppeArtikelBean zuLoeschendebaugruppeZugriffsrechtBean) {
//		getFehlerList().clear();
//		zuLoeschendebaugruppeZugriffsrechtBean.getFehlerList().clear();
//		
//		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean().getKinderList().iterator();
//		
//		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
//			ModelKnotenBean modelKnotenBean = iterator.next();
//			if (modelKnotenBean.getIBean() instanceof BaugruppeArtikelBean && modelKnotenBean.getIBean().equals(zuLoeschendebaugruppeZugriffsrechtBean)){
//				switch (zuLoeschendebaugruppeZugriffsrechtBean.getBeanDBStatus()){
//				case INSERT : {
//					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
//					break;
//				}
//				case SELECT : zuLoeschendebaugruppeZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
//				case UPDATE : zuLoeschendebaugruppeZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
//				default : zuLoeschendebaugruppeZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
//				}
//				abbruch = true;
//			}
//		}
//		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
//	}
//	
	
	


	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new BaugruppeModelKnotenBean((BaugruppeBean)iBean,null);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	
	public void erzeugeNeueBaugruppeArtikel()  {
		getFehlerList().clear();
		
		try {
			BaugruppeBean baugruppeBean = (BaugruppeBean)getAktiveTreeModelBean().getIBean();
			BaugruppeArtikelBean baugruppeArtikelBean = new BaugruppeArtikelBean(); //Daten im Knoten
		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
			BaugruppeArtikelModelKnotenBean baugruppeArtikelModelKnotenBean = 
				new BaugruppeArtikelModelKnotenBean(baugruppeArtikelBean,getAktiveTreeModelBean());
		baugruppeArtikelBean.setBaugruppe(baugruppeBean); //Artikel in die Gruppe einfügen
		baugruppeArtikelBean.setArtikel(new ArtikelBean()); //neu
		baugruppeArtikelBean.setBeanDBStatus(BeanDBStatus.INSERT);
		baugruppeArtikelModelKnotenBean.setIBean(baugruppeArtikelBean);
		getAktiveTreeModelBean().getKinderList().add(baugruppeArtikelModelKnotenBean);
		baugruppeBean.getBaugruppeArtikelBeans().add(baugruppeArtikelBean);
		//Beobachter werden benachrichtigt
		benachrichtigeDetailsBeobachter();
		benachrichtigeTreeBeobachter();
//		benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		}
	}
	
	public void erzeugeNeueBaugruppeBaugruppe()  {
		getFehlerList().clear();
		
		try {
			//aktuell gewählte Baugruppe im Tree (oder in details)
			ModelKnotenBean modelKnotenBean = getAktiveTreeModelBean();
			BaugruppeBean neuBaugruppeBean = new BaugruppeBean();
			Integer neueId;
			neueId = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BAUGRUPPE);
			neuBaugruppeBean.setId(neueId);
			BaugruppeModelKnotenBean neuBaugruppeModelKnotenBean = 
				new BaugruppeModelKnotenBean(neuBaugruppeBean, modelKnotenBean);
			modelKnotenBean.getKinderList().add(0,neuBaugruppeModelKnotenBean);
			neuBaugruppeBean.setVaterBaugruppe((BaugruppeBean)modelKnotenBean.getIBean());
			neuBaugruppeBean.setBeanDBStatus(BeanDBStatus.INSERT);
			benachrichtigeDetailsBeobachter();
			benachrichtigeTreeBeobachter();
		} catch (LagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (SQLException e1) {
			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e1.getMessage()));
			e1.printStackTrace();
		}
		
		
		
//		try {
			// neuer BestellanfoderungsPositions-Knoten wird erzeugt
//			BaugruppeBean baugruppeBean = (BaugruppeBean) getSelectedListModelKnotenBean()
//					.getIBean();
//			baugruppeZugriffsrechtModelKnotenBean baugruppeZugriffsrectModelKnotenBean = 
//				new baugruppeZugriffsrechtModelKnotenBean(getSelectedListModelKnotenBean());
//			baugruppeZugriffsrechtBean baugruppeZugriffsrechtBean = new baugruppeZugriffsrechtBean(); // Daten
																						// im
																						// Knoten
//			baugruppeZugriffsrechtBean.setbaugruppe(baugruppeBean); // aktueller User
//																// aus der Liste
//			baugruppeZugriffsrechtBean.setZugriffsrecht(new ZugriffsrechtBean()); // neu
//			baugruppeZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.INSERT);
//			baugruppeZugriffsrectModelKnotenBean.setIBean(baugruppeZugriffsrechtBean);
//			getSelectedListModelKnotenBean().getKinderList().add(
//					baugruppeZugriffsrectModelKnotenBean);
//			baugruppeBean.getbaugruppeZugriffsrechtBeans().add(baugruppeZugriffsrechtBean);
//			// Beobachter werden benachrichtigt
//			benachrichtigeDetailsBeobachter();
//			benachrichtigeTreeBeobachter();
			// benachrichtigeTreePaneBeobachter();
//		} catch (LagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		}
	}

	
	
//	@Override
//	public void addISuchBeobachters(ISuchBeobachter ibaugruppeSuchPaneBeobachter) {
//		getibaugruppeSuchPaneBeobachters().add(ibaugruppeSuchPaneBeobachter);
//		benachrichtigeSuchBeobachter();
//	}







}
