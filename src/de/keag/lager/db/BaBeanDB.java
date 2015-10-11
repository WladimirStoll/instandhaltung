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
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.BaSuchBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class BaBeanDB extends BeanDB {
	
	private BaPosBeanDB baPosBeanDB;
	
	private LhBeanDB herstellerLieferantBeanDB;
	private BenutzerBeanDB benutzerBeanDB;
	
	
	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = "select id, name, vorname, loginName, password, email_ba_empfaenger from benutzer where loginName=?";
		}
		return selectSQL;
	}
	
	
	@Override
	protected String getDeleteSQL() {
		return deleteSQL;
	}

	@Override
	protected String getUpdateSQL() {
		if(updateSQL==null){
			updateSQL = "update bestellanforderung " +
					"set erstellungsdatum=?, status=?, fk_herstellerLieferant=?, " +
					"fk_benutzerAbsender=?, fk_benutzerAnnehmer=?, " +
					"email_ba_empfaenger=?" +
					" where id = ? ";
		}
		return updateSQL;
	}

	@Override
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into bestellanforderung " +
					"(id, erstellungsdatum, status, fk_herstellerLieferant, " +
					"fk_benutzerAbsender, fk_benutzerAnnehmer, email_ba_empfaenger)" +
					" values (?,?,?,?,?,?,?)";
		}
		return insertSQL;
	}

	

	public BaBeanDB(){
		super();
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException{
		if (bean!=null){
			if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
				insertBean((BaBean)bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
				updateBean((BaBean)bean);
			}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
				//es passiert nix. Wir müssen die Bean nicht speichern
//			}else if (bean.getBeanStatus() == BeanStatus.DELETE){
//				deleteBean(bean);
//				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Eine Bestellanforderung darf aus der DB nicht entfernt werden."));
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
	protected void updateBean(Bean bean) throws SQLException {
		getUpdateStmt().setDate(1, ((BaBean)bean).getErstellungsDatum());
		
		getUpdateStmt().setString(2, AnforderungStatus.JavaToDB(((BaBean)bean).getStatus()));
		
		if (((BaBean)bean).getLhBean()!=null && ((BaBean)bean).getLhBean().getId()!=0)
			getUpdateStmt().setInt(3, ((BaBean)bean).getLhBean().getId());
		else getUpdateStmt().setNull(3, java.sql.Types.INTEGER);
		
		if (((BaBean)bean).getAbsenderBenutzerBean()!=null && ((BaBean)bean).getAbsenderBenutzerBean().getId()!=0)
			getUpdateStmt().setInt(4, ((BaBean)bean).getAbsenderBenutzerBean().getId());
		else getUpdateStmt().setNull(4,java.sql.Types.INTEGER);
		
		if (((BaBean)bean).getAnnehmerBenutzerBean()!=null && ((BaBean)bean).getAnnehmerBenutzerBean().getId()!=0)
			getUpdateStmt().setInt(5, ((BaBean)bean).getAnnehmerBenutzerBean().getId());
		else getUpdateStmt().setNull(5, java.sql.Types.INTEGER);
		
		getUpdateStmt().setString(6, ((BaBean)bean).getEmail());
		
		getUpdateStmt().setInt(7, ((BaBean)bean).getId());
		
		getUpdateStmt().execute();
		
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}

	
	@Override
	protected void selectBean(Bean bean) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	protected void insertBean(Bean bean) throws SQLException {
		getInsertStmt().setInt(1, ((BaBean)bean).getId());
		getInsertStmt().setDate(2, ((BaBean)bean).getErstellungsDatum());
		getInsertStmt().setString(3, AnforderungStatus.JavaToDB(((BaBean)bean).getStatus()));
		if (((BaBean)bean).getLhBean()!=null && ((BaBean)bean).getLhBean().getId()!=0)
			 getInsertStmt().setInt(4, ((BaBean)bean).getLhBean().getId());
		else getInsertStmt().setNull(4, java.sql.Types.INTEGER);
		
//		//Es darf keine Anforderung ohne den aktuellen Benutzer abgespeichert werden.
//		if (((BaBean)bean).getAbsenderBenutzerBean()!=null||((BaBean)bean).getAbsenderBenutzerBean().getId().equals(0)){
//			//Aktueller Benutzer ist leer und wird deswegen durch den aktuell angemeldeten Benutzer ersetzt.
//			((BaBean)bean).setAbsenderBenutzerBean(Run.getOneInstance().getBenutzerBean());
//		}
		if (((BaBean)bean).getAbsenderBenutzerBean()!=null && ((BaBean)bean).getAbsenderBenutzerBean().getId()!=0)
			 getInsertStmt().setInt(5, ((BaBean)bean).getAbsenderBenutzerBean().getId());
		else getInsertStmt().setNull(5,java.sql.Types.INTEGER);
		
		if (((BaBean)bean).getAnnehmerBenutzerBean()!=null && ((BaBean)bean).getAnnehmerBenutzerBean().getId()!=0)
			 getInsertStmt().setInt(6, ((BaBean)bean).getAnnehmerBenutzerBean().getId());
		else getInsertStmt().setNull(6, java.sql.Types.INTEGER);
		getInsertStmt().setString(7, ((BaBean)bean).getEmail());
		getInsertStmt().execute();
//		bean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	
	
	@Override
	protected String selectAnhandIdSQL() {
		// TODO Auto-generated method stub
		return "select id, erstellungsdatum, status, " +
		"fk_herstellerLieferant, fk_benutzerAbsender, fk_benutzerAnnehmer, email_ba_empfaenger " +
		"from bestellanforderung where id=?";
	}
	
	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		BaBean resultBean = new BaBean();
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
	
	private LhBeanDB getHerstellerLieferantBeanDB() {
		if (herstellerLieferantBeanDB == null) {
			herstellerLieferantBeanDB = new LhBeanDB();
		}
		return herstellerLieferantBeanDB;
	}

	private BenutzerBeanDB getBenutzerBeanDB() {
		if (benutzerBeanDB == null) {
			benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}

	
	public BaPosBeanDB getBaPosBeanDB() {
		if (baPosBeanDB == null) {
			baPosBeanDB = new BaPosBeanDB();
		}
		return baPosBeanDB;
	}

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException{
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//sql bilden
		String sql = "select id, erstellungsdatum, status, fk_herstellerLieferant, fk_benutzerAbsender, " +
				"fk_benutzerAnnehmer, email_ba_empfaenger " +
				"from bestellanforderung where erstellungsdatum>=? and erstellungsdatum<=?";
		switch (((BaSuchBean)iSuchBean).getAnforderungStatus())	{
		case ALLE:break;
		default:{
			sql = sql + " and status = '"+AnforderungStatus.JavaToDB(((BaSuchBean)iSuchBean).getAnforderungStatus())+"'";	
		}
		};
		sql = sql + " order by erstellungsdatum desc";
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
		stmt.setDate(1, new java.sql.Date(((BaSuchBean)iSuchBean).getErstellungsDatumVon().getTime()));
		stmt.setDate(2, new java.sql.Date(((BaSuchBean)iSuchBean).getErstellungsDatumBis().getTime()));
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			BaBean baBean = new BaBean();
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
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		BaBean resultBean = (BaBean) bean;
		resultBean.setId(rs.getInt("id"));
		resultBean.setErstellungsDatum(rs.getDate("erstellungsdatum"));
		resultBean.setStatus(AnforderungStatus.DbToJava(rs.getString("status")));
		resultBean.setLhBean((LhBean)getHerstellerLieferantBeanDB().selectAnhandID(rs.getInt("fk_herstellerLieferant"), 0, null));
		resultBean.setAbsenderBenutzerBean((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzerAbsender"), 0, null));
		resultBean.setFk_benutzerAnnehmer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzerAnnehmer"), 0, null));
		resultBean.setEmail(rs.getString("email_ba_empfaenger"));
	}


	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return new ArrayList<Bean>();
	}


	public ArrayList<BaPosBean>  sucheBaPosBeansAnhandBaBean(BaBean baBean ) throws SQLException, LagerException{
		return getBaPosBeanDB().sucheBaPosBeansAnhandBaBean(baBean, 0);
	}

}
