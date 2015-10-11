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
