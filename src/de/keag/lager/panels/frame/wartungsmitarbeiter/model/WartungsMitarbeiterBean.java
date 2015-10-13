package de.keag.lager.panels.frame.wartungsmitarbeiter.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;

public class WartungsMitarbeiterBean extends Bean {
	
	 
	private Integer id; 
	private WartungBean fk_wartung;  
	private BenutzerBean fk_benutzer;  
	private Double stunden; 
	private LhBean fk_herstellerlieferant;  
	private String bemerkung; 
	
	public WartungsMitarbeiterBean() {
		super();
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungsMitarbeiterBean){
			WartungsMitarbeiterBean wartungsMitarbeiterBean = (WartungsMitarbeiterBean) bean;
			if (wartungsMitarbeiterBean==null){
				return false;
			}else{
				return (this.getId() == wartungsMitarbeiterBean.getId());
			}
		}else return false;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();

		if (getId().equals(0)) {
			getFehlerList().add(new Fehler(136));
		}

		if (getFk_benutzer().equals(0)&&getFk_herstellerlieferant().equals(0)) {
			getFehlerList().add(new Fehler(137));
		}

		if (getStunden().equals(0)) {
			getFehlerList().add(new Fehler(138));
		}

		return getFehlerList();
	}

	public void setId(Integer id) {
		if (!this.getId().equals(id)){
			this.id = id;
			beanIstGeaendert();
		}
	}

	public Integer getId() {
		if (this.id==null){
				this.id = new Integer(0);
		}
		return id;
	}

	public WartungBean getFk_wartung() {
	if (this.fk_wartung==null){
			this.fk_wartung = new WartungBean();
	}
	return fk_wartung;}
	

	public void setFk_wartung(WartungBean fkWartung) {
		if (!this.getFk_wartung().equals(fkWartung)){
			fk_wartung = fkWartung;
			beanIstGeaendert();
		}
	}

	public BenutzerBean getFk_benutzer() {
	if (this.fk_benutzer==null){
			this.fk_benutzer = new BenutzerBean();
	}
	return fk_benutzer;}
	

	public void setFk_benutzer(BenutzerBean fkBenutzer) {
		if (!this.getFk_benutzer().equals(fkBenutzer)){
			fk_benutzer = fkBenutzer;
			beanIstGeaendert();
		}
	}

	public Double getStunden() {
	if (this.stunden==null){
			this.stunden = new Double(0D);
	}
	return stunden;}
	

	public void setStunden(Double stunden) {
		if (!this.getStunden().equals(stunden)){
			this.stunden = stunden;
			beanIstGeaendert();
		}
	}

	public LhBean getFk_herstellerlieferant() {
	if (this.fk_herstellerlieferant==null){
			this.fk_herstellerlieferant = new LhBean();
	}
	return fk_herstellerlieferant;}
	

	public void setFk_herstellerlieferant(LhBean fkHerstellerlieferant) {
		if (!this.getFk_herstellerlieferant().equals(fkHerstellerlieferant)){
			fk_herstellerlieferant = fkHerstellerlieferant;
			beanIstGeaendert();
		}
	}

	public String getBemerkung() {
	if (this.bemerkung==null){
			this.bemerkung = new String();
	}
	return bemerkung;}
	

	public void setBemerkung(String bemerkung) {
		if (!this.getBemerkung().equals(bemerkung)){
			this.bemerkung = bemerkung;
			beanIstGeaendert();
		}
		
	}
	
	@Override
	public String toString() {
		return this.getFk_benutzer().toString().trim() + " " +getFk_herstellerlieferant().toString().trim();
	}

}
