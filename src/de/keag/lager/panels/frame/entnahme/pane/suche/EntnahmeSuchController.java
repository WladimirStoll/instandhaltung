package de.keag.lager.panels.frame.entnahme.pane.suche;

import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.panels.frame.entnahme.pane.EntnahmePaneController;

public class EntnahmeSuchController extends SuchController{
	private EntnahmeSuchView entnahmeSuchView = null;
	private static EntnahmeSuchController entnahmeSuchPaneController = null;
	
	private EntnahmeSuchController() {
		super();
	}

	public static EntnahmeSuchController getOneInstance() {
		if(entnahmeSuchPaneController==null){
			entnahmeSuchPaneController = new EntnahmeSuchController();
			EntnahmePaneController.getOneInstance().addSuchBeobachter((EntnahmeSuchView)entnahmeSuchPaneController.getSuchView());
		}
		return entnahmeSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(entnahmeSuchView==null){
			entnahmeSuchView = new EntnahmeSuchView(this,EntnahmePaneController.getOneInstance().getIModel());
		}
		return entnahmeSuchView;
	}


	@Override
	public PaneController getPaneController(){
		return EntnahmePaneController.getOneInstance();
	}

	
}
