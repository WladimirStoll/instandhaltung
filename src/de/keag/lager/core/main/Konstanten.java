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
package de.keag.lager.core.main;

import java.awt.Color;
import java.awt.SystemColor;


public class Konstanten {
	public static final String UEBERSCHRIFT_ANWENDUNG = "Instandhaltung";
	public static final String LOGIN = "Anmeldung";
	public static final String SLASH = " / ";
	public static final String MENUE = "Menü";
	public static final String UEBERSCHRIFT_ENTNAHME = "E N T N A H M E";
	public static final String UEBERSCHRIFT_EINLAGERUNG = "E I N L A G E R U N G";
	public static final String UEBERSCHRIFT_BESTELLANFORDERUNGSLISTE = "Bestellanforderung";
	public static final String UEBERSCHRIFT_BESTELLUNG = "Bestellung";
	public static final String UEBERSCHRIFT_DRUCK = "Drucken / Verschicken";
	public static final String UEBERSCHRIFT_BENUTZER = "Benutzer";
	public static final String UEBERSCHRIFT_LIEFERANT_HERSTELLER = "Lieferant/Hersteller";
	public static final String UEBERSCHRIFT_BAUGRUPPE = "Anlage";
	public static final String UEBERSCHRIFT_HALLE = "Halle";
	public static final String UEBERSCHRIFT_ARTIKEL = "Artikel";
	public static final String UEBERSCHRIFT_KATALOG = "Katalog";
	public static final String UEBERSCHRIFT_WARTUNG = "Wartung";
	public static final String UEBERSCHRIFT_IMPRESSUM = "Lizenz";
		
	public static final String FORMAT_DATUM_ZEIT = "dd.MM.yyyy HH:mm";
	public static final String FORMAT_DATUM = "dd.MM.yyyy";
	public static final String FORMAT_ZEIT = "HH:mm"; //24 Stunden - Anzeige
	public static final String FORMAT_ZEIT_MIT_SEKUNDEN = "HH:mm:ss"; //24 Stunden - Anzeige
	public static final String BUTTON_NEU="Neu";
	public static final String BUTTON_SPEICHERN = "Speichern";
	public static final String BUTTON_ABBRECHEN="Abbrechen";
	public static final String BUTTON_DRUCKEN="Drucken";
	public static final String BUTTON_LOESCHEN="Löschen";
	public static final String BUTTON_DRUCKEN_FRAME = "Ansicht drucken";
		
	
	public static final String ID_DB_ENTNAHME = "EntnahmeID";
	public static final String ID_DB_ENTNAHME_POS = "EntnahmenspositionPosID";
	public static final String ID_DB_EINLAGERUNG_POS = "EinlagerungsPosID";
	public static final String ID_DB_BESTELLUNG = "BestellungID";
	public static final String ID_DB_BESTELLUNG_POS = "BestellungPosID";
	public static final String ID_DB_BESTELLANFORDERUNG = "BestellAnforderungID";
	public static final String ID_DB_BESTELLANFORDERUNG_POS = "BestellAnforderungPosID";
	public static final String ID_DB_BENUTZER = "BenutzerID";
	public static final String ID_DB_LIEFERANT_HERSTELLER = "Lieferant/HerstellerID";
	public static final String ID_DB_BAUGRUPPE = "BaugruppeID";
	public static final String ID_DB_BERICHT = "BerichtID";
	public static final String ID_DB_ETAGE = "EtageID";
	public static final String ID_DB_HALLE = "HalleID";
	public static final String ID_DB_ZEILE = "ZeileID";
	public static final String ID_DB_SAEULE = "SaeuleID";
	public static final String ID_DB_EBENE = "EbeneID";
	public static final String ID_DB_POSITION = "PositionID";
	public static final String ID_DB_BESTANDSPACKSTUECK = "BestandsID";
	public static final String ID_DB_ARTIKEL = "ArtikelID";
	public static final String ID_DB_KATALOG = "KatalogID";
	public static final String ID_DB_WARTUNG = "WartungID";
	public static final String ID_DB_WARTUNG_MITARBEITER = "WartungMitarbeiterID";
	public static final String ID_DB_WARTUNG_ARTIKEL = "WartungArtikelID";

