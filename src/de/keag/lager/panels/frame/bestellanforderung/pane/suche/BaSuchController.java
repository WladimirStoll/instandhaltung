package de.keag.lager.panels.frame.bestellanforderung.pane.suche;

import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;

public class BaSuchController extends SuchController{
	private BaSuchView baSuchView = null;
	private static BaSuchController baSuchPaneController = null;
	
	private BaSuchController() {
		super();
	}

	public static BaSuchController getOneInstance() {
		if(baSuchPaneController==null){
			baSuchPaneController = new BaSuchController();
			BaPaneController.getOneInstance().addSuchBeobachter((BaSuchView)baSuchPaneController.getSuchView());
		}
		return baSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(baSuchView==null){
			baSuchView = new BaSuchView(this,BaPaneController.getOneInstance().getIModel());
		}
		return baSuchView;
	}


	@Override
	public PaneController getPaneController(){
		return BaPaneController.getOneInstance();
	}

	
}
