package de.keag.lager.panels.frame.artikel.pane;

import java.sql.SQLException;
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
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.pane.ArtikelModel.AktiverReiter;
import de.keag.lager.panels.frame.artikel.pane.details.artikel.ArtikelDetailsController;
import de.keag.lager.panels.frame.artikel.pane.details.artikelBaugruppe.ArtikelBaugruppeDetailsController;
import de.keag.lager.panels.frame.artikel.pane.details.artikelPosition.ArtikelPosDetailsController;
import de.keag.lager.panels.frame.artikel.pane.details.lieferantenBestellnummer.LieferantenBestellnummerDetailsController;
import de.keag.lager.panels.frame.artikel.pane.details.unterArtikel.UnterArtikelDetailsController;
import de.keag.lager.panels.frame.artikel.pane.liste.ArtikelListPaneController;
import de.keag.lager.panels.frame.artikel.pane.suche.ArtikelSuchController;
import de.keag.lager.panels.frame.artikel.pane.tree.ArtikelTreePaneController;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;

public class ArtikelPaneController extends PaneController {
	private static ArtikelPaneController paneController;
	private ArtikelPane pane;
	private ArtikelModel model;

	public static ArtikelPaneController getOneInstance() {
		if (paneController == null) {
			paneController = new ArtikelPaneController();
		}
		return paneController;
	}

	private ArtikelPane getArtikelPane() {
		if (pane == null) {
			pane = new ArtikelPane(this, getModel());
		}
		return pane;
	}

	@Override
	public void showView() {
		getArtikelPane().setContentPane(Run.getOneInstance().getMainFrame());
	}

