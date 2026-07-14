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
