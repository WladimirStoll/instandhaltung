package de.keag.lager.panels.frame.bestellung;

public enum BestellungPosStatus {
	FEHLERHAFT, OFFEN, ABGESCHLOSSEN;
	
	public static BestellungPosStatus DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("O"))
			return BestellungPosStatus.OFFEN;
		else if (dbWert.toUpperCase().equals("A"))
			return BestellungPosStatus.ABGESCHLOSSEN;
		else
			return BestellungPosStatus.FEHLERHAFT;
	}
	public static String JavaToDB(BestellungPosStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == BestellungPosStatus.OFFEN)
			return "O";
		else if (bestellanforderungsStatus == BestellungPosStatus.ABGESCHLOSSEN)
			return "A";
		else
			return "F";//Fehlerhaft
	}
	
	public boolean equals(BestellungPosStatus posStatus){
			return this == posStatus; 
	}
	
}
