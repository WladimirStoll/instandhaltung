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
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;

public class SaeuleBeanDB extends BeanDB {

	private ZeileBeanDB zeileBeanDB;
	private EbeneBeanDB ebeneBeanDB;
//	private AbteilungBeanDB abteilungBeanDB;
//	private EtageBeanDB etageBeanDB;
	private String nachsteSaeuleNrSql;
	private PreparedStatement nachsteSaeuleNrStmt;

	public SaeuleBeanDB(){
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

	
	private void speichereBean(Bean bean) throws SQLException, LagerException {
		speichereEbene((SaeuleBean)bean,BeanDBStatus.OHNE_STATUS_ANGABE);
		
	}

	private void speichereEbene(SaeuleBean bean,BeanDBStatus beanDBStatus) throws SQLException, LagerException {
		for(int i = 0; i<bean.getEbeneBeans().size();i++){
			EbeneBean ebeneBean = bean.getEbeneBeans().get(i);
			if (beanDBStatus.equals(BeanDBStatus.DELETE)){
				ebeneBean.setBeanDBStatus(BeanDBStatus.DELETE);
			}
			getEbeneBeanDB().saveBean(ebeneBean);
		}
	}

	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from saeule " +
						" where id = ? ";
			}
		return deleteSQL;
	}
	

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update saeule " +
					" set nummer=?, fk_zeile=? " +
					" where id = ? ";
		}
		return updateSQL;	}
	
	private void updateBean(SaeuleBean bean) throws SQLException, LagerException {
		try{
//			if (bean.getZugriffsrecht()==null || bean.getZugriffsrecht().getId()==0){
//				getUpdateStmt().setNull(1, java.sql.Types.INTEGER);
//				getUpdateStmt().setNull(3, java.sql.Types.INTEGER);
//			}else{
//				getUpdateStmt().setInt(1, bean.getZugriffsrecht().getId());
//				getUpdateStmt().setInt(3, bean.getZugriffsrecht().getId());
//			}
//
//			if (bean.getBenutzer()==null || bean.getBenutzer().getId()==0){
//				getUpdateStmt().setNull(2, java.sql.Types.INTEGER);
//				getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
//			}else{
//				getUpdateStmt().setInt(2, bean.getBenutzer().getId());
//				getUpdateStmt().setInt(4, bean.getBenutzer().getId());
//			}

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
			insertSQL = "insert into saeule " +
					"(id, nummer, fk_zeile)" +
					" values (?,?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		SaeuleBean saeuleBean = (SaeuleBean) bean;

		getInsertStmt().setInt(1, saeuleBean.getId());
		getInsertStmt().setInt(2, saeuleBean.getNummer());
		getInsertStmt().setInt(3, saeuleBean.getZeileBean().getId());
		getInsertStmt().executeUpdate();
	}

		
//		if (benutzerZugriffsrechtBean.getZugriffsrecht()==null || benutzerZugriffsrechtBean.getZugriffsrecht().getId()==0){
//			getInsertStmt().setNull(1, java.sql.Types.INTEGER);
//		}else{
//			getInsertStmt().setInt(1, benutzerZugriffsrechtBean.getZugriffsrecht().getId());
//		}
//
//		if (benutzerZugriffsrechtBean.getBenutzer()==null || benutzerZugriffsrechtBean.getBenutzer().getId()==0){
//			getInsertStmt().setNull(2, java.sql.Types.INTEGER);
//		}else{
//			getInsertStmt().setInt(2, benutzerZugriffsrechtBean.getBenutzer().getId());
//		}
//		
//		try{
//			getInsertStmt().executeUpdate();
//		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
//			if (e.getSQLState().equals("23000")){
//				if (e.getMessage().contains(Konstanten.SQL_ERROR_bestellanforderungsposition_bestellanforderung_artikel)){
//					throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,benutzerZugriffsrechtBean.getBenutzer().getId().toString()));
//				}
//				if (e.getMessage().contains(Konstanten.SQL_ERROR_fk_abteilung_cannot_be_null)){
//					throw new LagerException(new Fehler(53));
//				}
//				if (e.getMessage().contains(Konstanten.SQL_ERROR_Duplicate_entry)){
//					throw new LagerException(new Fehler(54));
//				}
//				
//				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
//			}else{
//				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
//			}
//		}
//		beanBaPos.setBeanDBStatus(BeanDBStatus.SELECT);
//	}

	
//	@Override
//	public void deleteBean(Bean bean) throws SQLException, LagerException {
//		SaeuleBean benutzerZugriffsrechtBean = (SaeuleBean) bean;
//		try{
//			getDeleteStmt().setInt(1, benutzerZugriffsrechtBean.getZugriffsrecht().getId());
//			getDeleteStmt().setInt(2, benutzerZugriffsrechtBean.getBenutzer().getId());
//			getDeleteStmt().execute();
//		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
//			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
//		}
//	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		SaeuleBean saeuleBean = (SaeuleBean) bean;
		saeuleBean.setId(rs.getInt("id"));
		saeuleBean.setNummer(rs.getInt("nummer"));
		if (!saeuleBean.getZeileBean().getId().equals(rs.getInt("fk_zeile"))){
			//Zeile wird gelesen, falls es noch nicht geschehen ist.
			saeuleBean.setZeileBean((ZeileBean)getZeileBeanDB().selectAnhandID(rs.getInt("fk_zeile"), depthLevel+1, null));
		}
		saeuleBean.setEbeneBeans(getEbeneBeanDB().sucheAnhandSaeuleBean(saeuleBean, depthLevel+1));
		
//		saeuleBean.setAbteilungBean((AbteilungBean)getAbteilungBeanDB().selectAnhandID(rs.getInt("fk_abteilung")));
//		saeuleBean.setEtageBean((EtageBean)getEtageBeanDB().selectAnhandID(rs.getInt("fk_etage")));
		saeuleBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}


	private EbeneBeanDB getEbeneBeanDB() {
		if (ebeneBeanDB == null){
			ebeneBeanDB = new EbeneBeanDB();
 
		}
		return ebeneBeanDB;
}

	@Override
	protected String selectAnhandIdSQL() {
		// TODO Auto-generated method stub
		if (selectAnhandIdSQL == null) {
			selectAnhandIdSQL = "select id, nummer, fk_zeile from saeule where id = ?";
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
		SaeuleBean resultBean;
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new SaeuleBean();
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

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<SaeuleBean> sucheAnhandZeileBean(ZeileBean zeileBean, int depthLevel) throws SQLException, LagerException {
		ArrayList<SaeuleBean> resultList = new ArrayList<SaeuleBean>();
		ResultSet rs;
		getSqlParams().clear();
		//sql bilden
		String sql = " SELECT id, nummer, fk_zeile FROM saeule " +
				" where fk_zeile = ? " +
				" order by nummer";
		getSqlParams().put("fk_zeile",zeileBean.getId());
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		//Params setzen
		getSqlParams().setParams(stmt);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			SaeuleBean saeuleBean = new SaeuleBean();
			saeuleBean.setZeileBean(zeileBean);
			erzeugeBeanAusResultSet(rs, saeuleBean, depthLevel, null);
			resultList.add(saeuleBean);
			Log.log().finest(saeuleBean.toString());
			
		}
		return resultList;
	}

	private ZeileBeanDB getZeileBeanDB() {
		if (zeileBeanDB==null){
			zeileBeanDB = new ZeileBeanDB();
		}
		return zeileBeanDB;
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		// TODO Auto-generated method stub
		
	}

//	private AbteilungBeanDB getAbteilungBeanDB() {
//		if (abteilungBeanDB==null){
//			abteilungBeanDB = new AbteilungBeanDB();
//		}
//		return abteilungBeanDB;
//	}
//
//	private EtageBeanDB getEtageBeanDB() {
//		if (etageBeanDB==null){
//			etageBeanDB = new EtageBeanDB();
//		}
//		return etageBeanDB;
//	}
//	


//	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
//		if(mengenEinheitBeanDB==null){
//			mengenEinheitBeanDB = new MengenEinheitBeanDB();
//		}
//		return mengenEinheitBeanDB;
//	}

	public Integer getNachsteNummer(ZeileBean zeileBean) throws SQLException {
		getNachsteSaeuleNrStmt().setInt(1, zeileBean.getId());
		java.sql.ResultSet rs = getNachsteSaeuleNrStmt().executeQuery();
		rs.next();
		Integer posNummer = rs.getInt("naechstePosNummer");//rs.getInt("naechstePosNummer");
		if (posNummer.equals(0)){
			posNummer = 1;
		}
		return posNummer;
	}

	protected String getNachsteSaueleNrSql() {
		if (nachsteSaeuleNrSql==null){
			nachsteSaeuleNrSql = "select max(nummer)+1 as naechstePosNummer from saeule s " +
					"where s.fk_zeile = ?";
		}
		return nachsteSaeuleNrSql;
	}

	protected PreparedStatement getNachsteSaeuleNrStmt() throws SQLException {
		if (nachsteSaeuleNrStmt == null) {
			nachsteSaeuleNrStmt = LagerConnection.getOneInstance()
					.getConnection().prepareStatement(getNachsteSaueleNrSql());						
		}
		return nachsteSaeuleNrStmt;
	}


}
