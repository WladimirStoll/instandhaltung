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
package de.keag.lager.panels.frame.matchcode.etage;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.EtageBeanDB;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.etage.model.EtageSuchBean;


public class EtageMCModel implements IEtageMCModel{
	private EtageBeanDB beanDB;
	private ArrayList<EtageBean> beans;
	private ArrayList<EtageBean> selectedBean;
	private EtageSuchBean suchKriterien;
	private IEtageMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public EtageMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<EtageBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<EtageBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		ArrayList<EtageBean> etageBeans = new ArrayList<EtageBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			etageBeans.add((EtageBean)element);
		}
		
//		for (Bean element : beans) {
//			etageBeans.add((EtageBean)element);
//		}
		
		EtageBean leereBean = new EtageBean();
		etageBeans.add(0,leereBean);
		setBeans(etageBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<EtageBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<EtageBean>();
		}
		return selectedBean;
	}

	private EtageBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new EtageBeanDB();
		}
		return beanDB;
	}

	public EtageSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new EtageSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			EtageSuchBean suchKriterien) throws SQLException, LagerException  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
		
		ArrayList<EtageBean> etageBeans = new ArrayList<EtageBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			etageBeans.add((EtageBean)element);
		}
		
		EtageBean leereBean = new EtageBean();
		beans.add(0,leereBean);
		setBeans(etageBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(EtageSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IEtageMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IEtageMCBeobachter iMCBeobachter) {
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
//		ArrayList<EtageBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			EtageBean leereBean = new EtageBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (EtageBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (EtageBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (EtageBeanDbLagerException e) {
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
