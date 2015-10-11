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
package de.keag.lager.core.fehler;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import de.keag.lager.core.formatter.*;
/*
 * http://www.vogella.com/articles/Logging/article.html
 * http://onjava.com/pub/a/onjava/2002/06/19/log.html
 * 
 */



//import java.util.logging.Logger;
//
//public class Main {
//
//  public static void main(String[] args) {
//    Logger log = Logger.getLogger("global");
//
//    log.finest("A");
//    log.finer("B");
//    log.fine("C");
//    log.config("D");
//    log.info("E");
//    log.warning("O");
//    log.severe("A");
//  }
//}
public class Log{
//	private static Logger logger = Logger.getLogger("LagerLogger");
	
	static private FileHandler fileTxt;
	static private LagerLogDbHandler lagerLogDbHandler;
	static private LagerLogDbFormatter lagerLogDbFormatter;
	static private SimpleFormatter formatterTxt;

	static private FileHandler fileHTML;
	static private Formatter formatterHTML;

	public final static Logger logger = Logger.getLogger(Log.class.getName());
	
	public static Logger log() {
		return logger;
	}
	public static void setFinest(){
		Log.log().setLevel(Level.FINEST);
	}
	
	public static void setFine(){
		Log.log().setLevel(Level.FINE);
	}	
	
	
	static public void setup() throws IOException {
		// Create Logger
//		Logger logger = Logger.getLogger("");
//		logger.setLevel(Level.INFO);
		fileTxt = new FileHandler("Logging.txt");
//		fileHTML = new FileHandler("Logging.html");

		// Create txt Formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);

		// Create txt Formatter
		lagerLogDbHandler = new LagerLogDbHandler();
		lagerLogDbFormatter = new LagerLogDbFormatter();
		lagerLogDbHandler.setFormatter(lagerLogDbFormatter);
		logger.addHandler(lagerLogDbHandler);
		
//		// Create HTML Formatter
//		formatterHTML = new MyHtmlFormatter();
//		fileHTML.setFormatter(formatterHTML);
//		logger.addHandler(fileHTML);
	}
	public static void testLog() {
		Log.log().setLevel(Level.SEVERE);
		Log.log().severe("Info Log");
		Log.log().warning("Info Log");
		Log.log().info("Info Log");
		Log.log().finest("Really not important");

		// Set the LogLevel to Info, severe, warning and info will be written
		// Finest is still not written
		Log.log().setLevel(Level.INFO);
		Log.log().severe("Info Log");
		Log.log().warning("Info Log");
		Log.log().info("Info Log");
		Log.log().finest("Really not important");
		
		Log.log().setLevel(Level.FINEST);
		Log.log().severe("Info Log");
		Log.log().warning("Info Log");
		Log.log().info("Info Log");
		Log.log().fine("fine: Really not important");
		Log.log().finest("finest: Really not important");
	}
	
}
