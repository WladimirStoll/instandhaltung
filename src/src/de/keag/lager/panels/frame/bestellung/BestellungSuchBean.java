package de.keag.lager.panels.frame.bestellung;


import java.util.Date;

import de.keag.lager.core.ISuchBean;


public class BestellungSuchBean implements ISuchBean {
	private Date erstellungsDatumVon;
	private Date erstellungsDatumBis;
	private BestellungStatus bestellungStatus;
	private Integer anforderungsID;
	
	public BestellungSuchBean() {
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

	public BestellungStatus getAnforderungStatus() {
		if (bestellungStatus==null){
			bestellungStatus = BestellungStatus.FEHLERHAFT;
		}
		return bestellungStatus;
	}

	public void setAnforderungStatus(BestellungStatus bestellungStatus) {
		this.bestellungStatus = bestellungStatus;
	}

	@Override
	public String toString(){
		String result = "Bestellung:" + getAnforderungStatus().toString();
		result = result + ", Erstellungsdatum Von:" + getErstellungsDatumBis();
		result = result + ", Erstellungsdatum Bis:" + getErstellungsDatumVon();
		result = result + ", Status:" + getAnforderungStatus();
		return result + super.toString();
	}

	public void setAnforderungsID(Integer anforderungsID) {
		this.anforderungsID = anforderungsID;
	}

	public Integer getAnforderungsID() {
		if (anforderungsID==null){
			anforderungsID = 0;
		}
		return anforderungsID;
	}
}
