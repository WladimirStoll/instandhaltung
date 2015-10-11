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

import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.LagerConnection;
import junit.framework.TestCase;


public class LoginJUnit extends TestCase {

	public void testSetzeAktuellenBenutzerAufNull(){
		LoginController.getOneInstance().getLoginView().getLoginController().setzeAktuellenBenutzerAufNull();
	}
	
	public void testAnlegenUserLager() throws SQLException{
		//alte Werte werden gelöscht.
		PreparedStatement stmt = LagerConnection.getOneInstance()
		.getConnection().prepareStatement(
					"delete from benutzer where name = 'testunit'");
//	"delete from benutzer where name = 'lager'");
					stmt.executeUpdate();
		stmt.close();
		//neuen Satz anlegen
		stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(
//						"INSERT INTO benutzer VALUES (1,'lager', 'lager', 'lager', 'pass')");
						" INSERT INTO benutzer (id, name,      vorname,    loginName, password, copy_ba_email_empfaenger) " +
						" VALUES " +
						               "       (1,'testunit', 'testunit', 'testunit', 'test', 0 ) ");
		stmt.executeUpdate();
	}
	
	/**
	 * Test: Benutzer "lager" ist vorhanden.
	 * Das Passwort wurde falsch eingegeben.
	 * @throws Exception
	 */
	public void testTuEinloggen5() throws Exception{
		LoginController.getOneInstance().getLoginView().getJTextFieldUserName().setText("lager");
		LoginController.getOneInstance().getLoginView().getJPasswordField().setText("falsch");
		LoginController.getOneInstance().getLoginView().getJButtonLogin().doClick();
		if(LoginController.getOneInstance().getLoginView().getiLoginModel().getFehlerListe().get(0).getId()!=9)
		{
			throw new Exception("Fehlercode <> 9, sondern " + LoginController.getOneInstance().getLoginView().getiLoginModel().getFehlerListe().get(0).getId());
		}
	}
	
	//Ablauf: User meldet sich korrekt an. Ergebnis: User ist angemeldet.
	public void testTuEinloggen6() throws Exception{
		LoginController.getOneInstance().getLoginView().getJTextFieldUserName().setText("lager");
		LoginController.getOneInstance().getLoginView().getJPasswordField().setText("pass");
		LoginController.getOneInstance().getLoginView().getJButtonLogin().doClick();
		if(LoginController.getOneInstance().getLoginView().getiLoginModel().getFehlerListe().size()!=0)
		{
			throw new Exception("User konnte nicht angemeldet werden.");
		}
	}
	
	//Ablauf: Loginname, passwort ist falsch. Ergebnis: fehlercode = 6.
	public void testTuEinloggen7() throws Exception{
		LoginController.getOneInstance().getLoginView().getJTextFieldUserName().setText("false");
		LoginController.getOneInstance().getLoginView().getJPasswordField().setText("false");
		LoginController.getOneInstance().getLoginView().getJButtonLogin().doClick();
		if(LoginController.getOneInstance().getLoginView().getiLoginModel().getFehlerListe().get(0).getId()!=6)
		{
			throw new Exception("Fehlercode <> 6");
		}
	}
	//Ablauf: User meldet ohne daten an. Ergebnis: fehlercode = 6.
	public void testTuEinloggen8() throws Exception{
		LoginController.getOneInstance().getLoginView().getJTextFieldUserName().setText("");
		LoginController.getOneInstance().getLoginView().getJPasswordField().setText("");
		LoginController.getOneInstance().getLoginView().getJButtonLogin().doClick();
		if(LoginController.getOneInstance().getLoginView().getiLoginModel().getFehlerListe().get(0).getId()!=6)
		{
			throw new Exception("Fehlercode <> 6");
		}
	}
	
	//Ablauf: Passwort fehlt. Ergebnis: fehlercode = 6.
	public void testTuEinloggen9() throws Exception{
		LoginController.getOneInstance().getLoginView().getJTextFieldUserName().setText("lager");
		LoginController.getOneInstance().getLoginView().getJPasswordField().setText("");
		LoginController.getOneInstance().getLoginView().getJButtonLogin().doClick();
		if(LoginController.getOneInstance().getLoginView().getiLoginModel().getFehlerListe().get(0).getId()!=9)
		{
			throw new Exception("Fehlercode <> 9");
		}
	}	
}
