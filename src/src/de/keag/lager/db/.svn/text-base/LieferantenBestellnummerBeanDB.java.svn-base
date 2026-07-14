package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.LieferantenBestellnummerBeanDbException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerSuchBean;

public class LieferantenBestellnummerBeanDB extends BeanDB{
	private String sucheByArtikelIdSQL;
	private PreparedStatement selectByIdSqlStmt;
	private PreparedStatement sucheByArtikelIdSqlStmt;
	private ArtikelBeanDB artikelBeanDB;
	private KatalogBeanDB katalogBeanDB;
	
	//DB-Zugriff, fremde Tabellen.
	

	@Override
	protected String selectAnhandIdSQL(){
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select fk_artikel, fk_katalog, bestellnummer, katalogseite, preis " +
					"from lieferantenartikelbestellnummer " +
					"where fk_artikel=? and fk_katalog=?";
		}
		return selectAnhandIdSQL;
	}
	
	public LieferantenBestellnummerBeanDB() {
		super();
	}

	
	public LieferantenBestellnummerBean selectById(Integer artikel_id,Integer katalog_id) throws LagerException, SQLException{
		LieferantenBestellnummerBean resultBean;
		if (artikel_id!=null&&artikel_id!=0&&katalog_id!=null&&katalog_id!=0){ 
			getSelectByIdStmt().setInt(1, artikel_id);
			getSelectByIdStmt().setInt(2, katalog_id);
				java.sql.ResultSet rs = getSelectByIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new LieferantenBestellnummerBean();
					erzeugeBeanAusResultSet(rs, resultBean);
					return resultBean;
				}else{
					throw new LieferantenBestellnummerBeanDbException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			throw new LieferantenBestellnummerBeanDbException(new Fehler(6));//Benutzer kann nicht gelesen werden.
		}
	}
	
	public ArrayList<LieferantenBestellnummerBean> sucheByArtikelId(Integer artikel_id) throws LagerException, SQLException  {
		ArrayList<LieferantenBestellnummerBean> resultBeans = new ArrayList<LieferantenBestellnummerBean>();
		LieferantenBestellnummerBean tempBean;
		
		if (artikel_id!=null&&artikel_id!=0){ 
			getSucheByArtikelIdSqlStmt().setInt(1, artikel_id);
				java.sql.ResultSet rs = getSucheByArtikelIdSqlStmt().executeQuery();
				while (rs.next()){
					tempBean = new LieferantenBestellnummerBean();
					erzeugeBeanAusResultSet(rs, tempBean);
					resultBeans.add(tempBean);
				};
		}else{
			throw new LieferantenBestellnummerBeanDbException(new Fehler(6));//Benutzer kann nicht gelesen werden.
		}
		return resultBeans;
	}
	
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		LieferantenBestellnummerBean lieferantenBestellnummerBean = (LieferantenBestellnummerBean) bean;
		if (lieferantenBestellnummerBean!=null){
			if (lieferantenBestellnummerBean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(lieferantenBestellnummerBean);
			}else if (lieferantenBestellnummerBean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(lieferantenBestellnummerBean);
//			}else if (LieferantenBestellnummerBean.getBeanStatus() == BeanStatus.DELETE){
//				deleteBean(LieferantenBestellnummerBean);
			}else if (lieferantenBestellnummerBean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT){
				throw new LieferantenBestellnummerBeanDbException(new Fehler(4));
			}else{
				throw new LieferantenBestellnummerBeanDbException(new Fehler(3));
			}
		}else{
			throw new LieferantenBestellnummerBeanDbException(new Fehler(2));
		}
	}

	private PreparedStatement getSelectByIdStmt() throws SQLException  {
		if(selectByIdSqlStmt==null){
			selectByIdSqlStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(selectAnhandIdSQL());			
		}
		return selectByIdSqlStmt;
	}
	
	private PreparedStatement getSucheByArtikelIdSqlStmt() throws SQLException  {
		if(sucheByArtikelIdSqlStmt==null){
			sucheByArtikelIdSqlStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getSucheByArtikelIdSQL());			
		}
		return sucheByArtikelIdSqlStmt;
	}
	
	private String getSucheByArtikelIdSQL() {
		if(sucheByArtikelIdSQL==null){
			sucheByArtikelIdSQL = "select fk_artikel, fk_katalog, bestellnummer, katalogseite, preis " +
					"from lieferantenartikelbestellnummer " +
					"where fk_artikel=?";
		}
		return sucheByArtikelIdSQL;
	}

