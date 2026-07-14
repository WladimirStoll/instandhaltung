package de.keag.lager.panels.frame.bericht.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;

public class BerichtTreeController extends TreeController{
	private TreeView treeView = null;
	private static BerichtTreeController treeController = null;
	
	private BerichtTreeController() {
		super();
	}

	public static BerichtTreeController getOneInstance() {
		if(treeController==null){
			treeController = new BerichtTreeController();
			BerichtPaneController.getOneInstance().addTreeBeobachter((BerichtTreeView)treeController.getTreeView());
		}
		return treeController;
	}

	@Override
	public TreeView getTreeView() {
		if(treeView==null){
			treeView = new BerichtTreeView(this);
		}
		return treeView;
	}

	@Override
	public PaneController getPaneController(){
		return BerichtPaneController.getOneInstance();
	}


	
}
