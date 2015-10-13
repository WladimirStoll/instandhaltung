package de.keag.lager.core.fehler;

import java.sql.SQLException;

import de.keag.lager.core.main.Konstanten;

public class Fehler{
	private String meldung;
	private Integer fehlerId;
	private FehlerTyp fehlerTypEnum;
	
	public Fehler(int fehlerId) {
		super();
		String 	meldung = null;
		switch ( fehlerId ) 
		{ 
		  case 0: 
			    meldung = "Unbekannter Fehler!";
			    break; 
		  case 1: 
				meldung = "Anmeldung ist erfolglos!";
			    break; 
		  case 2: 
				meldung = "Das Objekt ist nicht vorhanden und kann nicht gespeichert werden.";
			    break; 
		  case 3: 
				meldung = "Objektzustand ist unbekannt. Das Objekt kann nicht verarbeitet werden.";
			    break;
		  case 4: 
				meldung = "Funktion ist nicht programmiert.";
			    break;
		  case 5: 
				meldung = "Objektzustand ist falsch definiert. Operation ist unmöglich";
			    break;
		  case 6: 
				meldung = "Benutzer kann nicht gelesen werden.";
			    break;
		  case 7: 
				meldung = "Objekt ist nicht vorhanden.";
			    break;
		  case 8: 
				meldung = "Anmeldung fehlgeschlagen.";
			    break;
		  case 9: 
				meldung = "Falsches Passwort";
			    break;
		  case 10: 
				meldung = "ID konnte nicht ermittelt werden.";
			    break;
		  case 11: 
				meldung = "ID konnte nicht ermittelt werden.";
			    break;
		  case 12: 
				meldung = "Fehler in der Benutzeroberfläche.";
			    break;
		  case 13: 
				meldung = "Fehler während der Suche nach Anforderung.";
			    break;
		  case 14: 
				meldung = "Kein Satz gewählt.";
			    break;
		  case 15: 
				meldung = "Gewählte Zeile kann nicht verarbeitet werden.";
			    break;		  
			case 16:
				meldung = "Zu der Bestellanforderung konnten keine Positionen ermittelt werden.";
				break;
			case 17:
				meldung = "KnotenBean darf nicht gesetzt werden. Sie ist gleich null.";
				break;
			case 18:
				meldung = "Falscher Typ";
				break;
			case 19:
				meldung = "Falscher Knotentyp:";
				break;
			case 20:
				meldung = "Kein Knoten gewählt";
				break;
			case 21:
				meldung = "Falscher Knoten-Typ";
				break;
			case 22:
				meldung = "Der Benutzer kann anhand der ID nicht gelesen werden. ID:";
				break;
			case 23:
				meldung = "Hersteller-Id oder Lieferanten-Id ist falsch. ID:";
				break;			
			case 24:
				meldung = "Fehler beim DB-Zugriff: ";
				break;
			case 25:
				meldung = "Programmfehler:";
				break;
			case 26:
				meldung = "Hersteller-Name oder Lieferanten-Name ist falsch. ID:";
				break;			
			case 27:
				meldung = "Keine Zeile ist gewählt. Vorgang ist abgebrochen.";
				break;			
			case 28:
				meldung = "Wert ist ungültig.";
				break;			
			case 29:
				meldung = "Kalenderwochenwert ist ungültig.";
				break;			
			case 30:
				meldung = "Bestellmenge muss angegeben werden.";
				break;			
			case 31:
				meldung = "Bestellposition ohne Bestellung ist ungültig";
				break;			
			case 32:
				meldung = "Bestellposition mit Bestellungs-ID = 0 ist ungültig";
				break;			
			case 33:
				meldung = "Bestellposition ohne Artikel ist ungültig.";
				break;			
			case 34:
				meldung = "Bestellmenge ist ungültig";
				break;			
			case 35:
				meldung = "Lieferant ist ungültig";
				break;			
			case 36:
				meldung = "Fehler:";
				break;			
			case 37:
				meldung = "Der Artikel ist in mehreren Positionen vorhanden. Positions-ID:";
				break;			
			case 38:
				meldung = "Datensatz muss vorher abgespeichert oder verworfen werden.";
				break;			
			case 39:
				meldung = "Datensatz ist bereits gelöscht.";
				break;			
			case 40:
				meldung = "Email ist fehlerhaft";
				break;			
			case 41:
				meldung = "Benuzter - Mussfelder müssen gefüllt werden.";
				break;			
			case 42:
				meldung = "Abteilung muss angegeben werden.";
				break;			
			case 43:
				meldung = "Benutzer muss angegeben werden.";
				break;			
			case 44:
				meldung = "Name ist ungültig oder leer.";
				break;			
			case 45:
				meldung = "Vorname ist ungültig oder leer.";
				break;			
			case 46:
				meldung = "Loginname ist ungültig oder leer.";
				break;			
			case 47:
				meldung = "Passwort ist ungültig. Es muss 4-stellig sein.";
				break;			
			case 48:
				meldung = "E-Mail-Adresse ist nicht gültig.";
				break;			
			case 49:
				meldung = "ID darf nicht 0 sein.";
				break;		
			case 50:
				meldung = "Der Loginname ist bereits vorhanden. Loginname:";
				break;			
			case 51:
				meldung = "Objekt AbteilungBenutzer hat keine Abteilung";
				break;			
			case 52:
				meldung = "Objekt AbteilungBenutzer hat keinen Benutzer";
				break;			
			case 53:
				meldung = "Abteilung muss angegeben werden";
				break;			
			case 54:
				meldung = "Ungültige Dateneingabe. Doppelter Schlüssel.";
				break;			
			case 55:
				meldung = "Fehlerhafter Satz darf nicht verarbeitet werden.";
				break;			
			case 56:
				meldung = "Artikel muss angegeben werden.";
				break;			
			case 57:
				meldung = "Eingebaute Menge ist ungültig.";
				break;			
			case 58:
				meldung = "Baugruppe kann nicht gespeichert werden.";
				break;	
			case 59:
				meldung = "ID ist ungültig oder leer.";
				break;
			case 60:
				meldung = "Name ist ungültig oder leer.";
				break;
			case 61:
				meldung = "Adresse_land ist ungültig oder leer.";
				break;
			case 62:
				meldung = "Adresse_plz ist ungültig oder leer.";
				break;
			case 63:
				meldung = "Adresse_stadt ist ungültig oder leer.";
				break;
			case 64:
				meldung = "Adresse_strasse ist ungültig oder leer.";
				break;
			case 65:
				meldung = "Telefon ist ungültig oder leer.";
				break;
			case 66:
				meldung = "Fax ist ungültig oder leer.";
				break;
			case 67:
				meldung = "Email ist ungültig oder leer.";
				break;
			case 68:
				meldung = "Ansprechpartner ist ungültig oder leer.";
				break;
			case 69:
				meldung = "Halle ist ungültig oder leer.";
				break;
			case 70:
				meldung = "Nummer ist ungültig oder leer.";
				break;
			case 71:
				meldung = "Etage ist ungültig oder leer.";
				break;
			case 72:
				meldung = "Zeile ist ungültig oder leer.";
				break;
			case 73:
				meldung = "Mengeneinheit ist ungültig oder leer.";
				break;
			case 74:
				meldung = "Menge muss angegeben werden.";
				break;
			case 75:
				meldung = "Position ist ungültig oder leer.";
				break;			

			case 76:
				meldung = "Ebene ist ungültig oder leer.";
				break;			

			case 77:
				meldung = "Saeule ist ungültig oder leer.";
				break;			

			case 78:
				meldung = "Entnahmeposition ohne Entnahme ist ungültig";
				break;			
			case 79:
				meldung = "Entnahmeposition ohne Artikel ist ungültig.";
				break;

			case 100:
				meldung = "Fehler in den Daten:";
				break;
			case 101:
				meldung = "Exception";
				break;

			case 102:
				meldung = "Eine Position darf höhstens ein Bestandspackstück haben!";
				break;
				
			case 103:
				meldung = "Fehler: Artikebezeichnung fehlt im Artikelstammsatz";
				break;
				
			case 104:
				meldung = "Fehler: Typ fehlt im Artikelstammsatz";
				break;
				
			case 105:
				meldung = "Fehler: KE-Nr fehlt im Artikelstammsatz";
				break;
				
			case 106:
				meldung = "Fehler: Mengeneinheit fehlt im Artikelstammsatz";
				break;
				
			case 107:
				meldung = "Fehler: Hersteller fehlt im Artikelstammsatz";
				break;
				
			case 108:
				meldung = "Fehler: Empfohlene Bestellmenge fehlt im Artikelstammsatz";
				break;
				
			case 109:
				meldung = "Fehler: Feldwert ist bereits vorhanden. Doppelter Schlüssel!";
				break;
				
			case 110:
				meldung = "Fehler: Mengeneinheit muss eingegegen werden!";
				break;
				
			case 111:
				meldung = "Fehler: Bezeichnung ist ungültig!";
				break;
				
			case 112:
				meldung = "Fehler: Typ ist ungülig!";
				break;
				
			case 113:
				meldung = "Fehler: KE-Nummer ist ungülig!";
				break;
				
			case 114:
				meldung = "Fehler: Mindestbestand ist ungülig!";
				break;
				
			case 115:
				meldung = "Fehler: Empfohlene Bestellmenge ist ungülig!";
				break;
				
			case 116:
				meldung = "Fehler: KE-Nummer ist bereits vorhanden!";
				break;
				
			case 117:
				meldung = "Fehler: Lager-Position kann nicht ermittelt werden!";
				break;
				
			case 118:
				meldung = "Fehler: Lager-Position kann nicht gefunden werden!";
				break;
				
			case 119:
				meldung = "Fehler: Bestellnummer muss angegeben werden!";
				break;
				
			case 120:
				meldung = "Fehler: Artikel muss angegeben werden!";
				break;
				
			case 121:
				meldung = "Fehler: Katalog muss angegeben werden!";
				break;
				
			case 122:
				meldung = "Fehler: Preis ist ungültig!";
				break;
				
			case 123:
				meldung = "Fehler: Hauptartikel fehlt!";
				break;
				
			case 124:
				meldung = "Fehler: Stücklistenartikel fehlt!";
				break;
				
			case 125:
				meldung = "Fehler: Menge des Stücklistenartikels fehlt!";
				break;
				
			case 126:
				meldung = "Fehler: Katalog kann nicht gelesen werden!";
				break;
				
			case 127:
				meldung = "Fehler: Hallennummer muss angegeben werden!";
				break;
				
			case 128:
				meldung = "Fehler: Hallennummer ist bereits vorhanden!";
				break;
				
			case 129:
				meldung = "Fehler: Wartungsintervall muss gefüllt werden!";
				break;
				
			case 130:
				meldung = "Fehler: Soll-Termin muss gefüllt werden!";
				break;
				
			case 131:
				meldung = "Fehler: Baugruppe muss gefüllt werden!";
				break;
				
			case 132:
				meldung = "Fehler: ID im Wartungsartikel muss gefüllt werden!";
				break;
				
			case 133:
				meldung = "Fehler: Wartung fehlt im Wartungsartkel!";
				break;
				
			case 134:
				meldung = "Fehler: Artikel fehlt im Wartungsartkel!";
				break;
				
			case 135:
				meldung = "Fehler: Menge fehlt im Wartungsartikel!";
				break;
				
			case 136:
				meldung = "Fehler: ID fehlt!";
				break;
				
			case 137:
				meldung = "Fehler: Bitte Mitarbeiter oder/und Firma angeben!";
				break;
				
			case 138:
				meldung = "Fehler: Bitte Zeitaufwand angeben!";
				break;
				
			case 139:
				meldung = "Fehler: Wartungs-Karten-ID ist fehlerhaft!";
				break;
				
			case 140:
				meldung = "Fehler: Bemerkung ist fehlerhaft!";
				break;
				
			case 141:
				meldung = "Fehler: Wartungsart ist nicht gefüllt!";
				break;
				
			case 142:
				meldung = "Fehler: Baugruppbe muss angegeben werden!";
				break;
				
				
				
		default:
				meldung = "Fehler unbekannt!";
		}
		setId(fehlerId);
		setMeldung(meldung);
		setFehlerTypEnum(FehlerTyp.FEHLER);
	}
	
