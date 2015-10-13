package de.keag.lager.panels.frame.halle.pane;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IBean;
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
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.halle.pane.details.halle.HalleDetailsController;
import de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck.BestandspackstueckDetailsController;
import de.keag.lager.panels.frame.halle.pane.details.ebene.EbeneDetailsController;
import de.keag.lager.panels.frame.halle.pane.details.etage.EtageDetailsController;
import de.keag.lager.panels.frame.halle.pane.details.position.PositionDetailsController;
import de.keag.lager.panels.frame.halle.pane.details.saeule.SaeuleDetailsController;
import de.keag.lager.panels.frame.halle.pane.details.zeile.ZeileDetailsController;
import de.keag.lager.panels.frame.halle.pane.liste.HalleListPaneController;
import de.keag.lager.panels.frame.halle.pane.suche.HalleSuchController;
import de.keag.lager.panels.frame.halle.pane.tree.HalleTreePaneController;


public class HallePaneController extends PaneController {
	private static HallePaneController paneController;
	private HallePane benutzerPane; 
	private HalleModel model;
	
	public static HallePaneController getOneInstance(){
		if (paneController == null){
			paneController = new HallePaneController();
		}
		return paneController;
	}
	
	private HallePane getHallePane() {
		if(benutzerPane==null){
			benutzerPane = new HallePane(this,getModel());
		}
		return benutzerPane;
	}

	@Override
	public void showView(){
		getHallePane().setContentPane(Run.getOneInstance().getMainFrame());
	}

	private HalleModel getModel() {
		if(model==null){
			model = new HalleModel();
			try {
				model.addIListBeobachter(getHallePane().getHalleListPane());
			} catch (BenutzerOberflacheLagerException e) {
				e.printStackTrace();
				Log.log().severe(e.getLocalizedMessage());
				Log.log().severe(e.getFehler().getMessage());
			}
		}
		return model;
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
		return HalleTreePaneController.getOneInstance().getHalleTreePane();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * @return
	 */
	
	@Override
	public SuchView getSuchView() {
		return HalleSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return HalleListPaneController.getOneIntstance().getListView();
	}


	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.HALLE){
			JPanel details = HalleDetailsController.getOneInstance().getDetailsView();
			getHallePane().setBlatt(details);
		}else{
			if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ETAGE){
				JPanel benutzerAbteilungDetailsView = 
					EtageDetailsController.getOneInstance().getDetailsView();
				getHallePane().setBlatt(benutzerAbteilungDetailsView);
			}else{
				if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ZEILE){
					JPanel zeileDetailsView = 
					ZeileDetailsController.getOneInstance().getDetailsView();
					getHallePane().setBlatt(zeileDetailsView);
				}else{
					if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.SAEULE){
						JPanel saeuleDetailsView = 
						SaeuleDetailsController.getOneInstance().getDetailsView();
						getHallePane().setBlatt(saeuleDetailsView);
					}else{
						if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.EBENE){
							JPanel ebeneDetailsView = 
							EbeneDetailsController.getOneInstance().getDetailsView();
							getHallePane().setBlatt(ebeneDetailsView);
						}else{
							if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.POSITION){
								JPanel positionDetailsView = 
								PositionDetailsController.getOneInstance().getDetailsView();
								getHallePane().setBlatt(positionDetailsView);
							}else{
								if(selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTANDSPACKSTUECK){
									JPanel bestandspackstueckDetailsView = 
									BestandspackstueckDetailsController.getOneInstance().getDetailsView();
									getHallePane().setBlatt(bestandspackstueckDetailsView);
								}else{
									Log.log().severe("SCHWERER FEHLER");
								}
								
							}
							
						}
						
					}
					
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
		JPanel details = HalleDetailsController.getOneInstance().getDetailsView();
		getHallePane().setBlatt(details);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		getModel().abbrechenSatz(bean);
	}
	

	public void erzeugeNeueEtage() {
		getModel().erzeugeNeueEtage();
	}

	public void loescheEtage(EtageBean etageBean) {
		getModel().loescheEtage(etageBean);
	}
	
	public void loescheZeile(ZeileBean zeileBean) {
		getModel().loescheZeile(zeileBean);
	}
	
	
	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((HalleBean)bean);
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
//		getModel().addIHalleListPaneBeobachter((IHalleListPaneBeobachter)listView);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((HalleBean)bean);
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

	public void erzeugeNeueZeile()  {
		getModel().erzeugeNeueZeile();
	}

	public void erzeugeNeueSauele() {
		getModel().erzeugeNeueSauele();
	}

	
	public void erzeugeNeueEbene() {
		getModel().erzeugeNeueEbene();
	}
	
	public void erzeugeNeuePosition() {
		getModel().erzeugeNeuePosition();
	}

	public void erzeugeNeuesBestandspackstueck(){
		getModel().erzeugeNeueBestandspackstueck();
	}

	public void loescheBestandspackstueck(BestandspackstueckBean bean) {
		getModel().loescheBestandspackstueck(bean);
	}

	public void loescheEbene(EbeneBean bean) {
		getModel().loescheEbene(bean);
	}

	public void loescheSaeule(SaeuleBean bean) {
		getModel().loescheSaeule(bean);
	}

	public void loescheLagerPosition(PositionBean bean) {
		getModel().loescheLagerPosition(bean);
	}

	/**
	 * Druckt(versendet per E-Mail die Inventur-Liste
	 * @param mitMengen 
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws LagerException 
	 */
	public void druckeInventurListe(Bean bean, boolean mitMengen) throws LagerException, SQLException, Exception {
		getModel().druckeBericht(bean,mitMengen);
//		if (bean==null){
//			IBean ibean =  getModel().getSelectedListModelKnotenBean().getIBean();
//			if (ibean instanceof HalleBean){
//			 	getModel().druckeBericht((HalleBean)ibean);	
//			}else{
//				throw new LagerException("Falsche Klasse:"+bean);
//			}
//		}else if (bean instanceof ZeileBean){
//			//Inventurliste für die Zeile drucken
//		 	getModel().druckeBericht((HalleBean)ibean);	
//		}else{
//			throw new LagerException("Falsche Klasse:"+bean);
//		}
	}



}
