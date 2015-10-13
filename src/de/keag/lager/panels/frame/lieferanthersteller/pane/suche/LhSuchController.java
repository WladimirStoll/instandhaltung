package de.keag.lager.panels.frame.lieferanthersteller.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.lieferanthersteller.pane.LhPaneController;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;

public class LhSuchController extends SuchController {
	private LhSuchPaneView lhSuchPaneView = null;
	private static LhSuchController lhSuchPaneController = null;
	
	private LhSuchController() {
		super();
	}

	public static LhSuchController getOneInstance() {
		if(lhSuchPaneController==null){
			lhSuchPaneController = new LhSuchController();
			LhPaneController.getOneInstance().addSuchBeobachter(lhSuchPaneController.getSuchView());
		}
		return lhSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(lhSuchPaneView==null){
			lhSuchPaneView = new LhSuchPaneView(this,LhPaneController.getOneInstance().getIModel());
		}
		return lhSuchPaneView;
	}

	
	@Override
	public PaneController getPaneController() {
		return LhPaneController.getOneInstance();
	}


}
