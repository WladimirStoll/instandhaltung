package de.keag.lager.panels.frame.matchcode.baugruppe;

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
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class BaugruppeMCView extends JDialog implements IBaugruppeMCBeobachter {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private BaugruppeMCController mCController;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModel;
	private IBaugruppeMCModel iMCModel;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehlerList = null;
	private JPanel jPanelSuche = null;
	private JToolBar jToolBarSuche = null;
	private JButton jButtonAuswahlEinschraenken = null;
	private JLabel jLabelName = null;
	private JTextField jTextFieldName = null;
	/**
	 * @param frame
	 */
	protected BaugruppeMCView(Frame frame, BaugruppeMCController mCController, IBaugruppeMCModel iMCModel) {
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
		this.setTitle("Auswahl Baugruppe");
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
			jContentPane.add(getJComboBoxFehlerList(), gridBagConstraints21);
			jContentPane.add(getJPanelSuche(), new GridBagConstraints());
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
						BaugruppeBean bean = getiMCModel().getBeans().get(jTable.getSelectedRow());
						getMCController().setGewaehlteBean(bean);
						BaugruppeMCView.this.setVisible(false);
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

	private BaugruppeMCController getMCController() {
		return mCController;
	}

	private void setMCController(
			BaugruppeMCController lieferantMCController) {
		this.mCController = lieferantMCController;
	}

	private AbstractTableModel getAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private static final long serialVersionUID = 5323530208186092130L;
				private String[] columnNames = {"Übergeordnete Baugruppen","Anlage/Baugruppe"}; 
				
				@Override
				public String getColumnName(int spalte) {
			        return columnNames[spalte].toString();
			    }
				
				@Override
				public Object getValueAt(int zeile, int spalte) {
					try{
						switch (spalte){
						case 0: {
							if (!getiMCModel().getBeans().get(zeile).getName().isEmpty()){
								if (getiMCModel().getBeans().get(zeile).getVaterBaugruppe().getId().equals(0)){
									return "";  
								}else{
//									return getiMCModel().getBeans().get(zeile).getVaterBaugruppe().getName();  
									return getiMCModel().getBeans().get(zeile).getVaterBaugruppeNamenListe();
								}
							} else return "";
						}
						case 1: {
							if (!getiMCModel().getBeans().get(zeile).getName().isEmpty()){
								if (getiMCModel().getBeans().get(zeile).getVaterBaugruppe().getId().equals(0)){
									return getiMCModel().getBeans().get(zeile).getName();  //Anlage
								}else{
									return getiMCModel().getBeans().get(zeile).getName(); //"Baugruppe "    
								}
							} else return "";
						}
						
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

	private IBaugruppeMCModel getiMCModel() {
		return iMCModel;
	}

	private void setiMCModel(IBaugruppeMCModel iMCModel) {
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
//		getJTextFieldBezeichnung().setText(getiMCModel().getSuchKriterien().getName());
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

	/**
	 * This method initializes jPanelSuche	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSuche() {
		if (jPanelSuche == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.gridy = 2;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.insets = new Insets(1, 1, 1, 1);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.gridx = 0;
			jLabelName = new JLabel();
			jLabelName.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelName.setText("Anlage/Baugruppe");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
			jPanelSuche = new JPanel();
			jPanelSuche.setLayout(new GridBagLayout());
			jPanelSuche.add(getJToolBarSuche(), gridBagConstraints2);
			jPanelSuche.add(jLabelName, gridBagConstraints4);
			jPanelSuche.add(getJTextFieldName(), gridBagConstraints8);
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
							//Name
							getiMCModel().getSuchKriterien().setBaugruppeName(getJTextFieldName().getText());
							//Hersteller
//							LhBean lhBean = new LhBean();
//							lhBean.setName(getJTextFieldHersteller().getText());
//							getiMCModel().getSuchKriterien().setHersteller(lhBean);
							//KEG
//							Integer keg_nr;
//							try{
//								keg_nr = new Integer(getJTextFieldKEG().getText());
//							}catch(Exception ex){
//								keg_nr = 0;
//							}
//							getiMCModel().getSuchKriterien().setKeg_nr(keg_nr);
							//Typ
//							getiMCModel().getSuchKriterien().setTyp(getJTextFieldTyp().getText());
							//Suchen!
							try {
								getMCController().holeBeansNachSuchKriterien(getiMCModel().getSuchKriterien());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (LagerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
		}
		return jButtonAuswahlEinschraenken;
	}
	/**
	 * This method initializes jTextFieldName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldName() {
		if (jTextFieldName == null) {
			jTextFieldName = new JTextField();
			jTextFieldName.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldName;
	}


}  //  @jve:decl-index=0:visual-constraint="9,30"
