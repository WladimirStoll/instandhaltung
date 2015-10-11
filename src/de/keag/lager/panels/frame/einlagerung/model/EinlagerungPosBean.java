package de.keag.lager.panels.frame.einlagerung.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class EinlagerungPosBean extends Bean {
	private Integer id = 0;
//	private EinlagerungBean einlagerungBean; // ZEiger auf die Bestellanforderung
	private ArtikelBean artikelBean; // Zeiger auf den Artikel(aus Stamm-Daten)
//	private HalleBean halleBean; // Zeiger auf den Artikel(aus Stamm-Daten)
//	private ZeileBean zeileBean; // Zeiger auf den Artikel(aus Stamm-Daten)
//	private EtageBean etageBean; // Zeiger auf den Artikel(aus Stamm-Daten)
//	private SaeuleBean saeuleBean; // Zeiger auf den Artikel(aus Stamm-Daten)
//	private EbeneBean ebeneBean; // Zeiger auf den Artikel(aus Stamm-Daten)
	private PositionBean positionBean; // Zeiger auf den Artikel(aus
										// Stamm-Daten)
	private Integer menge = 0; // Menge
//	private Integer position = 0; // KW
//	private Integer ebene = 0; // KW
//	private Integer saeule = 0; // KW
//	private Integer zeile = 0; // KW
//	private String halle = ""; // KW
//	private String etage = ""; // KW
	private EinlagerungStatus status = EinlagerungStatus.OFFEN;
	private KostenstelleBean kostenstelle;
	private MengenEinheitBean mengenEinheitBean;// kann vom Artikel-Stamm
												// abweichen
	private BenutzerBean benutzerEntnehmerBean;
	private BenutzerBean benutzerKontrollerBean;
	// private Integer liefertermin = 0; //KW
	// private KatalogBean katalogBean;
	// private String lieferantenbestellnummer=""; //wird aus
	// lieferantenArtikelBestellnummer-Tabelle übernommen
	// private String katalogseite = ""; //wird aus
	// lieferantenArtikelBestellnummer-Tabelle übernommen
	// private Double katalogpreis = 0.0;

	private ModelKnotenBean modelKnotenBean;

	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}

	public EinlagerungPosBean() {
		super();
		// //Kalenderwoche ermitteln
		// GregorianCalendar gc = new GregorianCalendar();
		// gc.setGregorianChange(new Date());
		// liefertermin = gc.get(Calendar.WEEK_OF_YEAR);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (this.id != id) {
			this.id = id;
			beanIstGeaendert();
		}
	}

//	public EinlagerungBean getEinlagerungBean() {
//		return einlagerungBean;
//	}
//
//	public void setEinlagerungBean(EinlagerungBean einlagerungBean) {
//		if (this.einlagerungBean == null || einlagerungBean == null
//				|| !this.einlagerungBean.equals(einlagerungBean)) {
//			this.einlagerungBean = einlagerungBean;
//			beanIstGeaendert();
//		}
//	}

	public Integer getMenge() {
		return menge;
	}

	public void setMenge(Integer menge) {
		if (this.menge != menge) {
			this.menge = menge;
			beanIstGeaendert();
		}
	}

