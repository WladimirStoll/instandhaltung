package de.keag.lager.panels.frame.matchcode.halle;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.baugruppe.pane.details.baugruppe.BaugruppeDetailsController;
//import de.keag.lager.panels.frame.entnahme.pane.details.position.EntnahmePosDetailsController;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;
import de.keag.lager.panels.frame.halle.pane.suche.HalleSuchController;
import de.keag.lager.panels.frame.matchcode.mengeneinheit.IMengeneinheitMCAnforderer;

public class HalleMCController {
	private static HalleMCController mCController = null;
	private HalleMCView mCView;
	private HalleMCModel mCModel;
	private IHalleMCAnforderer imCAnforderer;
	
	private HalleMCController() {
		super();
	}
	
	public static HalleMCController getOneInstance(){
		if(mCController==null){
			mCController = new HalleMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IHalleMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private HalleMCView getMCView() {
		if (mCView==null){
			mCView = new HalleMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private HalleMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new HalleMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(HalleBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedHalleBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IHalleMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IHalleMCAnforderer iMCAnforderer2) {
		this.imCAnforderer = (IHalleMCAnforderer) iMCAnforderer2;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(HalleSuchBean suchKriterien) throws SQLException, LagerException {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

//	public void holeAlleBeans(
//			EntnahmePosDetailsController entnahmePosDetailsController) {
//		// TODO Auto-generated method stub
//		
//	}

//	public void sucheHalleByArtikelId(Integer artikelId,
//			IHalleMCAnforderer iMCAnforderer) {
//		setiMCAnforderer(iMCAnforderer);
//		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
//		getMCModel().sucheHalleByArtikelId(artikelId);
//		getMCView().setVisible(true); //Fenster anzeigen.
//	}
	
}
