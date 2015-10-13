package de.keag.lager.panels.frame.lieferanthersteller.pane.liste;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import de.keag.lager.core.IModel;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.core.renderer.ImageCellRenderer;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

public class LhListPaneView extends ListView{

	private JToolBar jToolBar = null;
	private JButton jButtonMenue = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private LhListPaneController lhListPaneController;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JButton jButtonNeu = null;
	private JButton jButtonSpeichern = null;
	private JButton jButtonLoeschen = null;
	private JButton jButtonAbbrechen = null;
	private ListSelectionListener listSelectionListener = null;  //  @jve:decl-index=0:
	private IModel iModel = null;  //  @jve:decl-index=0:
	private DefaultTableModel defaultTableModel = null;
	private ImageCellRenderer statusCellRenderer = null;  //  @jve:decl-index=0:

	
	/**
	 * This method initializes  
	 * 
	 */
	public LhListPaneView(LhListPaneController lhListPaneController,IModel iModel) {
		super();
		setiModel(iModel) ;
		setLhListPaneController(lhListPaneController);
		initialize();
	}

	private void setLhListPaneController(LhListPaneController lhListPaneController) {
		this.lhListPaneController = lhListPaneController;
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
					if (!prueferFehler()) return;
					ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
					if(modelKnotenBean!=null && modelKnotenBean.istGesamterInhaltGeaendert()){
						zeigeFehlerDialogSatzGeaendert();
					}else{
						getIController().showMenue();
					}
				}

			});
		}
		return jButtonMenue;
	}

	@Override
	public ListController getIController() {
		return lhListPaneController;
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


	protected void setGewaehlteZeile(int gewaehlteZeilenNummer) {
		getIController().setGewaehlteZeile(gewaehlteZeilenNummer);
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
			LhBean rowModelLhBean = (LhBean)getJTable().getModel().getValueAt(i, Konstanten.COLUMN_COUNT_BIG);
			if (rowModelLhBean == modelKnotenBean.getIBean()){
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
					LhListPaneView.this.erstelleNeuenSatz();
//					if (!prueferFehler()) return;
//					ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
//					if(modelKnotenBean.istGesamterInhaltGeaendert()){
//						zeigeFehlerDialogSatzGeaendert();
//					}else{
//						getLhListPaneController().erstelleNeuenLh();
//					}
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
					LhListPaneView.this.speichereSatz();
					//					if (!prueferFehler()) return;
//					LhBean lhBean = (LhBean)getiModel().
//								getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
//	//													getIBean(); //aktuelle Bean holen
//					ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
//					if(!modelKnotenBean.istGesamterInhaltGeaendert()){
//						JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
//							    "Daten sind nicht geändert und müssen nicht gespeichert werden!",
//							    "Hinweis",JOptionPane.WARNING_MESSAGE);
//					}else{
//						getLhListPaneController().speichereLh(lhBean);
//					}
//					
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
					LhListPaneView.this.loescheSatz();
//					LhBean lhBean = (LhBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
//					getLhListPaneController().loescheLh(lhBean);
					
				}
			});
			Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		}
		return jButtonLoeschen;
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
//					getLhListPaneController().setzeNeuenAktivenController(); //klappt oder nicht = egal					
//					LhBean lhBean = (LhBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
//					getLhListPaneController().abbrechenLh(lhBean);
					LhListPaneView.this.abbrechenSatz();
				}
			});
		}
		return jButtonAbbrechen;
	}

	protected ListSelectionListener getListSelectionListener() {
		if (listSelectionListener==null){
			listSelectionListener = new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				int aktuelleZeile = lsm.getMinSelectionIndex();
				int aktuelleBeanPosition = LhListPaneView.this.getiModel().getSelectedListModelKnotenBeanLaufendeNummer();
				if(aktuelleZeile==aktuelleBeanPosition){
					//Verlassen der Funktion, denn die Zeile ist bereits gewählt.
					//keine Handlung ist notwendig
					return;
				}
				if (prueferFehler()){
					ModelKnotenBean modelKnotenBean = LhListPaneView.this.getiModel().getSelectedListModelKnotenBean();
//					LhBean lhBean = (LhBean) modelKnotenBean.getIBean();
					ArrayList<Fehler> errors = LhListPaneView.this.getIController().pruefeAktuellenSatz();
					//Wenn Status "offen" und Fehler sind vorhanden, dann Fehlerdialog anzeigen
					if( errors.size()>0 || modelKnotenBean.istGesamterInhaltGeaendert()){
						if (errors.size()>0){
							JFehlerDialogWechselController.getOneIntstance().showView(errors);
						}else{
							if(modelKnotenBean.istGesamterInhaltGeaendert()){
								zeigeFehlerDialogSatzGeaendert();
							}
						}
						//Position bleibt erhalten //
						if (aktuelleBeanPosition >= 0){
							getJTable().getSelectionModel().setSelectionInterval(aktuelleBeanPosition, aktuelleBeanPosition);
						}
					}else{  //
							//Es darf eine andere Zeile(Bestellan gewählt werden.
							if (lsm.isSelectionEmpty()) {
								setGewaehlteZeile(-1);
					        } else {
					            // Ausgewählte Zeile(Laufnummer).
					        	setGewaehlteZeile(lsm.getMinSelectionIndex());
					        }
					}
				}else{
					//Fehler irgendwo in der Anzeige
					//Es muss auf den alten Wert positioniert werden.
					LhListPaneView.this.getIController().aktualisiereAnzeige();
				}
			}

			};
		}
		return listSelectionListener;
	}

	
