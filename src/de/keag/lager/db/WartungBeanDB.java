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
package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;


import de.keag.lager.core.Bean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtSuchBean;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellung.BestellungStatus;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.model.WartungSuchBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelSuchBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

/*
 * BestellanforderungsPositionBean für den Datenbank-Zugriff
 */
public class WartungBeanDB extends BeanDB {

	private WartungsTypBeanDB wartungsTypBeanDB;
	private WartungsArtBeanDB wartungsArtBeanDB;
	private BaugruppeBeanDB baugruppeBeanDB;
	private BenutzerBeanDB benutzerBeanDB;
	private String neueKartenIdSQL;
	private PreparedStatement neueKartenIdStmt;
	

	public WartungBeanDB(){
		super();
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean != null) {
			if (bean.pruefeEigeneDaten().size() > 0) {
				throw new LagerException(bean.pruefeEigeneDaten().get(0));
			}
			try {
				if (bean.getBeanDBStatus() == BeanDBStatus.INSERT) {
					insertBean(bean);
				} else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE) {
					updateBean(bean);
				} else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE) {
					deleteBean(bean);
				} else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT) {
					// nix zu tun
				} else {
					throw new LagerException(new Fehler(4));
				}
			} catch (SQLException e) {
				if (e.getSQLState().equals("23000")){
					throw new LagerException("Doppelter Schlüssel");
				}
				e.printStackTrace();
			}
		} else {
			throw new LagerException(new Fehler(2));
		}
	}

	
	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
			
			
		updateSQL = " update wartung " +
					" set " +
					" aenderungsbenutzer=? , " + 
					" aenderungsdatum=?,    " +
					" fk_wartungstyp=?, " +
					" karteiid=?, " +
					" beschreibung=?, " +
					" intervall=?,   " +
					" termin_soll=?, " +
					" termin_ist=?,  " +
					" status=?, " +
					" fk_baugruppe=?, " +
					" fk_wartungsart=?, " +
					" bemerkung=? " +
					" where id=?";
		}
		return updateSQL;	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		WartungBean wartungBean = (WartungBean ) bean;
		try{
			tempNr=0;
			getUpdateStmt().setInt(++tempNr, Run.getOneInstance().getBenutzerBean().getId());
			getUpdateStmt().setDate(++tempNr, wartungBean.getAktuellesDatum());
			//Wartungstyp
			if (wartungBean.getFk_wartungstyp()==null || wartungBean.getFk_wartungstyp().getId()==0){
				getUpdateStmt().setNull(++tempNr, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(++tempNr, wartungBean.getFk_wartungstyp().getId());
			}
			//kateiid
			getUpdateStmt().setInt(++tempNr, wartungBean.getKarteiid());
			//beschreibung
			getUpdateStmt().setString(++tempNr, wartungBean.getBeschreibung());
			//intervall
			getUpdateStmt().setInt(++tempNr, wartungBean.getIntervall());
			//termin_soll
			getUpdateStmt().setDate(++tempNr, wartungBean.getTermin_soll());
			//termin_ist
			getUpdateStmt().setDate(++tempNr, wartungBean.getTermin_ist());
			//status
			getUpdateStmt().setString(++tempNr, wartungBean.getStatus().JavaToDB());
			//fk_baugruppe
			if (wartungBean.getFk_baugruppe()==null || wartungBean.getFk_baugruppe().getId()==0){
				getUpdateStmt().setNull(++tempNr, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(++tempNr, wartungBean.getFk_baugruppe().getId());
			}
			//fk_wartungsart
			if (wartungBean.getFk_wartungsart()==null || wartungBean.getFk_wartungsart().getId()==0){
				getUpdateStmt().setNull(++tempNr, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(++tempNr, wartungBean.getFk_wartungsart().getId());
			}
			//bemerkung
			getUpdateStmt().setString(++tempNr, wartungBean.getBemerkung().toString());
			//id
			getUpdateStmt().setInt(++tempNr, wartungBean.getId());
			
			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = " insert into wartung " +
					" (erfassungsbenutzer, " +
					" erfassungsdatum, " +
					" id, " +
					" fk_wartungstyp," +
					" karteiid," +
					" beschreibung," +
					" intervall," +
					" termin_soll," +
					" termin_ist," +
					" status," +
					" fk_baugruppe," +
					" fk_wartungsart," +
					" bemerkung" +			
					")" +
					" values (?,?,?,  ?,?,?,  ?,?,?,  ?,?,?, ?)";
			
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		WartungBean wartungBean = (WartungBean) bean;
		tempNr = 0;
		getInsertStmt().setInt(++tempNr, Run.getOneInstance().getBenutzerBean().getId());
		getInsertStmt().setDate(++tempNr, wartungBean.getAktuellesDatum());
		getInsertStmt().setInt(++tempNr, wartungBean.getId());
		//Wartungstyp
		if (wartungBean.getFk_wartungstyp()==null || wartungBean.getFk_wartungstyp().getId()==0){
			getInsertStmt().setNull(++tempNr, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(++tempNr, wartungBean.getFk_wartungstyp().getId());
		}
		//kateiid
		getInsertStmt().setInt(++tempNr, wartungBean.getKarteiid());
		//beschreibung
		getInsertStmt().setString(++tempNr, wartungBean.getBeschreibung());
		//intervall
		getInsertStmt().setInt(++tempNr, wartungBean.getIntervall());
		//termin_soll
		getInsertStmt().setDate(++tempNr, wartungBean.getTermin_soll());
		//termin_ist
		getInsertStmt().setDate(++tempNr, wartungBean.getTermin_ist());
		//status
		getInsertStmt().setString(++tempNr, wartungBean.getStatus().JavaToDB());
		//fk_baugruppe
		if (wartungBean.getFk_baugruppe()==null || wartungBean.getFk_baugruppe().getId()==0){
			getInsertStmt().setNull(++tempNr, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(++tempNr, wartungBean.getFk_baugruppe().getId());
		}
		//fk_wartungsart
		if (wartungBean.getFk_wartungsart()==null || wartungBean.getFk_wartungsart().getId()==0){
			getInsertStmt().setNull(++tempNr, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(++tempNr, wartungBean.getFk_wartungsart().getId());
		}
		//bemerkung
		getInsertStmt().setString(++tempNr, wartungBean.getBemerkung().toString());
		
		
		getInsertStmt().execute();
		
//		wartungBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	
	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from wartung " +
						" where id = ? ";
			}
		return deleteSQL;
	}

	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		WartungBean wartungBeanBean = (WartungBean) bean;
		try{
			getDeleteStmt().setInt(1, wartungBeanBean.getId());
			getDeleteStmt().execute();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
		}
	}

	@Override
	protected String getSelectSpalten(){
		if (selectSpalten ==null){
			selectSpalten =
			" erfassungsbenutzer," +
			" erfassungsdatum," +
			" aenderungsbenutzer," +
			" aenderungsdatum," +
			" id," +
			" fk_wartungstyp," +
			" karteiid," +
			" beschreibung," +
			" intervall," +
			" termin_soll," +
			" termin_ist," +
			" status," +
			" fk_baugruppe," +
			" fk_wartungsart," +
			" bemerkung "
			;
		}
		return selectSpalten;
	}	
	
	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		WartungBean resultBean = (WartungBean) bean;
		//Benutzer - Änderungen
		resultBean.setErfassungsBenutzer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("erfassungsbenutzer"), 0, null));
		resultBean.setErfassungsDatum(rs.getDate("erfassungsdatum"));
		resultBean.setAenderungsBenutzer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("aenderungsbenutzer"), 0, null));
		resultBean.setErfassungsDatum(rs.getDate("aenderungsdatum"));
		resultBean.setId(rs.getInt("id"));
		resultBean.setFk_wartungstyp((WartungsTypBean)getWartungsTypBeanDB().selectAnhandID(rs.getInt("fk_wartungstyp"), 0, null));
		resultBean.setKarteiid(rs.getInt("karteiid"));
		resultBean.setBeschreibung(rs.getString("beschreibung"));
		resultBean.setIntervall(rs.getInt("intervall"));
		resultBean.setTermin_soll(rs.getDate("termin_soll"));
		resultBean.setTermin_ist(rs.getDate("termin_ist"));
		resultBean.setStatus(StatusEnum.DbToJava(rs.getString("status")));
		resultBean.setFk_baugruppe((BaugruppeBean)getBaugruppeBeanDB().selectAnhandID(rs.getInt("fk_baugruppe"), 0, null));
		resultBean.setFk_wartungsart((WartungsArtBean)getWartungsArtBeanDB().selectAnhandID(rs.getInt("fk_wartungsart"), 0, null));
		resultBean.setBemerkung(rs.getString("bemerkung"));
	}


	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = " select " + 
						getSelectSpalten() +
						" from wartung " +
						" where id = ? ";
		}
		return selectAnhandIdSQL;	
	}
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		WartungBean resultBean = new WartungBean();
		ResultSet rs;
		PreparedStatement stmt = getSelectAnhandIdStmt();
		stmt.setInt(1, id);
		rs = stmt.executeQuery(); //Ausführen
		//ergebnisse auswerten.
		if(rs.next()){
			erzeugeBeanAusResultSet(rs, resultBean, 0, null);
			resultBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
			Log.log().finest(resultBean.toString());
		}else{
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Bestellung nicht gefunden"));
		}
		rs.close();
		rs = null;
