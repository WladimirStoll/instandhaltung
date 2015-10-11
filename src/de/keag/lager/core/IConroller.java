package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;

public interface IConroller {

	/**
	 * Kontroller prüft sich selbst auf Fehler
	 * @return
	 */
	public ArrayList<Fehler> pruefeDich();
	/*
	 * Controller setzt sich selbst als Aktiven Kontroller
	 */
	public ArrayList<Fehler> setzeNeuenAktivenController();
	public void setStandardFocusPosition();
	public void setBorder(Boolean aktiv);
//	public void erstelleNeuenSatz();
//	public void showMenue();
//	public void setGewaehlteZeile(int gewaehlteZeilenNummer);
//	public void speichereSatz(Bean bean);
//	public void abbrechenSatz(Bean bean);
//	public ArrayList<Fehler> pruefeAktuellenSatz();
	public void aktualisiereAnzeige();
}
