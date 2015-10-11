package de.keag.lager.panels.frame.matchcode.wartungstyp;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

public interface IWartungsTypMCAnforderer {
	public void selectedWartungsTypBeans(ArrayList<WartungsTypBean> WartungsTypBeans);
	public void holeWartungsTyp() throws SQLException, LagerException;
}
