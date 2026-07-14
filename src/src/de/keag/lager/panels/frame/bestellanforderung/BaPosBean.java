package de.keag.lager.panels.frame.bestellanforderung;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

public class BaPosBean extends Bean{
	private Integer id = 0;
	private BaBean baBean ; //ZEiger auf die Bestellanforderung
	private ArtikelBean artikelBean; //Zeiger auf den Artikel(aus Stamm-Daten)
	private Integer bestellmenge = 0; //Bestellmenge
	private PosStatus status = PosStatus.OFFEN;
	private KostenstelleBean kostenstelle;
	private MengenEinheitBean mengenEinheitBean;//kann vom Artikel-Stamm abweichen
	private Integer liefertermin = 0; //KW
	private KatalogBean katalogBean;
	private String lieferantenbestellnummer=""; //wird aus lieferantenArtikelBestellnummer-Tabelle übernommen
	private String katalogseite = ""; //wird aus lieferantenArtikelBestellnummer-Tabelle übernommen
	private Double katalogpreis = 0.0;
	
	private ModelKnotenBean modelKnotenBean;
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	public BaPosBean() {
		super();
		//Kalenderwoche ermitteln
		GregorianCalendar gc = new GregorianCalendar();
		gc.setGregorianChange(new Date());
		liefertermin = gc.get(Calendar.WEEK_OF_YEAR);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if(this.id != id){
			this.id = id;
			beanIstGeaendert();
		}
	}
	public BaBean getBaBean() {
		return baBean;
	}
	public void setBaBean(BaBean baBean) {
		if(this.baBean==null || baBean == null || !this.baBean.equals(baBean)){
				this.baBean = baBean;
				beanIstGeaendert();
		}
	}
	public Integer getBestellmenge() {
		return bestellmenge;
	}
	public void setBestellmenge(Integer bestellmenge) {
		if (this.bestellmenge != bestellmenge){
			this.bestellmenge = bestellmenge;
			beanIstGeaendert();
		}
	}
	public Integer getLiefertermin() {
		return liefertermin;
	}
	public void setLiefertermin(Integer liefertermin) {
		if(this.liefertermin != liefertermin){
			this.liefertermin = liefertermin;
			beanIstGeaendert();
		}
	}
	public PosStatus getStatus() {
		return status;
	}
	public void setStatus(PosStatus status) {
		if(this.status.equals(status)){
			this.status = status;
			beanIstGeaendert();
		}
	}

	@Override
	public String toString(){
		return "Bestellposition " + getId(); 
	}

	public ArtikelBean getArtikelBean() {
		if(artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		return artikelBean;
	}

	public void setArtikelBean(ArtikelBean artikelBean) {
		if(artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		if (this.artikelBean == null || artikelBean == null	|| !this.artikelBean.equals(artikelBean)) {
			setKostenstelle(new KostenstelleBean());
			setMengenEinheitBean(new MengenEinheitBean());
			setKatalogBean(new KatalogBean());
			setLieferantenbestellnummer("");
			setKatalogseite("");
			setKatalogpreis(0.0);
			this.artikelBean = artikelBean;
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
		if(this.kostenstelle==null || kostenstelle == null || !this.kostenstelle.equals(kostenstelle)){
			this.kostenstelle = kostenstelle;
			beanIstGeaendert();
		}
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		return modelKnotenBean;
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		this.modelKnotenBean = modelKnotenBean;
	}


	public MengenEinheitBean getMengenEinheitBean() {
		if(mengenEinheitBean==null){
			mengenEinheitBean = new MengenEinheitBean();
		}
		return mengenEinheitBean;
	}

	public void setMengenEinheitBean(MengenEinheitBean mengenEinheitBean) {
		if(this.mengenEinheitBean==null || mengenEinheitBean == null || !this.mengenEinheitBean.equals(mengenEinheitBean)){
			this.mengenEinheitBean = mengenEinheitBean;
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
		if(this.katalogBean==null || katalogBean == null || !this.katalogBean.equals(katalogBean)){
			this.katalogBean = katalogBean;
			beanIstGeaendert();
	}
	}

	public String getLieferantenbestellnummer() {
		return lieferantenbestellnummer;
	}

	public void setLieferantenbestellnummer(String lieferantenbestellnummer) {
		if(lieferantenbestellnummer==null){
			lieferantenbestellnummer = "";
		}
		if(!this.lieferantenbestellnummer.equals(lieferantenbestellnummer)){
			this.lieferantenbestellnummer = lieferantenbestellnummer;
			beanIstGeaendert();
		}
	}

	public String getKatalogseite() {
		return katalogseite;
	}

	public void setKatalogseite(String katalogseite) {
		if (katalogseite==null){
			katalogseite = "";
		}
		if (!this.katalogseite.equals(katalogseite)){
			this.katalogseite = katalogseite;
		}
	}

	public Double getKatalogpreis() {
		return katalogpreis;
	}

	public void setKatalogpreis(Double katalogpreis) {
		if(katalogpreis==null){
			katalogpreis = 0.0;
		}
		if(this.katalogpreis != katalogpreis){
			this.katalogpreis = katalogpreis;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if (getLiefertermin() < 0 || getLiefertermin() > 53){
			getFehlerList().add(new Fehler(29));
		}
		if (getBestellmenge() < 1){
			getFehlerList().add(new Fehler(30));
		}
		if(getBaBean()==null){
			getFehlerList().add(new Fehler(31));
		}else{
			if (getBaBean().getId()==0){
				getFehlerList().add(new Fehler(32));
			}
		}
		if(getArtikelBean()==null){
			getFehlerList().add(new Fehler(33));
		}else{
			if(getArtikelBean().getId()==0){
				getFehlerList().add(new Fehler(33));
			}
		}
		return getFehlerList();
	}

	static public java.text.NumberFormat getLieferterminFormat(){
		return LagerFormate.getNumberFormat(2);
	}

	static public java.text.NumberFormat getBestellmengeFormat() {
		return LagerFormate.getNumberFormat(1000);
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BaPosBean){
			BaPosBean tempBean = (BaPosBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}

}
