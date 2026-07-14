package de.keag.lager.panels.frame.artikel;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;

import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

public class ArtikelBean extends Bean{
	private Integer id;
	private String bezeichnung; 
	private String bemerkung; 
	private String typ;
	private Integer keg_nr;
	private Integer mindestbestand; 
	private KostenstelleBean kostenstelle = null;
	private MengenEinheitBean mengenEinheitBean = null;
	private LhBean hersteller; 
	private Integer empfohlene_bestellmenge;
//	private ArrayList<BestandspackstueckBean> bestandspackstueckBeans = null;
	
	public ArtikelBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_ARTIKEL + getId();
	}
	
	public Integer getId() {
		if (id ==null){
			id = 0;
		}
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
		
	}
	public String getBezeichnung() {
		if (bezeichnung==null){
			bezeichnung = "";
		}
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		if (!getBezeichnung().equals(bezeichnung)){
			this.bezeichnung = bezeichnung;
			beanIstGeaendert();
		}
	}
	public String getTyp() {
		if(typ==null){
			typ = "";
		}
		return typ;
	}
	public void setTyp(String typ) {
		if(!getTyp().equals(typ)){
			this.typ = typ;
			beanIstGeaendert();
		}
	}
	public Integer getKeg_nr() {
		if (keg_nr==null){
			keg_nr = 0;
		}
		return keg_nr;
	}
	public void setKeg_nr(Integer kegNr) {
		if(!getKeg_nr().equals(kegNr)){
			this.keg_nr = kegNr;
			beanIstGeaendert();
		}
	}
	public Integer getMindestbestand() {
		if(mindestbestand==null){
			mindestbestand = 0;
		}
		return mindestbestand;
	}
	public void setMindestbestand(Integer mindestbestand) {
		if(!getMindestbestand().equals(mindestbestand)){
			this.mindestbestand = mindestbestand;
			beanIstGeaendert();
		}
	}
	public KostenstelleBean getKostenstelle() {
		if (kostenstelle==null){
			kostenstelle = new KostenstelleBean();
		}
		return kostenstelle;
	}
	public void setKostenstelle(KostenstelleBean kostenstelle) {
		if (!getKostenstelle().equals(kostenstelle)){
			this.kostenstelle = kostenstelle;
			beanIstGeaendert();
		}
	}
	public MengenEinheitBean getMengenEinheitBean() {
		if (mengenEinheitBean==null){
			mengenEinheitBean = new MengenEinheitBean();
		}
		return mengenEinheitBean;
	}
	public void setMengenEinheitBean(MengenEinheitBean mengenEinheitBean) {
		if(!getMengenEinheitBean().equals(mengenEinheitBean)){
			this.mengenEinheitBean = mengenEinheitBean;
			beanIstGeaendert();
		}
	}
	
//	@Override
//	public ModelKnotenBean getModelKnotenBean() {
//		Log.log().severe("nicht implementiert");
//		return null;
//	}
	
//	@Override
//	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}

	public LhBean getHersteller() {
		if (hersteller==null){
			hersteller = new LhBean();
		}
		return hersteller;
	}

	public void setHersteller(LhBean hersteller) {
		if(!getHersteller().equals(hersteller)){
			this.hersteller = hersteller;
			beanIstGeaendert();
		}
	}

	public Integer getEmpfohlene_bestellmenge() {
		if (empfohlene_bestellmenge==null){
			empfohlene_bestellmenge = 0;
		}
		return empfohlene_bestellmenge;
	}

	public void setEmpfohlene_bestellmenge(Integer empfohleneBestellmenge) {
		if (!getEmpfohlene_bestellmenge().equals(empfohleneBestellmenge)){
			empfohlene_bestellmenge = empfohleneBestellmenge;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		
		if(getBezeichnung().trim().equals("")){ 
			getFehlerList().add(new Fehler(103));
		}
		
		if(getTyp().trim().equals("")){ 
			getFehlerList().add(new Fehler(104));
		}
		
		if(getKeg_nr().equals(0)){ 
			getFehlerList().add(new Fehler(105));
		}
		
//		if(getMindestbestand().equals(0)){ 
//			getFehlerList().add(new Fehler());
//		}
//		
//		if(getKostenstelle().trim().equals("")){ 
//			getFehlerList().add(new Fehler());
//		}
		
		if(getMengenEinheitBean().getId().equals(0)){ 
			getFehlerList().add(new Fehler(106));
		}
		
		if(getHersteller().getId().equals(0)){ 
			getFehlerList().add(new Fehler(107));
		}
		
		if(getEmpfohlene_bestellmenge().equals(0)){ 
			getFehlerList().add(new Fehler(108));
		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof ArtikelBean){
			ArtikelBean tempBean = (ArtikelBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId().equals(tempBean.getId()));
			}
		}else return false;
    }

	@Override
	public String toString() {
		return "Bezeichung " + getBezeichnung() + " / Typ " + getTyp();
	}

//	public ArrayList<BestandspackstueckBean> getBestandspackstueckBeans() {
//		if (bestandspackstueckBeans==null){
//			bestandspackstueckBeans = new ArrayList<BestandspackstueckBean>();
//		}
//		return bestandspackstueckBeans;
//	}
//
//	public void setBestandspackstueckBeans(
//			ArrayList<BestandspackstueckBean> bestandspackstueckBeans) {
//		if (this.bestandspackstueckBeans != bestandspackstueckBeans){
//			this.bestandspackstueckBeans = bestandspackstueckBeans;
//			beanIstGeaendert();
//		}
//	}

	public String getBemerkung() {
		if (bemerkung==null){
			bemerkung= "";
		}
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		if(!getBemerkung().equals(bemerkung)){
			this.bemerkung = bemerkung;
			beanIstGeaendert();
		}
	}

}
