package de.keag.lager.panels.frame.matchcode.wartungstyp;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypSuchBean;


public interface IWartungsTypMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<WartungsTypBean> getBeans();
	public WartungsTypSuchBean getSuchKriterien();
}
