package de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IBean;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.ILhMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.LhMCController;

public class BaDetailsController extends DetailsController implements ILhMCAnforderer, IConroller, IEmailMCAnforderer {
	private static BaDetailsController baDetailsController = null;
	private BaDetailsView baDetailsView = null;
	/**
	 * Konstruktor
	 */
	private BaDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static BaDetailsController getOneInstance() {
		if(baDetailsController==null){
			baDetailsController = new BaDetailsController();
			baDetailsController.addDetailsBeobachter(baDetailsController.getDetailsView());
		}
		return baDetailsController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		 getPaneController().addDetailsBeobachter(detailsView);
	}
	
	
	@Override
	public DetailsView getDetailsView() {
		if(baDetailsView==null){
			baDetailsView = new BaDetailsView(this);
		}
		return baDetailsView;
	}

	public void holeLieferanten() throws SQLException, LagerException {
		LhMCController.getOneInstance().holeAlleLieferanten(this);
	}

	@Override
	public void selectedLhBeans(ArrayList<LhBean> lhBeans) {
		if(lhBeans!=null){
			if(lhBeans.size()>0){
				((BaDetailsView)getDetailsView()).setMatchCodeLieferanten(lhBeans.get(0));
			}
		}
	}

	public LhBean getLhAnhandName(String lhName) throws LagerException, SQLException {
		return LhMCController.getOneInstance().getLhAnhandName(lhName);
	}


	public void holeEMail() throws SQLException, LagerException {
		EmailMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedEmailBeans(ArrayList<EmailBean> EmailBeans) {
		if(EmailBeans!=null){
			if(EmailBeans.size()>0){
				((BaDetailsView)getDetailsView()).setMatchCodeEmail(EmailBeans.get(0),null);
			}
		}
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		BaPaneController.getOneInstance().erzeugeNeueBaPosition();
	}

	@Override
	public void loeschePosition(Bean bean) {
		BaPaneController.getOneInstance().loescheBaPosition((BaPosBean)bean);
	}
	
	@Override
	public PaneController getPaneController(){
		return BaPaneController.getOneInstance(); 
	}



}