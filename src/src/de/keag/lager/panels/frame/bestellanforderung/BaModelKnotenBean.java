package de.keag.lager.panels.frame.bestellanforderung;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;

public class BaModelKnotenBean extends ModelKnotenBean {
	public BaModelKnotenBean(BaBean baBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.ANFORDERUNG,null);
		setIBean(baBean);
		baBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Anforderung " + getIBean().getId();
		else return "Anforderung " ;
	}
	
	@Override
	public BaBean getIBean() {
		// TODO Auto-generated method stub
		return (BaBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BaBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
