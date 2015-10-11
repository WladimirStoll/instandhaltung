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
package de.keag.lager.panels.frame.wartung.pane.suche;


import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.model.WartungSuchBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import de.keag.lager.core.main.Konstanten;
import java.util.Date;
import de.keag.lager.panels.frame.bericht.Berichttyp;

public class WartungSuchPaneView extends SuchView implements IWartungSuchPaneBeobachter{

	private JToolBar jToolBar = null;
	private JButton jButtonSuchen = null;
	private JButton jButtonCancel = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelMitte = null;
	private JPanel jPanelUnten = null;
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelBaugruppe = null;
	private JComboBox jComboBoxAbteilung = null;
	private SuchController suchController = null;  //  @jve:decl-index=0:
	private IModel iModel = null;  //  @jve:decl-index=0:
	private JTextField jTextFieldBaugrupe = null;
	private JButton jButtonMatchCodeBaugruppe = null;
	private JTextField jTextFieldWartungsTyp = null;
	private JTextField jTextFieldWartungsArt = null;
	private JLabel jLabelTerminVon = null;
	private JLabel jLabelTerminBis = null;
	private JLabel jLabelStatus = null;
	private JLabel jLabelArt = null;
	private JLabel jLabelTyp = null;
	private JButton jButtonMatchCodeWartungsArt = null;
	private JButton jButtonMatchCodeWartungsTyp = null;
	private JDateChooser jPanelDatumVon = null;
	private JDateChooser jPanelTerminBis = null;
	private JComboBox jComboBoxStatus = null;
	/**
	 * This method initializes 
	 * @param iModel 
	 * 
	 */
	protected WartungSuchPaneView(WartungSuchController benutzerSuchPaneController, IModel iModel) {
		super();
		setWartungSuchPaneController(benutzerSuchPaneController);
		setiModel(iModel);
		initialize();
	}

