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

