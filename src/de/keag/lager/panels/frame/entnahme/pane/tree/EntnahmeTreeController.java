package de.keag.lager.panels.frame.entnahme.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.entnahme.pane.EntnahmePaneController;

public class EntnahmeTreeController extends TreeController{
	private TreeView treeView = null;
	private static EntnahmeTreeController entnahmeSuchPaneController = null;
	
	private EntnahmeTreeController() {
		super();
	}

	public static EntnahmeTreeController getOneInstance() {
		if(entnahmeSuchPaneController==null){
			entnahmeSuchPaneController = new EntnahmeTreeController();
			EntnahmePaneController.getOneInstance().addTreeBeobachter((EntnahmeTreeView)entnahmeSuchPaneController.getTreeView());
		}
		return entnahmeSuchPaneController;
	}

	@Override
	public TreeView getTreeView() {
		if(treeView==null){
			treeView = new EntnahmeTreeView(this);
		}
		return treeView;
	}

	@Override
	public PaneController getPaneController(){
		return EntnahmePaneController.getOneInstance();
	}


	
}
