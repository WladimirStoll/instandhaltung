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
package de.keag.lager.panels.frame.lieferanthersteller.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;

public class LhBean extends Bean{
	private Integer id = 0;
	private String name = ""; 
	private String adresse_land = ""; 
	private String adresse_plz = ""; 
	private String adresse_stadt = ""; 
	private String adresse_strasse = ""; 
	private String telefon = ""; 
	private String fax = ""; 
	private String email = ""; 
	private String ansprechpartner = "";
	private ArrayList<LhKatalogBean> lhKatalogBeans = null;
	
	public LhBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	public ArrayList<LhKatalogBean> getLhKatalogBeans() {
		if(lhKatalogBeans==null){
			lhKatalogBeans = new ArrayList<LhKatalogBean>();
		}
		return lhKatalogBeans;
	}
	
	public void setLhKatalogBeans(ArrayList<LhKatalogBean> katalogBeans) {
		if (katalogBeans==null){
			katalogBeans = new ArrayList<LhKatalogBean>();
		}
		if (this.lhKatalogBeans != katalogBeans){
			this.lhKatalogBeans = katalogBeans;
			beanIstGeaendert();
		}
	}
	
	
	@Override
	public String toString(){
		return getName();
//		return "LhBean: Status:"+	getBeanDBStatus()+
//		" id:" + getId()+
//		" name:" + getName();
	}
	public Integer getId() {
		if (id == null){
			id = 0;
		}
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
	public String getAdresse_land() {
		if (adresse_land==null){
			adresse_land = "";
		}
		
		return adresse_land;
	}
	public void setAdresse_land(String adresseLand) {
		if (!this.adresse_land.equals(adresseLand)){
			this.adresse_land = adresseLand;
			beanIstGeaendert();
		}
	}

	public String getAdresse_plz() {
		if (adresse_plz==null){
			adresse_plz = "";
		}
		
		return adresse_plz;
	}
	public void setAdresse_plz(String adressePlz) {
		if (!this.adresse_plz.equals(adressePlz)){
			this.adresse_plz = adressePlz;
			beanIstGeaendert();
		}
	}
	public String getAdresse_stadt() {
		if (adresse_stadt==null){
			adresse_stadt = "";
		}
		
		return adresse_stadt;
	}
	public void setAdresse_stadt(String adresseStadt) {
		if (!this.adresse_stadt.equals(adresseStadt)){
			this.adresse_stadt = adresseStadt;
			beanIstGeaendert();
		}
	}
	public String getAdresse_strasse() {
		if (adresse_strasse==null){
			adresse_strasse = "";
		}
		
		return adresse_strasse;
	}
	public void setAdresse_strasse(String adresseStrasse) {
		if (!this.adresse_strasse.equals(adresseStrasse)){
			this.adresse_strasse = adresseStrasse;
			beanIstGeaendert();
		}
	}
	public String getTelefon() {
		if (telefon ==null){
			telefon = "";
		}
		return telefon;
	}
	public void setTelefon(String telefon) {
		if (!this.telefon.equals(telefon)){
			this.telefon = telefon;
			beanIstGeaendert();
		}
	}
	public String getFax() {
		if (fax == null){
			fax = "";
		}
		return fax;
	}
	public void setFax(String fax) {
		if (!this.fax.equals(fax)){
			this.fax = fax;
			beanIstGeaendert();
		}
	}
	public String getEmail() {
		if (email==null){
			email = "";
		}
		return email;
	}
	public void setEmail(String email) {
		if (!this.email.equals(email)){
			this.email = email;
			beanIstGeaendert();
		}
	}
	public String getAnsprechpartner() {
		if (ansprechpartner==null){
			ansprechpartner = "";
		}
		return ansprechpartner;
	}
	public void setAnsprechpartner(String ansprechpartner) {
		if (!this.ansprechpartner.equals(ansprechpartner)){
			this.ansprechpartner = ansprechpartner;
			beanIstGeaendert();
		}
	}
//	@Override
//	public ModelKnotenBean getModelKnotenBean() {
//		Log.log().severe("nicht implementiert");
//		return null;
//	}
//	@Override
//	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}

	
	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(59));
		}
		
		if(getName().trim().equals("")
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(60));
		}
//		if(getAdresse_land().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(61));
//		}
//		if(getAdresse_plz().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(62));
//		}
//		if(getAdresse_stadt().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(63));
//		}
//		if(getAdresse_strasse().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(64));
//		}
//		if(getTelefon().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(65));
//		}
//		if(getFax().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(66));
//		}
//		if(getEmail().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(67));
//		}
//		if(getAnsprechpartner().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(68));
//		}
		return getFehlerList();
	}
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof LhBean){
			LhBean tempBean = (LhBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}
}	


