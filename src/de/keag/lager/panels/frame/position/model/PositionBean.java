package de.keag.lager.panels.frame.position.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;

public class PositionBean extends Bean{
	private Integer id = 0;
	private Integer nummer = 0;
	private EbeneBean ebeneBean = null;
	private ArrayList<BestandspackstueckBean> bestandspackstueckBeans = null;
	
	public PositionBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_POSITION + getId();
	}
	
	public PositionBean(Integer id, Integer nummer, EbeneBean ebeneBean) {
		this();
		this.setId(id) ;
		this.setNummer(nummer) ;
		this.setEbeneBean(ebeneBean) ;
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
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		if(getNummer().equals(0)){
			getFehlerList().add(new Fehler(70));
		}
		if(getEbeneBean().getId().equals(0)){
			getFehlerList().add(new Fehler(72));
		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof PositionBean){
			PositionBean tempBean = (PositionBean) bean;
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

	public EbeneBean getEbeneBean() {
		if (ebeneBean==null){
			ebeneBean = new EbeneBean();
		}
		return ebeneBean;
	}

	public void setEbeneBean(EbeneBean ebene) {
		if (!getEbeneBean().equals(ebene)){
			this.ebeneBean = ebene;
			beanIstGeaendert();
		}
	}
	public ArrayList<BestandspackstueckBean> getBestandspackstueckBeans() {
		if (bestandspackstueckBeans==null){
			bestandspackstueckBeans = new ArrayList<BestandspackstueckBean>(); 
		}
		return bestandspackstueckBeans;
	}
	public void setBestandspackstueckBeans(
			ArrayList<BestandspackstueckBean> bestandspackstueckBeans) {
		if (bestandspackstueckBeans==null){
			bestandspackstueckBeans = new ArrayList<BestandspackstueckBean>();
		}
		if (this.bestandspackstueckBeans != bestandspackstueckBeans){
			this.bestandspackstueckBeans = bestandspackstueckBeans;
			beanIstGeaendert();
		}
		
	}

	@Override
	public String toString() {
		return getNummer().toString();
	}

	public Object getLagerOrt() {
		return this.getEbeneBean().getLagerOrt() + "-" +getNummer();
	}

}
