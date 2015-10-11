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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;

public class SqlParams {
	private ArrayList<SqlParam> params;
	
	public SqlParams(){
		super();
		params = new ArrayList<SqlParam>(); 
	}
	
	public void clear() {
		params.clear();
	}

	/**
	 * Überlengen werden abgeschnitten
	 * @param key
	 * @param value
	 * @param maxLength
	 */
	public void put(String key, String value, int maxLength){
		if (value!=null&&value.length()>maxLength){
			put(key, value.substring(0, maxLength));
		}else{
			put(key, value);
		}
	}
	
	public void put(String key, Object value){
		if (getValue(key)==null){
			params.add(new SqlParam(key,value));
		}
	}
	
	public void put(String key, Object value, boolean auchDoppelt){
		if (auchDoppelt){
			params.add(new SqlParam(key,value));
		}else{
			this.put (key,value);
		}
	}
	
	
	private Object getValue(String key){
		for (Iterator<SqlParam> iterator = params.iterator(); iterator.hasNext(); )
		{
		  SqlParam sqlParam = iterator.next();
		  if (sqlParam.getKey().equals(key)){
			  return sqlParam.getValue(); 
		  }
		}
		return null;
	}
	
    private Iterator<SqlParam> iterator() {
    	return params.iterator();
    }

	public void setParams(PreparedStatement stmt) throws SQLException,
			LagerException {
		int paramNummer = 1;
		for (Iterator<SqlParam> iterator = this.iterator(); iterator.hasNext();) {
			SqlParam sqlParam = iterator.next();
			// double
			if (sqlParam.getValue() instanceof Double) {
				Double param = (Double) sqlParam.getValue();
				if (param.equals(0)) {
					stmt.setNull(paramNummer++, java.sql.Types.DOUBLE);
				} else {
					stmt.setDouble(paramNummer++, param);
				}
			} else {

				// fload
				if (sqlParam.getValue() instanceof Float) {
					Float param = (Float) sqlParam.getValue();
					if (param.equals(0)) {
						stmt.setNull(paramNummer++, java.sql.Types.FLOAT);
					} else {
						stmt.setFloat(paramNummer++, param);
					}
				} else {
					// integer
					if (sqlParam.getValue() instanceof Integer) {
						Integer param = (Integer) sqlParam.getValue();
						if (param.equals(0)) {
							stmt.setNull(paramNummer++, java.sql.Types.INTEGER);
						} else {
							stmt.setInt(paramNummer++, param);
						}
					} else {
						// Date
						if (sqlParam.getValue() instanceof Date) {
							Date param = (Date) sqlParam.getValue();
							if (param.getTime() == 0) {
								stmt
										.setNull(paramNummer++,
												java.sql.Types.DATE);
							} else {
								stmt.setDate(paramNummer++, new java.sql.Date(
										param.getTime()));
							}
						} else {
							// String
							if (sqlParam.getValue() instanceof String) {
								String param = (String) sqlParam.getValue();
								if (param.equals("")) {
									stmt.setNull(paramNummer++,
											java.sql.Types.CHAR);
								} else {
									stmt.setString(paramNummer++, param);
								}
							} else {
								Log.log().severe(
										"Typ ist nicht bekannt"
												+ sqlParam.toString());
								throw new LagerException(
										"Parametertyp ist nicht bekannt");
							}
						}
					}
				}

			}
		}
	}
	
}
