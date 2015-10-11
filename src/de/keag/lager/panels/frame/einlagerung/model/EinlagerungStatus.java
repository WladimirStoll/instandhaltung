package de.keag.lager.panels.frame.einlagerung.model;

public enum EinlagerungStatus {
	FEHLERHAFT, //Mit Fehler behaftet. 
	OFFEN, //Einlagerung ist offen
	BESTAETIGT, //Einlagerung ist abgeschlossen
	GELOESCHT, //Einlagerung ist inaktiv
	ALLE; //Diese Option ist für die Auswahlkrieterien gedacht und darf einer Bean so gut wie nie zugeordnet werden.
	
	public static EinlagerungStatus DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("O"))
			return EinlagerungStatus.OFFEN;
		else if (dbWert.toUpperCase().equals("L"))
			return EinlagerungStatus.GELOESCHT;
		else if (dbWert.toUpperCase().equals("B"))
			return EinlagerungStatus.BESTAETIGT;
		else if (dbWert.toUpperCase().equals("A"))
			return EinlagerungStatus.ALLE;
		else
			return EinlagerungStatus.FEHLERHAFT;
	}
	public static String JavaToDB(EinlagerungStatus status){
		if (status == EinlagerungStatus.OFFEN)
			return "O";
		else if (status == EinlagerungStatus.BESTAETIGT)
			return "B";
		else if (status == EinlagerungStatus.ALLE)
			return "A";
		else if (status == EinlagerungStatus.GELOESCHT)
			return "L";
		else
			return "F"; //Fehlerhaft

	}
	public static String JavaToView(EinlagerungStatus status){
		if (status == EinlagerungStatus.OFFEN)
			return "Offen";
		else if (status == EinlagerungStatus.BESTAETIGT)
			return "Bestätigt";
		else if (status == EinlagerungStatus.ALLE)
			return "Alle";
		else if (status == EinlagerungStatus.GELOESCHT)
			return "Gelöscht";
		else
			return "Fehlerhaft"; //Fehlerhaft
	}
	@Override
	
	public String toString(){
		return JavaToView(this);
	}
}
