package de.keag.lager.panels.frame.etage.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class EtageModelKnotenBean extends ModelKnotenBean {
	
//	public EtageModelKnotenBean(EtageBean etageBean) throws BenutzerOberflacheLagerException {
//		super(ModelKnotenTyp.ETAGE,null);
//		setIBean(etageBean);
//		etageBean.setModelKnotenBean(this);
//	}
	
	public EtageModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.ETAGE,vater);
	}
	
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "E " + getIBean().getName(); // +"("+getIBean().getId()+")";
//			return "Etage " + getIBean().getName() +"("+getIBean().getId()+")";
		else return "Etage " ;
	}
	
	@Override
	public EtageBean getIBean() {
		return (EtageBean)super.getIBean();
	}

//	@Override
//	public boolean istGesamterInhaltGeaendert() {
//		if (((EtageBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
