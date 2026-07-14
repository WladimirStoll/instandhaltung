package de.keag.lager.panels.frame.entnahme.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class EntnahmeBean extends Bean{
	private Integer id=0;
	private java.sql.Date erstellungsDatum;
	private EntnahmeStatus entnahmeStatus=EntnahmeStatus.FEHLERHAFT;
	private LhBean lhBean=null;
	private BenutzerBean entnehmerBenutzerBean=null;
	private BenutzerBean annehmerBenutzerBean=null;
	private String email = "thurner@ke-ag.de";
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	
	public EntnahmeBean() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
	}
	public java.sql.Date getErstellungsDatum() {
		if (erstellungsDatum==null){
			erstellungsDatum=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
//			erstellungsDatum.toGMTString();
		}
		return erstellungsDatum;
	}
	public void setErstellungsDatum(java.sql.Date erstellungsDatum) {
		if(this.erstellungsDatum==null){
			this.erstellungsDatum = erstellungsDatum;
		}else{
			if(this.erstellungsDatum.getTime()!=erstellungsDatum.getTime()){
				beanIstGeaendert();
				this.erstellungsDatum = erstellungsDatum;
			}
		}
	}
	public EntnahmeStatus getStatus() {
		return entnahmeStatus;
	}
	public void setStatus(
			EntnahmeStatus status) {
		if(status!=entnahmeStatus){
			this.entnahmeStatus = status;
			beanIstGeaendert();
		}
	}
	public LhBean getLhBean() {
		return lhBean;
	}
	public void setLhBean(LhBean lhBean) {
		if (lhBean!=this.lhBean){
			this.lhBean = lhBean;
			beanIstGeaendert();
		}
	}
//	public BenutzerBean getEntnehmerBenutzerBean() {
//		return entnehmerBenutzerBean;
//	}
	public BenutzerBean getEntnehmerBenutzerBean() {
		return entnehmerBenutzerBean;
	}
	public void setFk_benutzerEntnehmer(BenutzerBean fkBenutzerEntnehmer) {
		if(fkBenutzerEntnehmer!=entnehmerBenutzerBean){
			entnehmerBenutzerBean = fkBenutzerEntnehmer;
			beanIstGeaendert();
		}
	}
	public BenutzerBean getAnnehmerBenutzerBean() {
		return annehmerBenutzerBean;
	}
	public void setFk_benutzerAnnehmer(BenutzerBean fkBenutzerAnnehmer) {
		if(annehmerBenutzerBean != fkBenutzerAnnehmer){
			annehmerBenutzerBean = fkBenutzerAnnehmer;
			beanIstGeaendert();
		}
	}

	@Override
	public String toString(){
		return "EntnahmeBean:"+	getBeanDBStatus()+
		" Erstellungsdatum:" + getErstellungsDatum()+
		" fkBenutzerEntnehmer:" + getEntnehmerBenutzerBean()+
		" fkBenutzerAnnehmer:" + getAnnehmerBenutzerBean()+
		" fkHerstellerLieferant:" + getLhBean();
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

	
	public boolean equals(EntnahmeBean entnahmeBean){
		if (entnahmeBean==null){
			return false;
		}else{
			//id' sind gleich und ungleich 0
			return (this.getId() == entnahmeBean.getId())&(entnahmeBean.getId()!=0); 
		}
	}

	public String getEmail() {
		if(email==null){
			email = "";
		}
		return email;
	}

	public void setEmail(String email) {
		if(email==null){
			email = "";
		}
		if(!this.email.equals(email)){
			this.email = email;
			beanIstGeaendert();
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof EntnahmeBean){
			EntnahmeBean tempBean = (EntnahmeBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}




	
//	public void addEntnahmePosition(EntnahmePosBean baPosBean) {
//		getEntnahmePosBeanList().add(baPosBean);
//	}
//	public ArrayList<EntnahmePosBean> getEntnahmePosBeanList() {
//		if(baPosBeanList==null){
//			baPosBeanList = new ArrayList<EntnahmePosBean>();
//		}
//		return baPosBeanList;
//	}
//	public void loescheAlleEntnahmePositionen() {
//		getEntnahmePosBeanList().clear();
//	}
}
