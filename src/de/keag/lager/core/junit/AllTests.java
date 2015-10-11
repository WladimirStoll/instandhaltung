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
