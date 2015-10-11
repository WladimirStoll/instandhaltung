package de.keag.lager.panels.frame.bericht;

public class BerichtSpalte {
	private String spaltenName1;
	private String spaltenName2;
	private String spaltenName3;
	private Integer breite;
	private String inhalt;
	
	private BerichtSpalte() {
		super();
	}
	
	public BerichtSpalte(String spaltenName1,String spaltenName2, int spaltenBreite, String splatenInhalt) {
		this(spaltenName1, spaltenName2, "", spaltenBreite,
				splatenInhalt);
	}

	public BerichtSpalte(String spaltenName1,String spaltenName2, String spaltenName3, int spaltenBreite, String splatenInhalt) {
		this();
		setSpaltenName1(spaltenName1);
		setSpaltenName2(spaltenName2);
		setBreite(spaltenBreite);
		setInhalt(splatenInhalt);
	}
	
	
	public String getSpaltenName1() {
		if (spaltenName1==null){
			spaltenName1 = "";
		}
		return spaltenName1;
	}
	public void setSpaltenName1(String spaltenName1) {
		this.spaltenName1 = spaltenName1;
	}
	
	public String getSpaltenName2() {
		if (spaltenName2==null){
			spaltenName2 = "";
		}
		return spaltenName2;
	}
	public void setSpaltenName2(String spaltenName2) {
		this.spaltenName2 = spaltenName2;
	}
	
	public String getSpaltenName3() {
		if (spaltenName3==null){
			spaltenName3 = "";
		}
		return spaltenName3;
	}
	public void setSpaltenName3(String spaltenName3) {
		this.spaltenName3 = spaltenName3;
	}
	
	public Integer getBreite() {
		if (breite == null){
			breite = 5;
		}
		return breite;
	}
	public void setBreite(Integer breite) {
		this.breite = breite;
	}
	
	public String getInhalt() {
		if (inhalt == null){
			inhalt = "Fehler";
		}
		return inhalt;
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
}
