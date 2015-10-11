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
package de.keag.lager.panels.frame.matchcode.lieferant;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.LhBeanDB;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;



public class LhMCModel implements ILhMCModel{
	private LhBeanDB lhBeanDB;
	
	private ArrayList<LhBean> lhBeans;
	private ArrayList<LhBean> lhSelectedBeans;
	private ILhMCBeobachter iLieferandMCBeobachter;

	public LhMCModel(){
		super();
	}
	
	public void addBeobachter(ILhMCBeobachter iLieferandMCBeobachter) {
		setiLieferandMCBeobachter(iLieferandMCBeobachter);
		
	}

	private ILhMCBeobachter getILhMCBeobachter() {
		return iLieferandMCBeobachter;
	}

	private void setiLieferandMCBeobachter(
			ILhMCBeobachter iLieferandMCBeobachter) {
		this.iLieferandMCBeobachter = iLieferandMCBeobachter;
	}
	
	private void benachrichteBeobachter(){
		if (getILhMCBeobachter()!=null){
			getILhMCBeobachter().zeichneDich();
		}
	}

	@Override
	public ArrayList<LhBean> getLhBeans() {
		return lhBeans;
	}

	private void setLhBeans(ArrayList<LhBean> lhBeans) {
		this.lhBeans = lhBeans;
		benachrichteBeobachter();
	}
	
	public void holeDaten() throws SQLException, LagerException {
		ArrayList<Bean> beans = getLhBeanDB().sucheAlle();
		
		ArrayList<LhBean> kopieBeans = new ArrayList<LhBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((LhBean)element);
		}
		
		
		LhBean leereBean = new LhBean();
		kopieBeans.add(0,leereBean);
		setLhBeans(kopieBeans);
	}

	private LhBeanDB getLhBeanDB() {
		if(lhBeanDB==null){
			lhBeanDB = new LhBeanDB();
		}
		return lhBeanDB;
	}

	public ArrayList<LhBean> getLhSelectedBeans() {
		if (lhSelectedBeans==null){
			lhSelectedBeans = new ArrayList<LhBean>();
		}
		return lhSelectedBeans;
	}

	public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException {
		return getLhBeanDB().getLhAnhandName(lhName);
	}


}
