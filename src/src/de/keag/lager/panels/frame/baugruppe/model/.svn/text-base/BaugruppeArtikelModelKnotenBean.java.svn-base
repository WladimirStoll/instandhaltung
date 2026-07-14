package de.keag.lager.panels.frame.baugruppe.model;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;


public class BaugruppeArtikelModelKnotenBean extends ModelKnotenBean {

	/**
	 * Hauptkonsturor
	 * @param baugruppeArtikelBean
	 * @throws BenutzerOberflacheLagerException
	 */
	public BaugruppeArtikelModelKnotenBean(BaugruppeArtikelBean baugruppeArtikelBean,ModelKnotenBean vater) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BAUGRUPPE_ARTIKEL,vater);
		setIBean(baugruppeArtikelBean);
		baugruppeArtikelBean.setModelKnotenBean(this);
	}

//	public BaugruppeArtikelModelKnotenBean(ModelKnotenBean vater) {
//		super(ModelKnotenTyp.BAUGRUPPE_ARTIKEL,vater);
//	}
	

	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getArtikel()!=null){
				return "Artikel " + getIBean().getArtikel().getBezeichnung() + " " + getIBean().getArtikel().getTyp();
			}else{
				return "Artikel ";
			}
		else return "Artikel " ;
	}
	
	@Override
	public BaugruppeArtikelBean getIBean() {
		return (BaugruppeArtikelBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((BaugruppeArtikelBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}
		for (int i = 0; i < getKinderList().size(); i++) {
			ModelKnotenBean kind = getKinderList().get(i);
			if (kind.istGesamterInhaltGeaendert()){
				return true;
			}
		}
		return false;
		
	}
}
