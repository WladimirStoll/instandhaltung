package de.keag.lager.panels.frame.bestellung;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;

public class BestellungModelKnotenBean extends ModelKnotenBean {
	public BestellungModelKnotenBean(BestellungBean baBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BESTELLUNG,null);
		setIBean(baBean);
		baBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Bestellung " + getIBean().getId();
		else return "Bestellung " ;
	}
	
	@Override
	public BestellungBean getIBean() {
		// TODO Auto-generated method stub
		return (BestellungBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BestellungBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
