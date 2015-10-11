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

import javax.swing.Icon;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.model.ArtikelModelKnotenBean;


public class BestandspackstueckModelKnotenBean extends ModelKnotenBean {
	public BestandspackstueckModelKnotenBean(BestandspackstueckBean bean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BESTANDSPACKSTUECK,null);
		setIBean(bean);
		bean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
//		if (getIBean()!=null)
//			return "Bestandspackstück " + getIBean().getId();
//		else return "Bestandspackstück " ;
		String b = "Bestandspackstück";
		if (getIBean()!=null){
			if (this.getVater() instanceof ArtikelModelKnotenBean){
				String p = getIBean().getPositionBean().toString();
				String e = getIBean().getPositionBean().getEbeneBean().toString();
				String s = getIBean().getPositionBean().getEbeneBean().getSaeuleBean().toString();
				String z = getIBean().getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().toString();
				String h = getIBean().getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getHalleBean().toString();
				b = h + " / " + z + "-" + s + "-" + e + "-" + p + ", Menge:"+getIBean().getMenge();
			}else{
				b = getIBean().getArtikelBean().getBezeichnung();
			}
			return b;
		}    
		else return b ;
	}
	
	@Override
	public BestandspackstueckBean getIBean() {
		return (BestandspackstueckBean)super.getIBean();
	}
	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_BESTANDSPACKSTUECK);	
	}

}
