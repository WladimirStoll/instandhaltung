package de.keag.lager.panels.frame.wartung.model;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;


public class WartungsMitarbeiterModelKnotenBean extends ModelKnotenBean {
	
	public WartungsMitarbeiterModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.WARTUNG_MITARBEITER,vater);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getFk_benutzer()!=null){
				return getIBean().getFk_benutzer().toString().trim() + " / " + getIBean().getFk_herstellerlieferant().toString();
			}else{
				return "Mitarbeiter/Firma";
			}
		else return "Mitarbeiter/Firma... " ;
	}
	
	@Override
	public WartungsMitarbeiterBean getIBean() {
		return (WartungsMitarbeiterBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((WartungsMitarbeiterBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
