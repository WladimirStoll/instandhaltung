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
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class HalleBeanDB extends BeanDB{
	
	EtageBeanDB etageBeanDB = null;
	ZeileBeanDB zeileBeanDB = null;
	
	@Override
	protected String selectAnhandIdSQL() {
		if(selectAnhandIdSQL==null){
			selectAnhandIdSQL = "select id, name, nummer from halle where id=?";
		}
		return selectAnhandIdSQL;
	}
	
	@Override
	protected String getUpdateSQL() {
		return "update halle set " +
			" name = ? , nummer = ? " +
			" where id = ? ";
	}
	
	public HalleBeanDB() {
		super();
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		HalleBean resultBean;
		
		if (depthLevelArrayDB!=null){
			if (!depthLevelArrayDB.isDepthLevelOK(this.getClass(),depthLevel)){
				return null;
			}
		}
			
		
		if (id!=null&&id!=0){
				getSelectAnhandIdStmt().setInt(1, id);
				java.sql.ResultSet rs = getSelectAnhandIdStmt().executeQuery();
				if (rs.next()){
					resultBean = new HalleBean();
					erzeugeBeanAusResultSet(rs, resultBean, depthLevel, depthLevelArrayDB);
					resultBean.setBeanDBStatus(BeanDBStatus.SELECT);
					return resultBean;
				}else{
					throw new LagerException(new Fehler(6));//Benutzer kann nicht gelesen werden.
				}
		}else{
			return null;
		}
	}
	
	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		HalleBean HalleBean = (HalleBean) bean;
		if (HalleBean!=null){
			if (HalleBean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean(HalleBean);
			}else if (HalleBean.getBeanDBStatus() == BeanDBStatus.SELECT){
				speichereBean(HalleBean);
			}else if (HalleBean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean(HalleBean);
			}else if (HalleBean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(HalleBean);
			}else if (HalleBean.getBeanDBStatus() == BeanDBStatus.FEHLERHAFT){
				throw new LagerException(new Fehler(4));
			}else{
				throw new LagerException(new Fehler(3));
			}
		}else{
			throw new LagerException(new Fehler(2));
		}
	}

	private void speichereBean(HalleBean halleBean) throws SQLException, LagerException {
		//Etagen und ZEilen werden gespeichert
		SpeichereEtagenUndZeilen(halleBean);
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		HalleBean halleBean = (HalleBean) bean;
		
		//Alle untergeordneten Objekt als gelöscht markieren.
		for(int i = 0; i<halleBean.getEtageBeans().size();i++){
			EtageBean etageBean = halleBean.getEtageBeans().get(i);
			etageBean.setBeanDBStatus(BeanDBStatus.DELETE);
		}
		
		for(int i = 0; i<halleBean.getZeileBeans().size();i++){
			ZeileBean zeileBean = halleBean.getZeileBeans().get(i);
			zeileBean.setBeanDBStatus(BeanDBStatus.DELETE);
		}
		SpeichereEtagenUndZeilen(halleBean);
		
		getDeleteStmt().setInt(1, halleBean.getId());
		getDeleteStmt().executeUpdate();
		
	}

	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		HalleBean halleBean = (HalleBean)bean;
//		getUpdateStmt().setDate(1, bean.getAktuellesDatum());
//		getUpdateStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
		getUpdateStmt().setString(1, halleBean.getName());
		getUpdateStmt().setInt(2, halleBean.getId());
		getUpdateStmt().setInt(3, halleBean.getNummer());
		getUpdateStmt().execute();
		
		//Etagen und ZEilen werden gespeichert
		SpeichereEtagenUndZeilen((HalleBean)halleBean);
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into halle " +
					"(id, name, nummer) " +
					" values (?,?,?)";
		}
		return insertSQL;
	}
