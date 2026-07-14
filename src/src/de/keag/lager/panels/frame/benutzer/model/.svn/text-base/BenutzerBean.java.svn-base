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