	public static final String VERZEICHNIS_IMAGES = "images/";
	public static final String ICON_ENTNAHME = "package.png";
	public static final String ICON_BESTELLANFORDERUNG = "package.png"; 
	public static final String ICON_BERICHT = "report.png"; 
	public static final String ICON_BENUTZER = "user.png"; 
	public static final String ICON_HALLE = "application_home.png"; 
	public static final String ICON_ETAGE = "etage.png"; 
	public static final String ICON_ZEILE = "zeile.png"; 
	public static final String ICON_SAEULE = "application_side_list.png"; 
	public static final String ICON_EBENE = "application_tile_vertical.png"; 
	public static final String ICON_POSITION = "application_view_tile.png"; 
	public static final String ICON_ABTEILUNG = "group.png"; 
	public static final String ICON_LIEFERANT_HERSTELLER = "lorry.png"; 
	public static final String ICON_LIEFERANT_HERSTELLER_KATALOG = "book_open.png"; 
	public static final String ICON_BAUGRUPPE = "chart_organisation.png";
	public static final String ICON_ARTIKEL = "cog.png";
	public static final String ICON_BESTANDSPACKSTUECK = "cog_blau.png";
	public static final String ICON_ENTNAHMENSPOSITION = "page.png";
	public static final String ICON_EINLAGERUNGPOSITION = "page.png";
	public static final String ICON_BESTELLANFORDERUNGPOSITION = "page.png";
	public static final String ICON_RECHT = "award_star_silver_1.png";
	public static final String ICON_FEHLER = "error.png";
	public static final String ICON_BEAN_STATUS_FEHLERHAFT = "database_error.png";
	public static final String ICON_BEAN_STATUS_INSERT = "database_add.png";
	public static final String ICON_BEAN_STATUS_INSERT_DELETE = "database_add.png";
	public static final String ICON_BEAN_STATUS_DELETE = "database_delete.png";
	public static final String ICON_BEAN_STATUS_UPDATE = "database_edit.png";
	public static final String ICON_BEAN_STATUS_SELECT = "database.png"	;
	public static final String ICON_MENU_IMAGE = "zahnrad.jpg"	;
	public static final String ICON_UNTERARTIKEL = "cog.png";
	public static final String ICON_WARTUNG = "wrench.png";
	
	
	public static final Color COLOR_BACKGROUND_TEXTFIELD_ERROR = new Color(255, 200, 200);
	public static final Color COLOR_BACKGROUND_TEXTFIELD_DEFAULT = Color.white;
	public static final Color COLOR_LINEBORDER_AKTIV = SystemColor.blue;
	public static final Color COLOR_LINEBORDER_NICHT_AKTIV = SystemColor.controlShadow;
	public static final int COLUMN_COUNT_BIG = 999999999;
	public static final String SQL_ERROR_bestellanforderungsposition_bestellanforderung_artikel = "bestellanforderungsposition_bestellanforderung_artikel";
	public static final String SQL_ERROR_fk_abteilung_cannot_be_null = "Column 'fk_abteilung' cannot be null";
	public static final String SQL_ERROR_fk_field_cannot_be_null = "fk-Feld darf nicht null sein";
	public static final String SQL_ERROR_Duplicate_entry = "Doppelter Schluessel";
	public static final String SQL_ERROR_bestellungsposition_bestellung_artikel = "bestellungsposition_bestellung_artikel";
	public static final String SQL_ERROR_DOPPELTER_SCHLUESSEL = "Duplicate entry";
	public static final String SQL_ERROR_benutzer_loginName_eindeutig = "benutzer_loginName_eindeutig";
	
