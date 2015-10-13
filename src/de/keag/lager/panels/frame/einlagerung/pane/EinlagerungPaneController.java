package de.keag.lager.panels.frame.einlagerung.pane;

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
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosSuchBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungStatus;

import de.keag.lager.panels.frame.einlagerung.pane.details.anforderung.EinlagerungPosDetailsController;
import de.keag.lager.panels.frame.einlagerung.pane.liste.EinlagerungListController;
import de.keag.lager.panels.frame.einlagerung.pane.suche.EinlagerungSuchController;
import de.keag.lager.panels.frame.einlagerung.pane.tree.EinlagerungTreeController;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class EinlagerungPaneController extends PaneController{
//	private IConroller iAktiverConroller = null;
	private static EinlagerungPaneController einlagerungPaneController;
	private EinlagerungPane einlagerungPane; 
	private EinlagerungModel einlagerungModel;
	
	public static EinlagerungPaneController getOneInstance(){
		if (einlagerungPaneController == null){
			einlagerungPaneController = new EinlagerungPaneController();
		}
		return einlagerungPaneController;
	}
	
	@Override
	public void setGewaehlteZeile(int gewaehlteZeilenNummer) {
		ModelKnotenBean modelKnotenBean = getModel().selectKnoten(gewaehlteZeilenNummer);
		selectKnoten(modelKnotenBean); //Details sollen wieder angezeigt werden.
	}
	
	private EinlagerungPane getEinlagerungPane() {
		if(einlagerungPane==null){
			einlagerungPane = new EinlagerungPane(this,getModel());
		}
		return einlagerungPane;
	}
	
	private EinlagerungModel getModel() {
		if(einlagerungModel==null){
			einlagerungModel = new EinlagerungModel();
		}
		return einlagerungModel;
	}
	
//	public void erzeugeNeueEinlagerungPosition() {
//		getModel().erzeugeNeueEinlagerungPosition();
//	}
//
//	public void loescheEinlagerungPosition(EinlagerungPosBean einlagerungPosBean) {
//		getModel().loescheEinlagerungPosition(einlagerungPosBean);
//	}
//
//	
	
	@Override
	public void showView(){
		getEinlagerungPane().setContentPane(Run.getOneInstance().getMainFrame());
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
	public void addSuchBeobachter(ISuchBeobachter einlagerungSuchPane) {
		getModel().addISuchBeobachters(einlagerungSuchPane);
	}
	

	@Override
	public void addTreeBeobachter(ITreeBeobachter iTreeBeobachter) {
		getModel().addITreeBeobachter(iTreeBeobachter);
	}

	@Override
	public void suchen(ISuchBean iSuchBean) {
		getModel().suchen((EinlagerungPosSuchBean)iSuchBean);
	}

	/**
	 * Delegieren --> EinlagerungTreePane erzeugen / holen
	 * @return
	 */
	@Override
	public TreeView getTreePane() {
		return EinlagerungTreeController.getOneInstance().getTreeView();
	}

	/**
	 * Delegieren --> EinlagerungSuchPane erzeugen / holen
	 * @return
	 */
	@Override
	public SuchView getSuchView() {
		return EinlagerungSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return EinlagerungListController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if (selectedModelKnotenBean!=null){
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.EINLAGERUNGSPOSITION){
				JPanel EinlagerungBlatt = EinlagerungPosDetailsController.getOneInstance().getDetailsView();
				getEinlagerungPane().setBlatt(EinlagerungBlatt);
			}else{
				Log.log().severe("Fehler:typ falsch:"+selectedModelKnotenBean);
//				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.EINLAGERUNGPOSITION){
//					JPanel einlagerungPosBlattView = 
//						EinlagerungPosDetailsController.getOneInstance().getDetailsView();
//					getEinlagerungPane().setBlatt(einlagerungPosBlattView);
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
		JPanel einlagerungPosBlatt = EinlagerungPosDetailsController.getOneInstance().getDetailsView();
		getEinlagerungPane().setBlatt(einlagerungPosBlatt);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((EinlagerungPosBean)bean);
//		Log.log().severe("nict implementiert " + einlagerungBean);
	}

	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((EinlagerungPosBean)bean);
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		JPanel EinlagerungBlatt = EinlagerungPosDetailsController.getOneInstance().getDetailsView();
		getEinlagerungPane().setBlatt(EinlagerungBlatt);
		getModel().abbrechenSatz((EinlagerungPosBean)bean);
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

	public void bestaetigeEinlagerungPosition() {
		getModel().bestaetigeEinlagerungPosition();
	}	
}
