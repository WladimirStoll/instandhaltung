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
package de.keag.lager.panels.frame.halle.pane.suche;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import de.keag.lager.core.IModel;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.formatter.LagerStringFormatter;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageSuchBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HalleSuchPaneView extends SuchView implements IHalleSuchPaneBeobachter{

	private JToolBar jToolBar = null;
	private JButton jButtonSuchen = null;
	private JButton jButtonCancel = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelMitte = null;
	private JPanel jPanelUnten = null;
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelAbteilung = null;
	private JComboBox jComboBoxAbteilung = null;
	private SuchController suchController = null;  //  @jve:decl-index=0:
	private IModel iModel = null;  //  @jve:decl-index=0:
	private JButton jButtonMatchCodeAbteilung = null;
	private JLabel jLabelArtikel = null;
	private JLabel jLabelHalle = null;
	private JLabel jLabelEtage = null;
	private JButton jButtonMatchCodeHalle = null;
	private JButton jButtonMatchCodeEtage = null;
	private JButton jButtonMatchCodeArtikel = null;
	private JFormattedTextField jFormattedTextFieldHalle = null;
	private JFormattedTextField jFormattedTextFieldEtage = null;
	private JFormattedTextField jFormattedTextFieldAbteilung = null;
	private JFormattedTextField jFormattedTextFieldArtikel = null;
	private JLabel jLabelLeerUnten = null;
	private JLabel jLabelLeerOben = null;
	/**
	 * This method initializes 
	 * @param iModel 
	 * 
	 */
	protected HalleSuchPaneView(HalleSuchController benutzerSuchPaneController, IModel iModel) {
		super();
		setHalleSuchPaneController(benutzerSuchPaneController);
		setiModel(iModel);
		initialize();
	}

	private void setHalleSuchPaneController(
			HalleSuchController benutzerSuchPaneController) {
		this.suchController = benutzerSuchPaneController;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 5;
        jLabelArtikel = new JLabel();
        jLabelArtikel.setText("Artikel");
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
        gridBagConstraintsPanelUnten.gridy = 4;
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
        this.setSize(new Dimension(367, 350));
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
	 * Übernahme aller Daten in SuchBean und anschliessendes Suchen	
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
					HalleSuchBean halleSuchBean =  (HalleSuchBean)getiModel().getiSuchBean();
					if (halleSuchBean==null){
						halleSuchBean = new HalleSuchBean();
					}
//                    //Zeile					
//					Integer zeilenNummer;
//					if (getJFormattedTextFieldZeile().getValue()==null){
//						zeilenNummer = 0;
//					}else{
//						zeilenNummer = ((Long) getJFormattedTextFieldZeile()
//								.getValue()).intValue();
//					}
//					halleSuchBean.setZeileBean(new ZeileBean(
//							new Integer(0),
//							zeilenNummer, null, null, null));
//					//Säule
//					Integer saueleNummer;
//					if (getJFormattedTextFieldSaeule().getValue()==null){
//						saueleNummer = 0;
//					}else{
//						saueleNummer = ((Long) getJFormattedTextFieldSaeule()
//								.getValue()).intValue();
//					}
//					halleSuchBean
//							.setSaeuleBean(new SaeuleBean(
//									new Integer(0),
//									saueleNummer, null));
//					//Ebene
//					Integer ebeneNummer;
//					if (getJFormattedTextFieldEbene().getValue()==null){
//						ebeneNummer = 0;
//					}else{
//						ebeneNummer = ((Long) getJFormattedTextFieldEbene()
//								.getValue()).intValue();
//					}
//					halleSuchBean.setEbeneBean(new EbeneBean(
//							new Integer(0),
//							ebeneNummer, null));
//					//PositionNummer
//					Integer positionNummer;
//					if (getJFormattedTextFieldPosition().getValue()==null){
//						positionNummer = 0;
//					}else{
//						positionNummer = ((Long) getJFormattedTextFieldPosition()
//								.getValue()).intValue();
//					}
//					halleSuchBean.setPositionBean(new PositionBean(
//							new Integer(0),	positionNummer, null));
//					halleSuchBean.setErstellungsDatumVon(getJPanelDatumVon().getDate());
//					halleSuchBean.setErstellungsDatumBis(getJPanelDatumBis().getDate());
//					halleSuchBean.setAnforderungStatus((AnforderungStatus)getJComboBoxStatus().getSelectedItem());
//					
					getSuchController().suchen(halleSuchBean);
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
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.gridy = 0;
			jLabelLeerOben = new JLabel();
			jLabelLeerOben.setText(" ");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 13;
			jLabelLeerUnten = new JLabel();
			jLabelLeerUnten.setText(" ");
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints27.gridy = 10;
			gridBagConstraints27.weightx = 1.0;
			gridBagConstraints27.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints27.gridx = 1;
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints26.gridy = 9;
			gridBagConstraints26.weightx = 1.0;
			gridBagConstraints26.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints26.gridx = 1;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints21.gridx = 1;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints20.gridy = 1;
			gridBagConstraints20.weightx = 2.0D;
			gridBagConstraints20.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints20.gridx = 1;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 2;
			gridBagConstraints18.anchor = GridBagConstraints.WEST;
			gridBagConstraints18.gridy = 10;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 2;
			gridBagConstraints17.anchor = GridBagConstraints.WEST;
			gridBagConstraints17.gridy = 2;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 2;
			gridBagConstraints16.weightx = 1.0D;
			gridBagConstraints16.anchor = GridBagConstraints.WEST;
			gridBagConstraints16.gridy = 1;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.anchor = GridBagConstraints.EAST;
			gridBagConstraints15.gridy = 10;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.anchor = GridBagConstraints.EAST;
			gridBagConstraints14.gridy = 1;
			gridBagConstraints14.weightx = 1.0D;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridy = 2;
			jLabelEtage = new JLabel();
			jLabelEtage.setText("Etage");
			jLabelEtage.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelHalle = new JLabel();
			jLabelHalle.setText("Halle");
			jLabelHalle.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridy = 9;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 0.0D;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.weightx = 0.0D;
			gridBagConstraints4.weighty = 0.0D;
			gridBagConstraints4.gridy = 9;
			jPanelMitte = new JPanel();
			jPanelMitte.setLayout(new GridBagLayout());
			jLabelAbteilung = new JLabel();
			jLabelAbteilung.setText("Abteilung");
			jLabelAbteilung.setHorizontalAlignment(SwingConstants.RIGHT);
			jPanelMitte.add(jLabelHalle, gridBagConstraints14);
			jPanelMitte.add(jLabelEtage, gridBagConstraints6);
			jPanelMitte.add(jLabelArtikel, gridBagConstraints15);
			jPanelMitte.add(jLabelAbteilung, gridBagConstraints4);
			jPanelMitte.add(getJButtonMatchCodeAbteilung(), gridBagConstraints3);
			jPanelMitte.add(getJButtonMatchCodeHalle(), gridBagConstraints16);
			jPanelMitte.add(getJButtonMatchCodeEtage(), gridBagConstraints17);
			jPanelMitte.add(getJButtonMatchCodeArtikel(), gridBagConstraints18);
			jPanelMitte.add(getJFormattedTextFieldHalle(), gridBagConstraints20);
			jPanelMitte.add(getJFormattedTextFieldEtage(), gridBagConstraints21);
			jPanelMitte.add(getJFormattedTextFieldAbteilung(), gridBagConstraints26);
			jPanelMitte.add(getJFormattedTextFieldArtikel(), gridBagConstraints27);
			jPanelMitte.add(jLabelLeerUnten, gridBagConstraints1);
			jPanelMitte.add(jLabelLeerOben, gridBagConstraints30);
			
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
				public void focusGained(java.awt.event.FocusEvent e) {
					if (!pruefeFehler()) return;
				}
			});
		}
		return jComboBoxFehler;
	}




