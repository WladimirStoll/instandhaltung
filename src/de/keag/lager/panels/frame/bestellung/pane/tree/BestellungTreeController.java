package de.keag.lager.panels.frame.bestellung.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.bestellung.pane.BestellungPaneController;

public class BestellungTreeController extends TreeController{
	private TreeView treeView = null;
	private static BestellungTreeController baSuchPaneController = null;
	
	private BestellungTreeController() {
		super();
	}

	public static BestellungTreeController getOneInstance() {
		if(baSuchPaneController==null){
			baSuchPaneController = new BestellungTreeController();
			BestellungPaneController.getOneInstance().addTreeBeobachter((BestellungTreeView)baSuchPaneController.getTreeView());
		}
		return baSuchPaneController;
	}

	@Override
	public TreeView getTreeView() {
		if(treeView==null){
			treeView = new BestellungTreeView(this);
		}
		return treeView;
	}

	@Override
	public PaneController getPaneController(){
		return BestellungPaneController.getOneInstance();
	}


	
}
