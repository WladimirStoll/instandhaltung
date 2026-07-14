package de.keag.lager.panels.frame.halle.pane.liste;

import java.util.ArrayList;

import de.keag.lager.core.Controller;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.menu.MenuController;

public class HalleListPaneController extends ListController {
	private HalleListPaneView listPaneView = null;
	private static HalleListPaneController listPaneController = null;
	
	private HalleListPaneController() {
		super();
	}
	
	public static HalleListPaneController getOneIntstance() {
		if(listPaneController==null){
			listPaneController = new HalleListPaneController();
			HallePaneController.getOneInstance().addListBeobachter(
					listPaneController.getListView());
		}
		return listPaneController;
	}

	/**
	 * Es gibt nichts zum Prüfen
	 */
	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		return errors;
	}

	@Override
	public void aktualisiereAnzeige() {
		HallePaneController.getOneInstance().benachrichtigeListBeobachter();
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();	
	}

	@Override
	public ListView getListView() {
		if(listPaneView==null){
			listPaneView = new HalleListPaneView(this,HallePaneController.getOneInstance().getIModel());
		}
		return listPaneView;
	}


	
}