//	private void setHalleSuchPaneController(HalleSuchPaneController benutzerSuchPaneController) {
//		this.benutzerSuchPaneController = benutzerSuchPaneController;
//	}

	@Override
	public void zeichneDich(ISuchBean iSuchBean) {
		//Log.log().severe(" Nicht implemnentiert! ");
		//TODO
	}

	@Override
	public void setStandardFocusPosition() {
			jButtonSuchen.requestFocus();
	}



	/**
	 * This method initializes jButtonMatchCodeAbteilung	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeAbteilung() {
		if (jButtonMatchCodeAbteilung == null) {
			jButtonMatchCodeAbteilung = new JButton();
			jButtonMatchCodeAbteilung.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeAbteilung.setText("...");
			jButtonMatchCodeAbteilung
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((HalleSuchController)getSuchController()).holeAbteilung();
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
		return jButtonMatchCodeAbteilung;
	}

	public void setEtage(EtageBean etageBean) {
		if (etageBean==null){
			etageBean = new EtageBean();
		}
		HalleSuchBean halleSuchBean =  (HalleSuchBean)getiModel().getiSuchBean();
		if (halleSuchBean==null){
			halleSuchBean = new HalleSuchBean();
		}
		halleSuchBean.setEtageBean(etageBean); //Im Model bekannt
		//in der Oberfläche anzeigen
		getJFormattedTextFieldEtage().setText(etageBean.getName());
	}



	@Override
	public void setSuchController(SuchController suchController) {
		this.suchController = suchController;
	}
	
	@Override
	public SuchController getSuchController() {
		return suchController;
	}
	

	@Override
	public void setiModel(IModel iModel) {
		this.iModel = iModel;
		
	}

	
	
	@Override
	public IModel getiModel() {
		return iModel;
	}

	/**
	 * This method initializes jButtonMatchCodeHalle	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeHalle() {
		if (jButtonMatchCodeHalle == null) {
			jButtonMatchCodeHalle = new JButton();
			jButtonMatchCodeHalle.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeHalle.setText("...");
			jButtonMatchCodeHalle.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((HalleSuchController)getSuchController()).holeHalle();
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
		return jButtonMatchCodeHalle;
	}

	/**
	 * This method initializes jButtonMatchCodeEtage	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeEtage() {
		if (jButtonMatchCodeEtage == null) {
			jButtonMatchCodeEtage = new JButton();
			jButtonMatchCodeEtage.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeEtage.setText("...");
			jButtonMatchCodeEtage.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((HalleSuchController)getSuchController()).holeEtage();
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
		return jButtonMatchCodeEtage;
	}

	/**
	 * This method initializes jButtonMatchCodeArtikel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeArtikel() {
		if (jButtonMatchCodeArtikel == null) {
			jButtonMatchCodeArtikel = new JButton();
			jButtonMatchCodeArtikel.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeArtikel.setText("...");
			jButtonMatchCodeArtikel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((HalleSuchController)getSuchController()).holeArtikel();
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
		return jButtonMatchCodeArtikel;
	}

	/**
	 * This method initializes jTextFieldHalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldHalle() {
		if (jFormattedTextFieldHalle == null) {
			jFormattedTextFieldHalle = new JFormattedTextField();
			jFormattedTextFieldHalle.setEditable(false);
		}
		return jFormattedTextFieldHalle;
	}

	/**
	 * This method initializes jFormattedTextFieldEtage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldEtage() {
		if (jFormattedTextFieldEtage == null) {
			jFormattedTextFieldEtage = new JFormattedTextField();
			jFormattedTextFieldEtage.setEditable(false);
		}
		return jFormattedTextFieldEtage;
	}



	/**
	 * This method initializes jFormattedTextFieldAbteilung	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldAbteilung() {
		if (jFormattedTextFieldAbteilung == null) {
			jFormattedTextFieldAbteilung = new JFormattedTextField();
			jFormattedTextFieldAbteilung.setEditable(false);
		}
		return jFormattedTextFieldAbteilung;
	}

	/**
	 * This method initializes jFormattedTextFieldArtikel	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldArtikel() {
		if (jFormattedTextFieldArtikel == null) {
			jFormattedTextFieldArtikel = new JFormattedTextField();
			jFormattedTextFieldArtikel.setEditable(false);
		}
		return jFormattedTextFieldArtikel;
	}

	/**
	 * This method initializes jPanelEinlagerungdatum	
	 * 	
	 * @return javax.swing.JPanel	
	 */
