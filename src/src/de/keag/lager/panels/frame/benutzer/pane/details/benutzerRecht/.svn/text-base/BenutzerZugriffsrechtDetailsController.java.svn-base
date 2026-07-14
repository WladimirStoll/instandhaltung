package de.keag.lager.panels.frame.benutzer.pane.details.benutzerRecht;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.ZugriffsrechtMCController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.IZugriffsrechtMCAnforderer;

public class BenutzerZugriffsrechtDetailsController extends DetailsController implements  
										IZugriffsrechtMCAnforderer {
	private static BenutzerZugriffsrechtDetailsController BenutzerPosZugriffsrechtBlattController;
	private BenutzerZugriffsrechtDetailsView detailsView;
	
	private BenutzerZugriffsrechtDetailsController() {
		super();
	}

	public static BenutzerZugriffsrechtDetailsController getOneInstance() {
		if(BenutzerPosZugriffsrechtBlattController==null){
			BenutzerPosZugriffsrechtBlattController = new BenutzerZugriffsrechtDetailsController();
			BenutzerPosZugriffsrechtBlattController.addDetailsBeobachter (BenutzerPosZugriffsrechtBlattController.getDetailsView());
		}
		return BenutzerPosZugriffsrechtBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new BenutzerZugriffsrechtDetailsView(this);
		}
		return detailsView;
	}

	@Override
	public void holeZugriffsrecht() throws SQLException, LagerException {
		ZugriffsrechtMCController.getOneInstance().holeAlleBeans(this);
	}

	//Matchode - Fenster "Zugriffsrecht" ruft zurück.
	@Override
	public void selectedZugriffsrechtBeans(ArrayList<ZugriffsrechtBean> zugriffsrechtBeans) {
		if (zugriffsrechtBeans!=null && zugriffsrechtBeans.size()>0){
			((BenutzerZugriffsrechtDetailsView)getDetailsView()).setZugriffsrecht(zugriffsrechtBeans.get(0));
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
//		if(iBean instanceof BenutzerZugriffsrechtBean){
//			BenutzerZugriffsrechtBean benutzerPosBean = (BenutzerZugriffsrechtBean)iBean;
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
		return BenutzerPaneController.getOneInstance();
	}
	
}
