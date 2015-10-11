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
package de.keag.lager.panels.frame.bestellung;

public enum BestellungStatus {
	FEHLERHAFT, //Mit Fehler behaftet. 
	OFFEN, //Anforderung ist offen
	ABGESCHLOSSEN, //Anforderung ist abgeschlossen
	GELOESCHT, //Anforderung ist inaktiv
	ALLE; //Diese Option ist für die Auswahlkrieterien gedacht und darf einer Bean so gut wie nie zugeordnet werden.
	
	public static BestellungStatus DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("O"))
			return BestellungStatus.OFFEN;
		else if (dbWert.toUpperCase().equals("L"))
			return BestellungStatus.GELOESCHT;
		else if (dbWert.toUpperCase().equals("E"))
			return BestellungStatus.ABGESCHLOSSEN;
		else if (dbWert.toUpperCase().equals("A"))
			return BestellungStatus.ALLE;
		else
			return BestellungStatus.FEHLERHAFT;
	}
	public static String JavaToDB(BestellungStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == BestellungStatus.OFFEN)
			return "O";
		else if (bestellanforderungsStatus == BestellungStatus.ABGESCHLOSSEN)
			return "E";
		else if (bestellanforderungsStatus == BestellungStatus.ALLE)
			return "A";
		else if (bestellanforderungsStatus == BestellungStatus.GELOESCHT)
			return "L";
		else
			return "F"; //Fehlerhaft

	}
	public static String JavaToView(BestellungStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == BestellungStatus.OFFEN)
			return "Offen";
		else if (bestellanforderungsStatus == BestellungStatus.ABGESCHLOSSEN)
			return "Abgeschlossen";
		else if (bestellanforderungsStatus == BestellungStatus.ALLE)
			return "Alle";
		else if (bestellanforderungsStatus == BestellungStatus.GELOESCHT)
			return "Gelöscht";
		else
			return "Fehlerhaft"; //Fehlerhaft
	}
	@Override
	
	public String toString(){
		return JavaToView(this);
	}
}
