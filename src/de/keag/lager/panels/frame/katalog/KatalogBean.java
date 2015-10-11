package de.keag.lager.panels.frame.katalog;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class KatalogBean extends Bean {

	private Integer id = 0;
	private String name = "";
	private LhBean herstellerLieferantBean = null;
	
//	@Override
//	public ModelKnotenBean getModelKnotenBean() {
//		return null;
//	}

	@Override
	public int hashCodeForModelKnoten() {
//		return getId();
		return Konstanten.HASH_OFFSET_KATALOG + getId();
		
	}

//	@Override
//	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
//		this.modelKnotenBean = modelKnotenBean;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
	}

	public String getName() {
		if (this.name==null){
			this.name = "";
		}
		return name;
	}

	public void setName(String name) {
		if(!getName().equals(name)){
			this.name = name;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if (getId().equals(0)) {
			getFehlerList().add(new Fehler(10));
		}
		if (getName().equals("")) {
			getFehlerList().add(new Fehler(44));
		}
		return getFehlerList();
	}
	public boolean equals(KatalogBean katalogBean){
		if (katalogBean==null){
			return false;
		}else{
			//id' sind gleich und ungleich 0
			return (this.getId() == katalogBean.getId())&(katalogBean.getId()!=0); 
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof KatalogBean){
			KatalogBean tempBean = (KatalogBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}

	public LhBean getHerstellerLieferantBean() {
		if(herstellerLieferantBean==null){
			herstellerLieferantBean = new LhBean();
		}
		return herstellerLieferantBean;
	}

	public void setHerstellerLieferantBean(LhBean herstellerLieferantBean) {
		if (!this.getHerstellerLieferantBean().equals(herstellerLieferantBean)){
			this.herstellerLieferantBean = herstellerLieferantBean;
			beanIstGeaendert();
		}
	}
	

}
