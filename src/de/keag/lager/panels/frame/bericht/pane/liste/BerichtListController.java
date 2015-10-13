package de.keag.lager.panels.frame.bericht.pane.liste;

import java.sql.SQLException;
import java.util.Map;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;

public class BerichtListController extends ListController {
	private ListView listView = null;
	private static BerichtListController listController = null;
	
	private BerichtListController() {
		super();
	}

	@Override
	public ListView getListView() {
		if(listView==null){
			listView = new BerichtListView(this,BerichtPaneController.getOneInstance().getIModel());
		}
		return listView;
	}

	public static BerichtListController getOneIntstance() {
		if(listController==null){
			listController = new BerichtListController();
			BerichtPaneController.getOneInstance().addListBeobachter(listController.getListView());
		}
		return listController;
	}

	@Override
	public PaneController getPaneController() {
		return BerichtPaneController.getOneInstance();
	}

		
//		@Override
//	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception {
//		druckeBericht(bean, null);
//	}

		@Override
		public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception {
//			super.druckenSatz(bean);
			BerichtPaneController.getOneInstance().druckeBericht(bean, druckParameter);
		}
		
//		@Override
//		public void verschickeSatz(Bean bean) throws LagerException, SQLException,
//				Exception {
////			super.verschickeSatz(bean);
//			BerichtPaneController.getOneInstance().verschickeSatz(bean);
//		}


}
