package de.keag.lager.db.readingDeep;

import java.util.ArrayList;

import de.keag.lager.core.IBean;

public class DepthLevelDB {
	private ArrayList<Integer> integerArray;
	private Class classDB; 
	
	public DepthLevelDB(Class classDB, int readingLevelFrom, int readingLevelTo) {
		super();
		this.classDB = classDB;
		while (readingLevelFrom <= readingLevelTo){
			getIntegerArray().add(readingLevelFrom);
			readingLevelFrom++;
		}
	}

	public ArrayList<Integer> getIntegerArray() {
		if (this.integerArray==null){
				this.integerArray = new ArrayList<Integer>();
		}
		return integerArray;
	}

	
	public Class getClassDB() {
		return this.classDB;
	}

	@Override
	public String toString() {
		return classDB.toString() + " Level:"+integerArray.toString();
	}
	
}
