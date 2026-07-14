package de.keag.lager.core;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Run;

abstract public class SuchView extends View implements ISuchBeobachter{


	abstract public void setStandardFocusPosition() ;
	
	public boolean pruefeFehler() {
		ArrayList<Fehler> errors = getSuchController().setzeNeuenAktivenController();
		for(int i=0; i<errors.size();i++){
			System.out.println("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
		}
		Boolean result = errors.size()==0;
		if(!result){
			JFehlerDialogWechselController.getOneIntstance().showView(errors);
		}else{
			//bitte daten vorher speichern
			if (getiModel()!=null &&
					getiModel().getSelectedListModelKnotenBean() != null &&
					getiModel().getSelectedListModelKnotenBean().istGesamterInhaltGeaendert()){
				JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(),
					    "Bitte vorher Änderung abspeichern oder verwerfen!",
					    "Hinweis",JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return result;
	}

	abstract public SuchController getSuchController() ;

	abstract public IModel getiModel();
	
	@Override
	public void zeichneDich(ISuchBean iSuchBean) {
	}

	public abstract void setSuchController(SuchController suchController);

	public abstract void setiModel(IModel iModel) ;

	

}
