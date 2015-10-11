package de.keag.lager.panels.frame.ebene;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;

public class EbeneBean extends Bean{
	private Integer id = 0;
	private Integer nummer = 0;
	private SaeuleBean saeuleBean = null;
	private ArrayList<PositionBean> positionBeans = null;
	
	
	public EbeneBean() {
		super();
	}
	public EbeneBean(Integer id, Integer nummer, SaeuleBean saeuleBean) {
		this();
		this.setId(id) ;
		this.setNummer(nummer) ;
		this.setSaeuleBean(saeuleBean) ;
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_EBENE + getId();
	}
	
	public Integer getId() {
		if (id==null){
			id = 0;
		}
		return id;
	}
	
	public void setId(Integer id) {
		if (!getId().equals(id)){
			this.id = id;
			beanIstGeaendert();
		}
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
		if(getSaeuleBean().getId().equals(0)){
			getFehlerList().add(new Fehler(72));
		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof EbeneBean){
			EbeneBean tempBean = (EbeneBean) bean;
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

	public SaeuleBean getSaeuleBean() {
		if (saeuleBean==null){
			saeuleBean = new SaeuleBean();
		}
		return saeuleBean;
	}

	public void setSaeuleBean(SaeuleBean saeule) {
		if(!getSaeuleBean().equals(saeule)){
			this.saeuleBean = saeule;
			beanIstGeaendert();
		}
	}
	public ArrayList<PositionBean> getPositionBeans() {
		if (positionBeans == null){
			positionBeans = new ArrayList<PositionBean>();
		}
		return positionBeans;
	}
	public void setPositionBeans(ArrayList<PositionBean> positionBeans) {
		if (positionBeans==null){
			positionBeans = new ArrayList<PositionBean>();
		}
		if (this.positionBeans != positionBeans){
			this.positionBeans = positionBeans;
			beanIstGeaendert();
		}
	}

	@Override
	public String toString() {
		return getNummer().toString();
	}
	public String getLagerOrt() {
		return this.getSaeuleBean().getLagerOrt() + "-" +getNummer();
	}

}
