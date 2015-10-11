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
package de.keag.lager.panels.frame.baugruppe.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class BaugruppeBean extends Bean {
	private Integer id = 0;
	private String name = "";
	private BaugruppeBean vaterBaugruppe = null;
	private HalleBean halleBean = null;

	private ArrayList<BaugruppeArtikelBean> baugruppeArtikelBeans = null;
	private ArrayList<BaugruppeBean> baugruppeBeans = null;
	
	public BaugruppeBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_BAUGRUPPE +  getId();
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

	public ArrayList<BaugruppeArtikelBean> getBaugruppeArtikelBeans() {
		if(baugruppeArtikelBeans==null){
			baugruppeArtikelBeans = new ArrayList<BaugruppeArtikelBean>();
		}
		return baugruppeArtikelBeans;
	}
	
	public ArrayList<BaugruppeBean> getBaugruppeBeans() {
		if(baugruppeBeans==null){
			baugruppeBeans = new ArrayList<BaugruppeBean>();
		}
		return baugruppeBeans ;
	}
	

	public void setBaugruppeArtikelBeans(ArrayList<BaugruppeArtikelBean> baugruppeArtikelBeans) {
		if (baugruppeArtikelBeans==null){
			baugruppeArtikelBeans = new ArrayList<BaugruppeArtikelBean>();
		}
		if (this.baugruppeArtikelBeans != baugruppeArtikelBeans){
			this.baugruppeArtikelBeans = baugruppeArtikelBeans;
			beanIstGeaendert();
		}
	}
	
	public void setBenutzerZugriffsrechtBeans(ArrayList<BaugruppeBean> baugruppeBeans) {
		if (baugruppeBeans==null){
			baugruppeBeans = new ArrayList<BaugruppeBean>();
		}
		if (this.baugruppeBeans != baugruppeBeans){
			this.baugruppeBeans = baugruppeBeans;
			beanIstGeaendert();
		}
	}
	

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BaugruppeBean){
			BaugruppeBean baugruppeBean = (BaugruppeBean) bean;
			if (baugruppeBean==null){
				return false;
			}else{
				return (this.getId().equals(baugruppeBean.getId()));
			}
		}else return false;
    }

	public BaugruppeBean getVaterBaugruppe() {
		if (vaterBaugruppe == null){
			vaterBaugruppe = new BaugruppeBean();
		}
		return vaterBaugruppe;
	}

	public void setVaterBaugruppe(BaugruppeBean vaterBaugruppe) {
		this.vaterBaugruppe = vaterBaugruppe;
	}

	public HalleBean getHalleBean() {
		if (halleBean == null){
			halleBean = new HalleBean();
		}
		return halleBean;
	}

	public  void setHalleBean(HalleBean halleBean) {
		if (halleBean!=this.halleBean){
			this.halleBean = halleBean;
			beanIstGeaendert();
		}
	}

	private static String string150 = "                                                                                                                                                                ";
	private String getCompareString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getVaterBaugruppeNamenListe());
		sb.append(string150);                                                                                                                                                      
		String vater150 = sb.toString().substring(0, 150);
		return vater150 + this.getName();
	}
	
	@Override
	public int compareTo(Bean bean) {
		if(bean==null){
			return -1;
		}else{
			BaugruppeBean baugruppeBean = (BaugruppeBean) bean;
			return this.getCompareString().compareTo(baugruppeBean.getCompareString());
//			BaugruppeBean baugruppeBean = (BaugruppeBean) bean;
//			if (baugruppeBean.getVaterBaugruppe().getId().equals(0)&&this.getVaterBaugruppe().getId().equals(0)){
//				//Zwei Anlagen werden verglichen
//				return this.getVaterBaugruppe().getName().compareTo(baugruppeBean.getVaterBaugruppe().getName());
//			}else{
//				if (!baugruppeBean.getVaterBaugruppe().getId().equals(0)&&!this.getVaterBaugruppe().getId().equals(0)){
//					//zwei Baugruppen werden verglichen
//    				return this.getName().compareTo(baugruppeBean.getName());
//				}else{
//					//Eine Anlage und eine Baugruppe
//					if (baugruppeBean.getVaterBaugruppe().getId().equals(0)){
//						//Anlage ist kleiner als Baugruppe (Vorne in der Liste) 
//						return 1;
//					}else{
//						return -1;
//					}
//				}
//			}
		}
//		return super.compareTo(bean);
	}

	public String getVaterBaugruppeNamenListe() {
		if (getVaterBaugruppe().getId().equals(0)){
			return "";
		}
		String vatersNamenListe = getVaterBaugruppe().getVaterBaugruppeNamenListe();
		if (vatersNamenListe.trim().length()>0){
			vatersNamenListe = vatersNamenListe + "  >>  ";
		}
		return vatersNamenListe + getVaterBaugruppe().getName();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}


	
}
