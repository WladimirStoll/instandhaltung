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
