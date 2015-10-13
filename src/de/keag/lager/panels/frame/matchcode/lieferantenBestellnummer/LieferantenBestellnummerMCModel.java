package de.keag.lager.panels.frame.matchcode.lieferantenBestellnummer;

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
import de.keag.lager.core.fehler.LieferantenBestellnummerBeanDbException;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.db.LieferantenBestellnummerBeanDB;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerSuchBean;


public class LieferantenBestellnummerMCModel implements ILieferantenBestellnummerMCModel{
	private LieferantenBestellnummerBeanDB beanDB;
	private ArrayList<LieferantenBestellnummerBean> beans;
	private ArrayList<LieferantenBestellnummerBean> selectedBean;
	private LieferantenBestellnummerSuchBean suchKriterien;
	private ILieferantenBestellnummerMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public LieferantenBestellnummerMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<LieferantenBestellnummerBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<LieferantenBestellnummerBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException {
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<LieferantenBestellnummerBean> kopieBeans = new ArrayList<LieferantenBestellnummerBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((LieferantenBestellnummerBean)element);
		}
		
		LieferantenBestellnummerBean leereBean = new LieferantenBestellnummerBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans );
		benachrichtigeBeobachter();
	}

	public ArrayList<LieferantenBestellnummerBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<LieferantenBestellnummerBean>();
		}
		return selectedBean;
	}

	private LieferantenBestellnummerBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new LieferantenBestellnummerBeanDB();
		}
		return beanDB;
	}

	public LieferantenBestellnummerSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new LieferantenBestellnummerSuchBean();
		}
		return suchKriterien;
	}

	public void holeBeansNachSuchKriterien(
			LieferantenBestellnummerSuchBean suchKriterien) throws SQLException  {
		getFehlerList().clear();

		//Suchkriteren werden übernommen(auch wenn leer)
		setSuchKriterien(suchKriterien);
		ArrayList<Bean> beans;
		beans = getBeanDB().selectAnhandSuchBean(getSuchKriterien(), 0, null);
		
		ArrayList<LieferantenBestellnummerBean> kopieBeans = new ArrayList<LieferantenBestellnummerBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((LieferantenBestellnummerBean)element);
		}
		
		LieferantenBestellnummerBean leereBean = new LieferantenBestellnummerBean();
		beans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	private void setSuchKriterien(LieferantenBestellnummerSuchBean suchKriterien) {
		this.suchKriterien = suchKriterien;
	}

	protected ILieferantenBestellnummerMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(ILieferantenBestellnummerMCBeobachter iMCBeobachter) {
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

	public void sucheLieferantenBestellnummerByArtikelId(Integer artikelId) throws LagerException {
		getFehlerList().clear();
		ArrayList<LieferantenBestellnummerBean> beans;
		try {
			beans = getBeanDB().sucheByArtikelId(artikelId);
			
			
//			LieferantenBestellnummerBean leereBean = new LieferantenBestellnummerBean();
//			beans.add(0,leereBean);
			setBeans(beans);
		} catch (LieferantenBestellnummerBeanDbException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (ArtikelBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (KatalogBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (MengenEinheitBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (HerstellerLieferantLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (KostenstelleBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		}
		benachrichtigeBeobachter();
	}

}
