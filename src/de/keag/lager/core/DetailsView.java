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
