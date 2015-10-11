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

import org.netbeans.jemmy.*;
import org.netbeans.jemmy.explorer.*;
import org.netbeans.jemmy.operators.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.*;

/**
 * This is the fourth test of the GUI assignment. 
 *
 * Last updated: This is the original program that was used during
 * the spring 2004 course. It is not documented when the program
 * code was last changed but probably in February 2004. This comment
 * was added April 20, 2004.
 *
 * @author Janne Auvinen
 */
public class Tester24 implements Scenario {

//    StockControlApplication vh;

    public int runIt(Object param) {
	switch(part) {
	case 1:
	    return testOne();
	case 2:
	    return testTwo();
	case 3:
	    return testThree();
	}
	return 0;
    }

    private int testOne() {
        try {
            ClassReference applicationReference =
                new ClassReference("StockControlApplication");
            
            applicationReference.startApplication();
            
            JFrameOperator mainWindow =
                new JFrameOperator("Stock Control Application v0.0.1");
            
            JMenuBarOperator menuBar = new JMenuBarOperator(mainWindow);
            
            int numberOfMenusInMenuBar = menuBar.getMenuCount();
            
            if (numberOfMenusInMenuBar != 4) {
                throw new JemmyException("The menu bar should contain four " +
                                         "menus.");
            }
            
            JMenuOperator menuOne = new JMenuOperator(menuBar.getMenu(0));
            JMenuOperator menuTwo = new JMenuOperator(menuBar.getMenu(1));
            JMenuOperator menuThree = new JMenuOperator(menuBar.getMenu(2));
            JMenuOperator menuFour = new JMenuOperator(menuBar.getMenu(3));
            
            String labelOfMenuOne = menuOne.getText();
            String labelOfMenuTwo = menuTwo.getText();
            String labelOfMenuThree = menuThree.getText();
            String labelOfMenuFour = menuFour.getText();
            
            if (!labelOfMenuOne.equals("File")) {
                throw new JemmyException("The label of the first menu is not " +
                                         "'File'.");
            } else if (!labelOfMenuTwo.equals("Order")) {
                throw new JemmyException("The label of the second menu is " +
                                         "not 'Order'.");
            } else if (!labelOfMenuThree.equals("Product")) {
                throw new JemmyException("The label of the third menu is not " +
                                         "'Product'.");
            } else if (!labelOfMenuFour.equals("Help")) {
                throw new JemmyException("The label of the fourth menu is not " +
                                         "'Help'.");
            }
            
            int numberOfComponentsOfMenuOne = menuOne.getMenuComponentCount();
            int numberOfComponentsOfMenuTwo = menuTwo.getMenuComponentCount();
            int numberOfComponentsOfMenuThree =
                menuThree.getMenuComponentCount();
            int numberOfComponentsOfMenuFour = menuFour.getMenuComponentCount();
            
            if (numberOfComponentsOfMenuOne != 1 && numberOfComponentsOfMenuOne != 2) {
                throw new JemmyException("'File'-menu should contain one " +
                                         "component.");
            } else if (numberOfComponentsOfMenuTwo != 1) {
                throw new JemmyException("'Order'-menu should contain one " +
                                         "component.");
            } else if (numberOfComponentsOfMenuThree != 3) {
                throw new JemmyException("'Product'-menu should contain three " +
                                         "components.");
            } else if (numberOfComponentsOfMenuFour != 3) {
                throw new JemmyException("'Help'-menu should contain three " +
                                         "components.");
            }
            
            JMenuItemOperator menuItemOneOfMenuOne =
                new JMenuItemOperator(menuOne.getItem(numberOfComponentsOfMenuOne-1));
            JMenuItemOperator menuItemOneOfMenuTwo =
                new JMenuItemOperator(menuTwo.getItem(0));
            JMenuItemOperator menuItemOneOfMenuThree =
                new JMenuItemOperator(menuThree.getItem(0));
            JMenuItemOperator menuItemTwoOfMenuThree =
                new JMenuItemOperator(menuThree.getItem(1));
            JMenuItemOperator menuItemThreeOfMenuThree =
                new JMenuItemOperator(menuThree.getItem(2));
            JMenuItemOperator menuItemOneOfMenuFour =
                new JMenuItemOperator(menuFour.getItem(0));
            JMenuItemOperator menuItemThreeOfMenuFour =
                new JMenuItemOperator(menuFour.getItem(2));
            
            String textOfMenuItemOneOfMenuOne = menuItemOneOfMenuOne.getText();
            String textOfMenuItemOneOfMenuTwo = menuItemOneOfMenuTwo.getText();
            String textOfMenuItemOneOfMenuThree =
                menuItemOneOfMenuThree.getText();
            String textOfMenuItemTwoOfMenuThree =
                menuItemTwoOfMenuThree.getText();
            String textOfMenuItemThreeOfMenuThree =
                menuItemThreeOfMenuThree.getText();
            String textOfMenuItemOneOfMenuFour =
                menuItemOneOfMenuFour.getText();
            String textOfMenuItemThreeOfMenuFour =
                menuItemThreeOfMenuFour.getText();
            
            if (!textOfMenuItemOneOfMenuOne.equals("Exit")) {
                throw new JemmyException("The menu item in 'File'-menu " +
                                         "should have 'Exit'-text.");
            } else if (!textOfMenuItemOneOfMenuTwo.equals("Handle...")) {
                throw new JemmyException("The menu item in 'Order'-menu " +
                                         "should have 'Handle...'-text.");
            } else if (!textOfMenuItemOneOfMenuThree.equals("Add New...")) {
                throw new JemmyException("The first menu item in 'Product'-" +
                                         "menu should have 'Add New...'-text.");
            } else if (!textOfMenuItemTwoOfMenuThree.
                       equals("Change Info...")) {
                throw new JemmyException("The second menu item in 'Product'-" +
                                         "menu should have 'Change Product " +
                                         "Data...'-text.");
            } else if (!textOfMenuItemThreeOfMenuThree.equals("Delete")) {
                throw new JemmyException("The third menu item in 'Product'-" +
                                         "menu should have 'Delete'-text.");
            } else if (!textOfMenuItemOneOfMenuFour.equals("Help Topics")) {
                throw new JemmyException("The first menu item in 'Help'-menu " +
                                         "should have 'Help Topics'-text.");
            } else if (!textOfMenuItemThreeOfMenuFour.
                           equals("About Stock Control...")) {
                throw new JemmyException("The third menu item in 'Help'-menu " +
                                         "should have 'About Stock " +
                                         "Control...'-text.");
            }
            
            Component menuComponentTwoOfMenuFour = menuFour.getMenuComponent(1);
            
            Class classOfmenuComponentTwoOfMenuFour =
                menuComponentTwoOfMenuFour.getClass();
            
            String nameOfClassOfMenuComponentTwoOfMenuFour =
                classOfmenuComponentTwoOfMenuFour.getName();
            
	    if ((!nameOfClassOfMenuComponentTwoOfMenuFour.
		 equals("javax.swing.JPopupMenu$Separator")) && 
		(!nameOfClassOfMenuComponentTwoOfMenuFour.
		 equals("javax.swing.JSeparator"))) {
	      throw new JemmyException("Second menu item in 'Help'-menu " +
				       "should be a separator.");
            }
        } catch(Exception e) {
            e.printStackTrace();
	    return 1;
	}
        
	System.out.println(additionalText + 
			   "Menus are ok");

        return 0;
    }
    
