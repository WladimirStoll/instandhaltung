package de.keag.lager.core.formatter;

import com.jgoodies.validation.formatter.EmptyNumberFormatter;

public class LagerEmptyNumberFormatter extends EmptyNumberFormatter {
	private Integer minWert = 0;
	private Integer maxWert = 0;
	private String standardHinweis = "";
	
	public LagerEmptyNumberFormatter(Integer minWert, Integer maxWert){
		super();
		setMinWert(minWert);
		setMaxWert(maxWert);
	}

	public LagerEmptyNumberFormatter(Integer minWert, Integer maxWert, String standardHinweis){
		this(minWert,maxWert);
		setStandardHinweis(standardHinweis + " Kleinster Wert:"+getMinWert()+", Höchster Wert:"+getMaxWert()+".");
	}
	
	
	public Integer getMinWert() {
		return minWert;
	}
	private void setMinWert(Integer minWert) {
		this.minWert = minWert;
	}
	
	public Integer getMaxWert() {
		return maxWert;
	}
	private void setMaxWert(Integer maxWert) {
		this.maxWert = maxWert;
	}

	public String getStandardHinweis() {
		return standardHinweis;
	}

	private void setStandardHinweis(String standardHinweis) {
		if (standardHinweis==null){
			standardHinweis = ""; 
		}
		this.standardHinweis = standardHinweis;
	}

}
