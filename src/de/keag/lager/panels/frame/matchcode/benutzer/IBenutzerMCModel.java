package de.keag.lager.panels.frame.matchcode.benutzer;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;


public interface IBenutzerMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<BenutzerBean> getBeans();
	public BenutzerSuchBean getSuchKriterien();
}
