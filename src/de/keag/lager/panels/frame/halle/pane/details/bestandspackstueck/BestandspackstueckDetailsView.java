package de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck;

import java.awt.GridBagLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.View;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung.BenutzerAbteilungDetailsController;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.position.BaPosDetailsController;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

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
import javax.swing.text.DefaultFormatterFactory;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;


public class BestandspackstueckDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelBestandspackstueck = null;
	private JFormattedTextField jFormattedTextFieldId = null;
	private JLabel jLabelOben = null;
	private JLabel jLabelUnten = null;
	private JLabel jLabelAenderungsBenutzer = null;
	private JLabel jLabelAenderungsDatum = null;
	private JLabel jLabelKeg = null;
	private JLabel jLabelArtikelBezeichnung = null;
	private JLabel jLabelArtikelTyp = null;
	private JLabel jLabelArtikelHersteller = null;
	private JLabel jLabelMenge = null;
	private JLabel jLabelMengenEinheit = null;
	private JLabel jLabel = null;
	private JLabel jLabelOben2 = null;
	private JTextField jFormattedTextFieldAenderungsBenutzer = null;
	private JDateChooser jFormattedTextFieldAenderungsdatum = null;
	private JTextField jFormattedTextFieldKeg = null;
	private JTextField jFormattedTextFieldBezeichnung = null;
	private JTextField jFormattedTextFieldTyp = null;
	private JTextField jFormattedTextFieldHersteller = null;
	private JFormattedTextField jFormattedTextFieldMenge = null;
	private JTextField jFormattedTextFieldMengenEinheit = null;
	private JButton jButtonMatchcodeArtikel = null;
	private JButton jButtonMatchcodeMengeneinheit = null;
	private JLabel jLabelAbteilung = null;
	private JTextField jTextFieldAbteilung = null;
	private JButton jButtonMatchCodeAbteilung = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosZugriffsrechtDetailsController 
	 */
	public BestandspackstueckDetailsView(DetailsController BenutzerPosZugriffsrechtDetailsController) {
		super();
		setController(BenutzerPosZugriffsrechtDetailsController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 2;
		gridBagConstraints31.gridy = 2;
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints22.gridy = 2;
		gridBagConstraints22.weightx = 1.0;
		gridBagConstraints22.gridx = 1;
		GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
		gridBagConstraints110.gridx = 0;
		gridBagConstraints110.gridy = 2;
		jLabelAbteilung = new JLabel();
		jLabelAbteilung.setText("Abteilung");
		GridBagConstraints gridBagConstraints211 = new GridBagConstraints();
		gridBagConstraints211.gridx = 2;
		gridBagConstraints211.gridy = 9;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 2;
		gridBagConstraints21.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints21.gridy = 5;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 10;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.anchor = GridBagConstraints.WEST;
		gridBagConstraints18.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints7.gridy = 9;
		gridBagConstraints7.weightx = 1.0;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints7.gridx = 1;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints6.gridy = 8;
		gridBagConstraints6.weightx = 1.0;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridy = 7;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.anchor = GridBagConstraints.WEST;
		gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints5.gridx = 1;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints4.gridy = 6;
		gridBagConstraints4.weightx = 1.0;
		gridBagConstraints4.anchor = GridBagConstraints.WEST;
		gridBagConstraints4.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints4.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints2.gridy = 5;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.anchor = GridBagConstraints.CENTER;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints2.gridx = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.NONE;
		gridBagConstraints1.gridy = 4;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.anchor = GridBagConstraints.WEST;
		gridBagConstraints1.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints1.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.gridx = 1;
		GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
		gridBagConstraints20.gridx = 2;
		gridBagConstraints20.gridy = 0;
		jLabelOben2 = new JLabel();
		jLabelOben2.setText("     ");
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.gridx = 1;
		gridBagConstraints19.weightx = 1.0D;
		gridBagConstraints19.gridy = 0;
		jLabel = new JLabel();
		jLabel.setText(" ");
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.gridx = 0;
		gridBagConstraints17.anchor = GridBagConstraints.EAST;
		gridBagConstraints17.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints17.gridy = 10;
		jLabelMengenEinheit = new JLabel();
		jLabelMengenEinheit.setText("Mengeneinheit");
		GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
		gridBagConstraints16.gridx = 0;
		gridBagConstraints16.anchor = GridBagConstraints.EAST;
		gridBagConstraints16.insets = new Insets(20, 0, 0, 5);
		gridBagConstraints16.gridy = 9;
		jLabelMenge = new JLabel();
		jLabelMenge.setText("Menge");
		GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		gridBagConstraints15.gridx = 0;
		gridBagConstraints15.anchor = GridBagConstraints.EAST;
		gridBagConstraints15.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints15.gridy = 8;
		jLabelArtikelHersteller = new JLabel();
		jLabelArtikelHersteller.setText("Artikel-Hersteller");
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 0;
		gridBagConstraints14.anchor = GridBagConstraints.EAST;
		gridBagConstraints14.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints14.gridy = 7;
		jLabelArtikelTyp = new JLabel();
		jLabelArtikelTyp.setText("Artikel-Typ");
		GridBagConstraints gridBagConstraints131 = new GridBagConstraints();
		gridBagConstraints131.gridx = 0;
		gridBagConstraints131.anchor = GridBagConstraints.EAST;
		gridBagConstraints131.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints131.gridy = 6;
		jLabelArtikelBezeichnung = new JLabel();
		jLabelArtikelBezeichnung.setText("Artikel-Bezeichnung");
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints12.gridy = 5;
		jLabelKeg = new JLabel();
		jLabelKeg.setText("KE-Nummer");
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.gridx = 0;
		gridBagConstraints111.anchor = GridBagConstraints.EAST;
		gridBagConstraints111.insets = new Insets(20, 0, 20, 5);
		gridBagConstraints111.gridy = 4;
		jLabelAenderungsDatum = new JLabel();
		jLabelAenderungsDatum.setText("Änderungsdatum");
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.gridx = 0;
		gridBagConstraints10.anchor = GridBagConstraints.EAST;
		gridBagConstraints10.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints10.gridy = 3;
		jLabelAenderungsBenutzer = new JLabel();
		jLabelAenderungsBenutzer.setText("Änderungsbenutzer");
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.gridx = 3;
		gridBagConstraints9.weighty = 20.0D;
		gridBagConstraints9.weightx = 1.0D;
		gridBagConstraints9.gridy = 13;
		jLabelUnten = new JLabel();
		jLabelUnten.setText("  ");
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.weighty = 20.0D;
		gridBagConstraints8.weightx = 1.0D;
		gridBagConstraints8.gridy = 0;
		jLabelOben = new JLabel();
		jLabelOben.setText("  ");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 1;
		gridBagConstraints3.weightx = 1.0D;
		gridBagConstraints3.anchor = GridBagConstraints.WEST;
		gridBagConstraints3.insets = new Insets(0, 5, 0, 0);
		gridBagConstraints3.gridheight = 1;
		gridBagConstraints3.gridwidth = 1;
		gridBagConstraints3.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.weighty = 1.0D;
		gridBagConstraints11.weightx = 0.0D;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 1;
		jLabelBestandspackstueck = new JLabel();
		jLabelBestandspackstueck.setText("Bestandspackstueck-ID");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 27;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(jLabelBestandspackstueck, gridBagConstraints11);
		this.add(getJFormattedTextFieldId(), gridBagConstraints3);
		this.add(jLabelOben, gridBagConstraints8);
		this.add(jLabelUnten, gridBagConstraints9);
		this.add(jLabelAenderungsBenutzer, gridBagConstraints10);
		this.add(jLabelAenderungsDatum, gridBagConstraints111);
		this.add(jLabelKeg, gridBagConstraints12);
		this.add(jLabelArtikelBezeichnung, gridBagConstraints131);
		this.add(jLabelArtikelTyp, gridBagConstraints14);
		this.add(jLabelArtikelHersteller, gridBagConstraints15);
		this.add(jLabelMenge, gridBagConstraints16);
		this.add(jLabelMengenEinheit, gridBagConstraints17);
		this.add(jLabel, gridBagConstraints19);
		this.add(jLabelOben2, gridBagConstraints20);
		this.add(getJFormattedTextFieldAenderungsBenutzer(), gridBagConstraints);
		this.add(getJFormattedTextFieldAenderungsdatum(), gridBagConstraints1);
		this.add(getJFormattedTextFieldKeg(), gridBagConstraints2);
		this.add(getJFormattedTextFieldBezeichnung(), gridBagConstraints4);
		this.add(getJFormattedTextFieldTyp(), gridBagConstraints5);
		this.add(getJFormattedTextFieldHersteller(), gridBagConstraints6);
		this.add(getJFormattedTextFieldMenge(), gridBagConstraints7);
		this.add(getJFormattedTextFieldMengenEinheit(), gridBagConstraints18);
		this.add(getJButtonMatchcodeArtikel(), gridBagConstraints21);
		this.add(jLabelAbteilung, gridBagConstraints110);
		this.add(getJTextFieldAbteilung(), gridBagConstraints22);
		this.add(getJButtonMatchCodeAbteilung(), gridBagConstraints31);
	}

	@Override
	public void zeichneDich(ModelKnotenBean bestandspackstueckModelKnotenBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(bestandspackstueckModelKnotenBean!=null){
			if (bestandspackstueckModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTANDSPACKSTUECK){
				setModelBean(bestandspackstueckModelKnotenBean);//merken
				BestandspackstueckBean bestandspackstueckBean = (BestandspackstueckBean) bestandspackstueckModelKnotenBean.getIBean();
				//id anzeigen
//				getJFormattedTextFieldBestandspackstueck().setText(bestandspackstueckBean.getNummer().toString());
				getJFormattedTextFieldId().setText(bestandspackstueckBean.getId().toString());
				getJTextFieldAbteilung().setText(bestandspackstueckBean.getAbteilung().getAbteilungName());
				getJFormattedTextFieldAenderungsBenutzer().setText(bestandspackstueckBean.getAenderungsBenutzer().toString());
				getJFormattedTextFieldKeg().setText(bestandspackstueckBean.getArtikelBean().getKeg_nr().toString());				
				getJFormattedTextFieldBezeichnung().setText(bestandspackstueckBean.getArtikelBean().getBezeichnung());				
				getJFormattedTextFieldTyp().setText(bestandspackstueckBean.getArtikelBean().getTyp());				
//				getJFormattedTextFieldHersteller().setText(bestandspackstueckBean.getArtikelBean().getHersteller().toString());	
				getJFormattedTextFieldHersteller().setText(bestandspackstueckBean.getArtikelBean().getHersteller().getName());	
//				getJFormattedTextFieldMengenEinheit().setText(bestandspackstueckBean.getMengenEinheitBean().getName());				
				getJFormattedTextFieldMenge().setValue(bestandspackstueckBean.getMenge());				
				//kostenstelle anzeigen
//				if (bestandspackstueckBean.getZugriffsrecht()!=null){
//					getJTextFieldZugriffsrecht().setText(bestandspackstueckBean.getZugriffsrecht().getZugriffsrechtName());
//				}else{
//					getJTextFieldZugriffsrecht().setText(null);
//				}
				
				//Fehler anzeigen.
				for(int i=0; i<bestandspackstueckModelKnotenBean.getFehlerList().size();i++){
					Fehler fehler = bestandspackstueckModelKnotenBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				this.repaint();//alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet				this.invalidate();
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


	
	public void setBestandspackstueck(BestandspackstueckBean zugriffsrechtBean) {
		//Das Model wird geändert.
		BestandspackstueckBean BestandspackstueckBean = ((BestandspackstueckBean)modelKnotenBean.getIBean());
//		BestandspackstueckBean.setBestandspackstueck(zugriffsrechtBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
//		if (source==getJFormattedTextFieldId()){
//			leseAusgetJFormattedTextFieldBestandspackstueck();
//		}
		if (source==getJFormattedTextFieldMenge()){
			leseAusgetJFormattedTextFieldMenge();
		}
		getController().ausgewaehlterKnotenIstGeandert();
		
	}

	private void leseAusgetJFormattedTextFieldMenge() {
		BestandspackstueckBean bean = (BestandspackstueckBean)getModelBean().getIBean();
		Integer wert ;
		try{
			if(getJFormattedTextFieldMenge().getValue()!=null){
				wert = ((Number)getJFormattedTextFieldMenge().getValue()).intValue();
				bean.setMenge(wert);
			}
		}catch(NumberFormatException ex){
		}
	}
	
	
	private void leseAusgetJFormattedTextFieldBestandspackstueck() {
		BestandspackstueckBean bean = (BestandspackstueckBean)getModelBean().getIBean();
		Integer wert ;
		try{
			if(getJFormattedTextFieldId().getValue()!=null){
				wert = ((Number)getJFormattedTextFieldId().getValue()).intValue();
//				bean.setNummer(wert);
			}
		}catch(NumberFormatException ex){
		}
	}

	@Override
	public void setStandardFocusPosition() {
		getJFormattedTextFieldId().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJFormattedTextFieldId().setEditable(enabled);
//		getJButtonMatchCodeZugriffsrecht().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldZugriffsrecht	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldId() {
		if (jFormattedTextFieldId == null) {
			jFormattedTextFieldId = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldId.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldId.setEditable(false);
			jFormattedTextFieldId.addPropertyChangeListener("value", this);
//			
//			jFormattedTextFieldBestandspackstueck = new JTextField();
//			jFormattedTextFieldBestandspackstueck.setEditable(false);
		}
		return jFormattedTextFieldId;
	}

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
		Log.log().severe("nicht implementiert");
		return null;
	}

	/**
	 * This method initializes jFormattedTextFieldAenderungsBenutzer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFormattedTextFieldAenderungsBenutzer() {
		if (jFormattedTextFieldAenderungsBenutzer == null) {
			jFormattedTextFieldAenderungsBenutzer = new JTextField();
			jFormattedTextFieldAenderungsBenutzer.setEditable(false);
		}
		return jFormattedTextFieldAenderungsBenutzer;
	}

	/**
	 * This method initializes jFormattedTextFieldAenderungsdatum	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JDateChooser getJFormattedTextFieldAenderungsdatum() {
		if (jFormattedTextFieldAenderungsdatum == null) {
			jFormattedTextFieldAenderungsdatum = new JDateChooser(Konstanten.FORMAT_DATUM_ZEIT,false);
			jFormattedTextFieldAenderungsdatum.setEnabled(false);
			jFormattedTextFieldAenderungsdatum.setPreferredSize(new Dimension(100, 20));
		}
		return jFormattedTextFieldAenderungsdatum;
	}

	/**
	 * This method initializes jFormattedTextFieldKeg	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFormattedTextFieldKeg() {
		if (jFormattedTextFieldKeg == null) {
			jFormattedTextFieldKeg = new JTextField();
			jFormattedTextFieldKeg.setEditable(false);
		}
		return jFormattedTextFieldKeg;
	}

	/**
	 * This method initializes jFormattedTextFieldBezeichnung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFormattedTextFieldBezeichnung() {
		if (jFormattedTextFieldBezeichnung == null) {
			jFormattedTextFieldBezeichnung = new JTextField();
			jFormattedTextFieldBezeichnung.setEditable(false);
		}
		return jFormattedTextFieldBezeichnung;
	}

	/**
	 * This method initializes jFormattedTextFieldTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFormattedTextFieldTyp() {
		if (jFormattedTextFieldTyp == null) {
			jFormattedTextFieldTyp = new JTextField();
			jFormattedTextFieldTyp.setEditable(false);
		}
		return jFormattedTextFieldTyp;
	}

	/**
	 * This method initializes jFormattedTextFieldHersteller	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFormattedTextFieldHersteller() {
		if (jFormattedTextFieldHersteller == null) {
			jFormattedTextFieldHersteller = new JTextField();
			jFormattedTextFieldHersteller.setEditable(false);
		}
		return jFormattedTextFieldHersteller;
	}

	/**
	 * This method initializes jFormattedTextFieldMenge	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldMenge() {
		if (jFormattedTextFieldMenge == null) {
			jFormattedTextFieldMenge = new JFormattedTextField();
			jFormattedTextFieldMenge.setFormatterFactory(new DefaultFormatterFactory(new LagerEmptyNumberFormatter(1, 1000,"")));
//			jFormattedTextFieldMenge = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 1000,""));
			jFormattedTextFieldMenge.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldMenge.addPropertyChangeListener("value", this);
			jFormattedTextFieldMenge.setPreferredSize(new Dimension(80, 20));
			jFormattedTextFieldMenge.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			
		}
		return jFormattedTextFieldMenge;
	}

	/**
	 * This method initializes jFormattedTextFieldMengenEinheit	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFormattedTextFieldMengenEinheit() {
		if (jFormattedTextFieldMengenEinheit == null) {
			jFormattedTextFieldMengenEinheit = new JTextField();
			jFormattedTextFieldMengenEinheit.setEditable(false);
		}
		return jFormattedTextFieldMengenEinheit;
	}

	/**
	 * This method initializes jButtonMatchcodeArtikel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchcodeArtikel() {
		if (jButtonMatchcodeArtikel == null) {
			jButtonMatchcodeArtikel = new JButton();
			jButtonMatchcodeArtikel.setText("...");
			jButtonMatchcodeArtikel.setPreferredSize(new Dimension(43, 20));
			jButtonMatchcodeArtikel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					if (pruefeFehler()){
						try {
							((BestandspackstueckDetailsController)getController()).holeArtikel();
						} catch (SQLException ex) {
							getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,ex.getMessage()));
							ex.printStackTrace();
						} catch (LagerException ex) {
							getJComboBoxFehler().addItem(ex.getFehler());
							ex.printStackTrace();
						}
					}
//				}
			});
		}
		return jButtonMatchcodeArtikel;
	}

	public void setArtikel(ArtikelBean artikelBean) {
		//Das Model wird geändert.
		BestandspackstueckBean bean = ((BestandspackstueckBean)getModelBean().getIBean());
		bean.setArtikelBean(artikelBean);
//		bean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
		
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
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
//								if (pruefeFehler()){
									((BestandspackstueckDetailsController)getController()).holeAbteilung();
//								}
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
		BestandspackstueckBean bestandspackstueckBean = ((BestandspackstueckBean)modelKnotenBean.getIBean());
		bestandspackstueckBean.setAbteilung(abteilungBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

//	/**
//	 * This method initializes jButtonMatchcodeMengeneinheit	
//	 * 	
//	 * @return javax.swing.JButton	
//	 */
//	private JButton getJButtonMatchcodeMengeneinheit() {
//		if (jButtonMatchcodeMengeneinheit == null) {
//			jButtonMatchcodeMengeneinheit = new JButton();
//			jButtonMatchcodeMengeneinheit.setText("...");
//			jButtonMatchcodeMengeneinheit
//					.addActionListener(new java.awt.event.ActionListener() {
//						public void actionPerformed(java.awt.event.ActionEvent e) {
//							try {
//								if (pruefeFehler()){
//									((BestandspackstueckDetailsController)getController()).holeMengeneinheit();
//								}
//							} catch (SQLException e1) {
//								getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
//								e1.printStackTrace();
//							} catch (LagerException e1) {
//								getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
//								e1.printStackTrace();
//							}
//						}
//					});
//		}
//		return jButtonMatchcodeMengeneinheit;
//	}

//	public void setMengeneinheit(MengenEinheitBean mengenEinheitBean) {
//		//Das Model wird geändert.
//		((BestandspackstueckBean)getModelBean().getIBean()).setMengenEinheitBean(mengenEinheitBean);
//		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//		getController().ausgewaehlterKnotenIstGeandert();
//	}

	
}  //  @jve:decl-index=0:visual-constraint="49,1"
