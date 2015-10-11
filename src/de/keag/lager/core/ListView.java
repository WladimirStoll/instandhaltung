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

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellanforderung.AnforderungStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;



public abstract class ListView extends View implements IListBeobachter{

	private ListSelectionListener listSelectionListener = null;  //  @jve:decl-index=0:
	
	
	
public ListView() {
	super();
}

protected void erstelleNeuenSatz(){
	if (!pruefeFehler()) return;
	ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
	if(modelKnotenBean!=null && modelKnotenBean.istGesamterInhaltGeaendert()){
		zeigeFehlerDialogSatzGeaendert();
	}else{
		getIController().erstelleNeuenSatz();
	}
	
}

abstract public ListController getIController() ;


public boolean pruefeFehler() {
	ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
	
	ArrayList<Fehler> errors = getIController().setzeNeuenAktivenController();
	if (modelKnotenBean!=null){
		IBean iBean = modelKnotenBean.getIBean();
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

abstract public TableCellRenderer getStatusCellRenderer(int row, int column);


abstract public IModel getiModel();

protected void zeigeFehlerDialogSatzGeaendert() {
	JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
		    "Bitte vorher Änderung abspeichern oder verwerfen!",
		    "Hinweis",JOptionPane.WARNING_MESSAGE);
}

protected void speichereSatz() {
	if (!pruefeFehler()) return;
	if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
		Bean bean = (Bean) getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
		ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
		if(!modelKnotenBean.istGesamterInhaltGeaendert()){
			JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
				    "Daten sind nicht geändert und müssen nicht gespeichert werden!",
				    "Hinweis",JOptionPane.WARNING_MESSAGE);
		}else{
			getIController().speichereSatz(bean);
		}
	}

//	if (!pruefeFehler()) return;
//	if(getiModel()!=null && getiModel().getSelectedModelKnotenBean()!=null){
//		BaBean baBean = (BaBean)getiModel().getSelectedModelKnotenBean().getIBean(); //aktuelle Bean holen
//		ModelKnotenBean modelKnotenBean = getiModel().getSelectedModelKnotenBean();
//		if(!modelKnotenBean.istGesamterInhaltGeaendert()){
//			JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
//				    "Daten sind nicht geändert und müssen nicht gespeichert werden!",
//				    "Hinweis",JOptionPane.WARNING_MESSAGE);
//		}else{
//			getIController().speichereSatz(baBean);
//		}
//	}
	}
	
protected void abbrechenSatz() {
//	getIController().setzeNeuenAktivenController(); //klappt oder nicht = egal
	if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
		Bean bean = (Bean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
		getIController().abbrechenSatz(bean);
	}
//	getIController().setzeNeuenAktivenController(); //klappt oder nicht = egal
//	if(getiModel()!=null && getiModel().getSelectedModelKnotenBean()!=null){
//		BaBean baBean = (BaBean)getiModel().getSelectedModelKnotenBean().getIBean(); //aktuelle Bean holen
//		getIController().abbrechenSatz(baBean);
//	}
}

//protected void druckeBericht() throws Exception {
//	druckeBericht(null);
//}

protected void druckeBericht(Map<String, String> druckParameter) throws Exception {
//	getIController().setzeNeuenAktivenController(); //klappt oder nicht = egal
	if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
		Bean bean = (Bean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
		getIController().druckeBericht(bean,druckParameter);
	}
//	getIController().setzeNeuenAktivenController(); //klappt oder nicht = egal
//	if(getiModel()!=null && getiModel().getSelectedModelKnotenBean()!=null){
//		BaBean baBean = (BaBean)getiModel().getSelectedModelKnotenBean().getIBean(); //aktuelle Bean holen
//		getIController().abbrechenSatz(baBean);
//	}
}


protected void showMenue(){
	if (!pruefeFehler()) return;
	ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
	if(modelKnotenBean!=null && modelKnotenBean.istGesamterInhaltGeaendert()){
		zeigeFehlerDialogSatzGeaendert();
	}else{
		getIController().showMenue();
	}
}

