package de.keag.lager.panels.frame.matchcode.zugriffsrecht;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.ZugriffsrechtBeanDB;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtSuchBean;


public class ZugriffsrechtMCModel implements IZugriffsrechtMCModel{
	private ZugriffsrechtBeanDB beanDB;
	private ArrayList<ZugriffsrechtBean> beans;
	private ArrayList<ZugriffsrechtBean> selectedBean;
	private ZugriffsrechtSuchBean suchKriterien;
	private IZugriffsrechtMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public ZugriffsrechtMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<ZugriffsrechtBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<ZugriffsrechtBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		ArrayList<ZugriffsrechtBean> kopieBeans = new ArrayList<ZugriffsrechtBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((ZugriffsrechtBean)element);
		}
		
//		for (Bean element : beans) {
//			abteilungBeans.add((ZugriffsrechtBean)element);
//		}
		
		ZugriffsrechtBean leereBean = new ZugriffsrechtBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<ZugriffsrechtBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<ZugriffsrechtBean>();
		}
		return selectedBean;
	}

	private ZugriffsrechtBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new ZugriffsrechtBeanDB();
		}
		return beanDB;
	}

	public ZugriffsrechtSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new ZugriffsrechtSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			ZugriffsrechtSuchBean suchKriterien) throws SQLException, LagerException  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
		
		ArrayList<ZugriffsrechtBean> abteilungBeans = new ArrayList<ZugriffsrechtBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			abteilungBeans.add((ZugriffsrechtBean)element);
		}
		
		ZugriffsrechtBean leereBean = new ZugriffsrechtBean();
		beans.add(0,leereBean);
		setBeans(abteilungBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(ZugriffsrechtSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IZugriffsrechtMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IZugriffsrechtMCBeobachter iMCBeobachter) {
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
//		ArrayList<ZugriffsrechtBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			ZugriffsrechtBean leereBean = new ZugriffsrechtBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (ZugriffsrechtBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ZugriffsrechtBeanDbLagerException e) {
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