//	private JDateChooser getJPanelEinlagerungdatum() {
//		if (jPanelEinlagerungdatum == null) {
//			Date dieWocheBeforeDate = new Date();//heutiges Datum holen 
//			dieWocheBeforeDate.setTime(dieWocheBeforeDate.getTime() - ((24L * 60L * 60L * 1000L)*365L)); //x-Tage abzeiehen
//			jPanelEinlagerungdatum.setLayout(new GridBagLayout());
//			
//		}
//		return jPanelEinlagerungdatum;
//	}

	public void setAbteilung(AbteilungBean abteilungBean) {
		if (abteilungBean==null){
			abteilungBean = new AbteilungBean();
		}
		HalleSuchBean suchBean =  (HalleSuchBean)getiModel().getiSuchBean();
		if (suchBean==null){
			suchBean = new HalleSuchBean();
		}
		suchBean.setAbteilung(abteilungBean); //Im Model bekannt
		getJFormattedTextFieldAbteilung().setText(abteilungBean.getAbteilungName());
	}

	public void setHalle(HalleBean halleBean) {
		if (halleBean==null){
			halleBean = new HalleBean();
		}
		HalleSuchBean suchBean =  (HalleSuchBean)getiModel().getiSuchBean();
		if (suchBean==null){
			suchBean = new HalleSuchBean();
		}
		suchBean.setHalleBean(halleBean); //Im Model bekannt
		getJFormattedTextFieldHalle().setText(halleBean.getName());
	}

	public void setArtikel(ArtikelBean artikelBean) {
		if (artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		HalleSuchBean suchBean =  (HalleSuchBean)getiModel().getiSuchBean();
		if (suchBean==null){
			suchBean = new HalleSuchBean();
		}
		suchBean.setArtikelBean(artikelBean); //Im Model bekannt
		getJFormattedTextFieldArtikel().setText(artikelBean.getBezeichnung());
	}

//	public void setBenutzer(BenutzerBean benutzerBean) {
//		if (benutzerBean==null){
//			benutzerBean = new BenutzerBean();
//		}
//		HalleSuchBean suchBean =  (HalleSuchBean)getiModel().getiSuchBean();
//		if (suchBean==null){
//			suchBean = new HalleSuchBean();
//		}
//		suchBean.setEinlagerungsBenutzer(benutzerBean); //Im Model bekannt
//		getJFormattedTextFieldEinlagerungsBenutzer().setText(benutzerBean.toString());
//	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
