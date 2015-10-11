/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
package de.keag.lager.panels.frame.artikel;

import java.awt.Component;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;

import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

public class ArtikelBean extends Bean{
	private Integer id;
	private String bezeichnung; 
	private String bemerkung; 
	private String typ;
	private Integer keg_nr;
	private Integer mindestbestand; 
	private KostenstelleBean kostenstelle = null;
	private MengenEinheitBean mengenEinheitBean = null;
	private LhBean hersteller; 
	private Integer empfohlene_bestellmenge;
	private ArrayList<String> oberArtikelBeansInfos;
	private VaterArtikelArray oberArtikelBeans;
	private VaterArtikelArray binMitgliedInDiesemArray;
//	private ArrayList<BestandspackstueckBean> bestandspackstueckBeans = null;
	
	public ArtikelBean() {
		super();
	}
	
	@Override
	public int hashCodeForModelKnoten() {
		return Konstanten.HASH_OFFSET_ARTIKEL + getId();
	}
	
	public Integer getId() {
		if (id ==null){
			id = 0;
		}
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		beanIstGeaendert();
		
	}
	public String getBezeichnung() {
		if (bezeichnung==null){
			bezeichnung = "";
		}
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		if (!getBezeichnung().equals(bezeichnung)){
			this.bezeichnung = bezeichnung;
			beanIstGeaendert();
		}
	}
	public String getTyp() {
		if(typ==null){
			typ = "";
		}
		return typ;
	}
	public void setTyp(String typ) {
		if(!getTyp().equals(typ)){
			this.typ = typ;
			beanIstGeaendert();
		}
	}
	public Integer getKeg_nr() {
		if (keg_nr==null){
			keg_nr = 0;
		}
		return keg_nr;
	}
	public void setKeg_nr(Integer kegNr) {
		if(!getKeg_nr().equals(kegNr)){
			this.keg_nr = kegNr;
			beanIstGeaendert();
		}
	}
	public Integer getMindestbestand() {
		if(mindestbestand==null){
			mindestbestand = 0;
		}
		return mindestbestand;
	}
	public void setMindestbestand(Integer mindestbestand) {
		if(!getMindestbestand().equals(mindestbestand)){
			this.mindestbestand = mindestbestand;
			beanIstGeaendert();
		}
	}
	public KostenstelleBean getKostenstelle() {
		if (kostenstelle==null){
			kostenstelle = new KostenstelleBean();
		}
		return kostenstelle;
	}
	public void setKostenstelle(KostenstelleBean kostenstelle) {
		if (!getKostenstelle().equals(kostenstelle)){
			this.kostenstelle = kostenstelle;
			beanIstGeaendert();
		}
	}
	public MengenEinheitBean getMengenEinheitBean() {
		if (mengenEinheitBean==null){
			mengenEinheitBean = new MengenEinheitBean();
		}
		return mengenEinheitBean;
	}
	public void setMengenEinheitBean(MengenEinheitBean mengenEinheitBean) {
		if(!getMengenEinheitBean().equals(mengenEinheitBean)){
			this.mengenEinheitBean = mengenEinheitBean;
			beanIstGeaendert();
		}
	}
	
//	@Override
//	public ModelKnotenBean getModelKnotenBean() {
//		Log.log().severe("nicht implementiert");
//		return null;
//	}
	
//	@Override
//	public void setModelKnotenBean(ModelKnotenBean modelKnotenBean) {
//		Log.log().severe("nicht implementiert");
//	}

	public LhBean getHersteller() {
		if (hersteller==null){
			hersteller = new LhBean();
		}
		return hersteller;
	}

	public void setHersteller(LhBean hersteller) {
		if(!getHersteller().equals(hersteller)){
			this.hersteller = hersteller;
			beanIstGeaendert();
		}
	}

	public Integer getEmpfohlene_bestellmenge() {
		if (empfohlene_bestellmenge==null){
			empfohlene_bestellmenge = 0;
		}
		return empfohlene_bestellmenge;
	}

	public void setEmpfohlene_bestellmenge(Integer empfohleneBestellmenge) {
		if (!getEmpfohlene_bestellmenge().equals(empfohleneBestellmenge)){
			empfohlene_bestellmenge = empfohleneBestellmenge;
			beanIstGeaendert();
		}
	}

	@Override
	public ArrayList<Fehler> pruefeEigeneDaten() {
		getFehlerList().clear();
		
		if(getId().equals(0)){
			getFehlerList().add(new Fehler(10));
		}
		
		if(getBezeichnung().trim().equals("")){ 
			getFehlerList().add(new Fehler(103));
		}
		
		if(getTyp().trim().equals("")){ 
			getFehlerList().add(new Fehler(104));
		}
		
		if(getKeg_nr().equals(0)){ 
			getFehlerList().add(new Fehler(105));
		}
		
//		if(getMindestbestand().equals(0)){ 
//			getFehlerList().add(new Fehler());
//		}
//		
//		if(getKostenstelle().trim().equals("")){ 
//			getFehlerList().add(new Fehler());
//		}
		
		if(getMengenEinheitBean().getId().equals(0)){ 
			getFehlerList().add(new Fehler(106));
		}
		
		if(getHersteller().getId().equals(0)){ 
			getFehlerList().add(new Fehler(107));
		}
		
		if(getEmpfohlene_bestellmenge().equals(0)){ 
			getFehlerList().add(new Fehler(108));
		}
		return getFehlerList();
	}
	
