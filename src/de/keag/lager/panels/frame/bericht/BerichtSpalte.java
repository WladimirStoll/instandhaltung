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
package de.keag.lager.panels.frame.bericht;

public class BerichtSpalte {
	private String spaltenName1;
	private String spaltenName2;
	private String spaltenName3;
	private Integer breite;
	private String inhalt;
	
	private BerichtSpalte() {
		super();
	}
	
	public BerichtSpalte(String spaltenName1,String spaltenName2, int spaltenBreite, String splatenInhalt) {
		this(spaltenName1, spaltenName2, "", spaltenBreite,
				splatenInhalt);
	}

	public BerichtSpalte(String spaltenName1,String spaltenName2, String spaltenName3, int spaltenBreite, String splatenInhalt) {
		this();
		setSpaltenName1(spaltenName1);
		setSpaltenName2(spaltenName2);
		setBreite(spaltenBreite);
		setInhalt(splatenInhalt);
	}
	
	
	public String getSpaltenName1() {
		if (spaltenName1==null){
			spaltenName1 = "";
		}
		return spaltenName1;
	}
	public void setSpaltenName1(String spaltenName1) {
		this.spaltenName1 = spaltenName1;
	}
	
	public String getSpaltenName2() {
		if (spaltenName2==null){
			spaltenName2 = "";
		}
		return spaltenName2;
	}
	public void setSpaltenName2(String spaltenName2) {
		this.spaltenName2 = spaltenName2;
	}
	
	public String getSpaltenName3() {
		if (spaltenName3==null){
			spaltenName3 = "";
		}
		return spaltenName3;
	}
	public void setSpaltenName3(String spaltenName3) {
		this.spaltenName3 = spaltenName3;
	}
	
	public Integer getBreite() {
		if (breite == null){
			breite = 5;
		}
		return breite;
	}
	public void setBreite(Integer breite) {
		this.breite = breite;
	}
	
	public String getInhalt() {
		if (inhalt == null){
			inhalt = "Fehler";
		}
		return inhalt;
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
}
