package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;

abstract public class Controller implements IConroller {

	@Override
	public void aktualisiereAnzeige() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBorder(Boolean aktiv) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setStandardFocusPosition() {
		// TODO Auto-generated method stub

	}

	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		return errors;
	}
	
	abstract public PaneController getPaneController();
	
	@Override
	public ArrayList<Fehler> setzeNeuenAktivenController() {
		return getPaneController().setzeNeuenAktivenController(this);
	}
	
	
}
