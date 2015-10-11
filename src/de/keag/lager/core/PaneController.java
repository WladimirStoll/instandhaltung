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
package de.keag.lager.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;

abstract public class PaneController {
	
	private PaneController vorherigerPaneController = null;
	
	private IConroller iAktiverConroller = null;
	
	public IConroller getiAktiverConroller() {
		return iAktiverConroller;
	}

	// * Ein neuer Artiver Kontroller kann gesetzt werden,
	public ArrayList<Fehler> setzeNeuenAktivenController(IConroller iNeuerAktiverConroller){
		Log.log().finest("PaneController: setzeNeuenAktivenController vor ");
		if (getiAktiverConroller()==null){
			setiAktiverConroller(iNeuerAktiverConroller);
			Log.log().finest("PaneController: setzeNeuenAktivenController nach 1 ");
			return new ArrayList<Fehler>();
		}else{
			if (getiAktiverConroller()!=iNeuerAktiverConroller){
				Log.log().finest("PaneController: getiAktiverConroller().pruefeDich() vor ");
				ArrayList<Fehler> errors = getiAktiverConroller().pruefeDich();
				Log.log().finest("PaneController: getiAktiverConroller().pruefeDich() nach");
				if((errors==null)||(errors.size()==0)){
					getiAktiverConroller().setBorder(false);//fokus-Anzeige wegnehmen
					setiAktiverConroller(iNeuerAktiverConroller);
					iNeuerAktiverConroller.setBorder(true);//fokus-Anzeige aktivieren
					Log.log().finest("PaneController: setzeNeuenAktivenController nach 2 ");
					return new ArrayList<Fehler>();
				}else{
					//Defaultfocus- Komponente setzen
					getiAktiverConroller().setStandardFocusPosition();
					Log.log().finest("PaneController: setzeNeuenAktivenController nach 3 ");
					return errors;
				}
			}else{
				Log.log().finest("PaneController: setzeNeuenAktivenController nach 4 ");
				return new ArrayList<Fehler>();
			}
		}
	}
	
	public void loescheAktivenController(){
		setiAktiverConroller(null);
	}
	
//	// * Ein neuer Artiver Kontroller kann gesetzt werden,
//	@Override
//	public ArrayList<Fehler> setzeNeuenAktivenController(IConroller iNeuerAktiverConroller){
//		if (getiAktiverConroller()==null){
//			setiAktiverConroller(iNeuerAktiverConroller);
//			return new ArrayList<Fehler>();
//		}else{
//			if (getiAktiverConroller()!=iNeuerAktiverConroller){
//				ArrayList<Fehler> errors = getiAktiverConroller().pruefeDich();
//				if((errors==null)||(errors.size()==0)){
//					getiAktiverConroller().setBorder(false);//fokus-Anzeige wegnehmen
//					setiAktiverConroller(iNeuerAktiverConroller);
//					iNeuerAktiverConroller.setBorder(true);//fokus-Anzeige aktivieren
//					return new ArrayList<Fehler>();
//				}else{
//					//Defaultfocus- Komponente setzen
//					getiAktiverConroller().setStandardFocusPosition();
//					return errors;
//				}
//			}else{
//				return new ArrayList<Fehler>();
//			}
//		}
//	}
	
	
	protected void setiAktiverConroller(IConroller iAktiverConroller) {
		this.iAktiverConroller = iAktiverConroller;
	}

	abstract protected void benachrichtigeBeobachter(); 
	
	abstract public void showView();
	
	final public void showView(PaneController vorherigerPaneController){
		this.vorherigerPaneController = vorherigerPaneController;
		showView();
	}

	final public boolean showVorigesView(){
		if (this.vorherigerPaneController != null){
			this.vorherigerPaneController.showView();
			this.vorherigerPaneController = null;
			return true;
		}
		return false;
	}
	
//	abstract public void setGewaehlteZeile(int gewaehlteZeilenNummer);
//	@Override
	public void setGewaehlteZeile(int gewaehlteZeilenNummer){
		ModelKnotenBean modelKnotenBean = getIModel().selectKnoten(gewaehlteZeilenNummer);
		if (modelKnotenBean != null){
			selectKnoten(modelKnotenBean); //Details sollen wieder angezeigt werden.
		}
	}

	abstract public void erstelleNeuenSatz() ;

	abstract public void speichereSatz(Bean bean);

	abstract public void abbrechenSatz(Bean bean);
	
	public void druckeBericht(Bean bean, Map<String, String> druckParameter) throws LagerException, SQLException, Exception{
		Log.log().severe("Hook ist nicht implemenitiert");
	};
	
//	public void verschickeSatz(Bean bean) throws LagerException, SQLException, Exception{
//		Log.log().severe("Hook ist nicht implemenitiert");
//	};

	abstract public void benachrichtigeListBeobachter();

	abstract public ArrayList<Fehler> pruefeAktuellenSatz() ;
	
	abstract public SuchView getSuchView();

	abstract public void suchen(ISuchBean iSuchBean) ;

	abstract public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) ;

	abstract public void benachrichtigeTreeBeobachter() ;

	abstract public void addDetailsBeobachter(DetailsView detailsView);
	
	abstract public void addListBeobachter(ListView listView);

	abstract public IModel getIModel();

	abstract public void ausgewaehlterKnotenIstGeandert() ;

	abstract public void addSuchBeobachter(ISuchBeobachter iSuchBeobachter);

	abstract public void addTreeBeobachter(ITreeBeobachter iTreeBeobachter);

	abstract public TreeView getTreePane();

	abstract protected ListView getListPane();

	abstract public void loescheSatz(Bean bean);
	
	public void loeschePosition(Bean bean){
		
	}
}
