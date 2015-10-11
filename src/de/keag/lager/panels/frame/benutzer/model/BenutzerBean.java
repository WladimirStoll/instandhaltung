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

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;

public class BenutzerBean extends Bean {
	private Integer id = 0;
	private String name = ""; 
	private String vorname = ""; 
	private String loginName = ""; 
	private String password = "";
	private String email = "";
	private ArrayList<BenutzerAbteilungBean> benutzerAbteilungBeans = null;
	private ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans = null;
	private Integer Copi_ba_email = 0;
	public BenutzerBean() {
		super();
	}
	
	public AbteilungBean getErsteAbteilungDesBenutzers(){
		if (getBenutzerAbteilungBeans().size()>0){
			return getBenutzerAbteilungBeans().get(0).getAbteilung();
		}
		return new AbteilungBean(); 
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if (!this.id.equals(id)){
			this.id = id;
			beanIstGeaendert();
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (!this.name.equals(name)){
			this.name = name;
			beanIstGeaendert();
		}
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		if (!this.vorname.equals(vorname)){
			this.vorname = vorname;
			beanIstGeaendert();
		}
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		if (!this.loginName.equals(loginName)){
			this.loginName = loginName;
			beanIstGeaendert();
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if (!this.password.equals(password)){
			this.password = password;
			beanIstGeaendert();
		}
		
	}


//	@Override
//	public void setModelKnotenBean(BenutzerModelKnotenBean benutzerModelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		
		if(getName().trim().equals("")
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		
		if(getLoginName().trim().equals("") 
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(46));
		}
		
		if(getPassword().trim().equals("") 
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(47));
		}
		
		if(getVorname().trim().equals("")  
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(45));
		}
		
		if(getLoginName().trim().equals("") 
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(46));
		}
		
//		if(getEmail().trim().equals("") 
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(48));
//		}
		
		return getFehlerList();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(!this.email.equals(email)){
			this.email = email;
			beanIstGeaendert();
		}
	}



	public Integer getCopi_ba_email() {
		return Copi_ba_email;
	}

	public void setCopi_ba_email(Integer copiBaEmail) {
		if (!this.Copi_ba_email.equals(copiBaEmail)){
			Copi_ba_email = copiBaEmail;
			beanIstGeaendert();
		}
	}

	
	public ArrayList<BenutzerZugriffsrechtBean> getBenutzerZugriffsrechtBeans() {
		if(benutzerZugriffsrechtBeans==null){
			benutzerZugriffsrechtBeans = new ArrayList<BenutzerZugriffsrechtBean>();
		}
		return benutzerZugriffsrechtBeans;
	}
	

	public ArrayList<BenutzerAbteilungBean> getBenutzerAbteilungBeans() {
		if(benutzerAbteilungBeans==null){
			benutzerAbteilungBeans = new ArrayList<BenutzerAbteilungBean>();
		}
		return benutzerAbteilungBeans;
	}
	
	public void setBenutzerAbteilungBeans(ArrayList<BenutzerAbteilungBean> benutzerAbteilungBeans) {
		if (benutzerAbteilungBeans==null){
			benutzerAbteilungBeans = new ArrayList<BenutzerAbteilungBean>();
		}
		if (this.benutzerAbteilungBeans != benutzerAbteilungBeans){
			this.benutzerAbteilungBeans = benutzerAbteilungBeans;
			beanIstGeaendert();
		}
	}
	
	public void setBenutzerZugriffsrechtBeans(ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans) {
		if (benutzerZugriffsrechtBeans==null){
			benutzerZugriffsrechtBeans = new ArrayList<BenutzerZugriffsrechtBean>();
		}
		if (this.benutzerZugriffsrechtBeans != benutzerZugriffsrechtBeans){
			this.benutzerZugriffsrechtBeans = benutzerZugriffsrechtBeans;
			beanIstGeaendert();
		}
	}
	

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BenutzerBean){
			BenutzerBean benutzerBean = (BenutzerBean) bean;
			if (benutzerBean==null){
				return false;
			}else{
				return (this.getId() == benutzerBean.getId());
			}
		}else return false;
    }
	
	@Override
	public String toString(){
		return getVorname() + " " + getName();
	}
	
	
}
