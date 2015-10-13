package de.keag.lager.core;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import de.keag.lager.panels.frame.MainFrame;

abstract public class JPanePanel extends JPanel implements IPanel {

	@Override
	abstract public void setContentPane(MainFrame mainFrame) ;
	
	abstract public void setBlatt(JPanel blatt) ;

}
