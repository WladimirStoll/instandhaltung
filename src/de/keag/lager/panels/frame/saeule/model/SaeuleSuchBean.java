package de.keag.lager.panels.frame.saeule.model;

import de.keag.lager.core.ISuchBean;


/*
 * Diese Klasse wird benutzt, wann immer wir nach dem Artikel in der Datenbank suchen müssen.
 */
public class SaeuleSuchBean implements ISuchBean {
	private Integer nummer;

	public Integer getNummer() {
		if (nummer == null){
			nummer = 0;
		}
		return nummer;
	}

	public void setNummer(Integer nummer) {
		this.nummer = nummer;
	} 
}