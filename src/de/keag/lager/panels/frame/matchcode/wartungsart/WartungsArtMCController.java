/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
package de.keag.lager.panels.frame.matchcode.wartungsart;

import java.sql.SQLException;

import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtSuchBean;

public class WartungsArtMCController {
	private static WartungsArtMCController mCController = null;
	private WartungsArtMCView mCView;
	private WartungsArtMCModel mCModel;
	private IWartungsArtMCAnforderer imCAnforderer;
	
	private WartungsArtMCController() {
		super();
	}
	
	public static WartungsArtMCController getOneInstance(){
		if(mCController==null){
			mCController = new WartungsArtMCController();
			mCController.getMCView() ;
		}
		return mCController;
	}
	
	public void holeAlleBeans(IWartungsArtMCAnforderer iMCAnforderer) throws SQLException, LagerException{
		setiMCAnforderer(iMCAnforderer);
		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
		getMCModel().holeDaten(); //Datenbank neu abfragen.
		getMCView().setVisible(true); //Fenster anzeigen.
	}
	
	private WartungsArtMCView getMCView() {
		if (mCView==null){
			mCView = new WartungsArtMCView(Run.getOneInstance().getMainFrame(),this,getMCModel());
			getMCModel().setiMCBeobachter(mCView);
		}
		return mCView;
	}
	
	private WartungsArtMCModel getMCModel() {
		if(mCModel==null){
			mCModel = new WartungsArtMCModel();
		}
		return mCModel;
	}
	
	protected void setGewaehlteBean(WartungsArtBean bean) {
		getMCModel().getSelectedBeans().clear();
		getMCModel().getSelectedBeans().add(bean);
	}
	
	protected void fensterIstGeschlossen() {
		getiMCAnforderer().selectedWartungsArtBeans(getMCModel().getSelectedBeans());
		setiMCAnforderer(null); //jetzt ist uns der Anforderer egal.
	}
	
	private IWartungsArtMCAnforderer getiMCAnforderer() {
		return imCAnforderer;
	}
	
	private void setiMCAnforderer(IWartungsArtMCAnforderer iMCAnforderer) {
		this.imCAnforderer = iMCAnforderer;
	}

	//Der Benutzer möchte nur bestimmte Artikel im Suchfenster anzeigen lassen
	protected void holeBeansNachSuchKriterien(WartungsArtSuchBean suchKriterien) {
		getMCModel().holeBeansNachSuchKriterien(suchKriterien);
		
	}

//	public void sucheWartungsArtByArtikelId(Integer artikelId,
//			IWartungsArtMCAnforderer iMCAnforderer) {
//		setiMCAnforderer(iMCAnforderer);
//		getMCModel().getSelectedBeans().clear(); //gewählte Lieferanten löschen
//		getMCModel().sucheWartungsArtByArtikelId(artikelId);
//		getMCView().setVisible(true); //Fenster anzeigen.
//	}
	
}
