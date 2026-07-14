package de.keag.lager.panels.frame.matchcode.baugruppe;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;


public interface IBaugruppeMCModel {
	public ArrayList<Fehler> getFehlerList() ;
	public ArrayList<BaugruppeBean> getBeans();
	public BaugruppeSuchBean getSuchKriterien();
}
