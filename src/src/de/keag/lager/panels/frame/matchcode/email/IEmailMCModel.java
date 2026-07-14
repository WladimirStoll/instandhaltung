package de.keag.lager.panels.frame.matchcode.email;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.email.EmailSuchBean;


public interface IEmailMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<EmailBean> getBeans();
	public EmailSuchBean getSuchKriterien();
}
