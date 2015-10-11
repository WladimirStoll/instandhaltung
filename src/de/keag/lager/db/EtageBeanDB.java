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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class EtageBeanDB extends BeanDB {
	
	private HalleBeanDB halleBeanDB;

	public EtageBeanDB(){
		super();
	}
	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, name, fk_halle from etage where id=?";
		}
		return selectAnhandIdSQL;
	}
	
	@Override
	protected String getUpdateSQL() {
		return "update etage set " +
			" id  = ?, " +
			" name = ?, " +
			" fk_halle = ? " +
			" where id = ? ";
	}
	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		EtageBean resultBean;
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new EtageBean();
					erzeugeBeanAusResultSet(rs, resultBean, depthLevel, null);
					return resultBean;
				}else{
					throw new LagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return null;
		}
	}
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		EtageBean EtageBean = (EtageBean) bean;
		if (EtageBean!=null){
			if (EtageBean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(EtageBean);
			}else if (EtageBean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(EtageBean);
			}else if (EtageBean.getBeanDBStatus() == BeanDBStatus.SELECT){
//				speichereBean(EtageBean);
			}else if (EtageBean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(EtageBean);
			}else if (EtageBean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT){
				throw new LagerException(new Fehler(4));
			}else{
				throw new LagerException(new Fehler(3));
			}
		}else{
			throw new LagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		EtageBean etageBean = (EtageBean)bean;
		getDeleteStmt().setInt(1, etageBean.getId());
		getDeleteStmt().execute();
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		EtageBean etageBean = (EtageBean)bean;
//		getUpdateStmt().setDate(1, bean.getAktuellesDatum());
//		getUpdateStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
		getUpdateStmt().setInt(1, etageBean.getId());
		getUpdateStmt().setString(2, etageBean.getName());
		getUpdateStmt().setInt(3, etageBean.getHalleBean().getId());
		getUpdateStmt().execute();
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into etage " +
					"(id, name, fk_halle)" +
					" values (?,?,?)";
		}
		return insertSQL;
	}
	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		EtageBean etageBean = (EtageBean) bean;
		getInsertStmt().setInt(1, etageBean.getId());
		getInsertStmt().setString(2, etageBean.getName());
		getInsertStmt().setInt(3, etageBean.getHalleBean().getId());
		getInsertStmt().executeUpdate();
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name, fk_halle" +
				" from etage order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			EtageBean bean = new EtageBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)	throws SQLException, LagerException {
		EtageBean etageBean = (EtageBean) bean;
		etageBean.setId(rs.getInt("id"));
		etageBean.setName(rs.getString("name"));
		etageBean.setHalleBean((HalleBean)getHalleBeanDB().selectAnhandID(rs.getInt("fk_halle"), depthLevel+1, depthLevelArrayDB));
		etageBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)throws SQLException, LagerException {
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
	public ArrayList<EtageBean> sucheAnhandHalleBean(HalleBean halleBean, int depthLevel) throws SQLException, LagerException {
		ArrayList<EtageBean> resultList = new ArrayList<EtageBean>();
		BeanDBStatus halleStatusAlt;
		getSqlParams().clear();
		ResultSet rs;
		//sql bilden
		String sql = " SELECT id, name, fk_halle FROM etage " +
				"where fk_halle = ? " + 
				"order by name";
		getSqlParams().put("fk_halle",halleBean.getId());
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		//Parameter setzen
		getSqlParams().setParams(stmt);
		
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			EtageBean etageBean = new EtageBean();
//			zeileBean.setBenutzer(halleBean);
//			ZugriffsrechtBean zugriffsRechtBean = (ZugriffsrechtBean)getZugriffsrechtBeanDB().selectAnhandID(rs.getInt("fk_zugrifsrecht"));
//			benutzerZugriffsrechtBean.setZugriffsrecht(zugriffsRechtBean);
			erzeugeBeanAusResultSet(rs, etageBean, depthLevel, null);
			etageBean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(etageBean);
			Log.log().finest(etageBean.toString());
		}
		halleStatusAlt = halleBean.getBeanDBStatus();
		halleBean.setEtageBeans(resultList);
		halleBean.setBeanDBStatus(halleStatusAlt);
		return resultList;
	}
	private HalleBeanDB getHalleBeanDB() {
		if (halleBeanDB==null){
			halleBeanDB = new HalleBeanDB();
		}
		return halleBeanDB;
	}


}
