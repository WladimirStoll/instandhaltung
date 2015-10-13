package de.keag.lager.panels.frame.wartung.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.matchcode.baugruppe.BaugruppeMCController;
import de.keag.lager.panels.frame.matchcode.baugruppe.IBaugruppeMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungsart.IWartungsArtMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungsart.WartungsArtMCController;
import de.keag.lager.panels.frame.matchcode.wartungstyp.IWartungsTypMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungstyp.WartungsTypMCController;

public class WartungSuchController extends SuchController 
	implements IBaugruppeMCAnforderer,
				IWartungsArtMCAnforderer,
				IWartungsTypMCAnforderer{
	
	private WartungSuchPaneView benutzerSuchPaneView = null;
	private static WartungSuchController benutzerSuchPaneController = null;
	
	private WartungSuchController() {
		super();
	}

	public static WartungSuchController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new WartungSuchController();
			WartungPaneController.getOneInstance().addSuchBeobachter(benutzerSuchPaneController.getSuchView());
		}
		return benutzerSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(benutzerSuchPaneView==null){
			benutzerSuchPaneView = new WartungSuchPaneView(this,WartungPaneController.getOneInstance().getIModel());
		}
		return benutzerSuchPaneView;
	}

	
	@Override
	public PaneController getPaneController() {
		return WartungPaneController.getOneInstance();
	}

	@Override
	public void holeBaugruppe() throws SQLException, LagerException {
		BaugruppeMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedBaugruppeBeans(ArrayList<BaugruppeBean> beans) {
		if (beans!=null && beans.size()>0){
			((WartungSuchPaneView)getSuchView()).setBaugruppe(beans.get(0));
		}
	}

	@Override
	public void holeWartungsArt() throws SQLException, LagerException {
		WartungsArtMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedWartungsArtBeans(ArrayList<WartungsArtBean> beans) {
		if (beans != null && beans.size() > 0) {
			((WartungSuchPaneView) getSuchView()).setWartungsArt(beans.get(0));
		}
	}

	@Override
	public void holeWartungsTyp() throws SQLException, LagerException {
		WartungsTypMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedWartungsTypBeans(
			ArrayList<WartungsTypBean> beans) {
		if (beans!=null && beans.size()>0){
			((WartungSuchPaneView)getSuchView()).setWartungsTyp(beans.get(0));
		}
	}

//	@Override
//	public void holeAbteilung() throws SQLException, LagerException {
//		AbteilungMCController.getOneInstance().holeAlleBeans(this);
//	}
//
//	@Override
//	public void selectedBenutzerBeans(ArrayList<BenutzerBean> abteilungBeans) {
//		if (abteilungBeans!=null && abteilungBeans.size()>0){
//			((WartungSuchPaneView)getSuchView()).setAbteilung(abteilungBeans.get(0));
//		}
//	}

}
