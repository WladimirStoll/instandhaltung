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
package de.keag.lager.panels.frame.bericht.einzelberichte;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.mysql.jdbc.PreparedStatement;

import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.db.connection.SqlParams;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtZeile;
import de.keag.lager.panels.frame.bericht.BerichtZeileHtml;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;

abstract public class EinzelBericht implements IEinzelBericht {
	
	protected BerichtZeile berichtZeile;
	private BerichtZeileHtml berichtZeileHtml;
	
	private Integer schritt = 0;
	
	private Integer id ;
	private Berichtart berichtart;
	private Berichttyp berichttyp;
	private Integer berichttypId;

	private SqlParams sqlParams;
	private Map<String, String> druckParameter;

	public EinzelBericht(Map<String, String> druckParameter) {
		super();
		this.druckParameter = druckParameter;
	}
	
	private Integer getSchritt() {
		return schritt;
	}

	private void setSchritt(int schritt) {
		this.schritt = schritt;
	}
	
	protected SqlParams getSqlParams() {
		if (sqlParams==null){
			sqlParams = new SqlParams();
		}
		return sqlParams;
	}
	
	
	@Override
	public void erzeugeBerichtDbSatz(Berichttyp berichttyp, Berichtart berichtart, Integer berichttypId) throws SQLException, LagerException {
		if (!schritt.equals(0)){
			new LagerException("Fehler: Schritt muss gleich 0 sein.");
		}else{
			setSchritt(getSchritt()+1);
		}
		setBerichtart(berichtart);
		setBerichttyp(berichttyp);
		setBerichttypId(berichttypId);
	}

	@Override
	public void uebernehmeDruckDaten() throws SQLException, LagerException, Exception {
		if (!schritt.equals(1)){
			new LagerException("Fehler: Schritt muss gleich 1 sein.");
		}else{
			setSchritt(getSchritt()+1);
		}
	}

	@Override
	public void verarbeiteBericht() throws SQLException, LagerException, Exception{
		if (!schritt.equals(2)){
			new LagerException("Fehler: Schritt muss gleich 1 sein.");
		}else{
			setSchritt(getSchritt()+1);
		}
	}

	final protected Integer getId() throws IdLagerException, SQLException {
		if (id == null || id.equals(0)){
			id = LagerConnection.getOneInstance().getID(Konstanten.ID_DB_BERICHT);
		}
		return id;
	}

	protected Berichtart getBerichtart() {
		return berichtart;
	}

	protected void setBerichtart(Berichtart berichtart) {
		this.berichtart = berichtart;
	}

	protected Berichttyp getBerichttyp() {
		return berichttyp;
	}

	protected void setBerichttyp(Berichttyp berichttyp) {
		this.berichttyp = berichttyp;
	}

	public Integer getBerichttypId() {
		return berichttypId;
	}

	private void setBerichttypId(Integer berichttypId) {
		this.berichttypId = berichttypId;
	}

	protected BerichtZeile getBerichtZeile() {
		if (berichtZeile==null){
			berichtZeile = new BerichtZeile();
		}
		return berichtZeile;
	}
	
	protected BerichtZeileHtml getBerichtZeileHtml() {
		if (berichtZeileHtml==null){
			berichtZeileHtml = new BerichtZeileHtml();
		}
		return berichtZeileHtml;
	}
	

	public static String textToHtml(String text, String kopfText){
		String htmlText = "Kein Inhalt!!";
		
		if (text != null) {
			Pattern p = Pattern.compile("\n");
			Matcher m = p.matcher(text);
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
				m.appendReplacement(sb, "<br>");
			}
			m.appendTail(sb);
			System.out.println(sb.toString());
			StringBuilder stringBuilder = EinzelBericht.ersetzeUmlauteNachHtml(new StringBuilder(sb.toString()));
			htmlText = stringBuilder.toString(); // text;
		}
		
//		htmlText = htmlText.replaceAll("\n", "<br>");
//		if (text!=null){
////			htmlText = "<html><head>"+kopfText+"</head><body>"+text+"</body></html>";
//			htmlText = kopfText+"<br>"+text;
//		}
		return htmlText;
	}
	
	public static StringBuilder ersetzeUmlauteNachHtml(StringBuilder textHtml) {
		String s;
//			s = new String(
//						new String(spaltenInhalt).getBytes("UTF-8"),
//						"ISO-8859-1");
//			String s = new String(spaltenInhalt);
			s = new String(textHtml);
//			if (s.matches("/[üäöß]/")) {
			
			for (int i = 0; i<s.length();i++){
				
				if (s.substring(i, i+1).equals("ü")){
					s = s.replaceAll("ü", "&uuml;");
				}
				if (s.substring(i, i+1).equals("ä")){
					s = s.replaceAll("ä", "&auml;");
				}
				if (s.substring(i, i+1).equals("ö")){
					s = s.replaceAll("ö", "&ouml;");
				}
				
				if (s.substring(i, i+1).equals("Ü")){
					s = s.replaceAll("Ü", "&Uuml;");
				}
				if (s.substring(i, i+1).equals("Ä")){
					s = s.replaceAll("Ä", "	&Auml;");
				}
				if (s.substring(i, i+1).equals("Ö")){
					s = s.replaceAll("Ö", "	&Ouml;");
				}
				
				if (s.substring(i, i+1).equals("ß")){
					s = s.replaceAll("ß", "&szlig;");
				}
				
				if (s.substring(i, i+1).equals("€")){
					s = s.replaceAll("€", "&euro;");
				}
				
//				if (s.substring(i, i+1).equals(" ")){
//					s = s.replaceAll(" ", "&nbsp;");
//				}
				
				
			}
		return new StringBuilder(s);
	}
	
	public static String ersetzeUmlauteNachHtml(String textHtml) {
		return ersetzeUmlauteNachHtml(new StringBuilder(textHtml)).toString();
	}

	protected Map<String,String> getDruckParameter() {
	  if (druckParameter == null){
		  druckParameter =  new HashMap<String,String>();
	  }
	return druckParameter;}
	
	
	
}
