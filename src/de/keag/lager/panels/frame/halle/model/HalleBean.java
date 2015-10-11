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
package de.keag.lager.panels.frame.halle.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class HalleBean extends Bean {
	
	private Integer id;
	private String name;
	private Integer nummer;
	
	private ArrayList<EtageBean> etageBeans = null;
	private ArrayList<ZeileBean> zeileBeans = null;
	

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof HalleBean){
			HalleBean tempBean = (HalleBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId()&&(!this.getId().equals(0)));
			}
		}else return false;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_HALLE + getId();
	}
	
	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		
		if(getName().trim().equals("")){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		return getFehlerList();
	}

	public Integer getId() {
		if (id == null){
			id = 0;
		}
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
	}

	public String getName() {
		if (name == null){
			name = "";
		}
		return name;
	}

	public void setName(String name) {
		if (!getName().equals(name)){
			beanIstGeaendert();
			this.name = name;
		}
	}

	public ArrayList<EtageBean> getEtageBeans() {
		if(etageBeans==null){
			etageBeans = new ArrayList<EtageBean>();
		}
		return etageBeans;
	}

	public void setEtageBeans(ArrayList<EtageBean> etageBeans) {
		if (etageBeans==null){
			etageBeans = new ArrayList<EtageBean>();
		}
		if (this.etageBeans != etageBeans){
			this.etageBeans = etageBeans;
			beanIstGeaendert();
		}
		
	}

	public ArrayList<ZeileBean> getZeileBeans() {
		if(zeileBeans==null){
			zeileBeans = new ArrayList<ZeileBean>();
		}
		return zeileBeans;
	}

	public void setZeileBeans(ArrayList<ZeileBean> zeileBeans) {
		if (zeileBeans==null){
			zeileBeans = new ArrayList<ZeileBean>();
		}
		if (this.zeileBeans != zeileBeans){
			this.zeileBeans = zeileBeans;
			beanIstGeaendert();
		}
		
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getLagerOrt() {
		return getName();
	}

	

	public void setNummer(Integer nummer) {
		if (!this.getNummer().equals(nummer)){
			this.nummer = nummer;
			beanIstGeaendert();
		}
	}

	public Integer getNummer() {
		if (nummer == null) {
			nummer = 0;
		}
		return nummer;
	}
	
}


