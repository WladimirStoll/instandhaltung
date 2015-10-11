package de.keag.lager.panels.frame.wartungsartikel.model;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;


public class WartungsArtikelModelKnotenBean extends ModelKnotenBean {
	
	public WartungsArtikelModelKnotenBean(ModelKnotenBean vater) {
		super(ModelKnotenTyp.WARTUNG_ARTIKEL,vater);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			if ( getIBean().getFk_artikel()!=null){
				ArtikelBean artikelBean = getIBean().getFk_artikel();
				return artikelBean.getBezeichnung()
						+ ", Typ:" + artikelBean.getTyp();

//				return getIBean().getFk_artikel().toString();
			}else{
				return "Wartungsartikel...";
			}
		else return "Wartungsartikel.. " ;
	}
	
	@Override
	public WartungsArtikelBean getIBean() {
		return (WartungsArtikelBean)super.getIBean();
	}

	@Override
	public boolean istGesamterInhaltGeaendert() {
		if (((WartungsArtikelBean)getIBean()).getBeanDBStatus() != BeanDBStatus.SELECT) {
			return true;
		}else{
			return false;
		}
	}
}
