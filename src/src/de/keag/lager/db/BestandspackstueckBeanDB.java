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
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class BestandspackstueckBeanDB extends BeanDB {

	private PositionBeanDB positionBeanDB;
	private ArtikelBeanDB artikelBeanDB;
	private BenutzerBeanDB benutzerBeanDB;
	private MengenEinheitBeanDB mengenEinheitBeanDB;
	private String sucheAnhandPositionBeanSql;
	private PreparedStatement sucheAnhandPositionBeanStmt;

	public BestandspackstueckBeanDB() {
		super();
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		if (bean != null) {

			if (((BestandspackstueckBean) bean).getPositionBean().getId()
					.equals(0)) {
				PositionBean positionBean = getPositionBeanDB()
						.lesePositionAnhandNummer(
								(((BestandspackstueckBean) bean)
										.getPositionBean()).getNummer(),
								(((BestandspackstueckBean) bean)
										.getPositionBean()).getEbeneBean()
										.getNummer(),
								(((BestandspackstueckBean) bean)
										.getPositionBean()).getEbeneBean()
										.getSaeuleBean().getNummer(),
								(((BestandspackstueckBean) bean)
										.getPositionBean()).getEbeneBean()
										.getSaeuleBean().getZeileBean()
										.getNummer(),
								(((BestandspackstueckBean) bean)
										.getPositionBean()).getEbeneBean()
										.getSaeuleBean().getZeileBean()
										.getHalleBean().getId());
				((BestandspackstueckBean) bean).setPositionBean(positionBean);
			}

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
					// nix zu tun
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

	@Override
	protected String getDeleteSQL() {
		if (deleteSQL == null) {
			deleteSQL = "delete from bestandspackstueck " + " where id = ?";
		}
		return deleteSQL;
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		BestandspackstueckBean bestandBean = (BestandspackstueckBean) bean;
		getDeleteStmt().setInt(1, bestandBean.getId());
		getDeleteStmt().execute();
	}

	@Override
	protected String getUpdateSQL() {
		if (updateSQL == null) {
			updateSQL = " update bestandspackstueck "
					+ " set erfassungsbenutzer=?,  " + " erfassungsdatum=?, "
					+ " aenderungsbenutzer=?, " + " aenderungsdatum=?, "
					+ " fk_artikel=?, " + " fk_position=?, " + " menge=? " +
					// " fk_mengeneinheit=?" +
					" where id = ?";
		}
		return updateSQL;
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		BestandspackstueckBean bestandBean = (BestandspackstueckBean) bean;
		getUpdateStmt().setInt(1, bestandBean.getErfassungsBenutzer().getId());
		getUpdateStmt().setDate(2, bestandBean.getErfassungsDatum());
		getUpdateStmt().setInt(3, bestandBean.getAenderungsBenutzer().getId());
		getUpdateStmt().setDate(4, bestandBean.getAenderungsDatum());
		getUpdateStmt().setInt(5, bestandBean.getArtikelBean().getId());
		getUpdateStmt().setInt(6, bestandBean.getPositionBean().getId());
		getUpdateStmt().setInt(7, bestandBean.getMenge());
		// getUpdateStmt().setInt(8,
		// bestandBean.getMengenEinheitBean().getId());
		getUpdateStmt().setInt(8, bestandBean.getId());
		getUpdateStmt().execute();
	}

	@Override
	protected String getInsertSQL() {
		if (insertSQL == null) {
			insertSQL = "insert into bestandspackstueck " + "(id, "
					+ "erfassungsbenutzer, " + "erfassungsdatum, "
					+ "aenderungsbenutzer, " + "aenderungsdatum, "
					+ "fk_artikel, " + "fk_position, " + "menge " +
					// "fk_mengeneinheit" +
					")" + " values (?,?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}

	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		BestandspackstueckBean bestandBean = (BestandspackstueckBean) bean;
		getInsertStmt().setInt(1, bestandBean.getId());
		getInsertStmt().setInt(2, bestandBean.getErfassungsBenutzer().getId());
		getInsertStmt().setDate(3, bestandBean.getErfassungsDatum());
		getInsertStmt().setInt(4, bestandBean.getAenderungsBenutzer().getId());
		getInsertStmt().setDate(5, bestandBean.getAenderungsDatum());
		getInsertStmt().setInt(6, bestandBean.getArtikelBean().getId());
		getInsertStmt().setInt(7, bestandBean.getPositionBean().getId());
		getInsertStmt().setInt(8, bestandBean.getMenge());
		// getInsertStmt().setInt(9,
		// bestandBean.getMengenEinheitBean().getId());
		getInsertStmt().executeUpdate();
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean)
			throws SQLException, LagerException {
		BestandspackstueckBean bestandBean = (BestandspackstueckBean) bean;
		bestandBean.setId(rs.getInt("id"));
		if (!bestandBean.getPositionBean().getId().equals(
				rs.getInt("fk_position"))) {
			bestandBean.setPositionBean((PositionBean) getPositionBeanDB()
					.selectAnhandID(rs.getInt("fk_position")));
		}
		bestandBean.setErfassungsDatum(rs.getDate("erfassungsdatum"));
		bestandBean.setErfassungsBenutzer((BenutzerBean) getBenutzerBeanDB()
				.selectAnhandID(rs.getInt("erfassungsbenutzer")));
		bestandBean.setAenderungsBenutzer((BenutzerBean) getBenutzerBeanDB()
				.selectAnhandID(rs.getInt("aenderungsbenutzer")));
		bestandBean.setAenderungsDatum(rs.getDate("aenderungsdatum"));
		bestandBean.setArtikelBean(getArtikelBeanDB().selectById(
				rs.getInt("fk_artikel")));
		bestandBean.setMenge(rs.getInt("menge"));
		// bestandBean.setMengenEinheitBean((MengenEinheitBean)getMengenEinheitBeanDB().selectAnhandID(rs.getInt("fk_mengeneinheit")));
		// //Achtung! Nicht aus dem Artikelstamm.
		bestandBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	protected String selectAnhandIdSQL() {
		// TODO Auto-generated method stub
		Log.log().severe("nicht implemneitert");
		return null;
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
		return new ArrayList<Bean>();
	}

	@Override
	public Bean selectAnhandID(Integer id) throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean)
			throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ZeileBean> sucheAnhandHalleBean(HalleBean halleBean)
			throws SQLException, LagerException {
		ArrayList<ZeileBean> resultList = new ArrayList<ZeileBean>();
		ResultSet rs;
		getSqlParams().clear();
		// sql bilden
		String sql = " SELECT id, nummer, fk_halle, fk_etage, fk_abteilung FROM zeile "
				+ " where fk_halle = ? " + " order by nummer";
		getSqlParams().put("fk_halle", halleBean.getId());
		// SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(sql);
		// Params setzen
		getSqlParams().setParams(stmt);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
		// baBean.loescheAlleBaPositionen();
		while (rs.next()) {
			ZeileBean zeileBean = new ZeileBean();
			erzeugeBeanAusResultSet(rs, zeileBean);
			resultList.add(zeileBean);
			Log.log().finest(zeileBean.toString());

		}
		return resultList;
	}

	public ArrayList<BestandspackstueckBean> sucheAnhandPositionBean(
			PositionBean positionBean) throws SQLException, LagerException {
		ArrayList<BestandspackstueckBean> resultList = new ArrayList<BestandspackstueckBean>();
		ResultSet rs;
		getSqlParams().clear();
		// sql bilden
		String sql = sucheAnhandPositionBeanSql();
		getSqlParams().put("fk_position", positionBean.getId());
		// SQL absetzen
		PreparedStatement stmt = sucheAnhandPositionBeanStmt();
		// Params setzen
		getSqlParams().setParams(stmt);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
		// baBean.loescheAlleBaPositionen();
		while (rs.next()) {
			BestandspackstueckBean bestandBean = new BestandspackstueckBean();
			bestandBean.setPositionBean(positionBean);
			erzeugeBeanAusResultSet(rs, bestandBean);
			resultList.add(bestandBean);
			Log.log().finest(bestandBean.toString());

		}
		return resultList;
	}

	private PreparedStatement sucheAnhandPositionBeanStmt() throws SQLException {
		if (sucheAnhandPositionBeanStmt == null) {
			sucheAnhandPositionBeanStmt = LagerConnection.getOneInstance()
					.getConnection().prepareStatement(
							sucheAnhandPositionBeanSql());
		}
		return sucheAnhandPositionBeanStmt;
	}

	private String sucheAnhandPositionBeanSql() {
		if (sucheAnhandPositionBeanSql == null) {
			sucheAnhandPositionBeanSql = " SELECT id, erfassungsbenutzer, erfassungsdatum, "
					+ " aenderungsbenutzer, aenderungsdatum, fk_artikel, "
					+ "fk_position, menge "
					+
					// "fk_mengeneinheit " +
					" FROM bestandspackstueck "
					+ " where fk_position = ? "
					+ " order by fk_artikel";
		}
		return sucheAnhandPositionBeanSql;
	}

	private PositionBeanDB getPositionBeanDB() {
		if (positionBeanDB == null) {
			positionBeanDB = new PositionBeanDB();
		}
		return positionBeanDB;
	}

	private ArtikelBeanDB getArtikelBeanDB() {
		if (artikelBeanDB == null) {
			artikelBeanDB = new ArtikelBeanDB();
		}
		return artikelBeanDB;
	}

	private BenutzerBeanDB getBenutzerBeanDB() {
		if (benutzerBeanDB == null) {
			benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	private MengenEinheitBeanDB getMengenEinheitBeanDB() {
		if (mengenEinheitBeanDB == null) {
			mengenEinheitBeanDB = new MengenEinheitBeanDB();
		}
		return mengenEinheitBeanDB;
	}

	public ArrayList<BestandspackstueckBean> sucheAnhandArtikelBean(
			ArtikelBean artikelBeanNeu) throws SQLException, LagerException {
		ArrayList<BestandspackstueckBean> resultList = new ArrayList<BestandspackstueckBean>();
		ResultSet rs;
		getSqlParams().clear();
		// sql bilden
		String sql = " SELECT id, erfassungsbenutzer, erfassungsdatum, "
				+ " aenderungsbenutzer, aenderungsdatum, fk_artikel, "
				+ " fk_position, menge "
				+
				// "fk_mengeneinheit " +
				" FROM bestandspackstueck " + " where fk_artikel = ? "
				+ " order by id ";
		getSqlParams().put("fk_artikel", artikelBeanNeu.getId());
		// SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(sql);
		// Params setzen
		getSqlParams().setParams(stmt);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
		// baBean.loescheAlleBaPositionen();
		while (rs.next()) {
			BestandspackstueckBean bestandBean = new BestandspackstueckBean();
			bestandBean.setPositionBean((PositionBean) getPositionBeanDB()
					.selectAnhandID(rs.getInt("fk_position")));
			erzeugeBeanAusResultSet(rs, bestandBean);
			resultList.add(bestandBean);
			Log.log().finest(bestandBean.toString());

		}
		return resultList;
	}

}
