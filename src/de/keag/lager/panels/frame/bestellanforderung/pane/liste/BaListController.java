package de.keag.lager.panels.frame.bestellanforderung.pane.liste;

import java.sql.SQLException;
import java.util.Map;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;

public class BaListController extends ListController {
	private ListView baListPane = null;
	private static BaListController baListPaneController = null;
	
	private BaListController() {
		super();
	}

	@Override
	public ListView getListView() {
		if(baListPane==null){
			baListPane = new BaListView(this,BaPaneController.getOneInstance().getIModel());
		}
		return baListPane;
	}

	public static BaListController getOneIntstance() {
		if(baListPaneController==null){
			baListPaneController = new BaListController();
			BaPaneController.getOneInstance().addListBeobachter(baListPaneController.getListView());
		}
		return baListPaneController;
	}

	@Override
	public PaneController getPaneController() {
		return BaPaneController.getOneInstance();
	}

//	/**
//	 * Satz wird abgeschlossen und zu der Bestellung kopiert.
//	 * @throws Exception 
//	 */
//	@Override
//	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception {
//		druckeBericht(bean, druckParameter);
//	}

	/**
	 * Satz wird abgeschlossen und zu der Bestellung kopiert.
	 * @throws Exception 
	 */
	@Override
	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception {
		// TODO Auto-generated method stub
//		super.druckeBericht(bean);
		((BaPaneController)getPaneController()).druckeBericht(bean, null);
	}
	


}
