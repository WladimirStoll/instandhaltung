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

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;

abstract public class DetailsView extends View implements IDetailsBeobachter, KeyListener{
	
	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:

	public DetailsView() {
		super();
	}

	abstract public ArrayList<Fehler> pruefeDich();

	abstract public void setStandardFocusPosition() ;

	abstract protected void uebernehmeDaten() throws LagerException;
	
	protected boolean pruefeFehler() {
		ArrayList<Fehler> errors = pruefeDich();
		if (errors==null){
			errors = new ArrayList<Fehler>();
		}
		if (errors.size()==0){
			errors = getController().setzeNeuenAktivenController();
		}
		
		if (getModelBean()!=null){
			IBean iBean = getModelBean().getIBean();
			if (iBean!=null){
				errors.addAll(iBean.pruefeEigeneDaten());
			}
		}
		
		for(int i=0; i<errors.size();i++){
			System.out.println("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
		}
		
		Boolean result = errors.size()==0;
		if(!result){
			JFehlerDialogWechselController.getOneIntstance().showView(errors);
		}
		return errors.size()==0;
	}

	protected abstract DetailsController getController();

	abstract protected void setController(DetailsController detailsController);
	
	protected ModelKnotenBean getModelBean() {
		return modelKnotenBean;
	}

	protected void setModelBean(ModelKnotenBean modelBean) {
		this.modelKnotenBean = modelBean;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
//		int key = e.getKeyCode();
//	     if (key != KeyEvent.VK_TAB) {
//	     }
    	 //sofort zum Aktiven kontroller machen
    	 getController().setzeNeuenAktivenController();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}
