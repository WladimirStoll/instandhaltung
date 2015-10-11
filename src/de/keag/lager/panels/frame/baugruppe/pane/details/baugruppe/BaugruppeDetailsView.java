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
package de.keag.lager.panels.frame.baugruppe.pane.details.baugruppe;

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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.pane.suche.BaugruppeSuchController;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.suche.HalleSuchController;
import de.keag.lager.panels.frame.matchcode.halle.IHalleMCAnforderer;
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


public class BaugruppeDetailsView extends DetailsView implements IBaugruppeDetailsBeobachter, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelBaugruppeId = null;
	private JTextField jTextFieldId = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;

	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JToolBar jToolBar = null;
	private JButton jButtonLoeschen = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JFormattedTextField jTextFieldName = null;
	private JLabel jLabelName = null;
	private JButton jButtonNeu = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private AbstractTableModel abstractTableModel;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModelArtikel;  //  @jve:decl-index=0:
	private JToolBar jToolBar2 = null;
	private JButton jButtonNeuArtikel = null;
	private JButton jButtonLoeschenArtikel = null;
	private JScrollPane jScrollPaneArtikele = null;
	private JTable jTableArtikel = null;
	private JLabel jLabelHalle = null;
	private JFormattedTextField jFormattedTextFieldHalle = null;
	private JButton jButtonMatchCodeHalle = null;
	/**
	 * This is the default constructor
	 * @param controller 
	 */
	public BaugruppeDetailsView(BaugruppeDetailsController controller) {
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
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 3;
		gridBagConstraints4.gridy = 11;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 11;
		gridBagConstraints3.weightx = 1.0;
		gridBagConstraints3.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints2.gridy = 11;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.gridx = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.gridy = 11;
		jLabelHalle = new JLabel();
		jLabelHalle.setText("Halle");
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
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.gridx = 0;
		gridBagConstraints13.anchor = GridBagConstraints.EAST;
		gridBagConstraints13.gridy = 10;
		jLabelName = new JLabel();
		jLabelName.setText("Baugruppe");
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints22.gridy = 10;
		gridBagConstraints22.weightx = 1.0;
		gridBagConstraints22.anchor = GridBagConstraints.WEST;
		gridBagConstraints22.gridx = 1;
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
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints6.gridy = 1;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelBaugruppeId = new JLabel();
		jLabelBaugruppeId.setText("ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelBaugruppeId, gridBagConstraints);
		this.add(jLabelName, gridBagConstraints13);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJTextFieldName(), gridBagConstraints22);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(getJScrollPane(), gridBagConstraints51);
		this.add(getJToolBar2(), gridBagConstraints21);
		this.add(getJScrollPaneArtikele(), gridBagConstraints34);
		this.add(jLabelHalle, gridBagConstraints1);
		this.add(getJFormattedTextFieldHalle(), gridBagConstraints3);
		this.add(getJButtonMatchCodeHalle(), gridBagConstraints4);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				BaugruppeDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
//				System.out.println("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				BaugruppeDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
				BaugruppeDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof BaugruppeBean){
					BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt BaugruppeBean sein. Es sei denn, dass Objet ist neu(INSERT).
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

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		setzeHintergrund();
		if(modelKnotenBean!=null){
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BAUGRUPPE){
				getJComboBoxFehler().removeAllItems();
				setModelBean(modelKnotenBean);//merken
				BaugruppeBean baugruppeBean = (BaugruppeBean) modelKnotenBean.getIBean();
				getJTextFieldId().setText(baugruppeBean.getId().toString());
				getJTextFieldId().setEnabled(false);
				
				getJTextFieldName().removePropertyChangeListener("value", this);
				getJTextFieldName().setValue(baugruppeBean.getName());
				getJTextFieldName().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldHalle().setText(baugruppeBean.getHalleBean().getName());
		//		//Erstellungsdatum wird angezeigt.
		//		getJDateChooserErstellungsDatum().setDate(baugruppeBean.getErstellungsDatum());
		//		getJDateChooserErstellungsDatum().setEnabled(false);
				
				//Fehler anzeigen.
//				baugruppeBean.pruefeEigeneDaten();
				for(int i=0; i<baugruppeBean.getFehlerList().size();i++){
					Fehler fehler = baugruppeBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				
				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				((AbstractTableModel)jTableArtikel.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
				Log.log().finest("zeichneDich() BaugruppeDetailsView");
				
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


	private AbstractTableModel getKinderBaugruppeAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = {"Untergeordnete Baugruppen"};

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
					if (modelKnotenBean.getIBean() instanceof BaugruppeBean){
						BaugruppeBean baugruppeBean = (BaugruppeBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (baugruppeBean!=null && baugruppeBean.getId()!=null){
									return baugruppeBean.getName();
								}else return "";
							}
							case 999 : return baugruppeBean; 
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
							 if (getModelBean().getKinderList().get(i) instanceof BaugruppeModelKnotenBean){
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
	
	private AbstractTableModel getArtikelAbstractTableModel() {
		if(abstractTableModelArtikel==null){
			abstractTableModelArtikel = new AbstractTableModel() {
				private String[] columnNames = {"Artikel"};

				@Override
				public String getColumnName(int column){
					return columnNames[column];
				}
				
				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean()==null){
						return "";
					}
					Log.log().finest("row:"+row);
					
					//Finde heraus, ab welcher Zeile die Artikele beginnen.
        			int artikelAbZeile = 0;
					 if (getModelBean().getKinderList().get(0) instanceof BaugruppeArtikelModelKnotenBean){
						 artikelAbZeile = 0;
					 }else{
						 for (int i=0;i<getModelBean().getKinderList().size()&&artikelAbZeile==0;i++){
							 if (getModelBean().getKinderList().get(i) instanceof BaugruppeArtikelModelKnotenBean){
								 artikelAbZeile = i;
							 }
						 }
					 }
					 
					 
					
					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(artikelAbZeile+row);
					if (modelKnotenBean.getIBean() instanceof BaugruppeArtikelBean){
						BaugruppeArtikelBean baugruppeArtikelBean = 
							(BaugruppeArtikelBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (baugruppeArtikelBean!=null && baugruppeArtikelBean.getArtikel()!=null){
									return baugruppeArtikelBean.getArtikel().getBezeichnung() 
											+ baugruppeArtikelBean.getArtikel().getTyp(); 
								}else return "";
							}
							case 999 : return baugruppeArtikelBean; 
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
						 int anzahlArtikel = 0;
						 for (int i=0;i<getModelBean().getKinderList().size();i++){
							 if (getModelBean().getKinderList().get(i) instanceof BaugruppeArtikelModelKnotenBean){
								 anzahlArtikel++;
							 }
						 }
						 Log.log().finest("Art:"+anzahlArtikel);
						 return anzahlArtikel;
					 }
				}
				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModelArtikel;
	}
	


	@Override
	public void setModelBean(ModelKnotenBean baugruppeModelBean) {
		this.modelKnotenBean = baugruppeModelBean;
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
							BaugruppeBean bean = 
								(BaugruppeBean) getJTable().
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
		getJTextFieldId().setEnabled(false);
		getJTextFieldName().setEditable(enabled);
		getJTable().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldName() {
		if (jTextFieldName == null) {
			jTextFieldName = new JFormattedTextField(new LagerStringFormatter(0,45));
			jTextFieldName.setInputVerifier(LagerFormate.getInputVerifier());
			//jTextFieldName.addPropertyChangeListener("value", this);
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
						getController().erstelleNeuenSatz(ModelKnotenTyp.BAUGRUPPE);
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
			jTable.setModel(getKinderBaugruppeAbstractTableModel());
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


//	private void leseAusgetJTextFieldEmail() {
//		BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldEmail().getValue() != null) {
//			wert = getJTextFieldEmail().getValue().toString();
//			baugruppeBean.setEmail(wert);
//		}
//	}

//	private void leseAusgetJTextFieldPasswort() {
//		BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldPasswort().getValue() != null) {
//			wert = getJTextFieldPasswort().getValue().toString();
//			baugruppeBean.setPassword(wert);
//		}
//	}

//	private void leseAusgetJTextFieldLoginName() {
//		BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldLoginName().getValue() != null) {
//			wert = getJTextFieldLoginName().getValue().toString();
//			baugruppeBean.setLoginName(wert);
//		}
//	}

//	private void leseAusgetJTextFieldVorname() {
//		BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldVorname().getValue() != null) {
//			wert = getJTextFieldVorname().getValue().toString();
//			baugruppeBean.setVorname(wert);
//		}
//	}

	private void leseAusgetJTextFieldName()  {
		BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldName().getValue() != null) {
			wert = getJTextFieldName().getValue().toString();
			baugruppeBean.setName(wert);
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
//		if (source==getJTextFieldVorname()){
//			leseAusgetJTextFieldVorname();
//		}
//		if (source==getJTextFieldLoginName()){
//			leseAusgetJTextFieldLoginName();
//		}
//		if (source==getJTextFieldPasswort()){
//			leseAusgetJTextFieldPasswort();
//		}
//		if (source==getJTextFieldEmail()){
//			leseAusgetJTextFieldEmail();
//		}
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
//			if (!getJTextFieldVorname().getInputVerifier().shouldYieldFocus(
//					getJTextFieldVorname())) {
//				errors.add(new Fehler(45));
//			} else {
//				leseAusgetJTextFieldVorname();
//			}
//			if (!getJTextFieldLoginName().getInputVerifier().shouldYieldFocus(
//					getJTextFieldLoginName())) {
//				errors.add(new Fehler(46));
//			} else {
//				leseAusgetJTextFieldLoginName();
//			}
//			if (!getJTextFieldPasswort().getInputVerifier().shouldYieldFocus(
//					getJTextFieldPasswort())) {
//				errors.add(new Fehler(47));
//			} else {
//				leseAusgetJTextFieldPasswort();
//			}
//			if (!getJTextFieldEmail().getInputVerifier().shouldYieldFocus(
//					getJTextFieldEmail())) {
//				errors.add(new Fehler(48));
//			} else {
//				leseAusgetJTextFieldEmail();
//			}
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
			jToolBar2.add(getJButtonNeuArtikel());
			jToolBar2.add(getJButtonLoeschenArtikel());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes jButtonNeuArtikel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNeuArtikel() {
		if (jButtonNeuArtikel == null) {
			jButtonNeuArtikel = new JButton();
			jButtonNeuArtikel.setText("Neu");
			jButtonNeuArtikel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						getController().erstelleNeuenSatz(ModelKnotenTyp.BAUGRUPPE_ARTIKEL);
					}
				}
			});
			
		}
		return jButtonNeuArtikel;
	}

	/**
	 * This method initializes jButtonLoeschenArtikel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLoeschenArtikel() {
		if (jButtonLoeschenArtikel == null) {
			jButtonLoeschenArtikel = new JButton();
			jButtonLoeschenArtikel.setText("Löschen");
			jButtonLoeschenArtikel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						if (getJTableArtikele().getSelectedRow()>=0){
							BaugruppeArtikelBean bean = 
								(BaugruppeArtikelBean) getJTableArtikele().
										getModel().
											getValueAt(getJTableArtikele().getSelectedRow(), 999);
							getController().loeschePosition(bean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
						}
					}

				}
			});
			
			
		}
		return jButtonLoeschenArtikel;
	}

	/**
	 * This method initializes jScrollPaneArtikele	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneArtikele() {
		if (jScrollPaneArtikele == null) {
			jScrollPaneArtikele = new JScrollPane();
			jScrollPaneArtikele.setViewportView(getJTableArtikele());
		}
		return jScrollPaneArtikele;
	}

	/**
	 * This method initializes jTableArtikele	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTableArtikele() {
		if (jTableArtikel == null) {
			jTableArtikel = new JTable();
			jTableArtikel.setModel(getArtikelAbstractTableModel());
			jTableArtikel.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			jTableArtikel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==2){
						//ausgewählte Bean wird ermittelt. 
						int aktuelleZeile = jTableArtikel.getSelectedRow();
						if (getModelBean()!=null 
								&& getModelBean().getKinderList().size()>0
								&& getModelBean().getKinderList().size()>=aktuelleZeile){
							int countArt = 0;
							for(int i = 0;i<getModelBean().getKinderList().size();i++){
								ModelKnotenBean mkb = getModelBean().getKinderList().get(i);
								if (mkb instanceof BaugruppeArtikelModelKnotenBean){
									if (countArt==aktuelleZeile){
										getController().selectKnoten(mkb);
										continue;
									}
									countArt++;
								}
							}
//							ModelKnotenBean modelKnotenBean =  getModelBean().getKinderList().get(aktuelleZeile);
//							getController().selectKnoten(modelKnotenBean);
						}
						
					};
				}
			});
			
		}
		return jTableArtikel;
	}

	/**
	 * This method initializes jFormattedTextFieldHalle	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldHalle() {
		if (jFormattedTextFieldHalle == null) {
			jFormattedTextFieldHalle = new JFormattedTextField();
			jFormattedTextFieldHalle.setEditable(false);
		}
		return jFormattedTextFieldHalle;
	}

	/**
	 * This method initializes jButtonMatchCodeHalle	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeHalle() {

			if (jButtonMatchCodeHalle == null) {
				jButtonMatchCodeHalle = new JButton();
				jButtonMatchCodeHalle.setPreferredSize(new Dimension(30, 20));
				jButtonMatchCodeHalle.setText("...");
				jButtonMatchCodeHalle.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						try {
							if (pruefeFehler()){
								((BaugruppeDetailsController)getController()).holeHalle();
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
			return jButtonMatchCodeHalle;
		}

	public void setMatchCodeHalle(HalleBean halleBean,String email) {
		if (email!=null){
			BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
			baugruppeBean.setHalleBean(halleBean);
		}
		if(halleBean!=null){
			BaugruppeBean baugruppeBean = (BaugruppeBean)getModelBean().getIBean();
			baugruppeBean.setHalleBean(halleBean);
			getJFormattedTextFieldHalle().setText(baugruppeBean.getHalleBean().getName());
		}
	}
//		if (jButtonMatchCodeHalle == null) {
//			jButtonMatchCodeHalle = new JButton();
//			jButtonMatchCodeHalle.setPreferredSize(new Dimension(30, 20));
//			jButtonMatchCodeHalle.setText("...");
//		}
//		return jButtonMatchCodeHalle;
//	}

	public void setArtikel(ArtikelBean artikelBean) {
		//Das Model wird geändert.
		BaPosBean baPosBean = ((BaPosBean)getModelBean().getIBean());
		baPosBean.setArtikelBean(artikelBean);
		baPosBean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
		baPosBean.setBestellmenge(artikelBean.getEmpfohlene_bestellmenge());
		
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setHalle(HalleBean halleBean) {

			//Das Model wird geändert.
			BaugruppeBean baugruppeBean = ((BaugruppeBean)getModelBean().getIBean());
			baugruppeBean.setHalleBean(halleBean);
//			baPosBean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
//			baPosBean.setBestellmenge(artikelBean.getEmpfohlene_bestellmenge());
//			
			//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
			getController().ausgewaehlterKnotenIstGeandert();
		}

		
	}
	
	

