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
package de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung;

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
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;

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


public class BenutzerAbteilungDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelAbteilung = null;
	private JButton jButtonMatchCodeAbteilung = null;
	private JTextField jTextFieldAbteilung = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosAbteilungDetailsController 
	 */
	public BenutzerAbteilungDetailsView(DetailsController BenutzerPosAbteilungDetailsController) {
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
		gridBagConstraints11.weighty = 100.0D;
		gridBagConstraints11.weightx = 1.0D;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 10;
		jLabelAbteilung = new JLabel();
		jLabelAbteilung.setText("Abteilung");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 25;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(jLabelAbteilung, gridBagConstraints11);
		this.add(getJButtonMatchCodeAbteilung(), gridBagConstraints2);
		this.add(getJTextFieldAbteilung(), gridBagConstraints3);
	}

	@Override
	public void zeichneDich(ModelKnotenBean BenutzerAbteilungModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(BenutzerAbteilungModelBean!=null){
			if (BenutzerAbteilungModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ABTEILUNG){
				setModelBean(BenutzerAbteilungModelBean);//merken
				BenutzerAbteilungBean benutzerAbteilungBean = (BenutzerAbteilungBean) BenutzerAbteilungModelBean.getIBean();
				//id anzeigen
//				getJTextFieldId().setText(BenutzerPosBean.getId().toString());
//				getJTextFieldId().setEnabled(false);
				//kostenstelle anzeigen
				if (benutzerAbteilungBean.getAbteilung()!=null){
					getJTextFieldAbteilung().setText(benutzerAbteilungBean.getAbteilung().getAbteilungName());
				}else{
					getJTextFieldAbteilung().setText(null);
				}
				
				//Fehler anzeigen.
				for(int i=0; i<BenutzerAbteilungModelBean.getFehlerList().size();i++){
					Fehler fehler = BenutzerAbteilungModelBean.getFehlerList().get(i);
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


	
	public void setAbteilung(AbteilungBean abteilungBean) {
		//Das Model wird geändert.
		BenutzerAbteilungBean BenutzerAbteilungBean = ((BenutzerAbteilungBean)modelKnotenBean.getIBean());
		BenutzerAbteilungBean.setAbteilung(abteilungBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
//		if (source==getJTextBestellmenge()){
//			leseAusgetJTextBestellmenge();
//		}
//		if (source==getJTextLiefertermin()){
//				leseAusgetJTextLiefertermin();
//		}
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	@Override
	public void setStandardFocusPosition() {
		getJButtonMatchCodeAbteilung().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJButtonMatchCodeAbteilung().setEnabled(enabled);
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
							((BenutzerAbteilungDetailsController)getController()).holeAbteilung();
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

	
}  //  @jve:decl-index=0:visual-constraint="49,1"
