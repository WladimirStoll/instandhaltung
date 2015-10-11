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
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitSuchBean;

public class MengenEinheitBeanDB extends BeanDB{
	private String selectAlleSQL;
	private PreparedStatement selectAlleSqlStmt;
	
	
	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = "select id, name, vordefiniert from mengeneinheit where id=?";
		}
		return selectSQL;
	}
	
	public MengenEinheitBeanDB() {
		super();
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		MengenEinheitBean resultBean;
		if (id!=null&&id!=0){
				getSelectStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectStmt().executeQuery();
				if (rs.next()){
					resultBean = new MengenEinheitBean();
					resultBean.setId(rs.getInt("id"));
					resultBean.setName(rs.getString("name"));
					resultBean.setVordefiniert(rs.getInt("vordefiniert"));
					return resultBean;
				}else{
					throw new MengenEinheitBeanDbLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return null;
		}
	}
	
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		MengenEinheitBean MengenEinheitBean = (MengenEinheitBean)bean;
		if (MengenEinheitBean!=null){
			if (MengenEinheitBean.getBeanStatus() == BeanDBStatus.INSERT){
				insertBean(MengenEinheitBean);
			}else if (MengenEinheitBean.getBeanStatus() == BeanDBStatus.UPDATE){
				updateBean(MengenEinheitBean);
//			}else if (MengenEinheitBean.getBeanStatus() == BeanStatus.DELETE){
//				deleteBean(MengenEinheitBean);
			}else if (MengenEinheitBean.getBeanStatus() == BeanDBStatus.FEHLERHAFT){
				throw new MengenEinheitBeanDbLagerException(new Fehler(4));
			}else{
				throw new MengenEinheitBeanDbLagerException(new Fehler(3));
			}
		}else{
			throw new MengenEinheitBeanDbLagerException(new Fehler(2));
		}
	}

	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		MengenEinheitBean MengenEinheitBean = (MengenEinheitBean) bean;
		getInsertStmt().setInt(1, MengenEinheitBean.getId());
		Log.log().severe("nicht implementiert");
		getInsertStmt().executeUpdate();
	}

	public MengenEinheitBean sucheVordefinierteMengenEinheit() throws SQLException, LagerException{
		ArrayList<Bean> alle = sucheAlle();
		for(Bean b : alle){
			if (((MengenEinheitBean)b).getVordefiniert().equals(MengenEinheitBean.VORDEFINIERT)){
				return (MengenEinheitBean)b;
			}
		}
		return new MengenEinheitBean(); //nix gefunden, keine Einheit ist vordefiniert
	}
	
	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException{ 
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//SQL absetzen
		PreparedStatement stmt = getSelectAlleSqlStmt();
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			MengenEinheitBean bean = new MengenEinheitBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			resultList.add(bean);
			Log.log().finest(bean.toString());
		}
		return resultList;
	}
	
	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		MengenEinheitBean mengenEinheitBean = (MengenEinheitBean) bean; 
		mengenEinheitBean.setId(rs.getInt("id"));
		mengenEinheitBean.setName(rs.getString("name"));
		mengenEinheitBean.setVordefiniert(rs.getInt("vordefiniert"));
		
		mengenEinheitBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB){
		Log.log().severe("nicht implementiert");
		return null;
	}

	private String getSelectAlleSQL() {
		if(selectAlleSQL==null){
			selectAlleSQL = "select id, name, vordefiniert from mengeneinheit order by name";
		}
		return selectAlleSQL;
	}

	private PreparedStatement getSelectAlleSqlStmt() throws SQLException {
		if(selectAlleSqlStmt==null){
			selectAlleSqlStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getSelectAlleSQL()); 
		}
		return selectAlleSqlStmt;
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
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
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		
	}

}
