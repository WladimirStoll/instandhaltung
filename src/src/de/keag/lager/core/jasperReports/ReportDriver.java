package de.keag.lager.core.jasperReports;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;

import de.keag.lager.core.email.SendJavaMail;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.LagerProperties;
import de.keag.lager.db.connection.LagerConnection;
import net.sf.jasperreports.view.JasperViewer;
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
   * @param databaseName holds database name,
   * @param userName holds user name
   * @param password holds password to connect the database,
   * @param reportFile holds the location of the Jasper Report file (.jrxml)
   */
   private static OutputStream runReport(String reportFile, 
		   boolean tuAnzeigen, 
		   boolean tuDrucken, 
		   boolean tuSpeichernPDF, 
		   boolean tuSpeichernHTML, 
		   boolean tuSpeichernToPdfStream, 
		   boolean tuSpeichernToXml) {
	  System.out.println("runReport");
   	 OutputStream outputStream = null;
      try{
         JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
         Connection jdbcConnection = connectDB();
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jdbcConnection);
         if (tuAnzeigen){
             JasperViewer.viewReport(jasperPrint);
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
         String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
         System.out.println(connectMsg);
      }
	  System.out.println("runReport OK");
      return outputStream;
   }

   
   
   public static void runReportHTMLandMailToAdress(String reportFile, String eMail, String[] attachments) throws MessagingException, IOException {
	   //speichere die HTML-Datei
	   runReport(reportFile,false,false,false,true,false,false);
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
	   runReport(reportFile,false,false,false,false,false,true);
		   
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
   
   
   public static void runReportView(String reportFile) {
	   //speichere die HTML-Datei
	   runReport(reportFile,true,false,false,false,false,false);
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
         runReportView(reportFile);
      }else{
         System.out.println("Usage:");
         System.out.println("java ReportDriver reportFileLocation");
      }
      return;
   }


	public void runReportPdfAndMailToAdress(String reportJrxml, String eMail,
			String[] attachments) throws MessagingException, IOException {
		// speichere die HTML-Datei
		runReport(reportJrxml, false, false, true, false, false, false);

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
		OutputStream pdfStream = runReport(reportJrxml, false, false, false, false, true, false);
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