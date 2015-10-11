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

import java.awt.Color;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JToolTip;
import javax.swing.JFormattedTextField.AbstractFormatter;

import com.jgoodies.validation.formatter.EmptyNumberFormatter;

import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;

public class LagerFormate {
	

	private LagerFormate() throws Exception {
		//keine Instance ist möglich.
		throw new Exception("Fehler!");
	}

	static public java.text.NumberFormat getNumberFormat(int maxAnzahlVorkommastellen){
//		java.text.NumberFormat nummerformat = java.text.NumberFormat.getNumberInstance();
//		nummerformat.setMaximumFractionDigits(0); //keine Nachkommastellen  
//		nummerformat.setMaximumIntegerDigits(maxAnzahlVorkommastellen);  
//		nummerformat.setGroupingUsed(false); //Keine gruppierung in 100er Schritten
		
		
		java.text.NumberFormat nummerformat = java.text.NumberFormat.getNumberInstance();
		nummerformat.setMaximumFractionDigits(0); //keine Nachkommastellen  
		nummerformat.setGroupingUsed(false); //Keine gruppierung in 100er Schritten
		
		
		return nummerformat;
	}

	
	static public java.text.DecimalFormat getDecimalFormat(int maxAnzahlVorkommastellen, int maxAnzahlNachkommastellen) {
		java.text.DecimalFormat myDF = new java.text.DecimalFormat();
		myDF.setMaximumFractionDigits(maxAnzahlNachkommastellen); // Nachkommastellen einstellen
		myDF.setMinimumFractionDigits(0);
		myDF.setMaximumIntegerDigits(maxAnzahlVorkommastellen);
		myDF.setMinimumIntegerDigits(0);
		return myDF;
	}	

