package de.keag.lager.panels.frame.bestellanforderung;

public enum AnforderungStatus {
	FEHLERHAFT, //Mit Fehler behaftet. 
	OFFEN, //Anforderung ist offen
	ABGESCHLOSSEN, //Anforderung ist abgeschlossen
	GELOESCHT, //Anforderung ist inaktiv
	ALLE; //Diese Option ist für die Auswahlkrieterien gedacht und darf einer Bean so gut wie nie zugeordnet werden.
	
	public static AnforderungStatus DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("O"))
			return AnforderungStatus.OFFEN;
		else if (dbWert.toUpperCase().equals("L"))
			return AnforderungStatus.GELOESCHT;
		else if (dbWert.toUpperCase().equals("E"))
			return AnforderungStatus.ABGESCHLOSSEN;
		else if (dbWert.toUpperCase().equals("A"))
			return AnforderungStatus.ALLE;
		else
			return AnforderungStatus.FEHLERHAFT;
	}
	public static String JavaToDB(AnforderungStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == AnforderungStatus.OFFEN)
			return "O";
		else if (bestellanforderungsStatus == AnforderungStatus.ABGESCHLOSSEN)
			return "E";
		else if (bestellanforderungsStatus == AnforderungStatus.ALLE)
			return "A";
		else if (bestellanforderungsStatus == AnforderungStatus.GELOESCHT)
			return "L";
		else
			return "F"; //Fehlerhaft

	}
	public static String JavaToView(AnforderungStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == AnforderungStatus.OFFEN)
			return "Offen";
		else if (bestellanforderungsStatus == AnforderungStatus.ABGESCHLOSSEN)
			return "Abgeschlossen";
		else if (bestellanforderungsStatus == AnforderungStatus.ALLE)
			return "Alle";
		else if (bestellanforderungsStatus == AnforderungStatus.GELOESCHT)
			return "Gelöscht";
		else
			return "Fehlerhaft"; //Fehlerhaft
	}
	@Override
	
	public String toString(){
		return JavaToView(this);
	}
	
	public String JavaToDB(){
		return JavaToDB(this);
	}
}
