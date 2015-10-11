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
package de.keag.lager.panels.frame.matchcode.abteilung;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.AbteilungBeanDB;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.abteilung.AbteilungSuchBean;


public class AbteilungMCModel implements IAbteilungMCModel{
	private AbteilungBeanDB beanDB;
	private ArrayList<AbteilungBean> beans;
	private ArrayList<AbteilungBean> selectedBean;
	private AbteilungSuchBean suchKriterien;
	private IAbteilungMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public AbteilungMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<AbteilungBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<AbteilungBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		ArrayList<AbteilungBean> abteilungBeans = new ArrayList<AbteilungBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			abteilungBeans.add((AbteilungBean)element);
		}
		
//		for (Bean element : beans) {
//			abteilungBeans.add((AbteilungBean)element);
//		}
		
		AbteilungBean leereBean = new AbteilungBean();
		abteilungBeans.add(0,leereBean);
		setBeans(abteilungBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<AbteilungBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<AbteilungBean>();
		}
		return selectedBean;
	}

	private AbteilungBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new AbteilungBeanDB();
		}
		return beanDB;
	}

	public AbteilungSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new AbteilungSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			AbteilungSuchBean suchKriterien) throws SQLException, LagerException  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
		
		ArrayList<AbteilungBean> abteilungBeans = new ArrayList<AbteilungBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			abteilungBeans.add((AbteilungBean)element);
		}
		
		AbteilungBean leereBean = new AbteilungBean();
		beans.add(0,leereBean);
		setBeans(abteilungBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(AbteilungSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IAbteilungMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IAbteilungMCBeobachter iMCBeobachter) {
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
//		ArrayList<AbteilungBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			AbteilungBean leereBean = new AbteilungBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (AbteilungBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (AbteilungBeanDbLagerException e) {
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
