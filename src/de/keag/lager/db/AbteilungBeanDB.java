package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.BenutzerBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.LoginLagerException;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.abteilung.AbteilungBeanDbLagerException;
import de.keag.lager.panels.frame.abteilung.AbteilungSuchBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;


public class AbteilungBeanDB extends BeanDB{
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "???";
		}
		return insertSQL;
	}

	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, name from abteilung where id=?";
		}
		return selectAnhandIdSQL;
	}
	
	@Override
	protected String getUpdateSQL() {
		return "";
	}

	@Override
	protected String getDeleteSQL() {
		return "";
	}

	public AbteilungBeanDB() {
		super();
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
//	public AbteilungBean sucheAnhandID(Integer id) throws BenutzerBeanDbLagerException, SQLException{
		AbteilungBean resultBean;
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return null;
			}
		}
			
		if (id!=null&&id!=0){
			getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new AbteilungBean();
					resultBean.setId(rs.getInt("id"));
		    	 	resultBean.setAbteilungName(rs.getString("name"));
		//			resultBean.setVorname(rs.getString("vorname"));
		//			resultBean.setLoginName(rs.getString("loginName"));
		//			resultBean.setPassword(rs.getString("password"));
					return resultBean;
				}else{
					throw new BenutzerBeanDbLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return null;
		}
	}
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean!=null){
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(bean);
    	 	}else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT){
				throw new AbteilungBeanDbLagerException(new Fehler(4));
			}else{
				throw new AbteilungBeanDbLagerException(new Fehler(3));
			}
		}else{
			throw new AbteilungBeanDbLagerException(new Fehler(2));
		}
	}

	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		AbteilungBean abteilungBean = (AbteilungBean) bean; 
		getInsertStmt().setInt(1, abteilungBean.getId());
		getInsertStmt().setString(1, abteilungBean.getAbteilungName());
	//	getInsertStmt().setString(2, benutzerBean.getVorname());
	//	getInsertStmt().setString(3, benutzerBean.getLoginName());
	//	getInsertStmt().setString(4, benutzerBean.getPassword());
		getInsertStmt().executeUpdate();
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException{ 
//	public ArrayList<AbteilungBean> sucheAlle() throws SQLException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name " +
				" from abteilung order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			AbteilungBean bean = new AbteilungBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws  SQLException, LagerException {
//	private void erzeugeBeanAusResultSet(ResultSet rs, AbteilungBean bean) throws SQLException{
		AbteilungBean abteilungBean = (AbteilungBean) bean;
		abteilungBean.setId(rs.getInt("id"));
		abteilungBean.setAbteilungName(rs.getString("name"));
		abteilungBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
//	public ArrayList<AbteilungBean> sucheAnhandSuchBean(AbteilungSuchBean suchKriterien) {
		Log.log().severe("nicht implementiert");
		return null;
	}


	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return "";
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
}
