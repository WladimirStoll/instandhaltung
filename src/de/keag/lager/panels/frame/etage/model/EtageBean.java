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
package de.keag.lager.panels.frame.etage.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.halle.model.HalleBean;

public class EtageBean extends Bean {
	
	private Integer id;
	private String name;
	private HalleBean halleBean;

	public EtageBean() {
		super();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof EtageBean){
			EtageBean tempBean = (EtageBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_ETAGE + getId();
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
		
		if(getHalleBean().getId().equals(0)){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(69));
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

	public HalleBean getHalleBean() {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		return halleBean;
	}

	public void setHalleBean(HalleBean halleBean) {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		if (getHalleBean() != halleBean){
			this.halleBean = halleBean;
			beanIstGeaendert();
		}
		
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public String getLagerOrt() {
		return this.getHalleBean().getLagerOrt() + "(" +getName()+")";
	}

}


