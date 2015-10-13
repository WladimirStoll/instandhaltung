package de.keag.lager.panels.frame.wartung.model;

import java.util.Date;

import de.keag.lager.core.ISuchBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

public class WartungSuchBean implements ISuchBean {

	private BaugruppeBean baugruppeBean;
	private Date datumVon;
	private Date datumBis;
	private StatusEnum statusEnum;
	private WartungsArtBean wartungsArtBean;
	private WartungsTypBean wartungsTypBean;

	public WartungSuchBean() {
		super();
	}

	public BaugruppeBean getBaugruppeBean() {
		if (this.baugruppeBean == null) {
			this.baugruppeBean = new BaugruppeBean();
		}
		return baugruppeBean;
	}

	public void setBaugruppeBean(BaugruppeBean baugruppeBean) {
		this.baugruppeBean = baugruppeBean;
	}

	public Date getDatumVon() {
		if (this.datumVon == null) {
			this.datumVon = new Date();
		}
		return datumVon;
	}

	public void setDatumVon(Date datumVon) {
		this.datumVon = datumVon;
	}

	public Date getDatumBis() {
		if (this.datumBis == null) {
			this.datumBis = new Date();
		}
		return datumBis;
	}

	public void setDatumBis(Date datumBis) {
		this.datumBis = datumBis;
	}

	public StatusEnum getStatusEnum() {
		if (this.statusEnum == null) {
			this.statusEnum = StatusEnum.ALLE;
		}
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public WartungsArtBean getWartungsArtBean() {
		if (this.wartungsArtBean == null) {
			this.wartungsArtBean = new WartungsArtBean();
		}
		return wartungsArtBean;
	}

	public void setWartungsArtBean(WartungsArtBean wartungsArtBean) {
		this.wartungsArtBean = wartungsArtBean;
	}
	
	public WartungsTypBean getWartungsTypBean() {
		if (this.wartungsTypBean == null) {
			this.wartungsTypBean = new WartungsTypBean();
		}
		return wartungsTypBean;
	}

	public void setWartungsTypBean(WartungsTypBean wartungsTypBean) {
		this.wartungsTypBean = wartungsTypBean;
	}
	

}