    private int testTwo() {
        try {
            ClassReference applicationReference =
                new ClassReference("StockControlApplication");
            
            applicationReference.startApplication();
            
            JFrameOperator mainWindow =
                new JFrameOperator("Stock Control Application v0.0.1");
            
            JMenuBarOperator menuBar = new JMenuBarOperator(mainWindow);
            
            JMenuOperator menuThree = new JMenuOperator(menuBar.getMenu(2));
            
            menuThree.pushMenuNoBlock("Product/Change Info...", "/");
            
            JDialogOperator changeProductDataDialog =
                new JDialogOperator("Change Product Information");
        } catch(Exception e) {
            e.printStackTrace();
	    return(1);
	}
        
	System.out.println(additionalText + 
			   "Change dialog is opened from the menu");

        return(0);
    }

    protected void findAllRequiredAtomicComponents(Container parent) {
        Component component;
        int i;
        
        for (i = 0; i <  parent.getComponentCount(); i++) {
            component = parent.getComponent(i);
            
            int numberOfAccessibleChildrenOfComponent =
                SwingUtilities.getAccessibleChildrenCount(component);
            
            Class classOfComponent = component.getClass();
            
            String nameOfClassOfComponent = classOfComponent.getName();
            
            if (numberOfAccessibleChildrenOfComponent > 0) {
                findAllRequiredAtomicComponents((Container)component);
            }
	    
	    System.out.println("KOMPONENTTI: " + nameOfClassOfComponent);
            
            if (nameOfClassOfComponent.equals("javax.swing.JButton")) {
                if ( ((JButton)component).getText().equals("Yes") )
		    this.yesButton = (JButton)component;
		else 
		    this.noButton = (JButton)component;
            } 

	    
        }
    }

    private JButton yesButton;
    private JButton noButton;

