package de.keag.lager.panels.frame.artikel.pane.details.unterArtikel;

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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;

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
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import java.awt.Color;


public class UnterArtikelDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
//	private JButton jButtonMatchCodeArtikel = null;
//	private JTextField jTextFieldArtikel = null;
	private JLabel jLabelBaugruppe = null;
	private JTextField jTextBaugruppe = null;
	private JButton jButtonMatchcodeArtikel = null;
	private JLabel jLabelUebergeordneteArtikel = null;
	private JTextField jTextUebergeordneteArtikeln = null;
	private JLabel jLabelEingebauteMenge = null;
	private JFormattedTextField jTextFieldEingebauteMenge = null;
	private JLabel jLabelLeer = null;
	private JLabel jLabelLeer2 = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosAbteilungDetailsController 
	 */
	public UnterArtikelDetailsView(DetailsController ArtikelBaugruppeDetailsController) {
		super();
		setController(ArtikelBaugruppeDetailsController);
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
		gridBagConstraints7.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints7.gridy = 15;
		jLabelEingebauteMenge = new JLabel();
		jLabelEingebauteMenge.setText("Menge");
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints41.gridy = 1;
		gridBagConstraints41.weightx = 2.0D;
		gridBagConstraints41.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.anchor = GridBagConstraints.EAST;
		gridBagConstraints31.weightx = 1.0D;
		gridBagConstraints31.weighty = 0.0D;
		gridBagConstraints31.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints31.gridy = 1;
		jLabelUebergeordneteArtikel = new JLabel();
		jLabelUebergeordneteArtikel.setText("Übergeordnete Artikel");
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
		jLabelBaugruppe = new JLabel();
		jLabelBaugruppe.setText("Artikel");
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
		this.add(jLabelBaugruppe, gridBagConstraints);
		this.add(getJTextUnterArtikelBezeichnung(), gridBagConstraints1);
		this.add(getJButtonMatchcodeArtikel(), gridBagConstraints4);
		this.add(jLabelUebergeordneteArtikel, gridBagConstraints31);
		this.add(getJTextUebergeordneteArtikeln(), gridBagConstraints41);
		this.add(jLabelEingebauteMenge, gridBagConstraints7);
		this.add(getJTextFieldEingebauteMenge(), gridBagConstraints8);
		this.add(jLabelLeer, gridBagConstraints9);
		this.add(jLabelLeer2, gridBagConstraints10);
	}

	@Override
	public void zeichneDich(ModelKnotenBean UnterArtikelModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(UnterArtikelModelBean!=null){
			if (UnterArtikelModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.UNTER_ARTIKEL){
				setModelBean(UnterArtikelModelBean);//merken
				UnterArtikelBean unterArtikelBean = (UnterArtikelBean) UnterArtikelModelBean.getIBean();
//				ArtikelBean artikelBean = ArtikelBaugruppeBean.getArtikel();
//				BaugruppeBean baugruppeBean = unterArtikelBean.getBaugruppe();
				//id anzeigen
				
				getJTextUnterArtikelBezeichnung().setText(unterArtikelBean.getKindArtikelBean().getBezeichnung());
				
//				getJTextUebergeordneteBaugruppen().setText(baugruppeBean.getVaterBaugruppeNamenListe());
				
				//Eingebaute Menge
//				Log.log().finest("ArtikelBaugruppeDeatialsView:ZeichneDich:Text:"+ getJTextFieldEingebauteMenge().getText()+ "=" +unterArtikelBean.getEingebauteMenge().toString());
//				Log.log().finest("ArtikelBaugruppeDeatialsView:ZeichneDich:Value alt"+ getJTextFieldEingebauteMenge().getValue());
				
				getJTextFieldEingebauteMenge().removePropertyChangeListener("value", this);
				getJTextFieldEingebauteMenge().setValue(unterArtikelBean.getAnzahl());
				getJTextFieldEingebauteMenge().addPropertyChangeListener("value", this);
//				getJTextFieldEingebauteMenge().setValue(ArtikelBaugruppeBean.getEingebauteMenge());
//				jTextFieldEingebauteMenge.addPropertyChangeListener("value", this);				
//				Log.log().finest("ArtikelBaugruppeDeatialsView:ZeichneDich:Text:"+ getJTextFieldEingebauteMenge().getText()+ "=" +unterArtikelBean.getEingebauteMenge().toString());
//				Log.log().finest("ArtikelBaugruppeDeatialsView:ZeichneDich:Value neu"+ getJTextFieldEingebauteMenge().getValue());
				
				setEnabled(true);
				
				//kostenstelle anzeigen
//				if (benutzerArtikelBean.getArtikel()!=null){
//					getJTextFieldArtikel().setText(benutzerArtikelBean.getArtikel().getArtikelName());
//				}else{
//					getJTextFieldArtikel().setText(null);
//				}
				
				//Fehler anzeigen.
				for(int i=0; i<UnterArtikelModelBean.getFehlerList().size();i++){
					Fehler fehler = UnterArtikelModelBean.getFehlerList().get(i);
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


	
	public void setArtikel(ArtikelBean artikelBean) {
		//Das Model wird geändert.
		UnterArtikelBean unterArtikelBean = ((UnterArtikelBean)modelKnotenBean.getIBean());
		unterArtikelBean.setKindArtikelBean(artikelBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


//	public void setBaugruppe(BaugruppeBean baugruppeBean) {
//
//		BaugruppeArtikelBean ArtikelBaugruppeBean = ((BaugruppeArtikelBean)modelKnotenBean.getIBean());
//		ArtikelBaugruppeBean.setBaugruppe(baugruppeBean);
//		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//		getController().ausgewaehlterKnotenIstGeandert();
//		
//	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		Log.log().finest("ArtikelBaugruppeDetailsView: propertyChange prüfeFehler vor");
		if (source==getJTextFieldEingebauteMenge()){
			Log.log().finest("ArtikelBaugruppeDetailsView: propertyChange leseAusgetJTextFieldEingebauteMenge vor");
			leseAusgetJTextFieldEingebauteMenge();
			Log.log().finest("ArtikelBaugruppeDetailsView: propertyChange leseAusgetJTextFieldEingebauteMenge nach");
		}
//		if (pruefeFehler()){
//		}
//		Log.log().finest("ArtikelBaugruppeDetailsView: propertyChange prüfeFehler vor");
		
//		if (source==getJTextLiefertermin()){
//				leseAusgetJTextLiefertermin();
//		}
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
//		System.out.println("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJTextFieldEingebauteMenge() {
		UnterArtikelBean unterArtikelBean = (UnterArtikelBean)getModelBean().getIBean();
		Integer wert = 0 ;
		try{
			Log.log().finest("ArtikelBaugruppeDetailsView:leseAusgetJTextFieldEingebauteMenge:"+ getJTextFieldEingebauteMenge().getValue() + "="+ unterArtikelBean.getAnzahl().toString());
			if(getJTextFieldEingebauteMenge().getValue()!=null){
				wert = ((Number)getJTextFieldEingebauteMenge().getValue()).intValue();
				unterArtikelBean.setAnzahl(wert);
			}
//			boolean stopMe = (ArtikelBaugruppeBean.getEingebauteMenge() == 1);
			Log.log().finest("ArtikelBaugruppeDetailsView:leseAusgetJTextFieldEingebauteMenge:"+ getJTextFieldEingebauteMenge().getValue() + "="+ unterArtikelBean.getAnzahl().toString());
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
			getJButtonMatchcodeArtikel().requestFocus();
			Log.log().finest("Neuer Focus:"+getJButtonMatchcodeArtikel().toString());
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
		getJButtonMatchcodeArtikel().setEnabled(enabled);
		
		getJTextUnterArtikelBezeichnung().setEditable(false);
		getJTextUebergeordneteArtikeln().setEditable(false);
		
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
//							((ArtikelBaugruppeDetailsController)getController()).holeArtikel();
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
			
			
			if (getJTextUnterArtikelBezeichnung().getText().isEmpty()){
				errors.add(new Fehler(56));
			}
			Log.log().finest("ArtikelBaugruppeDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getText() vor :"+getJTextFieldEingebauteMenge().getText());
			Log.log().finest("ArtikelBaugruppeDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getValue() vor :"+getJTextFieldEingebauteMenge().getValue());
		
			if (!getJTextFieldEingebauteMenge().getInputVerifier().shouldYieldFocus(
					getJTextFieldEingebauteMenge())) {
				errors.add(new Fehler(57));
			} else {
				leseAusgetJTextFieldEingebauteMenge();
				
			}
			Log.log().finest("ArtikelBaugruppeDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getText() nach:"+getJTextFieldEingebauteMenge().getText());
			Log.log().finest("ArtikelBaugruppeDetailsView:pruefeDich:getJTextFieldEingebauteMenge().getValue() nach :"+getJTextFieldEingebauteMenge().getValue());
		} catch (Exception e) {
			//Alle Fehler abfangen
			errors.add(new Fehler(36,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}

	/**
	 * This method initializes jTextBaugruppe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextUnterArtikelBezeichnung() {
		if (jTextBaugruppe == null) {
			jTextBaugruppe = new JTextField();
			jTextBaugruppe.setPreferredSize(new Dimension(80, 20));
			jTextBaugruppe.setEditable(false);
		}
		return jTextBaugruppe;
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
					try {
//						if (pruefeFehler()){
							((UnterArtikelDetailsController)getController()).holeArtikel();
//						}
					} catch (SQLException e1) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
						e1.printStackTrace();
					} catch (LagerException e1) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
						e1.printStackTrace();
					}
//					try {
//						if (pruefeFehler()){
//							((ArtikelBaugruppeDetailsController)getController()).holeArtikel();
//						}
//					} catch (SQLException ex) {
//						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,ex.getMessage()));
//						ex.printStackTrace();
//					} catch (KostenstelleBeanDbLagerException ex) {
//						getJComboBoxFehler().addItem(ex.getFehler());
//						ex.printStackTrace();
//					} catch (MengenEinheitBeanDbLagerException ex) {
//						getJComboBoxFehler().addItem(ex.getFehler());
//						ex.printStackTrace();
//					} catch (HerstellerLieferantLagerException ex) {
//						getJComboBoxFehler().addItem(ex.getFehler());
//						ex.printStackTrace();
//					} catch (LagerException ex) {
//						getJComboBoxFehler().addItem(ex.getFehler());
//						ex.printStackTrace();
//					}
				}
			});
			
		}
		return jButtonMatchcodeArtikel;
	}

	/**
	 * This method initializes jTextUebergeordneteBaugruppen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextUebergeordneteArtikeln() {
		if (jTextUebergeordneteArtikeln == null) {
			jTextUebergeordneteArtikeln = new JTextField();
			jTextUebergeordneteArtikeln.setPreferredSize(new Dimension(80, 20));
			jTextUebergeordneteArtikeln.setEditable(false);
		}
		return jTextUebergeordneteArtikeln;
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
