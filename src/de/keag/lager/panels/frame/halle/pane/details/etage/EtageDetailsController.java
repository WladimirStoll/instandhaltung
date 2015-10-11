package de.keag.lager.panels.frame.halle.pane.details.etage;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class EtageDetailsController extends DetailsController  {
	private static EtageDetailsController etageDetailsController;
	private EtageDetailsView detailsView;
	
	private EtageDetailsController() {
		super();
	}

	public static EtageDetailsController getOneInstance() {
		if(etageDetailsController==null){
			etageDetailsController = new EtageDetailsController();
			etageDetailsController.addDetailsBeobachter (etageDetailsController.getDetailsView());
		}
		return etageDetailsController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new EtageDetailsView(this);
		}
		return detailsView;
	}

//	@Override
//	public void holeAbteilung() throws SQLException, LagerException {
//		AbteilungMCController.getOneInstance().holeAlleBeans(this);
//	}
//
//	//Matchode - Fenster "Abteilung" ruft zurück.
//	@Override
//	public void selectedEtageBeans(ArrayList<EtageBean> abteilungBeans) {
//		if (abteilungBeans!=null && abteilungBeans.size()>0){
//			((EtageDetailsView)getDetailsView()).setEtage(abteilungBeans.get(0));
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
//		if(iBean instanceof EtageBean){
//			EtageBean benutzerPosBean = (EtageBean)iBean;
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
		if (modelKnotenTyp==ModelKnotenTyp.ZEILE){
			HallePaneController.getOneInstance().erzeugeNeueZeile();
		}
	}


	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof ZeileBean){
			HallePaneController.getOneInstance().loescheZeile((ZeileBean)bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();
	}
	
}
