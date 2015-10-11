/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
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
