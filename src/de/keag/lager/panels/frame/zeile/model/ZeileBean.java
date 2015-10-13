package de.keag.lager.panels.frame.zeile.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;

public class ZeileBean extends Bean{
	private Integer id = 0;
	private Integer nummer = 0;
	private HalleBean halleBean = null;
	private EtageBean etageBean = null;
	private AbteilungBean abteilungBean = null;
	private ArrayList<SaeuleBean> saeuleBeans = null;
	
	public ZeileBean() {
		super();
	}
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_ZEILE + getId();
	}
	
	public ZeileBean(Integer id, 
					Integer nummer, 
					HalleBean halleBean, 
					EtageBean etageBean, 
					AbteilungBean abteilungBean) {
		this(); //eigener Konstruktor wird aufgerufen.
		this.setId(id);
		this.setNummer(nummer);
		this.setHalleBean(halleBean);
		this.setEtageBean(etageBean);
		this.setAbteilungBean(abteilungBean);
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
		
	}
	
//	@Override
//	public ModelKnotenBean getModelKnotenBean() {
//		Log.log().severe("nicht implementiert");
//		return null;
//	}
//	
//	@Override
//	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		if(getNummer().equals(0)){
			getFehlerList().add(new Fehler(70));
		}
		if(getHalleBean().getId().equals(0)){
			getFehlerList().add(new Fehler(69));
		}
		
//		if(getEtageBean().getId().equals(0)){
//			getFehlerList().add(new Fehler(71));
//		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof ZeileBean){
			ZeileBean tempBean = (ZeileBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId()&&(!this.getId().equals(0)));
			}
		}else return false;
    }

	public Integer getNummer() {
		if (nummer==null){
			nummer = 0;
		}
		return nummer;
	}

	public void setNummer(Integer nummer) {
		if(!getNummer().equals(nummer)){
			this.nummer = nummer;
			beanIstGeaendert();
		}
	}

	public HalleBean getHalleBean() {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		return halleBean;
	}

	public void setHalleBean(HalleBean halle) {
		this.halleBean = halle;
	}

	public EtageBean getEtageBean() {
		if (etageBean==null){
			etageBean = new EtageBean();
		}
		return etageBean;
	}

	public void setEtageBean(EtageBean etage) {
		this.etageBean = etage;
	}

	public void setAbteilungBean(AbteilungBean abteilung) {
		this.abteilungBean = abteilung;
	}

	public ArrayList<SaeuleBean> getSaeuleBeans() {
		if (saeuleBeans==null){
			saeuleBeans = new ArrayList<SaeuleBean>(); 
		}
		return saeuleBeans;
	}

	public void setSaeuleBeans(ArrayList<SaeuleBean> saeuleBeans) {
		if (saeuleBeans==null){
			saeuleBeans = new ArrayList<SaeuleBean>();
		}
		if (this.saeuleBeans != saeuleBeans){
			this.saeuleBeans = saeuleBeans;
			beanIstGeaendert();
		}
	}

	public AbteilungBean getAbteilungBean() {
		if (abteilungBean == null){
			abteilungBean = new AbteilungBean();
		}
		return abteilungBean;
	}

	@Override
	public String toString() {
		return getNummer().toString();
	}
	public String getLagerOrt() {
		if (this.getEtageBean().getId()!=0){
			return this.getEtageBean().getLagerOrt() + " " +getNummer();
		}else{
			return this.getHalleBean().getLagerOrt() + " " +getNummer();
		}
	}

}
