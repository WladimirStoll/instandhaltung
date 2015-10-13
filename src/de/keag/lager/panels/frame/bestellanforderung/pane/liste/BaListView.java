package de.keag.lager.panels.frame.bestellanforderung.pane.liste;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListSelectionModel;

import de.keag.lager.core.IModel;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.core.renderer.ImageCellRenderer;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class BaListView extends ListView {

	private JToolBar jToolBar = null;
	private JButton jButtonMenue = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private BaListController baListPaneController;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JButton jButtonNeu = null;
	private JButton jButtonSpeichern = null;
	private JButton jButtonLoeschen = null;
	private JButton jButtonAbbrechen = null;
	private IModel iModel  = null;  //  @jve:decl-index=0:
	private DefaultTableModel defaultTableModel = null;
	private ImageCellRenderer statusCellRenderer = null;  //  @jve:decl-index=0:
	private JButton jButtonVerschicken = null;

	
	/**
	 * This method initializes  
	 * 
	 */
	protected BaListView(BaListController baListPaneController,IModel iModel) {
		super();
		setiModel(iModel) ;
		setBaListPaneController(baListPaneController);
		initialize();
	}

	private void setBaListPaneController(BaListController baListPaneController) {
		this.baListPaneController = baListPaneController;
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
			jToolBar.add(getJButtonVerschicken());
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
					BaListView.this.showMenue();
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
					BaListView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
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
			BaBean rowModelBaBean = (BaBean)getJTable().getModel().getValueAt(i, Konstanten.COLUMN_COUNT_BIG);
			if (rowModelBaBean == modelKnotenBean.getIBean()){
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
					BaListView.this.erstelleNeuenSatz();
				}
			});
			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
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
					BaListView.this.speichereSatz();
				}
			});
			Zugriffsrechtpruefung.addRecht(jButtonSpeichern,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jButtonSpeichern,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			Zugriffsrechtpruefung.addRecht(jButtonSpeichern,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
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
					BaListView.this.loescheSatz();
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
			BaBean baBean = (BaBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
			if (baBean!=null && baBean.getStatus()==AnforderungStatus.GELOESCHT){
				JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
					    new Fehler(39).getMessage(),
					    "Hinweis",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				baBean.setStatus(AnforderungStatus.GELOESCHT);
				((ListController)getIController()).speichereSatz(baBean);
			}
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
			jButtonAbbrechen.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButtonAbbrechen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BaListView.this.abbrechenSatz();
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
//					BaBean baBean = (BaBean) modelKnotenBean.getIBean();
//					ArrayList<Fehler> errors = BaListPaneView.this.getIController().pruefeAktuellenSatz();
//					//Wenn Status "offen" und Fehler sind vorhanden, dann Fehlerdialog anzeigen
//					if(baBean.getStatus()==AnforderungStatus.OFFEN 
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
//					BaListPaneView.this.getIController().aktualisiereAnzeige();
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
				private String[] baColumnNames = {"", "Erstellungsdatum",
						"Status", "Hersteller/Lieferant", "Absender",
						"Abnehmer" };

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					// Anand der aktuellen Zeile(rowIndex) wird die
					// Anforderung(Nutzdaten) ermittelt.
					BaModelKnotenBean baModelKnotenBean = (BaModelKnotenBean) BaListView.this.getiModel().getModelBeanList().get(rowIndex);
					// Aus dem Anforderungsknoten wird die
					// Anforderung(Nutzdaten) gelesen.
					BaBean baBean = baModelKnotenBean.getIBean();
					// je Spalte wird ein entsprechendes Feld(Inalt der Spalte)
					// genommen.
					switch (columnIndex) {
					case 0:
						return "";
					case 1:
						return Konstanten.DateToString(baBean
								.getErstellungsDatum());// Erstellungsdatum
					case 2:
						return baBean.getStatus(); // "Status"
					case 3:
						if (baBean.getLhBean()!=null)
							return baBean.getLhBean().getName(); // "Hersteller/Lieferant"
						else return null;
					case 4:
						if(baBean.getAbsenderBenutzerBean()!=null)
							return baBean.getAbsenderBenutzerBean().getVorname() + " " + baBean.getAbsenderBenutzerBean().getName(); // "Absender"
						else return null;
					case 5:
						if(baBean.getAnnehmerBenutzerBean()!=null)
							return baBean.getAnnehmerBenutzerBean().getVorname() + " " + baBean.getAnnehmerBenutzerBean().getName(); // "Abnehmer"
						else return null;
					case Konstanten.COLUMN_COUNT_BIG: return baBean;
					default:
						return "Fehler!!"; // Spaltennummer ist nicht bekannt.
					}
				}

				@Override
				public int getRowCount() {
//					return BaModel.this.getModelBeanList().size();
					if (BaListView.this.getiModel().getModelBeanList()==null){
						return 0;
					}else {
						return BaListView.this.getiModel().getModelBeanList().size();
					}
				}

				@Override
				public int getColumnCount() {
					return baColumnNames.length;
				}

				@Override
				public String getColumnName(int col) {
					return baColumnNames[col];
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
			BaBean baBean = (BaBean)modelKnotenBean.getIBean();
			if (baBean.getStatus()!=AnforderungStatus.OFFEN){
				//nicht mehr prüfen.
				return true;
			}
		}
		return super.pruefeFehler();
//		ArrayList<Fehler> errors = getIController().setzeNeuenAktivenController();
//		for(int i=0; i<errors.size();i++){
//			Log.log().finest("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
//		}
//		Boolean result = errors.size()==0;
//		if(!result){
//			JFehlerDialogWechselController.getOneIntstance().showView(errors);
//		}
//		return errors.size()==0;
	}

	@Override
	public TableCellRenderer getStatusCellRenderer(int row, int column) {
		if (statusCellRenderer==null){
			statusCellRenderer = new ImageCellRenderer(true);
		}
		if (column == 0){
			BaModelKnotenBean baModelKnotenBean = (BaModelKnotenBean) BaListView.this.getiModel().getModelBeanList().get(row);
			Icon icon = BeanDBStatus.JavaToIcon(baModelKnotenBean.getIBean().getBeanDBStatus());
			statusCellRenderer.setIcon(icon);
		}
		return statusCellRenderer;
	}

	@Override
	public ListController getIController() {
		return baListPaneController;
	}

	

	@Override
	public IModel getiModel() {
		return iModel;
	}

	/**
	 * This method initializes jButtonVerschicken	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonVerschicken() {
		if (jButtonVerschicken == null) {
			jButtonVerschicken = new JButton();
			jButtonVerschicken.setText("Verschicken");

			jButtonVerschicken.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (!pruefeFehler()) return;
						BaListView.this.druckeBericht(null);
					} catch (LagerException e1) {
						getJComboBoxFehler().addItem(new Fehler(25,FehlerTyp.FEHLER, e1.getMessage().toString()));
						e1.printStackTrace();
					} catch (SQLException e1) {
						getJComboBoxFehler().addItem(new Fehler(25,FehlerTyp.FEHLER, e1.getMessage().toString()));
						e1.printStackTrace();
					} catch (Exception e1) {
						getJComboBoxFehler().addItem(new Fehler(25,FehlerTyp.FEHLER, e1.getMessage().toString()));
						e1.printStackTrace();
					}
				}
			});
		}
		return jButtonVerschicken;
	}

	
//	@Override
//	protected void druckeBericht() throws Exception {
//		druckeBericht(null);
//	}

	@Override
	protected void druckeBericht(Map<String, String> druckParameter) throws Exception {
		if (!pruefeFehler()) return;
		if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
			BaBean baBean = (BaBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
			//aktueller Benutzer (der jenige, welcher tatsächlich verschickt) wird gespeichert
			baBean.setAbsenderBenutzerBean(Run.getOneInstance().getBenutzerBean());
			((BaListController)getIController()).speichereSatz(baBean);
//			//Es darf keine Anforderung ohne den aktuellen Benutzer abgespeichert werden.
//			if (((BaBean)bean).getAbsenderBenutzerBean()!=null||((BaBean)bean).getAbsenderBenutzerBean().getId().equals(0)){
//				//Aktueller Benutzer ist leer und wird deswegen durch den aktuell angemeldeten Benutzer ersetzt.
//				((BaBean)bean).setAbsenderBenutzerBean(Run.getOneInstance().getBenutzerBean());
//			}

			((BaListController)getIController()).druckeBericht(baBean, null);
		}
		
	}
	
	
}
