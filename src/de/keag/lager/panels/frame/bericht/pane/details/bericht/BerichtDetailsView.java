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
package de.keag.lager.panels.frame.bericht.pane.details.bericht;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bericht.BerichtBean;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;


public class BerichtDetailsView extends DetailsView  {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelAnforderungsId = null;
	private JLabel jLabelErstellungsDatum = null;
	private JLabel jLabelBenutzer = null;
	private JTextField jTextFieldId = null;
	private JDateChooser JDateChooserOriginalDruckDatum = null;
	private JLabel jLabelHerstellerLieferantName = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JTextField jTextFieldBenutzer = null;
//	private AbstractTableModel abstractTableModelBaPosition = null;
	private JToolBar jToolBar = null;
//	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JTextField jTextFieldEmail = null;
	private JLabel jLabelKopieDruckDatum = null;
	private JDateChooser JDateChooserKopieDruckDatum = null;
	private JLabel jLabelEMail = null;
	private JLabel jLabelLeer = null;
	private JLabel jLabelTyp = null;
	private JLabel jLabelArt = null;
	private JTextField jTextFieldTyp = null;
	private JTextField jTextFieldArt = null;
	private JLabel jLabelTypID = null;
	private JTextField jTextFieldTypID = null;
	/**
	 * This is the default constructor
	 * @param baBlattController 
	 */
	public BerichtDetailsView(BerichtDetailsController baBlattController) {
		super();
		setController(baBlattController);//Controller merken
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
		gridBagConstraints24.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints24.gridy = 3;
		gridBagConstraints24.weightx = 1.0;
		gridBagConstraints24.anchor = GridBagConstraints.WEST;
		gridBagConstraints24.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 3;
		jLabelTypID = new JLabel();
		jLabelTypID.setText("Typ-ID");
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.NONE;
		gridBagConstraints8.gridy = 10;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.anchor = GridBagConstraints.WEST;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
		gridBagConstraints71.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints71.gridy = 2;
		gridBagConstraints71.weightx = 1.0;
		gridBagConstraints71.anchor = GridBagConstraints.WEST;
		gridBagConstraints71.gridx = 1;
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.gridx = 0;
		gridBagConstraints61.anchor = GridBagConstraints.EAST;
		gridBagConstraints61.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints61.gridy = 10;
		jLabelArt = new JLabel();
		jLabelArt.setText("Art");
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.anchor = GridBagConstraints.EAST;
		gridBagConstraints5.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints5.gridy = 2;
		jLabelTyp = new JLabel();
		jLabelTyp.setText("Typ");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.weighty = 1.0D;
		gridBagConstraints4.weightx = 1.0D;
		gridBagConstraints4.gridy = 11;
		jLabelLeer = new JLabel();
		jLabelLeer.setText("  ");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.anchor = GridBagConstraints.EAST;
		gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints3.gridy = 8;
		jLabelEMail = new JLabel();
		jLabelEMail.setText("EMail");
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.gridx = 1;
		gridBagConstraints23.anchor = GridBagConstraints.WEST;
		gridBagConstraints23.gridy = 5;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.gridx = 0;
		gridBagConstraints13.anchor = GridBagConstraints.EAST;
		gridBagConstraints13.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints13.gridy = 5;
		jLabelKopieDruckDatum = new JLabel();
		jLabelKopieDruckDatum.setText("Kopiedruckdatum");
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints22.gridy = 8;
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
		gridBagConstraints18.gridy = 6;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.gridy = 15;
		gridBagConstraints31.gridheight = 1;
		gridBagConstraints31.gridwidth = 3;
		gridBagConstraints31.weightx = 1.0;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 3;
		gridBagConstraints21.gridy = 7;
		jLabelHerstellerLieferantName = new JLabel();
		jLabelHerstellerLieferantName.setText("");
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
		gridBagConstraints7.gridy = 4;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.NONE;
		gridBagConstraints6.gridy = 1;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.weighty = 0.0D;
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 6;
		jLabelBenutzer = new JLabel();
		jLabelBenutzer.setText("Benutzer");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints1.gridy = 4;
		jLabelErstellungsDatum = new JLabel();
		jLabelErstellungsDatum.setText("Originaldruckdatum");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelAnforderungsId = new JLabel();
		jLabelAnforderungsId.setText("Bericht-ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelAnforderungsId, gridBagConstraints);
		this.add(jLabelErstellungsDatum, gridBagConstraints1);
		this.add(jLabelBenutzer, gridBagConstraints2);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJDateChooserOriginalDruckDatum(), gridBagConstraints7);
		this.add(jLabelHerstellerLieferantName, gridBagConstraints21);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJTextFieldBenutzer(), gridBagConstraints18);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(getJTextFieldEmail(), gridBagConstraints22);
		this.add(jLabelKopieDruckDatum, gridBagConstraints13);
		this.add(getJDateChooserKopieDruckDatum(), gridBagConstraints23);
		this.add(jLabelEMail, gridBagConstraints3);
		this.add(jLabelLeer, gridBagConstraints4);
		this.add(jLabelTyp, gridBagConstraints5);
		this.add(jLabelArt, gridBagConstraints61);
		this.add(getJTextFieldTyp(), gridBagConstraints71);
		this.add(getJTextFieldArt(), gridBagConstraints8);
		this.add(jLabelTypID, gridBagConstraints11);
		this.add(getJTextFieldTypID(), gridBagConstraints24);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				BerichtDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				BerichtDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
					BerichtDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof BerichtBean){
					BerichtBean bean = (BerichtBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt BerichtBean sein. Es sei denn, dass Objet ist neu(INSERT).
					if(!(!(bean.getBeanDBStatus()!=BeanDBStatus.INSERT) && (bean.getId().compareTo(Integer.decode(getJTextFieldId().getText()))!=0))){
						//Daten werden NIE übernommen!
						//Dieser Satz ist nicht editierbar auf diese Art und weise!
					}else{
						Log.log().severe("Fehler4");
					}
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
			jTextFieldId.setPreferredSize(new Dimension(100, 20));
			jTextFieldId.setEnabled(false);
			jTextFieldId.setEditable(false);
		}
		return jTextFieldId;
	}

	/**
	 * This method initializes JDateChooserOriginalDruckDatum	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JDateChooser getJDateChooserOriginalDruckDatum() {
		if (JDateChooserOriginalDruckDatum == null) {
			JDateChooserOriginalDruckDatum = new JDateChooser(Konstanten.FORMAT_DATUM,false);
			JDateChooserOriginalDruckDatum.setLayout(new GridBagLayout());
			JDateChooserOriginalDruckDatum.setPreferredSize(new Dimension(100, 20));
			JDateChooserOriginalDruckDatum.setDateFormatString("dd.MM.yyyy");
		}
		return JDateChooserOriginalDruckDatum;
	}

	@Override
	public DetailsController getController() {
		return detailsController;
	}

	@Override
	protected void setController(DetailsController detailsController) {
		this.detailsController = detailsController;
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelBean, IModel iModel) {
		if(modelBean!=null){
			if (modelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BERICHT){
				getJComboBoxFehler().removeAllItems();
				setModelBean(modelBean);//merken
				BerichtBean bean = (BerichtBean) modelBean.getIBean();
				getJTextFieldId().setText(bean.getId().toString());
				getJTextFieldTypID().setText(bean.getBerichtTypId().toString());
				getJDateChooserOriginalDruckDatum().setDate(bean.getDruckDatumOriginal());
				getJDateChooserKopieDruckDatum().setDate(bean.getDruckDatumKopie());
				getJTextFieldBenutzer().setText(bean.getAktuellerBenutzer().toString());
				getJTextFieldEmail().setText(bean.getEMail());
				getJTextFieldTyp().setText(bean.getBerichtTyp().toString());
				getJTextFieldArt().setText(bean.getBerichtArt().toString());
				
				//Fehler anzeigen.
				bean.pruefeEigeneDaten();
				for(int i=0; i<bean.getFehlerList().size();i++){
					Fehler fehler = bean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
//				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
				Log.logger.finest("zeichneDich");
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
			jComboBoxFehler.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					BerichtDetailsView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
				}
			});
		}
		return jComboBoxFehler;
	}

	/**
	 * This method initializes jTextFieldBenutzer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBenutzer() {
		if (jTextFieldBenutzer == null) {
			jTextFieldBenutzer = new JTextField();
			jTextFieldBenutzer.setEditable(false);
		}
		return jTextFieldBenutzer;
	}

//	private AbstractTableModel getAbstractTableModelBaPosition() {
//		if(abstractTableModelBaPosition==null){
//			abstractTableModelBaPosition = new AbstractTableModel() {
//				private String[] columnNames = {"","id","KEG-Nummer","Bezeichnung","Typ","Menge","ME","Liefertermin","Status"};
//
//				@Override
//				public String getColumnName(int column){
//					return columnNames[column];
//				}
//				
//				@Override
//				public Object getValueAt(int row, int column) {
//					if (getModelBean()==null){
//						return "";
//					}
//					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(row);
//					if (modelKnotenBean.getIBean() instanceof BaPosBean){
//						BaPosBean baPosBean = (BaPosBean)modelKnotenBean.getIBean();
//							switch(column){
//							case 0 : return new Integer(row+1).toString(); 
//							case 1 : return baPosBean.getId().toString(); 
//							case 2 : if (baPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								return baPosBean.getArtikelBean().getKeg_nr().toString();
//							}
//							case 3 : if (baPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								return baPosBean.getArtikelBean().getBezeichnung().toString();
//							}
//							case 4 : if (baPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								return baPosBean.getArtikelBean().getTyp().toString();
//							}
//							case 5 : return baPosBean.getBestellmenge().toString(); 
//							case 6 :  if (baPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								if (baPosBean.getArtikelBean().getMengenEinheitBean()==null){
//									return "";
//								}else{
//									return baPosBean.getArtikelBean().getMengenEinheitBean().getName();
//								}
//							} 
//							case 7 : return baPosBean.getLiefertermin().toString(); 
//							case 8 : return baPosBean.getStatus().toString(); 
//							case 999 : return baPosBean; 
//							default : return "Fehler, column:"+new Integer(column).toString(); 
//							}
//					}else{
//						return "Falsche Klasse:"+modelKnotenBean.getIBean().toString();
//					}
//				}
//				
//				@Override
//				public int getRowCount() {
//					 if (getModelBean()==null){
//						 return 0;
//					 }else{
//					     return getModelBean().getKinderList().size();
//					 }
//				}
//				
//				@Override
//				public int getColumnCount() {
//					return columnNames.length;
//				}
//			};
//		}
//		return abstractTableModelBaPosition;
//	}


	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
		}
		return jToolBar;
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
//		getJButtonMatchCodeLieferant().requestFocus();
	}

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler> ();
//		if(!getJTextLiefertermin().getInputVerifier().shouldYieldFocus(getJTextLiefertermin())){
//			errors.add(new Fehler(29));
//		}
//		if(!getJTextBestellmenge().getInputVerifier().shouldYieldFocus(getJTextBestellmenge())){
//			errors.add(new Fehler(34));
//		}
		return errors;
	}
	
	
	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJTextFieldId().setEditable(false);
		getJDateChooserKopieDruckDatum().setEnabled(false);
		getJDateChooserOriginalDruckDatum().setEnabled(false);
		getJTextFieldBenutzer().setEditable(false);
		getJTextFieldEmail().setEditable(false);
		getJTextFieldTyp().setEditable(false);
		getJTextFieldArt().setEditable(false);
//		getJButtonMatchCodeLieferant().setEnabled(enabled);
//		getJButtonNeu().setEnabled(enabled);
//		getJButtonLoeschen().setEnabled(enabled);
//		getJTextFieldEmail().setEditable(enabled);
//		getJButtonMatchCodeEmail().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEmail() {
		if (jTextFieldEmail == null) {
			jTextFieldEmail = new JTextField();
			jTextFieldEmail.setEditable(false);
			jTextFieldEmail.setEnabled(false);
//			jTextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
//				public void keyTyped(java.awt.event.KeyEvent e) {
//					String email = BerichtDetailsView.this.getJTextFieldEmail().getText();
//					setMatchCodeEmail(null,email);
//				}
//			});
		}
		return jTextFieldEmail;
	}

//	//setzen der Email aus dem Matchcode-Fenster
//	public void setMatchCodeEmail(EmailBean emailBean,String email) {
//		if (email!=null){
//			BerichtBean bean = (BerichtBean)getModelBean().getIBean();
//			bean.setEmail(email);
//		}
//		if(emailBean!=null){
//			BerichtBean bean = (BerichtBean)getModelBean().getIBean();
//			bean.setEmail(emailBean.getEmail());
//			getJTextFieldEmail().setText(bean.getEmail());
//		}
//	}

	/**
	 * This method initializes JDateChooserKopieDruckDatum	
	 * 	
	 * @return com.toedter.calendar.JDateChooser	
	 */
	private JDateChooser getJDateChooserKopieDruckDatum() {
		if (JDateChooserKopieDruckDatum == null) {
			JDateChooserKopieDruckDatum = new JDateChooser(Konstanten.FORMAT_DATUM,
					false);
			JDateChooserKopieDruckDatum.setLayout(new GridBagLayout());
			JDateChooserKopieDruckDatum.setPreferredSize(new Dimension(100, 20));
		}
		return JDateChooserKopieDruckDatum;
	}

	/**
	 * This method initializes jTextFieldTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldTyp() {
		if (jTextFieldTyp == null) {
			jTextFieldTyp = new JTextField();
			jTextFieldTyp.setText("          ");
			jTextFieldTyp.setEnabled(false);
			jTextFieldTyp.setPreferredSize(new Dimension(100, 20));
			jTextFieldTyp.setEditable(false);
		}
		return jTextFieldTyp;
	}

	/**
	 * This method initializes jTextFieldArt	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldArt() {
		if (jTextFieldArt == null) {
			jTextFieldArt = new JTextField();
			jTextFieldArt.setText("          ");
			jTextFieldArt.setEnabled(false);
			jTextFieldArt.setPreferredSize(new Dimension(100, 20));
			jTextFieldArt.setEditable(false);
		}
		return jTextFieldArt;
	}

	/**
	 * This method initializes jTextFieldTypID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldTypID() {
		if (jTextFieldTypID == null) {
			jTextFieldTypID = new JTextField();
			jTextFieldTypID.setPreferredSize(new Dimension(100, 20));
			jTextFieldTypID.setEnabled(false);
			jTextFieldTypID.setEditable(false);
		}
		return jTextFieldTypID;
	}



	
}
