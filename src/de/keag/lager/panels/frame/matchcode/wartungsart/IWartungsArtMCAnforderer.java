package de.keag.lager.panels.frame.matchcode.wartungsart;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;

public interface IWartungsArtMCAnforderer {
	public void selectedWartungsArtBeans(ArrayList<WartungsArtBean> WartungsArtBeans);
	public void holeWartungsArt() throws SQLException, LagerException;
}
