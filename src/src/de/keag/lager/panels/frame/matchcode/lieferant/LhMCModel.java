package de.keag.lager.panels.frame.matchcode.lieferant;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.LhBeanDB;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;



public class LhMCModel implements ILhMCModel{
	private LhBeanDB lhBeanDB;
	
	private ArrayList<LhBean> lhBeans;
	private ArrayList<LhBean> lhSelectedBeans;
	private ILhMCBeobachter iLieferandMCBeobachter;

	public LhMCModel(){
		super();
	}
	
	public void addBeobachter(ILhMCBeobachter iLieferandMCBeobachter) {
		setiLieferandMCBeobachter(iLieferandMCBeobachter);
		
	}

	private ILhMCBeobachter getILhMCBeobachter() {
		return iLieferandMCBeobachter;
	}

	private void setiLieferandMCBeobachter(
			ILhMCBeobachter iLieferandMCBeobachter) {
		this.iLieferandMCBeobachter = iLieferandMCBeobachter;
	}
	
	private void benachrichteBeobachter(){
		if (getILhMCBeobachter()!=null){
			getILhMCBeobachter().zeichneDich();
		}
	}

	@Override
	public ArrayList<LhBean> getLhBeans() {
		return lhBeans;
	}

	private void setLhBeans(ArrayList<LhBean> lhBeans) {
		this.lhBeans = lhBeans;
		benachrichteBeobachter();
	}
	
	public void holeDaten() throws SQLException, LagerException {
		ArrayList<Bean> beans = getLhBeanDB().sucheAlle();
		
		ArrayList<LhBean> kopieBeans = new ArrayList<LhBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((LhBean)element);
		}
		
		
		LhBean leereBean = new LhBean();
		kopieBeans.add(0,leereBean);
		setLhBeans(kopieBeans);
	}

	private LhBeanDB getLhBeanDB() {
		if(lhBeanDB==null){
			lhBeanDB = new LhBeanDB();
		}
		return lhBeanDB;
	}

	public ArrayList<LhBean> getLhSelectedBeans() {
		if (lhSelectedBeans==null){
			lhSelectedBeans = new ArrayList<LhBean>();
		}
		return lhSelectedBeans;
	}

	public LhBean getLhAnhandName(String lhName) throws SQLException, LagerException {
		return getLhBeanDB().getLhAnhandName(lhName);
	}


}
