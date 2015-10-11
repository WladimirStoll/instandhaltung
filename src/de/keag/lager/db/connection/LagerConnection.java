package de.keag.lager.db.connection;

import java.sql.SQLException;
import java.util.logging.Handler;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerLogDbHandler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.LagerProperties;
import de.keag.lager.core.main.Run;

/**
 * Doku zu UNI-Code in MySQL: http://dev.mysql.com/doc/refman/5.1/de/charset-unicode-sets.html
 * 					und hier: http://www.unicode.org/reports/tr10/
 * Die bedeutsamste Funktion in utf8_unicode_ci  besteht darin, 
 * dass Erweiterungen unterstuetzt werden, d. h., 
 * wenn ein Zeichen mit einer Kombination anderer Zeichen 
 * gleichgesetzt wird. Beispielsweise wird im Deutschen 
 * und einigen anderen Sprachen "scharfes s" mit "ss" gleichgesetzt. 
 * @author wladimir
 *
 */


public class LagerConnection {
	
	private static LagerConnection oneInstance;
	public static LagerConnection getOneInstance(){
		if(oneInstance==null){
			oneInstance = new LagerConnection(Run.getOneInstance().getLagerProperties());
//			oneInstance = new LagerConnection(new LagerProperties());
		}
		return oneInstance;
	}
	
	private java.sql.Connection connection; 
	private LagerProperties konfiguration; //Zeiger auf die Konfigurations-Klasse

	private LagerConnection(LagerProperties konfiguration) {
		super();
		this.konfiguration = konfiguration;
	}

	/**
	 * Ermittelung einer eindeutigen Nummer. 
	 * Die Funktion wird so gut wie immer für die Ermittlung der eindeutigen Nummern verwendet.
	 * @param tabelleUndFeld
	 * @return
	 * @throws SQLException
	 * @throws IdLagerException
	 */
	public Integer getID(String tabelleUndFeld) throws SQLException, IdLagerException{
		Integer new_id = ermittleDieNextID(tabelleUndFeld);
		//Falls die ID = 0 ist, dann muss evtl. der Satz neu angelegt werden. Es ist immer der Fall, wenn die ID zum ersten Mal angefordert wird.
		if (new_id == 0){
			erzeugeNeuenSatzInDerSequenz(tabelleUndFeld);
			new_id = ermittleDieNextID(tabelleUndFeld); //es wird noch mal versucht, die ID zu ermitteln.
			if (new_id == 0){
				throw new IdLagerException(new Fehler(11,FehlerTyp.FEHLER,"Sequence-ID:"+tabelleUndFeld));
			}
		}
		return new_id;
	}

	/**
	 * Der Satz wird neu angelegt.
	 * @param tabelleUndFeld
	 * @throws SQLException
	 */
	private void erzeugeNeuenSatzInDerSequenz(String tabelleUndFeld	) throws SQLException {
		java.sql.Connection conn = LagerConnection.getOneInstance().getConnection();
		java.sql.PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO sequence(id, tablefield) VALUES (1, ?) ");
		insertStmt.setString(1, tabelleUndFeld);
		insertStmt.executeUpdate();
		insertStmt.close();
	}
	
	/**
	 * Die ID wird ermittelt
	 * @param tabelleUndFeld
	 * @return
	 * @throws SQLException
	 * @throws IdLagerException
	 */
	private Integer ermittleDieNextID(String tabelleUndFeld) throws SQLException, IdLagerException{
			Integer new_id;
			java.sql.Connection conn = LagerConnection.getOneInstance().getConnection();//zeiger holen
			//neue ID erzeugen
			java.sql.PreparedStatement stmt = conn.prepareStatement("UPDATE sequence SET id=LAST_INSERT_ID(id+1) where tablefield = ?");
			stmt.setString(1, tabelleUndFeld);
			int changedRecords =  stmt.executeUpdate();
			stmt.close();
			//neu erzeugte ID wird ausgelesen
			stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			java.sql.ResultSet rs = stmt.executeQuery();
			//Ergenis zurückgeben
			if (rs.next()) {
				//die ID wurde ermittelt
				new_id = rs.getInt(1);
			}else{
				//Fehler, falls eine ID nicht ermittelt werden konnte.
				throw new IdLagerException(new Fehler(10,FehlerTyp.FEHLER,"Sequence-ID:"+tabelleUndFeld));
			}
			rs.close();
			stmt.close();
			if (changedRecords==1){
				return new_id; //ID wird zurückgegeben.
			}else{
				return 0;
			}
			
	}
	
