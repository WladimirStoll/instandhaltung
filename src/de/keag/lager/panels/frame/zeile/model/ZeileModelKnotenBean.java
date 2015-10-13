package de.keag.lager.panels.frame.zeile.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.db.connection.BeanDBStatus;


public class ZeileModelKnotenBean extends ModelKnotenBean {
//	public ZeileModelKnotenBean(ZeileBean zeileBean) throws BenutzerOberflacheLagerException {
//		super(ModelKnotenTyp.ZEILE,null);
//		setIBean(zeileBean);
//		zeileBean.setModelKnotenBean(this);
//	}
	
public ZeileModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.ZEILE,vater);
	}
	
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Z " + getIBean().getNummer().toString(); // +"("+getIBean().getId()+")";
//			return "Zeile " + getIBean().getNummer().toString() +"("+getIBean().getId()+")";
		else return "Zeile " ;
	}
	
	@Override
	public ZeileBean getIBean() {
		return (ZeileBean)super.getIBean();
	}

//	@Override
//	public boolean istGesamterInhaltGeaendert() {
//		if (((ZeileBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
