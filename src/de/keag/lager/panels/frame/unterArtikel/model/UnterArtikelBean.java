package de.keag.lager.panels.frame.unterArtikel.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;

import de.keag.lager.panels.frame.artikel.ArtikelBean;

public class UnterArtikelBean extends Bean{
	private ArtikelBean vaterArtikelBean;
	private ArtikelBean kindArtikelBean;
	private Integer anzahl;
	
	public UnterArtikelBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_UNTER_ARTIKEL + getVaterArtikelBean().getId()*10000000 + getKindArtikelBean().getId();
	}
	
	public Integer getAnzahl() {
		if (anzahl ==null){
			anzahl = 0;
		}
		return anzahl;
	}
	public void setAnzahl(Integer anzahl) {
		if (!this.getAnzahl().equals(anzahl)){
			this.anzahl = anzahl;
			beanIstGeaendert();
		}
	}
	public ArtikelBean getVaterArtikelBean() {
		if (vaterArtikelBean==null){
			vaterArtikelBean = new ArtikelBean();
		}
		return vaterArtikelBean;
	}

	public void setVaterArtikelBean(ArtikelBean vaterArtikelBean) {
		if(!getVaterArtikelBean().equals(vaterArtikelBean)){
			this.vaterArtikelBean = vaterArtikelBean;
			beanIstGeaendert();
		}
	}
	
	public ArtikelBean getKindArtikelBean() {
		if (kindArtikelBean==null){
			kindArtikelBean = new ArtikelBean();
		}
		return kindArtikelBean;
	}

	public void setKindArtikelBean(ArtikelBean kindArtikelBean) {
		if(!getKindArtikelBean().equals(kindArtikelBean)){
			this.kindArtikelBean = kindArtikelBean;
			beanIstGeaendert();
		}
	}	

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getKindArtikelBean().equals(0)){
			getFehlerList().add(new Fehler(124));
		}
		
		if(getVaterArtikelBean().equals(0)){ 
			getFehlerList().add(new Fehler(123));
		}
		
		if(getAnzahl().equals(0)){ 
			getFehlerList().add(new Fehler(125));
		}
		
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof UnterArtikelBean){
			UnterArtikelBean tempBean = (UnterArtikelBean) bean;
			if (tempBean==null){
				return false;
			}else{
				if (this.getVaterArtikelBean().equals(tempBean.getVaterArtikelBean())){
					if (this.getKindArtikelBean().equals(tempBean.getKindArtikelBean())){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
		}else return false;
    }

	@Override
	public String toString() {
		return "Artikel" + getKindArtikelBean().toString() + "("+getVaterArtikelBean().toString()+")";
	}

}
