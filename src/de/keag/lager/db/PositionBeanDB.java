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
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class PositionBeanDB extends BeanDB {

	private HalleBeanDB halleBeanDB;
	private AbteilungBeanDB abteilungBeanDB;
	private EtageBeanDB etageBeanDB;
	private EbeneBeanDB ebeneBeanDB;
	private BestandspackstueckBeanDB bestandspackstueckBeanDB;
	private String selectAnhandNummerSql;
	private PreparedStatement selectAnhandNummerStmt;
	private String nachstePosNrSql;
	private PreparedStatement nachstePosNrStmt;

	public PositionBeanDB() {
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
					speichereBean((PositionBean) bean);
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

	private void speichereBean(PositionBean bean) throws SQLException,
			LagerException {
		speichereBestandspackstuecke(bean, BeanDBStatus.OHNE_STATUS_ANGABE);

	}

	private void speichereBestandspackstuecke(PositionBean bean,
			BeanDBStatus beanDBStatus) throws SQLException, LagerException {
		for (int i = 0; i < bean.getBestandspackstueckBeans().size(); i++) {
			BestandspackstueckBean bestandBean = bean
					.getBestandspackstueckBeans().get(i);
			if (BeanDBStatus.DELETE.equals(beanDBStatus)) {
				bestandBean.setBeanDBStatus(BeanDBStatus.DELETE);
			}
			getBestandspackstueckBeanDB().saveBean(bestandBean);
		}
	}

	@Override
	protected String getDeleteSQL() {
		if (deleteSQL == null) {
			deleteSQL = "delete from position " + " where id =  ?";
		}
		return deleteSQL;
	}

	@Override
	protected String getUpdateSQL() {
		if (updateSQL == null) {
			updateSQL = " update position " + " set nummer=?, fk_ebene=? "
					+ " where id = ? ";
		}
		return updateSQL;
	}

	private void updateBean(PositionBean bean) throws SQLException,
			LagerException {
		try {
			PositionBean posBean = (PositionBean) bean;
			getUpdateStmt().setInt(1, posBean.getId());
			getUpdateStmt().setInt(2, posBean.getNummer());
			getUpdateStmt().setInt(3, posBean.getEbeneBean().getId());
			getUpdateStmt().execute(); // ausführen
			try {
				// Etagen und ZEilen werden gespeichert
				speichereBestandspackstuecke(posBean,
						BeanDBStatus.OHNE_STATUS_ANGABE);
			} finally {
				getUpdateStmt().close();
			}
		} catch (SQLException e) {
			Log.log().severe(e.getMessage());
			Log.log().severe(getUpdateSQL());
			throw e;
		}
		// bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	protected String getInsertSQL() {
		if (insertSQL == null) {
			insertSQL = "insert into position " + "(id, nummer , fk_ebene)"
					+ " values (?,?,?)";
		}
		return insertSQL;
	}

	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		PositionBean posBean = (PositionBean) bean;

		getInsertStmt().setInt(1, posBean.getId());
		getInsertStmt().setInt(2, posBean.getNummer());
		getInsertStmt().setInt(3, posBean.getEbeneBean().getId());
		getInsertStmt().executeUpdate();

		speichereBestandspackstuecke(posBean, BeanDBStatus.OHNE_STATUS_ANGABE);
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		try {
			PositionBean posBean = (PositionBean) bean;
			speichereBestandspackstuecke(posBean, BeanDBStatus.DELETE);
			getDeleteStmt().setInt(1, posBean.getId());
			getDeleteStmt().execute();
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			throw new LagerException(new Fehler(25, FehlerTyp.FEHLER, e
					.getMessage()));
		}
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		PositionBean positionBean = (PositionBean) bean;
		positionBean.setId(rs.getInt("id"));
		positionBean.setNummer(rs.getInt("nummer"));
		if (!positionBean.getEbeneBean().getId().equals(rs.getInt("fk_ebene"))) {
			positionBean.setEbeneBean((EbeneBean) getEbeneBeanDB()
					.selectAnhandID(rs.getInt("fk_ebene"), depthLevel+1, depthLevelArrayDB));
		}
		positionBean.setBestandspackstueckBeans(getBestandspackstueckBeanDB()
				.sucheAnhandPositionBean(positionBean, depthLevel+1, depthLevelArrayDB));

		// zeileBean.setHalleBean((HalleBean)getHalleBeanDB().selectAnhandID(rs.getInt("fk_halle")));
		// zeileBean.setAbteilungBean((AbteilungBean)getAbteilungBeanDB().selectAnhandID(rs.getInt("fk_abteilung")));
		// zeileBean.setEtageBean((EtageBean)getEtageBeanDB().selectAnhandID(rs.getInt("fk_etage")));
		positionBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	protected String selectAnhandIdSQL() {
		if (selectAnhandIdSQL == null) {
			selectAnhandIdSQL = "select id, nummer, fk_ebene from position where id = ?";
		}
		return selectAnhandIdSQL;
	}

	@Override
	protected String getSelectSQL() {
		if (selectSQL == null) {
			selectSQL = "select id, nummer, fk_ebene from position order by nummer";
		}
		return selectSQL;
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
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		PositionBean resultBean;
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return null;
			}
		}
		
		if (id != null && id != 0) {
			getSelectAnhandIdStmt().setInt(1, id);
			java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
			if (rs.next()) {
				resultBean = new PositionBean();
				erzeugeBeanAusResultSet(rs, resultBean, depthLevel, depthLevelArrayDB);
				return resultBean;
			} else {
				throw new LagerException(new Fehler(6));// Benutzer kann nicht
				// gelesen werden.
			}
		} else {
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

	public ArrayList<ZeileBean> sucheAnhandHalleBean(HalleBean halleBean, int depthLevel)
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
			erzeugeBeanAusResultSet(rs, zeileBean, depthLevel, null);
			resultList.add(zeileBean);
			Log.log().finest(zeileBean.toString());

		}
		return resultList;
	}

	private HalleBeanDB getHalleBeanDB() {
		if (halleBeanDB == null) {
			halleBeanDB = new HalleBeanDB();
		}
		return halleBeanDB;
	}

	private AbteilungBeanDB getAbteilungBeanDB() {
		if (abteilungBeanDB == null) {
			abteilungBeanDB = new AbteilungBeanDB();
		}
		return abteilungBeanDB;
	}

	private EtageBeanDB getEtageBeanDB() {
		if (etageBeanDB == null) {
			etageBeanDB = new EtageBeanDB();
		}
		return etageBeanDB;
	}

	public ArrayList<PositionBean> sucheAnhandEbeneBean(EbeneBean ebeneBean, int depthLevel)
			throws SQLException, LagerException {
		ArrayList<PositionBean> resultList = new ArrayList<PositionBean>();
		ResultSet rs;
		getSqlParams().clear();
		// sql bilden
		String sql = " SELECT id, nummer, fk_ebene FROM position "
				+ " where fk_ebene = ? " + " order by nummer";
		getSqlParams().put("fk_ebene", ebeneBean.getId());
		// SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(sql);
		// Params setzen
		getSqlParams().setParams(stmt);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
		// baBean.loescheAlleBaPositionen();
		while (rs.next()) {
			PositionBean positionBean = new PositionBean();
			positionBean.setEbeneBean(ebeneBean);
			erzeugeBeanAusResultSet(rs, positionBean, depthLevel, null);
			resultList.add(positionBean);
			Log.log().finest(positionBean.toString());

		}
		return resultList;
	}

	private EbeneBeanDB getEbeneBeanDB() {
		if (ebeneBeanDB == null) {
			ebeneBeanDB = new EbeneBeanDB();
		}
		return ebeneBeanDB;
	}

	private BestandspackstueckBeanDB getBestandspackstueckBeanDB() {
		if (bestandspackstueckBeanDB == null) {
			bestandspackstueckBeanDB = new BestandspackstueckBeanDB();
		}
		return bestandspackstueckBeanDB;
	}

	public PositionBean lesePositionAnhandNummer(Integer positionNummer,
			Integer ebeneNummer, Integer saeuleNummer, Integer zeileNummer,
			Integer halleId) throws LagerException, SQLException {

		if ((positionNummer == null) || (ebeneNummer == null)
				|| (saeuleNummer == null) || (zeileNummer == null)
				|| (halleId == null)) {
			throw new LagerException(new Fehler(117));
		}

		if ((positionNummer.equals(0)) || (ebeneNummer.equals(0))
				|| (saeuleNummer.equals(0)) || (zeileNummer.equals(0))
				|| (halleId.equals(0))) {
			throw new LagerException(new Fehler(117));
		}

		PositionBean resultBean;
		getSelectAnhandNummerStmt().setInt(1, positionNummer);
		getSelectAnhandNummerStmt().setInt(2, ebeneNummer);
		getSelectAnhandNummerStmt().setInt(3, saeuleNummer);
		getSelectAnhandNummerStmt().setInt(4, zeileNummer);
		getSelectAnhandNummerStmt().setInt(5, halleId);
		java.sql.ResultSet rs = getSelectAnhandNummerStmt().executeQuery();
		if (rs.next()) {
			resultBean = new PositionBean();
			erzeugeBeanAusResultSet(rs, resultBean, 0, null);
			return resultBean;
		} else {
			throw new LagerException(new Fehler(118));// Benutzer kann nicht
			// gelesen werden.
		}
	}

	protected String getSelectAnhandNummerSQL() {
		if (selectAnhandNummerSql == null) {
			selectAnhandNummerSql = " SELECT  " 
				    + " p.id as id, "
					+ " p.nummer as nummer," 
					+ " p.fk_ebene as fk_ebene "
					+ " FROM position p, ebene e, saeule s, zeile z, halle h "
					+ " where p.fk_ebene = e.id" 
					+ " and e.fk_saeule = s.id "
					+ " and s.fk_zeile = z.id" 
					+ " and z.fk_halle = h.id "
					+ " and p.nummer = ?" 
					+ " and e.nummer = ? "
					+ " and s.nummer = ?" 
					+ " and z.nummer = ? " 
					+ " and h.id = ?";
		}
		return selectAnhandNummerSql;
	}

	protected PreparedStatement getSelectAnhandNummerStmt() throws SQLException {
		if (selectAnhandNummerStmt == null) {
			selectAnhandNummerStmt = LagerConnection.getOneInstance()
					.getConnection().prepareStatement(getSelectAnhandNummerSQL());
		}
		return selectAnhandNummerStmt;
	}

	public Integer getNachsteNummer(EbeneBean ebeneBean) throws SQLException {
		getNachstePosNrStmt().setInt(1, ebeneBean.getId());
		java.sql.ResultSet rs = getNachstePosNrStmt().executeQuery();
		rs.next();
		Integer posNummer = rs.getInt("naechstePosNummer");//rs.getInt("naechstePosNummer");
		if (posNummer.equals(0)){
			posNummer = 1;
		}
		return posNummer;
	}

	protected String getNachstePosNrSql() {
		if (nachstePosNrSql==null){
			nachstePosNrSql = "select max(nummer)+1 as naechstePosNummer from position p " +
					"where p.fk_ebene = ?";
		}
		return nachstePosNrSql;
	}

	protected PreparedStatement getNachstePosNrStmt() throws SQLException {
		if (nachstePosNrStmt == null) {
			nachstePosNrStmt = LagerConnection.getOneInstance()
					.getConnection().prepareStatement(getNachstePosNrSql());						
		}
		return nachstePosNrStmt;
	}

	// private MengenEinheitBeanDB getMengenEinheitBeanDB() {
	// if(mengenEinheitBeanDB==null){
	// mengenEinheitBeanDB = new MengenEinheitBeanDB();
	// }
	// return mengenEinheitBeanDB;
	// }

}
