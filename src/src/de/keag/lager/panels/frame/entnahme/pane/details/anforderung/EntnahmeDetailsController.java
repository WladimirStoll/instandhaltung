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
	import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
	import de.keag.lager.panels.frame.entnahme.pane.EntnahmePaneController;
	import de.keag.lager.panels.frame.email.EmailBean;
	import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
	import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
	import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;
	import de.keag.lager.panels.frame.matchcode.lieferant.ILhMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.LhMCController;

	public class EntnahmeDetailsController extends DetailsController implements ILhMCAnforderer, IConroller, IEmailMCAnforderer {
		private static EntnahmeDetailsController entnahmeDetailsController = null;
		private EntnahmeDetailsView entnahmeDetailsView = null;
		/**
		 * Konstruktor
		 */
		private EntnahmeDetailsController() {
			super(); //der Konstruktor der Superklasse muss aufgerufen werden.
		}
		
		public static EntnahmeDetailsController getOneInstance() {
			if(entnahmeDetailsController==null){
				entnahmeDetailsController = new EntnahmeDetailsController();
				entnahmeDetailsController.addDetailsBeobachter(entnahmeDetailsController.getDetailsView());
			}
			return entnahmeDetailsController;
		}

		@Override
		public void addDetailsBeobachter(DetailsView detailsView) {
			 getPaneController().addDetailsBeobachter(detailsView);
		}
		
		
		@Override
		public DetailsView getDetailsView() {
			if(entnahmeDetailsView==null){
				entnahmeDetailsView = new EntnahmeDetailsView(this);
			}
			return entnahmeDetailsView;
		}

		public void holeLieferanten() throws SQLException, LagerException {
			LhMCController.getOneInstance().holeAlleLieferanten(this);
		}

//		@Override
//		public void selectedLhBeans(ArrayList<LhBean> lhBeans) {
//			if(lhBeans!=null){
//				if(lhBeans.size()>0){
//					((EntnahmeDetailsView)getDetailsView()).setMatchCodeLieferanten(lhBeans.get(0));
//				}
//			}
//		}

		public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException {
			return LhMCController.getOneInstance().getLhAnhandName(lhName);
		}


		public void holeEMail() throws SQLException, LagerException {
			EmailMCController.getOneInstance().holeAlleBeans(this);
		}

		@Override
		public void selectedEmailBeans(ArrayList<EmailBean> EmailBeans) {
			if(EmailBeans!=null){
				if(EmailBeans.size()>0){
					((EntnahmeDetailsView)getDetailsView()).setMatchCodeEmail(EmailBeans.get(0),null);
				}
			}
		}

		@Override
		public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
			EntnahmePaneController.getOneInstance().erzeugeNeueEntnahmePosition();
		}

		@Override
		public void loeschePosition(Bean bean) {
			EntnahmePaneController.getOneInstance().loescheEntnahmePosition((EntnahmePosBean)bean);
		}
		
		@Override
		public PaneController getPaneController(){
			return EntnahmePaneController.getOneInstance(); 
		}

		@Override
		public void selectedLhBeans(ArrayList<LhBean> lhBeans) {
			// TODO Auto-generated method stub
			
		}

//		@Override
//		public void addDetailsBeobachter(DetailsView detailsView) {
//			// TODO Auto-generated method stub
//			
//		}



	}


