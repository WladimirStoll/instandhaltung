package de.keag.lager.panels.frame.matchcode.katalog;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.KatalogBeanDB;
import de.keag.lager.panels.frame.katalog.KatalogBean;
import de.keag.lager.panels.frame.katalog.KatalogSuchBean;


public class KatalogMCModel implements IKatalogMCModel{
	private KatalogBeanDB beanDB;
	private ArrayList<KatalogBean> beans;
	private ArrayList<KatalogBean> selectedBean;
	private KatalogSuchBean suchKriterien;
	private IKatalogMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public KatalogMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<KatalogBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<KatalogBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		ArrayList<KatalogBean> katalogBeans = new ArrayList<KatalogBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			katalogBeans.add((KatalogBean)element);
		}
		
//		for (Bean element : beans) {
//			katalogBeans.add((KatalogBean)element);
//		}
		
		KatalogBean leereBean = new KatalogBean();
		katalogBeans.add(0,leereBean);
		setBeans(katalogBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<KatalogBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<KatalogBean>();
		}
		return selectedBean;
	}

	private KatalogBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new KatalogBeanDB();
		}
		return beanDB;
	}

	public KatalogSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new KatalogSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			KatalogSuchBean suchKriterien) throws SQLException, LagerException  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
		
		ArrayList<KatalogBean> katalogBeans = new ArrayList<KatalogBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			katalogBeans.add((KatalogBean)element);
		}
		
		KatalogBean leereBean = new KatalogBean();
		beans.add(0,leereBean);
		setBeans(katalogBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(KatalogSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IKatalogMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IKatalogMCBeobachter iMCBeobachter) {
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
//		ArrayList<KatalogBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			KatalogBean leereBean = new KatalogBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (KatalogBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
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
