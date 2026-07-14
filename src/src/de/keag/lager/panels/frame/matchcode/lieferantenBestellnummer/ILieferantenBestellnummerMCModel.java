package de.keag.lager.panels.frame.matchcode.lieferantenBestellnummer;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerSuchBean;


public interface ILieferantenBestellnummerMCModel {
	public ArrayList<LieferantenBestellnummerBean> getBeans();
	public LieferantenBestellnummerSuchBean getSuchKriterien();
	public ArrayList<Fehler> getFehlerList() ;
}