	static public java.text.DateFormat getDateFormatSHORT(){
		java.text.DateFormat df = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, Locale.GERMAN);
		return df;
		// SHORT nummerisch, wie 12.13.52 oder 3:30pm
		// MEDIUM länger Jan 12, 1952
		// LONG noch länger January 12, 1952 oder 3:30:32pm
		// FULL komplettes Datum Tuesday, April 12, 1952 AD oder 3:30:42pm PST.
	}
	
	static public InputVerifier getInputVerifier() {
		return new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				if (input instanceof JFormattedTextField) {
					JFormattedTextField ftf = (JFormattedTextField) input;
					AbstractFormatter formatter = ftf.getFormatter();
					if (formatter != null) {
						String text = ftf.getText();
						try {
							if (formatter instanceof LagerStringFormatter){
								String erfassterWert = (String)((LagerStringFormatter)formatter).stringToValue(text);
								String valueWert = null;
								if (ftf.getValue()!=null){
									valueWert = ftf.getValue().toString();
//									erfassterWert = valueWert;
								}else{
									valueWert = "";
								}
								if (!erfassterWert.equals(valueWert)){
									ftf.setValue(erfassterWert);
								}
								int minWert = ((LagerStringFormatter)formatter).getMinLaenge();
								int maxWert = ((LagerStringFormatter)formatter).getMaxLaenge();
								if (erfassterWert.length() < minWert){
									String fehlerText = "Wort >"+ erfassterWert + "< ist zu kurz! " + ((LagerStringFormatter)formatter).getStandardHinweis();
									input.setToolTipText(fehlerText);
									throw new ParseException(fehlerText,1);
								}
								if (erfassterWert.length() > maxWert){
									String fehlerText = "Wort >"+ erfassterWert + "< ist zu lang! "  + ((LagerStringFormatter)formatter).getStandardHinweis();
									input.setToolTipText(fehlerText);
									throw new ParseException(fehlerText,1);
								}
								input.setToolTipText(((LagerStringFormatter)formatter).getStandardHinweis());
								ftf.setBackground(Konstanten.COLOR_BACKGROUND_TEXTFIELD_DEFAULT);
							}else
							if (formatter instanceof LagerEmptyNumberFormatter){
								if (((LagerEmptyNumberFormatter)formatter).stringToValue(text) instanceof Long){
									Long erfassterWert = (Long)((LagerEmptyNumberFormatter)formatter).stringToValue(text);
//									Double erfassterWert = (Double)((LagerEmptyNumberFormatter)formatter).stringToValue(text);
									if (erfassterWert==null){
										erfassterWert = 0L;
									}
									Long valueWert = null;
//									Double valueWert = null;
									if (ftf.getValue()!=null){
										valueWert = ((Number)ftf.getValue()).longValue();
//										erfassterWert = valueWert;
									}else{
										valueWert = null;
									}
//									ftf.setText(valueWert.toString());
									if (!erfassterWert.equals(valueWert)){
//										ftf.setText(valueWert.toString());
										ftf.setValue(erfassterWert);
									}
									int minWert = ((LagerEmptyNumberFormatter)formatter).getMinWert();
									int maxWert = ((LagerEmptyNumberFormatter)formatter).getMaxWert();
									if (erfassterWert < minWert){
										String fehlerText = "Wert "+ erfassterWert.toString() + " ist zu klein! "+((LagerEmptyNumberFormatter)formatter).getStandardHinweis();
										input.setToolTipText(fehlerText);
										throw new ParseException(fehlerText,1);
									}
									if (erfassterWert > maxWert){
										String fehlerText = "Wert "+ erfassterWert.toString() + " ist zu groß! "+((LagerEmptyNumberFormatter)formatter).getStandardHinweis();
										input.setToolTipText(fehlerText);
										throw new ParseException(fehlerText,1);
									}
									input.setToolTipText(((LagerEmptyNumberFormatter)formatter).getStandardHinweis());
									ftf.setBackground(Konstanten.COLOR_BACKGROUND_TEXTFIELD_DEFAULT);
									
								}else if (((LagerEmptyNumberFormatter)formatter).stringToValue(text) instanceof Double) {
//									Long erfassterWert = (Long)((LagerEmptyNumberFormatter)formatter).stringToValue(text);
									Double erfassterWert = (Double)((LagerEmptyNumberFormatter)formatter).stringToValue(text);
									if (erfassterWert==null){
										erfassterWert = 0D;
									}
//									Long valueWert = null;
									Double valueWert = null;
									if (ftf.getValue()!=null){
										valueWert = ((Number)ftf.getValue()).doubleValue();
//										erfassterWert = valueWert;
									}else{
										valueWert = null;
									}
//									ftf.setText(valueWert.toString());
									if (!erfassterWert.equals(valueWert)){
//										ftf.setText(valueWert.toString());
										ftf.setValue(erfassterWert);
									}
									int minWert = ((LagerEmptyNumberFormatter)formatter).getMinWert();
									int maxWert = ((LagerEmptyNumberFormatter)formatter).getMaxWert();
									if (erfassterWert < minWert){
										String fehlerText = "Wert "+ erfassterWert.toString() + " ist zu klein! "+((LagerEmptyNumberFormatter)formatter).getStandardHinweis();
										input.setToolTipText(fehlerText);
										throw new ParseException(fehlerText,1);
									}
									if (erfassterWert > maxWert){
										String fehlerText = "Wert "+ erfassterWert.toString() + " ist zu groß! "+((LagerEmptyNumberFormatter)formatter).getStandardHinweis();
										input.setToolTipText(fehlerText);
										throw new ParseException(fehlerText,1);
									}
									input.setToolTipText(((LagerEmptyNumberFormatter)formatter).getStandardHinweis());
									ftf.setBackground(Konstanten.COLOR_BACKGROUND_TEXTFIELD_DEFAULT);
									
								}else{
									Log.log().severe("Wrong type:"+((LagerEmptyNumberFormatter)formatter).stringToValue(text).toString());
								}
							}else{
								formatter.stringToValue(text);
								ftf.setBackground(Konstanten.COLOR_BACKGROUND_TEXTFIELD_DEFAULT);
							}
							return true;
						} catch (ParseException pe) {
							ftf.setBackground(Konstanten.COLOR_BACKGROUND_TEXTFIELD_ERROR);
							return false;
						}
					}
				}
				return true;

			}

			@Override
			public boolean shouldYieldFocus(JComponent input) {
				return verify(input);
			}
		};
	}
	
	
}
