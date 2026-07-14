package de.keag.lager.panels.frame.email;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

public class EmailBean extends Bean {
	private String beschreibung = "";
	private String email = "";

	public String getEmail() {
		return email;
	}
	

	public String getBeschreibung() {
		if(beschreibung==null){
			beschreibung = "";
		}
		return beschreibung;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return super.hashCode();
	}
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
		if(!this.beschreibung.equals(beschreibung)){
			this.beschreibung = beschreibung;
			beanIstGeaendert();
		}
	}


	public void setEmail(String email) {
		if (email==null){
			email = "";
		}
		if(!this.email.equals(email)){
			this.email = email;
			beanIstGeaendert();
		}
	}


	@Override
	public ModelKnotenBean getModelKnotenBean() {
		return null;
	}


	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
	}


	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		ArrayList<Fehler>  errors = new ArrayList<Fehler>();
		if(email==null || email.equals("")){
			errors.add(new Fehler(40));
		}
		return errors;
	}


	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof EmailBean){
			EmailBean tempBean = (EmailBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getEmail()).equalsIgnoreCase(tempBean.getEmail());
			}
		}else return false;
	}

}
