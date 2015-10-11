package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosSuchBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungStatus;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.katalog.KatalogSuchBean;

public class KatalogBeanDB extends BeanDB{
	
	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, name from katalog where id=? ";
		}
		return selectAnhandIdSQL;
	}
	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
			updateSQL = " update katalog " +
						" set " +
						" name = ?  "+
						" where id = ?";
			}
			return updateSQL;	
	}
	
	public KatalogBeanDB() {
		super();
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		KatalogBean resultBean;
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new KatalogBean();
					erzeugeBeanAusResultSet(rs, resultBean, 0, null);
					return resultBean;
				}else{
					throw new KatalogBeanDbLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return null;
		}
	}
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		KatalogBean KatalogBean = (KatalogBean) bean;
		if (KatalogBean!=null){
			if (KatalogBean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(KatalogBean);
			}else if (KatalogBean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(KatalogBean);
			}else if (KatalogBean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(KatalogBean);
			}else if (KatalogBean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT){
				throw new KatalogBeanDbLagerException(new Fehler(4));
			}else{
				throw new KatalogBeanDbLagerException(new Fehler(3));
			}
		}else{
			throw new KatalogBeanDbLagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		KatalogBean katalogBean = (KatalogBean) bean;
		getDeleteStmt().setInt(1, katalogBean.getId());
		getDeleteStmt().execute();
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		KatalogBean katalogBean = (KatalogBean) bean;
		try{
			getUpdateStmt().setString(1, katalogBean.getName());
			getUpdateStmt().setInt(2, katalogBean.getId());
			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into katalog " +
					"(id, " +
					" name)" +
					" values (?,?)";
		}
		return insertSQL;
	}
	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		KatalogBean katalogBean = (KatalogBean) bean;
		getInsertStmt().setInt(1, katalogBean.getId());
		getInsertStmt().setString(2, katalogBean.getName());
		try{
			getInsertStmt().execute();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			if (e.getSQLState().equals("23000")){
					throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,katalogBean.getId().toString()));
			}
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name" +
				" from katalog order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			KatalogBean bean = new KatalogBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)	throws SQLException, LagerException {
		KatalogBean katalogBean = (KatalogBean) bean;
		katalogBean.setId(rs.getInt("id"));
		katalogBean.setName(rs.getString("name"));
		katalogBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name " +
				"from katalog where name like '%"+((KatalogSuchBean)iSuchBean).getName()+"%' " +
				" order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
//		stmt.setString(1, ((KatalogSuchBean)iSuchBean).getName());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			KatalogBean katalogBean = new KatalogBean();
			erzeugeBeanAusResultSet(rs, katalogBean, 0, null);
			Log.log().finest(katalogBean.toString());
			resultList.add(katalogBean);
		}
		return resultList;
	}

	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from katalog " +
						" where id = ? ";
			}
		return deleteSQL;
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

}
