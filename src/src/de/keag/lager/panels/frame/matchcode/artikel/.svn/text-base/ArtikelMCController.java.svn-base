package de.keag.lager.panels.frame.matchcode.artikel;

import java.sql.SQLException;

import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.ArtikelSuchBean;

public class ArtikelMCController {
	private static ArtikelMCController mCController = null;
	private ArtikelMCView mCView;
	private ArtikelMCModel mCModel;
	private IArtikelMCAnforderer imCAnforderer;
	
	private ArtikelMCController() {
		super();
	}
	
	public static ArtikelMCController getOneInstance(){
		if(mCController==null){
			mCController = new ArtikelMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IArtikelMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private ArtikelMCView getMCView() {
		if (mCView==null){
			mCView = new ArtikelMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private ArtikelMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new ArtikelMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(ArtikelBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedArtikelBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IArtikelMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IArtikelMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(ArtikelSuchBean suchKriterien) {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}
	
}
