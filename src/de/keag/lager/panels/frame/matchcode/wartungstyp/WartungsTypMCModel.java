package de.keag.lager.panels.frame.matchcode.wartungstyp;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.WartungsTypBeanDB;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypSuchBean;


public class WartungsTypMCModel implements IWartungsTypMCModel{
	private WartungsTypBeanDB beanDB;
	private ArrayList<WartungsTypBean> beans;
	private ArrayList<WartungsTypBean> selectedBean;
	private WartungsTypSuchBean suchKriterien;
	private IWartungsTypMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public WartungsTypMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<WartungsTypBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<WartungsTypBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<WartungsTypBean> kopieBeans = new ArrayList<WartungsTypBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((WartungsTypBean)element);
		}
		
		WartungsTypBean leereBean = new WartungsTypBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<WartungsTypBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<WartungsTypBean>();
		}
		return selectedBean;
	}

	private WartungsTypBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new WartungsTypBeanDB();
		}
		return beanDB;
	}

	public WartungsTypSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new WartungsTypSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			WartungsTypSuchBean suchKriterien)  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		try {
			beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
			ArrayList<WartungsTypBean> kopieBeans = new ArrayList<WartungsTypBean> ();
			for(int i=0; i<beans.size(); i++){
				Bean element = beans.get(i);
				kopieBeans.add((WartungsTypBean)element);
			}
			
			WartungsTypBean leereBean = new WartungsTypBean();
			beans.add(0,leereBean);
			setBeans(kopieBeans);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getFehlerList().add(new Fehler(e.getMessage()));
		} catch (LagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getFehlerList().add(new Fehler(e.getMessage()));
		}
		
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(WartungsTypSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IWartungsTypMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IWartungsTypMCBeobachter iMCBeobachter) {
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
//		ArrayList<WartungsTypBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			WartungsTypBean leereBean = new WartungsTypBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (WartungsTypBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (WartungsTypBeanDbLagerException e) {
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
