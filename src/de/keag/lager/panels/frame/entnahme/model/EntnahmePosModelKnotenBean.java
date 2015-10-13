package de.keag.lager.panels.frame.entnahme.model;


import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class EntnahmePosModelKnotenBean extends ModelKnotenBean {
	public EntnahmePosModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.ENTNAHMENSPOSITION,vater);
	}
	
	public EntnahmePosModelKnotenBean(EntnahmePosBean entnahmePosBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.ENTNAHMENSPOSITION,null);
		setIBean(entnahmePosBean);
		entnahmePosBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Entnahme " + getIBean().getId();
		else return "Entnahme ";
	}
	
	@Override
	public EntnahmePosBean getIBean() {
		// TODO Auto-generated method stub
		return (EntnahmePosBean)super.getIBean();
	}
	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((EntnahmePosBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
