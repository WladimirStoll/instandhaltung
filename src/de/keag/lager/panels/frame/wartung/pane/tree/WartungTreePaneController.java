package de.keag.lager.panels.frame.wartung.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.IConroller;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;

public class WartungTreePaneController extends TreeController{
	private WartungTreePaneView WartungTreePane = null;
	private static WartungTreePaneController benutzerSuchPaneController = null;
	
	private WartungTreePaneController() {
		super();
	}

	public static WartungTreePaneController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new WartungTreePaneController();
			WartungPaneController.getOneInstance().addTreeBeobachter(benutzerSuchPaneController.getWartungTreePane());
		}
		return benutzerSuchPaneController;
	}

	public WartungTreePaneView getWartungTreePane() {
		if(WartungTreePane==null){
			WartungTreePane = new WartungTreePaneView(this);
		}
		return WartungTreePane;
	}

	/**
	 * der im Tree vom Benutzer gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	@Override
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		WartungPaneController.getOneInstance().selectKnoten(selectedModelKnotenBean);
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
		return WartungPaneController.getOneInstance().setzeNeuenAktivenController(this);

	}

	@Override
	public void setStandardFocusPosition() {
		//getWartungTreePane().setStandardFocusPosition();
		WartungPaneController.getOneInstance().benachrichtigeTreeBeobachter();
	}

	@Override
	public void setBorder(Boolean aktiv) {
		getWartungTreePane().setBorder(aktiv);
		
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
