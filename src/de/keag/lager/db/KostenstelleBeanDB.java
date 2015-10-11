package de.keag.lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;

public class KostenstelleBeanDB extends BeanDB{
	
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
			selectAnhandIdSQL = "select id, name, nummer from kostenstelle where id=?";
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

	public KostenstelleBeanDB() {
		super();
	}

	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		KostenstelleBean resultBean;
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new KostenstelleBean();
					erzeugeBeanAusResultSet(rs, resultBean, depthLevel, null);
					return resultBean;
				}else{
					throw new KostenstelleBeanDbLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
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
//			}else if (KostenstelleBean.getBeanStatus() == BeanStatus.DELETE){
//				deleteBean(KostenstelleBean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT){
				throw new KostenstelleBeanDbLagerException(new Fehler(4));
			}else{
				throw new KostenstelleBeanDbLagerException(new Fehler(3));
			}
		}else{
			throw new KostenstelleBeanDbLagerException(new Fehler(2));
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
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		getInsertStmt().setInt(1, ((KostenstelleBean)bean).getId());
		Log.log().severe("nicht implementiert");
		getInsertStmt().executeUpdate();
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException{
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name, nummer " +
				" from kostenstelle order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			KostenstelleBean bean = new KostenstelleBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)	throws SQLException, LagerException {
		KostenstelleBean kostenstelleBean = (KostenstelleBean) bean;
		kostenstelleBean.setId(rs.getInt("id"));
		kostenstelleBean.setName(rs.getString("name")); 
		kostenstelleBean.setNummer(rs.getString("nummer"));
		kostenstelleBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	public KostenstelleBean getAnhandName(String name) {
		return null;
	}






	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}





	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		// TODO Auto-generated method stub
		Log.log().severe("nicht implementiert");
		return null;
	}

	

}
