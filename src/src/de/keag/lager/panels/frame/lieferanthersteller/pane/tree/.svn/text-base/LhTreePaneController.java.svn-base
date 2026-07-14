package de.keag.lager.panels.frame.lieferanthersteller.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.lieferanthersteller.pane.LhPaneController;

public class LhTreePaneController extends TreeController{
	private LhTreePaneView LhTreePane = null;
	private static LhTreePaneController lhSuchPaneController = null;
	
	private LhTreePaneController() {
		super();
	}

	public static LhTreePaneController getOneInstance() {
		if(lhSuchPaneController==null){
			lhSuchPaneController = new LhTreePaneController();
			LhPaneController.getOneInstance().addTreeBeobachter(lhSuchPaneController.getLhTreePane());
		}
		return lhSuchPaneController;
	}

	public LhTreePaneView getLhTreePane() {
		if(LhTreePane==null){
			LhTreePane = new LhTreePaneView(this);
		}
		return LhTreePane;
	}

	/**
	 * der im Tree vom Lh gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	@Override
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		LhPaneController.getOneInstance().selectKnoten(selectedModelKnotenBean);
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
		return LhPaneController.getOneInstance().setzeNeuenAktivenController(this);

	}

	@Override
	public void setStandardFocusPosition() {
		//getLhTreePane().setStandardFocusPosition();
		LhPaneController.getOneInstance().benachrichtigeTreeBeobachter();
	}

	@Override
	public void setBorder(Boolean aktiv) {
		getLhTreePane().setBorder(aktiv);
		
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
