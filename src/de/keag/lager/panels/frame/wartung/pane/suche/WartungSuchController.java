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
package de.keag.lager.panels.frame.wartung.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.matchcode.baugruppe.BaugruppeMCController;
import de.keag.lager.panels.frame.matchcode.baugruppe.IBaugruppeMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungsart.IWartungsArtMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungsart.WartungsArtMCController;
import de.keag.lager.panels.frame.matchcode.wartungstyp.IWartungsTypMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungstyp.WartungsTypMCController;

public class WartungSuchController extends SuchController 
	implements IBaugruppeMCAnforderer,
				IWartungsArtMCAnforderer,
				IWartungsTypMCAnforderer{
	
	private WartungSuchPaneView benutzerSuchPaneView = null;
	private static WartungSuchController benutzerSuchPaneController = null;
	
	private WartungSuchController() {
		super();
	}

	public static WartungSuchController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new WartungSuchController();
			WartungPaneController.getOneInstance().addSuchBeobachter(benutzerSuchPaneController.getSuchView());
		}
		return benutzerSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(benutzerSuchPaneView==null){
			benutzerSuchPaneView = new WartungSuchPaneView(this,WartungPaneController.getOneInstance().getIModel());
		}
		return benutzerSuchPaneView;
	}

	
	@Override
	public PaneController getPaneController() {
		return WartungPaneController.getOneInstance();
	}

	@Override
	public void holeBaugruppe() throws SQLException, LagerException {
		BaugruppeMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedBaugruppeBeans(ArrayList<BaugruppeBean> beans) {
		if (beans!=null && beans.size()>0){
			((WartungSuchPaneView)getSuchView()).setBaugruppe(beans.get(0));
		}
	}

	@Override
	public void holeWartungsArt() throws SQLException, LagerException {
		WartungsArtMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedWartungsArtBeans(ArrayList<WartungsArtBean> beans) {
		if (beans != null && beans.size() > 0) {
			((WartungSuchPaneView) getSuchView()).setWartungsArt(beans.get(0));
		}
	}

	@Override
	public void holeWartungsTyp() throws SQLException, LagerException {
		WartungsTypMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedWartungsTypBeans(
			ArrayList<WartungsTypBean> beans) {
		if (beans!=null && beans.size()>0){
			((WartungSuchPaneView)getSuchView()).setWartungsTyp(beans.get(0));
		}
	}

//	@Override
//	public void holeAbteilung() throws SQLException, LagerException {
//		AbteilungMCController.getOneInstance().holeAlleBeans(this);
//	}
//
//	@Override
//	public void selectedBenutzerBeans(ArrayList<BenutzerBean> abteilungBeans) {
//		if (abteilungBeans!=null && abteilungBeans.size()>0){
//			((WartungSuchPaneView)getSuchView()).setAbteilung(abteilungBeans.get(0));
//		}
//	}

}
