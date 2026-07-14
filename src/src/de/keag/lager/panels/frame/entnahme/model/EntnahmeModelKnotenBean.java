package de.keag.lager.panels.frame.entnahme.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;

public class EntnahmeModelKnotenBean extends ModelKnotenBean {
	public EntnahmeModelKnotenBean(EntnahmeBean entnahmeBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.ENTNAHME,null);
		setIBean(entnahmeBean);
		entnahmeBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Entnahme " + getIBean().getId();
		else return "Entnahme " ;
	}
	
	@Override
	public EntnahmeBean getIBean() {
		// TODO Auto-generated method stub
		return (EntnahmeBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((EntnahmeBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
