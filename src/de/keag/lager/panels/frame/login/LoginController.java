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
package de.keag.lager.panels.frame.login;

import java.util.logging.Handler;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.LagerLogDbHandler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.menu.MenuController;

public class LoginController {
	private static LoginController loginContoller = null;
	private LoginModel loginModel;
	private LoginView loginView;

	/**
	 * Konstruktor
	 */
	public LoginController() {
		super();
	}

	public void tuEinloggen(LoginBean loginBean) {
			loginModel.loescheFehlerMeldungen();//vorherige Fehlermeldungen im Model löschen.
			if (loginModel.tuEinloggen(loginBean)){ //Versuch, den User anzumelden.
				MenuController.getOneInstance().showView();
//				//DB-Protokoll erst jetzt einschalten
//				for(Handler handler : Log.log().getHandlers()){
//					if (handler instanceof LagerLogDbHandler){
//						((LagerLogDbHandler)handler).setEingeschaltet(true);
//					}
//				}
				
			}
	}

	//Vor dem Neuanmelden eines User sollen die Werte gelöscht werden.
	public void setzeAktuellenBenutzerAufNull() {
		loginModel.setzeAktuellenLoginAufNull();
	}

	public static LoginController getOneInstance() {
		if(loginContoller==null){
			loginContoller = new LoginController();
			loginContoller.getLoginModel().setIBeobachter(loginContoller.getLoginView());
		}
		return loginContoller;
	}

	private LoginModel getLoginModel() {
		if(loginModel==null){
			loginModel = new LoginModel();
		}
		return loginModel;
	}

	protected LoginView getLoginView() {
		if(loginView==null){
			loginView = new LoginView(this,getLoginModel());
		}
		return loginView;
	}
	
	public void showView(){
		getLoginView().showView();
	}
}
