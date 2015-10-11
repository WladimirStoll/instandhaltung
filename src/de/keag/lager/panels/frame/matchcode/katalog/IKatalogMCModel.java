package de.keag.lager.panels.frame.matchcode.katalog;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.katalog.KatalogSuchBean;


public interface IKatalogMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<KatalogBean> getBeans();
	public KatalogSuchBean getSuchKriterien();
}
