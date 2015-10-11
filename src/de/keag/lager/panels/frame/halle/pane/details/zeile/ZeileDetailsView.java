package de.keag.lager.panels.frame.halle.pane.details.zeile;

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
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;
import de.keag.lager.panels.frame.halle.pane.details.halle.HalleDetailsController;
import de.keag.lager.panels.frame.halle.pane.suche.HalleSuchController;
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
import java.awt.ComponentOrientation;


public class ZeileDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelZeile = null;
	private JFormattedTextField jFormattedTextFieldZeile = null;
	private JLabel jLabelAbteilung = null;
	private JLabel jLabelRechtsUnten = null;
	private JLabel jLabel1 = null;
	private JFormattedTextField jFormattedTextFieldAbteilung = null;
	private JButton jButtonMatchCodeAbteilung = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private AbstractTableModel abstractTableModel;  //  @jve:decl-index=0:
	private JToolBar jToolBar = null;
	private JButton jButtonNeu = null;
	private JButton jButtonLoeschen = null;
	private JButton jButtonBestandsListe = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosZugriffsrechtDetailsController 
	 */
	public ZeileDetailsView(DetailsController BenutzerPosZugriffsrechtDetailsController) {
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
		gridBagConstraints12.gridx = 1;
		gridBagConstraints12.anchor = GridBagConstraints.WEST;
		gridBagConstraints12.insets = new Insets(0, 5, 0, 0);
		gridBagConstraints12.gridy = 13;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints1.gridy = 15;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.anchor = GridBagConstraints.WEST;
		gridBagConstraints1.gridx = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 16;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 100.0D;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.gridx = 0;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 3;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.gridy = 12;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints6.gridy = 12;
		gridBagConstraints6.weightx = 1.0;
		gridBagConstraints6.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints6.insets = new Insets(0, 5, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.weighty = 50.0D;
		gridBagConstraints5.gridy = 10;
		jLabel1 = new JLabel();
		jLabel1.setText("   ");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 3;
		gridBagConstraints4.weighty = 50.0D;
		gridBagConstraints4.weightx = 1.0D;
		gridBagConstraints4.gridy = 14;
		jLabelRechtsUnten = new JLabel();
		jLabelRechtsUnten.setText("  ");
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.anchor = GridBagConstraints.EAST;
		gridBagConstraints31.weighty = 1.0D;
		gridBagConstraints31.gridy = 12;
		jLabelAbteilung = new JLabel();
		jLabelAbteilung.setText("Abteilung");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 11;
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
		gridBagConstraints11.gridy = 11;
		jLabelZeile = new JLabel();
		jLabelZeile.setText("Zeile");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 28;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(jLabelZeile, gridBagConstraints11);
		this.add(getJFormattedTextFieldZeile(), gridBagConstraints3);
		this.add(jLabelAbteilung, gridBagConstraints31);
		this.add(jLabelRechtsUnten, gridBagConstraints4);
		this.add(jLabel1, gridBagConstraints5);
		this.add(getJFormattedTextFieldAbteilung(), gridBagConstraints6);
		this.add(getJButtonMatchCodeAbteilung(), gridBagConstraints7);
		this.add(getJScrollPane(), gridBagConstraints);
		this.add(getJToolBar(), gridBagConstraints1);
		this.add(getJButtonBestandsListe(), gridBagConstraints12);
	}

	@Override
	public void zeichneDich(ModelKnotenBean zeileModelKnotenBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(zeileModelKnotenBean!=null){
			if (zeileModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ZEILE){
				setModelBean(zeileModelKnotenBean);//merken
				ZeileBean zeileBean = (ZeileBean) zeileModelKnotenBean.getIBean();
				//id anzeigen

				jFormattedTextFieldZeile.removePropertyChangeListener("value", this);
				getJFormattedTextFieldZeile().setValue(zeileBean.getNummer());
				getJFormattedTextFieldZeile().setEnabled(true);
				jFormattedTextFieldZeile.addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldAbteilung().setText(zeileBean.getAbteilungBean().getAbteilungName());
				getJFormattedTextFieldAbteilung().setEditable(false);
				
				//kostenstelle anzeigen
//				if (zeileBean.getZugriffsrecht()!=null){
//					getJTextFieldZugriffsrecht().setText(zeileBean.getZugriffsrecht().getZugriffsrechtName());
//				}else{
//					getJTextFieldZugriffsrecht().setText(null);
//				}
				
				//Fehler anzeigen.
				for(int i=0; i<zeileModelKnotenBean.getFehlerList().size();i++){
					Fehler fehler = zeileModelKnotenBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				//Tabelle aktualisieren
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


	
	public void setZeile(ZeileBean zugriffsrechtBean) {
		//Das Model wird geändert.
		ZeileBean ZeileBean = ((ZeileBean)modelKnotenBean.getIBean());
//		ZeileBean.setZeile(zugriffsrechtBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJFormattedTextFieldZeile()){
			leseAusgetJFormattedTextFieldZeile();
		}
		getController().ausgewaehlterKnotenIstGeandert();
		
	}

	private void leseAusgetJFormattedTextFieldZeile() {
		ZeileBean bean = (ZeileBean)getModelBean().getIBean();
		Integer wert ;
		try{
			if(getJFormattedTextFieldZeile().getValue()!=null){
				wert = ((Number)getJFormattedTextFieldZeile().getValue()).intValue();
				bean.setNummer(wert);
			}
		}catch(NumberFormatException ex){
		}
	}

	@Override
	public void setStandardFocusPosition() {
		getJFormattedTextFieldZeile().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJFormattedTextFieldZeile().setEditable(enabled);
//		getJButtonMatchCodeZugriffsrecht().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldZugriffsrecht	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldZeile() {
		if (jFormattedTextFieldZeile == null) {
			jFormattedTextFieldZeile = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldZeile.setInputVerifier(LagerFormate.getInputVerifier());
//			jFormattedTextFieldZeile.addPropertyChangeListener("value", this);
//			
//			jFormattedTextFieldZeile = new JTextField();
//			jFormattedTextFieldZeile.setEditable(false);
		}
		return jFormattedTextFieldZeile;
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
	 * This method initializes jFormattedTextFieldAbteilung	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldAbteilung() {
		if (jFormattedTextFieldAbteilung == null) {
			jFormattedTextFieldAbteilung = new JFormattedTextField();
			jFormattedTextFieldAbteilung.setEditable(false);
		}
		return jFormattedTextFieldAbteilung;
	}

	/**
	 * This method initializes jButtonMatchCodeAbteilung	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeAbteilung() {
		if (jButtonMatchCodeAbteilung == null) {
			jButtonMatchCodeAbteilung = new JButton();
			jButtonMatchCodeAbteilung.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeAbteilung.setText("...");
			jButtonMatchCodeAbteilung
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((ZeileDetailsController)getController()).holeAbteilung();
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

	public void setAbteilung(AbteilungBean abteilungBean) {
		//Das Model wird geändert.
		ZeileBean bean = ((ZeileBean)modelKnotenBean.getIBean());
		bean.setAbteilungBean(abteilungBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
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

	private AbstractTableModel getAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = {"Säule"};

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
					if (modelKnotenBean.getIBean() instanceof SaeuleBean){
						SaeuleBean bean = (SaeuleBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								return bean.getNummer();
							}
							case Konstanten.COLUMN_COUNT_BIG : return bean;  
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
							 if (getModelBean().getKinderList().get(i) instanceof SaeuleModelKnotenBean){
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
						getController().erstelleNeuenSatz(ModelKnotenTyp.SAEULE);
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
							SaeuleBean bean = (SaeuleBean) getJTable().getModel().getValueAt(getJTable().getSelectedRow(), Konstanten.COLUMN_COUNT_BIG);
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
							ZeileBean bean = ((ZeileBean)modelKnotenBean.getIBean());
							((ZeileDetailsController)getController()).druckeInventurListe(bean);
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
	
	
}  //  @jve:decl-index=0:visual-constraint="49,1"