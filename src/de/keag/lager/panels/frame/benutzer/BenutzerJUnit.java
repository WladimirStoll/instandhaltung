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
package de.keag.lager.panels.frame.benutzer;

import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JMenuItemOperator;
import org.netbeans.jemmy.operators.JMenuOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import de.keag.lager.core.junit.AllTests;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPane;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;
import de.keag.lager.panels.frame.benutzer.pane.suche.BenutzerSuchPaneView;
import junit.framework.TestCase;


public class BenutzerJUnit implements Scenario {


	JFrameOperator mainFrame = null;
	JMenuBarOperator menuBar = null;
    JMenuOperator jMenuSystemMenue = null;
    JMenuOperator jMenuFormulareListen = null;
    JMenuOperator jMenuStammdaten = null;
	
	
	@Override
    public int runIt(Object param) {
    	try {
    	    //Anmelden am Programm
    	    anmelden();
    	    
    		//menüs holen-Bar
    		menuHolen();
    	    
    	    //In die Benutzermaske wechseln
    	    wechselInDieBenutzerStammdatenMaske();
    	    
    	    //In die Benutzermaske wechseln
    	    suchViewTesten();
    	    
            
//    	    new JFrameOperator("Lager / Anmeldung2 ");
//    	    new JTextComponentOperator(mainFrame, "");

    	    
    		AllTests.waitOneSec();
    		AllTests.waitOneSec();
    		AllTests.waitOneSec();
    		AllTests.waitOneSec();
    	} catch(Exception e) {
    	    e.printStackTrace();
    	    return(1); //Wenn ungleich 0, dann ist ein Fehler aufgetreten
    	}
    	return(0); //keine Fehler, Rückmeldung = 0
     }


	private void suchViewTesten() {
		
		
	}


	private void menuHolen() {
		menuBar = new JMenuBarOperator(mainFrame);
		jMenuSystemMenue = new JMenuOperator(menuBar.getMenu(0));
		jMenuFormulareListen = new JMenuOperator(menuBar.getMenu(1));
		jMenuStammdaten = new JMenuOperator(menuBar.getMenu(2));
	}
	
	
	/**
	 * Stammdatenmaske öffnen
	 * @throws Exception 
	 */
	private void wechselInDieBenutzerStammdatenMaske() throws Exception {
		jMenuStammdaten.push();
		System.out.println(jMenuStammdaten);
		JMenuItemOperator jMenuItemOperator = null;

		
		for (int i = 0; i<jMenuStammdaten.getItemCount();i++){
			JMenuItem jMenuItem = jMenuStammdaten.getItem(i);
			if (jMenuItem.getText().equals("Benutzer")){
				jMenuItemOperator = new JMenuItemOperator(jMenuItem);
			}
//			System.out.println(jMenuStammdaten.getItem(i));
		}
		if (jMenuItemOperator==null){
			throw new Exception("MenuItem ist nicht gefunden");
		}else{
			jMenuItemOperator.push();
		}
		System.out.println("jMenuItem angeklickt:"+jMenuItemOperator);
		
	}



//	public void testAuswahlAlleBenutzer(){
////		BenutzerPaneController.getOneInstance().getLoginView().getLoginController().setzeAktuellenBenutzerAufNull();
////		BenutzerPane benutzerPane = (BenutzerSuchPaneView)BenutzerPaneController.getOneInstance().getSuchView();
//
//		BenutzerSuchPaneView benutzerSuchPaneView = (BenutzerSuchPaneView)BenutzerPaneController.getOneInstance().getSuchView();
//		benutzerSuchPaneView.g
//	}
	
	private void anmelden() throws InvocationTargetException,
			NoSuchMethodException, ClassNotFoundException {
		// start application
		// new
		// ClassReference("org.netbeans.jemmy.explorer.GUIBrowser").startApplication();
		new ClassReference("de.keag.lager.core.main.Main").startApplication();

		// wait frame
		// new JFrameOperator("GUI Browser");
		mainFrame = new JFrameOperator("Lager / Anmeldung");

		// and couple of text components:
		JTextFieldOperator loginNameField = new JTextFieldOperator(mainFrame, 0);
		// type new value in the text field
		loginNameField.clearText();
		loginNameField.typeText("lager");

		// and couple of text components:
		JTextFieldOperator loginPassWortField = new JTextFieldOperator(
				mainFrame, 1);
		// type new value in the text field
		loginPassWortField.clearText();
		loginPassWortField.typeText("pass");

		// puch button
		new JButtonOperator(mainFrame, "OK").push();
		
	}
	
}
