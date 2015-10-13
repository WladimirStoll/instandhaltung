package de.keag.lager.panels.frame.katalog.model;


import javax.swing.Icon;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.katalog.KatalogBean;


public class KatalogModelKnotenBean extends ModelKnotenBean {
	public KatalogModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.KATALOG,vater);
	}
	
	public KatalogModelKnotenBean(KatalogBean einlagerungPosBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.KATALOG,null);
		setIBean(einlagerungPosBean);
		einlagerungPosBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return "" + getIBean().getName();
		else return "";
	}
	
	@Override
	public KatalogBean getIBean() {
		// TODO Auto-generated method stub
		return (KatalogBean)super.getIBean();
	}
	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((KatalogBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_LIEFERANT_HERSTELLER_KATALOG);	
	}
	
}
