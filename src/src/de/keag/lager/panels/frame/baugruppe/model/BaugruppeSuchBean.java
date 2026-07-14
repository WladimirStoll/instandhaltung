package de.keag.lager.panels.frame.baugruppe.model;



import de.keag.lager.core.ISuchBean;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;



public class BaugruppeSuchBean implements ISuchBean {
//	private Date erstellungsDatumVon;
//	private Date erstellungsDatumBis;
	private ArtikelBean artikelBean;
	private String baugruppeName;
	private HalleBean halleBean;
	public BaugruppeSuchBean() {
		super();
	}
	

	public ArtikelBean getArtikelBean() {
		if(artikelBean==null){
			artikelBean = new ArtikelBean();
		}
		return artikelBean;
	}

	public void setArtikelBean(ArtikelBean abteilungBean) {
		this.artikelBean = abteilungBean;
	}

	public String getBaugruppeName() {
		if(baugruppeName==null){
			baugruppeName = "";
		}
		return baugruppeName;
	}

	public void setBaugruppeName(String baugruppeName) {
		this.baugruppeName = baugruppeName;
	}


	public HalleBean getHalleBean() {
		if(halleBean==null){
			halleBean =new HalleBean();
		}
		return halleBean;
	}


	public void setHalleBean(HalleBean halleBean) {
		this.halleBean = halleBean;
	}


}
