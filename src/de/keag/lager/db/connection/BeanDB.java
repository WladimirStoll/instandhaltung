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
package de.keag.lager.db.connection;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import de.keag.lager.core.Bean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;

abstract public class BeanDB {
	
	protected int tempNr;
	private SqlParams sqlParams;
	protected String selectAnhandIdSQL;
	protected String selectSQL;
	protected String updateSQL;
	protected String deleteSQL;
	protected String insertSQL;
	protected String selectSpalten;
	private PreparedStatement selectAnhandIdStmt;
	private PreparedStatement selectStmt;
	private PreparedStatement insertStmt;
	private PreparedStatement updateStmt;
	private PreparedStatement deleteStmt;
	private DepthLevelArrayDB depthLevelArrayDB;
	
	public BeanDB() {
		super();
	}

	protected String getSelectSpalten(){
		return "Fehler: Überschreibe die getSelectSpalten() in "+this.getClass().getCanonicalName();
	}
	
	public String getWhereKlausel(String where, String value, String feldName) {
		value = value.trim().toUpperCase(); 
		if (!value.equals("")){
			String neuerVergleich = " " + feldName +" like '%" +value + "%' "; 
			if (where.equals("")){
				where = " where " + neuerVergleich;
			}else{
				where = where + " and " + neuerVergleich;
			}
		}
		return where;
	}
	
	public String getWhereKlausel(String where, Integer value, String feldName) {
		if (!value.equals(0)){
			String neuerVergleich = " " + feldName +" = " +value.toString() + " "; 
			if (where.equals("")){
				where = " where " + neuerVergleich;
			}else{
				where = where + " and " + neuerVergleich;
			}
		}
		return where;
	}

	


	protected void setUpdateSQL(String updateSQL) {
		this.updateSQL = updateSQL;
	}
	
	protected void setSelectAnhandIdSQL(String selectAnhandIdSQL) {
		this.selectAnhandIdSQL = selectAnhandIdSQL;
	}

	protected void setInsertSQL(String insertSQL) {
		this.insertSQL = insertSQL;
	}

	protected void setDeleteSQL(String deleteSQL) {
		this.deleteSQL = deleteSQL;
	}
	
	protected PreparedStatement getInsertStmt() throws SQLException {
		if(insertStmt==null){
			insertStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getInsertSQL());			
		}
		return insertStmt;
	}

	protected PreparedStatement getUpdateStmt() throws SQLException  {
		if(updateStmt==null){
			updateStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getUpdateSQL());			
		}
		return updateStmt;
	}

	protected PreparedStatement getDeleteStmt() throws LagerException, SQLException {
		if(deleteStmt==null){
			deleteStmt = LagerConnection.getOneInstance().getConnection().prepareStatement(getDeleteSQL());			
		}
		return deleteStmt;
	}
	
	protected PreparedStatement getSelectStmt() throws SQLException  {
		if(selectStmt==null){
			selectStmt = LagerConnection.getOneInstance().getConnection().prepareStatement(getSelectSQL());			
		}
		return selectStmt;
	}
	
	protected PreparedStatement getSelectAnhandIdStmt() throws SQLException  {
		if(selectAnhandIdStmt==null){
			selectAnhandIdStmt = LagerConnection.getOneInstance().getConnection().prepareStatement(selectAnhandIdSQL());			
		}
		return selectAnhandIdStmt;
	}
	

	abstract protected String getSelectSQL();
	
	abstract protected String selectAnhandIdSQL();

	abstract protected String getDeleteSQL();
	
	abstract protected String getUpdateSQL();

	abstract protected String getInsertSQL();

	abstract public void saveBean(Bean bean) throws SQLException, LagerException;

	abstract public void deleteBean(Bean bean) throws SQLException , LagerException;

	abstract protected void updateBean(Bean bean) throws SQLException , LagerException ;

	abstract protected void selectBean(Bean bean) throws SQLException , LagerException;

	abstract protected void insertBean(Bean bean) throws SQLException , LagerException;

	abstract public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException;
	
	abstract public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException;

	abstract protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws  SQLException, LagerException ;

	abstract public ArrayList<Bean> sucheAlle() throws SQLException, LagerException ;
	
	public SqlParams getSqlParams() {
		if (sqlParams==null){
			sqlParams = new SqlParams();
		}
		return sqlParams;
	}

	public DepthLevelArrayDB getDepthLevelArrayDB() {
		if (this.depthLevelArrayDB==null){
				this.depthLevelArrayDB = new DepthLevelArrayDB(this.getClass());
		}
		return depthLevelArrayDB;
	}


}
