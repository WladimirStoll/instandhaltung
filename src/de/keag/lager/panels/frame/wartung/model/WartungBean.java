/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
package de.keag.lager.panels.frame.wartung.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean.WartungsArtIntervallFaehigEnum;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;

public class WartungBean extends Bean {

	public enum StatusEnum {
		ALLE, OFFEN, ABGESCHLOSSEN, UNKNOWN;
		public static String _UNKNOWN_GUI = "Unbekannt";
		public static String _ALLE_GUI = "Alle";
		public static String _OFFEN_GUI = "Offen";
		public static String _ABGESCHLOSSEN_GUI = "Abgeschlossen";
		
		public static String _UNKNOWN_DB = "U";
		public static String _ALLE_DB = "A";
		public static String _OFFEN_DB = "O";
		public static String _ABGESCHLOSSEN_DB = "F";
		

//		public static StatusEnum toStatusEnum(String value) {
//			if (value == null || value.equals(_OFFEN_GUI)) {
//				return OFFEN;
//			} else if (value.equals(_ABGESCHLOSSEN_GUI)) {
//				return ABGESCHLOSSEN;
//			} else if (value.equals(_ALLE_GUI)) {
//				return ALLE;
//			} else
//				return ALLE;
//		}
		
		public static StatusEnum DbToJava(String dbWert){
			if (dbWert.toUpperCase().equals(_ALLE_DB))
				return StatusEnum.ALLE;
			else if (dbWert.toUpperCase().equals(_ABGESCHLOSSEN_DB))
				return StatusEnum.ABGESCHLOSSEN;
			else if (dbWert.toUpperCase().equals(_OFFEN_DB))
				return StatusEnum.OFFEN;
			else
				return StatusEnum.UNKNOWN;
		}
		
		public static String JavaToDB(StatusEnum statusEnum){
			if (statusEnum == OFFEN)
				return _OFFEN_DB;
			if (statusEnum == ABGESCHLOSSEN)
				return _ABGESCHLOSSEN_DB;
			if (statusEnum == ALLE)
				return _ALLE_DB;
			else
				return _UNKNOWN_DB; //Fehlerhaft

		}
		
		public static String JavaToView(StatusEnum statusEnum){
			if (statusEnum == ALLE)
				return _ALLE_GUI;
			else if (statusEnum == ABGESCHLOSSEN)
				return _ABGESCHLOSSEN_GUI;
			else if (statusEnum == OFFEN)
				return _OFFEN_GUI;
			else if (statusEnum == UNKNOWN)
				return _UNKNOWN_GUI;
			else
				return _UNKNOWN_GUI; //Fehlerhaft
		}
		
		public String toString(){
			return JavaToView(this);
		}
		public String JavaToDB() {
			return StatusEnum.JavaToDB(this);
		}
		
	}

	private Integer id = 0;
	private WartungsTypBean fk_wartungstyp;
	private Integer karteiid;
	private String beschreibung;
	private Integer intervall;
	private java.sql.Date termin_soll;
	private java.sql.Date termin_ist;
	private StatusEnum status;
	private BaugruppeBean fk_baugruppe;
	private WartungsArtBean fk_wartungsart;
	private String bemerkung;
	
	private ArrayList<WartungsMitarbeiterBean> wartungsMitarbeiterBeans = null;
	private ArrayList<WartungsArtikelBean> wartungsArtikelBeans = null;
	

