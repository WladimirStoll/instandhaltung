package de.keag.lager.core;

import java.sql.Date;
import java.util.ArrayList;

import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;

public abstract class Bean implements IBean, Comparable<Bean> {
	
	private BenutzerBean erfassungsBenutzer;
	private Date erfassungsDatum;
	private BenutzerBean aenderungsBenutzer;
	private Date aenderungsDatum;
	
	private BeanDBStatus beanDBStatus; //Für DB-Speicherung
	private ArrayList<Fehler> fehlerList;
	
	private ModelKnotenBean modelKnotenBean; //mein Besitzer-Knoten
	
	public static java.sql.Date  getAktuellesDatum(){
		return new java.sql.Date(new java.util.Date().getTime()); //aktuelles Datum
	}
	
	public static java.sql.Date getLeeresDatum(){
		return new java.sql.Date(new java.util.Date(0L).getTime()); //leeres Datum
	}
		
	
	
	@Override
	public ModelKnotenBean getModelKnotenBean() {
		return modelKnotenBean;
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		this.modelKnotenBean = modelKnotenBean;
	}
	
	
	public BeanDBStatus getBeanDBStatus() {
		if(beanDBStatus==null){
			beanDBStatus = BeanDBStatus.FEHLERHAFT;
		}
		return beanDBStatus;
	}

	public void setBeanDBStatus(BeanDBStatus beanStatus) {
		switch (beanStatus){
		
		case DELETE : {
			switch (this.getBeanDBStatus()){
			case SELECT : this.beanDBStatus = BeanDBStatus.DELETE; break;
			case INSERT : this.beanDBStatus = BeanDBStatus.INSERT_DELETE; break;
			case UPDATE : this.beanDBStatus = BeanDBStatus.DELETE; break;
			case INSERT_DELETE :  break;
			case DELETE : break;
			case FEHLERHAFT : break;
			default : this.beanDBStatus = BeanDBStatus.FEHLERHAFT; break;
			};
			break;
		}
		
		case FEHLERHAFT : {
			switch (this.getBeanDBStatus()){
			case SELECT :  this.beanDBStatus = BeanDBStatus.FEHLERHAFT; break;
			case INSERT :  break;
			case UPDATE :  break;
			case INSERT_DELETE :   break;
			case DELETE :  break;
			case FEHLERHAFT :  break;
			default : this.beanDBStatus = BeanDBStatus.FEHLERHAFT; break;
			};
			break;
		}

		case INSERT : {
			switch (this.getBeanDBStatus()){
			case SELECT : break;
			case INSERT :  break;
			case UPDATE : break;
			case INSERT_DELETE :  this.beanDBStatus = BeanDBStatus.INSERT; break;
			case DELETE : break;
			case FEHLERHAFT : this.beanDBStatus = BeanDBStatus.INSERT; break; //neuer Satz wird ereugt
			default : this.beanDBStatus = BeanDBStatus.FEHLERHAFT; break;
			};
			break;
		}
		
		case INSERT_DELETE : {
			switch (this.getBeanDBStatus()){
			case SELECT :  break;
			case INSERT : this.beanDBStatus = BeanDBStatus.INSERT_DELETE; break;
			case UPDATE :  break;
			case INSERT_DELETE :  break;
			case DELETE : break;
			case FEHLERHAFT : break;
			default : this.beanDBStatus = BeanDBStatus.FEHLERHAFT; break;
			};
			break;
		}
		
		case SELECT : {
			switch (this.getBeanDBStatus()){
			case SELECT : break;
			case INSERT : break;
			case UPDATE : this.beanDBStatus = BeanDBStatus.SELECT; break;
			case INSERT_DELETE :  break;
			case DELETE : break;
			case FEHLERHAFT : this.beanDBStatus = BeanDBStatus.SELECT; break;
			default : this.beanDBStatus = BeanDBStatus.FEHLERHAFT; break;
			};
			break;
		}
		
		case UPDATE : {
			switch (this.getBeanDBStatus()){
			case SELECT : this.beanDBStatus = BeanDBStatus.UPDATE; break;
			case INSERT :  break;
			case UPDATE :  break;
			case INSERT_DELETE :  break;
			case DELETE : break;
			case FEHLERHAFT : break;
			default : this.beanDBStatus = BeanDBStatus.FEHLERHAFT; break;
			};
			break;
		}
		
		}
	}
	
	protected void beanIstGeaendert()  {
		switch (this.getBeanDBStatus()){
		case SELECT : this.setBeanDBStatus(BeanDBStatus.UPDATE); break;
		case INSERT : break;
		case UPDATE : break;
		default : this.setBeanDBStatus(BeanDBStatus.FEHLERHAFT); break;
		}
	}
	
	public ArrayList<Fehler> getFehlerList() {
		if(fehlerList==null){
			fehlerList = new ArrayList<Fehler>();
		}
		return fehlerList;
	}
	public void setFehlerList(ArrayList<Fehler> fehlerList) {
		this.fehlerList = fehlerList;
	}

	public BenutzerBean getErfassungsBenutzer() {
		if (erfassungsBenutzer==null){
			erfassungsBenutzer = new BenutzerBean();
		}
		return erfassungsBenutzer;
	}

	public void setErfassungsBenutzer(BenutzerBean erfassungsBenutzer) {
		if (!getErfassungsBenutzer().equals(erfassungsBenutzer)){
			this.erfassungsBenutzer = erfassungsBenutzer;
			beanIstGeaendert();
		}
	}

	public Date getErfassungsDatum() {
		if (erfassungsDatum == null){
			erfassungsDatum = getAktuellesDatum();
		}
		return erfassungsDatum;
	}

	public void setErfassungsDatum(Date erfassungsDatum) {
		if(this.erfassungsDatum==null){
			this.erfassungsDatum = erfassungsDatum;
			beanIstGeaendert();
		}else{
			if(erfassungsDatum==null || this.getErfassungsDatum().getTime()!=erfassungsDatum.getTime()){
				this.erfassungsDatum = erfassungsDatum;
				beanIstGeaendert();
			}
		}
	}

	public BenutzerBean getAenderungsBenutzer() {
		if (aenderungsBenutzer==null){
			aenderungsBenutzer = new BenutzerBean();
		}
		return aenderungsBenutzer;
	}

	public void setAenderungsBenutzer(BenutzerBean aenderungsBenutzer) {
		if (!getAenderungsBenutzer().equals(aenderungsBenutzer)){
			this.aenderungsBenutzer = aenderungsBenutzer;
			beanIstGeaendert();
		}
		
	}

	public Date getAenderungsDatum() {
		if (aenderungsDatum == null){
			aenderungsDatum = getAktuellesDatum();
		}
		return aenderungsDatum;
	}

	public void setAenderungsDatum(Date aenderungsDatum) {
		if(this.aenderungsDatum==null){
			this.aenderungsDatum = aenderungsDatum;
			beanIstGeaendert();
		}else{
			if(this.aenderungsDatum.getTime()!=aenderungsDatum.getTime()){
				this.aenderungsDatum = aenderungsDatum;
				beanIstGeaendert();
			}
		}
		
	}
	
	/**
	 * Diese Methode sollte immer überschireben werden.
	 */
	@Override
	public int compareTo(Bean bean) {
		Log.log().severe("Die Methode sollte auf jeden Fall überschrieben werden!");
		if (bean==null){
			return -1;
		}else{
			if (this.equals(bean)){
				return 0;	
			}else{
				return -1;
			}
		}
	}
	
	
}
