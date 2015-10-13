package de.keag.lager.panels.frame.wartung.pane.details.wartung;

import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;



public interface IWartungDetailsBeobachter {
	void zeichneDich(ModelKnotenBean wartungModelBean, IModel iModel);
}