	public WartungBean() {
		super();
	}

	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}

	public Integer getId() {
		if (id==null){
			id = 0;
		}
		return id;
	}

	public void setId(Integer id) {
		if (!this.getId().equals(id)) {
			this.id = id;
			beanIstGeaendert();
		}
	}

	public String getBeschreibung() {
		if (beschreibung==null){
			beschreibung = new String();
		}
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		if (!this.getBeschreibung().equals(beschreibung)) {
			this.beschreibung = beschreibung;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();

		if (getId().equals(0)) {
			getFehlerList().add(new Fehler(44));
		}

		if (getIntervall().equals(0)) {
			if (getFk_wartungsart().getIntervallFaehig()==WartungsArtIntervallFaehigEnum.JA){
				getFehlerList().add(new Fehler(129));
			}
		}
		
		if (getFk_baugruppe().getId().equals(0)) {
				getFehlerList().add(new Fehler(142));
		}
		

		if (getTermin_soll().equals(0)) {
			getFehlerList().add(new Fehler(130));
		}
		
		if (getFk_wartungsart()==null||getFk_wartungsart().getId().equals(0)) {
			getFehlerList().add(new Fehler(141));
		}
		

		// if(getBaugruppeId().equals(0)
		// ){
		// getFehlerList().add(new Fehler(131));
		// }

		// if(getBeschreibung().trim().equals("")
		// ){ //trim()=abschneiden der leerzeichen
		// getFehlerList().add(new Fehler(44));
		// }

		return getFehlerList();
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungBean) {
			WartungBean wartungBean = (WartungBean) bean;
			if (wartungBean == null) {
				return false;
			} else {
				return (this.getId() == wartungBean.getId());
			}
		} else
			return false;
	}

	@Override
	public String toString() {
		return getId().toString();
	}

	public WartungsTypBean getWartungsTypBean() {
		if (fk_wartungstyp==null){
			fk_wartungstyp = new WartungsTypBean();
		}
		return fk_wartungstyp;
	}

	public void setWartungsTypBean(WartungsTypBean wartungsTypBean) {
		if (!getWartungsTypBean().equals(wartungsTypBean)) {
			this.fk_wartungstyp = wartungsTypBean;
			beanIstGeaendert();
		}
	}



	public Integer getIntervall() {
		if (intervall==null){
			intervall = new Integer(0);
		}
		return intervall;
	}

	public void setIntervall(Integer intervall) {
		if (!this.getIntervall().equals(intervall)) {
			this.intervall = intervall;
			beanIstGeaendert();
		}
	}

	public java.sql.Date getTermin_soll() {
		if (termin_soll==null){
			termin_soll = getLeeresDatum();
		}
		return termin_soll;
	}

	public void setTermin_soll(java.sql.Date terminSoll) {
		if (!this.getTermin_soll().equals(terminSoll)) {
			termin_soll = terminSoll;
			beanIstGeaendert();
		}
	}

	public java.sql.Date getTermin_ist() {
		if (termin_ist==null){
			termin_ist = getLeeresDatum();
		}
		return termin_ist;
	}

	public void setTermin_ist(java.sql.Date terminIst) {
		if (!this.getTermin_ist().equals(terminIst)) {
			termin_ist = terminIst;
			beanIstGeaendert();
		}
	}

	public StatusEnum getStatus() {
		if(status==null){
			status = StatusEnum.OFFEN;
		}
		return status;
	}

	public void setStatus(StatusEnum status) {
		if (!this.getStatus().equals(status)) {
			this.status = status;
			beanIstGeaendert();
		}
	}

	public WartungsTypBean getFk_wartungstyp() {
		if (this.fk_wartungstyp == null) {
			this.fk_wartungstyp = new WartungsTypBean();
		}
		return fk_wartungstyp;
	}

	public void setFk_wartungstyp(WartungsTypBean fkWartungstyp) {
		if (!this.getFk_wartungstyp().equals(fkWartungstyp)) {
		fk_wartungstyp = fkWartungstyp;
		beanIstGeaendert();
		}
	}

	public Integer getKarteiid() {
		if (this.karteiid == null) {
			this.karteiid = new Integer(0);
		}
		return karteiid;
	}

	public void setKarteiid(Integer karteiid) {
		if (!this.getKarteiid().equals(karteiid)) {
			this.karteiid = karteiid;
			beanIstGeaendert();
		}		
	}

	public BaugruppeBean getFk_baugruppe() {
		if (this.fk_baugruppe == null) {
			this.fk_baugruppe = new BaugruppeBean();
		}
		return fk_baugruppe;
	}

	public void setFk_baugruppe(BaugruppeBean fkBaugruppe) {
		if (!this.getFk_baugruppe().equals(fkBaugruppe)) {
			fk_baugruppe = fkBaugruppe;
			beanIstGeaendert();
		}		
	}

	public WartungsArtBean getFk_wartungsart() {
		if (this.fk_wartungsart == null) {
			this.fk_wartungsart = new WartungsArtBean();
		}
		return fk_wartungsart;
	}

	public void setFk_wartungsart(WartungsArtBean fkWartungsart) {
		if (!this.getFk_wartungsart().equals(fkWartungsart)) {
	}		fk_wartungsart = fkWartungsart;
			beanIstGeaendert();
	}

	public String getBemerkung() {
		if (this.bemerkung == null) {
			this.bemerkung = new String();
		}
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		if (!this.getBemerkung().equals(bemerkung)) {
			this.bemerkung = bemerkung;
			beanIstGeaendert();
		}		
	}

	
	public ArrayList<WartungsArtikelBean> getWartungsArtikelBeans() {
		if(wartungsArtikelBeans==null){
			wartungsArtikelBeans = new ArrayList<WartungsArtikelBean>();
		}
		return wartungsArtikelBeans;
	}
	

	public ArrayList<WartungsMitarbeiterBean> getWartungsMitarbeiterBeans() {
		if(wartungsMitarbeiterBeans==null){
			wartungsMitarbeiterBeans = new ArrayList<WartungsMitarbeiterBean>();
		}
		return wartungsMitarbeiterBeans;
	}
	
	public void setWartungsMitarbeiterBeans(ArrayList<WartungsMitarbeiterBean> wartungsMitarbeiterBeans) {
		if (wartungsMitarbeiterBeans==null){
			wartungsMitarbeiterBeans = new ArrayList<WartungsMitarbeiterBean>();
		}
		if (this.wartungsMitarbeiterBeans != wartungsMitarbeiterBeans){
			this.wartungsMitarbeiterBeans = wartungsMitarbeiterBeans;
			beanIstGeaendert();
		}
	}
	
	public void setWartungsArtikelBeans(ArrayList<WartungsArtikelBean> wartungsArtikelBeans) {
		if (wartungsArtikelBeans==null){
			wartungsArtikelBeans = new ArrayList<WartungsArtikelBean>();
		}
		if (this.wartungsArtikelBeans != wartungsArtikelBeans){
			this.wartungsArtikelBeans = wartungsArtikelBeans;
			beanIstGeaendert();
		}
	}
	
}
