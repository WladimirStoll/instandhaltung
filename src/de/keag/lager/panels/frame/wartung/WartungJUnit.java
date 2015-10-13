package de.keag.lager.panels.frame.wartung;

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
import de.keag.lager.panels.frame.wartung.pane.WartungPane;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;
import de.keag.lager.panels.frame.wartung.pane.suche.WartungSuchPaneView;
import junit.framework.TestCase;


public class WartungJUnit implements Scenario {


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
////		WartungPaneController.getOneInstance().getLoginView().getLoginController().setzeAktuellenBenutzerAufNull();
////		WartungPane benutzerPane = (WartungSuchPaneView)WartungPaneController.getOneInstance().getSuchView();
//
//		WartungSuchPaneView benutzerSuchPaneView = (WartungSuchPaneView)WartungPaneController.getOneInstance().getSuchView();
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
