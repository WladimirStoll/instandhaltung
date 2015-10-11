package de.keag.lager.panels.frame.bestellung.pane.suche;

import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.panels.frame.bestellung.pane.BestellungPaneController;

public class BestellungSuchController extends SuchController{
	private BestellungSuchView baSuchView = null;
	private static BestellungSuchController baSuchPaneController = null;
	
	private BestellungSuchController() {
		super();
	}

	public static BestellungSuchController getOneInstance() {
		if(baSuchPaneController==null){
			baSuchPaneController = new BestellungSuchController();
			BestellungPaneController.getOneInstance().addSuchBeobachter((BestellungSuchView)baSuchPaneController.getSuchView());
		}
		return baSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(baSuchView==null){
			baSuchView = new BestellungSuchView(this,BestellungPaneController.getOneInstance().getIModel());
		}
		return baSuchView;
	}


	@Override
	public PaneController getPaneController(){
		return BestellungPaneController.getOneInstance();
	}

	
}
