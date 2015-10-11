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


import java.sql.SQLException;
import java.util.ArrayList;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.LoginLagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.BenutzerBeanDB;
import de.keag.lager.db.LoginBeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.menu.MenuView;

public class LoginModel implements ILoginModel {
	private ILoginBeobachter iLoginBeobachter; //Zeiger auf View
	private ArrayList<Fehler> fehlerListe = new ArrayList<Fehler>(); //Sammlung von Fehlern
	private LoginBean loginBean;
	private LoginBeanDB loginBeanDB;
	private BenutzerBeanDB benutzerBeanDB = null;
	
	private LoginBeanDB getLoginBeanDB() {
		return loginBeanDB;
	}

	/**
	 * Konstruktor
	 */
	public LoginModel() {
		super();
		loginBean = new LoginBean();
		loginBeanDB = new LoginBeanDB();
		iLoginBeobachter = null; //Am Angang kennt diese Klasse seinen ILoginBeobachter nicht.
	}
	
	private void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	

	/**
	 * Mit dieser Funktion kann man zu jedem Zeitpunkt 
	 * den Beobachter neu setzen.
	 * @param iLoginBeobachter
	 */
	public void setIBeobachter(ILoginBeobachter iLoginBeobachter) {
		this.iLoginBeobachter = iLoginBeobachter;
	}

	private void benachrichtigeBeobachter(){
		if(iLoginBeobachter != null){
			iLoginBeobachter.zeichneDich();
		}
	}

	public Boolean tuEinloggen(LoginBean loginBean)   {
		Boolean istAngemeldet = false; //false=fehler in der Anmeldung
		if (loginBean != null) {
			setLoginBean(loginBean);
			try {
				getLoginBean().setBeanStatus(BeanDBStatus.SELECT);
				LoginBean resultBean;
				try {
					resultBean = getLoginBeanDB().selectByLoginName(
							getLoginBean());
					if (resultBean.getPassword().equalsIgnoreCase(
							getLoginBean().getPassword())) {
						setLoginBean(resultBean);
						Log.log().info("Benutzer "+ getLoginBean().toString() + " ist angemeldet.");

						//Benutzer wird gelesen
						int benutzerID = resultBean.getId();
						BenutzerBean benutzerBean = (BenutzerBean) getBenutzerBeanDB().selectAnhandID(benutzerID, 0, null);
						Run.getOneInstance().setBenutzer(benutzerBean);
						istAngemeldet = true ; //Anmeldung ist OK
					} else {
						// passwort ist falsch
						fehlerListe.add(new Fehler(9));// Falsches Passwort
					}
				} catch (LoginLagerException e) {
					fehlerListe.add(e.getFehler());
					e.printStackTrace();
				} catch (LagerException e) {
					fehlerListe.add(e.getFehler());
					e.printStackTrace();
				}

			} catch (SQLException e) {
				fehlerListe.add(new Fehler(8));// Anmeldung fehlgeschlagen.
				Log.logger.severe(e.getMessage());
				e.printStackTrace();
			}
		} else {
			fehlerListe.add(new Fehler(7));//Objekt ist nicht vorhanden.
		}
		benachrichtigeBeobachter();
		return istAngemeldet;
	}

	/* (non-Javadoc)
	 * @see de.keag.lager.panels.frame.login.IBenutzerModel#getBenutzerBean()
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public ArrayList<Fehler> getFehlerListe() {
		return fehlerListe;
	}

	public void loescheFehlerMeldungen() {
		this.fehlerListe.clear();
		
	}

	public void setzeAktuellenLoginAufNull() {
		setLoginBean(new LoginBean());
		benachrichtigeBeobachter();
	}

	private BenutzerBeanDB getBenutzerBeanDB() {
		if (benutzerBeanDB == null){
			benutzerBeanDB = new BenutzerBeanDB();
		}
		return benutzerBeanDB;
	}
	

}