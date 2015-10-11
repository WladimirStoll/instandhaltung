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

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;

abstract public class View extends JPanel {
				
	/**
	 * 
	 */
	private static final long serialVersionUID = 8497108671083596528L;

	public View() {
		super();	
	}

	protected void mouseClickedFehler(MouseEvent e, JComboBox jComboBoxFehler) {
		if (jComboBoxFehler!=null && e.getClickCount() >= 2) {
			ArrayList<Fehler> errors = new ArrayList<Fehler>(); 
			for(int i=0; i<jComboBoxFehler.getItemCount();i++){
				if (jComboBoxFehler.getItemAt(i)!=null){
					if (jComboBoxFehler.getItemAt(i) instanceof Fehler){
						errors.add((Fehler)jComboBoxFehler.getItemAt(i));
					}
				}
			}
			Boolean result = errors.size()==0;
			if(!result){
				JFehlerDialogWechselController.getOneIntstance().showView(errors);
			}
        }
	}
	
	protected void setzeHintergrund(){
		for (int i=0; i<this.getComponentCount();i++){
			Component c = this.getComponent(i);
			if(c instanceof JFormattedTextField){
				c.setBackground(Konstanten.COLOR_BACKGROUND_TEXTFIELD_DEFAULT);
			}
		}
	}
	
	
	public void setBorder(Boolean aktiv) {
		Border b = this.getBorder();
		if (b!=null){
			if (b instanceof LineBorder){
				if (aktiv){
					if (((LineBorder) b).getLineColor()!=Konstanten.COLOR_LINEBORDER_AKTIV){
						this.setBorder(BorderFactory.createLineBorder(Konstanten.COLOR_LINEBORDER_AKTIV, 1));
					}
				}else{
					if (((LineBorder) b).getLineColor()!=Konstanten.COLOR_LINEBORDER_NICHT_AKTIV){
						this.setBorder(BorderFactory.createLineBorder(Konstanten.COLOR_LINEBORDER_NICHT_AKTIV, 1));
					}
				}
			}
		}
	}
	
	
}
