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
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
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
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageModelKnotenBean; //import de.keag.lager.panels.frame.artikel.model.ArtikelBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.zeile.model.ZeileModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung.BaDetailsController;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.position.BaPosDetailsController;

import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
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
	private JToolBar jToolBar2 = null;
	private JButton jButtonNeuZeile = null;
	private JButton jButtonLoeschenZeile = null;
	private JScrollPane jScrollPaneZeile = null;
	private JTable jTableZeilen = null;
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
//	private ArrayList<BaugruppeBean> baugruppeBeans; // @jve:decl-index=0:
//	private ArtikelBean artikelBean; 

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
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.gridx = 0;
		gridBagConstraints23.gridy = 6;
		jLabelBemerkung = new JLabel();
		jLabelBemerkung.setText("Bemerkung");
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints19.gridy = 6;
		gridBagConstraints19.weightx = 1.0;
		gridBagConstraints19.gridx = 1;
		GridBagConstraints gridBagConstraints181 = new GridBagConstraints();
		gridBagConstraints181.gridx = 2;
		gridBagConstraints181.gridy = 12;
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.gridx = 2;
		gridBagConstraints17.gridy = 11;
		GridBagConstraints gridBagConstraints161 = new GridBagConstraints();
		gridBagConstraints161.gridx = 2;
		gridBagConstraints161.gridy = 10;
		GridBagConstraints gridBagConstraints151 = new GridBagConstraints();
		gridBagConstraints151.gridx = 2;
		gridBagConstraints151.gridy = 14;
		jLabelRechtsUnten1 = new JLabel();
		jLabelRechtsUnten1.setText("     ");
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints14.gridy = 13;
		gridBagConstraints14.weightx = 1.0;
		gridBagConstraints14.gridx = 1;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 12;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints12.gridy = 11;
		gridBagConstraints12.weightx = 1.0;
		gridBagConstraints12.gridx = 1;
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints111.gridy = 10;
		gridBagConstraints111.weightx = 1.0;
		gridBagConstraints111.gridx = 1;
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints10.gridy = 9;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.gridy = 8;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.gridx = 1;
		GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
		gridBagConstraints81.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints81.gridy = 7;
		gridBagConstraints81.weightx = 1.0;
		gridBagConstraints81.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 0;
		gridBagConstraints7.gridy = 13;
		jLabelEmpfohleneBestellMenge = new JLabel();
		jLabelEmpfohleneBestellMenge.setText("Empfohlene Bestellmenge");
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.gridx = 0;
		gridBagConstraints61.gridy = 12;
		jLabelHersteller = new JLabel();
		jLabelHersteller.setText("Hersteller");
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.gridy = 11;
		jLabelMengenEinheit = new JLabel();
		jLabelMengenEinheit.setText("Mengeneinheit");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.gridy = 10;
		jLabelKostenstelle = new JLabel();
		jLabelKostenstelle.setText("Kostenstelle");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.gridy = 9;
		jLabelMindestBestand = new JLabel();
		jLabelMindestBestand.setText("Mindestbestand");
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.gridx = 0;
		gridBagConstraints22.gridy = 8;
		jLabelKegNr = new JLabel();
		jLabelKegNr.setText("KE-Nr");
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.gridy = 7;
		jLabelTyp = new JLabel();
		jLabelTyp.setText("Typ");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 2;
		gridBagConstraints1.weightx = 1.0D;
		gridBagConstraints1.gridy = 5;
		jLabelRechtsUnten = new JLabel();
		jLabelRechtsUnten.setText("     ");
		GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
		gridBagConstraints34.fill = GridBagConstraints.BOTH;
		gridBagConstraints34.gridy = 18;
		gridBagConstraints34.weightx = 1.0;
		gridBagConstraints34.weighty = 1.0;
		gridBagConstraints34.gridwidth = 3;
		gridBagConstraints34.gridx = 0;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints21.gridy = 17;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.anchor = GridBagConstraints.WEST;
		gridBagConstraints21.gridx = 0;
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.fill = GridBagConstraints.BOTH;
		gridBagConstraints51.gridy = 16;
		gridBagConstraints51.weightx = 1.0;
		gridBagConstraints51.weighty = 1.0;
		gridBagConstraints51.gridwidth = 3;
		gridBagConstraints51.gridx = 0;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 5;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.gridx = 0;
		gridBagConstraints41.fill = GridBagConstraints.BOTH;
		gridBagConstraints41.weightx = 1.0D;
		gridBagConstraints41.weighty = 1.0D;
		gridBagConstraints41.insets = new Insets(1, 1, 1, 1);
		gridBagConstraints41.ipadx = 1;
		gridBagConstraints41.gridy = 0;
		GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
		gridBagConstraints32.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints32.gridy = 15;
		gridBagConstraints32.weightx = 1.0;
		gridBagConstraints32.anchor = GridBagConstraints.WEST;
		gridBagConstraints32.weighty = 0.0D;
		gridBagConstraints32.gridx = 0;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 5;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.gridy = 19;
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
		gridBagConstraints6.gridy = 1;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 5;
		jLabelBezeichnung = new JLabel();
		jLabelBezeichnung.setText("Bezeichnung");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelId = new JLabel();
		jLabelId.setText("ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(
				SystemColor.controlShadow, 1));
		this.add(jLabelId, gridBagConstraints);
		this.add(jLabelBezeichnung, gridBagConstraints2);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJTextFieldBezeichnung(), gridBagConstraints8);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(getJScrollPane(), gridBagConstraints51);
		this.add(getJToolBar2(), gridBagConstraints21);
		this.add(getJScrollPaneZugriffsrechte(), gridBagConstraints34);
		this.add(jLabelRechtsUnten, gridBagConstraints1);
		this.add(jLabelTyp, gridBagConstraints11);
		this.add(jLabelKegNr, gridBagConstraints22);
		this.add(jLabelMindestBestand, gridBagConstraints3);
		this.add(jLabelKostenstelle, gridBagConstraints4);
		this.add(jLabelMengenEinheit, gridBagConstraints5);
		this.add(jLabelHersteller, gridBagConstraints61);
		this.add(jLabelEmpfohleneBestellMenge, gridBagConstraints7);
		this.add(getJTextFieldTyp(), gridBagConstraints81);
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
		this.add(getJTextFieldBemerkung(), gridBagConstraints19);
		this.add(jLabelBemerkung, gridBagConstraints23);
		this.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				ArtikelDetailsView.this.setBorder(BorderFactory
						.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event
				// stub focusGained()
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
	public void zeichneDich(ModelKnotenBean modelKnotenBean) {
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
				getJTextFieldBezeichnung().removePropertyChangeListener(
						"value", this);
				getJTextFieldBezeichnung().setValue(
						artikelBean.getBezeichnung());
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
				setBaugruppeBeans(baugruppeBeans,artikelBean);
				((AbstractTableModel) jTableZeilen.getModel())
						.fireTableDataChanged();

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

	private void setBaugruppeBeans(ArrayList<BaugruppeBean> baugruppeBeans, ArtikelBean artikelBean) {
		getArtikelInBaugruppes().clear();
		for(int i = 0; i < baugruppeBeans.size();i++){
			fuelleArtikelInBaugruppes(getArtikelInBaugruppes(),baugruppeBeans.get(i),artikelBean);
		}
	}

	private void fuelleArtikelInBaugruppes(ArrayList<ArtikelInBaugruppe> artikelInBaugruppes,
			BaugruppeBean baugruppeBean, ArtikelBean artikelBean) {
		//Alle Artikel der Baugrupe werden durchlaufen. Der gesuchte Artikel wird der Liste hinzugefügt
		for (int i = 0; i < baugruppeBean.getBaugruppeArtikelBeans().size(); i++) {
			BaugruppeArtikelBean baugruppeArtikelBean = baugruppeBean
				.getBaugruppeArtikelBeans().get(i);
			if (baugruppeArtikelBean.getArtikel().getId()==artikelBean.getId()){
				if (!baugruppeArtikelBean.getArtikel().getId().equals(0)){
					artikelInBaugruppes.add(new ArtikelInBaugruppe(baugruppeArtikelBean, baugruppeBean));
				}
			}	
		}	
		//Unterbaugruppen werden durchsucht. (Rekursion)
		for (int i = 0; i < baugruppeBean.getBaugruppeBeans().size(); i++) {
			fuelleArtikelInBaugruppes(artikelInBaugruppes,baugruppeBean.getBaugruppeBeans().get(i),artikelBean);
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
				private String[] columnNames = { "id", "Halle", "Etage",
						"Zeile", "Säule", "Ebene", "Position", "Menge" };

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
							return bean.getPositionBean().getEbeneBean()
									.getSaeuleBean().getZeileBean()
									.getEtageBean().toString();
						}
						case 3: {
							return bean.getPositionBean().getEbeneBean()
									.getSaeuleBean().getZeileBean().toString();
						}
						case 4: {
							return bean.getPositionBean().getEbeneBean()
									.getSaeuleBean().toString();
						}
						case 5: {
							return bean.getPositionBean().getEbeneBean()
									.toString();
						}
						case 6: {
							return bean.getPositionBean().toString();
						}
						case 7: {
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

	private AbstractTableModel getAbstractTableModelZeile() {
		if (abstractTableModelZeile == null) {
			abstractTableModelZeile = new AbstractTableModel() {
				private String[] columnNames = { "Anlage/Baugruppe...", "Menge" };

				@Override
				public String getColumnName(int column) {
					return columnNames[column];
				}

				@Override
				public Object getValueAt(int row, int column) {
					ArtikelInBaugruppe artikelInBaugruppe  = getArtikelInBaugruppes().get(row);
					switch (column) {
					case 0: {
						return artikelInBaugruppe.getBaugruppeBean().getName();
					}
					case 1: {
						return artikelInBaugruppe.getBaugruppeArtikelBean().getEingebauteMenge();
					}
					case 999:
						return artikelInBaugruppe;
					default:
						return "Fehler, column:"
								+ new Integer(column).toString();
					}
//					if (getModelBean() == null) {
//						return "";
//					}
//
//					// Finde heraus, ab welcher Zeile die Zugriffsrechte
//					// beginnen.
//					int zugriffsrechtAbZeile = 0;
//					for (int i = 0; i < getModelBean().getKinderList().size()
//							&& zugriffsrechtAbZeile == 0; i++) {
//						if (getModelBean().getKinderList().get(i) instanceof ZeileModelKnotenBean) {
//							zugriffsrechtAbZeile = i;
//						}
//					}
//
//					ModelKnotenBean modelKnotenBean = getModelBean()
//							.getKinderList().get(zugriffsrechtAbZeile + row);
//					if (modelKnotenBean.getIBean() instanceof ZeileBean) {
//						ZeileBean zeileBean = (ZeileBean) modelKnotenBean
//								.getIBean();
//						switch (column) {
//						case 0: {
//							return zeileBean.getNummer();
//						}
//						case 999:
//							return zeileBean;
//						default:
//							return "Fehler, column:"
//									+ new Integer(column).toString();
//						}
//					} else {
//						return "Falsche Klasse:"
//								+ modelKnotenBean.getIBean().toString();
//					}
				}

				@Override
				public int getRowCount() {
					return getArtikelInBaugruppes().size();
					// if (getModelBean() == null) {
					// return 0;
					// } else {
					// int anzahlZugriffsrecht = 0;
					// for (int i = 0; i < getModelBean().getKinderList()
					// .size(); i++) {
					// if (getModelBean().getKinderList().get(i) instanceof
					// ZeileModelKnotenBean) {
					// anzahlZugriffsrecht++;
					// }
					// }
					// return anzahlZugriffsrecht;
					// }
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModelZeile;
	}

	@Override
	public void setModelBean(ModelKnotenBean benutzerModelBean) {
		this.modelKnotenBean = benutzerModelBean;
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
			jPanelOben.setPreferredSize(new Dimension(10, 10));
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
			jTable.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
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
			jToolBar2.add(getJButtonNeuZugriffsrecht());
			jToolBar2.add(getJButtonLoeschenZugriffsrecht());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes jButtonNeuZugriffsrecht
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonNeuZugriffsrecht() {
		if (jButtonNeuZeile == null) {
			jButtonNeuZeile = new JButton();
			jButtonNeuZeile.setText("Neu");
			jButtonNeuZeile
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								getController().erstelleNeuenSatz(
										ModelKnotenTyp.ZEILE);
							}
						}
					});

		}
		return jButtonNeuZeile;
	}

	/**
	 * This method initializes jButtonLoeschenZugriffsrecht
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLoeschenZugriffsrecht() {
		if (jButtonLoeschenZeile == null) {
			jButtonLoeschenZeile = new JButton();
			jButtonLoeschenZeile.setText("Löschen");
			jButtonLoeschenZeile
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (pruefeFehler()) {
								if (getJTableZeilen().getSelectedRow() >= 0) {
									ZeileBean bean = (ZeileBean) getJTableZeilen()
											.getModel().getValueAt(
													getJTableZeilen()
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

		}
		return jButtonLoeschenZeile;
	}

	/**
	 * This method initializes jScrollPaneZugriffsrechte
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneZugriffsrechte() {
		if (jScrollPaneZeile == null) {
			jScrollPaneZeile = new JScrollPane();
			jScrollPaneZeile.setViewportView(getJTableZeilen());
		}
		return jScrollPaneZeile;
	}

	/**
	 * This method initializes jTableZeile
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTableZeilen() {
		if (jTableZeilen == null) {
			jTableZeilen = new JTable();
			jTableZeilen.setModel(getAbstractTableModelZeile());
			jTableZeilen.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			jTableZeilen.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) {
						// ausgewählte Bean wird ermittelt.
						int aktuelleZeile = jTableZeilen.getSelectedRow();
						if (getModelBean() != null
								&& getModelBean().getKinderList().size() > 0
								&& getModelBean().getKinderList().size() >= aktuelleZeile) {
							ModelKnotenBean modelKnotenBean = getModelBean()
									.getKinderList().get(aktuelleZeile);
							getController().selectKnoten(modelKnotenBean);
						}

					}
					;
				}
			});

		}
		return jTableZeilen;
	}

	/**
	 * This method initializes jTextFieldTyp
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldTyp() {
		if (jTextFieldTyp == null) {
			jTextFieldTyp = new JFormattedTextField(new LagerStringFormatter(1,
					40));
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
					new LagerEmptyNumberFormatter(1, 999999999));
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
			jButtonMatchcodeKostenstelle.setText("...");
			jButtonMatchcodeKostenstelle
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()) {
									((ArtikelDetailsController) getController())
											.holeKostenstelle();
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
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()) {
									((ArtikelDetailsController) getController())
											.holeMengeneinheit();
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
			jButtonMatchCodeLieferant.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeLieferant.setText("...");
			jButtonMatchCodeLieferant
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()) {
									((ArtikelDetailsController) getController())
											.holeLieferanten();
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

	private class ArtikelInBaugruppe{
		private BaugruppeArtikelBean baugruppeArtikelBean;
		private BaugruppeBean baugruppeBean;
		public ArtikelInBaugruppe(BaugruppeArtikelBean baugruppeArtikelBean, BaugruppeBean baugruppeBean) {
			super();
			this.baugruppeArtikelBean = baugruppeArtikelBean;
			this.baugruppeBean = baugruppeBean;
			// TODO Auto-generated constructor stub
		}
		private BaugruppeArtikelBean getBaugruppeArtikelBean() {
			return baugruppeArtikelBean;
		}
		private BaugruppeBean getBaugruppeBean() {
			return baugruppeBean;
		}
	}
//	private ArrayList<BaugruppeBean> getBaugruppeBeans() {
//		if (baugruppeBeans == null) {
//			baugruppeBeans = new ArrayList<BaugruppeBean>();
//		}
//		return baugruppeBeans;
//	}
//
//	private ArtikelBean getArtikelBean() {
//		if (artikelBean==null){
//			artikelBean = new ArtikelBean();
//		}
//		return artikelBean;
//	}

	protected ArrayList<ArtikelInBaugruppe> getArtikelInBaugruppes() {
		if(artikelInBaugruppes==null){
			artikelInBaugruppes = new ArrayList<ArtikelInBaugruppe>();
		}
		return artikelInBaugruppes;
	}

}
