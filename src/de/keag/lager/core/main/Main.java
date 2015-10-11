package de.keag.lager.core.main;

import java.io.IOException;
import java.util.logging.Level;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.login.LoginController;

public class Main {
	
	/**
	 * Launches this application
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)  {
		System.out.println("main:start");  
//		Log.testLog();
		try {
			Log.setup();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Problems with creating the log files");			
		}
		Log.log().setLevel(Level.WARNING);
		Log.log().log(Level.INFO, "MAIN:START - LOG");
//		Log.log().setLevel(Level.FINEST);
		
//		Run.getOneInstance().zeigeLoginView();
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
//					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Run.getOneInstance().zeigeLoginView();
			}
		});
	}

}