	public Fehler(int fehlerId, FehlerTyp fehlerTyp){
		this(fehlerId);
		setFehlerTypEnum(fehlerTyp);
	}
	
	public Fehler(int fehlerId, FehlerTyp fehlerTyp, String FehlerMeldungZusatz){
		this(fehlerId);
		setFehlerTypEnum(fehlerTyp);
		setMeldung(this.getMessage() + " " + FehlerMeldungZusatz);
	}
	
	
	
	public Fehler(SQLException e) {
		this(101,FehlerTyp.FEHLER,e.getMessage());
	}
	
	public Fehler(LagerException e) {
		this(101,FehlerTyp.FEHLER,e.getMessage());
	}
	

	public Fehler(String string) {
		this(36,FehlerTyp.FEHLER,string);
	}

	public String getMessage() {
		if (meldung==null){
			meldung = "";
		}
		return meldung;
	}
	
	public void setMeldung(String meldung) {
		if (meldung == null){
			meldung = "unbekannter Fehler";
		}
		this.meldung = meldung;
	}
	
	public Integer getId() {
		return fehlerId;
	}
	public void setId(Integer fehlerId) {
		this.fehlerId = fehlerId;
	}
	
	private FehlerTyp getFehlerTypEnum() {
		return fehlerTypEnum;
	}

	private void setFehlerTypEnum(FehlerTyp fehlerTypEnum) {
		this.fehlerTypEnum = fehlerTypEnum;
	}

	@Override
	public String toString(){
		return getFehlerTypEnum() + " " + getId() + " " + getMessage();
	}

	public static String getFehlerIcon() {
		return Konstanten.ICON_FEHLER;
	}
	
}
