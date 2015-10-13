package de.keag.lager.panels.frame.benutzer.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.IConroller;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;

public class BenutzerTreePaneController extends TreeController{
	private BenutzerTreePaneView BenutzerTreePane = null;
	private static BenutzerTreePaneController benutzerSuchPaneController = null;
	
	private BenutzerTreePaneController() {
		super();
	}

	public static BenutzerTreePaneController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new BenutzerTreePaneController();
			BenutzerPaneController.getOneInstance().addTreeBeobachter(benutzerSuchPaneController.getBenutzerTreePane());
		}
		return benutzerSuchPaneController;
	}

	public BenutzerTreePaneView getBenutzerTreePane() {
		if(BenutzerTreePane==null){
			BenutzerTreePane = new BenutzerTreePaneView(this);
		}
		return BenutzerTreePane;
	}

	/**
	 * der im Tree vom Benutzer gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	@Override
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		BenutzerPaneController.getOneInstance().selectKnoten(selectedModelKnotenBean);
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
		return BenutzerPaneController.getOneInstance().setzeNeuenAktivenController(this);

	}

	@Override
	public void setStandardFocusPosition() {
		//getBenutzerTreePane().setStandardFocusPosition();
		BenutzerPaneController.getOneInstance().benachrichtigeTreeBeobachter();
	}

	@Override
	public void setBorder(Boolean aktiv) {
		getBenutzerTreePane().setBorder(aktiv);
		
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
