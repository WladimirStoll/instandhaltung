package de.keag.lager.panels.frame.saeule.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class SaeuleBean extends Bean{
	private Integer id = 0;
	private Integer nummer = 0;
	private ZeileBean zeileBean = null;
	private ArrayList<EbeneBean> ebeneBeans = null;
	
	public SaeuleBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_SAEULE + getId();
		
	}
	
	public SaeuleBean(Integer id, Integer nummer, ZeileBean zeileBean) {
		this();
		this.setId(id)  ;
		this.setNummer(nummer)  ;
		this.setZeileBean(zeileBean)  ;
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
		if(getZeileBean().getId().equals(0)){
			getFehlerList().add(new Fehler(72));
		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof SaeuleBean){
			SaeuleBean tempBean = (SaeuleBean) bean;
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
		if (!getNummer().equals(nummer)){
			this.nummer = nummer;
			beanIstGeaendert();
		} 
	}

	public ZeileBean getZeileBean() {
		if (zeileBean==null){
			zeileBean = new ZeileBean();
		}
		return zeileBean;
	}

	public void setZeileBean(ZeileBean zeileBean) {
		if (!getZeileBean().equals(zeileBean)){
			this.zeileBean = zeileBean;
			beanIstGeaendert();
		}
	}
	public ArrayList<EbeneBean> getEbeneBeans() {
		if (ebeneBeans == null){
			ebeneBeans = new ArrayList<EbeneBean>();
		}
		return ebeneBeans;
	}
	public void setEbeneBeans(ArrayList<EbeneBean> ebeneBeans) {
		if (ebeneBeans==null){
			ebeneBeans = new ArrayList<EbeneBean>();
		}
		if (this.ebeneBeans != ebeneBeans){
			this.ebeneBeans = ebeneBeans;
			beanIstGeaendert();
		}
	}

	@Override
	public String toString() {
		return getNummer().toString();
	}

	public String getLagerOrt() {
		return this.getZeileBean().getLagerOrt() + "-" +getNummer();
	}

}
