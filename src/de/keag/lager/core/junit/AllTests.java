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
package de.keag.lager.core.junit;

/**
 * 
 * https://jemmy.dev.java.net/
 * https://jemmy.dev.java.net/tutorial.html
 * Wie setzen jemmy 2 ein und nicht jemmy 3
 * http://www.java2s.com/Open-Source/Java-Document/IDE-Netbeans/jemmy/org/netbeans/jemmy/operators/JMenuItemOperator.java.htm
 * 
 * UI test work-flow could be broken to three kinds of steps:

    * FIND 		FIND 	FIND 			controls you need to do next action for
    * DO		DO		DO			  	something with that control
    * VERIFY 	VERIFY  VERIFYresults 	of the action


 */

import de.keag.lager.core.junit.db.connection.JUnitTestConnection;
import de.keag.lager.core.junit.login.JUnitTestMainFrame;
import de.keag.lager.core.junit.login.TestMainFrame;
import de.keag.lager.panels.frame.login.LoginJUnit;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for de.keag.lager.core.junit");
		//$JUnit-BEGIN$
		suite.addTestSuite(LoginJUnit.class);
		suite.addTestSuite(JUnitTestConnection.class);
		suite.addTestSuite(JUnitTestMainFrame.class);
		//$JUnit-END$
		return suite;
	}
	
	public static void waitOneSec() {
	     try {
	    	 	Thread.currentThread().sleep(1000);
	       }
	     catch (InterruptedException e) {
	       e.printStackTrace();
	       }
	 }  	
	
}
