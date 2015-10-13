package de.keag.lager.panels.frame.einlagerung.model;


import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class EinlagerungPosModelKnotenBean extends ModelKnotenBean {
	public EinlagerungPosModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.EINLAGERUNGSPOSITION,vater);
	}
	
	public EinlagerungPosModelKnotenBean(EinlagerungPosBean einlagerungPosBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.EINLAGERUNGSPOSITION,null);
		setIBean(einlagerungPosBean);
		einlagerungPosBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Einlagerung " + getIBean().getId();
		else return "Einlagerung ";
	}
	
	@Override
	public EinlagerungPosBean getIBean() {
		// TODO Auto-generated method stub
		return (EinlagerungPosBean)super.getIBean();
	}
	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((EinlagerungPosBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
