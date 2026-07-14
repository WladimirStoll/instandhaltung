package de.keag.lager.panels.frame.matchcode.benutzer;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;

public class BenutzerMCController {
	private static BenutzerMCController mCController = null;
	private BenutzerMCView mCView;
	private BenutzerMCModel mCModel;
	private IBenutzerMCAnforderer imCAnforderer;
	
	private BenutzerMCController() {
		super();
	}
	
	public static BenutzerMCController getOneInstance(){
		if(mCController==null){
			mCController = new BenutzerMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IBenutzerMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private BenutzerMCView getMCView() {
		if (mCView==null){
			mCView = new BenutzerMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private BenutzerMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new BenutzerMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(BenutzerBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedBenutzerBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IBenutzerMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IBenutzerMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(BenutzerSuchBean suchKriterien) throws SQLException, LagerException {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

//	public void sucheBenutzerByArtikelId(Integer artikelId,
//			IBenutzerMCAnforderer iMCAnforderer) {
//		setiMCAnforderer(iMCAnforderer);
//		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
//		getMCModel().sucheBenutzerByArtikelId(artikelId);
//		getMCView().setVisible(true); //Fenster anzeigen.
//	}
	
}
