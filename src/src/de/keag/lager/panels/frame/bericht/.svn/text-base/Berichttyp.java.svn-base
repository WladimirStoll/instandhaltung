package de.keag.lager.panels.frame.bericht;

public enum Berichttyp {
	FEHLERHAFT, //Mit Fehler behaftet. 
	BENUTZER, 
	ANLAGE, 
	ANFORDERUNG,
	BESTELLUNG,
	ALLE;
	
	public static Berichttyp DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("BENUTZER"))
			return Berichttyp.BENUTZER;
		else if (dbWert.toUpperCase().equals("ANFORDERUNG"))
			return Berichttyp.ANFORDERUNG;
		else if (dbWert.toUpperCase().equals("BESTELLUNG"))
			return Berichttyp.BESTELLUNG;
		else if (dbWert.toUpperCase().equals("ANLAGE"))
			return Berichttyp.ANLAGE;
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
		else if (bestellanforderungsStatus == Berichttyp.ANFORDERUNG)
			return "ANFORDERUNG";
		else if (bestellanforderungsStatus == Berichttyp.BESTELLUNG)
			return "BESTELLUNG";
		else if (bestellanforderungsStatus == Berichttyp.ALLE)
			return "ALLE";
		else
			return "Fehlerhaft"; //Fehlerhaft

	}
	public static String JavaToView(Berichttyp bestellanforderungsStatus){
		if (bestellanforderungsStatus == Berichttyp.BENUTZER)
			return "Benutzer";
		else if (bestellanforderungsStatus == Berichttyp.ANLAGE)
			return "Anlage";
		else if (bestellanforderungsStatus == Berichttyp.ANFORDERUNG)
			return "Anforderung";
		else if (bestellanforderungsStatus == Berichttyp.BESTELLUNG)
			return "Bestellung";
		else if (bestellanforderungsStatus == Berichttyp.ALLE)
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
