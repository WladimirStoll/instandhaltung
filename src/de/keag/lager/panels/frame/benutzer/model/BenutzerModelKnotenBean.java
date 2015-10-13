package de.keag.lager.panels.frame.benutzer.model;

import javax.swing.Icon;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;


public class BenutzerModelKnotenBean extends ModelKnotenBean {
	public BenutzerModelKnotenBean(BenutzerBean benutzerBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BENUTZER,null);
		setIBean(benutzerBean);
		benutzerBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Benutzer " + getIBean().getVorname() + " " + getIBean().getName();
		else return "Benutzer " ;
	}
	
	@Override
	public BenutzerBean getIBean() {
		// TODO Auto-generated method stub
		return (BenutzerBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BenutzerBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
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
	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_BENUTZER);	
	}	
	
}
