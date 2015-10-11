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

public enum Berichttyp {
	FEHLERHAFT, //Mit Fehler behaftet. 
	BENUTZER, 
	ANLAGE, 
	ANFORDERUNG,
	BESTELLUNG,
	INVENTUR_LISTE_HALLE,
	INVENTUR_LISTE_ZEILE,
	INVENTUR_LISTE_SAEULE,
	MINDEST_BESTANDS_LISTE,
	WARTUNG,
	ALLE;
	
	//Achtung! Maximal 20 ZEichen
	final private static String _INVENTUR_LISTE_SAEULE = "INVENTUR_LISTE_SAULE";
	final private static String _MINDEST_BESTANDS_LISTE = "MINDEST_BESTAND_LST";
	
	public static Berichttyp DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("BENUTZER"))
			return Berichttyp.BENUTZER;
		if (dbWert.toUpperCase().equals("WARTUNG"))
			return Berichttyp.WARTUNG;
		else if (dbWert.toUpperCase().equals("ANFORDERUNG"))
			return Berichttyp.ANFORDERUNG;
		else if (dbWert.toUpperCase().equals("BESTELLUNG"))
			return Berichttyp.BESTELLUNG;
		else if (dbWert.toUpperCase().equals("ANLAGE"))
			return Berichttyp.ANLAGE;
		else if (dbWert.toUpperCase().equals("INVENTUR_LISTE_HALLE"))
			return Berichttyp.INVENTUR_LISTE_HALLE;
		else if (dbWert.toUpperCase().equals("INVENTUR_LISTE_ZEILE"))
			return Berichttyp.INVENTUR_LISTE_ZEILE;
		else if (dbWert.toUpperCase().equals(_INVENTUR_LISTE_SAEULE))
			return Berichttyp.INVENTUR_LISTE_SAEULE;
		else if (dbWert.toUpperCase().equals(_MINDEST_BESTANDS_LISTE))
			return Berichttyp.MINDEST_BESTANDS_LISTE;
		else if (dbWert.toUpperCase().equals("ALLE"))
			return Berichttyp.ALLE;
		else
			return Berichttyp.FEHLERHAFT;
	}
	public static String JavaToDB(Berichttyp bestellanforderungsStatus){
		if (bestellanforderungsStatus == Berichttyp.BENUTZER)
			return "BENUTZER";
		else if (bestellanforderungsStatus == Berichttyp.ANLAGE)
			return "ANLAGE";
		else if (bestellanforderungsStatus == Berichttyp.WARTUNG)
			return "WARTUNG";
		else if (bestellanforderungsStatus == Berichttyp.ANFORDERUNG)
			return "ANFORDERUNG";
		else if (bestellanforderungsStatus == Berichttyp.BESTELLUNG)
			return "BESTELLUNG";
		else if (bestellanforderungsStatus == Berichttyp.INVENTUR_LISTE_HALLE)
			return "INVENTUR_LISTE_HALLE";
		else if (bestellanforderungsStatus == Berichttyp.INVENTUR_LISTE_ZEILE)
			return "INVENTUR_LISTE_ZEILE";
		else if (bestellanforderungsStatus == Berichttyp.INVENTUR_LISTE_SAEULE)
			return _INVENTUR_LISTE_SAEULE;
		else if (bestellanforderungsStatus == Berichttyp.MINDEST_BESTANDS_LISTE)
			return _MINDEST_BESTANDS_LISTE;
		else if (bestellanforderungsStatus == Berichttyp.ALLE)
			return "ALLE";
		else
			return "Fehlerhaft"; //Fehlerhaft

	}
	
	public static String JavaToView(Berichttyp berichtTyp){
		if (berichtTyp == Berichttyp.BENUTZER)
			return "Benutzer";
		else if (berichtTyp == Berichttyp.ANLAGE)
			return "Anlage";
		else if (berichtTyp == Berichttyp.ANFORDERUNG)
			return "Anforderung";
		else if (berichtTyp == Berichttyp.BESTELLUNG)
			return "Bestellung";
		else if (berichtTyp == Berichttyp.INVENTUR_LISTE_HALLE)
			return "Inventur-Liste, Halle";
		else if (berichtTyp == Berichttyp.INVENTUR_LISTE_ZEILE)
			return "Inventur-Liste, Zeile";
		else if (berichtTyp == Berichttyp.INVENTUR_LISTE_SAEULE)
			return "Inventur-Liste, Säule";
		else if (berichtTyp == Berichttyp.MINDEST_BESTANDS_LISTE)
			return "Mindestbestandsmenge";
		else if (berichtTyp == Berichttyp.ALLE)
			return "Alle";
		else
			return "Fehlerhaft"; //Fehlerhaft
	}
	
	public String toString(){
		return JavaToView(this);
	}
	public String JavaToDB() {
		return Berichttyp.JavaToDB(this);
	}
}
