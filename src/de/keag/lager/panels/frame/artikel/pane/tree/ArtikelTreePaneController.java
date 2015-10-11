package de.keag.lager.panels.frame.artikel.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.IConroller;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;

public class ArtikelTreePaneController extends TreeController{
	private ArtikelTreePaneView HalleTreePane = null;
	private static ArtikelTreePaneController benutzerSuchPaneController = null;
	
	private ArtikelTreePaneController() {
		super();
	}

	public static ArtikelTreePaneController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new ArtikelTreePaneController();
			ArtikelPaneController.getOneInstance().addTreeBeobachter(benutzerSuchPaneController.getHalleTreePane());
		}
		return benutzerSuchPaneController;
	}

	public ArtikelTreePaneView getHalleTreePane() {
		if(HalleTreePane==null){
			HalleTreePane = new ArtikelTreePaneView(this);
		}
		return HalleTreePane;
	}

	/**
	 * der im Tree vom Benutzer gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	@Override
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		ArtikelPaneController.getOneInstance().selectKnoten(selectedModelKnotenBean);
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
		return ArtikelPaneController.getOneInstance().setzeNeuenAktivenController(this);

	}

	@Override
	public void setStandardFocusPosition() {
		//getHalleTreePane().setStandardFocusPosition();
		ArtikelPaneController.getOneInstance().benachrichtigeTreeBeobachter();
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
