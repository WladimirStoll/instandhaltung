package de.keag.lager.panels.frame.katalog.pane.liste;

import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListSelectionModel;

import de.keag.lager.core.IModel;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.core.renderer.ImageCellRenderer;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.katalog.model.KatalogModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

import java.awt.Color;

public class KatalogListView extends ListView {

	private JToolBar jToolBar = null;
	private JButton jButtonMenue = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private KatalogListController einlagerungListPaneController;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JButton jButtonNeu = null;
	private JButton jButtonSpeichern = null;
	private JButton jButtonLoeschen = null;
	private JButton jButtonAbbrechen = null;
	private IModel iModel  = null;  //  @jve:decl-index=0:
	private DefaultTableModel defaultTableModel = null;
	private ImageCellRenderer statusCellRenderer = null;  //  @jve:decl-index=0:

	
	/**
	 * This method initializes  
	 * 
	 */
	protected KatalogListView(KatalogListController einlagerungListPaneController,IModel iModel) {
		super();
		setiModel(iModel) ;
		setKatalogListPaneController(einlagerungListPaneController);
		initialize();
	}

	private void setKatalogListPaneController(KatalogListController einlagerungListPaneController) {
		this.einlagerungListPaneController = einlagerungListPaneController;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints.weightx = 1.0;
        GridBagConstraints gridBagConstraintsToolBar = new GridBagConstraints();
        gridBagConstraintsToolBar.gridx = 0;
        gridBagConstraintsToolBar.gridy = 0;
        gridBagConstraintsToolBar.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraintsToolBar.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsToolBar.weightx = 1.0;
        gridBagConstraintsToolBar.insets = new Insets(1, 1, 1, 1);
        gridBagConstraintsToolBar.weighty = 1.0D;
        GridBagConstraints gridBagConstraintsTabelle = new GridBagConstraints();
        gridBagConstraintsTabelle.gridx = 0;
        gridBagConstraintsTabelle.gridy = 1;
        gridBagConstraintsTabelle.fill = GridBagConstraints.BOTH;
        gridBagConstraintsTabelle.anchor = GridBagConstraints.CENTER;
        gridBagConstraintsTabelle.weightx = 1.0;
        gridBagConstraintsTabelle.insets = new Insets(1, 1, 1, 1);
        gridBagConstraintsTabelle.weighty = 300.0D;
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        this.add(getJToolBar(), gridBagConstraintsToolBar);
        this.add(getJScrollPane(), gridBagConstraintsTabelle);
        this.add(getJComboBoxFehler(), gridBagConstraints);
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
			jToolBar.add(getJButtonMenue());
			jToolBar.add(getJButtonNeu());
			jToolBar.add(getJButtonSpeichern());
			jToolBar.add(getJButtonLoeschen());
			jToolBar.add(getJButtonAbbrechen());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jButtonMenue	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMenue() {
		if (jButtonMenue == null) {
			jButtonMenue = new JButton();
			jButtonMenue.setText("Zurück");
			jButtonMenue.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KatalogListView.this.showMenue();
				}

			});
		}
		return jButtonMenue;
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
	@Override
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(){
				@Override
				public TableCellRenderer getCellRenderer(int row, int column) {
			        if (column == 0) {
			        	//Renderer pro Spalte definieren
//			        	return super.getCellRenderer(row, column);
			        	return getStatusCellRenderer(row,column);
			        }
			        return super.getCellRenderer(row, column);
			    }
			};
			jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable.getSelectionModel().addListSelectionListener(getListSelectionListener());
			jTable.setModel(getDefaultTableModel()); //Table-Model wird gesetzt.
			jTable.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					if (!pruefeFehler()) return;
				}
			});
			getDefaultTableModel().addTableModelListener(jTable);//für die fire... Methoden
			jTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		}
		return jTable;
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
					KatalogListView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
				}
			});
		}
		return jComboBoxFehler;
	}

	@Override
	public void zeichneDich(IModel iModel) {
		jTable.getSelectionModel().removeListSelectionListener(getListSelectionListener());
		getJComboBoxFehler().removeAllItems();
		setiModel(iModel);
		//Fehler ausgeben.
		for(int i=0; i<iModel.getFehlerList().size();i++){
			Fehler fehler = iModel.getFehlerList().get(i);
			getJComboBoxFehler().addItem(fehler);
		}
		//Positioniren des Cursors neu
		ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
		for(int i = 0; i<getJTable().getModel().getRowCount();i++){
			KatalogBean rowModelKatalogBean = (KatalogBean)getJTable().getModel().getValueAt(i, Konstanten.COLUMN_COUNT_BIG);
			if (rowModelKatalogBean == modelKnotenBean.getIBean()){
				getJTable().setRowSelectionInterval(i, i); 
			}
		}
		
		this.repaint();//alte Komponenten werden gelöscht
		this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
		this.validate();   //werden gezeichnet.
		this.revalidate(); //Layout-Manager tut seinen JOB, und richtet				this.invalidate();
		
		getDefaultTableModel().fireTableDataChanged();
		jTable.getSelectionModel().addListSelectionListener(getListSelectionListener());
	}

	/**
	 * This method initializes jButtonNeu	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNeu() {
		if (jButtonNeu == null) {
			jButtonNeu = new JButton();
			jButtonNeu.setText(Konstanten.BUTTON_NEU);
			jButtonNeu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KatalogListView.this.erstelleNeuenSatz();
				}
			});
			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			
		}
		return jButtonNeu;
	}

	/**
	 * This method initializes jButtonSpeichern	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSpeichern() {
		if (jButtonSpeichern == null) {
			jButtonSpeichern = new JButton();
			jButtonSpeichern.setText(Konstanten.BUTTON_SPEICHERN);
			jButtonSpeichern.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KatalogListView.this.speichereSatz();
				}
			});
			Zugriffsrechtpruefung.addRecht(jButtonSpeichern,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jButtonSpeichern,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		}
		return jButtonSpeichern;
	}


	/**
	 * This method initializes jButtonLoeschen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLoeschen() {
		if (jButtonLoeschen == null) {
			jButtonLoeschen = new JButton();
			jButtonLoeschen.setText(Konstanten.BUTTON_LOESCHEN);
			jButtonLoeschen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KatalogListView.this.loescheSatz();
				}
			});
			Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		}
		return jButtonLoeschen;
	}
	
	@Override
	protected void loescheSatz() {
		if (!pruefeFehler()) return;
		if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
			KatalogBean einlagerungBean = (KatalogBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
				einlagerungBean.setBeanDBStatus(BeanDBStatus.DELETE);
				((ListController)getIController()).speichereSatz(einlagerungBean);
		}
	}


	/**
	 * This method initializes jButtonAbbrechen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAbbrechen() {
		if (jButtonAbbrechen == null) {
			jButtonAbbrechen = new JButton();
			jButtonAbbrechen.setText(Konstanten.BUTTON_ABBRECHEN);
			jButtonAbbrechen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KatalogListView.this.abbrechenSatz();
				}
			});
		}
		return jButtonAbbrechen;
	}

//	@Override
//	private ListSelectionListener getListSelectionListener() {
//		if (listSelectionListener==null){
//			listSelectionListener = new ListSelectionListener(){
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
//				int aktuelleZeile = lsm.getMinSelectionIndex();
//				int aktuelleBeanPosition = BaListPaneView.this.getiModel().getSelectedModelKnotenBeanLaufendeNummer();
//				if(aktuelleZeile==aktuelleBeanPosition){
//					//Verlassen der Funktion, denn die Zeile ist bereits gewählt.
//					//keine Handlung ist notwendig
//					return;
//				}else{
//				}
//				
//				if (pruefeFehler()){
//					ModelKnotenBean modelKnotenBean = BaListPaneView.this.getiModel().getSelectedModelKnotenBean();
//					BaBean einlagerungBean = (BaBean) modelKnotenBean.getIBean();
//					ArrayList<Fehler> errors = BaListPaneView.this.getIController().pruefeAktuellenSatz();
//					//Wenn Status "offen" und Fehler sind vorhanden, dann Fehlerdialog anzeigen
//					if(baBean.getStatus()==KatalogStatus.OFFEN 
//					   && ( errors.size()>0 || modelKnotenBean.istGesamterInhaltGeaendert())){
//						if (errors.size()>0){
//							JFehlerDialogWechselController.getOneIntstance().showView(errors);
//						}else{
//							if(modelKnotenBean.istGesamterInhaltGeaendert()){
//								BaListPaneView.this.zeigeFehlerDialogSatzGeaendert();
//							}
//						}
//						//Position bleibt erhalten //
//						if (aktuelleBeanPosition >= 0){
//							getJTable().getSelectionModel().setSelectionInterval(aktuelleBeanPosition, aktuelleBeanPosition);
//							System.out.println(aktuelleBeanPosition);
//						}
//					}else{  //
//							//Es darf eine andere Zeile(Bestellan gewählt werden.
//							if (lsm.isSelectionEmpty()) {
//								setGewaehlteZeile(-1);
//					        } else {
//					            // Ausgewählte Zeile(Laufnummer).
//					        	setGewaehlteZeile(lsm.getMinSelectionIndex());
//					        }
//					}
//				}else{
//					//Fehler irgendwo in der Anzeige
//					//Es muss auf den alten Wert positioniert werden.
//					KatalogListPaneView.this.getIController().aktualisiereAnzeige();
//				}
//			}
//
//			};
//		}
//		return listSelectionListener;
//	}

	
	

	private void setiModel(IModel iModel) {
		this.iModel = iModel;
	}


	private DefaultTableModel getDefaultTableModel() {
		if (defaultTableModel == null) {
			defaultTableModel = new DefaultTableModel() {
				private String[] einlagerungColumnNames = {"","Katalogname"};

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					// Anand der aktuellen Zeile(rowIndex) wird die
					// Katalog(Nutzdaten) ermittelt.
					KatalogModelKnotenBean modelKnotenBean = (KatalogModelKnotenBean) KatalogListView.this.getiModel().getModelBeanList().get(rowIndex);
					// Aus dem Katalogsknoten wird die
					// Katalog(Nutzdaten) gelesen.
					KatalogBean katalogBean = modelKnotenBean.getIBean();
					// je Spalte wird ein entsprechendes Feld(Inalt der Spalte)
					// genommen.
					switch (columnIndex) {
					case 0:
						return "";
					case 1:
						return katalogBean.getName();
					case Konstanten.COLUMN_COUNT_BIG:
						return katalogBean;
					default:
						return "Fehler!!"; // Spaltennummer ist nicht bekannt.
					}
				}

				@Override
				public int getRowCount() {
//					return KatalogModel.this.getModelBeanList().size();
					if (KatalogListView.this.getiModel().getModelBeanList()==null){
						return 0;
					}else {
						return KatalogListView.this.getiModel().getModelBeanList().size();
					}
				}

				@Override
				public int getColumnCount() {
					return einlagerungColumnNames.length;
				}

				@Override
				public String getColumnName(int col) {
					return einlagerungColumnNames[col];
				}
				
				@Override
				public boolean isCellEditable(int row, int col) {
		            return false;
			    }
				

			};

		}
		return defaultTableModel;
	}


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
	public boolean pruefeFehler() {
		ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
		if (modelKnotenBean!=null && modelKnotenBean.getIBean()!=null){
			KatalogBean katalogBean = (KatalogBean)modelKnotenBean.getIBean();
		}
		
		ArrayList<Fehler> errors = getIController().setzeNeuenAktivenController();
		for(int i=0; i<errors.size();i++){
			System.out.println("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
		}
		Boolean result = errors.size()==0;
		if(!result){
			JFehlerDialogWechselController.getOneIntstance().showView(errors);
		}
		return errors.size()==0;
	}

	@Override
	public TableCellRenderer getStatusCellRenderer(int row, int column) {
		if (statusCellRenderer==null){
			statusCellRenderer = new ImageCellRenderer(true);
		}
		if (column == 0){
			KatalogModelKnotenBean modelKnotenBean = (KatalogModelKnotenBean) KatalogListView.this.getiModel().getModelBeanList().get(row);
			Icon icon = BeanDBStatus.JavaToIcon(modelKnotenBean.getIBean().getBeanDBStatus());
			statusCellRenderer.setIcon(icon);
		}
		return statusCellRenderer;
	}

	@Override
	public ListController getIController() {
		return einlagerungListPaneController;
	}

	

	@Override
	public IModel getiModel() {
		return iModel;
	}
	
	
}
