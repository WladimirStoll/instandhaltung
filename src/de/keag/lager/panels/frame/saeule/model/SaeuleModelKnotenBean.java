package de.keag.lager.panels.frame.saeule.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class SaeuleModelKnotenBean extends ModelKnotenBean {
	public SaeuleModelKnotenBean(SaeuleBean saueleBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.SAEULE,null);
		setIBean(saueleBean);
		saueleBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "S " + getIBean().getNummer() ; //+"("+getIBean().getId()+")";
//			return "Säule " + getIBean().getNummer() +"("+getIBean().getId()+")";
		else return "Säule " ;
	}
	
	@Override
	public SaeuleBean getIBean() {
		return (SaeuleBean)super.getIBean();
	}

//	@Override
//	public boolean istGesamterInhaltGeaendert() {
//		if (((SaeuleBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
//			return true;
//		}
//		for (int i = 0; i < getKinderList().size(); i++) {
//			ModelKnotenBean kind = getKinderList().get(i);
//			if (((Bean) kind.getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
//				return true;
//			}
//		}
//		return false;
//	}
}
