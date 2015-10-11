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
package de.keag.lager.panels.frame.bericht.pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtSuchBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.einzelberichte.anforderung.AnforderungBericht;
import de.keag.lager.panels.frame.bericht.einzelberichte.inventurListe.InventurBericht;
import de.keag.lager.panels.frame.bericht.einzelberichte.mindestBestand.MindestBestandsMengeBericht;
import de.keag.lager.panels.frame.bericht.einzelberichte.wartung.WartungsBericht;
import de.keag.lager.panels.frame.bericht.pane.details.bericht.BerichtDetailsController;
import de.keag.lager.panels.frame.bericht.pane.liste.BerichtListController;
import de.keag.lager.panels.frame.bericht.pane.suche.BerichtSuchController;
import de.keag.lager.panels.frame.bericht.pane.tree.BerichtTreeController;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.menu.MenuController;

public class BerichtPaneController extends PaneController{
//	private IConroller iAktiverConroller = null;
	private static BerichtPaneController paneController;
	private BerichtPane berichtPane; 
	private BerichtModel berichtModel;
	
	public static BerichtPaneController getOneInstance(){
		if (paneController == null){
			paneController = new BerichtPaneController();
		}
		return paneController;
	}
	
	@Override
	public void setGewaehlteZeile(int gewaehlteZeilenNummer) {
		ModelKnotenBean modelKnotenBean = getModel().selectKnoten(gewaehlteZeilenNummer);
		selectKnoten(modelKnotenBean); //Details sollen wieder angezeigt werden.
	}
	
	private BerichtPane getBaPane() {
		if(berichtPane==null){
			berichtPane = new BerichtPane(this,getModel());
		}
		return berichtPane;
	}
	
	private BerichtModel getModel() {
		if(berichtModel==null){
			berichtModel = new BerichtModel();
		}
		return berichtModel;
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
		getModel().suchen((BerichtSuchBean)iSuchBean);
	}

