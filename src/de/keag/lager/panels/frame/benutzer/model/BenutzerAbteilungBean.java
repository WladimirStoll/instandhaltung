package de.keag.lager.panels.frame.benutzer.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;

public class BenutzerAbteilungBean extends Bean{
	
	private BenutzerBean benutzerBean = null;
	private AbteilungBean abteilungBean = null;
	
	public BenutzerAbteilungBean() {
		super();
	}

	@Override
	public int hashCodeForModelKnoten() {
		return getAbteilung().getId();
	}
	
	@Override
	public ModelKnotenBean getModelKnotenBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if(abteilungBean.equals(0)){
			getFehlerList().add(new Fehler(42));
		}
		if(benutzerBean.equals(0)){
			getFehlerList().add(new Fehler(43));
		}
		return getFehlerList();
	}

	public BenutzerBean getBenutzer() {
		if (benutzerBean==null){
			benutzerBean = new BenutzerBean();
		}
		return benutzerBean;
	}

	public void setBenutzer(BenutzerBean benutzer) {
		
		if (this.benutzerBean==null || !this.benutzerBean.equals(benutzer)){
			this.benutzerBean = benutzer;
			beanIstGeaendert();
		}
	}

	public AbteilungBean getAbteilung() {
		if (abteilungBean==null){
			abteilungBean = new AbteilungBean();
		}
		return abteilungBean;
	}

	public void setAbteilung(AbteilungBean abteilung) {
		if (this.abteilungBean==null || !this.abteilungBean.equals(abteilung)){
			abteilungBean = abteilung;
			beanIstGeaendert();
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BenutzerAbteilungBean){
			BenutzerAbteilungBean tempBean = (BenutzerAbteilungBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return this.getAbteilung().equals(tempBean.getAbteilung())
					&& this.getBenutzer().equals(tempBean.getBenutzer());
			}
		}else return false;
	}
	
}
