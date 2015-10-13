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
