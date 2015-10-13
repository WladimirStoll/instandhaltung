package de.keag.lager.panels.frame.artikel.pane.details.artikel;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;

import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.artikel.pane.ArtikelModel.AktiverReiter;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung.BaDetailsView;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.position.BaPosDetailsView;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;
import de.keag.lager.panels.frame.matchcode.kostenstelle.IKostenstelleMCAnforderer;
import de.keag.lager.panels.frame.matchcode.kostenstelle.KostenstelleMCController;
import de.keag.lager.panels.frame.matchcode.lieferant.ILhMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.LhMCController;
import de.keag.lager.panels.frame.matchcode.mengeneinheit.IMengeneinheitMCAnforderer;
import de.keag.lager.panels.frame.matchcode.mengeneinheit.MengeneinheitMCController;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;

public class ArtikelDetailsController extends DetailsController implements   
		IEmailMCAnforderer,
		IKostenstelleMCAnforderer,
		IMengeneinheitMCAnforderer,
		ILhMCAnforderer
		{
	private static ArtikelDetailsController benutzerBlattController = null;
	private ArtikelDetailsView benutzerBlatt = null;
	/**
	 * Konstruktor
	 */
	private ArtikelDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static ArtikelDetailsController getOneInstance() {
		if(benutzerBlattController==null){
			benutzerBlattController = new ArtikelDetailsController();
			benutzerBlattController.addDetailsBeobachter(benutzerBlattController.getDetailsView());
		}
		return benutzerBlattController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView){
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public ArtikelDetailsView getDetailsView() {
		if(benutzerBlatt==null){
			benutzerBlatt = new ArtikelDetailsView(this);
		}
		return benutzerBlatt;
	}

//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = ArtikelPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof ArtikelBean){
//			ArtikelBean halleBean = (ArtikelBean)iBean;
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
		if (modelKnotenTyp==ModelKnotenTyp.BESTANDSPACKSTUECK){
			ArtikelPaneController.getOneInstance().erzeugeNeuesBestandspackstueck();
		}else if (modelKnotenTyp==ModelKnotenTyp.BAUGRUPPE_ARTIKEL){
			ArtikelPaneController.getOneInstance().erzeugeNeueArtikelBaugruppe();
		}else if (modelKnotenTyp==ModelKnotenTyp.LIEFERANT_BESTELLNUMMER){
			ArtikelPaneController.getOneInstance().erzeugeNeueLieferantenBestellnummer();
		}else if (modelKnotenTyp==ModelKnotenTyp.UNTER_ARTIKEL){
			ArtikelPaneController.getOneInstance().erzeugeNeuenUnterArtikel();
		}else{
			Log.log().severe("Fehlerhafter Typ: " + modelKnotenTyp.toString());
		}
//		if (modelKnotenTyp==ModelKnotenTyp.ETAGE){
//			ArtikelPaneController.getOneInstance().erzeugeNeueEtage();
//		}
//		if (modelKnotenTyp==ModelKnotenTyp.ZEILE){
//			ArtikelPaneController.getOneInstance().erzeugeNeueZeile();
//		}
	}
	
	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof BestandspackstueckBean){
			ArtikelPaneController.getOneInstance().loescheBestandspackstueck((BestandspackstueckBean)bean);
		}else if (bean instanceof BaugruppeArtikelBean){
			ArtikelPaneController.getOneInstance().loescheArtikelBaugruppe((BaugruppeArtikelBean)bean);
		}else if (bean instanceof LieferantenBestellnummerBean){
			ArtikelPaneController.getOneInstance().loescheLieferantenBestellnummer((LieferantenBestellnummerBean)bean);
		}else if (bean instanceof UnterArtikelBean){
			ArtikelPaneController.getOneInstance().loescheUnterArtikel((UnterArtikelBean)bean);
		}else{
			Log.log().severe("ungültige Klasse:"+bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return ArtikelPaneController.getOneInstance();
	}

	@Override
	public void holeKostenstelle() throws SQLException, LagerException {
		KostenstelleMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedKostenstelleBeans(ArrayList<KostenstelleBean> kostenstelleBeans) {
		if (kostenstelleBeans!=null && kostenstelleBeans.size()>0){
			((ArtikelDetailsView)getDetailsView()).setKostenstelle(kostenstelleBeans.get(0));
		}
	}
	
	@Override
	public void holeMengeneinheit() throws SQLException, LagerException {
		MengeneinheitMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedMengeneinheitBeans(
			ArrayList<MengenEinheitBean> mengeneinheitBeans) {
		if (mengeneinheitBeans!=null && mengeneinheitBeans.size()>0){
			((ArtikelDetailsView)getDetailsView()).setMengeneinheit(mengeneinheitBeans.get(0));
		}
	}

	@Override
	public void holeLieferanten() throws SQLException, LagerException {
		LhMCController.getOneInstance().holeAlleLieferanten(this);
	}

	@Override
	public void selectedLhBeans(ArrayList<LhBean> lhBeans) {
		if(lhBeans!=null){
			if(lhBeans.size()>0){
				((ArtikelDetailsView)getDetailsView()).setMatchCodeLieferanten(lhBeans.get(0));
			}
		}
	}

	public ArrayList<BaugruppeBean> getBaugruppeBeans() {
		return  ((ArtikelPaneController)getPaneController()).getBaugruppeBeans();
	}

	public void setAktiverReiter(AktiverReiter aktiverReiter) {
		 ((ArtikelPaneController)getPaneController()).setAktiverReiter(aktiverReiter);
	}
	

}
