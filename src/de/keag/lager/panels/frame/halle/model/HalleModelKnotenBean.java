package de.keag.lager.panels.frame.halle.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;


public class HalleModelKnotenBean extends ModelKnotenBean {
	public HalleModelKnotenBean(HalleBean halleBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.HALLE,null);
		setIBean(halleBean);
		halleBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "H " + getIBean().getNummer().toString()+ " " + getIBean().getName();
//			return "Halle " + getIBean().getName() +"("+getIBean().getId()+")";
		else return "Halle " ;
	}
	
	@Override
	public HalleBean getIBean() {
		return (HalleBean)super.getIBean();
	}

}
