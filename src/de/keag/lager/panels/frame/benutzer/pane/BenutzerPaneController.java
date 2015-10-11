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
package de.keag.lager.panels.frame.benutzer.pane;

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
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzer.BenutzerDetailsController;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzer.BenutzerDetailsView;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzer.IBenutzerDetailsBeobachter;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung.BenutzerAbteilungDetailsController;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung.IBenutzerAbteilungDetailsBeobachter;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerRecht.BenutzerZugriffsrechtDetailsController;
import de.keag.lager.panels.frame.benutzer.pane.liste.BenutzerListPaneController;
import de.keag.lager.panels.frame.benutzer.pane.liste.BenutzerListPaneView;
import de.keag.lager.panels.frame.benutzer.pane.liste.IBenutzerListPaneBeobachter;
import de.keag.lager.panels.frame.benutzer.pane.suche.BenutzerSuchController;
import de.keag.lager.panels.frame.benutzer.pane.suche.BenutzerSuchPaneView;
import de.keag.lager.panels.frame.benutzer.pane.suche.IBenutzerSuchPaneBeobachter;
import de.keag.lager.panels.frame.benutzer.pane.tree.BenutzerTreePaneController;
import de.keag.lager.panels.frame.benutzer.pane.tree.BenutzerTreePaneView;
import de.keag.lager.panels.frame.benutzer.pane.tree.IBenutzerTreePaneBeobachter;


public class BenutzerPaneController extends PaneController {
	private static BenutzerPaneController benutzerPaneController;
	private BenutzerPane benutzerPane; 
	private BenutzerModel benutzerModel;
	
	public static BenutzerPaneController getOneInstance(){
		if (benutzerPaneController == null){
			benutzerPaneController = new BenutzerPaneController();
		}
		return benutzerPaneController;
	}
	
	private BenutzerPane getBenutzerPane() {
		if(benutzerPane==null){
			benutzerPane = new BenutzerPane(this,getModel());
		}
		return benutzerPane;
	}

	@Override
	public void showView(){
		getBenutzerPane().setContentPane(Run.getOneInstance().getMainFrame());
	}

	private BenutzerModel getModel() {
		if(benutzerModel==null){
			benutzerModel = new BenutzerModel();
			try {
				benutzerModel.addIListBeobachter(getBenutzerPane().getBenutzerListPane());
			} catch (BenutzerOberflacheLagerException e) {
				e.printStackTrace();
				Log.log().severe(e.getLocalizedMessage());
				Log.log().severe(e.getFehler().getMessage());
			}
		}
		return benutzerModel;
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
		return BenutzerTreePaneController.getOneInstance().getBenutzerTreePane();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	
	@Override
	public SuchView getSuchView() {
		return BenutzerSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return BenutzerListPaneController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER){
			JPanel details = BenutzerDetailsController.getOneInstance().getDetailsView();
			getBenutzerPane().setBlatt(details);
		}else{
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ABTEILUNG){
				JPanel benutzerAbteilungDetailsView = 
					BenutzerAbteilungDetailsController.getOneInstance().getDetailsView();
				getBenutzerPane().setBlatt(benutzerAbteilungDetailsView);
			}else{
				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT){
					JPanel benutzerZugriffsrechtDetailsView = 
					BenutzerZugriffsrechtDetailsController.getOneInstance().getDetailsView();
					getBenutzerPane().setBlatt(benutzerZugriffsrechtDetailsView);
				}
				
			}
		}
		getModel().selectKnoten(selectedModelKnotenBean);
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getModel().addIDetailsBeobachter(detailsView);
	}
	
	@Override
	public void erstelleNeuenSatz() {
		JPanel details = BenutzerDetailsController.getOneInstance().getDetailsView();
		getBenutzerPane().setBlatt(details);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		getModel().abbrechenSatz(bean);
	}
	

	public void erzeugeNeueBenutzerAbteilung() {
		getModel().erzeugeNeueBenutzerAbteilung();
	}

	public void loescheBenutzerAbteilung(BenutzerAbteilungBean benutzerAbteilungBean) {
		getModel().loescheBenutzerAbteilung(benutzerAbteilungBean);
	}
	
	public void loescheBenutzerZugriffsrecht(BenutzerZugriffsrechtBean benutzerZugriffsrechtBean) {
		getModel().loescheBenutzerZugriffsrecht(benutzerZugriffsrechtBean);
	}
	
	
	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((BenutzerBean)bean);
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
//		getModel().addIBenutzerListPaneBeobachter((IBenutzerListPaneBeobachter)listView);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((BenutzerBean)bean);
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

	public void erzeugeNeueBenutzerZugriffsrecht() {
		getModel().erzeugeNeueBenutzerZugriffsrecht();
	}




}
