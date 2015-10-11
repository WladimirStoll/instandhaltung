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
