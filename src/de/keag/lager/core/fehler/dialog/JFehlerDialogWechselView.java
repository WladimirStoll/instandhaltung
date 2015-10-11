package de.keag.lager.core.fehler.dialog;

import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.liste.BaListView;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Insets;
import java.util.ArrayList;

public class JFehlerDialogWechselView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3053800957592084294L;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JFehlerDialogWechselModel model = null;  //  @jve:decl-index=0:
	private JFehlerDialogWechselController controller = null;
	private DefaultTableModel defaultTableModel = null;
	private JPanel jPanel = null;
	private JTextPane jTextPane = null;
	private ListSelectionListener listSelectionListener;  //  @jve:decl-index=0:
	/**
	 * This method initializes 
	 * @param jFehlerDialogWechselController 
	 * @param jFehlerDialogWechselModel 
	 * 
	 */
	public JFehlerDialogWechselView(JFehlerDialogWechselController jFehlerDialogWechselController, JFehlerDialogWechselModel jFehlerDialogWechselModel) {
		super();
		setController(jFehlerDialogWechselController);
		setModel(jFehlerDialogWechselModel);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setModal(true);
		this.setContentPane(getJPanel());
        this.setTitle("Fehler: Der Vorgang kann nicht fortgesetzt werden");
        this.setSize(new Dimension(700, 200));
        this.setContentPane(getJPanel());
		Run.setDialogPosition(Run.getOneInstance().getMainFrame(),this);
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
			jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable.setModel(getDefaultTableModel());
			jTable.getSelectionModel().addListSelectionListener(getListSelectionListener());
		}
		return jTable;
	}

	public void showView() {
		zeigeFehlerInhalt(getJTable().getSelectionModel());
		this.repaint();//alte Komponenten werden gelöscht
		this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
		this.validate();   //werden gezeichnet.
		this.setVisible(true);
//		this.getRootPane().revalidate(); //Layout-Manager tut seinen JOB, und richtet				this.invalidate();
	}

	private JFehlerDialogWechselModel getModel() {
		return model;
	}

	private void setModel(JFehlerDialogWechselModel model) {
		this.model = model;
	}

	private JFehlerDialogWechselController getController() {
		return controller;
	}

	private void setController(JFehlerDialogWechselController controller) {
		this.controller = controller;
	}

	private DefaultTableModel getDefaultTableModel() {
		if (defaultTableModel == null) {
			defaultTableModel = new DefaultTableModel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = -551537083596905961L;
				private String[] baColumnNames = { "Fehler-ID",
						"Fehlermeldung"};

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					switch (columnIndex) {
					case 0:
						return getModel().getErrors().get(rowIndex).getId();
					case 1:
						return getModel().getErrors().get(rowIndex).getMessage();
					case Konstanten.COLUMN_COUNT_BIG: {
						if (rowIndex>=0 && getModel()!=null && getModel().getErrors().size()>0){
							if (getModel()!=null && getModel().getErrors()!=null){
								return  getModel().getErrors().get(rowIndex);
							}else{
								return null;
							}
						}else{
							return null;
						}
					}
					default:
						return "Fehler!!"; // Spaltennummer ist nicht bekannt.
					}
				}

				@Override
				public int getRowCount() {
					if (getModel()==null) return 0;
					if (getModel().getErrors()==null) return 0;
					return getModel().getErrors().size();
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

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints1.gridheight = 1;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 3.0D;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 3.0D;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJTextPane(), gridBagConstraints1);
			jPanel.add(getJScrollPane(), gridBagConstraints);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setPreferredSize(new Dimension(80, 200));
			jTextPane.setEditable(false);
		}
		return jTextPane;
	}
	
	private ListSelectionListener getListSelectionListener() {
		if (listSelectionListener == null) {
			listSelectionListener = new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					zeigeFehlerInhalt(getJTable().getSelectionModel());
				};
			};
		}
		return listSelectionListener;
	}
	
	private void zeigeFehlerInhalt(ListSelectionModel lsm){
		int aktuelleZeile = lsm.getMinSelectionIndex();
		Fehler fehler = (Fehler)getJTable().getModel().getValueAt(aktuelleZeile, Konstanten.COLUMN_COUNT_BIG);
		if (fehler!=null){
			getJTextPane().setText(fehler.toString());
		}else{
			getJTextPane().setText("--");
		}
	}

	
}
