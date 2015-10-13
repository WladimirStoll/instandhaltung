package de.keag.lager.panels.frame.halle.pane.details.position;

import java.awt.GridBagLayout;

import javax.swing.JFormattedTextField;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckModelKnotenBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import javax.swing.BorderFactory;
import javax.swing.table.AbstractTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import java.awt.Dimension;


public class PositionDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JLabel jLabelPosition = null;
	private JFormattedTextField jFormattedTextFieldPosition = null;
	private JLabel jLabelRechts = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JToolBar jToolBar = null;
	private JButton jButtonNeu = null;
	private JButton jButtonLoeschen = null;
	private AbstractTableModel abstractTableModel;
	private JLabel jLabelRechtsUnten = null;
	/**
	 * This is the default constructor
	 * @param BenutzerPosZugriffsrechtDetailsController 
	 */
	public PositionDetailsView(DetailsController BenutzerPosZugriffsrechtDetailsController) {
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
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 3;
		gridBagConstraints1.gridheight = 1;
		gridBagConstraints1.gridwidth = 1;
		gridBagConstraints1.gridy = 10;
		jLabelRechtsUnten = new JLabel();
		jLabelRechtsUnten.setText("                                       ");
		jLabelRechtsUnten.setPreferredSize(new Dimension(90, 16));
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints5.gridy = 11;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.anchor = GridBagConstraints.WEST;
		gridBagConstraints5.gridx = 0;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.BOTH;
		gridBagConstraints4.gridy = 12;
		gridBagConstraints4.weightx = 1.0;
		gridBagConstraints4.weighty = 100.0D;
		gridBagConstraints4.gridwidth = 4;
		gridBagConstraints4.gridx = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridheight = 1;
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
		gridBagConstraints11.weighty = 50.0D;
		gridBagConstraints11.weightx = 1.0D;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 10;
		jLabelPosition = new JLabel();
		jLabelPosition.setText("Position");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 26;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 4;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.gridx = 0;
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(jLabelPosition, gridBagConstraints11);
		this.add(getJFormattedTextFieldPosition(), gridBagConstraints3);
		this.add(getJLabelRechts(), gridBagConstraints);
		this.add(getJScrollPane(), gridBagConstraints4);
		this.add(getJToolBar(), gridBagConstraints5);
		this.add(jLabelRechtsUnten, gridBagConstraints1);
	}

	@Override
	public void zeichneDich(ModelKnotenBean positionModelKnotenBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		Log.log().severe("zeichneDich");
		if(positionModelKnotenBean!=null){
			
			//Fehler anzeigen.
			for(int i=0; i<positionModelKnotenBean.getFehlerList().size();i++){
				Fehler fehler = positionModelKnotenBean.getFehlerList().get(i);
				getJComboBoxFehler().addItem(fehler);
			}
			
			if (positionModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.POSITION){
				setModelBean(positionModelKnotenBean);//merken
				PositionBean positionBean = (PositionBean) positionModelKnotenBean.getIBean();
				//id anzeigen
				getJFormattedTextFieldPosition().setText(positionBean.getNummer().toString());
				getJFormattedTextFieldPosition().setEnabled(true);
				//kostenstelle anzeigen
//				if (positionBean.getZugriffsrecht()!=null){
//					getJTextFieldZugriffsrecht().setText(positionBean.getZugriffsrecht().getZugriffsrechtName());
//				}else{
//					getJTextFieldZugriffsrecht().setText(null);
//				}
				
				//Fehler anzeigen.
				for(int i=0; i<positionModelKnotenBean.getFehlerList().size();i++){
					Fehler fehler = positionModelKnotenBean.getFehlerList().get(i);
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


	
	public void setPosition(PositionBean zugriffsrechtBean) {
		//Das Model wird geändert.
		PositionBean PositionBean = ((PositionBean)modelKnotenBean.getIBean());
//		PositionBean.setPosition(zugriffsrechtBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJFormattedTextFieldPosition()){
			leseAusgetJFormattedTextFieldPosition();
		}
		getController().ausgewaehlterKnotenIstGeandert();
		
	}

	private void leseAusgetJFormattedTextFieldPosition() {
		PositionBean bean = (PositionBean)getModelBean().getIBean();
		Integer wert ;
		try{
			if(getJFormattedTextFieldPosition().getValue()!=null){
				wert = ((Number)getJFormattedTextFieldPosition().getValue()).intValue();
				bean.setNummer(wert);
			}
		}catch(NumberFormatException ex){
		}
	}

	@Override
	public void setStandardFocusPosition() {
		getJFormattedTextFieldPosition().requestFocus();
	}

	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJFormattedTextFieldPosition().setEditable(enabled);
//		getJButtonMatchCodeZugriffsrecht().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldZugriffsrecht	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldPosition() {
		if (jFormattedTextFieldPosition == null) {
			jFormattedTextFieldPosition = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldPosition.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldPosition.addPropertyChangeListener("value", this);
//			
//			jFormattedTextFieldPosition = new JTextField();
//			jFormattedTextFieldPosition.setEditable(false);
		}
		return jFormattedTextFieldPosition;
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
	private JLabel getJLabelRechts() {
		if (jLabelRechts == null) {
			jLabelRechts = new JLabel();
			jLabelRechts.setText("  ");
		}
		return jLabelRechts;
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
						getController().erstelleNeuenSatz(ModelKnotenTyp.BESTANDSPACKSTUECK);
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
							BestandspackstueckBean bean = (BestandspackstueckBean) getJTable().getModel().getValueAt(getJTable().getSelectedRow(), 999);
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
				private String[] columnNames = {
						"Bestandspackstück-ID",
						"Keg-Nummer",
						"Artikelbezeichung",
						"Typ",
						"Menge"};

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
					if (modelKnotenBean.getIBean() instanceof BestandspackstueckBean){
						BestandspackstueckBean bean = 
							(BestandspackstueckBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {return bean.getId() ;	}
							case 1 : {return bean.getArtikelBean().getKeg_nr() ;	}
							case 2 : {return bean.getArtikelBean().getBezeichnung() ;	}
							case 3 : {return bean.getArtikelBean().getTyp() ;	}
							case 4 : {return bean.getMenge() ;	
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
							 if (getModelBean().getKinderList().get(i) instanceof BestandspackstueckModelKnotenBean){
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
	
	
}  //  @jve:decl-index=0:visual-constraint="49,1"
