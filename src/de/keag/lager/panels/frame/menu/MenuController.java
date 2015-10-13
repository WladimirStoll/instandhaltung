package de.keag.lager.panels.frame.menu;

import de.keag.lager.core.PaneControllerAdapter;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.baugruppe.pane.BaugruppePaneController;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;
import de.keag.lager.panels.frame.bestellung.pane.BestellungPaneController;
import de.keag.lager.panels.frame.einlagerung.pane.EinlagerungPaneController;
import de.keag.lager.panels.frame.entnahme.pane.EntnahmePaneController;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.impressum.pane.ImpressumPaneController;
import de.keag.lager.panels.frame.katalog.pane.KatalogPaneController;
import de.keag.lager.panels.frame.lieferanthersteller.pane.LhPaneController;
import de.keag.lager.panels.frame.login.LoginController;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;

public class MenuController extends PaneControllerAdapter {
	private static MenuController menuController = null;
	private MenuView menuView  = null;
//	private MenuModel menuModel  = null;
	
	private MenuController() {
		super();
	}
	
	/**
	 * Deligation der Aufgabe an die Anforderungspane
	 */
	protected void showAnforderungPane() {
		BaPaneController.getOneInstance().showView();
	}
	
	public static MenuController getOneInstance(){
		if(menuController ==null){
			menuController = new MenuController();
		}
		return menuController;
	}

	/**
	 * Delegation: View wird angezeigt.
	 */
	@Override
	public void showView(){
		getMenuView().showView(); //Menü anzeigen
	}

	private MenuView getMenuView() {
		if(menuView==null){
			menuView = new MenuView(this); //Das MenuView kennt danach auch den Kontroller
		}
		return menuView;
	}

//	private MenuModel getMenuModel() {
//		if(menuModel==null){
//			menuModel = new MenuModel();
//			menuModel.setBeobachter(getMenuView());
//		}
//		return menuModel;
//	}

	/**
	 * Deligation --> an --> LoginController 
	 */
	public void zeigeLoginMaske() {
		LoginController.getOneInstance().setzeAktuellenBenutzerAufNull();
		LoginController.getOneInstance().showView();
	}

	public void showBenutzerPane() {
		BenutzerPaneController.getOneInstance().showView(this);
	}

	protected void showAnlagenPane() {
		BaugruppePaneController.getOneInstance().showView(this);
	}

	public void showGrosshandelHerstellerPane() {
		LhPaneController.getOneInstance().showView(this);
		
	}

	public void showEntnahmePane() {
		EntnahmePaneController.getOneInstance().showView(this);
		
	}

	public void showEinlagerungPane() {
		EinlagerungPaneController.getOneInstance().showView(this);
		
	}

	protected void showBestellungsPane() {
		//Bestellungen anzeigen
		//Danach zurück zum Menü (dieser controller)
		BestellungPaneController.getOneInstance().showView(this);
	}
	
	protected void showBerichtePane() {
		//Bestellungen anzeigen
		//Danach zurück zum Menü (dieser controller)
		BerichtPaneController.getOneInstance().showView(this);
	}

	public void showHallePane() {
		//Hallen anzeigen
		//Danach zurück zum Menü (dieser controller)
		HallePaneController.getOneInstance().showView(this);
	}

	public void showArtikelPane() {
		//Artikel anzeigen
		//Danach zurück zum Menü (dieser controller)
		ArtikelPaneController.getOneInstance().showView(this);
	}

	public void druckeMindestBestandsListe() throws Exception {
		BerichtPaneController.getOneInstance().druckeMindestBestandsListe(this);
	}

	public void showKatalogPane() {
		KatalogPaneController.getOneInstance().showView(this);
	}

	public void showWartungPane() {
		WartungPaneController.getOneInstance().showView(this);
	}

	public void showImpressumPane() {
		ImpressumPaneController.getOneInstance().showView(this);
	}

}
