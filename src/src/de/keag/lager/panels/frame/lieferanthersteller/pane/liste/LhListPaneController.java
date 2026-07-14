package de.keag.lager.panels.frame.lieferanthersteller.pane.liste;

import java.util.ArrayList;

import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.lieferanthersteller.pane.LhPaneController;
import de.keag.lager.panels.frame.lieferanthersteller.pane.suche.LhSuchPaneView;

public class LhListPaneController extends ListController {
	private LhListPaneView lhListPaneView = null;
	private static LhListPaneController lhListPaneController = null;
	
	private LhListPaneController() {
		super();
	}
	
	public static LhListPaneController getOneIntstance() {
		if(lhListPaneController==null){
			lhListPaneController = new LhListPaneController();
			LhPaneController.getOneInstance().addListBeobachter(
					lhListPaneController.getListView());
		}
		return lhListPaneController;
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
		LhPaneController.getOneInstance().benachrichtigeListBeobachter();
	}

	@Override
	public PaneController getPaneController() {
		return LhPaneController.getOneInstance();	
	}

	@Override
	public ListView getListView() {
		if(lhListPaneView==null){
			lhListPaneView = new LhListPaneView(this,LhPaneController.getOneInstance().getIModel());
		}
		return lhListPaneView;
	}


	
}