protected void showVorigesView(){
	if (!pruefeFehler()) return;
	ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
	if(modelKnotenBean!=null && modelKnotenBean.istGesamterInhaltGeaendert()){
		zeigeFehlerDialogSatzGeaendert();
	}else{
		getIController().showVorigesView();
	}
}




protected ListSelectionListener getListSelectionListener() {
	if (listSelectionListener==null){
		listSelectionListener = new ListSelectionListener(){
		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			int aktuelleZeile = lsm.getMinSelectionIndex();
			int aktuelleBeanPosition = getiModel().getSelectedListModelKnotenBeanLaufendeNummer();
			if(aktuelleZeile==aktuelleBeanPosition){
				//Verlassen der Funktion, denn die Zeile ist bereits gewählt.
				//keine Handlung ist notwendig
				return;
			}else{
			}
			
			if (pruefeFehler()){
				ModelKnotenBean modelKnotenBean = getiModel().getSelectedListModelKnotenBean();
				ArrayList<Fehler> errors = getIController().pruefeAktuellenSatz();
				Bean bean = (Bean)modelKnotenBean.getIBean();
				if (bean instanceof BaBean){
					BaBean baBean = (BaBean)bean;
					if(baBean.getStatus()==AnforderungStatus.OFFEN 
							   && ( errors.size()>0 || modelKnotenBean.istGesamterInhaltGeaendert())){
								if (errors.size()>0){
									JFehlerDialogWechselController.getOneIntstance().showView(errors);
								}else{
									if(modelKnotenBean.istGesamterInhaltGeaendert()){
										zeigeFehlerDialogSatzGeaendert();
									}
								}
								//Position bleibt erhalten //
								if (aktuelleBeanPosition >= 0){
									getJTable().getSelectionModel().setSelectionInterval(aktuelleBeanPosition, aktuelleBeanPosition);
									System.out.println(aktuelleBeanPosition);
								}
							}else{  //
									//Es darf eine andere Zeile(Bestellan gewählt werden.
									if (lsm.isSelectionEmpty()) {
										setGewaehlteZeile(-1);
							        } else {
							            // Ausgewählte Zeile(Laufnummer).
							        	setGewaehlteZeile(lsm.getMinSelectionIndex());
							        }
							}
				}else{
					//Es darf eine andere Zeile(Bestellan gewählt werden.
					if (lsm.isSelectionEmpty()) {
						setGewaehlteZeile(-1);
			        } else {
			            // Ausgewählte Zeile(Laufnummer).
			        	setGewaehlteZeile(lsm.getMinSelectionIndex());
			        }
				}
				//Wenn Status "offen" und Fehler sind vorhanden, dann Fehlerdialog anzeigen
			}else{
				//Fehler irgendwo in der Anzeige
				//Es muss auf den alten Wert positioniert werden.
				getIController().aktualisiereAnzeige();
			}
		}

		};
	}
	return listSelectionListener;
}

abstract public JTable getJTable();

protected void setGewaehlteZeile(int gewaehlteZeilenNummer) {
	getIController().setGewaehlteZeile(gewaehlteZeilenNummer);
}

protected void loescheSatz() {
//	if (!pruefeFehler()) return;
	if(getiModel()!=null && getiModel().getSelectedListModelKnotenBean()!=null){
		Bean bean = (Bean)getiModel().getSelectedListModelKnotenBean().getIBean(); //aktuelle Bean holen
		//sonderbehandlung BaBean
		if (bean!=null && bean instanceof BaBean){
			if (bean!=null && ((BaBean)bean).getStatus()==AnforderungStatus.GELOESCHT){
				JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
					    new Fehler(39).getMessage(),
					    "Hinweis",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				getIController().speichereSatz(bean);
			}
		}else{
			bean.setBeanDBStatus(BeanDBStatus.DELETE);
			getIController().speichereSatz(bean);
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

public void setStandardFocusPosition() {
	getJTable().requestFocus();
}



}
