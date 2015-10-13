package de.keag.lager.panels.frame.login;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;

public class LoginBean extends Bean{
	private Integer id;
	private String name;
	private String vorname;
	private String loginName; //name, mit dem sich der Benutzer einloggt.
	private String password;
	private BeanDBStatus beanStatus = BeanDBStatus.FEHLERHAFT;
	
	public BeanDBStatus getBeanStatus() {
		return beanStatus;
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return getId();
	}
	

	public void setBeanStatus(BeanDBStatus beanStatus) {
		this.beanStatus = beanStatus;
	}

	/*
	 * Konstruktor
	 */
	public LoginBean(){
		super();
		id = 0;
		name = "";
		vorname = "";
		loginName = "";
		password = "";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return this.getVorname() + " " + this.getName() + " ID:" + this.getId();
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		Log.log().severe("nicht implementiert");
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof LoginBean){
			LoginBean tempBean = (LoginBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
	}
}
