package de.keag.lager.panels.frame.artikel.pane.suche;

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
import de.keag.lager.panels.frame.artikel.ArtikelSuchBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung.BaDetailsController;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageSuchBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
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

public class ArtikelSuchPaneView extends SuchView implements IArtikelSuchPaneBeobachter{

	private JToolBar jToolBar = null;
	private JButton jButtonSuchen = null;
	private JButton jButtonCancel = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelMitte = null;
	private JPanel jPanelUnten = null;
	private JComboBox jComboBoxFehler = null;
	private JComboBox jComboBoxAbteilung = null;
	private SuchController suchController = null;  //  @jve:decl-index=0:
	private IModel iModel = null;  //  @jve:decl-index=0:
	private JLabel jLabelArtikel = null;
	private JButton jButtonMatchCodeArtikel = null;
	private JFormattedTextField jFormattedTextFieldZeile = null;// @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldArtikel = null;
	private JButton jButtonMatchCodeLieferant = null;
	private JTextField jTextFieldHerstellerLieferant = null;
	private JLabel jLabelHersteller = null;
	private JLabel jLabelArtikelBezeichnung = null;
	private JLabel jLabelArtikeltyp = null;
	private JTextField jTextFieldBezeichnung = null;
	private JTextField jTextFieldTyp = null;
	/**
	 * This method initializes 
	 * @param iModel 
	 * 
	 */
	protected ArtikelSuchPaneView(ArtikelSuchController benutzerSuchPaneController, IModel iModel) {
		super();
		setHalleSuchPaneController(benutzerSuchPaneController);
		setiModel(iModel);
		initialize();
	}

