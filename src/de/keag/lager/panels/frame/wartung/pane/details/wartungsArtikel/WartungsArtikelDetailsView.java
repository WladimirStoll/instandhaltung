package de.keag.lager.panels.frame.wartung.pane.details.wartungsArtikel;

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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;

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


public class WartungsArtikelDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelKegNr = null;
	private JButton jButtonMatchCodeArtikel = null;
	private JTextField jTextFieldArtikel = null;
	private JLabel jLabelArtikelBezeichnung = null;
	private JTextField jTextFieldArtikelBezeichnung = null;
	private JLabel jLabelArtikelTyp = null;
	private JLabel jLabelArtikelHersteller = null;
	private JLabel jLabelArtikelMenge = null;
	private JLabel jLabelArtikelMengeneinheit = null;
	private JTextField jTextFieldArtikelTyp = null;
	private JTextField jTextFieldArtikelHersteller = null;
	private JFormattedTextField jTextFieldArtikelMenge = null;
	private JTextField jTextFieldArtikelEinheit = null;
	private JLabel jLabel = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosZugriffsrechtDetailsController 
	 */
	public WartungsArtikelDetailsView(DetailsController BenutzerPosZugriffsrechtDetailsController) {
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
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 0;
		gridBagConstraints14.weighty = 1.0D;
		gridBagConstraints14.weightx = 1.0D;
		gridBagConstraints14.gridy = 0;
		jLabel = new JLabel();
		jLabel.setText(" ");
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints12.gridy = 15;
		gridBagConstraints12.weightx = 1.0;
		gridBagConstraints12.gridx = 2;
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints10.gridy = 14;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.gridx = 2;
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.gridy = 13;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.gridx = 2;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 12;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 2;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 0;
		gridBagConstraints7.anchor = GridBagConstraints.EAST;
		gridBagConstraints7.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints7.gridy = 15;
		jLabelArtikelMengeneinheit = new JLabel();
		jLabelArtikelMengeneinheit.setText("Mengeneinheit");
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.gridx = 0;
		gridBagConstraints6.anchor = GridBagConstraints.EAST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints6.gridy = 14;
		jLabelArtikelMenge = new JLabel();
		jLabelArtikelMenge.setText("Menge");
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.anchor = GridBagConstraints.EAST;
		gridBagConstraints5.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints5.gridy = 13;
		jLabelArtikelHersteller = new JLabel();
		jLabelArtikelHersteller.setText("Artikelhersteller");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints4.gridy = 12;
		jLabelArtikelTyp = new JLabel();
		jLabelArtikelTyp.setText("Artikeltyp");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridy = 11;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.gridx = 2;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 11;
		jLabelArtikelBezeichnung = new JLabel();
		jLabelArtikelBezeichnung.setText("Artikelbezeichnung");
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
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.weighty = 0.0D;
		gridBagConstraints11.weightx = 0.0D;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 10;
		jLabelKegNr = new JLabel();
		jLabelKegNr.setText("KEG Nr.");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 25;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.anchor = GridBagConstraints.SOUTH;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(jLabelKegNr, gridBagConstraints11);
		this.add(getJButtonMatchCodeArtikel(), gridBagConstraints2);
		this.add(getJTextFieldArtikel(), gridBagConstraints3);
		this.add(jLabelArtikelBezeichnung, gridBagConstraints);
		this.add(getJTextFieldArtikelBezeichnung(), gridBagConstraints1);
		this.add(jLabelArtikelTyp, gridBagConstraints4);
		this.add(jLabelArtikelHersteller, gridBagConstraints5);
		this.add(jLabelArtikelMenge, gridBagConstraints6);
		this.add(jLabelArtikelMengeneinheit, gridBagConstraints7);
		this.add(getJTextFieldArtikelTyp(), gridBagConstraints8);
		this.add(getJTextFieldArtikelHersteller(), gridBagConstraints9);
		this.add(getJTextFieldArtikelMenge(), gridBagConstraints10);
		this.add(getJTextFieldArtikelEinheit(), gridBagConstraints12);
		this.add(jLabel, gridBagConstraints14);
	}

	@Override
	public void zeichneDich(ModelKnotenBean WartungsArtikelModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(WartungsArtikelModelBean!=null){
			if (WartungsArtikelModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.WARTUNG_ARTIKEL){
				setModelBean(WartungsArtikelModelBean);//merken
				WartungsArtikelBean wartungsArtikelBean = (WartungsArtikelBean) WartungsArtikelModelBean.getIBean();
				//id anzeigen
//				getJTextFieldId().setText(BenutzerPosBean.getId().toString());
//				getJTextFieldId().setEnabled(false);
				//kostenstelle anzeigen
				if (wartungsArtikelBean.getFk_artikel()!=null){
					getJTextFieldArtikel().setText(wartungsArtikelBean.getFk_artikel().getKeg_nr().toString());
					getJTextFieldArtikelBezeichnung().setText(wartungsArtikelBean.getFk_artikel().getBezeichnung().toString());
					getJTextFieldArtikelTyp().setText(wartungsArtikelBean.getFk_artikel().getTyp().toString());
					getJTextFieldArtikelHersteller().setText(wartungsArtikelBean.getFk_artikel().getHersteller().toString());
					getJTextFieldArtikelEinheit().setText(wartungsArtikelBean.getFk_artikel().getMengenEinheitBean().toString());
					
				}else{
					getJTextFieldArtikel().setText(null);
					getJTextFieldArtikelBezeichnung().setText(null);
					getJTextFieldArtikelTyp().setText(null);
					getJTextFieldArtikelHersteller().setText(null);
					getJTextFieldArtikelEinheit().setText(null);

//					getJTextFieldArtikelMenge().removePropertyChangeListener("value", this);				
//					getJTextFieldArtikelMenge().removeKeyListener(this);				
//					getJTextFieldArtikelMenge().setText(null);
//					getJTextFieldArtikelMenge().addPropertyChangeListener("value", this);				
//					getJTextFieldArtikelMenge().addKeyListener(this);				
				}
				
				getJTextFieldArtikelMenge().removePropertyChangeListener("value", this);				
				getJTextFieldArtikelMenge().removeKeyListener(this);				
				getJTextFieldArtikelMenge().setValue(wartungsArtikelBean.getMenge());
				getJTextFieldArtikelMenge().addPropertyChangeListener("value", this);				
				getJTextFieldArtikelMenge().addKeyListener(this);				
				
				
				WartungBean wartungBean = wartungsArtikelBean.getFk_wartung();
				switch (wartungBean.getStatus()) {
				case OFFEN:
					getJButtonMatchCodeArtikel().setEnabled(true);
					getJTextFieldArtikelMenge().setEditable(true);
					break;
				default:
					getJButtonMatchCodeArtikel().setEnabled(false);
					getJTextFieldArtikelMenge().setEditable(false);
				}
				
				
				//Fehler anzeigen.
				for(int i=0; i<WartungsArtikelModelBean.getFehlerList().size();i++){
					Fehler fehler = WartungsArtikelModelBean.getFehlerList().get(i);
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


	
	public void setArtikel(ArtikelBean bean) {
		//Das Model wird geändert.
		WartungsArtikelBean WartungsArtikelBean = ((WartungsArtikelBean)modelKnotenBean.getIBean());
		WartungsArtikelBean.setFk_artikel(bean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJTextFieldArtikelMenge()){
			leseAusgetJTextFieldArtikelMenge();
		}
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJTextFieldArtikelMenge() {
		WartungsArtikelBean bean = (WartungsArtikelBean)getModelBean().getIBean();
		Integer wert;
		if (((JFormattedTextField) getJTextFieldArtikelMenge())
				.getValue() != null) {
			JFormattedTextField jFormattedTextField = (JFormattedTextField) getJTextFieldArtikelMenge();
			Number number = (Number) jFormattedTextField.getValue();
			wert = number.intValue();
			bean.setMenge(wert);
		}
	}

	@Override
	public void setStandardFocusPosition() {
		getJButtonMatchCodeArtikel().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJButtonMatchCodeArtikel().setEnabled(enabled);
	}

	/**
	 * This method initializes jButtonMatchCodeArtikel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeArtikel() {
		if (jButtonMatchCodeArtikel == null) {
			jButtonMatchCodeArtikel = new JButton();
			jButtonMatchCodeArtikel.setPreferredSize(new Dimension(43, 20));
			jButtonMatchCodeArtikel.setText("...");
			jButtonMatchCodeArtikel
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
//						if (pruefeFehler()){
							((WartungsArtikelDetailsController)getController()).holeArtikel();
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
		return jButtonMatchCodeArtikel;
	}

	/**
	 * This method initializes jTextFieldArtikel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldArtikel() {
		if (jTextFieldArtikel == null) {
			jTextFieldArtikel = new JTextField();
			jTextFieldArtikel.setEditable(false);
		}
		return jTextFieldArtikel;
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
	 * This method initializes jTextFieldArtikelBezeichnung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldArtikelBezeichnung() {
		if (jTextFieldArtikelBezeichnung == null) {
			jTextFieldArtikelBezeichnung = new JTextField();
			jTextFieldArtikelBezeichnung.setEditable(false);
		}
		return jTextFieldArtikelBezeichnung;
	}

	/**
	 * This method initializes jTextFieldArtikelTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldArtikelTyp() {
		if (jTextFieldArtikelTyp == null) {
			jTextFieldArtikelTyp = new JTextField();
			jTextFieldArtikelTyp.setEditable(false);
		}
		return jTextFieldArtikelTyp;
	}

	/**
	 * This method initializes jTextFieldArtikelHersteller	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldArtikelHersteller() {
		if (jTextFieldArtikelHersteller == null) {
			jTextFieldArtikelHersteller = new JTextField();
			jTextFieldArtikelHersteller.setEditable(false);
		}
		return jTextFieldArtikelHersteller;
	}

	/**
	 * This method initializes jTextFieldArtikelMenge	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldArtikelMenge() {
		if (jTextFieldArtikelMenge == null) {
			jTextFieldArtikelMenge = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 100,"Gültige Werte: 1 bis 100"));
			jTextFieldArtikelMenge.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldArtikelMenge.setPreferredSize(new Dimension(20, 20));
			jTextFieldArtikelMenge.addKeyListener(this);
			
		}
		return jTextFieldArtikelMenge;
	}

	/**
	 * This method initializes jTextFieldArtikelEinheit	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldArtikelEinheit() {
		if (jTextFieldArtikelEinheit == null) {
			jTextFieldArtikelEinheit = new JTextField();
			jTextFieldArtikelEinheit.setEditable(false);
		}
		return jTextFieldArtikelEinheit;
	}

	
}  //  @jve:decl-index=0:visual-constraint="49,1"
