package de.keag.lager.panels.frame.lieferantenBestellnummer.model;

import javax.swing.Icon;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.unterArtikel.model.UnterArtikelBean;


public class UnterArtikelModelKnotenBean extends ModelKnotenBean {

	/**
	 * Hauptkonsturor
	 * @param bean
	 * @throws BenutzerOberflacheLagerException
	 */
	public UnterArtikelModelKnotenBean(UnterArtikelBean bean,ModelKnotenBean vater) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.UNTER_ARTIKEL,vater);
		setIBean(bean);
		bean.setModelKnotenBean(this);
	}

//	public BaugruppeArtikelModelKnotenBean(ModelKnotenBean vater) {
//		super(ModelKnotenTyp.BAUGRUPPE_ARTIKEL,vater);
//	}
	

	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getKindArtikelBean()!=null){
				return "Unterg.Art.: " + getIBean().getKindArtikelBean().getBezeichnung();
			}else{
				return "Unterg.Art.";
			}
		else return "Unterg.Art." ;
	}
	
	@Override
	public UnterArtikelBean getIBean() {
		return (UnterArtikelBean)super.getIBean();
	}
	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_UNTERARTIKEL);	
	}
	

//	@Override
//	public boolean istGesamterInhaltGeaendert() {
//		if (((LieferantenBestellnummerBean) getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
//			return true;
//		}
//		for (int i = 0; i < getKinderList().size(); i++) {
//			ModelKnotenBean kind = getKinderList().get(i);
//			if (kind.istGesamterInhaltGeaendert()){
//				return true;
//			}
//		}
//		return false;
//		
//	}
}
