package de.keag.lager.panels.frame.bestandspackstueck.model;

import javax.swing.Icon;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.model.ArtikelModelKnotenBean;


public class BestandspackstueckModelKnotenBean extends ModelKnotenBean {
	public BestandspackstueckModelKnotenBean(BestandspackstueckBean bean) throws BenutzerOberflacheLagerException {
		super(ModelKnotenTyp.BESTANDSPACKSTUECK,null);
		setIBean(bean);
		bean.setModelKnotenBean(this);
	}
	
	@Override
	public String toString() {
//		if (getIBean()!=null)
//			return "Bestandspackstück " + getIBean().getId();
//		else return "Bestandspackstück " ;
		String b = "Bestandspackstück";
		if (getIBean()!=null){
			if (this.getVater() instanceof ArtikelModelKnotenBean){
				String p = getIBean().getPositionBean().toString();
				String e = getIBean().getPositionBean().getEbeneBean().toString();
				String s = getIBean().getPositionBean().getEbeneBean().getSaeuleBean().toString();
				String z = getIBean().getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().toString();
				String h = getIBean().getPositionBean().getEbeneBean().getSaeuleBean().getZeileBean().getHalleBean().toString();
				b = h + " / " + z + "-" + s + "-" + e + "-" + p + ", Menge:"+getIBean().getMenge();
			}else{
				b = getIBean().getArtikelBean().getBezeichnung();
			}
			return b;
		}    
		else return b ;
	}
	
	@Override
	public BestandspackstueckBean getIBean() {
		return (BestandspackstueckBean)super.getIBean();
	}
	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_BESTANDSPACKSTUECK);	
	}

}
