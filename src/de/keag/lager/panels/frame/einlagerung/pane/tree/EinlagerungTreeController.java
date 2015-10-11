package de.keag.lager.panels.frame.einlagerung.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.einlagerung.pane.EinlagerungPaneController;

public class EinlagerungTreeController extends TreeController{
	private TreeView treeView = null;
	private static EinlagerungTreeController einlagerungSuchPaneController = null;
	
	private EinlagerungTreeController() {
		super();
	}

	public static EinlagerungTreeController getOneInstance() {
		if(einlagerungSuchPaneController==null){
			einlagerungSuchPaneController = new EinlagerungTreeController();
			EinlagerungPaneController.getOneInstance().addTreeBeobachter((EinlagerungTreeView)einlagerungSuchPaneController.getTreeView());
		}
		return einlagerungSuchPaneController;
	}

	@Override
	public TreeView getTreeView() {
		if(treeView==null){
			treeView = new EinlagerungTreeView(this);
		}
		return treeView;
	}

	@Override
	public PaneController getPaneController(){
		return EinlagerungPaneController.getOneInstance();
	}


	
}
