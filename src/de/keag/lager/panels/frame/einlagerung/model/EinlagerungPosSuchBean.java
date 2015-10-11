package de.keag.lager.panels.frame.einlagerung.model;


import java.util.Date;

import de.keag.lager.core.ISuchBean;


public class EinlagerungPosSuchBean implements ISuchBean {
	private Date erstellungsDatumVon;
	private Date erstellungsDatumBis;
	private EinlagerungStatus einlagerungStatus;
	
	public EinlagerungPosSuchBean() {
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

	public EinlagerungStatus getEinlagerungStatus() {
		if (einlagerungStatus==null){
			einlagerungStatus = EinlagerungStatus.FEHLERHAFT;
		}
		return einlagerungStatus;
	}

	public void setEinlagerungStatus(EinlagerungStatus einlagerungStatus) {
		this.einlagerungStatus = einlagerungStatus;
	}

	@Override
	public String toString(){
		String result = "Einlagerung:" + getEinlagerungStatus().toString();
		result = result + ", Erstellungsdatum Von:" + getErstellungsDatumBis();
		result = result + ", Erstellungsdatum Bis:" + getErstellungsDatumVon();
		result = result + ", Status:" + getEinlagerungStatus();
		return result + super.toString();
	}
}
