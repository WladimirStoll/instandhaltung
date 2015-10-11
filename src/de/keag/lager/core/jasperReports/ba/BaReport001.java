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
