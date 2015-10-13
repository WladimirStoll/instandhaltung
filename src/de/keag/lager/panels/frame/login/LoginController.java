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
