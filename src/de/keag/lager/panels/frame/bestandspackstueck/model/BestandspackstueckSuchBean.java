/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
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
