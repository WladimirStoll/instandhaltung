package de.keag.lager.panels.frame.baugruppe.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;


public class BaugruppeModelKnotenBean extends ModelKnotenBean {
	public BaugruppeModelKnotenBean(BaugruppeBean baugruppeBean,ModelKnotenBean vater) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BAUGRUPPE,vater);
		setIBean(baugruppeBean);
		baugruppeBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null){
			if (getIBean().getVaterBaugruppe().getId().equals(0)){
				return "Anlage " + getIBean().getName();
			}else{
				return " " + getIBean().getName();
			}
		}
		else return " " ;
	}
	
	@Override
	public BaugruppeBean getIBean() {
		// TODO Auto-generated method stub
		return (BaugruppeBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BaugruppeBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
	
	@Override
	public int hashCode(){
		if (getIBean()==null){
			return 0;
		}
		return getIBean().getId();
	}
}
