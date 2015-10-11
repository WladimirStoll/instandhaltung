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
package de.keag.lager.panels.frame.entnahme.model;

public enum EntnahmeStatus {
	FEHLERHAFT, //Mit Fehler behaftet. 
	OFFEN, //Entnahme ist offen
	BESTAETIGT, //Entnahme ist abgeschlossen
	GELOESCHT, //Entnahme ist inaktiv
	ALLE; //Diese Option ist für die Auswahlkrieterien gedacht und darf einer Bean so gut wie nie zugeordnet werden.
	
	public static EntnahmeStatus DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("O"))
			return EntnahmeStatus.OFFEN;
		else if (dbWert.toUpperCase().equals("L"))
			return EntnahmeStatus.GELOESCHT;
		else if (dbWert.toUpperCase().equals("B"))
			return EntnahmeStatus.BESTAETIGT;
		else if (dbWert.toUpperCase().equals("A"))
			return EntnahmeStatus.ALLE;
		else
			return EntnahmeStatus.FEHLERHAFT;
	}
	public static String JavaToDB(EntnahmeStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == EntnahmeStatus.OFFEN)
			return "O";
		else if (bestellanforderungsStatus == EntnahmeStatus.BESTAETIGT)
			return "B";
		else if (bestellanforderungsStatus == EntnahmeStatus.ALLE)
			return "A";
		else if (bestellanforderungsStatus == EntnahmeStatus.GELOESCHT)
			return "L";
		else
			return "F"; //Fehlerhaft

	}
	public static String JavaToView(EntnahmeStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == EntnahmeStatus.OFFEN)
			return "Offen";
		else if (bestellanforderungsStatus == EntnahmeStatus.BESTAETIGT)
			return "Bestätigt";
		else if (bestellanforderungsStatus == EntnahmeStatus.ALLE)
			return "Alle";
		else if (bestellanforderungsStatus == EntnahmeStatus.GELOESCHT)
			return "Gelöscht";
		else
			return "Fehlerhaft"; //Fehlerhaft
	}
	@Override
	
	public String toString(){
		return JavaToView(this);
	}
}
