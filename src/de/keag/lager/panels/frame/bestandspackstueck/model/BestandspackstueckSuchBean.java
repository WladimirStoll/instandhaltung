package de.keag.lager.panels.frame.bestandspackstueck.model;

import de.keag.lager.core.ISuchBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.position.model.PositionBean;


/*
 * Diese Klasse wird benutzt, wann immer wir nach dem Artikel in der Datenbank suchen müssen.
 */
public class BestandspackstueckSuchBean implements ISuchBean {
	private Integer nummer;
	private ArtikelBean artikelBean;
	private PositionBean positionBean;

	public Integer getNummer() {
		if (nummer == null){
			nummer = 0;
		}
		return nummer;
	}

	public void setNummer(Integer nummer) {
		this.nummer = nummer;
	}

	public ArtikelBean getArtikelBean() {
		if (artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		return artikelBean;
	}

	public void setArtikelBean(ArtikelBean artikelBean) {
		this.artikelBean = artikelBean;
	}

	public PositionBean getPositionBean() {
		if(positionBean==null){
			positionBean = new PositionBean();
		}
		return positionBean;
	}

	public void setPositionBean(PositionBean positionBean) {
		this.positionBean = positionBean;
	} 
}
