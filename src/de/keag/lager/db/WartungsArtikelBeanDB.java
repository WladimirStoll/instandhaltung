package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelSuchBean;

/*
 * BestellanforderungsPositionBean für den Datenbank-Zugriff
 */
public class WartungsArtikelBeanDB extends BeanDB {

	private WartungBeanDB wartungBeanDB;
	private BenutzerBeanDB benutzerBeanDB;
	private ArtikelBeanDB artikelBeanDB;

	public WartungsArtikelBeanDB(){
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
					if (e.getMessage().contains(Konstanten.SQL_ERROR_benutzer_loginName_eindeutig)){
						throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,((BenutzerAbteilungBean)bean).getBenutzer().getLoginName()));
					}
				}else{
					e.printStackTrace();
					throw e;
				}
			}
		} else {
			throw new LagerException(new Fehler(2));
		}
	}

	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into wartungsartikel " +
					"(erfassungsbenutzer, erfassungsdatum, id, fk_wartung, fk_artikel, menge)" +
					" values (?,?,?,?,?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		WartungsArtikelBean wartungsArtikel = (WartungsArtikelBean) bean;
		
		getInsertStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
		getInsertStmt().setDate(2, wartungsArtikel.getAktuellesDatum());
		getInsertStmt().setInt(3, wartungsArtikel.getId());
		//Wartung
		if (wartungsArtikel.getFk_wartung()==null || wartungsArtikel.getFk_wartung().getId()==0){
			getInsertStmt().setNull(4, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(4, wartungsArtikel.getFk_wartung().getId());
		}
		//Artikel
		if (wartungsArtikel.getFk_artikel()==null || wartungsArtikel.getFk_artikel().getId()==0){
			getInsertStmt().setNull(5, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(5, wartungsArtikel.getFk_artikel().getId());
		}
		//menge
		getInsertStmt().setInt(6, wartungsArtikel.getMenge());
		
		getInsertStmt().executeUpdate();
		wartungsArtikel.setBeanDBStatus(BeanDBStatus.SELECT); //nicht nötig, weil danach soweiso mit select gelesen wird.
	}

	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from wartungsartikel " +
						" where id = ?";
			}
		return deleteSQL;
	}
	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		WartungsArtikelBean wartungsArtikel = (WartungsArtikelBean) bean;
		try{
			getDeleteStmt().setInt(1, wartungsArtikel.getId());
			getDeleteStmt().execute();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
		}
	}


	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implemneitert");
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
			WartungsArtikelBean wartungsArtikelBean = new WartungsArtikelBean();
			erzeugeBeanAusResultSet(rs, wartungsArtikelBean, 0, null);
			wartungsArtikelBean.setBeanDBStatus(BeanDBStatus.SELECT); //nicht nötig, weil danach soweiso mit select gelesen wird.
			Log.log().finest(wartungsArtikelBean.toString());
		}
		rs.close();
		rs = null;
//		stmt.close();
//		stmt = null;
		return resultList;
	}

	
	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = " select " + 
						getSelectSpalten() +
						" from wartungsartikel " +
						" where id = ? ";
		}
		return selectAnhandIdSQL;	
	}	
	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		WartungsArtikelBean resultBean = new WartungsArtikelBean();
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
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		WartungsArtikelSuchBean wartungsArtikelSuchBean = (WartungsArtikelSuchBean) iSuchBean;
		return sucheAlle();
	}

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update wartungsartikel " +
					" set " +
					" aenderungsbenutzer=?," + 
					" aenderungsdatum=?," +
					" id=?," +
					" fk_wartung=?," +
					" fk_artikel=?," +
					" menge=? " +
					" where id = ? ";
		}
		return updateSQL;	
	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		WartungsArtikelBean wartungsArtikelBean = (WartungsArtikelBean) bean;
	
		try{
			getUpdateStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
			getUpdateStmt().setDate(2, wartungsArtikelBean.getAktuellesDatum());
			getUpdateStmt().setInt(3, wartungsArtikelBean.getId());
			//Wartung
			if (wartungsArtikelBean.getFk_wartung()==null || wartungsArtikelBean.getFk_wartung().getId()==0){
				getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(4, wartungsArtikelBean.getFk_wartung().getId());
			}
			//Artikel
			if (wartungsArtikelBean.getFk_artikel()==null || wartungsArtikelBean.getFk_artikel().getId()==0){
				getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(5, wartungsArtikelBean.getFk_artikel().getId());
			}
			//menge
			getUpdateStmt().setInt(6, wartungsArtikelBean.getMenge());
			
			//ID
			getUpdateStmt().setInt(7, wartungsArtikelBean.getId());
			
			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
		
	}

	public ArrayList<WartungsArtikelBean> sucheAnhandWartungBean(WartungBean wartungBean) throws SQLException, LagerException {
		ArrayList<WartungsArtikelBean> resultList = new ArrayList<WartungsArtikelBean>();
		ResultSet rs;
		//sql bilden
		String sql = "SELECT" +
				getSelectSpalten() +
				"FROM wartungsartikel  " +
				"where fk_wartung = ? " +
				"order by wartungsartikel.id";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, wartungBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			WartungsArtikelBean wartungsArtikelBean = new WartungsArtikelBean();
			erzeugeBeanAusResultSet(rs, wartungsArtikelBean, 0, null);
			wartungsArtikelBean.setBeanDBStatus(BeanDBStatus.SELECT); //nicht nötig, weil danach soweiso mit select gelesen wird.
			resultList.add(wartungsArtikelBean);
			Log.log().finest(wartungsArtikelBean.toString());
		}
		rs.close();
		rs = null;
//		stmt.close();
//		stmt = null;
//		sql = null;
		return resultList;
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
			" fk_wartung," +
			" fk_artikel," +
			" menge ";
		}
		return selectSpalten;
	}
	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		WartungsArtikelBean wartungsArtikel = (WartungsArtikelBean) bean;
		wartungsArtikel.setErfassungsBenutzer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("erfassungsbenutzer"), 0, null));
		wartungsArtikel.setErfassungsDatum(rs.getDate("erfassungsdatum"));
		wartungsArtikel.setAenderungsBenutzer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("aenderungsbenutzer"), 0, null));
		wartungsArtikel.setErfassungsDatum(rs.getDate("aenderungsdatum"));
		wartungsArtikel.setId(rs.getInt("id"));
		wartungsArtikel.setFk_wartung((WartungBean)getWartungBeanDB().selectAnhandID(rs.getInt("fk_wartung"), 0, null));
		wartungsArtikel.setFk_artikel((ArtikelBean)getArtikelBeanDB().selectAnhandID(rs.getInt("fk_artikel"), 0, null));
		wartungsArtikel.setMenge(rs.getInt("menge"));
	}	

	private WartungBeanDB getWartungBeanDB() {
		if (wartungBeanDB==null){
			wartungBeanDB = new WartungBeanDB();
		}
		return wartungBeanDB;
	}

	private BenutzerBeanDB getBenutzerBeanDB() {
		if (this.benutzerBeanDB==null){
				this.benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	private ArtikelBeanDB getArtikelBeanDB() {
		if (this.artikelBeanDB==null){
				this.artikelBeanDB = new ArtikelBeanDB();
		}
		return artikelBeanDB;
	}
	


//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}



}
