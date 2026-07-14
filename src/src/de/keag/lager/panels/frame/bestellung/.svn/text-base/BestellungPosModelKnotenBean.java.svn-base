package de.keag.lager.panels.frame.bestellung;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.db.connection.BeanDBStatus;


public class BestellungPosModelKnotenBean extends ModelKnotenBean {
	public BestellungPosModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.BESTELLUNGSPOSITION,vater);
	}
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Position " + getIBean().getId();
		else return "Position ";
	}
	
	@Override
	public BestellungPosBean getIBean() {
		// TODO Auto-generated method stub
		return (BestellungPosBean)super.getIBean();
	}
	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BestellungPosBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
