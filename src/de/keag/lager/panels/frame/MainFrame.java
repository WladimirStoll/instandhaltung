package de.keag.lager.panels.frame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import de.keag.lager.core.main.Run;
import de.keag.lager.core.print.PrintUtilities;

import javax.swing.JMenuBar;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MainFrame extends JFrame implements ComponentListener{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Run run;
	private JMenuBar jJMenuBar = null;
	public Run getRun() {
		return run;
	}

	/**
	 * This is the default constructor
	 */
	public MainFrame(Run run) {
		super();
		this.run = run;
		this.addComponentListener(this);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
		}
		return jJMenuBar;
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Die ContenPane wird immer auf die größe des Frames gesetzt.
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		getContentPane().setSize(this.getWidth(), this.getHeight());
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void printMainFrame(){
		PrintUtilities pu = new PrintUtilities(this);
		pu.print();
	}


}