//	private void zeigeFehlerDialogSatzGeaendert() {
//		JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
//			    "Bitte vorher Änderung abspeichern oder verwerfen!",
//			    "Hinweis",JOptionPane.WARNING_MESSAGE);
//	}
	
//	private IModel getiModel() {
//		return iModel;
//	}

	private void setiModel(IModel iModel) {
		this.iModel = iModel;
	}


	private DefaultTableModel getDefaultTableModel() {
		if (defaultTableModel == null) {
			defaultTableModel = new DefaultTableModel() {
				private String[] lhColumnNames = {"ID",
						"Name", "Email",
						"Ansprechpartner",
					    "Telefon"};

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					// Anand der aktuellen Zeile(rowIndex) wird die
					// Anforderung(Nutzdaten) ermittelt.
					LhModelKnotenBean lhModelKnotenBean = (LhModelKnotenBean) LhListPaneView.this.getiModel().getModelBeanList().get(rowIndex);
					// Aus dem Anforderungsknoten wird die
					// Anforderung(Nutzdaten) gelesen.
					LhBean LhBean = lhModelKnotenBean.getIBean();
					// je Spalte wird ein entsprechendes Feld(Inalt der Spalte)
					// genommen.
					switch (columnIndex) {
					case 0:
						return LhBean.getId();
					case 1:
						return LhBean.getName();
					case 2:
						return LhBean.getEmail();
					case 3:
						return LhBean.getAnsprechpartner();
					case 4:
						return LhBean.getTelefon();
					case Konstanten.COLUMN_COUNT_BIG: return LhBean;
					default:
						return "Fehler!!"; // Spaltennummer ist nicht bekannt.
					}
				}

				@Override
				public int getRowCount() {
//					return LhModel.this.getModelBeanList().size();
					if (LhListPaneView.this.getiModel().getModelBeanList()==null){
						return 0;
					}else {
						return LhListPaneView.this.getiModel().getModelBeanList().size();
					}
				}

				@Override
				public int getColumnCount() {
					return lhColumnNames.length;
				}

				@Override
				public String getColumnName(int col) {
					return lhColumnNames[col];
				}
				
				@Override
				public boolean isCellEditable(int row, int col) {
		            return false;
			    }
				

			};

		}
		return defaultTableModel;
	}

	public void setBorder(Boolean aktiv) {
		Border b = this.getBorder();
		if (b!=null){
			if (b instanceof LineBorder){
				if (aktiv){
					if (((LineBorder) b).getLineColor()!=Konstanten.COLOR_LINEBORDER_AKTIV){
						this.setBorder(BorderFactory.createLineBorder(Konstanten.COLOR_LINEBORDER_AKTIV, 1));
					}
				}else{
					if (((LineBorder) b).getLineColor()!=Konstanten.COLOR_LINEBORDER_NICHT_AKTIV){
						this.setBorder(BorderFactory.createLineBorder(Konstanten.COLOR_LINEBORDER_NICHT_AKTIV, 1));
					}
				}
			}
		}
	}

	public void setStandardFocusPosition() {
		getJTable().requestFocus();
	}
	
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler> ();

		return errors;
	}
	
	protected boolean prueferFehler() {
		ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
		ArrayList<Fehler> errors;
		if (modelKnotenBean != null && modelKnotenBean.getIBean() != null) {
			LhBean lhBean = (LhBean) modelKnotenBean
					.getIBean();
			errors = lhBean.pruefeEigeneDaten();
			Boolean result = errors.size() == 0;
			if (!result) {
				JFehlerDialogWechselController.getOneIntstance().showView(
						errors);
				return result; // Fehler sind vorhanden
			}
			// if (lhBean.getStatus()!=AnforderungStatus.OFFEN){
			// //nicht mehr prüfen.
			// return true;
			// }
		}
		
		errors = getIController().setzeNeuenAktivenController();
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
//			jTable.addFocusListener(new java.awt.event.FocusAdapter() {
//				public void focusGained(java.awt.event.FocusEvent e) {
//					if (!prueferFehler()) return;
//				}
//			});
			getDefaultTableModel().addTableModelListener(jTable);//für die fire... Methoden
			jTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		}
		return jTable;
	}

	@Override
	public TableCellRenderer getStatusCellRenderer(int row, int column) {
		if (statusCellRenderer==null){
			statusCellRenderer = new ImageCellRenderer(true);
		}
		if (column == 0){
			LhModelKnotenBean lhModelKnotenBean = (LhModelKnotenBean) LhListPaneView.this.getiModel().getModelBeanList().get(row);
//			String iconError; 
//			if (lhModelKnotenBean.getFehlerList().size()>0){
//				iconError = Fehler.getFehlerIcon(); 
//			}else{
//				iconError = null;
//			}
//			String IconNameStatus = BeanDBStatus.JavaToIconName(lhModelKnotenBean.getIBean().getBeanDBStatus());
//			Icon icon = Run.createCompoundIcon(Konstanten.ICON_BENUTZER, IconNameStatus,iconError);
			Icon icon = Run.createCompoundIcon(Konstanten.ICON_BENUTZER);
			statusCellRenderer.setIcon(icon);
			
		}
		return statusCellRenderer;
	}

	@Override
	public IModel getiModel() {
		return iModel;
	}


	
}
