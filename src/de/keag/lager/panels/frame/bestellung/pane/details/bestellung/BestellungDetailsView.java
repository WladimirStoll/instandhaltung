package de.keag.lager.panels.frame.bestellung.pane.details.bestellung;

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
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellung.BestellungStatus;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.bestellung.BestellungPosBean;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;


public class BestellungDetailsView extends DetailsView  {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelBestellungId = null;
	private JLabel jLabelErstellungsDatum = null;
	private JLabel jLabelStatus = null;
	private JLabel jLabelLieferant = null;
	private JLabel jLabelAbsender = null;
	private JTextField jTextFieldId = null;
	private JDateChooser JDateChooserErstellungsDatum = null;
	private JTextField jTextFieldHerstellerLieferant = null;
	private JTextField jTextFieldAbsender = null;
	private JButton jButtonMatchCodeLieferant = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel jLabelHerstellerLieferantName = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JTextField jTextFieldStatus = null;
	private AbstractTableModel abstractTableModelBaPosition = null;
	private JToolBar jToolBar = null;
	private JButton jButtonNeu = null;
	private JButton jButtonLoeschen = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JLabel jLabelEMail = null;
	private JTextField jTextFieldEmail = null;
	private JButton jButtonMatchCodeEmail = null;
	private JLabel jLabelAnforderungsId = null;
	private JTextField jTextFieldAnforderungsID = null;
	/**
	 * This is the default constructor
	 * @param baBlattController 
	 */
	public BestellungDetailsView(BestellungDetailsController baBlattController) {
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
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints23.gridy = 7;
		gridBagConstraints23.weightx = 1.0;
		gridBagConstraints23.anchor = GridBagConstraints.WEST;
		gridBagConstraints23.gridx = 1;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.gridx = 0;
		gridBagConstraints13.anchor = GridBagConstraints.EAST;
		gridBagConstraints13.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints13.gridy = 7;
		jLabelAnforderungsId = new JLabel();
		jLabelAnforderungsId.setText("Anforderung");
		GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
		gridBagConstraints33.gridx = 2;
		gridBagConstraints33.gridy = 5;
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints22.gridy = 5;
		gridBagConstraints22.weightx = 1.0;
		gridBagConstraints22.anchor = GridBagConstraints.WEST;
		gridBagConstraints22.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 5;
		jLabelEMail = new JLabel();
		jLabelEMail.setText("E-Mail");
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
		gridBagConstraints32.gridy = 8;
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
		gridBagConstraints31.gridy = 11;
		gridBagConstraints31.gridheight = 1;
		gridBagConstraints31.gridwidth = 3;
		gridBagConstraints31.weightx = 1.0;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 3;
		gridBagConstraints21.gridy = 4;
		jLabelHerstellerLieferantName = new JLabel();
		jLabelHerstellerLieferantName.setText("");
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.fill = GridBagConstraints.BOTH;
		gridBagConstraints17.gridy = 9;
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
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 2;
		gridBagConstraints12.anchor = GridBagConstraints.WEST;
		gridBagConstraints12.gridy = 4;
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints10.gridy = 6;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.anchor = GridBagConstraints.WEST;
		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.gridy = 4;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.anchor = GridBagConstraints.WEST;
		gridBagConstraints9.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.fill = GridBagConstraints.NONE;
		gridBagConstraints7.gridy = 2;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
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
		jLabelAbsender = new JLabel();
		jLabelAbsender.setText("Absender");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.anchor = GridBagConstraints.EAST;
		gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints3.gridy = 4;
		jLabelLieferant = new JLabel();
		jLabelLieferant.setText("Lieferant");
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
		jLabelBestellungId = new JLabel();
		jLabelBestellungId.setText("Bestellung-ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.setPreferredSize(new Dimension(20, 20));
		this.add(jLabelBestellungId, gridBagConstraints);
		this.add(jLabelErstellungsDatum, gridBagConstraints1);
		this.add(jLabelStatus, gridBagConstraints2);
		this.add(jLabelLieferant, gridBagConstraints3);
		this.add(jLabelAbsender, gridBagConstraints4);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJDateChooserErstellungsDatum(), gridBagConstraints7);
		this.add(getJTextFieldHerstellerLieferant(), gridBagConstraints9);
		this.add(getJTextFieldAbsender(), gridBagConstraints10);
		this.add(getJButtonMatchCodeLieferant(), gridBagConstraints12);
		this.add(getJScrollPane(), gridBagConstraints17);
		this.add(jLabelHerstellerLieferantName, gridBagConstraints21);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJTextFieldStatus(), gridBagConstraints18);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(jLabelEMail, gridBagConstraints11);
		this.add(getJTextFieldEmail(), gridBagConstraints22);
		this.add(getJButtonMatchCodeEmail(), gridBagConstraints33);
		this.add(jLabelAnforderungsId, gridBagConstraints13);
		this.add(getJTextFieldAnforderungsID(), gridBagConstraints23);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				BestellungDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				BestellungDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
					BestellungDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof BestellungBean){
					BestellungBean baBean = (BestellungBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt BaBean sein. Es sei denn, dass Objet ist neu(INSERT).
					if(!(!(baBean.getBeanDBStatus()!=BeanDBStatus.INSERT) && (baBean.getId().compareTo(Integer.decode(getJTextFieldId().getText()))!=0))){
						String lhName = getJTextFieldHerstellerLieferant().getText().trim();
						if(!lhName.equals("")){
							//Name wurde angegeben.
							try {
     							if (baBean.getLhBean()!=null){
	    							//Es gibt den "alten" Lieferanten
		    						if(lhName.compareToIgnoreCase(baBean.getLhBean().getName())!=0){
			    						//neuen Lieferanten holen
				    					LhBean lhBean = ((BestellungDetailsController)getController()).getLhAnhandName(lhName); //Hole Lieferanten von DB
					    				baBean.setLhBean(lhBean); //Der aktuelle Lieferant wird durch einen neuen ausgetauscht.
						    		}else{
							    		//Der Lieferant-Name hat sich nicht geändert.
								    }
							    }else{
							    	//Lieferant war nicht vorhanden und ist neu angegeben.
								    LhBean lhBean = ((BestellungDetailsController)getController()).getLhAnhandName(lhName);
									baBean.setLhBean(lhBean); //Der aktuelle Lieferant wird durch einen neuen ausgetauscht.
							    }
							} catch (LagerException e) {
								getJTextFieldHerstellerLieferant().setText(lhName + " <Fehler>");
								e.printStackTrace();
							} catch (SQLException e) {
								getJTextFieldHerstellerLieferant().setText(lhName + " <Fehler>");
								e.printStackTrace();
							} //Hole Lieferanten von DB
						}else{
						    //Der Name wurde gelöscht, oder nicht angegeben.
							baBean.setLhBean(null); //Lieferant ist nicht angegeben.
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

	/**
	 * This method initializes jTextFieldHerstellerLieferant	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldHerstellerLieferant() {
		if (jTextFieldHerstellerLieferant == null) {
			jTextFieldHerstellerLieferant = new JTextField();
			jTextFieldHerstellerLieferant.setPreferredSize(new Dimension(100, 20));
			jTextFieldHerstellerLieferant.setEditable(false);
			jTextFieldHerstellerLieferant
					.addFocusListener(new java.awt.event.FocusAdapter() {
						public void focusLost(java.awt.event.FocusEvent e) {
							BestellungDetailsView.this.uebernehmeDaten();
							Log.log().finest("focusLost()");
						}
					});
		}
		return jTextFieldHerstellerLieferant;
	}

	/**
	 * This method initializes jTextFieldAbsender	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldAbsender() {
		if (jTextFieldAbsender == null) {
			jTextFieldAbsender = new JTextField();
			jTextFieldAbsender.setPreferredSize(new Dimension(100, 20));
			jTextFieldAbsender.setEditable(false);
		}
		return jTextFieldAbsender;
	}

	/**
	 * This method initializes jButtonMatchCodeLieferant	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeLieferant() {
		if (jButtonMatchCodeLieferant == null) {
			jButtonMatchCodeLieferant = new JButton();
			jButtonMatchCodeLieferant.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeLieferant.setText("...");
			jButtonMatchCodeLieferant
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((BestellungDetailsController)getController()).holeLieferanten();
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
		return jButtonMatchCodeLieferant;
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
			jTable.setModel(getAbstractTableModelBaPosition());
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
	public void zeichneDich(ModelKnotenBean baModelBean, IModel iModel) {
		if(baModelBean!=null){
			if (baModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTELLUNG){
				getJComboBoxFehler().removeAllItems();
				setModelBean(baModelBean);//merken
				BestellungBean baBean = (BestellungBean) baModelBean.getIBean();
				getJTextFieldId().setText(baBean.getId().toString());
				getJTextFieldId().setEnabled(false);
				getJTextFieldStatus().setText(BestellungStatus.JavaToView(baBean.getStatus()));
				if (baBean.getLhBean()!=null){
					getJTextFieldHerstellerLieferant().setText(baBean.getLhBean().getName());
				}else{
					getJTextFieldHerstellerLieferant().setText("");
				}
				if (baBean.getAbsenderBenutzerBean()!=null){
					getJTextFieldAbsender().setText(baBean.getAbsenderBenutzerBean().getName());
				}else{
					getJTextFieldAbsender().setText("");
				}
				
				getJTextFieldEmail().setText(baBean.getEmail());
				
				getJTextFieldAnforderungsID().setText(
						baBean.getBaBean().getId() 
						+ " " 
						+ baBean.getBaBean().getLhBean().getName()
						+ " " + baBean.getBaBean().getAbsenderBenutzerBean().getVorname()
						+ " " + baBean.getBaBean().getAbsenderBenutzerBean().getName());
				
				//Erstellungsdatum wird angezeigt.
				getJDateChooserErstellungsDatum().setDate(baBean.getErstellungsDatum());
				getJDateChooserErstellungsDatum().setEnabled(false);
				
				//Fehler anzeigen.
				baBean.pruefeEigeneDaten();
				for(int i=0; i<baBean.getFehlerList().size();i++){
					Fehler fehler = baBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				if (baBean.getStatus()==BestellungStatus.OFFEN){
					this.setEnabled(true);
				}else{
					this.setEnabled(false);
				}
				
				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
//				System.out.println("zeichneDich() BaDetailsView");
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
					BestellungDetailsView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
				}
			});
		}
		return jComboBoxFehler;
	}

	protected void setMatchCodeLieferanten(LhBean lhBean) {
		getJTextFieldHerstellerLieferant().setText(lhBean.getName());
		getJTextFieldHerstellerLieferant().requestFocusInWindow();
	}

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

	private AbstractTableModel getAbstractTableModelBaPosition() {
		if(abstractTableModelBaPosition==null){
			abstractTableModelBaPosition = new AbstractTableModel() {
				private String[] columnNames = {"","id","KEG-Nummer","Bezeichnung","Typ","Menge","ME","Liefertermin","Status"};

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
					if (modelKnotenBean.getIBean() instanceof BestellungPosBean){
						BestellungPosBean baPosBean = (BestellungPosBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : return new Integer(row+1).toString(); 
							case 1 : return baPosBean.getId().toString(); 
							case 2 : if (baPosBean.getArtikelBean()==null){
								return "";  
							}else{
								return baPosBean.getArtikelBean().getKeg_nr().toString();
							}
							case 3 : if (baPosBean.getArtikelBean()==null){
								return "";  
							}else{
								return baPosBean.getArtikelBean().getBezeichnung().toString();
							}
							case 4 : if (baPosBean.getArtikelBean()==null){
								return "";  
							}else{
								return baPosBean.getArtikelBean().getTyp().toString();
							}
							case 5 : return baPosBean.getBestellmenge().toString(); 
							case 6 :  if (baPosBean.getArtikelBean()==null){
								return "";  
							}else{
								if (baPosBean.getArtikelBean().getMengenEinheitBean()==null){
									return "";
								}else{
									return baPosBean.getArtikelBean().getMengenEinheitBean().getName();
								}
							} 
							case 7 : return baPosBean.getLiefertermin().toString(); 
							case 8 : return baPosBean.getStatus().toString(); 
							case 999 : return baPosBean; 
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
		return abstractTableModelBaPosition;
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
						getController().erstelleNeuenSatz(ModelKnotenTyp.BESTELLUNGSPOSITION);
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
							BestellungPosBean baPosBean = (BestellungPosBean) getJTable().getModel().getValueAt(getJTable().getSelectedRow(), 999);
							getController().loeschePosition(baPosBean);
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
		getJButtonMatchCodeLieferant().requestFocus();
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
		getJButtonMatchCodeLieferant().setEnabled(enabled);
		getJButtonNeu().setEnabled(enabled);
		getJButtonLoeschen().setEnabled(enabled);
		getJTextFieldEmail().setEditable(enabled);
		getJButtonMatchCodeEmail().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEmail() {
		if (jTextFieldEmail == null) {
			jTextFieldEmail = new JTextField();
			jTextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					String email = BestellungDetailsView.this.getJTextFieldEmail().getText();
					setMatchCodeEmail(null,email);
				}
			});
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
							((BestellungDetailsController)getController()).holeEMail();
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
			BestellungBean baBean = (BestellungBean)getModelBean().getIBean();
			baBean.setEmail(email);
		}
		if(emailBean!=null){
			BestellungBean baBean = (BestellungBean)getModelBean().getIBean();
			baBean.setEmail(emailBean.getEmail());
			getJTextFieldEmail().setText(baBean.getEmail());
		}
	}

	/**
	 * This method initializes jTextFieldAnforderungsID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldAnforderungsID() {
		if (jTextFieldAnforderungsID == null) {
			jTextFieldAnforderungsID = new JTextField();
			jTextFieldAnforderungsID.setPreferredSize(new Dimension(100, 20));
			jTextFieldAnforderungsID.setEditable(false);
		}
		return jTextFieldAnforderungsID;
	}



	
}
