package de.keag.lager.panels.frame.bericht.pane.liste;

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

import de.keag.lager.core.Bean;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.core.renderer.ImageCellRenderer;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtModelKnotenBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht;

import java.awt.Color;

public class BerichtListView extends ListView {

	private JToolBar jToolBar = null;
	private JButton jButtonMenue = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private BerichtListController listController;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JButton jButtonNochMal = null;
	private IModel iModel  = null;  //  @jve:decl-index=0:
	private DefaultTableModel defaultTableModel = null;
	private ImageCellRenderer statusCellRenderer = null;  //  @jve:decl-index=0:

	
	/**
	 * This method initializes  
	 * 
	 */
	protected BerichtListView(BerichtListController baListPaneController,IModel iModel) {
		super();
		setiModel(iModel) ;
		setBaListPaneController(baListPaneController);
		initialize();
	}

	private void setBaListPaneController(BerichtListController baListPaneController) {
		this.listController = baListPaneController;
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
			jToolBar.add(getJButtonNochMal());
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
//					BerichtListView.this.showMenue();
					BerichtListView.this.showVorigesView();
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
					BerichtListView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
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
			BerichtBean rowModelBaBean = (BerichtBean)getJTable().getModel().getValueAt(i, Konstanten.COLUMN_COUNT_BIG);
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
	 * This method initializes jButtonNochMal	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNochMal() {
		if (jButtonNochMal == null) {
			jButtonNochMal = new JButton();
			jButtonNochMal.setText("Drucken / verschicken");
			jButtonNochMal.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					BerichtListView.this.speichereSatz();
					try {
						BerichtListView.this.druckeVerschickeSatz();
					} catch (Exception e1) {
						getJComboBoxFehler().addItem(new Fehler(e1.getMessage()));
						getJComboBoxFehler().addItem(new Fehler("Fehler beim Versenden! Kein Netz?"));
						e1.printStackTrace();
					}
				}
			});
		}
		return jButtonNochMal;
	}


	protected void druckeVerschickeSatz() throws Exception {
//		if (!pruefeFehler()) return;
		if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
			Bean bean = (Bean) getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
			ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
			if (bean instanceof BerichtBean){
				((BerichtListController)getIController()).druckeBericht(bean, null);
//				if (((BerichtBean)bean).getBerichtArt().equals(Berichtart.EMAIL)){
//					((BerichtListController)getIController()).druckeBericht(bean);
//				}else{
//					if (((BerichtBean)bean).getBerichtArt().equals(Berichtart.PRINT)){
//						((BerichtListController)getIController()).verschickeSatz(bean);
//					}else{
//						Log.log().severe("BerichtArt wird nicht unterstützt:"+((BerichtBean)bean).getBerichtArt());
//					}
//				}
			}
		}
	}

	@Override
	protected void loescheSatz() {
//		if (!pruefeFehler()) return;
//		if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
//			BaBean baBean = (BaBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
//			if (baBean!=null && baBean.getStatus()==Berichttyp.GELOESCHT){
//				JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
//					    new Fehler(39).getMessage(),
//					    "Hinweis",JOptionPane.WARNING_MESSAGE);
//				return;
//			}else{
//				baBean.setStatus(Berichttyp.GELOESCHT);
//				((ListController)getIController()).speichereSatz(baBean);
//			}
//		}
//		
	}


	private void setiModel(IModel iModel) {
		this.iModel = iModel;
	}


	private DefaultTableModel getDefaultTableModel() {
		if (defaultTableModel == null) {
			defaultTableModel = new DefaultTableModel() {
				
				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					// Anand der aktuellen Zeile(rowIndex) wird die
					// Anforderung(Nutzdaten) ermittelt.
					BerichtModelKnotenBean berichtModelKnotenBean 
						= (BerichtModelKnotenBean) BerichtListView.this.getiModel().getModelBeanList().get(rowIndex);
					// Aus dem Anforderungsknoten wird die
					// Anforderung(Nutzdaten) gelesen.
					BerichtBean berichtBean = berichtModelKnotenBean.getIBean();
					// je Spalte wird ein entsprechendes Feld(Inalt der Spalte)
					// genommen.
					switch (columnIndex) {
					case 0:
						return "";
					case 1:
						return berichtBean.getBerichtTyp().toString(); 
					case 2:
						return berichtBean.getBerichtTypId().toString(); 
					case 3:
						return berichtBean.getId().toString(); 
					case 4:
						return berichtBean.getAktuellerBenutzer(); //Druck-Benutzer 
					case 5:
						return berichtBean.getBerichtArt().toString(); 
					case 6:
						return Konstanten.DateToString(berichtBean
								.getDruckDatumOriginal());
					case 7:
						return Konstanten.DateToString(berichtBean
								.getDruckDatumKopie());
					case Konstanten.COLUMN_COUNT_BIG: return berichtBean;
					default:
						return "Fehler!!"; // Spaltennummer ist nicht bekannt.
					}
				}

				@Override
				public int getRowCount() {
//					return BaModel.this.getModelBeanList().size();
					if (BerichtListView.this.getiModel().getModelBeanList()==null){
						return 0;
					}else {
						return BerichtListView.this.getiModel().getModelBeanList().size();
					}
				}

				@Override
				public int getColumnCount() {
					return getBaColumnNames().length;
				}

				@Override
				public String getColumnName(int col) {
					return getBaColumnNames()[col];
				}
				
				@Override
				public boolean isCellEditable(int row, int col) {
		            return false;
			    }
				
				private String[] getBaColumnNames(){
					final String[] baColumnNames = {"", "Typ","Typ-ID", "Bericht-ID", "Benutzer", "Art", "Druckdatum(Original)", "Druckdatum(Kopie)"};
					return baColumnNames;
					
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
			BerichtBean bean = (BerichtBean)modelKnotenBean.getIBean();
//			if (bean.getStatus()!=Berichttyp.OFFEN){
//				//nicht mehr prüfen.
//				return true;
//			}
		}
		
		ArrayList<Fehler> errors = getIController().setzeNeuenAktivenController();
		for(int i=0; i<errors.size();i++){
			Log.log().finest("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
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
			BerichtModelKnotenBean modelKnotenBean = (BerichtModelKnotenBean) BerichtListView.this.getiModel().getModelBeanList().get(row);
			Icon icon = BeanDBStatus.JavaToIcon(modelKnotenBean.getIBean().getBeanDBStatus());
			statusCellRenderer.setIcon(icon);
		}
		return statusCellRenderer;
	}

	@Override
	public ListController getIController() {
		return listController;
	}

	

	@Override
	public IModel getiModel() {
		return iModel;
	}
	
	
}
