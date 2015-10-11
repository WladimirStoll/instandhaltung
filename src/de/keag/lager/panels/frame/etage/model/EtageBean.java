package de.keag.lager.panels.frame.etage.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.halle.model.HalleBean;

public class EtageBean extends Bean {
	
	private Integer id;
	private String name;
	private HalleBean halleBean;

	public EtageBean() {
		super();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof EtageBean){
			EtageBean tempBean = (EtageBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_ETAGE + getId();
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		
		if(getName().trim().equals("")){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		
		if(getHalleBean().getId().equals(0)){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(69));
		}
		
		return getFehlerList();
	}

	public Integer getId() {
		if (id == null){
			id = 0;
		}
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
	}

	public String getName() {
		if (name == null){
			name = "";
		}
		return name;
	}

	public void setName(String name) {
		if (!getName().equals(name)){
			beanIstGeaendert();
			this.name = name;
		}
	}

	public HalleBean getHalleBean() {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		return halleBean;
	}

	public void setHalleBean(HalleBean halleBean) {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		if (getHalleBean() != halleBean){
			this.halleBean = halleBean;
			beanIstGeaendert();
		}
		
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public String getLagerOrt() {
		return this.getHalleBean().getLagerOrt() + "(" +getName()+")";
	}

}


