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
package de.keag.lager.panels.frame.lieferanthersteller.pane.details.lhKatalog;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.pane.LhPaneController;
//import de.keag.lager.panels.frame.lieferanthersteller.pane.details.benutzerRecht.LhZugriffsrechtDetailsView;
import de.keag.lager.panels.frame.matchcode.katalog.KatalogMCController;
import de.keag.lager.panels.frame.matchcode.katalog.IKatalogMCAnforderer;

public class LhKatalogDetailsController extends DetailsController implements  
										IKatalogMCAnforderer {
	private static LhKatalogDetailsController LhPosKatalogBlattController;
	private LhKatalogDetailsView detailsView;
	
	private LhKatalogDetailsController() {
		super();
	}

	public static LhKatalogDetailsController getOneInstance() {
		if(LhPosKatalogBlattController==null){
			LhPosKatalogBlattController = new LhKatalogDetailsController();
			LhPosKatalogBlattController.addDetailsBeobachter (LhPosKatalogBlattController.getDetailsView());
		}
		return LhPosKatalogBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new LhKatalogDetailsView(this);
		}
		return detailsView;
	}

	@Override
	public void holeKatalog() throws SQLException, LagerException {
		KatalogMCController.getOneInstance().holeAlleBeans(this);
	}

	//Matchode - Fenster "Katalog" ruft zurück.
	@Override
	public void selectedKatalogBeans(ArrayList<KatalogBean> katalogBeans) {
		if (katalogBeans!=null && katalogBeans.size()>0){
			((LhKatalogDetailsView)getDetailsView()).setKatalog(katalogBeans.get(0));
		}
	}
	

//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	protected void ausgewaehlterKnotenIstGeandert() {
//		LhPaneController.getOneInstance().ausgewaehlterKnotenIstGeandert();
//	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = LhPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof LhKatalogBean){
//			LhKatalogBean benutzerPosBean = (LhKatalogBean)iBean;
//			errors = getLhPosKatalogDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = benutzerPosBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
//		return errors;
//	}


	
	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		Log.log().severe("nicht implementiert");
	}


	@Override
	public void loeschePosition(Bean bean) {
		Log.log().severe("nicht implementiert");
	}

	@Override
	public PaneController getPaneController() {
		return LhPaneController.getOneInstance();
	}
	
}
