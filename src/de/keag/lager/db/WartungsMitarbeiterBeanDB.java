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
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterSuchBean;

public class WartungsMitarbeiterBeanDB extends BeanDB {

	private WartungBeanDB wartungBeanDB;
	private BenutzerBeanDB benutzerBeanDB;
	private LhBeanDB lhBeanDB;

	public WartungsMitarbeiterBeanDB(){
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
						throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,((BenutzerAbteilungBean)bean).getBenutzer().getLoginName()));
					}
				}else{
					e.printStackTrace();
					throw e;
				}
			}
		} else {
			throw new LagerException(new Fehler(2));
		}
	}

	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into wartungsmitarbeiter " +
					"(erfassungsbenutzer, erfassungsdatum, " +
					" id, fk_wartung, " +
					" fk_benutzer, stunden, " +
					" fk_herstellerlieferant, bemerkung)" +
					" values (?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		WartungsMitarbeiterBean wartungsMitarbeiter = (WartungsMitarbeiterBean) bean;
		
		getInsertStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
		getInsertStmt().setDate(2, wartungsMitarbeiter.getAktuellesDatum());
		getInsertStmt().setInt(3, wartungsMitarbeiter.getId());
		//Wartung
		if (wartungsMitarbeiter.getFk_wartung()==null || wartungsMitarbeiter.getFk_wartung().getId()==0){
			getInsertStmt().setNull(4, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(4, wartungsMitarbeiter.getFk_wartung().getId());
		}
		//fk_benutzer
		if (wartungsMitarbeiter.getFk_benutzer()==null || wartungsMitarbeiter.getFk_benutzer().getId()==0){
			getInsertStmt().setNull(5, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(5, wartungsMitarbeiter.getFk_benutzer().getId());
		}
		//stunden
		getInsertStmt().setDouble(6, wartungsMitarbeiter.getStunden());
		//fk_herstellerlieferant
		if (wartungsMitarbeiter.getFk_herstellerlieferant()==null || wartungsMitarbeiter.getFk_herstellerlieferant().getId()==0){
			getInsertStmt().setNull(7, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(7, wartungsMitarbeiter.getFk_herstellerlieferant().getId());
		}
		//Bemerkung
		getInsertStmt().setString(8, wartungsMitarbeiter.getBemerkung());
		
		getInsertStmt().executeUpdate();
		wartungsMitarbeiter.setBeanDBStatus(BeanDBStatus.SELECT); //nicht nötig, weil danach soweiso mit select gelesen wird.
	}

	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from wartungsmitarbeiter " +
						" where id = ?";
			}
		return deleteSQL;
	}
	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		WartungsMitarbeiterBean wartungsMitarbeiter = (WartungsMitarbeiterBean) bean;
		try{
			getDeleteStmt().setInt(1, wartungsMitarbeiter.getId());
			getDeleteStmt().execute();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
		}
	}


	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implemneitert");
	}

	
	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = " select " + 
						getSelectSpalten() +
						" from wartungsmitarbeiter " +
						" where id = ? ";
			}
			return selectSQL;	
	}
	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		PreparedStatement stmt = getSelectStmt(); 
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			WartungsMitarbeiterBean wartungsMitarbeiterBean = new WartungsMitarbeiterBean();
			erzeugeBeanAusResultSet(rs, wartungsMitarbeiterBean, 0, null);
			wartungsMitarbeiterBean.setBeanDBStatus(BeanDBStatus.SELECT); //nicht nötig, weil danach soweiso mit select gelesen wird.
			Log.log().finest(wartungsMitarbeiterBean.toString());
		}
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
		return resultList;
	}

	
	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = " select " + 
						getSelectSpalten() +
						" from wartungsmitarbeiter " +
						" where id = ? ";
		}
		return selectAnhandIdSQL;	
	}	
	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		WartungsMitarbeiterBean resultBean = new WartungsMitarbeiterBean();
		ResultSet rs;
		PreparedStatement stmt = getSelectAnhandIdStmt();
		stmt.setInt(1, id);
		rs = stmt.executeQuery(); //Ausführen
		//ergebnisse auswerten.
		if(rs.next()){
			erzeugeBeanAusResultSet(rs, resultBean, 0, null);
			resultBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
			Log.log().finest(resultBean.toString());
		}else{
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Bestellung nicht gefunden"));
		}
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
		return resultBean;	
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		WartungsMitarbeiterSuchBean wartungsMitarbeiterSuchBean = (WartungsMitarbeiterSuchBean) iSuchBean;
		Log.log().severe("NICHT IMPLEMENTIERT");
		return sucheAlle(); //es werden alle geliefert. 
	}

	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update wartungsmitarbeiter " +
					" set " +
					" aenderungsbenutzer=?," + 
					" aenderungsdatum=?," +
					" id=?," +
					" fk_wartung=?," +
					" fk_benutzer=?," +
					" stunden=?," +
					" fk_herstellerlieferant=?," +
					" bemerkung=? " +
					" where id = ? ";
		}
		return updateSQL;	
	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		WartungsMitarbeiterBean wartungsMitarbeiterBean = (WartungsMitarbeiterBean) bean;
	
		try{
			getUpdateStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
			getUpdateStmt().setDate(2, wartungsMitarbeiterBean.getAktuellesDatum());
			getUpdateStmt().setInt(3, wartungsMitarbeiterBean.getId());
			//Wartung
			if (wartungsMitarbeiterBean.getFk_wartung()==null || wartungsMitarbeiterBean.getFk_wartung().getId()==0){
				getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(4, wartungsMitarbeiterBean.getFk_wartung().getId());
			}
			
			//fk_benutzer
			if (wartungsMitarbeiterBean.getFk_benutzer()==null || wartungsMitarbeiterBean.getFk_benutzer().getId()==0){
				getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(5, wartungsMitarbeiterBean.getFk_benutzer().getId());
			}
			//stunden
			getUpdateStmt().setDouble(6, wartungsMitarbeiterBean.getStunden());
			//fk_herstellerlieferant
			if (wartungsMitarbeiterBean.getFk_herstellerlieferant()==null || wartungsMitarbeiterBean.getFk_herstellerlieferant().getId()==0){
				getUpdateStmt().setNull(7, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(7, wartungsMitarbeiterBean.getFk_herstellerlieferant().getId());
			}
			//Bemerkung
			getUpdateStmt().setString(8, wartungsMitarbeiterBean.getBemerkung());
			//Satz-ID
			getUpdateStmt().setInt(9, wartungsMitarbeiterBean.getId());
			
			
			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
		
	}

	public ArrayList<WartungsMitarbeiterBean> sucheAnhandWartungBean(WartungBean wartungBean) throws SQLException, LagerException {
		ArrayList<WartungsMitarbeiterBean> resultList = new ArrayList<WartungsMitarbeiterBean>();
		ResultSet rs;
		//sql bilden
		String sql = "SELECT" +
				getSelectSpalten() +
				"FROM wartungsmitarbeiter  " +
				"where fk_wartung = ? " +
				"order by id";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, wartungBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		wartungBean.getWartungsMitarbeiterBeans().clear();
		while(rs.next()){
			WartungsMitarbeiterBean wartungsMitarbeiterBean = new WartungsMitarbeiterBean();
			erzeugeBeanAusResultSet(rs, wartungsMitarbeiterBean, 0, null);
			wartungsMitarbeiterBean.setBeanDBStatus(BeanDBStatus.SELECT); //nicht nötig, weil danach soweiso mit select gelesen wird.
//			wartungBean.getWartungsMitarbeiterBeans().add(wartungsMitarbeiterBean);
			resultList.add(wartungsMitarbeiterBean);
			Log.log().finest(wartungsMitarbeiterBean.toString());
		}
		rs.close();
		rs = null;
//		stmt.close();
//		stmt = null;
//		sql = null;
		return resultList;
	}
	
	@Override
	protected String getSelectSpalten(){
		if (selectSpalten ==null){
			selectSpalten =
			" erfassungsbenutzer," +
			" erfassungsdatum," +
			" aenderungsbenutzer," +
			" aenderungsdatum," +
			" id," +
			" fk_wartung," +
			" fk_benutzer," +
			" stunden," +
			" fk_herstellerlieferant, " +
			" bemerkung ";
		}
		return selectSpalten;
	}
	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		WartungsMitarbeiterBean wartungsMitarbeiter = (WartungsMitarbeiterBean) bean;
		wartungsMitarbeiter.setErfassungsBenutzer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("erfassungsbenutzer"), 0, null));
		wartungsMitarbeiter.setErfassungsDatum(rs.getDate("erfassungsdatum"));
		wartungsMitarbeiter.setAenderungsBenutzer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("aenderungsbenutzer"), 0, null));
		wartungsMitarbeiter.setErfassungsDatum(rs.getDate("aenderungsdatum"));
		wartungsMitarbeiter.setId(rs.getInt("id"));
		wartungsMitarbeiter.setFk_wartung((WartungBean)getWartungBeanDB().selectAnhandID(rs.getInt("fk_wartung"), 0, null));
		wartungsMitarbeiter.setFk_benutzer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzer"), 0, null));
		wartungsMitarbeiter.setStunden(rs.getDouble("stunden"));
		wartungsMitarbeiter.setFk_herstellerlieferant((LhBean)getLhBeanDB().selectAnhandID(rs.getInt("fk_herstellerlieferant"), 0, null));
		wartungsMitarbeiter.setBemerkung(rs.getString("bemerkung"));
	}	

	private WartungBeanDB getWartungBeanDB() {
		if (wartungBeanDB==null){
			wartungBeanDB = new WartungBeanDB();
		}
		return wartungBeanDB;
	}

	private BenutzerBeanDB getBenutzerBeanDB() {
		if (this.benutzerBeanDB==null){
				this.benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	private LhBeanDB getLhBeanDB() {
		if (this.lhBeanDB==null){
				this.lhBeanDB = new LhBeanDB();
		}
		return lhBeanDB;
	}
	


//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}



}
