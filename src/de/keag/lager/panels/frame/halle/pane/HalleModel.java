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
package de.keag.lager.panels.frame.halle.pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
import de.keag.lager.core.main.Run;
import de.keag.lager.db.EbeneBeanDB;
import de.keag.lager.db.EtageBeanDB;
import de.keag.lager.db.HalleBeanDB;
import de.keag.lager.db.PositionBeanDB;
import de.keag.lager.db.SaeuleBeanDB;
import de.keag.lager.db.ZeileBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.ebene.EbeneModelKnotenBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageModelKnotenBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.model.HalleModelKnotenBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.position.model.PositionModelKnotenBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleModelKnotenBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.zeile.model.ZeileModelKnotenBean;




public class HalleModel extends Model{
	// DB-Zugriff
	private HalleBeanDB halleBeanDB;
	private EtageBeanDB etageBeanDB;
	private ZeileBeanDB zeileBeanDB;
	private PositionBeanDB positionBeanDB;
	private EbeneBeanDB ebeneBeanDB;
	private SaeuleBeanDB saeuleBeanDB;
	 

	protected HalleModel() {
		super();
		initialize();
	}

	private void initialize() {
	}


	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new HalleSuchBean();
		}
		return iSuchBean;
	}

	@Override
	protected BeanDB getBeanDB() {
		if (halleBeanDB == null) {
			halleBeanDB = new HalleBeanDB();
		}
		return halleBeanDB;
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
				setAktiveTreeModelBean(getModelBeanList()
						.get(gewaehlteZeilenNummer));
				try {
					
					leseSatzAnhandIDneuEin(((HalleModelKnotenBean) getAktiveTreeModelBean()).getIBean());
					
//					// Datenbankzugriff: Wir suchen alle Positionen zu der
//					// gewählten Anforderung.
//					ArrayList<EtageBean> etageBeans = 
//						getEtageBeanDB().sucheAnhandHalleBean(
//								((HalleModelKnotenBean) getAktiveTreeModelBean()).getIBean()
//						);
//					
//					ArrayList<ZeileBean> zeileBeans = 
//						getZeileBeanDB().sucheAnhandHalleBean(
//								((HalleModelKnotenBean) getAktiveTreeModelBean()).getIBean()
//						);
//					
//					setEtageenUndZeilen(
//							etageBeans,
//							zeileBeans,
//							(HalleModelKnotenBean) getAktiveTreeModelBean());
					
					
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

	private void setEtageenUndZeilen(
			ArrayList<EtageBean> etagePositionen,
			ArrayList<ZeileBean> zeilePositionen,
			HalleModelKnotenBean halleModelKnotenBean)
			throws BenutzerOberflacheLagerException {
		// Alle Kinder werden rückwärts durchgelaufen.
		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
		
		int posAnzahl = 0;
		for (int i = halleModelKnotenBean.getKinderList().size() - 1; i >= 0; i--) {
			ModelKnotenBean modelKnotenBean = halleModelKnotenBean.getKinderList().get(i);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ETAGE) {
				// Wenn aktuelle bestehende Position ausgetauscht werden soll
				// und kann
				if (i <= etagePositionen.size() - 1) {
					//der Knoten wird nochmal verwendet. Initialisieren.
					modelKnotenBean.initialize();
					// nächste neue Bean wird ermittelt
					EtageBean etagePosBean = etagePositionen.get(posAnzahl);
					// laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
					posAnzahl++;
					// neue Bean wird übernommen.
					modelKnotenBean.setIBean(etagePosBean);
				} else {
					// löschen der alten ModelBeanPosition. Wir brauchen diese
					// nicht mehr.
					halleModelKnotenBean.getKinderList().remove(modelKnotenBean);
				}
			} else {
				//den Rest löschen
				halleModelKnotenBean.getKinderList().remove(modelKnotenBean);
			}
		}
		// Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
		// wurden.
		if (etagePositionen !=null && posAnzahl < etagePositionen.size()) {
			for (; posAnzahl < etagePositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				EtageBean etageBean = etagePositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean modelKontenBean = new EtageModelKnotenBean(halleModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				modelKontenBean.setIBean(etageBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				halleModelKnotenBean.getKinderList().add(modelKontenBean);
			}
		}
		
		//An dieser Stelle sind alle Abteilungen(Etage)in der Liste  aufgenommen.
		
		//Jetzt müssen noch die Zugriffsreichte in die Liste aufgenommen werden.
		if (zeilePositionen!=null){
			for (posAnzahl = 0; posAnzahl < zeilePositionen.size(); posAnzahl++) {
				// nächste nicht aufgenommene Position finden.
				ZeileBean zeileBean = zeilePositionen.get(posAnzahl);
				// neuer Knoten wird erstellt.
				ModelKnotenBean zeileModelKontenBean = new ZeileModelKnotenBean(halleModelKnotenBean);
				// ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
				zeileModelKontenBean.setIBean(zeileBean);
				// neuer Knoten(samt Inhalten) wird in die KinderListe
				// übernommen.
				boolean etageOK;
				//keine Etage ist gefunden.
				etageOK = false;
				if (!zeileBean.getEtageBean().getId().equals(0)){ //Etagennummer ist ungleich 0
					//Zeile gehört zu einer Etage
					//Wir durchlaufen alle Knoten
					for( Iterator<ModelKnotenBean> iterator = halleModelKnotenBean.getKinderList().iterator(); iterator.hasNext();){
						ModelKnotenBean modelKnotenBean =  iterator.next();
						//Wenn der aktuelle Konten eine Etage ist, ....
						if (modelKnotenBean instanceof EtageModelKnotenBean){
							EtageModelKnotenBean etageModelKnotenBean = (EtageModelKnotenBean)modelKnotenBean;
							EtageBean etageBean = etageModelKnotenBean.getIBean();
							if (etageBean.getId().equals(zeileBean.getEtageBean().getId())){
								etageModelKnotenBean.getKinderList().add(zeileModelKontenBean);
								zeileModelKontenBean.setVater(etageModelKnotenBean);
								//Etage für diese Zeile ist gefunden
								etageOK = true;
							}
						}
					}
				}
				
				if (!etageOK){
					//Zeile ohne Etage
					halleModelKnotenBean.getKinderList().add(zeileModelKontenBean);
 					zeileModelKontenBean.setVater(halleModelKnotenBean);
				}
				
				//Säulen anzeigen
				setSaeulen((ZeileModelKnotenBean)zeileModelKontenBean);
					
			}
			
		}
		
		
	}

	
	/**
	 * Zu eine Zeile werden alle Säulenknoten erzeugt. 
	 * @param zeileModelKontenBean
	 * @throws BenutzerOberflacheLagerException 
	 */
	private void setSaeulen(ZeileModelKnotenBean zeileModelKontenBean) throws BenutzerOberflacheLagerException {
		if (zeileModelKontenBean == null){
			return;
		}
		ZeileBean zeileBean = zeileModelKontenBean.getIBean();
		if (zeileBean==null){
			return;
		}
		//Schleife über alle Säulen in der Zeile
		for (int i=0; i<zeileBean.getSaeuleBeans().size();i++){
			SaeuleBean saeuleBean = zeileBean.getSaeuleBeans().get(i);
			SaeuleModelKnotenBean saeuleModelKnotenBean = new SaeuleModelKnotenBean(saeuleBean);
			saeuleModelKnotenBean.setVater(zeileModelKontenBean); //Kind muss den Vater kennen
			zeileModelKontenBean.getKinderList().add(saeuleModelKnotenBean); //Vater muss das Kind kennen
			setEbenen(saeuleModelKnotenBean);
		}
	}
	
	/**
	 * Zu einer Säule werden alle Ebenen erzeugt. 
	 * @param saeuleModelKontenBean
	 * @throws BenutzerOberflacheLagerException 
	 */
	private void setEbenen(SaeuleModelKnotenBean saeuleModelKontenBean) throws BenutzerOberflacheLagerException {
		if (saeuleModelKontenBean == null){
			return;
		}
		SaeuleBean saeuleBean = saeuleModelKontenBean.getIBean();
		if (saeuleBean==null){
			return;
		}
		//Schleife über alle Säulen in der Zeile
		for (int i=0; i<saeuleBean.getEbeneBeans().size();i++){
			EbeneBean ebeneBean = saeuleBean.getEbeneBeans().get(i);
			EbeneModelKnotenBean ebeneModelKnotenBean = new EbeneModelKnotenBean(ebeneBean);
			ebeneModelKnotenBean.setVater(saeuleModelKontenBean); //Kind muss den Vater kennen
			saeuleModelKontenBean.getKinderList().add(ebeneModelKnotenBean); //Vater muss das Kind kennen
			setPositionen(ebeneModelKnotenBean);
		}
	}
	
	/**
	 * Zu einer Säule werden alle Ebenen erzeugt. 
	 * @param ebeneModelKontenBean
	 * @throws BenutzerOberflacheLagerException 
	 */
	private void setPositionen(EbeneModelKnotenBean ebeneModelKontenBean) throws BenutzerOberflacheLagerException {
		if (ebeneModelKontenBean == null){
			return;
		}
		EbeneBean ebeneBean = ebeneModelKontenBean.getIBean();
		if (ebeneBean==null){
			return;
		}
		//Schleife über alle Säulen in der Zeile
		for (int i=0; i<ebeneBean.getPositionBeans().size();i++){
			PositionBean positionBean = ebeneBean.getPositionBeans().get(i);
			PositionModelKnotenBean positionModelKnotenBean = new PositionModelKnotenBean(positionBean);
			positionModelKnotenBean.setVater(ebeneModelKontenBean); //Kind muss den Vater kennen
			ebeneModelKontenBean.getKinderList().add(positionModelKnotenBean); //Vater muss das Kind kennen
			setBestandspackstuecke(positionModelKnotenBean);
		}
	}
	
	/**
	 * Zu einer Säule werden alle Ebenen erzeugt. 
	 * @param positionModelKontenBean
	 * @throws BenutzerOberflacheLagerException 
	 */
	private void setBestandspackstuecke(PositionModelKnotenBean positionModelKontenBean) throws BenutzerOberflacheLagerException {
		if (positionModelKontenBean == null){
			return;
		}
		PositionBean positionBean = positionModelKontenBean.getIBean();
		if (positionBean==null){
			return;
		}
		//Schleife über alle Säulen in der Zeile
		for (int i=0; i<positionBean.getBestandspackstueckBeans().size();i++){
			BestandspackstueckBean bestandBean = positionBean.getBestandspackstueckBeans().get(i);
			BestandspackstueckModelKnotenBean bestandModelKnotenBean = new BestandspackstueckModelKnotenBean(bestandBean);
			bestandModelKnotenBean.setVater(positionModelKontenBean); //Kind muss den Vater kennen
			positionModelKontenBean.getKinderList().add(bestandModelKnotenBean); //Vater muss das Kind kennen 
		}
	}
	
	
	

	private EtageBeanDB getEtageBeanDB() {
		if (etageBeanDB == null) {
			etageBeanDB = new EtageBeanDB();
		}
		return etageBeanDB;
	}
	
	private ZeileBeanDB getZeileBeanDB() {
		if (zeileBeanDB == null) {
			zeileBeanDB = new ZeileBeanDB();
		}
		return zeileBeanDB;
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
//			if (modelKnotenBean.getIBean() instanceof HalleBean){
//				return modelKnotenBean;
//			}else{
//				if (modelKnotenBean.getIBean() instanceof EtageBean){
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
			HalleBean halleBean= new HalleBean();
			halleBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_HALLE));
		//	halleBean.setStatus(AnforderungStatus.OFFEN);
			halleBean.setBeanDBStatus(BeanDBStatus.INSERT); //als neu markieren
			ModelKnotenBean modelKnotenBean;
			//ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new HalleModelKnotenBean(halleBean);
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
//				speichereEtagen(bean);
				getBeanDB().saveBean((HalleBean)bean); //wichtigste Zeile in dieser Procedure
				loescheBeanAusModel(bean, false);
				break;
			case INSERT_DELETE:
				loescheBeanAusModel(bean, false);
				break;
			default:
				getBeanDB().saveBean((HalleBean)bean); //wichtigste Zeile in dieser Procedure
//				speichereEtagen(bean);
				leseSatzAnhandIDneuEin((HalleBean)bean);
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
			Fehler fehler = null;
			if (e instanceof MySQLIntegrityConstraintViolationException){
				MySQLIntegrityConstraintViolationException sqlE = (MySQLIntegrityConstraintViolationException)e;
				if (sqlE.getErrorCode()==1062){
					fehler = new Fehler(128);				
				}else{
					fehler = new Fehler(25, FehlerTyp.FEHLER,"Satz kann nicht gespeichert werden:"+e.getMessage());				
				}
			}else{
				fehler = new Fehler(25, FehlerTyp.FEHLER,"Satz kann nicht gespeichert werden:"+e.getMessage()); 
			}
			getFehlerList().add(fehler);
			bean.getFehlerList().add(fehler);
			e.printStackTrace();
			Log.log().severe(e.getMessage());
		}
		//Anzeige auffrischen
		benachrichtigeBeobachter();
	}

