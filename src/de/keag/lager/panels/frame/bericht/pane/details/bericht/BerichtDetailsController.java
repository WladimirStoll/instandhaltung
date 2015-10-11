package de.keag.lager.panels.frame.bericht.pane.details.bericht;


import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;

public class BerichtDetailsController extends DetailsController  {
	private static BerichtDetailsController baDetailsController = null;
	private BerichtDetailsView baDetailsView = null;
	/**
	 * Konstruktor
	 */
	private BerichtDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static BerichtDetailsController getOneInstance() {
		if(baDetailsController==null){
			baDetailsController = new BerichtDetailsController();
			baDetailsController.addDetailsBeobachter(baDetailsController.getDetailsView());
		}
		return baDetailsController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(baDetailsView==null){
			baDetailsView = new BerichtDetailsView(this);
		}
		return baDetailsView;
	}

	@Override
	public PaneController getPaneController(){
		return BerichtPaneController.getOneInstance(); 
	}

}
