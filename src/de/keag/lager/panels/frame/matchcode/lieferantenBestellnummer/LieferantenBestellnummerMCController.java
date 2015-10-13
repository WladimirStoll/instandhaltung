package de.keag.lager.panels.frame.matchcode.lieferantenBestellnummer;

import java.sql.SQLException;

import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerSuchBean;

public class LieferantenBestellnummerMCController {
	private static LieferantenBestellnummerMCController mCController = null;
	private LieferantenBestellnummerMCView mCView;
	private LieferantenBestellnummerMCModel mCModel;
	private ILieferantenBestellnummerMCAnforderer imCAnforderer;
	
	private LieferantenBestellnummerMCController() {
		super();
	}
	
	public static LieferantenBestellnummerMCController getOneInstance(){
		if(mCController==null){
			mCController = new LieferantenBestellnummerMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(ILieferantenBestellnummerMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private LieferantenBestellnummerMCView getMCView() {
		if (mCView==null){
			mCView = new LieferantenBestellnummerMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private LieferantenBestellnummerMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new LieferantenBestellnummerMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(LieferantenBestellnummerBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedLieferantenBestellnummerBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private ILieferantenBestellnummerMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(ILieferantenBestellnummerMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(LieferantenBestellnummerSuchBean suchKriterien) throws SQLException {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

	public void sucheLieferantenBestellnummerByArtikelId(Integer artikelId,
			ILieferantenBestellnummerMCAnforderer iMCAnforderer) throws LagerException {
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().sucheLieferantenBestellnummerByArtikelId(artikelId);
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
}
