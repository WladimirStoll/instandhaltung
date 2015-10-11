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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.PosStatus;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

/*
 * BestellanforderungsPositionBean für den Datenbank-Zugriff
 */
public class BaPosBeanDB extends BeanDB {
	private String selectByLoginNameSQL;
	private PreparedStatement selectByLoginNameStmt;
	
	//DB-Zugriff auf fremde Tabellen
	private ArtikelBeanDB artikelBeanDB;
	private MengenEinheitBeanDB mengenEinheitBeanDB;
	private KostenstelleBeanDB kostenstelleBeanDB;
	private LhBeanDB lhBeanDB;
	private KatalogBeanDB katalogBeanDB;

	private String getSelectByLoginNameSQL() {
		if(selectByLoginNameSQL==null){
			selectByLoginNameSQL = "select id, name, vorname, loginName, password from benutzer where loginName=?";
		}
		return selectByLoginNameSQL;
	}

	public BaPosBeanDB(){
		super();
	}
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean!=null){
			if (bean.pruefeEigeneDaten().size() > 0){
				throw new LagerException(bean.pruefeEigeneDaten().get(0));
			}

			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
				//nix zu tun
			}else{
				throw new LagerException(new Fehler(4));
			}
		}else{
			throw new LagerException(new Fehler(2));
		}
	}

	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from bestellanforderungsposition " +
						" where id = ? ";
			}
		return deleteSQL;
	}
	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		BaPosBean baPosBean = (BaPosBean) bean;
		getDeleteStmt().setInt(1, baPosBean.getId());
		getDeleteStmt().execute();
	}

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update bestellanforderungsposition " +
					" set fk_artikel=?, bestellmenge=?, liefertermin=?, " +
					" status=?, fk_kostenstelle=?, fk_mengeneinheit=?, " +
					" fk_katalog=?, lieferantenbestellnummer=?, katalogseite=?, katalogpreis=? "+
					" where id = ? ";
		}
		return updateSQL;	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		BaPosBean baPosBean = (BaPosBean) bean;
		try{
			getUpdateStmt().setInt(1, baPosBean.getArtikelBean().getId());
			getUpdateStmt().setInt(2, baPosBean.getBestellmenge());
			getUpdateStmt().setInt(3, baPosBean.getLiefertermin());
			getUpdateStmt().setString(4, PosStatus.JavaToDB(baPosBean.getStatus()));
			if (baPosBean.getKostenstelle()==null || baPosBean.getKostenstelle().getId()==0){
				getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(5, baPosBean.getKostenstelle().getId());
			}
			if (baPosBean.getMengenEinheitBean()==null || baPosBean.getMengenEinheitBean().getId()==0){
				getUpdateStmt().setNull(6, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(6, baPosBean.getMengenEinheitBean().getId());
			}
			getUpdateStmt().setInt(7, baPosBean.getKatalogBean().getId());
			getUpdateStmt().setString(8, baPosBean.getLieferantenbestellnummer());
			getUpdateStmt().setString(9, baPosBean.getKatalogseite());
			getUpdateStmt().setDouble(10, baPosBean.getKatalogpreis());
					
			getUpdateStmt().setInt(11, baPosBean.getId());
			
			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	
	private PreparedStatement getSelectByLoginNameStmt() throws SQLException,LagerException  {
		if(selectByLoginNameStmt==null){
			selectByLoginNameStmt = LagerConnection.getOneInstance().getConnection().prepareStatement(getSelectByLoginNameSQL());			
		}
		return selectByLoginNameStmt;
	}

	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into bestellanforderungsposition " +
					"(id, fk_bestellanforderung, fk_artikel, bestellmenge, liefertermin, status, " +
					"fk_kostenstelle, fk_mengeneinheit , " +
					"fk_katalog, lieferantenbestellnummer, katalogseite, katalogpreis)" +
					" values (?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}	
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		BaPosBean beanBaPos = (BaPosBean) bean;
		getInsertStmt().setInt(1, beanBaPos.getId());
		getInsertStmt().setInt(2, beanBaPos.getBaBean().getId());
		getInsertStmt().setInt(3, beanBaPos.getArtikelBean().getId());
		getInsertStmt().setInt(4, beanBaPos.getBestellmenge());
		getInsertStmt().setInt(5, beanBaPos.getLiefertermin());
		getInsertStmt().setString(6, PosStatus.JavaToDB(beanBaPos.getStatus()));
		if (beanBaPos.getKostenstelle()==null || beanBaPos.getKostenstelle().getId()==0){
			getInsertStmt().setNull(7, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(7, beanBaPos.getKostenstelle().getId());
		}
		if (beanBaPos.getMengenEinheitBean()==null || beanBaPos.getMengenEinheitBean().getId()==0){
			getInsertStmt().setNull(8, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(8, beanBaPos.getMengenEinheitBean().getId());
		}
		getInsertStmt().setInt(9, beanBaPos.getKatalogBean().getId());
		getInsertStmt().setString(10, beanBaPos.getLieferantenbestellnummer());
		getInsertStmt().setString(11, beanBaPos.getKatalogseite());
		getInsertStmt().setDouble(12, beanBaPos.getKatalogpreis());
		try{
			getInsertStmt().executeUpdate();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			if (e.getSQLState().equals("23000")){
				if (e.getMessage().contains(Konstanten.SQL_ERROR_bestellanforderungsposition_bestellanforderung_artikel)){
					throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,beanBaPos.getId().toString()));
				}
			}
			e.printStackTrace();
		}
//		beanBaPos.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	public ArrayList<BaPosBean>  sucheBaPosBeansAnhandBaBean(BaBean baBean, int depthLevel ) throws SQLException, LagerException{
		ArrayList<BaPosBean> resultList = new ArrayList<BaPosBean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, fk_bestellanforderung, fk_artikel, " +
				"bestellmenge, liefertermin, status, fk_kostenstelle, fk_mengeneinheit, " +
				"fk_katalog, lieferantenbestellnummer, katalogseite, katalogpreis" +
				" from bestellanforderungsposition where fk_bestellanforderung = ? order by id";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, baBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			BaPosBean baPosBean = new BaPosBean();
			baPosBean.setId(rs.getInt("id"));
			baPosBean.setStatus(PosStatus.DbToJava(rs.getString("status")));
			if (baBean.getId()==rs.getInt("fk_bestellanforderung")){
				baPosBean.setBaBean(baBean);
			}else{
				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"id der Bestellanforderung ist falsch"));
			}
			ArtikelBean artikelBean = getArtikelBeanDB().selectById(rs.getInt("fk_artikel"), depthLevel+1, null); 
			baPosBean.setArtikelBean(artikelBean);
			baPosBean.setBestellmenge(rs.getInt("bestellmenge"));
			baPosBean.setLiefertermin(rs.getInt("liefertermin"));
			baPosBean.setKostenstelle((KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle"), depthLevel+1, null));
			baPosBean.setMengenEinheitBean((MengenEinheitBean)getMengenEinheitBeanDB().selectAnhandID(rs.getInt("fk_mengeneinheit"), depthLevel+1, null)); //Achtung! Nicht aus dem Artikelstamm.
			baPosBean.setKatalogBean((KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog"), depthLevel+1, null));
			baPosBean.setLieferantenbestellnummer(rs.getString("lieferantenbestellnummer"));
			baPosBean.setKatalogseite(rs.getString("katalogseite"));
			baPosBean.setKatalogpreis(rs.getDouble("katalogpreis"));
			baPosBean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(baPosBean);
			Log.log().finest(baPosBean.toString());
//			baBean.addBaPosition(baPosBean);
		}
		BeanDBStatus beanDBStatus = baBean.getBeanDBStatus();
		baBean.setBaPosBeans(resultList);
		baBean.setBeanDBStatus(beanDBStatus);
		return resultList;
	}

	private ArtikelBeanDB getArtikelBeanDB() {
		if(artikelBeanDB==null){
			artikelBeanDB = new ArtikelBeanDB();
		}
		return artikelBeanDB;
	}

	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
		if(mengenEinheitBeanDB==null){
			mengenEinheitBeanDB = new MengenEinheitBeanDB();
		}
		return mengenEinheitBeanDB;
	}

	private KostenstelleBeanDB getKostenstelleBeanDB() {
		if(kostenstelleBeanDB==null){
			kostenstelleBeanDB = new KostenstelleBeanDB();
		}
		return kostenstelleBeanDB;
	}

	private LhBeanDB getLhBeanDB() {
		if(lhBeanDB==null){
			lhBeanDB = new LhBeanDB();
		}
		return lhBeanDB;
	}

	private KatalogBeanDB getKatalogBeanDB() {
		if(katalogBeanDB==null){
			katalogBeanDB = new KatalogBeanDB();
		}
		return katalogBeanDB;
	}


	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String selectAnhandIdSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}



}
