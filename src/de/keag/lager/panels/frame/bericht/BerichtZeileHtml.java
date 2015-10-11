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
