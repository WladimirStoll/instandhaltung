package de.keag.lager.panels.frame.katalog;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

public class KatalogBean extends Bean {

	private Integer id = 0;
	private String name = "";
	
	@Override
	public ModelKnotenBean getModelKnotenBean() {
		return null;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		// TODO Auto-generated method stub
		return null;
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
	

}
