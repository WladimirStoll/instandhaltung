package de.keag.lager.panels.frame.matchcode.kostenstelle;

import javax.swing.JPanel;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class KostenstelleMCView extends JDialog implements IKostenstelleMCBeobachter {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private KostenstelleMCController mCController;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModel;
	private IKostenstelleMCModel iMCModel;  //  @jve:decl-index=0:
	/**
	 * @param frame
	 */
	protected KostenstelleMCView(Frame frame, KostenstelleMCController mCController, IKostenstelleMCModel iMCModel) {
		super(frame);
		this.setModal(true);
		setMCController(mCController);
		setiMCModel(iMCModel);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Auswahl  Kostenstelle");
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowDeactivated(java.awt.event.WindowEvent e) {    
				getMCController().fensterIstGeschlossen();						
			}
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
						KostenstelleBean bean = getiMCModel().getBeans().get(jTable.getSelectedRow());
						getMCController().setGewaehlterLieferant(bean);
						KostenstelleMCView.this.setVisible(false);
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

	private KostenstelleMCController getMCController() {
		return mCController;
	}

	private void setMCController(
			KostenstelleMCController lieferantMCController) {
		this.mCController = lieferantMCController;
	}

	private AbstractTableModel getAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = {"Name","Nummer"}; 
				
				@Override
				public String getColumnName(int col) {
			        return columnNames[col].toString();
			    }
				
				@Override
				public Object getValueAt(int row, int col) {
					switch (col){
					case 0: return getiMCModel().getBeans().get(row).getName();  
					case 1: return getiMCModel().getBeans().get(row).getNummer().toString();
					default: return "Fehler";
					}
				}				
				
				@Override
				public int getRowCount() {
					return getiMCModel().getBeans().size();
				}
				
				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModel;
	}

	private IKostenstelleMCModel getiMCModel() {
		return iMCModel;
	}

	private void setiMCModel(IKostenstelleMCModel iMCModel) {
		this.iMCModel = iMCModel;
	}

	@Override
	public void zeichneDich() {
		getAbstractTableModel().fireTableDataChanged();
	}


}
