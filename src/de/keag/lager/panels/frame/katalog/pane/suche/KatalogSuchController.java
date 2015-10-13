package de.keag.lager.panels.frame.katalog.pane.suche;

import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.panels.frame.katalog.pane.KatalogPaneController;

public class KatalogSuchController extends SuchController{
	private KatalogSuchView einlagerungSuchView = null;
	private static KatalogSuchController einlagerungSuchPaneController = null;
	
	private KatalogSuchController() {
		super();
	}

	public static KatalogSuchController getOneInstance() {
		if(einlagerungSuchPaneController==null){
			einlagerungSuchPaneController = new KatalogSuchController();
			KatalogPaneController.getOneInstance().addSuchBeobachter((KatalogSuchView)einlagerungSuchPaneController.getSuchView());
		}
		return einlagerungSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(einlagerungSuchView==null){
			einlagerungSuchView = new KatalogSuchView(this,KatalogPaneController.getOneInstance().getIModel());
		}
		return einlagerungSuchView;
	}


	@Override
	public PaneController getPaneController(){
		return KatalogPaneController.getOneInstance();
	}

	
}
