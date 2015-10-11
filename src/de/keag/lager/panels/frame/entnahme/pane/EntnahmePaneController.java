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
package de.keag.lager.panels.frame.entnahme.pane;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.IDetailsBeobachter;
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
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosSuchBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeStatus;

import de.keag.lager.panels.frame.entnahme.pane.details.anforderung.EntnahmePosDetailsController;
import de.keag.lager.panels.frame.entnahme.pane.liste.EntnahmeListController;
import de.keag.lager.panels.frame.entnahme.pane.suche.EntnahmeSuchController;
import de.keag.lager.panels.frame.entnahme.pane.tree.EntnahmeTreeController;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class EntnahmePaneController extends PaneController{
//	private IConroller iAktiverConroller = null;
	private static EntnahmePaneController entnahmePaneController;
	private EntnahmePane entnahmePane; 
	private EntnahmeModel entnahmeModel;
	
	public static EntnahmePaneController getOneInstance(){
		if (entnahmePaneController == null){
			entnahmePaneController = new EntnahmePaneController();
		}
		return entnahmePaneController;
	}
	
	@Override
	public void setGewaehlteZeile(int gewaehlteZeilenNummer) {
		ModelKnotenBean modelKnotenBean = getModel().selectKnoten(gewaehlteZeilenNummer);
		selectKnoten(modelKnotenBean); //Details sollen wieder angezeigt werden.
	}
	
	private EntnahmePane getEntnahmePane() {
		if(entnahmePane==null){
			entnahmePane = new EntnahmePane(this,getModel());
		}
		return entnahmePane;
	}
	
	private EntnahmeModel getModel() {
		if(entnahmeModel==null){
			entnahmeModel = new EntnahmeModel();
		}
		return entnahmeModel;
	}
	
//	public void erzeugeNeueEntnahmePosition() {
//		getModel().erzeugeNeueEntnahmePosition();
//	}
//
//	public void loescheEntnahmePosition(EntnahmePosBean entnahmePosBean) {
//		getModel().loescheEntnahmePosition(entnahmePosBean);
//	}
//
//	
	
	@Override
	public void showView(){
		getEntnahmePane().setContentPane(Run.getOneInstance().getMainFrame());
	}

	@Override
	public IModel getIModel(){
		return getModel();
	}

	@Override
	protected void benachrichtigeBeobachter() {
		getModel().benachrichtigeBeobachter();
	}

	@Override
	public void addSuchBeobachter(ISuchBeobachter entnahmeSuchPane) {
		getModel().addISuchBeobachters(entnahmeSuchPane);
	}
	

	@Override
	public void addTreeBeobachter(ITreeBeobachter iTreeBeobachter) {
		getModel().addITreeBeobachter(iTreeBeobachter);
	}

	@Override
	public void suchen(ISuchBean iSuchBean) {
		getModel().suchen((EntnahmePosSuchBean)iSuchBean);
	}

	/**
	 * Delegieren --> EntnahmeTreePane erzeugen / holen
	 * @return
	 */
	@Override
	public TreeView getTreePane() {
		return EntnahmeTreeController.getOneInstance().getTreeView();
	}

	/**
	 * Delegieren --> EntnahmeSuchPane erzeugen / holen
	 * @return
	 */
	@Override
	public SuchView getSuchView() {
		return EntnahmeSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return EntnahmeListController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if (selectedModelKnotenBean!=null){
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ENTNAHMENSPOSITION){
				JPanel EntnahmeBlatt = EntnahmePosDetailsController.getOneInstance().getDetailsView();
				getEntnahmePane().setBlatt(EntnahmeBlatt);
			}else{
				Log.log().severe("Fehler:typ falsch:"+selectedModelKnotenBean);
//				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ENTNAHMENSPOSITION){
//					JPanel entnahmePosBlattView = 
//						EntnahmePosDetailsController.getOneInstance().getDetailsView();
//					getEntnahmePane().setBlatt(entnahmePosBlattView);
//				}
			}
			getModel().selectKnoten(selectedModelKnotenBean);
		}
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getModel().addIDetailsBeobachter(detailsView);
	}

	@Override
	public void erstelleNeuenSatz() {
		JPanel entnahmePosBlatt = EntnahmePosDetailsController.getOneInstance().getDetailsView();
		getEntnahmePane().setBlatt(entnahmePosBlatt);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((EntnahmePosBean)bean);
//		Log.log().severe("nict implementiert " + entnahmeBean);
	}

	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((EntnahmePosBean)bean);
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		JPanel EntnahmeBlatt = EntnahmePosDetailsController.getOneInstance().getDetailsView();
		getEntnahmePane().setBlatt(EntnahmeBlatt);
		getModel().abbrechenSatz((EntnahmePosBean)bean);
		selectKnoten(getModel().getSelectedListModelKnotenBean()); //oberfläche neu zeichnen
	}

//	public void addBaPosDetailsBeobachter(IDetailsBeobachter baPosDetailsView) {
////		getBaModel().addIBaPosDetailsBeobachter(baPosDetailsView);
//		getBaModel().addIDetailsBeobachter(baPosDetailsView);
//	}

	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	@Override
	public void ausgewaehlterKnotenIstGeandert() {
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
	}

	public void vervollstaendigePosition(PositionBean positionBean) throws LagerException, SQLException {
		getModel().vervollstaendigePosition(positionBean);
	}

	public void bestaetigeEntnahmePosition() {
		getModel().bestaetigeEntnahmePosition();
	}	
}
