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
import de.keag.lager.core.fehler.BaugruppeBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
public class BaugruppeBeanDB extends BeanDB {
	
	private BaugruppeArtikelBeanDB baugruppeArtikelBeanDB = null;
	private HalleBeanDB halleBeanDB = null;
	private String sucheAlleBaugruppenIdsAnnahdArtikelSQL = null;
	private String sucheAlleBaugruppenBeansAnhandIDsSQL = null;
	private String sucheAlleBaugruppenBeansAnhandNameSQL = null;
	private String sucheKinderBaugruppenBeansAnhandIdSQL = null;
	
	private PreparedStatement sucheAlleBaugruppenIdsAnnahdArtikelStmt;
	private PreparedStatement sucheAlleBaugruppenBeansAnhandIDsStmt;
	private PreparedStatement sucheAlleBaugruppenBeansAnhandNameStmt;
	private PreparedStatement sucheKinderBaugruppenBeansAnhandIdStmt;

	
	public BaugruppeBeanDB() {
		super();
	}


	@Override
	protected String selectAnhandIdSQL() {
		if(selectSQL==null){
			selectSQL = " select id, " +
					" name, " +
					" fk_baugruppe, " +
					" fk_halle, " +
					" deletedrecord " +
					" from baugruppe " +
					" where id=? "; //Suche des Baugruppes in der Datenbank nach der ID
		}
		return selectSQL;
	}
	
	@Override
	protected String getUpdateSQL() {
		if (updateSQL==null){
			updateSQL = " update baugruppe set " +
					" aenderungsdatum  = ?, " +
					" aenderungsbenutzer = ?, " +
					" name = ?, " +
					" fk_baugruppe = ?, " +
					" fk_halle = ? " +
					" where id = ? " + 
					" and deletedrecord <> 'J' "
					;
		}
		return updateSQL;
	}
	
