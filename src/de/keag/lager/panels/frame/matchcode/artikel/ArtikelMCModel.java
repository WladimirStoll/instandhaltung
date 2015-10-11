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
package de.keag.lager.panels.frame.matchcode.artikel;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.db.ArtikelBeanDB;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.ArtikelSuchBean;


public class ArtikelMCModel implements IArtikelMCModel{
	private ArtikelBeanDB beanDB;
	private ArrayList<ArtikelBean> beans;
	private ArrayList<ArtikelBean> selectedBean;
	private ArtikelSuchBean suchKriterien;
	private IArtikelMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public ArtikelMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<ArtikelBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<ArtikelBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException {
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<ArtikelBean> kopieBeans = new ArrayList<ArtikelBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((ArtikelBean)element);
		}
		
		ArtikelBean leereBean = new ArtikelBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<ArtikelBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<ArtikelBean>();
		}
		return selectedBean;
	}

	private ArtikelBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new ArtikelBeanDB();
		}
		return beanDB;
	}

	public ArtikelSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new ArtikelSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			ArtikelSuchBean suchKriterien)  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		ArrayList<ArtikelBean> artikelBeans;
		try {
			beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
			artikelBeans = new ArrayList<ArtikelBean>();
			for(int i = 0; i<beans.size();i++){
				artikelBeans.add((ArtikelBean)beans.get(i));
			}
			ArtikelBean leereBean = new ArtikelBean();
			beans.add(0,leereBean);
			setBeans(artikelBeans);
		} catch (KostenstelleBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (MengenEinheitBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (HerstellerLieferantLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		} catch (LagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		}
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(ArtikelSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IArtikelMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IArtikelMCBeobachter iMCBeobachter) {
		this.iMCBeobachter = iMCBeobachter;
	}

	private void benachrichtigeBeobachter(){
		if (getiMCBeobachter()!=null){
			getiMCBeobachter().zeichneDich();
		}
	}

	public ArrayList<Fehler> getFehlerList() {
		if (fehlerList == null) {
			fehlerList = new ArrayList<Fehler>();
		}
		return fehlerList;
	}

	public void setFehlerList(ArrayList<Fehler> fehlerList) {
		this.fehlerList = fehlerList;
	}

}
