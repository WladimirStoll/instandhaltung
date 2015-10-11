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
import de.keag.lager.core.IBean;
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
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;

/*
 * BestellanforderungsPositionBean für den Datenbank-Zugriff
 */
public class BenutzerZugriffsrechtBeanDB extends BeanDB {

	private ZugriffsrechtBeanDB zugriffsrechtBeanDB;

	public BenutzerZugriffsrechtBeanDB(){
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
					// nix zu tun
				} else {
					throw new LagerException(new Fehler(4));
				}
			} catch (SQLException e) {
				if (e.getSQLState().equals("23000")){
					if (e.getMessage().contains(Konstanten.SQL_ERROR_benutzer_loginName_eindeutig)){
						throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,((BenutzerZugriffsrechtBean)bean).getBenutzer().getLoginName()));
					}
				}
				e.printStackTrace();
			}
		} else {
			throw new LagerException(new Fehler(2));
		}
	}

	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from benutzerzugrifsrechtecht " +
						" where fk_zugrifsrecht = ? and fk_benutzer = ?";
			}
		return deleteSQL;
	}
	

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update benutzerzugrifsrechtecht " +
					" set fk_zugrifsrecht=?, fk_benutzer=? " +
					" where fk_zugrifsrecht=? fk_benutzer = ? ";
		}
		return updateSQL;	}
	
	private void updateBean(BenutzerZugriffsrechtBean bean) throws SQLException, LagerException {
		try{
			if (bean.getZugriffsrecht()==null || bean.getZugriffsrecht().getId()==0){
				getUpdateStmt().setNull(1, java.sql.Types.INTEGER);
				getUpdateStmt().setNull(3, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(1, bean.getZugriffsrecht().getId());
				getUpdateStmt().setInt(3, bean.getZugriffsrecht().getId());
			}

			if (bean.getBenutzer()==null || bean.getBenutzer().getId()==0){
				getUpdateStmt().setNull(2, java.sql.Types.INTEGER);
				getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(2, bean.getBenutzer().getId());
				getUpdateStmt().setInt(4, bean.getBenutzer().getId());
			}

			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into benutzerzugrifsrechtecht " +
					"(fk_zugrifsrecht, fk_benutzer)" +
					" values (?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		BenutzerZugriffsrechtBean benutzerZugriffsrechtBean = (BenutzerZugriffsrechtBean) bean;
		
		if (benutzerZugriffsrechtBean.getZugriffsrecht()==null || benutzerZugriffsrechtBean.getZugriffsrecht().getId()==0){
			getInsertStmt().setNull(1, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(1, benutzerZugriffsrechtBean.getZugriffsrecht().getId());
		}

		if (benutzerZugriffsrechtBean.getBenutzer()==null || benutzerZugriffsrechtBean.getBenutzer().getId()==0){
			getInsertStmt().setNull(2, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(2, benutzerZugriffsrechtBean.getBenutzer().getId());
		}
		
		try{
			getInsertStmt().executeUpdate();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			if (e.getSQLState().equals("23000")){
				if (e.getMessage().contains(Konstanten.SQL_ERROR_bestellanforderungsposition_bestellanforderung_artikel)){
					throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,benutzerZugriffsrechtBean.getBenutzer().getId().toString()));
				}
				if (e.getMessage().contains(Konstanten.SQL_ERROR_fk_abteilung_cannot_be_null)){
					throw new LagerException(new Fehler(53));
				}
				if (e.getMessage().contains(Konstanten.SQL_ERROR_Duplicate_entry)){
					throw new LagerException(new Fehler(54));
				}
				
				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
			}else{
				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
			}
		}
//		beanBaPos.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		BenutzerZugriffsrechtBean benutzerZugriffsrechtBean = (BenutzerZugriffsrechtBean) bean;
		try{
			getDeleteStmt().setInt(1, benutzerZugriffsrechtBean.getZugriffsrecht().getId());
			getDeleteStmt().setInt(2, benutzerZugriffsrechtBean.getBenutzer().getId());
			getDeleteStmt().execute();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
		}
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		Log.log().severe("nicht implemneitert");
	}


	@Override
	protected String selectAnhandIdSQL() {
		// TODO Auto-generated method stub
		Log.log().severe("nicht implemneitert");
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<BenutzerZugriffsrechtBean> sucheAnhandBenutzerBean(BenutzerBean benutzerBean) throws SQLException, LagerException {
		ArrayList<BenutzerZugriffsrechtBean> resultList = new ArrayList<BenutzerZugriffsrechtBean>();
		ResultSet rs;
		//sql bilden
		String sql = "SELECT fk_zugrifsrecht " +
				"FROM benutzerzugrifsrechtecht br left join zugriftsrechtt r " +
				"on br.fk_zugrifsrecht = r.id " +
				"where fk_benutzer = ? " +
				"order by r.name";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, benutzerBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			BenutzerZugriffsrechtBean benutzerZugriffsrechtBean = new BenutzerZugriffsrechtBean();
			benutzerZugriffsrechtBean.setBenutzer(benutzerBean);
			ZugriffsrechtBean zugriffsRechtBean = (ZugriffsrechtBean)getZugriffsrechtBeanDB().selectAnhandID(rs.getInt("fk_zugrifsrecht"), 0, null);
			benutzerZugriffsrechtBean.setZugriffsrecht(zugriffsRechtBean);
			benutzerZugriffsrechtBean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(benutzerZugriffsrechtBean);
			Log.log().finest(benutzerZugriffsrechtBean.toString());
			
//			benutzerZugriffsrechtBean.setArtikelBean(artikelBean);
//			baPosBean.setBestellmenge(rs.getInt("bestellmenge"));
//			baPosBean.setLiefertermin(rs.getInt("liefertermin"));
//			baPosBean.setKostenstelle((KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle")));
//			baPosBean.setMengenEinheitBean((MengenEinheitBean)getMengenEinheitBeanDB().selectAnhandID(rs.getInt("fk_mengeneinheit"))); //Achtung! Nicht aus dem Artikelstamm.
//			baPosBean.setKatalogBean((KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog")));
//			baPosBean.setLieferantenbestellnummer(rs.getString("lieferantenbestellnummer"));
//			baPosBean.setKatalogseite(rs.getString("katalogseite"));
//			baPosBean.setKatalogpreis(rs.getDouble("katalogpreis"));
//			baBean.addBaPosition(baPosBean);
//			if (baBean.getId()==rs.getInt("fk_bestellanforderung")){
//				benutzerZugriffsrechtBean.setBaBean(baBean);
//			}else{
//				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"id der Bestellanforderung ist falsch"));
//			}
		}
		return resultList;
	}

	private ZugriffsrechtBeanDB getZugriffsrechtBeanDB() {
		if (zugriffsrechtBeanDB==null){
			zugriffsrechtBeanDB = new ZugriffsrechtBeanDB();
		}
		return zugriffsrechtBeanDB;
	}
	


//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}



}
