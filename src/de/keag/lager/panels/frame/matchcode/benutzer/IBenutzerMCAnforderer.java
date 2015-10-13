package de.keag.lager.panels.frame.matchcode.benutzer;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;

public interface IBenutzerMCAnforderer {
	public void selectedBenutzerBeans(ArrayList<BenutzerBean> benutzerBeans);
	public void holeBenutzer() throws SQLException, LagerException ;
}
