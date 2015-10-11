package de.keag.lager.panels.frame.matchcode.baugruppe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.BaugruppeBeanDB;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;


public class BaugruppeMCModel implements IBaugruppeMCModel{
	private BaugruppeBeanDB beanDB;
	private ArrayList<BaugruppeBean> beans;
	private ArrayList<BaugruppeBean> selectedBean;
	private BaugruppeSuchBean suchKriterien;
	private IBaugruppeMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public BaugruppeMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<BaugruppeBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<BaugruppeBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		ArrayList<BaugruppeBean> baugruppeBeans = new ArrayList<BaugruppeBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			getBeanDB().vervollstaendigeVater(((BaugruppeBean)element).getVaterBaugruppe());
			baugruppeBeans.add((BaugruppeBean)element);
		}
		
//		for (Bean element : beans) {
//			abteilungBeans.add((BaugruppeBean)element);
//		}
		
		Collections.sort(baugruppeBeans);
		
		BaugruppeBean leereBean = new BaugruppeBean();
		baugruppeBeans.add(0,leereBean);
		setBeans(baugruppeBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<BaugruppeBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<BaugruppeBean>();
		}
		return selectedBean;
	}

	private BaugruppeBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new BaugruppeBeanDB();
		}
		return beanDB;
	}

	public BaugruppeSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new BaugruppeSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			BaugruppeSuchBean suchKriterien) throws SQLException, LagerException  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0+1, null);
		
		ArrayList<BaugruppeBean> baugruppeBeans = new ArrayList<BaugruppeBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			getBeanDB().vervollstaendigeVater(((BaugruppeBean)element).getVaterBaugruppe());
			baugruppeBeans.add((BaugruppeBean)element);
		}
		
		Collections.sort(baugruppeBeans);
		
		BaugruppeBean leereBean = new BaugruppeBean();
		baugruppeBeans.add(0,leereBean);
		setBeans(baugruppeBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(BaugruppeSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected IBaugruppeMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IBaugruppeMCBeobachter iMCBeobachter) {
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
//		ArrayList<BaugruppeBean> beans;
//		try {
//			beans = getBeanDB().sucheById(beanId);
////			BaugruppeBean leereBean = new BaugruppeBean();
////			beans.add(0,leereBean);
//			setBeans(beans);
//		} catch (BaugruppeBeanDbException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (ArtikelBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (KatalogBeanDbLagerException e) {
//			getFehlerList().add(e.getFehler());
//			e.printStackTrace();
//		} catch (BaugruppeBeanDbLagerException e) {
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
