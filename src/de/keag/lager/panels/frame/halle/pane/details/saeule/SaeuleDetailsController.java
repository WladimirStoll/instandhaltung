package de.keag.lager.panels.frame.halle.pane.details.saeule;

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
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.ZugriffsrechtMCController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.IZugriffsrechtMCAnforderer;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class SaeuleDetailsController extends DetailsController  {
	private static SaeuleDetailsController BenutzerPosZugriffsrechtBlattController;
	private SaeuleDetailsView detailsView;
	
	private SaeuleDetailsController() {
		super();
	}

	public static SaeuleDetailsController getOneInstance() {
		if(BenutzerPosZugriffsrechtBlattController==null){
			BenutzerPosZugriffsrechtBlattController = new SaeuleDetailsController();
			BenutzerPosZugriffsrechtBlattController.addDetailsBeobachter (BenutzerPosZugriffsrechtBlattController.getDetailsView());
		}
		return BenutzerPosZugriffsrechtBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new SaeuleDetailsView(this);
		}
		return detailsView;
	}

//	@Override
//	public void holeZugriffsrecht() throws SQLException, LagerException {
//		ZugriffsrechtMCController.getOneInstance().holeAlleBeans(this);
//	}

//	//Matchode - Fenster "Zugriffsrecht" ruft zurück.
//	@Override
//	public void selectedSaeuleBeans(ArrayList<SaeuleBean> zugriffsrechtBeans) {
//		if (zugriffsrechtBeans!=null && zugriffsrechtBeans.size()>0){
//			((SaeuleDetailsView)getDetailsView()).setSaeule(zugriffsrechtBeans.get(0));
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
//		if(iBean instanceof SaeuleBean){
//			SaeuleBean benutzerPosBean = (SaeuleBean)iBean;
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
		if (modelKnotenTyp==ModelKnotenTyp.EBENE){
			HallePaneController.getOneInstance().erzeugeNeueEbene()	;
		}else{
			Log.log().severe("Falscher Typ:"+modelKnotenTyp);
		}
	}


	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof EbeneBean){
			HallePaneController.getOneInstance().loescheEbene((EbeneBean)bean);
		}else{
			Log.log().severe("Falscher Typ:"+bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();
	}

	public void druckeInventurListe(SaeuleBean bean, boolean mitMengen) throws LagerException, SQLException, Exception{
		 ((HallePaneController)getPaneController()).druckeInventurListe(bean, mitMengen);
	}
	
	
}
