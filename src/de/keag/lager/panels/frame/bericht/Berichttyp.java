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
