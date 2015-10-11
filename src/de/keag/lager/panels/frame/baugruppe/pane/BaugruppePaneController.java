package de.keag.lager.panels.frame.baugruppe.pane;

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
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.pane.details.baugruppe.BaugruppeDetailsController;
import de.keag.lager.panels.frame.baugruppe.pane.details.baugruppeArtikel.BaugruppeArtikelDetailsController;
import de.keag.lager.panels.frame.baugruppe.pane.liste.BaugruppeListPaneController;
import de.keag.lager.panels.frame.baugruppe.pane.suche.BaugruppeSuchController;
import de.keag.lager.panels.frame.baugruppe.pane.tree.BaugruppeTreePaneController;


public class BaugruppePaneController extends PaneController {
	private static BaugruppePaneController baugruppePaneController;
	private BaugruppePane baugruppePane; 
	private BaugruppeModel baugruppeModel;
	
	public static BaugruppePaneController getOneInstance(){
		if (baugruppePaneController == null){
			baugruppePaneController = new BaugruppePaneController();
		}
		return baugruppePaneController;
	}
	
	private BaugruppePane getBaugruppePane() {
		if(baugruppePane==null){
			baugruppePane = new BaugruppePane(this,getModel());
		}
		return baugruppePane;
	}

	@Override
	public void showView(){
		getBaugruppePane().setContentPane(Run.getOneInstance().getMainFrame());
	}

	private BaugruppeModel getModel() {
		if(baugruppeModel==null){
			baugruppeModel = new BaugruppeModel();
			try {
				baugruppeModel.addIListBeobachter(getBaugruppePane().getBaugruppeListPane());
			} catch (LagerException e) {
				e.printStackTrace();
				Log.log().severe(e.getLocalizedMessage());
				Log.log().severe(e.getFehler().getMessage());
			}
		}
		return baugruppeModel;
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
		return BaugruppeTreePaneController.getOneInstance().getBaugruppeTreePane();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	
	@Override
	public SuchView getSuchView() {
		return BaugruppeSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return BaugruppeListPaneController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BAUGRUPPE){
			JPanel details = BaugruppeDetailsController.getOneInstance().getDetailsView();
			getBaugruppePane().setBlatt(details);
		}else{
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BAUGRUPPE_ARTIKEL){
				JPanel baugruppeArtikelDetailsView = 
					BaugruppeArtikelDetailsController.getOneInstance().getDetailsView();
				getBaugruppePane().setBlatt(baugruppeArtikelDetailsView);
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
		JPanel details = BaugruppeDetailsController.getOneInstance().getDetailsView();
		getBaugruppePane().setBlatt(details);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		getModel().abbrechenSatz(bean);
		if (getModel().getSelectedListModelKnotenBean()!=null){
			selectKnoten(getModel().getSelectedListModelKnotenBean()); //oberfläche neu zeichnen
		}
		benachrichtigeBeobachter();
	}
	

	public void erzeugeNeueBaugruppeBaugruppe()  {
		getModel().erzeugeNeueBaugruppeBaugruppe();
	}

	public void loescheBaugruppeBaugruppe(BaugruppeBean bean) {
		getModel().loescheBaugruppeBaugruppe(bean);
	}
	
	public void loescheBaugruppeArtikel(BaugruppeArtikelBean bean) {
		getModel().loescheBaugruppeArtikel(bean);
	}
	
	
	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((BaugruppeBean)bean);
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
		getModel().speichereSatz((BaugruppeBean)bean);
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

	public void erzeugeNeueBaugruppeArtikel() {
		getModel().erzeugeNeueBaugruppeArtikel();
	}




}
