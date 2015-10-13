package de.keag.lager.panels.frame.baugruppe.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.pane.BaugruppePaneController;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;
import de.keag.lager.panels.frame.matchcode.baugruppe.BaugruppeMCController;
import de.keag.lager.panels.frame.matchcode.baugruppe.IBaugruppeMCAnforderer;

public class BaugruppeSuchController extends SuchController implements IBaugruppeMCAnforderer, IArtikelMCAnforderer{
	private BaugruppeSuchPaneView baugruppeSuchPaneView = null;
	private static BaugruppeSuchController baugruppeSuchPaneController = null;
	
	private BaugruppeSuchController() {
		super();
	}

	public static BaugruppeSuchController getOneInstance() {
		if(baugruppeSuchPaneController==null){
			baugruppeSuchPaneController = new BaugruppeSuchController();
			BaugruppePaneController.getOneInstance().addSuchBeobachter(baugruppeSuchPaneController.getSuchView());
		}
		return baugruppeSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(baugruppeSuchPaneView==null){
			baugruppeSuchPaneView = new BaugruppeSuchPaneView(this,BaugruppePaneController.getOneInstance().getIModel());
		}
		return baugruppeSuchPaneView;
	}

	
	@Override
	public PaneController getPaneController() {
		return BaugruppePaneController.getOneInstance();
	}


	@Override
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
		if (artikelBeans!=null && artikelBeans.size()>0){
			((BaugruppeSuchPaneView)getSuchView()).setBaugruppe(null);
			((BaugruppeSuchPaneView)getSuchView()).setArtikel(artikelBeans.get(0));
		}
	}

	@Override
	public void holeArtikel() throws SQLException, LagerException {
		ArtikelMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void holeBaugruppe() throws SQLException, LagerException {
		BaugruppeMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedBaugruppeBeans(ArrayList<BaugruppeBean> baugruppeBeans) {
		if (baugruppeBeans!=null && baugruppeBeans.size()>0){
			((BaugruppeSuchPaneView)getSuchView()).setArtikel(null);
			((BaugruppeSuchPaneView)getSuchView()).setBaugruppe(baugruppeBeans.get(0));
			
		}
	}

}
