package de.keag.lager.panels.frame.matchcode.benutzer;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.BenutzerBeanDB;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerSuchBean;


public class BenutzerMCModel implements IBenutzerMCModel{
	private BenutzerBeanDB beanDB;
	private ArrayList<BenutzerBean> beans;
	private ArrayList<BenutzerBean> selectedBean;
	private BenutzerSuchBean suchKriterien;
	private IBenutzerMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public BenutzerMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<BenutzerBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<BenutzerBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		ArrayList<BenutzerBean> abteilungBeans = new ArrayList<BenutzerBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			abteilungBeans.add((BenutzerBean)element);
		}
		
//		for (Bean element : beans) {
//			abteilungBeans.add((BenutzerBean)element);
//		}
		
		BenutzerBean leereBean = new BenutzerBean();
		abteilungBeans.add(0,leereBean);
		setBeans(abteilungBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<BenutzerBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<BenutzerBean>();
		}
		return selectedBean;
	}

	private BenutzerBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new BenutzerBeanDB();
		}
		return beanDB;
	}

	public BenutzerSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new BenutzerSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			BenutzerSuchBean suchKriterien) throws SQLException, LagerException  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
		
		ArrayList<BenutzerBean> abteilungBeans = new ArrayList<BenutzerBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			abteilungBeans.add((BenutzerBean)element);
		}
		
		BenutzerBean leereBean = new BenutzerBean();
		beans.add(0,leereBean);
		setBeans(abteilungBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(BenutzerSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IBenutzerMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IBenutzerMCBeobachter iMCBeobachter) {
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
//		ArrayList<BenutzerBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			BenutzerBean leereBean = new BenutzerBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (BenutzerBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (BenutzerBeanDbLagerException e) {
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
