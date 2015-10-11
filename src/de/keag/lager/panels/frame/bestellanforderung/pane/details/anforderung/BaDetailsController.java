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
package de.keag.lager.panels.frame.bestellanforderung.pane.details.anforderung;

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
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.matchcode.email.EmailMCController;
import de.keag.lager.panels.frame.matchcode.email.IEmailMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.ILhMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferant.LhMCController;

public class BaDetailsController extends DetailsController implements ILhMCAnforderer, IConroller, IEmailMCAnforderer {
	private static BaDetailsController baDetailsController = null;
	private BaDetailsView baDetailsView = null;
	/**
	 * Konstruktor
	 */
	private BaDetailsController() {
		super(); //der Konstruktor der Superklasse muss aufgerufen werden.
	}
	
	public static BaDetailsController getOneInstance() {
		if(baDetailsController==null){
			baDetailsController = new BaDetailsController();
			baDetailsController.addDetailsBeobachter(baDetailsController.getDetailsView());
		}
		return baDetailsController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		 getPaneController().addDetailsBeobachter(detailsView);
	}
	
	
	@Override
	public DetailsView getDetailsView() {
		if(baDetailsView==null){
			baDetailsView = new BaDetailsView(this);
		}
		return baDetailsView;
	}

	public void holeLieferanten() throws SQLException, LagerException {
		LhMCController.getOneInstance().holeAlleLieferanten(this);
	}

	@Override
	public void selectedLhBeans(ArrayList<LhBean> lhBeans) {
		if(lhBeans!=null){
			if(lhBeans.size()>0){
				((BaDetailsView)getDetailsView()).setMatchCodeLieferanten(lhBeans.get(0));
			}
		}
	}

	public LhBean getLhAnhandName(String lhName) throws LagerException, SQLException {
		return LhMCController.getOneInstance().getLhAnhandName(lhName);
	}


	public void holeEMail() throws SQLException, LagerException {
		EmailMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedEmailBeans(ArrayList<EmailBean> EmailBeans) {
		if(EmailBeans!=null){
			if(EmailBeans.size()>0){
				((BaDetailsView)getDetailsView()).setMatchCodeEmail(EmailBeans.get(0),null);
			}
		}
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		BaPaneController.getOneInstance().erzeugeNeueBaPosition();
	}

	@Override
	public void loeschePosition(Bean bean) {
		BaPaneController.getOneInstance().loescheBaPosition((BaPosBean)bean);
	}
	
	@Override
	public PaneController getPaneController(){
		return BaPaneController.getOneInstance(); 
	}



}