//	@Override
//	protected String getInsertSQL() {
//		if(insertSQL==null){
//			insertSQL = "insert into benutzer " +
//					"(id, name) " +
//					" values (?,?)";
//		}
//		return insertSQL;
//	}
	
	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		HalleBean halleBean = (HalleBean) bean;
		getInsertStmt().setInt(1, halleBean.getId());
		getInsertStmt().setString(2, halleBean.getName());
		getInsertStmt().setInt(3, halleBean.getNummer());
		getInsertStmt().executeUpdate();

		//Etagen und ZEilen werden gespeichert
		SpeichereEtagenUndZeilen(halleBean);
	}

	/**
	 * Anhand der HallenBean wernden Etagen und Zeilen gespeichert
	 * @param halleBean
	 * @throws SQLException
	 * @throws LagerException
	 */
	private void SpeichereEtagenUndZeilen(HalleBean halleBean)
			throws SQLException, LagerException {
		for(int i = 0; i<halleBean.getEtageBeans().size();i++){
			EtageBean etageBean = halleBean.getEtageBeans().get(i);
			getEtageBeanDB().saveBean(etageBean);
		}
		
		for(int i = 0; i<halleBean.getZeileBeans().size();i++){
			ZeileBean zeileBean = halleBean.getZeileBeans().get(i);
			getZeileBeanDB().saveBean(zeileBean);
		}
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, name, nummer" +
				" from halle order by name ";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			HalleBean bean = new HalleBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			bean.setBeanDBStatus(BeanDBStatus.SELECT);
			Log.log().finest(bean.toString());
			resultList.add(bean);
		}
		return resultList;
	}

	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)	throws SQLException, LagerException {
		HalleBean halleBean = (HalleBean) bean;
		halleBean.setId(rs.getInt("id"));
		halleBean.setName(rs.getString("name"));
		halleBean.setNummer(rs.getInt("nummer"));
		
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)throws SQLException, LagerException {
		getSqlParams().clear();
		//falls, su
		if(iSuchBean==null){
			iSuchBean = new HalleSuchBean();
		}
		HalleSuchBean halleSuchBean = (HalleSuchBean)iSuchBean; 
		
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		
		String sql = "  SELECT distinct " +
		"  halle.id as id, " +
		"  halle.nummer as nummer, " +
		"  halle.name as name " +
		"  FROM halle " +
		"		  left join (etage) " +
		"		    on (halle.id = etage.fk_halle) " +
		"		  left join (zeile) " +
		"		    on (halle.id = zeile.fk_halle) " +
		"		  left join (saeule) " +
		"		    on (zeile.id = saeule.fk_zeile) " +
		"		  left join (ebene) " +
		"		    on (saeule.id = ebene.fk_saeule) " +
		"		  left join (position) " +
		"		    on (ebene.id = position.fk_ebene) " +
		"		  left join (bestandspackstueck) " +
		"		    on (position.id = bestandspackstueck.fk_position) " +
		"		  left join (abteilung) " +
		"		    on (abteilung.id = zeile.fk_abteilung) " ;
		String where = 	"		where 1 = 1 ";
		
		if (!halleSuchBean.getAbteilungBean().getId().equals(0)){
//			"       and abteilung.id = 1
			where = where + " and abteilung.id = ?";
			getSqlParams().put("abteilung.id", halleSuchBean.getAbteilungBean().getId());
		}
		if (!halleSuchBean.getHalleBean().getId().equals(0)){
//			"		and halle.id = 1 " +
			where = where + " and halle.id = ?";
			getSqlParams().put("halle.id", halleSuchBean.getHalleBean().getId());
		}
		if (!halleSuchBean.getEtageBean().getId().equals(0)){
//			"		and etage.id = 2 " +
			where = where + " and etage.id = ?";
			getSqlParams().put("etage.id", halleSuchBean.getEtageBean().getId());
		}
		if (!halleSuchBean.getZeileBean().getNummer().equals(0)){
//			"		and zeile.id = 1 " +
			where = where + " and zeile.id = ?";
			getSqlParams().put("zeile.id", halleSuchBean.getZeileBean().getNummer());
		}
		if (!halleSuchBean.getSaeuleBean().getNummer().equals(0)){
//			"		and saeule.id = 1 " +
			where = where + " and saeule.id = ?";
			getSqlParams().put("saeule.id", halleSuchBean.getSaeuleBean().getNummer());
		}
		if (!halleSuchBean.getEbeneBean().getNummer().equals(0)){
//			"		and ebene.id = 1 " +
			where = where + " and ebene.id = ?";
			getSqlParams().put("ebene.id", halleSuchBean.getEbeneBean().getNummer());
		}
		if (!halleSuchBean.getPositionBean().getNummer().equals(0)){
//			"		and position.id = 1 " +
			where = where + " and position.id = ?";
			getSqlParams().put("position.id", halleSuchBean.getPositionBean().getNummer());
		}
		if (!halleSuchBean.getArtikelBean().getId().equals(0)){
//			"		and bestandspackstueck.id = 1 " +
			where = where + " and bestandspackstueck.fk_artikel = ?";
			getSqlParams().put("bestandspackstueck.fk_artikel", halleSuchBean.getArtikelBean().getId());
		}
		
		sql = sql + where + " order by name "; 
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		
		//Paramete setzen
		getSqlParams().setParams(stmt);
		//Protokoll
	    Log.log().finest(stmt.toString());
		
		
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			HalleBean bean = new HalleBean();
			erzeugeBeanAusResultSet(rs, bean, depthLevel, null);
			bean.setBeanDBStatus(BeanDBStatus.SELECT);
			resultList.add(bean);
		}
		
		return resultList;
	}

	@Override
	protected String getDeleteSQL() {
		if(deleteSQL==null){
			deleteSQL = "delete from halle " +
					" where id = ? ";
		}
		return deleteSQL;
	}

	@Override
	protected String getSelectSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	private EtageBeanDB getEtageBeanDB() {
		if (etageBeanDB==null){
			etageBeanDB = new EtageBeanDB();
		}
		return etageBeanDB;
	}

	private ZeileBeanDB getZeileBeanDB() {
		if (zeileBeanDB==null){
			zeileBeanDB = new ZeileBeanDB();
		}
		return zeileBeanDB;
	}

}