	private ArtikelModel getModel() {
		if (model == null) {
			model = new ArtikelModel();
			try {
				model.addIListBeobachter(getArtikelPane().getHalleListPane());
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
	 * 
	 * @return
	 */
	@Override
	public TreeView getTreePane() {
		return ArtikelTreePaneController.getOneInstance().getHalleTreePane();
	}

	/**
	 * Delegieren --> BaSuchPane erzeugen / holen
	 * 
	 * @return
	 */

	@Override
	public SuchView getSuchView() {
		return ArtikelSuchController.getOneInstance().getSuchView();
	}

	/**
	 * Delegieren --> BaListPane erzeugen / holen
	 * 
	 * @return
	 */
	@Override
	protected ListView getListPane() {
		return ArtikelListPaneController.getOneIntstance().getListView();
	}

	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		if (selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ARTIKEL) {
			JPanel details = ArtikelDetailsController.getOneInstance()
					.getDetailsView();
			getArtikelPane().setBlatt(details);
			setzeNeuenAktivenController(ArtikelDetailsController.getOneInstance());
		} else if (selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTANDSPACKSTUECK) {
			JPanel details = ArtikelPosDetailsController.getOneInstance()
					.getDetailsView();
			getArtikelPane().setBlatt(details);
			setzeNeuenAktivenController(ArtikelPosDetailsController.getOneInstance());
		} else if (selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BAUGRUPPE_ARTIKEL) {
			JPanel details = ArtikelBaugruppeDetailsController.getOneInstance()
					.getDetailsView();
			getArtikelPane().setBlatt(details);
			setzeNeuenAktivenController(ArtikelBaugruppeDetailsController.getOneInstance());
		} else if (selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_BESTELLNUMMER) {
			JPanel details = LieferantenBestellnummerDetailsController.getOneInstance()
					.getDetailsView();
			getArtikelPane().setBlatt(details);
			setzeNeuenAktivenController(LieferantenBestellnummerDetailsController.getOneInstance());
		} else if (selectedModelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.UNTER_ARTIKEL) {
			JPanel details = UnterArtikelDetailsController.getOneInstance()
					.getDetailsView();
			getArtikelPane().setBlatt(details);
			setzeNeuenAktivenController(UnterArtikelDetailsController.getOneInstance());
		} else {
			Log.log().severe("NICHT IMPLEMENTIERT");
		}

		// if(selectedModelKnotenBean.getSammelKnotenTypENUM() ==
		// ModelKnotenTyp.HALLE){
		// JPanel details =
		// ArtikelDetailsController.getOneInstance().getDetailsView();
		// getArtikelPane().setBlatt(details);
		// }else{
		// if(selectedModelKnotenBean.getSammelKnotenTypENUM() ==
		// ModelKnotenTyp.ETAGE){
		// JPanel benutzerAbteilungDetailsView =
		// EtageDetailsController.getOneInstance().getDetailsView();
		// getArtikelPane().setBlatt(benutzerAbteilungDetailsView);
		// }else{
		// if(selectedModelKnotenBean.getSammelKnotenTypENUM() ==
		// ModelKnotenTyp.ZEILE){
		// JPanel zeileDetailsView =
		// ZeileDetailsController.getOneInstance().getDetailsView();
		// getArtikelPane().setBlatt(zeileDetailsView);
		// }else{
		// if(selectedModelKnotenBean.getSammelKnotenTypENUM() ==
		// ModelKnotenTyp.SAEULE){
		// JPanel saeuleDetailsView =
		// SaeuleDetailsController.getOneInstance().getDetailsView();
		// getArtikelPane().setBlatt(saeuleDetailsView);
		// }else{
		// if(selectedModelKnotenBean.getSammelKnotenTypENUM() ==
		// ModelKnotenTyp.EBENE){
		// JPanel ebeneDetailsView =
		// EbeneDetailsController.getOneInstance().getDetailsView();
		// getArtikelPane().setBlatt(ebeneDetailsView);
		// }else{
		// if(selectedModelKnotenBean.getSammelKnotenTypENUM() ==
		// ModelKnotenTyp.POSITION){
		// JPanel positionDetailsView =
		// PositionDetailsController.getOneInstance().getDetailsView();
		// getArtikelPane().setBlatt(positionDetailsView);
		// }else{
		// if(selectedModelKnotenBean.getSammelKnotenTypENUM() ==
		// ModelKnotenTyp.BESTANDSPACKSTUECK){
		// JPanel bestandspackstueckDetailsView =
		// BestandspackstueckDetailsController.getOneInstance().getDetailsView();
		// getArtikelPane().setBlatt(bestandspackstueckDetailsView);
		// }else{
		// Log.log().severe("SCHWERER FEHLER");
		// }
		//								
		// }
		//							
		// }
		//						
		// }
		//					
		// }
		//				
		// }
		// }
		getModel().selectKnoten(selectedModelKnotenBean);
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getModel().addIDetailsBeobachter(detailsView);
	}

	@Override
	public void erstelleNeuenSatz() {
		JPanel details = ArtikelDetailsController.getOneInstance()
				.getDetailsView();
		getArtikelPane().setBlatt(details);
		getModel().erstelleNeuenSatz();
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		getModel().abbrechenSatz(bean);
	}

	// public void erzeugeNeueEtage() {
	// getModel().erzeugeNeueEtage();
	// }
	//
	// public void loescheEtage(EtageBean etageBean) {
	// getModel().loescheEtage(etageBean);
	// }
	//	
	// public void loescheZeile(ZeileBean zeileBean) {
	// getModel().loescheZeile(zeileBean);
	// }

	@Override
	public void loescheSatz(Bean bean) {
		getModel().loescheSatz((ArtikelBean) bean);
	}

	// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
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
		return getModel().pruefeAktuelleBean(
				getModel().getSelectedListModelKnotenBean());
	}

	@Override
	public void addListBeobachter(ListView listView) {
		getModel().addIListBeobachter(listView);
		// getModel().addIArtikelListPaneBeobachter((IArtikelListPaneBeobachter)listView);
	}

	@Override
	public void speichereSatz(Bean bean) {
		getModel().speichereSatz((ArtikelBean) bean);
		// Log.log().severe("nict implementiert " + baBean);
	}

	@Override
	protected void benachrichtigeBeobachter() {
		getModel().benachrichtigeBeobachter();
	}

	@Override
	public IModel getIModel() {
		return getModel();
	}

	public void erzeugeNeuesBestandspackstueck() {
		getModel().erzeugeNeueBestandspackstueck();
	}

	public void loescheBestandspackstueck(BestandspackstueckBean bean) {
		getModel().loescheBestandspackstueck(bean);
	}

	// public void erzeugeNeueZeile() {
	// getModel().erzeugeNeueZeile();
	// }
	//
	// public void erzeugeNeueSauele() {
	// getModel().erzeugeNeueSauele();
	// }
	//
	//	
	// public void erzeugeNeueEbene() {
	// getModel().erzeugeNeueEbene();
	// }
	//	
	// public void erzeugeNeuePosition() {
	// getModel().erzeugeNeuePosition();
	// }
	//
	// public void erzeugeNeuesBestandspackstueck() {
	// getModel().erzeugeNeueBestandspackstueck();
	// }
	//
	// public void loescheBestandspackstueck(BestandspackstueckBean bean) {
	// getModel().loescheBestandspackstueck(bean);
	// }
	//
	// public void loescheEbene(EbeneBean bean) {
	// getModel().loescheEbene(bean);
	// }
	//
	// public void loescheSaeule(SaeuleBean bean) {
	// getModel().loescheSaeule(bean);
	// }
	//
	// public void loescheLagerPosition(PositionBean bean) {
	// getModel().loescheLagerPosition(bean);
	// }
	
	public void vervollstaendigePosition(PositionBean positionBean) throws LagerException, SQLException {
		getModel().vervollstaendigePosition(positionBean);
	}

	public ArrayList<BaugruppeBean> getBaugruppeBeans() {
		return getModel().getBaugruppeBeans();
	}

	public void erzeugeNeueArtikelBaugruppe() {
		getModel().erzeugeNeueArtikelBaugruppe();
		
	}

	public void loescheArtikelBaugruppe(BaugruppeArtikelBean bean) {
		getModel().loescheArtikelBaugruppe(bean);
		
	}

	public void erzeugeNeueLieferantenBestellnummer() {
		getModel().erzeugeNeueLieferantenBestellnummer();
		
	}

	public void loescheLieferantenBestellnummer(
			LieferantenBestellnummerBean bean) {
		getModel().loescheLieferantenBestellnummer(bean);
	}

	public void erzeugeNeuenUnterArtikel() {
		getModel().erzeugeNeuenUnterArtikel();
	}

	public void loescheUnterArtikel(UnterArtikelBean bean) {
		getModel().loescheUnterArtikel(bean);
		
	}

	public void setAktiverReiter(AktiverReiter aktiverReiter) {
		getModel().setAktiverReiter(aktiverReiter);
	}

	

}
