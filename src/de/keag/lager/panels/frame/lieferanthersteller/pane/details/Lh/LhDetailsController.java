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
package de.keag.lager.panels.frame.lieferanthersteller.pane.details.Lh;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhZugriffsrechtBean;
import de.keag.lager.panels.frame.lieferanthersteller.pane.LhPaneController;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;

public class LhDetailsController extends DetailsController implements   IEmailMCAnforderer {
	private static LhDetailsController lhBlattController = null;
	private LhDetailsView lhBlatt = null;
	/**
	 * Konstruktor
	 */
	private LhDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static LhDetailsController getOneInstance() {
		if(lhBlattController==null){
			lhBlattController = new LhDetailsController();
			lhBlattController.addDetailsBeobachter(lhBlattController.getDetailsView());
		}
		return lhBlattController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView){
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public LhDetailsView getDetailsView() {
		if(lhBlatt==null){
			lhBlatt = new LhDetailsView(this);
		}
		return lhBlatt;
	}

//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = LhPaneController.getOneInstance().getIModel().getAktiveTreeModelBean().getIBean();
//		if(iBean instanceof LhBean){
//			LhBean lhBean = (LhBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = benutzerBean.pruefeEigeneDaten();
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
		if (modelKnotenTyp==ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG){
			LhPaneController.getOneInstance().erzeugeNeueLhKatalog();
		}
//		if (modelKnotenTyp==ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT){
//			LhPaneController.getOneInstance().erzeugeNeueLhZugriffsrecht();
//		}
	}
	
	@Override
	public void loeschePosition(Bean bean) {
		if (bean instanceof LhKatalogBean){
			LhPaneController.getOneInstance().loescheLhKatalog((LhKatalogBean)bean);
		}
//		if (bean instanceof LhZugriffsrechtBean){
//			LhPaneController.getOneInstance().loescheLhZugriffsrecht((LhZugriffsrechtBean)bean);
//		}
	}

	@Override
	public PaneController getPaneController() {
		return LhPaneController.getOneInstance();
	}




}
