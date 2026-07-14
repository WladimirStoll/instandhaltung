package de.keag.lager.panels.frame.etage.model;

import de.keag.lager.core.ISuchBean;


/*
 * Diese Klasse wird benutzt, wann immer wir nach der Halle in der Datenbank suchen müssen.
 */
public class EtageSuchBean implements ISuchBean {
	private String name;

	public String getName() {
		if (name==null){
			name = "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
