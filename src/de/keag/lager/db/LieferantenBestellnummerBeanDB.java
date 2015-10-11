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
import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.LieferantenBestellnummerBeanDbException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
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
					erzeugeBeanAusResultSet(rs, resultBean, 0, null);
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
					erzeugeBeanAusResultSet(rs, tempBean, 0, null);
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
			}else if (lieferantenBestellnummerBean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(lieferantenBestellnummerBean);
			}else if (lieferantenBestellnummerBean.getBeanDBStatus() == BeanDBStatus.SELECT){
//				deleteBean(lieferantenBestellnummerBean);
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
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws  SQLException, LagerException{
		LieferantenBestellnummerBean lbBean = (LieferantenBestellnummerBean) bean;
		lbBean.setArtikel(getArtikelBeanDB().selectById(rs.getInt("fk_artikel"), depthLevel+1, null));
		KatalogBean katalogBean = (KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog"), depthLevel+1, null);
		lbBean.setKatalogBean(katalogBean);
		lbBean.setBestellnummer(rs.getString("bestellnummer"));
		lbBean.setKatalogseite(rs.getString("katalogseite"));
		lbBean.setPreis(rs.getDouble("preis"));
		lbBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB){
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
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getUpdateSQL() {
		if (updateSQL == null) {
			updateSQL = " update lieferantenartikelbestellnummer set " +
					" bestellnummer=?, katalogseite=?, preis=?" +
					" where fk_artikel=? and fk_katalog=? ";
		}
		return updateSQL;
	}
	
	@Override
	protected void updateBean(Bean b) throws SQLException, LagerException {

		LieferantenBestellnummerBean bean = (LieferantenBestellnummerBean) b;
		getUpdateStmt().setString(1, bean.getBestellnummer());
		getUpdateStmt().setString(2, bean.getKatalogseite());
		getUpdateStmt().setDouble(3, bean.getPreis());
		getUpdateStmt().setInt(4, bean.getArtikel().getId());
		getUpdateStmt().setInt(5, bean.getKatalogBean().getId());

//		if (bean.getKostenstelle().getId() == 0) {
//			getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
//		} else {
//			getUpdateStmt().setInt(5, bean.getKostenstelle().getId());
//		}

		getUpdateStmt().execute();
	}

	@Override
	protected String getDeleteSQL() {
		if (deleteSQL == null) {
			deleteSQL = " delete from lieferantenartikelbestellnummer " +
					" where fk_artikel=? and fk_katalog=? ";
		}
		return deleteSQL;
	}

	@Override
	public void deleteBean(Bean b) throws SQLException, LagerException {
		LieferantenBestellnummerBean bean = (LieferantenBestellnummerBean) b;
		getDeleteStmt().setInt(1, bean.getArtikel().getId());
		getDeleteStmt().setInt(2, bean.getKatalogBean().getId());
		getDeleteStmt().execute();
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getInsertSQL() {
		if (insertSQL == null) {
			insertSQL = "insert into lieferantenartikelbestellnummer "
					+ "(fk_artikel, fk_katalog, bestellnummer, katalogseite, preis) "
					+ " values (?,?,?,?,?)";
		}
		return insertSQL;
	}

	
	@Override
	protected void insertBean(Bean b) throws SQLException, LagerException {
		LieferantenBestellnummerBean bean = (LieferantenBestellnummerBean) b; // Zeigerumwandlung,
		// Casting
		getInsertStmt().setInt(1, bean.getArtikel().getId());
		getInsertStmt().setInt(2, bean.getKatalogBean().getId());
		getInsertStmt().setString(3, bean.getBestellnummer());
		getInsertStmt().setString(4, bean.getKatalogseite());
		getInsertStmt().setDouble(5, bean.getPreis());

		try {
			getInsertStmt().executeUpdate();
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			if (e.getSQLState().equals("23000")) {
				if (e.getMessage().contains(
						Konstanten.SQL_ERROR_DOPPELTER_SCHLUESSEL)) {
					throw new LagerException(new Fehler(109, FehlerTyp.FEHLER,
							e.getMessage()));
				} else {
					throw new LagerException(new Fehler(36, FehlerTyp.FEHLER, e
							.getMessage()));
				}
			}
			throw e;
		}

	}

	public ArrayList<LieferantenBestellnummerBean> sucheAnhandArtikelBean(
			ArtikelBean artikelBean) throws SQLException, LagerException {
		ArrayList<LieferantenBestellnummerBean> resultList = new ArrayList<LieferantenBestellnummerBean>();
		ResultSet rs;
		//sql bilden
		String sql = "SELECT fk_artikel, fk_katalog, bestellnummer, katalogseite, preis FROM lieferantenartikelbestellnummer b where fk_artikel = ?";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, artikelBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			LieferantenBestellnummerBean newBean = new LieferantenBestellnummerBean();
//			baugruppeArtikelBean.setBaugruppe(baugruppeBean);
			newBean.setArtikel(artikelBean);
			
			KatalogBean katalogBean = (KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog"), 0, null);
			newBean.setKatalogBean(katalogBean);
//			ArtikelBean artikelBean = (ArtikelBean)getArtikelBeanDB().selectAnhandID(rs.getInt("id_artikel"));
//			baugruppeArtikelBean.setArtikel(artikelBean);
			
			newBean.setBestellnummer(rs.getString("bestellnummer"));
			newBean.setKatalogseite(rs.getString("katalogseite"));
			newBean.setPreis(rs.getDouble("preis"));
			
			newBean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(newBean);
			Log.log().finest(newBean.toString());
			
		}
		return resultList;
	}

	

}
