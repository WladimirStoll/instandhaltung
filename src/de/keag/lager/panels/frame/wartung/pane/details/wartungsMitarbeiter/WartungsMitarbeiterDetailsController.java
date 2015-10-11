package de.keag.lager.panels.frame.wartung.pane.details.wartungsMitarbeiter;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;
import de.keag.lager.panels.frame.matchcode.benutzer.BenutzerMCController;
import de.keag.lager.panels.frame.matchcode.benutzer.IBenutzerMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.ILhMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.LhMCController;

public class WartungsMitarbeiterDetailsController extends DetailsController implements  
										IBenutzerMCAnforderer,
										ILhMCAnforderer
										{
	private static WartungsMitarbeiterDetailsController BenutzerPosAbteilungBlattController;
	private WartungsMitarbeiterDetailsView detailsView;
	
	private WartungsMitarbeiterDetailsController() {
		super();
	}

	public static WartungsMitarbeiterDetailsController getOneInstance() {
		if(BenutzerPosAbteilungBlattController==null){
			BenutzerPosAbteilungBlattController = new WartungsMitarbeiterDetailsController();
			BenutzerPosAbteilungBlattController.addDetailsBeobachter (BenutzerPosAbteilungBlattController.getDetailsView());
		}
		return BenutzerPosAbteilungBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new WartungsMitarbeiterDetailsView(this);
		}
		return detailsView;
	}


//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	protected void ausgewaehlterKnotenIstGeandert() {
//		WartungPaneController.getOneInstance().ausgewaehlterKnotenIstGeandert();
//	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = WartungPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof WartungsMitarbeiterBean){
//			WartungsMitarbeiterBean benutzerPosBean = (WartungsMitarbeiterBean)iBean;
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
		return WartungPaneController.getOneInstance();
	}

	@Override
	public void holeBenutzer() throws SQLException, LagerException {
		BenutzerMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedBenutzerBeans(ArrayList<BenutzerBean> benutzerBeans) {
		if (benutzerBeans!=null && benutzerBeans.size()>0){
			((WartungsMitarbeiterDetailsView)getDetailsView()).setBenutzer(benutzerBeans.get(0));
		}
	}

	@Override
	public void holeLieferanten() throws SQLException, LagerException {
		LhMCController.getOneInstance().holeAlleLieferanten(this);
	}

	@Override
	public void selectedLhBeans(ArrayList<LhBean> lhBeans) {
		if (lhBeans!=null && lhBeans.size()>0){
			((WartungsMitarbeiterDetailsView)getDetailsView()).setHerstellerLieferant(lhBeans.get(0));
		}
	}
	
	
}