	private void setWartungSuchPaneController(
			WartungSuchController benutzerSuchPaneController) {
		this.suchController = benutzerSuchPaneController;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.weightx = 2.0D;
        gridBagConstraints1.gridx = 1;
        GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.anchor = GridBagConstraints.WEST;
        gridBagConstraints13.gridx = 1;
        gridBagConstraints13.gridy = 6;
        gridBagConstraints13.weightx = 1.0;
        gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
        GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        gridBagConstraints14.anchor = GridBagConstraints.WEST;
        gridBagConstraints14.gridx = 1;
        gridBagConstraints14.gridy = 7;
        gridBagConstraints14.weightx = 1.0;
        gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
        jLabelStatus = new JLabel();
        jLabelStatus.setText("Status");
        jLabelTerminVon = new JLabel();
        jLabelTerminVon.setText("Termin von");
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
					WartungSuchBean benutzerSuchBean =  (WartungSuchBean)getiModel().getiSuchBean();
					if (benutzerSuchBean==null){
						benutzerSuchBean = new WartungSuchBean();
					}
					
					benutzerSuchBean.setDatumVon(getJPanelDatumVon().getDate());
					benutzerSuchBean.setDatumBis(getJPanelTerminBis().getDate());
					
					StatusEnum status = (StatusEnum) getJComboBoxStatus().getSelectedItem();
					benutzerSuchBean.setStatusEnum(status);
					
					getSuchController().suchen(benutzerSuchBean);
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
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints17.gridy = 5;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.gridwidth = 1;
			gridBagConstraints17.anchor = GridBagConstraints.WEST;
			gridBagConstraints17.gridx = 1;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 1;
			gridBagConstraints16.gridwidth = 2;
			gridBagConstraints16.anchor = GridBagConstraints.WEST;
			gridBagConstraints16.gridy = 4;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 1;
			gridBagConstraints15.gridwidth = 2;
			gridBagConstraints15.fill = GridBagConstraints.NONE;
			gridBagConstraints15.anchor = GridBagConstraints.WEST;
			gridBagConstraints15.gridy = 3;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.gridy = 7;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.gridy = 6;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.anchor = GridBagConstraints.EAST;
			gridBagConstraints10.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints10.gridy = 7;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.anchor = GridBagConstraints.EAST;
			gridBagConstraints9.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints9.gridy = 6;
			jLabelArt = new JLabel();
			jLabelArt.setText("Art");
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints8.gridy = 5;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints6.gridy = 4;
			jLabelTerminBis = new JLabel();
			jLabelTerminBis.setText("Termin bis");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints5.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.weightx = 1.0D;
			gridBagConstraints4.weighty = 0.0D;
			gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints4.gridy = 2;
			
			
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.weightx = 1.0D;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridy = 2;
			
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 0.0D;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.gridx = 1;
			
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 7;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridx = 1;
			
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints22.gridy = 6;
			gridBagConstraints22.weightx = 1.0;
			gridBagConstraints22.gridx = 1;
			
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints23.gridy = 2;
			gridBagConstraints23.weightx = 1.0;
			gridBagConstraints23.gridx = 1;
			
			jPanelMitte = new JPanel();
			jPanelMitte.setLayout(new GridBagLayout());
			jLabelBaugruppe = new JLabel();
			jLabelBaugruppe.setText("Anlage/Baugruppe");
			jPanelMitte.add(jLabelBaugruppe, gridBagConstraints4);
			jPanelMitte.add(getJButtonMatchCodeBaugruppe(), gridBagConstraints3);
			jPanelMitte.add(jLabelTerminVon, gridBagConstraints5);
			jPanelMitte.add(jLabelTerminBis, gridBagConstraints6);
			jPanelMitte.add(jLabelStatus, gridBagConstraints8);
			jPanelMitte.add(jLabelArt, gridBagConstraints9);
			jLabelTyp = new JLabel();
			jLabelTyp.setText("Typ");
			jPanelMitte.add(jLabelTyp, gridBagConstraints10);
			jPanelMitte.add(getJButtonMatchCodeWartungsArt(), gridBagConstraints11);
			jPanelMitte.add(getJButtonMatchCodeWartungsTyp(), gridBagConstraints12);
			jPanelMitte.add(getJPanelDatumVon(), gridBagConstraints15);
			jPanelMitte.add(getJPanelTerminBis(), gridBagConstraints16);
			jPanelMitte.add(getJComboBoxStatus(), gridBagConstraints17);
			jPanelMitte.add(getJTextFieldWartungsTyp(), gridBagConstraints21);
			jPanelMitte.add(getJTextFieldWartungsArt(), gridBagConstraints22);
			jPanelMitte.add(getJTextFieldBaugruppe(), gridBagConstraints23);
			
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
			gridBagConstraints2.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints2.weighty = 1.0D;
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
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




//	private void setWartungSuchPaneController(WartungSuchPaneController benutzerSuchPaneController) {
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
	 * This method initializes jTextFieldBaugrupe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBaugruppe() {
		if (jTextFieldBaugrupe == null) {
			jTextFieldBaugrupe = new JTextField();
			jTextFieldBaugrupe.setEditable(false);
			jTextFieldBaugrupe.setPreferredSize(new Dimension(100, 20));
		}
		return jTextFieldBaugrupe;
	}
	
	/**
	 * This method initializes jTextFieldBaugrupe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldWartungsArt() {
		if (jTextFieldWartungsArt == null) {
			jTextFieldWartungsArt = new JTextField();
			jTextFieldWartungsArt.setEditable(false);
			jTextFieldWartungsArt.setPreferredSize(new Dimension(100, 20));
		}
		return jTextFieldWartungsArt;
	}
	
	/**
	 * This method initializes jTextFieldBaugrupe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldWartungsTyp() {
		if (jTextFieldWartungsTyp == null) {
			jTextFieldWartungsTyp = new JTextField();
			jTextFieldWartungsTyp.setEditable(false);
			jTextFieldWartungsTyp.setPreferredSize(new Dimension(100, 20));
		}
		return jTextFieldWartungsTyp;
	}
	
	

	/**
	 * This method initializes jButtonMatchCodeBaugruppe	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeBaugruppe() {
		if (jButtonMatchCodeBaugruppe == null) {
			jButtonMatchCodeBaugruppe = new JButton();
			jButtonMatchCodeBaugruppe.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeBaugruppe.setText("...");
			jButtonMatchCodeBaugruppe
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((WartungSuchController)getSuchController()).holeBaugruppe();
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
		return jButtonMatchCodeBaugruppe;
	}

//	public void setAbteilung(BenutzerBean abteilungBean) {
//		if (abteilungBean==null){
//			abteilungBean = new BenutzerBean();
//		}
//		WartungSuchBean benutzerSuchBean =  (WartungSuchBean)getiModel().getiSuchBean();
//		if (benutzerSuchBean==null){
//			benutzerSuchBean = new WartungSuchBean();
//		}
//		benutzerSuchBean.setBenutzerBean(abteilungBean); //Im Model bekannt
//		//in der Oberfläche anzeigen
//		getJTextFieldAbteilung().setText(abteilungBean.getAbteilungName());
//	}



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

	public void setBaugruppe(BaugruppeBean bean) {
		if (bean == null) {
			bean = new BaugruppeBean();
		}
		WartungSuchBean suchBean = (WartungSuchBean) getiModel()
				.getiSuchBean();
		if (suchBean == null) {
			suchBean = new WartungSuchBean();
		}
		suchBean.setBaugruppeBean(bean);
		// in der Oberfläche anzeigen
		getJTextFieldBaugruppe().setText(bean.getName());
	}

	public void setWartungsArt(WartungsArtBean bean) {
		if (bean == null) {
			bean = new WartungsArtBean();
		}
		WartungSuchBean suchBean = (WartungSuchBean) getiModel()
				.getiSuchBean();
		if (suchBean == null) {
			suchBean = new WartungSuchBean();
		}
		suchBean.setWartungsArtBean(bean);
		// in der Oberfläche anzeigen
		getJTextFieldWartungsArt().setText(bean.getName());
	}

	public void setWartungsTyp(WartungsTypBean bean) {
		if (bean == null) {
			bean = new WartungsTypBean();
		}
		WartungSuchBean suchBean = (WartungSuchBean) getiModel()
				.getiSuchBean();
		if (suchBean == null) {
			suchBean = new WartungSuchBean();
		}
		suchBean.setWartungsTypBean(bean);
		// in der Oberfläche anzeigen
		getJTextFieldWartungsTyp().setText(bean.getName());
	}

	/**
	 * This method initializes jButtonMatchCodeWartungsArt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeWartungsArt() {
		if (jButtonMatchCodeWartungsArt == null) {
			jButtonMatchCodeWartungsArt = new JButton();
			jButtonMatchCodeWartungsArt.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeWartungsArt.setText("...");
			jButtonMatchCodeWartungsArt
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((WartungSuchController)getSuchController()).holeWartungsArt();
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
		return jButtonMatchCodeWartungsArt;
	}

	/**
	 * This method initializes jButtonMatchCodeWartungsTyp	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeWartungsTyp() {
		if (jButtonMatchCodeWartungsTyp == null) {
			jButtonMatchCodeWartungsTyp = new JButton();
			jButtonMatchCodeWartungsTyp.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeWartungsTyp.setText("...");
			jButtonMatchCodeWartungsTyp
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((WartungSuchController)getSuchController()).holeWartungsTyp();
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
		return jButtonMatchCodeWartungsTyp;
	}

	/**
	 * This method initializes jPanelDatumVon	
	 * 	
	 * @return com.toedter.calendar.JDateChooser	
	 */
	private JDateChooser getJPanelDatumVon() {
		if (jPanelDatumVon == null) {
			Date datumTerminVon = new Date();
			datumTerminVon.setTime(WartungBean.getAktuellesDatum().getTime()-(24L*60L*60L*1000L)*10);
//			datumTerminVon.setTime(datumTerminVon.getTime() - ((24L * 60L * 60L * 1000L) * 365L));
			jPanelDatumVon = new JDateChooser(Konstanten.FORMAT_DATUM, false);
			jPanelDatumVon.setLayout(new GridBagLayout());
			jPanelDatumVon.setDate(datumTerminVon);
			
		}
		return jPanelDatumVon;
	}

	/**
	 * This method initializes jPanelTerminBis	
	 * 	
	 * @return com.toedter.calendar.JDateChooser	
	 */
	private JDateChooser getJPanelTerminBis() {
		if (jPanelTerminBis == null) {
			Date datumTerminBis = new Date();
			datumTerminBis.setTime(WartungBean.getAktuellesDatum().getTime()+(24L*60L*60L*1000L)*10);
//			datumTerminBis.setTime(datumTerminBis.getTime() - ((24L * 60L * 60L * 1000L) * 365L));
			jPanelTerminBis = new JDateChooser(Konstanten.FORMAT_DATUM, false);
			jPanelTerminBis.setLayout(new GridBagLayout());
			jPanelTerminBis.setDate(datumTerminBis);
		}
		return jPanelTerminBis;
	}

	/**
	 * This method initializes jComboBoxStatus	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxStatus() {
		if (jComboBoxStatus == null) {
			jComboBoxStatus = new JComboBox(WartungBean.StatusEnum.values());
			jComboBoxStatus.setSelectedIndex(0);
		}
		return jComboBoxStatus;
	}

	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
