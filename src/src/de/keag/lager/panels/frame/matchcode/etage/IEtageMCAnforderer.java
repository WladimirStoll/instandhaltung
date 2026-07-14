package de.keag.lager.panels.frame.matchcode.etage;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.etage.model.EtageBean;

public interface IEtageMCAnforderer {
	public void selectedEtageBeans(ArrayList<EtageBean> etageBeans);
	public void holeEtage() throws SQLException, LagerException ;
}
