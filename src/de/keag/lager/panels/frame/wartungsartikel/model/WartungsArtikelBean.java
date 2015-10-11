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
package de.keag.lager.panels.frame.wartungsartikel.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

public class WartungsArtikelBean extends Bean {
	
	private Integer id; 
	private WartungBean fk_wartung; 
	private ArtikelBean fk_artikel; 
	private Integer menge;
	
	
	public WartungsArtikelBean() {
		super();
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungsArtikelBean){
			WartungsArtikelBean wartungsArtBean = (WartungsArtikelBean) bean;
			if (wartungsArtBean==null){
				return false;
			}else{
				return (this.getId() == wartungsArtBean.getId());
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
		
		if(getId().equals(0)
		){ 
			getFehlerList().add(new Fehler(132));
		}
		
		if(getFk_wartung().equals(0)
		){ 
			getFehlerList().add(new Fehler(133));
		}
		
		if(getFk_artikel().equals(0)
		){ 
			getFehlerList().add(new Fehler(134));
		}
		
		if(getMenge().equals(0)
		){ 
			getFehlerList().add(new Fehler(135));
		}
		
		
//		if(getBaugruppeId().equals(0)
//		){ 
//			getFehlerList().add(new Fehler(131));
//		}
		
		
		
		
//		if(getBeschreibung().trim().equals("")
//		){ //trim()=abschneiden der leerzeichen
//			getFehlerList().add(new Fehler(44));
//		}
		
		
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
			id = new Integer(0);
		}
		return id;
	}

	public WartungBean getFk_wartung() {
	if (this.fk_wartung==null){
			this.fk_wartung = new WartungBean();
	}
	return fk_wartung;
	}
	

	public void setFk_wartung(WartungBean fkWartung) {
		if (!this.getFk_wartung().equals(fkWartung)){
			this.fk_wartung = fkWartung;
			beanIstGeaendert();
		}
	}

	public ArtikelBean getFk_artikel() {
	if (this.fk_artikel==null){
			this.fk_artikel = new ArtikelBean();
	}
	return fk_artikel;}
	

	public void setFk_artikel(ArtikelBean fkArtikel) {
		if (!this.getFk_artikel().equals(fkArtikel)){
			this.fk_artikel = fkArtikel;
			beanIstGeaendert();
		}
	}

	public Integer getMenge() {
	if (this.menge==null){
			this.menge = new Integer(0);
	}
	return menge;}
	

	public void setMenge(Integer menge) {
		if (!this.getMenge().equals(menge)){
			this.menge = menge;
			beanIstGeaendert();
		}
	}

}
