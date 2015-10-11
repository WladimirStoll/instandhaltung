package de.keag.lager.panels.frame.bericht;

public enum Berichtart {
	FEHLERHAFT, //Mit Fehler behaftet. 
	PRINT, 
	EMAIL,
	ALLE; 
	
	public static Berichtart DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("PRINT"))
			return Berichtart.PRINT;
		else if (dbWert.toUpperCase().equals("EMAIL"))
			return Berichtart.EMAIL;
		else if (dbWert.toUpperCase().equals("ALLE"))
			return Berichtart.ALLE;
		else
			return Berichtart.FEHLERHAFT;
	}
	public static String JavaToDB(Berichtart bestellanforderungsStatus){
		if (bestellanforderungsStatus == Berichtart.PRINT)
			return "PRINT";
		else if (bestellanforderungsStatus == Berichtart.EMAIL)
			return "EMAIL";
		else if (bestellanforderungsStatus == Berichtart.ALLE)
			return "ALLE";
		else
			return "Fehlerhaft"; //Fehlerhaft

	}
	public static String JavaToView(Berichtart bestellanforderungsStatus){
		if (bestellanforderungsStatus == Berichtart.PRINT)
			return "Druck";
		else if (bestellanforderungsStatus == Berichtart.EMAIL)
			return "E-Mail";
		else if (bestellanforderungsStatus == Berichtart.ALLE)
			return "Alle";
		else
			return "Fehlerhaft"; //Fehlerhaft
	}
	@Override
	
	public String toString(){
		return JavaToView(this);
	}
	public String JavaToDB() {
		return Berichtart.JavaToDB(this);
	}
}
