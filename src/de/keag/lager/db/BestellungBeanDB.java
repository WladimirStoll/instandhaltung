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
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellung.BestellungStatus;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.bestellung.BestellungSuchBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class BestellungBeanDB extends BeanDB {
	
	private BestellungPosBeanDB bestellungPosBeanDB;
	
	
	private LhBeanDB herstellerLieferantBeanDB;
	private BenutzerBeanDB benutzerBeanDB;


	private BaBeanDB baBeanDB;
	
	
	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = "select id, name, vorname, loginName, password, email_ba_empfaenger, fk_anforderung_id from benutzer where loginName=?";
		}
		return selectSQL;
	}
	
	
	@Override
	protected String getDeleteSQL() {
		return deleteSQL;
	}

	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
			updateSQL = "update bestellung " +
					"set erstellungsdatum=?, status=?, fk_herstellerLieferant=?, " +
					"fk_benutzerAbsender=?, fk_benutzerAnnehmer=?, " +
					"email_ba_empfaenger=?, fk_anforderung_id=?" +
					" where id = ? ";
		}
		return updateSQL;
	}

	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into bestellung " +
					"(id, erstellungsdatum, status, fk_herstellerLieferant, " +
					"fk_benutzerAbsender, fk_benutzerAnnehmer, email_ba_empfaenger, fk_anforderung_id)" +
					" values (?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}

	

	public BestellungBeanDB(){
		super();
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException{
		if (bean!=null){
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean((BestellungBean)bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean((BestellungBean)bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
				//es passiert nix. Wir müssen die Bean nicht speichern
//			}else if (bean.getBeanStatus() == BeanStatus.DELETE){
//				deleteBean(bean);
//				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Eine Bestellanforderung darf aus der DB nicht entfernt werden."));
			}else{
				throw new LagerException(new Fehler(4,FehlerTyp.FEHLER,bean.getBeanDBStatus().toString()));
			}
		}else{
			throw new LagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws LagerException {
		throw new LagerException(new Fehler(4));
	}


	@Override
	protected void updateBean(Bean bean) throws SQLException {
		getUpdateStmt().setDate(1, ((BestellungBean)bean).getErstellungsDatum());
		
		getUpdateStmt().setString(2, BestellungStatus.JavaToDB(((BestellungBean)bean).getStatus()));
		
		if (((BestellungBean)bean).getLhBean()!=null && ((BestellungBean)bean).getLhBean().getId()!=0)
			getUpdateStmt().setInt(3, ((BestellungBean)bean).getLhBean().getId());
		else getUpdateStmt().setNull(3, java.sql.Types.INTEGER);
		
		if (((BestellungBean)bean).getAbsenderBenutzerBean()!=null && ((BestellungBean)bean).getAbsenderBenutzerBean().getId()!=0)
			getUpdateStmt().setInt(4, ((BestellungBean)bean).getAbsenderBenutzerBean().getId());
		else getUpdateStmt().setNull(4,java.sql.Types.INTEGER);
		
		if (((BestellungBean)bean).getAnnehmerBenutzerBean()!=null && ((BestellungBean)bean).getAnnehmerBenutzerBean().getId()!=0)
			getUpdateStmt().setInt(5, ((BestellungBean)bean).getAnnehmerBenutzerBean().getId());
		else getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
		
		getUpdateStmt().setString(6, ((BestellungBean)bean).getEmail());
		
		getUpdateStmt().setInt(7, ((BestellungBean)bean).getId());
		
		if (((BestellungBean)bean).getBaBean().getId().equals(0)){
			getUpdateStmt().setNull(8, java.sql.Types.INTEGER);
		}else{
			getUpdateStmt().setInt(8, ((BestellungBean)bean).getBaBean().getId());
		}
		
		getUpdateStmt().execute();
		
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	
	@Override
	protected void selectBean(Bean bean) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	protected void insertBean(Bean bean) throws SQLException {
		getInsertStmt().setInt(1, ((BestellungBean)bean).getId());
		getInsertStmt().setDate(2, ((BestellungBean)bean).getErstellungsDatum());
		getInsertStmt().setString(3, BestellungStatus.JavaToDB(((BestellungBean)bean).getStatus()));
		if (((BestellungBean)bean).getLhBean()!=null && ((BestellungBean)bean).getLhBean().getId()!=0)
			 getInsertStmt().setInt(4, ((BestellungBean)bean).getLhBean().getId());
		else getInsertStmt().setNull(4, java.sql.Types.INTEGER);
		if (((BestellungBean)bean).getAbsenderBenutzerBean()!=null && ((BestellungBean)bean).getAbsenderBenutzerBean().getId()!=0)
			 getInsertStmt().setInt(5, ((BestellungBean)bean).getAbsenderBenutzerBean().getId());
		else getInsertStmt().setNull(5,java.sql.Types.INTEGER);
		if (((BestellungBean)bean).getAnnehmerBenutzerBean()!=null && ((BestellungBean)bean).getAnnehmerBenutzerBean().getId()!=0)
			 getInsertStmt().setInt(6, ((BestellungBean)bean).getAnnehmerBenutzerBean().getId());
		else getInsertStmt().setNull(6, java.sql.Types.INTEGER);
		getInsertStmt().setString(7, ((BestellungBean)bean).getEmail());
		if (((BestellungBean)bean).getBaBean().getId().equals(0)){
			getInsertStmt().setNull(8, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(8, ((BestellungBean)bean).getBaBean().getId());
		}
		
		getInsertStmt().execute();
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	
	@Override
	protected String selectAnhandIdSQL() {
		// TODO Auto-generated method stub
		return " select id, erstellungsdatum, status, " +
		" fk_herstellerLieferant, fk_benutzerAbsender, fk_benutzerAnnehmer, " +
		" email_ba_empfaenger, fk_anforderung_id " +
		" from bestellung where id=?";
	}
	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		BestellungBean resultBean = new BestellungBean();
		ResultSet rs;
		//sql bilden
//		String sql = getSelectAnhandIdSQL();
		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
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
		return resultBean;	
	}
	
	private LhBeanDB getHerstellerLieferantBeanDB() {
		if (herstellerLieferantBeanDB == null) {
			herstellerLieferantBeanDB = new LhBeanDB();
		}
		return herstellerLieferantBeanDB;
	}

	private BenutzerBeanDB getBenutzerBeanDB() {
		if (benutzerBeanDB == null) {
			benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	
	public BestellungPosBeanDB getBestellungPosBeanDB() {
		if (bestellungPosBeanDB == null) {
			bestellungPosBeanDB = new BestellungPosBeanDB();
		}
		return bestellungPosBeanDB;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, erstellungsdatum, " +
				"status, fk_herstellerLieferant, fk_benutzerAbsender, " +
				"fk_benutzerAnnehmer, email_ba_empfaenger, fk_anforderung_id " +
				"from bestellung where erstellungsdatum>=? and erstellungsdatum<=?";
		
		switch (((BestellungSuchBean)iSuchBean).getAnforderungStatus())	{
		case ALLE:break;
		default:{
			sql = sql + " and status = '"+BestellungStatus.JavaToDB(((BestellungSuchBean)iSuchBean).getAnforderungStatus())+"'";	
		}
		};
		
		if (!((BestellungSuchBean)iSuchBean).getAnforderungsID().equals(0)){
			sql = sql + " and fk_anforderung_id = " + ((BestellungSuchBean)iSuchBean).getAnforderungsID();
		}
		
		sql = sql + " order by erstellungsdatum desc";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setDate(1, new java.sql.Date(((BestellungSuchBean)iSuchBean).getErstellungsDatumVon().getTime()));
		stmt.setDate(2, new java.sql.Date(((BestellungSuchBean)iSuchBean).getErstellungsDatumBis().getTime()));
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			BestellungBean baBean = new BestellungBean();
			erzeugeBeanAusResultSet(rs, baBean, 0, null);
			baBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
			Log.log().finest(baBean.toString());
			resultList.add(baBean);
		}
		return resultList;
	}

	

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		BestellungBean resultBean = (BestellungBean) bean;
		resultBean.setId(rs.getInt("id"));
		resultBean.setErstellungsDatum(rs.getDate("erstellungsdatum"));
		resultBean.setStatus(BestellungStatus.DbToJava(rs.getString("status")));
		resultBean.setLhBean((LhBean)getHerstellerLieferantBeanDB().selectAnhandID(rs.getInt("fk_herstellerLieferant"), 0, null));
		resultBean.setFk_benutzerAbsender((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzerAbsender"), 0, null));
		resultBean.setFk_benutzerAnnehmer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzerAnnehmer"), 0, null));
		resultBean.setEmail(rs.getString("email_ba_empfaenger"));
		if (rs.getInt("fk_anforderung_id")!=0){
			resultBean.setBaBean((BaBean)getBaBeanDB().selectAnhandID(rs.getInt("fk_anforderung_id"), 0, null));
		}else{
			resultBean.setBaBean(new BaBean());
		}
	}


	private BaBeanDB getBaBeanDB() {
		if (baBeanDB == null){
			baBeanDB = new BaBeanDB();
		}
		return baBeanDB;
	}


	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return new ArrayList<Bean>();
	}

}
