package de.keag.lager.panels.frame.halle.pane.details.halle;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;

import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;

public class HalleDetailsController extends DetailsController implements   IEmailMCAnforderer {
	private static HalleDetailsController benutzerBlattController = null;
	private HalleDetailsView benutzerBlatt = null;
	/**
	 * Konstruktor
	 */
	private HalleDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static HalleDetailsController getOneInstance() {
		if(benutzerBlattController==null){
			benutzerBlattController = new HalleDetailsController();
			benutzerBlattController.addDetailsBeobachter(benutzerBlattController.getDetailsView());
		}
		return benutzerBlattController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView){
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public HalleDetailsView getDetailsView() {
		if(benutzerBlatt==null){
			benutzerBlatt = new HalleDetailsView(this);
		}
		return benutzerBlatt;
	}

//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = HallePaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof HalleBean){
//			HalleBean halleBean = (HalleBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = halleBean.pruefeEigeneDaten();
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
		if (modelKnotenTyp==ModelKnotenTyp.ETAGE){
			HallePaneController.getOneInstance().erzeugeNeueEtage();
		}
		if (modelKnotenTyp==ModelKnotenTyp.ZEILE){
			HallePaneController.getOneInstance().erzeugeNeueZeile();
		}
	}
	
	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof EtageBean){
			HallePaneController.getOneInstance().loescheEtage((EtageBean)bean);
		}
		if (bean instanceof ZeileBean){
			HallePaneController.getOneInstance().loescheZeile((ZeileBean)bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();
	}

	public void druckeInventurListe() throws LagerException, SQLException, Exception{
		 ((HallePaneController)getPaneController()).druckeInventurListe(null,true);
	}

}
