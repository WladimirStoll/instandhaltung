package de.keag.lager.core.fehler;

import java.util.ArrayList;

public class LagerException extends Exception{

	private Fehler fehler;
	
	
	public Fehler getFehler(){
		if (fehler == null){
			fehler = new Fehler(0);
		};
		return fehler;
	}
	
	@Override
	public String getMessage() {
		String message;
		if (fehler==null){
			message = super.getMessage(); 
		}else{
			message = fehler.getMessage();
		}
		return message; 
	}
	
	public LagerException(Fehler fehler){
		super();
		this.fehler = fehler;
	}

	public LagerException() {
		this("");
	}
	
	//Fehler ohne nummer
	public LagerException(String message) {
		super();
		this.fehler = getFehler();
		fehler.setMeldung(message);
	}
	

}
