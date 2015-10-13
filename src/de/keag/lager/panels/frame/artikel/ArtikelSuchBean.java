package de.keag.lager.panels.frame.artikel;

import de.keag.lager.core.ISuchBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;


/*
 * Diese Klasse wird benutzt, wann immer wir nach dem Artikel in der Datenbank suchen müssen.
 */
public class ArtikelSuchBean implements ISuchBean {
	
	private ArtikelBean artikelBean;
	
	private String bezeichnung = ""; 
	private String typ = "";
	private Integer keg_nr = 0;
	private LhBean hersteller;
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public Integer getKeg_nr() {
		return keg_nr;
	}
	public void setKeg_nr(Integer kegNr) {
		keg_nr = kegNr;
	}
	public LhBean getHersteller() {
		if(hersteller==null){
			hersteller = new LhBean();
		}
		return hersteller;
	}
	public void setHersteller(LhBean hersteller) {
		this.hersteller = hersteller;
	}
	public ArtikelBean getArtikelBean() {
		if (artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		return artikelBean;
	}
	public void setArtikelBean(ArtikelBean artikelBean) {
		this.artikelBean = artikelBean;
	}
}
