package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;


import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.LoginLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhSuchBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

public class LhBeanDB extends BeanDB{
	private String selectBySuchBeanSQL;
	
	private PreparedStatement selectBySuchBeanStmt;
	private PreparedStatement selectByNameStmt;
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into herstellerlieferant" +
					" (id, " +
					"name, " +
					"adresse_land, " +
					"adresse_plz, " +
					"adresse_stadt, " +
					"adresse_strasse, " +
					"telefon, " +
					"fax, " +
					"email, " +
					"ansprechpartner) " +
					"values " +
					"(?,?,?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}


	public LhBeanDB(){
		super();
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException{
		if (bean!=null){
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.INSERT_DELETE){
				//Objekt muss nicht gelöscht werden.
			}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
				//Objekt muss nicht gelöscht werden.
			}else{
				throw new LoginLagerException(new Fehler(4));
			}
		}else{
			throw new LoginLagerException(new Fehler(2));
		}
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name, adresse_land, adresse_plz, adresse_stadt, adresse_strasse, telefon, fax, email, ansprechpartner" +
				" from herstellerlieferant order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			LhBean lhBean = new LhBean();
			erzeugeBeanAusResultSet(rs,lhBean, 0, null );		
//			lhBean.setId(rs.getInt("id"));
//			lhBean.setName(rs.getString("name")); 
//			lhBean.setAdresse_land(rs.getString("adresse_land")); 
//			lhBean.setAdresse_plz(rs.getString("adresse_plz"));
//			lhBean.setAdresse_stadt(rs.getString("adresse_stadt")); 
//			lhBean.setAdresse_strasse(rs.getString("adresse_strasse"));
//			lhBean.setTelefon(rs.getString("telefon"));
//			lhBean.setFax(rs.getString("fax"));
//			lhBean.setEmail(rs.getString("email"));
//			lhBean.setAnsprechpartner(rs.getString("ansprechpartner"));
			Log.log().finest(lhBean.toString());
			resultList.add(lhBean);
		}
		return resultList;
	}
	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					LhBean lhBean = new LhBean();
					erzeugeBeanAusResultSet(rs, lhBean, depthLevel, null);
					return lhBean;
				}else{
					throw new HerstellerLieferantLagerException(new Fehler(23,FehlerTyp.FEHLER,id.toString()));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return new LhBean();
		}
	}
	
