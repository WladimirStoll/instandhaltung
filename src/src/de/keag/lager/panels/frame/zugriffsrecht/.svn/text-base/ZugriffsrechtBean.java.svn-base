package de.keag.lager.panels.frame.zugriffsrecht;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.email.ProviderBean;
import de.keag.lager.core.fehler.Fehler;


public class ZugriffsrechtBean extends Bean{
	private Integer id = 0;	
    private String zugriffsrechtName = "";
	
	
	public ZugriffsrechtBean(int zugriffsRechtID) {
		this();
		setId(zugriffsRechtID);
	}
    
    
	public ZugriffsrechtBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}


	@Override
	public String toString(){
		return "ZugriffsrechtBean:"+	getBeanDBStatus()+
		" Name:" + getZugriffsrechtName();
//		" fkBenutzerAbsender:" + getAbsenderBenutzerBean()+
//		" fkBenutzerAnnehmer:" + getAnnehmerBenutzerBean()+
//		" fkHerstellerLieferant:" + getLhBean();

	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		return getFehlerList();
	}

	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof ZugriffsrechtBean){
			ZugriffsrechtBean tempBean = (ZugriffsrechtBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId())&(tempBean.getId()!=0); 
			}
		}else return false;
    }
	



	public void setZugriffsrechtName(String abteilungName) {
		this.zugriffsrechtName = abteilungName;
	}

	public String getZugriffsrechtName() {
		return zugriffsrechtName;
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		// TODO Auto-generated method stub
		
	}
	
}