	public String getUrl(){
		return konfiguration.getHostname();
	}
	
	/**
	 * Datenbank verbindung wird aufgebaut.
	 * http://dev.mysql.com/doc/refman/5.4/en/charset-unicode.html
	 * http://bugs.mysql.com/bug.php?id=3114
	 * http://search.mysql.com/search?q=Charset-Unicode-sets.html&lr=lang_en
	 * http://search.mysql.com/search?q=Charset-examples.html&lr=lang_en
	 * http://dev.mysql.com/doc/refman/5.1/en/charset-examples.html
	 * http://dev.mysql.com/doc/refman/5.4/en/charset-unicode-sets.html
	 * 
	 * @return
	 */
	private java.sql.Connection getNewConnection(){
		
		//Treiber fuer MySQL wird geladen. in der Regel  "com.mysql.jdbc.Driver"
		loadDriver(konfiguration.getTreiber());
		
		java.util.Properties props = new java.util.Properties();
		props.put("characterEncoding", konfiguration.getCharacterEncoding());
		props.put("autoReconnect", konfiguration.getAutoReconnect());
		props.put("zeroDateTimeBehavior", konfiguration.getZeroDateTimeBehavior()); //um JasperReport korrekt starten zu können. Sonst werden die DATE - Felder nicht ausgelesen.
		props.put("user", konfiguration.getUser());
		props.put("password", konfiguration.getPassword());
		
		String url = "jdbc:mysql://" + konfiguration.getHostname() + ":" + konfiguration.getPort() + "/" + konfiguration.getDbname();
		try {
//			System.out.println("DB>"+url+","+konfiguration.getUser()+","+konfiguration.getPassword()+".");
//			java.sql.Connection conn = java.sql.DriverManager.getConnection(url, konfiguration.getUser(), konfiguration.getPassword());
//			java.sql.Connection conn =	java.sql.DriverManager.getConnection("jdbc:mysql://localhost/lager?user=lager&password=lager");			
			java.sql.Connection conn = java.sql.DriverManager.getConnection(url,props);
			return conn;
		} catch (SQLException e) {
			System.out.println("Datenbankverbindung kann nicht aufgebaut werdern. Fehler:"+e.getMessage());
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			e.printStackTrace();
			return null;
		}
	}
	
