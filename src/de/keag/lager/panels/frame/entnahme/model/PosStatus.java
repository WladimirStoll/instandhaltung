package de.keag.lager.panels.frame.entnahme.model;

public enum PosStatus {
	FEHLERHAFT, OFFEN, ABGESCHLOSSEN;
	
	public static PosStatus DbToJava(String dbWert){
		if (dbWert.toUpperCase().equals("O"))
			return PosStatus.OFFEN;
		else if (dbWert.toUpperCase().equals("A"))
			return PosStatus.ABGESCHLOSSEN;
		else
			return PosStatus.FEHLERHAFT;
	}
	public static String JavaToDB(PosStatus bestellanforderungsStatus){
		if (bestellanforderungsStatus == PosStatus.OFFEN)
			return "O";
		else if (bestellanforderungsStatus == PosStatus.ABGESCHLOSSEN)
			return "A";
		else
			return "F";//Fehlerhaft
	}
	
	public boolean equals(PosStatus posStatus){
			return this == posStatus; 
	}
	
}
