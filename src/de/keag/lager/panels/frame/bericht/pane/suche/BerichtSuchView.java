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
package de.keag.lager.panels.frame.bericht.pane.suche;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import de.keag.lager.core.IModel;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.formatter.LagerStringFormatter;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;
import de.keag.lager.panels.frame.bericht.BerichtSuchBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;

import java.awt.Color;
import java.sql.SQLException;
import java.util.Date;
import java.awt.Dimension;
import javax.swing.JTextField;

public class BerichtSuchView extends SuchView {

	private JToolBar jToolBar = null;
	private JButton jButtonSuchen = null;
	private JButton jButtonCancel = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelMitte = null;
	private JPanel jPanelUnten = null;
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelDruckOriginalDatum = null;
	private JLabel jLabelTyp = null;
	private JDateChooser jPanelDatumVon = null;
	private JComboBox jComboBoxTyp = null;
	private JComboBox jComboBoxArt = null;
	private JLabel jLabelDruckDatumOriginalBis = null;
	private JDateChooser jPanelDatumBis = null;
	private SuchController suchController = null;  //  @jve:decl-index=0:
	private IModel iModel = null;  //  @jve:decl-index=0:
	private JLabel jLabelArt = null;
	private JLabel jLabelBenutzer = null;
	private JTextField jTextFieldBenutzer = null;
	private JLabel jLabelBerichtID = null;
	private JFormattedTextField jTextFieldBerichtID = null;
	private JButton jButtonMatchCodeBenutzer = null;
	private JLabel jLabelTypID = null;
	private JFormattedTextField jTextFieldTypID = null;

