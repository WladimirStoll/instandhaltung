package de.keag.lager.panels.frame.bestellanforderung.pane.suche;

import javax.swing.BorderFactory;
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
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaSuchBean;

import java.awt.Color;
import java.util.Date;

public class BaSuchView extends SuchView {

	private JToolBar jToolBar = null;
	private JButton jButtonSuchen = null;
	private JButton jButtonCancel = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelMitte = null;
	private JPanel jPanelUnten = null;
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelErstellungsDatum = null;
	private JLabel jLabelStatus = null;
	private JDateChooser jPanelDatumVon = null;
	private JComboBox jComboBoxStatus = null;
	private JLabel jLabelErstellungsDatumBis = null;
	private JDateChooser jPanelDatumBis = null;
	private SuchController suchController = null;  //  @jve:decl-index=0:
	private IModel iModel = null;  //  @jve:decl-index=0:

	/**
	 * This method initializes 
	 * @param iModel 
	 * 
	 */
	protected BaSuchView(BaSuchController baSuchPaneController, IModel iModel) {
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
					BaSuchBean baSuchBean = new BaSuchBean();
					baSuchBean.setErstellungsDatumVon(getJPanelDatumVon().getDate());
					baSuchBean.setErstellungsDatumBis(getJPanelDatumBis().getDate());
					baSuchBean.setAnforderungStatus((AnforderungStatus)getJComboBoxStatus().getSelectedItem());
					getSuchController().suchen(baSuchBean);
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
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.weightx = 0.0D;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.insets = new Insets(0, 2, 2, 2);
			gridBagConstraints10.gridy = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridy = 1;
			jLabelErstellungsDatumBis = new JLabel();
			jLabelErstellungsDatumBis.setText("ErstellungsDatum(bis)");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 0.0D;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.gridx = 1;
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
			gridBagConstraints8.gridy = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.weightx = 0.0D;
			gridBagConstraints4.weighty = 0.0D;
			gridBagConstraints4.gridy = 2;
			jPanelMitte = new JPanel();
			jPanelMitte.setLayout(new GridBagLayout());
			jLabelErstellungsDatum = new JLabel();
			jLabelErstellungsDatum.setText("Erstellungsdatum(von)");
			jLabelStatus = new JLabel();
			jLabelStatus.setText("Status");
			jPanelMitte.add(jLabelErstellungsDatum, gridBagConstraints8);
			jPanelMitte.add(jLabelStatus, gridBagConstraints4);
			jPanelMitte.add(getJPanelDatumVon(), gridBagConstraints9);
			jPanelMitte.add(getJComboBoxStatus(), gridBagConstraints7);
			jPanelMitte.add(jLabelErstellungsDatumBis, gridBagConstraints6);
			jPanelMitte.add(getJPanelDatumBis(), gridBagConstraints10);
			
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
					BaSuchView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
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
					BaSuchView.this.mouseClickedFehler(e, getJComboBoxFehler());
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
	 * This method initializes jComboBoxStatus	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxStatus() {
		if (jComboBoxStatus == null) {
			AnforderungStatus[] petAnforderungen = {
					AnforderungStatus.OFFEN,
					AnforderungStatus.ABGESCHLOSSEN,
					AnforderungStatus.GELOESCHT,
					AnforderungStatus.FEHLERHAFT,
					AnforderungStatus.ALLE
			};			
			jComboBoxStatus = new JComboBox(petAnforderungen);
			jComboBoxStatus.setSelectedIndex(0); //erster Eintrag wird angezeigt.
			jComboBoxStatus.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
//					if (!prueferFehler()) return;
				}
			});
		}
		return jComboBoxStatus;
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

	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
