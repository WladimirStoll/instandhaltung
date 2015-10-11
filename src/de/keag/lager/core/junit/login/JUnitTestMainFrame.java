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
