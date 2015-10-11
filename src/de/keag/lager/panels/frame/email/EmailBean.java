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
package de.keag.lager.panels.frame.email;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

public class EmailBean extends Bean {
	private String beschreibung = "";
	private String email = "";

	public String getEmail() {
		return email;
	}
	

	public String getBeschreibung() {
		if(beschreibung==null){
			beschreibung = "";
		}
		return beschreibung;
	}

	@Override
	public int hashCodeForModelKnoten() {
		return super.hashCode();
	}
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
		if(!this.beschreibung.equals(beschreibung)){
			this.beschreibung = beschreibung;
			beanIstGeaendert();
		}
	}


	public void setEmail(String email) {
		if (email==null){
			email = "";
		}
		if(!this.email.equals(email)){
			this.email = email;
			beanIstGeaendert();
		}
	}


	@Override
	public ModelKnotenBean getModelKnotenBean() {
		return null;
	}


	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
	}


	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		ArrayList<Fehler>  errors = new ArrayList<Fehler>();
		if(email==null || email.equals("")){
			errors.add(new Fehler(40));
		}
		return errors;
	}


	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof EmailBean){
			EmailBean tempBean = (EmailBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getEmail()).equalsIgnoreCase(tempBean.getEmail());
			}
		}else return false;
	}

}
