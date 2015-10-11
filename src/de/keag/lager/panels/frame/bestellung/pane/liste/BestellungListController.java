package de.keag.lager.panels.frame.bestellung.pane.liste;

import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.panels.frame.bestellung.pane.BestellungPaneController;

public class BestellungListController extends ListController {
	private ListView baListPane = null;
	private static BestellungListController baListPaneController = null;
	
	private BestellungListController() {
		super();
	}

	@Override
	public ListView getListView() {
		if(baListPane==null){
			baListPane = new BestellungListView(this,BestellungPaneController.getOneInstance().getIModel());
		}
		return baListPane;
	}

	public static BestellungListController getOneIntstance() {
		if(baListPaneController==null){
			baListPaneController = new BestellungListController();
			BestellungPaneController.getOneInstance().addListBeobachter(baListPaneController.getListView());
		}
		return baListPaneController;
	}

	@Override
	public PaneController getPaneController() {
		return BestellungPaneController.getOneInstance();
	}



}