//	private void insertBean(LieferantenBestellnummerBean LieferantenBestellnummerBean) throws SQLException {
////		getInsertStmt().setInt(1, LieferantenBestellnummerBean.getId());
//		Log.log().severe("nicht implementiert");
//		getInsertStmt().executeUpdate();
//	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select fk_artikel, fk_katalog, bestellnummer, katalogseite, preis " +
				" from lieferantenartikelbestellnummer order by fk_artikel, fk_katalog ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			LieferantenBestellnummerBean bean = new LieferantenBestellnummerBean();
			erzeugeBeanAusResultSet(rs, bean);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean) throws  SQLException, LagerException{
		LieferantenBestellnummerBean lbBean = (LieferantenBestellnummerBean) bean;
		lbBean.setArtikel(getArtikelBeanDB().selectById(rs.getInt("fk_artikel")));
		KatalogBean katalogBean = (KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog"));
		lbBean.setKatalogBean(katalogBean);
		lbBean.setBestellnummer(rs.getString("bestellnummer"));
		lbBean.setKatalogseite(rs.getString("katalogseite"));
		lbBean.setPreis(rs.getDouble("preis"));
		lbBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean){
		ArrayList<Bean> resultList = new ArrayList<Bean>();
//		ResultSet rs;
//		//sql bilden
//		String sql = "select artikel.id as id, bezeichnung, typ, keg_nr, mindestbestand, fk_kostenstelle, fk_mengeneinheit, fk_hersteller, empfohlene_bestellmenge, herstellerlieferant.name as hersteller_name " +
//				" from artikel, herstellerlieferant ";
//		String where = " where artikel.fk_hersteller = herstellerlieferant.id ";
//		//falls, su
//		if(LieferantenBestellnummerBeanSuchKriterien!=null){
//			where = getWhereKlausel(where, LieferantenBestellnummerBeanSuchKriterien.getBezeichnung(), "bezeichnung");
//			where = getWhereKlausel(where, LieferantenBestellnummerBeanSuchKriterien.getTyp(), "typ");
//			if (LieferantenBestellnummerBeanSuchKriterien.getKeg_nr()!=0){
//				where = getWhereKlausel(where, LieferantenBestellnummerBeanSuchKriterien.getKeg_nr().toString(), "keg_nr");
//			}
//			if (LieferantenBestellnummerBeanSuchKriterien.getHersteller()!=null){
//				where = getWhereKlausel(where, LieferantenBestellnummerBeanSuchKriterien.getHersteller().getName(), "herstellerlieferant.name");
//			}
//		}
//		sql = sql + where + " order by bezeichnung "; 
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
//		rs = stmt.executeQuery();
//		//ergebnisse auswerten.
//		while(rs.next()){
//			LieferantenBestellnummerBean bean = new LieferantenBestellnummerBean();
//			erzeugeBeanAusResultSet(rs, bean);
//			Log.log().finest(bean.toString());
//			resultList.add(bean);
//		}
		return resultList;
	}

	private KatalogBeanDB getKatalogBeanDB() {
		if(katalogBeanDB==null){
			katalogBeanDB = new KatalogBeanDB();
		}
		return katalogBeanDB;
	}

	private ArtikelBeanDB getArtikelBeanDB() {
		if(artikelBeanDB==null){
			artikelBeanDB = new ArtikelBeanDB();
		}
		return artikelBeanDB;
	}



	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}

	@Override
	public Bean selectAnhandID(Integer id) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}


	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}

	@Override
	protected String getInsertSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		// TODO Auto-generated method stub
		
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

	@Override
	protected String getUpdateSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}

	

}
