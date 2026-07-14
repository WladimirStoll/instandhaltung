package de.keag.lager.panels.frame.entnahme.model;

public enum EntnahmeStatus {
	FEHLERHAFT, //Mit Fehler behaftet. 
	OFFEN, //Entnahme ist offen
	ABGESCHLOSSEN, //Entnahme ist abgeschlossen
	GELOESCHT, //Entnahme ist inaktiv
	ALLE; //Diese Option ist für die Auswahlkrieterien gedacht und darf einer Bean so gut wie nie zugeordnet werden.
	
	public static EntnahmeStatus DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("O"))
			return EntnahmeStatus.OFFEN;
		else if (dbWert.toUpperCase().equals("L"))
			return EntnahmeStatus.GELOESCHT;
		else if (dbWert.toUpperCase().equals("E"))
			return EntnahmeStatus.ABGESCHLOSSEN;
		else if (dbWert.toUpperCase().equals("A"))
			return EntnahmeStatus.ALLE;
		else
			return EntnahmeStatus.FEHLERHAFT;
	}
	public static String JavaToDB(EntnahmeStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == EntnahmeStatus.OFFEN)
			return "O";
		else if (bestellanforderungsStatus == EntnahmeStatus.ABGESCHLOSSEN)
			return "E";
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
		else if (bestellanforderungsStatus == EntnahmeStatus.ABGESCHLOSSEN)
			return "Abgeschlossen";
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
