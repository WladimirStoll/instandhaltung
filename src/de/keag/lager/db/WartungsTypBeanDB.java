package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import de.keag.lager.core.Bean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

/*
 * BestellanforderungsPositionBean für den Datenbank-Zugriff
 */
public class WartungsTypBeanDB extends BeanDB {
	private String selectByNameSQL;
	private PreparedStatement selectBynameStmt;
	
	private String selectAlleSQL;
	private PreparedStatement selectAlleSqlStmt;
	
//	//DB-Zugriff auf fremde Tabellen
//	private ArtikelBeanDB artikelBeanDB;
//	private MengenEinheitBeanDB mengenEinheitBeanDB;
//	private KostenstelleBeanDB kostenstelleBeanDB;
//	private LhBeanDB lhBeanDB;
//	private KatalogBeanDB katalogBeanDB;

	private String getSelectByLoginNameSQL() {
		return getSelectByNameSQL();
	}

	private String getSelectByNameSQL() {
		if(selectByNameSQL==null){
			selectByNameSQL 
			= "select id, name " +
					"from wartungstyp where id=?";
		}
		return selectByNameSQL;
	}

	public WartungsTypBeanDB(){
		super();
	}
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean!=null){
			if (bean.pruefeEigeneDaten().size() > 0){
				throw new LagerException(bean.pruefeEigeneDaten().get(0));
			}
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
				//nix zu tun
			}else{
				throw new LagerException(new Fehler(4));
			}
		}else{
			throw new LagerException(new Fehler(2));
		}
	}

	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from wartungstyp " +
						" where id = ? ";
			}
		return deleteSQL;
	}
	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		WartungsTypBean wartungsTypBean = (WartungsTypBean) bean;
		getDeleteStmt().setInt(1, wartungsTypBean.getId());
		getDeleteStmt().execute();
	}

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update wartungstyp " +
					" set name=?" +
					" where id = ? ";
		}
		return updateSQL;	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		WartungsTypBean wartungsTypBean = (WartungsTypBean) bean;
		try{
			getUpdateStmt().setInt(1, wartungsTypBean.getId());
			getUpdateStmt().setString(2, wartungsTypBean.getName());
//			getUpdateStmt().setInt(3, WartungsTypBean.getLiefertermin());
//			getUpdateStmt().setString(4, PosStatus.JavaToDB(WartungsTypBean.getStatus()));
//			if (WartungsTypBean.getKostenstelle()==null || WartungsTypBean.getKostenstelle().getId()==0){
//				getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
//			}else{
//				getUpdateStmt().setInt(5, WartungsTypBean.getKostenstelle().getId());
//			}
//			if (WartungsTypBean.getMengenEinheitBean()==null || WartungsTypBean.getMengenEinheitBean().getId()==0){
//				getUpdateStmt().setNull(6, java.sql.Types.INTEGER);
//			}else{
//				getUpdateStmt().setInt(6, WartungsTypBean.getMengenEinheitBean().getId());
//			}
//			getUpdateStmt().setInt(7, WartungsTypBean.getKatalogBean().getId());
//			getUpdateStmt().setString(8, WartungsTypBean.getLieferantenbestellnummer());
//			getUpdateStmt().setString(9, WartungsTypBean.getKatalogseite());
//			getUpdateStmt().setDouble(10, WartungsTypBean.getKatalogpreis());
//					
//			getUpdateStmt().setInt(11, WartungsTypBean.getId());
			
			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	
	private PreparedStatement getSelectByNameStmt() throws SQLException,LagerException  {
		if(selectBynameStmt==null){
			selectBynameStmt = LagerConnection.
				getOneInstance().
					getConnection().
						prepareStatement(getSelectByNameSQL());			
		}
		return selectBynameStmt;
	}

	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into wartungstyp " +
					"(id, name)" +
					" values (?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		WartungsTypBean wartungsTypBean = (WartungsTypBean) bean;
		getInsertStmt().setInt(1, wartungsTypBean.getId());
		getInsertStmt().setString(2, wartungsTypBean.getName());
//		getInsertStmt().setInt(3, beanBaPos.getArtikelBean().getId());
//		getInsertStmt().setInt(4, beanBaPos.getBestellmenge());
//		getInsertStmt().setInt(5, beanBaPos.getLiefertermin());
//		getInsertStmt().setString(6, PosStatus.JavaToDB(beanBaPos.getStatus()));
//		if (beanBaPos.getKostenstelle()==null || beanBaPos.getKostenstelle().getId()==0){
//			getInsertStmt().setNull(7, java.sql.Types.INTEGER);
//		}else{
//			getInsertStmt().setInt(7, beanBaPos.getKostenstelle().getId());
//		}
//		if (beanBaPos.getMengenEinheitBean()==null || beanBaPos.getMengenEinheitBean().getId()==0){
//			getInsertStmt().setNull(8, java.sql.Types.INTEGER);
//		}else{
//			getInsertStmt().setInt(8, beanBaPos.getMengenEinheitBean().getId());
//		}
//		getInsertStmt().setInt(9, beanBaPos.getKatalogBean().getId());
//		getInsertStmt().setString(10, beanBaPos.getLieferantenbestellnummer());
//		getInsertStmt().setString(11, beanBaPos.getKatalogseite());
//		getInsertStmt().setDouble(12, beanBaPos.getKatalogpreis());
		try{
			getInsertStmt().executeUpdate();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			if (e.getSQLState().equals("23000")){
				throw new LagerException("Doppelter Schlüssel, Wartungstyp");
			}
			e.printStackTrace();
		}
//		beanBaPos.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
//	public ArrayList<WartungsTypBean>  sucheWartungsTypBeansAnhandBaBean(BaBean baBean ) throws SQLException, LagerException{
//		ArrayList<WartungsTypBean> resultList = new ArrayList<WartungsTypBean>();
//		ResultSet rs;
//		//sql bilden
//		String sql = "select id, fk_bestellanforderung, fk_artikel, " +
//				"bestellmenge, liefertermin, status, fk_kostenstelle, fk_mengeneinheit, " +
//				"fk_katalog, lieferantenbestellnummer, katalogseite, katalogpreis" +
//				" from bestellanforderungsposition where fk_bestellanforderung = ? order by id";
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
//		stmt.setInt(1, baBean.getId());
//		rs = stmt.executeQuery();
//		//ergebnisse auswerten.
////		baBean.loescheAlleBaPositionen();
//		while(rs.next()){
//			WartungsTypBean WartungsTypBean = new WartungsTypBean();
//			WartungsTypBean.setId(rs.getInt("id"));
//			WartungsTypBean.setStatus(PosStatus.DbToJava(rs.getString("status")));
//			if (baBean.getId()==rs.getInt("fk_bestellanforderung")){
//				WartungsTypBean.setBaBean(baBean);
//			}else{
//				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"id der Bestellanforderung ist falsch"));
//			}
//			ArtikelBean artikelBean = getArtikelBeanDB().selectById(rs.getInt("fk_artikel")); 
//			WartungsTypBean.setArtikelBean(artikelBean);
//			WartungsTypBean.setBestellmenge(rs.getInt("bestellmenge"));
//			WartungsTypBean.setLiefertermin(rs.getInt("liefertermin"));
//			WartungsTypBean.setKostenstelle((KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle")));
//			WartungsTypBean.setMengenEinheitBean((MengenEinheitBean)getMengenEinheitBeanDB().selectAnhandID(rs.getInt("fk_mengeneinheit"))); //Achtung! Nicht aus dem Artikelstamm.
//			WartungsTypBean.setKatalogBean((KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog")));
//			WartungsTypBean.setLieferantenbestellnummer(rs.getString("lieferantenbestellnummer"));
//			WartungsTypBean.setKatalogseite(rs.getString("katalogseite"));
//			WartungsTypBean.setKatalogpreis(rs.getDouble("katalogpreis"));
//			WartungsTypBean.setBeanDBStatus(BeanDBStatus.SELECT);
//			resultList.add(WartungsTypBean);
//			Log.log().finest(WartungsTypBean.toString());
////			baBean.addBaPosition(WartungsTypBean);
//		}
//		BeanDBStatus beanDBStatus = baBean.getBeanDBStatus();
//		baBean.setWartungsTypBeans(resultList);
//		baBean.setBeanDBStatus(beanDBStatus);
//		return resultList;
//	}

//	private ArtikelBeanDB getArtikelBeanDB() {
//		if(artikelBeanDB==null){
//			artikelBeanDB = new ArtikelBeanDB();
//		}
//		return artikelBeanDB;
//	}
//
//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}
//
//	private KostenstelleBeanDB getKostenstelleBeanDB() {
//		if(kostenstelleBeanDB==null){
//			kostenstelleBeanDB = new KostenstelleBeanDB();
//		}
//		return kostenstelleBeanDB;
//	}
//
//	private LhBeanDB getLhBeanDB() {
//		if(lhBeanDB==null){
//			lhBeanDB = new LhBeanDB();
//		}
//		return lhBeanDB;
//	}
//
//	private KatalogBeanDB getKatalogBeanDB() {
//		if(katalogBeanDB==null){
//			katalogBeanDB = new KatalogBeanDB();
//		}
//		return katalogBeanDB;
//	}


	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		WartungsTypBean wartungsTypBean = (WartungsTypBean) bean;
		wartungsTypBean.setId(rs.getInt("id"));
		wartungsTypBean.setName(rs.getString("name"));
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//SQL absetzen
		PreparedStatement stmt = getSelectAlleSqlStmt();
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			WartungsTypBean bean = new WartungsTypBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			resultList.add(bean);
			Log.log().finest(bean.toString());
		}
		return resultList;
	}
	
	private PreparedStatement getSelectAlleSqlStmt() throws SQLException {
		if(selectAlleSqlStmt==null){
			selectAlleSqlStmt = (PreparedStatement) 
				LagerConnection.getOneInstance().
					getConnection().prepareStatement(getSelectAlleSQL()); 
		}
		return selectAlleSqlStmt;
	}
	
	private String getSelectAlleSQL() {
		if(selectAlleSQL==null){
			selectAlleSQL = "select id, name from wartungstyp order by name";
		}
		return selectAlleSQL;
	}
	

	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, name from wartungstyp where id = ?";
		}
		return selectAnhandIdSQL;
	}

	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		WartungsTypBean resultBean;
		if (id!=null&&id!=0){
			getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new WartungsTypBean();
					erzeugeBeanAusResultSet(rs,resultBean, 0, null);
					return resultBean;
				}else{
					throw new MengenEinheitBeanDbLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return null;
		}
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}



}