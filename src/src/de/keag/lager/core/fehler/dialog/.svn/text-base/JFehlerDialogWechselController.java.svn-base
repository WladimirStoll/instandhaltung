package de.keag.lager.core.fehler.dialog;

import java.util.ArrayList;

import de.keag.lager.core.fehler.Fehler;


public class JFehlerDialogWechselController {
	private JFehlerDialogWechselView view = null;
	private JFehlerDialogWechselModel model = null;
	private static JFehlerDialogWechselController controller = null;

	private JFehlerDialogWechselController() {
		super();
	}
	public static JFehlerDialogWechselController getOneIntstance() {
		if(controller==null){
			controller = new JFehlerDialogWechselController();
			controller.getModel();
			controller.getView();
		}
		return controller;
	}
	private JFehlerDialogWechselView getView() {
		if(view==null){
			view = new JFehlerDialogWechselView(this,getModel());
		}
		return view;
	}
	private JFehlerDialogWechselModel getModel() {
		if(model==null){
			model = new JFehlerDialogWechselModel();
		}
		return model;
	}
	public void showView(ArrayList<Fehler> errors){
		getModel().setErrors(errors);
		getView().showView();
	}
}
