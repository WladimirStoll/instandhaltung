package de.keag.lager.panels.frame.bericht.einzelberichte.mindestBestand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.jasperReports.ReportDriver;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.BerichtBeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht;

public class MindestBestandsMengeBericht extends EinzelBericht {
	
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
	
	
//	private ArrayList<EtageBean> etageBeans;
//	private ArrayList<ZeileBean> zeileBeans;
//	private BaBean baBean2;
//	private ArrayList<BaPosBean> baPosBeans2;
	
	public MindestBestandsMengeBericht(Map<String, String> druckParameter) {
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
		//id = 0, weil es keinen führenden Satz gibt.
		//Stattdessen haben wir den Benutzer und seine Abteilung(en)
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
		LagerConnection.getOneInstance().startTransaction();
		try{
			//Zeilen aus der DB einlesen
			for (int i = 0; i< Run.getOneInstance().getBenutzerBean().getBenutzerAbteilungBeans().size();i++){
				AbteilungBean abteilungBean = Run.getOneInstance().getBenutzerBean().getBenutzerAbteilungBeans().get(i).getAbteilung();
				//todo: Artikel finden, dessen SummenAnzahl pro Abteilung kleiner als Mindestbestandmenge sind.
				ermittleBetroffeneArtikel(abteilungBean);
				uebernehmeneDruckDatenProAbteilung(abteilungBean);
			}
			
			//Mindestbestellmenge anpassen
			passeMindestMengeAn();
			
			//Alle neuen Zeilen müssen durchlaufen werden.
			//Mindestbestand darf nur einmal pro Artikel angezeigt werden.
			//Auch wenn es mehrere Bestandspackstücke zu einem Artikel gibt, darf es
			//die Mindestbestandsmenge nur einmal auf dem Bericht gedruckt werden.
			
			
			
			LagerConnection.getOneInstance().commit();
		}catch (Exception e) {
			LagerConnection.getOneInstance().rollback();
			throw e;
		}
		
		
	}

//	private void uebernehmeneDruckDatenInventurHalle(HalleBean halleBean) throws SQLException, LagerException {
//		//Aus der BaBean werden die Daten nach Datenbank übernommen
//		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
//		String insertSQL = 
//			"insert into bericht_inventur_halle " +
//			" (fk_bericht_id, id, name)" +
//			" values (?,?,?)";
//		//Params werden gesetzt.
//		getSqlParams().put("fk_bericht_id", this.getId());
//		getSqlParams().put("id", halleBean.getId());
//		getSqlParams().put("name", halleBean.getName());
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

