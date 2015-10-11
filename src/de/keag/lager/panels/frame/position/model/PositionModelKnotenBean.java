package de.keag.lager.panels.frame.position.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class PositionModelKnotenBean extends ModelKnotenBean {
	public PositionModelKnotenBean(PositionBean zeileBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.POSITION,null);
		setIBean(zeileBean);
		zeileBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "P" + getIBean().getNummer() ; //+"("+getIBean().getId()+")";
//			return "Position " + getIBean().getNummer() +"("+getIBean().getId()+")";
		else return "Position " ;
	}
	
	@Override
	public PositionBean getIBean() {
		return (PositionBean)super.getIBean();
	}

//	@Override
//	public boolean istGesamterInhaltGeaendert() {
//		if (((PositionBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
