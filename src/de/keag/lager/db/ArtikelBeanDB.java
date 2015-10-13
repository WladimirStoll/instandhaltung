package de.keag.lager.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import java.sql.PreparedStatement;

import sun.util.calendar.Gregorian;

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
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

public class ArtikelBeanDB extends BeanDB {

	// DB-Zugriff, fremde Tabellen.
	private MengenEinheitBeanDB mengenEinheitBeanDB;
	private LhBeanDB lhBeanDB;
	private KostenstelleBeanDB kostenstelleBeanDB;
//	private BestandspackstueckBeanDB bestandspackstueckBeanDB;

	@Override
	protected String getInsertSQL() {
		if (insertSQL == null) {
			insertSQL = "insert into artikel "
					+ "(id, bezeichnung, typ, "
					+ "keg_nr, mindestbestand, fk_kostenstelle, "
					+ "fk_mengeneinheit, fk_hersteller, empfohlene_bestellmenge, bemerkung) "
					+ " values (?,?,?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}

	@Override
	public void insertBean(Bean b) throws SQLException, LagerException {
		ArtikelBean bean = (ArtikelBean) b; // Zeigerumwandlung,
		// Casting
		getInsertStmt().setInt(1, bean.getId());
		getInsertStmt().setString(2, bean.getBezeichnung());
		getInsertStmt().setString(3, bean.getTyp());
		getInsertStmt().setInt(4, bean.getKeg_nr());
		getInsertStmt().setInt(5, bean.getMindestbestand());

		// getInsertStmt().setInt(6, bean.getKostenstelle().getId());
		if (bean.getKostenstelle().getId() == 0) {
			getInsertStmt().setNull(6, java.sql.Types.INTEGER);
		} else {
			getInsertStmt().setInt(6, bean.getKostenstelle().getId());
		}

		// getInsertStmt().setInt(7, bean.getMengenEinheitBean().getId());
		if (bean.getMengenEinheitBean().getId() == 0) {
			getInsertStmt().setNull(7, java.sql.Types.INTEGER);
		} else {
			getInsertStmt().setInt(7, bean.getMengenEinheitBean().getId());
		}

		// getInsertStmt().setInt(8, bean.getHersteller().getId());
		if (bean.getHersteller().getId() == 0) {
			getInsertStmt().setNull(8, java.sql.Types.INTEGER);
		} else {
			getInsertStmt().setInt(8, bean.getHersteller().getId());
		}

		getInsertStmt().setInt(9, bean.getEmpfohlene_bestellmenge());

		if (bean.getBemerkung().equals("")) {
			getInsertStmt().setNull(10, java.sql.Types.CHAR);
		} else {
			getInsertStmt().setString(10, bean.getBemerkung());
		}

		try {
			getInsertStmt().executeUpdate();
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			if (e.getSQLState().equals("23000")) {
				if (e.getMessage().contains(
						Konstanten.SQL_ERROR_FK_MENGENEINHEIT)) {
					throw new LagerException(new Fehler(110, FehlerTyp.FEHLER,
							""));
				} else if (e.getMessage().contains(
						Konstanten.SQL_ERROR_ARTIKEL_KEG_NR_EINDEUTIG)) {
					throw new LagerException(new Fehler(116, FehlerTyp.FEHLER,
							""));
				} else if (e.getMessage().contains(
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
			selectAnhandIdSQL = "select id, bezeichnung, typ, keg_nr, mindestbestand, fk_kostenstelle, fk_mengeneinheit, fk_hersteller, empfohlene_bestellmenge, bemerkung from artikel where id=?";
		}
		return selectAnhandIdSQL;
	}

	@Override
	protected String getUpdateSQL() {
		if (updateSQL == null) {
			updateSQL = "update artikel set " + " bezeichnung = ?,"
					+ " typ = ?," + " keg_nr = ?, " + "mindestbestand = ?, "
					+ "fk_kostenstelle = ?, " + "fk_mengeneinheit = ?, "
					+ "fk_hersteller = ?, " + "empfohlene_bestellmenge = ?, "
					+ "bemerkung=? " + " where id = ? ";
		}

		return updateSQL;
	}

	@Override
	public void updateBean(Bean b) throws SQLException, LagerException {
		ArtikelBean bean = (ArtikelBean) b;
		getUpdateStmt().setString(1, bean.getBezeichnung());
		getUpdateStmt().setString(2, bean.getTyp());
		getUpdateStmt().setInt(3, bean.getKeg_nr());
		getUpdateStmt().setInt(4, bean.getMindestbestand());
		if (bean.getKostenstelle().getId() == 0) {
			getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
		} else {
			getUpdateStmt().setInt(5, bean.getKostenstelle().getId());
		}
		if (bean.getMengenEinheitBean().getId() == 0) {
			getUpdateStmt().setNull(6, java.sql.Types.INTEGER);
		} else {
			getUpdateStmt().setInt(6, bean.getMengenEinheitBean().getId());
		}
		if (bean.getHersteller().getId() == 0) {
			getUpdateStmt().setNull(7, java.sql.Types.INTEGER);
		} else {
			getUpdateStmt().setInt(7, bean.getHersteller().getId());
		}
		getUpdateStmt().setInt(8, bean.getEmpfohlene_bestellmenge());
		if (bean.getBemerkung().equals("")) {
			getUpdateStmt().setNull(9, java.sql.Types.CHAR);
		} else {
			getUpdateStmt().setString(9, bean.getBemerkung());
		}
		getUpdateStmt().setInt(10, bean.getId());


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
			deleteSQL = "delete from artikel " + " where id = ? ";
		}
		return deleteSQL;
	}

	public ArtikelBeanDB() {
		super();
	}

	public ArtikelBean selectById(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws LagerException,
			SQLException {
		ArtikelBean resultBean;
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return null;
			}
		}
			
		if (id != null && id != 0) {
			getSelectAnhandIdStmt().setInt(1, id);
			java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
			if (rs.next()) {
				resultBean = new ArtikelBean();
				erzeugeBeanAusResultSet(rs, resultBean, depthLevel, depthLevelArrayDB);
				return resultBean;
			} else {
				throw new ArtikelBeanDbLagerException(new Fehler(6));// Benutzer
				// kann
				// nicht
				// gelesen
				// werden.
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

		getDeleteStmt().setInt(1, ((ArtikelBean) bean).getId());
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
		String sql = "select id, bezeichnung, typ, keg_nr, mindestbestand, fk_kostenstelle, fk_mengeneinheit, fk_hersteller, empfohlene_bestellmenge, bemerkung "
				+ " from artikel order by bezeichnung ";
		// SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
		while (rs.next()) {
			ArtikelBean bean = new ArtikelBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		
		ArtikelBean artikelBean = (ArtikelBean) bean;
		artikelBean.setId(rs.getInt("id"));
		artikelBean.setKeg_nr(rs.getInt("keg_nr"));
		artikelBean.setBezeichnung(rs.getString("bezeichnung"));
		artikelBean.setBemerkung(rs.getString("bemerkung"));
		artikelBean.setTyp(rs.getString("typ"));
		artikelBean.setMindestbestand(rs.getInt("mindestbestand"));
		artikelBean.setKostenstelle((KostenstelleBean) getKostenstelleBeanDB()
				.selectAnhandID(rs.getInt("fk_kostenstelle"), depthLevel+1, depthLevelArrayDB));
		artikelBean
				.setMengenEinheitBean((MengenEinheitBean) getMengenEinheitBeanDB()
						.selectAnhandID(rs.getInt("fk_mengeneinheit"), depthLevel+1, depthLevelArrayDB));
		artikelBean.setHersteller((LhBean) getLhBeanDB().selectAnhandID(
				rs.getInt("fk_hersteller"), depthLevel+1, depthLevelArrayDB));
		artikelBean.setEmpfohlene_bestellmenge(rs
				.getInt("empfohlene_bestellmenge"));

		artikelBean.setBeanDBStatus(BeanDBStatus.SELECT);
	};

	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
		if (mengenEinheitBeanDB == null) {
			mengenEinheitBeanDB = new MengenEinheitBeanDB();
		}
		return mengenEinheitBeanDB;
	}

	private LhBeanDB getLhBeanDB() {
		if (lhBeanDB == null) {
			lhBeanDB = new LhBeanDB();
		}
		return lhBeanDB;
	}

	private KostenstelleBeanDB getKostenstelleBeanDB() {
		if (kostenstelleBeanDB == null) {
			kostenstelleBeanDB = new KostenstelleBeanDB();
		}
		return kostenstelleBeanDB;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(
			ISuchBean artikelBeanSuchKriterien, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException,
			LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return resultList;
			}
		}
		
		ResultSet rs;
		// sql bilden
		String sql = "select artikel.id as id, bezeichnung, typ, keg_nr, mindestbestand, fk_kostenstelle, fk_mengeneinheit, fk_hersteller, empfohlene_bestellmenge, herstellerlieferant.name as hersteller_name, artikel.bemerkung as bemerkung  "
				+ " from artikel, herstellerlieferant ";
		String where = " where artikel.fk_hersteller = herstellerlieferant.id ";
		// falls, su
		if (artikelBeanSuchKriterien != null) {
			where = getWhereKlausel(where,
					((ArtikelSuchBean) artikelBeanSuchKriterien)
							.getBezeichnung(), "bezeichnung");
			where = getWhereKlausel(where,
					((ArtikelSuchBean) artikelBeanSuchKriterien).getTyp(),
					"typ");
			if (((ArtikelSuchBean) artikelBeanSuchKriterien).getKeg_nr() != 0) {
				where = getWhereKlausel(where,
						((ArtikelSuchBean) artikelBeanSuchKriterien)
								.getKeg_nr().toString(), "keg_nr");
			}
			if (!((ArtikelSuchBean) artikelBeanSuchKriterien).getHersteller()
					.getId().equals(0)) {
				where = getWhereKlausel(where,
						((ArtikelSuchBean) artikelBeanSuchKriterien)
								.getHersteller().getName(),
						"herstellerlieferant.name");
			}
			if (!((ArtikelSuchBean) artikelBeanSuchKriterien).getArtikelBean()
					.getId().equals(0)) {
				where = getWhereKlausel(where,
						((ArtikelSuchBean) artikelBeanSuchKriterien)
								.getArtikelBean().getId(), "artikel.id");
			}
		}
		sql = sql + where + " order by bezeichnung limit 500";
		// SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
//		System.out.println("Start:"+GregorianCalendar.getInstance().toString());
		while (rs.next()) {
			ArtikelBean bean = new ArtikelBean();
//			System.out.println("Vor:"+GregorianCalendar.getInstance().toString());
			erzeugeBeanAusResultSet(rs, bean, 0, null);
//			System.out.println("Nach:"+GregorianCalendar.getInstance().toString());
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
//		System.out.println("Start:"+GregorianCalendar.getInstance().toString());
		return resultList;
	}

	@Override
	protected String getSelectSQL() {
		return "select id, bezeichnung, typ, keg_nr, "
				+ "mindestbestand, fk_kostenstelle, fk_mengeneinheit, "
				+ "fk_hersteller, empfohlene_bestellmenge, bemerkung " +
				"from FROM artikel a where id = ?";
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		ArtikelBean artikelBean = new ArtikelBean();
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return artikelBean;
			}
		}
		
		getSelectAnhandIdStmt().setInt(1, id);
		ResultSet rs = getSelectAnhandIdStmt().executeQuery();
		if (rs.next()) {
			erzeugeBeanAusResultSet(rs, artikelBean, depthLevel, depthLevelArrayDB);
			Log.log().finest(artikelBean.toString());
		}
		return artikelBean;
	}


}