//	public Integer getPosition() {
//		return position;
//	}
//
//	public void setPosition(Integer position) {
//		if (this.position != position) {
//			this.position = position;
//			beanIstGeaendert();
//		}
//	}
//
//	public Integer getEbene() {
//		return ebene;
//	}
//
//	public void setEbene(Integer ebene) {
//		if (this.ebene != ebene) {
//			this.ebene = ebene;
//			beanIstGeaendert();
//		}
//	}
//
//	public Integer getSaeule() {
//		return saeule;
//	}
//
//	public void setSaeule(Integer saeule) {
//		if (this.saeule != saeule) {
//			this.saeule = saeule;
//			beanIstGeaendert();
//		}
//	}
//
//	public Integer getZeile() {
//		return zeile;
//	}
//
//	public void setZeile(Integer zeile) {
//		if (this.zeile != zeile) {
//			this.zeile = zeile;
//			beanIstGeaendert();
//		}
//	}
//
//	public void setHalle(String halle) {
//		if (halle == null) {
//			halle = "";
//		}
//		if (!this.halle.equals(halle)) {
//			this.halle = halle;
//		}
//	}

	// public Integer getLiefertermin() {
	// return liefertermin;
	// }
	// public void setLiefertermin(Integer liefertermin) {
	// if(this.liefertermin != liefertermin){
	// this.liefertermin = liefertermin;
	// beanIstGeaendert();
	// }
	// }
	public EinlagerungStatus getStatus() {
		if (status == null) {
			status = EinlagerungStatus.OFFEN;
		}
		return status;
	}

	public void setStatus(EinlagerungStatus status) {
		if (!this.status.equals(status)) {
			this.status = status;
			beanIstGeaendert();
		}
	}

	@Override
	public String toString() {
		return "Einlagerungnsposition " + getId();
	}

	public ArtikelBean getArtikelBean() {
		if (artikelBean == null) {
			artikelBean = new ArtikelBean();
		}
		return artikelBean;
	}

	public void setArtikelBean(ArtikelBean artikelBean) {
		if (artikelBean == null) {
			artikelBean = new ArtikelBean();
		}
		if (this.artikelBean == null || artikelBean == null
				|| !this.artikelBean.equals(artikelBean)) {
			setKostenstelle(new KostenstelleBean());
			setMengenEinheitBean(new MengenEinheitBean());
			// setKatalogBean(new KatalogBean());
			// setLieferantenbestellnummer("");
			// setKatalogseite("");
			// setKatalogpreis(0.0);
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
		if (this.kostenstelle == null || kostenstelle == null
				|| !this.kostenstelle.equals(kostenstelle)) {
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
		if (mengenEinheitBean == null) {
			mengenEinheitBean = new MengenEinheitBean();
		}
		return mengenEinheitBean;
	}

	public void setMengenEinheitBean(MengenEinheitBean mengenEinheitBean) {
		if (this.mengenEinheitBean == null || mengenEinheitBean == null
				|| !this.mengenEinheitBean.equals(mengenEinheitBean)) {
			this.mengenEinheitBean = mengenEinheitBean;
			beanIstGeaendert();
		}
	}

//	public HalleBean getHalleBean() {
//		if (halleBean == null) {
//			halleBean = new HalleBean();
//		}
//		return halleBean;
//	}
//
//	public void setHalleBean(HalleBean halleBean) {
//		this.halleBean = halleBean;
//	}

	// public KatalogBean getKatalogBean() {
	// if(katalogBean==null){
	// katalogBean = new KatalogBean();
	// }
	// return katalogBean;
	// }
	//
	// public void setKatalogBean(KatalogBean katalogBean) {
	// if(this.katalogBean==null || katalogBean == null ||
	// !this.katalogBean.equals(katalogBean)){
	// this.katalogBean = katalogBean;
	// beanIstGeaendert();
	// }
	// }
	//
	// public String getLieferantenbestellnummer() {
	// return lieferantenbestellnummer;
	// }
	//
	// public void setLieferantenbestellnummer(String lieferantenbestellnummer)
	// {
	// if(lieferantenbestellnummer==null){
	// lieferantenbestellnummer = "";
	// }
	// if(!this.lieferantenbestellnummer.equals(lieferantenbestellnummer)){
	// this.lieferantenbestellnummer = lieferantenbestellnummer;
	// beanIstGeaendert();
	// }
	// }
	//
	// public String getKatalogseite() {
	// return katalogseite;
	// }
	//
	// public void setKatalogseite(String katalogseite) {
	// if (katalogseite==null){
	// katalogseite = "";
	// }
	// if (!this.katalogseite.equals(katalogseite)){
	// this.katalogseite = katalogseite;
	// }
	// }
	//
	// public Double getKatalogpreis() {
	// return katalogpreis;
	// }
	//
	// public void setKatalogpreis(Double katalogpreis) {
	// if(katalogpreis==null){
	// katalogpreis = 0.0;
	// }
	// if(this.katalogpreis != katalogpreis){
	// this.katalogpreis = katalogpreis;
	// beanIstGeaendert();
	// }
	// }

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		// if (getLiefertermin() < 0 || getLiefertermin() > 53){
		// getFehlerList().add(new Fehler(29));
		// }
		if (getMenge() < 1) {
			getFehlerList().add(new Fehler(74));
		}
		if (getPositionBean().getNummer() < 1 || getPositionBean().getNummer() > 100) {
			getFehlerList().add(new Fehler(29));
		}
		if (getPositionBean().getEbeneBean().getNummer() < 1 || getPositionBean().getEbeneBean().getNummer() > 100) {
			getFehlerList().add(new Fehler(29));
		}
		if (getPositionBean().getEbeneBean().getSaeuleBean().getNummer() < 1 || getPositionBean().getEbeneBean().getSaeuleBean().getNummer() > 1000) {
			getFehlerList().add(new Fehler(29));
		}
		if (getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getNummer() < 1 || getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getNummer() > 1000) {
			getFehlerList().add(new Fehler(29));
		}
		if (getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getHalleBean() == null) {
			getFehlerList().add(new Fehler(69));
		} else {
			if (getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getHalleBean().getId() == 0) {
				getFehlerList().add(new Fehler(69));
			}
		}
//		if (getEinlagerungBean() == null) {
//			getFehlerList().add(new Fehler(78));
//		} else {
//			if (getEinlagerungBean().getId() == 0) {
//				getFehlerList().add(new Fehler(78));
//			}
//		}
		if (getArtikelBean() == null) {
			getFehlerList().add(new Fehler(79));
		} else {
			if (getArtikelBean().getId() == 0) {
				getFehlerList().add(new Fehler(79));
			}
		}
		return getFehlerList();
	}

	// static public java.text.NumberFormat getLieferterminFormat(){
	// return LagerFormate.getNumberFormat(2);
	// }

	static public java.text.NumberFormat getMengeFormat() {
		return LagerFormate.getNumberFormat(1000);
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof EinlagerungPosBean) {
			EinlagerungPosBean tempBean = (EinlagerungPosBean) bean;
			if (tempBean == null) {
				return false;
			} else {
				return (this.getId() == tempBean.getId());
			}
		} else
			return false;
	}