	public java.sql.Connection getConnection() throws SQLException{
		if (connection == null){
			connection = getNewConnection();
			connection.setAutoCommit(false);
			java.sql.Statement stmt = connection.createStatement();
			//http://dev.mysql.com/doc/refman/5.1/de/set-transaction.html
			stmt.execute("SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
			stmt.close();
			
			//DB-Protokoll erst jetzt einschalten, nach dem die Konnection vorhanden ist.
			for(Handler handler : Log.log().getHandlers()){
				if (handler instanceof LagerLogDbHandler){
					((LagerLogDbHandler)handler).setEingeschaltet(true);
				}
			}
		}
		return connection;
	}
	
	private void loadDriver(String treiberName) {
           try {
			Class.forName(treiberName).newInstance();
		} catch (InstantiationException e) {
			System.out.println("Treiber '"+treiberName+"' konnte nicht geladen werden. Fehler(1):"+e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Treiber '"+treiberName+"' konnte nicht geladen werden. Fehler(2):"+e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber '"+treiberName+"' konnte nicht geladen werden. Fehler(3):"+e.getMessage());
			e.printStackTrace();
		}
	}	
	
	/**
	 * Testaufruf fuer diese Klasse
	 */
	public static void main(String[] args) {
		LagerConnection lagerConnection = new LagerConnection(new LagerProperties());
		java.sql.Statement stmt;
		java.sql.ResultSet rs; 
		try {
			java.sql.Connection conn = lagerConnection.getConnection();
			conn.setAutoCommit(false); //dadurch sind die Transaktionen überhaupt möglich
			testConnection(conn);
			conn.commit(); //Änderungen sind abgeschlossen.
			conn.close();
		} catch (SQLException e) {
			System.out.println("Fehler im Testlauf:"+e.getMessage());
			e.printStackTrace();
			try {
				java.sql.Connection conn = lagerConnection.getConnection();
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Fehler während Rollbacks:"+e.getMessage());
				e1.printStackTrace();
			}
		}
		
		lagerConnection = new LagerConnection(new LagerProperties());
		try {
			java.sql.Connection conn = lagerConnection.getConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			stmt.execute("START TRANSACTION");
			
			//http://dev.mysql.com/doc/refman/5.1/de/set-transaction.html
			stmt.execute("SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
			stmt.close();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT @@global.tx_isolation");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			stmt.close();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT @@tx_isolation");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			stmt.close();
			
			testSequence(conn);
			stmt = conn.createStatement();
			stmt.execute("COMMIT");
			stmt.close();
			
			testSequence(conn);
			stmt = conn.createStatement();
			stmt.execute("ROLLBACK");
			stmt.close();

			testSequence(conn);
			stmt = conn.createStatement();
			stmt.execute("COMMIT");
			stmt.close();

			testSequence(conn);
			stmt = conn.createStatement();
			stmt.execute("ROLLBACK");
			stmt.close();

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT @@tx_isolation");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			stmt.close();
			
			
		} catch (SQLException e) {
			System.out.println("Fehler im Testlauf:"+e.getMessage());
			e.printStackTrace();
			try {
				java.sql.Connection conn = lagerConnection.getConnection();
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Fehler während Rollbacks:"+e.getMessage());
				e1.printStackTrace();
			}
		}
		
//Testen der getID
		try {
			Integer idForAgent007 = LagerConnection.getOneInstance().getID("agent007");
			System.out.println("idForAgent007:"+idForAgent007);
		} catch (IdLagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("* Datenbank-Verbindung beenden");
		
	}

	private static void testSequence(java.sql.Connection conn) throws SQLException {
		java.sql.Statement stmt;
		java.sql.ResultSet rs;
		System.out.println("*** sequence ***");
		try{
			stmt = conn.createStatement();
			stmt.execute("UPDATE sequence SET id=LAST_INSERT_ID(id+1) where tablefield = 'testtabelle'");
			stmt.close();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			Integer new_id = 0;
			if (rs.next()) {
				new_id = new Integer(rs.getString(1));
				System.out.println("id:" + new_id);
			}
			rs.close();
			stmt.close();

			stmt = conn.createStatement();
			String sql = "INSERT INTO sequence VALUES (" +new_id+ ", 'testtabelle"+new_id.toString()+"')";
			stmt.execute(sql);
			stmt.close();
			
			
		} catch (SQLException e) {
			System.out.println("Fehler in sequence:"+e.getMessage());
			e.printStackTrace();
		}
		
	}

	private static void testConnection(java.sql.Connection conn)
			throws SQLException {
		java.sql.Statement stmt;
		java.sql.ResultSet rs;
		System.out.println("*** insert ***");
		stmt = conn.createStatement();
		stmt.execute("insert into benutzer(name, vorname, loginName, password) " +
				"values ('Mustername','Mustervorname','musterlogin','1111')");
		
		
		try{
			stmt = conn.createStatement();
			stmt.execute("insert into benutzer(name, vorname, loginName, password) " +
					"values ('lager','lager','lager','7890')");
		} catch (SQLException e) {
			System.out.println("Fehler in insert(OK):"+e.getMessage());
			e.printStackTrace();
		}

		
		System.out.println("*** select ***");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT id, name, vorname, loginName, password FROM benutzer");
		while (rs.next()) {
			System.out.println("id:" + rs.getString("id"));
			System.out.println("name:" + rs.getString("name"));
			System.out.println("vorname:" + rs.getString("vorname"));
			System.out.println("loginName:" + rs.getString("loginName"));
			System.out.println("password:" + rs.getString("password"));
		}
		rs.close();
		stmt.close();
		
		System.out.println("*** delete ***");
		stmt = conn.createStatement();
		stmt.execute("delete from benutzer where name = 'Mustername'");
		
		System.out.println("*** close ***");
	}
	
	public static void startTransaction() throws SQLException{
		LagerConnection.getOneInstance().getConnection().createStatement().execute("START TRANSACTION");
	}	
	
	public static void commit() throws SQLException{
		LagerConnection.getOneInstance().getConnection().createStatement().execute("COMMIT");
	}	
	
	public static void rollback() throws SQLException{
		LagerConnection.getOneInstance().getConnection().createStatement().execute("ROLLBACK");
	}
	
}