//		stmt.close();
//		stmt = null;
		return resultBean;	
	}


	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = " select " + 
						getSelectSpalten() +
						" from wartungsartikel " +
						" where id = id "; //alle
			}
			return selectSQL;	
	}
	
	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		PreparedStatement stmt = getSelectStmt(); 
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			WartungBean wartungBean = new WartungBean();
			erzeugeBeanAusResultSet(rs, wartungBean, 0, null);
			wartungBean.setBeanDBStatus(BeanDBStatus.SELECT); //nicht nötig, weil danach soweiso mit select gelesen wird.
			Log.log().finest(wartungBean.toString());
		}
		rs.close();
		rs = null;
//		stmt.close();
//		stmt = null;
		return resultList;
	}
	

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implemneitert");
	}


	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		getSqlParams().clear();
		WartungSuchBean suchBean = (WartungSuchBean) iSuchBean;
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = " select " + //select
				getSelectSpalten() +
				" from wartung " + //from
				" where 1 = 1 " ;
		
		
		//Baugruppe
		if (suchBean.getBaugruppeBean() != null && !suchBean.getBaugruppeBean().getId().equals(0)) {
			ArrayList<Integer> alleBaugruppen = new ArrayList<Integer> (); 
			getBaugruppeBeanDB().sucheAlleNachkommenEinerBaugruppeAnhandId(suchBean.getBaugruppeBean().getId(),alleBaugruppen);
			for(int i=0; i< alleBaugruppen.size(); i++ ){
				if (i==0){
					sql = sql + " and ( ";
				}else{
					sql = sql + "       or ";
				}
				sql =     sql + "       fk_baugruppe=? ";
//				getSqlParams().putAuchDoppelt("fk_baugruppe", suchBean.getBaugruppeBean().getId().toString());
				getSqlParams().put("fk_baugruppe", alleBaugruppen.get(i).toString(),true);
				if ((i+1)==alleBaugruppen.size()){
					sql = sql + "      ) ";
				}
			}
		}
		
		//DAtum ist IMMER im Spiel (muss immer gefüllt sein
		//Druckdatum original von
		sql = sql + " and (( termin_soll >= ? ";
		getSqlParams().put("termin_soll", suchBean.getDatumVon(),true);
		//Druckdatum original bis
		sql = sql + " and termin_soll <= ?) ";
		getSqlParams().put("termin_soll", suchBean.getDatumBis(),true);
		sql = sql + " OR (termin_ist >= ? ";
		getSqlParams().put("termin_ist", suchBean.getDatumVon(),true);
		//Druckdatum original bis
		sql = sql + " and termin_ist <= ?)) ";
		getSqlParams().put("termin_ist", suchBean.getDatumBis(),true);
		
		
		// Status
		switch (suchBean.getStatusEnum()) {
		case ALLE:
			break;
		case ABGESCHLOSSEN:
		case OFFEN:
		case UNKNOWN:
		default: {
			sql = sql + " and status = ? ";
			getSqlParams().put("status", suchBean.getStatusEnum().JavaToDB());
		}
		}
		;
		
		//Wartungs ART
		if (suchBean.getWartungsArtBean() != null && !suchBean.getWartungsArtBean().getId().equals(0)) {
			sql = sql + " and fk_wartungsart=? ";
			getSqlParams().put("fk_wartungsart", suchBean.getWartungsArtBean().getId().toString(),true);
		}
		//Wartungs TYP
		if (suchBean.getWartungsTypBean() != null && !suchBean.getWartungsTypBean().getId().equals(0)) {
			sql = sql + " and fk_wartungstyp=? ";
			getSqlParams().put("fk_wartungstyp", suchBean.getWartungsTypBean().getId().toString(),true);
		}
		
		sql = sql + " order by id desc";
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		//Fragezeichen durch werte ersetzen
		
		//Paramete setzen
		getSqlParams().setParams(stmt);
	    Log.log().finest(stmt.toString());
		
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			WartungBean baBean = new WartungBean();
			erzeugeBeanAusResultSet(rs, baBean, 0, null);
			baBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
			Log.log().finest(baBean.toString());
			resultList.add(baBean);
		}
		return resultList;		
	}


