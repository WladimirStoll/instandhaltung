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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;

/*
 * BestellanforderungsPositionBean für den Datenbank-Zugriff
 */
public class BaugruppeArtikelBeanDB extends BeanDB {

	private ArtikelBeanDB artikelBeanDB;
	private BaugruppeBeanDB baugruppeBeanDB;

	public BaugruppeArtikelBeanDB(){
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
					if (e.getMessage().contains(Konstanten.SQL_ERROR_bestellanforderungsposition_bestellanforderung_artikel)){
//						if (e.getMessage().contains(Konstanten.SQL_ERROR_bestellanforderungsposition_bestellanforderung_artikel)){

						throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,((BaugruppeArtikelBean)bean).toString()));
					}
				}
				e.printStackTrace();
			}
		} else {
			throw new LagerException(new Fehler(2));
		}
	}

	
	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update baugruppeartikel " +
					" set id_artikel=?, id_baugruppe=? ,eingebauteArtikelMenge=?" +
					" where id_artikel=? and id_baugruppe=? ";
		}
		return updateSQL;	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		BaugruppeArtikelBean baugruppeArtikelBean = (BaugruppeArtikelBean ) bean;
		try{
			if (baugruppeArtikelBean.getArtikel()==null || baugruppeArtikelBean.getArtikel().getId()==0){
				getUpdateStmt().setNull(1, java.sql.Types.INTEGER);
				getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(1, baugruppeArtikelBean.getArtikel().getId());
				getUpdateStmt().setInt(4, baugruppeArtikelBean.getArtikel().getId());
			}

			if (baugruppeArtikelBean.getBaugruppe()==null || baugruppeArtikelBean.getBaugruppe().getId()==0){
				getUpdateStmt().setNull(2, java.sql.Types.INTEGER);
				getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(2, baugruppeArtikelBean.getBaugruppe().getId());
				getUpdateStmt().setInt(5, baugruppeArtikelBean.getBaugruppe().getId());
			}
			
			getUpdateStmt().setInt(3, baugruppeArtikelBean.getEingebauteMenge());

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
			insertSQL = "insert into baugruppeartikel " +
					"(id_artikel, id_baugruppe, eingebauteArtikelMenge)" +
					" values (?,?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		BaugruppeArtikelBean baugruppeArtikelBean = (BaugruppeArtikelBean) bean;
		
		if (baugruppeArtikelBean.getArtikel()==null || baugruppeArtikelBean.getArtikel().getId()==0){
			getInsertStmt().setNull(1, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(1, baugruppeArtikelBean.getArtikel().getId());
		}

		if (baugruppeArtikelBean.getBaugruppe()==null || baugruppeArtikelBean.getBaugruppe().getId()==0){
			getInsertStmt().setNull(2, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(2, baugruppeArtikelBean.getBaugruppe().getId());
		}
		
		getInsertStmt().setInt(3, baugruppeArtikelBean.getEingebauteMenge());
		
		getInsertStmt().executeUpdate();
		
//		beanBaPos.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	
	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from baugruppeartikel " +
						" where id_artikel = ? and id_baugruppe = ?";
			}
		return deleteSQL;
	}

	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		BaugruppeArtikelBean baugruppeArtikelBean = (BaugruppeArtikelBean) bean;
		try{
			getDeleteStmt().setInt(1, baugruppeArtikelBean.getArtikel().getId());
			getDeleteStmt().setInt(2, baugruppeArtikelBean.getBaugruppe().getId());
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


	public ArrayList<BaugruppeArtikelBean> sucheAnhandBaugruppeBean(BaugruppeBean baugruppeBean) throws SQLException, LagerException {
		ArrayList<BaugruppeArtikelBean> resultList = new ArrayList<BaugruppeArtikelBean>();
		ResultSet rs;
		//sql bilden
		String sql = "SELECT id_artikel, eingebauteArtikelMenge FROM baugruppeartikel b where id_baugruppe = ?";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, baugruppeBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			BaugruppeArtikelBean baugruppeArtikelBean = new BaugruppeArtikelBean();
			baugruppeArtikelBean.setBaugruppe(baugruppeBean);
			
			ArtikelBean artikelBean = (ArtikelBean)getArtikelBeanDB().selectAnhandID(rs.getInt("id_artikel"), 0, null);
			baugruppeArtikelBean.setArtikel(artikelBean);
			
			baugruppeArtikelBean.setEingebauteMenge(rs.getInt("eingebauteArtikelMenge"));
			
			baugruppeArtikelBean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(baugruppeArtikelBean);
			Log.log().finest(baugruppeArtikelBean.toString());
			
		}
		return resultList;
	}

	private ArtikelBeanDB getArtikelBeanDB() {
		if (artikelBeanDB==null){
			artikelBeanDB = new ArtikelBeanDB();
		}
		return artikelBeanDB;
	}

	public ArrayList<BaugruppeArtikelBean> sucheAnhandArtikelBean(
			ArtikelBean artikelBean) throws SQLException, LagerException {
		ArrayList<BaugruppeArtikelBean> resultList = new ArrayList<BaugruppeArtikelBean>();
		ResultSet rs;
		//sql bilden
		String sql = "SELECT id_baugruppe, eingebauteArtikelMenge FROM baugruppeartikel b where id_artikel = ?";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, artikelBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			BaugruppeArtikelBean baugruppeArtikelBean = new BaugruppeArtikelBean();
//			baugruppeArtikelBean.setBaugruppe(baugruppeBean);
			baugruppeArtikelBean.setArtikel(artikelBean);
			
			BaugruppeBean baugruppeBean = (BaugruppeBean)getBaugruppeBeanDB().selectAnhandID(rs.getInt("id_baugruppe"), 0, null);
			baugruppeArtikelBean.setBaugruppe(baugruppeBean);
//			ArtikelBean artikelBean = (ArtikelBean)getArtikelBeanDB().selectAnhandID(rs.getInt("id_artikel"));
//			baugruppeArtikelBean.setArtikel(artikelBean);
			
			baugruppeArtikelBean.setEingebauteMenge(rs.getInt("eingebauteArtikelMenge"));
			
			baugruppeArtikelBean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(baugruppeArtikelBean);
			Log.log().finest(baugruppeArtikelBean.toString());
			
		}
		return resultList;
	}

	private BaugruppeBeanDB getBaugruppeBeanDB() {
		if (baugruppeBeanDB==null){
			baugruppeBeanDB = new BaugruppeBeanDB();
		}
		return baugruppeBeanDB;
	}
	


//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}



}
