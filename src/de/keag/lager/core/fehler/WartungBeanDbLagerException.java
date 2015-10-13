package de.keag.lager.core.fehler;

public class WartungBeanDbLagerException extends LagerException{

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
	
	public WartungBeanDbLagerException(Fehler fehler){
		super();
	}
}
