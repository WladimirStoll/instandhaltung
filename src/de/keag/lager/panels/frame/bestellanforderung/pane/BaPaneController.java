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
package de.keag.lager.panels.frame.bestellanforderung.pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
import de.keag.lager.panels.frame.artikel.pane.details.artikelBaugruppe.ArtikelBaugruppeDetailsController;
import de.keag.lager.panels.frame.bericht.BerichtSuchBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.BaSuchBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung.BaDetailsController;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.position.BaPosDetailsController;
import de.keag.lager.panels.frame.bestellanforderung.pane.liste.BaListController;
import de.keag.lager.panels.frame.bestellanforderung.pane.suche.BaSuchController;
import de.keag.lager.panels.frame.bestellanforderung.pane.tree.BaTreeController;

public class BaPaneController extends PaneController{
//	private IConroller iAktiverConroller = null;
	private static BaPaneController baPaneController;
	private BaPane baPane; 
	private BaModel baModel;
	
	public static BaPaneController getOneInstance(){
		if (baPaneController == null){
			baPaneController = new BaPaneController();
		}
		return baPaneController;
	}
	
	@Override
	public void setGewaehlteZeile(int gewaehlteZeilenNummer) {
		ModelKnotenBean modelKnotenBean = getModel().selectKnoten(gewaehlteZeilenNummer);
		selectKnoten(modelKnotenBean); //Details sollen wieder angezeigt werden.
	}
	
	private BaPane getBaPane() {
		if(baPane==null){
			baPane = new BaPane(this,getModel());
		}
		return baPane;
	}
	
	private BaModel getModel() {
		if(baModel==null){
			baModel = new BaModel();
		}
		return baModel;
	}
	
	public void erzeugeNeueBaPosition() {
		getModel().erzeugeNeueBaPosition();
	}

	public void loescheBaPosition(BaPosBean baPosBean) {
		getModel().loescheBaPosition(baPosBean);
	}

	
	
	@Override
	public void showView(){
		getBaPane().setContentPane(Run.getOneInstance().getMainFrame());
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
	public void addSuchBeobachter(ISuchBeobachter baSuchPane) {
		getModel().addISuchBeobachters(baSuchPane);
	}
	

	@Override
	public void addTreeBeobachter(ITreeBeobachter iTreeBeobachter) {
		getModel().addITreeBeobachter(iTreeBeobachter);
	}

	@Override
	public void suchen(ISuchBean iSuchBean) {
		getModel().suchen((BaSuchBean)iSuchBean);
	}

	/**
	 * Delegieren --> BaTreePane erzeugen / holen
	 * @return
	 */
	@Override
	public TreeView getTreePane() {
		return BaTreeController.getOneInstance().getTreeView();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	@Override
	public SuchView getSuchView() {
		return BaSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return BaListController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if (selectedModelKnotenBean!=null){
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ANFORDERUNG){
				JPanel BaBlatt = BaDetailsController.getOneInstance().getDetailsView();
				getBaPane().setBlatt(BaBlatt);
				setzeNeuenAktivenController(BaDetailsController.getOneInstance());
			}else{
				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ANFORDERUNGSPOSITION){
					JPanel baPosBlattView = 
						BaPosDetailsController.getOneInstance().getDetailsView();
					getBaPane().setBlatt(baPosBlattView);
					setzeNeuenAktivenController(BaPosDetailsController.getOneInstance());
				}
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
		JPanel BaBlatt = BaDetailsController.getOneInstance().getDetailsView();
		getModel().erstelleNeuenSatz();
		getBaPane().setBlatt(BaBlatt);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((BaBean)bean);
//		Log.log().severe("nict implementiert " + baBean);
	}

	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((BaBean)bean);
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		JPanel BaBlatt = BaDetailsController.getOneInstance().getDetailsView();
		getBaPane().setBlatt(BaBlatt);
		getModel().abbrechenSatz((BaBean)bean);
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

//	/**
//	 * Hook
//	 * @throws LagerException 
//	 * @throws SQLException 
//	 */
//	@Override
//	public void druckeSatz(Bean bean) throws LagerException, SQLException{
//		
//		Log.log().severe("nicht implementiert");
//		
//	}

	/**
	 * Aktueller Satz wird abgeschlossen und nach Bestellung kopiert.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Override
	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception{
		//per E-Mail verschicken
		getModel().druckeSatz();
		
		// ----------- Jetzt nur noch anzeigen -----------
		
		//Anzeige der Verwaltungsmaske
		BaBean baBean = (BaBean) getIModel().getSelectedListModelKnotenBean().getIBean();
		//Anzeige vorbereiten (nach dem Drucken)
		BerichtSuchBean suchBean = new BerichtSuchBean();
		suchBean.setBerichtTyp(Berichttyp.ANFORDERUNG);
		suchBean.setBerichtTypId(baBean.getId()); //Wurde eben erzeugt und gedruckt(bzw. versandt)
		suchBean.setBerichtArt(Berichtart.EMAIL);
		//aktuell gedrucktes Dokument auswählen
		BerichtPaneController.getOneInstance().suchen(suchBean);
		//Anzeigen (Und beim Schliesse beim Schliessen aber den aktuellen Kontroller wieder anzeigen)
		BerichtPaneController.getOneInstance().showView(this);
		
	}
	
}
