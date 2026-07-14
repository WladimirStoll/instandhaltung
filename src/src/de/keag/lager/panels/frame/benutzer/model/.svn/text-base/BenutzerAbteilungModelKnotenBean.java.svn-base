package de.keag.lager.panels.frame.benutzer.model;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;


public class BenutzerAbteilungModelKnotenBean extends ModelKnotenBean {
	
	public BenutzerAbteilungModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.BENUTZER_ABTEILUNG,vater);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getAbteilung()!=null){
				return "Abteilung " + getIBean().getAbteilung().getAbteilungName();
			}else{
				return "Abteilung ?";
			}
		else return "Abteilung " ;
	}
	
	@Override
	public BenutzerAbteilungBean getIBean() {
		return (BenutzerAbteilungBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BenutzerAbteilungBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
