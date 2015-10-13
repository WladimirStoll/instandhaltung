package de.keag.lager.panels.frame.katalog.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.katalog.pane.KatalogPaneController;

public class KatalogTreeController extends TreeController{
	private TreeView treeView = null;
	private static KatalogTreeController einlagerungSuchPaneController = null;
	
	private KatalogTreeController() {
		super();
	}

	public static KatalogTreeController getOneInstance() {
		if(einlagerungSuchPaneController==null){
			einlagerungSuchPaneController = new KatalogTreeController();
			KatalogPaneController.getOneInstance().addTreeBeobachter((KatalogTreeView)einlagerungSuchPaneController.getTreeView());
		}
		return einlagerungSuchPaneController;
	}

	@Override
	public TreeView getTreeView() {
		if(treeView==null){
			treeView = new KatalogTreeView(this);
		}
		return treeView;
	}

	@Override
	public PaneController getPaneController(){
		return KatalogPaneController.getOneInstance();
	}


	
}
