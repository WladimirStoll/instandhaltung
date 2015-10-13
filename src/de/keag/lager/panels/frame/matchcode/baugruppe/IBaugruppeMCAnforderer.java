package de.keag.lager.panels.frame.matchcode.baugruppe;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;

public interface IBaugruppeMCAnforderer {
	public void selectedBaugruppeBeans(ArrayList<BaugruppeBean> abteilungBeans);
	public void holeBaugruppe() throws SQLException, LagerException ;
}
