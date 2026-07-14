package de.keag.lager.panels.frame.entnahme.pane.details.anforderung;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ListView;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.View;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeStatus;
import de.keag.lager.panels.frame.entnahme.pane.liste.EntnahmeListView;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCView;

import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;


public class EntnahmeDetailsView extends DetailsView  {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelEntnahmeId = null;
	private JLabel jLabelErstellungsDatum = null;
	private JLabel jLabelStatus = null;
	private JLabel jLabelEntnehmer = null;
	private JTextField jTextFieldId = null;
	private JDateChooser JDateChooserErstellungsDatum = null;
	private JTextField jTextFieldEntnehmer = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JTextField jTextFieldStatus = null;
	private AbstractTableModel abstractTableModelEntnahmePosition = null;
	private JToolBar jToolBar = null;
	private JButton jButtonNeu = null;
	private JButton jButtonLoeschen = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	/**
	 * This is the default constructor
	 * @param entnahmeBlattController 
	 */
	public EntnahmeDetailsView(EntnahmeDetailsController entnahmeBlattController) {
		super();
		setController(entnahmeBlattController);//Controller merken
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
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
		gridBagConstraints32.gridy = 7;
		gridBagConstraints32.weightx = 1.0;
		gridBagConstraints32.anchor = GridBagConstraints.WEST;
		gridBagConstraints32.weighty = 0.0D;
		gridBagConstraints32.gridx = 0;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 3;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.gridy = 10;
		gridBagConstraints31.gridheight = 1;
		gridBagConstraints31.gridwidth = 3;
		gridBagConstraints31.weightx = 1.0;
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.fill = GridBagConstraints.BOTH;
		gridBagConstraints17.gridy = 8;
		gridBagConstraints17.weightx = 1.0;
		gridBagConstraints17.weighty = 300.0D;
		gridBagConstraints17.gridwidth = 4;
		gridBagConstraints17.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints17.gridx = 0;
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
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints10.gridy = 6;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.anchor = GridBagConstraints.WEST;
		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.fill = GridBagConstraints.NONE;
		gridBagConstraints7.gridy = 2;
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
		gridBagConstraints4.gridy = 6;
		jLabelEntnehmer = new JLabel();
		jLabelEntnehmer.setText("Entnehmer");
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 3;
		jLabelStatus = new JLabel();
		jLabelStatus.setText("Status");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints1.gridy = 2;
		jLabelErstellungsDatum = new JLabel();
		jLabelErstellungsDatum.setText("Erstellungsdatum");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelEntnahmeId = new JLabel();
		jLabelEntnahmeId.setText("Entnahme-ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelEntnahmeId, gridBagConstraints);
		this.add(jLabelErstellungsDatum, gridBagConstraints1);
		this.add(jLabelStatus, gridBagConstraints2);
		this.add(jLabelEntnehmer, gridBagConstraints4);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJDateChooserErstellungsDatum(), gridBagConstraints7);
		this.add(getJTextFieldStatus(), gridBagConstraints18);
		this.add(getJTextFieldEntnehmer(), gridBagConstraints10);
		this.add(getJScrollPane(), gridBagConstraints17);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				EntnahmeDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				EntnahmeDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
					try {
						EntnahmeDetailsView.this.uebernehmeDaten();
					} catch (LagerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() throws LagerException {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof EntnahmeBean){
					EntnahmeBean entnahmeBean = (EntnahmeBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt EntnahmeBean sein. Es sei denn, dass Objet ist neu(INSERT).
					if(!(!(entnahmeBean.getBeanDBStatus()!=BeanDBStatus.INSERT) && (entnahmeBean.getId().compareTo(Integer.decode(getJTextFieldId().getText()))!=0))){
//						String lhName = getJTextFieldHerstellerLieferant().getText().trim();
//						if(!lhName.equals("")){
//							//Name wurde angegeben.
//							try {
//     							if (entnahmeBean.getLhBean()!=null){
//	    							//Es gibt den "alten" Lieferanten
//		    						if(lhName.compareToIgnoreCase(entnahmeBean.getLhBean().getName())!=0){
//			    						//neuen Lieferanten holen
//				    					LhBean lhBean = ((EntnahmeDetailsController)getController()).getLhAnhandName(lhName); //Hole Lieferanten von DB
//					    				entnahmeBean.setLhBean(lhBean); //Der aktuelle Lieferant wird durch einen neuen ausgetauscht.
//						    		}else{
//							    		//Der Lieferant-Name hat sich nicht geändert.
//								    }
//							    }else{
//							    	//Lieferant war nicht vorhanden und ist neu angegeben.
//								    LhBean lhBean = ((EntnahmeDetailsController)getController()).getLhAnhandName(lhName);
//									entnahmeBean.setLhBean(lhBean); //Der aktuelle Lieferant wird durch einen neuen ausgetauscht.
//							    }
//							} catch (HerstellerLieferantLagerException e) {
//								getJTextFieldHerstellerLieferant().setText(lhName + " <Fehler>");
//								e.printStackTrace();
//							} catch (SQLException e) {
//								getJTextFieldHerstellerLieferant().setText(lhName + " <Fehler>");
//								e.printStackTrace();
//							} //Hole Lieferanten von DB
						}else{
						    //Der Name wurde gelöscht, oder nicht angegeben.
							entnahmeBean.setLhBean(null); //Lieferant ist nicht angegeben.
						}
					}else{
						Log.log().severe("Fehler4");
					}
				}else{
					Log.log().severe("Fehler3");
				}
			}else{
				Log.log().severe("Fehler2");
			}
	
//			Log.log().severe("Fehler");
//			}else{}
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

	/**
	 * This method initializes jTextFieldEntnehmer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEntnehmer() {
		if (jTextFieldEntnehmer == null) {
			jTextFieldEntnehmer = new JTextField();
			jTextFieldEntnehmer.setPreferredSize(new Dimension(100, 20));
			jTextFieldEntnehmer.setEditable(false);
		}
		return jTextFieldEntnehmer;
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
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setModel(getAbstractTableModelEntnahmePosition());
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

	@Override
	public DetailsController getController() {
		return detailsController;
	}

	@Override
	protected void setController(DetailsController detailsController) {
		this.detailsController = detailsController;
	}

	@Override
	public void zeichneDich(ModelKnotenBean entnahmeModelBean) {
		if(entnahmeModelBean!=null){
			if (entnahmeModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ENTNAHME){
				getJComboBoxFehler().removeAllItems();
				setModelBean(entnahmeModelBean);//merken
				EntnahmeBean entnahmeBean = (EntnahmeBean) entnahmeModelBean.getIBean();
				getJTextFieldId().setText(entnahmeBean.getId().toString());
				getJTextFieldId().setEnabled(false);
				getJTextFieldStatus().setText(EntnahmeStatus.JavaToView(entnahmeBean.getStatus()));
//				if (entnahmeBean.getLhBean()!=null){
//					getJTextFieldHerstellerLieferant().setText(entnahmeBean.getLhBean().getName());
//				}else{
//					getJTextFieldHerstellerLieferant().setText("");
//				}
				if (entnahmeBean.getEntnehmerBenutzerBean()!=null){
					getJTextFieldEntnehmer().setText(entnahmeBean.getEntnehmerBenutzerBean().getName());
				}else{
					getJTextFieldEntnehmer().setText("");
				}
				
//				getJTextFieldEmail().setText(entnahmeBean.getEmail());
				
				//Erstellungsdatum wird angezeigt.
				getJDateChooserErstellungsDatum().setDate(entnahmeBean.getErstellungsDatum());
				getJDateChooserErstellungsDatum().setEnabled(false);
				
				//Fehler anzeigen.
				entnahmeBean.pruefeEigeneDaten();
				for(int i=0; i<entnahmeBean.getFehlerList().size();i++){
					Fehler fehler = entnahmeBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				if (entnahmeBean.getStatus()==EntnahmeStatus.OFFEN){
					this.setEnabled(true);
				}else{
					this.setEnabled(false);
				}
				
				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
//				System.out.println("zeichneDich() EntnahmeDetailsView");
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
					EntnahmeDetailsView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
				}
			});
		}
		return jComboBoxFehler;
	}

//	protected void setMatchCodeLieferanten(LhBean lhBean) {
//		getJTextFieldHerstellerLieferant().setText(lhBean.getName());
//		getJTextFieldHerstellerLieferant().requestFocusInWindow();
//	}

	/**
	 * This method initializes jTextFieldStatus	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldStatus() {
		if (jTextFieldStatus == null) {
			jTextFieldStatus = new JTextField();
			jTextFieldStatus.setEditable(false);
		}
		return jTextFieldStatus;
	}

	private AbstractTableModel getAbstractTableModelEntnahmePosition() {
		if(abstractTableModelEntnahmePosition==null){
			abstractTableModelEntnahmePosition = new AbstractTableModel() {
				private String[] columnNames = {"","id","KEG-Nummer","Bezeichnung","Typ","Menge","ME","Position","Ebene","Saeule","Zeile","Halle","Status"};

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
					if (modelKnotenBean.getIBean() instanceof EntnahmePosBean){
						EntnahmePosBean entnahmePosBean = (EntnahmePosBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : return new Integer(row+1).toString(); 
							case 1 : return entnahmePosBean.getId().toString(); 
							case 2 : if (entnahmePosBean.getArtikelBean()==null){
								return "";  
							}else{
								return entnahmePosBean.getArtikelBean().getKeg_nr().toString();
							}
							case 3 : if (entnahmePosBean.getArtikelBean()==null){
								return "";  
							}else{
								return entnahmePosBean.getArtikelBean().getBezeichnung().toString();
							}
							case 4 : if (entnahmePosBean.getArtikelBean()==null){
								return "";  
							}else{
								return entnahmePosBean.getArtikelBean().getTyp().toString();
							}
							case 5 : return entnahmePosBean.getMenge().toString(); 
							case 6 :  if (entnahmePosBean.getArtikelBean()==null){
								return "";  
							}else{
								if (entnahmePosBean.getArtikelBean().getMengenEinheitBean()==null){
									return "";
								}else{
									return entnahmePosBean.getArtikelBean().getMengenEinheitBean().getName();
								}
							} 
							case 7 :  if (entnahmePosBean.getPositionBean()==null){
								return "";  
							}else{
								if (entnahmePosBean.getPositionBean().getNummer()==null){
									return "";
								}else{
									return entnahmePosBean.getPositionBean().getNummer().toString();
								}
							}
							case 8 :  if (entnahmePosBean.getEbeneBean()==null){
								return "";  
							}else{
								if (entnahmePosBean.getEbeneBean().getNummer()==null){
									return "";
								}else{
									return entnahmePosBean.getEbeneBean().getNummer().toString();
								}
							}
							case 9 :  if (entnahmePosBean.getSaeuleBean().getNummer()==null){
								return "";  
							}else{
								if (entnahmePosBean.getSaeuleBean().getNummer()==null){
									return "";
								}else{
									return entnahmePosBean.getSaeuleBean().getNummer().toString();
								}
							}
							case 10 :  if (entnahmePosBean.getZeileBean()==null){
								return "";  
							}else{
								if (entnahmePosBean.getZeileBean().getNummer()==null){
									return "";
								}else{
									return entnahmePosBean.getZeileBean().getNummer().toString();
								}
							}
							case 11 :  if (entnahmePosBean.getHalleBean()==null){
								return "";  
							}else{
								if (entnahmePosBean.getHalleBean().getName()==null){
									return "";
								}else{
									return entnahmePosBean.getHalleBean().getName().toString();
								}
							}

//							case 7 : return entnahmePosBean.getLiefertermin().toString(); 
							case 12 : return entnahmePosBean.getStatus().toString(); 
							case 999 : return entnahmePosBean; 
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
					     return getModelBean().getKinderList().size();
					 }
				}
				
				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModelEntnahmePosition;
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
						getController().erstelleNeuenSatz(ModelKnotenTyp.ENTNAHMENSPOSITION);
					}
				}
			});
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
							EntnahmePosBean entnahmePosBean = (EntnahmePosBean) getJTable().getModel().getValueAt(getJTable().getSelectedRow(), 999);
							getController().loeschePosition(entnahmePosBean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
						}
					}

				}
			});
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


//	@Override
//	public void setStandardFocusPosition() {
//		getJButtonMatchCodeLieferant().requestFocus();
//	}

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
//		getJButtonMatchCodeLieferant().setEnabled(enabled);
		getJButtonNeu().setEnabled(enabled);
		getJButtonLoeschen().setEnabled(enabled);
//		getJTextFieldEmail().setEditable(enabled);
//		getJButtonMatchCodeEmail().setEnabled(enabled);
	}

	//setzen der Email aus dem Matchcode-Fenster
	public void setMatchCodeEmail(EmailBean emailBean,String email) {
		if (email!=null){
			EntnahmeBean entnahmeBean = (EntnahmeBean)getModelBean().getIBean();
			entnahmeBean.setEmail(email);
		}
		if(emailBean!=null){
			EntnahmeBean entnahmeBean = (EntnahmeBean)getModelBean().getIBean();
			entnahmeBean.setEmail(emailBean.getEmail());
//			getJTextFieldEmail().setText(entnahmeBean.getEmail());
		}
	}

	@Override
	public void setStandardFocusPosition() {
		// TODO Auto-generated method stub
		
	}



	
}
