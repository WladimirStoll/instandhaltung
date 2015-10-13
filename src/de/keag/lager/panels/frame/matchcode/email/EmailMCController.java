package de.keag.lager.panels.frame.matchcode.email;

import java.sql.SQLException;

import de.keag.lager.core.fehler.EmailBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.email.EmailSuchBean;

public class EmailMCController {
	private static EmailMCController mCController = null;
	private EmailMCView mCView;
	private EmailMCModel mCModel;
	private IEmailMCAnforderer imCAnforderer;
	
	private EmailMCController() {
		super();
	}
	
	public static EmailMCController getOneInstance(){
		if(mCController==null){
			mCController = new EmailMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IEmailMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private EmailMCView getMCView() {
		if (mCView==null){
			mCView = new EmailMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private EmailMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new EmailMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(EmailBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedEmailBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IEmailMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IEmailMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(EmailSuchBean suchKriterien) {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

//	public void sucheEmailByArtikelId(Integer artikelId,
//			IEmailMCAnforderer iMCAnforderer) {
//		setiMCAnforderer(iMCAnforderer);
//		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
//		getMCModel().sucheEmailByArtikelId(artikelId);
//		getMCView().setVisible(true); //Fenster anzeigen.
//	}
	
}
