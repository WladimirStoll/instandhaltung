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
package de.keag.lager.panels.frame.artikel.pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.Model;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.AbteilungBeanDB;
import de.keag.lager.db.BaugruppeArtikelBeanDB;
import de.keag.lager.db.BaugruppeBeanDB;
import de.keag.lager.db.BenutzerBeanDB;
import de.keag.lager.db.BestandspackstueckBeanDB;
import de.keag.lager.db.EbeneBeanDB;
import de.keag.lager.db.EtageBeanDB;
import de.keag.lager.db.ArtikelBeanDB;
import de.keag.lager.db.HalleBeanDB;
import de.keag.lager.db.LieferantenBestellnummerBeanDB;
import de.keag.lager.db.MengenEinheitBeanDB;
import de.keag.lager.db.PositionBeanDB;
import de.keag.lager.db.SaeuleBeanDB;
import de.keag.lager.db.UnterArtikelBeanDB;
import de.keag.lager.db.ZeileBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.ArtikelSuchBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean.VaterArtikelArray;
import de.keag.lager.panels.frame.artikel.model.ArtikelModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
//import de.keag.lager.panels.frame.lieferantenBestellnummer.model.LieferantenBestellnummerModelKnotenBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.model.UnterArtikelModelKnotenBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.unterArtikel.model.LieferantenBestellnummerModelKnotenBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;

public class ArtikelModel extends Model {
	
	
	public enum AktiverReiter{
		UNKNOWN,
		LAGERPLATZ,
		BAUGRUPPEN,
		OBERARTIKEL,
		UNTERARTIKEL,
		KATALOG
	}
	
	private AktiverReiter aktiverReiter;
	
	// DB-Zugriff
	private ArtikelBeanDB artikelBeanDB;
	private MengenEinheitBeanDB mengenEinheitBeanDB;
	private EtageBeanDB etageBeanDB;
	private ZeileBeanDB zeileBeanDB;
	private PositionBeanDB positionBeanDB;
	private BestandspackstueckBeanDB bestandspackstueckBeanDB;
	private BaugruppeArtikelBeanDB baugruppeArtikelBeanDB;
	private LieferantenBestellnummerBeanDB lieferantenBestellnummerBeanDB;
	private UnterArtikelBeanDB unterArtikelBeanDB;
	private BaugruppeBeanDB baugruppeBeanDB;
	private BaugruppeSuchBean baugruppeSuchBean;
	private ArrayList<BaugruppeBean> baugruppeBeans;

	protected ArtikelModel() {
		super();
		initialize();
	}

	private void initialize() {
	}

	@Override
	public ISuchBean getiSuchBean() {
		if (iSuchBean == null) {
			iSuchBean = new ArtikelSuchBean();
		}
		return iSuchBean;
	}

