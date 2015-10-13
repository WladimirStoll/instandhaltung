package de.keag.lager.panels.frame.bestandspackstueck.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class BestandspackstueckBean extends Bean{
	private Integer id = 0;
	private PositionBean positionBean;	
//	private MengenEinheitBean mengenEinheitBean; 
	private ArtikelBean artikelBean; 
	private Integer menge;
	private AbteilungBean abteilungBean;
	
	public BestandspackstueckBean() {
		super();
	}
	
	public Integer getId() {
		if (id==null){
			id = 0;
		}
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
		
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_BESTANDSPACKSTUECK + getId();
	}
	
	
	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		if(getPositionBean().getId().equals(0)){
			getFehlerList().add(new Fehler(72));
		}
//		if(getMengenEinheitBean().getId().equals(0)){
//			getFehlerList().add(new Fehler(73));
//		}
		if(getArtikelBean().getId().equals(0)){
			getFehlerList().add(new Fehler(56));
		}
		if(getAbteilung().getId().equals(0)){
			getFehlerList().add(new Fehler(42));
		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BestandspackstueckBean){
			BestandspackstueckBean tempBean = (BestandspackstueckBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
    }


	public PositionBean getPositionBean() {
		if (positionBean==null){
			positionBean = new PositionBean();
		}
		return positionBean;
	}

	public void setPositionBean(PositionBean position) {
		if(!getPositionBean().equals(position)){
			this.positionBean = position;
			beanIstGeaendert();
		}
	}

//	public MengenEinheitBean getMengenEinheitBean() {
//		if (mengenEinheitBean==null){
//			mengenEinheitBean = new MengenEinheitBean();
//		}
//		return mengenEinheitBean;
//	}
//
//	public void setMengenEinheitBean(MengenEinheitBean mengenEinheitBean) {
//		if(!getMengenEinheitBean().equals(mengenEinheitBean)){
//			this.mengenEinheitBean = mengenEinheitBean;
//			beanIstGeaendert();
//		}
//	}

	public ArtikelBean getArtikelBean() {
		if(artikelBean == null){
			artikelBean = new ArtikelBean();
		}
		return artikelBean;
	}

	public void setArtikelBean(ArtikelBean artikelBean) {
		if(!getArtikelBean().equals(artikelBean)){
			this.artikelBean = artikelBean;
			beanIstGeaendert();
		}
	}

	public Integer getMenge() {
		if (menge==null){
			menge = 0;
		}
		return menge;
	}

	public void setMenge(Integer menge) {
		if(!getMenge().equals(menge)){
			this.menge = menge;
			beanIstGeaendert();
		}
	}

	@Override
	public String toString() {
		return getArtikelBean().toString();
	}

	public AbteilungBean getAbteilung() {
		if (abteilungBean==null){
			abteilungBean = new AbteilungBean();
		}
		return abteilungBean;
	}

	public void setAbteilung(AbteilungBean abteilung) {
		if (this.abteilungBean==null || !this.abteilungBean.equals(abteilung)){
			abteilungBean = abteilung;
			beanIstGeaendert();
		}
	}

	public String getBarCodeInhalt() {
		StringBuilder sb = new StringBuilder();
		//Halle
		sb.append(this.getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getHalleBean().getNummer());
		sb.append("-");
		//Zeile
		sb.append(this.getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getNummer());
		sb.append("-");
		//Säule
		sb.append(this.getPositionBean().getEbeneBean().getSaeuleBean().getNummer());
		sb.append("-");
		//Ebene
		sb.append(this.getPositionBean().getEbeneBean().getNummer());
		sb.append("-");
		//Position
		sb.append(this.getPositionBean().getNummer());
		sb.append("-");
		//KE-Nr
		sb.append(this.getArtikelBean().getKeg_nr());
		return sb.toString();
	}


	
}
