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
package de.keag.lager.panels.frame.matchcode.bestandspackstueck;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.BestandspackstueckBeanDB;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class BestandspackstueckMCModel implements IBestandspackstueckMCModel{
	private BestandspackstueckBeanDB beanDB;
	private ArrayList<BestandspackstueckBean> beans;
	private ArrayList<BestandspackstueckBean> selectedBean;

	public BestandspackstueckMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<BestandspackstueckBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<BestandspackstueckBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten(PositionBean positionBean) throws SQLException, LagerException {
		ArrayList<Bean> beans = ((BestandspackstueckBeanDB)getBeanDB()).sucheAufDerPosition(positionBean);
		
		ArrayList<BestandspackstueckBean> kopieBeans = new ArrayList<BestandspackstueckBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((BestandspackstueckBean)element);
		}
		
		
		BestandspackstueckBean leereBean = new BestandspackstueckBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
	}

	public ArrayList<BestandspackstueckBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<BestandspackstueckBean>();
		}
		return selectedBean;
	}

	public BestandspackstueckBean getAnhandName(String name) {
		Log.log().severe("Fehler:");
		return null;
//		return getBeanDB().getAnhandName(name);
	}

	private BestandspackstueckBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new BestandspackstueckBeanDB();
		}
		return beanDB;
	}


}
