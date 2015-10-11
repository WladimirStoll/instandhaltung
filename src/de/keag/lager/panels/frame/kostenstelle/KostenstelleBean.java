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
package de.keag.lager.panels.frame.kostenstelle;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.katalog.KatalogBean;

public class KostenstelleBean extends Bean{
	private Integer id = 0;
	private String name; 
	private String nummer;
	
	public KostenstelleBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		if (name==null){
			name = "";
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNummer() {
		if (nummer==null)
		{
			nummer = "";
		}
		return nummer;
	}
	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

//	@Override
//	public ModelKnotenBean getModelKnotenBean() {
//		Log.log().severe("nicht implementiert");
//		return null;
//	}
//
//	@Override
//	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(KostenstelleBean kostenstelleBean){
		if (kostenstelleBean==null){
			return false;
		}else{
			//id' sind gleich und ungleich 0
			return (this.getId() == kostenstelleBean.getId())&(kostenstelleBean.getId()!=0); 
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof KostenstelleBean){
			KostenstelleBean tempBean = (KostenstelleBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
}
