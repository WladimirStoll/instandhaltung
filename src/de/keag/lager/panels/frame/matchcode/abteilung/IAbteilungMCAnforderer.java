package de.keag.lager.panels.frame.matchcode.abteilung;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;

public interface IAbteilungMCAnforderer {
	public void selectedAbteilungBeans(ArrayList<AbteilungBean> abteilungBeans);
	public void holeAbteilung() throws SQLException, LagerException ;
}
