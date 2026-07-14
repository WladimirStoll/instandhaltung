package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;

public interface IModel {

	public ModelKnotenBean getSelectedListModelKnotenBean(); //Bestellanforderung
	public int getSelectedListModelKnotenBeanLaufendeNummer();
	public ModelKnotenBean getAktiveTreeModelBean();
	public ArrayList<ModelKnotenBean> getModelBeanList();
	public ArrayList<Fehler> getFehlerList();
	public ISuchBean getiSuchBean();
	public ModelKnotenBean selectKnoten(int gewaehlteZeilenNummer);

	
}
