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
package de.keag.lager.core.main;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import sun.util.logging.resources.logging;

import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.LagerConnection;

/**
 * Diese Klasse liest die Konfigurationsdatei "lager.properties" aus.
 * @author wladimir
 *
 */
public class LagerProperties extends Properties { //Konfiguration zu der gesamten Anwendung

	
//	public static	String userEingabeHost = "192.168.19.131"; //Produktion Eugen Arbeitspc
//	public static String userEingabeHost = "192.168.0.195"; //Laptop
//	public static String userEingabeHost = "192.168.0.193"; //Alexanders PC
//	public static String userEingabeHost = "172.21.20.33"; //alt produktion am 14.04.2017
	public static String userEingabeHost = "172.21.1.21"; //alt produktion am 14.04.2017
//	public static String userEingabeHost = "192.168.1.5"; //Alexanders PC
//	public static String userEingabeHost = "192.168.0.100"; //Wladimirs PC
//	public static String userEingabeHost = "127.0.0.1"; //Entwicklung, oder wenn das Programm auf dem gleichen Rechner startet.

	public LagerProperties(){
		super();
		try {
//			FileInputStream stream;
			BufferedReader br; 
			try{
				//stream = new FileInputStream("lager.properties");
				br = new BufferedReader(new InputStreamReader(new FileInputStream("lager.properties")));
				Log.log().severe("Verbindung zu lager.property über lokale File");
			}catch (Exception e){
				e.printStackTrace();
				Log.log().severe("Vermutlich keine Entwickungsmaschine! Also Produktion oder Test.");
				try{
					URL lagerPropertiesUrl = new URL("http://"+userEingabeHost.trim()+":8080/lager/lager.properties");
//            		 URL lagerPropertiesUrl = new URL("http://"+LagerConnection.getOneInstance().getUrl()+":8080/lager/lager.properties");
//					URL lagerPropertiesUrl = new URL("http://lagerkeg.dyndns-server.com:8080/lager/lager.properties");
					br =  new BufferedReader(new InputStreamReader(lagerPropertiesUrl.openStream()));
					Log.log().severe("Verbindung zu lager.property " + lagerPropertiesUrl.toString());
				}catch (Exception eUrl){
					eUrl.printStackTrace();
					Log.log().severe(eUrl.getMessage());
					Log.log().severe("Keine Verbindung zu  lager.property auf  dem "+userEingabeHost + ". Es wird versucht, von vordefinierten Adressen zu starten.");
					try{
//						URL lagerPropertiesUrl = new URL("http://172.21.20.33:8080/lager/lager.properties"); alt produktion am 14.04.2017
						URL lagerPropertiesUrl = new URL("http://172.21.1.21:8080/lager/lager.properties");
						br =  new BufferedReader(new InputStreamReader(lagerPropertiesUrl.openStream()));
						Log.log().severe("Verbindung zu lager.property auf " + lagerPropertiesUrl.toString());
					}catch (Exception ekeag) {
						ekeag.printStackTrace();
						Log.log().severe(ekeag.getMessage());
						Log.log().severe("Keine Verbindung zu  lager.property auf  192.168.19.131(Eugen Arbeits-PC)");
						br = null;
						try{
							URL lagerPropertiesUrl = new URL("http://192.168.0.100:8080/lager/lager.properties");
							br =  new BufferedReader(new InputStreamReader(lagerPropertiesUrl.openStream()));
							Log.log().severe("Verbindung zu  lager.property auf " + lagerPropertiesUrl.toString());
						}catch (Exception eUrl2){
							eUrl2.printStackTrace();
							Log.log().severe(eUrl2.getMessage());
							Log.log().severe("Keine Verbindung zu  lager.property auf  192.168.0.100(Entwicklung) ");
							br = null;
							try{
								URL lagerPropertiesUrl = new URL("http://192.168.0.193:8080/lager/lager.properties");
								br =  new BufferedReader(new InputStreamReader(lagerPropertiesUrl.openStream()));
								Log.log().severe("Verbindung zu  lager.property auf " + lagerPropertiesUrl.toString());
							}catch (Exception eUrl3){
								Log.log().severe("Keine Verbindung zu  lager.property auf 192.168.0.193(Alexanders PC)");
								eUrl3.printStackTrace();
								Log.log().severe(eUrl3.getMessage());
								try{
									URL lagerPropertiesUrl = new URL("http://lagerkeg.dyndns-server.com:8080/lager/lager.properties");
									br =  new BufferedReader(new InputStreamReader(lagerPropertiesUrl.openStream()));
									Log.log().severe("Verbindung zu " + lagerPropertiesUrl.toString());
								}catch (Exception eUrl4){
									Log.log().severe("Keine Verbindung zu  lager.property auf lagerkeg.dyndns-server.com");
									eUrl4.printStackTrace();
									Log.log().severe(eUrl4.getMessage());
									try{
										//Laptop, SVN
										URL lagerPropertiesUrl = new URL("http://192.168.0.195:8080/lager/lager.properties");
										br =  new BufferedReader(new InputStreamReader(lagerPropertiesUrl.openStream()));
										Log.log().severe("Verbindung zu  lager.property auf  " + lagerPropertiesUrl.toString());
									}catch (Exception eUrl5){
										Log.log().severe("Keine Verbindung zu lager.property auf 192.168.0.195 ");
										eUrl5.printStackTrace();
										br = null;
										try{
											//Entwicklungsmaschine
											URL lagerPropertiesUrl = new URL("http://127.0.0.1:8080/lager/lager.properties");
											br =  new BufferedReader(new InputStreamReader(lagerPropertiesUrl.openStream()));
											Log.log().severe("Verbindung zu  lager.property auf  " + lagerPropertiesUrl.toString());
										}catch (Exception eUrl6){
											Log.log().severe("Keine Verbindung zu lager.property auf 127.0.0.1 ");
											eUrl6.printStackTrace();
											br = null;
										}
									}
								}
							}
						}
					}
				}
//				stream = new FileInputStream("http://lagerkeg.dyndns-server.com:8080/lager/lager.properties");
			}

			
			Log.log().severe("Before load from " + br.toString());
			this.load(br);
			Log.log().severe("After load from " + br.toString());
			br.close();
			Log.log().severe("After close from " + br.toString());
			
//			this.load(stream);
//			stream.close();
			
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
	
	public Object getMail_mail_smtp_host(){return getProperty("mail_mail_smtp_host","mail.gmx.net");}
	public Object getMail_mail_smtp_port(){return getProperty("mail_mail_smtp_port","25");}
	public Object getMail_mail_smtp_auth(){return getProperty("mail_mail_smtp_auth","true");}
	public Object getMail_session_transport(){return getProperty("mail_session_transport","smtp");}
	
	
	
	
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
//		return getProperty("lagerEmailSuperUser","thurner@keag.de");	}
	    return getProperty("lagerEmailSuperUser","wladimir.stoll@gmx.de");	
	}

	public String getLizenzSatz() {
		return getProperty("lizenzSatz","");	
	}	

}
