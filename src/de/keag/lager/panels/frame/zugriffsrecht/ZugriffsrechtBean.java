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
package de.keag.lager.panels.frame.zugriffsrecht;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.email.ProviderBean;
import de.keag.lager.core.fehler.Fehler;


public class ZugriffsrechtBean extends Bean{
	private Integer id = 0;	
    private String zugriffsrechtName = "";
	
	
	public ZugriffsrechtBean(int zugriffsRechtID) {
		this();
		setId(zugriffsRechtID);
	}
    
    
	public ZugriffsrechtBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}


	@Override
	public String toString(){
		return "ZugriffsrechtBean:"+	getBeanDBStatus()+
		" Name:" + getZugriffsrechtName();
//		" fkBenutzerAbsender:" + getAbsenderBenutzerBean()+
//		" fkBenutzerAnnehmer:" + getAnnehmerBenutzerBean()+
//		" fkHerstellerLieferant:" + getLhBean();

	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		return getFehlerList();
	}

	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof ZugriffsrechtBean){
			ZugriffsrechtBean tempBean = (ZugriffsrechtBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId())&(tempBean.getId()!=0); 
			}
		}else return false;
    }
	



	public void setZugriffsrechtName(String abteilungName) {
		this.zugriffsrechtName = abteilungName;
	}

	public String getZugriffsrechtName() {
		return zugriffsrechtName;
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		// TODO Auto-generated method stub
		
	}
	
}

