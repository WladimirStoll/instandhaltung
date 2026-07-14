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
