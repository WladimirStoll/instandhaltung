package de.keag.lager.panels.frame.wartung.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;


public class WartungModelKnotenBean extends ModelKnotenBean {
	public WartungModelKnotenBean(WartungBean wartungBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.WARTUNG,null);
		setIBean(wartungBean);
		wartungBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return getIBean().getFk_wartungsart().toString() + " " + getIBean().getFk_wartungstyp().toString();
		else return "Wartung/Reparatur " ;
	}
	
	@Override
	public WartungBean getIBean() {
		// TODO Auto-generated method stub
		return (WartungBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((WartungBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}
		for (int i = 0; i < getKinderList().size(); i++) {
			ModelKnotenBean kind = getKinderList().get(i);
			if (((Bean) kind.getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
				return true;
			}
		}
		return false;
	}
}
