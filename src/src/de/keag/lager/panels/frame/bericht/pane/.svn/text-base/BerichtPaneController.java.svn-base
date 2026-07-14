package de.keag.lager.panels.frame.bericht.pane;

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
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtSuchBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.einzelberichte.anforderung.AnforderungBericht;
import de.keag.lager.panels.frame.bericht.pane.details.bericht.BerichtDetailsController;
import de.keag.lager.panels.frame.bericht.pane.liste.BerichtListController;
import de.keag.lager.panels.frame.bericht.pane.suche.BerichtSuchController;
import de.keag.lager.panels.frame.bericht.pane.tree.BerichtTreeController;

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
	public void drucken(Berichttyp berichtTyp, Integer berichtTypId, Berichtart berichtArt) throws Exception {
		switch (berichtTyp) {
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
		AnforderungBericht anforderungBericht =  new AnforderungBericht();
		anforderungBericht.erzeugeBerichtDbSatz(berichtTyp, berichtArt, berichtTypId); //neuen Satz anlegen.
		anforderungBericht.uebernehmeDruckDaten(); //Datenübernehmen
		anforderungBericht.verarbeiteBericht();//Daten verarbeiten (Drucken oder per E-Mail verschicken)
//		Log.log().severe("Anforderung kann nicht verschickt werden:"+berichtTypId);
	}
	
	private void druckeAnforderung(Berichttyp berichtTyp, Integer id, Berichtart berichtArt) {
		Log.log().severe("Anforderung kann nicht gedruckt werden:"+id);
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
	public void druckeBericht(Bean bean) throws LagerException, SQLException, Exception{
		if (bean instanceof BerichtBean){
			drucken(
					((BerichtBean)bean).getBerichtTyp(),
					((BerichtBean)bean).getBerichtTypId(),
					((BerichtBean)bean).getBerichtArt()
			);
		}else{
			throw new LagerException(new Fehler("Bean ist keine Berichtbean:"+bean ));
		}
	}
	
	
}
