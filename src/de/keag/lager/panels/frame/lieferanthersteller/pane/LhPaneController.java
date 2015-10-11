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
package de.keag.lager.panels.frame.lieferanthersteller.pane;

import java.util.ArrayList;

import javax.swing.JPanel;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.ISuchBeobachter;
import de.keag.lager.core.ITreeBeobachter;
import de.keag.lager.core.ListView;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.lieferanthersteller.pane.tree.LhTreePaneController;
import de.keag.lager.panels.frame.lieferanthersteller.pane.suche.LhSuchController;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhZugriffsrechtBean;
import de.keag.lager.panels.frame.lieferanthersteller.pane.details.Lh.LhDetailsController;
import de.keag.lager.panels.frame.lieferanthersteller.pane.details.lhKatalog.LhKatalogDetailsController;
import de.keag.lager.panels.frame.lieferanthersteller.pane.liste.LhListPaneController;


public class LhPaneController extends PaneController {
	private static LhPaneController lhPaneController;
	private LhPane lhPane; 
	private LhModel lhModel;
	
	public static LhPaneController getOneInstance(){
		if (lhPaneController == null){
			lhPaneController = new LhPaneController();
		}
		return lhPaneController;
	}
	
	private LhPane getLhPane() {
		if(lhPane==null){
			lhPane = new LhPane(this,getModel());
		}
		return lhPane;
	}

	@Override
	public void showView(){
		getLhPane().setContentPane(Run.getOneInstance().getMainFrame());
	}

	@Override
	public void addSuchBeobachter(ISuchBeobachter iSuchBeobachter) {
		getModel().addISuchBeobachters(iSuchBeobachter);
	}

	@Override
	public void addTreeBeobachter(ITreeBeobachter iTreeBeobachter) {
		getModel().addITreeBeobachter(iTreeBeobachter);
	}

	@Override
	public void suchen(ISuchBean iSuchBean) {
		getModel().suchen(iSuchBean);
	}

	/**
	 * Delegieren --> BaTreePane erzeugen / holen
	 * @return
	 */
	@Override
	public TreeView getTreePane() {
		return LhTreePaneController.getOneInstance().getLhTreePane();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	
	@Override
	public SuchView getSuchView() {
		return LhSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return LhListPaneController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_HERSTELLER){
			JPanel details = LhDetailsController.getOneInstance().getDetailsView();
			getLhPane().setBlatt(details);
		}else{
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG){
				JPanel lhKatalogDetailsView = 
					LhKatalogDetailsController.getOneInstance().getDetailsView();
				getLhPane().setBlatt(lhKatalogDetailsView);
			}else{
				Log.log().severe("nicht implementiert");
//				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT){
//					JPanel lhZugriffsrechtDetailsView = 
//					LhZugriffsrechtDetailsController.getOneInstance().getDetailsView();
//					getLhPane().setBlatt(lhZugriffsrechtDetailsView);
//				}
			}
		}
		getModel().selectKnoten(selectedModelKnotenBean);
	}

		
		private LhModel getModel() {
			if(lhModel==null){
				lhModel = new LhModel();
				try {
					lhModel.addIListBeobachter(getLhPane().getLhListPane());
				} catch (BenutzerOberflacheLagerException e) {
					e.printStackTrace();
					Log.log().severe(e.getLocalizedMessage());
					Log.log().severe(e.getFehler().getMessage());
				}
			}
			return lhModel;
		}
		
		

		
	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getModel().addIDetailsBeobachter(detailsView);
	}
	
	@Override
	public void erstelleNeuenSatz() {
		JPanel details = LhDetailsController.getOneInstance().getDetailsView();
		getLhPane().setBlatt(details);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		getModel().abbrechenSatz(bean);
	}
	

	
	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((LhBean)bean);
	}
	

	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	@Override
	public void ausgewaehlterKnotenIstGeandert(){
		getModel().ausgewaehlterKnotenIstGeandert();
		
	}

	@Override
	public void benachrichtigeTreeBeobachter() {
		getModel().benachrichtigeTreeBeobachter();
	}

	@Override
	public void benachrichtigeListBeobachter() {
		getModel().benachrichtigeListBeobachter();
		
	}

	@Override
	public ArrayList<Fehler> pruefeAktuellenSatz() {
		getModel().getFehlerList().clear();
		return getModel().pruefeAktuelleBean(getModel().getSelectedListModelKnotenBean());
	}

	@Override
	public void addListBeobachter(ListView listView) {
		getModel().addIListBeobachter(listView); 
//		getModel().addILhListPaneBeobachter((ILhListPaneBeobachter)listView);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((LhBean)bean);
//		Log.log().severe("nict implementiert " + baBean);
	}


	@Override
	protected void benachrichtigeBeobachter() {
		getModel().benachrichtigeBeobachter();
	}

	@Override
	public IModel getIModel() {
		return getModel();
	}
	
	public void erzeugeNeueLhKatalog() {
		getModel().erzeugeNeueLhKatalog();
	}

//	public void erzeugeNeueLhZugriffsrecht() {
//		getModel().erzeugeNeueLhZugriffsrecht();
//	}

public void loescheLhKatalog(LhKatalogBean lhKatalogBean) {
	getModel().loescheLhKatalog(lhKatalogBean);
}

//public void loescheLhZugriffsrecht(LhZugriffsrechtBean lhZugriffsrechtBean) {
//	getModel().loescheLhZugriffsrecht(lhZugriffsrechtBean);
//}




}
