package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.katalog.KatalogBean;

public class KatalogBeanDB extends BeanDB{
	
	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, name from katalog where id=?";
		}
		return selectAnhandIdSQL;
	}
	
	@Override
	protected String getUpdateSQL() {
		return "";
	}
	
	public KatalogBeanDB() {
		super();
	}

	@Override
	public Bean selectAnhandID(Integer id) throws SQLException, LagerException {
		KatalogBean resultBean;
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new KatalogBean();
					erzeugeBeanAusResultSet(rs, resultBean);
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
//			}else if (KatalogBean.getBeanStatus() == BeanStatus.DELETE){
//				deleteBean(KatalogBean);
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
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "???";
		}
		return insertSQL;
	}
	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		KatalogBean katalogBean = (KatalogBean) bean;
		getInsertStmt().setInt(1, katalogBean.getId());
		Log.log().severe("nicht implementiert");
		getInsertStmt().executeUpdate();
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
			erzeugeBeanAusResultSet(rs, bean);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean)	throws SQLException, LagerException {
		KatalogBean katalogBean = (KatalogBean) bean;
		katalogBean.setId(rs.getInt("id"));
		katalogBean.setName(rs.getString("name"));
		katalogBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean)throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		return resultList;
	}

	@Override
	protected String getDeleteSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

}
