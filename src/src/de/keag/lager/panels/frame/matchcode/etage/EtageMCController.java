package de.keag.lager.panels.frame.matchcode.etage;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageSuchBean;

public class EtageMCController {
	private static EtageMCController mCController = null;
	private EtageMCView mCView;
	private EtageMCModel mCModel;
	private IEtageMCAnforderer imCAnforderer;
	
	private EtageMCController() {
		super();
	}
	
	public static EtageMCController getOneInstance(){
		if(mCController==null){
			mCController = new EtageMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IEtageMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private EtageMCView getMCView() {
		if (mCView==null){
			mCView = new EtageMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private EtageMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new EtageMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(EtageBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedEtageBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IEtageMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IEtageMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(EtageSuchBean suchKriterien) throws SQLException, LagerException {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

//	public void sucheEtageByArtikelId(Integer artikelId,
//			IEtageMCAnforderer iMCAnforderer) {
//		setiMCAnforderer(iMCAnforderer);
//		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
//		getMCModel().sucheEtageByArtikelId(artikelId);
//		getMCView().setVisible(true); //Fenster anzeigen.
//	}
	
}
