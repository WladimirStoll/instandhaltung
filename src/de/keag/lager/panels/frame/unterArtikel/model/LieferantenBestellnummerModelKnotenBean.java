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
package de.keag.lager.panels.frame.unterArtikel.model;

import javax.swing.Icon;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;


public class LieferantenBestellnummerModelKnotenBean extends ModelKnotenBean {

	/**
	 * Hauptkonsturor
	 * @param lieferantenBestellnummerBean
	 * @throws BenutzerOberflacheLagerException
	 */
	public LieferantenBestellnummerModelKnotenBean(LieferantenBestellnummerBean lieferantenBestellnummerBean,ModelKnotenBean vater) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.LIEFERANT_BESTELLNUMMER,vater);
		setIBean(lieferantenBestellnummerBean);
		lieferantenBestellnummerBean.setModelKnotenBean(this);
	}

//	public BaugruppeArtikelModelKnotenBean(ModelKnotenBean vater) {
//		super(ModelKnotenTyp.BAUGRUPPE_ARTIKEL,vater);
//	}
	

	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getBestellnummer()!=null){
				return "" + getIBean().getKatalogBean().getName();
			}else{
				return "";
			}
		else return "" ;
	}
	
	@Override
	public LieferantenBestellnummerBean getIBean() {
		return (LieferantenBestellnummerBean)super.getIBean();
	}

	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_LIEFERANT_HERSTELLER_KATALOG);	
	}
	
//	@Override
//	public boolean istGesamterInhaltGeaendert() {
//		if (((LieferantenBestellnummerBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
//			return true;
//		}
//		for (int i = 0; i < getKinderList().size(); i++) {
//			ModelKnotenBean kind = getKinderList().get(i);
//			if (kind.istGesamterInhaltGeaendert()){
//				return true;
//			}
//		}
//		return false;
//		
//	}
}
