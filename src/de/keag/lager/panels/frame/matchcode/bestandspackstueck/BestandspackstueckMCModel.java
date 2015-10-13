package de.keag.lager.panels.frame.matchcode.bestandspackstueck;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.BestandspackstueckBeanDB;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class BestandspackstueckMCModel implements IBestandspackstueckMCModel{
	private BestandspackstueckBeanDB beanDB;
	private ArrayList<BestandspackstueckBean> beans;
	private ArrayList<BestandspackstueckBean> selectedBean;

	public BestandspackstueckMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<BestandspackstueckBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<BestandspackstueckBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten(PositionBean positionBean) throws SQLException, LagerException {
		ArrayList<Bean> beans = ((BestandspackstueckBeanDB)getBeanDB()).sucheAufDerPosition(positionBean);
		
		ArrayList<BestandspackstueckBean> kopieBeans = new ArrayList<BestandspackstueckBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((BestandspackstueckBean)element);
		}
		
		
		BestandspackstueckBean leereBean = new BestandspackstueckBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
	}

	public ArrayList<BestandspackstueckBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<BestandspackstueckBean>();
		}
		return selectedBean;
	}

	public BestandspackstueckBean getAnhandName(String name) {
		Log.log().severe("Fehler:");
		return null;
//		return getBeanDB().getAnhandName(name);
	}

	private BestandspackstueckBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new BestandspackstueckBeanDB();
		}
		return beanDB;
	}


}
