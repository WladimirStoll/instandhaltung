package de.keag.lager.panels.frame.artikel.pane.details.artikelBaugruppe;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.pane.suche.BaugruppeSuchPaneView;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;
import de.keag.lager.panels.frame.matchcode.baugruppe.BaugruppeMCController;
import de.keag.lager.panels.frame.matchcode.baugruppe.IBaugruppeMCAnforderer;

public class ArtikelBaugruppeDetailsController extends DetailsController
											   implements IArtikelMCAnforderer ,
											   IBaugruppeMCAnforderer
{
	private static ArtikelBaugruppeDetailsController BaugruppePosArtikelBlattController;
	private ArtikelBaugruppeDetailsView detailsView;
	
	private ArtikelBaugruppeDetailsController() {
		super();
	}

	public static ArtikelBaugruppeDetailsController getOneInstance() {
		if(BaugruppePosArtikelBlattController==null){
			BaugruppePosArtikelBlattController = new ArtikelBaugruppeDetailsController();
			BaugruppePosArtikelBlattController.addDetailsBeobachter (BaugruppePosArtikelBlattController.getDetailsView());
		}
		return BaugruppePosArtikelBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new ArtikelBaugruppeDetailsView(this);
		}
		return detailsView;
	}

//	@Override
//	public void holeArtikel() throws SQLException, LagerException {
//		ArtikelMCController.getOneInstance().holeAlleBeans(this);
//	}

//	//Matchode - Fenster "Artikel" ruft zurück.
//	@Override
//	public void selectedArtikelBeans(ArrayList<ArtikelBean> abteilungBeans) {
//		if (abteilungBeans!=null && abteilungBeans.size()>0){
//			((ArtikelBaugruppeDetailsView)getDetailsView()).setArtikel(abteilungBeans.get(0));
//		}
//	}
	

//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	protected void ausgewaehlterKnotenIstGeandert() {
//		BaugruppePaneController.getOneInstance().ausgewaehlterKnotenIstGeandert();
//	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = BaugruppePaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof ArtikelBaugruppeBean){
//			ArtikelBaugruppeBean benutzerPosBean = (ArtikelBaugruppeBean)iBean;
//			errors = getBaugruppePosArtikelDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = benutzerPosBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
//		return errors;
//	}


	
	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		if (modelKnotenTyp==ModelKnotenTyp.BAUGRUPPE_ARTIKEL){
			ArtikelPaneController.getOneInstance().erzeugeNeueArtikelBaugruppe();
		}else{
			Log.log().severe("Fehlerhafter Typ: " + modelKnotenTyp.toString());
		}
	}


	@Override
	public void loeschePosition(Bean bean) {
		Log.log().severe("nicht implementiert");
	}

	@Override
	public PaneController getPaneController() {
		return ArtikelPaneController.getOneInstance();
	}

	@Override
	public void holeArtikel() throws SQLException, LagerException {
		ArtikelMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
		if (artikelBeans!=null && artikelBeans.size()>0){
			((ArtikelBaugruppeDetailsView)getDetailsView()).setArtikel(artikelBeans.get(0));
		}
	}

	@Override
	public void holeBaugruppe() throws SQLException, LagerException {
		BaugruppeMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedBaugruppeBeans(ArrayList<BaugruppeBean> baugruppeBeans) {
		if (baugruppeBeans!=null && baugruppeBeans.size()>0){
			((ArtikelBaugruppeDetailsView)getDetailsView()).setBaugruppe(baugruppeBeans.get(0));
			
		}
	}

//	@Override
//	public void zeigeGewahlteDetails() {
//		super.zeigeGewahlteDetails();
//	}
	
}
