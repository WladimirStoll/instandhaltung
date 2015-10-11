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
