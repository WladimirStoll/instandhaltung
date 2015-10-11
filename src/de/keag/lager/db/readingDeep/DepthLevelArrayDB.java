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
package de.keag.lager.db.readingDeep;

import java.util.ArrayList;
import java.util.Collection;

import de.keag.lager.db.ArtikelBeanDB;

public class DepthLevelArrayDB {
	
	private Class callClassDB;
	private ArrayList<DepthLevelDB> searchDepthArrayDB;
	private ArrayList<DepthLevelDB> forbiddenDepthArrayDB;
	private ArrayList<DepthLevelDB> notConfigedDepthArrayDB;
	private boolean debugMode=false;
	
	public DepthLevelArrayDB(Class callClassDB) {
		super();
		this.callClassDB = callClassDB; 
	}
	
//	public void add(int index, DepthLevelDB element) {
//		getSearchDepthArrayDB().add(index, element);
//	}
	
	public boolean add(DepthLevelDB element) {
		return getSearchDepthArrayDB().add(element);
	}
	
	public ArrayList<DepthLevelDB> getSearchDepthArrayDB() {
		if (this.searchDepthArrayDB==null){
				this.searchDepthArrayDB = new ArrayList<DepthLevelDB>();
		}
		return searchDepthArrayDB;
	}
	
//	public int indexOf(DepthLevelDB element){
//		return getSearchDepthArrayDB().indexOf(element);
//	}
	
	/**
	 * 
	 */
	public boolean isDepthLevelOK(Class callDB, int depthLevel){
		if (getSearchDepthArrayDB().size()<=0){
			return true; //keine Prüfung, denn es gibt keine Prüfung-Bedingungen
		}else{
			boolean thisClassIsFound = false;
			for(DepthLevelDB depthLevelDB : getSearchDepthArrayDB()){
				if (depthLevelDB.getClassDB()==callDB){
					//Klassen erlaubnis ist gefunden
					thisClassIsFound = true;
					for(Integer dl : depthLevelDB.getIntegerArray()){
						if (dl==depthLevel){
							return true; //für diese Klasse und diesen Level ist die Erlaubnis vorhanden
						}
					}
				}
			}
			if (thisClassIsFound){
				if (debugMode){
					getForbiddenDepthArrayDB().add(new DepthLevelDB(callDB, depthLevel, depthLevel));
				}
				return false; //klasse ist konfiguriert, aber nicht für dieses Level
			}
		}
		if (debugMode){
			getNotConfigedDepthArrayDB().add(new DepthLevelDB(callDB, depthLevel, depthLevel));		
		}
		return true; //Klasse callDB ist nicht konfiguriert. Also auch erlaubt!
	}

	/*
	 * Test
	 */
	public static void main(String[] args)  {
		DepthLevelArrayDB searchDeepingDB = new DepthLevelArrayDB(ArtikelBeanDB.class);
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}


	public ArrayList<DepthLevelDB> getForbiddenDepthArrayDB() {
		if (this.forbiddenDepthArrayDB==null){
				this.forbiddenDepthArrayDB = new ArrayList<DepthLevelDB>();
		}
		return forbiddenDepthArrayDB;
	}


	public ArrayList<DepthLevelDB> getNotConfigedDepthArrayDB() {
		if (this.notConfigedDepthArrayDB==null){
				this.notConfigedDepthArrayDB = new ArrayList<DepthLevelDB>();
		}
		return notConfigedDepthArrayDB;
	}

	
}

