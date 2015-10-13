package de.keag.lager.panels.frame.wartung.pane.liste;

import java.util.ArrayList;

import de.keag.lager.core.Controller;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;
import de.keag.lager.panels.frame.menu.MenuController;

public class WartungListPaneController extends ListController {
	private WartungListPaneView benutzerListPaneView = null;
	private static WartungListPaneController benutzerListPaneController = null;
	
	private WartungListPaneController() {
		super();
	}
	
	public static WartungListPaneController getOneIntstance() {
		if(benutzerListPaneController==null){
			benutzerListPaneController = new WartungListPaneController();
			WartungPaneController.getOneInstance().addListBeobachter(
					benutzerListPaneController.getListView());
		}
		return benutzerListPaneController;
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
		WartungPaneController.getOneInstance().benachrichtigeListBeobachter();
	}

	@Override
	public PaneController getPaneController() {
		return WartungPaneController.getOneInstance();	
	}

	@Override
	public ListView getListView() {
		if(benutzerListPaneView==null){
			benutzerListPaneView = new WartungListPaneView(this,WartungPaneController.getOneInstance().getIModel());
		}
		return benutzerListPaneView;
	}


	
}
