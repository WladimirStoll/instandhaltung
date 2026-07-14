package de.keag.lager.panels.frame.matchcode.katalog;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.katalog.KatalogSuchBean;

public class KatalogMCController {
	private static KatalogMCController mCController = null;
	private KatalogMCView mCView;
	private KatalogMCModel mCModel;
	private IKatalogMCAnforderer imCAnforderer;
	
	private KatalogMCController() {
		super();
	}
	
	public static KatalogMCController getOneInstance(){
		if(mCController==null){
			mCController = new KatalogMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IKatalogMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private KatalogMCView getMCView() {
		if (mCView==null){
			mCView = new KatalogMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private KatalogMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new KatalogMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(KatalogBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedKatalogBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IKatalogMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IKatalogMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(KatalogSuchBean suchKriterien) throws SQLException, LagerException {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

//	public void sucheKatalogByArtikelId(Integer artikelId,
//			IKatalogMCAnforderer iMCAnforderer) {
//		setiMCAnforderer(iMCAnforderer);
//		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
//		getMCModel().sucheKatalogByArtikelId(artikelId);
//		getMCView().setVisible(true); //Fenster anzeigen.
//	}
	
}
