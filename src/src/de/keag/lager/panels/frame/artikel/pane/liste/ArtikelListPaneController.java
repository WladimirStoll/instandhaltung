package de.keag.lager.panels.frame.artikel.pane.liste;

import java.util.ArrayList;

import de.keag.lager.core.Controller;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.menu.MenuController;

public class ArtikelListPaneController extends ListController {
	private ArtikelListPaneView listPaneView = null;
	private static ArtikelListPaneController listPaneController = null;
	
	private ArtikelListPaneController() {
		super();
	}
	
	public static ArtikelListPaneController getOneIntstance() {
		if(listPaneController==null){
			listPaneController = new ArtikelListPaneController();
			ArtikelPaneController.getOneInstance().addListBeobachter(
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
		ArtikelPaneController.getOneInstance().benachrichtigeListBeobachter();
	}

	@Override
	public PaneController getPaneController() {
		return ArtikelPaneController.getOneInstance();	
	}

	@Override
	public ListView getListView() {
		if(listPaneView==null){
			listPaneView = new ArtikelListPaneView(this,ArtikelPaneController.getOneInstance().getIModel());
		}
		return listPaneView;
	}


	
}
