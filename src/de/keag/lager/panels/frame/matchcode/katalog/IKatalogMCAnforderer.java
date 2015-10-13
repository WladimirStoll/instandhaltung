package de.keag.lager.panels.frame.matchcode.katalog;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.katalog.KatalogBean;

public interface IKatalogMCAnforderer {
	public void selectedKatalogBeans(ArrayList<KatalogBean> katalogBeans);
	public void holeKatalog() throws SQLException, LagerException ;
}
