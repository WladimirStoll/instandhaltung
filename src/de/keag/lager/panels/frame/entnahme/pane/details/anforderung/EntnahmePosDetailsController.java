package de.keag.lager.panels.frame.entnahme.pane.details.anforderung;

//public class EntnahmeDetailsController {
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
	import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
	import de.keag.lager.panels.frame.entnahme.pane.EntnahmePaneController;
	import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck.BestandspackstueckDetailsView;
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

	public class EntnahmePosDetailsController 
	extends DetailsController 
	implements  IConroller,
	IKostenstelleMCAnforderer,
//	IArtikelMCAnforderer,
	IBestandspackstueckMCAnforderer,
	IHalleMCAnforderer {
		private static EntnahmePosDetailsController entnahmePosDetailsController = null;  //  @jve:decl-index=0:
		private EntnahmePosDetailsView entnahmePosDetailsView = null;
		/**
		 * Konstruktor
		 */
		private EntnahmePosDetailsController() {
			super(); //der Konstruktor der Superklasse muss aufgerufen werden.
		}
		
		public static EntnahmePosDetailsController getOneInstance() {
			if(entnahmePosDetailsController==null){
				entnahmePosDetailsController = new EntnahmePosDetailsController();
				entnahmePosDetailsController.addDetailsBeobachter(entnahmePosDetailsController.getDetailsView());
			}
			return entnahmePosDetailsController;
		}

		@Override
		public void addDetailsBeobachter(DetailsView detailsView) {
			 getPaneController().addDetailsBeobachter(detailsView);
		}
		
		
		@Override
		public DetailsView getDetailsView() {
			if(entnahmePosDetailsView==null){
				entnahmePosDetailsView = new EntnahmePosDetailsView(this);
			}
			return entnahmePosDetailsView;
		}




		public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException {
			return LhMCController.getOneInstance().getLhAnhandName(lhName);
		}

//		@Override
//		public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
//			EntnahmePaneController.getOneInstance().erzeugeNeueEntnahmePosition();
//		}
//
//		@Override
//		public void loeschePosition(Bean bean) {
//			EntnahmePaneController.getOneInstance().loescheEntnahmePosition((EntnahmePosBean)bean);
//		}
		
		@Override
		public PaneController getPaneController(){
			return EntnahmePaneController.getOneInstance(); 
		}

		public void vervollstaendigePosition(PositionBean positionBean) throws LagerException, SQLException {
			((EntnahmePaneController) getPaneController())
					.vervollstaendigePosition(positionBean);

		}

		@Override
		public void holeHalle() throws SQLException, LagerException {
			HalleMCController.getOneInstance().holeAlleBeans(this);
		}

		@Override
		public void selectedHalleBeans(ArrayList<HalleBean> halleBeans) {
			if (halleBeans != null && halleBeans.size() > 0) {
				((EntnahmePosDetailsView) getDetailsView()).setHalle(halleBeans
						.get(0));
			}
		}

		@Override
		public void holeKostenstelle() throws SQLException, LagerException {
			KostenstelleMCController.getOneInstance().holeAlleBeans(this);
		}

		@Override
		public void selectedKostenstelleBeans(ArrayList<KostenstelleBean> kostenstelleBeans) {
			if (kostenstelleBeans!=null && kostenstelleBeans.size()>0){
				((EntnahmePosDetailsView)getDetailsView()).setKostenstelle(kostenstelleBeans.get(0));
			}
		}

//		@Override
//		public void holeArtikel() throws SQLException, LagerException {
//			ArtikelMCController.getOneInstance().holeAlleBeans(this);
//		}

//		@Override
//		public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
//			if (artikelBeans!=null && artikelBeans.size()>0){
//				((EntnahmePosDetailsView)getDetailsView()).setArtikel(artikelBeans.get(0));
//			}
//		}
//
		@Override
		public void holeBestandspackstueck(PositionBean positionBean) throws SQLException,
				LagerException {
			BestandspackstueckMCController.getOneInstance().holeAlleBeans(this, positionBean);
		}

		@Override
		public void selectedBestandspackstueckBeans(
				ArrayList<BestandspackstueckBean> bestandspackstueckBeans) {
			if (bestandspackstueckBeans!=null && bestandspackstueckBeans.size()>0){
				((EntnahmePosDetailsView)getDetailsView()).setBestandspackstueck(bestandspackstueckBeans.get(0));
			}
		}

		public void bestaetigeEntnahmePosition() {
			((EntnahmePaneController)getPaneController()).bestaetigeEntnahmePosition();
			
		}



	}


