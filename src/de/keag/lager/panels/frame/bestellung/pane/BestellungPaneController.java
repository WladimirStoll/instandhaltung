package de.keag.lager.panels.frame.bestellung.pane;

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
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.bericht.BerichtSuchBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.bestellung.BestellungPosBean;
import de.keag.lager.panels.frame.bestellung.BestellungSuchBean;
import de.keag.lager.panels.frame.bestellung.pane.details.bestellung.BestellungDetailsController;
import de.keag.lager.panels.frame.bestellung.pane.details.position.BestellungPosDetailsController;
import de.keag.lager.panels.frame.bestellung.pane.liste.BestellungListController;
import de.keag.lager.panels.frame.bestellung.pane.suche.BestellungSuchController;
import de.keag.lager.panels.frame.bestellung.pane.tree.BestellungTreeController;

public class BestellungPaneController extends PaneController{
//	private IConroller iAktiverConroller = null;
	private static BestellungPaneController baPaneController;
	private BestellungPane baPane; 
	private BestellungModel baModel;
	
	public static BestellungPaneController getOneInstance(){
		if (baPaneController == null){
			baPaneController = new BestellungPaneController();
		}
		return baPaneController;
	}
	
	@Override
	public void setGewaehlteZeile(int gewaehlteZeilenNummer) {
		ModelKnotenBean modelKnotenBean = getModel().selectKnoten(gewaehlteZeilenNummer);
		selectKnoten(modelKnotenBean); //Details sollen wieder angezeigt werden.
	}
	
	private BestellungPane getBaPane() {
		if(baPane==null){
			baPane = new BestellungPane(this,getModel());
		}
		return baPane;
	}
	
	private BestellungModel getModel() {
		if(baModel==null){
			baModel = new BestellungModel();
		}
		return baModel;
	}
	
	public void erzeugeNeueBaPosition() {
		getModel().erzeugeNeueBaPosition();
	}

	public void loescheBaPosition(BestellungPosBean baPosBean) {
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
		getModel().suchen((BestellungSuchBean)iSuchBean);
	}

	/**
	 * Delegieren --> BaTreePane erzeugen / holen
	 * @return
	 */
	@Override
	public TreeView getTreePane() {
		return BestellungTreeController.getOneInstance().getTreeView();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	@Override
	public SuchView getSuchView() {
		return BestellungSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return BestellungListController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if (selectedModelKnotenBean!=null){
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTELLUNG){
				JPanel BaBlatt = BestellungDetailsController.getOneInstance().getDetailsView();
				getBaPane().setBlatt(BaBlatt);
			}else{
				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTELLUNGSPOSITION){
					JPanel baPosBlattView = 
						BestellungPosDetailsController.getOneInstance().getDetailsView();
					getBaPane().setBlatt(baPosBlattView);
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
		getModel().erstelleNeuenSatz();
		JPanel BaBlatt = BestellungDetailsController.getOneInstance().getDetailsView();
		getBaPane().setBlatt(BaBlatt);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((BestellungBean)bean);
//		Log.log().severe("nict implementiert " + baBean);
	}

	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((BestellungBean)bean);
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		JPanel BaBlatt = BestellungDetailsController.getOneInstance().getDetailsView();
		getBaPane().setBlatt(BaBlatt);
		getModel().abbrechenSatz((BestellungBean)bean);
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
	 * Hook
	 * @throws Exception 
	 */
	@Override
	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws Exception{
		//Aktuelle Bean(BaBean)
		BestellungBean baBean = (BestellungBean)bean;
		
		//Druckausgabe erzeugen
		BerichtPaneController.getOneInstance().drucken(
				Berichttyp.BESTELLUNG, //Anforderungen
				baBean.getId(), //Anforderungs-ID
				Berichtart.EMAIL, //Per E-Mail verschicken
				null
		);
		
		//Anzeige vorbereiten (nach dem Drucken)
		BerichtSuchBean suchBean = new BerichtSuchBean();
		suchBean.setBerichtTyp(Berichttyp.BESTELLUNG);
		suchBean.setBerichtTypId(baBean.getId()); //Wurde eben erzeugt und gedruckt(bzw. versandt)
		suchBean.setBerichtArt(Berichtart.EMAIL);
		//aktuell gedrucktes Dokument auswählen
		BerichtPaneController.getOneInstance().suchen(suchBean);
		
		//Anzeigen (Und beim Schliesse beim Schliessen aber den aktuellen Kontroller wieder anzeigen)
		BerichtPaneController.getOneInstance().showView(this);
		
	};
	
}
