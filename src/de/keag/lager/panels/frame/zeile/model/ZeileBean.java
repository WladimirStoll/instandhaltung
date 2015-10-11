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
package de.keag.lager.panels.frame.zeile.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;

public class ZeileBean extends Bean{
	private Integer id = 0;
	private Integer nummer = 0;
	private HalleBean halleBean = null;
	private EtageBean etageBean = null;
	private AbteilungBean abteilungBean = null;
	private ArrayList<SaeuleBean> saeuleBeans = null;
	
	public ZeileBean() {
		super();
	}
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_ZEILE + getId();
	}
	
	public ZeileBean(Integer id, 
					Integer nummer, 
					HalleBean halleBean, 
					EtageBean etageBean, 
					AbteilungBean abteilungBean) {
		this(); //eigener Konstruktor wird aufgerufen.
		this.setId(id);
		this.setNummer(nummer);
		this.setHalleBean(halleBean);
		this.setEtageBean(etageBean);
		this.setAbteilungBean(abteilungBean);
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
		
	}
	
//	@Override
//	public ModelKnotenBean getModelKnotenBean() {
//		Log.log().severe("nicht implementiert");
//		return null;
//	}
//	
//	@Override
//	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		if(getNummer().equals(0)){
			getFehlerList().add(new Fehler(70));
		}
		if(getHalleBean().getId().equals(0)){
			getFehlerList().add(new Fehler(69));
		}
		
//		if(getEtageBean().getId().equals(0)){
//			getFehlerList().add(new Fehler(71));
//		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof ZeileBean){
			ZeileBean tempBean = (ZeileBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId()&&(!this.getId().equals(0)));
			}
		}else return false;
    }

	public Integer getNummer() {
		if (nummer==null){
			nummer = 0;
		}
		return nummer;
	}

	public void setNummer(Integer nummer) {
		if(!getNummer().equals(nummer)){
			this.nummer = nummer;
			beanIstGeaendert();
		}
	}

	public HalleBean getHalleBean() {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		return halleBean;
	}

	public void setHalleBean(HalleBean halle) {
		this.halleBean = halle;
	}

	public EtageBean getEtageBean() {
		if (etageBean==null){
			etageBean = new EtageBean();
		}
		return etageBean;
	}

	public void setEtageBean(EtageBean etage) {
		this.etageBean = etage;
	}

	public void setAbteilungBean(AbteilungBean abteilung) {
		this.abteilungBean = abteilung;
	}

	public ArrayList<SaeuleBean> getSaeuleBeans() {
		if (saeuleBeans==null){
			saeuleBeans = new ArrayList<SaeuleBean>(); 
		}
		return saeuleBeans;
	}

	public void setSaeuleBeans(ArrayList<SaeuleBean> saeuleBeans) {
		if (saeuleBeans==null){
			saeuleBeans = new ArrayList<SaeuleBean>();
		}
		if (this.saeuleBeans != saeuleBeans){
			this.saeuleBeans = saeuleBeans;
			beanIstGeaendert();
		}
	}

	public AbteilungBean getAbteilungBean() {
		if (abteilungBean == null){
			abteilungBean = new AbteilungBean();
		}
		return abteilungBean;
	}

	@Override
	public String toString() {
		return getNummer().toString();
	}
	public String getLagerOrt() {
		if (this.getEtageBean().getId()!=0){
			return this.getEtageBean().getLagerOrt() + " " +getNummer();
		}else{
			return this.getHalleBean().getLagerOrt() + " " +getNummer();
		}
	}

}
