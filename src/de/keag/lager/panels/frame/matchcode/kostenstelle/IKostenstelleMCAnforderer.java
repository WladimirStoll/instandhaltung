package de.keag.lager.panels.frame.matchcode.kostenstelle;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;

public interface IKostenstelleMCAnforderer {
	public void selectedKostenstelleBeans(ArrayList<KostenstelleBean> kostenstelleBeans);
	void holeKostenstelle() throws SQLException, LagerException;
}
