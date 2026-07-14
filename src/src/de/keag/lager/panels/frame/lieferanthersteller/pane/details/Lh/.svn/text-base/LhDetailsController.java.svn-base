package de.keag.lager.panels.frame.lieferanthersteller.pane.details.Lh;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhZugriffsrechtBean;
import de.keag.lager.panels.frame.lieferanthersteller.pane.LhPaneController;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;

public class LhDetailsController extends DetailsController implements   IEmailMCAnforderer {
	private static LhDetailsController lhBlattController = null;
	private LhDetailsView lhBlatt = null;
	/**
	 * Konstruktor
	 */
	private LhDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static LhDetailsController getOneInstance() {
		if(lhBlattController==null){
			lhBlattController = new LhDetailsController();
			lhBlattController.addDetailsBeobachter(lhBlattController.getDetailsView());
		}
		return lhBlattController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView){
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public LhDetailsView getDetailsView() {
		if(lhBlatt==null){
			lhBlatt = new LhDetailsView(this);
		}
		return lhBlatt;
	}

//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = LhPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof LhBean){
//			LhBean lhBean = (LhBean)iBean;
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
		if (modelKnotenTyp==ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG){
			LhPaneController.getOneInstance().erzeugeNeueLhKatalog();
		}
//		if (modelKnotenTyp==ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT){
//			LhPaneController.getOneInstance().erzeugeNeueLhZugriffsrecht();
//		}
	}
	
	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof LhKatalogBean){
			LhPaneController.getOneInstance().loescheLhKatalog((LhKatalogBean)bean);
		}
//		if (bean instanceof LhZugriffsrechtBean){
//			LhPaneController.getOneInstance().loescheLhZugriffsrecht((LhZugriffsrechtBean)bean);
//		}
	}

	@Override
	public PaneController getPaneController() {
		return LhPaneController.getOneInstance();
	}




}
