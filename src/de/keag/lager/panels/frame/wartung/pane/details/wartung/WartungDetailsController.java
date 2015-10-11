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
package de.keag.lager.panels.frame.wartung.pane.details.wartung;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;

import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartung.pane.WartungPaneController;
import de.keag.lager.panels.frame.wartung.pane.suche.WartungSuchPaneView;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.matchcode.baugruppe.BaugruppeMCController;
import de.keag.lager.panels.frame.matchcode.baugruppe.IBaugruppeMCAnforderer;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungsart.IWartungsArtMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungsart.WartungsArtMCController;
import de.keag.lager.panels.frame.matchcode.wartungstyp.IWartungsTypMCAnforderer;
import de.keag.lager.panels.frame.matchcode.wartungstyp.WartungsTypMCController;

public class WartungDetailsController extends DetailsController 
		implements   
		IEmailMCAnforderer,
		IBaugruppeMCAnforderer,
		IWartungsArtMCAnforderer,
		IWartungsTypMCAnforderer
		
		{
	private static WartungDetailsController benutzerBlattController = null;
	private WartungDetailsView benutzerBlatt = null;
	/**
	 * Konstruktor
	 */
	private WartungDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static WartungDetailsController getOneInstance() {
		if(benutzerBlattController==null){
			benutzerBlattController = new WartungDetailsController();
			benutzerBlattController.addDetailsBeobachter(benutzerBlattController.getDetailsView());
		}
		return benutzerBlattController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView){
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public WartungDetailsView getDetailsView() {
		if(benutzerBlatt==null){
			benutzerBlatt = new WartungDetailsView(this);
		}
		return benutzerBlatt;
	}

//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = WartungPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof WartungBean){
//			WartungBean wartungBean = (WartungBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = wartungBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
//		return errors;
//	}


	@Override
	public void holeEMail() throws SQLException, LagerException {
		EmailMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedEmailBeans(ArrayList<EmailBean> EmailBeans) {
		if(EmailBeans!=null){
			if(EmailBeans.size()>0){
				getDetailsView().setMatchCodeEmail(EmailBeans.get(0),null);
			}
		}
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		if (modelKnotenTyp==ModelKnotenTyp.WARTUNG_MITARBEITER){
			WartungPaneController.getOneInstance().erzeugeNeueWartungsMitarbeiter();
		}
		if (modelKnotenTyp==ModelKnotenTyp.WARTUNG_ARTIKEL){
			WartungPaneController.getOneInstance().erzeugeNeueWartungsArtikel();
		}
	}
	
	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof WartungsMitarbeiterBean){
			WartungPaneController.getOneInstance().loescheWartungsMitarbeiter((WartungsMitarbeiterBean)bean);
		}
		if (bean instanceof WartungsArtikelBean){
			WartungPaneController.getOneInstance().loescheWartungsArtikel((WartungsArtikelBean)bean);
		}
	}

	@Override
	public PaneController getPaneController() {
		return WartungPaneController.getOneInstance();
	}

	@Override
	public void holeBaugruppe() throws SQLException, LagerException {
		BaugruppeMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedBaugruppeBeans(ArrayList<BaugruppeBean> beans) {
		if (beans!=null && beans.size()>0){
			((WartungDetailsView)getDetailsView()).setBaugruppe(beans.get(0));
		}
	}

	@Override
	public void holeWartungsArt() throws SQLException, LagerException {
		WartungsArtMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedWartungsArtBeans(ArrayList<WartungsArtBean> beans) {
		if (beans != null && beans.size() > 0) {
			((WartungDetailsView) getDetailsView()).setWartungsArt(beans.get(0));
		}
	}

	@Override
	public void holeWartungsTyp() throws SQLException, LagerException {
		WartungsTypMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedWartungsTypBeans(
			ArrayList<WartungsTypBean> beans) {
		if (beans!=null && beans.size()>0){
			((WartungDetailsView)getDetailsView()).setWartungsTyp(beans.get(0));
		}
	}


	public void generiereWartungsKartenId() throws SQLException, LagerException {
		((WartungPaneController)getPaneController()).generiereWartungsKartenId();
	}

	/**
	 * Auswahl des Status
	 * @param selectedItem
	 */
	public void changeStatus(StatusEnum statusEnum) {
		((WartungPaneController)getPaneController()).changeStatus(statusEnum);
		
	}


}
