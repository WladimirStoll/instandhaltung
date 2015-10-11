package de.keag.lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.BenutzerBeanDbLagerException;
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
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
public class BenutzerBeanDB extends BeanDB {
	
	private BenutzerZugriffsrechtBeanDB benutzerZugriffsrechtBeanDB = null;
	private BenutzerAbteilungBeanDB benutzerAbteilungBeanDB;
	
	public BenutzerBeanDB() {
		super();
	}


	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, " +
					"name, " +
					"vorname, " +
					"loginName, " +
					"password, " +
					"email, " +
					"copy_ba_email_empfaenger, " +
					"erfassungsbenutzer, erfassungsdatum, aenderungsbenutzer, aenderungsdatum " +
					"from benutzer " +
					"where id=?"; //Suche des Benutzers in der Datenbank nach der ID
		}
		return selectAnhandIdSQL;
	}
	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
			
			updateSQL = "update benutzer set " +
			" aenderungsdatum  = ?, " +
			" aenderungsbenutzer = ?, " +
					" name = ?, " +
					" vorname = ?, " +
					" loginName = ?, " +
					" password = ?, " +
					" email = ?, " +
					" copy_ba_email_empfaenger = ? " +
					" where id = ?";
		}
		return updateSQL;
	}
	
	@Override
	public void updateBean(Bean bean) throws SQLException, LagerException {
		BenutzerBean benutzerBean = (BenutzerBean)bean;
		getUpdateStmt().setDate(1, benutzerBean.getAktuellesDatum());
		getUpdateStmt().setInt(2, Run.getOneInstance().getBenutzerBean().getId());
		getUpdateStmt().setString(3, benutzerBean.getName());
		getUpdateStmt().setString(4, benutzerBean.getVorname());
		getUpdateStmt().setString(5, benutzerBean.getLoginName());
		getUpdateStmt().setString(6, benutzerBean.getPassword());
		getUpdateStmt().setString(7, benutzerBean.getEmail());
		getUpdateStmt().setInt(8, benutzerBean.getCopi_ba_email());
		getUpdateStmt().setInt(9, benutzerBean.getId());
		getUpdateStmt().execute();
	}
	

	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from benutzer " +
					" where id = ? ";
		}
		return deleteSQL;
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		BenutzerBean resultBean;
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return null;
			}
		}
			
		if (id!=null && id!=0){ //&&id!=0
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery(); //Suche in der Datenbank
				if (rs.next()){
					resultBean = new BenutzerBean();
					erzeugeBeanAusResultSet(rs, resultBean, 0, null);
					leseZugriffsrechte(resultBean);
					leseAbteilungen(resultBean);
					resultBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
					return resultBean;
				}else{
					throw new BenutzerBeanDbLagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return new BenutzerBean();
		}
	}
	
	private void leseZugriffsrechte(BenutzerBean benutzerBean) throws SQLException, LagerException {
		if (benutzerBean!=null){
			benutzerBean.getBenutzerZugriffsrechtBeans().clear();
			ArrayList<BenutzerZugriffsrechtBean> zugriffsRechte = 
				getBenutzerZugriffsrechtBeanDB().sucheAnhandBenutzerBean(benutzerBean);
			benutzerBean.setBenutzerZugriffsrechtBeans(zugriffsRechte);
		}
	}

	private void leseAbteilungen(BenutzerBean benutzerBean) throws SQLException, LagerException {
		if (benutzerBean!=null){
			benutzerBean.getBenutzerAbteilungBeans().clear();
			ArrayList<BenutzerAbteilungBean> abteilungen = 
				getBenutzerAbteilungBeanDB().sucheAnhandBenutzerBean(benutzerBean);
			benutzerBean.setBenutzerAbteilungBeans(abteilungen);
		}
	}


	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException {
		BenutzerBean benutzerBean = (BenutzerBean) bean;
		benutzerBean.setId(rs.getInt("id"));
		benutzerBean.setName(rs.getString("name"));
		benutzerBean.setVorname(rs.getString("vorname"));
		benutzerBean.setLoginName(rs.getString("loginName"));
		benutzerBean.setPassword(rs.getString("password"));
		benutzerBean.setEmail(rs.getString("email"));
		benutzerBean.setCopi_ba_email(rs.getInt("copy_ba_email_empfaenger"));
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
				throw new BenutzerBeanDbLagerException(new Fehler(4));
			}else{
				throw new BenutzerBeanDbLagerException(new Fehler(3));
			}
		}else{
			throw new BenutzerBeanDbLagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		getDeleteStmt().setInt(1, ((BenutzerBean)bean).getId());
		getDeleteStmt().executeUpdate();
	}


	@Override
	public void selectBean(Bean bean)  throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into benutzer " +
					"(id, name, vorname, " +
					"loginName, password, email, " +
					"copy_ba_email_empfaenger) " +
					" values (?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		getInsertStmt().setInt(1, ((BenutzerBean)bean).getId());
		getInsertStmt().setString(2, ((BenutzerBean)bean).getName());
		getInsertStmt().setString(3, ((BenutzerBean)bean).getVorname());
		getInsertStmt().setString(4, ((BenutzerBean)bean).getLoginName());
		getInsertStmt().setString(5, ((BenutzerBean)bean).getPassword());
		getInsertStmt().setString(6, ((BenutzerBean)bean).getEmail());
		getInsertStmt().setInt(7, ((BenutzerBean)bean).getCopi_ba_email());
		try{
			getInsertStmt().executeUpdate();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			if (e.getSQLState().equals("23000")){
				if (e.getMessage().contains(Konstanten.SQL_ERROR_benutzer_loginName_eindeutig)){
					throw new LagerException(new Fehler(50,FehlerTyp.FEHLER,((BenutzerBean)bean).getLoginName().toString()));
				}
			}
			throw e;
		}
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean suchKriterien, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		
		String sql = "select " +
		"distinct "+
		"b.id id, "+
		"b.name name, "+
		"b.vorname vorname, "+
		"b.loginName loginName, "+
		"b.password password, "+
		"b.email email, "+
		"b.copy_ba_email_empfaenger copy_ba_email_empfaenger "+
		"from benutzer b left join (abteilung a, abteilungbenutzer ab) " +
		"on (b.id = ab.fk_benutzer and a.id = ab.fk_abteilung)";
		String where = "";
		//falls, su
		if(suchKriterien!=null && ((BenutzerSuchBean)suchKriterien).getAbteilungBean().getId()!=0){
			where = getWhereKlausel(where, ((BenutzerSuchBean)suchKriterien).getAbteilungBean().getId().toString(), "a.id");
//			if (artikelBeanSuchKriterien.getKeg_nr()!=0){
//				where = getWhereKlausel(where, artikelBeanSuchKriterien.getKeg_nr().toString(), "keg_nr");
//			}
		}
		sql = sql + where + "order by b.name "; 
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			BenutzerBean bean = new BenutzerBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			bean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(bean);
		}
		return resultList;
	}


	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = "select id, " +
			" name, " +
			" vorname, " +
			" loginName, " +
			" password, " +
			" email, " +
			" copy_ba_email_empfaenger, " +
			" erfassungsbenutzer, erfassungsdatum, aenderungsbenutzer, aenderungsdatum " +
			" from benutzer " +
			" order by name, vorname "; 
			
		}
		return selectSQL;
	}


	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//SQL absetzen
		PreparedStatement stmt =  getSelectStmt();
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			BenutzerBean bean = new BenutzerBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			resultList.add(bean);
			Log.log().finest(bean.toString());
		}
		return resultList;
	}


	public ArrayList<BenutzerAbteilungBean> sucheAnhandBenutzerBean(
			BenutzerBean iBean) {
		// TODO Auto-generated method stub
		return null;
	}


	private BenutzerZugriffsrechtBeanDB getBenutzerZugriffsrechtBeanDB() {
		if (benutzerZugriffsrechtBeanDB==null){
			benutzerZugriffsrechtBeanDB = new BenutzerZugriffsrechtBeanDB();
		}
		return benutzerZugriffsrechtBeanDB;
	}

	private BenutzerAbteilungBeanDB getBenutzerAbteilungBeanDB() {
		if (benutzerAbteilungBeanDB==null){
			benutzerAbteilungBeanDB = new BenutzerAbteilungBeanDB();
		}
		return benutzerAbteilungBeanDB;
	}




}
