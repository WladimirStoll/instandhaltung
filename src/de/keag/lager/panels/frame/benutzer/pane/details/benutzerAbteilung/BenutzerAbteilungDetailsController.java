package de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung;

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
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerRecht.BenutzerZugriffsrechtDetailsView;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;

public class BenutzerAbteilungDetailsController extends DetailsController implements  
										IAbteilungMCAnforderer {
	private static BenutzerAbteilungDetailsController BenutzerPosAbteilungBlattController;
	private BenutzerAbteilungDetailsView detailsView;
	
	private BenutzerAbteilungDetailsController() {
		super();
	}

	public static BenutzerAbteilungDetailsController getOneInstance() {
		if(BenutzerPosAbteilungBlattController==null){
			BenutzerPosAbteilungBlattController = new BenutzerAbteilungDetailsController();
			BenutzerPosAbteilungBlattController.addDetailsBeobachter (BenutzerPosAbteilungBlattController.getDetailsView());
		}
		return BenutzerPosAbteilungBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new BenutzerAbteilungDetailsView(this);
		}
		return detailsView;
	}

	@Override
	public void holeAbteilung() throws SQLException, LagerException {
		AbteilungMCController.getOneInstance().holeAlleBeans(this);
	}

	//Matchode - Fenster "Abteilung" ruft zurück.
	@Override
	public void selectedAbteilungBeans(ArrayList<AbteilungBean> abteilungBeans) {
		if (abteilungBeans!=null && abteilungBeans.size()>0){
			((BenutzerAbteilungDetailsView)getDetailsView()).setAbteilung(abteilungBeans.get(0));
		}
	}
	

//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	protected void ausgewaehlterKnotenIstGeandert() {
//		BenutzerPaneController.getOneInstance().ausgewaehlterKnotenIstGeandert();
//	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = BenutzerPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof BenutzerAbteilungBean){
//			BenutzerAbteilungBean benutzerPosBean = (BenutzerAbteilungBean)iBean;
//			errors = getBenutzerPosAbteilungDetailsView().pruefeDich();
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
		return BenutzerPaneController.getOneInstance();
	}
	
}
