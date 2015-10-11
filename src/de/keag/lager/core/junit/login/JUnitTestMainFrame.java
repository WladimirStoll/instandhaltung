package de.keag.lager.core.junit.login;

import de.keag.lager.panels.frame.login.LoginController;
import junit.framework.TestCase;

public class JUnitTestMainFrame extends TestCase {

	
	public void testMainFrame(){
//		//Variante 1
//		TestMainFrame.main(null);
//		
//		//Variante 2
//		String[] params = {"de.keag.lager.core.junit.TestMainFrame"};
//		org.netbeans.jemmy.Test.main(params);
//		
//		//Variante 3
//		String[] params = {"de.keag.lager.core.junit.TestMainFrame"};
//		org.netbeans.jemmy.Test.run(params);
		
//		//Variante 4
//		String[] params = {"de.keag.lager.core.junit.TestMainFrame"};
//		int ergebnis = org.netbeans.jemmy.Test.run(params);
//		System.out.println("Fehler sind vorhanden:"+(ergebnis!=0));
//		assertEquals(true, ergebnis==0);
		
		//Variante 5
		String[] params = {"de.keag.lager.core.junit.login.TestMainFrame"}; //Diese Jemmy-Scenario-Klassen wird ausgeführt
		assertEquals(0, org.netbeans.jemmy.Test.run(params));
	}
	
	public void testBenutzerJUnit(){
		//Variante 
		String[] params = {"de.keag.lager.panels.frame.benutzer.BenutzerJUnit"}; //Diese Jemmy-Scenario-Klassen wird ausgeführt
		assertEquals(0, org.netbeans.jemmy.Test.run(params));
	}
		
	
	
	
}
