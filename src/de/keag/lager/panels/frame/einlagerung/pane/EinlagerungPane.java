package de.keag.lager.panels.frame.einlagerung.pane;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.IListBeobachter;
import de.keag.lager.core.IModel;
import de.keag.lager.core.IPanel;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.MainFrame;
import de.keag.lager.panels.frame.einlagerung.pane.liste.EinlagerungListView;
import de.keag.lager.panels.frame.einlagerung.pane.liste.EinlagerungListController;
import de.keag.lager.panels.frame.einlagerung.pane.suche.EinlagerungSuchController;
import de.keag.lager.panels.frame.einlagerung.pane.tree.EinlagerungTreeController;
import de.keag.lager.panels.frame.einlagerung.pane.tree.EinlagerungTreeView;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class EinlagerungPane extends JPanel implements IPanel  {

	private JSplitPane jSplitPaneBasic = null;
	private JSplitPane jSplitPaneRechtsBasic = null;
	private JPanel jPanelTreeBasic = null;
	private JPanel jPanelDetailsBasic = null;
	private JSplitPane jSplitPaneLinksBasic = null;
	private JPanel jPanelListBasic = null;
	private JPanel jPanelSuchBasic = null;
	private EinlagerungPaneController einlagerungPaneController = null;
	private EinlagerungListView einlagerungListPane;
	private IModel iModel  = null; 
	/**
	 * This method initializes 
	 * @param baPaneController 
	 * 
	 */
	protected EinlagerungPane(EinlagerungPaneController einlagerungPaneController, IModel iModel) {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(1);
        gridLayout.setHgap(0);
        gridLayout.setColumns(1);
        this.setLayout(gridLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(getJSplitPaneBasic(), gridBagConstraints);
		definePaneSize(); //Größen werden definiert. 
	}

	private void definePaneSize() {
		getJSplitPaneBasic().setDividerLocation(Run.getOneInstance().getMainFrame().getWidth()/3);
		getJSplitPaneLinksBasic().setDividerLocation(Run.getOneInstance().getMainFrame().getHeight()*2/3);
		getJSplitPaneRechtsBasic().setDividerLocation(Run.getOneInstance().getMainFrame().getHeight()*1/5);
	}

	/**
	 * This method initializes jSplitPaneBasic	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPaneBasic() {
		if (jSplitPaneBasic == null) {
			jSplitPaneBasic = new JSplitPane();
			jSplitPaneBasic.setLeftComponent(getJSplitPaneLinksBasic());
			jSplitPaneBasic.setRightComponent(getJSplitPaneRechtsBasic());
			getJSplitPaneLinksBasic().setMinimumSize(new Dimension(100, 100));
			getJSplitPaneRechtsBasic().setMinimumSize(new Dimension(100, 100));
		}
		return jSplitPaneBasic;
	}

	/**
	 * This method initializes jSplitPaneRechtsBasic	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPaneRechtsBasic() {
		if (jSplitPaneRechtsBasic == null) {
			jSplitPaneRechtsBasic = new JSplitPane();
			jSplitPaneRechtsBasic.setLeftComponent(getJPanelTreeBasic());
			jSplitPaneRechtsBasic.setRightComponent(getJPanelDetailsBasic());
			getJPanelTreeBasic().setMinimumSize(new Dimension(100, 100));
			getJPanelDetailsBasic().setMinimumSize(new Dimension(100, 100));
			jSplitPaneRechtsBasic.setDividerLocation(0.25);
		}
		return jSplitPaneRechtsBasic;
	}

	/**
	 * This method initializes jPanelTreeBasic	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTreeBasic() {
		if (jPanelTreeBasic == null) {
			jPanelTreeBasic = new JPanel();
			jPanelTreeBasic.setLayout(new GridBagLayout());

	        GridBagConstraints gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	        gridBagConstraints.weightx = 1.0D;
	        gridBagConstraints.weighty = 1.0D;
	        gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			//Tree hinzufügen.
	        jPanelTreeBasic.add(EinlagerungPaneController.getOneInstance().getTreePane(), gridBagConstraints); //mit konstains
			
		}
		return jPanelTreeBasic;
	}

	/**
	 * This method initializes jPanelDetailsBasic	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDetailsBasic() {
		if (jPanelDetailsBasic == null) {
			jPanelDetailsBasic = new JPanel();
			jPanelDetailsBasic.setLayout(new GridBagLayout());
		}
		return jPanelDetailsBasic;
	}

	/**
	 * This method initializes jSplitPaneLinksBasic	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPaneLinksBasic() {
		if (jSplitPaneLinksBasic == null) {
			jSplitPaneLinksBasic = new JSplitPane();
			jSplitPaneLinksBasic.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPaneLinksBasic.setTopComponent(getJPanelListBasic());
			jSplitPaneLinksBasic.setBottomComponent(getJPanelSuchBasic());
			getJPanelListBasic().setMinimumSize(new Dimension(100,100));
			getJPanelSuchBasic().setMinimumSize(new Dimension(100,100));
		}
		return jSplitPaneLinksBasic;
	}

	/**
	 * This method initializes jPanelListBasic	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelListBasic() {
		if (jPanelListBasic == null) {
			jPanelListBasic = new JPanel();
			jPanelListBasic.setLayout(new GridBagLayout());
			
			//konstrains für die Liste
	        GridBagConstraints gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.weightx = 1.0D;
	        gridBagConstraints.weighty = 1.0D;
	        gridBagConstraints.fill = GridBagConstraints.BOTH;
	        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
			
			//Liste hinzufügen.
			jPanelListBasic.add(EinlagerungPaneController.getOneInstance().getListPane(), gridBagConstraints);
			
		}
		return jPanelListBasic;
	}

	/**
	 * This method initializes jPanelSuchBasic	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSuchBasic() {
		if (jPanelSuchBasic == null) {
			jPanelSuchBasic = new JPanel();
			jPanelSuchBasic.setLayout(new GridBagLayout());
			
			//konstrains für den SuchPane
	        GridBagConstraints gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.weightx = 1.0D;
	        gridBagConstraints.weighty = 1.0D;
	        gridBagConstraints.fill = GridBagConstraints.BOTH;
	        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	        
			//SuchPane hinzufügen.
			jPanelSuchBasic.add(EinlagerungPaneController.getOneInstance().getSuchView(), gridBagConstraints); //mit konstains
			
		}
		return jPanelSuchBasic;
	}

	@Override
	public void setContentPane(MainFrame mainFrame) {
		mainFrame.setTitle(Konstanten.UEBERSCHRIFT_ANWENDUNG + Konstanten.SLASH + Konstanten.UEBERSCHRIFT_EINLAGERUNG);
		mainFrame.getJMenuBar().removeAll();
		mainFrame.setContentPane(this);
		mainFrame.getJMenuBar().setVisible(false);
		Zugriffsrechtpruefung.getOneInstance().jComponentCheck(mainFrame);
		mainFrame.validate();
		
	}

	private EinlagerungPaneController getEinlagerungPaneController() {
		return einlagerungPaneController;
	}

	private void setEinlagerungPaneController(EinlagerungPaneController einlagerungPaneController) {
		this.einlagerungPaneController = einlagerungPaneController;
	}

	protected EinlagerungListView getEinlagerungListPane() throws BenutzerOberflacheLagerException{
		if(einlagerungListPane==null){
			EinlagerungPaneController.getOneInstance().getListPane();
//			throw new BenutzerOberflacheLagerException(new Fehler(12,FehlerTyp.FEHLER,"getEinlagerungListPane()"));
		}
		return einlagerungListPane;
	}

	public void setBlatt(JPanel einlagerungBlatt) {
		//bisherige Inhalte löschen
		getJPanelDetailsBasic().removeAll();
		//Positionierung von baBlatt wird vorbereitet.
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        gridBagConstraints.fill = GridBagConstraints.BOTH;	
        //baBlatt wird angezeigt.
		getJPanelDetailsBasic().add(einlagerungBlatt,gridBagConstraints);
		getJPanelDetailsBasic().validate();
	}

	private IModel getiModel() {
		return iModel;
	}

	private void setiEinlagerungModel(IModel iEinlagerungModel) {
		this.iModel = iEinlagerungModel;
	}

}  //  @jve:decl-index=0:visual-constraint="0,0"
