/**
 */

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