package de.keag.lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.ArtikelSuchBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean.VaterArtikelArray;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;

public class UnterArtikelBeanDB extends BeanDB {

	// DB-Zugriff, fremde Tabellen.
	private ArtikelBeanDB artikelBeanDB;

	@Override
	protected String getInsertSQL() {
		if (insertSQL == null) {
			insertSQL = "insert into unterartikel "
					+ "(vater_id, kind_id, anzahl ) "
					+ " values (?,?,?)";
		}
		return insertSQL;
	}

	@Override
	public void insertBean(Bean b) throws SQLException, LagerException {
		UnterArtikelBean bean = (UnterArtikelBean) b; // Zeigerumwandlung,
		// Casting
		getInsertStmt().setInt(1, bean.getVaterArtikelBean().getId());
		getInsertStmt().setInt(2, bean.getKindArtikelBean().getId());
		getInsertStmt().setInt(3, bean.getAnzahl());

//		// getInsertStmt().setInt(6, bean.getKostenstelle().getId());
//		if (bean.getKostenstelle().getId() == 0) {
//			getInsertStmt().setNull(6, java.sql.Types.INTEGER);
//		} else {
//			getInsertStmt().setInt(6, bean.getKostenstelle().getId());
//		}

		try {
			getInsertStmt().executeUpdate();
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
//			if (e.getSQLState().equals("23000")) {
//				if (e.getMessage().contains(
//						Konstanten.SQL_ERROR_FK_MENGENEINHEIT)) {
//					throw new LagerException(new Fehler(110, FehlerTyp.FEHLER,
//							""));
//				} else if (e.getMessage().contains(
//						Konstanten.SQL_ERROR_ARTIKEL_KEG_NR_EINDEUTIG)) {
//					throw new LagerException(new Fehler(116, FehlerTyp.FEHLER,
//							""));
//				} else if (e.getMessage().contains(
//						Konstanten.SQL_ERROR_DOPPELTER_SCHLUESSEL)) {
//					throw new LagerException(new Fehler(109, FehlerTyp.FEHLER,
//							e.getMessage()));
//				} else {
//					throw new LagerException(new Fehler(36, FehlerTyp.FEHLER, e
//							.getMessage()));
//				}
//			}
			throw e;
		}
//		// Bestandspackstücke (Positionen) speichern
//		for (int i = 0; i < bean.getBestandspackstueckBeans().size(); i++) {
//			BestandspackstueckBean bestandspackstueckBean = bean
//					.getBestandspackstueckBeans().get(i);
//			getBestandspackstueckBeanDB().saveBean(bestandspackstueckBean);
//		}
	}

	@Override
	protected String selectAnhandIdSQL() {
		if (selectAnhandIdSQL == null) {
			selectAnhandIdSQL = " select vater_id, kind_id, anzahl " +
					" from unterartikel " +
					" where vater_id=? and kind_id=? ";
		}
		return selectAnhandIdSQL;
	}

	@Override
	protected String getUpdateSQL() {
		if (updateSQL == null) {
			updateSQL = " update unterartikel set " + " anzahl = ? "
					+ " where vater_id = ? and kind_id = ? ";
		}
		return updateSQL;
	}

	@Override
	public void updateBean(Bean b) throws SQLException, LagerException {
		UnterArtikelBean bean = (UnterArtikelBean) b;
		getUpdateStmt().setInt(1, bean.getAnzahl());
		getUpdateStmt().setInt(2, bean.getVaterArtikelBean().getId());
		getUpdateStmt().setInt(3, bean.getKindArtikelBean().getId());
//		if (bean.getMengenEinheitBean().getId() == 0) {
//			getUpdateStmt().setNull(6, java.sql.Types.INTEGER);
//		} else {
//			getUpdateStmt().setInt(6, bean.getMengenEinheitBean().getId());
//		}
		
		getUpdateStmt().execute();

//		// Bestandspackstücke (Positionen) speichern
//		for (int i = 0; i < bean.getBestandspackstueckBeans().size(); i++) {
//			BestandspackstueckBean bestandspackstueckBean = bean
//					.getBestandspackstueckBeans().get(i);
//			getBestandspackstueckBeanDB().saveBean(bestandspackstueckBean);
//		}

	}

	@Override
	protected String getDeleteSQL() {
		if (deleteSQL == null) {
			deleteSQL = "delete from unterartikel " + " where vater_id = ? and kind_id = ? ";
		}
		return deleteSQL;
	}

