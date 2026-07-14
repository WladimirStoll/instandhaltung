package de.keag.lager.panels.frame.matchcode.kostenstelle;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;

public class KostenstelleMCController {
	private static KostenstelleMCController mCController = null;
	private KostenstelleMCView mCView;
	private KostenstelleMCModel mCModel;
	private IKostenstelleMCAnforderer imCAnforderer;
	
	private KostenstelleMCController() {
		super();
	}
	
	public static KostenstelleMCController getOneInstance(){
		if(mCController==null){
			mCController = new KostenstelleMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IKostenstelleMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private KostenstelleMCView getMCView() {
		if (mCView==null){
			mCView = new KostenstelleMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
		}
		return mCView;
	}
	
	private KostenstelleMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new KostenstelleMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlterLieferant(KostenstelleBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedKostenstelleBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IKostenstelleMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IKostenstelleMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}
	
	public KostenstelleBean getAnhandName(String name) throws SQLException {
		return getMCModel().getAnhandName(name);
	}
}
