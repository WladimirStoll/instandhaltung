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
import java.util.Iterator;
import java.util.Map;


import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Main;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.connection.SqlParam;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtSuchBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class BerichtBeanDB extends BeanDB {
	
	
	private BenutzerBeanDB benutzerBeanDB;
	
	
	@Override
	protected String getDeleteSQL() {
		return deleteSQL;
	}

	public BerichtBeanDB(){
		super();
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException{
		if (bean!=null){
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean((BerichtBean)bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean((BerichtBean)bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
				//es passiert nix. Wir müssen die Bean nicht speichern
			}else if (bean.getBeanDBStatus() == BeanDBStatus.DELETE){
				deleteBean(bean);
			}else{
				throw new LagerException(new Fehler(4,FehlerTyp.FEHLER,bean.getBeanDBStatus().toString()));
			}
		}else{
			throw new LagerException(new Fehler(2));
		}
	}

	@Override
	public void deleteBean(Bean bean) throws LagerException {
		throw new LagerException(new Fehler(4));
	}


	
	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
			updateSQL = " update bericht set " +
					" aenderungsbenutzer=?, " +
					" aenderungsdatum=?, " +
					" TYP=?, " +
					" benutzer=?, " +
					" art=?, " +
					" druckdatumoriginal=?, " +
					" druckdatumkopie=?, " +
					" email=?, " +
					" typID=? " +
					" where id = ? " + //where
					"";
		}
		return updateSQL;
	}

	
	@Override
	protected void updateBean(Bean bean) throws SQLException {
		BerichtBean berichtBean = (BerichtBean)bean;
		getUpdateStmt().setInt(1, Run.getOneInstance().getBenutzerBean().getId());
		getUpdateStmt().setDate(2, Bean.getAktuellesDatum());
		getUpdateStmt().setString(3, berichtBean.getBerichtTyp().JavaToDB());
		getUpdateStmt().setInt(4, berichtBean.getAktuellerBenutzer().getId());
		getUpdateStmt().setString(5, berichtBean.getBerichtArt().JavaToDB());
		getUpdateStmt().setDate(6, berichtBean.getDruckDatumOriginal());
		getUpdateStmt().setDate(7, berichtBean.getDruckDatumKopie());
		getUpdateStmt().setString(8, berichtBean.getEMail());
		getUpdateStmt().setInt(9, berichtBean.getId());
		
//		if (((BerichtBean)bean).getLhBean()!=null && ((BerichtBean)bean).getLhBean().getId()!=0)
//			getUpdateStmt().setInt(3, ((BerichtBean)bean).getLhBean().getId());
//		else getUpdateStmt().setNull(3, java.sql.Types.INTEGER);
		
		getUpdateStmt().execute();
		
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	
	@Override
	protected void selectBean(Bean bean) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = " insert into bericht " +
					" (id, " +
					" erfassungsbenutzer, " +
					" erfassungsdatum, " +
					" aenderungsbenutzer, " +
					" aenderungsdatum, " +
					" TYP, " +
					" benutzer, " +
					" art, " +
					" druckdatumoriginal, " +
					" druckdatumkopie, " +
					" email, " +
					"typID) " +
					" values (?,?,?,?,?,?,?,?,?,?,?,?) ";
		}
		return insertSQL;
	}

	@Override
	protected void insertBean(Bean bean) throws SQLException {
		BerichtBean berichtBean = (BerichtBean)bean;
		getInsertStmt().setInt(1, berichtBean.getId());
		getInsertStmt().setInt(2, Run.getOneInstance().getBenutzerBean().getId());
//		getInsertStmt().setInt(2, berichtBean.getErfassungsBenutzer().getId());
		getInsertStmt().setDate(3, Bean.getAktuellesDatum());
//		getInsertStmt().setDate(3, berichtBean.getErfassungsDatum());
		getInsertStmt().setNull(4, java.sql.Types.INTEGER); //Änderungsbenutzer
		getInsertStmt().setNull(5, java.sql.Types.DATE); //Änderungsdatum
		getInsertStmt().setString(6, berichtBean.getBerichtTyp().JavaToDB());
		getInsertStmt().setInt(7, Run.getOneInstance().getBenutzerBean().getId());//aktueller Benutzer
		getInsertStmt().setString(8, berichtBean.getBerichtArt().JavaToDB());
		getInsertStmt().setDate(9, berichtBean.getDruckDatumOriginal());
		getInsertStmt().setDate(10, berichtBean.getDruckDatumKopie());
		getInsertStmt().setString(11, berichtBean.getEMail());
		getInsertStmt().setInt(12, berichtBean.getBerichtTypId()); //z.B. Anforderung-ID
		getInsertStmt().execute();
		
//		if (((BerichtBean)bean).getLhBean()!=null && ((BerichtBean)bean).getLhBean().getId()!=0)
//			 getInsertStmt().setInt(4, ((BerichtBean)bean).getLhBean().getId());
//		else getInsertStmt().setNull(4, java.sql.Types.INTEGER);
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	
	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = " select " +            //select
					" id, " +
					" erfassungsbenutzer, " +
					" erfassungsdatum, " +
					" aenderungsbenutzer, " +
					" aenderungsdatum, " +
					" TYP, " +
					" benutzer, " +
					" art, " +
					" druckdatumoriginal, " +
					" druckdatumkopie, " +
					" email," +
					" typID " +
					" from  bericht " +         //From
					" where id=?" + //where
					" order by id desc "; //Rückwärts sortieren        
		}
		return selectSQL;
	}
	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		BerichtBean resultBean = new BerichtBean();
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
			throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Bestellung nicht gefunden"));
		}
		return resultBean;	
	}
	
	@Override
	protected String selectAnhandIdSQL() {
		// TODO Auto-generated method stub
		return  " select" +
				" id, " +
				" erfassungsbenutzer, " +
				" erfassungsdatum, " +
				" aenderungsbenutzer, " +
				" aenderungsdatum, " +
				" TYP, " +
				" benutzer, " +
				" art, " +
				" druckdatumoriginal, " +
				" druckdatumkopie, " +
				" email, " +
				" typID " +
				" from bericht where id=?";
	}
	
	
	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		BerichtBean resultBean = (BerichtBean) bean;
		
		resultBean.setId(rs.getInt("id"));
		
		//Benutzer dazulesen
		BenutzerBean benutzerBean = (BenutzerBean) getBenutzerBeanDB().selectAnhandID(rs.getInt("erfassungsbenutzer"), 0, null);
		resultBean.setErfassungsBenutzer(benutzerBean);
		//Erfassungdatum
		resultBean.setErfassungsDatum(rs.getDate("erfassungsdatum"));
		
		//Benutzer dazulesen
		benutzerBean = (BenutzerBean) getBenutzerBeanDB().selectAnhandID(rs.getInt("aenderungsbenutzer"), 0, null);
		resultBean.setErfassungsBenutzer(benutzerBean);
		//Ändeerungsatum
		resultBean.setErfassungsDatum(rs.getDate("aenderungsdatum"));
		
		resultBean.setBerichtTyp(Berichttyp.DbToJava(rs.getString("TYP")));
		
		//Benutzer dazulesen
		benutzerBean = (BenutzerBean) getBenutzerBeanDB().selectAnhandID(rs.getInt("benutzer"), 0, null);
		resultBean.setAktuellerBenutzer(benutzerBean);
		
		resultBean.setBerichtArt(Berichtart.DbToJava(rs.getString("art")));

		resultBean.setDruckDatumOriginal(rs.getDate("druckdatumoriginal"));
		resultBean.setDruckDatumKopie(rs.getDate("druckdatumkopie"));
		
		resultBean.setEMail(rs.getString("email"));
		
		resultBean.setBerichtTypId(rs.getInt("typID"));
	}

	
	
	private BenutzerBeanDB getBenutzerBeanDB() {
		if (benutzerBeanDB == null) {
			benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	
	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		getSqlParams().clear();
		BerichtSuchBean suchBean = (BerichtSuchBean) iSuchBean;
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = " select " + //select
				" id, " +
				" erfassungsbenutzer, " +
				" erfassungsdatum, " +
				" aenderungsbenutzer, " +
				" aenderungsdatum, " +
				" TYP, " +
				" benutzer, " +
				" art, " +
				" druckdatumoriginal, " +
				" druckdatumkopie, " +
				" email," +
				" typID " +
				" from bericht " + //from
				" where 1 = 1 " ;
		if (suchBean.getId() != null && !suchBean.getId().equals(0)) {
			sql = sql + " and id=? ";
			getSqlParams().put("id", suchBean.getId().toString());
		}
		//Typ
		switch (suchBean.getBerichtTyp()) {
		case ALLE:
			break;
		default: {
			sql = sql + " and TYP = ? ";
			getSqlParams().put("TYP", suchBean.getBerichtTyp().JavaToDB());
		}
		}
		;
		//Typ-ID
		if (suchBean.getBerichtTypId() != null && !suchBean.getBerichtTypId().equals(0)) {
			sql = sql + " and typID=? ";
			getSqlParams().put("typID", suchBean.getBerichtTypId().toString());
		}
		
		//Benutzer
		if (suchBean.getAktuellerBenutzer() != null
				&& !suchBean.getAktuellerBenutzer().getId().equals(0)) {
			sql = sql + " and benutzer=? ";
			getSqlParams().put("benutzer",
					suchBean.getAktuellerBenutzer().getId().toString());
		}
		//art
		switch (suchBean.getBerichtArt()) {
		case ALLE:
			break;
		default: {
			sql = sql + " and art = ? ";
			getSqlParams().put("art", suchBean.getBerichtArt().JavaToDB());
		}
		}
		;
		//Druckdatum original von
		if (suchBean.getDruckDatumOriginalVon().getTime() != 0){
			sql = sql + " and druckdatumoriginal >= ? ";
			getSqlParams().put("druckdatumoriginalvon", suchBean.getDruckDatumOriginalVon());
		}
		//Druckdatum original bis
		if (suchBean.getDruckDatumOriginalBis().getTime() != 0){
			sql = sql + " and druckdatumoriginal <= ? ";
			getSqlParams().put("druckdatumoriginalbis", suchBean.getDruckDatumOriginalBis());
		}
		//Druckdatum druckdatumkopie von
		if (suchBean.getDruckDatumOriginalVon().getTime() != 0){
			sql = sql + " and druckdatumkopie >= ? ";
			getSqlParams().put("druckdatumkopievon", suchBean.getDruckDatumOriginalVon());
		}
		//Druckdatum druckdatumkopie bis
		if (suchBean.getDruckDatumOriginalBis().getTime() != 0){
			sql = sql + " and druckdatumkopie <= ? ";
			getSqlParams().put("druckdatumkopiebis", suchBean.getDruckDatumOriginalBis());
		}
		
		sql = sql + " order by id desc";
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		//Fragezeichen durch werte ersetzen
		
		//Paramete setzen
		getSqlParams().setParams(stmt);
	    Log.log().finest(stmt.toString());
		
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			BerichtBean baBean = new BerichtBean();
			erzeugeBeanAusResultSet(rs, baBean, 0, null);
//			baBean.setId(rs.getInt("id"));
//			baBean.setErstellungsDatum(rs.getDate("erstellungsdatum"));
//			baBean.setStatus(AnforderungStatus.DbToJava(rs.getString("status")));
//			baBean.setLhBean(getHerstellerLieferantBeanDB().selectById(rs.getInt("fk_herstellerLieferant")));
//			baBean.setFk_benutzerAbsender((BenutzerBean)getBenutzerBeanDB().sucheAnhandID(rs.getInt("fk_benutzerAbsender")));
//			baBean.setFk_benutzerAnnehmer((BenutzerBean)getBenutzerBeanDB().sucheAnhandID(rs.getInt("fk_benutzerAnnehmer")));
//			baBean.setEmail(rs.getString("email_ba_empfaenger"));
			
			baBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
			Log.log().finest(baBean.toString());
			resultList.add(baBean);
		}
		return resultList;
	}

	


	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return new ArrayList<Bean>();
	}


}
