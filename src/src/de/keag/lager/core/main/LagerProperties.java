package de.keag.lager.core.main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Diese Klasse liest die Konfigurationsdatei "lager.properties" aus.
 * @author wladimir
 *
 */
public class LagerProperties extends Properties { //Konfiguration zu der gesamten Anwendung
	
	public LagerProperties(){
		super();
		try {
			FileInputStream stream = new FileInputStream("lager.properties");
			this.load(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Konfigurationsdatei kann nicht gelesen werden. Fehler(1):"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Konfigurationsdatei kann nicht gelesen werden. Fehler(2):"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getPort(){
		return getProperty("port","3306");
	}
	
	public String getDbname(){
		return getProperty("dbname","lager");
	}
	
	public String getUser(){
		return getProperty("user","lager");
	}
	
	public String getPassword(){
		return getProperty("password","lager");
	}
	
	public String getHostname(){
		return getProperty("hostname","127.0.0.1");
	}
	
	public String getTreiber(){
		return getProperty("treiber","com.mysql.jdbc.Driver");
	}
	
	public String getCharacterEncoding(){
		return getProperty("characterEncoding","utf-8");
	}
	
	public String getAutoReconnect(){
		return getProperty("autoReconnect","true");
	}
	
	public Object getZeroDateTimeBehavior() {
		return getProperty("zeroDateTimeBehavior","convertToNull");
	}
	
	
	
	/**
	 * Testaufruf für diese Klasse
	 */
	public static void main(String[] args) {
		LagerProperties k = new LagerProperties();
		System.out.println(k.getHostname());
		System.out.println(k.getPort());
		System.out.println(k.getDbname());
		System.out.println(k.getUser());
		System.out.println(k.getPassword());
	}

	public String getLagerKegGmxPassword() {
		return getProperty("lagerKegGmxPassword","lagereugen");
	}

	public String getLagerKegGmxUser() {
		return getProperty("lagerKegGmxUser","lagerkeg@gmx.de");	}

	public String getLagerEmailSuperUser() {
		return getProperty("lagerEmailSuperUser","thurner@keag.de");	}	

}
