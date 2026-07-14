package de.keag.lager.core.main;

import java.awt.Color;
import java.awt.SystemColor;


public class Konstanten {
	public static final String UEBERSCHRIFT_ANWENDUNG = "Lager";
	public static final String LOGIN = "Anmeldung";
	public static final String SLASH = " / ";
	public static final String MENUE = "Menü";
	public static final String UEBERSCHRIFT_ENTNAHMENSLISTE = "Entnahme";
	public static final String UEBERSCHRIFT_BESTELLANFORDERUNGSLISTE = "Bestellanforderung";
	public static final String UEBERSCHRIFT_BESTELLUNG = "Bestellung";
	public static final String UEBERSCHRIFT_DRUCK = "Drucken / Verschicken";
	public static final String UEBERSCHRIFT_BENUTZER = "Benutzer";
	public static final String UEBERSCHRIFT_LIEFERANT_HERSTELLER = "Lieferant/Hersteller";
	public static final String UEBERSCHRIFT_BAUGRUPPE = "Anlage";
	public static final String UEBERSCHRIFT_HALLE = "Halle";
	public static final String UEBERSCHRIFT_ARTIKEL = "Artikel";
		
	public static final String FORMAT_DATUM_ZEIT = "dd.MM.yyyy HH:mm";
	public static final String FORMAT_DATUM = "dd.MM.yyyy";
	public static final String FORMAT_ZEIT = "HH:mm"; //24 Stunden - Anzeige
	public static final String FORMAT_ZEIT_MIT_SEKUNDEN = "HH:mm:ss"; //24 Stunden - Anzeige
	public static final String BUTTON_NEU="Neu";
	public static final String BUTTON_SPEICHERN = "Speichern";
	public static final String BUTTON_ABBRECHEN="Abbrechen";
	public static final String BUTTON_DRUCKEN="Drucken";
	public static final String BUTTON_LOESCHEN="Löschen";
	
	public static final String ID_DB_ENTNAHME = "EntnahmeID";
	public static final String ID_DB_ENTNAHME_POS = "EntnahmenspositionPosID";
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
	public static final String ICON_ENTNAHMENSPOSITION = "page.png";
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
	public static final CharSequence SQL_ERROR_FK_MENGENEINHEIT = "fk_mengeneinheit";
	public static final CharSequence SQL_ERROR_ARTIKEL_KEG_NR_EINDEUTIG = "artikel_keg_nr_eindeutig";
	
	
	
	public static String DateToString(java.util.Date date) {
		final java.text.DateFormat dfmt = new java.text.SimpleDateFormat(Konstanten.FORMAT_DATUM_ZEIT);
		return dfmt.format(date);
	}
}