	@Override
	public void updateBean(Bean bean) throws SQLException, LagerException {
		BaugruppeBean baugruppeBean = (BaugruppeBean)bean;
		getUpdateStmt().setDate(1, BaugruppeBean.getAktuellesDatum());
		getUpdateStmt().setInt(2, Run.getOneInstance().getBenutzerBean().getId());
		getUpdateStmt().setString(3, baugruppeBean.getName());
		if (baugruppeBean.getVaterBaugruppe().getId()==0){
			getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
		}else{
			getUpdateStmt().setInt(4, baugruppeBean.getVaterBaugruppe().getId());
		}
		
		if (((BaugruppeBean)bean).getHalleBean()!=null && ((BaugruppeBean)bean).getHalleBean().getId()!=0)
			getUpdateStmt().setInt(5, ((BaugruppeBean)bean).getHalleBean().getId());
		else getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
	
		getUpdateStmt().setInt(6, baugruppeBean.getId());
		getUpdateStmt().executeUpdate();
	}
	

	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from baugruppe " +
					" where id = ? " + 
					" and deletedrecord <> 'J' "
					;
		}
		return deleteSQL;
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		BaugruppeBean resultBean;
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return null;
			}
		}
		
		if (id!=null && id!=0){ //&&id!=0
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery(); //Suche in der Datenbank
				if (rs.next()){
					resultBean = new BaugruppeBean();
					erzeugeBeanAusResultSet(rs, resultBean, depthLevel, depthLevelArrayDB);
//					leseArtikel(resultBean);
					return resultBean;
				}else{
					throw new BaugruppeBeanDbLagerException(new Fehler(6));//Baugruppe kann nicht gelesen werden.
				}
		}else{
			return new BaugruppeBean();
		}
	}
	

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		BaugruppeBean baugruppeBean = (BaugruppeBean) bean;
		baugruppeBean.setId(rs.getInt("id"));
		baugruppeBean.setName(rs.getString("name"));
		baugruppeBean.setHalleBean((HalleBean)getHalleBeanDB().selectAnhandID(rs.getInt("fk_halle"), depthLevel, depthLevelArrayDB));
		baugruppeBean.setDeletedrecord(
				rs.getString("deletedrecord")!=null
				&&rs.getString("deletedrecord").equals("J")
				);
		
		BaugruppeBean vaterBaugruppe;
		if (rs.getInt("fk_baugruppe")!=0){
			vaterBaugruppe = (BaugruppeBean)selectAnhandID(rs.getInt("fk_baugruppe"), depthLevel, depthLevelArrayDB);
		}else{
			vaterBaugruppe = null;
		}
		baugruppeBean.setVaterBaugruppe(vaterBaugruppe);
		baugruppeBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean!=null){
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
			}else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT){
				throw new BaugruppeBeanDbLagerException(new Fehler(4));
			}else{
				throw new BaugruppeBeanDbLagerException(new Fehler(3));
			}
		}else{
			throw new BaugruppeBeanDbLagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		getDeleteStmt().setInt(1, ((BaugruppeBean)bean).getId());
		getDeleteStmt().executeUpdate();
	}


	@Override
	public void selectBean(Bean bean)  throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into baugruppe " +
					" (erfassungsbenutzer, erfassungsdatum, id, name, fk_baugruppe, fk_halle) " +
					" values (?,?,?,?,?,?)";
		}
		return insertSQL;
	}
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		BaugruppeBean baugruppeBean = (BaugruppeBean)bean;
//		getInsertStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
		getInsertStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
		getInsertStmt().setDate(2, baugruppeBean.getErfassungsDatum());
		getInsertStmt().setInt(3,  baugruppeBean.getId());
		getInsertStmt().setString(4, baugruppeBean.getName());
	
		if (baugruppeBean.getVaterBaugruppe().getId()!=0){
			getInsertStmt().setInt(5, baugruppeBean.getVaterBaugruppe().getId());
		}else{
			getInsertStmt().setNull(5, java.sql.Types.INTEGER);		
		}
		
		if (((BaugruppeBean) bean).getHalleBean() != null
				&& ((BaugruppeBean) bean).getHalleBean().getId() != 0){
			getInsertStmt().setInt(6,
					((BaugruppeBean) bean).getHalleBean().getId());
		}else{
			getInsertStmt().setNull(6, java.sql.Types.INTEGER);
		}	
		try{
			getInsertStmt().execute();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			throw new LagerException(new Fehler(58,FehlerTyp.FEHLER,((BaugruppeBean)bean).getName().toString()));
//			if (e.getSQLState().equals("23000")){
//				if (e.getMessage().contains(Konstanten.SQL_ERROR_baugruppe_loginName_eindeutig)){
//					throw new LagerException(new Fehler(50,FehlerTyp.FEHLER,((BaugruppeBean)bean).getLoginName().toString()));
//				}
//			}
//			throw e;
		}
	}



	@Override
	protected String getSelectSQL() {
//	//	return " SELECT id, name, fk_baugruppe " +
//	//	" FROM baugruppe b where fk_baugruppe is null " +
//	//	" and name like '%?%' order by name ";
		return " SELECT id, name, fk_baugruppe, fk_halle, deletedrecord  " +
		" FROM baugruppe b " +
		//"where fk_baugruppe is null  " +
		//" and b.deletedrecord <> 'J' " + //
		"order by name ";
	}


	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultBeans = new ArrayList<Bean>();
		ResultSet rs = getSelectStmt().executeQuery(); 
		while(rs.next()){
			BaugruppeBean baugruppeBean = new BaugruppeBean();
			erzeugeBeanAusResultSet(rs,baugruppeBean, 0, null);
			boolean bereitsInDerListe = false;
			boolean deleted = baugruppeBean.getDeletedrecord();
			if (!deleted){
				for (int i = 0; i < resultBeans.size(); i++){
					BaugruppeBean bean = (BaugruppeBean )resultBeans.get(i);
					if (bean.getName().equalsIgnoreCase(baugruppeBean.getName())){
						bereitsInDerListe = true;
					}
				}
				if (!bereitsInDerListe){
					resultBeans.add(baugruppeBean);
				}
			}
		}
		return resultBeans;
	}


	public ArrayList<BaugruppeArtikelBean> sucheAnhandBaugruppeBean(
			BaugruppeBean iBean) {
		// TODO Auto-generated method stub
		return null;
	}


	private BaugruppeArtikelBeanDB getBaugruppeArtikelBeanDB() {
		if (baugruppeArtikelBeanDB==null){
			baugruppeArtikelBeanDB = new BaugruppeArtikelBeanDB();
		}
		return baugruppeArtikelBeanDB;
	}


	private String getSucheAlleBaugruppenIdsAnnahdArtikelSQL() {
		if (sucheAlleBaugruppenIdsAnnahdArtikelSQL==null){
			sucheAlleBaugruppenIdsAnnahdArtikelSQL = " SELECT b.id as id " +
					" FROM baugruppe b, baugruppeartikel a " +
					" where b.id = a.id_baugruppe " +
					" and id_artikel = ? ";
//			SELECT
//			  b.id as id,
//			  b.name as name,
//			  b.fk_baugruppe as fk_baugruppe,
//			  a.id_artikel as id_artikel
//			FROM
//			baugruppe b left outer join baugruppeartikel a on b.id = a.id_baugruppe
//			WHERE id_artikel = 1
		}
		return sucheAlleBaugruppenIdsAnnahdArtikelSQL;
	}


	/**
	 * Alle Baugruppen
	 * @param artikelID
	 * @param depthLevel TODO
	 * @param depthLevelArrayDB TODO
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<Integer> sucheAlleBaugruppenIdsAnnahdArtikel(int artikelID, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException{
		ArrayList<Integer> resultBaugruppenIDs = new ArrayList<Integer>();
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return resultBaugruppenIDs;
			}
		}
		
		getSucheAlleBaugruppenIdsAnnahdArtikelStmt().setInt(1, artikelID);
		ResultSet rs = getSucheAlleBaugruppenIdsAnnahdArtikelStmt().executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			resultBaugruppenIDs.add(rs.getInt("id"));
		}
		return resultBaugruppenIDs;
	}
	
	private PreparedStatement getSucheAlleBaugruppenIdsAnnahdArtikelStmt() throws SQLException {
		if(sucheAlleBaugruppenIdsAnnahdArtikelStmt==null){
			sucheAlleBaugruppenIdsAnnahdArtikelStmt = LagerConnection.getOneInstance().getConnection().prepareStatement(getSucheAlleBaugruppenIdsAnnahdArtikelSQL());			
		}
		return sucheAlleBaugruppenIdsAnnahdArtikelStmt;
	}


	private String getSucheAlleBaugruppenBeansAnhandIDsSQL(ArrayList<Integer> baugruppenIDs) {
		sucheAlleBaugruppenBeansAnhandIDsSQL =	
			" SELECT id, name, fk_baugruppe, fk_halle, b.deletedrecord as deletedrecord FROM baugruppe b where id in(-1";
		for(Integer i : baugruppenIDs){
			sucheAlleBaugruppenBeansAnhandIDsSQL =	sucheAlleBaugruppenBeansAnhandIDsSQL + 
												" , " + i.toString(); 
		}
		sucheAlleBaugruppenBeansAnhandIDsSQL = sucheAlleBaugruppenBeansAnhandIDsSQL +	")";
		return sucheAlleBaugruppenBeansAnhandIDsSQL;
	}


	private PreparedStatement getSucheAlleBaugruppenBeansAnhandIDsStmt(ArrayList<Integer> baugruppenIDs) throws SQLException {
		sucheAlleBaugruppenBeansAnhandIDsStmt = LagerConnection.getOneInstance().getConnection().prepareStatement(getSucheAlleBaugruppenBeansAnhandIDsSQL(baugruppenIDs));			
		return sucheAlleBaugruppenBeansAnhandIDsStmt;
	}

	
	/**
	 * 1. Suche alle Vaterbaugruppen
	 * 2. Fülle die Liste aller Baugruppen auf der höhsten Ebene 
	 * @param artikelID
	 * @return
	 * @throws SQLException
	 */
	private void sucheBaugruppenSucheHoechsteBaugruppen(ArrayList<Integer> baugruppenIDs, ArrayList<Integer> hoechsteBaugruppenIDs) throws SQLException{
		PreparedStatement stmt = getSucheAlleBaugruppenBeansAnhandIDsStmt(baugruppenIDs);
		ResultSet rs = stmt.executeQuery(); 
		baugruppenIDs.clear();
		while(rs.next()){
			int fk_baugruppe = rs.getInt("fk_baugruppe");
			int id = rs.getInt("id");
			if (fk_baugruppe!=0){
				//diese Baugruppe ist untergeordnet
				baugruppenIDs.add(fk_baugruppe);
			}else{
				//diese Baugruppe ist auf der höchsten Ebene
				if (hoechsteBaugruppenIDs.indexOf(id)==-1){
					//id ist noch nicht in der Liste, also hinzufügen
					hoechsteBaugruppenIDs.add(id);
				}
			}
		}
		if (!baugruppenIDs.isEmpty()){
			sucheBaugruppenSucheHoechsteBaugruppen(baugruppenIDs,hoechsteBaugruppenIDs);
		}
	}
	

	

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean suchKriterien, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return resultList;
			}
		}
		
		PreparedStatement stmt = null;
		BaugruppeSuchBean baugruppeSuchBean = (BaugruppeSuchBean)suchKriterien;
		Integer artikelID = baugruppeSuchBean.getArtikelBean().getId();
		if (artikelID!=0){
			ArrayList<Integer> hoechsteBaugruppenIDs = new ArrayList<Integer> ();
			ArrayList<Integer> baugruppenMitArtikelID = sucheAlleBaugruppenIdsAnnahdArtikel(artikelID, depthLevel, depthLevelArrayDB);
			sucheBaugruppenSucheHoechsteBaugruppen(baugruppenMitArtikelID, hoechsteBaugruppenIDs);
			stmt = getSucheAlleBaugruppenBeansAnhandIDsStmt(hoechsteBaugruppenIDs);
		}else{
			//alle Baugruppen holen
			ArrayList<Integer> baugruppenIDs = new ArrayList<Integer>();
			ArrayList<Integer> hoechsteBaugruppenIDs = new ArrayList<Integer> ();
		    getSucheAlleBaugruppenBeansAnhandNameStmt().setString(1, "%"+baugruppeSuchBean.getBaugruppeName()+"%");
			stmt = getSucheAlleBaugruppenBeansAnhandNameStmt();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				baugruppenIDs.add(rs.getInt("id"));//rs.getString("deletedrecord")
			}
			sucheBaugruppenSucheHoechsteBaugruppen(baugruppenIDs, hoechsteBaugruppenIDs);
			stmt = getSucheAlleBaugruppenBeansAnhandIDsStmt(hoechsteBaugruppenIDs);
		}
		ResultSet rs;
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			boolean showThisRecord;
			boolean thisRecoredIsDeleted = rs.getString("deletedrecord")!=null&&rs.getString("deletedrecord").equals("J"); //"J=gelöscht"
			//falls die gelöschten Sätze nicht angezeigt werden sollen UND falls dieser Satz gelöscht ist, dann nicht anzeigen.
			if (!baugruppeSuchBean.getDeletedrecord()){
				//falls die gelöschten Sätze nicht angezeigt werden sollen 
				if (thisRecoredIsDeleted){
					//dieser record ist gelöscht >> nicht anzeigen 
					showThisRecord = false;
				}else{
					//dieser record ist NICHT gelöscht >> anzeigen 
					showThisRecord = true;
				}
			}else{
				//gelöscte generel anzeigen, also diesen Recored sowieso anzeigen, egal, ob gelösct oder nicht
				showThisRecord = true;
			}
			if (rs.getInt("fk_baugruppe")==0&&showThisRecord){
				BaugruppeBean bean = new BaugruppeBean();
				erzeugeBeanAusResultSet(rs, bean, depthLevel, depthLevelArrayDB);
				Log.log().finest(bean.toString());
				//Beim Einfügen wird die Bean nach dem Namen sortiert/eingefügt.
				boolean istEingefuegt = false;
				for(int i = 0; i<resultList.size()&&!istEingefuegt;i++){
					BaugruppeBean bgb = (BaugruppeBean)resultList.get(i);
					int vergleich = bgb.getName().compareToIgnoreCase(bean.getName());
					if (vergleich>0){
						resultList.add(i,bean);
						istEingefuegt = true;
					}
				}
				if (!istEingefuegt){
					//Am Ende einfügen
					resultList.add(bean);
				}
			}
		}
		return resultList;
	}


	private String getSucheAlleBaugruppenBeansAnhandNameSQL() {
		if(sucheAlleBaugruppenBeansAnhandNameSQL==null){
			sucheAlleBaugruppenBeansAnhandNameSQL = "SELECT id, name, fk_baugruppe, fk_halle, deletedrecord  FROM baugruppe b where name like ? ";
		}
		return sucheAlleBaugruppenBeansAnhandNameSQL;
	}


	private PreparedStatement getSucheAlleBaugruppenBeansAnhandNameStmt() throws SQLException {
		if (sucheAlleBaugruppenBeansAnhandNameStmt==null){
			sucheAlleBaugruppenBeansAnhandNameStmt = 
				LagerConnection.getOneInstance().
				getConnection().prepareStatement(getSucheAlleBaugruppenBeansAnhandNameSQL());			
		}
		return sucheAlleBaugruppenBeansAnhandNameStmt;
	}



	private String getSucheKinderBaugruppenBeansAnhandIdSQL() {
		if(sucheKinderBaugruppenBeansAnhandIdSQL==null){
			sucheKinderBaugruppenBeansAnhandIdSQL = "SELECT id, name, fk_baugruppe, fk_halle, deletedrecord FROM baugruppe b where fk_baugruppe = ? order by name";
		}
		return sucheKinderBaugruppenBeansAnhandIdSQL;
	}


	private PreparedStatement getSucheKinderBaugruppenBeansAnhandIdStmt() throws SQLException {
		if (sucheKinderBaugruppenBeansAnhandIdStmt==null){
			sucheKinderBaugruppenBeansAnhandIdStmt = 
				LagerConnection.getOneInstance().
				getConnection().prepareStatement(getSucheKinderBaugruppenBeansAnhandIdSQL());			
		}
		return sucheKinderBaugruppenBeansAnhandIdStmt;
	}


	public ArrayList<BaugruppeBean> sucheKinderBaugruppenBeansAnhandId(Integer id) throws SQLException, LagerException {
		ArrayList<BaugruppeBean> resultBeans = new ArrayList<BaugruppeBean>();
		getSucheKinderBaugruppenBeansAnhandIdStmt().setInt(1, id);
		ResultSet rs = getSucheKinderBaugruppenBeansAnhandIdStmt().executeQuery(); 
		while(rs.next()){
			BaugruppeBean baugruppeBean = new BaugruppeBean();
			erzeugeBeanAusResultSet(rs,baugruppeBean, 0, null);
			resultBeans.add(baugruppeBean);
		}
		return resultBeans;
	}
	
	public void sucheAlleNachkommenEinerBaugruppeAnhandId(Integer id,ArrayList<Integer> retultList) throws SQLException, LagerException{
		if (!retultList.contains(id)){
			retultList.add(id);
		}
		ArrayList<BaugruppeBean> list = sucheKinderBaugruppenBeansAnhandId(id);
		for(BaugruppeBean bean : list){
			retultList.add(bean.getId());
			sucheAlleNachkommenEinerBaugruppeAnhandId(bean.getId(),retultList);
		}
	}




	public HalleBeanDB getHalleBeanDB() {
		if (halleBeanDB == null) {
			halleBeanDB = new HalleBeanDB();
		}

		return halleBeanDB;
	}


	public void vervollstaendigeVater(BaugruppeBean element) throws SQLException, LagerException {
		selectAnhandID(element.getId(), 0, null);
		if (element.getVaterBaugruppe().getId()!=0){
			vervollstaendigeVater(element.getVaterBaugruppe());
		}
	}


	
}
