package de.keag.lager.panels.frame.matchcode.wartungsart;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtSuchBean;


public interface IWartungsArtMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<WartungsArtBean> getBeans();
	public WartungsArtSuchBean getSuchKriterien();
}
