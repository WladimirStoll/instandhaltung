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
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class EbeneBeanDB extends BeanDB {

	private SaeuleBeanDB saeuleBeanDB;
	private PositionBeanDB positionBeanDB;
	private HalleBeanDB halleBeanDB;
	private AbteilungBeanDB abteilungBeanDB;
	private EtageBeanDB etageBeanDB;
	private String nachsteEbeneNrSql;
	private PreparedStatement nachsteEbeneNrStmt;
	

	public EbeneBeanDB() {
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
					speichereBean((EbeneBean) bean);
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

	private void speichereBean(EbeneBean bean) throws SQLException,
			LagerException {
		speicherePositionen(bean, BeanDBStatus.OHNE_STATUS_ANGABE);
	}

	private void speicherePositionen(EbeneBean bean, BeanDBStatus beanDBStatus)
			throws SQLException, LagerException {
		for (int i = 0; i < bean.getPositionBeans().size(); i++) {
			PositionBean positionBean = bean.getPositionBeans().get(i);
			if (BeanDBStatus.DELETE.equals(beanDBStatus)) {
				positionBean.setBeanDBStatus(BeanDBStatus.DELETE);
			}
			getPositionBeanDB().saveBean(positionBean);
		}
	}

	@Override
	protected String getDeleteSQL() {
		if (deleteSQL == null) {
			deleteSQL = "delete from ebene " + " where id = ? ";
		}
		return deleteSQL;
	}

	@Override
	protected String getUpdateSQL() {
		if (updateSQL == null) {
			updateSQL = " update ebene " + " set nummer=? , fk_saeule=? "
					+ " where id = ? ";
		}
		return updateSQL;
	}

	private void updateBean(ZeileBean bean) throws SQLException, LagerException {
		try {
			// if (bean.getZugriffsrecht()==null ||
			// bean.getZugriffsrecht().getId()==0){
			// getUpdateStmt().setNull(1, java.sql.Types.INTEGER);
			// getUpdateStmt().setNull(3, java.sql.Types.INTEGER);
			// }else{
			// getUpdateStmt().setInt(1, bean.getZugriffsrecht().getId());
			// getUpdateStmt().setInt(3, bean.getZugriffsrecht().getId());
			// }
			//
			// if (bean.getBenutzer()==null || bean.getBenutzer().getId()==0){
			// getUpdateStmt().setNull(2, java.sql.Types.INTEGER);
			// getUpdateStmt().setNull(4, java.sql.Types.INTEGER);
			// }else{
			// getUpdateStmt().setInt(2, bean.getBenutzer().getId());
			// getUpdateStmt().setInt(4, bean.getBenutzer().getId());
			// }

			getUpdateStmt().execute();
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
			insertSQL = "insert into ebene " + "(id, nummer, fk_saeule)"
					+ " values (?,?,?)";
		}
		return insertSQL;
	}

	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		EbeneBean ebeneBean = (EbeneBean) bean;
		getInsertStmt().setInt(1, ebeneBean.getId());
		getInsertStmt().setInt(2, ebeneBean.getNummer());
		getInsertStmt().setInt(3, ebeneBean.getSaeuleBean().getId());
		getInsertStmt().executeUpdate();

		// Etagen und Zeilen werden gespeichert
		speicherePositionen(ebeneBean, BeanDBStatus.OHNE_STATUS_ANGABE);
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		EbeneBean ebeneBean = (EbeneBean) bean;
		speicherePositionen(ebeneBean, BeanDBStatus.DELETE);
		getDeleteStmt().setInt(1, ebeneBean.getId());
		getDeleteStmt().execute();
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		EbeneBean ebeneBean = (EbeneBean) bean;
		ebeneBean.setId(rs.getInt("id"));
		ebeneBean.setNummer(rs.getInt("nummer"));

		if (!ebeneBean.getSaeuleBean().getId().equals(rs.getInt("fk_saeule"))) {
			ebeneBean.setSaeuleBean(
					(SaeuleBean) getSaeuleBeanDB()
					.selectAnhandID(rs.getInt("fk_saeule"), depthLevel+1, null));
		}
		ebeneBean.setPositionBeans(getPositionBeanDB().sucheAnhandEbeneBean(
				ebeneBean, depthLevel+1));
		ebeneBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	@Override
	protected String selectAnhandIdSQL() {
		if (selectAnhandIdSQL == null) {
			selectAnhandIdSQL = "select id, nummer, fk_saeule from ebene where id = ?";
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
		return new ArrayList<Bean>();
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		EbeneBean resultBean;
		if (id != null && id != 0) {
			getSelectAnhandIdStmt().setInt(1, id);
			java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
			if (rs.next()) {
				resultBean = new EbeneBean();
				erzeugeBeanAusResultSet(rs, resultBean, depthLevel, null);
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
		EbeneBean ebeneBean = (EbeneBean) bean;
		getUpdateStmt().setInt(1, ebeneBean.getId());
		getUpdateStmt().setInt(2, ebeneBean.getNummer());
		getUpdateStmt().setInt(3, ebeneBean.getSaeuleBean().getId());
		getUpdateStmt().execute();

		// Etagen und ZEilen werden gespeichert
		speicherePositionen(ebeneBean, BeanDBStatus.OHNE_STATUS_ANGABE);
	}

	// public ArrayList<ZeileBean> sucheAnhandHalleBean(HalleBean halleBean)
	// throws SQLException, LagerException {
	// ArrayList<ZeileBean> resultList = new ArrayList<ZeileBean>();
	// ResultSet rs;
	// getSqlParams().clear();
	// //sql bilden
	// String sql =
	// " SELECT id, nummer, fk_halle, fk_etage, fk_abteilung FROM zeile " +
	// " where fk_halle = ? " +
	// " order by nummer";
	// getSqlParams().put("fk_halle",halleBean.getId());
	// //SQL absetzen
	// PreparedStatement stmt =
	// LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
	// //Params setzen
	// getSqlParams().setParams(stmt);
	// rs = stmt.executeQuery();
	// //ergebnisse auswerten.
	// // baBean.loescheAlleBaPositionen();
	// while(rs.next()){
	// ZeileBean zeileBean = new ZeileBean();
	// erzeugeBeanAusResultSet(rs, zeileBean);
	// resultList.add(zeileBean);
	// Log.log().finest(zeileBean.toString());
	//			
	// }
	// return resultList;
	// }

	// private HalleBeanDB getHalleBeanDB() {
	// if (halleBeanDB==null){
	// halleBeanDB = new HalleBeanDB();
	// }
	// return halleBeanDB;
	// }

	// private AbteilungBeanDB getAbteilungBeanDB() {
	// if (abteilungBeanDB==null){
	// abteilungBeanDB = new AbteilungBeanDB();
	// }
	// return abteilungBeanDB;
	// }

	// private EtageBeanDB getEtageBeanDB() {
	// if (etageBeanDB==null){
	// etageBeanDB = new EtageBeanDB();
	// }
	// return etageBeanDB;
	// }

	public ArrayList<EbeneBean> sucheAnhandSaeuleBean(SaeuleBean saeuleBean, int depthLevel)
			throws SQLException, LagerException {
		ArrayList<EbeneBean> resultList = new ArrayList<EbeneBean>();
		ResultSet rs;
		getSqlParams().clear();
		// sql bilden
		String sql = " SELECT id, nummer, fk_saeule FROM ebene "
				+ " where fk_saeule = ? " + " order by nummer";
		getSqlParams().put("fk_saeule", saeuleBean.getId());
		// SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(sql);
		// Params setzen
		getSqlParams().setParams(stmt);
		rs = stmt.executeQuery();
		// ergebnisse auswerten.
		// baBean.loescheAlleBaPositionen();
		while (rs.next()) {
			EbeneBean ebeneBean = new EbeneBean();
			ebeneBean.setSaeuleBean(saeuleBean);
			erzeugeBeanAusResultSet(rs, ebeneBean, depthLevel, null);
			resultList.add(ebeneBean);
			Log.log().finest(ebeneBean.toString());

		}
		return resultList;
	}

	private SaeuleBeanDB getSaeuleBeanDB() {
		if (saeuleBeanDB == null) {
			saeuleBeanDB = new SaeuleBeanDB();
		}
		return saeuleBeanDB;
	}

	private PositionBeanDB getPositionBeanDB() {
		if (positionBeanDB == null) {
			positionBeanDB = new PositionBeanDB();
		}
		return positionBeanDB;
	}

	public Integer getNachsteNummer(SaeuleBean saeuleBean) throws SQLException {
		getNachsteEbeneNrStmt().setInt(1, saeuleBean.getId());
		java.sql.ResultSet rs = getNachsteEbeneNrStmt().executeQuery();
		rs.next();
		Integer posNummer = rs.getInt("naechstePosNummer");//rs.getInt("naechstePosNummer");
		if (posNummer.equals(0)){
			posNummer = 1;
		}
		return posNummer;
	}

	protected String getNachsteEbeneNrSql() {
		if (nachsteEbeneNrSql==null){
			nachsteEbeneNrSql = "select max(nummer)+1 as naechstePosNummer from ebene e " +
					"where e.fk_saeule = ?";
		}
		return nachsteEbeneNrSql;
	}

	protected PreparedStatement getNachsteEbeneNrStmt() throws SQLException {
		if (nachsteEbeneNrStmt == null) {
			nachsteEbeneNrStmt = LagerConnection.getOneInstance()
					.getConnection().prepareStatement(getNachsteEbeneNrSql());						
		}
		return nachsteEbeneNrStmt;
	}
	
	// private MengenEinheitBeanDB getMengenEinheitBeanDB() {
	// if(mengenEinheitBeanDB==null){
	// mengenEinheitBeanDB = new MengenEinheitBeanDB();
	// }
	// return mengenEinheitBeanDB;
	// }

}
