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
package de.keag.lager.panels.frame.halle.pane.liste;

import java.util.ArrayList;

import de.keag.lager.core.Controller;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.menu.MenuController;

public class HalleListPaneController extends ListController {
	private HalleListPaneView listPaneView = null;
	private static HalleListPaneController listPaneController = null;
	
	private HalleListPaneController() {
		super();
	}
	
	public static HalleListPaneController getOneIntstance() {
		if(listPaneController==null){
			listPaneController = new HalleListPaneController();
			HallePaneController.getOneInstance().addListBeobachter(
					listPaneController.getListView());
		}
		return listPaneController;
	}

	/**
	 * Es gibt nichts zum Prüfen
	 */
	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		return errors;
	}

	@Override
	public void aktualisiereAnzeige() {
		HallePaneController.getOneInstance().benachrichtigeListBeobachter();
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();	
	}

	@Override
	public ListView getListView() {
		if(listPaneView==null){
			listPaneView = new HalleListPaneView(this,HallePaneController.getOneInstance().getIModel());
		}
		return listPaneView;
	}


	
}
