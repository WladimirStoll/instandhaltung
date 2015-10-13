package de.keag.lager.panels.frame.bestellung;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class BestellungBean extends Bean{
	private Integer id=0;
	private java.sql.Date erstellungsDatum;
	private BestellungStatus bestellungStatus=BestellungStatus.FEHLERHAFT;
	private LhBean lhBean=null;
	private BenutzerBean absenderBenutzerBean=null;
	private BenutzerBean annehmerBenutzerBean=null;
	private String email = "thurner@ke-ag.de";
	private BaBean baBean;
	
	/**
	 * Standard - Consturktor
	 */
	public BestellungBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}

	
	public BestellungBean(BaBean baBean) {
		this();
		
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
	public BestellungStatus getStatus() {
		return bestellungStatus;
	}
	public void setStatus(
			BestellungStatus status) {
		if(status!=bestellungStatus){
			this.bestellungStatus = status;
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
	public BenutzerBean getAbsenderBenutzerBean() {
		return absenderBenutzerBean;
	}
	public void setFk_benutzerAbsender(BenutzerBean fkBenutzerAbsender) {
		if(fkBenutzerAbsender!=absenderBenutzerBean){
			absenderBenutzerBean = fkBenutzerAbsender;
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
		return "BaBean:"+	getBeanDBStatus()+
		" Erstellungsdatum:" + getErstellungsDatum()+
		" fkBenutzerAbsender:" + getAbsenderBenutzerBean()+
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

	
	public boolean equals(BestellungBean baBean){
		if (baBean==null){
			return false;
		}else{
			//id' sind gleich und ungleich 0
			return (this.getId() == baBean.getId())&(baBean.getId()!=0); 
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
		if (bean instanceof BestellungBean){
			BestellungBean tempBean = (BestellungBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}

	public BaBean getBaBean() {
		if (baBean == null){
			baBean = new BaBean();
		}
		return baBean;
	}

	public void setBaBean(BaBean baBean) {
		if (!getBaBean().equals(baBean)){
			this.baBean = baBean;
			beanIstGeaendert();
		}
	}
	
//	public void addBaPosition(BaPosBean baPosBean) {
//		getBaPosBeanList().add(baPosBean);
//	}
//	public ArrayList<BaPosBean> getBaPosBeanList() {
//		if(baPosBeanList==null){
//			baPosBeanList = new ArrayList<BaPosBean>();
//		}
//		return baPosBeanList;
//	}
//	public void loescheAlleBaPositionen() {
//		getBaPosBeanList().clear();
//	}
}