	public UnterArtikelBeanDB() {
		super();
	}

	public UnterArtikelBean selectById(Integer id) throws LagerException,
			SQLException {
		UnterArtikelBean resultBean;
		if (id != null && id != 0) {
			getSelectAnhandIdStmt().setInt(1, id);
			java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
			if (rs.next()) {
				resultBean = new UnterArtikelBean();
				erzeugeBeanAusResultSet(rs, resultBean, 0, null);
				return resultBean;
			} else {
				throw new ArtikelBeanDbLagerException(new Fehler(6));// Benutzer
			}
		} else {
			return null;
		}
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean != null) {
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT) {
				insertBean(bean);
			} else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE) {
				updateBean(bean);
			} else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE) {
				deleteBean(bean);
			} else if (bean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT) {
				throw new ArtikelBeanDbLagerException(new Fehler(4));
			} else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT) {
				//select, tue nix
			}
		} else {
			throw new ArtikelBeanDbLagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {

//		// Bestandspackstücke (Positionen) speichern
//		for (int i = 0; i < ((ArtikelBean) bean).getBestandspackstueckBeans()
//				.size(); i++) {
//			BestandspackstueckBean bestandspackstueckBean = ((ArtikelBean) bean)
//					.getBestandspackstueckBeans().get(i);
//			getBestandspackstueckBeanDB().saveBean(bestandspackstueckBean);
//		}

		getDeleteStmt().setInt(1, ((UnterArtikelBean) bean).getVaterArtikelBean().getId());
		getDeleteStmt().setInt(2, ((UnterArtikelBean) bean).getKindArtikelBean().getId());
		getDeleteStmt().executeUpdate();

	}

	@Override
	public void selectBean(Bean bean) {
		Log.log().severe("nicht implementiert");

	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException,
			KostenstelleBeanDbLagerException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		// sql bilden
		String sql = "select vater_id, kind_id, anzahl "
				+ " from unterartikel order by vater_id, kind_id ";
		// SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
		while (rs.next()) {
			UnterArtikelBean bean = new UnterArtikelBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		UnterArtikelBean unterArtikelBean = (UnterArtikelBean) bean;
		unterArtikelBean.setAnzahl(rs.getInt("anzahl"));
		unterArtikelBean.setVaterArtikelBean((ArtikelBean) getArtikelBeanDB()
				.selectAnhandID(rs.getInt("vater_id"), 0, null));
		unterArtikelBean.setKindArtikelBean((ArtikelBean) getArtikelBeanDB()
				.selectAnhandID(rs.getInt("kind_id"), 0, null));
		unterArtikelBean.setBeanDBStatus(BeanDBStatus.SELECT);
	};


	@Override
	public ArrayList<Bean> selectAnhandSuchBean(
			ISuchBean artikelBeanSuchKriterien, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException,
			LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		Log.log().severe("nicht implementiert");
		// sql bilden
//		String sql = "select artikel.id as id, bezeichnung, typ, keg_nr, mindestbestand, fk_kostenstelle, fk_mengeneinheit, fk_hersteller, empfohlene_bestellmenge, herstellerlieferant.name as hersteller_name, artikel.bemerkung as bemerkung  "
//				+ " from artikel, herstellerlieferant ";
//		String where = " where artikel.fk_hersteller = herstellerlieferant.id ";
//		// falls, su
//		if (artikelBeanSuchKriterien != null) {
//			where = getWhereKlausel(where,
//					((ArtikelSuchBean) artikelBeanSuchKriterien)
//							.getBezeichnung(), "bezeichnung");
//			where = getWhereKlausel(where,
//					((ArtikelSuchBean) artikelBeanSuchKriterien).getTyp(),
//					"typ");
//			if (((ArtikelSuchBean) artikelBeanSuchKriterien).getKeg_nr() != 0) {
//				where = getWhereKlausel(where,
//						((ArtikelSuchBean) artikelBeanSuchKriterien)
//								.getKeg_nr().toString(), "keg_nr");
//			}
//			if (!((ArtikelSuchBean) artikelBeanSuchKriterien).getHersteller()
//					.getId().equals(0)) {
//				where = getWhereKlausel(where,
//						((ArtikelSuchBean) artikelBeanSuchKriterien)
//								.getHersteller().getName(),
//						"herstellerlieferant.name");
//			}
//			if (!((ArtikelSuchBean) artikelBeanSuchKriterien).getArtikelBean()
//					.getId().equals(0)) {
//				where = getWhereKlausel(where,
//						((ArtikelSuchBean) artikelBeanSuchKriterien)
//								.getArtikelBean().getId(), "artikel.id");
//			}
//		}
//		sql = sql + where + " order by bezeichnung ";
//		// SQL absetzen
//		PreparedStatement stmt = LagerConnection.getOneInstance()
//				.getConnection().prepareStatement(sql);
//		rs = stmt.executeQuery();
//		// ergebnisse auswerten.
//		while (rs.next()) {
//			ArtikelBean bean = new ArtikelBean();
//			erzeugeBeanAusResultSet(rs, bean);
//			Log.log().finest(bean.toString());
//			resultList.add(bean);
//		}
		return resultList;
	}

	@Override
	protected String getSelectSQL() {
		return "select vater_id, kind_id, anzahl " + 
				" from FROM unterartikel where vater_id = ?";
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		UnterArtikelBean unterArtikelBean = new UnterArtikelBean();
		getSelectAnhandIdStmt().setInt(1, id);
		ResultSet rs = getSelectAnhandIdStmt().executeQuery();
		if (rs.next()) {
			erzeugeBeanAusResultSet(rs, unterArtikelBean, 0, null);
			Log.log().finest(unterArtikelBean.toString());
		}
		return unterArtikelBean;
	}

	private ArtikelBeanDB getArtikelBeanDB() {
		if (artikelBeanDB == null) {
			artikelBeanDB = new ArtikelBeanDB();
		}
		return artikelBeanDB;
	}

	public ArrayList<UnterArtikelBean> sucheAnhandArtikelBean(
			ArtikelBean artikelBean) throws SQLException, LagerException {
		ArrayList<UnterArtikelBean> resultList = new ArrayList<UnterArtikelBean>();
		ResultSet rs;
		//sql bilden
		String sql = " SELECT vater_id, kind_id, anzahl " +
				" FROM unterartikel " +
				" where vater_id = ? ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, artikelBean.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			UnterArtikelBean newBean = new UnterArtikelBean();
			erzeugeBeanAusResultSet(rs, newBean, 0, null);
			resultList.add(newBean);
			Log.log().finest(newBean.toString());
//			LieferantenBestellnummerBean newBean = new LieferantenBestellnummerBean();
////			baugruppeArtikelBean.setBaugruppe(baugruppeBean);
//			newBean.setArtikel(artikelBean);
//			
//			KatalogBean katalogBean = (KatalogBean)getKatalogBeanDB().selectAnhandID(rs.getInt("fk_katalog"));
//			newBean.setKatalogBean(katalogBean);
////			ArtikelBean artikelBean = (ArtikelBean)getArtikelBeanDB().selectAnhandID(rs.getInt("id_artikel"));
////			baugruppeArtikelBean.setArtikel(artikelBean);
//			
//			newBean.setBestellnummer(rs.getString("bestellnummer"));
//			newBean.setKatalogseite(rs.getString("katalogseite"));
//			newBean.setPreis(rs.getDouble("preis"));
//			
//			newBean.setBeanDBStatus(BeanDBStatus.SELECT);
//			resultList.add(newBean);
//			Log.log().finest(newBean.toString());
			
		}
		return resultList;
	}
	

	

	//Alle Oberartikel werden in eine Liste geschrieben
	public void getOberArtikelListe(ArtikelBean kindArtikelBeanNeu, int level) throws SQLException, LagerException {
		if (level > 10){
			return;
		}
		
		kindArtikelBeanNeu.getOberArtikelBeans().clear();
		ResultSet rs;
		//sql bilden
		final String sql = " SELECT vater_id, kind_id, anzahl " +
				" FROM unterartikel " +
				" where kind_id = ? ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setInt(1, kindArtikelBeanNeu.getId());
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
//		baBean.loescheAlleBaPositionen();
		while(rs.next()){
			UnterArtikelBean vaterBean = new UnterArtikelBean();
			erzeugeBeanAusResultSet(rs, vaterBean, 0, null);
			kindArtikelBeanNeu.getOberArtikelBeans().add(vaterBean.getVaterArtikelBean());
		}
		rs.close();
		stmt.close();
		rs = null;
		stmt = null;
		
		for(ArtikelBean vaterArtikelBean : kindArtikelBeanNeu.getOberArtikelBeans()){
			getOberArtikelListe(vaterArtikelBean, level++);
		}
//		return vaterArtikelBeans;
	}

}
