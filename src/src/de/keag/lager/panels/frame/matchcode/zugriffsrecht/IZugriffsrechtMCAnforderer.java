package de.keag.lager.panels.frame.matchcode.zugriffsrecht;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;

public interface IZugriffsrechtMCAnforderer {
	public void selectedZugriffsrechtBeans(ArrayList<ZugriffsrechtBean> abteilungBeans);
	public void holeZugriffsrecht() throws SQLException, LagerException ;
}
