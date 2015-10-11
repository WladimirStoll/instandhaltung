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
package de.keag.lager.panels.frame.unterArtikel.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;

import de.keag.lager.panels.frame.artikel.ArtikelBean;

public class UnterArtikelBean extends Bean{
	private ArtikelBean vaterArtikelBean;
	private ArtikelBean kindArtikelBean;
	private Integer anzahl;
	
	public UnterArtikelBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_UNTER_ARTIKEL + getVaterArtikelBean().getId()*10000000 + getKindArtikelBean().getId();
	}
	
	public Integer getAnzahl() {
		if (anzahl ==null){
			anzahl = 0;
		}
		return anzahl;
	}
	public void setAnzahl(Integer anzahl) {
		if (!this.getAnzahl().equals(anzahl)){
			this.anzahl = anzahl;
			beanIstGeaendert();
		}
	}
	public ArtikelBean getVaterArtikelBean() {
		if (vaterArtikelBean==null){
			vaterArtikelBean = new ArtikelBean();
		}
		return vaterArtikelBean;
	}

	public void setVaterArtikelBean(ArtikelBean vaterArtikelBean) {
		if(!getVaterArtikelBean().equals(vaterArtikelBean)){
			this.vaterArtikelBean = vaterArtikelBean;
			beanIstGeaendert();
		}
	}
	
	public ArtikelBean getKindArtikelBean() {
		if (kindArtikelBean==null){
			kindArtikelBean = new ArtikelBean();
		}
		return kindArtikelBean;
	}

	public void setKindArtikelBean(ArtikelBean kindArtikelBean) {
		if(!getKindArtikelBean().equals(kindArtikelBean)){
			this.kindArtikelBean = kindArtikelBean;
			beanIstGeaendert();
		}
	}	

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getKindArtikelBean().equals(0)){
			getFehlerList().add(new Fehler(124));
		}
		
		if(getVaterArtikelBean().equals(0)){ 
			getFehlerList().add(new Fehler(123));
		}
		
		if(getAnzahl().equals(0)){ 
			getFehlerList().add(new Fehler(125));
		}
		
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof UnterArtikelBean){
			UnterArtikelBean tempBean = (UnterArtikelBean) bean;
			if (tempBean==null){
				return false;
			}else{
				if (this.getVaterArtikelBean().equals(tempBean.getVaterArtikelBean())){
					if (this.getKindArtikelBean().equals(tempBean.getKindArtikelBean())){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
		}else return false;
    }

	@Override
	public String toString() {
		return "Artikel" + getKindArtikelBean().toString() + "("+getVaterArtikelBean().toString()+")";
	}

}
