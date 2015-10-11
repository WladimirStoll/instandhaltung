package de.keag.lager.panels.frame.baugruppe.pane.liste;

import java.util.ArrayList;

import de.keag.lager.core.Controller;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.baugruppe.pane.BaugruppePaneController;
import de.keag.lager.panels.frame.menu.MenuController;

public class BaugruppeListPaneController extends ListController {
	private BaugruppeListPaneView baugruppeListPane = null;
	private static BaugruppeListPaneController baugruppeListPaneController = null;
	
	private BaugruppeListPaneController() {
		super();
	}
	
	public static BaugruppeListPaneController getOneIntstance() {
		if(baugruppeListPaneController==null){
			baugruppeListPaneController = new BaugruppeListPaneController();
			BaugruppePaneController.getOneInstance().addListBeobachter(
					baugruppeListPaneController.getListView());
		}
		return baugruppeListPaneController;
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
		super.aktualisiereAnzeige();
//		BaugruppePaneController.getOneInstance().benachrichtigeTreeBeobachter();
	}

	@Override
	public PaneController getPaneController() {
		return BaugruppePaneController.getOneInstance();	
	}

	@Override
	public ListView getListView() {
		if(baugruppeListPane==null){
			baugruppeListPane = new BaugruppeListPaneView(this,BaugruppePaneController.getOneInstance().getIModel());
		}
		return baugruppeListPane;
	}


	
}
