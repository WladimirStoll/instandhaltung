package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;

abstract public class SuchController extends Controller {

	@Override
	public void aktualisiereAnzeige() {
		//
	}

	@Override
	public void setStandardFocusPosition() {
		getSuchView().setStandardFocusPosition();
	}

	
	public void setBorder(Boolean aktiv) {
		getSuchView().setBorder(aktiv);
	}

	abstract public SuchView getSuchView();

	/**
	 * Delegation an Hauptkontroller
	 * @param iSuchBean
	 */
	public void suchen(ISuchBean iSuchBean) {
		//neue Daten werden gesucht
		getPaneController().suchen(iSuchBean);
		//Auf erste Zeile setzen
		getPaneController().setGewaehlteZeile(0);
	}

}
