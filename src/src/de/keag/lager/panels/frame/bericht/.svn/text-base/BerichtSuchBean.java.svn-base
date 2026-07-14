package de.keag.lager.panels.frame.bericht;

import de.keag.lager.core.ISuchBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;

public class BerichtSuchBean implements ISuchBean {
	
	public BerichtSuchBean() {
		super();
	}
	
	private Integer id;
	private Berichttyp berichtTyp;
	private Integer berichtTypId; //z.B. Anforderungs-ID, oder Bestellungs-ID, oder Baugruppen-ID
	private BenutzerBean aktuellerBenutzer; 
	private Berichtart berichtArt;
	private java.util.Date druckDatumOriginalVon; 
	private java.util.Date druckDatumOriginalBis; 
	private java.util.Date druckDatumKopieVon;
	private java.util.Date druckDatumKopieBis;
	
	public Integer getId() {
		if (id == null){
			id = 0;
		}
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Override
	public String toString(){
		return "BerichtSuchBean:"+	getId();
	}

	public Berichttyp getBerichtTyp() {
		if (berichtTyp==null){
			berichtTyp = Berichttyp.FEHLERHAFT;
		}
		return berichtTyp;
	}

	public void setBerichtTyp(Berichttyp berichtTyp) {
		if (!getBerichtTyp().equals(berichtTyp)){
			this.berichtTyp = berichtTyp;
		}
	}

	public BenutzerBean getAktuellerBenutzer() {
		if (aktuellerBenutzer==null){
			aktuellerBenutzer = new BenutzerBean();
		}
		return aktuellerBenutzer;
	}

	public void setAktuellerBenutzer(BenutzerBean aktuellerBenutzer) {
		if (!getAktuellerBenutzer().equals(aktuellerBenutzer)){
			this.aktuellerBenutzer = aktuellerBenutzer;
		}
	}

	public Berichtart getBerichtArt() {
		if(berichtArt == null){
			berichtArt = Berichtart.FEHLERHAFT; 
		}
		return berichtArt;
	}

	public void setBerichtArt(Berichtart berichtArt) {
		if(!getBerichtArt().equals(berichtArt)){
			this.berichtArt = berichtArt;
		}
	}

	public java.util.Date getDruckDatumOriginalVon() {
		if (druckDatumOriginalVon==null){
			druckDatumOriginalVon=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
		}
		return druckDatumOriginalVon;
	}

	public void setDruckDatumOriginalVon(java.sql.Date druckDatumOriginal) {
		if(this.druckDatumOriginalVon==null){
			this.druckDatumOriginalVon = druckDatumOriginal;
		}else{
			if(druckDatumOriginal==null||this.druckDatumOriginalVon.getTime()!=druckDatumOriginal.getTime()){
				this.druckDatumOriginalVon = druckDatumOriginal;
			}
		}
	}
	
	public void setDruckDatumOriginalVon(java.util.Date druckDatumOriginal) {
		setDruckDatumOriginalVon(new java.sql.Date(druckDatumOriginal.getTime()));
	}
	
	public java.util.Date getDruckDatumOriginalBis() {
		if (druckDatumOriginalBis==null){
			druckDatumOriginalBis=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
		}
		return druckDatumOriginalBis;
	}

	public void setDruckDatumOriginalBis(java.sql.Date druckDatumOriginal) {
		if(this.druckDatumOriginalBis==null){
			this.druckDatumOriginalBis = druckDatumOriginal;
		}else{
			if(druckDatumOriginal==null||this.druckDatumOriginalBis.getTime()!=druckDatumOriginal.getTime()){
				this.druckDatumOriginalBis = druckDatumOriginal;
			}
		}
	}
	
	public void setDruckDatumOriginalBis(java.util.Date druckDatumOriginal) {
		setDruckDatumOriginalBis(new java.sql.Date(druckDatumOriginal.getTime()));
	}
	
	
	
	

	public java.util.Date getDruckDatumKopieVon() {
		if (druckDatumKopieVon==null){
			druckDatumKopieVon=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
		}
		return druckDatumKopieVon;
	}

	public void setDruckDatumKopieVon(java.sql.Date druckDatumKopie) {
		if(this.druckDatumKopieVon==null){
			this.druckDatumKopieVon = druckDatumKopie;
		}else{
			if(this.druckDatumKopieVon.getTime()!=druckDatumKopie.getTime()){
				this.druckDatumKopieVon = druckDatumKopie;
			}
		}
	}

	public void setDruckDatumKopieVon(java.util.Date druckDatumKopie) {
		setDruckDatumOriginalVon((java.sql.Date)druckDatumKopie);
	}
	
	public java.util.Date getDruckDatumKopieBis() {
		if (druckDatumKopieBis==null){
			druckDatumKopieBis=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
		}
		return druckDatumKopieBis;
	}

	public void setDruckDatumKopieBis(java.sql.Date druckDatumKopie) {
		if(this.druckDatumKopieBis==null){
			this.druckDatumKopieBis = druckDatumKopie;
		}else{
			if(this.druckDatumKopieBis.getTime()!=druckDatumKopie.getTime()){
				this.druckDatumKopieBis = druckDatumKopie;
			}
		}
	}

	public void setDruckDatumKopieBis(java.util.Date druckDatumKopie) {
		setDruckDatumOriginalBis((java.sql.Date)druckDatumKopie);
	}

	public Integer getBerichtTypId() {
		if(berichtTypId==null){
			berichtTypId = 0;
		}
		return berichtTypId;
	}

	public void setBerichtTypId(Integer berichtTypId) {
		if (!this.getBerichtTypId().equals(berichtTypId)){
			this.berichtTypId = berichtTypId;
		}
	}

	
}