	/**
	 * Artikel je Abteilung in die Tabelle "bericht_mindestbestand_artikel"
	 */
	private void ermittleBetroffeneArtikel (AbteilungBean abteilungBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		final String insertSQL = 
			"insert into bericht_mindestbestand_artikel (" +
			"  fk_bericht_id ," +
			"  fk_artikel" +
			" )" +
			" SELECT distinct " +
			"  ?, " +
			"  art.id " +
			" FROM abteilung a, artikel art  left outer join  bestandspackstueck b " +
			" on b.fk_artikel = art.id " +
			" where a.id = b.fk_abteilung" +
			" and b.fk_artikel = art.id" +
			" and art.mindestbestand > 0 " +
			" and ( art.mindestbestand >= b.menge " +
			" or b.menge is null )" + 
			" and a.id = ?";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("fk_abteilung", abteilungBean.getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	    
	    stmt.close();
	    stmt = null;
		
	}
	
	private void uebernehmeneDruckDatenProAbteilung(AbteilungBean abteilungBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		final String insertSQL = 
			"insert into bericht_mindestbestand (" +
			"  `fk_bericht_id` ," +
			"  `id` ," +
			"  `fk_artikel`," +
			"  `artikel_bezeichnung` ," +
			"  `artikel_typ` ," +
			"  `artikel_keg_nr` ," +
			"  `artikel_mindestbestand` ," +
			"  `artikel_fk_mengeneinheit` ," +
			"  `mengeneinheit_name` ," +
			"  `artikel_fk_hersteller` ," +
			"  `hersteller_name` ," +
			"  `packstueck_menge` ," +
			"  `packstueck_id` ," +
			"  `position_id` ," +
			"  `position_nummer` ," +
			"  `ebene_id` ," +
			"  `ebene_nummer` ," +
			"  `saeule_id` ," +
			"  `saeule_nummer` ," +
			"  `zeile_id` ," +
			"  `zeile_nummer` ," +
			"  `halle_id` ," +
			"  `halle_name`," +
			"   abteilung_id," +
			"   abteilung_name" +
			" )" +
			" SELECT" +
			"  ?," +
			"  b.id," +
			"  art.id," +
			"  art.bezeichnung," +
			"  art.typ," +
			"  art.keg_nr," +
			"  art.mindestbestand," +
			"  art.fk_mengeneinheit," +
			"  me.name," +
			"  art.fk_hersteller," +
			"  her.name," +
			"  b.menge," +
			"  b.id," +
			"  p.id," +
			"  p.nummer," +
			"  e.id," +
			"  e.nummer," +
			"  s.id," +
			"  s.nummer," +
			"  z.id," +
			"  z.nummer," +
			"  h.id," +
			"  h.name," +
			"  a.id, " +
			"  a.name " +
			" FROM mengeneinheit me, herstellerlieferant her, abteilung a, artikel art "+
			" left outer join bestandspackstueck b on b.fk_artikel = art.id " +
			" left outer join position p on p.id = b.fk_position " +
			" left outer join ebene e on e.id = p.fk_ebene " +
			" left outer join saeule s on s.id = e.fk_saeule " +
			" left outer join zeile z on z.id = s.fk_zeile " +
			" left outer join halle h on h.id = z.fk_halle " +
			" where (a.id = b.fk_abteilung or b.fk_abteilung is null) " +
			" and art.fk_mengeneinheit = me.id " +
			" and her.id = art.fk_hersteller " +
			" and art.id in (select fk_artikel from bericht_mindestbestand_artikel where fk_bericht_id=?) " + 
//			" and art.mindestbestand > 0 " + 
//			" and art.mindestbestand > b.menge  " + 
			" and a.id = ?";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("fk_bericht_id_2", this.getId());
		getSqlParams().put("fk_abteilung", abteilungBean.getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	    
	    stmt.close();
	    stmt = null;
	    
	}
	
	private void passeMindestMengeAn() throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		final String insertSQL = 
			" SELECT" +
			"  mbm.abteilung_id as abteilung_id," +
			"  mbm.fk_artikel as artikel_id," +
			"  mbm.id as bestandspackstueck_id," +
			"  mbm.packstueck_menge as packstueck_menge," +
			"  mbm.artikel_mindestbestand as artikel_mindestmenge" +
			"  FROM bericht b" +
			"  left outer join bericht_mindestbestand mbm" +
			"  on b.id = mbm.fk_bericht_id" +
			" where"+		
			"  b.id = ? " +
			" and mbm.id is not null" +
			" and halle_id is not null" +
			//" order by artikel_id, bestandspackstueck_id	";
			//Reihenfolge genau wie im Bericht, damit das erste Mal der Artikel die Mindestbestandsmenge hat.
		    " order by fk_artikel, abteilung_id, halle_name, zeile_nummer, saeule_nummer, ebene_nummer, position_nummer, mbm.id  ";
		
		
		
		//Params werden gesetzt.
		getSqlParams().put("id", this.getId());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
//	    System.out.println(stmt.toString());
	    //Statement ausführen
	    ResultSet rs = stmt.executeQuery();
		// ergebnisse auswerten.
		final String changeSQL = 
			" update bericht_mindestbestand set artikel_mindestbestand = 0 " +
			" where fk_bericht_id = ? and  id = ? ";
		//SQL absetzen
		PreparedStatement chachedStmt = LagerConnection.getOneInstance().
					getConnection().prepareStatement(changeSQL);
		
		final String deleteSQL = 
			" delete from bericht_mindestbestand " +
			" where fk_bericht_id = ? and  fk_artikel = ? and abteilung_id = ? ";
		//SQL absetzen
		PreparedStatement deleteStmt = LagerConnection.getOneInstance().
					getConnection().prepareStatement(deleteSQL);
		
		Integer a_id = 0;
		Integer bp_id = 0;
		Integer abteilung_id = 0;
		Integer abteilungsArtMenge = 0;
		Integer a_mindestMenge = 0;
		Integer old_a_id = 0;
		Integer old_abteilung_id = 0;
		
		//Die Schleife macht zwei Sachen:
		// 1. Löschen der Artikel, wessen MengenSummen pro Abteilung den Mindestbestand überschreitn.
		// 2. Löschen der Mindestbestandsmenge ab der zweiten Zeile des Artikel je Abteilung
		while (rs.next()) {

			//alte Werte sichern!
			old_a_id = a_id;
			old_abteilung_id = abteilung_id;
//			Integer old_bp_id = bp_id;
//			Integer old_a_mindestMenge = a_mindestMenge;

			//neue Werte holen
			a_id = rs.getInt("artikel_id");
			bp_id = rs.getInt("bestandspackstueck_id");
			abteilung_id = rs.getInt("abteilung_id");
			
			if (a_id.equals(old_a_id)&&abteilung_id.equals(old_abteilung_id)){
				abteilungsArtMenge = abteilungsArtMenge + rs.getInt("packstueck_menge");
				//Folge-Artikel hat sich nicht geändert.
				//Also KEINE Mindestbestandmenge ausgeben
				getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
				//Params werden gesetzt.
				getSqlParams().put("fk_bericht_id", this.getId());
				getSqlParams().put("id", bp_id);
				//Parameter in den Statement übernehmen
				getSqlParams().setParams(chachedStmt);
				if (chachedStmt.executeUpdate()!=1){
					Log.log().severe("Kein Satz ist geändert!");
				}
			}else{
				if (abteilungsArtMenge>a_mindestMenge){
					//lösche Artikel aus der Mindest-Liste
					loescheArtikelVonListe(old_a_id,old_abteilung_id,this.getId(),deleteStmt);
				}
				
				//Der Artikel hat sich geändert (oder es ist der allererste artikel)
				a_mindestMenge = rs.getInt("artikel_mindestmenge");
				
				//Menge aus dem Packstück lesen
				abteilungsArtMenge = rs.getInt("packstueck_menge");
			}
		}
		
		if (abteilungsArtMenge>a_mindestMenge){
			//lösche Artikel aus der Mindest-Liste
			loescheArtikelVonListe(old_a_id,old_abteilung_id,this.getId(),deleteStmt);
		}
		
		chachedStmt.close();
		deleteStmt.close();
		rs.close();
	    stmt.close();
	    stmt = null;
	}	
	
	private void loescheArtikelVonListe(Integer artikel_Id, Integer abteilungId,
			Integer fk_bericht_id, PreparedStatement deleteStmt) throws SQLException, LagerException {
		getSqlParams().clear(); // Vorbereitung auf die nächste SQL-Anweisung
		// Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", fk_bericht_id);
		getSqlParams().put("artikel_Id", artikel_Id);
		getSqlParams().put("abteilungId", abteilungId);
		getSqlParams().setParams(deleteStmt);
		
		if (deleteStmt.executeUpdate() <= 0) {
			Log.log().severe("Kein Satz ist gelöscht!");
		}

	}

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
	

	
	@Override
	public void verarbeiteBericht() throws SQLException, LagerException, Exception {
		super.verarbeiteBericht();
		
		// Run-time report parameters
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("title", "Mindestbestand");
		parameters.put("bericht_id", this.getBerichtBean().getId().toString());
		parameters.put("user", this.getBerichtBean().getAktuellerBenutzer().getVorname() + " " +this.getBerichtBean().getAktuellerBenutzer().getName());
		parameters.put("druckdatum", this.getBerichtBean().getDruckDatumKopie().toString());
		//Zeilen aus der DB einlesen
		StringBuilder bemerkung = new StringBuilder();
		for (int i = 0; i< Run.getOneInstance().getBenutzerBean().getBenutzerAbteilungBeans().size();i++){
			if (i>0){
				bemerkung.append(", ");
			}
			AbteilungBean abteilungBean = Run.getOneInstance().
				getBenutzerBean().getBenutzerAbteilungBeans().get(i).getAbteilung();
			bemerkung.append(abteilungBean.getAbteilungName());
		}
		parameters.put("bemerkung", bemerkung.toString());
		
		ReportDriver.getOneInstance().runReportView(parameters, Konstanten.REPORT_MINDEST_BESTAND_JRXML,Konstanten.REPORT_MINDEST_BESTAND_JASPER);

	}
	
//	private String erzeugeEmailInhaltBestellung(BaBean baBean,
//			ArrayList<BaPosBean> baPosBeans) {
//		Integer tabellenBreite = 1000;
////		private String erzeugeEmailInhaltBestellanforderung(BaBean baBean2,
////	    ArrayList<BaPosBean> baPosBeans2) {
//		String inhalt = "<html><head><title>Bestellung</title></head><body>";
//		
//		
//		
//		//**************  Kopf **************
//		getBerichtZeileHtml().getBerichtSpalten().clear();
//		getBerichtZeileHtml().setSpaltenHoehe(2);
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 300," "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 300," "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 300," "));
//		inhalt = inhalt  + 
//		"<table style=\"text-align:left\" border=\"0\"  width=\"" + tabellenBreite.toString() +"\"> <colgroup width=\"500\" span=\""+
//			new Integer(getBerichtZeileHtml().getBerichtSpalten().size()).toString()+
//		"\"> </colgroup>";
//		
//		
//		//---
//		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt("Kemptener Eisengießerei Adam Hönig AG <br>Postfach 1849 . 87408 Kempten/Allgäu"); //erste Splate
//		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
//		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(	"im Allmey 4 1/2 <br>87435 Kempten ");
//		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
//		
//		//---
//		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
//		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt("");
//
//		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(
//				"<br>Abtelung: " + baBean.getAbsenderBenutzerBean().getErsteAbteilungDesBenutzers().getAbteilungName() + 
//				"<br>Bearbeiter:" + baBean.getAbsenderBenutzerBean().getName() +
//				"<br>Telefon:" + baBean.getAbsenderBenutzerBean().getId() +
//				"<br>E-mail:" + baBean.getAbsenderBenutzerBean().getEmail() +
//				"<br>Fax: 0831 / 58 11 014" + 
//				"<br>" 
//				);
//		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
//		
//		//Lieferant und Datum
//		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(baBean.getLhBean().getName().toString() + "" +
//				"<br>"+ baBean.getLhBean().getAnsprechpartner().toString() +
//				"<br>" + baBean.getLhBean().getAdresse_strasse().toString() +
//				"<br>" + baBean.getLhBean().getAdresse_land().toString() +
//				" " + baBean.getLhBean().getAdresse_plz().toString()+
//				" " + baBean.getLhBean().getAdresse_stadt().toString()
//				); //erste Splate
//		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
//		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("Datum:" + Bean.getAktuellesDatum());
//		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
//		
//		//Bestellung, Nummer
//		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
//		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt("Bestellung Nr. " + baBean.getId().toString()); 
//		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("");
//		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
//		
//		
//		inhalt = inhalt  + "</table>";
//		
//		inhalt = inhalt  + " " + "<br>";
//		inhalt = inhalt  + " " + "<br>";
//		inhalt = inhalt  + "Sehr geehrte" + baBean.getLhBean().getAnsprechpartner().toString() + ",<br>";
//		inhalt = inhalt  + "gem. vereinbarten Konditionen bestellen wir hiermit folgende Artikel:<br>";
//		inhalt = inhalt  + " " + "<br>";
//		inhalt = inhalt  + " " + "<br>";
//
//		inhalt = EinzelBericht.textToHtml(inhalt, ""); //umbrüche durch <br> ersetzen
//		
//		
//		//************** Beging Positionen **************
//		inhalt = inhalt  + 	"<table border=\"1\"  width=\""+  tabellenBreite + "\">" ;
////		"<table border=\"1\"  width=\""+  tabellenBreite + "\"> <colgroup width=\"200\" span=\""+
////		new Integer(getBerichtZeileHtml().getBerichtSpalten().size()).toString()+
////		"\"> </colgroup>";
//
//		// ************** Positionen **************
//		getBerichtZeileHtml().getBerichtSpalten().clear();
//		getBerichtZeileHtml().setSpaltenHoehe(2);
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Pos","", 30," "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Menge","", 50, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("ME","", 60, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Artikelbezeichnung/ ","     Typ", 250, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Hersteller","", 90, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Lieferant.","bestellnr.", 90, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Katalog/","Seite", 90, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte(" Liefer-","Termin /KW", 90, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("   KE-","ArtikelNr.", 90, " "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Kosten-","stelle", 90, " "));
////		//erste Linie über den Spalten
////		inhalt = inhalt  + getBerichtZeileHtml().getDurchgehendeLinie() + "\n";
////		
//		//Spalten-Bezeichnungen , erste Zeile
//		inhalt = inhalt  + getBerichtZeileHtml().getSpaltenNamenZeile(1) + "\n";
//		//Spalten-Bezeichnungen, zweite Zeile
//		inhalt = inhalt  + getBerichtZeileHtml().getSpaltenNamenZeile(2) + "\n";
//		
////		for(BaPosBean baPosBean : baPosBeans){ //identisch mit den nächsten 2 Zeilen
//		for(int i = 0; i<baPosBeans.size();i++){
//			BaPosBean baPosBean = baPosBeans.get(i);
//
//			//Alle Spalten mit Daten versorgen
//			getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(new Integer(i+1).toString()); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(baPosBean.getBestellmenge().toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(baPosBean.getMengenEinheitBean().getName()); //nächste Spalte
//    		getBerichtZeileHtml().getBerichtSpalten().get(3).setInhalt(baPosBean.getArtikelBean().getBezeichnung(). toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(4).setInhalt(baPosBean.getArtikelBean().getHersteller().getName().toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(5).setInhalt(baPosBean.getLieferantenbestellnummer().toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(6).setInhalt(baPosBean.getKatalogBean().getName().toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(7).setInhalt(baPosBean.getLiefertermin().toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(8).setInhalt(baPosBean.getArtikelBean().getKeg_nr().toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(9).setInhalt(baPosBean.getKostenstelle().toString()); //zweite Splate
//
//			
//			//Zeile drucken
//			inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile("#CCEECC") + "\n";
//	
//			
//			//Alle Spalten mit Daten versorgen
//			getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(""); //erste Splate
//    		getBerichtZeileHtml().getBerichtSpalten().get(3).setInhalt(
//    				baPosBean.getArtikelBean().getTyp().toString()); //zweite Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(4).setInhalt(""); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(5).setInhalt(""); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(6).setInhalt(
//					baPosBean.getKatalogseite()); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(7).setInhalt(""); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(8).setInhalt(""); //erste Splate
//			getBerichtZeileHtml().getBerichtSpalten().get(9).setInhalt(""); //erste Splate
//
//			//Zeile drucken
//			inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile("#CCEECC") + "\n";
//			
////			//abschliessende  Linie unter den Spalten-Daten
////			inhalt = inhalt  + getBerichtZeileHtml().getDurchgehendeLinie() + "\n";
//		}
//		inhalt = inhalt  + "</table>";
//		//************** Ende Positionen **************
//		
//		//************** FUSS **************
//		
//		
////		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Den Preis und Lieferzeit nehmen Sie bitte mit ihrer Auftragsbestätigung ") + "\n";
//		inhalt = inhalt + "<br>Liefertermin sieh Tabelle. ";
//		if (baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().size()>0){
//			inhalt = inhalt + "Abteilung: " + baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().get(0).getAbteilung().getAbteilungName();
//		}
//		
//		inhalt = inhalt + "<br>Lieferbedingungen frei Haus, einschl. Verpackung" ;
//		inhalt = inhalt + "<br>Zahlungsbedingungen: 8Tage, 2% Skonto, 30 Tage netto" ;
//		inhalt = inhalt + "<br>";
//		inhalt = inhalt + "<br>";
//		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Mit freundlichen Grüßen ") + "<br>";
//		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Kemptener Eisengießerei ") + "<br>";
//		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Adam Hönig AG") + "<br>";
//		inhalt = inhalt + "i.A. " +baBean.getAbsenderBenutzerBean().getName().toString() + "<br>";
//		inhalt = inhalt + " " + "<br>";
//		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Bestellung gilt ohne Unterschrift als erteilt ") + "<br>";
//		inhalt = inhalt + " " + "<br>";
//		inhalt = inhalt + " " + "<br>";
//		inhalt = inhalt + " " + "<br>";
//		
//		
//		getBerichtZeileHtml().getBerichtSpalten().clear();
//		getBerichtZeileHtml().setSpaltenHoehe(2);
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 100," "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 100," "));
//		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 100," "));
//		inhalt = inhalt  + 
//		"<table style=\"text-align:left\" border=\"0\"  width=\"" + tabellenBreite.toString() +"\"> ";
//		//---
//		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt("Aktiengesellschaft,Sitz Kempten" +
//				"<br>Register Kempten HRB 7539" +
//				"<br>AR-Vorsitzender: Thomas Wäschle" +
//				"<br>Vorstand Roland Hübner"); //erste Splate
//		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
//		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("Bankverbindung:" +
//				"<br>Sparkasse Allgäu(BLZ 733 500 00)310 014 709) " +
//				"<br>LB Baden-Württemberg(BLZ 600 501 01)454 185 7" +
//				"<br>" +
//				"");
//		//Zeile drucken
//		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
//		
//		inhalt = inhalt + "</table>";
//		inhalt = inhalt + "</body></html>";
//		return inhalt;
//	}
//
//	/**
//	 * Liste erstellen aus (Anforderung und Anforderungspositionen)
//	 * @param baBean
//	 * @param baPosBeans
//	 * @return
//	 */
////	private String erzeugeEmailInhaltBestellung(BaBean baBean,
////			ArrayList<BaPosBean> baPosBeans) {
//	private String erzeugeEmailInhaltBestellanforderung(BaBean baBean,
//		    ArrayList<BaPosBean> baPosBeans) {
//		String inhalt = "";
//
//		
//		// ************** Positionen **************
//		getBerichtZeile().setSymbolFuerHorizontaleLinie("-");
//		getBerichtZeile().setSymbolFuerVertikaleLinie("|");
//		getBerichtZeile().getBerichtSpalten().clear();
//		getBerichtZeile().setSpaltenHoehe(2);
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Pos","", 3," "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Menge","", 5, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("  ME","", 6, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte(" Artikelbezeichnung/ ","   Typ", 35, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Hersteller","", 10, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Lieferant.","bestellnr.", 10, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Katalog/","Seite", 10, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Liefer-","Termin /KW", 10, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("   KE-","ArtikelNr.", 10, " "));
//		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Kosten-","stelle", 10, " "));
//		
//		//**************  Kopf ************** 
//		if (baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().size()>0){
//			inhalt = inhalt + "Abteilung:" + baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().get(0).getAbteilung().getAbteilungName() +"\n";
//		}
//		inhalt = inhalt + "Bearbeiter:" + baBean.getAbsenderBenutzerBean().getName().toString() + "\n";
//		inhalt = inhalt  + " " + "\n";
//		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
////		inhalt = inhalt + "---------------------------------------------------------------------"+ "\n";
//		inhalt = inhalt + " " + "\n";
//		inhalt = inhalt  + "Gesendet am :" + Bean.getAktuellesDatum().toString() + "\n";
//		inhalt = inhalt  + "         an :" + baBean.getAnnehmerBenutzerBean().getEmail().toString()+"\n";
//		inhalt = inhalt  + " " + "\n";
//		inhalt = inhalt  + " " + "\n";
//		inhalt = inhalt  + " " + "\n";
//		inhalt = inhalt  + "                  Bestellanforderung Nr." + baBean.getId() + "\n";
//		inhalt = inhalt  + " " + "\n";
//		inhalt = inhalt  + " " + "\n";
//		
//		//erste Linie über den Spalten
//		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
//		
//		//Spalten-Bezeichnungen , erste Zeile
//		inhalt = inhalt  + getBerichtZeile().getSpaltenNamenZeile(1) + "\n";
//		
//		//Spalten-Bezeichnungen, zweite Zeile
//		inhalt = inhalt  + getBerichtZeile().getSpaltenNamenZeile(2) + "\n";
//		
//		//abschliessende  Linie unter den Spalten-Bezeichungen
//		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
//		
//		
////		for(BaPosBean baPosBean : baPosBeans){ //identisch mit den nächsten 2 Zeilen
//		for(int i = 0; i<baPosBeans.size();i++){
//			BaPosBean baPosBean = baPosBeans.get(i);
//
//			//Alle Spalten mit Daten versorgen
//			getBerichtZeile().getBerichtSpalten().get(0).setInhalt(new Integer(i+1).toString()); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(1).setInhalt(baPosBean.getBestellmenge().toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(2).setInhalt(baPosBean.getMengenEinheitBean().getName()); //nächste Spalte
//    		getBerichtZeile().getBerichtSpalten().get(3).setInhalt(baPosBean.getArtikelBean().getBezeichnung(). toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(4).setInhalt(baPosBean.getArtikelBean().getHersteller().getName().toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(5).setInhalt(baPosBean.getLieferantenbestellnummer().toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(6).setInhalt(baPosBean.getKatalogBean().getName().toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(7).setInhalt(baPosBean.getLiefertermin().toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(8).setInhalt(baPosBean.getArtikelBean().getKeg_nr().toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(9).setInhalt(baPosBean.getKostenstelle(). toString()); //zweite Splate
//
//			
//			//Zeile drucken
//			inhalt = inhalt  + getBerichtZeile().getDatenZeile() + "\n";
//	
//			
//			//Alle Spalten mit Daten versorgen
//			getBerichtZeile().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(1).setInhalt(""); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(2).setInhalt(""); //erste Splate
//    		getBerichtZeile().getBerichtSpalten().get(3).setInhalt(
//    				baPosBean.getArtikelBean().getTyp().toString()); //zweite Splate
//			getBerichtZeile().getBerichtSpalten().get(4).setInhalt(""); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(5).setInhalt(""); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(6).setInhalt(
//					baPosBean.getKatalogseite()); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(7).setInhalt(""); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(8).setInhalt(""); //erste Splate
//			getBerichtZeile().getBerichtSpalten().get(9).setInhalt(""); //erste Splate
//
//			//Zeile drucken
//			inhalt = inhalt  + getBerichtZeile().getDatenZeile() + "\n";
//			
//			//abschliessende  Linie unter den Spalten-Daten
//			inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
//		}
//		
//
//		
//		
//		
//		//************** FUSS **************
//		inhalt = inhalt + " " + "\n";
//		inhalt = inhalt + " " + "\n";
//		inhalt = inhalt + "Gewuenschter Liefertermin :  \n" ;
//		inhalt = inhalt + "!!! BITTE SOFORT LIEFERN !!! \n" ;
//		String linie = "1234567890123456789012345678901234567890\n" ;
//		inhalt = inhalt + linie;
//		inhalt = inhalt + linie.substring(0,11) + " , Lieferant, " +linie.substring(25); 
//		return inhalt;
//	}
//	
//	
//
//	private HalleBeanDB getHalleBeanDB() {
//		if (halleBeanDB ==null){
//			halleBeanDB = new HalleBeanDB();
//		}
//		return halleBeanDB;
//	}

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

//	private HalleBean getInventurHalleBean() {
//		return inventurHalleBean;
//	}
//
//	private void setHalleBean(HalleBean halleBean) {
//		this.inventurHalleBean = halleBean;
//	}

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
	
//	private EtageBeanDB getEtageBeanDB() {
//		if (etageBeanDB == null) {
//			etageBeanDB = new EtageBeanDB();
//		}
//		return etageBeanDB;
//	}
//	
//	private ZeileBeanDB getZeileBeanDB() {
//		if (zeileBeanDB == null) {
//			zeileBeanDB = new ZeileBeanDB();
//		}
//		return zeileBeanDB;
//	}
//	
//	private SaeuleBeanDB getSaeuleBeanDB() {
//		if (saeuleBeanDB == null) {
//			saeuleBeanDB = new SaeuleBeanDB();
//		}
//		return saeuleBeanDB;
//	}
	

}