	private void setHalleSuchPaneController(
			ArtikelSuchController benutzerSuchPaneController) {
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
					ArtikelSuchBean suchBean =  (ArtikelSuchBean)getiModel().getiSuchBean();
					if (suchBean==null){
						suchBean = new ArtikelSuchBean();
					}
					suchBean.setBezeichnung(getJTextFieldBezeichnung().getText());
					suchBean.setTyp(getJTextFieldTyp().getText());
					
                    //Zeile					
					Integer zeilenNummer;
					if (getJFormattedTextFieldZeile().getValue()==null){
						zeilenNummer = 0;
					}else{
						zeilenNummer = ((Long) getJFormattedTextFieldZeile()
								.getValue()).intValue();
					}
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
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.gridy = 2;
			gridBagConstraints30.weightx = 1.0;
			gridBagConstraints30.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints30.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			gridBagConstraints29.gridx = 0;
			gridBagConstraints29.anchor = GridBagConstraints.EAST;
			gridBagConstraints29.gridy = 2;
			jLabelArtikeltyp = new JLabel();
			jLabelArtikeltyp.setText("Artikeltyp");
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.gridx = 0;
			gridBagConstraints28.anchor = GridBagConstraints.EAST;
			gridBagConstraints28.gridy = 1;
			jLabelArtikelBezeichnung = new JLabel();
			jLabelArtikelBezeichnung.setText("Artikelbezeichung");
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.gridx = 0;
			gridBagConstraints19.anchor = GridBagConstraints.EAST;
			gridBagConstraints19.gridy = 0;
			jLabelHersteller = new JLabel();
			jLabelHersteller.setText("Hersteller");
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridy = 0;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints13.gridx = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.gridy = 0;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints27.gridy = 3;
			gridBagConstraints27.weightx = 1.0;
			gridBagConstraints27.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints27.gridx = 1;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 2;
			gridBagConstraints18.anchor = GridBagConstraints.WEST;
			gridBagConstraints18.gridy = 3;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.anchor = GridBagConstraints.EAST;
			gridBagConstraints15.gridy = 3;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 0.0D;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.gridx = 1;
			jPanelMitte = new JPanel();
			jPanelMitte.setLayout(new GridBagLayout());
			jPanelMitte.add(jLabelArtikel, gridBagConstraints15);
			jPanelMitte.add(getJButtonMatchCodeArtikel(), gridBagConstraints18);
			jPanelMitte.add(getJFormattedTextFieldArtikel(), gridBagConstraints27);
			jPanelMitte.add(getJButtonMatchCodeLieferant(), gridBagConstraints12);
			jPanelMitte.add(getJTextFieldHerstellerLieferant(), gridBagConstraints13);
			jPanelMitte.add(jLabelHersteller, gridBagConstraints19);
			jPanelMitte.add(jLabelArtikelBezeichnung, gridBagConstraints28);
			jPanelMitte.add(jLabelArtikeltyp, gridBagConstraints29);
			jPanelMitte.add(getJTextFieldBezeichnung(), gridBagConstraints1);
			jPanelMitte.add(getJTextFieldTyp(), gridBagConstraints30);
			
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
							((ArtikelSuchController)getSuchController()).holeArtikel();
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
	 * This method initializes jFormattedTextFieldZeile	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldZeile() {
		if (jFormattedTextFieldZeile == null) {
			jFormattedTextFieldZeile = new JFormattedTextField();
		}
		return jFormattedTextFieldZeile;
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

//	public void setAbteilung(AbteilungBean abteilungBean) {
//		if (abteilungBean==null){
//			abteilungBean = new AbteilungBean();
//		}
//		ArtikelSuchBean suchBean =  (ArtikelSuchBean)getiModel().getiSuchBean();
//		if (suchBean==null){
//			suchBean = new ArtikelSuchBean();
//		}
//		suchBean.setAbteilung(abteilungBean); //Im Model bekannt
//		getJFormattedTextFieldAbteilung().setText(abteilungBean.getAbteilungName());
//	}

//	public void setHalle(ArtikelBean halleBean) {
//		if (halleBean==null){
//			halleBean = new ArtikelBean();
//		}
//		ArtikelSuchBean suchBean =  (ArtikelSuchBean)getiModel().getiSuchBean();
//		if (suchBean==null){
//			suchBean = new ArtikelSuchBean();
//		}
//		suchBean.setArtikelBean(halleBean); //Im Model bekannt
//		getJFormattedTextFieldHalle().setText(halleBean.getName());
//	}

	public void setArtikel(ArtikelBean artikelBean) {
		if (artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		ArtikelSuchBean suchBean =  (ArtikelSuchBean)getiModel().getiSuchBean();
		if (suchBean==null){
			suchBean = new ArtikelSuchBean();
		}
		suchBean.setArtikelBean(artikelBean); //Im Model bekannt
		getJFormattedTextFieldArtikel().setText(artikelBean.getBezeichnung());
	}

	/**
	 * This method initializes jButtonMatchCodeLieferant	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeLieferant() {
		if (jButtonMatchCodeLieferant == null) {
			jButtonMatchCodeLieferant = new JButton();
			jButtonMatchCodeLieferant.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeLieferant.setText("...");
			jButtonMatchCodeLieferant
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((ArtikelSuchController)getSuchController()).holeLieferanten();
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
		return jButtonMatchCodeLieferant;
	}

	/**
	 * This method initializes jTextFieldHerstellerLieferant	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldHerstellerLieferant() {
		if (jTextFieldHerstellerLieferant == null) {
			jTextFieldHerstellerLieferant = new JTextField();
			jTextFieldHerstellerLieferant.setPreferredSize(new Dimension(100, 20));
			jTextFieldHerstellerLieferant.setEditable(false);
		}
		return jTextFieldHerstellerLieferant;
	}
	

	public void setMatchCodeLieferanten(LhBean lhBean) {
		getJTextFieldHerstellerLieferant().setText(lhBean.getName());
		getJTextFieldHerstellerLieferant().requestFocusInWindow();
		ArtikelSuchBean suchBean =  (ArtikelSuchBean)getiModel().getiSuchBean();
		if (suchBean==null){
			suchBean = new ArtikelSuchBean();
		}
		suchBean.setHersteller(lhBean); //Im Model bekannt
	}

	/**
	 * This method initializes jTextFieldBezeichnung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBezeichnung() {
		if (jTextFieldBezeichnung == null) {
			jTextFieldBezeichnung = new JTextField();
			jTextFieldBezeichnung.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldBezeichnung;
	}

	/**
	 * This method initializes jTextFieldTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldTyp() {
		if (jTextFieldTyp == null) {
			jTextFieldTyp = new JTextField();
			jTextFieldTyp.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldTyp;
	}

//	public void setBenutzer(BenutzerBean benutzerBean) {
//		if (benutzerBean==null){
//			benutzerBean = new BenutzerBean();
//		}
//		ArtikelSuchBean suchBean =  (ArtikelSuchBean)getiModel().getiSuchBean();
//		if (suchBean==null){
//			suchBean = new ArtikelSuchBean();
//		}
//		suchBean.setEinlagerungsBenutzer(benutzerBean); //Im Model bekannt
//		getJFormattedTextFieldEinlagerungsBenutzer().setText(benutzerBean.toString());
//	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
