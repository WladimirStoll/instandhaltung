package de.keag.lager.panels.frame.wartungsartikel.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

public class WartungsArtikelBean extends Bean {
	
	private Integer id; 
	private WartungBean fk_wartung; 
	private ArtikelBean fk_artikel; 
	private Integer menge;
	
	
	public WartungsArtikelBean() {
		super();
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungsArtikelBean){
			WartungsArtikelBean wartungsArtBean = (WartungsArtikelBean) bean;
			if (wartungsArtBean==null){
				return false;
			}else{
				return (this.getId() == wartungsArtBean.getId());
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
		
		if(getId().equals(0)
		){ 
			getFehlerList().add(new Fehler(132));
		}
		
		if(getFk_wartung().equals(0)
		){ 
			getFehlerList().add(new Fehler(133));
		}
		
		if(getFk_artikel().equals(0)
		){ 
			getFehlerList().add(new Fehler(134));
		}
		
		if(getMenge().equals(0)
		){ 
			getFehlerList().add(new Fehler(135));
		}
		
		
//		if(getBaugruppeId().equals(0)
//		){ 
//			getFehlerList().add(new Fehler(131));
//		}
		
		
		
		
//		if(getBeschreibung().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(44));
//		}
		
		
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
			id = new Integer(0);
		}
		return id;
	}

	public WartungBean getFk_wartung() {
	if (this.fk_wartung==null){
			this.fk_wartung = new WartungBean();
	}
	return fk_wartung;
	}
	

	public void setFk_wartung(WartungBean fkWartung) {
		if (!this.getFk_wartung().equals(fkWartung)){
			this.fk_wartung = fkWartung;
			beanIstGeaendert();
		}
	}

	public ArtikelBean getFk_artikel() {
	if (this.fk_artikel==null){
			this.fk_artikel = new ArtikelBean();
	}
	return fk_artikel;}
	

	public void setFk_artikel(ArtikelBean fkArtikel) {
		if (!this.getFk_artikel().equals(fkArtikel)){
			this.fk_artikel = fkArtikel;
			beanIstGeaendert();
		}
	}

	public Integer getMenge() {
	if (this.menge==null){
			this.menge = new Integer(0);
	}
	return menge;}
	

	public void setMenge(Integer menge) {
		if (!this.getMenge().equals(menge)){
			this.menge = menge;
			beanIstGeaendert();
		}
	}

}
