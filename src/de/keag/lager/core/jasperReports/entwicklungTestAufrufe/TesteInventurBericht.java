package de.keag.lager.core.jasperReports.entwicklungTestAufrufe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import de.keag.lager.core.jasperReports.ReportDriver;
import de.keag.lager.core.main.Konstanten;

public class TesteInventurBericht {
	public TesteInventurBericht() {
		super();
	}
	
	/*
	 * http://www.alexander-merz.com/41.html
	 * http://www.poornerd.com/2010/12/30/date-range-parameters-in-jasper-reports-with-sql-server/
	 
	 <parameter name="FROMDATE" class="java.sql.Timestamp">
    	<defaultValueExpression><![CDATA[new java.sql.Timestamp(System.currentTimeMillis()-2628000000)]]></defaultValueExpression>
	 </parameter>
     <parameter name="TODATE" class="java.sql.Timestamp">
        <defaultValueExpression><![CDATA[new java.sql.Timestamp(System.currentTimeMillis())]]></defaultValueExpression>
     </parameter>
     
     
     ORDER_DATE between cast('$P!{FROMDATE}' as date) and cast('$P!{TODATE}' as date)
	 */
	public void test(int ba_id,String eMail) throws MessagingException, IOException{
		/**
		 * Achtung! Aus dem Bericht XML muss "groovy" entfernt werden. Stattdessen "java" schreiben.
		 */
//		ReportDriver.getOneInstance().runReportView(Konstanten.REPORT_BA_001_JRXML);
		if (eMail != null && !eMail.equals("")) {

			// Run-time report parameters
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("title", "Bestandsliste !Test!");
			parameters.put("bericht_id", "88");
			parameters.put("user", "usermuster");
			parameters.put("druckdatum", "druckdatum");
			parameters.put("bemerkung", "bemerkung");
			
			ReportDriver.getOneInstance().runReportView(parameters, Konstanten.REPORT_INVENTUR_LISTE_01_JRXML, Konstanten.REPORT_INVENTUR_LISTE_01_JASPER);
			
//			ReportDriver.getOneInstance().runReportHTMLandMailToAdress(
//					Konstanten.REPORT_INVENTUR_LISTE_01_JRXML, eMail,new String[]{Konstanten.REPORT_BA_001_HTML});
//			
//			ReportDriver.getOneInstance().runReportXmlAndMailToAdress(
//					Konstanten.REPORT_INVENTUR_LISTE_01_JRXML, eMail,new String[]{Konstanten.REPORT_BA_001_XML});
//			
//			ReportDriver.getOneInstance().runReportPdfAndMailToAdress(
//					Konstanten.REPORT_INVENTUR_LISTE_01_JRXML, eMail,new String[]{Konstanten.REPORT_BA_001_PDF});
//
//			ReportDriver.getOneInstance().runReportPdfStreamAndMailToAdress(
//					Konstanten.REPORT_INVENTUR_LISTE_01_JRXML, eMail,null);

			
		}
	}
	public static void main(String[] args) {
		//Test des Reports
		try {
			new TesteInventurBericht().test(1, "wladimir.stoll@gmx.de");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
