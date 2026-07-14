package de.keag.lager.panels.frame.matchcode.kostenstelle;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.KostenstelleBeanDB;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;

public class KostenstelleMCModel implements IKostenstelleMCModel{
	private KostenstelleBeanDB beanDB;
	private ArrayList<KostenstelleBean> beans;
	private ArrayList<KostenstelleBean> selectedBean;

	public KostenstelleMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<KostenstelleBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<KostenstelleBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException {
		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<KostenstelleBean> kopieBeans = new ArrayList<KostenstelleBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((KostenstelleBean)element);
		}
		
		
		KostenstelleBean leereBean = new KostenstelleBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
	}

	public ArrayList<KostenstelleBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<KostenstelleBean>();
		}
		return selectedBean;
	}

	public KostenstelleBean getAnhandName(String name) {
		return getBeanDB().getAnhandName(name);
	}

	private KostenstelleBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new KostenstelleBeanDB();
		}
		return beanDB;
	}


}
