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
package de.keag.lager.panels.frame.wartungsart.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;

public class WartungsArtBean extends Bean {
	
	public enum WartungsArtIntervallFaehigEnum{
		UNBEKANNT, JA,	NEIN;
		
		public static String _UNKNOWN_GUI = "-";
		public static String _JA_GUI = "Intervallfähig";
		public static String _NEIN_GUI = "Ohne Intervall";
		
		public static String _UNKNOWN_DB = "U";
		public static String _JA_DB = "J";
		public static String _NEIN_DB = "N";
		
		public static WartungsArtIntervallFaehigEnum DbToJava(String dbWert){
			if (dbWert==null){
				return WartungsArtIntervallFaehigEnum.UNBEKANNT;
			}
			if (dbWert.toUpperCase().equals(_JA_DB))
				return WartungsArtIntervallFaehigEnum.JA;
			else if (dbWert.toUpperCase().equals(_NEIN_DB))
				return WartungsArtIntervallFaehigEnum.NEIN;
			else
				return WartungsArtIntervallFaehigEnum.UNBEKANNT;
		}
		
		public static String JavaToDB(WartungsArtIntervallFaehigEnum value){
			if (value == JA)
				return _JA_DB;
			if (value == NEIN)
				return _NEIN_DB;
			else
				return _UNKNOWN_DB; //Fehlerhaft

		}
		
		public static String JavaToView(WartungsArtIntervallFaehigEnum value){
			if (value == JA)
				return _JA_GUI;
			else if (value == NEIN)
				return _NEIN_GUI;
			else
				return _UNKNOWN_GUI; //Fehlerhaft
		}
		
		public String toString(){
			return JavaToView(this);
		}
		public String JavaToDB() {
			return WartungsArtIntervallFaehigEnum.JavaToDB(this);
		}
		
	}
	
	private Integer id = 0;
	private String name = ""; 
	private WartungsArtIntervallFaehigEnum intervallFaehig;
	
	public WartungsArtBean() {
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
		if (bean instanceof WartungsArtBean){
			WartungsArtBean wartungsArtBean = (WartungsArtBean) bean;
			if (wartungsArtBean==null){
				return false;
			}else{
				return (this.getId() == wartungsArtBean.getId());
			}
		}else return false;
    }
	
	@Override
	public String toString(){
		return getName();
	}

	public void setIntervallFaehig(WartungsArtIntervallFaehigEnum intervallFaehig) {
		if (!this.getIntervallFaehig().equals(intervallFaehig)){
			this.intervallFaehig = intervallFaehig;
			beanIstGeaendert();
		}
	}

	public WartungsArtIntervallFaehigEnum getIntervallFaehig() {
		if (this.intervallFaehig==null){
				this.intervallFaehig = WartungsArtIntervallFaehigEnum.UNBEKANNT;
		}
		return intervallFaehig;
	}
	
	
}
