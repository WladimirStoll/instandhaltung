package de.keag.lager.panels.frame.bericht.einzelberichte.wartung;
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


import de.keag.lager.core.Bean;
import de.keag.lager.core.datum.DatumUhrzeit;
import de.keag.lager.core.datum.DatumUhrzeit.DatumFormat;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.jasperReports.ReportDriver;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.BerichtBeanDB;
import de.keag.lager.db.WartungBeanDB;
import de.keag.lager.db.WartungsArtikelBeanDB;
import de.keag.lager.db.WartungsMitarbeiterBeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtSpalte;
import de.keag.lager.panels.frame.bericht.BerichtZeileHtml;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.model.WartungModelKnotenBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;

public class WartungsBericht extends EinzelBericht {
	
	public static String WARTUNG_ID_ = "WARTUNG_ID_";
	
//	private HalleBean inventurHalleBean;
//	private ZeileBean inventurZeileBean;
//	private SaeuleBean inventurSaeuleBean;
//	
//	
//	private HalleBeanDB halleBeanDB;
//	private EtageBeanDB etageBeanDB;
//	private ZeileBeanDB zeileBeanDB;
//	private SaeuleBeanDB saeuleBeanDB;
	
//	private ZeileBeanDB zeileBeanDB;

	private BerichtBean berichtBean;
	private BerichtBeanDB berichtBeanDB;
	private WartungBeanDB wartungBeanDB;
	private WartungsMitarbeiterBeanDB wartungsMitarbeiterBeanDB;
	private WartungsArtikelBeanDB wartungsArtikelBeanDB;
	
	
//	private ArrayList<EtageBean> etageBeans;
//	private ArrayList<ZeileBean> zeileBeans;
//	private BaBean baBean2;
//	private ArrayList<BaPosBean> baPosBeans2;
	
