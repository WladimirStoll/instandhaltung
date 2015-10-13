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
