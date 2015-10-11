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

import de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht;

public class BerichtZeileHtml  {

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
//		Integer laenge  = new Integer(0);
//		for(int i = 0; i<getBerichtSpalten().size();i++){
//			laenge = laenge + getBerichtSpalten().get(i).getBreite() + 1; 
//		}
//		StringBuilder sb = new StringBuilder(laenge);
//		for(int i = 0; i<laenge; i++){
//			sb.append(getSymbolFuerHorizontaleLinie());
//		}
		return new String("<tr></tr>") ;
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
//		ergebnisZeile.append("<tr>");
		ergebnisZeile.append("<tr bgcolor=\"#EEEEEE\">");
		for (int i = 0; i < getBerichtSpalten().size(); i++) {
			BerichtSpalte berichtSpalte = getBerichtSpalten().get(i);
//			StringBuilder spaltenName = new StringBuilder("<th bgcolor=\"#EEEEEE\">");
			String th = "<th>";
			if (berichtSpalte.getBreite()>0){
				th = "<th width=\""+ berichtSpalte.getBreite() +"\">";
			}
			StringBuilder spaltenName = new StringBuilder(th);
			if (spaltenZeileNummer == 1) {
				spaltenName.append(new StringBuilder(berichtSpalte.getSpaltenName1()));
			} else {
				if (spaltenZeileNummer == 2) {
					spaltenName.append(new StringBuilder(berichtSpalte
							.getSpaltenName2()));
				} else {
					spaltenName.append(new StringBuilder("Fehler"));
				}
			}
//			if (spaltenName.length() < berichtSpalte.getBreite()) {
//				while (spaltenName.length() < berichtSpalte.getBreite()) {
//					spaltenName.append(" ");
//				}
//			} else {
//				if (spaltenName.length() > berichtSpalte.getBreite()) {
//					spaltenName = spaltenName.delete(berichtSpalte.getBreite(),
//							spaltenName.length());
//				}
//			}
			spaltenName.append("</th>");
			ergebnisZeile = ergebnisZeile.append(spaltenName);
//			ergebnisZeile = ergebnisZeile.append(getSymbolFuerVertikaleLinie());

		}
		ergebnisZeile.append("</tr>");
		return new String(ergebnisZeile);
	}

	public String getDatenZeile(String farbe) {
		if (farbe==null){
//			farbe = "#CCEECC";
			farbe = "#FFFFFF";
		}
		StringBuilder ergebnisZeile = new StringBuilder(0);
//		ergebnisZeile.append("<tr>"); //bgcolor=\"#EEEEEE\"
//		ergebnisZeile.append("<tr bgcolor=\"#CCEECC\">"); 
		ergebnisZeile.append("<tr bgcolor=\""+farbe+"\">"); 
		for(int i = 0; i<getBerichtSpalten().size();i++){
			BerichtSpalte berichtSpalte = getBerichtSpalten().get(i);
//			StringBuilder spaltenInhalt; //pro Spalteninhalt immer wieder füllen
//			StringBuilder spaltenInhalt = new StringBuilder("<th bgcolor=\"#EEEEEE\">");
			String th = "<th>";
			if (berichtSpalte.getBreite()>0){
				th = "<th width=\""+ berichtSpalte.getBreite() +"\">";
			}
			StringBuilder spaltenInhalt = new StringBuilder(th);
			spaltenInhalt.append(new StringBuilder(berichtSpalte.getInhalt()));
			spaltenInhalt = ersetzeUmlaute(spaltenInhalt);
//			if (spaltenInhalt.length() < berichtSpalte.getBreite()){
//				int diff = (berichtSpalte.getBreite() - spaltenInhalt.length()) / 2;  
//				while (spaltenInhalt.length() < berichtSpalte.getBreite()){
//					if (diff>0){
//						diff = diff - 1;
//						spaltenInhalt = new StringBuilder(" ").append(spaltenInhalt);
//					}else{
//						spaltenInhalt.append(" ");
//					}
//				}
//			}else{
//				if (spaltenInhalt.length() > berichtSpalte.getBreite()){
//					spaltenInhalt = spaltenInhalt.delete(berichtSpalte.getBreite(),spaltenInhalt.length());
//				}
//			}
			spaltenInhalt.append("</th>");
			ergebnisZeile = ergebnisZeile.append(spaltenInhalt);
//			ergebnisZeile = ergebnisZeile.append(getSymbolFuerVertikaleLinie());
		}
		ergebnisZeile.append("</tr>");
		return new String(ergebnisZeile) ;
	}

	public static StringBuilder ersetzeUmlaute(StringBuilder spaltenInhalt) {
		return EinzelBericht.ersetzeUmlauteNachHtml(spaltenInhalt);
	}
	
	public static String ersetzeUmlaute(String spaltenInhalt) {
		return EinzelBericht.ersetzeUmlauteNachHtml(new StringBuilder(spaltenInhalt)).toString();
	}
	
	
	
}