	public WartungsBericht(Map<String, String> druckParameter) {
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


	/** Die Datenbank bericht_wartung_ids wird mit Daten gefüllt
	 * 
	 * @throws SQLException
	 * @throws LagerException
	 */
	private void fillWartungsID() throws SQLException, LagerException {
		
		for(int i=0;i<this.getDruckParameter().size();i++){
			String key = WARTUNG_ID_+new Integer(i).toString();
			if (getDruckParameter().containsKey(key)){
				Integer iKey  = new Integer(getDruckParameter().get(key));
				
				getBerichtBean().getId();
				getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
				String insertSQL = 
					"insert into bericht_wartung " +
					" (beschreibung, fk_bericht_id, id, baugruppe, " +
					" baugruppe_uebergeordnet, status, mitarbeiter, " +
					" artikel, termin_soll, termin_ist, " +
					" art_typ)" +
					" values (?, ?,?,?,?,? , ?,?,?,?,?)";
				//Params werden gesetzt.
				WartungBean wartungBean = (WartungBean) getWartungBeanDB().selectAnhandID(iKey, 0, null);
				getSqlParams().put("beschreibung", wartungBean.getBeschreibung());
				getSqlParams().put("fk_bericht_id", this.getId());
				getSqlParams().put("id", iKey); //Wartungsid
				
				ArrayList<WartungsMitarbeiterBean> wartungsMitarbeiterBeans 
					= getWartungsMitarbeiterBeanDB().sucheAnhandWartungBean(wartungBean);
				ArrayList<WartungsArtikelBean> wartungsArtikelBeans 
					= getWartungsArtikelBeanDB().sucheAnhandWartungBean(wartungBean);

				getSqlParams().put("baugruppe", wartungBean.getFk_baugruppe().toString()); 
				getSqlParams().put("baugruppe_uebergeordnet", wartungBean.getFk_baugruppe().getVaterBaugruppeNamenListe(),500); 
				getSqlParams().put("status", StatusEnum.JavaToView(wartungBean.getStatus()));
				
				StringBuffer mitarbeiter = new StringBuffer();
				for(WartungsMitarbeiterBean wartungsMitarbeiterBean: wartungsMitarbeiterBeans){
					if (mitarbeiter.length()>0){
						mitarbeiter.append(",\n");
					}
					mitarbeiter.append(
							wartungsMitarbeiterBean.toString()
//							+
//							"<" + wartungsMitarbeiterBean.getStunden().toString()+"Std>"
					);
				}
				getSqlParams().put("mitarbeiter", mitarbeiter.toString(),500);
				
				StringBuffer artikel = new StringBuffer();
				for(WartungsArtikelBean wartungsArtikelBean: wartungsArtikelBeans){
					if (artikel.length()>0){
						artikel.append(",\n");
					}
					artikel.append("<Bez>"+wartungsArtikelBean.getFk_artikel().getBezeichnung() + 
							"<Typ>"+ wartungsArtikelBean.getFk_artikel().getTyp() +
							"<Menge>"+ wartungsArtikelBean.getMenge().toString()+
							"" + wartungsArtikelBean.getFk_artikel().getMengenEinheitBean().getName()
					);
				}
				getSqlParams().put("artikel", artikel.toString(),500); 
//				getSqlParams().put("termin_soll",DatumUhrzeit.JavaToView(wartungBean.getTermin_soll(),DatumUhrzeit.DatumFormat.DATUM) );
//				getSqlParams().put("termin_ist",DatumUhrzeit.JavaToView(wartungBean.getTermin_ist(),DatumUhrzeit.DatumFormat.DATUM) );
				getSqlParams().put("termin_soll",wartungBean.getTermin_soll() );
				getSqlParams().put("termin_ist",wartungBean.getTermin_ist() );
				getSqlParams().put("art_typ",wartungBean.getFk_wartungsart().toString() + "/"+ wartungBean.getFk_wartungstyp().toString());
				
				//SQL absetzen
				PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
				//Parameter in den Statement übernehmen
				getSqlParams().setParams(stmt);
				//Statement protokolieren
			    Log.log().finest(stmt.toString());
//			    System.out.println(stmt.toString());
			    //Statement ausführen
			    stmt.execute();
				stmt.close();
				stmt=null;
				
				
				
//				getBerichtBean().getId();
//				getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//				String insertSQL = 
//					"insert into bericht_wartung_ids " +
//					" (fk_bericht_id, fk_wartung_id)" +
//					" values (?,?)";
//				//Params werden gesetzt.
//				getSqlParams().put("fk_bericht_id", this.getId());
//				getSqlParams().put("fk_wartung_id", iKey);
//				
//				//SQL absetzen
//				PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//				//Parameter in den Statement übernehmen
//				getSqlParams().setParams(stmt);
//				//Statement protokolieren
//			    Log.log().finest(stmt.toString());
//			    //Statement ausführen
//			    stmt.execute();
//				stmt.close();
//				stmt=null;
			}
		}
		
		
	}

//	private void setInventurSaeuleBean(SaeuleBean saeuleBean) {
//		this.inventurSaeuleBean = saeuleBean;
//		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_SAEULE){
//			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTE_SAEULE_Druck pro Säule");
//		}
//	}
//
//	private void setInventurZeileBean(ZeileBean inventurZeileBean) {
//		this.inventurZeileBean = inventurZeileBean;
//		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_ZEILE){
//			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTEN_Druck pro Zeile");
//		}
//	}
//	private ZeileBean  getInventurZeileBean() {
//		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_ZEILE){
//			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTEN_Druck pro Zeile");
//		}
//		return this.inventurZeileBean;
//	}
//	private SaeuleBean  getInventurSaeuleBean() {
//		if (getBerichttyp()!=Berichttyp.INVENTUR_LISTE_SAEULE){
//			Log.log().severe("Fehler. Nur möglich, falls INVENTUR_LISTEN_Druck pro Zeile");
//		}
//		return this.inventurSaeuleBean;
//	}

	/*
	 * Aus BaBean werden Druck-Daten-tabellen produziert.
	 * @see de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht#uebernehmeDruckDaten()
	 */
	@Override
	public void uebernehmeDruckDaten() throws Exception {
		super.uebernehmeDruckDaten();
		
		//Alle Wartungs-ID(wartung.id) werden nach bericht_wartung_ids übernommen
		try {
			fillWartungsID();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

//	private void uebernehmeneDruckDatenInventurHalle(HalleBean halleBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_halle " +
//			" (fk_bericht_id, id, name, nummer)" +
//			" values (?,?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", halleBean.getId());
//		getSqlParams().put("name", halleBean.getName());
//		getSqlParams().put("nummer", halleBean.getNummer());
//		
//		
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//		//Parameter in den Statement übernehmen
//		getSqlParams().setParams(stmt);
//		//Statement protokolieren
//	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
//	    //Statement ausführen
//	    stmt.execute();
//	}
//	private void uebernehmeneDruckDatenInventurEtage(EtageBean etageBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_etage " +
//			" (fk_bericht_id, id, name, fk_halle)" +
//			" values (?,?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", etageBean.getId());
//		getSqlParams().put("name", etageBean.getName());
//		getSqlParams().put("fk_halle", this.getId());
//		
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//		//Parameter in den Statement übernehmen
//		getSqlParams().setParams(stmt);
//		//Statement protokolieren
//	    Log.log().finest(stmt.toString());
//	    //System.out.println(stmt.toString());
//	    //Statement ausführen
//	    stmt.execute();
//	}
//
//	private void uebernehmeneDruckDatenInventurZeile(ZeileBean zeileBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_zeile " +
//			" (fk_bericht_id, id, nummer, fk_halle, fk_etage, fk_abteilung)" +
//			" values (?,?,?,?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", zeileBean.getId());
//		getSqlParams().put("nummer", zeileBean.getNummer());
//		getSqlParams().put("fk_halle", zeileBean.getHalleBean().getId());
//		getSqlParams().put("fk_etage", zeileBean.getEtageBean().getId());
//		getSqlParams().put("fk_abteilung", zeileBean.getAbteilungBean().getId());
//		
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//		//Parameter in den Statement übernehmen
//		getSqlParams().setParams(stmt);
//		//Statement protokolieren
//	    Log.log().finest(stmt.toString());
////	    System.out.println(stmt.toString());
//	    //Statement ausführen
//	    stmt.execute();
//	    
//		//Säulen in bericht - DB übernehmen
//		for (int i = 0; i< zeileBean.getSaeuleBeans().size();i++){
//			switch (getBerichttyp()) {
//			case INVENTUR_LISTE_SAEULE:
//				SaeuleBean saeuleBean = (SaeuleBean)zeileBean.getSaeuleBeans().get(i);
//				if (saeuleBean.equals(getInventurSaeuleBean())){
//					uebernehmeneDruckDatenInventurSauele(zeileBean.getSaeuleBeans().get(i));
//				}
//				break;
//			default:
//				uebernehmeneDruckDatenInventurSauele(zeileBean.getSaeuleBeans().get(i));
//				break;
//			}
//		}
//	}
//	
//	private void uebernehmeneDruckDatenInventurSauele(SaeuleBean saeuleBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_saeule " +
//			" (fk_bericht_id, id, nummer, fk_zeile)" +
//			" values (?,?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", saeuleBean.getId());
//		getSqlParams().put("nummer", saeuleBean.getNummer());
//		getSqlParams().put("fk_zeile", saeuleBean.getZeileBean().getId());
//		
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//		//Parameter in den Statement übernehmen
//		getSqlParams().setParams(stmt);
//		//Statement protokolieren
//	    Log.log().finest(stmt.toString());
////	    System.out.println(stmt.toString());
//	    //Statement ausführen
//	    stmt.execute();
//	    
//		//Säulen in bericht - DB übernehmen
//		for (int i = 0; i< saeuleBean.getEbeneBeans().size();i++){
//			uebernehmeneDruckDatenInventurEbene(saeuleBean.getEbeneBeans().get(i));
//		}
//	    
//	}	
//
//	private void uebernehmeneDruckDatenInventurEbene(EbeneBean ebeneBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_ebene " +
//			" (fk_bericht_id, id, nummer, fk_saeule)" +
//			" values (?,?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", ebeneBean.getId());
//		getSqlParams().put("nummer", ebeneBean.getNummer());
//		getSqlParams().put("fk_saeule", ebeneBean.getSaeuleBean().getId());
//		
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//		//Parameter in den Statement übernehmen
//		getSqlParams().setParams(stmt);
//		//Statement protokolieren
//	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
//	    //Statement ausführen
//	    stmt.execute();
//	    
//		//Säulen in bericht - DB übernehmen
//		for (int i = 0; i< ebeneBean.getPositionBeans().size();i++){
//			uebernehmeneDruckDatenInventurPosition(ebeneBean.getPositionBeans().get(i));
//		}
//	    
//	}	
//	
//	private void uebernehmeneDruckDatenInventurPosition(PositionBean positionBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_position " +
//			" (fk_bericht_id, id, nummer, fk_ebene)" +
//			" values (?,?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", positionBean.getId());
//		getSqlParams().put("nummer", positionBean.getNummer());
//		getSqlParams().put("fk_ebene", positionBean.getEbeneBean().getId());
//		
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//		//Parameter in den Statement übernehmen
//		getSqlParams().setParams(stmt);
//		//Statement protokolieren
//	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
//	    //Statement ausführen
//	    stmt.execute();
//	    
//		//Säulen in bericht - DB übernehmen
//		for (int i = 0; i< positionBean.getBestandspackstueckBeans().size();i++){
//			uebernehmeneDruckDatenInventurBestand(positionBean.getBestandspackstueckBeans().get(i));
//		}
//	    
//	}	
//	
//	private void uebernehmeneDruckDatenInventurBestand(BestandspackstueckBean bestandBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_bestandspackstueck " +
//			" (fk_bericht_id, id, "+
//			"fk_artikel, fk_position, menge, " +
//			"artikel_bezeichnung, artikel_typ, " +
//			"artikel_keg_nr, artikel_mindestbestand, " +
//			"artikel_fk_mengeneinheit, artikel_fk_hersteller, " +
//			"hersteller_name, mengeneinheit_name)" +
//			" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", bestandBean.getId());
//		getSqlParams().put("fk_artikel", bestandBean.getArtikelBean().getId());
//		getSqlParams().put("fk_position", bestandBean.getPositionBean().getId());
//		getSqlParams().put("menge", bestandBean.getMenge());
//		getSqlParams().put("artikel_bezeichnung", bestandBean.getArtikelBean().getBezeichnung());
//		getSqlParams().put("artikel_typ", bestandBean.getArtikelBean().getTyp());
//		getSqlParams().put("artikel_keg_nr", bestandBean.getArtikelBean().getKeg_nr());
//		getSqlParams().put("artikel_mindestbestand", bestandBean.getArtikelBean().getMindestbestand());
//		getSqlParams().put("artikel_fk_mengeneinheit", bestandBean.getArtikelBean().getMengenEinheitBean().getId());
//		getSqlParams().put("artikel_fk_hersteller", bestandBean.getArtikelBean().getHersteller().getId());
//		getSqlParams().put("hersteller_name", bestandBean.getArtikelBean().getHersteller().getName());
//		getSqlParams().put("mengeneinheit_name", bestandBean.getArtikelBean().getMengenEinheitBean().getName());
//		
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
//		//Parameter in den Statement übernehmen
//		getSqlParams().setParams(stmt);
//		//Statement protokolieren
//	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
//	    //Statement ausführen
//	    stmt.execute();
//	    
////		//Säulen in bericht - DB übernehmen
////		for (int i = 0; i< bestandBean.getBestandspackstueckBeans().size();i++){
////			uebernehmeneDruckDatenInventurBestand(bestandBean.getBestandspackstueckBeans().get(i));
////		}
////	    
//	}		
//	

	
	@Override
	public void verarbeiteBericht() throws SQLException, LagerException, Exception {
		super.verarbeiteBericht();
		
		// Run-time report parameters
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("title", "Wartungen");
		parameters.put("bericht_id", this.getBerichtBean().getId().toString());
		parameters.put("user", this.getBerichtBean().getAktuellerBenutzer().getVorname() + " " +this.getBerichtBean().getAktuellerBenutzer().getName());
		parameters.put("druckdatum", this.getBerichtBean().getDruckDatumKopie().toString());
		parameters.put("bemerkung", "");
		parameters.put("termin_von", getDruckParameter().get("termin_von"));
		parameters.put("termin_bis", getDruckParameter().get("termin_bis"));
		ReportDriver.getOneInstance().runReportView(parameters, Konstanten.REPORT_WARTUNG_LISTE_01_JRXML, Konstanten.REPORT_WARTUNG_LISTE_01_JASPER);

	}
	

	private BerichtBeanDB getBerichtBeanDB() {
		if (berichtBeanDB ==null){
			berichtBeanDB = new BerichtBeanDB();
		}
		return berichtBeanDB;
	}
	
	private WartungBeanDB getWartungBeanDB() {
		if (wartungBeanDB ==null){
			wartungBeanDB = new WartungBeanDB();
		}
		return wartungBeanDB;
	}
	

	private BerichtBean getBerichtBean() {
		return berichtBean;
	}

	private void setBerichtBean(BerichtBean berichtBean) {
		this.berichtBean = berichtBean;
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

}
