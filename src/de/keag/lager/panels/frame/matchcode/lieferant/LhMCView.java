package de.keag.lager.panels.frame.matchcode.lieferant;

import javax.swing.JPanel;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;


import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

public class LhMCView extends JDialog implements ILhMCBeobachter{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private LhMCController lieferantMCController;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModel;
	private ILhMCModel iLhMCModel;  //  @jve:decl-index=0:
	/**
	 * @param frame
	 */
	public LhMCView(Frame frame, LhMCController lieferantMCController, ILhMCModel iLhMCModel) {
		super(frame);
		this.setModal(true);
		setLieferantMCController(lieferantMCController);
		setiLhMCModel(iLhMCModel);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Auswahl  Lieferant / Hersteller");
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowDeactivated(java.awt.event.WindowEvent e) {    
				getLieferantMCController().fensterIstGeschlossen();						
			}
//			public void windowClosing(java.awt.event.WindowEvent e) {
////				getLieferantMCController().fensterIstGeschlossen();						
//			}
		});
		Run.setDialogPosition(getParent(),this);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.ipadx = -161;
			gridBagConstraints.ipady = -273;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints);
		}
		return jContentPane;
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
			jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable.setRowSelectionAllowed(true);
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==2){
						//ausgewählter Lieferant wird ermittelt. 
						LhBean lhBean = getiLhMCModel().getLhBeans().get(jTable.getSelectedRow());
						getLieferantMCController().setGewaehlterLieferant(lhBean);
						LhMCView.this.setVisible(false);
					};
				}
			});
			jTable.getSelectionModel().addListSelectionListener(getSelectionListener());
		}
		return jTable;
	}

	private ListSelectionListener getSelectionListener() {
		return new  ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//todo
			}
		};
	}

	private LhMCController getLieferantMCController() {
		return lieferantMCController;
	}

	private void setLieferantMCController(
			LhMCController lieferantMCController) {
		this.lieferantMCController = lieferantMCController;
	}

	@Override
	public void zeichneDich() {
		getAbstractTableModel().fireTableDataChanged();
	}

	private AbstractTableModel getAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = {"Name"}; 
				
				@Override
				public String getColumnName(int col) {
			        return columnNames[col].toString();
			    }
				
				@Override
				public Object getValueAt(int row, int col) {
					return getiLhMCModel().getLhBeans().get(row).getName();
				}				
				
				@Override
				public int getRowCount() {
					return getiLhMCModel().getLhBeans().size();
				}
				
				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModel;
	}

	private ILhMCModel getiLhMCModel() {
		return iLhMCModel;
	}

	private void setiLhMCModel(ILhMCModel iLhMCModel) {
		this.iLhMCModel = iLhMCModel;
	}


}
