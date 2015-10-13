package de.keag.lager.panels.frame.katalog.pane.details.anforderung;

//public class KatalogDetailsController {
	import java.sql.SQLException;
	import java.util.ArrayList;

	import de.keag.lager.core.Bean;
	import de.keag.lager.core.DetailsController;
	import de.keag.lager.core.DetailsView;
	import de.keag.lager.core.IBean;
	import de.keag.lager.core.IConroller;
	import de.keag.lager.core.ModelKnotenBean;
	import de.keag.lager.core.PaneController;
	import de.keag.lager.core.enums.ModelKnotenTyp;
	import de.keag.lager.core.fehler.Fehler;
	import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
	import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.artikel.pane.details.artikel.ArtikelDetailsView;
import de.keag.lager.panels.frame.artikel.pane.details.artikelPosition.ArtikelPosDetailsView;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
	import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck.BestandspackstueckDetailsView;
import de.keag.lager.panels.frame.katalog.pane.KatalogPaneController;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
	import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;
import de.keag.lager.panels.frame.matchcode.bestandspackstueck.BestandspackstueckMCController;
import de.keag.lager.panels.frame.matchcode.bestandspackstueck.IBestandspackstueckMCAnforderer;
	import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
	import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;
import de.keag.lager.panels.frame.matchcode.halle.HalleMCController;
import de.keag.lager.panels.frame.matchcode.halle.IHalleMCAnforderer;
import de.keag.lager.panels.frame.matchcode.kostenstelle.IKostenstelleMCAnforderer;
import de.keag.lager.panels.frame.matchcode.kostenstelle.KostenstelleMCController;
	import de.keag.lager.panels.frame.matchcode.lieferant.ILhMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.LhMCController;
import de.keag.lager.panels.frame.position.model.PositionBean;

	public class KatalogDetailsController 
	extends DetailsController 
{
		private static KatalogDetailsController einlagerungPosDetailsController = null;  //  @jve:decl-index=0:
		private KatalogDetailsView einlagerungPosDetailsView = null;
		/**
		 * Konstruktor
		 */
		private KatalogDetailsController() {
			super(); //der Konstruktor der Superklasse muss aufgerufen werden.
		}
		
		public static KatalogDetailsController getOneInstance() {
			if(einlagerungPosDetailsController==null){
				einlagerungPosDetailsController = new KatalogDetailsController();
				einlagerungPosDetailsController.addDetailsBeobachter(einlagerungPosDetailsController.getDetailsView());
			}
			return einlagerungPosDetailsController;
		}

		@Override
		public void addDetailsBeobachter(DetailsView detailsView) {
			 getPaneController().addDetailsBeobachter(detailsView);
		}
		
		
		@Override
		public DetailsView getDetailsView() {
			if(einlagerungPosDetailsView==null){
				einlagerungPosDetailsView = new KatalogDetailsView(this);
			}
			return einlagerungPosDetailsView;
		}

		@Override
		public PaneController getPaneController(){
			return KatalogPaneController.getOneInstance(); 
		}

//		public void vervollstaendigePosition(PositionBean positionBean) throws LagerException, SQLException {
//			((KatalogPaneController) getPaneController())
//					.vervollstaendigePosition(positionBean);
//
//		}


	}


