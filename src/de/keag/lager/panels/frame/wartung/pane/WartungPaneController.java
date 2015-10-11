package de.keag.lager.panels.frame.wartung.pane;

import java.sql.SQLException;
import java.util.ArrayList;
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
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.pane.details.anforderung.EntnahmePosDetailsController;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartung.pane.details.wartung.WartungDetailsController;
import de.keag.lager.panels.frame.wartung.pane.details.wartungsMitarbeiter.WartungsMitarbeiterDetailsController;
import de.keag.lager.panels.frame.wartung.pane.details.wartungsArtikel.WartungsArtikelDetailsController;
import de.keag.lager.panels.frame.wartung.pane.liste.WartungListPaneController;
import de.keag.lager.panels.frame.wartung.pane.suche.WartungSuchController;
import de.keag.lager.panels.frame.wartung.pane.tree.WartungTreePaneController;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;


public class WartungPaneController extends PaneController {
	private static WartungPaneController benutzerPaneController;
	private WartungPane benutzerPane; 
	private WartungModel wartungModel;
	
	public static WartungPaneController getOneInstance(){
		if (benutzerPaneController == null){
			benutzerPaneController = new WartungPaneController();
		}
		return benutzerPaneController;
	}
	
	private WartungPane getWartungPane() {
		if(benutzerPane==null){
			benutzerPane = new WartungPane(this,getModel());
		}
		return benutzerPane;
	}

	@Override
	public void showView(){
		getWartungPane().setContentPane(Run.getOneInstance().getMainFrame());
		
	}

	private WartungModel getModel() {
		if(wartungModel==null){
			wartungModel = new WartungModel();
			try {
				wartungModel.addIListBeobachter(getWartungPane().getWartungListPane());
			} catch (BenutzerOberflacheLagerException e) {
				e.printStackTrace();
				Log.log().severe(e.getLocalizedMessage());
				Log.log().severe(e.getFehler().getMessage());
			}
		}
		return wartungModel;
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
		return WartungTreePaneController.getOneInstance().getWartungTreePane();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	
	@Override
	public SuchView getSuchView() {
		return WartungSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return WartungListPaneController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.WARTUNG){
			JPanel details = WartungDetailsController.getOneInstance().getDetailsView();
			getWartungPane().setBlatt(details);
		}else{
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.WARTUNG_MITARBEITER){
				JPanel wartungsMitarbeiterDetailsView = 
					WartungsMitarbeiterDetailsController.getOneInstance().getDetailsView();
				getWartungPane().setBlatt(wartungsMitarbeiterDetailsView);
			}else{
				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.WARTUNG_ARTIKEL){
					JPanel benutzerZugriffsrechtDetailsView = 
					WartungsArtikelDetailsController.getOneInstance().getDetailsView();
					getWartungPane().setBlatt(benutzerZugriffsrechtDetailsView);
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
		JPanel details = WartungDetailsController.getOneInstance().getDetailsView();
		getWartungPane().setBlatt(details);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void abbrechenSatz(Bean bean) {
//		getModel().abbrechenSatz(bean);
		JPanel viewPanel = WartungDetailsController.getOneInstance().getDetailsView();
		getWartungPane().setBlatt(viewPanel);
		getModel().abbrechenSatz((WartungBean)bean);
		selectKnoten(getModel().getSelectedListModelKnotenBean()); //oberfläche neu zeichnen
	}
	

	public void erzeugeNeueWartungsMitarbeiter() {
		getModel().erzeugeNeueWartungsMitarbeiter();
	}

	public void loescheWartungsMitarbeiter(WartungsMitarbeiterBean wartungsMitarbeiterBean) {
		getModel().loescheWartungsMitarbeiter(wartungsMitarbeiterBean);
	}
	
	public void loescheWartungsArtikel(WartungsArtikelBean wartungsArtikelBean) {
		getModel().loescheWartungsArtikel(wartungsArtikelBean);
	}
	
	
	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((WartungBean)bean);
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
//		getModel().addIWartungListPaneBeobachter((IWartungListPaneBeobachter)listView);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((WartungBean)bean);
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

	public void erzeugeNeueWartungsArtikel() {
		getModel().erzeugeNeueWartungsArtikel();
	}

	public void generiereWartungsKartenId() throws SQLException, LagerException {
		getModel().generiereWartungsKartenId();
	}

	public void changeStatus(StatusEnum statusEnum) {
		getModel().changeStatus(statusEnum);
	}


	@Override
	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws LagerException, SQLException, Exception{
		getModel().druckeBericht((WartungBean)bean, druckParameter);
	};


}
