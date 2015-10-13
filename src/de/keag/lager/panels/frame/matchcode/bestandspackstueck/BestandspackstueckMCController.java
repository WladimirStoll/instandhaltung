package de.keag.lager.panels.frame.matchcode.bestandspackstueck;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class BestandspackstueckMCController {
	private static BestandspackstueckMCController mCController = null;
	private BestandspackstueckMCView mCView;
	private BestandspackstueckMCModel mCModel;
	private IBestandspackstueckMCAnforderer imCAnforderer;
	
	private BestandspackstueckMCController() {
		super();
	}
	
	public static BestandspackstueckMCController getOneInstance(){
		if(mCController==null){
			mCController = new BestandspackstueckMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IBestandspackstueckMCAnforderer iMCAnforderer, PositionBean positionBean) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(positionBean); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private BestandspackstueckMCView getMCView() {
		if (mCView==null){
			mCView = new BestandspackstueckMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
		}
		return mCView;
	}
	
	private BestandspackstueckMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new BestandspackstueckMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(BestandspackstueckBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedBestandspackstueckBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IBestandspackstueckMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IBestandspackstueckMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}
	
	public BestandspackstueckBean getAnhandName(String name) throws SQLException {
		return getMCModel().getAnhandName(name);
	}
}
