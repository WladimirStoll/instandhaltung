package de.keag.lager.panels.frame.bestellanforderung;


import java.util.Date;

import de.keag.lager.core.ISuchBean;


public class BaSuchBean implements ISuchBean {
	private Date erstellungsDatumVon;
	private Date erstellungsDatumBis;
	private AnforderungStatus anforderungStatus;
	
	public BaSuchBean() {
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

	public AnforderungStatus getAnforderungStatus() {
		if (anforderungStatus==null){
			anforderungStatus = AnforderungStatus.FEHLERHAFT;
		}
		return anforderungStatus;
	}

	public void setAnforderungStatus(AnforderungStatus anforderungStatus) {
		this.anforderungStatus = anforderungStatus;
	}

	@Override
	public String toString(){
		String result = "Anforderung:" + getAnforderungStatus().toString();
		result = result + ", Erstellungsdatum Von:" + getErstellungsDatumBis();
		result = result + ", Erstellungsdatum Bis:" + getErstellungsDatumVon();
		result = result + ", Status:" + getAnforderungStatus();
		return result + super.toString();
	}
}
