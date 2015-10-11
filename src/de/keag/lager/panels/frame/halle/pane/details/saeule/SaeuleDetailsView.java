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
package de.keag.lager.panels.frame.halle.pane.details.saeule;

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
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.ebene.EbeneModelKnotenBean;
import de.keag.lager.panels.frame.halle.pane.details.zeile.ZeileDetailsController;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleModelKnotenBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.zeile.model.ZeileModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

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
import javax.swing.table.AbstractTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;


public class SaeuleDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelSaeule = null;
	private JFormattedTextField jFormattedTextFieldSaeule = null;
	private JLabel jLabelRechts = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JToolBar jToolBar = null;
	private JButton jButtonNeu = null;
	private JButton jButtonLoeschen = null;
	private AbstractTableModel abstractTableModel;
	private JButton jButtonBestandsListe = null;
	private JLabel jLabelPlatzHalter = null;
	private JLabel jLabelPlatzHalter1 = null;
	private JCheckBox jCheckBoxMitMengen = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosZugriffsrechtDetailsController 
	 */
	public SaeuleDetailsView(DetailsController BenutzerPosZugriffsrechtDetailsController) {
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
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 3;
		gridBagConstraints12.anchor = GridBagConstraints.WEST;
		gridBagConstraints12.gridy = 11;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 3;
		gridBagConstraints5.weighty = 1.0D;
		gridBagConstraints5.weightx = 1.0D;
		gridBagConstraints5.gridy = 12;
		jLabelPlatzHalter1 = new JLabel();
		jLabelPlatzHalter1.setText(" ");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.weighty = 1.0D;
		gridBagConstraints4.weightx = 0.0D;
		gridBagConstraints4.gridy = 0;
		jLabelPlatzHalter = new JLabel();
		jLabelPlatzHalter.setText(" ");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 2;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.insets = new Insets(0, 5, 0, 0);
		gridBagConstraints1.weightx = 2.0D;
		gridBagConstraints1.weighty = 1.0D;
		gridBagConstraints1.gridy = 11;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints31.gridy = 13;
		gridBagConstraints31.weightx = 1.0;
		gridBagConstraints31.anchor = GridBagConstraints.WEST;
		gridBagConstraints31.gridx = 0;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.BOTH;
		gridBagConstraints2.gridy = 14;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.weighty = 50.0D;
		gridBagConstraints2.gridwidth = 4;
		gridBagConstraints2.gridx = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(0, 5, 0, 0);
		gridBagConstraints.gridy = 10;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 10;
		gridBagConstraints3.weightx = 1.0D;
		gridBagConstraints3.anchor = GridBagConstraints.WEST;
		gridBagConstraints3.insets = new Insets(0, 5, 0, 0);
		gridBagConstraints3.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.weighty = 1.0D;
		gridBagConstraints11.weightx = 1.0D;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(1, 1, 1, 1);
		gridBagConstraints11.gridy = 10;
		jLabelSaeule = new JLabel();
		jLabelSaeule.setText("Saeule");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 28;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(jLabelSaeule, gridBagConstraints11);
		this.add(getJFormattedTextFieldSaeule(), gridBagConstraints3);
		this.add(getJScrollPane(), gridBagConstraints2);
		this.add(getJToolBar(), gridBagConstraints31);
		this.add(getJButtonBestandsListe(), gridBagConstraints1);
		this.add(jLabelPlatzHalter, gridBagConstraints4);
		this.add(jLabelPlatzHalter1, gridBagConstraints5);
		this.add(getJCheckBoxMitMengen(), gridBagConstraints12);
	}

	@Override
	public void zeichneDich(ModelKnotenBean saeuleModelKnotenBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(saeuleModelKnotenBean!=null){
			if (saeuleModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.SAEULE){
				setModelBean(saeuleModelKnotenBean);//merken
				SaeuleBean saeuleBean = (SaeuleBean) saeuleModelKnotenBean.getIBean();
				//id anzeigen
				getJFormattedTextFieldSaeule().setText(saeuleBean.getNummer().toString());
				getJFormattedTextFieldSaeule().setEnabled(true);
				//kostenstelle anzeigen
//				if (saeuleBean.getZugriffsrecht()!=null){
//					getJTextFieldZugriffsrecht().setText(saeuleBean.getZugriffsrecht().getZugriffsrechtName());
//				}else{
//					getJTextFieldZugriffsrecht().setText(null);
//				}
				
				//Fehler anzeigen.
				for(int i=0; i<saeuleModelKnotenBean.getFehlerList().size();i++){
					Fehler fehler = saeuleModelKnotenBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}

				((AbstractTableModel)getJTable().getModel()).fireTableDataChanged();
				
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


	
	public void setSaeule(SaeuleBean zugriffsrechtBean) {
		//Das Model wird geändert.
		SaeuleBean SaeuleBean = ((SaeuleBean)modelKnotenBean.getIBean());
//		SaeuleBean.setSaeule(zugriffsrechtBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJFormattedTextFieldSaeule()){
			leseAusgetJFormattedTextFieldSaeule();
		}
		getController().ausgewaehlterKnotenIstGeandert();
	}

	private void leseAusgetJFormattedTextFieldSaeule() {
		SaeuleBean bean = (SaeuleBean)getModelBean().getIBean();
		Integer wert ;
		try{
			if(getJFormattedTextFieldSaeule().getValue()!=null){
				wert = ((Number)getJFormattedTextFieldSaeule().getValue()).intValue();
				bean.setNummer(wert);
			}
		}catch(NumberFormatException ex){
		}
	}

	@Override
	public void setStandardFocusPosition() {
		getJFormattedTextFieldSaeule().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJFormattedTextFieldSaeule().setEditable(enabled);
//		getJButtonMatchCodeZugriffsrecht().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldZugriffsrecht	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldSaeule() {
		if (jFormattedTextFieldSaeule == null) {
			jFormattedTextFieldSaeule = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldSaeule.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldSaeule.addPropertyChangeListener("value", this);
//			
//			jFormattedTextFieldSaeule = new JTextField();
//			jFormattedTextFieldSaeule.setEditable(false);
		}
		return jFormattedTextFieldSaeule;
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
	 * This method initializes jLabelRechts	
	 * 	
	 * @return javax.swing.JLabel	
	 */

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
						getController().erstelleNeuenSatz(ModelKnotenTyp.EBENE);
					}
				}
			});
