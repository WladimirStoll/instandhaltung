package de.keag.lager.panels.frame.einlagerung.pane.suche;

import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.panels.frame.einlagerung.pane.EinlagerungPaneController;

public class EinlagerungSuchController extends SuchController{
	private EinlagerungSuchView einlagerungSuchView = null;
	private static EinlagerungSuchController einlagerungSuchPaneController = null;
	
	private EinlagerungSuchController() {
		super();
	}

	public static EinlagerungSuchController getOneInstance() {
		if(einlagerungSuchPaneController==null){
			einlagerungSuchPaneController = new EinlagerungSuchController();
			EinlagerungPaneController.getOneInstance().addSuchBeobachter((EinlagerungSuchView)einlagerungSuchPaneController.getSuchView());
		}
		return einlagerungSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(einlagerungSuchView==null){
			einlagerungSuchView = new EinlagerungSuchView(this,EinlagerungPaneController.getOneInstance().getIModel());
		}
		return einlagerungSuchView;
	}


	@Override
	public PaneController getPaneController(){
		return EinlagerungPaneController.getOneInstance();
	}

	
}
