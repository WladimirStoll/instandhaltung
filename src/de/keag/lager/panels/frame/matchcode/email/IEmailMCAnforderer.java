package de.keag.lager.panels.frame.matchcode.email;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.email.EmailBean;

public interface IEmailMCAnforderer {
	public void selectedEmailBeans(ArrayList<EmailBean> EmailBeans);
	public void holeEMail() throws SQLException, LagerException;
}
