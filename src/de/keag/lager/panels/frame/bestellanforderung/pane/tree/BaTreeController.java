package de.keag.lager.panels.frame.bestellanforderung.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;

public class BaTreeController extends TreeController{
	private TreeView treeView = null;
	private static BaTreeController baSuchPaneController = null;
	
	private BaTreeController() {
		super();
	}

	public static BaTreeController getOneInstance() {
		if(baSuchPaneController==null){
			baSuchPaneController = new BaTreeController();
			BaPaneController.getOneInstance().addTreeBeobachter((BaTreeView)baSuchPaneController.getTreeView());
		}
		return baSuchPaneController;
	}

	@Override
	public TreeView getTreeView() {
		if(treeView==null){
			treeView = new BaTreeView(this);
		}
		return treeView;
	}

	@Override
	public PaneController getPaneController(){
		return BaPaneController.getOneInstance();
	}


	
}
