package de.keag.lager.panels.frame.benutzer.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.benutzer.pane.BenutzerPaneController;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;

public class BenutzerSuchController extends SuchController implements IAbteilungMCAnforderer{
	private BenutzerSuchPaneView benutzerSuchPaneView = null;
	private static BenutzerSuchController benutzerSuchPaneController = null;
	
	private BenutzerSuchController() {
		super();
	}

	public static BenutzerSuchController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new BenutzerSuchController();
			BenutzerPaneController.getOneInstance().addSuchBeobachter(benutzerSuchPaneController.getSuchView());
		}
		return benutzerSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(benutzerSuchPaneView==null){
			benutzerSuchPaneView = new BenutzerSuchPaneView(this,BenutzerPaneController.getOneInstance().getIModel());
		}
		return benutzerSuchPaneView;
	}

	
	@Override
	public PaneController getPaneController() {
		return BenutzerPaneController.getOneInstance();
	}

	@Override
	public void holeAbteilung() throws SQLException, LagerException {
		AbteilungMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedAbteilungBeans(ArrayList<AbteilungBean> abteilungBeans) {
		if (abteilungBeans!=null && abteilungBeans.size()>0){
			((BenutzerSuchPaneView)getSuchView()).setAbteilung(abteilungBeans.get(0));
		}
	}

}
