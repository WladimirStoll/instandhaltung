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
package de.keag.lager.panels.frame.bericht;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;

public class BerichtBean extends Bean{
	
	private Integer id = null;
	private Berichttyp berichtTyp;
	private Integer berichtTypId; //z.B. Anforderungs-ID, oder Bestellungs-ID, oder Baugruppen-ID
	private BenutzerBean aktuellerBenutzer; 
	private Berichtart berichtArt;
	private java.sql.Date druckDatumOriginal; 
	private java.sql.Date druckDatumKopie;
	private String eMail;

	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		if(getId()==0){
			getFehlerList().add(new Fehler(100,FehlerTyp.FEHLER,"Keine ID in Bericht(BerichtBean)"));
		}
		
		if(getBerichtTyp().equals(Berichttyp.FEHLERHAFT)){
			getFehlerList().add(new Fehler(100,FehlerTyp.FEHLER,"Kein Typ in Bericht(BerichtBean)"));
		}
		
		if(getAktuellerBenutzer()==null){
			getFehlerList().add(new Fehler(100,FehlerTyp.FEHLER,"Kein Benutzer in Bericht(BerichtBean)"));
		}
		
		if(getBerichtArt().equals(Berichtart.FEHLERHAFT)){
			getFehlerList().add(new Fehler(100,FehlerTyp.FEHLER,"Keine Berichtart in Bericht(BerichtBean)"));
		}
		
		return getFehlerList();
	}

	
	
	public BerichtBean() {
		super();
	}
	
	public Integer getId() {
		if (id == null){
			id = 0;
		}
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
	}
	
	
	@Override
	public String toString(){
		return "BerichtBean:"+	getId()+ " Erstellungsdatum:" + getErfassungsDatum();
	}


	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof BerichtBean){
			BerichtBean tempBean = (BerichtBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}
	
	
	public Berichttyp getBerichtTyp() {
		if (berichtTyp==null){
			berichtTyp = Berichttyp.FEHLERHAFT;
		}
		return berichtTyp;
	}

	public void setBerichtTyp(Berichttyp berichtTyp) {
		if (!getBerichtTyp().equals(berichtTyp)){
			this.berichtTyp = berichtTyp;
			beanIstGeaendert();
		}
	}

	public BenutzerBean getAktuellerBenutzer() {
		if (aktuellerBenutzer==null){
			aktuellerBenutzer = new BenutzerBean();
		}
		return aktuellerBenutzer;
	}

	public void setAktuellerBenutzer(BenutzerBean aktuellerBenutzer) {
		if (!getAktuellerBenutzer().equals(aktuellerBenutzer)){
			this.aktuellerBenutzer = aktuellerBenutzer;
			beanIstGeaendert();
		}
	}

	public Berichtart getBerichtArt() {
		if(berichtArt == null){
			berichtArt = Berichtart.FEHLERHAFT; 
		}
		return berichtArt;
	}

	public void setBerichtArt(Berichtart berichtArt) {
		if(!getBerichtArt().equals(berichtArt)){
			this.berichtArt = berichtArt;
			beanIstGeaendert();
		}
	}

	public java.sql.Date getDruckDatumOriginal() {
		if (druckDatumOriginal==null){
			druckDatumOriginal=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
		}
		return druckDatumOriginal;
	}

	public void setDruckDatumOriginal(java.sql.Date druckDatumOriginal) {
		if(this.druckDatumOriginal==null){
			this.druckDatumOriginal = druckDatumOriginal;
			beanIstGeaendert();
		}else{
			if(this.druckDatumOriginal.getTime()!=druckDatumOriginal.getTime()){
				this.druckDatumOriginal = druckDatumOriginal;
				beanIstGeaendert();
			}
		}
	}

	public java.sql.Date getDruckDatumKopie() {
		if (druckDatumKopie==null){
			druckDatumKopie=new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
		}
		return druckDatumKopie;
	}

	public void setDruckDatumKopie(java.sql.Date druckDatumKopie) {
		if(this.druckDatumKopie==null){
			this.druckDatumKopie = druckDatumKopie;
			beanIstGeaendert();
		}else{
			if(this.druckDatumKopie.getTime()!=druckDatumKopie.getTime()){
				this.druckDatumKopie = druckDatumKopie;
				beanIstGeaendert();
			}
		}
	}



	public String getEMail() {
		// TODO Auto-generated method stub
		if (eMail==null){
			eMail = "";
		}
		return eMail;
	}



	public void setEMail(String eMail) {
		if (!this.getEMail().equals(eMail)){
			this.eMail = eMail;
			beanIstGeaendert();
		}
	}
	
	public Integer getBerichtTypId() {
		if (berichtTypId == null){
			berichtTypId = 0;
		}
		return berichtTypId;
	}

	public void setBerichtTypId(Integer berichtTypId) {
		if (!this.getBerichtTypId().equals(berichtTypId)){
			this.berichtTypId = berichtTypId;
			beanIstGeaendert();
		}
	}

	
	
}
