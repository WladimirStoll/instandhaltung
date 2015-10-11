/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
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