	@Override
	public boolean equals(Bean bean) {
		if (bean instanceof ArtikelBean){
			ArtikelBean tempBean = (ArtikelBean) bean;
			if (tempBean==null){
				return false;
			}else{
				return (this.getId().equals(tempBean.getId()));
			}
		}else return false;
    }

	@Override
	public String toString() {
		return "KE-Nr.:" + getKeg_nr() + "    Bez.:" + getBezeichnung() + "    Typ:" + getTyp();
	}

//	public ArrayList<BestandspackstueckBean> getBestandspackstueckBeans() {
//		if (bestandspackstueckBeans==null){
//			bestandspackstueckBeans = new ArrayList<BestandspackstueckBean>();
//		}
//		return bestandspackstueckBeans;
//	}
//
//	public void setBestandspackstueckBeans(
//			ArrayList<BestandspackstueckBean> bestandspackstueckBeans) {
//		if (this.bestandspackstueckBeans != bestandspackstueckBeans){
//			this.bestandspackstueckBeans = bestandspackstueckBeans;
//			beanIstGeaendert();
//		}
//	}

	public String getBemerkung() {
		if (bemerkung==null){
			bemerkung= "";
		}
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		if(!getBemerkung().equals(bemerkung)){
			this.bemerkung = bemerkung;
			beanIstGeaendert();
		}
	}

//	public ArrayList<ArtikelBean> getOberArtikelBeans() {
//		if (oberArtikelBeans==null){
//			oberArtikelBeans = new ArrayList<ArtikelBean>();
//		}
//		return oberArtikelBeans;
//	}

	
	
//	public void setOberArtikelBeans(VaterArtikelArray oberArtikelBeans) {
//		if (!oberArtikelBeans.equals(this.oberArtikelBeans)){
//			this.oberArtikelBeans = oberArtikelBeans;
//			getOberArtikelBeansInfo().clear();
//			for(ArtikelBean vater : oberArtikelBeans){
//				fillOberArtikelBeansInfo(vater); 
//			}
////			beanIstGeaendert(); Dadurch wird die Bean nicht geändert.
//		}
//	}

	private void fillOberArtikelBeansInfo(ArtikelBean artikelBean) {
		if (artikelBean.getOberArtikelBeans().size()==0){
			//Keine Väter sind mehr da. Also die Zeile herausgeben.
			StringBuilder sb = new StringBuilder();
			while(artikelBean!=null){
//				artikelBean = artikelBean.getOberArtikelBeans().getKindBean(); //
				if (artikelBean.getBinMitgliedInDiesemArray()!=null){
					sb.append(artikelBean.getBezeichnung());
					sb.append(",");
					sb.append(artikelBean.getTyp());
					artikelBean = artikelBean.getBinMitgliedInDiesemArray().getKindBean();
				}else{
					artikelBean = null;
				}
				if (artikelBean!=null&&artikelBean!=this){
					sb.append("  >>  ");
				}
			}
			getOberArtikelBeansInfos().add(sb.toString());
		}else{
			//Vater sind da. Weiter 
			for(ArtikelBean bean : artikelBean.getOberArtikelBeans()){
				fillOberArtikelBeansInfo(bean);
			}
		}
	}

	public VaterArtikelArray getOberArtikelBeans() {
		if (oberArtikelBeans==null){
			oberArtikelBeans = new VaterArtikelArray(this);
		}
		return oberArtikelBeans;
	}

	public class VaterArtikelArray extends ArrayList<ArtikelBean>{
		ArtikelBean kindBean;
		
		public VaterArtikelArray(ArtikelBean kindBean) {
			super();
			setKindBean(kindBean);
		}
		public ArtikelBean getKindBean() {
			return kindBean;
		}
		public void fill(){
			ArtikelBean.this.getOberArtikelBeansInfos().clear();
			if (ArtikelBean.this.getOberArtikelBeans().size()>0){
				//es gibt mindestens einen Vater. Und wir möchten Info über ihn.
				ArtikelBean.this.fillOberArtikelBeansInfo(ArtikelBean.this);
			}
		}
		
		private void setKindBean(ArtikelBean kindBean) {
			this.kindBean = kindBean;
		}
		
		@Override
		public boolean add(ArtikelBean e) {
			e.setBinMitgliedInDiesemArray(this);
			return super.add(e);
		}
		
	}

	public ArrayList<String> getOberArtikelBeansInfos() {
		if (oberArtikelBeansInfos==null){
			oberArtikelBeansInfos = new ArrayList<String>();
		}
		return oberArtikelBeansInfos;
	}

	private VaterArtikelArray getBinMitgliedInDiesemArray() {
		return binMitgliedInDiesemArray;
	}

	private void setBinMitgliedInDiesemArray(
			VaterArtikelArray binMitgliedInDiesemArray) {
		this.binMitgliedInDiesemArray = binMitgliedInDiesemArray;
	}
	
}
