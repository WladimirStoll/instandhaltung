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
		if(!this.getArtikel().equals(artikel)){
			this.artikel = artikel;
			beanIstGeaendert();
		}

	}

	public KatalogBean getKatalogBean() {
		if(katalogBean==null){
			katalogBean = new KatalogBean();
		}
		return katalogBean;
	}

	public void setKatalogBean(KatalogBean katalogBean) {
		if(!this.getKatalogBean().equals(katalogBean)){
			this.katalogBean = katalogBean;
			beanIstGeaendert();
		}
	}

	public String getBestellnummer() {
		if (bestellnummer==null){
			bestellnummer = "";
		}
		return bestellnummer;
	}

	public void setBestellnummer(String bestellnummer) {
		if(!this.getBestellnummer().equals(bestellnummer)){
			this.bestellnummer = bestellnummer;
			beanIstGeaendert();
		}
	}

	public String getKatalogseite() {
		if(katalogseite==null){
			katalogseite = "";
		}
		return katalogseite;
	}

	public void setKatalogseite(String katalogseite) {
		if(!this.getKatalogseite().equals(katalogseite)){
			this.katalogseite = katalogseite;
			beanIstGeaendert();
		}
	}

	public Double getPreis() {
		if(preis==null){
			preis = 0D;
		}
		return preis;
	}

	public void setPreis(Double preis) {
		if(!this.getPreis().equals(preis)){
			this.preis = preis;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getArtikel().getId().equals(0)){
			getFehlerList().add(new Fehler(120));
		}
		
		if(getKatalogBean().getId().equals(0)){
			getFehlerList().add(new Fehler(121));
		}
		
		if(getBestellnummer().trim().length()==0){
			getFehlerList().add(new Fehler(119));
		}
		
		return getFehlerList();	
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean==null){
		 	return super.equals((Object)bean);
		}
		if (bean instanceof LieferantenBestellnummerBean){
			if (!((LieferantenBestellnummerBean) bean).getArtikel().equals(this.getArtikel())){
				return false;
			}
			if (!((LieferantenBestellnummerBean) bean).getKatalogBean().equals(this.getKatalogBean())){
				return false;
			}
			return true;
		}
		return false;
	}

}
