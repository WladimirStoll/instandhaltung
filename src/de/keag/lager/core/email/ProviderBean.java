package de.keag.lager.core.email;

import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;

public class ProviderBean extends Bean {
	private Integer id  = 0; 
	private String name = ""; 
	private String mail_smtp_host = "";
	private Integer mail_smtp_port = 0; 
	private String mail_smtp_user  = ""; 
	private String mail_smtp_pwd = "";
	private Boolean mail_smtp_auth = false;
	
	public ProviderBean() {
		super();
	}

	@Override
	public ModelKnotenBean getModelKnotenBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if(id==null){
			id = 0;
		}
		if(!this.id.equals(id)){
			this.id = id;
			beanIstGeaendert();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name==null){
			name = "";
		}
		if(!this.name.equals(name)){
			this.name = name;
			beanIstGeaendert();
		}
	}

	public String getMail_smtp_host() {
		return mail_smtp_host;
	}

	public void setMail_smtp_host(String mailSmtpHost) {
		if(mailSmtpHost==null){
			mailSmtpHost = "";
		}
		if(!this.mail_smtp_host.equals(mailSmtpHost)){
			this.mail_smtp_host = mailSmtpHost;
			beanIstGeaendert();
		}
	}

	public Integer getMail_smtp_port() {
		return mail_smtp_port;
	}

	public void setMail_smtp_port(Integer mailSmtpPort) {
		mail_smtp_port = mailSmtpPort;
	}

	public String getMail_smtp_user() {
		return mail_smtp_user;
	}

	public void setMail_smtp_user(String mailSmtpUser) {
		if(mailSmtpUser==null){
			mailSmtpUser = "";
		}
		if(!this.mail_smtp_user.equals(mailSmtpUser)){
			this.mail_smtp_user = mailSmtpUser;
			beanIstGeaendert();
		}
	}

	public String getMail_smtp_pwd() {
		return mail_smtp_pwd;
	}

	public void setMail_smtp_pwd(String mailSmtpPwd) {
		if(mailSmtpPwd==null){
			mailSmtpPwd = "";
		}
		if(!this.mail_smtp_pwd.equals(mailSmtpPwd)){
			this.mail_smtp_pwd = mailSmtpPwd;
			beanIstGeaendert();
		}
	}

	public Boolean getMail_smtp_auth() {
		return mail_smtp_auth;
	}

	public void setMail_smtp_auth(Boolean mailSmtpAuth) {
		if(mail_smtp_auth != mailSmtpAuth){
			mail_smtp_auth = mailSmtpAuth;
			beanIstGeaendert();
		}
	}

	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof ProviderBean){
			ProviderBean tempBean = (ProviderBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId() == tempBean.getId());
			}
		}else return false;
    }

	@Override
	public int hashCodeForModelKnoten() {
		// TODO Auto-generated method stub
		return Konstanten.HASH_OFFSET_PROVIDER + getId();
	}
	
	
	
}
