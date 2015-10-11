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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeStatus;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosSuchBean;
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
//	private LhBeanDB lhBeanDB;
//	private KatalogBeanDB katalogBeanDB;
//	private HalleBeanDB halleBeanDB;
//	private EtageBeanDB etageBeanDB;
//	private ZeileBeanDB zeileBeanDB;
//	private SaeuleBeanDB saeuleBeanDB;
//	private EbeneBeanDB ebeneBeanDB;
	private PositionBeanDB positionBeanDB;
	private BenutzerBeanDB benutzerBeanDB;


	public EntnahmePosBeanDB(){
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
		((EntnahmePosBean)bean).setStatus(EntnahmeStatus.GELOESCHT);
		updateBean(bean);
//		EntnahmePosBean entnahmePosBean = (EntnahmePosBean) bean;
//		getDeleteStmt().setInt(1, entnahmePosBean.getId());
//		getDeleteStmt().execute();
	}

	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
		updateSQL = " update entnahmeposition " +
					" set " +
					" fk_artikel=?," +
					" menge=?," +
					" fk_mengeeinheit=?, " +
					" fk_position=?," +
					" status = ? ," +
					" fk_kostenstelle = ?,  "+
					" fk_benutzer_entnehmer = ?,  "+
					" erstellungsdatum = ?,  "+
					" fk_benutzer_kontroller = ?  "+
					" where id = ?";
		}
		return updateSQL;	}
	
	
	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean) bean;
		try{
			getUpdateStmt().setInt(1, entnahmePosBean.getArtikelBean().getId());
			getUpdateStmt().setInt(2, entnahmePosBean.getMenge());
			if (entnahmePosBean.getMengenEinheitBean()==null || entnahmePosBean.getMengenEinheitBean().getId()==0){
				getUpdateStmt().setNull(3, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(3, entnahmePosBean.getMengenEinheitBean().getId());
			}
			getUpdateStmt().setInt(4, entnahmePosBean.getPositionBean().getId());
			getUpdateStmt().setString(5, EntnahmeStatus.JavaToDB(entnahmePosBean.getStatus()));
			if (entnahmePosBean.getKostenstelle()==null || entnahmePosBean.getKostenstelle().getId()==0){
				getUpdateStmt().setNull(6, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(6, entnahmePosBean.getKostenstelle().getId());
			}
			if (entnahmePosBean.getBenutzerEntnehmerBean()==null || entnahmePosBean.getBenutzerEntnehmerBean().getId()==0){
				getUpdateStmt().setNull(7, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(7, entnahmePosBean.getBenutzerEntnehmerBean().getId());
			}
			getUpdateStmt().setDate(8, entnahmePosBean.getErfassungsDatum());
			if (entnahmePosBean.getBenutzerKontrollerBean()==null || entnahmePosBean.getBenutzerKontrollerBean().getId()==0){
				getUpdateStmt().setNull(9, java.sql.Types.INTEGER);
			}else{
				getUpdateStmt().setInt(9, entnahmePosBean.getBenutzerKontrollerBean().getId());
			}
			
			getUpdateStmt().setInt(10, entnahmePosBean.getId());
			
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
			insertSQL = "insert into entnahmeposition " +
					"(id, " +
					" fk_artikel, " +
					" menge, " +
					" fk_mengeeinheit, " +
					" fk_position, " +
					" status, " +
					" fk_kostenstelle, " +
					" fk_benutzer_entnehmer, " +
					" erstellungsdatum, " +
					" fk_benutzer_kontroller)" +
					" values (?,?,?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		EntnahmePosBean beanEntnahmePos = (EntnahmePosBean) bean;
		getInsertStmt().setInt(1, beanEntnahmePos.getId());
		getInsertStmt().setInt(2, beanEntnahmePos.getArtikelBean().getId());
		getInsertStmt().setInt(3, beanEntnahmePos.getMenge());
		if (beanEntnahmePos.getMengenEinheitBean()==null || beanEntnahmePos.getMengenEinheitBean().getId()==0){
		   getInsertStmt().setNull(4, java.sql.Types.INTEGER);
	    }else{
		   getInsertStmt().setInt(4, beanEntnahmePos.getMengenEinheitBean().getId());
	    }
		getInsertStmt().setInt(5, beanEntnahmePos.getPositionBean().getId());
		getInsertStmt().setString(6, EntnahmeStatus.JavaToDB(beanEntnahmePos.getStatus()));
		if (beanEntnahmePos.getKostenstelle()==null || beanEntnahmePos.getKostenstelle().getId()==0){
			getInsertStmt().setNull(7, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(7, beanEntnahmePos.getKostenstelle().getId());
		}
		if (beanEntnahmePos.getBenutzerEntnehmerBean()==null || beanEntnahmePos.getBenutzerEntnehmerBean().getId()==0){
			getInsertStmt().setNull(8, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(8, beanEntnahmePos.getBenutzerEntnehmerBean().getId());
		}
		getInsertStmt().setDate(9,beanEntnahmePos.getErfassungsDatum());
		if (beanEntnahmePos.getBenutzerKontrollerBean()==null || beanEntnahmePos.getBenutzerKontrollerBean().getId()==0){
			getInsertStmt().setNull(10, java.sql.Types.INTEGER);
		}else{
			getInsertStmt().setInt(10, beanEntnahmePos.getBenutzerKontrollerBean().getId());
		}
		
		try{
			getInsertStmt().execute();
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
	
//	public ArrayList<EntnahmePosBean>  sucheAnhandEntnahmePosBean(EntnahmePosBean entnahmePosBean ) throws SQLException, LagerException{
//		ArrayList<EntnahmePosBean> resultList = new ArrayList<EntnahmePosBean>();
//		ResultSet rs;
//		//sql bilden
//		String sql = "select id, fk_entnahme, status, fk_artikel, " +
//        " menge, fk_mengeeinheit, fk_halle, fk_etage, " +
//		" fk_zeile, fk_saeule, fk_ebene, " +
//		" fk_position , fk_kostenstelle"+
////		" from entnahmeposition where fk_entnahme = ? order by id = ? ";
//		" from entnahmeposition where fk_entnahme = ? ";
//		//SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
//		stmt.setInt(1, entnahmePosBean.getId());
//		rs = stmt.executeQuery();
//		//ergebnisse auswerten.
////		entnahmePosBean.loescheAlleEntnahmePositionen();
//		while(rs.next()){
//			EntnahmePosBean entnahmePosBean = new EntnahmePosBean();
//			entnahmePosBean.setId(rs.getInt("id"));
////			entnahmePosBean.setStatus(PosStatus.DbToJava(rs.getString("status")));
//			if (entnahmePosBean.getId()==rs.getInt("fk_entnahme")){
//				entnahmePosBean.setEntnahmePosBean(entnahmePosBean);
//			}else{
//				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"id der Bestellanforderung ist falsch"));
//			}
//			entnahmePosBean.setStatus(PosStatus.DbToJava(rs.getString("status")));
//			ArtikelBean artikelBean = getArtikelBeanDB().selectById(rs.getInt("fk_artikel")); 
//			entnahmePosBean.setArtikelBean(artikelBean);
//			entnahmePosBean.setMenge(rs.getInt("menge"));
//			entnahmePosBean.setMengenEinheitBean((MengenEinheitBean)getMengenEinheitBeanDB().selectAnhandID(rs.getInt("fk_mengeeinheit"))); //Achtung! Nicht aus dem Artikelstamm.
//			entnahmePosBean.setHalleBean((HalleBean)getHalleBeanDB().selectAnhandID(rs.getInt("fk_halle")));
//			entnahmePosBean.setEtageBean((EtageBean)getEtageBeanDB().selectAnhandID(rs.getInt("fk_etage")));
//			entnahmePosBean.setZeileBean((ZeileBean)getZeileBeanDB().selectAnhandID(rs.getInt("fk_zeile")));
//			entnahmePosBean.setSaeuleBean((SaeuleBean)getSaeuleBeanDB().selectAnhandID(rs.getInt("fk_saeule")));
//			entnahmePosBean.setEbeneBean((EbeneBean)getEbeneBeanDB().selectAnhandID(rs.getInt("fk_ebene")));
//			entnahmePosBean.setPositionBean((PositionBean)getPositionBeanDB().selectAnhandID(rs.getInt("fk_position")));
//			entnahmePosBean.setKostenstelle((KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle")));
////			entnahmePosBean.setKatalogBean((KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog")));
////			entnahmePosBean.setLieferantenbestellnummer(rs.getString("lieferantenbestellnummer"));
////			entnahmePosBean.setKatalogseite(rs.getString("katalogseite"));
////			entnahmePosBean.setKatalogpreis(rs.getDouble("katalogpreis"));
////			entnahmePosBean.setKostenstelle((KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle")));
//			entnahmePosBean.setBeanDBStatus(BeanDBStatus.SELECT);
//			resultList.add(entnahmePosBean);
//			Log.log().finest(entnahmePosBean.toString());
////			entnahmePosBean.addEntnahmePosition(entnahmePosBean);
//		}
//		return resultList;
//	}


//	private HalleBeanDB getHalleBeanDB() {
//		if (halleBeanDB==null){
//			halleBeanDB=new HalleBeanDB();
//		}
//		return halleBeanDB;
//	}
//	private EtageBeanDB getEtageBeanDB() {
//		if (etageBeanDB==null){
//			etageBeanDB=new EtageBeanDB();
//		}
//		return etageBeanDB;
//	}
//	private ZeileBeanDB getZeileBeanDB() {
//		if (zeileBeanDB==null){
//			zeileBeanDB=new ZeileBeanDB();
//		}
//		return zeileBeanDB;
//	}
//	private SaeuleBeanDB getSaeuleBeanDB() {
//		if (saeuleBeanDB==null){
//			saeuleBeanDB=new SaeuleBeanDB();
//		}
//		return saeuleBeanDB;
//	}
//	private EbeneBeanDB getEbeneBeanDB() {
//		if (ebeneBeanDB==null){
//			ebeneBeanDB=new EbeneBeanDB();
//		}
//		return ebeneBeanDB;
//	}
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

//	private LhBeanDB getLhBeanDB() {
//		if(lhBeanDB==null){
//			lhBeanDB = new LhBeanDB();
//		}
//		return lhBeanDB;
//	}
//
//	private KatalogBeanDB getKatalogBeanDB() {
//		if(katalogBeanDB==null){
//			katalogBeanDB = new KatalogBeanDB();
//		}
//		return katalogBeanDB;
//	}


	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String selectAnhandIdSQL() {
		// TODO Auto-generated method stub
		return "select " +
				"id, " +
				"fk_artikel, " +
				"menge, " +
				"fk_mengeeinheit, " +
				"fk_position, " +
				"status, " +
				"fk_kostenstelle, " +
				"fk_benutzer_entnehmer, " +
				"erstellungsdatum, " +
				"fk_benutzer_kontroller " +
				"from entnahmeposition where id=?";	
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		EntnahmePosBean resultBean = new EntnahmePosBean();
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
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Bestellanforderung nicht gefunden"));
		}
		return resultBean;	
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, fk_artikel, menge, " +
				"fk_mengeeinheit, fk_position, status, " +
				"fk_kostenstelle, fk_benutzer_entnehmer, erstellungsdatum, " +
				"fk_benutzer_kontroller " +
				"from entnahmeposition where erstellungsdatum>=? and erstellungsdatum<=?";
		switch (((EntnahmePosSuchBean)iSuchBean).getEntnahmeStatus())	{
		case ALLE:break;
		default:{
			sql = sql + " and status = '"+EntnahmeStatus.JavaToDB(((EntnahmePosSuchBean)iSuchBean).getEntnahmeStatus())+"'";	
		}
		};
		sql = sql + " order by erstellungsdatum desc";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setDate(1, new java.sql.Date(((EntnahmePosSuchBean)iSuchBean).getErstellungsDatumVon().getTime()));
		stmt.setDate(2, new java.sql.Date(((EntnahmePosSuchBean)iSuchBean).getErstellungsDatumBis().getTime()));
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			EntnahmePosBean entnahmePosBean = new EntnahmePosBean();
			erzeugeBeanAusResultSet(rs, entnahmePosBean, 0, null);
			entnahmePosBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
			Log.log().finest(entnahmePosBean.toString());
			resultList.add(entnahmePosBean);
		}
		return resultList;
	}
	private BenutzerBeanDB getBenutzerBeanDB() {
		if (benutzerBeanDB==null){
			benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)  bean;
		
		entnahmePosBean.setId(rs.getInt("id"));
		ArtikelBean artikelBean = getArtikelBeanDB().selectById(rs.getInt("fk_artikel"), depthLevel+1, null);
		entnahmePosBean.setArtikelBean(artikelBean);
		entnahmePosBean.setMenge(rs.getInt("menge"));
		MengenEinheitBean mengenEinheitBean = (MengenEinheitBean)getMengenEinheitBeanDB().selectAnhandID(rs.getInt("fk_mengeeinheit"), depthLevel+1, null);
		entnahmePosBean.setMengenEinheitBean(mengenEinheitBean);
		PositionBean positionBean = (PositionBean)getPositionBeanDB().selectAnhandID(rs.getInt("fk_position"), depthLevel+1, null);
		entnahmePosBean.setPositionBean(positionBean);
		entnahmePosBean.setStatus(EntnahmeStatus.DbToJava(rs.getString("status")));
		KostenstelleBean kostenstelleBean = (KostenstelleBean)getKostenstelleBeanDB().selectAnhandID(rs.getInt("fk_kostenstelle"), depthLevel+1, null);
		entnahmePosBean.setKostenstelle(kostenstelleBean);
		BenutzerBean benutzerBean = (BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzer_entnehmer"), depthLevel+1, null);
		entnahmePosBean.setBenutzerEntnehmer(benutzerBean);
		entnahmePosBean.setErfassungsDatum(rs.getDate("erstellungsdatum"));

		benutzerBean = (BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzer_entnehmer"), depthLevel+1, null);
		entnahmePosBean.setBenutzerKontoller(benutzerBean);
	}



}
