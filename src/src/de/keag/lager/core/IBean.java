package de.keag.lager.core;

import java.util.ArrayList;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;

public interface IBean {
	
	public ModelKnotenBean getModelKnotenBean();
	void setModelKnotenBean(ModelKnotenBean modelKnotenBean);
	public ArrayList<Fehler> pruefeEigeneDaten();
	boolean equals(Bean bean);
	public int hashCodeForModelKnoten();
}
