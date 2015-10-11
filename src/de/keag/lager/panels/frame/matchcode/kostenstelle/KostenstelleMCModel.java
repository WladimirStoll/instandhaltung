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
package de.keag.lager.panels.frame.matchcode.kostenstelle;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.KostenstelleBeanDB;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;

public class KostenstelleMCModel implements IKostenstelleMCModel{
	private KostenstelleBeanDB beanDB;
	private ArrayList<KostenstelleBean> beans;
	private ArrayList<KostenstelleBean> selectedBean;

	public KostenstelleMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<KostenstelleBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<KostenstelleBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException {
		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<KostenstelleBean> kopieBeans = new ArrayList<KostenstelleBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((KostenstelleBean)element);
		}
		
		
		KostenstelleBean leereBean = new KostenstelleBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
	}

	public ArrayList<KostenstelleBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<KostenstelleBean>();
		}
		return selectedBean;
	}

	public KostenstelleBean getAnhandName(String name) {
		return getBeanDB().getAnhandName(name);
	}

	private KostenstelleBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new KostenstelleBeanDB();
		}
		return beanDB;
	}


}