//			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
//			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			
		}
		return jButtonNeu;
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
							EbeneBean bean = (EbeneBean) getJTable().getModel().getValueAt(getJTable().getSelectedRow(), 999);
							getController().loeschePosition(bean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
						}
					}
				}
			});
//			Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
		}
		return jButtonLoeschen;
	}

	private AbstractTableModel getAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = {"Ebene"};

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
					if (modelKnotenBean.getIBean() instanceof EbeneBean){
						EbeneBean bean = 
							(EbeneBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								return bean.getNummer();
							}
							case 999 : return bean; 
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
						 int anzahlZeilen = 0;
						 for (int i=0;i<getModelBean().getKinderList().size();i++){
							 if (getModelBean().getKinderList().get(i) instanceof EbeneModelKnotenBean){
								 anzahlZeilen++;
							 }
						 }
						 return anzahlZeilen;
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

	/**
	 * This method initializes jButtonBestandsListe	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonBestandsListe() {
		if (jButtonBestandsListe == null) {
			jButtonBestandsListe = new JButton();
			jButtonBestandsListe.setText("Bestandsliste drucken");
			jButtonBestandsListe.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						try {
							boolean mitMengen = getJCheckBoxMitMengen().isSelected();
							SaeuleBean bean = ((SaeuleBean)modelKnotenBean.getIBean());
							((SaeuleDetailsController)getController()).druckeInventurListe(bean,mitMengen);
						} catch (LagerException e1) {
							getJComboBoxFehler().addItem(
									new Fehler(24, FehlerTyp.FEHLER, e1
											.getMessage()));
							e1.printStackTrace();
						} catch (SQLException e1) {
							getJComboBoxFehler().addItem(
									new Fehler(24, FehlerTyp.FEHLER, e1
											.getMessage()));
							e1.printStackTrace();
						} catch (Exception e1) {
							getJComboBoxFehler().addItem(
									new Fehler(24, FehlerTyp.FEHLER, e1
											.getMessage()));
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return jButtonBestandsListe;
	}

	/**
	 * This method initializes jCheckBoxMitMengen	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBoxMitMengen() {
		if (jCheckBoxMitMengen == null) {
			jCheckBoxMitMengen = new JCheckBox();
			jCheckBoxMitMengen.setText("mit Mengen");
			jCheckBoxMitMengen.setSelected(true);
		}
		return jCheckBoxMitMengen;
	}
	
}  //  @jve:decl-index=0:visual-constraint="49,1"
