package de.keag.lager.panels.frame.zugriffsrecht;

import de.keag.lager.core.ISuchBean;

public class ZugriffsrechtSuchBean implements ISuchBean{
	private ZugriffsrechtBean bean;

	public ZugriffsrechtBean getZugriffsrechtBean() {
		if (bean==null){
			bean = new ZugriffsrechtBean();
		}
		return bean;
	}

	public void setZugriffsrechtBean(ZugriffsrechtBean bean) {
		this.bean = bean;
	}

}
