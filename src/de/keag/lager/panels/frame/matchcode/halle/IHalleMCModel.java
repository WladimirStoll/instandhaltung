package de.keag.lager.panels.frame.matchcode.halle;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;


public interface IHalleMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<HalleBean> getBeans();
	public HalleSuchBean getSuchKriterien();
}
