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
package de.keag.lager.core.jasperReports;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
//import org.w3c.dom.smil.*;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;

import de.keag.lager.core.email.SendJavaMail;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.LagerProperties;
import de.keag.lager.db.connection.LagerConnection;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.JasperReport;

/**
* Driver program to connect to a database and to view a jasper report (.jrxml)
* @author Oguzhan Topsakal
* @since 23 March 2006
*
* Required jar files to run this class:
* 1. jasperreports-1.2.0.jar
* 2. classes12.jar (for Oracle JDBC connection)
* 3. commons-beanutils-1.5.jar
* 4. commons-collections-2.1.jar
* 5. commons-digester-1.7.jar
* 6. commons-logging-1.0.2.jar
*
*/

public class ReportDriver {
	
	private static ReportDriver oneInstance;
	public static ReportDriver getOneInstance(){
		if(oneInstance==null){
			oneInstance = new ReportDriver();
		}
		return oneInstance;
	}
	

   /**
   * Constructor for ReportDriver
   */
   private ReportDriver() {
   }

   /**
   * Takes 3 parameters: databaseName, userName, password
   * and connects to the database.
   * @param databaseName holds database name,
   * @param userName holds user name
   * @param password holds password to connect the database,
   * @return Returns the JDBC connection to the database
   */
   public static Connection connectDB() {
      Connection jdbcConnection = null;
      try{
    	  //Lager-Connection benutzen
    	  jdbcConnection = LagerConnection.getOneInstance().getConnection();
      }catch(Exception ex) {
         String connectMsg = "Could not connect to the database: " + ex.getMessage() + " " + ex.getLocalizedMessage();
         System.out.println(connectMsg);
      }
      return jdbcConnection;
   }

