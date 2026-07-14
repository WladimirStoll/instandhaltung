package de.keag.lager.panels.frame.bestellanforderung;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.db.connection.BeanDBStatus;


public class BaPosModelKnotenBean extends ModelKnotenBean {
	public BaPosModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.ANFORDERUNGSPOSITION,vater);
	}
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Position " + getIBean().getId();
		else return "Position ";
	}
	
	@Override
	public BaPosBean getIBean() {
		// TODO Auto-generated method stub
		return (BaPosBean)super.getIBean();
	}
	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BaPosBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
