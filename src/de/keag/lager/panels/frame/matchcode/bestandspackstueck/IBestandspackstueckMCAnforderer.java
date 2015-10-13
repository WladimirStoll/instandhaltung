package de.keag.lager.panels.frame.matchcode.bestandspackstueck;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

public interface IBestandspackstueckMCAnforderer {
	public void selectedBestandspackstueckBeans(ArrayList<BestandspackstueckBean> BestandspackstueckBeans);
	void holeBestandspackstueck(PositionBean positionBean) throws SQLException, LagerException;
}
