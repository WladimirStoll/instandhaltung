package de.keag.lager.panels.frame.artikel.pane.details.artikel;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.View;
import de.keag.lager.core.enums.KnotenZustand;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.formatter.LagerStringFormatter;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.model.ArtikelModelKnotenBean;
import de.keag.lager.panels.frame.artikel.pane.ArtikelModel;
import de.keag.lager.panels.frame.artikel.pane.ArtikelModel.AktiverReiter;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageModelKnotenBean; //import de.keag.lager.panels.frame.artikel.model.ArtikelBean;
import de.keag.lager.panels.frame.unterArtikel.model.LieferantenBestellnummerModelKnotenBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.zeile.model.ZeileModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung.BaDetailsController;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.position.BaPosDetailsController;

import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.model.UnterArtikelModelKnotenBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToolBar;


import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;

public class ArtikelDetailsView extends DetailsView implements
		IArtikelDetailsBeobachter, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelId = null;
	private JLabel jLabelBezeichnung = null;
	private JTextField jTextFieldId = null;
	private DetailsController detailsController = null; // @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;

	private ModelKnotenBean modelKnotenBean = null; // @jve:decl-index=0:
	private JToolBar jToolBar = null;
	private JButton jButtonLoeschen = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JFormattedTextField jTextFieldBezeichnung = null;
	private JButton jButtonNeu = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private AbstractTableModel abstractTableModel; // @jve:decl-index=0:
	private AbstractTableModel abstractTableModelZeile; // @jve:decl-index=0:
	private AbstractTableModel abstractTableModelBestellnummer; // @jve:decl-index=0:
	private AbstractTableModel abstractTableUnterArtikel; // @jve:decl-index=0:
	private AbstractTableModel abstractTableOberArtikel; // @jve:decl-index=0:

	private JToolBar jToolBar2 = null;
	private JButton jButtonNeuArtikelBaugruppe = null;
	private JButton jButtonLoeschenArtikelBaugruppe = null;
	private JScrollPane jScrollPaneZeile = null;
	private JTable jTableArtikelBaugruppe = null;
	private JLabel jLabelRechtsUnten = null;
	private JLabel jLabelTyp = null;
	private JLabel jLabelKegNr = null;
	private JLabel jLabelMindestBestand = null;
	private JLabel jLabelKostenstelle = null;
	private JLabel jLabelMengenEinheit = null;
	private JLabel jLabelHersteller = null;
	private JLabel jLabelEmpfohleneBestellMenge = null;
	private JFormattedTextField jTextFieldTyp = null;
	private JFormattedTextField jTextFieldKegNr = null;
	private JFormattedTextField jTextFieldMindestBestand = null;
	private JTextField jTextFieldKostenstelle = null;
	private JTextField jTextFieldMengenEinheit = null;
	private JTextField jTextFieldHersteller = null;
	private JFormattedTextField jTextFieldEmpfohleneBestellMenge = null;
	private JLabel jLabelRechtsUnten1 = null;
	private JButton jButtonMatchcodeKostenstelle = null;
	private JButton jButtonMatchcodeMengeneinheit = null;
	private JButton jButtonMatchCodeLieferant = null;
	private JFormattedTextField jTextFieldBemerkung = null;
	private JLabel jLabelBemerkung = null;
	private ArrayList<ArtikelInBaugruppe> artikelInBaugruppes;
	// private ArrayList<BaugruppeBean> baugruppeBeans; // @jve:decl-index=0:
	// private ArtikelBean artikelBean;
	private JToolBar jToolBar3 = null;
	private JButton jButtonNeuArtikelBaugruppe1 = null;
	private JButton jButtonLoeschenLieferantenBestellnummer = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTableKatalog = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanelKatalog = null;
	private JPanel jPanelAllgemein = null;
	private JPanel jPanel = null;
	private JPanel jPanelUnterArtikel = null;
	private JToolBar jToolBarUnterArtikel = null;
	private JButton jButtonNeuerUnterArtikel = null;
	private JButton jButtonLoeschenUnterArtikel = null;
	private JScrollPane jScrollPaneUnterArtikel = null;
	private JTable jTableUnterArtikel = null;
	private JScrollPane jScrollPaneOberArtikel = null;
	private JTable jTableOberArtikel = null;
	private JPanel jPanelOberArtikel = null;

	/**
	 * This is the default constructor
	 * 
	 * @param controller
	 */
	public ArtikelDetailsView(ArtikelDetailsController controller) {
		super();
		setController(controller);// Controller merken
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints112 = new GridBagConstraints();
		gridBagConstraints112.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints112.gridx = 1;
		gridBagConstraints112.gridy = 7;
		gridBagConstraints112.anchor = GridBagConstraints.WEST;
		gridBagConstraints112.weightx = 1.0;
		GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
		gridBagConstraints110.fill = GridBagConstraints.BOTH;
		gridBagConstraints110.gridy = 16;
		gridBagConstraints110.weightx = 1.0;
		gridBagConstraints110.weighty = 1.0;
		gridBagConstraints110.gridwidth = 4;
		gridBagConstraints110.gridx = 0;
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.gridx = 0;
		gridBagConstraints23.anchor = GridBagConstraints.EAST;
		gridBagConstraints23.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints23.gridy = 7;
		jLabelBemerkung = new JLabel();
		jLabelBemerkung.setText("Bemerkung");
		GridBagConstraints gridBagConstraints181 = new GridBagConstraints();
		gridBagConstraints181.gridx = 2;
		gridBagConstraints181.anchor = GridBagConstraints.WEST;
		gridBagConstraints181.gridy = 13;
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.gridx = 2;
		gridBagConstraints17.anchor = GridBagConstraints.WEST;
		gridBagConstraints17.gridy = 12;
		GridBagConstraints gridBagConstraints161 = new GridBagConstraints();
		gridBagConstraints161.gridx = 2;
		gridBagConstraints161.anchor = GridBagConstraints.WEST;
		gridBagConstraints161.weightx = 0.0D;
		gridBagConstraints161.gridy = 11;
		GridBagConstraints gridBagConstraints151 = new GridBagConstraints();
		gridBagConstraints151.gridx = 3;
		gridBagConstraints151.weighty = 0.0D;
		gridBagConstraints151.weightx = 1.0D;
		gridBagConstraints151.gridy = 15;
		jLabelRechtsUnten1 = new JLabel();
		jLabelRechtsUnten1.setText("     ");
		jLabelRechtsUnten1.setPreferredSize(new Dimension(60, 60));
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints14.gridy = 14;
		gridBagConstraints14.weightx = 1.0;
		gridBagConstraints14.gridx = 1;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 13;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints12.gridy = 12;
		gridBagConstraints12.weightx = 1.0;
		gridBagConstraints12.gridx = 1;
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints111.gridy = 11;
		gridBagConstraints111.weightx = 1.0;
		gridBagConstraints111.gridx = 1;
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints10.gridy = 10;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.gridy = 9;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.gridx = 1;
		GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
		gridBagConstraints81.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints81.gridy = 8;
		gridBagConstraints81.weightx = 1.0;
		gridBagConstraints81.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 0;
		gridBagConstraints7.anchor = GridBagConstraints.EAST;
		gridBagConstraints7.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints7.gridy = 14;
		jLabelEmpfohleneBestellMenge = new JLabel();
		jLabelEmpfohleneBestellMenge.setText("Empfohlene Bestellmenge");
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.gridx = 0;
		gridBagConstraints61.anchor = GridBagConstraints.EAST;
		gridBagConstraints61.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints61.gridy = 13;
		jLabelHersteller = new JLabel();
		jLabelHersteller.setText("Hersteller");
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.anchor = GridBagConstraints.EAST;
		gridBagConstraints5.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints5.gridy = 12;
		jLabelMengenEinheit = new JLabel();
		jLabelMengenEinheit.setText("Mengeneinheit");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints4.gridheight = 1;
		gridBagConstraints4.gridwidth = 1;
		gridBagConstraints4.gridy = 11;
		jLabelKostenstelle = new JLabel();
		jLabelKostenstelle.setText("Kostenstelle");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.anchor = GridBagConstraints.EAST;
		gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints3.gridy = 10;
		jLabelMindestBestand = new JLabel();
		jLabelMindestBestand.setText("Mindestbestand");
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.gridx = 0;
		gridBagConstraints22.anchor = GridBagConstraints.EAST;
		gridBagConstraints22.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints22.gridy = 9;
		jLabelKegNr = new JLabel();
		jLabelKegNr.setText("KE-Nr");
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 8;
		jLabelTyp = new JLabel();
		jLabelTyp.setText("Typ");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.weightx = 1.0D;
		gridBagConstraints1.gridy = 1;
		jLabelRechtsUnten = new JLabel();
		jLabelRechtsUnten.setText("     ");
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 6;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.gridx = 0;
		gridBagConstraints41.fill = GridBagConstraints.BOTH;
		gridBagConstraints41.weightx = 0.0D;
		gridBagConstraints41.weighty = 0.0D;
		gridBagConstraints41.insets = new Insets(1, 1, 1, 1);
		gridBagConstraints41.ipadx = 1;
		gridBagConstraints41.gridy = 0;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 5;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.gridy = 23;
		gridBagConstraints31.gridheight = 1;
		gridBagConstraints31.gridwidth = 3;
		gridBagConstraints31.weightx = 1.0;
		GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
		gridBagConstraints16.fill = GridBagConstraints.BOTH;
		gridBagConstraints16.gridy = 6;
		gridBagConstraints16.weightx = 1.0;
		gridBagConstraints16.weighty = 1.0;
		gridBagConstraints16.gridwidth = 3;
		gridBagConstraints16.gridx = 0;
		GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		gridBagConstraints15.fill = GridBagConstraints.BOTH;
		gridBagConstraints15.gridy = 6;
		gridBagConstraints15.weightx = 1.0;
		gridBagConstraints15.weighty = 1.0;
		gridBagConstraints15.gridwidth = 3;
		gridBagConstraints15.gridx = 1;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints6.gridy = 2;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 6;
		jLabelBezeichnung = new JLabel();
		jLabelBezeichnung.setText("Bezeichnung");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 2;
		jLabelId = new JLabel();
		jLabelId.setText("ID");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(
				SystemColor.controlShadow, 1));
		this.setPreferredSize(new Dimension(460, 931));
		this.setBounds(new Rectangle(0, 0, 500, 500));
		this.add(jLabelId, gridBagConstraints);
		this.add(jLabelBezeichnung, gridBagConstraints2);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJTextFieldBezeichnung(), gridBagConstraints8);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(jLabelRechtsUnten, gridBagConstraints1);
		this.add(jLabelTyp, gridBagConstraints11);
		this.add(jLabelKegNr, gridBagConstraints22);
		this.add(jLabelMindestBestand, gridBagConstraints3);
		this.add(jLabelKostenstelle, gridBagConstraints4);
		this.add(jLabelMengenEinheit, gridBagConstraints5);
		this.add(jLabelHersteller, gridBagConstraints61);
		this.add(jLabelEmpfohleneBestellMenge, gridBagConstraints7);
		this.add(getJTextFieldTyp(), gridBagConstraints81);
		this.add(getJTextFieldBemerkung(), gridBagConstraints112);
		this.add(getJTextFieldKegNr(), gridBagConstraints9);
		this.add(getJTextFieldMindestBestand(), gridBagConstraints10);
		this.add(getJTextFieldKostenstelle(), gridBagConstraints111);
		this.add(getJTextFieldMengenEinheit(), gridBagConstraints12);
		this.add(getJTextFieldHersteller(), gridBagConstraints13);
		this.add(getJTextFieldEmpfohleneBestellMenge(), gridBagConstraints14);
		this.add(jLabelRechtsUnten1, gridBagConstraints151);
		this.add(getJButtonMatchcodeKostenstelle(), gridBagConstraints161);
		this.add(getJButtonMatchcodeMengeneinheit(), gridBagConstraints17);
		this.add(getJButtonMatchCodeLieferant(), gridBagConstraints181);
		this.add(jLabelBemerkung, gridBagConstraints23);
		this.add(getJTabbedPane(), gridBagConstraints110);
		this.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				ArtikelDetailsView.this.setBorder(BorderFactory
						.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event
			}

			public void focusLost(java.awt.event.FocusEvent e) {
				ArtikelDetailsView.this.setBorder(BorderFactory
						.createLineBorder(SystemColor.inactiveCaption, 5));
				ArtikelDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	// Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen
	// Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean() != null) {
			if (getModelBean().getIBean() != null) {
				if (getModelBean().getIBean() instanceof ArtikelBean) {
					ArtikelBean halleBean = (ArtikelBean) getModelBean()
							.getIBean();
					// id aus dem Formular muss gleich der Id in dem Objekt
					// ArtikelBean sein. Es sei denn, dass Objet ist
					// neu(INSERT).
				} else {
					Log.log().severe("Fehler3");
				}
			} else {
				Log.log().severe("Fehler2");
			}
		} else {
			Log.log().severe("Fehler");
		}
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
		}
		return jTextFieldId;
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		setzeHintergrund();
		if (modelKnotenBean != null) {
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ARTIKEL) {
				getJComboBoxFehler().removeAllItems();
				setModelBean(modelKnotenBean);// merken
				ArtikelBean artikelBean = (ArtikelBean) modelKnotenBean
						.getIBean();
				getJTextFieldId().setEnabled(false);
				getJTextFieldId().setText(artikelBean.getId().toString());

				getJTextFieldBezeichnung().setEditable(true);
				// getJTextFieldBezeichnung().setText(artikelBean.getBezeichnung());

				getJTextFieldBezeichnung().removeKeyListener(this);
				// muss weg
				getJTextFieldBezeichnung().removePropertyChangeListener(
						"value", this);
				getJTextFieldBezeichnung().setValue(
						artikelBean.getBezeichnung());
				// muss wiederhergestellt werden
				getJTextFieldBezeichnung().addPropertyChangeListener("value",
						this);
				getJTextFieldBezeichnung().addKeyListener(this);

				getJTextFieldBemerkung().setEditable(true);
				getJTextFieldBemerkung().removePropertyChangeListener("value",
						this);
				getJTextFieldBemerkung().removeKeyListener(this);
				getJTextFieldBemerkung().setValue(artikelBean.getBemerkung());
				getJTextFieldBemerkung().addKeyListener(this);
				getJTextFieldBemerkung().addPropertyChangeListener("value",
						this);

				getJTextFieldTyp().setEditable(true);
				getJTextFieldTyp().removeKeyListener(this);
				getJTextFieldTyp().removePropertyChangeListener("value", this);
				getJTextFieldTyp().setValue(artikelBean.getTyp());
				getJTextFieldTyp().addKeyListener(this);
				getJTextFieldTyp().addPropertyChangeListener("value", this);

				getJTextFieldKegNr().setEditable(true);
				getJTextFieldKegNr().removeKeyListener(this);
				getJTextFieldKegNr()
						.removePropertyChangeListener("value", this);
				getJTextFieldKegNr().setValue(artikelBean.getKeg_nr());
				getJTextFieldKegNr().addKeyListener(this);
				getJTextFieldKegNr().addPropertyChangeListener("value", this);

				getJTextFieldMindestBestand().setEditable(true);
				getJTextFieldMindestBestand().removeKeyListener(this);
				getJTextFieldMindestBestand().removePropertyChangeListener(
						"value", this);
				getJTextFieldMindestBestand().setValue(
						artikelBean.getMindestbestand());
				getJTextFieldMindestBestand().addKeyListener(this);
				getJTextFieldMindestBestand().addPropertyChangeListener(
						"value", this);

				getJTextFieldKostenstelle().setEditable(false);
				getJTextFieldKostenstelle().setText(
						artikelBean.getKostenstelle().getName());

				getJTextFieldMengenEinheit().setEditable(false);
				getJTextFieldMengenEinheit().setText(
						artikelBean.getMengenEinheitBean().getName());

				getJTextFieldHersteller().setEditable(false);
				getJTextFieldHersteller().setText(
						artikelBean.getHersteller().getName());

				getJTextFieldEmpfohleneBestellMenge().setEditable(true);
				getJTextFieldEmpfohleneBestellMenge().removeKeyListener(this);
				getJTextFieldEmpfohleneBestellMenge()
						.removePropertyChangeListener("value", this);
				getJTextFieldEmpfohleneBestellMenge().setValue(
						artikelBean.getEmpfohlene_bestellmenge());
				getJTextFieldEmpfohleneBestellMenge().addKeyListener(this);
				getJTextFieldEmpfohleneBestellMenge()
						.addPropertyChangeListener("value", this);

				// Fehler anzeigen.
				// halleBean.pruefeEigeneDaten();
				for (int i = 0; i < artikelBean.getFehlerList().size(); i++) {
					Fehler fehler = artikelBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}

				((AbstractTableModel) jTable.getModel()).fireTableDataChanged();

				// Zeiger auf die Liste holen
				ArrayList<BaugruppeBean> baugruppeBeans = ((ArtikelDetailsController) getController())
						.getBaugruppeBeans();
				setBaugruppeBeans(baugruppeBeans, artikelBean);

				((AbstractTableModel) jTableArtikelBaugruppe.getModel())
						.fireTableDataChanged();

				((AbstractTableModel) jTableUnterArtikel.getModel())
						.fireTableDataChanged();

				((AbstractTableModel) jTableOberArtikel.getModel())
						.fireTableDataChanged();

				((AbstractTableModel) jTableKatalog.getModel())
						.fireTableDataChanged();
				
				if (iModel!=null){
					AktiverReiter ar =((ArtikelModel)iModel).getAktiverReiter();
					switch (ar) {
					case LAGERPLATZ:
						this.getJTabbedPane().setSelectedIndex(0);	
						break;
					case BAUGRUPPEN:
						this.getJTabbedPane().setSelectedIndex(1);	
						break;
					case OBERARTIKEL:
						this.getJTabbedPane().setSelectedIndex(2);	
						break;
					case UNTERARTIKEL:
						this.getJTabbedPane().setSelectedIndex(3);	
						break;
					case KATALOG:
						this.getJTabbedPane().setSelectedIndex(4);	
						break;
					case UNKNOWN:
						Log.log().severe("Reiter ist unbekannt!");
						break;
					default:
						Log.log().severe("Reiter ist unbekannt.");
						break;
					} 
				}
				
				this.repaint(); // alte Komponenten werden gelöscht
				this.invalidate(); // alle bis zu dem obersten Kontainer auf
				// ungültig
				this.validate(); // werden gezeichnet.
				this.revalidate(); // Layout-Manager tut seinen JOB, und richtet
				// die Komponenten auf
				// System.out.println("zeichneDich() ArtikelDetailsView");
			}
		}
	}

	private void setBaugruppeBeans(ArrayList<BaugruppeBean> baugruppeBeans,
			ArtikelBean artikelBean) {
		getArtikelInBaugruppes().clear();
		for (int i = 0; i < baugruppeBeans.size(); i++) {
			fuelleArtikelInBaugruppes(getArtikelInBaugruppes(), baugruppeBeans
					.get(i), artikelBean);
		}
	}

	private void fuelleArtikelInBaugruppes(
			ArrayList<ArtikelInBaugruppe> artikelInBaugruppes,
			BaugruppeBean baugruppeBean, ArtikelBean artikelBean) {
		// Alle Artikel der Baugrupe werden durchlaufen. Der gesuchte Artikel
		// wird der Liste hinzugefügt
		for (int i = 0; i < baugruppeBean.getBaugruppeArtikelBeans().size(); i++) {
			BaugruppeArtikelBean baugruppeArtikelBean = baugruppeBean
					.getBaugruppeArtikelBeans().get(i);
			if (baugruppeArtikelBean.getArtikel().getId() == artikelBean
					.getId()) {
				if (!baugruppeArtikelBean.getArtikel().getId().equals(0)) {
					artikelInBaugruppes.add(new ArtikelInBaugruppe(
							baugruppeArtikelBean, baugruppeBean));
				}
			}
		}
		// Unterbaugruppen werden durchsucht. (Rekursion)
		for (int i = 0; i < baugruppeBean.getBaugruppeBeans().size(); i++) {
			fuelleArtikelInBaugruppes(artikelInBaugruppes, baugruppeBean
					.getBaugruppeBeans().get(i), artikelBean);
		}

	}

	/**
	 * This method initializes jComboBoxFehler
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxFehler() {
		if (jComboBoxFehler == null) {
			jComboBoxFehler = new JComboBox();
		}
		return jComboBoxFehler;
	}

	private AbstractTableModel getAbstractTableModel() {
		if (abstractTableModel == null) {
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = { "id", "Halle", "Abteilung",
						"Etage", "Zeile", "Säule", "Ebene", "Position", "Menge" };

				@Override
				public String getColumnName(int column) {
					return columnNames[column];
				}

				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean() == null) {
						return "";
					}
					ModelKnotenBean modelKnotenBean = getModelBean()
							.getKinderList().get(row);
					if (modelKnotenBean.getIBean() instanceof BestandspackstueckBean) {
						BestandspackstueckBean bean = (BestandspackstueckBean) modelKnotenBean
								.getIBean();
						switch (column) {
						case 0: {
							return bean.getId();
						}
						case 1: {
							return bean.getPositionBean().getEbeneBean()
									.getSaeuleBean().getZeileBean()
									.getHalleBean().toString();
						}
						case 2: {
							return bean.getAbteilung().getAbteilungName();
						}
						case 3: {
							return bean.getPositionBean().getEbeneBean()
									.getSaeuleBean().getZeileBean()
									.getEtageBean().toString();
						}
						case 4: {
							return bean.getPositionBean().getEbeneBean()
									.getSaeuleBean().getZeileBean().toString();
						}
						case 5: {
							return bean.getPositionBean().getEbeneBean()
									.getSaeuleBean().toString();
						}
						case 6: {
							return bean.getPositionBean().getEbeneBean()
									.toString();
						}
						case 7: {
							return bean.getPositionBean().toString();
						}
						case 8: {
							return bean.getMenge().toString();
						}
						case 999:
							return bean;
						default:
							return "Fehler, column:"
									+ new Integer(column).toString();
						}
					} else {
						return "Falsche Klasse:"
								+ modelKnotenBean.getIBean().toString();
					}
				}

				@Override
				public int getRowCount() {
					if (getModelBean() == null) {
						return 0;
					} else {
						int anzahl = 0;
						for (int i = 0; i < getModelBean().getKinderList()
								.size(); i++) {
							if (getModelBean().getKinderList().get(i) instanceof BestandspackstueckModelKnotenBean) {
								anzahl++;
							}
						}
						return anzahl;
					}
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModel;
	}

	private AbstractTableModel getAbstractTableModelArtikelBaugruppe() {
		if (abstractTableModelZeile == null) {
			abstractTableModelZeile = new AbstractTableModel() {
				private String[] columnNames = { "Übergeordnete Baugruppen",
						"Baugruppe", "Menge" };

				@Override
				public String getColumnName(int column) {
					return columnNames[column];
				}

				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean() == null) {
						return "";
					}

					// ermittle die Position, ab welcher die BaugruppenARtikel
					// gespeichert sind.
					// int rowInListe = getModelBean().getKinderList().size()
					// - getRowCount()
					// - getJTableKatalog().getModel().getRowCount()
					// ;
					int rowInListe = getJTable().getModel().getRowCount();

					ModelKnotenBean modelKnotenBean = getModelBean()
							.getKinderList().get(rowInListe + row);

					if (modelKnotenBean.getIBean() instanceof BaugruppeArtikelBean) {
						BaugruppeArtikelBean bean = (BaugruppeArtikelBean) modelKnotenBean
								.getIBean();
						switch (column) {
						case 0: {
							return bean.getBaugruppe()
									.getVaterBaugruppeNamenListe();
						}
						case 1: {
							return bean.getBaugruppe().getName();
						}
						case 2: {
							return bean.getEingebauteMenge().toString();
						}
						case 999:
							return bean;
						default:
							return "Fehler, column:"
									+ new Integer(column).toString();
						}
					} else {
						return "Falsche Klasse:"
								+ modelKnotenBean.getIBean().toString();
					}
				}

				@Override
				public int getRowCount() {
					if (getModelBean() == null) {
						return 0;
					} else {
						int anzahl = 0;
						for (int i = 0; i < getModelBean().getKinderList()
								.size(); i++) {
							if (getModelBean().getKinderList().get(i) instanceof BaugruppeArtikelModelKnotenBean) {
								anzahl++;
							}
						}
						return anzahl;
					}
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModelZeile;
	}

	private AbstractTableModel getAbstractTableModelUnterArtikel() {
		if (abstractTableUnterArtikel == null) {
			abstractTableUnterArtikel = new AbstractTableModel() {
				private String[] columnNames = { "Artikel", "Menge" };

				@Override
				public String getColumnName(int column) {
					return columnNames[column];
				}

				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean() == null) {
						return "";
					}

					// ermittle die Position, ab welcher die UnterArtikel
					// gespeichert sind.
					// int rowInListe = getModelBean().getKinderList().size()
					// - getRowCount()
					// - getJTable().getModel().getRowCount()
					// - getJTableArtikelBaugruppe().getModel().getRowCount()
					// ;
					int rowInListe = getJTable().getModel().getRowCount()
							+ getJTableArtikelBaugruppe().getModel()
									.getRowCount();

					ModelKnotenBean modelKnotenBean = getModelBean()
							.getKinderList().get(rowInListe + row);

					if (modelKnotenBean.getIBean() instanceof UnterArtikelBean) {
						UnterArtikelBean bean = (UnterArtikelBean) modelKnotenBean
								.getIBean();
						switch (column) {
						case 0: {
							return bean.getKindArtikelBean().toString();
						}
						case 1: {
							return bean.getAnzahl();
						}
						case 999:
							return bean;
						default:
							return "Fehler, column:"
									+ new Integer(column).toString();
						}
					} else {
						return "Falsche Klasse:"
								+ modelKnotenBean.getIBean().toString();
					}
				}

				@Override
				public int getRowCount() {
					if (getModelBean() == null) {
						return 0;
					} else {
						int anzahl = 0;
						for (int i = 0; i < getModelBean().getKinderList()
								.size(); i++) {
							if (getModelBean().getKinderList().get(i) instanceof UnterArtikelModelKnotenBean) {
								anzahl++;
							}
						}
						return anzahl;
					}
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableUnterArtikel;
	}

	private AbstractTableModel getAbstractTableModelOberArtikel() {
		if (abstractTableOberArtikel == null) {
			abstractTableOberArtikel = new AbstractTableModel() {
				private String[] columnNames = { "Übergeordnete Artikel" };

				@Override
				public String getColumnName(int column) {
					return columnNames[column];
				}

				// TODO
				@Override
				public Object getValueAt(int row, int column) {
					try {
						ArtikelBean artikelBean = (ArtikelBean) getModelBean()
								.getIBean();
						return artikelBean.getOberArtikelBeansInfos().get(row);
					} catch (Exception e) {
						return e.getMessage();
					}
					// ArtikelBean oberArtikel =
					// artikelBean.getOberArtikelBeans().get(row);
					// switch (column) {
					// case 0:
					// return oberArtikel.getBezeichnung();
					// case 1:
					// return oberArtikel.getTyp();
					// case 999:
					// return oberArtikel;
					// default:
					// return "ERROR";
					// }
				}

				@Override
				public int getRowCount() {
					if (getModelBean() == null) {
						return 0;
					} else {
						ArtikelBean artikelBean = (ArtikelBean) getModelBean()
								.getIBean();
						return artikelBean.getOberArtikelBeansInfos().size(); // artikelBean.getOberArtikelBeans().size();
					}
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableOberArtikel;
	}

	private AbstractTableModel getAbstractTableModelBestellnummer() {
		if (abstractTableModelBestellnummer == null) {
			abstractTableModelBestellnummer = new AbstractTableModel() {
				private String[] columnNames = { "Katalog", "Lieferant",
						"Bestellnummer", "Katalogseite", "Preis" };

				@Override
				public String getColumnName(int column) {
					return columnNames[column];
				}

				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean() == null) {
						return "";
					}

					// ermittle die Position, ab welcher die BaugruppenARtikel
					// gespeichert sind.
					// int rowInListe = getModelBean().getKinderList().size()
					// - getJTable().getModel().getRowCount()
					// - getJTableArtikelBaugruppe().getModel().getRowCount()
					// - getJTableUnterArtikel().getModel().getRowCount()
					// - getRowCount();
					int rowInListe = getJTable().getModel().getRowCount()
							+ getJTableArtikelBaugruppe().getModel()
									.getRowCount()
							+ getJTableUnterArtikel().getModel().getRowCount();

					ModelKnotenBean modelKnotenBean = getModelBean()
							.getKinderList().get(rowInListe + row);

					if (modelKnotenBean.getIBean() instanceof LieferantenBestellnummerBean) {
						LieferantenBestellnummerBean bean = (LieferantenBestellnummerBean) modelKnotenBean
								.getIBean();
						// "Katalog", "Lieferant",
						// "Bestellnummer","Katalogseite","Preis"
						switch (column) {
						case 0: {
							return bean.getKatalogBean().getName();
						}
						case 1: {
							return bean.getKatalogBean()
									.getHerstellerLieferantBean().getName();
						}
						case 2: {
							return bean.getBestellnummer();
						}
						case 3: {
							return bean.getKatalogseite();
						}
						case 4: {
							return bean.getPreis();
						}
						case 999:
							return bean;
						default:
							return "Fehler, column:"
									+ new Integer(column).toString();
						}
					} else {
						return "Falsche Klasse:"
								+ modelKnotenBean.getIBean().toString();
					}
				}

				@Override
				public int getRowCount() {
					if (getModelBean() == null) {
						return 0;
					} else {
						int anzahl = 0;
						for (int i = 0; i < getModelBean().getKinderList()
								.size(); i++) {
							if (getModelBean().getKinderList().get(i) instanceof LieferantenBestellnummerModelKnotenBean) {
								anzahl++;
							}
						}
						return anzahl;
					}
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModelBestellnummer;
	}

	@Override
	public void setModelBean(ModelKnotenBean benutzerModelBean) {
		this.modelKnotenBean = benutzerModelBean;
		if (this != null) {
			if (!(this.modelKnotenBean.getIBean() instanceof ArtikelBean)) {
				Log.log().severe(
						"Wrong type:" + this.modelKnotenBean.getIBean());
			}
		}
	}

	@Override
	public ModelKnotenBean getModelBean() {
		return modelKnotenBean;
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
			jToolBar.add(getJButtonNeu());
			jToolBar.add(getJButtonLoeschen());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jButtonLoeschen
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLoeschen() {
		if (jButtonLoeschen == null) {
			jButtonLoeschen = new JButton();
			jButtonLoeschen.setText("Löschen");
			jButtonLoeschen
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								if (getJTable().getSelectedRow() >= 0) {
									BestandspackstueckBean bean = (BestandspackstueckBean) getJTable()
											.getModel().getValueAt(
													getJTable()
															.getSelectedRow(),
													999);
									getController().loeschePosition(bean);
								} else {
									getJComboBoxFehler()
											.addItem(new Fehler(27));
								}
							}

						}
					});
//			Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
//		Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		}
		return jButtonLoeschen;
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
			jPanelOben.setPreferredSize(new Dimension(40, 40));
		}
		return jPanelOben;
	}

	@Override
	public void setStandardFocusPosition() {
		getJTextFieldBezeichnung().requestFocus();
	}

	/**
	 * enable = benutzbar editable = beschreibbar
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		// getJButtonMatchCodeEmail().setEnabled(enabled);
		getJButtonNeu().setEnabled(enabled);
		getJButtonLoeschen().setEnabled(enabled);
		// getJTextFieldEmail().setEditable(enabled);
		// getJButtonMatchCodeEmail().setEnabled(enabled);
		getJTextFieldBezeichnung().setEditable(enabled);
		getJTextFieldBemerkung().setEditable(enabled);
		// getJTextFieldLoginName().setEditable(enabled);
		// getJTextFieldPasswort().setEditable(enabled);
		// getJTextFieldVorname().setEditable(enabled);
		// getJDateChooserErstellungsDatum().setEnabled(enabled);
		getJTable().setEnabled(enabled);
	}

	// setzen der Email aus dem Matchcode-Fenster
	public void setMatchCodeEmail(EmailBean emailBean, String email) {
		// if (email!=null){
		// ArtikelBean halleBean = (ArtikelBean)getModelBean().getIBean();
		// halleBean.setEmail(email);
		// }
		// if(emailBean!=null){
		// ArtikelBean halleBean = (ArtikelBean)getModelBean().getIBean();
		// halleBean.setEmail(emailBean.getEmail());
		// getJTextFieldEmail().setText(halleBean.getEmail());
		// }
	}

	/**
	 * This method initializes jTextFieldName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldBezeichnung() {
		if (jTextFieldBezeichnung == null) {
			jTextFieldBezeichnung = new JFormattedTextField(
					new LagerStringFormatter(1, 70));
			jTextFieldBezeichnung.setInputVerifier(LagerFormate
					.getInputVerifier());
			// jTextFieldBezeichnung.addPropertyChangeListener("value", this);

		}
		return jTextFieldBezeichnung;
	}

	/**
	 * This method initializes jButtonNeu
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonNeu() {
		if (jButtonNeu == null) {
			jButtonNeu = new JButton();
			jButtonNeu.setText("Neu");
			jButtonNeu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()) {
						getController().erstelleNeuenSatz(
								ModelKnotenTyp.BESTANDSPACKSTUECK);
					}
				}
			});
		}
		
		return jButtonNeu;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 */

	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setModel(getAbstractTableModel());

			jTable.getColumnModel().getColumn(0).setPreferredWidth(15);
			jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
			jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
			jTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			jTable.getColumnModel().getColumn(4).setPreferredWidth(20);
			jTable.getColumnModel().getColumn(5).setPreferredWidth(20);
			jTable.getColumnModel().getColumn(6).setPreferredWidth(20);
			jTable.getColumnModel().getColumn(7).setPreferredWidth(20);
			jTable.getColumnModel().getColumn(8).setPreferredWidth(20);

			jTable.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					// pruefeFehler();
				}
			});
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (pruefeFehler()) {
						if (e.getClickCount() == 2) {
							// ausgewählte Bean wird ermittelt.
							int aktuelleZeile = jTable.getSelectedRow();
							if (getModelBean() != null
									&& getModelBean().getKinderList().size() > 0
									&& getModelBean().getKinderList().size() >= aktuelleZeile) {
								ModelKnotenBean modelKnotenBean = getModelBean()
										.getKinderList().get(aktuelleZeile);
								getController().selectKnoten(modelKnotenBean);
							}

						}
					}
					;
				}
			});
		}
		return jTable;
	}

	private void leseAusgetJTextFieldEmail() {
		// ArtikelBean halleBean = (ArtikelBean)getModelBean().getIBean();
		// String wert ;
		// if (getJTextFieldEmail().getValue() != null) {
		// wert = getJTextFieldEmail().getValue().toString();
		// halleBean.setEmail(wert);
		// }
	}

	private void leseAusgetJTextFieldPasswort() {
		// ArtikelBean halleBean = (ArtikelBean)getModelBean().getIBean();
		// String wert ;
		// if (getJTextFieldPasswort().getValue() != null) {
		// wert = getJTextFieldPasswort().getValue().toString();
		// halleBean.setPassword(wert);
		// }
	}

	private void leseAusgetJTextFieldLoginName() {
		// ArtikelBean halleBean = (ArtikelBean)getModelBean().getIBean();
		// String wert ;
		// if (getJTextFieldLoginName().getValue() != null) {
		// wert = getJTextFieldLoginName().getValue().toString();
		// halleBean.setLoginName(wert);
		// }
	}

	private void leseAusgetJTextFieldVorname() {
		// ArtikelBean halleBean = (ArtikelBean)getModelBean().getIBean();
		// String wert ;
		// if (getJTextFieldVorname().getValue() != null) {
		// wert = getJTextFieldVorname().getValue().toString();
		// halleBean.setVorname(wert);
		// }
	}

	private void leseAusgetJTextFieldBezeichnung() {
		ArtikelBean artikelBean = (ArtikelBean) getModelBean().getIBean();
		String wert;
		if (getJTextFieldBezeichnung().getValue() != null) {
			wert = getJTextFieldBezeichnung().getValue().toString();
			artikelBean.setBezeichnung(wert);
		}
	}

	/**
	 * Diese Funktion wird immer dann aufgerufen, falls in einem JFormattedField
	 * der Inhalt geändert wird.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		// Das Model wird geändert.
		try {

			if (source == getJTextFieldBezeichnung()) {
				leseAusgetJTextFieldBezeichnung();
			}

			if (source == getJTextFieldBemerkung()) {
				leseAusgetJTextFieldBemerkung();
			}

			if (source == getJTextFieldTyp()) {
				leseAusgetJTextFieldTyp();
			}

			if (source == getJTextFieldKegNr()) {
				leseAusgetJTextFieldKegNr();
			}

			if (source == getJTextFieldMindestBestand()) {
				leseAusgetJTextFieldMindestBestand();
			}

			if (source == getJTextFieldEmpfohleneBestellMenge()) {
				leseAusgetJTextFieldEmpfohleneBestellMenge();
			}

		} catch (NumberFormatException ex) {
		}

		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJTextFieldBemerkung() {
		ArtikelBean artikelBean = (ArtikelBean) getModelBean().getIBean();
		String wert;
		if (getJTextFieldBemerkung().getValue() != null) {
			wert = getJTextFieldBemerkung().getValue().toString();
			artikelBean.setBemerkung(wert);
		}
	}

	private void leseAusgetJTextFieldEmpfohleneBestellMenge() {
		ArtikelBean artikelBean = (ArtikelBean) getModelBean().getIBean();
		Integer wert;
		if (((JFormattedTextField) getJTextFieldEmpfohleneBestellMenge())
				.getValue() != null) {
			JFormattedTextField jFormattedTextField = (JFormattedTextField) getJTextFieldEmpfohleneBestellMenge();
			Number number = (Number) jFormattedTextField.getValue();
			wert = number.intValue();
			artikelBean.setEmpfohlene_bestellmenge(wert);
		}
	}

	private void leseAusgetJTextFieldMindestBestand() {
		ArtikelBean artikelBean = (ArtikelBean) getModelBean().getIBean();
		Integer wert;
		if (((JFormattedTextField) getJTextFieldMindestBestand()).getValue() != null) {
			JFormattedTextField jFormattedTextField = (JFormattedTextField) getJTextFieldMindestBestand();
			Number number = (Number) jFormattedTextField.getValue();
			wert = number.intValue();
			artikelBean.setMindestbestand(wert);
		}
	}

	private void leseAusgetJTextFieldKegNr() {
		ArtikelBean artikelBean = (ArtikelBean) getModelBean().getIBean();
		Integer wert;
		if (((JFormattedTextField) getJTextFieldKegNr()).getValue() != null) {
			JFormattedTextField jFormattedTextField = (JFormattedTextField) getJTextFieldKegNr();
			Number number = (Number) jFormattedTextField.getValue();
			wert = number.intValue();
			artikelBean.setKeg_nr(wert);
		}
	}

	private void leseAusgetJTextFieldTyp() {
		ArtikelBean artikelBean = (ArtikelBean) getModelBean().getIBean();
		String wert;
		if (((JFormattedTextField) getJTextFieldTyp()).getValue() != null) {
			wert = ((JFormattedTextField) getJTextFieldTyp()).getValue()
					.toString();
			artikelBean.setTyp(wert);
		}
	}

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {
			if (!getJTextFieldBezeichnung().getInputVerifier()
					.shouldYieldFocus(getJTextFieldBezeichnung())) {
				errors.add(new Fehler(111));
			} else {
				leseAusgetJTextFieldBezeichnung();
			}

			if (!getJTextFieldBemerkung().getInputVerifier().shouldYieldFocus(
					getJTextFieldBemerkung())) {
				errors.add(new Fehler(111));
			} else {
				leseAusgetJTextFieldBemerkung();
			}

			if (!getJTextFieldTyp().getInputVerifier().shouldYieldFocus(
					getJTextFieldTyp())) {
				errors.add(new Fehler(112));
			} else {
				leseAusgetJTextFieldTyp();
			}

			if (!getJTextFieldKegNr().getInputVerifier().shouldYieldFocus(
					getJTextFieldKegNr())) {
				errors.add(new Fehler(113));
			} else {
				leseAusgetJTextFieldKegNr();
			}

			if (!getJTextFieldMindestBestand().getInputVerifier()
					.shouldYieldFocus(getJTextFieldMindestBestand())) {
				errors.add(new Fehler(114));
			} else {
				leseAusgetJTextFieldMindestBestand();
			}

			if (!getJTextFieldEmpfohleneBestellMenge().getInputVerifier()
					.shouldYieldFocus(getJTextFieldEmpfohleneBestellMenge())) {
				errors.add(new Fehler(115));
			} else {
				leseAusgetJTextFieldEmpfohleneBestellMenge();
			}

			// if (!getJTextFieldVorname().getInputVerifier().shouldYieldFocus(
			// getJTextFieldVorname())) {
			// errors.add(new Fehler(45));
			// } else {
			// leseAusgetJTextFieldVorname();
			// }
			// if
			// (!getJTextFieldLoginName().getInputVerifier().shouldYieldFocus(
			// getJTextFieldLoginName())) {
			// errors.add(new Fehler(46));
			// } else {
			// leseAusgetJTextFieldLoginName();
			// }
			// if (!getJTextFieldPasswort().getInputVerifier().shouldYieldFocus(
			// getJTextFieldPasswort())) {
			// errors.add(new Fehler(47));
			// } else {
			// leseAusgetJTextFieldPasswort();
			// }
			// if (!getJTextFieldEmail().getInputVerifier().shouldYieldFocus(
			// getJTextFieldEmail())) {
			// errors.add(new Fehler(48));
			// } else {
			// leseAusgetJTextFieldEmail();
			// }
		} catch (Exception e) {
			// Alle Fehler abfangen
			errors.add(new Fehler(36, FehlerTyp.FEHLER, e.getMessage()));
			e.printStackTrace();
		}
		return errors;
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
	 * This method initializes jToolBar2
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			jToolBar2.setFloatable(false);
			jToolBar2.add(getJButtonNeuArtikelBaugruppe());
			jToolBar2.add(getJButtonLoeschenArtikelBaugruppe());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes jButtonNeuZugriffsrecht
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonNeuArtikelBaugruppe() {
		if (jButtonNeuArtikelBaugruppe == null) {
			jButtonNeuArtikelBaugruppe = new JButton();
			jButtonNeuArtikelBaugruppe.setText("Neu");
			jButtonNeuArtikelBaugruppe
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								getController().erstelleNeuenSatz(
										ModelKnotenTyp.BAUGRUPPE_ARTIKEL);
							}
						}
					});

		}
		return jButtonNeuArtikelBaugruppe;
	}

	/**
	 * This method initializes jButtonLoeschenZugriffsrecht
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLoeschenArtikelBaugruppe() {
		if (jButtonLoeschenArtikelBaugruppe == null) {
			jButtonLoeschenArtikelBaugruppe = new JButton();
			jButtonLoeschenArtikelBaugruppe.setText("Löschen");
			jButtonLoeschenArtikelBaugruppe
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								int aktuelleZeile = jTableArtikelBaugruppe
										.getSelectedRow();
								int anzahlRows = jTableArtikelBaugruppe
										.getModel().getRowCount();
								if (getModelBean() != null && anzahlRows > 0
										&& anzahlRows >= aktuelleZeile) {
									int anzBestand = jTable.getModel()
											.getRowCount();
									ModelKnotenBean modelKnotenBean = getModelBean()
											.getKinderList().get(
													anzBestand + aktuelleZeile);
									// getController().selectKnoten(
									// modelKnotenBean);
									getController().loeschePosition(
											(Bean) modelKnotenBean.getIBean());
									// getController().erstelleNeuenSatz(
									// ModelKnotenTyp.BAUGRUPPE_ARTIKEL);
								}
							}
							// if (pruefeFehler()) {
							// if (getJTableZeilen().getSelectedRow() >= 0) {
							// ZeileBean bean = (ZeileBean) getJTableZeilen()
							// .getModel().getValueAt(
							// getJTableZeilen()
							// .getSelectedRow(),
							// 999);
							// getController().loeschePosition(bean);
							// } else {
							// getJComboBoxFehler()
							// .addItem(new Fehler(27));
							// }
							// }

						}
					});

		}
		return jButtonLoeschenArtikelBaugruppe;
	}

	/**
	 * This method initializes jScrollPaneZugriffsrechte
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneZugriffsrechte() {
		if (jScrollPaneZeile == null) {
			jScrollPaneZeile = new JScrollPane();
			jScrollPaneZeile.setViewportView(getJTableArtikelBaugruppe());
		}
		return jScrollPaneZeile;
	}

	/**
	 * This method initializes jTableZeile
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTableArtikelBaugruppe() {
		if (jTableArtikelBaugruppe == null) {
			jTableArtikelBaugruppe = new JTable();
			jTableArtikelBaugruppe
					.setModel(getAbstractTableModelArtikelBaugruppe());
			jTableArtikelBaugruppe
					.addFocusListener(new java.awt.event.FocusAdapter() {
						public void focusGained(java.awt.event.FocusEvent e) {
							pruefeFehler();
						}
					});
			jTableArtikelBaugruppe
					.addMouseListener(new java.awt.event.MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							if (e.getClickCount() == 2) {
								// ausgewählte Bean wird ermittelt.
								int aktuelleZeile = jTableArtikelBaugruppe
										.getSelectedRow();
								int anzahlRows = jTableArtikelBaugruppe
										.getModel().getRowCount();
								if (getModelBean() != null && anzahlRows > 0
										&& anzahlRows >= aktuelleZeile) {
									int anzBestand = jTable.getModel()
											.getRowCount();
									ModelKnotenBean modelKnotenBean = getModelBean()
											.getKinderList().get(
													anzBestand + aktuelleZeile);
									getController().selectKnoten(
											modelKnotenBean);
								}

							}
							;
						}
					});

		}
		return jTableArtikelBaugruppe;
	}

	/**
	 * This method initializes jTextFieldTyp
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldTyp() {
		if (jTextFieldTyp == null) {
			jTextFieldTyp = new JFormattedTextField(new LagerStringFormatter(1,
					70));
			jTextFieldTyp.setInputVerifier(LagerFormate.getInputVerifier());
			// jTextFieldTyp.addPropertyChangeListener("value", this);
			// jTextFieldTyp.addKeyListener(this);

		}
		return jTextFieldTyp;
	}

	/**
	 * This method initializes jTextFieldKegNr
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldKegNr() {
		if (jTextFieldKegNr == null) {
			// jTextFieldKegNr = new JFormattedTextField();
			jTextFieldKegNr = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 999999));
			jTextFieldKegNr.setInputVerifier(LagerFormate.getInputVerifier());
			// jTextFieldKegNr.addPropertyChangeListener("value", this);
			// jTextFieldKegNr.addKeyListener(this);

		}
		return jTextFieldKegNr;
	}

	/**
	 * This method initializes jTextFieldMindestBestand
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldMindestBestand() {
		if (jTextFieldMindestBestand == null) {
			jTextFieldMindestBestand = new JFormattedTextField(
					new LagerEmptyNumberFormatter(0, 1000));
			jTextFieldMindestBestand.setInputVerifier(LagerFormate
					.getInputVerifier());
			// jTextFieldMindestBestand.addPropertyChangeListener("value",
			// this);
			// jTextFieldMindestBestand.addKeyListener(this);

		}
		return jTextFieldMindestBestand;
	}

	/**
	 * This method initializes jTextFieldKostenstelle
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldKostenstelle() {
		if (jTextFieldKostenstelle == null) {
			jTextFieldKostenstelle = new JTextField();

		}
		return jTextFieldKostenstelle;
	}

	/**
	 * This method initializes jTextFieldMengenEinheit
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldMengenEinheit() {
		if (jTextFieldMengenEinheit == null) {
			jTextFieldMengenEinheit = new JFormattedTextField();
		}
		return jTextFieldMengenEinheit;
	}

	/**
	 * This method initializes jTextFieldHersteller
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldHersteller() {
		if (jTextFieldHersteller == null) {
			jTextFieldHersteller = new JTextField();
		}
		return jTextFieldHersteller;
	}

	/**
	 * This method initializes jTextFieldEmpfohleneBestellMenge
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldEmpfohleneBestellMenge() {
		if (jTextFieldEmpfohleneBestellMenge == null) {
			jTextFieldEmpfohleneBestellMenge = new JFormattedTextField(
					new LagerEmptyNumberFormatter(0, 1000));
			jTextFieldEmpfohleneBestellMenge.setInputVerifier(LagerFormate
					.getInputVerifier());
			// jTextFieldEmpfohleneBestellMenge.addPropertyChangeListener("value",
			// this);
			// jTextFieldEmpfohleneBestellMenge.addKeyListener(this);
			jTextFieldEmpfohleneBestellMenge
					.setName("jTextFieldEmpfohleneBestellMenge");
		}
		return jTextFieldEmpfohleneBestellMenge;
	}

	/**
	 * This method initializes jButtonMatchcodeKostenstelle
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonMatchcodeKostenstelle() {
		if (jButtonMatchcodeKostenstelle == null) {
			jButtonMatchcodeKostenstelle = new JButton();
			jButtonMatchcodeKostenstelle.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButtonMatchcodeKostenstelle
					.setPreferredSize(new Dimension(43, 20));
			jButtonMatchcodeKostenstelle.setText("...");
			jButtonMatchcodeKostenstelle
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								// if (pruefeFehler()) {
								((ArtikelDetailsController) getController())
										.holeKostenstelle();
								// }
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
		return jButtonMatchcodeKostenstelle;
	}

	public void setKostenstelle(KostenstelleBean kostenstelleBean) {
		// Das Model wird geändert.
		((ArtikelBean) getModelBean().getIBean())
				.setKostenstelle(kostenstelleBean);
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	/**
	 * This method initializes jButtonMatchcodeMengeneinheit
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonMatchcodeMengeneinheit() {
		if (jButtonMatchcodeMengeneinheit == null) {
			jButtonMatchcodeMengeneinheit = new JButton();
			jButtonMatchcodeMengeneinheit.setText("...");
			jButtonMatchcodeMengeneinheit
					.setPreferredSize(new Dimension(43, 20));
			jButtonMatchcodeMengeneinheit
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								// if (pruefeFehler()) {
								((ArtikelDetailsController) getController())
										.holeMengeneinheit();
								// }
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
		return jButtonMatchcodeMengeneinheit;
	}

	public void setMengeneinheit(MengenEinheitBean mengenEinheitBean) {
		// Das Model wird geändert.
		((ArtikelBean) getModelBean().getIBean())
				.setMengenEinheitBean(mengenEinheitBean);
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	/**
	 * This method initializes jButtonMatchCodeLieferant
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonMatchCodeLieferant() {
		if (jButtonMatchCodeLieferant == null) {
			jButtonMatchCodeLieferant = new JButton();
			jButtonMatchCodeLieferant.setPreferredSize(new Dimension(43, 20));
			jButtonMatchCodeLieferant.setText("...");
			jButtonMatchCodeLieferant
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								// if (pruefeFehler()) {
								((ArtikelDetailsController) getController())
										.holeLieferanten();
								// }
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
		return jButtonMatchCodeLieferant;
	}

	protected void setMatchCodeLieferanten(LhBean lhBean) {
		// getJTextFieldHerstellerLieferant().setText(lhBean.getName());
		// getJTextFieldHerstellerLieferant().requestFocusInWindow();

		// Das Model wird geändert.
		((ArtikelBean) getModelBean().getIBean()).setHersteller(lhBean);
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();

	}

	/**
	 * This method initializes jTextFieldBemerkung
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldBemerkung() {
		if (jTextFieldBemerkung == null) {
			jTextFieldBemerkung = new JFormattedTextField();
			jTextFieldBemerkung = new JFormattedTextField(
					new LagerStringFormatter(0, 70));
			jTextFieldBemerkung.setInputVerifier(LagerFormate
					.getInputVerifier());
			// jTextFieldBemerkung.addPropertyChangeListener("value", this);
		}
		return jTextFieldBemerkung;
	}

	private class ArtikelInBaugruppe {
		private BaugruppeArtikelBean baugruppeArtikelBean;
		private BaugruppeBean baugruppeBean;

		public ArtikelInBaugruppe(BaugruppeArtikelBean baugruppeArtikelBean,
				BaugruppeBean baugruppeBean) {
			super();
			this.baugruppeArtikelBean = baugruppeArtikelBean;
			this.baugruppeBean = baugruppeBean;
		}

		private BaugruppeArtikelBean getBaugruppeArtikelBean() {
			return baugruppeArtikelBean;
		}

		private BaugruppeBean getBaugruppeBean() {
			return baugruppeBean;
		}
	}

	// private ArrayList<BaugruppeBean> getBaugruppeBeans() {
	// if (baugruppeBeans == null) {
	// baugruppeBeans = new ArrayList<BaugruppeBean>();
	// }
	// return baugruppeBeans;
	// }
	//
	// private ArtikelBean getArtikelBean() {
	// if (artikelBean==null){
	// artikelBean = new ArtikelBean();
	// }
	// return artikelBean;
	// }

	protected ArrayList<ArtikelInBaugruppe> getArtikelInBaugruppes() {
		if (artikelInBaugruppes == null) {
			artikelInBaugruppes = new ArrayList<ArtikelInBaugruppe>();
		}
		return artikelInBaugruppes;
	}

	/**
	 * This method initializes jToolBar3
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJToolBar3() {
		if (jToolBar3 == null) {
			jToolBar3 = new JToolBar();
			jToolBar3.setFloatable(false);
			jToolBar3.add(getJButtonNeuArtikelBaugruppe1());
			jToolBar3.add(getJButtonLoeschenArtikelBaugruppe1());
		}
		return jToolBar3;
	}

	/**
	 * This method initializes jButtonNeuArtikelBaugruppe1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonNeuArtikelBaugruppe1() {
		if (jButtonNeuArtikelBaugruppe1 == null) {
			jButtonNeuArtikelBaugruppe1 = new JButton();
			jButtonNeuArtikelBaugruppe1.setText("Neu");
			jButtonNeuArtikelBaugruppe1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								getController().erstelleNeuenSatz(
										ModelKnotenTyp.LIEFERANT_BESTELLNUMMER);
							}
						}
					});
		}
		return jButtonNeuArtikelBaugruppe1;
	}

	/**
	 * This method initializes jButtonLoeschenArtikelBaugruppe1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLoeschenArtikelBaugruppe1() {
		if (jButtonLoeschenLieferantenBestellnummer == null) {
			jButtonLoeschenLieferantenBestellnummer = new JButton();
			jButtonLoeschenLieferantenBestellnummer.setText("Löschen");
			jButtonLoeschenLieferantenBestellnummer
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								int aktuelleZeile = jTableKatalog
										.getSelectedRow();
								int anzahlRows = jTableKatalog.getModel()
										.getRowCount();
								if (getModelBean() != null && anzahlRows > 0
										&& anzahlRows >= aktuelleZeile) {
									int anzBestand = jTable.getModel()
											.getRowCount();
									int anzBaugruppen = jTableArtikelBaugruppe
											.getModel().getRowCount();
									int anzUnterArtikel = jTableUnterArtikel
											.getModel().getRowCount();
									ModelKnotenBean modelKnotenBean = getModelBean()
											.getKinderList().get(
													anzBestand + aktuelleZeile
															+ anzBaugruppen
															+ anzUnterArtikel);
									// getController().selectKnoten(
									// modelKnotenBean);
									getController().loeschePosition(
											(Bean) modelKnotenBean.getIBean());
									// getController().erstelleNeuenSatz(
									// ModelKnotenTyp.BAUGRUPPE_ARTIKEL);
								}
							}
						}
					});
		}
		return jButtonLoeschenLieferantenBestellnummer;
	}

	/**
	 * This method initializes jScrollPane1
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTableKatalog());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTableKatalog
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTableKatalog() {
		if (jTableKatalog == null) {
			jTableKatalog = new JTable();
			jTableKatalog.setModel(getAbstractTableModelBestellnummer());
			jTableKatalog.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			jTableKatalog.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) {
						// ausgewählte Bean wird ermittelt.
						int aktuelleZeile = jTableKatalog.getSelectedRow();
						int anzahlRows = jTableKatalog.getModel().getRowCount();
						if (getModelBean() != null && anzahlRows > 0
								&& anzahlRows >= aktuelleZeile) {
							int anzBestand = jTable.getModel().getRowCount();
							int anzBaugruppen = jTableArtikelBaugruppe
									.getModel().getRowCount();
							int anzUnterArtikel = jTableUnterArtikel.getModel()
									.getRowCount();
							ModelKnotenBean modelKnotenBean = getModelBean()
									.getKinderList().get(
											anzBestand + aktuelleZeile
													+ anzBaugruppen
													+ anzUnterArtikel);
							getController().selectKnoten(modelKnotenBean);
						}

					}
					;
				}
			});

		}

		return jTableKatalog;
	}

	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setPreferredSize(new Dimension(458, 600));
			jTabbedPane.addTab("Lagerplatz", BestandspackstueckModelKnotenBean
					.getEasyIcon(), getJPanelAllgemein(), null);
			jTabbedPane.addTab("Baugruppen", BaugruppeArtikelModelKnotenBean
					.getEasyIcon(), getJPanel(), null);
			jTabbedPane.addTab("Oberartikel", UnterArtikelModelKnotenBean
					.getEasyIcon(), getJPanelOberArtikel(), null);
			jTabbedPane.addTab("Unterartikel", UnterArtikelModelKnotenBean
					.getEasyIcon(), getJPanelUnterArtikel(), null);
			jTabbedPane.addTab("Katalog",
					LieferantenBestellnummerModelKnotenBean.getEasyIcon(),
					getJPanelKatalog(), null);

			ChangeListener changeListener = new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent changeEvent) {
					JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
							.getSource();
					int ausgewaehlterReiterIndex = sourceTabbedPane
							.getSelectedIndex();
					// int oberArtikelReiterIndex = ArtikelDetailsView.
					// this.getJTabbedPane().indexOfTabComponent(
					// ArtikelDetailsView.this.getJPanelOberArtikel()
					// );
					switch (ausgewaehlterReiterIndex) {
					case 0:
						((ArtikelDetailsController) ArtikelDetailsView.this
								.getController())
								.setAktiverReiter(ArtikelModel.AktiverReiter.LAGERPLATZ);
						break;
					case 1:
						((ArtikelDetailsController) ArtikelDetailsView.this
								.getController())
								.setAktiverReiter(ArtikelModel.AktiverReiter.BAUGRUPPEN);
						break;
					case 2:
						((ArtikelDetailsController) ArtikelDetailsView.this
								.getController())
								.setAktiverReiter(ArtikelModel.AktiverReiter.OBERARTIKEL);
						break;
					case 3:
						((ArtikelDetailsController) ArtikelDetailsView.this
								.getController())
								.setAktiverReiter(ArtikelModel.AktiverReiter.UNTERARTIKEL);
						break;
					case 4:
						((ArtikelDetailsController) ArtikelDetailsView.this
								.getController())
								.setAktiverReiter(ArtikelModel.AktiverReiter.KATALOG);
						break;
					default:
						((ArtikelDetailsController) ArtikelDetailsView.this
								.getController())
								.setAktiverReiter(ArtikelModel.AktiverReiter.UNKNOWN);
						break;
					}
				}
			};
			jTabbedPane.addChangeListener(changeListener);

		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanelKatalog
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelKatalog() {
		if (jPanelKatalog == null) {
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.fill = GridBagConstraints.BOTH;
			gridBagConstraints24.weighty = 1.0;
			gridBagConstraints24.gridx = 1;
			gridBagConstraints24.gridy = 1;
			gridBagConstraints24.gridwidth = 2;
			gridBagConstraints24.weightx = 1.0;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.anchor = GridBagConstraints.WEST;
			gridBagConstraints20.gridx = 1;
			gridBagConstraints20.gridy = 0;
			gridBagConstraints20.weightx = 1.0;
			gridBagConstraints20.fill = GridBagConstraints.VERTICAL;
			jPanelKatalog = new JPanel();
			jPanelKatalog.setLayout(new GridBagLayout());
			jPanelKatalog.add(getJScrollPane1(), gridBagConstraints24);
			jPanelKatalog.add(getJToolBar3(), gridBagConstraints20);
		}
		return jPanelKatalog;
	}

	/**
	 * This method initializes jPanelAllgemein
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelAllgemein() {
		if (jPanelAllgemein == null) {
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.anchor = GridBagConstraints.WEST;
			gridBagConstraints26.gridx = -1;
			gridBagConstraints26.gridy = -1;
			gridBagConstraints26.weightx = 1.0;
			gridBagConstraints26.weighty = 0.0D;
			gridBagConstraints26.fill = GridBagConstraints.VERTICAL;
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.fill = GridBagConstraints.BOTH;
			gridBagConstraints25.gridx = 0;
			gridBagConstraints25.gridy = 1;
			gridBagConstraints25.weightx = 1.0;
			gridBagConstraints25.weighty = 1.0;
			gridBagConstraints25.gridwidth = 3;
			jPanelAllgemein = new JPanel();
			jPanelAllgemein.setLayout(new GridBagLayout());
			jPanelAllgemein.add(getJToolBar(), gridBagConstraints26);
			jPanelAllgemein.add(getJScrollPane(), gridBagConstraints25);
		}
		return jPanelAllgemein;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.gridx = -1;
			gridBagConstraints21.gridy = -1;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJToolBar2(), gridBagConstraints21);
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.fill = GridBagConstraints.BOTH;
			gridBagConstraints27.gridx = 0;
			gridBagConstraints27.gridy = 1;
			gridBagConstraints27.weightx = 1.0;
			gridBagConstraints27.weighty = 1.0;
			gridBagConstraints27.gridwidth = 3;
			jPanel.add(getJScrollPaneZugriffsrechte(), gridBagConstraints27);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanelUnterArtikel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelUnterArtikel() {
		if (jPanelUnterArtikel == null) {
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.fill = GridBagConstraints.BOTH;
			gridBagConstraints28.gridy = 1;
			gridBagConstraints28.weightx = 1.0;
			gridBagConstraints28.weighty = 1.0;
			gridBagConstraints28.gridx = 0;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints19.anchor = GridBagConstraints.WEST;
			gridBagConstraints19.weightx = 1.0;
			jPanelUnterArtikel = new JPanel();
			jPanelUnterArtikel.setLayout(new GridBagLayout());
			jPanelUnterArtikel.add(getJToolBarUnterArtikel(),
					gridBagConstraints19);
			jPanelUnterArtikel.add(getJScrollPaneUnterArtikel(),
					gridBagConstraints28);
		}
		return jPanelUnterArtikel;
	}

	/**
	 * This method initializes jToolBarUnterArtikel
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJToolBarUnterArtikel() {
		if (jToolBarUnterArtikel == null) {
			jToolBarUnterArtikel = new JToolBar();
			jToolBarUnterArtikel.setFloatable(false);
			jToolBarUnterArtikel.add(getJButtonNeuerUnterArtikel());
			jToolBarUnterArtikel.add(getJButtonLoeschenUnterArtikel());
		}
		return jToolBarUnterArtikel;
	}

	/**
	 * This method initializes jButtonNeuerUnterArtikel
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonNeuerUnterArtikel() {
		if (jButtonNeuerUnterArtikel == null) {
			jButtonNeuerUnterArtikel = new JButton();
			jButtonNeuerUnterArtikel.setText("Neu");
			jButtonNeuerUnterArtikel
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								getController().erstelleNeuenSatz(
										ModelKnotenTyp.UNTER_ARTIKEL);
							}
						}
					});
		}
		return jButtonNeuerUnterArtikel;
	}

	/**
	 * This method initializes jButtonLoeschenUnterArtikel
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLoeschenUnterArtikel() {
		if (jButtonLoeschenUnterArtikel == null) {
			jButtonLoeschenUnterArtikel = new JButton();
			jButtonLoeschenUnterArtikel.setText("Löschen");
			jButtonLoeschenUnterArtikel
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								int aktuelleZeile = jTableUnterArtikel
										.getSelectedRow();
								int anzahlRows = jTableUnterArtikel.getModel()
										.getRowCount();
								if (getModelBean() != null && anzahlRows > 0
										&& anzahlRows >= aktuelleZeile) {
									int anzBestand = jTable.getModel()
											.getRowCount();
									int anzBaugruppe = jTableArtikelBaugruppe
											.getModel().getRowCount();
									ModelKnotenBean modelKnotenBean = getModelBean()
											.getKinderList().get(
													anzBestand + anzBaugruppe
															+ aktuelleZeile);
									// getController().selectKnoten(
									// modelKnotenBean);
									getController().loeschePosition(
											(Bean) modelKnotenBean.getIBean());
									// getController().erstelleNeuenSatz(
									// ModelKnotenTyp.BAUGRUPPE_ARTIKEL);
								}
							}
						}
					});
		}
		return jButtonLoeschenUnterArtikel;
	}

	/**
	 * This method initializes jScrollPaneUnterArtikel
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneUnterArtikel() {
		if (jScrollPaneUnterArtikel == null) {
			jScrollPaneUnterArtikel = new JScrollPane();
			jScrollPaneUnterArtikel.setViewportView(getJTableUnterArtikel());
		}
		return jScrollPaneUnterArtikel;
	}

	/**
	 * This method initializes jTableUnterArtikel
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTableUnterArtikel() {
		if (jTableUnterArtikel == null) {
			jTableUnterArtikel = new JTable();
			jTableUnterArtikel.setModel(getAbstractTableModelUnterArtikel());
			jTableUnterArtikel
					.addFocusListener(new java.awt.event.FocusAdapter() {
						public void focusGained(java.awt.event.FocusEvent e) {
							pruefeFehler();
						}
					});
			jTableUnterArtikel
					.addMouseListener(new java.awt.event.MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							if (e.getClickCount() == 2) {
								// ausgewählte Bean wird ermittelt.
								int aktuelleZeile = jTableUnterArtikel
										.getSelectedRow();
								int anzahlRows = jTableUnterArtikel.getModel()
										.getRowCount();
								if (getModelBean() != null && anzahlRows > 0
										&& anzahlRows >= aktuelleZeile) {
									int anzBestand = jTable.getModel()
											.getRowCount();
									int anzBaugruppen = jTableArtikelBaugruppe
											.getModel().getRowCount();
									ModelKnotenBean modelKnotenBean = getModelBean()
											.getKinderList().get(
													anzBestand + anzBaugruppen
															+ aktuelleZeile);
									getController().selectKnoten(
											modelKnotenBean);
								}

							}
						}
					});
		}
		return jTableUnterArtikel;

	}

	/**
	 * This method initializes jScrollPaneOberArtikel
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneOberArtikel() {
		if (jScrollPaneOberArtikel == null) {
			jScrollPaneOberArtikel = new JScrollPane();
			jScrollPaneOberArtikel.setViewportView(getJTableOberArtikel());
		}
		return jScrollPaneOberArtikel;
	}

	/**
	 * This method initializes jTableOberArtikel
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTableOberArtikel() {
		if (jTableOberArtikel == null) {
			jTableOberArtikel = new JTable();
			jTableOberArtikel.setModel(getAbstractTableModelOberArtikel());
		}
		return jTableOberArtikel;
	}

	/**
	 * This method initializes jPanelOberArtikel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelOberArtikel() {
		if (jPanelOberArtikel == null) {
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			gridBagConstraints29.fill = GridBagConstraints.BOTH;
			gridBagConstraints29.weighty = 1.0;
			gridBagConstraints29.weightx = 1.0;
			jPanelOberArtikel = new JPanel();
			jPanelOberArtikel.setLayout(new GridBagLayout());
			jPanelOberArtikel.add(getJScrollPaneOberArtikel(),
					gridBagConstraints29);

		}
		return jPanelOberArtikel;
	}

}