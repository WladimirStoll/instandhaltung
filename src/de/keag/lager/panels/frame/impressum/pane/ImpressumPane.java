package de.keag.lager.panels.frame.impressum.pane;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import de.keag.lager.core.IPanel;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.MainFrame;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ImpressumPane extends JPanel implements IPanel  {
	
	private JTextPane jTextPaneImpressum = null;
	private JTextPane jTextPaneLizenz = null;
	private JButton jButtonZurueck = null;
	private ImpressumPaneController impressumPaneController = null;

	public ImpressumPane(ImpressumPaneController impressumPaneController) {
		super();
		this.impressumPaneController = impressumPaneController; 
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = GridBagConstraints.BOTH;
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.insets = new Insets(2, 2, 2, 2);
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.fill = GridBagConstraints.BOTH;
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        gridBagConstraints1.anchor = GridBagConstraints.NORTH;
        gridBagConstraints1.insets = new Insets(2, 2, 2, 2);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 1;
        this.add(getJButtonZurueck(), gridBagConstraints);
//        this.add(getJTextPaneImpressum(), gridBagConstraints1);
        this.add(getJTextPaneLizenz(), gridBagConstraints2);
			
	}

	@Override
	public void setContentPane(MainFrame mainFrame) {
		mainFrame.setTitle(Konstanten.UEBERSCHRIFT_ANWENDUNG + Konstanten.SLASH + Konstanten.UEBERSCHRIFT_IMPRESSUM);
		mainFrame.getJMenuBar().removeAll();
		mainFrame.setContentPane(this);
		mainFrame.getJMenuBar().setVisible(false);
		Zugriffsrechtpruefung.getOneInstance().jComponentCheck(mainFrame);
		mainFrame.validate();
	}

	/**
	 * This method initializes jTextPaneImpressum	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPaneImpressum() {
		if (jTextPaneImpressum == null) {
			jTextPaneImpressum = new JTextPane();
			jTextPaneImpressum.setText("IMPRESSUM \n\nTitel: Diplom-Informatiker(FH) \nVorname: Wladimir \nNachname: Stoll \n\nEichendorffweg 6  \n87437 Kempten \n\nTel.: 0831/5257179 \nwladimir.stoll@gmx.de");
		}
		return jTextPaneImpressum;
	}

	/**
	 * This method initializes jTextPaneLizenz	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPaneLizenz() {
		if (jTextPaneLizenz == null) {
			jTextPaneLizenz = new JTextPane();
			jTextPaneLizenz.setText("LIZENZ \n\nDas Programm \"Instandhaltung\" wird unter der \nGNU General Public License (GPLv2) Version 2, June 1991 \nentwickelt und vertrieben.  \n\nHier finden Sie die weiteren Infos zu der Lizenz:\nhttp://www.gnu.org/licenses/gpl.html \nhttp://www.gnu.org/licenses/gpl-2.0.html \n\nDer Lizenz-Text befindet sich auch \nauf der Internetseite, von welcher das Programm \ngestartet wurde.");
		}
		return jTextPaneLizenz;
	}

	/**
	 * This method initializes jButtonZurueck	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonZurueck() {
		if (jButtonZurueck == null) {
			jButtonZurueck = new JButton();
			jButtonZurueck.setText("Zurück zum Hauptmenü");
			jButtonZurueck.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ImpressumPane.this.impressumPaneController.zurueckZumHauptmenue();
				}
			});
		}
		return jButtonZurueck;
	}

}
