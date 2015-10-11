package de.keag.lager.panels.frame.matchcode.zugriffsrecht;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtSuchBean;

public class ZugriffsrechtMCController {
	private static ZugriffsrechtMCController mCController = null;
	private ZugriffsrechtMCView mCView;
	private ZugriffsrechtMCModel mCModel;
	private IZugriffsrechtMCAnforderer imCAnforderer;
	
	private ZugriffsrechtMCController() {
		super();
	}
	
	public static ZugriffsrechtMCController getOneInstance(){
		if(mCController==null){
			mCController = new ZugriffsrechtMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IZugriffsrechtMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private ZugriffsrechtMCView getMCView() {
		if (mCView==null){
			mCView = new ZugriffsrechtMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private ZugriffsrechtMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new ZugriffsrechtMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(ZugriffsrechtBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedZugriffsrechtBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IZugriffsrechtMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IZugriffsrechtMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(ZugriffsrechtSuchBean suchKriterien) throws SQLException, LagerException {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

//	public void sucheZugriffsrechtByArtikelId(Integer artikelId,
//			IZugriffsrechtMCAnforderer iMCAnforderer) {
//		setiMCAnforderer(iMCAnforderer);
//		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
//		getMCModel().sucheZugriffsrechtByArtikelId(artikelId);
//		getMCView().setVisible(true); //Fenster anzeigen.
//	}
	
}