//	private LhBean getNewLhBean(java.sql.ResultSet rs) throws SQLException, LagerException {
//		LhBean resultBean = new LhBean();
//		erzeugeBeanAusResultSet(rs,resultBean);		
//		resultBean.setId(rs.getInt("id"));
//		resultBean.setName(rs.getString("name"));
//		resultBean.setAdresse_land(rs.getString("adresse_land")); 
//		resultBean.setAdresse_plz(rs.getString("adresse_plz"));
//		resultBean.setAdresse_stadt(rs.getString("adresse_stadt"));
//		resultBean.setAdresse_strasse(rs.getString("adresse_strasse"));
//		resultBean.setTelefon(rs.getString("telefon"));
//		resultBean.setFax(rs.getString("fax"));
//		resultBean.setEmail(rs.getString("email"));
//		resultBean.setAnsprechpartner(rs.getString("ansprechpartner"));
//		return resultBean;
//	}

	private PreparedStatement getSelectByNameStmt() throws SQLException  {
		if(selectByNameStmt==null){
			selectByNameStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getSelectByNameSQL());			
		}
		return selectByNameStmt;
	}
	
	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, name, adresse_land, adresse_plz, " +
					"adresse_stadt, adresse_strasse, telefon, fax, email, " +
					"ansprechpartner from herstellerlieferant where id=?";
		}
		return selectAnhandIdSQL;
		
	}	
	
	private String getSelectByNameSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = 
				" select  " +
				" id, " +
				" name," +
				" adresse_land," +
				" adresse_plz," +
				" adresse_stadt," +
				" adresse_strasse," +
				" telefon," +
				" fax," +
				" email," +
				" ansprechpartner" + 
				" from herstellerlieferant " +
				" where name like ? " +
				" order by name";
		}
		return selectAnhandIdSQL;
	}

	public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException{
		if (lhName!=null && !lhName.equals("")){
			getSelectByNameStmt().setString(1, lhName);
			java.sql.ResultSet rs = getSelectByNameStmt().executeQuery();
			if (rs.next()){
				LhBean lhBean = new LhBean();
				erzeugeBeanAusResultSet(rs, lhBean, 0, null);
				return lhBean;
			}else{
				throw new HerstellerLieferantLagerException(new Fehler(26,FehlerTyp.FEHLER,lhName));//Benutzer kann nicht gelesen werden.
			}
	}else{
		return null;
	}
}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		getDeleteStmt().setInt(1, ((LhBean)bean).getId());
		getDeleteStmt().executeUpdate();
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		LhBean lhBean = (LhBean)bean;
		lhBean.setId(rs.getInt("id"));
		lhBean.setName(rs.getString("name")); 
		lhBean.setAdresse_land(rs.getString("adresse_land")); 
		lhBean.setAdresse_plz(rs.getString("adresse_plz"));
		lhBean.setAdresse_stadt(rs.getString("adresse_stadt")); 
		lhBean.setAdresse_strasse(rs.getString("adresse_strasse"));
		lhBean.setTelefon(rs.getString("telefon"));
		lhBean.setFax(rs.getString("fax"));
		lhBean.setEmail(rs.getString("email"));
		lhBean.setAnsprechpartner(rs.getString("ansprechpartner"));
		lhBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = " delete from herstellerlieferant " +
					" where id = ? ";
		}
		return deleteSQL;
	}


	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getUpdateSQL() {
		if (updateSQL == null){
			updateSQL = "update herstellerlieferant set " +
			" name  = ?, " +
			" adresse_land = ?, " +
					" adresse_plz = ?, " +
					" adresse_stadt = ?, " +
					" adresse_strasse = ?, " +
					" telefon = ?, " +
					" fax = ?, " +
					" email = ?, " +
					" ansprechpartner = ? " +
					" where id = ?";
		}
		return updateSQL;
	}

	/**
	 * id, name, adresse_land, adresse_plz, 
	 * adresse_stadt, adresse_strasse, 
	 * telefon, fax, email, ansprechpartner
	 */
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		LhBean lhBean = (LhBean) bean;
		getInsertStmt().setInt(1, (lhBean.getId()));
		getInsertStmt().setString(2, lhBean.getName());
		getInsertStmt().setString(3, lhBean.getAdresse_land());
		getInsertStmt().setString(4, lhBean.getAdresse_plz());
		getInsertStmt().setString(5, lhBean.getAdresse_stadt());
		getInsertStmt().setString(6, lhBean.getAdresse_strasse());
		getInsertStmt().setString(7, lhBean.getTelefon());
		getInsertStmt().setString(8, lhBean.getFax());
		getInsertStmt().setString(9, lhBean.getEmail());
		getInsertStmt().setString(10, lhBean.getAnsprechpartner());
		try{
			getInsertStmt().executeUpdate();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			if (e.getSQLState().equals("23000")){
				if (e.getMessage().contains(Konstanten.SQL_ERROR_Duplicate_entry)){
					throw new LagerException(new Fehler(50,FehlerTyp.FEHLER,((BenutzerBean)bean).getLoginName().toString()));
				}
			}
			throw e;
		}
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}


	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		getSelectBySuchBeanStmt().setString(1, "%"+((LhSuchBean)iSuchBean).getName()+"%");
		ResultSet rs = getSelectBySuchBeanStmt().executeQuery();
		while(rs.next()){
			LhBean lhBean = new LhBean();
			erzeugeBeanAusResultSet(rs, lhBean, 0, null );
			resultList.add(lhBean);
			Log.log().finest(lhBean .toString());
		}
		return resultList;
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		LhBean lhBean = (LhBean)bean;
		getUpdateStmt().setString(1, lhBean.getName());
		getUpdateStmt().setString(2, lhBean.getAdresse_land());
		getUpdateStmt().setString(3, lhBean.getAdresse_plz());
		getUpdateStmt().setString(4, lhBean.getAdresse_stadt());
		getUpdateStmt().setString(5, lhBean.getAdresse_strasse());
		getUpdateStmt().setString(6, lhBean.getTelefon());
		getUpdateStmt().setString(7, lhBean.getFax());
		getUpdateStmt().setString(8, lhBean.getEmail());
		getUpdateStmt().setString(9, lhBean.getAnsprechpartner());
		getUpdateStmt().setInt(10, lhBean.getId());
		getUpdateStmt().execute();
	}

	private String getSelectBySuchBeanSQL() {
		if (selectBySuchBeanSQL==null){
			selectBySuchBeanSQL = " select " +
					" id, " +
					" name, " +
					" adresse_land, " +
					" adresse_plz, " +
					" adresse_stadt, " +
					" adresse_strasse, " +
					" telefon, " +
					" fax, " +
					" email, " +
					" ansprechpartner from herstellerlieferant " +
					" where name like ? " +
					" order by name ";
		}
		return selectBySuchBeanSQL;
	}

	private PreparedStatement getSelectBySuchBeanStmt() throws SQLException {
		if(selectBySuchBeanStmt==null){
			selectBySuchBeanStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getSelectBySuchBeanSQL());			
		}
		return selectBySuchBeanStmt;
	}

}
