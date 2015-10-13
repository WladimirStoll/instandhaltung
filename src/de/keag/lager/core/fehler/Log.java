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
