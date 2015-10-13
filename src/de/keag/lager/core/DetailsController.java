package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

abstract public class DetailsController extends Controller {


//	abstract public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp  );
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		getPaneController().erstelleNeuenSatz();
	}

//	abstract public void loeschePosition(Bean bean);
//	@Override
	public void loeschePosition(Bean bean) {
		getPaneController().loeschePosition(bean);
	}
	
	public void loescheSatz(Bean bean) {
		getPaneController().loescheSatz(bean);
	}
	
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		getPaneController().selectKnoten(selectedModelKnotenBean);//this
//		getPaneController().benachrichtigeTreeBeobachter();
	}

	abstract public DetailsView getDetailsView();

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = null;
		IBean iBean = null;
		if (getPaneController().getIModel().getAktiveTreeModelBean()!=null){
			iBean = getPaneController().getIModel().getAktiveTreeModelBean().getIBean();
		}
		errors = getDetailsView().pruefeDich();
		if (errors==null){
			errors = new ArrayList<Fehler>();
		}
		if (errors.size() == 0&&iBean!=null){
			errors = iBean.pruefeEigeneDaten();
		}
		return errors;//this
//		if(iBean instanceof BaBean){
//			BaBean baBean = (BaBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = baBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
		
	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = BaPaneController.getOneInstance().getIModel().getAktiveModelBean().getIBean();
//		if(iBean instanceof BaPosBean){
//			BaPosBean baPosBean = (BaPosBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = baPosBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
//		return errors;
//	}
	
	
	@Override
	public void setBorder(Boolean aktiv) {
		getDetailsView().setBorder(aktiv);
	}

	
	@Override
	public void setStandardFocusPosition() {
		zeigeGewahlteDetails();		
		getDetailsView().setStandardFocusPosition();
	}

//	abstract public void addDetailsBeobachter(DetailsView detailsView);
	public void addDetailsBeobachter(DetailsView detailsView) {
		 getPaneController().addDetailsBeobachter(detailsView);
	}
	
	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	public void ausgewaehlterKnotenIstGeandert() {
		getPaneController().ausgewaehlterKnotenIstGeandert();
	}
	
	public void zeigeGewahlteDetails(){
		if (getPaneController()!=null
				&& getPaneController().getIModel()!=null
				&& getPaneController().getIModel().getAktiveTreeModelBean()!=null){
			ModelKnotenBean aktiveTreeModelBean = getPaneController().getIModel().getAktiveTreeModelBean();

		}
	}

}
