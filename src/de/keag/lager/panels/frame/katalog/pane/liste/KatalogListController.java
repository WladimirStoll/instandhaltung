package de.keag.lager.panels.frame.katalog.pane.liste;

import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.panels.frame.katalog.pane.KatalogPaneController;

public class KatalogListController extends ListController {
	private ListView einlagerungListPane = null;
	private static KatalogListController einlagerungListPaneController = null;
	
	private KatalogListController() {
		super();
	}

	@Override
	public ListView getListView() {
		if(einlagerungListPane==null){
			einlagerungListPane = new KatalogListView(this,KatalogPaneController.getOneInstance().getIModel());
		}
		return einlagerungListPane;
	}

	public static KatalogListController getOneIntstance() {
		if(einlagerungListPaneController==null){
			einlagerungListPaneController = new KatalogListController();
			KatalogPaneController.getOneInstance().addListBeobachter(einlagerungListPaneController.getListView());
		}
		return einlagerungListPaneController;
	}

	@Override
	public PaneController getPaneController() {
		return KatalogPaneController.getOneInstance();
	}



}
