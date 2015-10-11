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
package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

abstract public class DetailsController extends Controller {


//	abstract public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp  );
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		getPaneController().erstelleNeuenSatz();
	}

//	abstract public void loeschePosition(Bean bean);
//	@Override
	public void loeschePosition(Bean bean) {
		getPaneController().loeschePosition(bean);
	}
	
	public void loescheSatz(Bean bean) {
		getPaneController().loescheSatz(bean);
	}
	
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		getPaneController().selectKnoten(selectedModelKnotenBean);//this
//		getPaneController().benachrichtigeTreeBeobachter();
	}

	abstract public DetailsView getDetailsView();

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = null;
		IBean iBean = null;
		if (getPaneController().getIModel().getAktiveTreeModelBean()!=null){
			iBean = getPaneController().getIModel().getAktiveTreeModelBean().getIBean();
		}
		errors = getDetailsView().pruefeDich();
		if (errors==null){
			errors = new ArrayList<Fehler>();
		}
		if (errors.size() == 0&&iBean!=null){
			errors = iBean.pruefeEigeneDaten();
		}
		return errors;//this
//		if(iBean instanceof BaBean){
//			BaBean baBean = (BaBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = baBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
		
	}
	
//	@Override
//	public ArrayList<Fehler> pruefeDich() {
//		ArrayList<Fehler> errors = null;
//		IBean iBean = BaPaneController.getOneInstance().getIModel().getAktiveModelBean().getIBean();
//		if(iBean instanceof BaPosBean){
//			BaPosBean baPosBean = (BaPosBean)iBean;
//			errors = getDetailsView().pruefeDich();
//			if (errors.size() == 0){
//				errors = baPosBean.pruefeEigeneDaten();
//			}
//		}else{
//			//Unbekantte Bean, unerwaretet Zustand. Keine Fehler werden gemeldet!
//			errors = new ArrayList<Fehler>(); 
//		}
//		return errors;
//	}
	
	
	@Override
	public void setBorder(Boolean aktiv) {
		getDetailsView().setBorder(aktiv);
	}

	
	@Override
	public void setStandardFocusPosition() {
		zeigeGewahlteDetails();		
		getDetailsView().setStandardFocusPosition();
	}

//	abstract public void addDetailsBeobachter(DetailsView detailsView);
	public void addDetailsBeobachter(DetailsView detailsView) {
		 getPaneController().addDetailsBeobachter(detailsView);
	}
	
	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	public void ausgewaehlterKnotenIstGeandert() {
		getPaneController().ausgewaehlterKnotenIstGeandert();
	}
	
	public void zeigeGewahlteDetails(){
		if (getPaneController()!=null
				&& getPaneController().getIModel()!=null
				&& getPaneController().getIModel().getAktiveTreeModelBean()!=null){
			ModelKnotenBean aktiveTreeModelBean = getPaneController().getIModel().getAktiveTreeModelBean();

		}
	}

}
