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
package de.keag.lager.panels.frame.mengenEinheit;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.login.LoginBean;

public class MengenEinheitBean extends Bean{
	public static final Integer VORDEFINIERT = 1;
	private Integer id = 0;
	private String name = ""; 
	private Integer vordefiniert;
	
	private BeanDBStatus beanStatus; //Für DB-Speicherung
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	public BeanDBStatus getBeanStatus() {
		if(beanStatus==null){
			beanStatus = BeanDBStatus.FEHLERHAFT;
		}
		return beanStatus;
	}
	public void setBeanStatus(BeanDBStatus beanStatus) {
		this.beanStatus = beanStatus;
	}	
	
	public MengenEinheitBean() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public ModelKnotenBean getModelKnotenBean() {
		Log.log().severe("nicht implementiert");
		return null;
	}
	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		Log.log().severe("nicht implementiert");
		
	}
	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof MengenEinheitBean){
			MengenEinheitBean tempBean = (MengenEinheitBean) bean;
			if (tempBean==null){
				return false;
			}else{
				//id' sind gleich und ungleich 0
				return (this.getId() == tempBean.getId())&(tempBean.getId()!=0); 
			}
		}else return false;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public void setVordefiniert(Integer vordefiniert) {
		this.vordefiniert = vordefiniert;
	}

	public Integer getVordefiniert() {
		if (this.vordefiniert==null){
				this.vordefiniert = 0;
		}
		return vordefiniert;
	}
}
