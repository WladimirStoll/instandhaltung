package de.keag.lager.core;

import java.util.ArrayList;

import javax.swing.Icon;


import de.keag.lager.core.enums.KnotenZustand;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.bestellung.BestellungPosBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

abstract public class ModelKnotenBean {
	private IBean iBean; //Daten
	
	private ModelKnotenBean vater; //Übergeordenter Knoten
	private ArrayList<ModelKnotenBean> kinderList; //alle untergeordente Knoten

	private ModelKnotenTyp modelKnotenTyp;
	private ArrayList<Fehler> fehlerList;
	private KnotenZustand knotenZustand = KnotenZustand.AUF;
	
	protected ModelKnotenBean(ModelKnotenTyp sammelKnotenTypENUM, ModelKnotenBean vater) {
		super();
		setSammelKnotenTypENUM(sammelKnotenTypENUM);
		initialize();
		setVater(vater);
	}
	
	public static Icon getEasyIcon() {
		try {
			throw new LagerException("Icon ist nicht gesetzt");
		} catch (LagerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IBean getIBean() {
		return iBean;
	}
	
	public int hashCodeForTreeView(){
		if(getIBean()!=null){
			return getIBean().hashCodeForModelKnoten();
		}
		return super.hashCode();
	}
	
	public void setIBean(IBean iBean) throws  BenutzerOberflacheLagerException{
		if (iBean!=null){
			if (getModelKnotenTyp()==ModelKnotenTyp.ANFORDERUNG){
				if(!(iBean instanceof BaBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			}else if (getModelKnotenTyp()==ModelKnotenTyp.ANFORDERUNGSPOSITION){
				if(!(iBean instanceof BaPosBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.BENUTZER){
				if(!(iBean instanceof BenutzerBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.BENUTZER_ABTEILUNG){
				if(!(iBean instanceof BenutzerAbteilungBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT){
				if(!(iBean instanceof BenutzerZugriffsrechtBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.BAUGRUPPE){
				if(!(iBean instanceof BaugruppeBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.BAUGRUPPE_ARTIKEL){
				if(!(iBean instanceof BaugruppeArtikelBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.LIEFERANT_HERSTELLER){
				if(!(iBean instanceof LhBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.BERICHT){
				if(!(iBean instanceof BerichtBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG){
				if(!(iBean instanceof LhKatalogBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.HALLE){
				if(!(iBean instanceof HalleBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.ETAGE){
				if(!(iBean instanceof EtageBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.ZEILE){
				if(!(iBean instanceof ZeileBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.SAEULE){
				if(!(iBean instanceof SaeuleBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.EBENE){
				if(!(iBean instanceof EbeneBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.POSITION){
				if(!(iBean instanceof PositionBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.BESTANDSPACKSTUECK){
				if(!(iBean instanceof BestandspackstueckBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
//			} else  if (getModelKnotenTyp()==ModelKnotenTyp.ENTNAHME){
//				if(!(iBean instanceof EntnahmeBean)){
//					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
//				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.ENTNAHMENSPOSITION){
				if(!(iBean instanceof EntnahmePosBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.EINLAGERUNGSPOSITION){
				if(!(iBean instanceof EinlagerungPosBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.KATALOG){
				if(!(iBean instanceof KatalogBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.ARTIKEL){
				if(!(iBean instanceof ArtikelBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.LIEFERANT_BESTELLNUMMER){
				if(!(iBean instanceof LieferantenBestellnummerBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.UNTER_ARTIKEL){
				if(!(iBean instanceof UnterArtikelBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.WARTUNG){
				if(!(iBean instanceof WartungBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.WARTUNG_ARTIKEL){
				if(!(iBean instanceof WartungsArtikelBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			} else  if (getModelKnotenTyp()==ModelKnotenTyp.WARTUNG_MITARBEITER){
				if(!(iBean instanceof WartungsMitarbeiterBean)){
					throw new BenutzerOberflacheLagerException(new Fehler(18,FehlerTyp.FEHLER,iBean.toString()));
				}
			}else{
				throw new BenutzerOberflacheLagerException(new Fehler(17,FehlerTyp.FEHLER,"Bitte um die neue Klasse erweitern!"));
			}
		}else{
			throw new BenutzerOberflacheLagerException(new Fehler(17));
		}
		this.iBean = iBean;
		this.iBean.setModelKnotenBean(this);
	}
	
	public ModelKnotenBean getVater() {
		return vater;
	}
	
	public void setVater(ModelKnotenBean vater) {
		this.vater = vater;
	}
	
	public ModelKnotenTyp getSammelKnotenTypENUM() {
		return modelKnotenTyp;
	}
	
	private void setSammelKnotenTypENUM(ModelKnotenTyp sammelKnotenTypENUM) {
		this.modelKnotenTyp = sammelKnotenTypENUM;
	}
	
	public ArrayList<ModelKnotenBean> getKinderList() {
		if (kinderList==null){
			kinderList = new ArrayList<ModelKnotenBean>();
		}
		return kinderList;
	}
	
	public void setKinderList(ArrayList<ModelKnotenBean> kinderList) {
		this.kinderList = kinderList;
	}
	public ArrayList<Fehler> getFehlerList() {
		if(fehlerList==null){
			fehlerList = new ArrayList<Fehler>(); 
		}
		return fehlerList;
	}
	public void setFehlerList(ArrayList<Fehler> fehlerList) {
		this.fehlerList = fehlerList;
	}
	public KnotenZustand getKnotenZustand() {
		return knotenZustand;
	}
	public void setKnotenZustand(KnotenZustand knotenZustand) {
		this.knotenZustand = knotenZustand;
	}
	@Override
	public String toString(){
		return "KnotenBean: "+getIBean().toString();
	}

	private ModelKnotenTyp getModelKnotenTyp() {
		return modelKnotenTyp;
	}
	
	public void initialize(){
		getFehlerList().clear();
		setKnotenZustand(KnotenZustand.AUF);
		getKinderList().clear();
	}
	
	public int getDeineLaufendeNummerInVaterliste(){
		if (getVater()!=null){
			for(int i=0; i <getVater().kinderList.size();i++){
				ModelKnotenBean kind = getVater().getKinderList().get(i);
				if (this.equals(kind)){
					return i;
				}
			}
		}else{
//			Log.log().severe("Vater ist unbekannt. Das kann nicht sein.");
		}
		return -1;
	}
	
	public boolean equals(ModelKnotenBean modelKnotenBean){
		if (modelKnotenBean.getIBean()!=null){
			if (this.getIBean()!=null){
				return this.getIBean().equals((Bean)modelKnotenBean.getIBean());
			}
		}
		return this == modelKnotenBean;
	}
	
//	abstract public boolean istGesamterInhaltGeaendert();
	/**
	 * Ist irgend etwas in den Inhalten geändert? 
	 * @return
	 */
//	abstract public boolean istGesamterInhaltGeaendert(){
//		if (((BaBean)getIBean()).getBeanDBStatus()!=BeanDBStatus.SELECT){
//			return true;
//		}
//		for(int i=0; i <getKinderList().size();i++){
//			ModelKnotenBean kind = getKinderList().get(i);
//			if (((Bean)kind.getIBean()).getBeanDBStatus()!=BeanDBStatus.SELECT){
//				return true;
//			}
//		}
//		return false;
//	}

	public boolean istGesamterInhaltGeaendert() {
		if (((Bean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}
		for (int i = 0; i < getKinderList().size(); i++) {
			ModelKnotenBean kind = getKinderList().get(i);
			if (kind.istGesamterInhaltGeaendert()){
				return true;
			}
		}
		return false;
	}
	
}	