//	public ArrayList<WartungBean> sucheAnhandBaugruppeBean(BaugruppeBean baugruppeBean) throws SQLException, LagerException {
//		ArrayList<WartungBean> resultList = new ArrayList<WartungBean>();
//		ResultSet rs;
//		//sql bilden
//		String sql = "SELECT erfassungsbenutzer, erfassungsdatum, aenderungsbenutzer, aenderungsdatum, id, typ, karteinummer, beschreibung, intervall, termin_soll, termin_ist, status, baugruppe_id " +
//				" FROM wartung where baugruppe_id = ? ";
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
//		stmt.setInt(1, baugruppeBean.getId());
//		rs = stmt.executeQuery();
//		//ergebnisse auswerten.
////		baBean.loescheAlleBaPositionen();
//		while(rs.next()){
//			WartungBean wartungBean = new WartungBean();
//			wartungBean.setBaugruppeBean(baugruppeBean);
//			
//			erzeugeBeanAusResultSet(rs,wartungBean);
//			
//			wartungBean.setBeanDBStatus(BeanDBStatus.SELECT);
//			
//			resultList.add(wartungBean);
//			Log.log().finest(wartungBean.toString());
//		}
//		return resultList;
//	}

	private WartungsTypBeanDB getWartungsTypBeanDB() {
		if (wartungsTypBeanDB==null){
			wartungsTypBeanDB = new WartungsTypBeanDB();
		}
		return wartungsTypBeanDB;
	}
	
	private WartungsArtBeanDB getWartungsArtBeanDB() {
		if (wartungsArtBeanDB==null){
			wartungsArtBeanDB = new WartungsArtBeanDB();
		}
		return wartungsArtBeanDB;
	}
	

