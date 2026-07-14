package de.keag.lager.panels.frame.baugruppe.pane.suche;


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
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JTextField;

public class BaugruppeSuchPaneView extends SuchView implements IBaugruppeSuchPaneBeobachter{

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
	private JTextField jTextFieldBaugruppe = null;
	private JButton jButtonMatchCodeBaugruppe = null;
	private JTextField jTextFieldArtikel = null;
	private JButton jButtonMatchCodeArtikel = null;
	private JLabel jLabelArtikel = null;
	/**
	 * This method initializes 
	 * @param iModel 
	 * 
	 */
	protected BaugruppeSuchPaneView(BaugruppeSuchController baugruppeSuchPaneController, IModel iModel) {
		super();
		setBenutzerSuchPaneController(baugruppeSuchPaneController);
		setiModel(iModel);
		initialize();
	}

	private void setBenutzerSuchPaneController(
			BaugruppeSuchController baugruppeSuchPaneController) {
		this.suchController = baugruppeSuchPaneController;
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
					BaugruppeSuchBean suchBean =  (BaugruppeSuchBean)getiModel().getiSuchBean();
					if (suchBean==null){
						suchBean = new BaugruppeSuchBean();
					}
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
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.gridy = 3;
			jLabelArtikel = new JLabel();
			jLabelArtikel.setText("Artikel");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.gridy = 3;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridy = 3;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 1;
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
			gridBagConstraints4.gridy = 2;
			jPanelMitte = new JPanel();
			jPanelMitte.setLayout(new GridBagLayout());
			jLabelAbteilung = new JLabel();
			jLabelAbteilung.setText("Baugruppe");
			jPanelMitte.add(jLabelAbteilung, gridBagConstraints4);
			jPanelMitte.add(getJTextFieldBaugruppe(), gridBagConstraints1);
			jPanelMitte.add(getJButtonMatchCodeBaugruppe(), gridBagConstraints3);
			jPanelMitte.add(getJTextFieldArtikel(), gridBagConstraints5);
			jPanelMitte.add(getJButtonMatchCodeArtikel(), gridBagConstraints6);
			jPanelMitte.add(jLabelArtikel, gridBagConstraints8);
			
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
			gridBagConstraints2.gridy = 1;
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




//	private void setBenutzerSuchPaneController(BenutzerSuchPaneController benutzerSuchPaneController) {
//		this.baugruppeSuchPaneController = baugruppeSuchPaneController;
//	}

	@Override
	public void zeichneDich(ISuchBean iSuchBean) {
		Log.log().severe(" Nicht implemnentiert! ");
		//TODO
	}

	@Override
	public void setStandardFocusPosition() {
			jButtonSuchen.requestFocus();
	}



	/**
	 * This method initializes jTextFieldBaugruppe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBaugruppe() {
		if (jTextFieldBaugruppe == null) {
			jTextFieldBaugruppe = new JTextField();
			jTextFieldBaugruppe.setEditable(false);
			jTextFieldBaugruppe.setPreferredSize(new Dimension(100, 20));
		}
		return jTextFieldBaugruppe;
	}

	/**
	 * This method initializes jButtonMatchCodeAbteilung	
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
									((BaugruppeSuchController)getSuchController()).holeBaugruppe();
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

	public void setArtikel(ArtikelBean artikelBean) {
		if (artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		BaugruppeSuchBean baugruppeSuchBean =  (BaugruppeSuchBean)getiModel().getiSuchBean();
		if (baugruppeSuchBean==null){
			baugruppeSuchBean = new BaugruppeSuchBean();
		}
		baugruppeSuchBean.setArtikelBean(artikelBean); //Im Model bekannt
		//in der Oberfläche anzeigen
		getJTextFieldArtikel().setText(artikelBean.getBezeichnung().toString() + " " + artikelBean.getTyp());

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
	 * This method initializes jTextFieldArtikel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldArtikel() {
		if (jTextFieldArtikel == null) {
			jTextFieldArtikel = new JTextField();
			jTextFieldArtikel.setPreferredSize(new Dimension(100, 20));
			jTextFieldArtikel.setEditable(false);
		}
		return jTextFieldArtikel;
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
			jButtonMatchCodeArtikel
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((BaugruppeSuchController)getSuchController()).holeArtikel();
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

	public void setBaugruppe(BaugruppeBean baugruppeBean) {
		if (baugruppeBean==null){
			baugruppeBean = new BaugruppeBean();
		}
		BaugruppeSuchBean suchBean =  (BaugruppeSuchBean)getiModel().getiSuchBean();
		if (suchBean==null){
			suchBean = new BaugruppeSuchBean();
		}
		suchBean.setBaugruppeName(baugruppeBean.getName()); //Im Model bekannt
		getJTextFieldBaugruppe().setText(baugruppeBean.getName());
		
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
