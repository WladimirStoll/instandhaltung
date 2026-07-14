package de.keag.lager.panels.frame.abteilung;

import de.keag.lager.core.ISuchBean;

public class AbteilungSuchBean implements ISuchBean{
	private AbteilungBean abteilungBean;

	public AbteilungBean getAbteilungBean() {
		if (abteilungBean==null){
			abteilungBean = new AbteilungBean();
		}
		return abteilungBean;
	}

	public void setAbteilungBean(AbteilungBean abteilungBean) {
		this.abteilungBean = abteilungBean;
	}

}
