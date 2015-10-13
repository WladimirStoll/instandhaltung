package de.keag.lager.panels.frame.entnahme.model;


import java.util.Date;

import de.keag.lager.core.ISuchBean;


public class EntnahmePosSuchBean implements ISuchBean {
	private Date erstellungsDatumVon;
	private Date erstellungsDatumBis;
	private EntnahmeStatus entnahmeStatus;
	
	public EntnahmePosSuchBean() {
		super();
	}
	
	public void setErstellungsDatumVon(Date erstellungsDatumVon) {
		this.erstellungsDatumVon = erstellungsDatumVon;
	}

	public Date getErstellungsDatumBis() {
		if (erstellungsDatumBis==null){
			erstellungsDatumBis = new Date((365*24*60*60*1000)*100); //Anzahl der Millisekunden MAL Anzahl Jahre
		}
		return erstellungsDatumBis;
	}
	
	public Date getErstellungsDatumVon() {
		if (erstellungsDatumVon==null){
			erstellungsDatumVon = new Date(0); //Anzahl der Millisekunden MAL Anzahl Jahre
		}
		return erstellungsDatumVon;
	}

	public void setErstellungsDatumBis(Date erstellungsDatumBis) {
		this.erstellungsDatumBis = erstellungsDatumBis;
	}

	public EntnahmeStatus getEntnahmeStatus() {
		if (entnahmeStatus==null){
			entnahmeStatus = EntnahmeStatus.FEHLERHAFT;
		}
		return entnahmeStatus;
	}

	public void setEntnahmeStatus(EntnahmeStatus entnahmeStatus) {
		this.entnahmeStatus = entnahmeStatus;
	}

	@Override
	public String toString(){
		String result = "Entnahme:" + getEntnahmeStatus().toString();
		result = result + ", Erstellungsdatum Von:" + getErstellungsDatumBis();
		result = result + ", Erstellungsdatum Bis:" + getErstellungsDatumVon();
		result = result + ", Status:" + getEntnahmeStatus();
		return result + super.toString();
	}
}
