package de.keag.lager.panels.frame.lieferanthersteller.model;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.db.connection.BeanDBStatus;


public class LhZugriffsrechtModelKnotenBean extends ModelKnotenBean {
	
	public LhZugriffsrechtModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT,vater);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getZugriffsrecht()!=null){
				return "Zugriffsrecht " + getIBean().getZugriffsrecht().getZugriffsrechtName();
			}else{
				return "Zugriffsrecht ?";
			}
		else return "Zugriffsrecht " ;
	}
	
	@Override
	public LhZugriffsrechtBean getIBean() {
		return (LhZugriffsrechtBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((LhZugriffsrechtBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
