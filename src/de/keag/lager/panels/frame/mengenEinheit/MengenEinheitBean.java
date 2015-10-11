package de.keag.lager.panels.frame.mengenEinheit;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.login.LoginBean;

public class MengenEinheitBean extends Bean{
	public static final Integer VORDEFINIERT = 1;
	private Integer id = 0;
	private String name = ""; 
	private Integer vordefiniert;
	
	private BeanDBStatus beanStatus; //Für DB-Speicherung
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	public BeanDBStatus getBeanStatus() {
		if(beanStatus==null){
			beanStatus = BeanDBStatus.FEHLERHAFT;
		}
		return beanStatus;
	}
	public void setBeanStatus(BeanDBStatus beanStatus) {
		this.beanStatus = beanStatus;
	}	
	
	public MengenEinheitBean() {
		super();
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
	public ModelKnotenBean getModelKnotenBean() {
		Log.log().severe("nicht implementiert");
		return null;
	}
	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		Log.log().severe("nicht implementiert");
		
	}
	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof MengenEinheitBean){
			MengenEinheitBean tempBean = (MengenEinheitBean) bean;
			if (tempBean==null){
				return false;
			}else{
				//id' sind gleich und ungleich 0
				return (this.getId() == tempBean.getId())&(tempBean.getId()!=0); 
			}
		}else return false;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public void setVordefiniert(Integer vordefiniert) {
		this.vordefiniert = vordefiniert;
	}

	public Integer getVordefiniert() {
		if (this.vordefiniert==null){
				this.vordefiniert = 0;
		}
		return vordefiniert;
	}
}