//	public ArrayList<BaugruppeArtikelBean> sucheAnhandArtikelBean(
//			ArtikelBean artikelBean) throws SQLException, LagerException {
//		ArrayList<BaugruppeArtikelBean> resultList = new ArrayList<BaugruppeArtikelBean>();
//		ResultSet rs;
//		//sql bilden
//		String sql = "SELECT id_baugruppe, eingebauteArtikelMenge FROM baugruppeartikel b where id_artikel = ?";
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
//		stmt.setInt(1, artikelBean.getId());
//		rs = stmt.executeQuery();
//		//ergebnisse auswerten.
////		baBean.loescheAlleBaPositionen();
//		while(rs.next()){
//			BaugruppeArtikelBean baugruppeArtikelBean = new BaugruppeArtikelBean();
////			baugruppeArtikelBean.setBaugruppe(baugruppeBean);
//			baugruppeArtikelBean.setArtikel(artikelBean);
//			
//			BaugruppeBean baugruppeBean = (BaugruppeBean)getBaugruppeBeanDB().selectAnhandID(rs.getInt("id_baugruppe"));
//			baugruppeArtikelBean.setBaugruppe(baugruppeBean);
////			ArtikelBean artikelBean = (ArtikelBean)getArtikelBeanDB().selectAnhandID(rs.getInt("id_artikel"));
////			baugruppeArtikelBean.setArtikel(artikelBean);
//			
//			baugruppeArtikelBean.setEingebauteMenge(rs.getInt("eingebauteArtikelMenge"));
//			
//			baugruppeArtikelBean.setBeanDBStatus(BeanDBStatus.SELECT);
//			resultList.add(baugruppeArtikelBean);
//			Log.log().finest(baugruppeArtikelBean.toString());
//			
//		}
//		return resultList;
//	}

//	private BaugruppeBeanDB getBaugruppeBeanDB() {
//		if (baugruppeBeanDB==null){
//			baugruppeBeanDB = new BaugruppeBeanDB();
//		}
//		return baugruppeBeanDB;
//	}
	


//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}

	private BenutzerBeanDB getBenutzerBeanDB() {
		if (this.benutzerBeanDB==null){
				this.benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	public BaugruppeBeanDB getBaugruppeBeanDB() {
		if (this.baugruppeBeanDB==null){
				this.baugruppeBeanDB = new BaugruppeBeanDB();
		}
		return baugruppeBeanDB;
	}

	public Integer generiereWartungsKartenId() throws SQLException, LagerException {
		Integer result;
		ResultSet rs;
		PreparedStatement stmt = getNeueKartenIdStmt();
		rs = stmt.executeQuery(); //Ausführen
		//ergebnisse auswerten.
		if(rs.next()){
			result = rs.getInt("neueId");
		}else{
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Keine neue ID gefunden"));
		}
		rs.close();
		rs = null;
		return result;	
	}

	public String getNeueKartenIdSQL() {
		if (this.neueKartenIdSQL==null){
				this.neueKartenIdSQL = " select max(karteiid)+ 1 as neueId from wartung ";
		}
		return neueKartenIdSQL;
	}

	protected PreparedStatement getNeueKartenIdStmt() throws SQLException  {
		if(neueKartenIdStmt==null){
			neueKartenIdStmt = LagerConnection.getOneInstance().getConnection().prepareStatement(getNeueKartenIdSQL());			
		}
		return neueKartenIdStmt;
	}
	

}
