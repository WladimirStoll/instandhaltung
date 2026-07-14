package de.keag.lager.core.main;

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
		Log.log().setLevel(Level.FINEST);
		
//		Run.getOneInstance().zeigeLoginView();
		
		
//		try {
////			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Run.getOneInstance().zeigeLoginView();
			}
		});
	}

}
