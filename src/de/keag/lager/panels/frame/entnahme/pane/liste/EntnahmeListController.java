package de.keag.lager.panels.frame.entnahme.pane.liste;

import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.panels.frame.entnahme.pane.EntnahmePaneController;

public class EntnahmeListController extends ListController {
	private ListView entnahmeListPane = null;
	private static EntnahmeListController entnahmeListPaneController = null;
	
	private EntnahmeListController() {
		super();
	}

	@Override
	public ListView getListView() {
		if(entnahmeListPane==null){
			entnahmeListPane = new EntnahmeListView(this,EntnahmePaneController.getOneInstance().getIModel());
		}
		return entnahmeListPane;
	}

	public static EntnahmeListController getOneIntstance() {
		if(entnahmeListPaneController==null){
			entnahmeListPaneController = new EntnahmeListController();
			EntnahmePaneController.getOneInstance().addListBeobachter(entnahmeListPaneController.getListView());
		}
		return entnahmeListPaneController;
	}

	@Override
	public PaneController getPaneController() {
		return EntnahmePaneController.getOneInstance();
	}



}
