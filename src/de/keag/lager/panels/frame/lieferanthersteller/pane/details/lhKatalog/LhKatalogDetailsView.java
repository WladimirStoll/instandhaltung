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
package de.keag.lager.panels.frame.lieferanthersteller.pane.details.lhKatalog;

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
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;

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


public class LhKatalogDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelKatalog = null;
	private JButton jButtonMatchCodeKatalog = null;
	private JTextField jTextFieldKatalog = null;
	/**
	 * This is the default constructor
	 * @param LhPosKatalogDetailsController 
	 */
	public LhKatalogDetailsView(DetailsController LhPosKatalogDetailsController) {
		super();
		setController(LhPosKatalogDetailsController);
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
		gridBagConstraints11.insets = new Insets(1, 1, 1, 1);
		gridBagConstraints11.gridy = 10;
		jLabelKatalog = new JLabel();
		jLabelKatalog.setText("Katalog");
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
		this.add(jLabelKatalog, gridBagConstraints11);
		this.add(getJButtonMatchCodeKatalog(), gridBagConstraints2);
		this.add(getJTextFieldKatalog(), gridBagConstraints3);
	}

	@Override
	public void zeichneDich(ModelKnotenBean lhKatalogModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(lhKatalogModelBean!=null){
			if (lhKatalogModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG){
				setModelBean(lhKatalogModelBean);//merken
				LhKatalogBean lhKatalogBean = (LhKatalogBean) lhKatalogModelBean.getIBean();
				//id anzeigen
//				getJTextFieldId().setText(LhPosBean.getId().toString());
//				getJTextFieldId().setEnabled(false);
				//kostenstelle anzeigen
				if (lhKatalogBean.getKatalog()!=null){
					getJTextFieldKatalog().setText(lhKatalogBean.getKatalog().getName());
				}else{
					getJTextFieldKatalog().setText(null);
				}
				
				//Fehler anzeigen.
				for(int i=0; i<lhKatalogModelBean.getFehlerList().size();i++){
					Fehler fehler = lhKatalogModelBean.getFehlerList().get(i);
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


	
	public void setKatalog(KatalogBean katalogBean) {
		//Das Model wird geändert.
		LhKatalogBean lhKatalogBean = ((LhKatalogBean)modelKnotenBean.getIBean());
		lhKatalogBean.setKatalog(katalogBean);
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
		getJButtonMatchCodeKatalog().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJButtonMatchCodeKatalog().setEnabled(enabled);
	}

	/**
	 * This method initializes jButtonMatchCodeKatalog	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeKatalog() {
		if (jButtonMatchCodeKatalog == null) {
			jButtonMatchCodeKatalog = new JButton();
			jButtonMatchCodeKatalog.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeKatalog.setText("...");
			jButtonMatchCodeKatalog
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((LhKatalogDetailsController)getController()).holeKatalog();
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
		return jButtonMatchCodeKatalog;
	}

	/**
	 * This method initializes jTextFieldKatalog	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldKatalog() {
		if (jTextFieldKatalog == null) {
			jTextFieldKatalog = new JTextField();
			jTextFieldKatalog.setEditable(false);
		}
		return jTextFieldKatalog;
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
