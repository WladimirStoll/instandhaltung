package de.keag.lager.panels.frame.lieferanthersteller.model;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;


public class LhKatalogModelKnotenBean extends ModelKnotenBean {
	
	public LhKatalogModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG,vater);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getKatalog()!=null){
				return "Katalog " + getIBean().getKatalog().getName();
			}else{
				return "Katalog ?";
			}
		else return "Katalog " ;
	}
	
	@Override
	public LhKatalogBean getIBean() {
		return (LhKatalogBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((LhKatalogBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
