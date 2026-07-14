package de.keag.lager.panels.frame.lieferantenBestellnummer;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;

public class LieferantenBestellnummerBean extends Bean {

	private ArtikelBean artikel;  
	private KatalogBean katalogBean; 
	private String bestellnummer = ""; 
	private String katalogseite = ""; 
	private Double preis;
	
	@Override
	public ModelKnotenBean getModelKnotenBean() {
		return null;
	}
	@Override
	public int hashCodeForModelKnoten() {
		return super.hashCode();
	}
	

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
	}

	public ArtikelBean getArtikel() {
		if(artikel==null){
			artikel = new ArtikelBean();
		}
		return artikel;
	}

	public void setArtikel(ArtikelBean artikel) {
		this.artikel = artikel;
	}

	public KatalogBean getKatalogBean() {
		if(katalogBean==null){
			katalogBean = new KatalogBean();
		}
		return katalogBean;
	}

	public void setKatalogBean(KatalogBean katalogBean) {
		this.katalogBean = katalogBean;
	}

	public String getBestellnummer() {
		return bestellnummer;
	}

	public void setBestellnummer(String bestellnummer) {
		this.bestellnummer = bestellnummer;
	}

	public String getKatalogseite() {
		return katalogseite;
	}

	public void setKatalogseite(String katalogseite) {
		this.katalogseite = katalogseite;
	}

	public Double getPreis() {
		return preis;
	}

	public void setPreis(Double preis) {
		this.preis = preis;
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Bean bean) {
		return false;
	}

}