	/**
	 * This method initializes 
	 * @param iModel 
	 * 
	 */
	protected BerichtSuchView(BerichtSuchController baSuchPaneController, IModel iModel) {
		super();
		setSuchController(baSuchPaneController);
		setiModel(iModel);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        GridBagConstraints gridBagConstraintsPanelOben = new GridBagConstraints();
        gridBagConstraintsPanelOben.gridx = 0;
        gridBagConstraintsPanelOben.gridy = 0;
        gridBagConstraintsPanelOben.anchor = GridBagConstraints.NORTH;
        gridBagConstraintsPanelOben.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsPanelOben.weightx = 1.0D;
        gridBagConstraintsPanelOben.insets = new Insets(1, 1, 1, 1);
        gridBagConstraintsPanelOben.weighty = 1.0D;
        GridBagConstraints gridBagConstraintsPanelUnten = new GridBagConstraints();
        gridBagConstraintsPanelUnten.gridx = 0;
        gridBagConstraintsPanelUnten.gridy = 2;
        gridBagConstraintsPanelUnten.anchor = GridBagConstraints.SOUTH;
        gridBagConstraintsPanelUnten.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsPanelUnten.weightx = 1.0D;
        gridBagConstraintsPanelUnten.insets = new Insets(1, 1, 1, 1);
        gridBagConstraintsPanelUnten.weighty = 1.0D;
        GridBagConstraints gridBagConstraintsPanelMitte = new GridBagConstraints();
        gridBagConstraintsPanelMitte.gridx = 0;
        gridBagConstraintsPanelMitte.gridy = 1;
        gridBagConstraintsPanelMitte.fill = GridBagConstraints.BOTH;
        gridBagConstraintsPanelMitte.weightx = 1.0D;
        gridBagConstraintsPanelMitte.insets = new Insets(1, 1, 1, 1);
        gridBagConstraintsPanelMitte.weighty = 300.0D;
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        this.add(getJPanelOben(), gridBagConstraintsPanelOben);
        this.add(getJPanelMitte(), gridBagConstraintsPanelMitte);
        this.add(getJPanelUnten(), gridBagConstraintsPanelUnten);
			
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.add(getJButtonSuchen());
			jToolBar.add(getJButtonCancel());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jButtonSuchen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSuchen() {
		if (jButtonSuchen == null) {
			jButtonSuchen = new JButton();
			jButtonSuchen.setText("Suchen");
			jButtonSuchen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!pruefeFehler()) return;
					BerichtSuchBean suchBean = new BerichtSuchBean();
					suchBean.setDruckDatumOriginalVon(getJPanelDatumVon().getDate());
					suchBean.setDruckDatumOriginalBis(getJPanelDatumBis().getDate());
					suchBean.setBerichtTyp((Berichttyp)getJComboBoxTyp().getSelectedItem());
					suchBean.setBerichtTypId((Integer)getJTextFieldTypID().getValue());
					suchBean.setBerichtArt((Berichtart)getJComboBoxArt().getSelectedItem());
//woanders!			suchBean.setAktuellerBenutzer(aktuellerBenutzer); //Wir durch Button gesetzt
					suchBean.setId((Integer)getJTextFieldBerichtID().getValue());
					getSuchController().suchen(suchBean);
				}
			});
		}
		return jButtonSuchen;
	}

	/**
	 * This method initializes jButtonCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCancel() {
		if (jButtonCancel == null) {
			jButtonCancel = new JButton();
			jButtonCancel.setText("Abbrechen");
			jButtonCancel.setVisible(false);
			jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!pruefeFehler()) return;
				}
			});
		}
		return jButtonCancel;
	}

	/**
	 * This method initializes jPanelOben	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelOben() {
		if (jPanelOben == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0D;
			gridBagConstraints.insets = new Insets(1, 1, 1, 1);
			jPanelOben = new JPanel();
			jPanelOben.setLayout(new GridBagLayout());
			jPanelOben.add(getJToolBar(), gridBagConstraints);
		}
		return jPanelOben;
	}

	/**
	 * This method initializes jPanelMitte	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelMitte() {
		if (jPanelMitte == null) {
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.BOTH;
			gridBagConstraints16.gridy = 3;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.anchor = GridBagConstraints.WEST;
			gridBagConstraints16.gridx = 1;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints15.anchor = GridBagConstraints.EAST;
			gridBagConstraints15.gridy = 3;
			jLabelTypID = new JLabel();
			jLabelTypID.setText("Typ-ID");
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 2;
			gridBagConstraints14.gridy = 5;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridy = 6;
			gridBagConstraints13.weightx = 0.0D;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.gridheight = 1;
			gridBagConstraints13.gridwidth = 1;
			gridBagConstraints13.weighty = 0.0D;
			gridBagConstraints13.gridx = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints12.gridy = 6;
			jLabelBerichtID = new JLabel();
			jLabelBerichtID.setText("Bericht-ID");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints3.gridy = 5;
			jLabelBenutzer = new JLabel();
			jLabelBenutzer.setText("Benutzer");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints1.gridy = 4;
			jLabelArt = new JLabel();
			jLabelArt.setText("Art");
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.weightx = 0.0D;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.insets = new Insets(0, 2, 2, 2);
			gridBagConstraints10.gridy = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints6.gridy = 1;
			jLabelDruckDatumOriginalBis = new JLabel();
			jLabelDruckDatumOriginalBis.setText("Druckdatum Original(bis)");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 0.0D;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.gridx = 1;
			
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 4;
			gridBagConstraints11.weightx = 0.0D;
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints11.gridwidth = 1;
			gridBagConstraints11.gridx = 1;
			
			
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 1;
			gridBagConstraints9.anchor = GridBagConstraints.WEST;
			gridBagConstraints9.fill = GridBagConstraints.NONE;
			gridBagConstraints9.weightx = 2.0D;
			gridBagConstraints9.insets = new Insets(2, 2, 0, 2);
			gridBagConstraints9.gridy = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.fill = GridBagConstraints.NONE;
			gridBagConstraints8.weightx = 1.0D;
			gridBagConstraints8.weighty = 0.0D;
			gridBagConstraints8.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints8.gridy = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.weightx = 0.0D;
			gridBagConstraints4.weighty = 0.0D;
			gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints4.gridy = 2;
			jPanelMitte = new JPanel();
			jPanelMitte.setLayout(new GridBagLayout());
			jLabelDruckOriginalDatum = new JLabel();
			jLabelDruckOriginalDatum.setText("Druckdatum Original(von)");
			jLabelTyp = new JLabel();
			jLabelTyp.setText("Typ");
			jPanelMitte.add(jLabelDruckOriginalDatum, gridBagConstraints8);
			jPanelMitte.add(jLabelTyp, gridBagConstraints4);
			jPanelMitte.add(getJPanelDatumVon(), gridBagConstraints9);
			jPanelMitte.add(jLabelDruckDatumOriginalBis, gridBagConstraints6);
			jPanelMitte.add(getJPanelDatumBis(), gridBagConstraints10);
			jPanelMitte.add(getJComboBoxTyp(), gridBagConstraints7);
			jPanelMitte.add(jLabelArt, gridBagConstraints1);
			jPanelMitte.add(getJComboBoxArt(), gridBagConstraints11);
			jPanelMitte.add(jLabelBenutzer, gridBagConstraints3);
			jPanelMitte.add(getJTextFieldBenutzer(), gridBagConstraints5);
			jPanelMitte.add(jLabelBerichtID, gridBagConstraints12);
			jPanelMitte.add(getJTextFieldBerichtID(), gridBagConstraints13);
			jPanelMitte.add(getJButtonMatchCodeBenutzer(), gridBagConstraints14);
			jPanelMitte.add(jLabelTypID, gridBagConstraints15);
			jPanelMitte.add(getJTextFieldTypID(), gridBagConstraints16);
			
		}
		return jPanelMitte;
	}

	/**
	 * This method initializes jPanelUnten	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelUnten() {
		if (jPanelUnten == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints2.weighty = 1.0D;
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints2.weightx = 1.0;
			jPanelUnten = new JPanel();
			jPanelUnten.setLayout(new GridBagLayout());
			jPanelUnten.add(getJComboBoxFehler(), gridBagConstraints2);
			jPanelUnten.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					BerichtSuchView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
				}
			});
		}
		return jPanelUnten;
	}

	/**
	 * This method initializes jComboBoxFehler	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxFehler() {
		if (jComboBoxFehler == null) {
			jComboBoxFehler = new JComboBox();
			jComboBoxFehler.addFocusListener(new java.awt.event.FocusAdapter() {
				@Override
				public void focusGained(java.awt.event.FocusEvent e) {
					if (!pruefeFehler()) return;
				}
			});
			jComboBoxFehler.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					BerichtSuchView.this.mouseClickedFehler(e, getJComboBoxFehler());
				}
			});
		}
		return jComboBoxFehler;
	}

	/**
	 * This method initializes jPanelDatumVon	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JDateChooser getJPanelDatumVon() {
		if (jPanelDatumVon == null) {
			jPanelDatumVon = new JDateChooser(Konstanten.FORMAT_DATUM,false);
			Date dieWocheBeforeDate = new Date();//heutiges Datum holen 
			dieWocheBeforeDate.setTime(dieWocheBeforeDate.getTime() - ((24L * 60L * 60L * 1000L)*365L)); //x-Tage abzeiehen
			jPanelDatumVon.setDate(dieWocheBeforeDate);
			jPanelDatumVon.setLayout(new GridBagLayout());
			jPanelDatumVon.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					if (!pruefeFehler()) return;
				}
			});
		}
		return jPanelDatumVon;
	}

	/**
	 * This method initializes jComboBoxTyp	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxTyp() {
		if (jComboBoxTyp == null) {
			
//			Berichttyp[] petAnforderungen = {
//					Berichttyp.ALLE,
//					Berichttyp.ANFORDERUNG,
//					Berichttyp.ANLAGE,
//					Berichttyp.MINDEST_BESTANDS_LISTE,
//					Berichttyp.INVENTUR_LISTE_HALLE,
//					Berichttyp.INVENTUR_LISTE_ZEILE,
//					Berichttyp.INVENTUR_LISTE_SAEULE
//					};			
			jComboBoxTyp = new JComboBox(Berichttyp.values());
			jComboBoxTyp.setSelectedIndex(0); //erster Eintrag wird angezeigt.
//			jComboBoxStatus.setPreferredSize(new Dimension(20, 40));
			jComboBoxTyp.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
//					if (!prueferFehler()) return;
				}
			});
		}
		return jComboBoxTyp;
	}
	
	/**
	 * This method initializes jComboBoxStatus	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxArt() {
		if (jComboBoxArt == null) {
			Berichtart[] petAnforderungen = {
					Berichtart.ALLE,
					Berichtart.PRINT,
					Berichtart.EMAIL
			};			
			jComboBoxArt = new JComboBox(petAnforderungen);
			jComboBoxArt.setSelectedIndex(0); //erster Eintrag wird angezeigt.
//			jComboBoxArt.setPreferredSize(new Dimension(20, 40));
			jComboBoxArt.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
//					if (!prueferFehler()) return;
				}
			});
		}
		return jComboBoxArt;
	}
	


	/**
	 * This method initializes jPanelDatumVonBis	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JDateChooser getJPanelDatumBis() {
		if (jPanelDatumBis == null) {
			jPanelDatumBis = new JDateChooser(Konstanten.FORMAT_DATUM,false);
			jPanelDatumBis.setLayout(new GridBagLayout());
			jPanelDatumBis.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					if (!pruefeFehler()) return;
				}
			});
		}
		return jPanelDatumBis;
	}

	
	@Override
	public SuchController getSuchController() {
		return suchController;
	}

	@Override
	public void setSuchController(SuchController suchController) {
		this.suchController = suchController;
	}

	@Override
	public void setStandardFocusPosition() {
			jButtonSuchen.requestFocus();
	}


	@Override
	public IModel getiModel() {
		return iModel;
	}

	@Override
	public void setiModel(IModel iModel) {
		this.iModel = iModel;
	}

	/**
	 * This method initializes jTextFieldBenutzer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBenutzer() {
		if (jTextFieldBenutzer == null) {
			jTextFieldBenutzer = new JFormattedTextField(new LagerStringFormatter(1,20));
			jTextFieldBenutzer.setInputVerifier(LagerFormate.getInputVerifier());
//			jTextFieldLoginName.addPropertyChangeListener("value", this);
			jTextFieldBenutzer.setEditable(false);
			jTextFieldBenutzer.setEnabled(true);
		}
		return jTextFieldBenutzer;
	}

	/**
	 * This method initializes jTextFieldBerichtID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldBerichtID() {
		if (jTextFieldBerichtID == null) {
			jTextFieldBerichtID = new JFormattedTextField(new LagerEmptyNumberFormatter(0, Integer.MAX_VALUE));
			jTextFieldBerichtID.setInputVerifier(LagerFormate.getInputVerifier());
		}
		return jTextFieldBerichtID;
	}

	/**
	 * This method initializes jButtonMatchCodeBenutzer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeBenutzer() {
		if (jButtonMatchCodeBenutzer == null) {
			jButtonMatchCodeBenutzer = new JButton();
			jButtonMatchCodeBenutzer.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeBenutzer.setText("...");
			jButtonMatchCodeBenutzer
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((BerichtSuchController)getSuchController()).holeBenutzer();
						}
					} catch (SQLException e1) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
						e1.printStackTrace();
					} catch (LagerException e1) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
						e1.printStackTrace();
					}
				}
			});
			
		}
		return jButtonMatchCodeBenutzer;
	}

	public void setBenutzer(BenutzerBean benutzerBean) {
		if (benutzerBean==null){
			benutzerBean = new BenutzerBean();
		}
		BerichtSuchBean suchBean =  (BerichtSuchBean)getiModel().getiSuchBean();
		if (suchBean==null){
			suchBean = new BerichtSuchBean();
		}
		suchBean.setAktuellerBenutzer(benutzerBean); //Im Model bekannt
		//in der Oberfläche anzeigen
		getJTextFieldBenutzer().setText(benutzerBean.getVorname() + " " + benutzerBean.getName());
	}

	/**
	 * This method initializes jTextFieldTypID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldTypID() {
		if (jTextFieldTypID == null) {
			jTextFieldTypID = new JFormattedTextField(new LagerEmptyNumberFormatter(0, Integer.MAX_VALUE));
			jTextFieldTypID.setInputVerifier(LagerFormate.getInputVerifier());
		}
		return jTextFieldTypID;
	}

	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
