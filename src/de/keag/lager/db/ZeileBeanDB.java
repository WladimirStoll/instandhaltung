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
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class ZeileBeanDB extends BeanDB {

	private HalleBeanDB halleBeanDB;
	private AbteilungBeanDB abteilungBeanDB;
	private EtageBeanDB etageBeanDB;
	private SaeuleBeanDB saeuleBeanDB;
	private String nachsteZeileNrSql;
	private PreparedStatement nachsteZeileNrStmt;
	

	public ZeileBeanDB(){
		super();
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean != null) {
			if (bean.pruefeEigeneDaten().size() > 0) {
				throw new LagerException(bean.pruefeEigeneDaten().get(0));
			}
			try {
				if (bean.getBeanDBStatus() == BeanDBStatus.INSERT) {
					insertBean(bean);
				} else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE) {
					updateBean(bean);
				} else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE) {
					deleteBean(bean);
				} else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT) {
					speichereBean(bean);
				} else {
					throw new LagerException(new Fehler(4));
				}
			} catch (SQLException e) {
				throw e;
			}
		} else {
			throw new LagerException(new Fehler(2));
		}
	}

	//Speichere alle objekte in der Zeile. In diesem Fall nur die Säulen
	private void speichereBean(Bean bean) throws SQLException, LagerException {
		speichereSaeulen(bean,BeanDBStatus.FEHLERHAFT);
	}

	/**
	 * @param bean
	 * @throws SQLException
	 * @throws LagerException
	 */
	private void speichereSaeulen(Bean bean, BeanDBStatus beanDBStatus) throws SQLException, LagerException {
		ZeileBean zeileBean = (ZeileBean) bean;
		for(int i = 0; i<zeileBean.getSaeuleBeans().size();i++){
			SaeuleBean saeuleBean = zeileBean.getSaeuleBeans().get(i);
			if (BeanDBStatus.DELETE.equals(beanDBStatus)){
				saeuleBean.setBeanDBStatus(BeanDBStatus.DELETE);
			}
			getSaeuleBeanDB().saveBean(saeuleBean);
		}
	}

	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from zeile " +
					" where id = ? ";
			}
		return deleteSQL;
	}
	

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update zeile " +
					" set nummer=?, fk_halle=?, fk_etage=?, fk_abteilung=? " +
					" where id=? ";
		}
		return updateSQL;	
	}
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		ZeileBean zeileBean = (ZeileBean)bean;
		getUpdateStmt().setInt(1, zeileBean.getNummer());
		getUpdateStmt().setInt(2, zeileBean.getHalleBean().getId());
		getUpdateStmt().setInt(3, zeileBean.getEtageBean().getId());
		getUpdateStmt().setInt(4, zeileBean.getAbteilungBean().getId());
		getUpdateStmt().setInt(5, zeileBean.getId());
		getUpdateStmt().execute();
		
		//Etagen und ZEilen werden gespeichert
		speichereSaeulen(zeileBean,BeanDBStatus.FEHLERHAFT);
	}
	
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into zeile " +
					"(id, nummer, fk_halle, fk_etage, fk_abteilung)" +
					" values (?,?,?,?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		ZeileBean zeileBean = (ZeileBean) bean;
		getInsertStmt().setInt(1, zeileBean.getId());
		getInsertStmt().setInt(2, zeileBean.getNummer());
		getInsertStmt().setInt(3, zeileBean.getHalleBean().getId());
		if (zeileBean.getEtageBean().getId().equals(0)){
			getInsertStmt().setNull(4, java.sql.Types.INTEGER );
		}else{
			getInsertStmt().setInt(4, zeileBean.getEtageBean().getId());
		}
		if (zeileBean.getAbteilungBean().getId().equals(0)){
			getInsertStmt().setNull(5, java.sql.Types.INTEGER );
		}else{
			getInsertStmt().setInt(5, zeileBean.getAbteilungBean().getId());
		}
		getInsertStmt().executeUpdate();

		//Etagen und Zeilen werden gespeichert
		speichereSaeulen(zeileBean,BeanDBStatus.OHNE_STATUS_ANGABE);
	}

	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		ZeileBean zeileBean = (ZeileBean) bean;
		speichereSaeulen(zeileBean,BeanDBStatus.DELETE);
		getDeleteStmt().setInt(1,zeileBean.getId());
		getDeleteStmt().execute();
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		ZeileBean zeileBean = (ZeileBean) bean;
		zeileBean.setId(rs.getInt("id"));
		zeileBean.setNummer(rs.getInt("nummer"));
		zeileBean.setHalleBean((HalleBean)getHalleBeanDB().selectAnhandID(rs.getInt("fk_halle"), depthLevel+1, null));
		zeileBean.setAbteilungBean((AbteilungBean)getAbteilungBeanDB().selectAnhandID(rs.getInt("fk_abteilung"), depthLevel+1, null));
		zeileBean.setEtageBean((EtageBean)getEtageBeanDB().selectAnhandID(rs.getInt("fk_etage"), depthLevel+1, null));
		zeileBean.setSaeuleBeans(getSaeuleBeanDB().sucheAnhandZeileBean(zeileBean, depthLevel+1));
		zeileBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}


	@Override
	protected String selectAnhandIdSQL() {
		if (selectAnhandIdSQL == null) {
			selectAnhandIdSQL = "select id, nummer, fk_halle, fk_etage, fk_abteilung from zeile where id = ?";
		}
		return selectAnhandIdSQL;
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implemneitert");
		return null;
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implemneitert");
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		return new ArrayList<Bean> ();
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		ZeileBean resultBean;
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new ZeileBean();
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
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<ZeileBean> sucheAnhandHalleBean(HalleBean halleBean, int depthLevel) throws SQLException, LagerException {
		ArrayList<ZeileBean> resultList = new ArrayList<ZeileBean>();
		BeanDBStatus halleStatusAlt;
		ResultSet rs;
		getSqlParams().clear();
		//sql bilden
		String sql = " SELECT id, nummer, fk_halle, fk_etage, fk_abteilung FROM zeile " +
				" where fk_halle = ? " +
				" order by nummer";
		getSqlParams().put("fk_halle",halleBean.getId());
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		//Params setzen
		getSqlParams().setParams(stmt);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			ZeileBean zeileBean = new ZeileBean();
			erzeugeBeanAusResultSet(rs, zeileBean, depthLevel, null);
			resultList.add(zeileBean);
			Log.log().finest(zeileBean.toString());
			
		}
		//neue ZeilenWErte in die Halle aufnehmen. Dabei wird der Status beibehalten.
		halleStatusAlt = halleBean.getBeanDBStatus();
		halleBean.setZeileBeans(resultList);
		halleBean.setBeanDBStatus(halleStatusAlt);
		return resultList;
	}

	private HalleBeanDB getHalleBeanDB() {
		if (halleBeanDB==null){
			halleBeanDB = new HalleBeanDB();
		}
		return halleBeanDB;
	}

	private AbteilungBeanDB getAbteilungBeanDB() {
		if (abteilungBeanDB==null){
			abteilungBeanDB = new AbteilungBeanDB();
		}
		return abteilungBeanDB;
	}

	private EtageBeanDB getEtageBeanDB() {
		if (etageBeanDB==null){
			etageBeanDB = new EtageBeanDB();
		}
		return etageBeanDB;
	}

	private SaeuleBeanDB getSaeuleBeanDB() {
		if (saeuleBeanDB==null){
			saeuleBeanDB = new SaeuleBeanDB();
		}
		return saeuleBeanDB;
	}

	public Integer getNachsteNummer(HalleBean halleBean) throws SQLException {
		getNachsteZeileNrStmt().setInt(1, halleBean.getId());
		java.sql.ResultSet rs = getNachsteZeileNrStmt().executeQuery();
		rs.next();
		Integer posNummer = rs.getInt("naechstePosNummer");//rs.getInt("naechstePosNummer");
		if (posNummer.equals(0)){
			posNummer = 1;
		}
		return posNummer;
	}

	protected String getNachsteZeileNrSql() {
		if (nachsteZeileNrSql==null){
			nachsteZeileNrSql = "select max(nummer)+1 as naechstePosNummer from zeile z " +
					"where z.fk_halle = ?";
		}
		return nachsteZeileNrSql;
	}

	protected PreparedStatement getNachsteZeileNrStmt() throws SQLException {
		if (nachsteZeileNrStmt == null) {
			nachsteZeileNrStmt = LagerConnection.getOneInstance()
					.getConnection().prepareStatement(getNachsteZeileNrSql());						
		}
		return nachsteZeileNrStmt;
	}

//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}



}
