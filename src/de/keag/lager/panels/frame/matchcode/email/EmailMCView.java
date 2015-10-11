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
package de.keag.lager.panels.frame.matchcode.email;

import javax.swing.JPanel;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.email.EmailBean;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JComboBox;

public class EmailMCView extends JDialog implements IEmailMCBeobachter {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private EmailMCController mCController;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModel;
	private IEmailMCModel iMCModel;  //  @jve:decl-index=0:
	private JPanel jPanelSuche = null;
	private JToolBar jToolBarSuche = null;
	private JButton jButtonAuswahlEinschraenken = null;
	private JLabel jLabelKEG = null;
	private JLabel jLabelBezeichnung = null;
	private JLabel jLabelHersteller = null;
	private JLabel jLabelTyp = null;
	private JTextField jTextFieldKEG = null;
	private JTextField jTextFieldBezeichnung = null;
	private JTextField jTextFieldHersteller = null;
	private JTextField jTextFieldTyp = null;
	private JComboBox jComboBoxFehlerList = null;
	/**
	 * @param frame
	 */
	protected EmailMCView(Frame frame, EmailMCController mCController, IEmailMCModel iMCModel) {
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
		this.setSize(600, 400);
		this.setTitle("Auswahl  E-Mail");
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowClosing(java.awt.event.WindowEvent e) {    
//				getMCController().fensterIstGeschlossen();						
			}   
			public void windowDeactivated(java.awt.event.WindowEvent e) {    
//				getMCController().fensterIstGeschlossen();						
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
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 3;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.weightx = 0.0D;
			gridBagConstraints1.weighty = 0.0D;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.fill = GridBagConstraints.NONE;
			gridBagConstraints1.gridy = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.ipadx = -161;
			gridBagConstraints.ipady = -273;
			gridBagConstraints.weightx = 2.0D;
			gridBagConstraints.weighty = 2.0D;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.setVisible(true);
			jContentPane.add(getJScrollPane(), gridBagConstraints);
			jContentPane.add(getJPanelSuche(), gridBagConstraints1);
			jContentPane.add(getJComboBoxFehlerList(), gridBagConstraints21);
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
						//ausgewählte Bean wird ermittelt. 
						EmailBean bean = getiMCModel().getBeans().get(jTable.getSelectedRow());
						getMCController().setGewaehlteBean(bean);
						EmailMCView.this.setVisible(false);
						getMCController().fensterIstGeschlossen();
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

	private EmailMCController getMCController() {
		return mCController;
	}

	private void setMCController(
			EmailMCController lieferantMCController) {
		this.mCController = lieferantMCController;
	}

	private AbstractTableModel getAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private static final long serialVersionUID = 5323530232081860930L;
				private String[] columnNames = {"Email", "Beschreibung"}; 
				
				@Override
				public String getColumnName(int spalte) {
			        return columnNames[spalte].toString();
			    }
				
				@Override
				public Object getValueAt(int zeile, int spalte) {
					try{
						switch (spalte){
						case 0: return getiMCModel().getBeans().get(zeile).getEmail();  
						case 1: return getiMCModel().getBeans().get(zeile).getBeschreibung();  
						default: return "Fehler";
						}
					}catch(Exception e){
						return e.getMessage();
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

	private IEmailMCModel getiMCModel() {
		return iMCModel;
	}

	private void setiMCModel(IEmailMCModel iMCModel) {
		this.iMCModel = iMCModel;
	}

	@Override
	public void zeichneDich() {
		getJComboBoxFehlerList().removeAllItems();
		//Fehler ausgeben.
		for(int i=0; i<getiMCModel().getFehlerList().size();i++){
			Fehler fehler = getiMCModel().getFehlerList().get(i);
			getJComboBoxFehlerList().addItem(fehler);
		}
		//Auswahlkritereine werden angzeigt. Sie sind evtl. durch SQL-Formatierung geändert.
//		getJTextFieldBezeichnung().setText(getiMCModel().getSuchKriterien().getBezeichnung());
//		getJTextFieldHersteller().setText(getiMCModel().getSuchKriterien().getHersteller().getName());
//		getJTextFieldKEG().setText(getiMCModel().getSuchKriterien().getKeg_nr().toString());
//		getJTextFieldTyp().setText(getiMCModel().getSuchKriterien().getTyp());
		
		this.repaint();//alte Komponenten werden gelöscht
		this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
		this.validate();   //werden gezeichnet.
//		this.revalidate(); //Layout-Manager tut seinen JOB, und richtet				this.invalidate();
		
		getAbstractTableModel().fireTableDataChanged();
	}

	/**
	 * This method initializes jPanelSuche	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSuche() {
		if (jPanelSuche == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints10.gridy = 4;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints10.gridx = 1;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 3;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints8.gridy = 2;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints8.gridx = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 1;
			gridBagConstraints7.weightx = 2.0D;
			gridBagConstraints7.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridy = 4;
			jLabelTyp = new JLabel();
			jLabelTyp.setText("Typ");
			jLabelTyp.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.gridy = 3;
			jLabelHersteller = new JLabel();
			jLabelHersteller.setText("Hersteller");
			jLabelHersteller.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.gridy = 2;
			jLabelBezeichnung = new JLabel();
			jLabelBezeichnung.setText("Bezeichnung");
			jLabelBezeichnung.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.weightx = 1.0D;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints3.gridy = 1;
			jLabelKEG = new JLabel();
			jLabelKEG.setText("KEG");
			jLabelKEG.setHorizontalAlignment(SwingConstants.LEADING);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.weightx = 1.0;
			jPanelSuche = new JPanel();
			jPanelSuche.setLayout(new GridBagLayout());
			jPanelSuche.setVisible(false);
			jPanelSuche.add(getJToolBarSuche(), gridBagConstraints2);
			jPanelSuche.add(jLabelKEG, gridBagConstraints3);
			jPanelSuche.add(jLabelBezeichnung, gridBagConstraints4);
			jPanelSuche.add(jLabelHersteller, gridBagConstraints5);
			jPanelSuche.add(jLabelTyp, gridBagConstraints6);
			jPanelSuche.add(getJTextFieldKEG(), gridBagConstraints7);
			jPanelSuche.add(getJTextFieldBezeichnung(), gridBagConstraints8);
			jPanelSuche.add(getJTextFieldHersteller(), gridBagConstraints9);
			jPanelSuche.add(getJTextFieldTyp(), gridBagConstraints10);
		}
		return jPanelSuche;
	}

	/**
	 * This method initializes jToolBarSuche	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBarSuche() {
		if (jToolBarSuche == null) {
			jToolBarSuche = new JToolBar();
			jToolBarSuche.setFloatable(false);
			jToolBarSuche.add(getJButtonAuswahlEinschraenken());
		}
		return jToolBarSuche;
	}

	/**
	 * This method initializes jButtonAuswahlEinschraenken	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAuswahlEinschraenken() {
		if (jButtonAuswahlEinschraenken == null) {
			jButtonAuswahlEinschraenken = new JButton();
			jButtonAuswahlEinschraenken.setText("Auswahl einschränken");
			jButtonAuswahlEinschraenken
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							//Bezeichnung
//							getiMCModel().getSuchKriterien().setBezeichnung(getJTextFieldBezeichnung().getText());
//							//Hersteller
//							LhBean lhBean = new LhBean();
//							lhBean.setName(getJTextFieldHersteller().getText());
//							getiMCModel().getSuchKriterien().setHersteller(lhBean);
//							//KEG
//							Integer keg_nr;
//							try{
//								keg_nr = new Integer(getJTextFieldKEG().getText());
//							}catch(Exception ex){
//								keg_nr = 0;
//							}
//							getiMCModel().getSuchKriterien().setKeg_nr(keg_nr);
//							//Typ
//							getiMCModel().getSuchKriterien().setTyp(getJTextFieldTyp().getText());
//							//Suchen!
//							getMCController().holeBeansNachSuchKriterien(getiMCModel().getSuchKriterien());
						}
					});
		}
		return jButtonAuswahlEinschraenken;
	}

	/**
	 * This method initializes jTextFieldKEG	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldKEG() {
		if (jTextFieldKEG == null) {
			jTextFieldKEG = new JTextField();
			jTextFieldKEG.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldKEG;
	}

	/**
	 * This method initializes jTextFieldBezeichnung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBezeichnung() {
		if (jTextFieldBezeichnung == null) {
			jTextFieldBezeichnung = new JTextField();
			jTextFieldBezeichnung.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldBezeichnung;
	}

	/**
	 * This method initializes jTextFieldHersteller	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldHersteller() {
		if (jTextFieldHersteller == null) {
			jTextFieldHersteller = new JTextField();
			jTextFieldHersteller.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldHersteller;
	}

	/**
	 * This method initializes jTextFieldTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldTyp() {
		if (jTextFieldTyp == null) {
			jTextFieldTyp = new JTextField();
			jTextFieldTyp.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldTyp;
	}

	/**
	 * This method initializes jComboBoxFehlerList	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxFehlerList() {
		if (jComboBoxFehlerList == null) {
			jComboBoxFehlerList = new JComboBox();
		}
		return jComboBoxFehlerList;
	}


}
