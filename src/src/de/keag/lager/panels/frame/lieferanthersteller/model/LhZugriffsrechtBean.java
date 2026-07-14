package de.keag.lager.panels.frame.lieferanthersteller.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;

public class LhZugriffsrechtBean extends Bean{
	
	private LhBean lhBean = null;
	private ZugriffsrechtBean zugriffsrechtBean = null;
	
	public LhZugriffsrechtBean() {
		super();
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
	public int hashCodeForModelKnoten() {
		return getZugriffsrecht().getId();
	}
	

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if(zugriffsrechtBean.equals(0)){
			getFehlerList().add(new Fehler(42));
		}
		if(lhBean.equals(0)){
			getFehlerList().add(new Fehler(43));
		}
		return getFehlerList();
	}

	public LhBean getLh() {
		if (lhBean==null){
			lhBean = new LhBean();
		}
		return lhBean;
	}

	public void setLh(LhBean lh) {
		
		if (this.lhBean==null || !this.lhBean.equals(lh)){
			this.lhBean = lh;
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
		if (bean instanceof LhZugriffsrechtBean){
			LhZugriffsrechtBean tempBean = (LhZugriffsrechtBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return this.getZugriffsrecht().equals(tempBean.getZugriffsrecht())
					&& this.getLh().equals(tempBean.getLh());
			}
		}else return false;
	}
	
}