	public static final String REPORT_BA_001_JRXML = "./eigeneReports/bestellanforderung001.jrxml";
	public static final String REPORT_BA_001_JASPER = "./eigeneReports/bestellanforderung001.jasper";
	public static final String REPORT_BA_001_PDF = "./eigeneReports/bestellanforderung001.pdf";
	public static final String REPORT_BA_001_HTML = "./eigeneReports/bestellanforderung001.html";
	public static final String REPORT_BA_001_XML = "./eigeneReports/bestellanforderung001.xml";
	public static final String REPORT_INVENTUR_LISTE_01_JRXML = "./eigeneReports/InventurListen/InventurListe01.jrxml";
	public static final String REPORT_INVENTUR_LISTE_01_JASPER = "./eigeneReports/InventurListen/InventurListe01.jasper";
	public static final String REPORT_INVENTUR_LISTE_01_PDF = "./eigeneReports/InventurListen/InventurListe01.pdf";
	public static final String REPORT_INVENTUR_LISTE_01_HTML = "./eigeneReports/InventurListen/InventurListe01.html";
	public static final String REPORT_INVENTUR_LISTE_01_XML = "./eigeneReports/InventurListen/InventurListe01.xml";
	public static final String REPORT_INVENTUR_MIT_MENGEN = "REPORT_INVENTUR_MIT_MENGEN";
	public static final String REPORT_INVENTUR_MIT_MENGEN_JA = "REPORT_INVENTUR_MIT_MENGEN_JA";
	public static final String REPORT_INVENTUR_MIT_MENGEN_NEIN = "REPORT_INVENTUR_MIT_MENGEN_NEIN";
	public static final String REPORT_MINDEST_BESTAND_JRXML = "./eigeneReports/Mindestbestand/Mindestbestand.jrxml";
	public static final String REPORT_MINDEST_BESTAND_JASPER = "./eigeneReports/Mindestbestand/Mindestbestand.jasper";
	
	public static final String REPORT_WARTUNG_LISTE_01_JRXML = "./eigeneReports/Wartungen/wartungen.jrxml";
	public static final String REPORT_WARTUNG_LISTE_01_JASPER = "./eigeneReports/Wartungen/wartungen.jasper";
	public static final String REPORT_WARTUNG_LISTE_01_PDF = "./eigeneReports/Wartungen/wartungen.pdf";
	public static final String REPORT_WARTUNG_LISTE_01_HTML = "./eigeneReports/Wartungen/wartungen.html";
	public static final String REPORT_WARTUNG_LISTE_01_XML = "./eigeneReports/Wartungen/wartungen.xml";
	
	
	public static final Integer RECHT_ADMINISTRATOR = 1;
	public static final Integer RECHT_ABTEILUNGSLEITER = 2;
	public static final Integer RECHT_MITARBEITER = 3;
	
	//Hash-Werte für die Anzeige in den JTrees
	public static final Integer HASH_OFFSET_BEREICH = 1000000;
	public static final Integer HASH_OFFSET_ETAGE = HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_HALLE = HASH_OFFSET_ETAGE + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_ZEILE = HASH_OFFSET_HALLE + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_SAEULE = HASH_OFFSET_ZEILE + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_EBENE = HASH_OFFSET_SAEULE + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_POSITION = HASH_OFFSET_EBENE + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_BESTANDSPACKSTUECK = HASH_OFFSET_POSITION + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_BAUGRUPPE = HASH_OFFSET_BESTANDSPACKSTUECK + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_ARTIKEL = HASH_OFFSET_BAUGRUPPE + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_PROVIDER = HASH_OFFSET_ARTIKEL+ HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_UNTER_ARTIKEL = HASH_OFFSET_PROVIDER + HASH_OFFSET_BEREICH;
	public static final Integer HASH_OFFSET_KATALOG = HASH_OFFSET_UNTER_ARTIKEL + HASH_OFFSET_BEREICH;
	
	public static final CharSequence SQL_ERROR_FK_MENGENEINHEIT = "fk_mengeneinheit";
	public static final CharSequence SQL_ERROR_ARTIKEL_KEG_NR_EINDEUTIG = "artikel_keg_nr_eindeutig";
	
	
	public static String DateToString(java.util.Date date) {
		final java.text.DateFormat dfmt = new java.text.SimpleDateFormat(Konstanten.FORMAT_DATUM_ZEIT);
		return dfmt.format(date);
	}
}