	/**
	 * Delegieren --> BaTreePane erzeugen / holen
	 * @return
	 */
	@Override
	public TreeView getTreePane() {
		return BerichtTreeController.getOneInstance().getTreeView();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	@Override
	public SuchView getSuchView() {
		return BerichtSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return BerichtListController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if (selectedModelKnotenBean!=null){
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BERICHT){
				JPanel BaBlatt = BerichtDetailsController.getOneInstance().getDetailsView();
				getBaPane().setBlatt(BaBlatt);
			}else{
				return;
//				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ANFORDERUNGSPOSITION){
//					JPanel baPosBlattView = 
//						BerichtPosDetailsController.getOneInstance().getDetailsView();
//					getBaPane().setBlatt(baPosBlattView);
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
		getModel().erstelleNeuenSatz();
		JPanel blatt = BerichtDetailsController.getOneInstance().getDetailsView();
		getBaPane().setBlatt(blatt);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((BerichtBean)bean);
//		Log.log().severe("nict implementiert " + baBean);
	}

	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((BerichtBean)bean);
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		JPanel BaBlatt = BerichtDetailsController.getOneInstance().getDetailsView();
		getBaPane().setBlatt(BaBlatt);
		getModel().abbrechenSatz((BerichtBean)bean);
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

	/**
	 * 
	 * @param berichtTyp <<< Anforderung oder Artikel etc.
	 * @param berichtTypId		 <<< id der Anforderung oder ID des Artikels etc.
	 * @param berichtArt <<< Drucken oder per EMail verschicken 
	 * @throws Exception 
	 */ 
	public void drucken(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt, Map<String, String> druckParameter) throws Exception {
		switch (berichtTyp) {
		case WARTUNG:
			switch (berichtArt) {
			case PRINT:
				druckeWartungsListe(berichtTyp, berichtTypId, berichtArt,druckParameter);
				break;
			case EMAIL:
				verschickeWartungsliste(berichtTyp, berichtTypId, berichtArt,druckParameter);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		case MINDEST_BESTANDS_LISTE:
			switch (berichtArt) {
			case PRINT:
				druckeMindestBestandsListe(berichtTyp, berichtTypId, berichtArt);
				break;
			case EMAIL:
				verschickeMindestBestandsliste(berichtTyp, berichtTypId, berichtArt);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		case INVENTUR_LISTE_HALLE:
			switch (berichtArt) {
			case PRINT:
				druckeInventurListeHalle(berichtTyp, berichtTypId, berichtArt);
				break;
			case EMAIL:
				verschickeInventurListeHalle(berichtTyp, berichtTypId, berichtArt);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		case INVENTUR_LISTE_ZEILE:
			switch (berichtArt) {
			case PRINT:
				druckeInventurListeZeile(berichtTyp, berichtTypId, berichtArt);
				break;
			case EMAIL:
				verschickeInventurListeZeile(berichtTyp, berichtTypId, berichtArt);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		case INVENTUR_LISTE_SAEULE:
			switch (berichtArt) {
			case PRINT:
				druckeInventurListeSaeule(berichtTyp, berichtTypId, berichtArt, druckParameter);
				break;
			case EMAIL:
				verschickeInventurListeSaeule(berichtTyp, berichtTypId, berichtArt);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		case ANFORDERUNG:
			switch (berichtArt) {
			case PRINT:
				druckeAnforderung(berichtTyp, berichtTypId, berichtArt);
				break;
			case EMAIL:
				verschickeAnforderung(berichtTyp, berichtTypId, berichtArt);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		case BESTELLUNG:
			switch (berichtArt) {
			case PRINT:
				druckeBestellung(berichtTyp, berichtTypId, berichtArt);
				break;
			case EMAIL:
				verschickeBestellung(berichtTyp, berichtTypId, berichtArt);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		case BENUTZER:
			switch (berichtArt) {
			case PRINT:
				druckeBenutzer(berichtTyp, berichtTypId, berichtArt);
				break;
			case EMAIL:
				verschickeBenutzer(berichtTyp, berichtTypId, berichtArt);
				break;
			default:
				throw new LagerException("Berichtart wird nicht unterstüzt.");
			}
			break;
		default:
			throw new LagerException("Berichttyp wird nicht unterstüzt.");
		}
	}

	/**
	 * Die Anforderugn wird gedruckt.
	 * 1. bericht-Satz wird angelegt. 
	 * 2. Druck-Daten werden in die Bericht-Tabellen übernommen. (SQL-Kopie von Anforderung)
	 * 3. E-Mail 
	 *   a) E-Mail wird erzeugt.
	 *   b) E-Mail wird verschickt. 
	 * @param berichtTypId
	 * @throws Exception 
	 */
	private void verschickeAnforderung(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt) throws Exception {
//		Log.log().severe("Anforderung kann nicht gedruckt werden:"+id);

		AnforderungBericht anforderungBericht =  new AnforderungBericht(null);
		anforderungBericht.erzeugeBerichtDbSatz(berichtTyp, berichtArt, berichtTypId); //neuen Satz anlegen.
		anforderungBericht.uebernehmeDruckDaten(); //Datenübernehmen
		anforderungBericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
//		Log.log().severe("Anforderung kann nicht verschickt werden:"+berichtTypId);
	}

	private void verschickeInventurListeHalle(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt) throws Exception {

		Log.log().severe("InventuerListeHalle kann nicht verschickt werden:"+berichtTypId);
	}
	private void verschickeWartungsliste(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt,Map<String, String> druckParameter) throws Exception {

		Log.log().severe("Wartungsliste kann nicht verschickt werden:"+berichtTypId);
	}
	
	private void verschickeMindestBestandsliste(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt) throws Exception {

		Log.log().severe("Mindestbestandsliste kann nicht verschickt werden:"+berichtTypId);
	}
	
	private void verschickeInventurListeZeile(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt) throws Exception {

		Log.log().severe("InventuerListeZeile kann nicht verschickt werden:"+berichtTypId);
	}
	private void verschickeInventurListeSaeule(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt) throws Exception {

		Log.log().severe("InventuerListeSaeule kann nicht verschickt werden:"+berichtTypId);
	}
	
	
	private void druckeAnforderung(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) {
		Log.log().severe("Anforderung kann nicht gedruckt werden:"+id);
	}
	
	private void druckeWartungsListe(Berichttyp berichtTyp, Integer id, Berichtart berichtArt, Map<String, String> druckParameter) throws Exception {
		WartungsBericht bericht =  new WartungsBericht(druckParameter);
		bericht.erzeugeBerichtDbSatz(berichtTyp, berichtArt, id); //neuen Satz anlegen.
		bericht.uebernehmeDruckDaten(); //Datenübernehmen
		bericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
	}
	
	private void druckeInventurListeHalle(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) throws Exception {
		InventurBericht bericht =  new InventurBericht(null);
		bericht.erzeugeBerichtDbSatz(berichtTyp, berichtArt, id); //neuen Satz anlegen.
		bericht.uebernehmeDruckDaten(); //Datenübernehmen
		bericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
	}
	
	private void druckeMindestBestandsListe(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) throws Exception {
		MindestBestandsMengeBericht bericht =  new MindestBestandsMengeBericht(null);
		bericht.erzeugeBerichtDbSatz(berichtTyp, berichtArt, id); //neuen Satz anlegen.
		bericht.uebernehmeDruckDaten(); //Datenübernehmen
		bericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
	}
	
	private void druckeInventurListeZeile(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) throws Exception {
		InventurBericht bericht =  new InventurBericht(null);
		bericht.erzeugeBerichtDbSatz(berichtTyp, berichtArt, id); //neuen Satz anlegen.
		bericht.uebernehmeDruckDaten(); //Datenübernehmen
		bericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
	}
	private void druckeInventurListeSaeule(Berichttyp berichtTyp, Integer id, Berichtart berichtArt, Map<String, String> druckParameter) throws Exception {
		InventurBericht bericht =  new InventurBericht(druckParameter);
		bericht.erzeugeBerichtDbSatz(berichtTyp, berichtArt, id); //neuen Satz anlegen.
		bericht.uebernehmeDruckDaten(); //Datenübernehmen
		bericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
	}
	
	
	/**
	 * Die Anforderugn wird gedruckt.
	 * 1. bericht-Satz wird angelegt. 
	 * 2. Druck-Daten werden in die Bericht-Tabellen übernommen. (SQL-Kopie von Anforderung)
	 * 3. E-Mail 
	 *   a) E-Mail wird erzeugt.
	 *   b) E-Mail wird verschickt. 
	 * @param id
	 * @throws LagerException 
	 * @throws SQLException 
	 */
	private void verschickeBestellung(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) throws SQLException, LagerException {
		Log.log().severe("Bestellung kann nicht gedruckt werden:"+id);
//		AnforderungBericht anforderungBericht =  new AnforderungBericht();
//		anforderungBericht.erzeugeBerichtDbSatz(id, berichtArt, berichtTyp); //neuen Satz anlegen.
//		anforderungBericht.uebernehmeDruckDaten(); //Datenübernehmen
//		anforderungBericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
	}
	
	private void druckeBestellung(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) {
		Log.log().severe("Anforderung kann nicht gedruckt werden:"+id);
	}
	
	
	private void druckeBenutzer(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) {
		Log.log().severe("Benutzer kann nicht gedruckt werden:"+id);
	}
	
	private void verschickeBenutzer(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) {
		Log.log().severe("Benutzer kann nicht gedruckt werden:"+id);
	}
	
	@Override
	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws LagerException, SQLException, Exception{
		if (bean instanceof BerichtBean){
			drucken(
					((BerichtBean)bean).getBerichtTyp(),
					((BerichtBean)bean).getBerichtTypId(),
					((BerichtBean)bean).getBerichtArt(),
					druckParameter
			);
		}else{
			throw new LagerException(new Fehler("Bean ist keine Berichtbean:"+bean ));
		}
	}

	public void druckeMindestBestandsListe(PaneController paneController) throws Exception {
		//per E-Mail verschicken
		getModel().druckeMindestBestandsListe();
		// ----------- Jetzt nur noch anzeigen -----------
		
//		//Anzeige vorbereiten (nach dem Drucken)
//		BerichtSuchBean suchBean = new BerichtSuchBean();
//		suchBean.setBerichtTyp(Berichttyp.MINDEST_BESTANDS_LISTE);
//		suchBean.setBerichtTypId(-1); //alle rückwärts vom heutigen Tag
//		suchBean.setBerichtArt(Berichtart.PRINT);
//		//aktuell gedrucktes Dokument auswählen
//		this.suchen(suchBean);
//		//Anzeigen (Und beim Schliesse beim Schliessen aber den aktuellen Kontroller wieder anzeigen)
//		this.showView(paneController);
				
	}
	
}
