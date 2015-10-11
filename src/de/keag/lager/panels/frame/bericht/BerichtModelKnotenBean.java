package de.keag.lager.panels.frame.bericht;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;

public class BerichtModelKnotenBean extends ModelKnotenBean {
	public BerichtModelKnotenBean(BerichtBean baBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BERICHT,null);
		setIBean(baBean);
		baBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Bericht " + getIBean().getId();
		else return "Bericht " ;
	}
	
	@Override
	public BerichtBean getIBean() {
		// TODO Auto-generated method stub
		return (BerichtBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BerichtBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
