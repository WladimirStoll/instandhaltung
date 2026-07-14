package de.keag.lager.panels.frame.halle.model;

import java.util.Date;

import de.keag.lager.core.ISuchBean;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;


/*
 * Diese Klasse wird benutzt, wann immer wir nach der Halle in der Datenbank suchen müssen.
 */
public class HalleSuchBean implements ISuchBean {
	private AbteilungBean abteilungBean;
	private HalleBean halleBean;
	private EtageBean etageBean;
	private ArtikelBean artikelBean;
	private BenutzerBean einlagerungsBenutzer;
	private Date einlagerungsDate;
	private ZeileBean zeileBean;
	private SaeuleBean saeuleBean;
	private EbeneBean ebeneBean;
	private PositionBean positionBean;

	public void setAbteilung(AbteilungBean abteilungBean) {
		this.abteilungBean = abteilungBean;
	}

	public AbteilungBean getAbteilungBean() {
		if (abteilungBean==null){
			abteilungBean= new AbteilungBean();
			
		}
		return abteilungBean;
	}

	public void setAbteilungBean(AbteilungBean abteilungBean) {
		this.abteilungBean = abteilungBean;
	}

	public HalleBean getHalleBean() {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		return halleBean;
	}

	public void setHalleBean(HalleBean halleBean) {
		this.halleBean = halleBean;
	}

	public EtageBean getEtageBean() {
		if (etageBean==null){
			etageBean = new EtageBean();
			
		}
		return etageBean;
	}

	public void setEtageBean(EtageBean etageBean) {
		this.etageBean = etageBean;
	}

	public ArtikelBean getArtikelBean() {
		if (artikelBean==null){
			artikelBean= new ArtikelBean();
			
		}
		return artikelBean;
	}

	public void setArtikelBean(ArtikelBean artikelBean) {
		this.artikelBean = artikelBean;
	}

	public BenutzerBean getEinlagerungsBenutzer() {
		if (einlagerungsBenutzer==null){
			einlagerungsBenutzer= new BenutzerBean();
			
		}
		return einlagerungsBenutzer;
	}

	public void setEinlagerungsBenutzer(BenutzerBean einlagerungsBenutzer) {
		this.einlagerungsBenutzer = einlagerungsBenutzer;
	}

	public Date getEinlagerungsDate() {
		if (einlagerungsDate==null){
			einlagerungsDate= new Date();
			
		}
		return einlagerungsDate;
	}

	public void setEinlagerungsDate(Date einlagerungsDate) {
		this.einlagerungsDate = einlagerungsDate;
	}

	public ZeileBean getZeileBean() {
		if (zeileBean==null){
			zeileBean= new ZeileBean();
			
		}
		return zeileBean;
	}

	public void setZeileBean(ZeileBean zeileBean) {
		this.zeileBean = zeileBean;
	}

	public SaeuleBean getSaeuleBean() {
		if (saeuleBean==null){
			saeuleBean= new SaeuleBean();
			
		}
		return saeuleBean;
	}

	public void setSaeuleBean(SaeuleBean saeuleBean) {
		this.saeuleBean = saeuleBean;
	}

	public EbeneBean getEbeneBean() {
		if (ebeneBean==null){
			ebeneBean= new EbeneBean();
		}
		return ebeneBean;
	}

	public void setEbeneBean(EbeneBean ebeneBean) {
		this.ebeneBean = ebeneBean;
	}

	public PositionBean getPositionBean() {
		if (positionBean==null){
			positionBean = new PositionBean();
		}
		return positionBean;
	}

	public void setPositionBean(PositionBean positionBean) {
		if (positionBean==null){
			positionBean= new PositionBean();
		}
		this.positionBean = positionBean;
	}

}