//	private void speichereEtagen(Bean bean) throws Exception {
//		//Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer Collection erlaubt.
//		//Collection ist eine Sammlung von Daten(z.B. ein Array, oder ArrayList)
//		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean().getKinderList().iterator(); 
//		while(iterator.hasNext()){
//			ModelKnotenBean modelKnotenBean = iterator.next();
//			modelKnotenBean.getFehlerList().clear(); //alte Fehler löschen, vor Schreiben
//			try{
//				IBean ibean = modelKnotenBean.getIBean();
//				if (ibean instanceof EtageBean){
//					EtageBean etageBean = (EtageBean)ibean; 
//					if (etageBean.getBeanDBStatus() == BeanDBStatus.DELETE){
//						//Löschen
//						etageBean.setBeanDBStatus(BeanDBStatus.DELETE);
//					}
//			        getEtageBeanDB().saveBean(etageBean);
//				}
//				if (ibean instanceof ZeileBean){
//					ZeileBean zeileBean = (ZeileBean)ibean; 
//					if (zeileBean.getBeanDBStatus() == BeanDBStatus.DELETE){
//						//Löschen
//						zeileBean.setBeanDBStatus(BeanDBStatus.DELETE);
//					}
//			        getZeileBeanDB().saveBean(zeileBean);
//				}
//			}catch(Exception e){
//				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,"Fehler:"+e.getMessage());
//				modelKnotenBean.getFehlerList().add(fehler); //Den Fehler in der Position speichern.
//				e.printStackTrace();
//				throw e;
//			}
//		}
//	}

	@Override
	protected void leseSatzAnhandIDneuEin(Bean bean) throws SQLException,	 
	LagerException, BenutzerOberflacheLagerException{ 
		
		HalleBean halleBean = (HalleBean) bean;
		HalleBean halleBeanNeu = (HalleBean)getBeanDB().selectAnhandID(halleBean.getId(), 0, null); //Bestellanforderung wird neu aus DB gelesen
		halleBean.getModelKnotenBean().setIBean(halleBeanNeu); //aktueller Knoten wird mit neuen Daten gefüllt.
		
		//Die neue Bestellanforderung(frisch aus Datebank ersetzt die alte gespeicherte HalleBean)
		ArrayList<EtageBean> etageBeans = 
		((EtageBeanDB)getEtageBeanDB()).sucheAnhandHalleBean(halleBeanNeu, 0);
		
		ArrayList<ZeileBean> zeileBeans = 
			((ZeileBeanDB)getZeileBeanDB()).sucheAnhandHalleBean(halleBeanNeu, 0);
		
		setEtageenUndZeilen(etageBeans,zeileBeans,(HalleModelKnotenBean) getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); //Am Schluss aufrufen
	}

	@Override
	public void loescheSatz(Bean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();

		if (getAktiveTreeModelBean().getKinderList().size() > 0) {
			try {
				throw new LagerException(
						new Fehler(
								"Säule darf nicht gelöscht werden. Löschen Sie zuerst alle Ebenen."));
			} catch (LagerException e) {
				getFehlerList().add(new Fehler(e));
				e.printStackTrace();
			}
		} else {

			switch (bean.getBeanDBStatus()) {
			case INSERT: {
				loescheBeanAusModel(bean, true);
				// LoescheObjektAusDerListe((HalleBean)bean);
				break;
			}
			case FEHLERHAFT: {
				getFehlerList().add(
						new Fehler(25, FehlerTyp.FEHLER,
								"Falscher Status der baBean beim Löschen. Status:"
										+ BeanDBStatus.JavaToString(bean
												.getBeanDBStatus())));
				Log.log().severe("Falscher Status");
				break;
			}
			case DELETE:
				;
			case SELECT:
				;
			case UPDATE: {
				try {
					LagerConnection.startTransaction(); // Mitteilung an die DB,
														// dass ab hier eine
														// Transaktion beginnt.
					bean.setBeanDBStatus(BeanDBStatus.DELETE);
					getBeanDB().saveBean((HalleBean) bean); // wichtigste Zeile
															// in dieser
															// Procedure
					loescheBeanAusModel(bean, true);
					// LoescheObjektAusDerListe((HalleBean)bean);
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
										"Fehler beim Rollback:"
												+ e.getMessage()));
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
		}
		benachrichtigeBeobachter();
	}

	

	
	public void erzeugeNeueEtage(){
		getFehlerList().clear();
		
		try {
		//neuer BestellanfoderungsPositions-Knoten wird erzeugt
		HalleBean halleBean = (HalleBean)getSelectedListModelKnotenBean().getIBean();
		EtageModelKnotenBean etageModelKnotenBean = new EtageModelKnotenBean(getSelectedListModelKnotenBean());
		EtageBean etageBean = new EtageBean();
		etageBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ETAGE));
		etageBean.setHalleBean(halleBean); //aktueller User aus der Liste
//		etageBean.setEtage(new EtageBean()); //neu
		etageBean.setBeanDBStatus(BeanDBStatus.INSERT);
		etageModelKnotenBean.setIBean(etageBean);
		getSelectedListModelKnotenBean().getKinderList().add(0,etageModelKnotenBean);
		halleBean.getEtageBeans().add(0,etageBean);
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
			getFehlerList().add(new Fehler(e));
			e.printStackTrace();
		}
		benachrichtigeDetailsBeobachter();
		
	}

	public void loescheEtage(EtageBean zuLoeschendeBenutzerPosBean) {
		getFehlerList().clear();
		zuLoeschendeBenutzerPosBean.getFehlerList().clear();

		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof EtageBean && modelKnotenBean.getIBean().equals(zuLoeschendeBenutzerPosBean)){
				if (modelKnotenBean.getKinderList().size() > 0) {
					try {
						throw new LagerException(
								new Fehler(
										"Etage darf nicht gelöscht werden. Löschen Sie zuerst alle Zeilen."));
					} catch (LagerException e) {
						getFehlerList().add(new Fehler(e));
						e.printStackTrace();
					}
				} else {
					
				
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
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}
	
	public void loescheZeile(ZeileBean zuLoeschendeZeileBean) {
		getFehlerList().clear();
		zuLoeschendeZeileBean.getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof ZeileBean && modelKnotenBean.getIBean().equals(zuLoeschendeZeileBean)){
				if (modelKnotenBean.getKinderList().size() > 0) {
					try {
						throw new LagerException(
								new Fehler(
										"Zeile darf nicht gelöscht werden. Löschen Sie zuerst alle Säulen."));
					} catch (LagerException e) {
						getFehlerList().add(new Fehler(e));
						e.printStackTrace();
					}
				} else {
					
				
				switch (zuLoeschendeZeileBean.getBeanDBStatus()){
				case INSERT : {
					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : zuLoeschendeZeileBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : zuLoeschendeZeileBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : zuLoeschendeZeileBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
				}
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}
	
	
	

//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	public void ausgewaehlterKnotenIstGeandert() {
//		ArrayList<Fehler> errors = getAktiveTreeModelBean().getIBean().pruefeEigeneDaten();
//		getAktiveTreeModelBean().setFehlerList(errors);
//		if (getAktiveTreeModelBean() instanceof EtageModelKnotenBean){
//			benachrichtigeDetailsBeobachter();
//		}else if(getAktiveTreeModelBean() instanceof HalleModelKnotenBean){
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
//			errors.add(0,new Fehler(25,FehlerTyp.FEHLER,"Fehler im Benutzersatz, id=" + ((HalleBean)getSelectedListModelKnotenBean().getIBean()).getId()));
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
			return new HalleModelKnotenBean((HalleBean)iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	public void erzeugeNeueZeile(){
		getFehlerList().clear();
		try {
			
			//Prüfen, ob die neue Zeile von einer Etage-Details-Maskeerzeugt wurde.
			ModelKnotenBean aktiveKnotenBean = getAktiveTreeModelBean();
			EtageBean etageBean = null;
			if (aktiveKnotenBean!=null){
				if (aktiveKnotenBean.getSammelKnotenTypENUM()==ModelKnotenTyp.ETAGE){
					//die neue Zeile soll zu der bestimmten Etage gehören.
					etageBean = (EtageBean)aktiveKnotenBean.getIBean(); 
				}else{
					etageBean = null;
				}
			}else{
				etageBean = null;
			}
			
			
			
			// neuer BestellanfoderungsPositions-Knoten wird erzeugt
			HalleBean halleBean = (HalleBean) getSelectedListModelKnotenBean()
					.getIBean();
			ZeileModelKnotenBean zeileModelKnotenBean = 
				new ZeileModelKnotenBean(getSelectedListModelKnotenBean());
			ZeileBean zeileBean = new ZeileBean(); // Daten
			zeileBean.setEtageBean(etageBean); 
																						// im
																						// Knoten
			zeileBean.setHalleBean(halleBean); // aktueller User
																// aus der Liste
			zeileBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ZEILE)); // neu
			
			Integer zeileNummer = getZeileBeanDB().getNachsteNummer(halleBean);
			for(ZeileBean zBean :halleBean.getZeileBeans()){
				if (zBean.getNummer()>=zeileNummer){
					zeileNummer = zBean.getNummer() + 1;
				}
			}
			zeileBean.setNummer(zeileNummer);
					
			
			zeileBean.setBeanDBStatus(BeanDBStatus.INSERT);
			zeileModelKnotenBean.setIBean(zeileBean);
			if(etageBean!=null
					&&
					getAktiveTreeModelBean() != null
					&&
					getAktiveTreeModelBean() instanceof EtageModelKnotenBean){
				//Aktive ist gerade eine Etage(modelknotenbean)
				zeileModelKnotenBean.setVater(getAktiveTreeModelBean());
				getAktiveTreeModelBean().getKinderList().add(
						zeileModelKnotenBean);
			}else{
				//Zeile direkt in die Halle hinzufügen.
				zeileModelKnotenBean.setVater(getSelectedListModelKnotenBean());
				getSelectedListModelKnotenBean().getKinderList().add(
						zeileModelKnotenBean);
			}
			halleBean.getZeileBeans().add(zeileBean);
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
			getFehlerList().add(new Fehler(e));
			e.printStackTrace();
		}
	}

	public void erzeugeNeueSauele() {
		getFehlerList().clear();
		
		try {
		//neuer Säule wird erzeugt
		ZeileBean zeileBean = (ZeileBean)getAktiveTreeModelBean().getIBean();
		SaeuleBean saeuleBean = new SaeuleBean();
		saeuleBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_SAEULE));
		
		Integer saeuleNummer = getSaeuleBeanDB().getNachsteNummer((ZeileBean)getAktiveTreeModelBean().getIBean());
		for(SaeuleBean sBean :zeileBean.getSaeuleBeans()){
			if (sBean.getNummer()>=saeuleNummer){
				saeuleNummer = sBean.getNummer() + 1;
			}
		}
		saeuleBean.setNummer(saeuleNummer);
		
		saeuleBean.setZeileBean(zeileBean);
		saeuleBean.setBeanDBStatus(BeanDBStatus.INSERT);
		zeileBean.getSaeuleBeans().add(0,saeuleBean);
		
		//neuer Anzeige-Knoten für die Säule wird erzeugt.
		SaeuleModelKnotenBean saeuleModelKnotenBean = new SaeuleModelKnotenBean(saeuleBean);
		getAktiveTreeModelBean().getKinderList().add(0,saeuleModelKnotenBean);
		saeuleModelKnotenBean.setVater(getSelectedListModelKnotenBean());
		
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
			getFehlerList().add(new Fehler(e));
			e.printStackTrace();
		}
		benachrichtigeDetailsBeobachter();
		
	}

	public void erzeugeNeuePosition() {
		getFehlerList().clear();
		
		try {
		//neuer Säule wird erzeugt
		EbeneBean ebeneBean = (EbeneBean)getAktiveTreeModelBean().getIBean();
		PositionBean positionBean = new PositionBean();
		positionBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_POSITION));
		Integer posNummer = getPositionBeanDB().getNachsteNummer((EbeneBean)getAktiveTreeModelBean().getIBean());
		for(PositionBean posBean :ebeneBean.getPositionBeans()){
			if (posBean.getNummer()>=posNummer){
				posNummer = posBean.getNummer() + 1;
			}
		}
		positionBean.setNummer(posNummer);
		positionBean.setEbeneBean(ebeneBean);
		positionBean.setBeanDBStatus(BeanDBStatus.INSERT);
		ebeneBean.getPositionBeans().add(0,positionBean);
		
		//neuer Anzeige-Knoten für die Säule wird erzeugt.
		PositionModelKnotenBean positionModelKnotenBean = new PositionModelKnotenBean(positionBean);
		getAktiveTreeModelBean().getKinderList().add(positionModelKnotenBean);
		positionModelKnotenBean.setVater(getSelectedListModelKnotenBean());
		
		//Beobachter werden benachrichtigt
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (IdLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(e));
			e.printStackTrace();
		}
		benachrichtigeTreeBeobachter();
		benachrichtigeDetailsBeobachter();
		
	}

	public void erzeugeNeueEbene() {
		getFehlerList().clear();
		
		try {
		//neuer Säule wird erzeugt
		SaeuleBean saeuleBean = (SaeuleBean)getAktiveTreeModelBean().getIBean();
		EbeneBean ebeneBean = new EbeneBean();
		ebeneBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_EBENE));
		
		Integer ebeneNummer = getEbeneBeanDB().getNachsteNummer((SaeuleBean)getAktiveTreeModelBean().getIBean());
		for(EbeneBean ebBean :saeuleBean.getEbeneBeans()){
			if (ebBean.getNummer()>=ebeneNummer){
				ebeneNummer = ebBean.getNummer() + 1;
			}
		}
		ebeneBean.setNummer(ebeneNummer);
		
		ebeneBean.setSaeuleBean(saeuleBean);
		ebeneBean.setBeanDBStatus(BeanDBStatus.INSERT);
		saeuleBean.getEbeneBeans().add(0,ebeneBean);
		
		//neuer Anzeige-Knoten für die Säule wird erzeugt.
		EbeneModelKnotenBean ebeneModelKnotenBean = new EbeneModelKnotenBean(ebeneBean);
		getAktiveTreeModelBean().getKinderList().add(ebeneModelKnotenBean);
		ebeneModelKnotenBean.setVater(getSelectedListModelKnotenBean());
		
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
			getFehlerList().add(new Fehler(e));
			e.printStackTrace();
		}
		benachrichtigeDetailsBeobachter();
		
	}

	public void erzeugeNeueBestandspackstueck(){
		getFehlerList().clear();
		
		try {
			
		//neuer Säule wird erzeugt
		PositionBean positionBean = (PositionBean)getAktiveTreeModelBean().getIBean();
//		if (positionBean.getBestandspackstueckBeans().size()>=1){
//			throw new LagerException(new Fehler(102,FehlerTyp.FEHLER));
//		}
		BestandspackstueckBean bestandspackstueckBean = new BestandspackstueckBean();
		bestandspackstueckBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BESTANDSPACKSTUECK));
		bestandspackstueckBean.setPositionBean(positionBean);
		
		//Vorbelegung Abteilung
		ArrayList<BenutzerAbteilungBean> abteilungen = Run.getOneInstance().getBenutzerBean().getBenutzerAbteilungBeans();
		if (abteilungen!=null&abteilungen.size()==1){
			bestandspackstueckBean.setAbteilung(abteilungen.get(0).getAbteilung());
		}
		
		bestandspackstueckBean.setBeanDBStatus(BeanDBStatus.INSERT);
		positionBean.getBestandspackstueckBeans().add(0,bestandspackstueckBean);
		
		//neuer Anzeige-Knoten für die Säule wird erzeugt.
		BestandspackstueckModelKnotenBean bestandspackstueckModelKnotenBean = new BestandspackstueckModelKnotenBean(bestandspackstueckBean);
		getAktiveTreeModelBean().getKinderList().add(0,bestandspackstueckModelKnotenBean);
		bestandspackstueckModelKnotenBean.setVater(getSelectedListModelKnotenBean());
		
		//Beobachter werden benachrichtigt
		benachrichtigeDetailsBeobachter();
		benachrichtigeTreeBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			getAktiveTreeModelBean().getFehlerList().addAll(getFehlerList());
		} catch (IdLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			getAktiveTreeModelBean().getFehlerList().addAll(getFehlerList());
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(e));
			e.printStackTrace();
			getAktiveTreeModelBean().getFehlerList().addAll(getFehlerList());
		} catch (LagerException e) {
			getFehlerList().add(new Fehler(e));
			e.printStackTrace();
			getAktiveTreeModelBean().getFehlerList().addAll(getFehlerList());
		}
		benachrichtigeDetailsBeobachter();
	}

	public void loescheBestandspackstueck(BestandspackstueckBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof BestandspackstueckBean && modelKnotenBean.getIBean().equals(bean)){
				switch (bean.getBeanDBStatus()){
				case INSERT : {
					getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
					break;
				}
				case SELECT : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				case UPDATE : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
				default : bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
				}
				abbruch = true;
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}

	public void loescheEbene(EbeneBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof EbeneBean && modelKnotenBean.getIBean().equals(bean)){
				if (modelKnotenBean.getKinderList().size()>0){
					try {
						throw new LagerException(new Fehler("Ebene darf nicht gelöscht werden. Löschen Sie zuerst alle Positionen."));
					} catch (LagerException e) {
						getFehlerList().add(new Fehler(e));
						e.printStackTrace();
					}
				}else{
					switch (bean.getBeanDBStatus()) {
					case INSERT: {
						getSelectedListModelKnotenBean().getKinderList()
								.remove(modelKnotenBean);
						break;
					}
					case SELECT:
						bean.setBeanDBStatus(BeanDBStatus.DELETE);
						break;
					case UPDATE:
						bean.setBeanDBStatus(BeanDBStatus.DELETE);
						break;
					default:
						bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT);
						break;
					}
					abbruch = true;
				}
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}

	public void loescheSaeule(SaeuleBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof SaeuleBean && modelKnotenBean.getIBean().equals(bean)){
				if (modelKnotenBean.getKinderList().size() > 0) {
					try {
						throw new LagerException(
								new Fehler(
										"Säule darf nicht gelöscht werden. Löschen Sie zuerst alle Ebenen."));
					} catch (LagerException e) {
						getFehlerList().add(new Fehler(e));
						e.printStackTrace();
					}
				} else {
					switch (bean.getBeanDBStatus()) {
					case INSERT: {
						getSelectedListModelKnotenBean().getKinderList()
								.remove(modelKnotenBean);
						break;
					}
					case SELECT:
						bean.setBeanDBStatus(BeanDBStatus.DELETE);
						break;
					case UPDATE:
						bean.setBeanDBStatus(BeanDBStatus.DELETE);
						break;
					default:
						bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT);
						break;
					}
					abbruch = true;
				}
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}

	public void loescheLagerPosition(PositionBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean().getKinderList().iterator();
		
		for(boolean abbruch = false; iterator.hasNext()&&!abbruch;  ){
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof PositionBean && modelKnotenBean.getIBean().equals(bean)){
				if (modelKnotenBean.getKinderList().size()>0){
					try {
						throw new LagerException(new Fehler("Position darf nicht gelöscht werden. Löschen Sie zuerst den Bestand."));
					} catch (LagerException e) {
						getFehlerList().add(new Fehler(e));
						e.printStackTrace();
					}
				}else{
					switch (bean.getBeanDBStatus()){
					case INSERT : {
						getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
						break;
					}
					case SELECT : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
					case UPDATE : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
					default : bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
					}
					abbruch = true;
				}
			}
		}
		benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	}

	public void druckeBericht(Bean bean, boolean mitMengen) throws Exception {
		
		if (bean==null){
			IBean ibean =  getSelectedListModelKnotenBean().getIBean();
			if (ibean instanceof HalleBean){
			 	HalleBean halleBean = (HalleBean)ibean;	
				if (halleBean!=null){
//					BerichtPaneController berichtPaneController = new BerichtPaneController();
					BerichtPaneController berichtPaneController = BerichtPaneController.getOneInstance();
					berichtPaneController.drucken(Berichttyp.INVENTUR_LISTE_HALLE, halleBean.getId(), Berichtart.PRINT,null);
//					bean.setStatus(AnforderungStatus.ABGESCHLOSSEN);
//					speichereSatz(bean);
				}else{
					throw new LagerException("Satz kann nicht verschickt werden. Bean ist leer");
				}
			}else{
				throw new LagerException("Falsche Klasse:"+bean);
			}
		}else if (bean instanceof ZeileBean){
			//Inventurliste für die Zeile drucken
			BerichtPaneController berichtPaneController = BerichtPaneController.getOneInstance();
			berichtPaneController.drucken(Berichttyp.INVENTUR_LISTE_ZEILE, ((ZeileBean)bean).getId(), Berichtart.PRINT,null);
		}else if (bean instanceof SaeuleBean){
			//Inventurliste für die Zeile drucken
			Map<String, String> parameters = new HashMap<String, String>();
			if (mitMengen){
				parameters.put(Konstanten.REPORT_INVENTUR_MIT_MENGEN,Konstanten.REPORT_INVENTUR_MIT_MENGEN_JA);
			}else{
				parameters.put(Konstanten.REPORT_INVENTUR_MIT_MENGEN,Konstanten.REPORT_INVENTUR_MIT_MENGEN_NEIN);
			}
			BerichtPaneController berichtPaneController = BerichtPaneController.getOneInstance();
			berichtPaneController.drucken(Berichttyp.INVENTUR_LISTE_SAEULE, ((SaeuleBean)bean).getId(), Berichtart.PRINT,parameters);
		}else{
			throw new LagerException("Falsche Klasse:"+bean);
		}
		
//		BaBean bean = (BaBean)getSelectedListModelKnotenBean().getIBean();
	}

	protected PositionBeanDB getPositionBeanDB() {
		if(positionBeanDB==null){
			positionBeanDB = new PositionBeanDB();
		}
		return positionBeanDB;
	}

	protected EbeneBeanDB getEbeneBeanDB() {
		if (ebeneBeanDB==null){
			ebeneBeanDB = new EbeneBeanDB();
		}
		return ebeneBeanDB;
	}

	public void setSaeuleBeanDB(SaeuleBeanDB saeuleBeanDB) {
		this.saeuleBeanDB = saeuleBeanDB;
	}

	public SaeuleBeanDB getSaeuleBeanDB() {
		if (this.saeuleBeanDB==null){
				this.saeuleBeanDB = new SaeuleBeanDB();
		}
		return saeuleBeanDB;
	}
	
	
//	@Override
//	public void addISuchBeobachters(ISuchBeobachter iHalleSuchPaneBeobachter) {
//		getiHalleSuchPaneBeobachters().add(iHalleSuchPaneBeobachter);
//		benachrichtigeSuchBeobachter();
//	}







}
