package de.keag.lager.panels.frame.lieferanthersteller.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
//import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class LhModelKnotenBean extends ModelKnotenBean {
	public LhModelKnotenBean(LhBean lhBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.LIEFERANT_HERSTELLER,null);
		setIBean(lhBean);
		lhBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Lieferant-Hersteller " + getIBean().getName();
		else return "Lieferant-Hersteller " ;
	}
//	@Override
//	public String toString() {
//		if (getIBean()!=null)
//			return "Lh " + getIBean().getVorname() + " " + getIBean().getName();
//		else return "Lh " ;
//	}
	
	@Override
	public LhBean getIBean() {
		// TODO Auto-generated method stub
		return (LhBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((LhBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
