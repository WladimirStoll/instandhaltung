package de.keag.lager.panels.frame.artikel.pane.suche;

import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.SuchController;
import de.keag.lager.core.SuchView;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.artikel.pane.details.artikel.ArtikelDetailsView;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.pane.details.benutzerAbteilung.BenutzerAbteilungDetailsView;
import de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung.BaDetailsView;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;
import de.keag.lager.panels.frame.matchcode.benutzer.BenutzerMCController;
import de.keag.lager.panels.frame.matchcode.benutzer.IBenutzerMCAnforderer;
import de.keag.lager.panels.frame.matchcode.etage.EtageMCController;
import de.keag.lager.panels.frame.matchcode.etage.IEtageMCAnforderer;
import de.keag.lager.panels.frame.matchcode.halle.HalleMCController;
import de.keag.lager.panels.frame.matchcode.halle.IHalleMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.ILhMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.LhMCController;

public class ArtikelSuchController extends SuchController 
//	implements IAbteilungMCAnforderer,
//				IHalleMCAnforderer,
//				IEtageMCAnforderer,
////				IBenutzerMCAnforderer,
	implements 			IArtikelMCAnforderer,
						ILhMCAnforderer
				{
	private ArtikelSuchPaneView benutzerSuchPaneView = null;
	private static ArtikelSuchController benutzerSuchPaneController = null;
	
	private ArtikelSuchController() {
		super();
	}

	public static ArtikelSuchController getOneInstance() {
		if(benutzerSuchPaneController==null){
			benutzerSuchPaneController = new ArtikelSuchController();
			ArtikelPaneController.getOneInstance().addSuchBeobachter(benutzerSuchPaneController.getSuchView());
		}
		return benutzerSuchPaneController;
	}

	@Override
	public SuchView getSuchView() {
		if(benutzerSuchPaneView==null){
			benutzerSuchPaneView = new ArtikelSuchPaneView(this,ArtikelPaneController.getOneInstance().getIModel());
		}
		return benutzerSuchPaneView;
	}

	
	@Override
	public PaneController getPaneController() {
		return ArtikelPaneController.getOneInstance();
	}

//	@Override
//	public void holeAbteilung() throws SQLException, LagerException {
//		AbteilungMCController.getOneInstance().holeAlleBeans(this);
//	}

//	//Matchode - Fenster "Abteilung" ruft zurück.
//	@Override
//	public void selectedAbteilungBeans(ArrayList<AbteilungBean> abteilungBeans) {
//		if (abteilungBeans!=null && abteilungBeans.size()>0){
//			((ArtikelSuchPaneView)getSuchView()).setAbteilung(abteilungBeans.get(0));
//		}
//	}

//	@Override
//	public void holeHalle() throws SQLException, LagerException {
//		HalleMCController.getOneInstance().holeAlleBeans(this);
//	}


//	@Override
//	public void holeEtage() throws SQLException, LagerException {
//		EtageMCController.getOneInstance().holeAlleBeans(this);
//	}

//	@Override
//	public void selectedEtageBeans(ArrayList<EtageBean> etageBeans) {
//		if (etageBeans!=null && etageBeans.size()>0){
//			((ArtikelSuchPaneView)getSuchView()).setEtage(etageBeans.get(0));
//		}
//	}

	@Override
	public void holeArtikel() throws SQLException,  LagerException {
		ArtikelMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
		if (artikelBeans!=null && artikelBeans.size()>0){
			((ArtikelSuchPaneView)getSuchView()).setArtikel(artikelBeans.get(0));
		}
	}

	public void holeLieferanten() throws SQLException, LagerException {
		LhMCController.getOneInstance().holeAlleLieferanten(this);
	}

	@Override
	public void selectedLhBeans(ArrayList<LhBean> lhBeans) {
		if(lhBeans!=null){
			if(lhBeans.size()>0){
				((ArtikelSuchPaneView)getSuchView()).setMatchCodeLieferanten(lhBeans.get(0));
			}
		}
	}

//	@Override
//	public void selectedHalleBeans(ArrayList<HalleBean> halleBeans) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void holeBenutzer() throws SQLException, LagerException {
//        BenutzerMCController.getOneInstance().holeAlleBeans(this);
//	}
//
//	@Override
//	public void selectedBenutzerBeans(ArrayList<BenutzerBean> benutzerBeans) {
//		if (benutzerBeans!=null && benutzerBeans.size()>0){
//			((ArtikelSuchPaneView)getSuchView()).setBenutzer(benutzerBeans.get(0));
//		}
//	}


}
