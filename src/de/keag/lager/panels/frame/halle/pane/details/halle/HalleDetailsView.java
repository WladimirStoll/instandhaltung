package de.keag.lager.panels.frame.halle.pane.details.halle;

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
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageModelKnotenBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.zeile.model.ZeileModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

import de.keag.lager.panels.frame.email.EmailBean;



import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class HalleDetailsView extends DetailsView implements IHalleDetailsBeobachter, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelId = null;
	private JLabel jLabelName = null;
	private JTextField jTextFieldId = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;

	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JToolBar jToolBar = null;
	private JButton jButtonLoeschen = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JFormattedTextField jTextFieldName = null;
	private JFormattedTextField jTextFieldNummer = null;
	private JButton jButtonNeu = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private AbstractTableModel abstractTableModel;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModelZeile;  //  @jve:decl-index=0:
	private JToolBar jToolBar2 = null;
	private JButton jButtonNeuZeile = null;
	private JButton jButtonLoeschenZeile = null;
	private JScrollPane jScrollPaneZeile = null;
	private JTable jTableZeilen = null;
	private JLabel jLabelRechtsUnten = null;
	private JButton jButton = null;
	private JLabel jLabelNummer = null;
	/**
	 * This is the default constructor
	 * @param controller 
	 */
	public HalleDetailsView(HalleDetailsController controller) {
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
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints22.gridy = 6;
		gridBagConstraints22.weightx = 1.0;
		gridBagConstraints22.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints12.gridy = 6;
		jLabelNummer = new JLabel();
		jLabelNummer.setText("Nummer");
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 1;
		gridBagConstraints11.anchor = GridBagConstraints.CENTER;
		gridBagConstraints11.gridy = 7;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 2;
		gridBagConstraints1.weightx = 1.0D;
		gridBagConstraints1.gridy = 5;
		jLabelRechtsUnten = new JLabel();
		jLabelRechtsUnten.setText("     ");
		GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
		gridBagConstraints34.fill = GridBagConstraints.BOTH;
		gridBagConstraints34.gridy = 16;
		gridBagConstraints34.weightx = 1.0;
		gridBagConstraints34.weighty = 1.0;
		gridBagConstraints34.gridwidth = 3;
		gridBagConstraints34.gridx = 0;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints21.gridy = 15;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.anchor = GridBagConstraints.WEST;
		gridBagConstraints21.gridx = 0;
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.fill = GridBagConstraints.BOTH;
		gridBagConstraints51.gridy = 14;
		gridBagConstraints51.weightx = 1.0;
		gridBagConstraints51.weighty = 1.0;
		gridBagConstraints51.gridwidth = 3;
		gridBagConstraints51.gridx = 0;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 5;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 1;
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
		gridBagConstraints32.gridy = 13;
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
		gridBagConstraints31.gridy = 17;
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
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 5;
		jLabelName = new JLabel();
		jLabelName.setText("Name");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelId = new JLabel();
		jLabelId.setText("ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelId, gridBagConstraints);
		this.add(jLabelName, gridBagConstraints2);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJTextFieldName(), gridBagConstraints8);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(getJScrollPane(), gridBagConstraints51);
		this.add(getJToolBar2(), gridBagConstraints21);
		this.add(getJScrollPaneZugriffsrechte(), gridBagConstraints34);
		this.add(jLabelRechtsUnten, gridBagConstraints1);
		this.add(getJButton(), gridBagConstraints11);
		this.add(jLabelNummer, gridBagConstraints12);
		this.add(getJTextFieldNummer(), gridBagConstraints22);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				HalleDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				HalleDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
				HalleDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof HalleBean){
					HalleBean halleBean = (HalleBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt HalleBean sein. Es sei denn, dass Objet ist neu(INSERT).
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
			jTextFieldId.setPreferredSize(new Dimension(40, 20));
		}
		return jTextFieldId;
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		setzeHintergrund();
		if(modelKnotenBean!=null){
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.HALLE){
				getJComboBoxFehler().removeAllItems();
				setModelBean(modelKnotenBean);//merken
				HalleBean halleBean = (HalleBean) modelKnotenBean.getIBean();
				getJTextFieldId().setEnabled(false);
				getJTextFieldId().setText(halleBean.getId().toString());
				getJTextFieldName().setText(halleBean.getName());
				
				getJTextFieldNummer().removeKeyListener(this);
				// muss weg
				getJTextFieldNummer().removePropertyChangeListener(
						"value", this);
				getJTextFieldNummer().setValue(halleBean.getNummer());
				// muss wiederhergestellt werden
				getJTextFieldNummer().addPropertyChangeListener("value",
						this);
				getJTextFieldNummer().addKeyListener(this);
				
				
//				getJTextFieldVorname().setText(halleBean.getVorname());
//				getJTextFieldLoginName().setText(halleBean.getLoginName());
//				getJTextFieldPasswort().setText(halleBean.getPassword());
//				getJTextFieldEmail().setText(halleBean.getEmail());
				
		//		//Erstellungsdatum wird angezeigt.
		//		getJDateChooserErstellungsDatum().setDate(halleBean.getErstellungsDatum());
		//		getJDateChooserErstellungsDatum().setEnabled(false);
				
				//Fehler anzeigen.
//				halleBean.pruefeEigeneDaten();
				for(int i=0; i<halleBean.getFehlerList().size();i++){
					Fehler fehler = halleBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				
				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
//				System.out.println("zeichneDich() HalleDetailsView");
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
				private String[] columnNames = {"Etage"};

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
					if (modelKnotenBean.getIBean() instanceof EtageBean){
						EtageBean etageBean = 
							(EtageBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								return etageBean.getName();
							}
							case 999 : return etageBean; 
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
							 if (getModelBean().getKinderList().get(i) instanceof EtageModelKnotenBean){
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
	
	private AbstractTableModel getAbstractTableModelZeile() {
		if(abstractTableModelZeile==null){
			abstractTableModelZeile = new AbstractTableModel() {
				private String[] columnNames = {"Zeile"};

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
						 if (getModelBean().getKinderList().get(i) instanceof ZeileModelKnotenBean){
							 zugriffsrechtAbZeile = i;
						 }
					 }
					
					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(zugriffsrechtAbZeile+row);
					if (modelKnotenBean.getIBean() instanceof ZeileBean){
						ZeileBean zeileBean = 
							(ZeileBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
									return zeileBean.getNummer();
							}
							case 999 : return zeileBean; 
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
							 if (getModelBean().getKinderList().get(i) instanceof ZeileModelKnotenBean){
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
		return abstractTableModelZeile;
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
							EtageBean bean = 
								(EtageBean) getJTable().
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
//		getJButtonMatchCodeEmail().setEnabled(enabled);
		getJButtonNeu().setEnabled(enabled);
		getJButtonLoeschen().setEnabled(enabled);
//		getJTextFieldEmail().setEditable(enabled);
//		getJButtonMatchCodeEmail().setEnabled(enabled);
		getJTextFieldName().setEditable(enabled);
		getJTextFieldNummer().setEditable(enabled);
//		getJTextFieldLoginName().setEditable(enabled);
//		getJTextFieldPasswort().setEditable(enabled);
//		getJTextFieldVorname().setEditable(enabled);
//		getJDateChooserErstellungsDatum().setEnabled(enabled);
		getJTable().setEnabled(enabled);
	}

	//setzen der Email aus dem Matchcode-Fenster
	public void setMatchCodeEmail(EmailBean emailBean,String email) {
//		if (email!=null){
//			HalleBean halleBean = (HalleBean)getModelBean().getIBean();
//			halleBean.setEmail(email);
//		}
//		if(emailBean!=null){
//			HalleBean halleBean = (HalleBean)getModelBean().getIBean();
//			halleBean.setEmail(emailBean.getEmail());
//			getJTextFieldEmail().setText(halleBean.getEmail());
//		}
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
	
	private JFormattedTextField getJTextFieldNummer() {
		if (jTextFieldNummer == null) {
			jTextFieldNummer = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 99));
			jTextFieldNummer.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldNummer.addPropertyChangeListener("value", this);
		}
		return jTextFieldNummer;
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
						getController().erstelleNeuenSatz(ModelKnotenTyp.ETAGE);
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
//		HalleBean halleBean = (HalleBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldEmail().getValue() != null) {
//			wert = getJTextFieldEmail().getValue().toString();
//			halleBean.setEmail(wert);
//		}
	}

	private void leseAusgetJTextFieldPasswort() {
//		HalleBean halleBean = (HalleBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldPasswort().getValue() != null) {
//			wert = getJTextFieldPasswort().getValue().toString();
//			halleBean.setPassword(wert);
//		}
	}

	private void leseAusgetJTextFieldLoginName() {
//		HalleBean halleBean = (HalleBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldLoginName().getValue() != null) {
//			wert = getJTextFieldLoginName().getValue().toString();
//			halleBean.setLoginName(wert);
//		}
	}

	private void leseAusgetJTextFieldVorname() {
//		HalleBean halleBean = (HalleBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldVorname().getValue() != null) {
//			wert = getJTextFieldVorname().getValue().toString();
//			halleBean.setVorname(wert);
//		}
	}

	private void leseAusgetJTextFieldName()  {
		HalleBean halleBean = (HalleBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldName().getValue() != null) {
			wert = getJTextFieldName().getValue().toString();
			halleBean.setName(wert);
		}
	}
	
	private void leseAusgetJTextFieldNummer()  {
		HalleBean bean = (HalleBean)getModelBean().getIBean();
		try{
			Integer wert;
			if(getJTextFieldNummer().getValue()!=null){
				wert = ((Number)getJTextFieldNummer().getValue()).intValue();
				bean.setNummer(wert);
			}
		}catch(NumberFormatException ex){
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
		if (source==getJTextFieldNummer()){
			leseAusgetJTextFieldNummer();
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
			
			if (!getJTextFieldNummer().getInputVerifier().shouldYieldFocus(
					getJTextFieldNummer())) {
				errors.add(new Fehler(127));
			} else {
				leseAusgetJTextFieldNummer();
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
		if (jButtonNeuZeile == null) {
			jButtonNeuZeile = new JButton();
			jButtonNeuZeile.setText("Neu");
			jButtonNeuZeile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						getController().erstelleNeuenSatz(ModelKnotenTyp.ZEILE);
					}
				}
			});
			
		}
		return jButtonNeuZeile;
	}

	/**
	 * This method initializes jButtonLoeschenZugriffsrecht	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLoeschenZugriffsrecht() {
		if (jButtonLoeschenZeile == null) {
			jButtonLoeschenZeile = new JButton();
			jButtonLoeschenZeile.setText("Löschen");
			jButtonLoeschenZeile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						if (getJTableZeilen().getSelectedRow()>=0){
							ZeileBean bean = 
								(ZeileBean) getJTableZeilen().
										getModel().
											getValueAt(getJTableZeilen().getSelectedRow(), 999);
							getController().loeschePosition(bean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
						}
					}

				}
			});
			
			
		}
		return jButtonLoeschenZeile;
	}

	/**
	 * This method initializes jScrollPaneZugriffsrechte	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneZugriffsrechte() {
		if (jScrollPaneZeile == null) {
			jScrollPaneZeile = new JScrollPane();
			jScrollPaneZeile.setViewportView(getJTableZeilen());
		}
		return jScrollPaneZeile;
	}

	/**
	 * This method initializes jTableZeile	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTableZeilen() {
		if (jTableZeilen == null) {
			jTableZeilen = new JTable();
			jTableZeilen.setModel(getAbstractTableModelZeile());
			jTableZeilen.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			jTableZeilen.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==2){
						//ausgewählte Bean wird ermittelt. 
						int aktuelleZeile = jTableZeilen.getSelectedRow();
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
		return jTableZeilen;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Bestandsliste drucken");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						try {
							((HalleDetailsController)getController()).druckeInventurListe();
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
		return jButton;
	}



	
	
}
