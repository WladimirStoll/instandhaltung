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
package de.keag.lager.panels.frame.benutzer.model;



import de.keag.lager.core.ISuchBean;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;



public class BenutzerSuchBean implements ISuchBean {
//	private Date erstellungsDatumVon;
//	private Date erstellungsDatumBis;
	private AbteilungBean abteilungBean;
	
	public BenutzerSuchBean() {
		super();
	}
	
//	public void setErstellungsDatumVon(Date erstellungsDatumVon) {
//		this.erstellungsDatumVon = erstellungsDatumVon;
//	}

//	public Date getErstellungsDatumBis() {
//		if (erstellungsDatumBis==null){
//			erstellungsDatumBis = new Date((365*24*60*60*1000)*100); //Anzahl der Millisekunden MAL Anzahl Jahre
//		}
//		return erstellungsDatumBis;
//	}
	
//	public Date getErstellungsDatumVon() {
//		if (erstellungsDatumVon==null){
//			erstellungsDatumVon = new Date(0); //Anzahl der Millisekunden MAL Anzahl Jahre
//		}
//		return erstellungsDatumVon;
//}

//	public void setErstellungsDatumBis(Date erstellungsDatumBis) {
//		this.erstellungsDatumBis = erstellungsDatumBis;
//	}

	//public AbteilungStatus Abteilung() {
	//	if (abteilungStatus==null){
	//		abteilungStatus = AbteilungStatus.FEHLERHAFT;
	//	}
	//	return abteilungStatus;
	//}

//	public void setAbteilungStatus(AbteilungStatus abteilungStatus) {
//	this.abteilungStatus = abteilungStatus;
//	}

//	@Override
//	public String toString(){
//		String result = "Abteilung:" + getFK_abteilung().toString();
//		result = result + ", Erstellungsdatum Von:" + getErstellungsDatumBis();
//		result = result + ", Erstellungsdatum Bis:" + getErstellungsDatumVon();
//		result = result + ", Status:" + getAbteilungStatus();
//		return result + super.toString();
//	}

//	private Object getAbteilungStatus() {
//		// TODO Auto-generated method stub
//		return null;
		
//	}

	
	public int getFK_abteilung() {
		// TODO Auto-generated method stub
		return 0;
	}

	public AbteilungBean getAbteilungBean() {
		if(abteilungBean==null){
			abteilungBean = new AbteilungBean();
		}
		return abteilungBean;
	}

	public void setAbteilungBean(AbteilungBean abteilungBean) {
		this.abteilungBean = abteilungBean;
	}


}
