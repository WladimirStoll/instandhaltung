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
