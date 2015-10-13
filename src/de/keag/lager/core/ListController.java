package de.keag.lager.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.menu.MenuController;

abstract public class ListController extends Controller{

	public ListController() {
		super();
	}

	abstract public ListView getListView();
	
	public ArrayList<Fehler> pruefeAktuellenSatz() {
		return getPaneController().pruefeAktuellenSatz();
	}
	
	
	/* Delegation -> an --> MenuController = Menü wird angezeigt.
	 * 
	 */
	
	public void showMenue() {
	   //Menü anzeigen
	   MenuController.getOneInstance().showView();
	}
	
	public void showVorigesView() {
		   if (getPaneController()!=null){
			   if (!getPaneController().showVorigesView()){
				   //Menü anzeigen, falls kein Voriges View existiert
				   showMenue();
			   }
		   }else{
			   //Menü anzeigen
			   showMenue();
		   }
	}
	
	

	@Override
	public void aktualisiereAnzeige() {
		getPaneController().benachrichtigeListBeobachter();
	}

	@Override
	public void setBorder(Boolean aktiv) {
		getListView().setBorder(aktiv);
	}

	@Override
	public void setStandardFocusPosition() {
		getListView().setStandardFocusPosition();
	}


	public void setGewaehlteZeile(int gewaehlteZeilenNummer) {
		getPaneController().setGewaehlteZeile(gewaehlteZeilenNummer);
	}
	
	public void erstelleNeuenSatz() {
		getPaneController().erstelleNeuenSatz();
	}

	public void speichereSatz(Bean bean) {
		getPaneController().speichereSatz(bean);
	}

	public void abbrechenSatz(Bean bean) {
		//Dadurch wird der Controller beim Verlassen/Ändern nicht mehr geprüft.
		getPaneController().loescheAktivenController();
		getPaneController().abbrechenSatz(bean);
	}
	
//	public void druckeBericht(Bean bean) throws Exception {
//		druckeBericht(bean, null);
//	}

	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception {
		getPaneController().druckeBericht(bean, druckParameter);
	}
	
//	public void verschickeSatz(Bean bean)  throws LagerException, SQLException, Exception {
//			getPaneController().druckeBericht(bean);
//	}
	
	
	
}
