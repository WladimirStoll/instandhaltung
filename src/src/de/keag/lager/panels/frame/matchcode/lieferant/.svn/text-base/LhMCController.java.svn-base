package de.keag.lager.panels.frame.matchcode.lieferant;

import java.sql.SQLException;

import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class LhMCController {
	private static LhMCController lieferantMCController = null;
	private LhMCView lieferantMCView;
	private LhMCModel lieferantMCModel;
	private ILhMCAnforderer iLhMCAnforderer;
	
	private LhMCController() {
		super();
	}
	public static LhMCController getOneInstance(){
		if(lieferantMCController==null){
			lieferantMCController = new LhMCController();
			lieferantMCController.getLieferantMCView() ;
		}
		return lieferantMCController;
	}
	private LhMCView getLieferantMCView() {
		if (lieferantMCView==null){
			lieferantMCView = new LhMCView(Run.getOneInstance().getMainFrame(),this,getLieferantMCModel());
			getLieferantMCModel().addBeobachter(lieferantMCView);
		}
		return lieferantMCView;
	}
	
	private LhMCModel getLieferantMCModel() {
		if(lieferantMCModel==null){
			lieferantMCModel = new LhMCModel();
		}
		return lieferantMCModel;
	}
	
	public void holeAlleLieferanten(ILhMCAnforderer iLhMCAnforderer) throws SQLException, LagerException{
		setiLhMCAnforderer(iLhMCAnforderer);
		getLieferantMCModel().getLhSelectedBeans().clear(); //gewählte Lieferanten löschen
		getLieferantMCModel().holeDaten(); //Datenbank neu abfragen.
		getLieferantMCView().setVisible(true); //Fenster anzeigen.
	}
	
	protected void setGewaehlterLieferant(LhBean lhBean) {
		getLieferantMCModel().getLhSelectedBeans().clear();
		getLieferantMCModel().getLhSelectedBeans().add(lhBean);
	}
	
	protected void fensterIstGeschlossen() {
		getiLhMCAnforderer().selectedLhBeans(getLieferantMCModel().getLhSelectedBeans());
		setiLhMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private ILhMCAnforderer getiLhMCAnforderer() {
		return iLhMCAnforderer;
	}
	
	public void setiLhMCAnforderer(ILhMCAnforderer iLhMCAnforderer) {
		this.iLhMCAnforderer = iLhMCAnforderer;
	}
	public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException {
		return getLieferantMCModel().getLhAnhandName(lhName);
	}
}
