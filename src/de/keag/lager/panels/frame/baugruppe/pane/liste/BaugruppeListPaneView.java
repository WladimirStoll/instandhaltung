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
package de.keag.lager.panels.frame.baugruppe.pane.liste;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListSelectionModel;

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
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

public class BaugruppeListPaneView extends ListView{

	private JToolBar jToolBar = null;
	private JButton jButtonMenue = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private BaugruppeListPaneController baugruppeListPaneController;  //  @jve:decl-index=0:
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
	public BaugruppeListPaneView(BaugruppeListPaneController baugruppeListPaneController,IModel iModel) {
		super();
		setiModel(iModel) ;
		setBaugruppeListPaneController(baugruppeListPaneController);
		initialize();
	}

	private void setBaugruppeListPaneController(BaugruppeListPaneController baugruppeListPaneController) {
		this.baugruppeListPaneController = baugruppeListPaneController;
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
		return baugruppeListPaneController;
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
			BaugruppeBean rowModelBaugruppeBean = (BaugruppeBean)getJTable().getModel().getValueAt(i, Konstanten.COLUMN_COUNT_BIG);
			if (rowModelBaugruppeBean == modelKnotenBean.getIBean()){
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
					BaugruppeListPaneView.this.erstelleNeuenSatz();
//					if (!prueferFehler()) return;
//					ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
//					if(modelKnotenBean.istGesamterInhaltGeaendert()){
//						zeigeFehlerDialogSatzGeaendert();
//					}else{
//						getBaugruppeListPaneController().erstelleNeuenBaugruppe();
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
					BaugruppeListPaneView.this.speichereSatz();
					//					if (!prueferFehler()) return;
//					BaugruppeBean baugruppeBean = (BaugruppeBean)getiModel().
//								getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
//	//													getIBean(); //aktuelle Bean holen
//					ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
//					if(!modelKnotenBean.istGesamterInhaltGeaendert()){
//						JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
//							    "Daten sind nicht geändert und müssen nicht gespeichert werden!",
//							    "Hinweis",JOptionPane.WARNING_MESSAGE);
//					}else{
//						getBaugruppeListPaneController().speichereBaugruppe(baugruppeBean);
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
					BaugruppeListPaneView.this.loescheSatz();
//					BaugruppeBean baugruppeBean = (BaugruppeBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
//					getBaugruppeListPaneController().loeschebaugruppe(BaugruppeBean);
					
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
//					getBaugruppeListPaneController().setzeNeuenAktivenController(); //klappt oder nicht = egal					
//					BaugruppeBean BaugruppeBean = (BaugruppeBean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
//					getBaugruppeListPaneController().abbrechenBaugruppe(BaugruppeBean);
					BaugruppeListPaneView.this.abbrechenSatz();
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
				int aktuelleBeanPosition = BaugruppeListPaneView.this.getiModel().getSelectedListModelKnotenBeanLaufendeNummer();
				if(aktuelleZeile==aktuelleBeanPosition){
					//Verlassen der Funktion, denn die Zeile ist bereits gewählt.
					//keine Handlung ist notwendig
					return;
				}
				if (prueferFehler()){
					ModelKnotenBean modelKnotenBean = BaugruppeListPaneView.this.getiModel().getSelectedListModelKnotenBean();
//					BaugruppeBean BaugruppeBean = (BaugruppeBean) modelKnotenBean.getIBean();
					ArrayList<Fehler> errors = BaugruppeListPaneView.this.getIController().pruefeAktuellenSatz();
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
					BaugruppeListPaneView.this.getIController().aktualisiereAnzeige();
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
				private String[] baugruppeColumnNames = {"","Anlage","Halle"};

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					// Anand der aktuellen Zeile(rowIndex) wird die
					// Anforderung(Nutzdaten) ermittelt.
					BaugruppeModelKnotenBean baugruppeModelKnotenBean = (BaugruppeModelKnotenBean) BaugruppeListPaneView.this.getiModel().getModelBeanList().get(rowIndex);
					// Aus dem Anforderungsknoten wird die
					// Anforderung(Nutzdaten) gelesen.
					BaugruppeBean baugruppeBean = baugruppeModelKnotenBean.getIBean();
					// je Spalte wird ein entsprechendes Feld(Inalt der Spalte)
					// genommen.
					switch (columnIndex) {
					case 0:
						return baugruppeBean.getId();
					case 1:
						return baugruppeBean.getName();
					case 2:
						return baugruppeBean.getHalleBean().getName();
					case Konstanten.COLUMN_COUNT_BIG: return baugruppeBean;
					default:
						return "Fehler!!"; // Spaltennummer ist nicht bekannt.
					}
				}

				@Override
				public int getRowCount() {
//					return BaugruppeModel.this.getModelBeanList().size();
					if (BaugruppeListPaneView.this.getiModel().getModelBeanList()==null){
						return 0;
					}else {
						return BaugruppeListPaneView.this.getiModel().getModelBeanList().size();
					}
				}

				@Override
				public int getColumnCount() {
					return baugruppeColumnNames.length;
				}

				@Override
				public String getColumnName(int col) {
					return baugruppeColumnNames[col];
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
			BaugruppeBean baugruppeBean = (BaugruppeBean) modelKnotenBean
					.getIBean();
			errors = baugruppeBean.pruefeEigeneDaten();
			Boolean result = errors.size() == 0;
			if (!result) {
				JFehlerDialogWechselController.getOneIntstance().showView(
						errors);
				return result; // Fehler sind vorhanden
			}
			// if (benutzerBean.getStatus()!=AnforderungStatus.OFFEN){
			// //nicht mehr prüfen.
			// return true;
			// }
		}
		
		errors = getIController().setzeNeuenAktivenController();
		for(int i=0; i<errors.size();i++){
			Log.log().finest("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
		}
		Boolean result = errors.size()==0;
		if(!result){
			JFehlerDialogWechselController.getOneIntstance().showView(errors);
		}
		return result;
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
//			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
			jTable.getColumnModel().getColumn(0).setPreferredWidth(35);
			jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
//			jTable.getColumnModel().getColumn(0).setWidth(35);
		}
		return jTable;
	}

	@Override
	public TableCellRenderer getStatusCellRenderer(int row, int column) {
		if (statusCellRenderer==null){
			statusCellRenderer = new ImageCellRenderer(true);
		}
		if (column == 0){
			BaugruppeModelKnotenBean baugruppeModelKnotenBean = (BaugruppeModelKnotenBean) BaugruppeListPaneView.this.getiModel().getModelBeanList().get(row);
			Icon icon = Run.createCompoundIcon(Konstanten.ICON_BAUGRUPPE);
			statusCellRenderer.setIcon(icon);
		}
		return statusCellRenderer;
	}

	@Override
	public IModel getiModel() {
		return iModel;
	}


	
}
