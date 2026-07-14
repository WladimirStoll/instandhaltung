/**
 */

package de.keag.lager.core.junit.login;

import java.lang.reflect.InvocationTargetException;

import org.netbeans.jemmy.*;
import org.netbeans.jemmy.explorer.*;
import org.netbeans.jemmy.operators.*;

import de.keag.lager.core.junit.AllTests;


public class TestMainFrame implements Scenario {
	
	
	JFrameOperator mainFrame = null;
	
//	public void testMain() throws Exception{
//    	String[] params = {"de.keag.lager.core.junit.login.TestMainFrame"};
//    	org.netbeans.jemmy.Test.main(params);
//    }
//	
    public int runIt(Object param) {
	try {
	    //Anmelden am Programm
	    anmelden();
	    
	    //Menüs checken
        pruefeMenuEintraegeNachDerLoginMaske();
        
//	    new JFrameOperator("Lager / Anmeldung2 ");
//	    new JTextComponentOperator(mainFrame, "");
	    
		AllTests.waitOneSec();
		AllTests.waitOneSec();
        
	} catch(Exception e) {
	    e.printStackTrace();
	    return(1); //Fehler zurückgeben
	}
	return(0);
    }

	private void pruefeMenuEintraegeNachDerLoginMaske() {
		JMenuBarOperator menuBar = new JMenuBarOperator(mainFrame);
        int numberOfMenusInMenuBar = menuBar.getMenuCount();
        
        if (numberOfMenusInMenuBar != 3) {
            throw new JemmyException("Menubar muss 3 Menüs enthalten:  " + numberOfMenusInMenuBar);
        }
        
        JMenuOperator jMenuSystemMenue = new JMenuOperator(menuBar.getMenu(0));
        JMenuOperator jMenuFormulareListen = new JMenuOperator(menuBar.getMenu(1));
        JMenuOperator jMenuStammdaten = new JMenuOperator(menuBar.getMenu(2));
//        JMenuOperator jMenueAbtelung = new JMenuOperator(menuBar.getMenu(2));
        
        String jLabelMenuSystemMenue = jMenuSystemMenue.getText();
        String jLabelMenuFormulareListen = jMenuFormulareListen.getText();
//        String jLabelMenuAbteilung = jMenueAbtelung.getText();
        String jLabelMenuStammdaten = jMenuStammdaten.getText();

        
        
        if (!jLabelMenuSystemMenue.equals("Systemmenü")) {
            throw new JemmyException("The label of the first menu is not " +
                                     "'Systemmenü'.");
        } else if (!jLabelMenuFormulareListen.equals("Formulare / Listen")) {
            throw new JemmyException("The label of the second menu is " +
                                     "not 'Formulare / Listen'.");
//        } else if (!jLabelMenuAbteilung.equals("Abteilung")) {
//            throw new JemmyException("The label of the third menu is not " +
//                                     "'Abteilung'.");
        } else if (!jLabelMenuStammdaten.equals("Stammdaten")) {
            throw new JemmyException("The label of the fourth menu is not " +
                                     "'Stammdaten'.");
        }
	}
    
	private void anmelden() throws InvocationTargetException,
			NoSuchMethodException, ClassNotFoundException {
		//start application
//	    new ClassReference("org.netbeans.jemmy.explorer.GUIBrowser").startApplication();
		new ClassReference("de.keag.lager.core.main.Main").startApplication();
	    
	    //wait frame
//	    new JFrameOperator("GUI Browser");
		mainFrame = new JFrameOperator("Lager / Anmeldung");
	    
	    //and couple of text components:
		JTextFieldOperator loginNameField = new JTextFieldOperator(mainFrame,0);		
        //type new value in the text field
	    loginNameField.clearText();
	    loginNameField.typeText("lager");
	    
	    //and couple of text components:
		JTextFieldOperator loginPassWortField = new JTextFieldOperator(mainFrame,1);		
        //type new value in the text field
	    loginPassWortField.clearText();
	    loginPassWortField.typeText("pass");
	    
	  //puch button
	    new JButtonOperator(mainFrame, "OK").push();
	}
	
    public static void main(String[] argv) {
	String[] params = {"de.keag.lager.core.junit.login.TestMainFrame"};
	org.netbeans.jemmy.Test.main(params);
    }
}