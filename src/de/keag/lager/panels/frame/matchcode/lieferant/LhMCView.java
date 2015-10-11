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