   /**
   * Takes 4 parameters: databaseName, userName, password, reportFileLocation
   * and connects to the database and prepares and views the report.
 * @param parameters TODO
 * @param reportFile holds the location of the Jasper Report file (.jrxml)
 * @param databaseName holds database name,
 * @param userName holds user name
 * @param password holds password to connect the database,
 * 
 * http://www.coderanch.com/t/62763/open-source/Jasper-Reports-Exception
 * 
   */
   private static OutputStream runReport(Map parameters, 
		   String reportFile, 
		   boolean tuAnzeigen, 
		   boolean tuDrucken, 
		   boolean tuSpeichernPDF, 
		   boolean tuSpeichernHTML, 
		   boolean tuSpeichernToPdfStream, boolean tuSpeichernToXml, String reportFileJasper) {
	  System.out.println("runReport");
   	 OutputStream outputStream = null;
      try{
    	 JasperDesign jasperDesign = null; 
    	 try{
    		 jasperDesign = JRXmlLoader.load(reportFile);
    	 }catch(Exception e){
    		 e.printStackTrace();
        	 try{
        		 URL lagerPropertiesUrl = new URL("http://"+LagerConnection.getOneInstance().getUrl()+":8080/lager/"+reportFile);
//        		 URL lagerPropertiesUrl = new URL("http://lagerkeg.dyndns-server.com:8080/lager/"+reportFile);
        		 InputStream inputStream = new BufferedInputStream(lagerPropertiesUrl.openStream());
        		 jasperDesign = JRXmlLoader.load(inputStream);
        	 }catch(Exception eUrl){
        		 eUrl.printStackTrace();
        	 }
    	 }
    	  
         JasperCompileManager.verifyDesign(jasperDesign);
         JasperReport jasperReport = null;
         try{ //Hier kann es zu einem Fehler kommen, falls im iReport für diesen Report "GROOVY" statt "Java" hinterlegt ist.
        	  //http://edwin.baculsoft.com/2010/11/how-to-handle-jasper-reports-compilationfailedexception/
        	 jasperReport = JasperCompileManager.compileReport(jasperDesign); 
         }catch (Exception e){
        	 e.printStackTrace();
        	 try{
        		 URL lagerPropertiesUrl = new URL("http://"+LagerConnection.getOneInstance().getUrl()+":8080/lager/"+reportFileJasper);
        		 InputStream inputStream = new BufferedInputStream(lagerPropertiesUrl.openStream());
        		 jasperReport =(JasperReport)JRLoader.loadObject(inputStream);
        	 }catch (Exception eUrl){
        		 eUrl.printStackTrace();
        	 }
         }
         	
         Connection jdbcConnection = connectDB();
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jdbcConnection);
         if (tuAnzeigen){
             JasperViewer.viewReport(jasperPrint,false);
         }
         if(tuSpeichernPDF){
        	 JasperExportManager.exportReportToPdfFile(jasperPrint, Konstanten.REPORT_BA_001_PDF);	
         }
         if(tuSpeichernHTML){
        	 JasperExportManager.exportReportToHtmlFile(jasperPrint, Konstanten.REPORT_BA_001_HTML);
         }
         if(tuSpeichernToPdfStream){
//        	 outputStream = new java.io.PipedOutputStream();
        	 outputStream = new java.io.ByteArrayOutputStream();
        	 JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
         }
         if(tuSpeichernToXml){
        	 JasperExportManager.exportReportToXmlFile(jasperPrint, Konstanten.REPORT_BA_001_XML, false);
         }
      }catch(Exception ex) {
    	 ex.printStackTrace();
         String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
         System.out.println(connectMsg);
      }
	  System.out.println("runReport OK");
      return outputStream;
   }

   
   
   public static void runReportHTMLandMailToAdress(String reportFile, String eMail, String[] attachments) throws MessagingException, IOException {
	   //speichere die HTML-Datei
	   runReport(null,reportFile,false,false,false,true,false, false,null);
	   //Versende die HTML-Datei an die E-Mail-Adresse
	   
//		   SendJavaMail.postMailToGmx(
//				   eMail,    
//				   "subjecttest", 
//				   "messagetest", 
//				   "wladimir.stoll@gmx.de",
//				   "wladimir.stoll@gmx.de",
//				   "ksutksut");
		   
//		   SendJavaMail.postMailToGmxAttachment(
//				   eMail,    
//				   "subjecttest", 
//				   "messagetest", 
//				   "wladimir.stoll@gmx.de", //ziel
//				   "wladimir.stoll@gmx.de", //gmxUser
//				   "ksutksut", //gmxPWD
//				   attachments);
//		   
//		   SendJavaMail.postMailToKdAttachment(
//				   eMail,    
//				   "subject: HTML: "+reportFile, 
//				   "messagetest", 
//				   "Wladimir.Stoll@kabelmail.de",
//				   "Wladimir.Stoll@kabelmail.de", //"tusklo" wäre falsche (ist nur ein Portal-User)
//				   "ksutksut", //kbPwd
//				   attachments);

		SendJavaMail.postMail(
				"smtp.superkabel.de",
				25,
				eMail, 
				"subjecttest HTML:"+reportFile,
				"messagetest", 
				"Wladimir.Stoll@kabelmail.de",
				"Wladimir.Stoll@kabelmail.de", // "tusklo" wäre falsche (ist nur
												// ein Portal-User)
				"ksutksut", // kbPwd
				attachments,
				null
				);		   
	   
		SendJavaMail.postMail(
				"mail.gmx.net",
				25,
				eMail, 
				"subjecttest HTML:"+reportFile,
				"messagetest", 
				"wladimir.stoll@gmx.de",
				"wladimir.stoll@gmx.de", //
				"ksutksut", // kbPwd
				attachments,
				null
				);		   
	   
//	   new SendJavaMail().postMail(recipient, subject,       message,      from);
	   
   }
   
   public static void runReportXmlAndMailToAdress(String reportFile, String eMail, String[] attachments) throws MessagingException, IOException {
	   //speichere die HTML-Datei
	   runReport(null,reportFile,false,false,false,false,false, true,null);
		   
//		   SendJavaMail.postMailToGmxAttachment(
//				   eMail,    
//				   "subjecttest", 
//				   "messagetest", 
//				   "wladimir.stoll@gmx.de",
//				   "wladimir.stoll@gmx.de", //gmxUser
//				   "ksutksut", //gmxPWD
//				   attachments);
		   
//		   SendJavaMail.postMailToKdAttachment(
//				   eMail,    
//				   "subjecttest XML: " + reportFile, 
//				   "messagetest", 
//				   "Wladimir.Stoll@kabelmail.de",
//				   "Wladimir.Stoll@kabelmail.de", //"tusklo" wäre falsche (ist nur ein Portal-User)
//				   "ksutksut", //kbPwd
//				   attachments);
		   
		SendJavaMail.postMail(
				"smtp.superkabel.de",
				25,
				eMail, 
				"subjecttest XML:"+reportFile,
				"messagetest", 
				"Wladimir.Stoll@kabelmail.de",
				"Wladimir.Stoll@kabelmail.de", // "tusklo" wäre falsche (ist nur
												// ein Portal-User)
				"ksutksut", // kbPwd
				attachments,
				null
				);		   
	   
		SendJavaMail.postMail(
				"mail.gmx.net",
				25,
				eMail, 
				"subjecttest XML:"+reportFile,
				"messagetest", 
				"wladimir.stoll@gmx.de",
				"wladimir.stoll@gmx.de", //
				"ksutksut", // kbPwd
				attachments,
				null
				);		   
	   
//	   new SendJavaMail().postMail(recipient, subject,       message,      from);
	   
   }
   
   
   public static void runReportView(Map parameters, String reportFile, String reportFileJasper) {
	   //speichere die HTML-Datei
	   runReport(parameters,reportFile,true,false,false,false,false, false,reportFileJasper);
	   //Versende die HTML-Datei an die E-Mail-Adresse
	   
   }
   
   

   /**
   * Uses runReport method to connect to the database and to prepare and view the report.
   * @param args Takes 4 arguments as an input: databaseName, userName, password, reportFileLocation
   * args[0] holds the location of the Jasper Report file (.jrxml)
   */
   public static void main(String[] args) {
      if (args.length == 1) {
         String reportFile = args[0];
         runReportView(null, reportFile,null);
      }else{
         System.out.println("Usage:");
         System.out.println("java ReportDriver reportFileLocation");
      }
      return;
   }


	public void runReportPdfAndMailToAdress(String reportJrxml, String eMail,
			String[] attachments) throws MessagingException, IOException {
		// speichere die HTML-Datei
		runReport(null, reportJrxml, false, false, true, false, false, false,null);

//		SendJavaMail.postMailToKdAttachment(eMail, "subjecttest PDF:"+reportJrxml,
//				"messagetest", "Wladimir.Stoll@kabelmail.de",
//				"Wladimir.Stoll@kabelmail.de", // "tusklo" wäre falsche (ist nur
//												// ein Portal-User)
//				"ksutksut", // kbPwd
//				attachments
//				);

		SendJavaMail.postMail(
				"smtp.superkabel.de",
				25,
				eMail, 
				"subjecttest PDF:"+reportJrxml,
				"messagetest", 
				"Wladimir.Stoll@kabelmail.de",
				"Wladimir.Stoll@kabelmail.de", // "tusklo" wäre falsche (ist nur
												// ein Portal-User)
				"ksutksut", // kbPwd
				attachments,
				null
				);
		
		SendJavaMail.postMail(
				"mail.gmx.net",
				25,
				eMail, 
				"subjecttest PDF:"+reportJrxml,
				"messagetest", 
				"wladimir.stoll@gmx.de",
				"wladimir.stoll@gmx.de", //
				"ksutksut", // kbPwd
				attachments,
				null
				);
		
		
		
	}
	
	public void runReportPdfStreamAndMailToAdress(String reportJrxml, String eMail,
			String[] attachments) throws MessagingException, IOException {
		// speichere die HTML-Datei
		System.out.println("runReport");
		OutputStream pdfStream = runReport(null, reportJrxml, false, false, false, false, true, false, null);
		System.out.println("runReport OK");

		System.out.println("postMail");
		SendJavaMail.postMail(
				"smtp.superkabel.de",
				25,
				eMail, 
				"subjecttest PDF-Stream:"+reportJrxml,
				"messagetest", 
				"Wladimir.Stoll@kabelmail.de",
				"Wladimir.Stoll@kabelmail.de", // "tusklo" wäre falsche (ist nur
												// ein Portal-User)
				"ksutksut", // kbPwd
				attachments,
				pdfStream
				);
		
		SendJavaMail.postMail(
				"mail.gmx.net",
				25,
				eMail, 
				"subjecttest PDF-Stream:"+reportJrxml,
				"messagetest", 
				"wladimir.stoll@gmx.de",
				"wladimir.stoll@gmx.de", //
				"ksutksut", // kbPwd
				attachments,
				pdfStream
				);
		
		System.out.println("postMail OK");
	}
	
}