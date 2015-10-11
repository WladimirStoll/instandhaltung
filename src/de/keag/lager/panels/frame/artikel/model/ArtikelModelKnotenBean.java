package de.keag.lager.panels.frame.artikel.model;

import javax.swing.Icon;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;


public class ArtikelModelKnotenBean extends ModelKnotenBean {
	
	public ArtikelModelKnotenBean(ArtikelBean artikelBean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.ARTIKEL,null);
		setIBean(artikelBean);
		artikelBean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
		if (getIBean()!=null)
			return getIBean().getBezeichnung() + ", "+getIBean().getTyp();
//		return "Artikel " + getIBean().getKeg_nr() +"("+getIBean().getId()+")";
		else return "Artikel " ;
	}
	
	@Override
	public ArtikelBean getIBean() {
		return (ArtikelBean)super.getIBean();
	}

	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_ARTIKEL);	
	}	

}
