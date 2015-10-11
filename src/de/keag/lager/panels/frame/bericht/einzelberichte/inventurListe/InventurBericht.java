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
package de.keag.lager.panels.frame.bericht.einzelberichte.inventurListe;
/* 
 * 
 * http://barcode4j.sourceforge.net/2.1/building.html <<< WICHTIG
 * http://barbecue.sourceforge.net/  
 * http://barcode4j.sourceforge.net/ << HOMEPAGE 
 * http://jasperforge.org/uploads/publish/jasperreportswebsite/trunk/sample.reference/barcode4j/index.html
 * xml-apis-ext-1.3.04.jar <<< Diese Jar wurde extra heruntergeladen, damit der JasperViewer die Datei anzeigen kann.
 * C:\ATA2_1_XP\ATA2_3_160\Daten\Eigene Dateien\aktuell\Job\Java\eclipse_workspace\Lager\lib\barcode4j-2.1.0\lib\xml-apis-ext-1.3.04.jar 
 */
	
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.email.SendJavaMail;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.jasperReports.ReportDriver;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.BaBeanDB;
import de.keag.lager.db.BaPosBeanDB;
import de.keag.lager.db.BerichtBeanDB;
import de.keag.lager.db.EtageBeanDB;
import de.keag.lager.db.HalleBeanDB;
import de.keag.lager.db.SaeuleBeanDB;
import de.keag.lager.db.ZeileBeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtSpalte;
import de.keag.lager.panels.frame.bericht.BerichtZeile;
import de.keag.lager.panels.frame.bericht.BerichtZeileHtml;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class InventurBericht extends EinzelBericht {
	
	private HalleBean inventurHalleBean;
	private ZeileBean inventurZeileBean;
	private SaeuleBean inventurSaeuleBean;
	
	
	private HalleBeanDB halleBeanDB;
	private EtageBeanDB etageBeanDB;
	private ZeileBeanDB zeileBeanDB;
	private SaeuleBeanDB saeuleBeanDB;
	
//	private ZeileBeanDB zeileBeanDB;

	private BerichtBean berichtBean;
	private BerichtBeanDB berichtBeanDB;
	
	
//	private ArrayList<EtageBean> etageBeans;
//	private ArrayList<ZeileBean> zeileBeans;
//	private BaBean baBean2;
//	private ArrayList<BaPosBean> baPosBeans2;
	
	public InventurBericht(Map<String, String> druckParameter) {
		super(druckParameter);
	}
	
	/*
	 * 1. BaBean wird gelesen.
	 * 2. BerichtBean wird erstellt.
	 * 3. BerichtBean wird in DB abgespeichert und neu eingelesen.
	 * @see de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht#erzeugeBerichtDbSatz(java.lang.Integer, de.keag.lager.panels.frame.bericht.Berichtart, de.keag.lager.panels.frame.bericht.Berichttyp)
	 */
	@Override
	public void erzeugeBerichtDbSatz(Berichttyp berichttyp, Berichtart berichtart, Integer berichtTypId) throws SQLException, LagerException {
		super.erzeugeBerichtDbSatz(berichttyp, berichtart, berichtTypId);
		//Aus der HallenBean wird der DruckSatz erzeugt.
		
		//Halle lesen
		HalleSuchBean halleSuchBean = new HalleSuchBean();
		switch (berichttyp) {
		case INVENTUR_LISTE_HALLE:
			halleSuchBean.getHalleBean().setId(berichtTypId);
			ArrayList<Bean> halleBeans = getHalleBeanDB().selectAnhandSuchBean(halleSuchBean, 0, null);
			if (halleBeans.size()!=1){
				new LagerException("Halle nicht gefunden:"+berichtTypId);
			}
			if (!(halleBeans.get(0) instanceof HalleBean)){
				new LagerException("Halle erwartet, falsche Klasse:"+halleBeans.get(0).getClass().getName());
			}
			setHalleBean((HalleBean)halleBeans.get(0));
			break;
		case INVENTUR_LISTE_ZEILE:
			ZeileBean zeileBean = (ZeileBean)getZeileBeanDB().selectAnhandID(berichtTypId, 0, null);
			setInventurZeileBean(zeileBean);
			halleSuchBean.setHalleBean(zeileBean.getHalleBean());
			setHalleBean(zeileBean.getHalleBean());
			break;
		case INVENTUR_LISTE_SAEULE:
			SaeuleBean saeuleBean = (SaeuleBean)getSaeuleBeanDB().selectAnhandID(berichtTypId, 0, null);
			setInventurSaeuleBean(saeuleBean);
			halleSuchBean.setHalleBean(saeuleBean.getZeileBean().getHalleBean());
			setHalleBean(saeuleBean.getZeileBean().getHalleBean());
			break;
		default:
			Log.log().severe("Falscher Typ:"+getBerichttyp());
			break;
		}
		
		if (getInventurHalleBean()==null){
			Log.log().severe("HalleBean muss gefüllt sein.");
		}
		
//		//Etagen aus DB lesen
//		ArrayList<EtageBean> etageBeans = 
//			((EtageBeanDB)getEtageBeanDB()).sucheAnhandHalleBean(getHalleBean());
//		getHalleBean().setEtageBeans(etageBeans);
//			
//		//Zeilen aus DB lesen
//		ArrayList<ZeileBean> zeileBeans = 
//				((ZeileBeanDB)getZeileBeanDB()).sucheAnhandHalleBean(getHalleBean());
//		getHalleBean().setZeileBeans(zeileBeans);
			
		//neuer Bericht-Kopf wird erzeugt
		setBerichtBean(new BerichtBean());
		getBerichtBean().setId(this.getId()); //neue ID wird ermittelt
		getBerichtBean().setBerichtArt(this.getBerichtart());
		getBerichtBean().setBerichtTyp(this.getBerichttyp());
		getBerichtBean().setBerichtTypId(berichtTypId);
		getBerichtBean().setAktuellerBenutzer(Run.getOneInstance().getBenutzerBean());
		getBerichtBean().setBeanDBStatus(BeanDBStatus.INSERT);
		getBerichtBeanDB().saveBean(getBerichtBean()); //speichern
		//neu einlesen un ersetzen. Jetzt haben wir den Bericht-Kopf und Bericht daten in Form von 
		setBerichtBean((BerichtBean) getBerichtBeanDB().selectAnhandID(getBerichtBean().getId(), 0, null));
	}


	private void setInventurSaeuleBean(SaeuleBean saeuleBean) {
		this.inventurSaeuleBean = saeuleBean;
		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_SAEULE){
			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTE_SAEULE_Druck pro Säule");
		}
	}

	private void setInventurZeileBean(ZeileBean inventurZeileBean) {
		this.inventurZeileBean = inventurZeileBean;
		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_ZEILE){
			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTEN_Druck pro Zeile");
		}
	}
	private ZeileBean  getInventurZeileBean() {
		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_ZEILE){
			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTEN_Druck pro Zeile");
		}
		return this.inventurZeileBean;
	}
	private SaeuleBean  getInventurSaeuleBean() {
		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_SAEULE){
			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTEN_Druck pro Zeile");
		}
		return this.inventurSaeuleBean;
	}

	/*
	 * Aus BaBean werden Druck-Daten-tabellen produziert.
	 * @see de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht#uebernehmeDruckDaten()
	 */
	@Override
	public void uebernehmeDruckDaten() throws Exception {
		super.uebernehmeDruckDaten();
		LagerConnection.getOneInstance().startTransaction();

		try{

			
			ArrayList<ZeileBean> zeileBeans;
//			ArrayList<ZeileBean> saeuleBeans;
			switch (getBerichttyp()) {
			case INVENTUR_LISTE_HALLE:
				//HalleBean in bericht_inventur_halle abspeichern
				uebernehmeneDruckDatenInventurHalle(getInventurHalleBean());
				//Zeilen aus DB lesen
				zeileBeans = ((ZeileBeanDB)getZeileBeanDB()).sucheAnhandHalleBean(getInventurHalleBean(), 0);
				getInventurHalleBean().setZeileBeans(zeileBeans);
				break;
			case INVENTUR_LISTE_ZEILE:
				//HalleBean in bericht_inventur_halle abspeichern
				uebernehmeneDruckDatenInventurHalle(getInventurZeileBean().getHalleBean());
				zeileBeans = new ArrayList<ZeileBean>();
				zeileBeans.add(getInventurZeileBean());
				getInventurHalleBean().setZeileBeans(zeileBeans);
				break;
			case INVENTUR_LISTE_SAEULE:
				//HalleBean in bericht_inventur_halle abspeichern
				uebernehmeneDruckDatenInventurHalle(getInventurSaeuleBean().getZeileBean().getHalleBean());
				zeileBeans = new ArrayList<ZeileBean>();
				zeileBeans.add(getInventurSaeuleBean().getZeileBean());
				getInventurHalleBean().setZeileBeans(zeileBeans);
				break;
			default:
				zeileBeans = new ArrayList<ZeileBean>();
				Log.log().severe("Falscher Typ:"+getBerichttyp());
				break;
			}
			
			//Etagen aus DB lesen
			ArrayList<EtageBean> etageBeans = 
				((EtageBeanDB)getEtageBeanDB()).sucheAnhandHalleBean(getInventurHalleBean(), 0);
			getInventurHalleBean().setEtageBeans(etageBeans);
				
			//Etagen aus der DB einlesen
			for (int i = 0; i< etageBeans.size();i++){
				uebernehmeneDruckDatenInventurEtage(etageBeans.get(i));
			}
			
			//Zeilen aus der DB einlesen
			for (int i = 0; i< zeileBeans.size();i++){
				uebernehmeneDruckDatenInventurZeile(zeileBeans.get(i));
			}
			
			//TODO Ende
			
			LagerConnection.getOneInstance().commit();
		}catch (Exception e) {
			LagerConnection.getOneInstance().rollback();
			throw e;
		}
		
		
	}

	private void uebernehmeneDruckDatenInventurHalle(HalleBean halleBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_inventur_halle " +
			" (fk_bericht_id, id, name, nummer)" +
			" values (?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", halleBean.getId());
		getSqlParams().put("name", halleBean.getName());
		getSqlParams().put("nummer", halleBean.getNummer());
		
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	}
	private void uebernehmeneDruckDatenInventurEtage(EtageBean etageBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_inventur_etage " +
			" (fk_bericht_id, id, name, fk_halle)" +
			" values (?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", etageBean.getId());
		getSqlParams().put("name", etageBean.getName());
		getSqlParams().put("fk_halle", this.getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
	    //System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	}

	private void uebernehmeneDruckDatenInventurZeile(ZeileBean zeileBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_inventur_zeile " +
			" (fk_bericht_id, id, nummer, fk_halle, fk_etage, fk_abteilung)" +
			" values (?,?,?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", zeileBean.getId());
		getSqlParams().put("nummer", zeileBean.getNummer());
		getSqlParams().put("fk_halle", zeileBean.getHalleBean().getId());
		getSqlParams().put("fk_etage", zeileBean.getEtageBean().getId());
		getSqlParams().put("fk_abteilung", zeileBean.getAbteilungBean().getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	    
		//Säulen in bericht - DB übernehmen
		for (int i = 0; i< zeileBean.getSaeuleBeans().size();i++){
			switch (getBerichttyp()) {
			case INVENTUR_LISTE_SAEULE:
				SaeuleBean saeuleBean = (SaeuleBean)zeileBean.getSaeuleBeans().get(i);
				if (saeuleBean.equals(getInventurSaeuleBean())){
					uebernehmeneDruckDatenInventurSauele(zeileBean.getSaeuleBeans().get(i));
				}
				break;
			default:
				uebernehmeneDruckDatenInventurSauele(zeileBean.getSaeuleBeans().get(i));
				break;
			}
		}
	}
	
	private void uebernehmeneDruckDatenInventurSauele(SaeuleBean saeuleBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_inventur_saeule " +
			" (fk_bericht_id, id, nummer, fk_zeile)" +
			" values (?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", saeuleBean.getId());
		getSqlParams().put("nummer", saeuleBean.getNummer());
		getSqlParams().put("fk_zeile", saeuleBean.getZeileBean().getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	    
		//Säulen in bericht - DB übernehmen
		for (int i = 0; i< saeuleBean.getEbeneBeans().size();i++){
			uebernehmeneDruckDatenInventurEbene(saeuleBean.getEbeneBeans().get(i));
		}
	    
	}	

	private void uebernehmeneDruckDatenInventurEbene(EbeneBean ebeneBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_inventur_ebene " +
			" (fk_bericht_id, id, nummer, fk_saeule)" +
			" values (?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", ebeneBean.getId());
		getSqlParams().put("nummer", ebeneBean.getNummer());
		getSqlParams().put("fk_saeule", ebeneBean.getSaeuleBean().getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	    
		//Säulen in bericht - DB übernehmen
		for (int i = 0; i< ebeneBean.getPositionBeans().size();i++){
			uebernehmeneDruckDatenInventurPosition(ebeneBean.getPositionBeans().get(i));
		}
	    
	}	
	
	private void uebernehmeneDruckDatenInventurPosition(PositionBean positionBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_inventur_position " +
			" (fk_bericht_id, id, nummer, fk_ebene)" +
			" values (?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", positionBean.getId());
		getSqlParams().put("nummer", positionBean.getNummer());
		getSqlParams().put("fk_ebene", positionBean.getEbeneBean().getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	    
		//Säulen in bericht - DB übernehmen
		for (int i = 0; i< positionBean.getBestandspackstueckBeans().size();i++){
			uebernehmeneDruckDatenInventurBestand(positionBean.getBestandspackstueckBeans().get(i));
		}
	    
	}	
	
	private void uebernehmeneDruckDatenInventurBestand(BestandspackstueckBean bestandBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_inventur_bestandspackstueck " +
			" (fk_bericht_id, id, "+
			"fk_artikel, fk_position, menge, " +
			"artikel_bezeichnung, artikel_typ, " +
			"artikel_keg_nr, artikel_mindestbestand, " +
			"artikel_fk_mengeneinheit, artikel_fk_hersteller, " +
			"hersteller_name, mengeneinheit_name)" +
			" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", bestandBean.getId());
		getSqlParams().put("fk_artikel", bestandBean.getArtikelBean().getId());
		getSqlParams().put("fk_position", bestandBean.getPositionBean().getId());
		getSqlParams().put("menge", bestandBean.getMenge());
		getSqlParams().put("artikel_bezeichnung", bestandBean.getArtikelBean().getBezeichnung());
		getSqlParams().put("artikel_typ", bestandBean.getArtikelBean().getTyp());
		getSqlParams().put("artikel_keg_nr", bestandBean.getArtikelBean().getKeg_nr());
		getSqlParams().put("artikel_mindestbestand", bestandBean.getArtikelBean().getMindestbestand());
		getSqlParams().put("artikel_fk_mengeneinheit", bestandBean.getArtikelBean().getMengenEinheitBean().getId());
		getSqlParams().put("artikel_fk_hersteller", bestandBean.getArtikelBean().getHersteller().getId());
		getSqlParams().put("hersteller_name", bestandBean.getArtikelBean().getHersteller().getName());
		getSqlParams().put("mengeneinheit_name", bestandBean.getArtikelBean().getMengenEinheitBean().getName());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	    
//		//Säulen in bericht - DB übernehmen
//		for (int i = 0; i< bestandBean.getBestandspackstueckBeans().size();i++){
//			uebernehmeneDruckDatenInventurBestand(bestandBean.getBestandspackstueckBeans().get(i));
//		}
//	    
	}		
	

	
	@Override
	public void verarbeiteBericht() throws SQLException, LagerException, Exception {
		super.verarbeiteBericht();
		
		// Run-time report parameters
		Map<String, String> parameters = new HashMap<String, String>();
		switch (getBerichttyp()) {
		case INVENTUR_LISTE_HALLE:
			parameters.put("title", "Bestandsliste, Halle");
			break;
		case INVENTUR_LISTE_ZEILE:
			parameters.put("title", "Bestandsliste, Zeile "+getInventurZeileBean().getNummer());
			break;
		case INVENTUR_LISTE_SAEULE:
			parameters.put("title", "Bestandsliste, Säule "+getInventurSaeuleBean().getNummer());
			break;
		default:
			parameters.put("title", "Bestandsliste");
			break;
		}
		
//		parameters.put("bericht_id", "76");
		parameters.put("bericht_id", this.getBerichtBean().getId().toString());
		parameters.put("user", this.getBerichtBean().getAktuellerBenutzer().getVorname() + " " +this.getBerichtBean().getAktuellerBenutzer().getName());
		parameters.put("druckdatum", this.getBerichtBean().getDruckDatumKopie().toString());
		parameters.put("bemerkung", "");
		
		
		//http://barcode4j.sourceforge.net/2.1/building.html
		 
		//$F{halle_nummer}+"-"+$F{z_nummer}+"-"+$F{s_nummer}+"-"+$F{pos_nummer}+"-"+$F{bp_artikel_keg_nr}
		if (getDruckParameter().containsKey(Konstanten.REPORT_INVENTUR_MIT_MENGEN)){
			String s = getDruckParameter().get(Konstanten.REPORT_INVENTUR_MIT_MENGEN);
			if (s.equals(Konstanten.REPORT_INVENTUR_MIT_MENGEN_JA)){
				//Mengen werden gedruckt
				parameters.put("mit_mengen", "J");
			}else{
				//Mengen werden NICHT gedruckt (List wird als Aufkleber an der Säule verwendet
				parameters.put("mit_mengen", "N");
			}
		}else{
			//keine Einstellung vorhanden, also werden die Mengen gedruckt.
			parameters.put("mit_mengen", "J");
		}
		
		
		//http://barbecue.sourceforge.net/
			
		ReportDriver.getOneInstance().runReportView(parameters, Konstanten.REPORT_INVENTUR_LISTE_01_JRXML, Konstanten.REPORT_INVENTUR_LISTE_01_JASPER);

		
		
////		SendJavaMail sendJavaMail = new SendJavaMail();
////		String toEmail = "wladimir.stoll@gmx.de";
//		String toEmail = "eugen_schmidt3@freenet.de";
////		toEmail = getBaBean().getEmail();
//		String messageEMail = "--- kein Inhalt ---";
//		String messageEMailHTML = "<html> <head> Kein Inhalt im head </head> <body> Kein Inhalt im head </body> </html> ";
//		
//		if (getBaBean()!=null){
//			
//			
//			if (toEmail.equalsIgnoreCase(Run.getOneInstance().getLagerProperties().getLagerEmailSuperUser()))//			if (!toEmail.equalsIgnoreCase("thurner@keag.de"))
//			{
//				String subject = "Bestellanforderung "+getBaBean().getId() + ", Abteilung:"+ getBaBean().getAbsenderBenutzerBean().getErsteAbteilungDesBenutzers().getAbteilungName();
//				messageEMail = erzeugeEmailInhaltBestellanforderung(getBaBean(),getBaPosBeans());
//				SendJavaMail.postMailToGmxLagerKEG(toEmail, subject, messageEMail, false);
//			}else{
//				String subject = "Bestellung "+getBaBean().getId() + ", Abteilung:"+ getBaBean().getAbsenderBenutzerBean().getErsteAbteilungDesBenutzers().getAbteilungName();
//				messageEMail = erzeugeEmailInhaltBestellung(getBaBean(),getBaPosBeans());
//				SendJavaMail.postMailToGmxLagerKEG(toEmail, subject, messageEMail, true);
//				SendJavaMail.postMailToGmxLagerKEG(
//						Run.getOneInstance().getLagerProperties().getLagerEmailSuperUser(), 
//						"KOPIE: " + subject, 
//						messageEMail, 
//						true);
//			}
//		}else{
//			throw new LagerException(new Fehler("Keine Daten zu versenden!"));
//		}

	}
	
	private String erzeugeEmailInhaltBestellung(BaBean baBean,
			ArrayList<BaPosBean> baPosBeans) {
		Integer tabellenBreite = 1000;
//		private String erzeugeEmailInhaltBestellanforderung(BaBean baBean2,
//	    ArrayList<BaPosBean> baPosBeans2) {
		String inhalt = "<html><head><title>Bestellung</title></head><body>";
		
		
		
		//**************  Kopf **************
		getBerichtZeileHtml().getBerichtSpalten().clear();
		getBerichtZeileHtml().setSpaltenHoehe(2);
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", "",300, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", "",300, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", "",300, " "));
		inhalt = inhalt  + 
		"<table style=\"text-align:left\" border=\"0\"  width=\"" + tabellenBreite.toString() +"\"> <colgroup width=\"500\" span=\""+
			new Integer(getBerichtZeileHtml().getBerichtSpalten().size()).toString()+
		"\"> </colgroup>";
		
		
		//---
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt("Kemptener Eisengießerei Adam Hönig AG <br>Postfach 1849 . 87408 Kempten/Allgäu"); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(	"im Allmey 4 1/2 <br>87435 Kempten ");
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		//---
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt("");

		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(
				"<br>Abtelung: " + baBean.getAbsenderBenutzerBean().getErsteAbteilungDesBenutzers().getAbteilungName() + 
				"<br>Bearbeiter:" + baBean.getAbsenderBenutzerBean().getName() +
				"<br>Telefon:" + baBean.getAbsenderBenutzerBean().getId() +
				"<br>E-mail:" + baBean.getAbsenderBenutzerBean().getEmail() +
				"<br>Fax: 0831 / 58 11 014" + 
				"<br>" 
				);
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		//Lieferant und Datum
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(baBean.getLhBean().getName().toString() + "" +
				"<br>"+ baBean.getLhBean().getAnsprechpartner().toString() +
				"<br>" + baBean.getLhBean().getAdresse_strasse().toString() +
				"<br>" + baBean.getLhBean().getAdresse_land().toString() +
				" " + baBean.getLhBean().getAdresse_plz().toString()+
				" " + baBean.getLhBean().getAdresse_stadt().toString()
				); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("Datum:" + Bean.getAktuellesDatum());
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		//Bestellung, Nummer
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt("Bestellung Nr. " + baBean.getId().toString()); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("");
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		
		inhalt = inhalt  + "</table>";
		
		inhalt = inhalt  + " " + "<br>";
		inhalt = inhalt  + " " + "<br>";
		inhalt = inhalt  + "Sehr geehrte" + baBean.getLhBean().getAnsprechpartner().toString() + ",<br>";
		inhalt = inhalt  + "gem. vereinbarten Konditionen bestellen wir hiermit folgende Artikel:<br>";
		inhalt = inhalt  + " " + "<br>";
		inhalt = inhalt  + " " + "<br>";

		inhalt = EinzelBericht.textToHtml(inhalt, ""); //umbrüche durch <br> ersetzen
		
		
		//************** Beging Positionen **************
		inhalt = inhalt  + 	"<table border=\"1\"  width=\""+  tabellenBreite + "\">" ;
//		"<table border=\"1\"  width=\""+  tabellenBreite + "\"> <colgroup width=\"200\" span=\""+
//		new Integer(getBerichtZeileHtml().getBerichtSpalten().size()).toString()+
//		"\"> </colgroup>";

		// ************** Positionen **************
		getBerichtZeileHtml().getBerichtSpalten().clear();
		getBerichtZeileHtml().setSpaltenHoehe(2);
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Pos","", "",30, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Menge","", "", 50, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("ME","", "", 60, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Artikelbezeichnung/ ","     Typ", "", 250, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Hersteller","", "", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Lieferant.","bestellnr.", "", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Katalog/","Seite", "", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte(" Liefer-","Termin /KW", "", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("   KE-","ArtikelNr.", "", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Kosten-","stelle", "", 90, " "));
//		//erste Linie über den Spalten
//		inhalt = inhalt  + getBerichtZeileHtml().getDurchgehendeLinie() + "\n";
//		
		//Spalten-Bezeichnungen , erste Zeile
		inhalt = inhalt  + getBerichtZeileHtml().getSpaltenNamenZeile(1) + "\n";
		//Spalten-Bezeichnungen, zweite Zeile
		inhalt = inhalt  + getBerichtZeileHtml().getSpaltenNamenZeile(2) + "\n";
		
//		for(BaPosBean baPosBean : baPosBeans){ //identisch mit den nächsten 2 Zeilen
		for(int i = 0; i<baPosBeans.size();i++){
			BaPosBean baPosBean = baPosBeans.get(i);

			//Alle Spalten mit Daten versorgen
			getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(new Integer(i+1).toString()); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(baPosBean.getBestellmenge().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(baPosBean.getMengenEinheitBean().getName()); //nächste Spalte
    		getBerichtZeileHtml().getBerichtSpalten().get(3).setInhalt(baPosBean.getArtikelBean().getBezeichnung(). toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(4).setInhalt(baPosBean.getArtikelBean().getHersteller().getName().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(5).setInhalt(baPosBean.getLieferantenbestellnummer().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(6).setInhalt(baPosBean.getKatalogBean().getName().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(7).setInhalt(baPosBean.getLiefertermin().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(8).setInhalt(baPosBean.getArtikelBean().getKeg_nr().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(9).setInhalt(baPosBean.getKostenstelle().toString()); //zweite Splate

			
			//Zeile drucken
			inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile("#CCEECC") + "\n";
	
			
			//Alle Spalten mit Daten versorgen
			getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(""); //erste Splate
    		getBerichtZeileHtml().getBerichtSpalten().get(3).setInhalt(
    				baPosBean.getArtikelBean().getTyp().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(4).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(5).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(6).setInhalt(
					baPosBean.getKatalogseite()); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(7).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(8).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(9).setInhalt(""); //erste Splate

			//Zeile drucken
			inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile("#CCEECC") + "\n";
			
//			//abschliessende  Linie unter den Spalten-Daten
//			inhalt = inhalt  + getBerichtZeileHtml().getDurchgehendeLinie() + "\n";
		}
		inhalt = inhalt  + "</table>";
		//************** Ende Positionen **************
		
		//************** FUSS **************
		
		
//		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Den Preis und Lieferzeit nehmen Sie bitte mit ihrer Auftragsbestätigung ") + "\n";
		inhalt = inhalt + "<br>Liefertermin sieh Tabelle. ";
		if (baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().size()>0){
			inhalt = inhalt + "Abteilung: " + baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().get(0).getAbteilung().getAbteilungName();
		}
		
		inhalt = inhalt + "<br>Lieferbedingungen frei Haus, einschl. Verpackung" ;
		inhalt = inhalt + "<br>Zahlungsbedingungen: 8Tage, 2% Skonto, 30 Tage netto" ;
		inhalt = inhalt + "<br>";
		inhalt = inhalt + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Mit freundlichen Grüßen ") + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Kemptener Eisengießerei ") + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Adam Hönig AG") + "<br>";
		inhalt = inhalt + "i.A. " +baBean.getAbsenderBenutzerBean().getName().toString() + "<br>";
		inhalt = inhalt + " " + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Bestellung gilt ohne Unterschrift als erteilt ") + "<br>";
		inhalt = inhalt + " " + "<br>";
		inhalt = inhalt + " " + "<br>";
		inhalt = inhalt + " " + "<br>";
		
		
		getBerichtZeileHtml().getBerichtSpalten().clear();
		getBerichtZeileHtml().setSpaltenHoehe(2);
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", "",100, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", "",100, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", "",100, " "));
		inhalt = inhalt  + 
		"<table style=\"text-align:left\" border=\"0\"  width=\"" + tabellenBreite.toString() +"\"> ";
		//---
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt("Aktiengesellschaft,Sitz Kempten" +
				"<br>Register Kempten HRB 7539" +
				"<br>AR-Vorsitzender: Thomas Wäschle" +
				"<br>Vorstand Roland Hübner"); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("Bankverbindung:" +
				"<br>Sparkasse Allgäu(BLZ 733 500 00)310 014 709) " +
				"<br>LB Baden-Württemberg(BLZ 600 501 01)454 185 7" +
				"<br>" +
				"");
		//Zeile drucken
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		inhalt = inhalt + "</table>";
		inhalt = inhalt + "</body></html>";
		return inhalt;
	}

	/**
	 * Liste erstellen aus (Anforderung und Anforderungspositionen)
	 * @param baBean
	 * @param baPosBeans
	 * @return
	 */
//	private String erzeugeEmailInhaltBestellung(BaBean baBean,
//			ArrayList<BaPosBean> baPosBeans) {
	private String erzeugeEmailInhaltBestellanforderung(BaBean baBean,
		    ArrayList<BaPosBean> baPosBeans) {
		String inhalt = "";

		
		// ************** Positionen **************
		getBerichtZeile().setSymbolFuerHorizontaleLinie("-");
		getBerichtZeile().setSymbolFuerVertikaleLinie("|");
		getBerichtZeile().getBerichtSpalten().clear();
		getBerichtZeile().setSpaltenHoehe(2);
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Pos","", "",3, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Menge","", "", 5, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("  ME","", "", 6, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte(" Artikelbezeichnung/ ","   Typ", "", 35, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Hersteller","", "", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Lieferant.","bestellnr.", "", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Katalog/","Seite", "", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Liefer-","Termin /KW", "", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("   KE-","ArtikelNr.", "", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Kosten-","stelle", "", 10, " "));
		
		//**************  Kopf ************** 
		if (baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().size()>0){
			inhalt = inhalt + "Abteilung:" + baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().get(0).getAbteilung().getAbteilungName() +"\n";
		}
		inhalt = inhalt + "Bearbeiter:" + baBean.getAbsenderBenutzerBean().getName().toString() + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
//		inhalt = inhalt + "---------------------------------------------------------------------"+ "\n";
		inhalt = inhalt + " " + "\n";
		inhalt = inhalt  + "Gesendet am :" + Bean.getAktuellesDatum().toString() + "\n";
		inhalt = inhalt  + "         an :" + baBean.getAnnehmerBenutzerBean().getEmail().toString()+"\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + "                  Bestellanforderung Nr." + baBean.getId() + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + " " + "\n";
		
		//erste Linie über den Spalten
		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
		
		//Spalten-Bezeichnungen , erste Zeile
		inhalt = inhalt  + getBerichtZeile().getSpaltenNamenZeile(1) + "\n";
		
		//Spalten-Bezeichnungen, zweite Zeile
		inhalt = inhalt  + getBerichtZeile().getSpaltenNamenZeile(2) + "\n";
		
		//abschliessende  Linie unter den Spalten-Bezeichungen
		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
		
		
//		for(BaPosBean baPosBean : baPosBeans){ //identisch mit den nächsten 2 Zeilen
		for(int i = 0; i<baPosBeans.size();i++){
			BaPosBean baPosBean = baPosBeans.get(i);

			//Alle Spalten mit Daten versorgen
			getBerichtZeile().getBerichtSpalten().get(0).setInhalt(new Integer(i+1).toString()); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(1).setInhalt(baPosBean.getBestellmenge().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(2).setInhalt(baPosBean.getMengenEinheitBean().getName()); //nächste Spalte
    		getBerichtZeile().getBerichtSpalten().get(3).setInhalt(baPosBean.getArtikelBean().getBezeichnung(). toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(4).setInhalt(baPosBean.getArtikelBean().getHersteller().getName().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(5).setInhalt(baPosBean.getLieferantenbestellnummer().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(6).setInhalt(baPosBean.getKatalogBean().getName().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(7).setInhalt(baPosBean.getLiefertermin().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(8).setInhalt(baPosBean.getArtikelBean().getKeg_nr().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(9).setInhalt(baPosBean.getKostenstelle(). toString()); //zweite Splate

			
			//Zeile drucken
			inhalt = inhalt  + getBerichtZeile().getDatenZeile() + "\n";
	
			
			//Alle Spalten mit Daten versorgen
			getBerichtZeile().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(1).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(2).setInhalt(""); //erste Splate
    		getBerichtZeile().getBerichtSpalten().get(3).setInhalt(
    				baPosBean.getArtikelBean().getTyp().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(4).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(5).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(6).setInhalt(
					baPosBean.getKatalogseite()); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(7).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(8).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(9).setInhalt(""); //erste Splate

			//Zeile drucken
			inhalt = inhalt  + getBerichtZeile().getDatenZeile() + "\n";
			
			//abschliessende  Linie unter den Spalten-Daten
			inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
		}
		

		
		
		
		//************** FUSS **************
		inhalt = inhalt + " " + "\n";
		inhalt = inhalt + " " + "\n";
		inhalt = inhalt + "Gewuenschter Liefertermin :  \n" ;
		inhalt = inhalt + "!!! BITTE SOFORT LIEFERN !!! \n" ;
		String linie = "1234567890123456789012345678901234567890\n" ;
		inhalt = inhalt + linie;
		inhalt = inhalt + linie.substring(0,11) + " , Lieferant, " +linie.substring(25); 
		return inhalt;
	}
	
	

	private HalleBeanDB getHalleBeanDB() {
		if (halleBeanDB ==null){
			halleBeanDB = new HalleBeanDB();
		}
		return halleBeanDB;
	}

	private BerichtBeanDB getBerichtBeanDB() {
		if (berichtBeanDB ==null){
			berichtBeanDB = new BerichtBeanDB();
		}
		return berichtBeanDB;
	}

	private BerichtBean getBerichtBean() {
		return berichtBean;
	}

	private void setBerichtBean(BerichtBean berichtBean) {
		this.berichtBean = berichtBean;
	}

	private HalleBean getInventurHalleBean() {
		return inventurHalleBean;
	}

	private void setHalleBean(HalleBean halleBean) {
		this.inventurHalleBean = halleBean;
	}

//	private ArrayList<EtageBean> getEtageBeans() {
//		if (etageBeans==null){
//			etageBeans = new ArrayList<EtageBean>();
//		}
//		return etageBeans;
//	}
//
//	private void setEtageBeans(ArrayList<EtageBean> etageBeans) {
//		this.etageBeans = etageBeans;
//	}
//
//	private ZeileBeanDB getZeileBeanDB() {
//		if (zeileBeanDB==null){
//			zeileBeanDB = new ZeileBeanDB();
//		}
//		return zeileBeanDB;
//	}
//
//	private ArrayList<ZeileBean> getZeileBeans() {
//		return zeileBeans;
//	}


//	private BaBean getBaBean2() {
//		return baBean2;
//	}
//
//	private void setBaBean2(BaBean baBean2) {
//		this.baBean2 = baBean2;
//	}

//	private ArrayList<BaPosBean> getBaPosBeans2() {
//		return baPosBeans2;
//	}
//
//	private void setBaPosBeans2(ArrayList<BaPosBean> baPosBeans2) {
//		this.baPosBeans2 = baPosBeans2;
//	}
	
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
	
	private SaeuleBeanDB getSaeuleBeanDB() {
		if (saeuleBeanDB == null) {
			saeuleBeanDB = new SaeuleBeanDB();
		}
		return saeuleBeanDB;
	}
	

}
