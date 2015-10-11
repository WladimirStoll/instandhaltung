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
package de.keag.lager.panels.frame.halle.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung.BenutzerAbteilungDetailsView;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;
import de.keag.lager.panels.frame.matchcode.benutzer.BenutzerMCController;
import de.keag.lager.panels.frame.matchcode.benutzer.IBenutzerMCAnforderer;
import de.keag.lager.panels.frame.matchcode.etage.EtageMCController;
import de.keag.lager.panels.frame.matchcode.etage.IEtageMCAnforderer;
import de.keag.lager.panels.frame.matchcode.halle.HalleMCController;
import de.keag.lager.panels.frame.matchcode.halle.IHalleMCAnforderer;

public class HalleSuchController extends SuchController 
	implements IAbteilungMCAnforderer,
				IHalleMCAnforderer,
				IEtageMCAnforderer,
//				IBenutzerMCAnforderer,
				IArtikelMCAnforderer
				{
	private HalleSuchPaneView benutzerSuchPaneView = null;
	private static HalleSuchController benutzerSuchPaneController = null;
	
	private HalleSuchController() {
		super();
	}

	public static HalleSuchController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new HalleSuchController();
			HallePaneController.getOneInstance().addSuchBeobachter(benutzerSuchPaneController.getSuchView());
		}
		return benutzerSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(benutzerSuchPaneView==null){
			benutzerSuchPaneView = new HalleSuchPaneView(this,HallePaneController.getOneInstance().getIModel());
		}
		return benutzerSuchPaneView;
	}

	
	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();
	}

	@Override
	public void holeAbteilung() throws SQLException, LagerException {
		AbteilungMCController.getOneInstance().holeAlleBeans(this);
	}

	//Matchode - Fenster "Abteilung" ruft zurück.
	@Override
	public void selectedAbteilungBeans(ArrayList<AbteilungBean> abteilungBeans) {
		if (abteilungBeans!=null && abteilungBeans.size()>0){
			((HalleSuchPaneView)getSuchView()).setAbteilung(abteilungBeans.get(0));
		}
	}

	@Override
	public void holeHalle() throws SQLException, LagerException {
		HalleMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedHalleBeans(ArrayList<HalleBean> halleBeans) {
		if (halleBeans!=null && halleBeans.size()>0){
			((HalleSuchPaneView)getSuchView()).setHalle(halleBeans.get(0));
		}
	}

	@Override
	public void holeEtage() throws SQLException, LagerException {
		EtageMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedEtageBeans(ArrayList<EtageBean> etageBeans) {
		if (etageBeans!=null && etageBeans.size()>0){
			((HalleSuchPaneView)getSuchView()).setEtage(etageBeans.get(0));
		}
	}

	@Override
	public void holeArtikel() throws SQLException, LagerException {
		ArtikelMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
		if (artikelBeans!=null && artikelBeans.size()>0){
			((HalleSuchPaneView)getSuchView()).setArtikel(artikelBeans.get(0));
		}
	}

//	@Override
//	public void holeBenutzer() throws SQLException, LagerException {
//        BenutzerMCController.getOneInstance().holeAlleBeans(this);
//	}
//
//	@Override
//	public void selectedBenutzerBeans(ArrayList<BenutzerBean> benutzerBeans) {
//		if (benutzerBeans!=null && benutzerBeans.size()>0){
//			((HalleSuchPaneView)getSuchView()).setBenutzer(benutzerBeans.get(0));
//		}
//	}


}
