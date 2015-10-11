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