	@Override
	protected BeanDB getBeanDB() {
		if (artikelBeanDB == null) {
			artikelBeanDB = new ArtikelBeanDB();
			//Depth-DB wird gesetzt
			artikelBeanDB.getDepthLevelArrayDB().setDebugMode(true);
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(BenutzerBeanDB.class, 1, 1));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(BaugruppeBeanDB.class, 1, 10));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(BaugruppeArtikelBeanDB.class, 1, 1));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(ArtikelBeanDB.class, 1, 10));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(BestandspackstueckBeanDB.class, 1, 1));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(AbteilungBeanDB.class, 2, 2));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(PositionBeanDB.class, 2, 2));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(EbeneBeanDB.class, 3, 3));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(SaeuleBeanDB.class, 4, 4));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(ZeileBeanDB.class, 5, 5));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(HalleBeanDB.class, 6, 6));
			artikelBeanDB.getDepthLevelArrayDB().add(new DepthLevelDB(EtageBeanDB.class, 7, 7));
		}
		return artikelBeanDB;
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
					//Artikel wird gelesen
					leseSatzAnhandIDneuEin(((ArtikelModelKnotenBean) getAktiveTreeModelBean())
							.getIBean());
					
					// Anhand des Artikels werden jetzt alle Baugruppen geholt.
					ArtikelBean artikelBean = ((ArtikelModelKnotenBean) getAktiveTreeModelBean())
							.getIBean();
					getBaugruppeSuchBean().setArtikelBean(artikelBean);
					ArrayList<Bean> baugruppeBeans = getBaugruppeBeanDB()
							.selectAnhandSuchBean(baugruppeSuchBean, 0+1,getBeanDB().getDepthLevelArrayDB());
					setBaugruppeBeans(baugruppeBeans);
					
					
					
					// // Datenbankzugriff: Wir suchen alle Positionen zu der
					// // gewählten Anforderung.
					// ArrayList<EtageBean> etageBeans =
					// getEtageBeanDB().sucheAnhandArtikelBean(
					// ((ArtikelModelKnotenBean)
					// getAktiveTreeModelBean()).getIBean()
					// );
					//					
					// ArrayList<ZeileBean> zeileBeans =
					// getZeileBeanDB().sucheAnhandArtikelBean(
					// ((ArtikelModelKnotenBean)
					// getAktiveTreeModelBean()).getIBean()
					// );
					//					
					// setEtageenUndZeilen(
					// etageBeans,
					// zeileBeans,
					// (ArtikelModelKnotenBean) getAktiveTreeModelBean());

				} catch (BenutzerOberflacheLagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
				} catch (LagerException e) {
					getFehlerList().add(e.getFehler());
					e.printStackTrace();
				} catch (SQLException e) {
					getFehlerList().add(
							new Fehler(25, FehlerTyp.FEHLER, e.getMessage()));
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

	// Alle Baugruppen werden in die neue Liste übernommen
	private void setBaugruppeBeans(ArrayList<Bean> baugruppeBeans) {
		getBaugruppeBeans().clear();
		for(Bean b : baugruppeBeans){
			getBaugruppeBeans().add((BaugruppeBean)b);
		}
//		setKinderBaugruppenUndArtikelEbene2(
//				(ArtikelBean)getSelectedListModelKnotenBean().getIBean() 
//				getBaugruppeBeans());
	}

//	private void setKinderBaugruppenUndArtikelEbene2(ArtikelBean artikelBean,
//			ArrayList<BaugruppeBean> kinderBaugruppeBeans,
//			BaugruppeModelKnotenBean baugruppeModelKnotenBean)
//			throws SQLException, LagerException {
//		// Alle Kinder werden rückwärts durchgelaufen.
//		// Bereits bestehende Positionen werden durch neue Positionen ersetzt.
//		int posAnzahl = 0;
//
//		baugruppeModelKnotenBean.getKinderList().clear();
//
//		// Alle Kinder-Baugruppen in die leere Liste aufnehmen
//		for (int i = 0; i < kinderBaugruppeBeans.size(); i++) {
//			BaugruppeModelKnotenBean baugruppeModelKontenBean = new BaugruppeModelKnotenBean(
//					kinderBaugruppeBeans.get(i), baugruppeModelKnotenBean);
//			baugruppeModelKnotenBean.getKinderList().add(
//					baugruppeModelKontenBean);
//
//			ArrayList<BaugruppeBean> kinderBaugruppeBeansEbene3 = ((BaugruppeBeanDB) getBeanDB())
//					.sucheKinderBaugruppenBeansAnhandId(kinderBaugruppeBeans
//							.get(i).getId());
//
//			ArrayList<BaugruppeArtikelBean> baugruppeArtikelBeansEbene3 = getBaugruppeArtikelBeanDB()
//					.sucheAnhandBaugruppeBean(kinderBaugruppeBeans.get(i));
//
//			setKinderBaugruppenUndArtikelEbene2(baugruppeArtikelBeansEbene3,
//					kinderBaugruppeBeansEbene3, baugruppeModelKontenBean);
//
//		}
//
//		// Alle Artikel der Gruppe in die Liste auch aufnehmen
//		for (int i = 0; i < baugruppeArtikelBeans.size(); i++) {
//			ModelKnotenBean modelKontenBean = new BaugruppeArtikelModelKnotenBean(
//					baugruppeArtikelBeans.get(i), baugruppeModelKnotenBean);
//			baugruppeModelKnotenBean.getKinderList().add(modelKontenBean);
//		}
//
//	}

	// private void setEtageenUndZeilen(
	// ArrayList<EtageBean> etagePositionen,
	// ArrayList<ZeileBean> zeilePositionen,
	// ArtikelModelKnotenBean halleModelKnotenBean)
	// throws BenutzerOberflacheLagerException {
	// // Alle Kinder werden rückwärts durchgelaufen.
	// // Bereits bestehende Positionen werden durch neue Positionen ersetzt.
	//		
	// int posAnzahl = 0;
	// for (int i = halleModelKnotenBean.getKinderList().size() - 1; i >= 0;
	// i--) {
	// ModelKnotenBean modelKnotenBean =
	// halleModelKnotenBean.getKinderList().get(i);
	// if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ETAGE) {
	// // Wenn aktuelle bestehende Position ausgetauscht werden soll
	// // und kann
	// if (i <= etagePositionen.size() - 1) {
	// //der Knoten wird nochmal verwendet. Initialisieren.
	// modelKnotenBean.initialize();
	// // nächste neue Bean wird ermittelt
	// EtageBean etagePosBean = etagePositionen.get(posAnzahl);
	// // laufende Nummer der neuen Pos-Bean-Liste wird erhöht.
	// posAnzahl++;
	// // neue Bean wird übernommen.
	// modelKnotenBean.setIBean(etagePosBean);
	// } else {
	// // löschen der alten ModelBeanPosition. Wir brauchen diese
	// // nicht mehr.
	// halleModelKnotenBean.getKinderList().remove(modelKnotenBean);
	// }
	// } else {
	// //den Rest löschen
	// halleModelKnotenBean.getKinderList().remove(modelKnotenBean);
	// }
	// }
	// // Wenn noch nicht alle neue Positionen in die KinderListe aufgenommen
	// // wurden.
	// if (etagePositionen !=null && posAnzahl < etagePositionen.size()) {
	// for (; posAnzahl < etagePositionen.size(); posAnzahl++) {
	// // nächste nicht aufgenommene Position finden.
	// EtageBean etageBean = etagePositionen.get(posAnzahl);
	// // neuer Knoten wird erstellt.
	// ModelKnotenBean modelKontenBean = new
	// EtageModelKnotenBean(halleModelKnotenBean);
	// // ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
	// modelKontenBean.setIBean(etageBean);
	// // neuer Knoten(samt Inhalten) wird in die KinderListe
	// // übernommen.
	// halleModelKnotenBean.getKinderList().add(modelKontenBean);
	// }
	// }
	//		
	// //An dieser Stelle sind alle Abteilungen(Etage)in der Liste aufgenommen.
	//		
	// //Jetzt müssen noch die Zugriffsreichte in die Liste aufgenommen werden.
	// if (zeilePositionen!=null){
	// for (posAnzahl = 0; posAnzahl < zeilePositionen.size(); posAnzahl++) {
	// // nächste nicht aufgenommene Position finden.
	// ZeileBean zeileBean = zeilePositionen.get(posAnzahl);
	// // neuer Knoten wird erstellt.
	// ModelKnotenBean zeileModelKontenBean = new
	// ZeileModelKnotenBean(halleModelKnotenBean);
	// // ModelKnotenBean(Position) wird mit dem Inhalt gefüllt.
	// zeileModelKontenBean.setIBean(zeileBean);
	// // neuer Knoten(samt Inhalten) wird in die KinderListe
	// // übernommen.
	// boolean etageOK;
	// //keine Etage ist gefunden.
	// etageOK = false;
	// if (!zeileBean.getEtageBean().getId().equals(0)){ //Etagennummer ist
	// ungleich 0
	// //Zeile gehört zu einer Etage
	// //Wir durchlaufen alle Knoten
	// for( Iterator<ModelKnotenBean> iterator =
	// halleModelKnotenBean.getKinderList().iterator(); iterator.hasNext();){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// //Wenn der aktuelle Konten eine Etage ist, ....
	// if (modelKnotenBean instanceof EtageModelKnotenBean){
	// EtageModelKnotenBean etageModelKnotenBean =
	// (EtageModelKnotenBean)modelKnotenBean;
	// EtageBean etageBean = etageModelKnotenBean.getIBean();
	// if (etageBean.getId().equals(zeileBean.getEtageBean().getId())){
	// etageModelKnotenBean.getKinderList().add(zeileModelKontenBean);
	// zeileModelKontenBean.setVater(etageModelKnotenBean);
	// //Etage für diese Zeile ist gefunden
	// etageOK = true;
	// }
	// }
	// }
	// }
	//				
	// if (!etageOK){
	// //Zeile ohne Etage
	// halleModelKnotenBean.getKinderList().add(zeileModelKontenBean);
	// zeileModelKontenBean.setVater(halleModelKnotenBean);
	// }
	//				
	// //Säulen anzeigen
	// setSaeulen((ZeileModelKnotenBean)zeileModelKontenBean);
	//					
	// }
	//			
	// }
	//		
	//		
	// }
	//
	//	
	// /**
	// * Zu eine Zeile werden alle Säulenknoten erzeugt.
	// * @param zeileModelKontenBean
	// * @throws BenutzerOberflacheLagerException
	// */
	// private void setSaeulen(ZeileModelKnotenBean zeileModelKontenBean) throws
	// BenutzerOberflacheLagerException {
	// if (zeileModelKontenBean == null){
	// return;
	// }
	// ZeileBean zeileBean = zeileModelKontenBean.getIBean();
	// if (zeileBean==null){
	// return;
	// }
	// //Schleife über alle Säulen in der Zeile
	// for (int i=0; i<zeileBean.getSaeuleBeans().size();i++){
	// SaeuleBean saeuleBean = zeileBean.getSaeuleBeans().get(i);
	// SaeuleModelKnotenBean saeuleModelKnotenBean = new
	// SaeuleModelKnotenBean(saeuleBean);
	// saeuleModelKnotenBean.setVater(zeileModelKontenBean); //Kind muss den
	// Vater kennen
	// zeileModelKontenBean.getKinderList().add(saeuleModelKnotenBean); //Vater
	// muss das Kind kennen
	// setEbenen(saeuleModelKnotenBean);
	// }
	// }
	//	
	// /**
	// * Zu einer Säule werden alle Ebenen erzeugt.
	// * @param saeuleModelKontenBean
	// * @throws BenutzerOberflacheLagerException
	// */
	// private void setEbenen(SaeuleModelKnotenBean saeuleModelKontenBean)
	// throws BenutzerOberflacheLagerException {
	// if (saeuleModelKontenBean == null){
	// return;
	// }
	// SaeuleBean saeuleBean = saeuleModelKontenBean.getIBean();
	// if (saeuleBean==null){
	// return;
	// }
	// //Schleife über alle Säulen in der Zeile
	// for (int i=0; i<saeuleBean.getEbeneBeans().size();i++){
	// EbeneBean ebeneBean = saeuleBean.getEbeneBeans().get(i);
	// EbeneModelKnotenBean ebeneModelKnotenBean = new
	// EbeneModelKnotenBean(ebeneBean);
	// ebeneModelKnotenBean.setVater(saeuleModelKontenBean); //Kind muss den
	// Vater kennen
	// saeuleModelKontenBean.getKinderList().add(ebeneModelKnotenBean); //Vater
	// muss das Kind kennen
	// setPositionen(ebeneModelKnotenBean);
	// }
	// }
	//	
	// /**
	// * Zu einer Säule werden alle Ebenen erzeugt.
	// * @param ebeneModelKontenBean
	// * @throws BenutzerOberflacheLagerException
	// */
	// private void setPositionen(EbeneModelKnotenBean ebeneModelKontenBean)
	// throws BenutzerOberflacheLagerException {
	// if (ebeneModelKontenBean == null){
	// return;
	// }
	// EbeneBean ebeneBean = ebeneModelKontenBean.getIBean();
	// if (ebeneBean==null){
	// return;
	// }
	// //Schleife über alle Säulen in der Zeile
	// for (int i=0; i<ebeneBean.getPositionBeans().size();i++){
	// PositionBean positionBean = ebeneBean.getPositionBeans().get(i);
	// PositionModelKnotenBean positionModelKnotenBean = new
	// PositionModelKnotenBean(positionBean);
	// positionModelKnotenBean.setVater(ebeneModelKontenBean); //Kind muss den
	// Vater kennen
	// ebeneModelKontenBean.getKinderList().add(positionModelKnotenBean);
	// //Vater muss das Kind kennen
	// setBestandspackstuecke(positionModelKnotenBean);
	// }
	// }
	//	
	// /**
	// * Zu einer Säule werden alle Ebenen erzeugt.
	// * @param positionModelKontenBean
	// * @throws BenutzerOberflacheLagerException
	// */
	// private void setBestandspackstuecke(PositionModelKnotenBean
	// positionModelKontenBean) throws BenutzerOberflacheLagerException {
	// if (positionModelKontenBean == null){
	// return;
	// }
	// PositionBean positionBean = positionModelKontenBean.getIBean();
	// if (positionBean==null){
	// return;
	// }
	// //Schleife über alle Säulen in der Zeile
	// for (int i=0; i<positionBean.getBestandspackstueckBeans().size();i++){
	// BestandspackstueckBean bestandBean =
	// positionBean.getBestandspackstueckBeans().get(i);
	// BestandspackstueckModelKnotenBean bestandModelKnotenBean = new
	// BestandspackstueckModelKnotenBean(bestandBean);
	// bestandModelKnotenBean.setVater(positionModelKontenBean); //Kind muss den
	// Vater kennen
	// positionModelKontenBean.getKinderList().add(bestandModelKnotenBean);
	// //Vater muss das Kind kennen
	// }
	// }
	//	
	//	
	//	
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

	// /**
	// * Die Funktion liefert die gerade ausgewählte Bestellanforderungsklasse
	// */
	// @Override
	// public ModelKnotenBean getSelectedListModelKnotenBean() {
	// ModelKnotenBean modelKnotenBean = getAktiveTreeModelBean();
	// if (modelKnotenBean == null){
	// return null;
	// }else{
	// if (modelKnotenBean.getIBean() instanceof ArtikelBean){
	// return modelKnotenBean;
	// }else{
	// if (modelKnotenBean.getIBean() instanceof EtageBean){
	// return modelKnotenBean.getVater();
	// }else{
	// Log.log().severe("falsche Klasse:"+modelKnotenBean.getIBean());
	// return null;
	// }
	// }
	// }
	// }

	@Override
	public void erstelleNeuenSatz() {
		getFehlerList().clear();

		try {
			ArtikelBean artikelBean = new ArtikelBean();
			artikelBean.setId(LagerConnection.getOneInstance().getID(
					Konstanten.ID_DB_ARTIKEL));
			artikelBean.setKeg_nr(artikelBean.getId()); 
			// halleBean.setStatus(AnforderungStatus.OFFEN);
			artikelBean.setBeanDBStatus(BeanDBStatus.INSERT); // als neu
			
			//Vorbelegung mit der vorbelegter Mengeneinheit
			try {
				MengenEinheitBean mengenEinheitBean =  getMengenEinheitBeanDB().sucheVordefinierteMengenEinheit();
				artikelBean.setMengenEinheitBean(mengenEinheitBean);
			} catch (LagerException e) {
				e.printStackTrace();
			}
			
			//Vordefinierter Mindestbestand
			artikelBean.setMindestbestand(1);
			
			//Vordefinieerte empfohlene Bestellmenge
			artikelBean.setEmpfohlene_bestellmenge(2);

			// markieren
			ModelKnotenBean modelKnotenBean;
			// ein neuer leerer Knoten wird erzeugt
			modelKnotenBean = new ArtikelModelKnotenBean(artikelBean);
			getModelBeanList().add(0, modelKnotenBean);// Der neue Knoten wird
			// an der ersten
			// Position angezeigt.
			// setAktiveModelBean(getModelBeanList().get(0));//Die erste
			// Position wird ausgewählt
			setAktiveTreeModelBean(getModelBeanList().get(0));
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
				speichereUnterArtikel(bean);
				speichereBestandspackstuecke(bean);
				speichereArtikelBaugruppe(bean);
				speichereLieferantenBestellnummer(bean);
				getBeanDB().saveBean((ArtikelBean) bean); // wichtigste Zeile in
				// dieser Procedure
				loescheBeanAusModel(bean, false);
				break;
			case INSERT_DELETE:
				loescheBeanAusModel(bean, false);
				break;
			default:
				getBeanDB().saveBean((ArtikelBean) bean); // wichtigste Zeile in
				// dieser Procedure
				speichereUnterArtikel(bean);				
				speichereBestandspackstuecke(bean);
				speichereArtikelBaugruppe(bean);
				speichereLieferantenBestellnummer(bean);
				leseSatzAnhandIDneuEin((ArtikelBean) bean);
				break;
			}

			LagerConnection.commit(); // transktion ist erfolgreich
			// abgeschlossen.
		} catch (Exception e) {
			try {
				LagerConnection.rollback();// abbruch der Verarbeitung.
				// DB-Änderungen werden
				// zurückgenommen.
			} catch (SQLException e1) {
				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,
						"Fehler beim Rollback:" + e.getMessage());
				getFehlerList().add(fehler);
				bean.getFehlerList().add(fehler);
				e.printStackTrace();
				Log.log().severe(e.getMessage());
			}
			Fehler fehler = new Fehler(25, FehlerTyp.FEHLER,
					"Satz kann nicht gespeichert werden:" + e.getMessage());
			getFehlerList().add(fehler);
			bean.getFehlerList().add(fehler);
			e.printStackTrace();
			Log.log().severe(e.getMessage());
		}
		// Anzeige auffrischen
		benachrichtigeBeobachter();
	}

	private void speichereBestandspackstuecke(Bean bean) throws Exception {

		// Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer
		// Collection erlaubt.
		// Collection ist eine Sammlung von Daten(z.B. ein Array, oder
		// ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean()
				.getKinderList().iterator();
		while (iterator.hasNext()) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); // alte Fehler löschen,
														// vorm Schreiben
			try {
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof BestandspackstueckBean) {
					BestandspackstueckBean bp = (BestandspackstueckBean) ibean;
					if (bean.getBeanDBStatus() == BeanDBStatus.DELETE) {
						// Löschen
						bp.setBeanDBStatus(BeanDBStatus.DELETE);
					}
					getBestandspackstueckBeanDB().saveBean(bp);
				}
			} catch (Exception e) {
				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER, "Fehler:"
						+ e.getMessage());
				modelKnotenBean.getFehlerList().add(fehler); // Den Fehler in
				// der Position
				// speichern.
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	private void speichereUnterArtikel(Bean bean) throws Exception {

		// Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer
		// Collection erlaubt.
		// Collection ist eine Sammlung von Daten(z.B. ein Array, oder
		// ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean()
				.getKinderList().iterator();
		while (iterator.hasNext()) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); // alte Fehler löschen,
														// vorm Schreiben
			try {
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof UnterArtikelBean) {
					UnterArtikelBean unterArtikel = (UnterArtikelBean) ibean;
					if (bean.getBeanDBStatus() == BeanDBStatus.DELETE) {
						// Löschen
						unterArtikel.setBeanDBStatus(BeanDBStatus.DELETE);
					}
					getUnterArtikelBeanDB().saveBean(unterArtikel);
				}
			} catch (Exception e) {
				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER, "Fehler:"
						+ e.getMessage());
				modelKnotenBean.getFehlerList().add(fehler); // Den Fehler in
				// der Position
				// speichern.
				e.printStackTrace();
				throw e;
			}
		}
	}	

	private void speichereArtikelBaugruppe(Bean bean) throws Exception {

		// Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer
		// Collection erlaubt.
		// Collection ist eine Sammlung von Daten(z.B. ein Array, oder
		// ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean()
				.getKinderList().iterator();
		while (iterator.hasNext()) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); // alte Fehler löschen,
														// vorm Schreiben
			try {
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof BaugruppeArtikelBean) {
					BaugruppeArtikelBean artBaugruppe = (BaugruppeArtikelBean) ibean;
					if (bean.getBeanDBStatus() == BeanDBStatus.DELETE) {
						// Löschen
						artBaugruppe.setBeanDBStatus(BeanDBStatus.DELETE);
					}
					getBaugruppeArtikelBeanDB().saveBean(artBaugruppe);
				}
			} catch (Exception e) {
				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER, "Fehler:"
						+ e.getMessage());
				modelKnotenBean.getFehlerList().add(fehler); // Den Fehler in
				// der Position
				// speichern.
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	
	private void speichereLieferantenBestellnummer(Bean bean) throws Exception {

		// Iterator ist eine Interface, welche das Durchsuche/Durchlaufe einer
		// Collection erlaubt.
		// Collection ist eine Sammlung von Daten(z.B. ein Array, oder
		// ArrayList)
		Iterator<ModelKnotenBean> iterator = bean.getModelKnotenBean()
				.getKinderList().iterator();
		while (iterator.hasNext()) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			modelKnotenBean.getFehlerList().clear(); // alte Fehler löschen,
														// vorm Schreiben
			try {
				IBean ibean = modelKnotenBean.getIBean();
				if (ibean instanceof LieferantenBestellnummerBean) {
					LieferantenBestellnummerBean lbBean = (LieferantenBestellnummerBean) ibean;
					if (bean.getBeanDBStatus() == BeanDBStatus.DELETE) {
						// Löschen
						lbBean.setBeanDBStatus(BeanDBStatus.DELETE);
					}
					getLieferantenBestellnummerBeanDB().saveBean(lbBean);
				}
			} catch (Exception e) {
				Fehler fehler = new Fehler(25, FehlerTyp.FEHLER, "Fehler:"
						+ e.getMessage());
				modelKnotenBean.getFehlerList().add(fehler); // Den Fehler in
				// der Position
				// speichern.
				e.printStackTrace();
				throw e;
			}
		}
	}
	

	
	
	@Override
	protected void leseSatzAnhandIDneuEin(Bean bean) throws SQLException,
			LagerException, BenutzerOberflacheLagerException {

		ArtikelBean artikelBean = (ArtikelBean) bean;
		ArtikelBean artikelBeanNeu = (ArtikelBean) getBeanDB().selectAnhandID(
				artikelBean.getId(), 0+1, getBeanDB().getDepthLevelArrayDB()); // Bestellanforderung wird neu aus DB
		// gelesen
		artikelBean.getModelKnotenBean().setIBean(artikelBeanNeu); // aktueller
		// Knoten
		// wird mit
		// neuen
		// Daten
		// gefüllt.

		// //Die neue Bestellanforderung(frisch aus Datebank ersetzt die alte
		// gespeicherte ArtikelBean)

		ArrayList<BestandspackstueckBean> bestandspackstueckBeans = getBestandspackstueckBeanDB()
		.sucheAnhandArtikelBean(artikelBeanNeu, 0+1, getBeanDB().getDepthLevelArrayDB());
		artikelBeanNeu.getModelKnotenBean().getKinderList().clear();
		// artikelBeanNeu.getBestandspackstueckBeans().clear();
		for (int i = 0; i < bestandspackstueckBeans.size(); i++) {
			BestandspackstueckBean bestandspackstueckBean = bestandspackstueckBeans
					.get(i);
			BestandspackstueckModelKnotenBean bestandspackstueckModelKnotenBean = new BestandspackstueckModelKnotenBean(
					bestandspackstueckBean);
			artikelBeanNeu.getModelKnotenBean().getKinderList().add(
					bestandspackstueckModelKnotenBean);
			bestandspackstueckModelKnotenBean.setVater(artikelBeanNeu
					.getModelKnotenBean());
			// artikelBeanNeu.getBestandspackstueckBeans().add(
			// bestandspackstueckBean);
		}

		ArrayList<BaugruppeArtikelBean> baugruppenArtikelBeans = getBaugruppeArtikelBeanDB()
		.sucheAnhandArtikelBean(artikelBeanNeu);
		for (int i = 0; i < baugruppenArtikelBeans.size(); i++) {
			BaugruppeArtikelBean bestandspackstueckBean = baugruppenArtikelBeans
					.get(i);
			BaugruppeArtikelModelKnotenBean baugruppeArtikelModelKnoten = new BaugruppeArtikelModelKnotenBean(
					bestandspackstueckBean,artikelBeanNeu
					.getModelKnotenBean());
			artikelBeanNeu.getModelKnotenBean().getKinderList().add(
					baugruppeArtikelModelKnoten);
//			bestandspackstueckModelKnotenBean.setVater(artikelBeanNeu
//					.getModelKnotenBean());
			// artikelBeanNeu.getBestandspackstueckBeans().add(
			// bestandspackstueckBean);
		}

		ArrayList<UnterArtikelBean> unterArtikelBeans = getUnterArtikelBeanDB()
		.sucheAnhandArtikelBean(artikelBeanNeu);
		for (int i = 0; i < unterArtikelBeans.size(); i++) {
			UnterArtikelBean lbBean = unterArtikelBeans
					.get(i);
			UnterArtikelModelKnotenBean modelKnoten = new UnterArtikelModelKnotenBean(
					lbBean,artikelBeanNeu.getModelKnotenBean());
			artikelBeanNeu.getModelKnotenBean().getKinderList().add(
					modelKnoten);
//			bestandspackstueckModelKnotenBean.setVater(artikelBeanNeu
//					.getModelKnotenBean());
			// artikelBeanNeu.getBestandspackstueckBeans().add(
			// bestandspackstueckBean);
		}

		ArrayList<LieferantenBestellnummerBean> lieferantBestellnummerBeans = getLieferantenBestellnummerBeanDB()
		.sucheAnhandArtikelBean(artikelBeanNeu);
		for (int i = 0; i < lieferantBestellnummerBeans.size(); i++) {
			LieferantenBestellnummerBean lbBean = lieferantBestellnummerBeans
					.get(i);
			LieferantenBestellnummerModelKnotenBean  modelKnoten = new LieferantenBestellnummerModelKnotenBean(
					lbBean,artikelBeanNeu.getModelKnotenBean());
			artikelBeanNeu.getModelKnotenBean().getKinderList().add(
					modelKnoten);
//			bestandspackstueckModelKnotenBean.setVater(artikelBeanNeu
//					.getModelKnotenBean());
			// artikelBeanNeu.getBestandspackstueckBeans().add(
			// bestandspackstueckBean);
		}

		//OberArtikel werden aus der DB gelesen und in der OberARtikel-Liste abgespeichert.
//		VaterArtikelArray oberArtikelBeans = 
		
//		switch (((ArtikelModelKnotenBean)getSelectedListModelKnotenBean()).getAktiverReiter()) {
		switch (getAktiverReiter()) {
		case OBERARTIKEL:
			getUnterArtikelBeanDB().getOberArtikelListe(artikelBeanNeu,0+1);
			artikelBeanNeu.getOberArtikelBeans().fill();
			break;
		default:
			artikelBeanNeu.getOberArtikelBeans().clear();
			break;
		} 
		;
		
		
//		artikelBeanNeu.setOberArtikelBeans(oberArtikelBeans);
		
		// setEtageenUndZeilen(etageBeans,zeileBeans,(ArtikelModelKnotenBean)
		// getSelectedListModelKnotenBean());
		super.leseSatzAnhandIDneuEin(bean); // Am Schluss aufrufen
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
				// LoescheObjektAusDerListe((ArtikelBean)bean);
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
					getBeanDB().saveBean((ArtikelBean) bean); // wichtigste
					// Zeile
					// in dieser
					// Procedure
					loescheBeanAusModel(bean, true);
					// LoescheObjektAusDerListe((ArtikelBean)bean);
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

	//	
	// public void erzeugeNeueEtage(){
	// getFehlerList().clear();
	//		
	// try {
	// //neuer BestellanfoderungsPositions-Knoten wird erzeugt
	// ArtikelBean artikelBean =
	// (ArtikelBean)getSelectedListModelKnotenBean().getIBean();
	// EtageModelKnotenBean etageModelKnotenBean = new
	// EtageModelKnotenBean(getSelectedListModelKnotenBean());
	// EtageBean etageBean = new EtageBean();
	// etageBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ETAGE));
	// etageBean.setArtikelBean(artikelBean); //aktueller User aus der Liste
	// // etageBean.setEtage(new EtageBean()); //neu
	// etageBean.setBeanDBStatus(BeanDBStatus.INSERT);
	// etageModelKnotenBean.setIBean(etageBean);
	// getSelectedListModelKnotenBean().getKinderList().add(0,etageModelKnotenBean);
	// artikelBean.getEtageBeans().add(0,etageBean);
	// //Beobachter werden benachrichtigt
	// benachrichtigeDetailsBeobachter();
	// benachrichtigeTreeBeobachter();
	// // benachrichtigeTreePaneBeobachter();
	// } catch (BenutzerOberflacheLagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// } catch (IdLagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// } catch (SQLException e) {
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }
	//
	// public void loescheEtage(EtageBean zuLoeschendeBenutzerPosBean) {
	// getFehlerList().clear();
	// zuLoeschendeBenutzerPosBean.getFehlerList().clear();
	//
	//		
	// Iterator<ModelKnotenBean> iterator =
	// getAktiveTreeModelBean().getKinderList().iterator();
	//		
	// for(boolean abbruch = false; iterator.hasNext()&&!abbruch; ){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// if (modelKnotenBean.getIBean() instanceof EtageBean &&
	// modelKnotenBean.getIBean().equals(zuLoeschendeBenutzerPosBean)){
	// if (modelKnotenBean.getKinderList().size() > 0) {
	// try {
	// throw new LagerException(
	// new Fehler(
	// "Etage darf nicht gelöscht werden. Löschen Sie zuerst alle Zeilen."));
	// } catch (LagerException e) {
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// } else {
	//					
	//				
	// switch (zuLoeschendeBenutzerPosBean.getBeanDBStatus()){
	// case INSERT : {
	// getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
	// break;
	// }
	// case SELECT :
	// zuLoeschendeBenutzerPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// case UPDATE :
	// zuLoeschendeBenutzerPosBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// default :
	// zuLoeschendeBenutzerPosBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT);
	// break;
	// }
	// abbruch = true;
	// }
	// }
	// }
	// benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	// }
	//	
	// public void loescheZeile(ZeileBean zuLoeschendeZeileBean) {
	// getFehlerList().clear();
	// zuLoeschendeZeileBean.getFehlerList().clear();
	//		
	// Iterator<ModelKnotenBean> iterator =
	// getAktiveTreeModelBean().getKinderList().iterator();
	//		
	// for(boolean abbruch = false; iterator.hasNext()&&!abbruch; ){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// if (modelKnotenBean.getIBean() instanceof ZeileBean &&
	// modelKnotenBean.getIBean().equals(zuLoeschendeZeileBean)){
	// if (modelKnotenBean.getKinderList().size() > 0) {
	// try {
	// throw new LagerException(
	// new Fehler(
	// "Zeile darf nicht gelöscht werden. Löschen Sie zuerst alle Säulen."));
	// } catch (LagerException e) {
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// } else {
	//					
	//				
	// switch (zuLoeschendeZeileBean.getBeanDBStatus()){
	// case INSERT : {
	// getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
	// break;
	// }
	// case SELECT :
	// zuLoeschendeZeileBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// case UPDATE :
	// zuLoeschendeZeileBean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// default : zuLoeschendeZeileBean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT);
	// break;
	// }
	// abbruch = true;
	// }
	// }
	// }
	// benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	// }
	//	
	//	
	//	
	// //Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	// public void ausgewaehlterKnotenIstGeandert() {
	// ArrayList<Fehler> errors =
	// getAktiveTreeModelBean().getIBean().pruefeEigeneDaten();
	// getAktiveTreeModelBean().setFehlerList(errors);
	// if (getAktiveTreeModelBean() instanceof EtageModelKnotenBean){
	// benachrichtigeDetailsBeobachter();
	// }else if(getAktiveTreeModelBean() instanceof ArtikelModelKnotenBean){
	// benachrichtigeDetailsBeobachter();
	// }else {
	// Log.log().severe("Fehler im Ablauf. Objekt-Klasse ist nicht vorgesehen.");
	// }
	// benachrichtigeTreeBeobachter();
	// }

	// /**
	// * Ermittelt, an welcher Stelle in der Ba-Liste die aktuell selektierte
	// Bestellanforderung steht.
	// */
	// @Override
	// public int getSelectedListModelKnotenBeanLaufendeNummer() {
	// ModelKnotenBean modelKnotenBean = getSelectedListModelKnotenBean();
	// for(int i = 0; i<getModelBeanList().size();i++){
	// if (getModelBeanList().get(i).equals(modelKnotenBean)){
	// return i;
	// }
	// }
	// return -1;
	// }

	// public ArrayList<Fehler> pruefeAktuelleBean() {
	//		
	// getFehlerList().clear();
	// //Die aktuelle Bestellanforderung wird geprüft
	// ArrayList<Fehler> errors =
	// getSelectedListModelKnotenBean().getIBean().pruefeEigeneDaten();
	// if (errors.size()>0){
	// errors.add(0,new Fehler(25,FehlerTyp.FEHLER,"Fehler im Benutzersatz, id="
	// + ((ArtikelBean)getSelectedListModelKnotenBean().getIBean()).getId()));
	// }
	// Iterator iterator =
	// getSelectedListModelKnotenBean().getKinderList().iterator();
	// while(iterator.hasNext()){
	// //Jede Position der aktuellen Bestellanforderung wird geprüft
	// ModelKnotenBean modelKnotenBean = (ModelKnotenBean)iterator.next();
	// BaPosBean baPosBean = (BaPosBean)modelKnotenBean.getIBean();
	// ArrayList<Fehler> errorsPos = baPosBean.pruefeEigeneDaten();
	// if (errorsPos.size()>0){
	// errors.add(new
	// Fehler(25,FehlerTyp.FEHLER,"Fehler in der Position(Abteilung), id=" +
	// baPosBean.getId()));
	// for(int i=0;i<errorsPos.size();i++){
	// errors.add(errorsPos.get(i));
	// }
	// }
	// }
	// setFehlerList(errors);
	// return getFehlerList();
	// }

	@Override
	public ModelKnotenBean getNeueListBean(IBean iBean) {
		try {
			return new ArtikelModelKnotenBean((ArtikelBean) iBean);
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
			return null;
		}
	}

	public void erzeugeNeueBestandspackstueck() {
		getFehlerList().clear();
		try {

			ArtikelBean artikelBean = (ArtikelBean) getSelectedListModelKnotenBean()
					.getIBean();
			// neuer Bestandspackstück-Knoten wird erzeugt
			BestandspackstueckBean bestandspackstueckBean = new BestandspackstueckBean();
			bestandspackstueckBean.setArtikelBean(artikelBean);
			// artikelBean.getBestandspackstueckBeans().add(0,
			// bestandspackstueckBean);

			//Vorbelegung Abteilung
			ArrayList<BenutzerAbteilungBean> abteilungen = Run.getOneInstance().getBenutzerBean().getBenutzerAbteilungBeans();
			if (abteilungen!=null&abteilungen.size()==1){
				bestandspackstueckBean.setAbteilung(abteilungen.get(0).getAbteilung());
			}
			
			BestandspackstueckModelKnotenBean neuerKnotenBean = new BestandspackstueckModelKnotenBean(
					bestandspackstueckBean);

			neuerKnotenBean.setVater(getAktiveTreeModelBean());
			getSelectedListModelKnotenBean().getKinderList().add(0,
					neuerKnotenBean);

			bestandspackstueckBean.setId(LagerConnection.getOneInstance()
					.getID(Konstanten.ID_DB_BESTANDSPACKSTUECK)); // neu
			bestandspackstueckBean.setBeanDBStatus(BeanDBStatus.INSERT);

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

	public void loescheBestandspackstueck(BestandspackstueckBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();

//		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean()
//				.getKinderList().iterator();
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean()
		.getKinderList().iterator();
		

		for (boolean abbruch = false; iterator.hasNext() && !abbruch;) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof BestandspackstueckBean
					&& modelKnotenBean.getIBean().equals(bean)) {
				switch (bean.getBeanDBStatus()) {
				case INSERT: {
					getSelectedListModelKnotenBean().getKinderList().remove(
							modelKnotenBean);
					// ((ArtikelBean)
					// getSelectedListModelKnotenBean().getIBean())
					// .getBestandspackstueckBeans().remove(bean);
					break;
				}
				case SELECT:
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
		benachrichtigeBeobachter();// alle Beobachter benachrichtigen
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

	private PositionBeanDB getPositionBeanDB() {
		if (positionBeanDB == null) {
			positionBeanDB = new PositionBeanDB();
		}
		return positionBeanDB;
	}

	private BestandspackstueckBeanDB getBestandspackstueckBeanDB() {
		if (bestandspackstueckBeanDB == null) {
			bestandspackstueckBeanDB = new BestandspackstueckBeanDB();
		}
		return bestandspackstueckBeanDB;
	}

	private BaugruppeArtikelBeanDB getBaugruppeArtikelBeanDB() {
		if (baugruppeArtikelBeanDB == null) {
			baugruppeArtikelBeanDB = new BaugruppeArtikelBeanDB();
		}
		return baugruppeArtikelBeanDB;
	}

	private BaugruppeBeanDB getBaugruppeBeanDB() {
		if (baugruppeBeanDB == null) {
			baugruppeBeanDB = new BaugruppeBeanDB();
		}
		return baugruppeBeanDB;
	}

	private BaugruppeSuchBean getBaugruppeSuchBean() {
		if (baugruppeSuchBean == null) {
			baugruppeSuchBean = new BaugruppeSuchBean();
		}
		return baugruppeSuchBean;
	}

	public ArrayList<BaugruppeBean> getBaugruppeBeans() {
		if (baugruppeBeans == null) {
			baugruppeBeans = new ArrayList<BaugruppeBean>();
		}
		return baugruppeBeans;
	}

	public void erzeugeNeueArtikelBaugruppe() {
		getFehlerList().clear();
		try {

			ArtikelBean artikelBean = (ArtikelBean) getSelectedListModelKnotenBean()
					.getIBean();
			// neuer Bestandspackstück-Knoten wird erzeugt
			BaugruppeArtikelBean baugruppeArtikelBean = new BaugruppeArtikelBean();
			baugruppeArtikelBean.setArtikel(artikelBean);
			// artikelBean.getBestandspackstueckBeans().add(0,
			// bestandspackstueckBean);

			BaugruppeArtikelModelKnotenBean neuerKnotenBean = new BaugruppeArtikelModelKnotenBean(
					baugruppeArtikelBean,getAktiveTreeModelBean());

//			neuerKnotenBean.setVater(getAktiveTreeModelBean());

			//suche, wann Bestand zu ende ist.
			int anz=0;
			for(int i = 0; i < getSelectedListModelKnotenBean().getKinderList().size();i++){
				ModelKnotenBean modelKnotenBean = getSelectedListModelKnotenBean().getKinderList().get(i);
				if (!(modelKnotenBean instanceof BestandspackstueckModelKnotenBean)){
					break;
				}
				anz++;
			}
			getSelectedListModelKnotenBean().getKinderList().add(anz,neuerKnotenBean);

//			baugruppeArtikelBean.setId(LagerConnection.getOneInstance()
//					.getID(Konstanten.ID_DB_)); // neu
			baugruppeArtikelBean.setBeanDBStatus(BeanDBStatus.INSERT);

			// Beobachter werden benachrichtigt
			benachrichtigeDetailsBeobachter();
			benachrichtigeTreeBeobachter();
			// benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
//		} catch (IdLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			getFehlerList().add(new Fehler(e));
//			e.printStackTrace();
//		}

		}
	}

	public void loescheArtikelBaugruppe(BaugruppeArtikelBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();

//		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean()
//				.getKinderList().iterator();
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean()
		.getKinderList().iterator();
		

		for (boolean abbruch = false; iterator.hasNext() && !abbruch;) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof BaugruppeArtikelBean
					&& modelKnotenBean.getIBean().equals(bean)) {
				switch (bean.getBeanDBStatus()) {
				case INSERT: {
					getSelectedListModelKnotenBean().getKinderList().remove(
							modelKnotenBean);
					// ((ArtikelBean)
					// getSelectedListModelKnotenBean().getIBean())
					// .getBestandspackstueckBeans().remove(bean);
					break;
				}
				case SELECT:
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
		benachrichtigeBeobachter();// alle Beobachter benachrichtigen
	}

	public void erzeugeNeueLieferantenBestellnummer() {
		getFehlerList().clear();
		try {

			ArtikelBean artikelBean = (ArtikelBean) getSelectedListModelKnotenBean()
					.getIBean();
			// neuer Bestandspackstück-Knoten wird erzeugt
			LieferantenBestellnummerBean lieferantenBestellnummerBean = new LieferantenBestellnummerBean();
			lieferantenBestellnummerBean.setArtikel(artikelBean);
			// artikelBean.getBestandspackstueckBeans().add(0,
			// bestandspackstueckBean);

			LieferantenBestellnummerModelKnotenBean  neuerKnotenBean = new LieferantenBestellnummerModelKnotenBean(
					lieferantenBestellnummerBean,getAktiveTreeModelBean());

//			neuerKnotenBean.setVater(getAktiveTreeModelBean());
			
			getSelectedListModelKnotenBean().getKinderList().add(
					neuerKnotenBean);

//			baugruppeArtikelBean.setId(LagerConnection.getOneInstance()
//					.getID(Konstanten.ID_DB_)); // neu
			lieferantenBestellnummerBean.setBeanDBStatus(BeanDBStatus.INSERT);

			// Beobachter werden benachrichtigt
			benachrichtigeDetailsBeobachter();
			benachrichtigeTreeBeobachter();
			// benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
//		} catch (IdLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			getFehlerList().add(new Fehler(e));
//			e.printStackTrace();
//		}

		}
	}

	private LieferantenBestellnummerBeanDB getLieferantenBestellnummerBeanDB() {
		if(lieferantenBestellnummerBeanDB==null){
			lieferantenBestellnummerBeanDB = new LieferantenBestellnummerBeanDB();
		}
		return lieferantenBestellnummerBeanDB;
	}
	
	private UnterArtikelBeanDB getUnterArtikelBeanDB() {
		if(unterArtikelBeanDB==null){
			unterArtikelBeanDB = new UnterArtikelBeanDB();
		}
		return unterArtikelBeanDB;
	}	

	public void loescheLieferantenBestellnummer(
			LieferantenBestellnummerBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();

//		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean()
//				.getKinderList().iterator();
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean()
		.getKinderList().iterator();
		

		for (boolean abbruch = false; iterator.hasNext() && !abbruch;) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof LieferantenBestellnummerBean
					&& modelKnotenBean.getIBean().equals(bean)) {
				switch (bean.getBeanDBStatus()) {
				case INSERT: {
					getSelectedListModelKnotenBean().getKinderList().remove(
							modelKnotenBean);
					// ((ArtikelBean)
					// getSelectedListModelKnotenBean().getIBean())
					// .getBestandspackstueckBeans().remove(bean);
					break;
				}
				case SELECT:
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
		benachrichtigeBeobachter();// alle Beobachter benachrichtigen
	}

	public void erzeugeNeuenUnterArtikel() {
		getFehlerList().clear();
		try {
			ArtikelBean artikelBean = (ArtikelBean) getSelectedListModelKnotenBean()
					.getIBean();
			// neuer Bestandspackstück-Knoten wird erzeugt
			UnterArtikelBean unterArtikelBean = new UnterArtikelBean();
			unterArtikelBean.setVaterArtikelBean(artikelBean);
			// artikelBean.getBestandspackstueckBeans().add(0,
			// bestandspackstueckBean);

			UnterArtikelModelKnotenBean  neuerKnotenBean = new UnterArtikelModelKnotenBean(
					unterArtikelBean,getAktiveTreeModelBean());

//			neuerKnotenBean.setVater(getAktiveTreeModelBean());
			
			//suche, wann BaugruppenListe zu ende ist.
			int anz=0;
			for(int i = 0; i < getSelectedListModelKnotenBean().getKinderList().size();i++){
				ModelKnotenBean modelKnotenBean = getSelectedListModelKnotenBean().getKinderList().get(i);
				if ((modelKnotenBean instanceof LieferantenBestellnummerModelKnotenBean)){
					break;
				}
				anz++;
			}
			getSelectedListModelKnotenBean().getKinderList().add(anz,neuerKnotenBean);
			
			unterArtikelBean.setBeanDBStatus(BeanDBStatus.INSERT);

			// Beobachter werden benachrichtigt
			benachrichtigeDetailsBeobachter();
			benachrichtigeTreeBeobachter();
			// benachrichtigeTreePaneBeobachter();
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
//		} catch (IdLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			getFehlerList().add(new Fehler(e));
//			e.printStackTrace();
//		}

		}
	}

	public void loescheUnterArtikel(UnterArtikelBean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();

//		Iterator<ModelKnotenBean> iterator = getAktiveTreeModelBean()
//		.getKinderList().iterator();
		Iterator<ModelKnotenBean> iterator = getSelectedListModelKnotenBean()
		.getKinderList().iterator();

		for (boolean abbruch = false; iterator.hasNext() && !abbruch;) {
			ModelKnotenBean modelKnotenBean = iterator.next();
			if (modelKnotenBean.getIBean() instanceof UnterArtikelBean
					&& modelKnotenBean.getIBean().equals(bean)) {
				switch (bean.getBeanDBStatus()) {
				case INSERT: {
					getSelectedListModelKnotenBean().getKinderList().remove(
							modelKnotenBean);
					// ((ArtikelBean)
					// getSelectedListModelKnotenBean().getIBean())
					// .getBestandspackstueckBeans().remove(bean);
					break;
				}
				case SELECT:
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
		benachrichtigeBeobachter();// alle Beobachter benachrichtigen
	}

	// public void erzeugeNeueZeile(){
	// getFehlerList().clear();
	// try {
	//			
	// //Prüfen, ob die neue Zeile von einer Etage-Details-Maskeerzeugt wurde.
	// ModelKnotenBean aktiveKnotenBean = getAktiveTreeModelBean();
	// EtageBean etageBean = null;
	// if (aktiveKnotenBean!=null){
	// if (aktiveKnotenBean.getSammelKnotenTypENUM()==ModelKnotenTyp.ETAGE){
	// //die neue Zeile soll zu der bestimmten Etage gehören.
	// etageBean = (EtageBean)aktiveKnotenBean.getIBean();
	// }else{
	// etageBean = null;
	// }
	// }else{
	// etageBean = null;
	// }
	//			
	//			
	//			
	// // neuer BestellanfoderungsPositions-Knoten wird erzeugt
	// ArtikelBean halleBean = (ArtikelBean) getSelectedListModelKnotenBean()
	// .getIBean();
	// ZeileModelKnotenBean zeileModelKnotenBean =
	// new ZeileModelKnotenBean(getSelectedListModelKnotenBean());
	// ZeileBean zeileBean = new ZeileBean(); // Daten
	// zeileBean.setEtageBean(etageBean);
	// // im
	// // Knoten
	// zeileBean.setArtikelBean(halleBean); // aktueller User
	// // aus der Liste
	// zeileBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_ZEILE));
	// // neu
	// zeileBean.setBeanDBStatus(BeanDBStatus.INSERT);
	// zeileModelKnotenBean.setIBean(zeileBean);
	// if(etageBean!=null
	// &&
	// getAktiveTreeModelBean() != null
	// &&
	// getAktiveTreeModelBean() instanceof EtageModelKnotenBean){
	// //Aktive ist gerade eine Etage(modelknotenbean)
	// zeileModelKnotenBean.setVater(getAktiveTreeModelBean());
	// getAktiveTreeModelBean().getKinderList().add(
	// zeileModelKnotenBean);
	// }else{
	// //Zeile direkt in die Halle hinzufügen.
	// zeileModelKnotenBean.setVater(getSelectedListModelKnotenBean());
	// getSelectedListModelKnotenBean().getKinderList().add(
	// zeileModelKnotenBean);
	// }
	// halleBean.getZeileBeans().add(zeileBean);
	// // Beobachter werden benachrichtigt
	// benachrichtigeDetailsBeobachter();
	// benachrichtigeTreeBeobachter();
	// // benachrichtigeTreePaneBeobachter();
	// } catch (BenutzerOberflacheLagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// } catch (IdLagerException e) {
	// getFehlerList().add(e.getFehler());
	// e.printStackTrace();
	// } catch (SQLException e) {
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }
	//
	// public void erzeugeNeueSauele() {
	// getFehlerList().clear();
	//		
	// try {
	// //neuer Säule wird erzeugt
	// ZeileBean zeileBean = (ZeileBean)getAktiveTreeModelBean().getIBean();
	// SaeuleBean saeuleBean = new SaeuleBean();
	// saeuleBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_SAEULE));
	// saeuleBean.setZeileBean(zeileBean);
	// saeuleBean.setBeanDBStatus(BeanDBStatus.INSERT);
	// zeileBean.getSaeuleBeans().add(0,saeuleBean);
	//		
	// //neuer Anzeige-Knoten für die Säule wird erzeugt.
	// SaeuleModelKnotenBean saeuleModelKnotenBean = new
	// SaeuleModelKnotenBean(saeuleBean);
	// getAktiveTreeModelBean().getKinderList().add(0,saeuleModelKnotenBean);
	// saeuleModelKnotenBean.setVater(getSelectedListModelKnotenBean());
	//		
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
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }
	//
	// public void erzeugeNeuePosition() {
	// getFehlerList().clear();
	//		
	// try {
	// //neuer Säule wird erzeugt
	// EbeneBean ebeneBean = (EbeneBean)getAktiveTreeModelBean().getIBean();
	// PositionBean positionBean = new PositionBean();
	// positionBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_POSITION));
	// positionBean.setEbeneBean(ebeneBean);
	// positionBean.setBeanDBStatus(BeanDBStatus.INSERT);
	// ebeneBean.getPositionBeans().add(0,positionBean);
	//		
	// //neuer Anzeige-Knoten für die Säule wird erzeugt.
	// PositionModelKnotenBean positionModelKnotenBean = new
	// PositionModelKnotenBean(positionBean);
	// getAktiveTreeModelBean().getKinderList().add(0,positionModelKnotenBean);
	// positionModelKnotenBean.setVater(getSelectedListModelKnotenBean());
	//		
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
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }
	//
	// public void erzeugeNeueEbene() {
	// getFehlerList().clear();
	//		
	// try {
	// //neuer Säule wird erzeugt
	// SaeuleBean saeuleBean = (SaeuleBean)getAktiveTreeModelBean().getIBean();
	// EbeneBean ebeneBean = new EbeneBean();
	// ebeneBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_EBENE));
	// ebeneBean.setSaeuleBean(saeuleBean);
	// ebeneBean.setBeanDBStatus(BeanDBStatus.INSERT);
	// saeuleBean.getEbeneBeans().add(0,ebeneBean);
	//		
	// //neuer Anzeige-Knoten für die Säule wird erzeugt.
	// EbeneModelKnotenBean ebeneModelKnotenBean = new
	// EbeneModelKnotenBean(ebeneBean);
	// getAktiveTreeModelBean().getKinderList().add(0,ebeneModelKnotenBean);
	// ebeneModelKnotenBean.setVater(getSelectedListModelKnotenBean());
	//		
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
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }
	//
	// public void erzeugeNeueBestandspackstueck() {
	// getFehlerList().clear();
	//		
	// try {
	// //neuer Säule wird erzeugt
	// PositionBean positionBean =
	// (PositionBean)getAktiveTreeModelBean().getIBean();
	// BestandspackstueckBean bestandspackstueckBean = new
	// BestandspackstueckBean();
	// bestandspackstueckBean.setId(LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BESTANDSPACKSTUECK));
	// bestandspackstueckBean.setPositionBean(positionBean);
	// bestandspackstueckBean.setBeanDBStatus(BeanDBStatus.INSERT);
	// positionBean.getBestandspackstueckBeans().add(0,bestandspackstueckBean);
	//		
	// //neuer Anzeige-Knoten für die Säule wird erzeugt.
	// BestandspackstueckModelKnotenBean bestandspackstueckModelKnotenBean = new
	// BestandspackstueckModelKnotenBean(bestandspackstueckBean);
	// getAktiveTreeModelBean().getKinderList().add(0,bestandspackstueckModelKnotenBean);
	// bestandspackstueckModelKnotenBean.setVater(getSelectedListModelKnotenBean());
	//		
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
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }
	//
	// public void loescheBestandspackstueck(BestandspackstueckBean bean) {
	// getFehlerList().clear();
	// bean.getFehlerList().clear();
	//		
	// Iterator<ModelKnotenBean> iterator =
	// getAktiveTreeModelBean().getKinderList().iterator();
	//		
	// for(boolean abbruch = false; iterator.hasNext()&&!abbruch; ){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// if (modelKnotenBean.getIBean() instanceof BestandspackstueckBean &&
	// modelKnotenBean.getIBean().equals(bean)){
	// switch (bean.getBeanDBStatus()){
	// case INSERT : {
	// getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
	// break;
	// }
	// case SELECT : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// case UPDATE : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// default : bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
	// }
	// abbruch = true;
	// }
	// }
	// benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	// }
	//
	// public void loescheEbene(EbeneBean bean) {
	// getFehlerList().clear();
	// bean.getFehlerList().clear();
	//		
	// Iterator<ModelKnotenBean> iterator =
	// getAktiveTreeModelBean().getKinderList().iterator();
	//		
	// for(boolean abbruch = false; iterator.hasNext()&&!abbruch; ){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// if (modelKnotenBean.getIBean() instanceof EbeneBean &&
	// modelKnotenBean.getIBean().equals(bean)){
	// if (modelKnotenBean.getKinderList().size()>0){
	// try {
	// throw new LagerException(new
	// Fehler("Ebene darf nicht gelöscht werden. Löschen Sie zuerst alle Positionen."));
	// } catch (LagerException e) {
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }else{
	// switch (bean.getBeanDBStatus()) {
	// case INSERT: {
	// getSelectedListModelKnotenBean().getKinderList()
	// .remove(modelKnotenBean);
	// break;
	// }
	// case SELECT:
	// bean.setBeanDBStatus(BeanDBStatus.DELETE);
	// break;
	// case UPDATE:
	// bean.setBeanDBStatus(BeanDBStatus.DELETE);
	// break;
	// default:
	// bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT);
	// break;
	// }
	// abbruch = true;
	// }
	// }
	// }
	// benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	// }
	//
	// public void loescheSaeule(SaeuleBean bean) {
	// getFehlerList().clear();
	// bean.getFehlerList().clear();
	//		
	// Iterator<ModelKnotenBean> iterator =
	// getAktiveTreeModelBean().getKinderList().iterator();
	//		
	// for(boolean abbruch = false; iterator.hasNext()&&!abbruch; ){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// if (modelKnotenBean.getIBean() instanceof SaeuleBean &&
	// modelKnotenBean.getIBean().equals(bean)){
	// if (modelKnotenBean.getKinderList().size() > 0) {
	// try {
	// throw new LagerException(
	// new Fehler(
	// "Säule darf nicht gelöscht werden. Löschen Sie zuerst alle Ebenen."));
	// } catch (LagerException e) {
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// } else {
	// switch (bean.getBeanDBStatus()) {
	// case INSERT: {
	// getSelectedListModelKnotenBean().getKinderList()
	// .remove(modelKnotenBean);
	// break;
	// }
	// case SELECT:
	// bean.setBeanDBStatus(BeanDBStatus.DELETE);
	// break;
	// case UPDATE:
	// bean.setBeanDBStatus(BeanDBStatus.DELETE);
	// break;
	// default:
	// bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT);
	// break;
	// }
	// abbruch = true;
	// }
	// }
	// }
	// benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	// }
	//
	// public void loescheLagerPosition(PositionBean bean) {
	// getFehlerList().clear();
	// bean.getFehlerList().clear();
	//		
	// Iterator<ModelKnotenBean> iterator =
	// getAktiveTreeModelBean().getKinderList().iterator();
	//		
	// for(boolean abbruch = false; iterator.hasNext()&&!abbruch; ){
	// ModelKnotenBean modelKnotenBean = iterator.next();
	// if (modelKnotenBean.getIBean() instanceof PositionBean &&
	// modelKnotenBean.getIBean().equals(bean)){
	// if (modelKnotenBean.getKinderList().size()>0){
	// try {
	// throw new LagerException(new
	// Fehler("Position darf nicht gelöscht werden. Löschen Sie zuerst den Bestand."));
	// } catch (LagerException e) {
	// getFehlerList().add(new Fehler(e));
	// e.printStackTrace();
	// }
	// }else{
	// switch (bean.getBeanDBStatus()){
	// case INSERT : {
	// getSelectedListModelKnotenBean().getKinderList().remove(modelKnotenBean);
	// break;
	// }
	// case SELECT : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// case UPDATE : bean.setBeanDBStatus(BeanDBStatus.DELETE);break;
	// default : bean.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
	// }
	// abbruch = true;
	// }
	// }
	// }
	// benachrichtigeBeobachter();//alle Beobachter benachrichtigen
	// }
	//	
	//	
	// // @Override
	// // public void addISuchBeobachters(ISuchBeobachter
	// iHalleSuchPaneBeobachter) {
	// // getiHalleSuchPaneBeobachters().add(iHalleSuchPaneBeobachter);
	// // benachrichtigeSuchBeobachter();
	// // }
	//
	//

	public void setAktiverReiter(AktiverReiter aktiverReiter) {
		this.aktiverReiter = aktiverReiter;
//		switch (((ArtikelModelKnotenBean)getSelectedListModelKnotenBean()).getAktiverReiter()) {
		
//		((ArtikelModelKnotenBean)getSelectedListModelKnotenBean()).setAktiverReiter(aktiverReiter);
		ArtikelBean artikelBean = ((ArtikelModelKnotenBean)getSelectedListModelKnotenBean()).getIBean();
		switch (getAktiverReiter()) {
		case OBERARTIKEL:
			try {
				getUnterArtikelBeanDB().getOberArtikelListe(artikelBean,0);
				artikelBean.getOberArtikelBeans().fill();
			} catch (Exception e) {
				getFehlerList().add(new Fehler(e.getMessage()));
				e.printStackTrace();
				artikelBean.getOberArtikelBeans().clear();
			}
			break;
		default:
			artikelBean.getOberArtikelBeans().clear();
			break;
		};
		benachrichtigeBeobachter();
	}

	public AktiverReiter getAktiverReiter() {
		if (aktiverReiter==null){
			aktiverReiter = AktiverReiter.LAGERPLATZ;
		}
		return aktiverReiter;
	}

	public void setMengenEinheitBeanDB(MengenEinheitBeanDB mengenEinheitBeanDB) {
		this.mengenEinheitBeanDB = mengenEinheitBeanDB;
	}

	public MengenEinheitBeanDB getMengenEinheitBeanDB() {
		if (this.mengenEinheitBeanDB==null){
				this.mengenEinheitBeanDB = new MengenEinheitBeanDB();
		}
		return mengenEinheitBeanDB;
	}
}
