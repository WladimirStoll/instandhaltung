package de.keag.lager.panels.frame.wartungstyp.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;

public class WartungsTypBean extends Bean {
	private Integer id = 0;
	private String name = ""; 
	public WartungsTypBean() {
		super();
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
		
		
		return getFehlerList();
	}

	

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungsTypBean){
			WartungsTypBean wartungsTypBean = (WartungsTypBean) bean;
			if (wartungsTypBean==null){
				return false;
			}else{
				return (this.getId() == wartungsTypBean.getId());
			}
		}else return false;
    }
	
	@Override
	public String toString(){
		return getName();
	}
	
	
}
