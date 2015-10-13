package de.keag.lager.panels.frame.halle.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.IConroller;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;

public class HalleTreePaneController extends TreeController{
	private HalleTreePaneView HalleTreePane = null;
	private static HalleTreePaneController benutzerSuchPaneController = null;
	
	private HalleTreePaneController() {
		super();
	}

	public static HalleTreePaneController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new HalleTreePaneController();
			HallePaneController.getOneInstance().addTreeBeobachter(benutzerSuchPaneController.getHalleTreePane());
		}
		return benutzerSuchPaneController;
	}

	public HalleTreePaneView getHalleTreePane() {
		if(HalleTreePane==null){
			HalleTreePane = new HalleTreePaneView(this);
		}
		return HalleTreePane;
	}

	/**
	 * der im Tree vom Benutzer gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	@Override
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		HallePaneController.getOneInstance().selectKnoten(selectedModelKnotenBean);
	}
	
	/**
	 * TreeController hat nie Fehler 
	 */
	@Override
	public ArrayList<Fehler> pruefeDich() {
		return new ArrayList<Fehler>();
	}

	@Override
	public ArrayList<Fehler> setzeNeuenAktivenController() {
//		return Run.getOneInstance().getMainFrame().setzeNeuenAktivenController(this);
		return HallePaneController.getOneInstance().setzeNeuenAktivenController(this);

	}

	@Override
	public void setStandardFocusPosition() {
		//getHalleTreePane().setStandardFocusPosition();
		HallePaneController.getOneInstance().benachrichtigeTreeBeobachter();
	}

	@Override
	public void setBorder(Boolean aktiv) {
		getHalleTreePane().setBorder(aktiv);
		
	}

	@Override
	public TreeView getTreeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaneController getPaneController() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
