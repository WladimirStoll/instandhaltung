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
package de.keag.lager.core.fehler;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;

public class LagerLogDbHandler extends Handler {
	
	private boolean eingeschaltet;
	private String ip = "";

	public boolean isEingeschaltet() {
		return eingeschaltet;
	}

	public void setEingeschaltet(boolean eingeschaltet) {
		if (this.eingeschaltet != eingeschaltet && eingeschaltet){
			
			//beim ersten setzen auf True soll system.out umgestellt werden.
			PrintStream origOut = System.out;
			PrintStream lagerSystemOutDbPrintStreamOut = new LagerSystemOutDbPrintStream(origOut);
			System.setOut(lagerSystemOutDbPrintStreamOut);
			
			//beim ersten setzen auf True soll system.err umgestellt werden.
			PrintStream origErr = System.err;
			PrintStream lagerSystemOutDbPrintStreamErr = new LagerSystemOutDbPrintStream(origErr);
			System.setErr(lagerSystemOutDbPrintStreamErr);
			
			try {
				ip = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				ip = "ip nicht bekannt!";
			}
			
		}
		this.eingeschaltet = eingeschaltet;
	}

	private String userInfo;
	public String getUserInfo() {
		if (userInfo==null){
			 userInfo = "|"+Run.getOneInstance().getBenutzerBean().getLoginName() + " " + 
					 Run.getOneInstance().getBenutzerBean().getVorname() + " " +
					 Run.getOneInstance().getBenutzerBean().getName() + "-";
					 ;
		}
		return userInfo;
	}

	private PreparedStatement insertStmt;
	protected String insertSQL;
	
	protected PreparedStatement getInsertStmt() throws SQLException {
		if(insertStmt==null){
			insertStmt = (PreparedStatement) LagerConnection.getOneInstance().getConnection().prepareStatement(getInsertSQL());			
		}
		return insertStmt;
	}	
	
	protected String getInsertSQL() {
		if(insertSQL==null){
			insertSQL = "insert into fehler " +
					"(fehler)" +
					" values (?)";
		}
		return insertSQL;
	}	
	
	public LagerLogDbHandler() {
		super();
		setEingeschaltet(false);
	}
	
	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub
		System.out.println("close()");
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		System.out.println("flush()");

	}

	@Override
	public void publish(LogRecord rec) {
		if (!isEingeschaltet()){
			return;
		}
		try {
			StringBuffer error = new StringBuffer(1000);
			error.append(calcDate(rec.getMillis()));
			error.append(" ");
			error.append(ip);
			error.append(" ");
//			error.append(formatMessage(rec));
			error.append(getFormatter().formatMessage(rec).trim());
			error.append(" ");  
			error.append(rec.getLevel().getName());
			error.append(" ");  
			error.append(" in ");
			error.append(rec.getSourceClassName());
			error.append(">>");
			error.append(rec.getSourceMethodName());
			error.append(" User:");
			error.append(getUserInfo().trim());
			getInsertStmt().setString(1, error.toString());
			getInsertStmt().execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error in public():"+e.getMessage());
		}
	}
	
	private String calcDate(long millisecs)
	{
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}		
	
//	private static Connection connectDB() {
//	   Connection jdbcConnection = null;
//	   try{
//	  	  //Lager-Connection benutzen
//	   	  jdbcConnection = LagerConnection.getOneInstance().getConnection();
//	    }catch(Exception ex) {
//	        String connectMsg = "Could not connect to the database: " + ex.getMessage() + " " + ex.getLocalizedMessage();
//	        System.out.println(connectMsg);
//	     }
//	     return jdbcConnection;
//	}
	

}
