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
