package de.keag.lager.panels.frame.unterArtikel.model;

import javax.swing.Icon;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;


public class LieferantenBestellnummerModelKnotenBean extends ModelKnotenBean {

	/**
	 * Hauptkonsturor
	 * @param lieferantenBestellnummerBean
	 * @throws BenutzerOberflacheLagerException
	 */
	public LieferantenBestellnummerModelKnotenBean(LieferantenBestellnummerBean lieferantenBestellnummerBean,ModelKnotenBean vater) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.LIEFERANT_BESTELLNUMMER,vater);
		setIBean(lieferantenBestellnummerBean);
		lieferantenBestellnummerBean.setModelKnotenBean(this);
	}

//	public BaugruppeArtikelModelKnotenBean(ModelKnotenBean vater) {
//		super(ModelKnotenTyp.BAUGRUPPE_ARTIKEL,vater);
//	}
	

	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getBestellnummer()!=null){
				return "" + getIBean().getKatalogBean().getName();
			}else{
				return "";
			}
		else return "" ;
	}
	
	@Override
	public LieferantenBestellnummerBean getIBean() {
		return (LieferantenBestellnummerBean)super.getIBean();
	}

	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_LIEFERANT_HERSTELLER_KATALOG);	
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