//public class BenutzerBean extends Bean {
//	private Integer id = 0;
//	private String fax = ""; 
//	private String vorfax = ""; 
//	private String loginName = ""; 
//	private String password = "";
//	private String email = "";
//	private ArrayList<BenutzerAbteilungBean> benutzerAbteilungBeans = null;
//	private ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans = null;
//	private Integer Copi_ba_email = 0;
//	public BenutzerBean() {
//		super();
//	}
	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		if (!this.id.equals(id)){
//			this.id = id;
//			beanIstGeaendert();
//		}
//	}
//	public String getName() {
//		return fax;
//	}
//	public void setName(String fax) {
//		if (!this.fax.equals(fax)){
//			this.fax = fax;
//			beanIstGeaendert();
//		}
//	}
//	public String getVorfax() {
//		return vorfax;
//	}
//	public void setVorfax(String vorfax) {
//		if (!this.vorfax.equals(vorfax)){
//			this.vorfax = vorfax;
//			beanIstGeaendert();
//		}
//	}
//	public String getLoginName() {
//		return loginName;
//	}
//	public void setLoginName(String loginName) {
//		if (!this.loginName.equals(loginName)){
//			this.loginName = loginName;
//			beanIstGeaendert();
//		}
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		if (!this.password.equals(password)){
//			this.password = password;
//			beanIstGeaendert();
//		}
//		
//	}


//	@Override
//	public void setModelKnotenBean(BenutzerModelKnotenBean benutzerModelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}
//
//	@Override
//	public ArrayList<Fehler> pruefeEigeneDaten() {
//		getFehlerList().clear();
//		
//		if(getId().equals(0)
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(44));
//		}
//		
//		if(getName().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(44));
//		}
//		
//		if(getLoginName().trim().equals("") 
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(46));
//		}
//		
//		if(getPassword().trim().equals("") 
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(47));
//		}
//		
//		if(getVorfax().trim().equals("")  
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(45));
//		}
//		
//		if(getLoginName().trim().equals("") 
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(46));
//		}
		
//		if(getEmail().trim().equals("") 
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(48));
//		}
//		
//		return getFehlerList();
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		if(!this.email.equals(email)){
//			this.email = email;
//			beanIstGeaendert();
//		}
//	}
//
//
//
//	public Integer getCopi_ba_email() {
//		return Copi_ba_email;
//	}
//
//	public void setCopi_ba_email(Integer copiBaEmail) {
//		if (!this.Copi_ba_email.equals(copiBaEmail)){
//			Copi_ba_email = copiBaEmail;
//			beanIstGeaendert();
//		}
//	}
//
//	public ArrayList<BenutzerAbteilungBean> getBenutzerAbteilungBeans() {
//		if(benutzerAbteilungBeans==null){
//			benutzerAbteilungBeans = new ArrayList<BenutzerAbteilungBean>();
//		}
//		return benutzerAbteilungBeans;
//	}
//	
//	public ArrayList<BenutzerZugriffsrechtBean> getBenutzerZugriffsrechtBeans() {
//		if(benutzerZugriffsrechtBeans==null){
//			benutzerZugriffsrechtBeans = new ArrayList<BenutzerZugriffsrechtBean>();
//		}
//		return benutzerZugriffsrechtBeans;
//	}
//	
//
//	public void setBenutzerAbteilungBeans(ArrayList<BenutzerAbteilungBean> benutzerAbteilungBeans) {
//		if (benutzerAbteilungBeans==null){
//			benutzerAbteilungBeans = new ArrayList<BenutzerAbteilungBean>();
//		}
//		if (this.benutzerAbteilungBeans != benutzerAbteilungBeans){
//			this.benutzerAbteilungBeans = benutzerAbteilungBeans;
//			beanIstGeaendert();
//		}
//	}
//	
//	public void setBenutzerZugriffsrechtBeans(ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans) {
//		if (benutzerZugriffsrechtBeans==null){
//			benutzerZugriffsrechtBeans = new ArrayList<BenutzerZugriffsrechtBean>();
//		}
//		if (this.benutzerZugriffsrechtBeans != benutzerZugriffsrechtBeans){
//			this.benutzerZugriffsrechtBeans = benutzerZugriffsrechtBeans;
//			beanIstGeaendert();
//		}
//	}
//	
//
//	@Override
//	public boolean equals(Bean bean) {
//		if (bean instanceof BenutzerBean){
//			BenutzerBean benutzerBean = (BenutzerBean) bean;
//			if (benutzerBean==null){
//				return false;
//			}else{
//				return (this.getId() == benutzerBean.getId());
//			}
//		}else return false;
//    }
//	
//	
//}
