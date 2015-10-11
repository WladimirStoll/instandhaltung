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
package de.keag.lager.panels.frame.matchcode.mengeneinheit;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.fehler.MengeneinheitBeanDbException;
import de.keag.lager.db.MengenEinheitBeanDB;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitSuchBean;


public class MengeneinheitMCModel implements IMengeneinheitMCModel{
	private MengenEinheitBeanDB beanDB;
	private ArrayList<MengenEinheitBean> beans;
	private ArrayList<MengenEinheitBean> selectedBean;
	private MengenEinheitSuchBean suchKriterien;
	private IMengeneinheitMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public MengeneinheitMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<MengenEinheitBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<MengenEinheitBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<MengenEinheitBean> kopieBeans = new ArrayList<MengenEinheitBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((MengenEinheitBean)element);
		}
		
		MengenEinheitBean leereBean = new MengenEinheitBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<MengenEinheitBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<MengenEinheitBean>();
		}
		return selectedBean;
	}

	private MengenEinheitBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new MengenEinheitBeanDB();
		}
		return beanDB;
	}

	public MengenEinheitSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new MengenEinheitSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			MengenEinheitSuchBean suchKriterien)  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
		
		ArrayList<MengenEinheitBean> kopieBeans = new ArrayList<MengenEinheitBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((MengenEinheitBean)element);
		}
		
		MengenEinheitBean leereBean = new MengenEinheitBean();
		beans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(MengenEinheitSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IMengeneinheitMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IMengeneinheitMCBeobachter iMCBeobachter) {
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

//	public void sucheById(Integer beanId) {
//		getFehlerList().clear();
//		ArrayList<MengenEinheitBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			MengenEinheitBean leereBean = new MengenEinheitBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (MengeneinheitBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (MengenEinheitBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (HerstellerLieferantLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KostenstelleBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
//			e.printStackTrace();
//		}
//		benachrichtigeBeobachter();
//	}

}
