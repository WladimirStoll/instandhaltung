package de.keag.lager.panels.frame.benutzer.pane.details.benutzer;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;

import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;

public class BenutzerDetailsController extends DetailsController implements   IEmailMCAnforderer {
	private static BenutzerDetailsController benutzerBlattController = null;
	private BenutzerDetailsView benutzerBlatt = null;
	/**
	 * Konstruktor
	 */
	private BenutzerDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static BenutzerDetailsController getOneInstance() {
		if(benutzerBlattController==null){
			benutzerBlattController = new BenutzerDetailsController();
			benutzerBlattController.addDetailsBeobachter(benutzerBlattController.getDetailsView());
		}
		return benutzerBlattController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView){
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public BenutzerDetailsView getDetailsView() {
		if(benutzerBlatt==null){
			benutzerBlatt = new BenutzerDetailsView(this);
		}
		return benutzerBlatt;
	}

//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = BenutzerPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof BenutzerBean){
//			BenutzerBean benutzerBean = (BenutzerBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = benutzerBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
//		return errors;
//	}


	@Override
	public void holeEMail() throws SQLException, LagerException {
		EmailMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedEmailBeans(ArrayList<EmailBean> EmailBeans) {
		if(EmailBeans!=null){
			if(EmailBeans.size()>0){
				getDetailsView().setMatchCodeEmail(EmailBeans.get(0),null);
			}
		}
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		if (modelKnotenTyp==ModelKnotenTyp.BENUTZER_ABTEILUNG){
			BenutzerPaneController.getOneInstance().erzeugeNeueBenutzerAbteilung();
		}
		if (modelKnotenTyp==ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT){
			BenutzerPaneController.getOneInstance().erzeugeNeueBenutzerZugriffsrecht();
		}
	}
	
	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof BenutzerAbteilungBean){
			BenutzerPaneController.getOneInstance().loescheBenutzerAbteilung((BenutzerAbteilungBean)bean);
		}
		if (bean instanceof BenutzerZugriffsrechtBean){
			BenutzerPaneController.getOneInstance().loescheBenutzerZugriffsrecht((BenutzerZugriffsrechtBean)bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return BenutzerPaneController.getOneInstance();
	}


}
