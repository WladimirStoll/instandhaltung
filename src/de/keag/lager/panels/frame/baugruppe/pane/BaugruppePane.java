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
package de.keag.lager.panels.frame.baugruppe.pane;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JSplitPane;

import de.keag.lager.core.IModel;
import de.keag.lager.core.IPanel;
import de.keag.lager.core.fehler.BaugruppeOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.MainFrame;
import de.keag.lager.panels.frame.baugruppe.pane.liste.BaugruppeListPaneView;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class BaugruppePane extends JPanel implements IPanel  {

	private JSplitPane jSplitPaneBasic = null;
	private JSplitPane jSplitPaneRechtsBasic = null;
	private JPanel jPanelTreeBasic = null;
	private JPanel jPanelDetailsBasic = null;
	private JSplitPane jSplitPaneLinksBasic = null;
	private JPanel jPanelListBasic = null;
	private JPanel jPanelSuchBasic = null;
	private BaugruppePaneController baugruppePaneController = null;  //  @jve:decl-index=0:
	private BaugruppeListPaneView baugruppeListPane;
	private IModel iBaugruppeModel  = null;  //  @jve:decl-index=0:
	/**
	 * This method initializes 
	 * @param baugruppePaneController 
	 * 
	 */
	protected BaugruppePane(BaugruppePaneController baugruppePaneController, IModel iBaugruppeModel) {
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
//		getJPanelListBasic().setPreferredSize(
//				new Dimension(
//						Run.getOneInstance().getMainFrame().getWidth() / 3, 
//						Run.getOneInstance().getMainFrame().getHeight() * 2 / 3)
//				);
//		getJPanelSuchBasic().setPreferredSize(
//				new Dimension(
//						Run.getOneInstance().getMainFrame().getWidth() / 3, 
//						Run.getOneInstance().getMainFrame().getHeight() * 2 / 3)
//				);
//		getJPanelTreeBasic().setPreferredSize(
//				new Dimension(
//						Run.getOneInstance().getMainFrame().getWidth() / 6, 
//						Run.getOneInstance().getMainFrame().getHeight())
//				);
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
	        jPanelTreeBasic.add(BaugruppePaneController.getOneInstance().getTreePane(), gridBagConstraints); //mit konstains
			
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
			jPanelListBasic.add(BaugruppePaneController.getOneInstance().getListPane(), gridBagConstraints);
			
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
			jPanelSuchBasic.add(BaugruppePaneController.getOneInstance().getSuchView(), gridBagConstraints); //mit konstains
			
		}
		return jPanelSuchBasic;
	}

	@Override
	public void setContentPane(MainFrame mainFrame) {
		mainFrame.setTitle(Konstanten.UEBERSCHRIFT_ANWENDUNG + Konstanten.SLASH + Konstanten.UEBERSCHRIFT_BAUGRUPPE);
		mainFrame.getJMenuBar().removeAll();
		mainFrame.setContentPane(this);
		mainFrame.getJMenuBar().setVisible(false);
		Zugriffsrechtpruefung.getOneInstance().jComponentCheck(mainFrame);
		mainFrame.validate();
	}

	private BaugruppePaneController getBaugruppePaneController() {
		return baugruppePaneController;
	}

	private void setBaugruppePaneController(BaugruppePaneController baugruppePaneController) {
		this.baugruppePaneController = baugruppePaneController;
	}

	protected BaugruppeListPaneView getBaugruppeListPane() throws BaugruppeOberflacheLagerException{
		if(baugruppeListPane==null){
			BaugruppePaneController.getOneInstance().getListPane();
//			throw new BaugruppeOberflacheLagerException(new Fehler(12,FehlerTyp.FEHLER,"getBaListPane()"));
		}
		return baugruppeListPane;
	}

	public void setBlatt(JPanel baugruppeBlatt) {
		//bisherige Inhalte löschen
		getJPanelDetailsBasic().removeAll();
		//Positionierung von baBlatt wird vorbereitet.
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        gridBagConstraints.fill = GridBagConstraints.BOTH;	
        //baBlatt wird angezeigt.
		getJPanelDetailsBasic().add(baugruppeBlatt,gridBagConstraints);
		getJPanelDetailsBasic().validate();
	}

	private IModel getiBaugruppeModel() {
		return iBaugruppeModel;
	}

	private void setiBaugruppeModel(IModel iBaugruppeModel) {
		this.iBaugruppeModel = iBaugruppeModel;
	}

}  //  @jve:decl-index=0:visual-constraint="0,0"
