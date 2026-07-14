package de.keag.lager.panels.frame.abteilung;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.email.ProviderBean;
import de.keag.lager.core.fehler.Fehler;


public class AbteilungBean extends Bean{
	private Integer id = 0;	
    private String abteilungName = "";
	
	
	public AbteilungBean() {
		super();
	}
	

//	public java.sql.Date getErstellungsDatum() {
//		if (erstellungsDatum==null){
//			erstellungsDatum=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
//			erstellungsDatum.toGMTString();
//		}
//		return erstellungsDatum;
//	}
//	public void setErstellungsDatum(java.sql.Date erstellungsDatum) {
//		if(this.erstellungsDatum==null){
//			this.erstellungsDatum = erstellungsDatum;
//		}else{
//			if(this.erstellungsDatum.getTime()!=erstellungsDatum.getTime()){
//				beanIstGeaendert();
//				this.erstellungsDatum = erstellungsDatum;
//			}
//		}
//	}
//	public AnforderungStatus getStatus() {
//		return anforderungStatus;
//	}
//	public void setStatus(
//			AnforderungStatus status) {
//		if(status!=anforderungStatus){
//			this.anforderungStatus = status;
//			beanIstGeaendert();
//		}
//	}
//	public LhBean getLhBean() {
//		return lhBean;
//	}
//	public void setLhBean(LhBean lhBean) {
//		if (lhBean!=this.lhBean){
//			this.lhBean = lhBean;
//			beanIstGeaendert();
//		}
//	}
//	public BenutzerBean getAbsenderBenutzerBean() {
//		return absenderBenutzerBean;
//	}
//	public void setFk_benutzerAbsender(BenutzerBean fkBenutzerAbsender) {
//		if(fkBenutzerAbsender!=absenderBenutzerBean){
//			absenderBenutzerBean = fkBenutzerAbsender;
//			beanIstGeaendert();
//		}
//	}
//	public BenutzerBean getAnnehmerBenutzerBean() {
//		return annehmerBenutzerBean;
//	}
//	public void setFk_benutzerAnnehmer(BenutzerBean fkBenutzerAnnehmer) {
//		if(annehmerBenutzerBean != fkBenutzerAnnehmer){
//			annehmerBenutzerBean = fkBenutzerAnnehmer;
//			beanIstGeaendert();
//		}
//	}

	@Override
	public String toString(){
		return "AbteilungBean:"+	getBeanDBStatus()+
		" Name:" + getAbteilungName();
//		" fkBenutzerAbsender:" + getAbsenderBenutzerBean()+
//		" fkBenutzerAnnehmer:" + getAnnehmerBenutzerBean()+
//		" fkHerstellerLieferant:" + getLhBean();

	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
//		if(getLhBean()==null){
//			getFehlerList().add(new Fehler(35));
//		}else{
//			if(getLhBean().getId()==0){
//				getFehlerList().add(new Fehler(35));
//			}
//		}
		return getFehlerList();
	}

	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof AbteilungBean){
			AbteilungBean tempBean = (AbteilungBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId())&(tempBean.getId()!=0); 
			}
		}else return false;
    }
	



	public void setAbteilungName(String abteilungName) {
		this.abteilungName = abteilungName;
	}

	public String getAbteilungName() {
		if (abteilungName==null){
			abteilungName = "";
		}
		return abteilungName;
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


	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
}

