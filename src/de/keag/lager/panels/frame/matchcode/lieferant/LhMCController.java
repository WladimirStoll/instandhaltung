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
package de.keag.lager.panels.frame.matchcode.lieferant;

import java.sql.SQLException;

import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class LhMCController {
	private static LhMCController lieferantMCController = null;
	private LhMCView lieferantMCView;
	private LhMCModel lieferantMCModel;
	private ILhMCAnforderer iLhMCAnforderer;
	
	private LhMCController() {
		super();
	}
	public static LhMCController getOneInstance(){
		if(lieferantMCController==null){
			lieferantMCController = new LhMCController();
			lieferantMCController.getLieferantMCView() ;
		}
		return lieferantMCController;
	}
	private LhMCView getLieferantMCView() {
		if (lieferantMCView==null){
			lieferantMCView = new LhMCView(Run.getOneInstance().getMainFrame(),this,getLieferantMCModel());
			getLieferantMCModel().addBeobachter(lieferantMCView);
		}
		return lieferantMCView;
	}
	
	private LhMCModel getLieferantMCModel() {
		if(lieferantMCModel==null){
			lieferantMCModel = new LhMCModel();
		}
		return lieferantMCModel;
	}
	
	public void holeAlleLieferanten(ILhMCAnforderer iLhMCAnforderer) throws SQLException, LagerException{
		setiLhMCAnforderer(iLhMCAnforderer);
		getLieferantMCModel().getLhSelectedBeans().clear(); //gewählte Lieferanten löschen
		getLieferantMCModel().holeDaten(); //Datenbank neu abfragen.
		getLieferantMCView().setVisible(true); //Fenster anzeigen.
	}
	
	protected void setGewaehlterLieferant(LhBean lhBean) {
		getLieferantMCModel().getLhSelectedBeans().clear();
		getLieferantMCModel().getLhSelectedBeans().add(lhBean);
	}
	
	protected void fensterIstGeschlossen() {
		getiLhMCAnforderer().selectedLhBeans(getLieferantMCModel().getLhSelectedBeans());
		setiLhMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private ILhMCAnforderer getiLhMCAnforderer() {
		return iLhMCAnforderer;
	}
	
	public void setiLhMCAnforderer(ILhMCAnforderer iLhMCAnforderer) {
		this.iLhMCAnforderer = iLhMCAnforderer;
	}
	public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException {
		return getLieferantMCModel().getLhAnhandName(lhName);
	}
}
