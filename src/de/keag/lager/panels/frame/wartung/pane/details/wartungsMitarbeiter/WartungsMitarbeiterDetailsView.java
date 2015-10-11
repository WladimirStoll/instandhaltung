/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
package de.keag.lager.panels.frame.wartung.pane.details.wartungsMitarbeiter;

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
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
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


public class WartungsMitarbeiterDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelMitarbeiterIntern = null;
	private JButton jButtonMatchCodeMitarbeiterIntern = null;
	private JTextField jTextFieldMitarbeiterIntern = null;
	private JLabel jLabelFirmaExtern = null;
	private JTextField jTextFieldFirmaExtern = null;
	private JButton jButtonMatchCodeFirmaExtern = null;
	private JLabel jLabelLeerObenLinks = null;
	private JLabel jLabelZeitAufwand = null;
	private JFormattedTextField jTextFieldZeitAufwandInStunden = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosAbteilungDetailsController 
	 */
	public WartungsMitarbeiterDetailsView(DetailsController BenutzerPosAbteilungDetailsController) {
		super();
		setController(BenutzerPosAbteilungDetailsController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridy = 3;
		gridBagConstraints31.weightx = 1.0;
		gridBagConstraints31.gridx = 2;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 0;
		gridBagConstraints21.anchor = GridBagConstraints.EAST;
		gridBagConstraints21.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints21.gridy = 3;
		jLabelZeitAufwand = new JLabel();
		jLabelZeitAufwand.setText("Zeitaufwand(in Stunden)");
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.weightx = 1.0D;
		gridBagConstraints12.weighty = 1.0D;
		gridBagConstraints12.gridy = 2;
		jLabelLeerObenLinks = new JLabel();
		jLabelLeerObenLinks.setText(" ");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 3;
		gridBagConstraints4.anchor = GridBagConstraints.WEST;
		gridBagConstraints4.gridy = 11;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridy = 11;
		gridBagConstraints1.weightx = 1.0D;
		gridBagConstraints1.gridx = 2;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 0.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 11;
		jLabelFirmaExtern = new JLabel();
		jLabelFirmaExtern.setText("Firma(extern)");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 12;
		gridBagConstraints3.weightx = 0.0D;
		gridBagConstraints3.gridx = 2;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 3;
		gridBagConstraints2.weightx = 1.0D;
		gridBagConstraints2.anchor = GridBagConstraints.WEST;
		gridBagConstraints2.gridy = 12;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.weighty = 0.0D;
		gridBagConstraints11.weightx = 0.0D;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridheight = 1;
		gridBagConstraints11.gridwidth = 1;
		gridBagConstraints11.gridy = 12;
		jLabelMitarbeiterIntern = new JLabel();
		jLabelMitarbeiterIntern.setText("Mitarbeiter(intern)");
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
		this.add(jLabelMitarbeiterIntern, gridBagConstraints11);
		this.add(getJButtonMatchCodeMitarbeiterIntern(), gridBagConstraints2);
		this.add(getJTextFieldMitarbeiterIntern(), gridBagConstraints3);
		this.add(jLabelFirmaExtern, gridBagConstraints);
		this.add(getJTextFieldFirmaExtern(), gridBagConstraints1);
		this.add(getJButtonMatchCodeFirmaExtern(), gridBagConstraints4);
		this.add(jLabelLeerObenLinks, gridBagConstraints12);
		this.add(jLabelZeitAufwand, gridBagConstraints21);
		this.add(getJTextFieldZeitAufwandInStunden(), gridBagConstraints31);
	}

	@Override
	public void zeichneDich(ModelKnotenBean WartungsMitarbeiterModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(WartungsMitarbeiterModelBean!=null){
			if (WartungsMitarbeiterModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.WARTUNG_MITARBEITER){
				setModelBean(WartungsMitarbeiterModelBean);//merken
				WartungsMitarbeiterBean wartungsMitarbeiterBean = (WartungsMitarbeiterBean) WartungsMitarbeiterModelBean.getIBean();
				//id anzeigen
//				getJTextFieldId().setText(BenutzerPosBean.getId().toString());
//				getJTextFieldId().setEnabled(false);
				//kostenstelle anzeigen
				if (wartungsMitarbeiterBean.getFk_benutzer()!=null){
					getJTextFieldMitarbeiterIntern().setText(wartungsMitarbeiterBean.getFk_benutzer().toString());
				}else{
					getJTextFieldMitarbeiterIntern().setText(null);
				}
				
				if (wartungsMitarbeiterBean.getFk_herstellerlieferant()!=null){
					getJTextFieldFirmaExtern().setText(wartungsMitarbeiterBean.getFk_herstellerlieferant().toString());
				}else{
					getJTextFieldFirmaExtern().setText(null);
				}
				
				getJTextFieldZeitAufwandInStunden().removePropertyChangeListener("value", this);				
				getJTextFieldZeitAufwandInStunden().removeKeyListener(this);				
				getJTextFieldZeitAufwandInStunden().setValue(wartungsMitarbeiterBean.getStunden().doubleValue());
				getJTextFieldZeitAufwandInStunden().addKeyListener(this);
				getJTextFieldZeitAufwandInStunden().addPropertyChangeListener("value", this);	
				
				WartungBean wartungBean = wartungsMitarbeiterBean.getFk_wartung();
				switch (wartungBean.getStatus()) {
				case OFFEN:
					getJButtonMatchCodeFirmaExtern().setEnabled(true);
					getJButtonMatchCodeMitarbeiterIntern().setEnabled(true);
					getJTextFieldZeitAufwandInStunden().setEditable(true);
					break;
				default:
					getJButtonMatchCodeFirmaExtern().setEnabled(false);
					getJButtonMatchCodeMitarbeiterIntern().setEnabled(false);
					getJTextFieldZeitAufwandInStunden().setEditable(false);
				}				
				
				//Fehler anzeigen.
				for(int i=0; i<WartungsMitarbeiterModelBean.getFehlerList().size();i++){
					Fehler fehler = WartungsMitarbeiterModelBean.getFehlerList().get(i);
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


	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJTextFieldZeitAufwandInStunden()){
			leseAusgetJTextFieldZeitAufwandInStunden();
		}
//		if (source==getJTextLiefertermin()){
//				leseAusgetJTextLiefertermin();
//		}
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJTextFieldZeitAufwandInStunden() {
		WartungsMitarbeiterBean bean = (WartungsMitarbeiterBean)getModelBean().getIBean();
		Double wert;
		if (((JFormattedTextField) getJTextFieldZeitAufwandInStunden())
				.getValue() != null) {
			JFormattedTextField jFormattedTextField = (JFormattedTextField) getJTextFieldZeitAufwandInStunden();
			Number number = (Number) jFormattedTextField.getValue();
			wert = number.doubleValue();
			bean.setStunden(wert);
		}
	}

	@Override
	public void setStandardFocusPosition() {
		getJButtonMatchCodeMitarbeiterIntern().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJButtonMatchCodeMitarbeiterIntern().setEnabled(enabled);
	}

	/**
	 * This method initializes jButtonMatchCodeMitarbeiterIntern	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeMitarbeiterIntern() {
		if (jButtonMatchCodeMitarbeiterIntern == null) {
			jButtonMatchCodeMitarbeiterIntern = new JButton();
			jButtonMatchCodeMitarbeiterIntern.setPreferredSize(new Dimension(43, 20));
			jButtonMatchCodeMitarbeiterIntern.setText("...");
			jButtonMatchCodeMitarbeiterIntern
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
//						if (pruefeFehler()){
							((WartungsMitarbeiterDetailsController)getController()).holeBenutzer();
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
		return jButtonMatchCodeMitarbeiterIntern;
	}

	/**
	 * This method initializes jTextFieldMitarbeiterIntern	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldMitarbeiterIntern() {
		if (jTextFieldMitarbeiterIntern == null) {
			jTextFieldMitarbeiterIntern = new JTextField();
			jTextFieldMitarbeiterIntern.setEditable(false);
		}
		return jTextFieldMitarbeiterIntern;
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
	 * This method initializes jTextFieldFirmaExtern	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldFirmaExtern() {
		if (jTextFieldFirmaExtern == null) {
			jTextFieldFirmaExtern = new JTextField();
			jTextFieldFirmaExtern.setEditable(false);
		}
		return jTextFieldFirmaExtern;
	}

	/**
	 * This method initializes jButtonMatchCodeFirmaExtern	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeFirmaExtern() {
		if (jButtonMatchCodeFirmaExtern == null) {
			jButtonMatchCodeFirmaExtern = new JButton();
			jButtonMatchCodeFirmaExtern.setPreferredSize(new Dimension(43, 20));
			jButtonMatchCodeFirmaExtern.setText("...");
			jButtonMatchCodeFirmaExtern
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
//						if (pruefeFehler()){
							((WartungsMitarbeiterDetailsController)getController()).holeLieferanten();
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
		return jButtonMatchCodeFirmaExtern;
	}

	/**
	 * This method initializes jTextFieldZeitAufwandInStunden	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldZeitAufwandInStunden() {
		if (jTextFieldZeitAufwandInStunden == null) {
			jTextFieldZeitAufwandInStunden = new JFormattedTextField(
					new LagerEmptyNumberFormatter(0, 1000,
							"Gültige Werte: 1 bis 1000"));
			jTextFieldZeitAufwandInStunden.setInputVerifier(LagerFormate.getInputVerifier());
			// jTextFieldEingebauteMenge.addPropertyChangeListener("value",
			// this);
			jTextFieldZeitAufwandInStunden.setPreferredSize(new Dimension(20, 20));
			// jTextFieldEingebauteMenge.setEditable(false);
			jTextFieldZeitAufwandInStunden.addKeyListener(this);
			
		}
		return jTextFieldZeitAufwandInStunden;
	}

	public void setHerstellerLieferant(LhBean lhBean) {
		WartungsMitarbeiterBean WartungsMitarbeiterBean = ((WartungsMitarbeiterBean)modelKnotenBean.getIBean());
		WartungsMitarbeiterBean.setFk_herstellerlieferant(lhBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		
		//Wenn Hersteller ausgewählt ist, dann darf es keinen Mitarbeiter geben (ENTWEDER / ODER und nicht umgekehrt)
		WartungsMitarbeiterBean.setFk_benutzer(new BenutzerBean());
		
		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setBenutzer(BenutzerBean bean) {
		//Das Model wird geändert.
		WartungsMitarbeiterBean WartungsMitarbeiterBean = ((WartungsMitarbeiterBean)modelKnotenBean.getIBean());
		WartungsMitarbeiterBean.setFk_benutzer(bean);
		
		//Wenn Hersteller ausgewählt ist, dann darf es keinen Mitarbeiter geben (ENTWEDER / ODER und nicht umgekehrt)
		WartungsMitarbeiterBean.setFk_herstellerlieferant(new LhBean());
		
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	
	
}  //  @jve:decl-index=0:visual-constraint="49,1"
