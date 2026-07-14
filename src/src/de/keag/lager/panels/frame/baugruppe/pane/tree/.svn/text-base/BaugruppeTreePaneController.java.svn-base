package de.keag.lager.panels.frame.baugruppe.pane.tree;

import java.util.ArrayList;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.fehler.Fehler;

import de.keag.lager.panels.frame.baugruppe.pane.BaugruppePaneController;

public class BaugruppeTreePaneController extends TreeController{
	private BaugruppeTreePaneView baugruppeTreePane = null;
	private static BaugruppeTreePaneController baugruppeSuchPaneController = null;
	
	private BaugruppeTreePaneController() {
		super();
	}

	public static BaugruppeTreePaneController getOneInstance() {
		if(baugruppeSuchPaneController==null){
			baugruppeSuchPaneController = new BaugruppeTreePaneController();
			BaugruppePaneController.getOneInstance().addTreeBeobachter(baugruppeSuchPaneController.getBaugruppeTreePane());
		}
		return baugruppeSuchPaneController;
	}

	public BaugruppeTreePaneView getBaugruppeTreePane() {
		if(baugruppeTreePane==null){
			baugruppeTreePane = new BaugruppeTreePaneView(this);
		}
		return baugruppeTreePane;
	}

	/**
	 * der im Tree vom baugruppe gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	@Override
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		BaugruppePaneController.getOneInstance().selectKnoten(selectedModelKnotenBean);
	}
	
	/**
	 * TreeController hat nie Fehler 
	 */
	@Override
	public ArrayList<Fehler> pruefeDich() {
		return new ArrayList<Fehler>();
	}

	@Override
	public ArrayList<Fehler> setzeNeuenAktivenController() {
//		return Run.getOneInstance().getMainFrame().setzeNeuenAktivenController(this);
		return BaugruppePaneController.getOneInstance().setzeNeuenAktivenController(this);

	}

	@Override
	public void setStandardFocusPosition() {
		//getbaugruppeTreePane().setStandardFocusPosition();
		BaugruppePaneController.getOneInstance().benachrichtigeTreeBeobachter();
	}

	@Override
	public void setBorder(Boolean aktiv) {
		getBaugruppeTreePane().setBorder(aktiv);
		
	}

	@Override
	public TreeView getTreeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaneController getPaneController() {
		return BaugruppePaneController.getOneInstance();
	}
	
	@Override
	public void aktualisiereAnzeige() {
		getPaneController().benachrichtigeTreeBeobachter();
	}
	
}
