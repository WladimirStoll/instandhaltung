package de.keag.lager.panels.frame.halle.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class HalleBean extends Bean {
	
	private Integer id;
	private String name;
	private Integer nummer;
	
	private ArrayList<EtageBean> etageBeans = null;
	private ArrayList<ZeileBean> zeileBeans = null;
	

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof HalleBean){
			HalleBean tempBean = (HalleBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId()&&(!this.getId().equals(0)));
			}
		}else return false;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_HALLE + getId();
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

	public ArrayList<EtageBean> getEtageBeans() {
		if(etageBeans==null){
			etageBeans = new ArrayList<EtageBean>();
		}
		return etageBeans;
	}

	public void setEtageBeans(ArrayList<EtageBean> etageBeans) {
		if (etageBeans==null){
			etageBeans = new ArrayList<EtageBean>();
		}
		if (this.etageBeans != etageBeans){
			this.etageBeans = etageBeans;
			beanIstGeaendert();
		}
		
	}

	public ArrayList<ZeileBean> getZeileBeans() {
		if(zeileBeans==null){
			zeileBeans = new ArrayList<ZeileBean>();
		}
		return zeileBeans;
	}

	public void setZeileBeans(ArrayList<ZeileBean> zeileBeans) {
		if (zeileBeans==null){
			zeileBeans = new ArrayList<ZeileBean>();
		}
		if (this.zeileBeans != zeileBeans){
			this.zeileBeans = zeileBeans;
			beanIstGeaendert();
		}
		
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getLagerOrt() {
		return getName();
	}

	

	public void setNummer(Integer nummer) {
		if (!this.getNummer().equals(nummer)){
			this.nummer = nummer;
			beanIstGeaendert();
		}
	}

	public Integer getNummer() {
		if (nummer == null) {
			nummer = 0;
		}
		return nummer;
	}
	
}


