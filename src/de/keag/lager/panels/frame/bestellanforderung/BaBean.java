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
package de.keag.lager.panels.frame.bestellanforderung;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class BaBean extends Bean{
	private Integer id=0;
	private java.sql.Date erstellungsDatum;
	private AnforderungStatus anforderungStatus=AnforderungStatus.FEHLERHAFT;
	private LhBean lhBean=null;
	private BenutzerBean absenderBenutzerBean=null;
	private BenutzerBean annehmerBenutzerBean=null;
	private String email = "thurner@ke-ag.de";
//	private String email = "thurner@ke-ag.de";
	private ArrayList<BaPosBean> baPosBeans = null;
	
	public BaBean() {
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
		this.id = id;
		beanIstGeaendert();
	}
	public java.sql.Date getErstellungsDatum() {
		if (erstellungsDatum==null){
			erstellungsDatum=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
//			erstellungsDatum.toGMTString();
		}
		return erstellungsDatum;
	}
	public void setErstellungsDatum(java.sql.Date erstellungsDatum) {
		if(this.erstellungsDatum==null){
			this.erstellungsDatum = erstellungsDatum;
		}else{
			if(this.erstellungsDatum.getTime()!=erstellungsDatum.getTime()){
				beanIstGeaendert();
				this.erstellungsDatum = erstellungsDatum;
			}
		}
	}
	public AnforderungStatus getStatus() {
		return anforderungStatus;
	}
	public void setStatus(
			AnforderungStatus status) {
		if(status!=anforderungStatus){
			this.anforderungStatus = status;
			beanIstGeaendert();
		}
	}
	public LhBean getLhBean() {
		if (lhBean==null){
			lhBean = new LhBean();
		}
		return lhBean;
	}
	public void setLhBean(LhBean lhBean) {
		if (lhBean!=this.lhBean){
			this.lhBean = lhBean;
			beanIstGeaendert();
		}
	}
	public BenutzerBean getAbsenderBenutzerBean() {
		if (absenderBenutzerBean == null){
			absenderBenutzerBean = new BenutzerBean();
		}
		return absenderBenutzerBean;
	}
	public void setAbsenderBenutzerBean(BenutzerBean fkBenutzerAbsender) {
		if(fkBenutzerAbsender!=absenderBenutzerBean){
			absenderBenutzerBean = fkBenutzerAbsender;
			beanIstGeaendert();
		}
	}
	public BenutzerBean getAnnehmerBenutzerBean() {
		if (annehmerBenutzerBean == null){
			annehmerBenutzerBean = new BenutzerBean();
		}
		return annehmerBenutzerBean;
	}
	public void setFk_benutzerAnnehmer(BenutzerBean fkBenutzerAnnehmer) {
		if(annehmerBenutzerBean != fkBenutzerAnnehmer){
			annehmerBenutzerBean = fkBenutzerAnnehmer;
			beanIstGeaendert();
		}
	}

	@Override
	public String toString(){
		return "BaBean:"+	getBeanDBStatus()+
		" Erstellungsdatum:" + getErstellungsDatum()+
		" fkBenutzerAbsender:" + getAbsenderBenutzerBean()+
		" fkBenutzerAnnehmer:" + getAnnehmerBenutzerBean()+
		" fkHerstellerLieferant:" + getLhBean();
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
//		if(getLhBean()==null){
//			getFehlerList().add(new Fehler(35));
//		}else{
//			if(getLhBean().getId()==0){
//				getFehlerList().add(new Fehler(35));
//			}
//		}
		return getFehlerList();
	}

	
	public boolean equals(BaBean baBean){
		if (baBean==null){
			return false;
		}else{
			//id' sind gleich und ungleich 0
			return (this.getId().equals(baBean.getId()))&(baBean.getId()!=0); 
		}
	}

	public String getEmail() {
		if(email==null){
			email = "";
		}
		return email;
	}

	public void setEmail(String email) {
		if(email==null){
			email = "";
		}
		if(!this.email.equals(email)){
			this.email = email;
			beanIstGeaendert();
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BaBean){
			BaBean tempBean = (BaBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}

	public ArrayList<BaPosBean> getBaPosBeans() {
		if(baPosBeans==null){
			baPosBeans = new ArrayList<BaPosBean>();
		}
		return baPosBeans;
	}

	public void setBaPosBeans(ArrayList<BaPosBean> baPosBeans) {
		if (getBaPosBeans() != baPosBeans){
			this.baPosBeans = baPosBeans;
			beanIstGeaendert();
		}
		
	}

//	public void addBaPosition(BaPosBean baPosBean) {
//		getBaPosBeanList().add(baPosBean);
//	}
//	public ArrayList<BaPosBean> getBaPosBeanList() {
//		if(baPosBeanList==null){
//			baPosBeanList = new ArrayList<BaPosBean>();
//		}
//		return baPosBeanList;
//	}
//	public void loescheAlleBaPositionen() {
//		getBaPosBeanList().clear();
//	}
}
