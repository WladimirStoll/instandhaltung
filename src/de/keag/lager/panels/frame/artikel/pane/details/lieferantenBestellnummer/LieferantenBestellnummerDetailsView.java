package de.keag.lager.panels.frame.artikel.pane.details.lieferantenBestellnummer;

import java.awt.Component;
import java.awt.GridBagLayout;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.formatter.LagerStringFormatter;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.pane.details.lhKatalog.LhKatalogDetailsController;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;

import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class LieferantenBestellnummerDetailsView extends DetailsView implements
		PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController; // @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null; // @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelKatalog = null;
	private JTextField jTextKatalog = null;
	private JButton jButtonMatchcodeKatalog = null;
	private JLabel jLabelBestellnummer = null;
	private JFormattedTextField jTextBestellnummer = null;
	private JLabel jLabelPreis = null;
	private JFormattedTextField jTextFieldPreis = null;
	private JLabel jLabelLeer = null;
	private JLabel jLabelLeer2 = null;
	private JLabel jLabelKatalogseite = null;
	private JFormattedTextField jTextKatalogseite = null;
	private JTextField jTextLieferant = null;
	private JLabel jLabelLieferant = null;

	/**
	 * This is the default constructor
	 * 
	 * @param BenutzerPosAbteilungDetailsController
	 */
	public LieferantenBestellnummerDetailsView(
			DetailsController LieferantenBestellnummerDetailsController) {
		super();
		setController(LieferantenBestellnummerDetailsController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
		gridBagConstraints42.gridx = 0;
		gridBagConstraints42.anchor = GridBagConstraints.EAST;
		gridBagConstraints42.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints42.gridy = 12;
		jLabelLieferant = new JLabel();
		jLabelLieferant.setText("Lieferant");
		GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
		gridBagConstraints32.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints32.gridy = 12;
		gridBagConstraints32.weightx = 1.0;
		gridBagConstraints32.gridx = 1;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints21.gridy = 14;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 14;
		jLabelKatalogseite = new JLabel();
		jLabelKatalogseite.setText("Katalogseite");
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.gridx = 0;
		gridBagConstraints10.weighty = 1.0D;
		gridBagConstraints10.gridy = 17;
		jLabelLeer2 = new JLabel();
		jLabelLeer2.setText(" ");
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.gridx = 0;
		gridBagConstraints9.weightx = 0.0D;
		gridBagConstraints9.weighty = 1.0D;
		gridBagConstraints9.gridy = 0;
		jLabelLeer = new JLabel();
		jLabelLeer.setText(" ");
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 16;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 0;
		gridBagConstraints7.anchor = GridBagConstraints.EAST;
		gridBagConstraints7.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints7.gridy = 16;
		jLabelPreis = new JLabel();
		jLabelPreis.setText("Preis");
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints41.gridy = 13;
		gridBagConstraints41.weightx = 2.0D;
		gridBagConstraints41.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.anchor = GridBagConstraints.EAST;
		gridBagConstraints31.weightx = 1.0D;
		gridBagConstraints31.weighty = 0.0D;
		gridBagConstraints31.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints31.gridy = 13;
		jLabelBestellnummer = new JLabel();
		jLabelBestellnummer.setText("Bestellnummer");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 2;
		gridBagConstraints4.weightx = 1.0D;
		gridBagConstraints4.anchor = GridBagConstraints.WEST;
		gridBagConstraints4.gridy = 11;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridy = 11;
		gridBagConstraints1.weightx = 2.0D;
		gridBagConstraints1.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.weighty = 0.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 11;
		jLabelKatalog = new JLabel();
		jLabelKatalog.setText("Katalog");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 10;
		gridBagConstraints3.weightx = 3.0D;
		gridBagConstraints3.gridx = 2;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 3;
		gridBagConstraints2.weightx = 1.0D;
		gridBagConstraints2.anchor = GridBagConstraints.WEST;
		gridBagConstraints2.gridy = 10;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 27;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 0.0D;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(jLabelKatalog, gridBagConstraints);
		this.add(getJTextKatalog(), gridBagConstraints1);
		this.add(getJButtonMatchcodeKatalog(), gridBagConstraints4);
		this.add(jLabelBestellnummer, gridBagConstraints31);
		this.add(getJTextBestellnummer(), gridBagConstraints41);
		this.add(jLabelPreis, gridBagConstraints7);
		this.add(getJTextFieldPreis(), gridBagConstraints8);
		this.add(jLabelLeer, gridBagConstraints9);
		this.add(jLabelLeer2, gridBagConstraints10);
		this.add(jLabelKatalogseite, gridBagConstraints11);
		this.add(getJTextKatalogseite(), gridBagConstraints21);
		this.add(getJTextLieferant(), gridBagConstraints32);
		this.add(jLabelLieferant, gridBagConstraints42);
	}

	@Override
	public void zeichneDich(ModelKnotenBean LieferantenBestellnummerModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); // alte Fehler werden gelöscht.
		setzeHintergrund();

		if (LieferantenBestellnummerModelBean != null) {
			if (LieferantenBestellnummerModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_BESTELLNUMMER) {
				setModelBean(LieferantenBestellnummerModelBean);// merken
				LieferantenBestellnummerBean lieferantenBestellnummerBean 
					= (LieferantenBestellnummerBean) LieferantenBestellnummerModelBean.getIBean();
				
//    			ArtikelBean artikelBean = lieferantenBestellnummerBean.getArtikel();
				KatalogBean katalogBean = lieferantenBestellnummerBean.getKatalogBean();
				
				getJTextKatalog().setText(katalogBean.getName());
				getJTextLieferant().setText(katalogBean.getHerstellerLieferantBean().getName());

				getJTextFieldPreis().removePropertyChangeListener("value", this);
				getJTextFieldPreis().setValue(lieferantenBestellnummerBean.getPreis());
				getJTextFieldPreis().addPropertyChangeListener("value", this);
				
				getJTextKatalogseite().removePropertyChangeListener("value", this);
				getJTextKatalogseite().setValue(lieferantenBestellnummerBean.getKatalogseite());
				getJTextKatalogseite().addPropertyChangeListener("value", this);
				
				getJTextBestellnummer().removePropertyChangeListener("value", this);
				getJTextBestellnummer().setValue(lieferantenBestellnummerBean.getBestellnummer());
				getJTextBestellnummer().addPropertyChangeListener("value", this);
				
				setEnabled(true);
				if (lieferantenBestellnummerBean.getBeanDBStatus()==BeanDBStatus.INSERT){
					//nur beim Anlegen ist die Auswahl möglich
					getJButtonMatchcodeKatalog().setEnabled(true);
				}else{
					getJButtonMatchcodeKatalog().setEnabled(false);
				}
				

				// Fehler anzeigen.
				for (int i = 0; i < LieferantenBestellnummerModelBean
						.getFehlerList().size(); i++) {
					Fehler fehler = LieferantenBestellnummerModelBean
							.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}

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
	public DetailsController getController() {
		return detailsController;
	}

	@Override
	public ModelKnotenBean getModelBean() {
		return modelKnotenBean;
	}

	@Override
	public void setModelBean(ModelKnotenBean modelKnotenBean) {
		this.modelKnotenBean = modelKnotenBean;
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

	public void setArtikel(ArtikelBean abteilungBean) {
		// Das Model wird geändert.
		LieferantenBestellnummerBean LieferantenBestellnummerBean = ((LieferantenBestellnummerBean) modelKnotenBean
				.getIBean());
		LieferantenBestellnummerBean.setArtikel(abteilungBean);
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setBaugruppe(BaugruppeBean baugruppeBean) {

		// LieferantenBestellnummerBean LieferantenBestellnummerBean =
		// ((LieferantenBestellnummerBean)modelKnotenBean.getIBean());
		// LieferantenBestellnummerBean.setBaugruppe(baugruppeBean);
		// //Alle Beobachter des Models werden über eine Änderung
		// benachrichtigt.
		// getController().ausgewaehlterKnotenIstGeandert();

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		// Das Model wird geändert.
		if (pruefeFehler()) {
			if (source == getJTextFieldPreis()) {
				leseAusgetJTextFieldPreis();
			}
			if (source == getJTextBestellnummer()) {
				leseAusgetJTextFieldBestellnummer();
			}
			if (source == getJTextKatalogseite()) {
				leseAusgetJTextFieldKatalogseite();
			}
		}

		// if (source==getJTextLiefertermin()){
		// leseAusgetJTextLiefertermin();
		// }
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
		System.out.println("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJTextFieldPreis() {
		LieferantenBestellnummerBean lieferantenBestellnummerBean = (LieferantenBestellnummerBean) getModelBean()
				.getIBean();
		Double wert = 0D;
		try {
			Log.log().finest(
					"LieferantenBestellnummerDetailsView:leseAusgetJTextFieldEingebauteMenge:"
							+ getJTextFieldPreis().getValue()
							+ "="
							+ lieferantenBestellnummerBean.getPreis()
									.toString());
			if (getJTextFieldPreis().getValue() != null) {
				wert = ((Number) getJTextFieldPreis().getValue())
						.doubleValue();
				lieferantenBestellnummerBean.setPreis(wert);
			}
			// boolean stopMe =
			// (LieferantenBestellnummerBean.getEingebauteMenge() == 1);
			Log.log().finest(
					"LieferantenBestellnummerDetailsView:leseAusgetJTextFieldEingebauteMenge:"
							+ getJTextFieldPreis().getValue()
							+ "="
							+ lieferantenBestellnummerBean.getPreis()
									.toString());
		} catch (NumberFormatException ex) {
		}
	}
	
	private void leseAusgetJTextFieldBestellnummer() {
		LieferantenBestellnummerBean lieferantenBestellnummerBean = (LieferantenBestellnummerBean) getModelBean()
				.getIBean();
		String wert = "";
		if (getJTextBestellnummer().getValue() != null) {
			wert = getJTextBestellnummer().getValue().toString();
			lieferantenBestellnummerBean.setBestellnummer(wert);
		}
	}

	private void leseAusgetJTextFieldKatalogseite() {
		LieferantenBestellnummerBean lieferantenBestellnummerBean = (LieferantenBestellnummerBean) getModelBean()
				.getIBean();
		String wert = "";
		if (getJTextKatalogseite().getValue() != null) {
			wert = getJTextKatalogseite().getValue().toString();
			lieferantenBestellnummerBean.setKatalogseite(wert);
		}
	}

	@Override
	public void setStandardFocusPosition() {
		Component focusComponent = null;
		for (int i = 0; i < this.getComponentCount() && focusComponent == null; i++) {
			focusComponent = this.getComponent(i);
			if (!focusComponent.isFocusOwner()) {
				focusComponent = null;
			}
		}
		if (focusComponent == null) {
			getJButtonMatchcodeKatalog().requestFocus();
			Log.log().finest(
					"Neuer Focus:" + getJButtonMatchcodeKatalog().toString());
		}
	}

	/**
	 * enabled = true: Die Maske darf bearbeitet werden. enabled = false: Die
	 * Maske darf NICHT bearbeitet werden.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		getJTextFieldPreis().setEditable(enabled);
		getJTextKatalogseite().setEditable(enabled);
		getJTextBestellnummer().setEditable(enabled);

		getJTextLieferant().setEnabled(false);
		getJTextKatalog().setEditable(false);

	}

	// /**
	// * This method initializes jButtonMatchCodeArtikel
	// *
	// * @return javax.swing.JButton
	// */
	// private JButton getJButtonMatchCodeArtikel() {
	// return null;
	// if (jButtonMatchCodeArtikel == null) {
	// jButtonMatchCodeArtikel = new JButton();
	// jButtonMatchCodeArtikel.setPreferredSize(new Dimension(30, 20));
	// jButtonMatchCodeArtikel.setText("...");
	// jButtonMatchCodeArtikel
	// .addActionListener(new java.awt.event.ActionListener() {
	// public void actionPerformed(java.awt.event.ActionEvent e) {
	// try {
	// if (pruefeFehler()){
	// ((LieferantenBestellnummerDetailsController)getController()).holeArtikel();
	// }
	// } catch (SQLException e1) {
	// getJComboBoxFehler().addItem(new
	// Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
	// e1.printStackTrace();
	// } catch (LagerException e1) {
	// getJComboBoxFehler().addItem(new
	// Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
	// e1.printStackTrace();
	// }
	// }
	// });
	//			
	// }
	// return jButtonMatchCodeArtikel;
	// }

	// /**
	// * This method initializes jTextFieldArtikel
	// *
	// * @return javax.swing.JTextField
	// */
	// private JTextField getJTextFieldArtikel() {
	// return null;
	// // if (jTextFieldArtikel == null) {
	// // jTextFieldArtikel = new JTextField();
	// // jTextFieldArtikel.setEditable(false);
	// // }
	// // return jTextFieldArtikel;
	// }

	@Override
	protected void setController(DetailsController detailsController) {
		this.detailsController = detailsController;
	}

	@Override
	protected void uebernehmeDaten() {
		Log.log().severe("nicht implementiert");

	}

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {

			// if (getJTextBestellnummerKEG().isFocusOwner()){
			// Log.log().severe("Test: owner");
			// }else{
			// Log.log().severe("Test: kein owner");
			// }
			//			
			// if (getJTextFieldEingebauteMenge().isFocusOwner()){
			// Log.log().severe("Test: owner");
			// }else{
			// Log.log().severe("Test: kein owner");
			// }
			//			

			if (getJTextKatalog().getText().isEmpty()) {
				errors.add(new Fehler(56));
			}

			if (!getJTextFieldPreis().getInputVerifier().shouldYieldFocus(
					getJTextFieldPreis())) {
				errors.add(new Fehler(122));
			} else {
				leseAusgetJTextFieldPreis();
			}
			
			if (!getJTextFieldPreis().getInputVerifier().shouldYieldFocus(
					getJTextBestellnummer())) {
				errors.add(new Fehler(119));
			} else {
				leseAusgetJTextFieldBestellnummer();
			}
			
			if (!getJTextFieldPreis().getInputVerifier().shouldYieldFocus(
					getJTextKatalogseite())) {
				errors.add(new Fehler(28));
			} else {
				leseAusgetJTextFieldKatalogseite();
			}
			
		} catch (Exception e) {
			// Alle Fehler abfangen
			errors.add(new Fehler(36, FehlerTyp.FEHLER, e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}

	/**
	 * This method initializes jTextKatalog
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextKatalog() {
		if (jTextKatalog == null) {
			jTextKatalog = new JTextField();
			jTextKatalog.setPreferredSize(new Dimension(80, 20));
			jTextKatalog.setEditable(false);
		}
		return jTextKatalog;
	}

	/**
	 * This method initializes jButtonMatchcodeKatalog
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonMatchcodeKatalog() {
		if (jButtonMatchcodeKatalog == null) {
			jButtonMatchcodeKatalog = new JButton();
			jButtonMatchcodeKatalog.setText("...");
			jButtonMatchcodeKatalog.setPreferredSize(new Dimension(43, 20));
			jButtonMatchcodeKatalog.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
//						if (pruefeFehler()){
							((LieferantenBestellnummerDetailsController)getController()).holeKatalog();
//						}
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
		return jButtonMatchcodeKatalog;
	}

	/**
	 * This method initializes jTextBestellnummer
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextBestellnummer() {
		if (jTextBestellnummer == null) {
			jTextBestellnummer = new JFormattedTextField(
					new LagerStringFormatter(1, 45));
			jTextBestellnummer
					.setInputVerifier(LagerFormate.getInputVerifier());
			jTextBestellnummer.setPreferredSize(new Dimension(80, 20));
			jTextBestellnummer.setEditable(false);
			jTextBestellnummer.addKeyListener(this);
		}
		return jTextBestellnummer;
	}

	/**
	 * This method initializes jTextFieldPreis
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldPreis() {
		if (jTextFieldPreis == null) {
			jTextFieldPreis = new JFormattedTextField(
					new LagerEmptyNumberFormatter(0, 10000,
							"Gültige Werte: 1 bis 10000"));
			jTextFieldPreis.setInputVerifier(LagerFormate.getInputVerifier());
			// jTextFieldEingebauteMenge.addPropertyChangeListener("value",
			// this);
			jTextFieldPreis.setPreferredSize(new Dimension(20, 20));
			// jTextFieldEingebauteMenge.setEditable(false);
			jTextFieldPreis.addKeyListener(this);
		}
		return jTextFieldPreis;
	}

	/**
	 * This method initializes jTextKatalogseite
	 * 
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextKatalogseite() {
		if (jTextKatalogseite == null) {
			jTextKatalogseite = new JFormattedTextField(
					new LagerStringFormatter(0, 45));
			jTextKatalogseite.setInputVerifier(LagerFormate.getInputVerifier());
			jTextKatalogseite.setPreferredSize(new Dimension(80, 20));
			jTextKatalogseite.setEditable(false);
			jTextKatalogseite.addKeyListener(this);
		}
		return jTextKatalogseite;
	}

	/**
	 * This method initializes jTextLieferant
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextLieferant() {
		if (jTextLieferant == null) {
			jTextLieferant = new JTextField();
			jTextLieferant.setPreferredSize(new Dimension(80, 20));
			jTextLieferant.setEditable(false);
		}
		return jTextLieferant;
	}

	public void setKatalog(KatalogBean katalogBean) {
		//Das Model wird geändert.
		LieferantenBestellnummerBean bean = ((LieferantenBestellnummerBean)modelKnotenBean.getIBean());
		bean.setKatalogBean(katalogBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

} // @jve:decl-index=0:visual-constraint="49,1"
