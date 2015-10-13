package de.keag.lager.panels.frame.matchcode.wartungsart;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.WartungsArtBeanDB;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtSuchBean;


public class WartungsArtMCModel implements IWartungsArtMCModel{
	private WartungsArtBeanDB beanDB;
	private ArrayList<WartungsArtBean> beans;
	private ArrayList<WartungsArtBean> selectedBean;
	private WartungsArtSuchBean suchKriterien;
	private IWartungsArtMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public WartungsArtMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<WartungsArtBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<WartungsArtBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<WartungsArtBean> kopieBeans = new ArrayList<WartungsArtBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((WartungsArtBean)element);
		}
		
		WartungsArtBean leereBean = new WartungsArtBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<WartungsArtBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<WartungsArtBean>();
		}
		return selectedBean;
	}

	private WartungsArtBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new WartungsArtBeanDB();
		}
		return beanDB;
	}

	public WartungsArtSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new WartungsArtSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			WartungsArtSuchBean suchKriterien)  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		try {
			beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
			ArrayList<WartungsArtBean> kopieBeans = new ArrayList<WartungsArtBean> ();
			for(int i=0; i<beans.size(); i++){
				Bean element = beans.get(i);
				kopieBeans.add((WartungsArtBean)element);
			}
			
			WartungsArtBean leereBean = new WartungsArtBean();
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

	private void setSuchKriterien(WartungsArtSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IWartungsArtMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IWartungsArtMCBeobachter iMCBeobachter) {
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
//		ArrayList<WartungsArtBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			WartungsArtBean leereBean = new WartungsArtBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (WartungsArtBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (WartungsArtBeanDbLagerException e) {
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
