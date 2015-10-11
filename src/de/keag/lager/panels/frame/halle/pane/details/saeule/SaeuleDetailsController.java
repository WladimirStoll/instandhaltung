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
package de.keag.lager.panels.frame.halle.pane.details.saeule;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.halle.pane.HallePaneController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.ZugriffsrechtMCController;
import de.keag.lager.panels.frame.matchcode.zugriffsrecht.IZugriffsrechtMCAnforderer;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

public class SaeuleDetailsController extends DetailsController  {
	private static SaeuleDetailsController BenutzerPosZugriffsrechtBlattController;
	private SaeuleDetailsView detailsView;
	
	private SaeuleDetailsController() {
		super();
	}

	public static SaeuleDetailsController getOneInstance() {
		if(BenutzerPosZugriffsrechtBlattController==null){
			BenutzerPosZugriffsrechtBlattController = new SaeuleDetailsController();
			BenutzerPosZugriffsrechtBlattController.addDetailsBeobachter (BenutzerPosZugriffsrechtBlattController.getDetailsView());
		}
		return BenutzerPosZugriffsrechtBlattController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new SaeuleDetailsView(this);
		}
		return detailsView;
	}

//	@Override
//	public void holeZugriffsrecht() throws SQLException, LagerException {
//		ZugriffsrechtMCController.getOneInstance().holeAlleBeans(this);
//	}

//	//Matchode - Fenster "Zugriffsrecht" ruft zurück.
//	@Override
//	public void selectedSaeuleBeans(ArrayList<SaeuleBean> zugriffsrechtBeans) {
//		if (zugriffsrechtBeans!=null && zugriffsrechtBeans.size()>0){
//			((SaeuleDetailsView)getDetailsView()).setSaeule(zugriffsrechtBeans.get(0));
//		}
//	}
//	

//	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//	protected void ausgewaehlterKnotenIstGeandert() {
//		HallePaneController.getOneInstance().ausgewaehlterKnotenIstGeandert();
//	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = HallePaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof SaeuleBean){
//			SaeuleBean benutzerPosBean = (SaeuleBean)iBean;
//			errors = getBenutzerPosZugriffsrechtDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = benutzerPosBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
//		return errors;
//	}


	
	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		if (modelKnotenTyp==ModelKnotenTyp.EBENE){
			HallePaneController.getOneInstance().erzeugeNeueEbene()	;
		}else{
			Log.log().severe("Falscher Typ:"+modelKnotenTyp);
		}
	}


	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof EbeneBean){
			HallePaneController.getOneInstance().loescheEbene((EbeneBean)bean);
		}else{
			Log.log().severe("Falscher Typ:"+bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return HallePaneController.getOneInstance();
	}

	public void druckeInventurListe(SaeuleBean bean, boolean mitMengen) throws LagerException, SQLException, Exception{
		 ((HallePaneController)getPaneController()).druckeInventurListe(bean, mitMengen);
	}
	
	
}
