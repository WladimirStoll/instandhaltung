package de.keag.lager.panels.frame.matchcode.email;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.EmailBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.db.EmailBeanDB;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.email.EmailSuchBean;


public class EmailMCModel implements IEmailMCModel{
	private EmailBeanDB beanDB;
	private ArrayList<EmailBean> beans;
	private ArrayList<EmailBean> selectedBean;
	private EmailSuchBean suchKriterien;
	private IEmailMCBeobachter iMCBeobachter;
	private ArrayList<Fehler> fehlerList;
	

	public EmailMCModel(){ //Konstuktor
		super();
	}

	@Override
	public ArrayList<EmailBean> getBeans() { //Schnittstelle zu Model
		return beans;
	}

	private void setBeans(ArrayList<EmailBean> beans) {
		this.beans = beans;
	}
	
	public void holeDaten() throws SQLException, LagerException{
		getFehlerList().clear();

		ArrayList<Bean> beans = getBeanDB().sucheAlle();
		
		ArrayList<EmailBean> kopieBeans = new ArrayList<EmailBean> ();
		for(int i=0; i<beans.size(); i++){
			Bean element = beans.get(i);
			kopieBeans.add((EmailBean)element);
		}
		
		
		EmailBean leereBean = new EmailBean();
		kopieBeans.add(0,leereBean);
		setBeans(kopieBeans);
		benachrichtigeBeobachter();
	}

	public ArrayList<EmailBean> getSelectedBeans() {
		if (selectedBean==null){
			selectedBean = new ArrayList<EmailBean>();
		}
		return selectedBean;
	}

	private EmailBeanDB getBeanDB() {
		if(beanDB==null){
			beanDB = new EmailBeanDB();
		}
		return beanDB;
	}

	public EmailSuchBean getSuchKriterien() {
		if (suchKriterien==null){
			suchKriterien = new EmailSuchBean();
		}
		return suchKriterien;
	}

	protected IEmailMCBeobachter getiMCBeobachter() {
		return iMCBeobachter;
	}

	protected void setiMCBeobachter(IEmailMCBeobachter iMCBeobachter) {
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

	public void holeBeansNachSuchKriterien(EmailSuchBean suchKriterien2) {
		// TODO Auto-generated method stub
		
	}


}