//	public ZeileBean getZeileBean() {
//		if (zeileBean == null) {
//			zeileBean = new ZeileBean();
//		}
//		return zeileBean;
//	}
//
//	public void setZeileBean(ZeileBean zeileBean) {
//		this.zeileBean = zeileBean;
//	}
//
//	public SaeuleBean getSaeuleBean() {
//		if (saeuleBean == null) {
//			saeuleBean = new SaeuleBean();
//		}
//		return saeuleBean;
//	}
//
//	public void setSaeuleBean(SaeuleBean saeuleBean) {
//		this.saeuleBean = saeuleBean;
//	}
//
//	public EbeneBean getEbeneBean() {
//		if (ebeneBean == null) {
//			ebeneBean = new EbeneBean();
//		}
//		return ebeneBean;
//	}
//
//	public void setEbeneBean(EbeneBean ebeneBean) {
//		this.ebeneBean = ebeneBean;
//	}

	public PositionBean getPositionBean() {
		if (positionBean == null) {
			positionBean = new PositionBean();
		}
		return positionBean;
	}

	public void setPositionBean(PositionBean positionBean) {

		this.positionBean = positionBean;
	}

//	public void setHalleBean() {
//		if (halleBean == null) {
//			halleBean = new HalleBean();
//		}
//		this.halleBean = halleBean;
//
//	}

//	public String getHalle() {
//		return halle;
//	}

//	public EtageBean getEtageBean() {
//		if (etageBean == null) {
//			etageBean = new EtageBean();
//		}
//		return etageBean;
//	}
//
//	public void setEtageBean(EtageBean etageBean) {
//
//		this.etageBean = etageBean;
//	}
//
//	public String getEtage() {
//		return etage;
//	}
//
//	public void setEtage(String etage) {
//		this.etage = etage;
//	}

	public void setBenutzerEntnehmer(BenutzerBean benutzerEntnehmerBean) {
		// TODO Auto-generated method stub

	}

	public BenutzerBean getBenutzerEntnehmerBean() {
		if (benutzerEntnehmerBean==null){
			benutzerEntnehmerBean = new BenutzerBean();
		}
		return benutzerEntnehmerBean;
	}

	public void setBenutzerEntnehmerBean(BenutzerBean benutzerEntnehmerBean) {
		if (this.benutzerEntnehmerBean == null || benutzerEntnehmerBean == null
				|| !this.benutzerEntnehmerBean.equals(benutzerEntnehmerBean)) {
			this.benutzerEntnehmerBean = benutzerEntnehmerBean;
			beanIstGeaendert();
		}
	}

		public BenutzerBean getBenutzerKontrollerBean() {
			if (benutzerKontrollerBean==null){
				benutzerKontrollerBean = new BenutzerBean();
			}
			return benutzerKontrollerBean;
		}

		public void setBenutzerKontoller(BenutzerBean benutzerKontrollerBean) {
			if (this.benutzerKontrollerBean == null || benutzerKontrollerBean == null
					|| !this.benutzerKontrollerBean.equals(benutzerKontrollerBean)) {
				this.benutzerKontrollerBean = benutzerKontrollerBean;
				beanIstGeaendert();
			}
		}


}
