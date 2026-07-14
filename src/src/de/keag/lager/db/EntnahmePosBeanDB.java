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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.PosStatus;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

/*
 * BestellanforderungsPositionBean für den Datenentnahmenk-Zugriff
 */
public class EntnahmePosBeanDB extends BeanDB {
	private String selectByLoginNameSQL;
	private PreparedStatement selectByLoginNameStmt;
	
	//DB-Zugriff auf fremde Tabellen
	private ArtikelBeanDB artikelBeanDB;
	private MengenEinheitBeanDB mengenEinheitBeanDB;
	private KostenstelleBeanDB kostenstelleBeanDB;
	private LhBeanDB lhBeanDB;
	private KatalogBeanDB katalogBeanDB;
	private HalleBeanDB halleBeanDB;
	private EtageBeanDB etageBeanDB;
	private ZeileBeanDB zeileBeanDB;
	private SaeuleBeanDB saeuleBeanDB;
	private EbeneBeanDB ebeneBeanDB;
	private PositionBeanDB positionBeanDB;


	public EntnahmePosBeanDB(){
		super();
	}
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into entnahmeposition " +
					"(id, fk_entnahme, status, fk_artikel, menge, fk_mengeneinheit," +
					"fk_halle,fk_etage,fk_zeile,fk_saele,fk_ebene,fk_position, fk_kostenstelle " +
//					"fk_katalog, lieferantenbestellnummer, katalogseite, katalogpreis)" +
					" values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
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
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		return resultList;
	}
	
	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from entnahmeposition " +
						" where id = ? ";
			}
		return deleteSQL;
	}
	
	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean) bean;
		getDeleteStmt().setInt(1, entnahmePosBean.getId());
		getDeleteStmt().execute();
	}

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update entnahmeposition " +
					" set fk_entnahme=?, fk_artikel==?,menge==?,fk_mengeneinheit==?, fk_halle=?, " +
					" fk_etage=?, fk_zeile=?, fk_saeule=?, fk_ebene=?, " +
					" fk_position=?,status = ? ,fk_kostenstelle = ?  "+
					" where id = ?";
		}
		return updateSQL;	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean) bean;
		try{
			getUpdateStmt().setInt(1, entnahmePosBean.getEntnahmeBean().getId());
			getUpdateStmt().setInt(2, entnahmePosBean.getArtikelBean().getId());
			getUpdateStmt().setInt(3, entnahmePosBean.getMenge());
//			getUpdateStmt().setInt(4, entnahmePosBean.getMengenEinheitBean().getId());
			if (entnahmePosBean.getMengenEinheitBean()==null || entnahmePosBean.getMengenEinheitBean().getId()==0){
				getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(4, entnahmePosBean.getMengenEinheitBean().getId());
			}
			getUpdateStmt().setInt(5, entnahmePosBean.getHalleBean().getId());
			getUpdateStmt().setInt(6, entnahmePosBean.getEtageBean().getId());
			getUpdateStmt().setInt(7, entnahmePosBean.getZeileBean().getId());
			getUpdateStmt().setInt(8, entnahmePosBean.getSaeuleBean().getId());
			getUpdateStmt().setInt(9, entnahmePosBean.getEbeneBean().getId());
			getUpdateStmt().setInt(10, entnahmePosBean.getPositionBean().getId());
			getUpdateStmt().setString(11, PosStatus.JavaToDB(entnahmePosBean.getStatus()));
			if (entnahmePosBean.getKostenstelle()==null || entnahmePosBean.getKostenstelle().getId()==0){
				getUpdateStmt().setNull(12, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(12, entnahmePosBean.getKostenstelle().getId());
			}
//			if (entnahmePosBean.getMengenEinheitBean()==null || entnahmePosBean.getMengenEinheitBean().getId()==0){
//				getUpdateStmt().setNull(6, java.sql.Types.INTEGER);
//			}else{
//				getUpdateStmt().setInt(6, entnahmePosBean.getMengenEinheitBean().getId());
//			}
//			getUpdateStmt().setInt(7, entnahmePosBean.getKatalogBean().getId());
//			getUpdateStmt().setString(8, entnahmePosBean.getLieferantenbestellnummer());
//			getUpdateStmt().setString(9, entnahmePosBean.getKatalogseite());
//			getUpdateStmt().setDouble(10, entnahmePosBean.getKatalogpreis());
					
			getUpdateStmt().setInt(13, entnahmePosBean.getId());
			
			getUpdateStmt().execute();	
		}catch(SQLException e){
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	

	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		EntnahmePosBean beanEntnahmePos = (EntnahmePosBean) bean;
		getInsertStmt().setInt(1, beanEntnahmePos.getId());
		getInsertStmt().setInt(2, beanEntnahmePos.getEntnahmeBean().getId());
		getInsertStmt().setString(3, PosStatus.JavaToDB(beanEntnahmePos.getStatus()));
		getInsertStmt().setInt(4, beanEntnahmePos.getArtikelBean().getId());
		getInsertStmt().setInt(5, beanEntnahmePos.getMenge());
		if (beanEntnahmePos.getMengenEinheitBean()==null || beanEntnahmePos.getMengenEinheitBean().getId()==0){
			getInsertStmt().setNull(6, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(6, beanEntnahmePos.getMengenEinheitBean().getId());
		}
		getInsertStmt().setInt(7, beanEntnahmePos.getHalleBean().getId());
		getInsertStmt().setInt(8, beanEntnahmePos.getEtageBean().getId());
		getInsertStmt().setInt(9, beanEntnahmePos.getZeileBean().getId());
		getInsertStmt().setInt(10, beanEntnahmePos.getSaeuleBean().getId());
		getInsertStmt().setInt(11, beanEntnahmePos.getEbeneBean().getId());
		getInsertStmt().setInt(12, beanEntnahmePos.getPositionBean().getId());
//		getInsertStmt().setInt(5, beanEntnahmePos.getLiefertermin());
//		getInsertStmt().setString(6, PosStatus.JavaToDB(beanEntnahmePos.getStatus()));
		if (beanEntnahmePos.getKostenstelle()==null || beanEntnahmePos.getKostenstelle().getId()==0){
			getInsertStmt().setNull(13, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(13, beanEntnahmePos.getKostenstelle().getId());
		}
//		if (beanEntnahmePos.getMengenEinheitBean()==null || beanEntnahmePos.getMengenEinheitBean().getId()==0){
//			getInsertStmt().setNull(8, java.sql.Types.INTEGER);
//		}else{
//			getInsertStmt().setInt(8, beanEntnahmePos.getMengenEinheitBean().getId());
//		}
//		getInsertStmt().setInt(9, beanEntnahmePos.getKatalogBean().getId());
//		getInsertStmt().setString(10, beanEntnahmePos.getLieferantenbestellnummer());
//		getInsertStmt().setString(11, beanEntnahmePos.getKatalogseite());
//		getInsertStmt().setDouble(12, beanEntnahmePos.getKatalogpreis());
		try{
			getInsertStmt().executeUpdate();
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			if (e.getSQLState().equals("23000")){
				if (e.getMessage().contains(Konstanten.SQL_ERROR_bestellanforderungsposition_bestellanforderung_artikel)){
					throw new LagerException(new Fehler(37,FehlerTyp.FEHLER,beanEntnahmePos.getId().toString()));
				}
			}
			e.printStackTrace();
		}
//		beanEntnahmePos.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	public ArrayList<EntnahmePosBean>  sucheAnhandEntnahmeBean(EntnahmeBean entnahmeBean ) throws SQLException, LagerException{
		ArrayList<EntnahmePosBean> resultList = new ArrayList<EntnahmePosBean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, fk_entnahme, status, fk_artikel, " +
        " menge, fk_mengeeinheit, fk_halle, fk_etage, " +
		" fk_zeile, fk_saeule, fk_ebene, " +
		" fk_position , fk_kostenstelle"+
//		" from entnahmeposition where fk_entnahme = ? order by id = ? ";
		" from entnahmeposition where fk_entnahme = ? ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, entnahmeBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		entnahmeBean.loescheAlleEntnahmePositionen();
		while(rs.next()){
			EntnahmePosBean entnahmePosBean = new EntnahmePosBean();
			entnahmePosBean.setId(rs.getInt("id"));
//			entnahmePosBean.setStatus(PosStatus.DbToJava(rs.getString("status")));
			if (entnahmeBean.getId()==rs.getInt("fk_entnahme")){
				entnahmePosBean.setEntnahmeBean(entnahmeBean);
			}else{
				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"id der Bestellanforderung ist falsch"));
			}
			entnahmePosBean.setStatus(PosStatus.DbToJava(rs.getString("status")));
			ArtikelBean artikelBean = getArtikelBeanDB().selectById(rs.getInt("fk_artikel")); 
			entnahmePosBean.setArtikelBean(artikelBean);
			entnahmePosBean.setMenge(rs.getInt("menge"));
			entnahmePosBean.setMengenEinheitBean((MengenEinheitBean)getMengenEinheitBeanDB().selectAnhandID(rs.getInt("fk_mengeeinheit"))); //Achtung! Nicht aus dem Artikelstamm.
			entnahmePosBean.setHalleBean((HalleBean)getHalleBeanDB().selectAnhandID(rs.getInt("fk_halle")));
			entnahmePosBean.setEtageBean((EtageBean)getEtageBeanDB().selectAnhandID(rs.getInt("fk_etage")));
			entnahmePosBean.setZeileBean((ZeileBean)getZeileBeanDB().selectAnhandID(rs.getInt("fk_zeile")));
			entnahmePosBean.setSaeuleBean((SaeuleBean)getSaeuleBeanDB().selectAnhandID(rs.getInt("fk_saeule")));
			entnahmePosBean.setEbeneBean((EbeneBean)getEbeneBeanDB().selectAnhandID(rs.getInt("fk_ebene")));
			entnahmePosBean.setPositionBean((PositionBean)getPositionBeanDB().selectAnhandID(rs.getInt("fk_position")));
			entnahmePosBean.setKostenstelle((KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle")));
//			entnahmePosBean.setKatalogBean((KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog")));
//			entnahmePosBean.setLieferantenbestellnummer(rs.getString("lieferantenbestellnummer"));
//			entnahmePosBean.setKatalogseite(rs.getString("katalogseite"));
//			entnahmePosBean.setKatalogpreis(rs.getDouble("katalogpreis"));
//			entnahmePosBean.setKostenstelle((KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle")));
			entnahmePosBean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(entnahmePosBean);
			Log.log().finest(entnahmePosBean.toString());
//			entnahmeBean.addEntnahmePosition(entnahmePosBean);
		}
		return resultList;
	}


	private HalleBeanDB getHalleBeanDB() {
		if (halleBeanDB==null){
			halleBeanDB=new HalleBeanDB();
		}
		return halleBeanDB;
	}
	private EtageBeanDB getEtageBeanDB() {
		if (etageBeanDB==null){
			etageBeanDB=new EtageBeanDB();
		}
		return etageBeanDB;
	}
	private ZeileBeanDB getZeileBeanDB() {
		if (zeileBeanDB==null){
			zeileBeanDB=new ZeileBeanDB();
		}
		return zeileBeanDB;
	}
	private SaeuleBeanDB getSaeuleBeanDB() {
		if (saeuleBeanDB==null){
			saeuleBeanDB=new SaeuleBeanDB();
		}
		return saeuleBeanDB;
	}
	private EbeneBeanDB getEbeneBeanDB() {
		if (ebeneBeanDB==null){
			ebeneBeanDB=new EbeneBeanDB();
		}
		return ebeneBeanDB;
	}
	private PositionBeanDB getPositionBeanDB() {
		if (positionBeanDB==null){
			positionBeanDB=new PositionBeanDB();
		}
		return positionBeanDB;
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
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean)
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
	public Bean selectAnhandID(Integer id) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean)
			throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}



}
