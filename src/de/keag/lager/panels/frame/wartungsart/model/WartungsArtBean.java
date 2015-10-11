package de.keag.lager.panels.frame.wartungsart.model;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;

public class WartungsArtBean extends Bean {
	
	public enum WartungsArtIntervallFaehigEnum{
		UNBEKANNT, JA,	NEIN;
		
		public static String _UNKNOWN_GUI = "-";
		public static String _JA_GUI = "Intervallfähig";
		public static String _NEIN_GUI = "Ohne Intervall";
		
		public static String _UNKNOWN_DB = "U";
		public static String _JA_DB = "J";
		public static String _NEIN_DB = "N";
		
		public static WartungsArtIntervallFaehigEnum DbToJava(String dbWert){
			if (dbWert==null){
				return WartungsArtIntervallFaehigEnum.UNBEKANNT;
			}
			if (dbWert.toUpperCase().equals(_JA_DB))
				return WartungsArtIntervallFaehigEnum.JA;
			else if (dbWert.toUpperCase().equals(_NEIN_DB))
				return WartungsArtIntervallFaehigEnum.NEIN;
			else
				return WartungsArtIntervallFaehigEnum.UNBEKANNT;
		}
		
		public static String JavaToDB(WartungsArtIntervallFaehigEnum value){
			if (value == JA)
				return _JA_DB;
			if (value == NEIN)
				return _NEIN_DB;
			else
				return _UNKNOWN_DB; //Fehlerhaft

		}
		
		public static String JavaToView(WartungsArtIntervallFaehigEnum value){
			if (value == JA)
				return _JA_GUI;
			else if (value == NEIN)
				return _NEIN_GUI;
			else
				return _UNKNOWN_GUI; //Fehlerhaft
		}
		
		public String toString(){
			return JavaToView(this);
		}
		public String JavaToDB() {
			return WartungsArtIntervallFaehigEnum.JavaToDB(this);
		}
		
	}
	
	private Integer id = 0;
	private String name = ""; 
	private WartungsArtIntervallFaehigEnum intervallFaehig;
	
	public WartungsArtBean() {
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
		if (!this.id.equals(id)){
			this.id = id;
			beanIstGeaendert();
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (!this.name.equals(name)){
			this.name = name;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		
		if(getName().trim().equals("")
		){ //trim()=abschneiden der leerzeichen
			getFehlerList().add(new Fehler(44));
		}
		
		
		return getFehlerList();
	}

	

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof WartungsArtBean){
			WartungsArtBean wartungsArtBean = (WartungsArtBean) bean;
			if (wartungsArtBean==null){
				return false;
			}else{
				return (this.getId() == wartungsArtBean.getId());
			}
		}else return false;
    }
	
	@Override
	public String toString(){
		return getName();
	}

	public void setIntervallFaehig(WartungsArtIntervallFaehigEnum intervallFaehig) {
		if (!this.getIntervallFaehig().equals(intervallFaehig)){
			this.intervallFaehig = intervallFaehig;
			beanIstGeaendert();
		}
	}

	public WartungsArtIntervallFaehigEnum getIntervallFaehig() {
		if (this.intervallFaehig==null){
				this.intervallFaehig = WartungsArtIntervallFaehigEnum.UNBEKANNT;
		}
		return intervallFaehig;
	}
	
	
}
