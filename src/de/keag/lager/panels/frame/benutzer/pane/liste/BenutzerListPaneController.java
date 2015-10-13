package de.keag.lager.panels.frame.benutzer.pane.liste;

import java.util.ArrayList;

import de.keag.lager.core.Controller;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;
import de.keag.lager.panels.frame.menu.MenuController;

public class BenutzerListPaneController extends ListController {
	private BenutzerListPaneView benutzerListPaneView = null;
	private static BenutzerListPaneController benutzerListPaneController = null;
	
	private BenutzerListPaneController() {
		super();
	}
	
	public static BenutzerListPaneController getOneIntstance() {
		if(benutzerListPaneController==null){
			benutzerListPaneController = new BenutzerListPaneController();
			BenutzerPaneController.getOneInstance().addListBeobachter(
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
		BenutzerPaneController.getOneInstance().benachrichtigeListBeobachter();
	}

	@Override
	public PaneController getPaneController() {
		return BenutzerPaneController.getOneInstance();	
	}

	@Override
	public ListView getListView() {
		if(benutzerListPaneView==null){
			benutzerListPaneView = new BenutzerListPaneView(this,BenutzerPaneController.getOneInstance().getIModel());
		}
		return benutzerListPaneView;
	}


	
}
