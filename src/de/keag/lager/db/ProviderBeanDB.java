package de.keag.lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.LoginLagerException;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.login.LoginBean;

public class ProviderBeanDB extends BeanDB{
	private String selectByLoginNameSQL;
	private PreparedStatement selectByLoginNameStmt;
	
	private String getSelectByLoginNameSQL() {
		if(selectByLoginNameSQL==null){
			selectByLoginNameSQL = "select id, name, vorname, loginName, password from benutzer where loginName=?";
		}
		return selectByLoginNameSQL;
	}

	public ProviderBeanDB(){
		super();
	}

	public LoginBean selectByLoginName(LoginBean benutzerBean) throws LoginLagerException, SQLException{
		LoginBean resultBean;
		if (benutzerBean!=null){
			if (benutzerBean.getBeanStatus() == BeanDBStatus.SELECT){
				getSelectByLoginNameStmt().setString(1, benutzerBean.getLoginName());
				java.sql.ResultSet rs = getSelectByLoginNameStmt().executeQuery();
				if (rs.next()){
					resultBean = new LoginBean();
					resultBean.setId(rs.getInt("id"));
					resultBean.setName(rs.getString("name"));
					resultBean.setVorname(rs.getString("vorname"));
					resultBean.setLoginName(rs.getString("loginName"));
					resultBean.setPassword(rs.getString("password"));
					return resultBean;
				}else{
					throw new LoginLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
			}else{
				throw new LoginLagerException(new Fehler(5));
			}
		}else{
			throw new LoginLagerException(new Fehler(2));
		}
	}
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		LoginBean benutzerBean = (LoginBean) bean;
		if (benutzerBean!=null){
			if (benutzerBean.getBeanStatus() == BeanDBStatus.INSERT){
				insertBean(benutzerBean);
			}else if (benutzerBean.getBeanStatus() == BeanDBStatus.UPDATE){
				updateBean(benutzerBean);
//			}else if (benutzerBean.getBeanStatus() == BeanStatus.DELETE){
//				deleteBean(benutzerBean);
			}else if (benutzerBean.getBeanStatus() == BeanDBStatus.FEHLERHAFT){
				throw new LoginLagerException(new Fehler(4));
			}else{
				throw new LoginLagerException(new Fehler(3));
			}
		}else{
			throw new LoginLagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		throw new LagerException(new Fehler(4));
	}
	
	private PreparedStatement getSelectByLoginNameStmt() throws SQLException  {
		if(selectByLoginNameStmt==null){
			selectByLoginNameStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getSelectByLoginNameSQL());			
		}
		return selectByLoginNameStmt;
	}

	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		LoginBean benutzerBean = (LoginBean)bean;
		getInsertStmt().setString(1, benutzerBean.getName());
		getInsertStmt().setString(2, benutzerBean.getVorname());
		getInsertStmt().setString(3, benutzerBean.getLoginName());
		getInsertStmt().setString(4, benutzerBean.getPassword());
		getInsertStmt().executeUpdate();
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}

	@Override
	protected String getDeleteSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getInsertSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getUpdateSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String selectAnhandIdSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
}
