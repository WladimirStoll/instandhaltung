package de.keag.lager.panels.frame.baugruppe.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;

public class BaugruppeArtikelBean extends Bean{
	
	private BaugruppeBean baugruppeBean = null;
	private ArtikelBean artikelBean = null;
	private Integer eingebauteMenge = 0;
	
	public BaugruppeArtikelBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getArtikel().getId();
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if(artikelBean.equals(0)){
			getFehlerList().add(new Fehler(42));
		}
		if(baugruppeBean.equals(0)){
			getFehlerList().add(new Fehler(43));
		}
		return getFehlerList();
	}

	public BaugruppeBean getBaugruppe() {
		if (baugruppeBean==null){
			baugruppeBean = new BaugruppeBean();
		}
		return baugruppeBean;
	}

	public void setBaugruppe(BaugruppeBean baugruppe) {
		
		if (this.baugruppeBean==null || !this.baugruppeBean.equals(baugruppe)){
			this.baugruppeBean = baugruppe;
			beanIstGeaendert();
		}
	}

	public ArtikelBean getArtikel() {
		if (artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		return artikelBean;
	}

	public void setArtikel(ArtikelBean abteilung) {
		if (this.artikelBean==null || !this.artikelBean.equals(abteilung)){
			artikelBean = abteilung;
			beanIstGeaendert();
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BaugruppeArtikelBean){
			BaugruppeArtikelBean tempBean = (BaugruppeArtikelBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return this.getArtikel().equals(tempBean.getArtikel())
					&& this.getBaugruppe().equals(tempBean.getBaugruppe());
			}
		}else return false;
	}

	public Integer getEingebauteMenge() {
		if (eingebauteMenge==null){
			eingebauteMenge = 0;
		}
		return eingebauteMenge;
	}

	public void setEingebauteMenge(Integer eingebauteMenge) {
		if (!this.eingebauteMenge.equals(eingebauteMenge)){
			this.eingebauteMenge = eingebauteMenge;
			beanIstGeaendert();
		}
	}

}
