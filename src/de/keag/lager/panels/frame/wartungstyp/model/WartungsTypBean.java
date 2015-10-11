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
package de.keag.lager.panels.frame.wartungstyp.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;

public class WartungsTypBean extends Bean {
	private Integer id = 0;
	private String name = ""; 
	public WartungsTypBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if (!this.id.equals(id)){
			this.id = id;
			beanIstGeaendert();
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (!this.name.equals(name)){
			this.name = name;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		
		if(getName().trim().equals("")
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		
		
		return getFehlerList();
	}

	

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungsTypBean){
			WartungsTypBean wartungsTypBean = (WartungsTypBean) bean;
			if (wartungsTypBean==null){
				return false;
			}else{
				return (this.getId() == wartungsTypBean.getId());
			}
		}else return false;
    }
	
	@Override
	public String toString(){
		return getName();
	}
	
	
}
