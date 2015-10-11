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
package de.keag.lager.panels.frame.bestellanforderung.pane.suche;

import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;

public class BaSuchController extends SuchController{
	private BaSuchView baSuchView = null;
	private static BaSuchController baSuchPaneController = null;
	
	private BaSuchController() {
		super();
	}

	public static BaSuchController getOneInstance() {
		if(baSuchPaneController==null){
			baSuchPaneController = new BaSuchController();
			BaPaneController.getOneInstance().addSuchBeobachter((BaSuchView)baSuchPaneController.getSuchView());
		}
		return baSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(baSuchView==null){
			baSuchView = new BaSuchView(this,BaPaneController.getOneInstance().getIModel());
		}
		return baSuchView;
	}


	@Override
	public PaneController getPaneController(){
		return BaPaneController.getOneInstance();
	}

	
}
