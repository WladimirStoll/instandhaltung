package de.keag.lager.panels.frame.artikel.pane.details.artikelPosition;

import java.awt.GridBagLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IDetailsBeobachter;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck.BestandspackstueckDetailsController;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class ArtikelPosDetailsView extends DetailsView implements
		IDetailsBeobachter, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController; // @jve:decl-index=0:
	private JTextField jTextFieldId = null;
	private JLabel jLabelBestellanforderungspositionID = null;
	private JComboBox jComboBoxFehler = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelUnten = null;
	private JLabel jLabelHalle = null;
	private JTextField jTextFieldHalle = null;
	private JButton jButtonMatchCodeHalle = null;
	private JLabel jLabelEtage = null;
	private JLabel jLabelZeile = null;
	private JLabel jLabelSaeule = null;
	private JLabel jLabelEbene = null;
	private JLabel jLabelPosition = null;
	private JTextField jTextFieldEtage = null;
	private JButton jButtonMatchCodeEtage = null;
	private JLabel jLabelArtikel = null;
	private JTextField jTextFieldArtikel = null;
	private JFormattedTextField jFormattedTextFieldZeile = null;
	private JFormattedTextField jFormattedTextFieldSaeule = null;
	private JFormattedTextField jFormattedTextFieldEbene = null;
	private JFormattedTextField jFormattedTextFieldPosition = null;
	private JFormattedTextField jFormattedTextFieldMenge = null;
	private JLabel jLabelMenge = null;

	// Nur für den Augenblick der Position-Auswahl nötig
	// private HalleBean halleBean; // @jve:decl-index=0:
	// private EtageBean etageBean; // @jve:decl-index=0:
	// private ZeileBean zeileBean; // @jve:decl-index=0:
	// private SaeuleBean saeuleBean; // @jve:decl-index=0:
	// private EbeneBean ebeneBean; // @jve:decl-index=0:
	// private PositionBean positionBean; // @jve:decl-index=0:
	private JLabel jLabel = null;
	private JLabel jLabelAbteilung = null;
	private JTextField jTextFieldAbteilung = null;
	private JButton jButtonMatchCodeAbteilung = null;
	private JLabel jLabelMenge1 = null;
	private JTextField jTextFieldBarCode = null;

	/**
	 * This is the default constructor
	 * 
	 * @param detailsController
	 */
	public ArtikelPosDetailsView(ArtikelPosDetailsController detailsController) {
		super();
		setController(detailsController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints41.gridy = 13;
		gridBagConstraints41.weightx = 1.0;
		gridBagConstraints41.gridx = 1;
		GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
		gridBagConstraints33.gridx = 0;
		gridBagConstraints33.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints33.anchor = GridBagConstraints.EAST;
		gridBagConstraints33.gridy = 13;
		jLabelMenge1 = new JLabel();
		jLabelMenge1.setText("Barcode-Inhalt");
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 2;
		gridBagConstraints31.gridy = 2;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints2.gridy = 2;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints2.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.gridy = 2;
		jLabelAbteilung = new JLabel();
		jLabelAbteilung.setText("Abteilung");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.gridy = 0;
		jLabel = new JLabel();
		jLabel.setText(" ");
		jLabel.setPreferredSize(new Dimension(30, 16));
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.gridx = 0;
		gridBagConstraints22.anchor = GridBagConstraints.EAST;
		gridBagConstraints22.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints22.gridy = 12;
		jLabelMenge = new JLabel();
		jLabelMenge.setText("Menge");
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints21.gridy = 12;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.gridx = 1;
		GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
		gridBagConstraints20.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints20.gridy = 11;
		gridBagConstraints20.weightx = 1.0;
		gridBagConstraints20.gridx = 1;
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints19.gridy = 10;
		gridBagConstraints19.weightx = 1.0;
		gridBagConstraints19.gridx = 1;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 9;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints17.gridy = 8;
		gridBagConstraints17.weightx = 1.0;
		gridBagConstraints17.gridx = 1;
		GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		gridBagConstraints15.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints15.gridy = 3;
		gridBagConstraints15.weightx = 1.0;
		gridBagConstraints15.gridx = 1;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 0;
		gridBagConstraints14.anchor = GridBagConstraints.EAST;
		gridBagConstraints14.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints14.gridy = 3;
		jLabelArtikel = new JLabel();
		jLabelArtikel.setText("Artikel");
		GridBagConstraints gridBagConstraints131 = new GridBagConstraints();
		gridBagConstraints131.gridx = 2;
		gridBagConstraints131.gridy = 7;
		GridBagConstraints gridBagConstraints112 = new GridBagConstraints();
		gridBagConstraints112.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints112.gridy = 7;
		gridBagConstraints112.weightx = 1.0;
		gridBagConstraints112.gridx = 1;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.anchor = GridBagConstraints.EAST;
		gridBagConstraints8.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints8.gridy = 11;
		jLabelPosition = new JLabel();
		jLabelPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelPosition.setText("Position");
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 0;
		gridBagConstraints7.anchor = GridBagConstraints.EAST;
		gridBagConstraints7.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints7.gridy = 10;
		jLabelEbene = new JLabel();
		jLabelEbene.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelEbene.setText("Ebene");
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.gridx = 0;
		gridBagConstraints6.anchor = GridBagConstraints.EAST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints6.gridy = 9;
		jLabelSaeule = new JLabel();
		jLabelSaeule.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelSaeule.setText("Säule");
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.gridx = 0;
		gridBagConstraints51.anchor = GridBagConstraints.EAST;
		gridBagConstraints51.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints51.gridy = 8;
		jLabelZeile = new JLabel();
		jLabelZeile.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelZeile.setText("Zeile");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints4.gridy = 7;
		jLabelEtage = new JLabel();
		jLabelEtage.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelEtage.setText("Etage");
		GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
		gridBagConstraints32.gridx = 2;
		gridBagConstraints32.gridy = 4;
		GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
		gridBagConstraints29.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints29.gridy = 4;
		gridBagConstraints29.weightx = 1.0;
		gridBagConstraints29.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.gridy = 4;
		jLabelHalle = new JLabel();
		jLabelHalle.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelHalle.setText("Halle");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.weighty = 150.0D;
		gridBagConstraints3.weightx = 1.0D;
		gridBagConstraints3.gridy = 21;
		GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
		gridBagConstraints28.gridx = 0;
		gridBagConstraints28.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints28.weightx = 1.0D;
		gridBagConstraints28.weighty = 150.0D;
		gridBagConstraints28.gridy = 0;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 27;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.gridx = 0;
		GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
		gridBagConstraints35.gridx = 0;
		gridBagConstraints35.anchor = GridBagConstraints.EAST;
		gridBagConstraints35.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints35.weightx = 1.0D;
		gridBagConstraints35.weighty = 0.0D;
		gridBagConstraints35.gridy = 1;
		jLabelBestellanforderungspositionID = new JLabel();
		jLabelBestellanforderungspositionID
				.setText("Positions ID");
		jLabelBestellanforderungspositionID
				.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints9.gridy = 16;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.anchor = GridBagConstraints.WEST;
		gridBagConstraints9.gridx = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.weightx = 2.0D;
		gridBagConstraints1.anchor = GridBagConstraints.WEST;
		gridBagConstraints1.gridx = 1;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJTextFieldId(), gridBagConstraints1);
		this.add(jLabelBestellanforderungspositionID, gridBagConstraints35);
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(getJPanelOben(), gridBagConstraints28);
		this.add(getJPanelUnten(), gridBagConstraints3);
		this.add(jLabelHalle, gridBagConstraints11);
		this.add(getJTextFieldHalle(), gridBagConstraints29);
		this.add(getJButtonMatchCodeHalle(), gridBagConstraints32);
		this.add(jLabelEtage, gridBagConstraints4);
		this.add(jLabelZeile, gridBagConstraints51);
		this.add(jLabelSaeule, gridBagConstraints6);
		this.add(jLabelEbene, gridBagConstraints7);
		this.add(jLabelPosition, gridBagConstraints8);
		this.add(getJTextFieldEtage(), gridBagConstraints112);
		this.add(getJButtonMatchCodeEtage(), gridBagConstraints131);
		this.add(jLabelArtikel, gridBagConstraints14);
		this.add(getJTextFieldArtikel(), gridBagConstraints15);
		this.add(getJFormattedTextFieldZeile(), gridBagConstraints17);
		this.add(getJFormattedTextFieldSaeule(), gridBagConstraints18);
		this.add(getJFormattedTextFieldEbene(), gridBagConstraints19);
		this.add(getJFormattedTextFieldPosition(), gridBagConstraints20);
		this.add(getJFormattedTextFieldMenge(), gridBagConstraints21);
		this.add(jLabelMenge, gridBagConstraints22);
		this.add(jLabel, gridBagConstraints);
		this.add(jLabelAbteilung, gridBagConstraints12);
		this.add(getJTextFieldAbteilung(), gridBagConstraints2);
		this.add(getJButtonMatchCodeAbteilung(), gridBagConstraints31);
		this.add(jLabelMenge1, gridBagConstraints33);
		this.add(getJTextFieldBarCode(), gridBagConstraints41);
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); // alte Fehler werden gelöscht.
		setzeHintergrund();

		if (modelKnotenBean != null) {
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTANDSPACKSTUECK) {
				setModelBean(modelKnotenBean);// merken
				BestandspackstueckBean bean = (BestandspackstueckBean) modelKnotenBean
						.getIBean();
				// id anzeigen
				getJTextFieldId().setText(bean.getId().toString());
				getJTextFieldId().setEnabled(false);

				//Abteilung
				getJTextFieldAbteilung().setText(bean.getAbteilung().getAbteilungName());
				
				// Artikel-Daten anzeigen
				ArtikelBean artikelBean = bean.getArtikelBean();
				getJTextFieldArtikel().setText(artikelBean.toString());
				getJTextFieldHalle()
						.setText(getHalleBean().toString());
				getJTextFieldEtage().setText(
						bean.getPositionBean().getEbeneBean().getSaeuleBean()
								.getZeileBean().getEtageBean().getName());
				
				//getJFormattedTextFieldZeile().getText()
				getJFormattedTextFieldZeile().removePropertyChangeListener("value", this);				
				getJFormattedTextFieldZeile().removeKeyListener(this);				
				getJFormattedTextFieldZeile().setValue(
						bean.getPositionBean().getEbeneBean().getSaeuleBean()
								.getZeileBean().getNummer());
				getJFormattedTextFieldZeile().addKeyListener(this);
				getJFormattedTextFieldZeile().addPropertyChangeListener("value", this);				
				//getJFormattedTextFieldSaeule().getText()	
				
				getJFormattedTextFieldSaeule().removePropertyChangeListener("value", this);				
				getJFormattedTextFieldSaeule().setValue(
						bean.getPositionBean().getEbeneBean().getSaeuleBean().getNummer());
				getJFormattedTextFieldSaeule().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldEbene().removePropertyChangeListener("value", this);
				getJFormattedTextFieldEbene()
						.setValue(bean.getPositionBean().getEbeneBean().getNummer());
				getJFormattedTextFieldEbene().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldPosition().removePropertyChangeListener("value", this);
				getJFormattedTextFieldPosition().setValue(
						getPositionBean().getNummer());
				getJFormattedTextFieldPosition().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldMenge().removePropertyChangeListener("value", this);
				getJFormattedTextFieldMenge().setValue(
						bean.getMenge());
				getJFormattedTextFieldMenge().addPropertyChangeListener("value", this);
				
				getJTextFieldBarCode().setText(bean.getBarCodeInhalt() + "  >>  " + bean.getArtikelBean().getTyp());
				
				this.repaint();// alte Komponenten werden gelöscht
				this.invalidate(); // alle bis zu dem obersten Kontainer auf
				// ungültig
				this.validate(); // werden gezeichnet.
				this.revalidate(); // Layout-Manager tut seinen JOB, und richtet
				// this.invalidate();
			}
		}
	}

	@Override
	protected DetailsController getController() {
		return detailsController;
	}

	@Override
	protected void setController(DetailsController detailsController) {
		this.detailsController = detailsController;
	}

	/**
	 * This method initializes jTextFieldId
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldId() {
		if (jTextFieldId == null) {
			jTextFieldId = new JTextField();
			jTextFieldId.setPreferredSize(new Dimension(40, 20));
			jTextFieldId.setEditable(false);
		}
		return jTextFieldId;
	}

	/**
	 * /** This method initializes jComboBoxFehler
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxFehler() {
		if (jComboBoxFehler == null) {
			jComboBoxFehler = new JComboBox();
			jComboBoxFehler.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					ArtikelPosDetailsView.super.mouseClickedFehler(e,
							(JComboBox) e.getSource());
				}
			});
		}
		return jComboBoxFehler;
	}

	/**
	 * This method initializes jPanelOben
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelOben() {
		if (jPanelOben == null) {
			jPanelOben = new JPanel();
			jPanelOben.setLayout(new GridBagLayout());
			jPanelOben.setPreferredSize(new Dimension(10, 10));
		}
		return jPanelOben;
	}

	/**
	 * This method initializes jPanelUnten
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelUnten() {
		if (jPanelUnten == null) {
			jPanelUnten = new JPanel();
			jPanelUnten.setLayout(new GridBagLayout());
			jPanelUnten.setPreferredSize(new Dimension(10, 10));
		}
		return jPanelUnten;
	}

	public void setKostenstelle(KostenstelleBean kostenstelleBean) {
		// Das Model wird geändert.
//		((BaPosBean) getModelBean().getIBean())
//				.setKostenstelle(kostenstelleBean);
//		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setArtikel(ArtikelBean artikelBean) {
		// Das Model wird geändert.
//		BaPosBean baPosBean = ((BaPosBean) getModelBean().getIBean());
//		baPosBean.setArtikelBean(artikelBean);
//		baPosBean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
//		baPosBean.setBestellmenge(artikelBean.getEmpfohlene_bestellmenge());
//
//		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//		getController().ausgewaehlterKnotenIstGeandert();
	}

	// public void setLieferantenBestellnummer(
	// LieferantenBestellnummerBean lieferantenBestellnummerBean) {
	// // Das Model wird geändert.
	// BaPosBean baPosBean = ((BaPosBean) getModelBean().getIBean());
	// baPosBean.setKatalogBean(lieferantenBestellnummerBean.getKatalogBean());
	// baPosBean.setKatalogseite(lieferantenBestellnummerBean
	// .getKatalogseite());
	// baPosBean.setLieferantenbestellnummer(lieferantenBestellnummerBean
	// .getBestellnummer());
	//
	// // Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	// getController().ausgewaehlterKnotenIstGeandert();
	// }

	// public void setMengeneinheit(MengenEinheitBean mengenEinheitBean) {
	// // Das Model wird geändert.
	// ((BaPosBean) getModelBean().getIBean())
	// .setMengenEinheitBean(mengenEinheitBean);
	// // Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	// getController().ausgewaehlterKnotenIstGeandert();
	// }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		Log.log().severe("propertyChange");
		// Das Model wird geändert.
		if (source == getJFormattedTextFieldZeile()) {
			leseAusgetJFormattedTextFieldZeile();
		}
		if (source == getJFormattedTextFieldSaeule()) {
			leseAusgetJFormattedTextFieldSaeule();
		}
		if (source == getJFormattedTextFieldEbene()) {
			leseAusgetJFormattedTextFieldEbene();
		}
		if (source == getJFormattedTextFieldPosition()) {
			leseAusgetJFormattedTextFieldPosition();
			Log.log().severe("leseAusgetJFormattedTextFieldPosition");
		}
		if (source == getJFormattedTextFieldMenge()) {
			leseAusgetJFormattedTextFieldMenge();
		}

		//Status des Bestandspackstückes wird auf "UPDATE" gesetzt. 
		getBestandspackstueck().setId(getBestandspackstueck().getId());

		try {
			((ArtikelPosDetailsController) getController())
				.vervollstaendigePosition(getPositionBean());
		} catch (LagerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJFormattedTextFieldZeile() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldZeile().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldZeile().getValue())
						.intValue();
				getZeileBean().setNummer(wert);
				getZeileBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}
	}

	
	
	private void leseAusgetJFormattedTextFieldSaeule() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldSaeule().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldSaeule().getValue())
						.intValue();
				getSaeuleBean().setNummer(wert);
				getSaeuleBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}
	}

	private void leseAusgetJFormattedTextFieldEbene() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldEbene().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldEbene().getValue())
						.intValue();
				getEbeneBean().setNummer(wert);
				getEbeneBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}
	}

	private void leseAusgetJFormattedTextFieldPosition() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldPosition().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldPosition().getValue())
						.intValue();
				getPositionBean().setNummer(wert);
				getPositionBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}

	}

	private void leseAusgetJFormattedTextFieldMenge() {
		BestandspackstueckBean bestandBean = (BestandspackstueckBean) getModelBean()
				.getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldMenge().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldMenge().getValue())
						.intValue();
				bestandBean.setMenge(wert);
			}
		} catch (NumberFormatException ex) {
		}
	}

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {

			if (!getJFormattedTextFieldZeile().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldZeile())) {
				errors.add(new Fehler(72));
			} else {
				leseAusgetJFormattedTextFieldZeile();
			}

			if (!getJFormattedTextFieldSaeule().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldSaeule())) {
				errors.add(new Fehler(77));
			} else {
				leseAusgetJFormattedTextFieldSaeule();
			}

			if (!getJFormattedTextFieldEbene().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldEbene())) {
				errors.add(new Fehler(76));
			} else {
				leseAusgetJFormattedTextFieldEbene();
			}

			if (!getJFormattedTextFieldPosition().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldPosition())) {
				errors.add(new Fehler(75));
			} else {
				leseAusgetJFormattedTextFieldPosition();
			}

			if (!getJFormattedTextFieldMenge().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldMenge())) {
				errors.add(new Fehler(74));
			} else {
				leseAusgetJFormattedTextFieldMenge();
			}

			// die gesamte Auswahl des Benutzers wird zusammen gefasst.
//			getPositionBean().setEbeneBean(getEbeneBean());
//			getEbeneBean().setSaeuleBean(getSaeuleBean());
//			getSaeuleBean().setZeileBean(getZeileBean());
//			getZeileBean().setEtageBean(getEtageBean());
//			getZeileBean().setHalleBean(getHalleBean());
//			getEtageBean().setHalleBean(getHalleBean());

			((ArtikelPosDetailsController) getController())
					.vervollstaendigePosition(getPositionBean());

			// Wir gehen davon aus, dass der Benutzer die Daten korrekt
			// eingegeben hat.
			// Die Prüfung erfolgt beim Speichern der Daten
			BestandspackstueckBean bestandBean = (BestandspackstueckBean) getModelBean()
					.getIBean();
			bestandBean.setPositionBean(getPositionBean());

		} catch (Exception e) {
			// Alle Fehler abfangen
			errors.add(new Fehler(36, FehlerTyp.FEHLER, e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}

	@Override
	protected boolean pruefeFehler() {
		ArrayList<Fehler> errors = getController()
				.setzeNeuenAktivenController();
		for (int i = 0; i < errors.size(); i++) {
			Log.log().finest(
					"Kontroller-Umschalten ist nicht möglich:"
							+ errors.get(i).getMessage());
		}
		Boolean result = errors.size() == 0;
		if (!result) {
			JFehlerDialogWechselController.getOneIntstance().showView(errors);
		}
		return errors.size() == 0;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		getJTextFieldArtikel().setEditable(false);
		getJTextFieldHalle().setEditable(false);
		getJTextFieldEtage().setEditable(false);
		getJFormattedTextFieldZeile().setEditable(true);
		getJFormattedTextFieldSaeule().setEditable(true);
		getJFormattedTextFieldEbene().setEditable(true);
		getJFormattedTextFieldPosition().setEditable(true);
		getJFormattedTextFieldMenge().setEditable(true);
		getJTextFieldBarCode().setEditable(false);
	}

	@Override
	protected void uebernehmeDaten() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStandardFocusPosition() {
		getJFormattedTextFieldZeile().requestFocus();
	}

	/**
	 * This method initializes jFormattedTextFieldHalle
	 * 
	 * @return javax.swing.JFormattedTextField
	 */
	private JTextField getJTextFieldHalle() {
		if (jTextFieldHalle == null) {
			jTextFieldHalle = new JTextField();
			jTextFieldHalle.setEditable(false);
		}
		return jTextFieldHalle;
	}

	/**
	 * This method initializes jButtonMatchCodeHalle
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonMatchCodeHalle() {
		if (jButtonMatchCodeHalle == null) {
			jButtonMatchCodeHalle = new JButton();
			jButtonMatchCodeHalle.setPreferredSize(new Dimension(43, 20));
			jButtonMatchCodeHalle.setText("...");
			jButtonMatchCodeHalle
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()) {
									((ArtikelPosDetailsController) getController())
											.holeHalle();
								}
							} catch (SQLException e1) {
								getJComboBoxFehler().addItem(
										new Fehler(24, FehlerTyp.FEHLER, e1
												.getMessage()));
								e1.printStackTrace();
							} catch (LagerException e1) {
								getJComboBoxFehler().addItem(
										new Fehler(24, FehlerTyp.FEHLER, e1
												.getMessage()));
								e1.printStackTrace();
							}
						}
					});

		}
		return jButtonMatchCodeHalle;
	}

	/**
	 * This method initializes jFormattedTextFieldEtage
	 * 
	 * @return javax.swing.JFormattedTextField
	 */
	private JTextField getJTextFieldEtage() {
		if (jTextFieldEtage == null) {
			jTextFieldEtage = new JTextField();
			jTextFieldEtage.setEditable(false);
		}
		return jTextFieldEtage;
	}

	/**
	 * This method initializes jButtonMatchCodeEtage
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonMatchCodeEtage() {
		if (jButtonMatchCodeEtage == null) {
			jButtonMatchCodeEtage = new JButton();
			jButtonMatchCodeEtage.setPreferredSize(new Dimension(43, 20));
			jButtonMatchCodeEtage.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButtonMatchCodeEtage.setText("...");
			jButtonMatchCodeEtage
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()) {
									((ArtikelPosDetailsController) getController())
											.holeEtage();
								}
							} catch (SQLException e1) {
								getJComboBoxFehler().addItem(
										new Fehler(24, FehlerTyp.FEHLER, e1
												.getMessage()));
								e1.printStackTrace();
							} catch (LagerException e1) {
								getJComboBoxFehler().addItem(
										new Fehler(24, FehlerTyp.FEHLER, e1
												.getMessage()));
								e1.printStackTrace();
							}
						}
					});

		}
		return jButtonMatchCodeEtage;
	}

	/**
	 * This method initializes jFormattedTextFieldArtikel
	 * 
	 * @return javax.swing.JFormattedTextField
	 */
	private JTextField getJTextFieldArtikel() {
		if (jTextFieldArtikel == null) {
			jTextFieldArtikel = new JTextField();
			jTextFieldArtikel.setEditable(false);
		}
		return jTextFieldArtikel;
	}

	/**
	 * This method initializes jFormattedTextFieldZeile
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJFormattedTextFieldZeile() {
		if (jFormattedTextFieldZeile == null) {
			jFormattedTextFieldZeile = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldZeile.setInputVerifier(LagerFormate
					.getInputVerifier());
//			jFormattedTextFieldZeile.addPropertyChangeListener("value", this);
//			jFormattedTextFieldZeile.addKeyListener(this);
		}
		return jFormattedTextFieldZeile;
	}

	/**
	 * This method initializes jFormattedTextFieldSaeule
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJFormattedTextFieldSaeule() {
		if (jFormattedTextFieldSaeule == null) {
			jFormattedTextFieldSaeule = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldSaeule.setInputVerifier(LagerFormate
					.getInputVerifier());
//			jFormattedTextFieldSaeule.addPropertyChangeListener("value", this);
			jFormattedTextFieldSaeule.addKeyListener(this);
		}
		return jFormattedTextFieldSaeule;
	}

	/**
	 * This method initializes jFormattedTextFieldEbene
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJFormattedTextFieldEbene() {
		if (jFormattedTextFieldEbene == null) {
			jFormattedTextFieldEbene = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldEbene.setInputVerifier(LagerFormate
					.getInputVerifier());
//			jFormattedTextFieldEbene.addPropertyChangeListener("value", this);
			jFormattedTextFieldEbene.addKeyListener(this);
		}
		return jFormattedTextFieldEbene;
	}

	/**
	 * This method initializes jFormattedTextFieldPosition
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJFormattedTextFieldPosition() {
		if (jFormattedTextFieldPosition == null) {
			jFormattedTextFieldPosition = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldPosition.setInputVerifier(LagerFormate
					.getInputVerifier());
//			jFormattedTextFieldPosition
//					.addPropertyChangeListener("value", this);
			jFormattedTextFieldPosition.addKeyListener(this);
		}
		return jFormattedTextFieldPosition;
	}

	/**
	 * This method initializes jTextFieldMenge
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJFormattedTextFieldMenge() {
		if (jFormattedTextFieldMenge == null) {
			jFormattedTextFieldMenge = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 999));
			jFormattedTextFieldMenge.setInputVerifier(LagerFormate
					.getInputVerifier());
//			jFormattedTextFieldMenge.addPropertyChangeListener("value", this);
			jFormattedTextFieldMenge.addKeyListener(this);
		}
		return jFormattedTextFieldMenge;
	}

	public void setHalle(HalleBean halleBean) {
		getZeileBean().setHalleBean(halleBean);
		// setHalleBean(halleBean);
		getJTextFieldHalle().setText(getHalleBean().getName());
	}

	// public void setEtage(EtageBean etageBean) {
	// setEtageBean(etageBean);
	// // if (etageBean==null){
	// // etageBean = new EtageBean();
	// // }
	// // HalleSuchBean halleSuchBean =
	// // (HalleSuchBean)getiModel().getiSuchBean();
	// // if (halleSuchBean==null){
	// // halleSuchBean = new HalleSuchBean();
	// // }
	// // halleSuchBean.setEtageBean(etageBean); //Im Model bekannt
	// // in der Oberfläche anzeigen
	// getJFormattedTextFieldEtage().setText(etageBean.getName());
	// }

	private HalleBean getHalleBean() {
		return getZeileBean().getHalleBean();
		// if (halleBean == null) {
		// halleBean = new HalleBean();
		// }
		// return halleBean;
	}

	// private void setHalleBean(HalleBean halleBean) {
	// this.halleBean = halleBean;
	// }

	// private EtageBean getEtageBean() {
	// if (etageBean == null) {
	// etageBean = new EtageBean();
	// }
	// return etageBean;
	// }

	// private void setEtageBean(EtageBean etageBean) {
	// this.etageBean = etageBean;
	// }

	private ZeileBean getZeileBean() {
		return getSaeuleBean().getZeileBean();
		// if (zeileBean == null) {
		// zeileBean = new ZeileBean();
		// }
		// return zeileBean;
	}

	// private void setZeileBean(ZeileBean zeileBean) {
	// this.zeileBean = zeileBean;
	// }

	private SaeuleBean getSaeuleBean() {
		return getEbeneBean().getSaeuleBean();
		// if (saeuleBean == null) {
		// saeuleBean = new SaeuleBean();
		// }
		// return saeuleBean;
	}

	// private void setSaeuleBean(SaeuleBean saeuleBean) {
	// this.saeuleBean = saeuleBean;
	// }

	private EbeneBean getEbeneBean() {
		return getPositionBean().getEbeneBean();
		// if (ebeneBean == null) {
		// ebeneBean = new EbeneBean();
		// }
		// return ebeneBean;
	}

	// private void setEbeneBean(EbeneBean ebeneBean) {
	// this.ebeneBean = ebeneBean;
	// }

	@Override
	public String toString() {
		return getName();
	}

	private BestandspackstueckBean getBestandspackstueck(){
		return (BestandspackstueckBean) getModelBean()
		.getIBean();
	}
	
	private PositionBean getPositionBean() {
		 
		return getBestandspackstueck().getPositionBean();
		// if (positionBean == null) {
		// positionBean = new PositionBean();
		// }
		// return positionBean;
	}

	public void setEtage(EtageBean etageBean) {
		getZeileBean().setEtageBean(etageBean);
	}

	/**
	 * This method initializes jTextFieldAbteilung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldAbteilung() {
		if (jTextFieldAbteilung == null) {
			jTextFieldAbteilung = new JTextField();
			jTextFieldAbteilung.setEditable(false);
		}
		return jTextFieldAbteilung;
	}

	/**
	 * This method initializes jButtonMatchCodeAbteilung	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeAbteilung() {
		if (jButtonMatchCodeAbteilung == null) {
			jButtonMatchCodeAbteilung = new JButton();
			jButtonMatchCodeAbteilung.setPreferredSize(new Dimension(43, 20));
			jButtonMatchCodeAbteilung.setText("...");
			jButtonMatchCodeAbteilung
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((ArtikelPosDetailsController)getController()).holeAbteilung();
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

	
	public void setAbteilung(AbteilungBean abteilungBean) {
		BestandspackstueckBean bestandBean = (BestandspackstueckBean) getModelBean()
		.getIBean();
		
		bestandBean.setAbteilung(abteilungBean);
		
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	/**
	 * This method initializes jTextFieldBarCode	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBarCode() {
		if (jTextFieldBarCode == null) {
			jTextFieldBarCode = new JTextField();
			jTextFieldBarCode.setEditable(false);
		}
		return jTextFieldBarCode;
	}
	
} // @jve:decl-index=0:visual-constraint="49,1"
