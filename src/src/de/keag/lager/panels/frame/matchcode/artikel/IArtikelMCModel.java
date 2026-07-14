package de.keag.lager.panels.frame.matchcode.artikel;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.ArtikelSuchBean;


public interface IArtikelMCModel {
	public ArrayList<ArtikelBean> getBeans();
	public ArtikelSuchBean getSuchKriterien();
	public ArrayList<Fehler> getFehlerList() ;
}
