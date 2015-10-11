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
package de.keag.lager.panels.frame.benutzer.pane.details.benutzer;

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
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

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
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungModelKnotenBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;



import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class BenutzerDetailsView extends DetailsView implements IBenutzerDetailsBeobachter, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelAnforderungsId = null;
	private JLabel jLabelErstellungsDatum = null;
	private JLabel jLabelName = null;
	private JLabel jLabelVorname = null;
	private JLabel jLabelPasswort = null;
	private JTextField jTextFieldId = null;
	private JDateChooser JDateChooserErstellungsDatum = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;

	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JToolBar jToolBar = null;
	private JButton jButtonLoeschen = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JLabel jLabeLoginName = null;
	private JFormattedTextField jTextFieldEmail = null;
	private JButton jButtonMatchCodeEmail = null;
	private JLabel jLabelIMail = null;
	private JFormattedTextField jTextFieldPasswort = null;
	private JFormattedTextField jTextFieldLoginName = null;
	private JFormattedTextField jTextFieldVorname = null;
	private JFormattedTextField jTextFieldName = null;
	private JButton jButtonNeu = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private AbstractTableModel abstractTableModel;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModelZugriffsrecht;  //  @jve:decl-index=0:
	private JToolBar jToolBar2 = null;
	private JButton jButtonNeuZugriffsrecht = null;
	private JButton jButtonLoeschenZugriffsrecht = null;
	private JScrollPane jScrollPaneZugriffsrechte = null;
	private JTable jTableZugriffsrechte = null;
	/**
	 * This is the default constructor
	 * @param controller 
	 */
	public BenutzerDetailsView(BenutzerDetailsController controller) {
		super();
		setController(controller);//Controller merken
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
		gridBagConstraints34.fill = GridBagConstraints.BOTH;
		gridBagConstraints34.gridy = 15;
		gridBagConstraints34.weightx = 1.0;
		gridBagConstraints34.weighty = 1.0;
		gridBagConstraints34.gridwidth = 3;
		gridBagConstraints34.gridx = 0;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints21.gridy = 14;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.anchor = GridBagConstraints.WEST;
		gridBagConstraints21.gridx = 0;
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.fill = GridBagConstraints.BOTH;
		gridBagConstraints51.gridy = 13;
		gridBagConstraints51.weightx = 1.0;
		gridBagConstraints51.weighty = 1.0;
		gridBagConstraints51.gridwidth = 3;
		gridBagConstraints51.gridx = 0;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 5;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints61.gridy = 6;
		gridBagConstraints61.weightx = 1.0;
		gridBagConstraints61.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridy = 7;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.gridx = 1;
		GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
		gridBagConstraints42.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints42.gridy = 8;
		gridBagConstraints42.weightx = 1.0;
		gridBagConstraints42.gridx = 1;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.gridx = 0;
		gridBagConstraints13.anchor = GridBagConstraints.EAST;
		gridBagConstraints13.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints13.gridy = 10;
		jLabelIMail = new JLabel();
		jLabelIMail.setText("EMail");
		GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
		gridBagConstraints33.gridx = 2;
		gridBagConstraints33.gridy = 10;
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints22.gridy = 10;
		gridBagConstraints22.weightx = 1.0;
		gridBagConstraints22.anchor = GridBagConstraints.WEST;
		gridBagConstraints22.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 7;
		jLabeLoginName = new JLabel();
		jLabeLoginName.setText("LoginName");
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
		gridBagConstraints32.gridy = 12;
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
		gridBagConstraints31.gridy = 16;
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
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.fill = GridBagConstraints.NONE;
		gridBagConstraints7.gridy = 3;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.NONE;
		gridBagConstraints6.gridy = 1;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints4.gridy = 8;
		jLabelPasswort = new JLabel();
		jLabelPasswort.setText("Passwort");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.anchor = GridBagConstraints.EAST;
		gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints3.gridy = 6;
		jLabelVorname = new JLabel();
		jLabelVorname.setText("Vorname");
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 5;
		jLabelName = new JLabel();
		jLabelName.setText("Name");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints1.gridy = 3;
		jLabelErstellungsDatum = new JLabel();
		jLabelErstellungsDatum.setText("Erstellungsdatum");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelAnforderungsId = new JLabel();
		jLabelAnforderungsId.setText("Benutzer-ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelAnforderungsId, gridBagConstraints);
		this.add(jLabelErstellungsDatum, gridBagConstraints1);
		this.add(jLabelName, gridBagConstraints2);
		this.add(jLabelVorname, gridBagConstraints3);
		this.add(jLabeLoginName, gridBagConstraints11);
		this.add(jLabelPasswort, gridBagConstraints4);
		this.add(jLabelIMail, gridBagConstraints13);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJTextFieldName(), gridBagConstraints8);
		this.add(getJTextFieldVorname(), gridBagConstraints61);
		this.add(getJTextFieldLoginName(), gridBagConstraints5);
		this.add(getJTextFieldPasswort(), gridBagConstraints42);
		this.add(getJTextFieldEmail(), gridBagConstraints22);
		this.add(getJDateChooserErstellungsDatum(), gridBagConstraints7);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJButtonMatchCodeEmail(), gridBagConstraints33);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(getJScrollPane(), gridBagConstraints51);
		this.add(getJToolBar2(), gridBagConstraints21);
		this.add(getJScrollPaneZugriffsrechte(), gridBagConstraints34);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				BenutzerDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				BenutzerDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
				BenutzerDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof BenutzerBean){
					BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt BenutzerBean sein. Es sei denn, dass Objet ist neu(INSERT).
				}else{
					Log.log().severe("Fehler3");
				}
			}else{
				Log.log().severe("Fehler2");
			}
		}else{
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
			jTextFieldId.setPreferredSize(new Dimension(50, 20));
		}
		return jTextFieldId;
	}

	/**
	 * This method initializes JDateChooserErstellungsDatum	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JDateChooser getJDateChooserErstellungsDatum() {
		if (JDateChooserErstellungsDatum == null) {
			JDateChooserErstellungsDatum = new JDateChooser(Konstanten.FORMAT_DATUM,false);
			JDateChooserErstellungsDatum.setLayout(new GridBagLayout());
			JDateChooserErstellungsDatum.setPreferredSize(new Dimension(100, 20));
		}
		return JDateChooserErstellungsDatum;
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		setzeHintergrund();
		if(modelKnotenBean!=null){
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER){
				getJComboBoxFehler().removeAllItems();
				setModelBean(modelKnotenBean);//merken
				BenutzerBean benutzerBean = (BenutzerBean) modelKnotenBean.getIBean();
				getJTextFieldId().setText(benutzerBean.getId().toString());
				getJTextFieldId().setEnabled(false);
				getJTextFieldName().setText(benutzerBean.getName());
				getJTextFieldVorname().setText(benutzerBean.getVorname());
				getJTextFieldLoginName().setText(benutzerBean.getLoginName());
				getJTextFieldPasswort().setText(benutzerBean.getPassword());
				getJTextFieldEmail().setText(benutzerBean.getEmail());
				
		//		//Erstellungsdatum wird angezeigt.
		//		getJDateChooserErstellungsDatum().setDate(benutzerBean.getErstellungsDatum());
		//		getJDateChooserErstellungsDatum().setEnabled(false);
				
				//Fehler anzeigen.
//				benutzerBean.pruefeEigeneDaten();
				for(int i=0; i<benutzerBean.getFehlerList().size();i++){
					Fehler fehler = benutzerBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				
				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
//				System.out.println("zeichneDich() BenutzerDetailsView");
			}
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
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = {"Abteilung"};

				@Override
				public String getColumnName(int column){
					return columnNames[column];
				}
				
				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean()==null){
						return "";
					}
					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(row);
					if (modelKnotenBean.getIBean() instanceof BenutzerAbteilungBean){
						BenutzerAbteilungBean benutzerAbteilungBean = 
							(BenutzerAbteilungBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (benutzerAbteilungBean!=null && benutzerAbteilungBean.getAbteilung()!=null){
									return benutzerAbteilungBean.getAbteilung().getAbteilungName();
								}else return "";
							}
							case 999 : return benutzerAbteilungBean; 
							default : return "Fehler, column:"+new Integer(column).toString(); 
							}
					}else{
						return "Falsche Klasse:"+modelKnotenBean.getIBean().toString();
					}
				}
				
				@Override
				public int getRowCount() {
					 if (getModelBean()==null){
						 return 0;
					 }else{
						 int anzahlAbteilungen = 0;
						 for (int i=0;i<getModelBean().getKinderList().size();i++){
							 if (getModelBean().getKinderList().get(i) instanceof BenutzerAbteilungModelKnotenBean){
								 anzahlAbteilungen++;
							 }
						 }
						 return anzahlAbteilungen;
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
	
	private AbstractTableModel getAbstractTableModelZugriffrecht() {
		if(abstractTableModelZugriffsrecht==null){
			abstractTableModelZugriffsrecht = new AbstractTableModel() {
				private String[] columnNames = {"Zugriffsrecht"};

				@Override
				public String getColumnName(int column){
					return columnNames[column];
				}
				
				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean()==null){
						return "";
					}
					
					//Finde heraus, ab welcher Zeile die Zugriffsrechte beginnen.
					int zugriffsrechtAbZeile = 0;
					 for (int i=0;i<getModelBean().getKinderList().size()&&zugriffsrechtAbZeile==0;i++){
						 if (getModelBean().getKinderList().get(i) instanceof BenutzerZugriffsrechtModelKnotenBean){
							 zugriffsrechtAbZeile = i;
						 }
					 }
					
					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(zugriffsrechtAbZeile+row);
					if (modelKnotenBean.getIBean() instanceof BenutzerZugriffsrechtBean){
						BenutzerZugriffsrechtBean benutzerZugriffsrechtBean = 
							(BenutzerZugriffsrechtBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (benutzerZugriffsrechtBean!=null && benutzerZugriffsrechtBean.getZugriffsrecht()!=null){
									return benutzerZugriffsrechtBean.getZugriffsrecht().getZugriffsrechtName();
								}else return "";
							}
							case 999 : return benutzerZugriffsrechtBean; 
							default : return "Fehler, column:"+new Integer(column).toString(); 
							}
					}else{
						return "Falsche Klasse:"+modelKnotenBean.getIBean().toString();
					}
				}
				
				@Override
				public int getRowCount() {
					 if (getModelBean()==null){
						 return 0;
					 }else{
						 int anzahlZugriffsrecht = 0;
						 for (int i=0;i<getModelBean().getKinderList().size();i++){
							 if (getModelBean().getKinderList().get(i) instanceof BenutzerZugriffsrechtModelKnotenBean){
								 anzahlZugriffsrecht++;
							 }
						 }
						 return anzahlZugriffsrecht;
					 }
				}
				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModelZugriffsrecht;
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
			jButtonLoeschen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						if (getJTable().getSelectedRow()>=0){
							BenutzerAbteilungBean bean = 
								(BenutzerAbteilungBean) getJTable().
										getModel().
											getValueAt(getJTable().getSelectedRow(), 999);
							getController().loeschePosition(bean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
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
			jPanelOben.setPreferredSize(new Dimension(10, 10));
		}
		return jPanelOben;
	}

	@Override
	public void setStandardFocusPosition() {
		getJTextFieldName().requestFocus();
	}

	/**
	 * enable = benutzbar
	 * editable = beschreibbar
	 */
	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJButtonMatchCodeEmail().setEnabled(enabled);
		getJButtonNeu().setEnabled(enabled);
		getJButtonLoeschen().setEnabled(enabled);
		getJTextFieldEmail().setEditable(enabled);
		getJButtonMatchCodeEmail().setEnabled(enabled);
		getJTextFieldName().setEditable(enabled);
		getJTextFieldLoginName().setEditable(enabled);
		getJTextFieldPasswort().setEditable(enabled);
		getJTextFieldVorname().setEditable(enabled);
		getJDateChooserErstellungsDatum().setEnabled(enabled);
		getJTable().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldEmail() {
		if (jTextFieldEmail == null) {
			jTextFieldEmail = new JFormattedTextField(new LagerStringFormatter(0,45));
			jTextFieldEmail.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldEmail.addPropertyChangeListener("value", this);
		}
		return jTextFieldEmail;
	}

	/**
	 * This method initializes jButtonMatchCodeEmail	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeEmail() {
		if (jButtonMatchCodeEmail == null) {
			jButtonMatchCodeEmail = new JButton();
			jButtonMatchCodeEmail.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeEmail.setText("...");
			jButtonMatchCodeEmail
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((BenutzerDetailsController)getController()).holeEMail();
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
		return jButtonMatchCodeEmail;
	}

	//setzen der Email aus dem Matchcode-Fenster
	public void setMatchCodeEmail(EmailBean emailBean,String email) {
		if (email!=null){
			BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
			benutzerBean.setEmail(email);
		}
		if(emailBean!=null){
			BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
			benutzerBean.setEmail(emailBean.getEmail());
			getJTextFieldEmail().setText(benutzerBean.getEmail());
		}
	}

	/**
	 * This method initializes jTextFieldPasswort	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldPasswort() {
		if (jTextFieldPasswort == null) {
			jTextFieldPasswort = new JFormattedTextField(new LagerStringFormatter(4,4));
			jTextFieldPasswort.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldPasswort.addPropertyChangeListener("value", this);
			
		}
		return jTextFieldPasswort;
	}

	/**
	 * This method initializes jTextFieldLoginName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldLoginName() {
		if (jTextFieldLoginName == null) {
			jTextFieldLoginName = new JFormattedTextField(new LagerStringFormatter(1,20));
			jTextFieldLoginName.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldLoginName.addPropertyChangeListener("value", this);
		}
		return jTextFieldLoginName;
	}

	/**
	 * This method initializes jTextFieldVorname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldVorname() {
		if (jTextFieldVorname == null) {
			jTextFieldVorname = new JFormattedTextField(new LagerStringFormatter(1,20));
			jTextFieldVorname.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldVorname.addPropertyChangeListener("value", this);
			
			
		}
		return jTextFieldVorname;
	}

	/**
	 * This method initializes jTextFieldName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldName() {
		if (jTextFieldName == null) {
			jTextFieldName = new JFormattedTextField(new LagerStringFormatter(1,20));
			jTextFieldName.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldName.addPropertyChangeListener("value", this);
		}
		return jTextFieldName;
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
					if (pruefeFehler()){
						getController().erstelleNeuenSatz(ModelKnotenTyp.BENUTZER_ABTEILUNG);
					}
				}
			});
//			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
//			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
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
					if(e.getClickCount()==2){
						//ausgewählte Bean wird ermittelt. 
						int aktuelleZeile = jTable.getSelectedRow();
						if (getModelBean()!=null 
								&& getModelBean().getKinderList().size()>0
								&& getModelBean().getKinderList().size()>=aktuelleZeile){
							ModelKnotenBean modelKnotenBean =  getModelBean().getKinderList().get(aktuelleZeile);
							getController().selectKnoten(modelKnotenBean);
						}
						
					};
				}
			});
		}
		return jTable;
	}


	private void leseAusgetJTextFieldEmail() {
		BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldEmail().getValue() != null) {
			wert = getJTextFieldEmail().getValue().toString();
			benutzerBean.setEmail(wert);
		}
	}

	private void leseAusgetJTextFieldPasswort() {
		BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldPasswort().getValue() != null) {
			wert = getJTextFieldPasswort().getValue().toString();
			benutzerBean.setPassword(wert);
		}
	}

	private void leseAusgetJTextFieldLoginName() {
		BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldLoginName().getValue() != null) {
			wert = getJTextFieldLoginName().getValue().toString();
			benutzerBean.setLoginName(wert);
		}
	}

	private void leseAusgetJTextFieldVorname() {
		BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldVorname().getValue() != null) {
			wert = getJTextFieldVorname().getValue().toString();
			benutzerBean.setVorname(wert);
		}
	}

	private void leseAusgetJTextFieldName()  {
		BenutzerBean benutzerBean = (BenutzerBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldName().getValue() != null) {
			wert = getJTextFieldName().getValue().toString();
			benutzerBean.setName(wert);
		}
	}
	
	/**
	 * Diese Funktion wird immer dann aufgerufen, falls in einem JFormattedField der Inhalt geändert wird.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJTextFieldName()){
			leseAusgetJTextFieldName();
		}
		if (source==getJTextFieldVorname()){
			leseAusgetJTextFieldVorname();
		}
		if (source==getJTextFieldLoginName()){
			leseAusgetJTextFieldLoginName();
		}
		if (source==getJTextFieldPasswort()){
			leseAusgetJTextFieldPasswort();
		}
		if (source==getJTextFieldEmail()){
			leseAusgetJTextFieldEmail();
		}
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}
	

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {
			if (!getJTextFieldName().getInputVerifier().shouldYieldFocus(
					getJTextFieldName())) {
				errors.add(new Fehler(44));
			} else {
				leseAusgetJTextFieldName();
			}
			if (!getJTextFieldVorname().getInputVerifier().shouldYieldFocus(
					getJTextFieldVorname())) {
				errors.add(new Fehler(45));
			} else {
				leseAusgetJTextFieldVorname();
			}
			if (!getJTextFieldLoginName().getInputVerifier().shouldYieldFocus(
					getJTextFieldLoginName())) {
				errors.add(new Fehler(46));
			} else {
				leseAusgetJTextFieldLoginName();
			}
			if (!getJTextFieldPasswort().getInputVerifier().shouldYieldFocus(
					getJTextFieldPasswort())) {
				errors.add(new Fehler(47));
			} else {
				leseAusgetJTextFieldPasswort();
			}
			if (!getJTextFieldEmail().getInputVerifier().shouldYieldFocus(
					getJTextFieldEmail())) {
				errors.add(new Fehler(48));
			} else {
				leseAusgetJTextFieldEmail();
			}
		} catch (Exception e) {
			//Alle Fehler abfangen
			errors.add(new Fehler(36,FehlerTyp.FEHLER,e.getMessage()));
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
		if (jButtonNeuZugriffsrecht == null) {
			jButtonNeuZugriffsrecht = new JButton();
			jButtonNeuZugriffsrecht.setText("Neu");
			jButtonNeuZugriffsrecht.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						getController().erstelleNeuenSatz(ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT);
					}
				}
			});
			
		}
		return jButtonNeuZugriffsrecht;
	}

	/**
	 * This method initializes jButtonLoeschenZugriffsrecht	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLoeschenZugriffsrecht() {
		if (jButtonLoeschenZugriffsrecht == null) {
			jButtonLoeschenZugriffsrecht = new JButton();
			jButtonLoeschenZugriffsrecht.setText("Löschen");
			jButtonLoeschenZugriffsrecht.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						if (getJTableZugriffsrechte().getSelectedRow()>=0){
							BenutzerZugriffsrechtBean bean = 
								(BenutzerZugriffsrechtBean) getJTableZugriffsrechte().
										getModel().
											getValueAt(getJTableZugriffsrechte().getSelectedRow(), 999);
							getController().loeschePosition(bean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
						}
					}

				}
			});
			
			
		}
		return jButtonLoeschenZugriffsrecht;
	}

	/**
	 * This method initializes jScrollPaneZugriffsrechte	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneZugriffsrechte() {
		if (jScrollPaneZugriffsrechte == null) {
			jScrollPaneZugriffsrechte = new JScrollPane();
			jScrollPaneZugriffsrechte.setViewportView(getJTableZugriffsrechte());
		}
		return jScrollPaneZugriffsrechte;
	}

	/**
	 * This method initializes jTableZugriffsrechte	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTableZugriffsrechte() {
		if (jTableZugriffsrechte == null) {
			jTableZugriffsrechte = new JTable();
			jTableZugriffsrechte.setModel(getAbstractTableModelZugriffrecht());
			jTableZugriffsrechte.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			jTableZugriffsrechte.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==2){
						//ausgewählte Bean wird ermittelt. 
						int aktuelleZeile = jTableZugriffsrechte.getSelectedRow();
						if (getModelBean()!=null 
								&& getModelBean().getKinderList().size()>0
								&& getModelBean().getKinderList().size()>=aktuelleZeile){
							ModelKnotenBean modelKnotenBean =  getModelBean().getKinderList().get(aktuelleZeile);
							getController().selectKnoten(modelKnotenBean);
						}
						
					};
				}
			});
			
		}
		return jTableZugriffsrechte;
	}


	
	
}
