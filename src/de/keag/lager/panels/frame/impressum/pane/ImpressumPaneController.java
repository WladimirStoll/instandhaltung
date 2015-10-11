package de.keag.lager.panels.frame.impressum.pane;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.ISuchBeobachter;
import de.keag.lager.core.ITreeBeobachter;
import de.keag.lager.core.ListView;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.pane.details.anforderung.EntnahmePosDetailsController;
import de.keag.lager.panels.frame.menu.MenuController;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.pane.details.wartung.WartungDetailsController;
import de.keag.lager.panels.frame.wartung.pane.details.wartungsMitarbeiter.WartungsMitarbeiterDetailsController;
import de.keag.lager.panels.frame.wartung.pane.details.wartungsArtikel.WartungsArtikelDetailsController;
import de.keag.lager.panels.frame.wartung.pane.liste.WartungListPaneController;
import de.keag.lager.panels.frame.wartung.pane.suche.WartungSuchController;
import de.keag.lager.panels.frame.wartung.pane.tree.WartungTreePaneController;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;


public class ImpressumPaneController extends PaneController {
	private static ImpressumPaneController benutzerPaneController;
	private ImpressumPane benutzerPane; 
	
	public static ImpressumPaneController getOneInstance(){
		if (benutzerPaneController == null){
			benutzerPaneController = new ImpressumPaneController();
		}
		return benutzerPaneController;
	}
	
	private ImpressumPane getWartungPane() {
		if(benutzerPane==null){
			benutzerPane = new ImpressumPane(this);
		}
		return benutzerPane;
	}

	@Override
	public void abbrechenSatz(Bean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListBeobachter(ListView listView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSuchBeobachter(ISuchBeobachter iSuchBeobachter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTreeBeobachter(ITreeBeobachter iTreeBeobachter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ausgewaehlterKnotenIstGeandert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void benachrichtigeBeobachter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void benachrichtigeListBeobachter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void benachrichtigeTreeBeobachter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void erstelleNeuenSatz() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IModel getIModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ListView getListPane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuchView getSuchView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeView getTreePane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loescheSatz(Bean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Fehler> pruefeAktuellenSatz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showView() {
		getWartungPane().setContentPane(Run.getOneInstance().getMainFrame());
	}

	@Override
	public void speichereSatz(Bean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suchen(ISuchBean iSuchBean) {
		// TODO Auto-generated method stub
		
	}

	public void zurueckZumHauptmenue() {
		MenuController.getOneInstance().showView();
		
	}




}
