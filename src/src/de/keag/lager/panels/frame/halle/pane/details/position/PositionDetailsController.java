package de.keag.lager.panels.frame.halle.pane.details.position;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.ZugriffsrechtMCController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.IZugriffsrechtMCAnforderer;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class PositionDetailsController extends DetailsController  {
	private static PositionDetailsController BenutzerPosZugriffsrechtBlattController;
	private PositionDetailsView detailsView;
	
	private PositionDetailsController() {
		super();
	}

	public static PositionDetailsController getOneInstance() {
		if(BenutzerPosZugriffsrechtBlattController==null){
			BenutzerPosZugriffsrechtBlattController = new PositionDetailsController();
			BenutzerPosZugriffsrechtBlattController.addDetailsBeobachter (BenutzerPosZugriffsrechtBlattController.getDetailsView());
		}
		return BenutzerPosZugriffsrechtBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new PositionDetailsView(this);
		}
		return detailsView;
	}

//	@Override
//	public void holeZugriffsrecht() throws SQLException, LagerException {
//		ZugriffsrechtMCController.getOneInstance().holeAlleBeans(this);
//	}

//	//Matchode - Fenster "Zugriffsrecht" ruft zurück.
//	@Override
//	public void selectedPositionBeans(ArrayList<PositionBean> zugriffsrechtBeans) {
//		if (zugriffsrechtBeans!=null && zugriffsrechtBeans.size()>0){
//			((PositionDetailsView)getDetailsView()).setPosition(zugriffsrechtBeans.get(0));
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
//		if(iBean instanceof PositionBean){
//			PositionBean benutzerPosBean = (PositionBean)iBean;
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
		if (modelKnotenTyp==ModelKnotenTyp.BESTANDSPACKSTUECK){
			HallePaneController.getOneInstance().erzeugeNeuesBestandspackstueck();
		}else{
			Log.log().severe("Falscher Typ:"+modelKnotenTyp);
		}
	}


	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof BestandspackstueckBean){
			HallePaneController.getOneInstance().loescheBestandspackstueck((BestandspackstueckBean)bean);
		}else{
			Log.log().severe("Falscher Typ:"+bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();
	}
	
}
