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
package de.keag.lager.panels.frame.wartung.pane.tree;

import java.util.ArrayList;

import de.keag.lager.core.IConroller;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;

public class WartungTreePaneController extends TreeController{
	private WartungTreePaneView WartungTreePane = null;
	private static WartungTreePaneController benutzerSuchPaneController = null;
	
	private WartungTreePaneController() {
		super();
	}

	public static WartungTreePaneController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new WartungTreePaneController();
			WartungPaneController.getOneInstance().addTreeBeobachter(benutzerSuchPaneController.getWartungTreePane());
		}
		return benutzerSuchPaneController;
	}

	public WartungTreePaneView getWartungTreePane() {
		if(WartungTreePane==null){
			WartungTreePane = new WartungTreePaneView(this);
		}
		return WartungTreePane;
	}

	/**
	 * der im Tree vom Benutzer gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	@Override
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		WartungPaneController.getOneInstance().selectKnoten(selectedModelKnotenBean);
	}
	
	/**
	 * TreeController hat nie Fehler 
	 */
	@Override
	public ArrayList<Fehler> pruefeDich() {
		return new ArrayList<Fehler>();
	}

	@Override
	public ArrayList<Fehler> setzeNeuenAktivenController() {
//		return Run.getOneInstance().getMainFrame().setzeNeuenAktivenController(this);
		return WartungPaneController.getOneInstance().setzeNeuenAktivenController(this);

	}

	@Override
	public void setStandardFocusPosition() {
		//getWartungTreePane().setStandardFocusPosition();
		WartungPaneController.getOneInstance().benachrichtigeTreeBeobachter();
	}

	@Override
	public void setBorder(Boolean aktiv) {
		getWartungTreePane().setBorder(aktiv);
		
	}

	@Override
	public TreeView getTreeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaneController getPaneController() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
