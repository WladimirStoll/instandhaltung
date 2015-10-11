package de.keag.lager.panels.frame.baugruppe.pane.details.wartung;

import java.awt.Component;
import java.awt.GridBagLayout;


import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;

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


public class BaugruppeArtikelDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JButton jButtonMatchCodeAbteilung = null;
	private JTextField jTextFieldAbteilung = null;
	private JLabel jLabelBestellnummerKEG = null;
	private JTextField jTextBestellnummerKEG = null;
	private JButton jButtonMatchcodeArtikel = null;
	private JLabel jLabelArtikelTyp = null;
	private JTextField jTextArtikelTyp = null;
	private JLabel jLabelArtikelBezeichnung = null;
	private JTextField jTextArtikelBezeichnung = null;
	private JLabel jLabelArtikelHersteller = null;
	private JTextField jTextArtikelHersteller = null;
	private JLabel jLabelEingebauteMenge = null;
	private JFormattedTextField jTextFieldEingebauteMenge = null;
	private JLabel jLabelLeer = null;
	private JLabel jLabelLeer2 = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosAbteilungDetailsController 
	 */
	public BaugruppeArtikelDetailsView(DetailsController BaugruppeArtikelDetailsController) {
		super();
		setController(BaugruppeArtikelDetailsController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.gridx = 0;
		gridBagConstraints10.weighty = 1.0D;
		gridBagConstraints10.gridy = 16;
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
		gridBagConstraints8.gridy = 15;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 0;
		gridBagConstraints7.anchor = GridBagConstraints.EAST;
		gridBagConstraints7.gridy = 15;
		jLabelEingebauteMenge = new JLabel();
		jLabelEingebauteMenge.setText("Eingebaute Menge");
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints6.gridy = 14;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.anchor = GridBagConstraints.EAST;
		gridBagConstraints5.gridy = 14;
		jLabelArtikelHersteller = new JLabel();
		jLabelArtikelHersteller.setText("ArtikelHersteller");
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints41.gridy = 12;
		gridBagConstraints41.weightx = 2.0D;
		gridBagConstraints41.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.anchor = GridBagConstraints.EAST;
		gridBagConstraints31.weightx = 1.0D;
		gridBagConstraints31.weighty = 0.0D;
		gridBagConstraints31.gridy = 12;
		jLabelArtikelBezeichnung = new JLabel();
		jLabelArtikelBezeichnung.setText("ArtikelBezeichnung");
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints21.gridy = 13;
		gridBagConstraints21.weightx = 2.0D;
		gridBagConstraints21.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.gridy = 13;
		jLabelArtikelTyp = new JLabel();
		jLabelArtikelTyp.setText("ArtikelTyp");
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
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.gridy = 11;
		jLabelBestellnummerKEG = new JLabel();
		jLabelBestellnummerKEG.setText("Bestellnummer KEG");
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
		gridBagConstraints13.gridy = 26;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 0.0D;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(jLabelBestellnummerKEG, gridBagConstraints);
		this.add(getJTextBestellnummerKEG(), gridBagConstraints1);
		this.add(getJButtonMatchCodeArtikel(), gridBagConstraints4);
		this.add(jLabelArtikelTyp, gridBagConstraints12);
		this.add(getJTextArtikelTyp(), gridBagConstraints21);
		this.add(jLabelArtikelBezeichnung, gridBagConstraints31);
		this.add(getJTextArtikelBezeichnung(), gridBagConstraints41);
		this.add(jLabelArtikelHersteller, gridBagConstraints5);
		this.add(getJTextArtikelHersteller(), gridBagConstraints6);
		this.add(jLabelEingebauteMenge, gridBagConstraints7);
		this.add(getJTextFieldEingebauteMenge(), gridBagConstraints8);
		this.add(jLabelLeer, gridBagConstraints9);
		this.add(jLabelLeer2, gridBagConstraints10);
	}

	@Override
	public void zeichneDich(ModelKnotenBean baugruppeArtikelModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(baugruppeArtikelModelBean!=null){
			if (baugruppeArtikelModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BAUGRUPPE_ARTIKEL){
				setModelBean(baugruppeArtikelModelBean);//merken
				BaugruppeArtikelBean baugruppeArtikelBean = (BaugruppeArtikelBean) baugruppeArtikelModelBean.getIBean();
				ArtikelBean artikelBean = baugruppeArtikelBean.getArtikel();
				//id anzeigen
				
				getJTextBestellnummerKEG().setText(artikelBean.getKeg_nr().toString());
				
				getJTextArtikelBezeichnung().setText(artikelBean.getBezeichnung().toString());
				
				getJTextArtikelTyp().setText(artikelBean.getTyp());
				
				getJTextArtikelHersteller().setText(artikelBean.getHersteller().getName());
				
				//Eingebaute Menge
				Log.log().finest("BaugruppeArtikelDeatialsView:ZeichneDich:Text:"+ getJTextFieldEingebauteMenge().getText()+ "=" +baugruppeArtikelBean.getEingebauteMenge().toString());
				Log.log().finest("BaugruppeArtikelDeatialsView:ZeichneDich:Value alt"+ getJTextFieldEingebauteMenge().getValue());
				
				getJTextFieldEingebauteMenge().removePropertyChangeListener("value", this);
				getJTextFieldEingebauteMenge().setValue(baugruppeArtikelBean.getEingebauteMenge());
				getJTextFieldEingebauteMenge().addPropertyChangeListener("value", this);
//				getJTextFieldEingebauteMenge().setValue(baugruppeArtikelBean.getEingebauteMenge());
//				jTextFieldEingebauteMenge.addPropertyChangeListener("value", this);				
				Log.log().finest("BaugruppeArtikelDeatialsView:ZeichneDich:Text:"+ getJTextFieldEingebauteMenge().getText()+ "=" +baugruppeArtikelBean.getEingebauteMenge().toString());
				Log.log().finest("BaugruppeArtikelDeatialsView:ZeichneDich:Value neu"+ getJTextFieldEingebauteMenge().getValue());
				
				setEnabled(true);
				
				//kostenstelle anzeigen
//				if (benutzerArtikelBean.getArtikel()!=null){
//					getJTextFieldArtikel().setText(benutzerArtikelBean.getArtikel().getArtikelName());
//				}else{
//					getJTextFieldArtikel().setText(null);
//				}
				
				//Fehler anzeigen.
				for(int i=0; i<baugruppeArtikelModelBean.getFehlerList().size();i++){
					Fehler fehler = baugruppeArtikelModelBean.getFehlerList().get(i);
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


	
	public void setArtikel(ArtikelBean abteilungBean) {
		//Das Model wird geändert.
		BaugruppeArtikelBean BaugruppeArtikelBean = ((BaugruppeArtikelBean)modelKnotenBean.getIBean());
		BaugruppeArtikelBean.setArtikel(abteilungBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		Log.log().finest("BaugruppeArtikelDetailsView: propertyChange prüfeFehler vor");
		if (pruefeFehler()){
			if (source==getJTextFieldEingebauteMenge()){
				Log.log().finest("BaugruppeArtikelDetailsView: propertyChange leseAusgetJTextFieldEingebauteMenge vor");
				leseAusgetJTextFieldEingebauteMenge();
				Log.log().finest("BaugruppeArtikelDetailsView: propertyChange leseAusgetJTextFieldEingebauteMenge nach");
			}
		}
		Log.log().finest("BaugruppeArtikelDetailsView: propertyChange prüfeFehler vor");
		
//		if (source==getJTextLiefertermin()){
//				leseAusgetJTextLiefertermin();
//		}
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
		System.out.println("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJTextFieldEingebauteMenge() {
		BaugruppeArtikelBean baugruppeArtikelBean = (BaugruppeArtikelBean)getModelBean().getIBean();
		Integer wert = 0 ;
		try{
			Log.log().finest("BaugruppeArtikelDetailsView:leseAusgetJTextFieldEingebauteMenge:"+ getJTextFieldEingebauteMenge().getValue() + "="+ baugruppeArtikelBean.getEingebauteMenge().toString());
			if(getJTextFieldEingebauteMenge().getValue()!=null){
				wert = ((Number)getJTextFieldEingebauteMenge().getValue()).intValue();
				baugruppeArtikelBean.setEingebauteMenge(wert);
			}
//			boolean stopMe = (baugruppeArtikelBean.getEingebauteMenge() == 1);
			Log.log().finest("BaugruppeArtikelDetailsView:leseAusgetJTextFieldEingebauteMenge:"+ getJTextFieldEingebauteMenge().getValue() + "="+ baugruppeArtikelBean.getEingebauteMenge().toString());
		}catch(NumberFormatException ex){
		}
	}
	
	@Override
	public void setStandardFocusPosition() {
		Component focusComponent = null;
		for(int i = 0; i < this.getComponentCount() && focusComponent==null; i++){
			focusComponent = this.getComponent(i);
			if (!focusComponent.isFocusOwner()){
				focusComponent=null;
			}
		}
		if (focusComponent==null){
			getJButtonMatchCodeArtikel().requestFocus();
			Log.log().finest("Neuer Focus:"+getJButtonMatchCodeArtikel().toString());
		}
	}

	/**
	 * enabled = true: Die Maske darf bearbeitet werden.
	 * enabled = false: Die Maske darf NICHT bearbeitet werden.
	 */
	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJTextFieldEingebauteMenge().setEditable(enabled);
		getJButtonMatchCodeArtikel().setEnabled(enabled);
		
		getJTextBestellnummerKEG().setEditable(false);
		getJTextArtikelBezeichnung().setEditable(false);
		getJTextArtikelTyp().setEditable(false);
		getJTextArtikelHersteller().setEditable(false);
		
	}

//	/**
//	 * This method initializes jButtonMatchCodeArtikel	
//	 * 	
//	 * @return javax.swing.JButton	
//	 */
//	private JButton getJButtonMatchCodeArtikel() {
//		return null;
//		if (jButtonMatchCodeArtikel == null) {
//			jButtonMatchCodeArtikel = new JButton();
//			jButtonMatchCodeArtikel.setPreferredSize(new Dimension(30, 20));
//			jButtonMatchCodeArtikel.setText("...");
//			jButtonMatchCodeArtikel
//			.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					try {
//						if (pruefeFehler()){
//							((BaugruppeArtikelDetailsController)getController()).holeArtikel();
//						}
//					} catch (SQLException e1) {
//						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
//						e1.printStackTrace();
//					} catch (LagerException e1) {
//						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
//						e1.printStackTrace();
//					}
//				}
//			});
//			
//		}
//		return jButtonMatchCodeArtikel;
//	}

//	/**
//	 * This method initializes jTextFieldArtikel	
//	 * 	
//	 * @return javax.swing.JTextField	
//	 */
//	private JTextField getJTextFieldArtikel() {
//		return null;
////		if (jTextFieldArtikel == null) {
////			jTextFieldArtikel = new JTextField();
////			jTextFieldArtikel.setEditable(false);
////		}
////		return jTextFieldArtikel;
//	}

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
			
			
//			if (getJTextBestellnummerKEG().isFocusOwner()){
//				Log.log().severe("Test: owner");
//			}else{
//				Log.log().severe("Test: kein owner");
//			}
//			
//			if (getJTextFieldEingebauteMenge().isFocusOwner()){
//				Log.log().severe("Test: owner");
//			}else{
//				Log.log().severe("Test: kein owner");
//			}
//			
			
			
			if (getJTextBestellnummerKEG().getText().isEmpty()){
				errors.add(new Fehler(56));
			}
			Log.log().finest("BaugruppeArtikelDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getText() vor :"+getJTextFieldEingebauteMenge().getText());
			Log.log().finest("BaugruppeArtikelDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getValue() vor :"+getJTextFieldEingebauteMenge().getValue());
		
			if (!getJTextFieldEingebauteMenge().getInputVerifier().shouldYieldFocus(
					getJTextFieldEingebauteMenge())) {
				errors.add(new Fehler(57));
			} else {
				leseAusgetJTextFieldEingebauteMenge();
				
			}
			Log.log().finest("BaugruppeArtikelDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getText() nach:"+getJTextFieldEingebauteMenge().getText());
			Log.log().finest("BaugruppeArtikelDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getValue() nach :"+getJTextFieldEingebauteMenge().getValue());
		} catch (Exception e) {
			//Alle Fehler abfangen
			errors.add(new Fehler(36,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}

	/**
	 * This method initializes jTextBestellnummerKEG	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextBestellnummerKEG() {
		if (jTextBestellnummerKEG == null) {
			jTextBestellnummerKEG = new JTextField();
			jTextBestellnummerKEG.setPreferredSize(new Dimension(80, 20));
			jTextBestellnummerKEG.setEditable(false);
		}
		return jTextBestellnummerKEG;
	}

	/**
	 * This method initializes jButtonMatchcodeArtikel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeArtikel() {
		if (jButtonMatchcodeArtikel == null) {
			jButtonMatchcodeArtikel = new JButton();
			jButtonMatchcodeArtikel.setText("...");
			jButtonMatchcodeArtikel.setPreferredSize(new Dimension(43, 20));
			jButtonMatchcodeArtikel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
//						if (pruefeFehler()){
							((BaugruppeArtikelDetailsController)getController()).holeArtikel();
//						}
					} catch (SQLException ex) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,ex.getMessage()));
						ex.printStackTrace();
					} catch (KostenstelleBeanDbLagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					} catch (MengenEinheitBeanDbLagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					} catch (HerstellerLieferantLagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					} catch (LagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					}
				}
			});
			
		}
		return jButtonMatchcodeArtikel;
	}

	/**
	 * This method initializes jTextArtikelTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextArtikelTyp() {
		if (jTextArtikelTyp == null) {
			jTextArtikelTyp = new JTextField();
			jTextArtikelTyp.setPreferredSize(new Dimension(40, 20));
			jTextArtikelTyp.setEditable(false);
		}
		return jTextArtikelTyp;
	}

	/**
	 * This method initializes jTextArtikelBezeichnung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextArtikelBezeichnung() {
		if (jTextArtikelBezeichnung == null) {
			jTextArtikelBezeichnung = new JTextField();
			jTextArtikelBezeichnung.setPreferredSize(new Dimension(80, 20));
			jTextArtikelBezeichnung.setEditable(false);
		}
		return jTextArtikelBezeichnung;
	}

	/**
	 * This method initializes jTextArtikelHersteller	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextArtikelHersteller() {
		if (jTextArtikelHersteller == null) {
			jTextArtikelHersteller = new JTextField();
			jTextArtikelHersteller.setPreferredSize(new Dimension(80, 20));
			jTextArtikelHersteller.setEditable(false);
		}
		return jTextArtikelHersteller;
	}

	/**
	 * This method initializes jTextFieldEingebauteMenge	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldEingebauteMenge() {
		if (jTextFieldEingebauteMenge == null) {
			jTextFieldEingebauteMenge = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 100,"Gültige Werte: 1 bis 100"));
			jTextFieldEingebauteMenge.setInputVerifier(LagerFormate.getInputVerifier());
//			jTextFieldEingebauteMenge.addPropertyChangeListener("value", this);
			jTextFieldEingebauteMenge.setPreferredSize(new Dimension(20, 20));
//			jTextFieldEingebauteMenge.setEditable(false);
			jTextFieldEingebauteMenge.addKeyListener(this);
		}
		return jTextFieldEingebauteMenge;
	}

	
}  //  @jve:decl-index=0:visual-constraint="49,1"