    private void checkExitConfirmationDialog(JDialogOperator exitConfirmationDialog) {
	
	findAllRequiredAtomicComponents(exitConfirmationDialog.getContentPane());

	ContainerOperator contentPaneOfExitConfirmationDialog =
            new ContainerOperator(exitConfirmationDialog.getContentPane());
        
        Component firstComponentOfContentPaneOfExitConfirmationDialog =
            contentPaneOfExitConfirmationDialog.getComponent(0);
        
        Class classOfFirstComponentOfContentPaneOfExitConfirmationDialog =
            firstComponentOfContentPaneOfExitConfirmationDialog.getClass();
        
        String nameOfClassOfFirstComponentOfContentPaneOfExitConfirmationDialog =
            classOfFirstComponentOfContentPaneOfExitConfirmationDialog.getName();
        
        if (!nameOfClassOfFirstComponentOfContentPaneOfExitConfirmationDialog.
            equals("javax.swing.JOptionPane")) {
            throw new JemmyException("'Exit Confirmation'-dialog should " +
                                     "be created using a JOptionPane-" +
                                     "class.");
        }
        
	System.out.println(additionalText + 
			   "JOptionPane is used to create the confirmation dialog");

        JOptionPane optionPaneOfExitConfirmationDialog =
            (JOptionPane)firstComponentOfContentPaneOfExitConfirmationDialog;
        
        Object messageOfOptionPaneOfExitConfirmationDialog =
            optionPaneOfExitConfirmationDialog.getMessage();
        int messageTypeOfOptionPaneOfExitConfirmationDialog =
            optionPaneOfExitConfirmationDialog.getMessageType();
        int optionTypeOfOptionPaneOfExitConfirmationDialog =
            optionPaneOfExitConfirmationDialog.getOptionType();
        
        Class classOfMessageOfOptionPaneOfExitConfirmationDialog =
            messageOfOptionPaneOfExitConfirmationDialog.getClass();
        
        
        if (optionTypeOfOptionPaneOfExitConfirmationDialog ==
                   optionPaneOfExitConfirmationDialog.YES_NO_OPTION) {
            System.out.println(additionalText +
				"Exit Confirmation-dialog  " +
				"contain two buttons and their " +
				"labels are 'Yes' and 'No'.");
	} else {
	    throw new JemmyException("Dialog is not a yes/no dialog");
	}


        if (messageTypeOfOptionPaneOfExitConfirmationDialog ==
	    optionPaneOfExitConfirmationDialog.QUESTION_MESSAGE) {
            System.out.println(additionalText +
			       "The type of the message of Exit " +
			       "Confirmation-dialog is a " +
			       "question message.");
        } else {
	    throw new JemmyException("Type of the dialog is not a question message");
	}
    }

    private int testThree() {
	
        try {
            ClassReference applicationReference =
                new ClassReference("StockControlApplication");
            
            applicationReference.startApplication();
            
            JFrameOperator mainWindow =
                new JFrameOperator("Stock Control Application v0.0.1");
            
            JButtonOperator exitButton =
                new JButtonOperator(mainWindow, "Exit");
            
            JMenuBarOperator menuBar = new JMenuBarOperator(mainWindow);
            
            JMenuOperator menuOne = new JMenuOperator(menuBar.getMenu(0));
            

	    exitButton.push();
            JDialogOperator exitConfirmationDialog =
                new JDialogOperator((JDialog)mainWindow.getOwnedWindows()[0]);
            checkExitConfirmationDialog(exitConfirmationDialog);
            noButton.doClick();


            menuOne.pushMenuNoBlock("File/Exit", "/");
	    exitConfirmationDialog =
                new JDialogOperator((JDialog)mainWindow.getOwnedWindows()[0]);
            checkExitConfirmationDialog(exitConfirmationDialog);
            noButton.doClick();
            
	    System.out.println(additionalText + 
			       "Confirmation comes from the menu and from the exit button");
	    
            
	    mainWindow.close();            	    
	    System.out.println(additionalText + 
			       "Upper right corner doesn't immediatelly close this application");


	    exitConfirmationDialog =
                new JDialogOperator((JDialog)mainWindow.getOwnedWindows()[0]);
            checkExitConfirmationDialog(exitConfirmationDialog);
	    JButtonOperator yesButtonOp = new JButtonOperator(yesButton);
            //yesButtonOp.push();
            
	    
	    //mainWindow.close();            	    
	    System.out.println(additionalText + 
			       "All the tests are done");


        } catch(Exception e) {
            e.printStackTrace();
	    return(1);
	}
        
        return(0);
    }

    private static String additionalText = "";
    private static int part = 10;

    public static void main(String[] args) { // throws IOException
	
	String[] params = {"Tester24"};

	if ( args.length > 1 )
	    additionalText = args[1];
	    
	if ( args.length > 0 ) {
	    part = Integer.parseInt(args[0]);
	    org.netbeans.jemmy.Test.main(params);
	}

	System.out.println("usage: java -classpath .:jemmy.jar Tester24 testcase\n" +
			   "testcase is 1, 2 or 3");

    }
}