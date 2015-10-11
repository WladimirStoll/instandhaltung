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
package de.keag.lager.panels.frame.wartungsmitarbeiter.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;

public class WartungsMitarbeiterBean extends Bean {
	
	 
	private Integer id; 
	private WartungBean fk_wartung;  
	private BenutzerBean fk_benutzer;  
	private Double stunden; 
	private LhBean fk_herstellerlieferant;  
	private String bemerkung; 
	
	public WartungsMitarbeiterBean() {
		super();
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungsMitarbeiterBean){
			WartungsMitarbeiterBean wartungsMitarbeiterBean = (WartungsMitarbeiterBean) bean;
			if (wartungsMitarbeiterBean==null){
				return false;
			}else{
				return (this.getId() == wartungsMitarbeiterBean.getId());
			}
		}else return false;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();

		if (getId().equals(0)) {
			getFehlerList().add(new Fehler(136));
		}

		if (getFk_benutzer().equals(0)&&getFk_herstellerlieferant().equals(0)) {
			getFehlerList().add(new Fehler(137));
		}

		if (getStunden().equals(0)) {
			getFehlerList().add(new Fehler(138));
		}

		return getFehlerList();
	}

	public void setId(Integer id) {
		if (!this.getId().equals(id)){
			this.id = id;
			beanIstGeaendert();
		}
	}

	public Integer getId() {
		if (this.id==null){
				this.id = new Integer(0);
		}
		return id;
	}

	public WartungBean getFk_wartung() {
	if (this.fk_wartung==null){
			this.fk_wartung = new WartungBean();
	}
	return fk_wartung;}
	

	public void setFk_wartung(WartungBean fkWartung) {
		if (!this.getFk_wartung().equals(fkWartung)){
			fk_wartung = fkWartung;
			beanIstGeaendert();
		}
	}

	public BenutzerBean getFk_benutzer() {
	if (this.fk_benutzer==null){
			this.fk_benutzer = new BenutzerBean();
	}
	return fk_benutzer;}
	

	public void setFk_benutzer(BenutzerBean fkBenutzer) {
		if (!this.getFk_benutzer().equals(fkBenutzer)){
			fk_benutzer = fkBenutzer;
			beanIstGeaendert();
		}
	}

	public Double getStunden() {
	if (this.stunden==null){
			this.stunden = new Double(0D);
	}
	return stunden;}
	

	public void setStunden(Double stunden) {
		if (!this.getStunden().equals(stunden)){
			this.stunden = stunden;
			beanIstGeaendert();
		}
	}

	public LhBean getFk_herstellerlieferant() {
	if (this.fk_herstellerlieferant==null){
			this.fk_herstellerlieferant = new LhBean();
	}
	return fk_herstellerlieferant;}
	

	public void setFk_herstellerlieferant(LhBean fkHerstellerlieferant) {
		if (!this.getFk_herstellerlieferant().equals(fkHerstellerlieferant)){
			fk_herstellerlieferant = fkHerstellerlieferant;
			beanIstGeaendert();
		}
	}

	public String getBemerkung() {
	if (this.bemerkung==null){
			this.bemerkung = new String();
	}
	return bemerkung;}
	

	public void setBemerkung(String bemerkung) {
		if (!this.getBemerkung().equals(bemerkung)){
			this.bemerkung = bemerkung;
			beanIstGeaendert();
		}
		
	}
	
	@Override
	public String toString() {
		return this.getFk_benutzer().toString().trim() + " " +getFk_herstellerlieferant().toString().trim();
	}

}
