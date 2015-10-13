package de.keag.lager.core.jasperReports.ba;

import java.io.IOException;

import javax.mail.MessagingException;

import de.keag.lager.core.jasperReports.ReportDriver;
import de.keag.lager.core.main.Konstanten;

public class BaReport001 {
	public BaReport001() {
		super();
	}
	public void test(int ba_id,String eMail) throws MessagingException, IOException{
		/**
		 * Achtung! Aus dem Bericht XML muss "groovy" entfernt werden. Stattdessen "java" schreiben.
		 */
//		ReportDriver.getOneInstance().runReportView(Konstanten.REPORT_BA_001_JRXML);
		if (eMail != null && !eMail.equals("")) {
			
			ReportDriver.getOneInstance().runReportHTMLandMailToAdress(
					Konstanten.REPORT_BA_001_JRXML, eMail,new String[]{Konstanten.REPORT_BA_001_HTML});
			
			ReportDriver.getOneInstance().runReportXmlAndMailToAdress(
					Konstanten.REPORT_BA_001_JRXML, eMail,new String[]{Konstanten.REPORT_BA_001_XML});
			
			ReportDriver.getOneInstance().runReportPdfAndMailToAdress(
					Konstanten.REPORT_BA_001_JRXML, eMail,new String[]{Konstanten.REPORT_BA_001_PDF});

			ReportDriver.getOneInstance().runReportPdfStreamAndMailToAdress(
					Konstanten.REPORT_BA_001_JRXML, eMail,null);

			ReportDriver.getOneInstance().runReportView(null, Konstanten.REPORT_BA_001_JRXML,Konstanten.REPORT_BA_001_JASPER);
			
		}
	}
	public static void main(String[] args) {
		//Test des Reports
		try {
			new BaReport001().test(1, "wladimir.stoll@gmx.de");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
