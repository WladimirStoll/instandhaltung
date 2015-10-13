package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;

abstract public class TreeController extends Controller {

	@Override
	public void aktualisiereAnzeige() {
	}

	/**
	 * TreeController hat nie Fehler 
	 */
	@Override
	public ArrayList<Fehler> pruefeDich() {
		return new ArrayList<Fehler>();
	}
	

	@Override
	public void setBorder(Boolean aktiv) {
		getTreeView().setBorder(aktiv);
	}

	/**
	 * der im Tree vom Benutzer gewählte/angeklickte Knoten wird weitergegeben.
	 * @param selectedModelKnotenBean
	 */
	public void selectKnoten(ModelKnotenBean  selectedModelKnotenBean) {
		getPaneController().selectKnoten(selectedModelKnotenBean);
	}
	
	@Override
	public void setStandardFocusPosition() {
		getPaneController().benachrichtigeTreeBeobachter();
	}

	abstract public TreeView getTreeView();

	
}
