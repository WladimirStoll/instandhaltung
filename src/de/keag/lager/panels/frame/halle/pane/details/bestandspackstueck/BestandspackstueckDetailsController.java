package de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung.BenutzerAbteilungDetailsView;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.position.BaPosDetailsView;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;
import de.keag.lager.panels.frame.matchcode.mengeneinheit.IMengeneinheitMCAnforderer;
import de.keag.lager.panels.frame.matchcode.mengeneinheit.MengeneinheitMCController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.ZugriffsrechtMCController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.IZugriffsrechtMCAnforderer;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

public class BestandspackstueckDetailsController 
		extends DetailsController 
		implements IArtikelMCAnforderer,
		IAbteilungMCAnforderer
		
//		           IMengeneinheitMCAnforderer
  {
	private static BestandspackstueckDetailsController controller;
	private BestandspackstueckDetailsView detailsView;
	
	private BestandspackstueckDetailsController() {
		super();
	}

	public static BestandspackstueckDetailsController getOneInstance() {
		if(controller==null){
			controller = new BestandspackstueckDetailsController();
			controller.addDetailsBeobachter (controller.getDetailsView());
		}
		return controller;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new BestandspackstueckDetailsView(this);
		}
		return detailsView;
	}

//	@Override
//	public void holeZugriffsrecht() throws SQLException, LagerException {
//		ZugriffsrechtMCController.getOneInstance().holeAlleBeans(this);
//	}

//	//Matchode - Fenster "Zugriffsrecht" ruft zurück.
//	@Override
//	public void selectedBestandspackstueckBeans(ArrayList<BestandspackstueckBean> zugriffsrechtBeans) {
//		if (zugriffsrechtBeans!=null && zugriffsrechtBeans.size()>0){
//			((BestandspackstueckDetailsView)getDetailsView()).setBestandspackstueck(zugriffsrechtBeans.get(0));
//		}
//	}
//	

//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	protected void ausgewaehlterKnotenIstGeandert() {
//		HallePaneController.getOneInstance().ausgewaehlterKnotenIstGeandert();
//	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = HallePaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof BestandspackstueckBean){
//			BestandspackstueckBean benutzerPosBean = (BestandspackstueckBean)iBean;
//			errors = getBenutzerPosZugriffsrechtDetailsView().pruefeDich();
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
		Log.log().severe("nicht implementiert");
	}


	@Override
	public void loeschePosition(Bean bean) {
		Log.log().severe("nicht implementiert");
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();
	}

	@Override
	public void holeArtikel() throws SQLException, LagerException {
		ArtikelMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
		if (artikelBeans!=null && artikelBeans.size()>0){
			((BestandspackstueckDetailsView)getDetailsView()).setArtikel(artikelBeans.get(0));
		}
	}

//	@Override
//	public void selectedMengeneinheitBeans(
//			ArrayList<MengenEinheitBean> mengeneinheitBeans) {
//		if (mengeneinheitBeans!=null && mengeneinheitBeans.size()>0){
//			((BestandspackstueckDetailsView)getDetailsView()).setMengeneinheit(mengeneinheitBeans.get(0));
//		}
//	}

//	@Override
//	public void holeMengeneinheit() throws SQLException, LagerException {
//		MengeneinheitMCController.getOneInstance().holeAlleBeans(this);
//	}
	
	@Override
	public void holeAbteilung() throws SQLException, LagerException {
		AbteilungMCController.getOneInstance().holeAlleBeans(this);
	}

	//Matchode - Fenster "Abteilung" ruft zurück.
	@Override
	public void selectedAbteilungBeans(ArrayList<AbteilungBean> abteilungBeans) {
		if (abteilungBeans!=null && abteilungBeans.size()>0){
			((BestandspackstueckDetailsView)getDetailsView()).setAbteilung(abteilungBeans.get(0));
		}
	}
	
	
}
