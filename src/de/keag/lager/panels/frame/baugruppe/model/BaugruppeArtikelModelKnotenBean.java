package de.keag.lager.panels.frame.baugruppe.model;

import javax.swing.Icon;

import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.model.ArtikelModelKnotenBean;
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
			if ( getIBean().getArtikel()!=null){ //this
				String bezeichung = getIBean().getArtikel().getBezeichnung();
				bezeichung = bezeichung.trim()  + ", Typ:" + getIBean().getArtikel().getTyp();
				if (bezeichung.length()>60){//maximal 20 Zeichen ausgeben
					bezeichung = bezeichung.substring(0,30)+"...";
				}
				String s = getIBean().getBaugruppe().getVaterBaugruppeNamenListe().trim(); 
				if(s.length()>0){
					s = s + " >> " 
					+ getIBean().getBaugruppe().toString();
				}else{
					s = getIBean().getBaugruppe().toString();
				}
				
				if (this.getVater()!=null && this.getVater() instanceof BaugruppeModelKnotenBean){
					return bezeichung; //Maske "Instandhaltung / Anlagen"
				}else{
					if (this.getVater()!=null && this.getVater() instanceof ArtikelModelKnotenBean){
						return s; //Maske "Instandhaltung / Artikel" 
					}else{
						return s; //sonst
					}
				}
//				+ " "
//				+ getIBean().getArtikel();
//				return bezeichung + ", Anlage:" + s; //neu am 28.08.2013, nicht immer OK
//				return bezeichung+ " >> "+ s; //neu am 28.08.2013
//				return s; //war am 28.08.2013
//				return bezeichung + " Typ:" + getIBean().getArtikel().getTyp();
//				return bezeichung + " Typ:" + getIBean().getArtikel().getTyp();
//				return "Artikel " + getIBean().getArtikel().getBezeichnung() + " " + getIBean().getArtikel().getTyp();
			}else{
				return "Baugruppe ";
			}
		else return "Baugruppe " ;
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
	
	public static Icon getEasyIcon() {
		return Run.createCompoundIcon(Konstanten.ICON_BAUGRUPPE);	
	}
	
}