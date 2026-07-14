package de.keag.lager.panels.frame.bestandspackstueck.model;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class BestandspackstueckModelKnotenBean extends ModelKnotenBean {
	public BestandspackstueckModelKnotenBean(BestandspackstueckBean bean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BESTANDSPACKSTUECK,null);
		setIBean(bean);
		bean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "Bestandspackstück " + getIBean().getId();
		else return "Bestandspackstück " ;
	}
	
	@Override
	public BestandspackstueckBean getIBean() {
		return (BestandspackstueckBean)super.getIBean();
	}

}
