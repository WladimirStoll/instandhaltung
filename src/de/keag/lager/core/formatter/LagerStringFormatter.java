package de.keag.lager.core.formatter;

import javax.swing.text.DefaultFormatter;

public class LagerStringFormatter extends DefaultFormatter {
	int minLaenge = 0;
	int maxLaenge = 100;
	private String standardHinweis = "";
	
	
	public LagerStringFormatter() {
		super();
	}
	public LagerStringFormatter(int minLaenge, int maxLaenge) {
		super();
		setMinLaenge(minLaenge);
		setMaxLaenge(maxLaenge);
		this.setOverwriteMode(false);
	}
	
	public LagerStringFormatter(int minLaenge, int maxLaenge, String standardHinweis){
		this(minLaenge,maxLaenge);
		setStandardHinweis(standardHinweis + " Kleinste Wortlänge:"+getMinLaenge()+", Höchste Wortlänge:"+getMaxLaenge()+".");
	}
	
	
	
	public int getMinLaenge() {
		return minLaenge;
	}
	public void setMinLaenge(int minLaenge) {
		this.minLaenge = minLaenge;
	}
	public int getMaxLaenge() {
		return maxLaenge;
	}
	public void setMaxLaenge(int maxLaenge) {
		this.maxLaenge = maxLaenge;
	}
	public String getStandardHinweis() {
		// TODO Auto-generated method stub
		return null;
	}
	private void setStandardHinweis(String standardHinweis) {
		this.standardHinweis = standardHinweis;
	}
}
