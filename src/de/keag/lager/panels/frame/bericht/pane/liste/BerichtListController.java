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
package de.keag.lager.panels.frame.bericht.pane.liste;

import java.sql.SQLException;
import java.util.Map;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ListController;
import de.keag.lager.core.ListView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;

public class BerichtListController extends ListController {
	private ListView listView = null;
	private static BerichtListController listController = null;
	
	private BerichtListController() {
		super();
	}

	@Override
	public ListView getListView() {
		if(listView==null){
			listView = new BerichtListView(this,BerichtPaneController.getOneInstance().getIModel());
		}
		return listView;
	}

	public static BerichtListController getOneIntstance() {
		if(listController==null){
			listController = new BerichtListController();
			BerichtPaneController.getOneInstance().addListBeobachter(listController.getListView());
		}
		return listController;
	}

	@Override
	public PaneController getPaneController() {
		return BerichtPaneController.getOneInstance();
	}

		
//		@Override
//	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception {
//		druckeBericht(bean, null);
//	}

		@Override
		public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception {
//			super.druckenSatz(bean);
			BerichtPaneController.getOneInstance().druckeBericht(bean, druckParameter);
		}
		
//		@Override
//		public void verschickeSatz(Bean bean) throws LagerException, SQLException,
//				Exception {
////			super.verschickeSatz(bean);
//			BerichtPaneController.getOneInstance().verschickeSatz(bean);
//		}


}
