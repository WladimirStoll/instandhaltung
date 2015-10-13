package de.keag.lager.panels.frame.ebene;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class EbeneModelKnotenBean extends ModelKnotenBean {
	public EbeneModelKnotenBean(EbeneBean ebeneBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.EBENE,null);
		setIBean(ebeneBean);
		ebeneBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "E " + getIBean().getNummer(); // +"("+getIBean().getId()+")";
//			return "Ebene " + getIBean().getNummer() +"("+getIBean().getId()+")";
		else return "Ebene " ;
	}
	
	@Override
	public EbeneBean getIBean() {
		return (EbeneBean)super.getIBean();
	}

//	@Override
//	public boolean istGesamterInhaltGeaendert() {
//		if (((EbeneBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
