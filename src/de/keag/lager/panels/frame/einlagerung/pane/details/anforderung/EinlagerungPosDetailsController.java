/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
package de.keag.lager.panels.frame.einlagerung.pane.details.anforderung;

//public class EinlagerungDetailsController {
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
	import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
	import de.keag.lager.panels.frame.einlagerung.pane.EinlagerungPaneController;
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

	public class EinlagerungPosDetailsController 
	extends DetailsController 
	implements  IConroller,
	IKostenstelleMCAnforderer,
//	IArtikelMCAnforderer,
	IBestandspackstueckMCAnforderer,
	IHalleMCAnforderer {
		private static EinlagerungPosDetailsController einlagerungPosDetailsController = null;  //  @jve:decl-index=0:
		private EinlagerungPosDetailsView einlagerungPosDetailsView = null;
		/**
		 * Konstruktor
		 */
		private EinlagerungPosDetailsController() {
			super(); //der Konstruktor der Superklasse muss aufgerufen werden.
		}
		
		public static EinlagerungPosDetailsController getOneInstance() {
			if(einlagerungPosDetailsController==null){
				einlagerungPosDetailsController = new EinlagerungPosDetailsController();
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
				einlagerungPosDetailsView = new EinlagerungPosDetailsView(this);
			}
			return einlagerungPosDetailsView;
		}




		public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException {
			return LhMCController.getOneInstance().getLhAnhandName(lhName);
		}

//		@Override
//		public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
//			EinlagerungPaneController.getOneInstance().erzeugeNeueEinlagerungPosition();
//		}
//
//		@Override
//		public void loeschePosition(Bean bean) {
//			EinlagerungPaneController.getOneInstance().loescheEinlagerungPosition((EinlagerungPosBean)bean);
//		}
		
		@Override
		public PaneController getPaneController(){
			return EinlagerungPaneController.getOneInstance(); 
		}

		public void vervollstaendigePosition(PositionBean positionBean) throws LagerException, SQLException {
			((EinlagerungPaneController) getPaneController())
					.vervollstaendigePosition(positionBean);

		}

		@Override
		public void holeHalle() throws SQLException, LagerException {
			HalleMCController.getOneInstance().holeAlleBeans(this);
		}

		@Override
		public void selectedHalleBeans(ArrayList<HalleBean> halleBeans) {
			if (halleBeans != null && halleBeans.size() > 0) {
				((EinlagerungPosDetailsView) getDetailsView()).setHalle(halleBeans
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
				((EinlagerungPosDetailsView)getDetailsView()).setKostenstelle(kostenstelleBeans.get(0));
			}
		}

//		@Override
//		public void holeArtikel() throws SQLException, LagerException {
//			ArtikelMCController.getOneInstance().holeAlleBeans(this);
//		}

//		@Override
//		public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
//			if (artikelBeans!=null && artikelBeans.size()>0){
//				((EinlagerungPosDetailsView)getDetailsView()).setArtikel(artikelBeans.get(0));
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
				((EinlagerungPosDetailsView)getDetailsView()).setBestandspackstueck(bestandspackstueckBeans.get(0));
			}
		}

		public void bestaetigeEinlagerungPosition() {
			((EinlagerungPaneController)getPaneController()).bestaetigeEinlagerungPosition();
			
		}



	}


