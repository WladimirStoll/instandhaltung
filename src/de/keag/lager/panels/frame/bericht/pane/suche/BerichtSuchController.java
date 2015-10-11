package de.keag.lager.panels.frame.bericht.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.pane.suche.BenutzerSuchPaneView;
import de.keag.lager.panels.frame.bericht.pane.BerichtPaneController;
import de.keag.lager.panels.frame.matchcode.benutzer.BenutzerMCController;
import de.keag.lager.panels.frame.matchcode.benutzer.IBenutzerMCAnforderer;

public class BerichtSuchController extends SuchController implements IBenutzerMCAnforderer{
	private BerichtSuchView suchView = null;
	private static SuchController suchPaneController = null;
	
	private BerichtSuchController() {
		super();
	}

	public static BerichtSuchController getOneInstance() {
		if(suchPaneController==null){
			suchPaneController = new BerichtSuchController();
			BerichtPaneController.getOneInstance().addSuchBeobachter((BerichtSuchView)suchPaneController.getSuchView());
		}
		return (BerichtSuchController)suchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(suchView==null){
			suchView = new BerichtSuchView(this,BerichtPaneController.getOneInstance().getIModel());
		}
		return suchView;
	}


	@Override
	public PaneController getPaneController(){
		return BerichtPaneController.getOneInstance();
	}

	@Override
	public void holeBenutzer() throws SQLException, LagerException {
		BenutzerMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedBenutzerBeans(ArrayList<BenutzerBean> benutzerBeans) {
		if (benutzerBeans!=null && benutzerBeans.size()>0){
			((BerichtSuchView)getSuchView()).setBenutzer(benutzerBeans.get(0));
		}
	}
	
}
