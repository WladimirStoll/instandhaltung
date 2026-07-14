package de.keag.lager.panels.frame.matchcode.zugriffsrecht;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtSuchBean;


public interface IZugriffsrechtMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<ZugriffsrechtBean> getBeans();
	public ZugriffsrechtSuchBean getSuchKriterien();
}
