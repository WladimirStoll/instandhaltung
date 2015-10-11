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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class BerichtZeile  {

	private String symbolFuerHorizontaleLinie;
	private String symbolFuerVertikaleLinie;
	private Integer spaltenHoehe;
	
	private ArrayList<BerichtSpalte> berichtSpalten;

	public ArrayList<BerichtSpalte> getBerichtSpalten() {
		if (berichtSpalten==null){
			berichtSpalten = new ArrayList<BerichtSpalte>(); 
		}
		return berichtSpalten;
	}

	public void setSymbolFuerHorizontaleLinie(String symbol) {
		symbolFuerHorizontaleLinie = symbol;
		
	}

	public void setSymbolFuerVertikaleLinie(String symbol) {
		symbolFuerVertikaleLinie = symbol;
	}

	private String getSymbolFuerHorizontaleLinie() {
		if (symbolFuerHorizontaleLinie==null){
			symbolFuerHorizontaleLinie = "-";
		}
		return symbolFuerHorizontaleLinie;
	}

	private String getSymbolFuerVertikaleLinie() {
		if (symbolFuerVertikaleLinie==null){
			symbolFuerVertikaleLinie = "|";
		}
		return symbolFuerVertikaleLinie;
	}

	public String getDurchgehendeLinie() {
		Integer laenge  = new Integer(0);
		for(int i = 0; i<getBerichtSpalten().size();i++){
			laenge = laenge + getBerichtSpalten().get(i).getBreite() + 1; 
		}
		StringBuilder sb = new StringBuilder(laenge);
		for(int i = 0; i<laenge; i++){
			sb.append(getSymbolFuerHorizontaleLinie());
		}
		return new String(sb) ;
	}

	public void setSpaltenHoehe(Integer spaltenHoehe) {
		this.spaltenHoehe = spaltenHoehe;
		
	}

	private Integer getSpaltenHoehe() {
		if (spaltenHoehe==null){
			spaltenHoehe = 1;
		}
		return spaltenHoehe;
	}

	public String getSpaltenNamenZeile(int spaltenZeileNummer) {
		StringBuilder ergebnisZeile = new StringBuilder(0);
		for(int i = 0; i<getBerichtSpalten().size();i++){
			BerichtSpalte berichtSpalte = getBerichtSpalten().get(i);
			StringBuilder spaltenName;
			if (spaltenZeileNummer == 1){
				spaltenName = new StringBuilder(berichtSpalte.getSpaltenName1());	
			}else{
				if(spaltenZeileNummer == 2){
					spaltenName = new StringBuilder(berichtSpalte.getSpaltenName2());	
				}else{
					if(spaltenZeileNummer == 3){
						spaltenName = new StringBuilder(berichtSpalte.getSpaltenName3());	
					}else{
						spaltenName = new StringBuilder("Fehler:"+this.getClass().getName()); 
					} 
				} 
			}
			if (spaltenName.length() < berichtSpalte.getBreite()){
				while (spaltenName.length() < berichtSpalte.getBreite()){
					spaltenName.append(" ");
				}
			}else{
				if (spaltenName.length() > berichtSpalte.getBreite()){
					spaltenName = spaltenName.delete(berichtSpalte.getBreite(),spaltenName.length());
				}
			}
			ergebnisZeile = ergebnisZeile.append(spaltenName);
			ergebnisZeile = ergebnisZeile.append(getSymbolFuerVertikaleLinie());
			
		}
		return new String(ergebnisZeile) ;
	}

	public String getDatenZeile() {
		StringBuilder ergebnisZeile = new StringBuilder(0);
		for(int i = 0; i<getBerichtSpalten().size();i++){
			BerichtSpalte berichtSpalte = getBerichtSpalten().get(i);
			StringBuilder spaltenInhalt; //pro Spalteninhalt immer wieder füllen
			spaltenInhalt = new StringBuilder(berichtSpalte.getInhalt());
			spaltenInhalt = ersetzeUmlaute(spaltenInhalt);
			if (spaltenInhalt.length() < berichtSpalte.getBreite()){
				int diff = (berichtSpalte.getBreite() - spaltenInhalt.length()) / 2;  
				while (spaltenInhalt.length() < berichtSpalte.getBreite()){
					if (diff>0){
						diff = diff - 1;
						spaltenInhalt = new StringBuilder(" ").append(spaltenInhalt);
					}else{
						spaltenInhalt.append(" ");
					}
				}
			}else{
				if (spaltenInhalt.length() > berichtSpalte.getBreite()){
					spaltenInhalt = spaltenInhalt.delete(berichtSpalte.getBreite(),spaltenInhalt.length());
				}
			}
			ergebnisZeile = ergebnisZeile.append(spaltenInhalt);
			ergebnisZeile = ergebnisZeile.append(getSymbolFuerVertikaleLinie());
		}
		return new String(ergebnisZeile) ;
	}

	public static StringBuilder ersetzeUmlaute(StringBuilder spaltenInhalt) {
		String s;
//			s = new String(
//						new String(spaltenInhalt).getBytes("UTF-8"),
//						"ISO-8859-1");
//			String s = new String(spaltenInhalt);
			s = new String(spaltenInhalt);
//			if (s.matches("/[üäöß]/")) {
			
			for (int i = 0; i<s.length();i++){
				
				if (s.substring(i, i+1).equals("ü")){
					s = s.replaceAll("ü", "ue");
				}
				if (s.substring(i, i+1).equals("ä")){
					s = s.replaceAll("ä", "ae");
				}
				if (s.substring(i, i+1).equals("ö")){
					s = s.replaceAll("ö", "oe");
				}
				
				if (s.substring(i, i+1).equals("Ü")){
					s = s.replaceAll("Ü", "Ue");
				}
				if (s.substring(i, i+1).equals("Ä")){
					s = s.replaceAll("Ä", "Ae");
				}
				if (s.substring(i, i+1).equals("Ö")){
					s = s.replaceAll("Ö", "Oe");
				}
				
				if (s.substring(i, i+1).equals("ß")){
					s = s.replaceAll("ß", "ss");
				}
				
			}
		return new StringBuilder(s);
	}
	
	public static String ersetzeUmlaute(String spaltenInhalt) {
		return ersetzeUmlaute(new StringBuilder(spaltenInhalt)).toString();
	}
	
	
	
}
