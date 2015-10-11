/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
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
