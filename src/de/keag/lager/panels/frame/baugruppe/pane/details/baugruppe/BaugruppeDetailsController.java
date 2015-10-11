package de.keag.lager.panels.frame.baugruppe.pane.details.baugruppe;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;

import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.pane.BaugruppePaneController;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.position.BaPosDetailsView;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;
import de.keag.lager.panels.frame.matchcode.halle.HalleMCController;
import de.keag.lager.panels.frame.matchcode.halle.IHalleMCAnforderer;

public class BaugruppeDetailsController extends DetailsController implements   IEmailMCAnforderer,IHalleMCAnforderer {
	private static BaugruppeDetailsController baugruppeBlattController = null;
	private BaugruppeDetailsView baugruppeBlatt = null;
	/**
	 * Konstruktor
	 */
	private BaugruppeDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static BaugruppeDetailsController getOneInstance() {
		if(baugruppeBlattController==null){
			baugruppeBlattController = new BaugruppeDetailsController();
			baugruppeBlattController.addDetailsBeobachter(baugruppeBlattController.getDetailsView());
		}
		return baugruppeBlattController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView){
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public BaugruppeDetailsView getDetailsView() {
		if(baugruppeBlatt==null){
			baugruppeBlatt = new BaugruppeDetailsView(this);
		}
		return baugruppeBlatt;
	}

//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = BaugruppePaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof BaugruppeBean){
//			BaugruppeBean baugruppeBean = (BaugruppeBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = baugruppeBean.pruefeEigeneDaten();
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
//				getDetailsView().setMatchCodeEmail(EmailBeans.get(0),null);
			}
		}
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp)   {
		if (modelKnotenTyp==ModelKnotenTyp.BAUGRUPPE){
			BaugruppePaneController.getOneInstance().erzeugeNeueBaugruppeBaugruppe();
		}
		if (modelKnotenTyp==ModelKnotenTyp.BAUGRUPPE_ARTIKEL){
			BaugruppePaneController.getOneInstance().erzeugeNeueBaugruppeArtikel();
		}
	}
	
	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof BaugruppeArtikelBean){
			BaugruppePaneController.getOneInstance().loescheBaugruppeArtikel((BaugruppeArtikelBean)bean);
		}
		if (bean instanceof BaugruppeBean){
			BaugruppePaneController.getOneInstance().loescheBaugruppeBaugruppe((BaugruppeBean)bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return BaugruppePaneController.getOneInstance();
	}

//	public void holeHalle() {
//		// TODO Auto-generated method stub
//		
//	}
	public void holeHalle() throws SQLException, LagerException {
		HalleMCController.getOneInstance().holeAlleBeans(this);
	}

	public void selectedHalleBeans(ArrayList<HalleBean> halleBeans) {
		if (halleBeans!=null && halleBeans.size()>0){
			((BaugruppeDetailsView)getDetailsView()).setHalle(halleBeans.get(0));
	//		getDetailsView().setMatchCodeHalle(halleBeans.get(0),null);
		}
	}

//	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
//		if (halleBeans!=null && halleBeans.size()>0){
//			((BaugruppeDetailsView)getDetailsView()).setHalle(halleBeans.get(0));
//		}
//	}


}
