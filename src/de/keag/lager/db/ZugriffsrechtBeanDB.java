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
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBeanDbLagerException;


public class ZugriffsrechtBeanDB extends BeanDB{
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
			selectAnhandIdSQL = "select id, name from zugriftsrechtt where id=?";
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

	public ZugriffsrechtBeanDB() {
		super();
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
//	public AbteilungBean sucheAnhandID(Integer id) throws BenutzerBeanDbLagerException, SQLException{
		ZugriffsrechtBean resultBean;
		if (id!=null&&id!=0){
			getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new ZugriffsrechtBean();
					resultBean.setId(rs.getInt("id"));
		    	 	resultBean.setZugriffsrechtName(rs.getString("name"));
		//			resultBean.setVorname(rs.getString("vorname"));
		//			resultBean.setLoginName(rs.getString("loginName"));
		//			resultBean.setPassword(rs.getString("password"));
					return resultBean;
				}else{
					throw new ZugriffsrechtBeanDbLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
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
				throw new ZugriffsrechtBeanDbLagerException(new Fehler(4));
			}else{
				throw new ZugriffsrechtBeanDbLagerException(new Fehler(3));
			}
		}else{
			throw new ZugriffsrechtBeanDbLagerException(new Fehler(2));
		}
	}

	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		ZugriffsrechtBean zugriftsrechttBean = (ZugriffsrechtBean) bean; 
		getInsertStmt().setInt(1, zugriftsrechttBean.getId());
		getInsertStmt().setString(1, zugriftsrechttBean.getZugriffsrechtName());
	//	getInsertStmt().setString(2, benutzerBean.getVorname());
	//	getInsertStmt().setString(3, benutzerBean.getLoginName());
	//	getInsertStmt().setString(4, benutzerBean.getPassword());
		getInsertStmt().executeUpdate();
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException{ 
//	public ArrayList<ZugriffsrechtBean> sucheAlle() throws SQLException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name " +
				" from zugriftsrechtt order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			ZugriffsrechtBean bean = new ZugriffsrechtBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws  SQLException, LagerException {
//	private void erzeugeBeanAusResultSet(ResultSet rs, ZugriffsrechtBean bean) throws SQLException{
		ZugriffsrechtBean zugriffsrechtBean = (ZugriffsrechtBean) bean;
		zugriffsrechtBean.setId(rs.getInt("id"));
		zugriffsrechtBean.setZugriffsrechtName(rs.getString("name"));
		zugriffsrechtBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
//	public ArrayList<ZugriffsrechtBean> sucheAnhandSuchBean(AbteilungSuchBean suchKriterien) {
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
