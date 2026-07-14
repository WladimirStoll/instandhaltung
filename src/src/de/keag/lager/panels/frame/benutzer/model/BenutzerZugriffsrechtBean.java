package de.keag.lager.panels.frame.benutzer.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;

public class BenutzerZugriffsrechtBean extends Bean{
	
	private BenutzerBean benutzerBean = null;
	private ZugriffsrechtBean zugriffsrechtBean = null;
	
	public BenutzerZugriffsrechtBean() {
		super();
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return getZugriffsrecht().getId();
	}
	
	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if(zugriffsrechtBean.equals(0)){
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

	public ZugriffsrechtBean getZugriffsrecht() {
		if (zugriffsrechtBean==null){
			zugriffsrechtBean = new ZugriffsrechtBean();
		}
		return zugriffsrechtBean;
	}

	public void setZugriffsrecht(ZugriffsrechtBean bean) {
		if (this.zugriffsrechtBean==null || !this.zugriffsrechtBean.equals(bean)){
			zugriffsrechtBean = bean;
			beanIstGeaendert();
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BenutzerZugriffsrechtBean){
			BenutzerZugriffsrechtBean tempBean = (BenutzerZugriffsrechtBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return this.getZugriffsrecht().equals(tempBean.getZugriffsrecht())
					&& this.getBenutzer().equals(tempBean.getBenutzer());
			}
		}else return false;
	}
	
}
