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
package de.keag.lager.panels.frame.lieferanthersteller.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;

public class LhZugriffsrechtBean extends Bean{
	
	private LhBean lhBean = null;
	private ZugriffsrechtBean zugriffsrechtBean = null;
	
	public LhZugriffsrechtBean() {
		super();
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getZugriffsrecht().getId();
	}
	

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if(zugriffsrechtBean.equals(0)){
			getFehlerList().add(new Fehler(42));
		}
		if(lhBean.equals(0)){
			getFehlerList().add(new Fehler(43));
		}
		return getFehlerList();
	}

	public LhBean getLh() {
		if (lhBean==null){
			lhBean = new LhBean();
		}
		return lhBean;
	}

	public void setLh(LhBean lh) {
		
		if (this.lhBean==null || !this.lhBean.equals(lh)){
			this.lhBean = lh;
			beanIstGeaendert();
		}
	}

	public ZugriffsrechtBean getZugriffsrecht() {
		if (zugriffsrechtBean==null){
			zugriffsrechtBean = new ZugriffsrechtBean();
		}
		return zugriffsrechtBean;
	}

	public void setZugriffsrecht(ZugriffsrechtBean bean) {
		if (this.zugriffsrechtBean==null || !this.zugriffsrechtBean.equals(bean)){
			zugriffsrechtBean = bean;
			beanIstGeaendert();
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof LhZugriffsrechtBean){
			LhZugriffsrechtBean tempBean = (LhZugriffsrechtBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return this.getZugriffsrecht().equals(tempBean.getZugriffsrecht())
					&& this.getLh().equals(tempBean.getLh());
			}
		}else return false;
	}
	
}
