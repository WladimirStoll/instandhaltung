package de.keag.lager.panels.frame.einlagerung.pane.liste;

import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.panels.frame.einlagerung.pane.EinlagerungPaneController;

public class EinlagerungListController extends ListController {
	private ListView einlagerungListPane = null;
	private static EinlagerungListController einlagerungListPaneController = null;
	
	private EinlagerungListController() {
		super();
	}

	@Override
	public ListView getListView() {
		if(einlagerungListPane==null){
			einlagerungListPane = new EinlagerungListView(this,EinlagerungPaneController.getOneInstance().getIModel());
		}
		return einlagerungListPane;
	}

	public static EinlagerungListController getOneIntstance() {
		if(einlagerungListPaneController==null){
			einlagerungListPaneController = new EinlagerungListController();
			EinlagerungPaneController.getOneInstance().addListBeobachter(einlagerungListPaneController.getListView());
		}
		return einlagerungListPaneController;
	}

	@Override
	public PaneController getPaneController() {
		return EinlagerungPaneController.getOneInstance();
	}



}
