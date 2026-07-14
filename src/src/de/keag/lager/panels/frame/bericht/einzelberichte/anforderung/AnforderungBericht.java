package de.keag.lager.panels.frame.bericht.einzelberichte.anforderung;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import de.keag.lager.core.Bean;
import de.keag.lager.core.email.SendJavaMail;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.IdLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.BaBeanDB;
import de.keag.lager.db.BaPosBeanDB;
import de.keag.lager.db.BerichtBeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.BerichtSpalte;
import de.keag.lager.panels.frame.bericht.BerichtZeile;
import de.keag.lager.panels.frame.bericht.BerichtZeileHtml;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;
import de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht;
import de.keag.lager.panels.frame.bestellanforderung.BaBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

public class AnforderungBericht extends EinzelBericht {
	
	private BaBeanDB baBeanDB;
	private BerichtBeanDB berichtBeanDB;
	private BerichtBean berichtBean;
	private BaBean baBean;
	private ArrayList<BaPosBean> baPosBeans;
//	private BaBean baBean2;
//	private ArrayList<BaPosBean> baPosBeans2;
	
	public AnforderungBericht() {
		super();
	}
	
	/*
	 * 1. BaBean wird gelesen.
	 * 2. BerichtBean wird erstellt.
	 * 3. BerichtBean wird in DB abgespeichert und neu eingelesen.
	 * @see de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht#erzeugeBerichtDbSatz(java.lang.Integer, de.keag.lager.panels.frame.bericht.Berichtart, de.keag.lager.panels.frame.bericht.Berichttyp)
	 */
	@Override
	public void erzeugeBerichtDbSatz(Berichttyp berichttyp, Berichtart berichtart, Integer berichtTypId) throws SQLException, LagerException {
		super.erzeugeBerichtDbSatz(berichttyp, berichtart, berichtTypId);
		//Aus der Bestellanforderung wird der DruckSatz erzeugt.
		setBaBean((BaBean)getBaBeanDB().selectAnhandID(berichtTypId)); //Anforderungsbean ist vorhanden und wird aus DB geholt.
		setBerichtBean(new BerichtBean());
		getBerichtBean().setId(this.getId()); //neue ID wird ermittelt
		getBerichtBean().setBerichtArt(this.getBerichtart());
		getBerichtBean().setBerichtTyp(this.getBerichttyp());
		getBerichtBean().setBerichtTypId(berichtTypId);
		getBerichtBean().setAktuellerBenutzer(Run.getOneInstance().getBenutzerBean());
		getBerichtBean().setBeanDBStatus(BeanDBStatus.INSERT);
		getBerichtBeanDB().saveBean(getBerichtBean()); //speichern
		//neu einlesen un ersetzen. Jetzt haben wir den Bericht-Kopf und Bericht daten in Form von 
		setBerichtBean((BerichtBean) getBerichtBeanDB().selectAnhandID(getBerichtBean().getId()));
	}

	/*
	 * Aus BaBean werden Druck-Daten-tabellen produziert.
	 * @see de.keag.lager.panels.frame.bericht.einzelberichte.EinzelBericht#uebernehmeDruckDaten()
	 */
	@Override
	public void uebernehmeDruckDaten() throws Exception {
		super.uebernehmeDruckDaten();
		LagerConnection.getOneInstance().startTransaction();
		try{
			//TODO Anfang, Programmmiert und nicht getestet
			
			//BaBean in bericht_bestellanforderung abspeichern
			uebernehmeneDruckDatenBestellanforderung();
			
			//BaPosBeans in bericht_bestellanfordurngpsotionen abspeichern
			//BaPositionen aus der DB einlesen
			setBaPosBeans(getBaBeanDB().sucheBaPosBeansAnhandBaBean(getBaBean()));
			for (int i = 0; i< getBaPosBeans().size();i++){
				uebernehmeneDruckDatenBestellanforderungPositionen(getBaPosBeans().get(i));
			}
			
			//TODO Ende
			
			LagerConnection.getOneInstance().commit();
		}catch (Exception e) {
			LagerConnection.getOneInstance().rollback();
			throw e;
		}
		
		
	}

	private void uebernehmeneDruckDatenBestellanforderung() throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String insertSQL = 
			"insert into bericht_bestellanforderung " +
			" (fk_bericht_id, " +
			" id, " +
			" erstellungsdatum, " +
			" status, " +
			
			" fk_herstellerLieferant, " +
			" fk_herstellerLieferant_name, " +
			" fk_herstellerLieferant_adresse_land, " +
			" fk_herstellerLieferant_adresse_plz, " +
			" fk_herstellerLieferant_adresse_stadt, " +
			" fk_herstellerLieferant_adresse_strasse, " +
			
			" fk_herstellerLieferant_telefon, " +
			" fk_herstellerLieferant_fax, " +
			" fk_herstellerLieferant_email, " +
			" fk_herstellerLieferant_ansprechpartner, " +
			" " +
			" fk_benutzerAbsender, " +
			" fk_benutzerAbsender_name, " +
			" fk_benutzerAbsender_vorname, " +
			" fk_benutzerAbsender_loginName, " +
			" fk_benutzerAbsender_email, " +
			
			" fk_benutzerAnnehmer, " +
			" fk_benutzerAnnehmer_name, " +
			" fk_benutzerAnnehmer_vorname, " +
			" fk_benutzerAnnehmer_loginName, " +
			" fk_benutzerAnnehmer_email, " +
			
			"email_ba_empfaenger)" +
			" values (?,?,?,?,?,?,?,?,?,?," +
			         "?,?,?,?,?,?,?,?,?,?," +
			         "?,?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId());
		getSqlParams().put("id", getBaBean().getId());
		getSqlParams().put("erstellungsdatum", getBaBean().getErstellungsDatum());
		getSqlParams().put("status", getBaBean().getStatus().JavaToDB());
		
		getSqlParams().put("fk_herstellerLieferant", getBaBean().getLhBean().getId());
		getSqlParams().put("fk_herstellerLieferant_name", getBaBean().getLhBean().getName());
		getSqlParams().put("fk_herstellerLieferant_adresse_land", getBaBean().getLhBean().getAdresse_land());
		getSqlParams().put("fk_herstellerLieferant_adresse_plz", getBaBean().getLhBean().getAdresse_plz());
		getSqlParams().put("fk_herstellerLieferant_adresse_stadt", getBaBean().getLhBean().getAdresse_stadt());
		getSqlParams().put("fk_herstellerLieferant_adresse_strasse", getBaBean().getLhBean().getAdresse_strasse());
		getSqlParams().put("fk_herstellerLieferant_telefon", getBaBean().getLhBean().getTelefon());
		getSqlParams().put("fk_herstellerLieferant_fax", getBaBean().getLhBean().getFax());
		getSqlParams().put("fk_herstellerLieferant_email", getBaBean().getLhBean().getEmail());
		getSqlParams().put("fk_herstellerLieferant_ansprechpartner", getBaBean().getLhBean().getAnsprechpartner());
		
		getSqlParams().put("fk_benutzerAbsender", getBaBean().getAbsenderBenutzerBean().getId());
		getSqlParams().put("fk_benutzerAbsender_name", getBaBean().getAbsenderBenutzerBean().getName());
		getSqlParams().put("fk_benutzerAbsender_vorname", getBaBean().getAbsenderBenutzerBean().getVorname());
		getSqlParams().put("fk_benutzerAbsender_loginName", getBaBean().getAbsenderBenutzerBean().getLoginName());
		getSqlParams().put("fk_benutzerAbsender_email", getBaBean().getAbsenderBenutzerBean().getEmail());
		
		getSqlParams().put("fk_benutzerAnnehmer", getBaBean().getAnnehmerBenutzerBean().getId());
		getSqlParams().put("fk_benutzerAnnehmer_name", getBaBean().getAnnehmerBenutzerBean().getName());
		getSqlParams().put("fk_benutzerAnnehmer_vorname", getBaBean().getAnnehmerBenutzerBean().getVorname());
		getSqlParams().put("fk_benutzerAnnehmer_loginName", getBaBean().getAnnehmerBenutzerBean().getLoginName());
		getSqlParams().put("fk_benutzerAnnehmer_email", getBaBean().getAnnehmerBenutzerBean().getEmail());
		
		getSqlParams().put("email_ba_empfaenger", getBaBean().getEmail());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
	    System.out.println(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	}

	private void uebernehmeneDruckDatenBestellanforderungPositionen(BaPosBean baPosBean) throws SQLException, LagerException {
		//Aus der BaBean werden die Daten nach Datenbank übernommen
		getSqlParams().clear(); //Vorbereitung auf die nächste SQL-Anweisung
		String 	insertSQL = "insert into bericht_bestellanforderungsposition " +
			" (fk_bericht_id, " +
			" id, " +
			" fk_bestellanforderung, " +
			" fk_artikel, " +
			" fk_artikel_bezeichnung, " +
			" fk_artikel_typ, " +
			" fk_artikel_keg_nr, " +
			" bestellmenge, " +
			" liefertermin, " +
			" status, " +
			" fk_kostenstelle, " +
			" fk_kostenstelle_name, " +
			" fk_kostenstelle_nummer , " +
			" fk_mengeneinheit , " +
			" fk_katalog, " +
			" fk_katalog_name, " +
			" lieferantenbestellnummer, " +
			" katalogseite, " +
			" katalogpreis)" +
			" values " +
			" (?,?,?,?,?,?,?,?,?,?," +
			 " ?,?,?,?,?,?,?,?,?)";
		//Params werden gesetzt.
		getSqlParams().put("fk_bericht_id", this.getId()); 
		getSqlParams().put("id", baPosBean.getId());
		getSqlParams().put("fk_bestellanforderung", getBaBean().getId());
		getSqlParams().put("fk_artikel", baPosBean.getArtikelBean().getId());
		getSqlParams().put("fk_artikel_bezeichnung", baPosBean.getArtikelBean().getBezeichnung());
		getSqlParams().put("fk_artikel_typ", baPosBean.getArtikelBean().getTyp());
		getSqlParams().put("fk_artikel_keg_nr", baPosBean.getArtikelBean().getKeg_nr());
		getSqlParams().put("bestellmenge", baPosBean.getBestellmenge());
		getSqlParams().put("liefertermin", baPosBean.getLiefertermin());
		getSqlParams().put("status", baPosBean.getStatus().JavaToDB());
		getSqlParams().put("fk_kostenstelle", baPosBean.getKostenstelle().getId());
		getSqlParams().put("fk_kostenstelle_name", baPosBean.getKostenstelle().getName());
		getSqlParams().put("fk_kostenstelle_nummer", baPosBean.getKostenstelle().getNummer());
		getSqlParams().put("fk_mengeneinheit", baPosBean.getMengenEinheitBean().getId());
		getSqlParams().put("fk_katalog", baPosBean.getKatalogBean().getId());
		getSqlParams().put("fk_katalog_name", baPosBean.getKatalogBean().getName());
		getSqlParams().put("lieferantenbestellnummer", baPosBean.getLieferantenbestellnummer());
		getSqlParams().put("katalogseite", baPosBean.getKatalogseite());
		getSqlParams().put("katalogpreis", baPosBean.getKatalogpreis());
		
		//SQL absetzen
		PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(insertSQL);
		//Parameter in den Statement übernehmen
		getSqlParams().setParams(stmt);
		//Statement protokolieren
	    Log.log().finest(stmt.toString());
	    //Statement ausführen
	    stmt.execute();
	}

	
	@Override
	public void verarbeiteBericht() throws SQLException, LagerException, Exception {
		super.verarbeiteBericht();
//		SendJavaMail sendJavaMail = new SendJavaMail();
//		String toEmail = "wladimir.stoll@gmx.de";
		String toEmail = "eugen_schmidt3@freenet.de";
//		toEmail = getBaBean().getEmail();
		String messageEMail = "--- kein Inhalt ---";
		String messageEMailHTML = "<html> <head> Kein Inhalt im head </head> <body> Kein Inhalt im head </body> </html> ";
		
		if (getBaBean()!=null){
			
			
			if (toEmail.equalsIgnoreCase(Run.getOneInstance().getLagerProperties().getLagerEmailSuperUser()))//			if (!toEmail.equalsIgnoreCase("thurner@keag.de"))
			{
				String subject = "Bestellanforderung "+getBaBean().getId() + ", Abteilung:"+ getBaBean().getAbsenderBenutzerBean().getErsteAbteilungDesBenutzers().getAbteilungName();
				messageEMail = erzeugeEmailInhaltBestellanforderung(getBaBean(),getBaPosBeans());
				SendJavaMail.postMailToGmxLagerKEG(toEmail, subject, messageEMail, false);
			}else{
				String subject = "Bestellung "+getBaBean().getId() + ", Abteilung:"+ getBaBean().getAbsenderBenutzerBean().getErsteAbteilungDesBenutzers().getAbteilungName();
				messageEMail = erzeugeEmailInhaltBestellung(getBaBean(),getBaPosBeans());
				SendJavaMail.postMailToGmxLagerKEG(toEmail, subject, messageEMail, true);
				SendJavaMail.postMailToGmxLagerKEG(
						Run.getOneInstance().getLagerProperties().getLagerEmailSuperUser(), 
						"KOPIE: " + subject, 
						messageEMail, 
						true);
			}
		}else{
			throw new LagerException(new Fehler("Keine Daten zu versenden!"));
		}
//		SendJavaMail.postMailToGmxLagerKEG(toEmail, subject, messageEMail, false);
//		messageEMailHTML = textToHtml(messageEMail,"Bestellanforderung: "+getBaBean().getId());
		
////		messageEMailHTML = textToHtml(messageEMail,"Bestellanforderung: "+getBaBean().getId());
//		SendJavaMail.postMailToGmxAttachmentKEAG(
//				toEmail, 
//				subject, 
//				"", //messageEMail, 
//				new String[]{},
////				messageEMailHTML);
//				messageEMail);
////		sendJavaMail.postMail(
////				host, 
////				port, 
////				toEmail, 
////				subject, 
////				message, 
////				fromEmail, 
////				kdUser, 
////				kdPwd, 
////				attachments, 
////				outputStream);
////		
		Log.log().severe("nicht implementiert");
	}
	
	private String erzeugeEmailInhaltBestellung(BaBean baBean,
			ArrayList<BaPosBean> baPosBeans) {
		Integer tabellenBreite = 1000;
//		private String erzeugeEmailInhaltBestellanforderung(BaBean baBean2,
//	    ArrayList<BaPosBean> baPosBeans2) {
		String inhalt = "<html><head><title>Bestellung</title></head><body>";
		
		
		
		//**************  Kopf **************
		getBerichtZeileHtml().getBerichtSpalten().clear();
		getBerichtZeileHtml().setSpaltenHoehe(2);
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 300," "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 300," "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 300," "));
		inhalt = inhalt  + 
		"<table style=\"text-align:left\" border=\"0\"  width=\"" + tabellenBreite.toString() +"\"> <colgroup width=\"500\" span=\""+
			new Integer(getBerichtZeileHtml().getBerichtSpalten().size()).toString()+
		"\"> </colgroup>";
		
		
		//---
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt("Kemptener Eisengießerei Adam Hönig AG <br>Postfach 1849 . 87408 Kempten/Allgäu"); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(	"im Allmey 4 1/2 <br>87435 Kempten ");
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		//---
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt("");

		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(
				"<br>Abtelung: " + baBean.getAbsenderBenutzerBean().getErsteAbteilungDesBenutzers().getAbteilungName() + 
				"<br>Bearbeiter:" + baBean.getAbsenderBenutzerBean().getName() +
				"<br>Telefon:" + baBean.getAbsenderBenutzerBean().getId() +
				"<br>E-mail:" + baBean.getAbsenderBenutzerBean().getEmail() +
				"<br>Fax: 0831 / 58 11 014" + 
				"<br>" 
				);
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		//Lieferant und Datum
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(baBean.getLhBean().getName().toString() + "" +
				"<br>"+ baBean.getLhBean().getAnsprechpartner().toString() +
				"<br>" + baBean.getLhBean().getAdresse_strasse().toString() +
				"<br>" + baBean.getLhBean().getAdresse_land().toString() +
				" " + baBean.getLhBean().getAdresse_plz().toString()+
				" " + baBean.getLhBean().getAdresse_stadt().toString()
				); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("Datum:" + Bean.getAktuellesDatum());
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		//Bestellung, Nummer
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt("Bestellung Nr. " + baBean.getId().toString()); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("");
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		
		inhalt = inhalt  + "</table>";
		
		inhalt = inhalt  + " " + "<br>";
		inhalt = inhalt  + " " + "<br>";
		inhalt = inhalt  + "Sehr geehrte" + baBean.getLhBean().getAnsprechpartner().toString() + ",<br>";
		inhalt = inhalt  + "gem. vereinbarten Konditionen bestellen wir hiermit folgende Artikel:<br>";
		inhalt = inhalt  + " " + "<br>";
		inhalt = inhalt  + " " + "<br>";

		inhalt = EinzelBericht.textToHtml(inhalt, ""); //umbrüche durch <br> ersetzen
		
		
		//************** Beging Positionen **************
		inhalt = inhalt  + 	"<table border=\"1\"  width=\""+  tabellenBreite + "\">" ;
//		"<table border=\"1\"  width=\""+  tabellenBreite + "\"> <colgroup width=\"200\" span=\""+
//		new Integer(getBerichtZeileHtml().getBerichtSpalten().size()).toString()+
//		"\"> </colgroup>";

		// ************** Positionen **************
		getBerichtZeileHtml().getBerichtSpalten().clear();
		getBerichtZeileHtml().setSpaltenHoehe(2);
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Pos","", 30," "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Menge","", 50, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("ME","", 60, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Artikelbezeichnung/ ","     Typ", 250, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Hersteller","", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Lieferant.","bestellnr.", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Katalog/","Seite", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte(" Liefer-","Termin /KW", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("   KE-","ArtikelNr.", 90, " "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("Kosten-","stelle", 90, " "));
//		//erste Linie über den Spalten
//		inhalt = inhalt  + getBerichtZeileHtml().getDurchgehendeLinie() + "\n";
//		
		//Spalten-Bezeichnungen , erste Zeile
		inhalt = inhalt  + getBerichtZeileHtml().getSpaltenNamenZeile(1) + "\n";
		//Spalten-Bezeichnungen, zweite Zeile
		inhalt = inhalt  + getBerichtZeileHtml().getSpaltenNamenZeile(2) + "\n";
		
//		for(BaPosBean baPosBean : baPosBeans){ //identisch mit den nächsten 2 Zeilen
		for(int i = 0; i<baPosBeans.size();i++){
			BaPosBean baPosBean = baPosBeans.get(i);

			//Alle Spalten mit Daten versorgen
			getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(new Integer(i+1).toString()); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(baPosBean.getBestellmenge().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(baPosBean.getMengenEinheitBean().getName()); //nächste Spalte
    		getBerichtZeileHtml().getBerichtSpalten().get(3).setInhalt(baPosBean.getArtikelBean().getBezeichnung(). toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(4).setInhalt(baPosBean.getArtikelBean().getHersteller().getName().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(5).setInhalt(baPosBean.getLieferantenbestellnummer().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(6).setInhalt(baPosBean.getKatalogBean().getName().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(7).setInhalt(baPosBean.getLiefertermin().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(8).setInhalt(baPosBean.getArtikelBean().getKeg_nr().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(9).setInhalt(baPosBean.getKostenstelle().toString()); //zweite Splate

			
			//Zeile drucken
			inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile("#CCEECC") + "\n";
	
			
			//Alle Spalten mit Daten versorgen
			getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt(""); //erste Splate
    		getBerichtZeileHtml().getBerichtSpalten().get(3).setInhalt(
    				baPosBean.getArtikelBean().getTyp().toString()); //zweite Splate
			getBerichtZeileHtml().getBerichtSpalten().get(4).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(5).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(6).setInhalt(
					baPosBean.getKatalogseite()); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(7).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(8).setInhalt(""); //erste Splate
			getBerichtZeileHtml().getBerichtSpalten().get(9).setInhalt(""); //erste Splate

			//Zeile drucken
			inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile("#CCEECC") + "\n";
			
//			//abschliessende  Linie unter den Spalten-Daten
//			inhalt = inhalt  + getBerichtZeileHtml().getDurchgehendeLinie() + "\n";
		}
		inhalt = inhalt  + "</table>";
		//************** Ende Positionen **************
		
		//************** FUSS **************
		
		
//		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Den Preis und Lieferzeit nehmen Sie bitte mit ihrer Auftragsbestätigung ") + "\n";
		inhalt = inhalt + "<br>Liefertermin sieh Tabelle. ";
		if (baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().size()>0){
			inhalt = inhalt + "Abteilung: " + baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().get(0).getAbteilung().getAbteilungName();
		}
		
		inhalt = inhalt + "<br>Lieferbedingungen frei Haus, einschl. Verpackung" ;
		inhalt = inhalt + "<br>Zahlungsbedingungen: 8Tage, 2% Skonto, 30 Tage netto" ;
		inhalt = inhalt + "<br>";
		inhalt = inhalt + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Mit freundlichen Grüßen ") + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Kemptener Eisengießerei ") + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Adam Hönig AG") + "<br>";
		inhalt = inhalt + "i.A. " +baBean.getAbsenderBenutzerBean().getName().toString() + "<br>";
		inhalt = inhalt + " " + "<br>";
		inhalt = inhalt + BerichtZeileHtml.ersetzeUmlaute("Bestellung gilt ohne Unterschrift als erteilt ") + "<br>";
		inhalt = inhalt + " " + "<br>";
		inhalt = inhalt + " " + "<br>";
		inhalt = inhalt + " " + "<br>";
		
		
		getBerichtZeileHtml().getBerichtSpalten().clear();
		getBerichtZeileHtml().setSpaltenHoehe(2);
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 100," "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 100," "));
		getBerichtZeileHtml().getBerichtSpalten().add(new BerichtSpalte("","", 100," "));
		inhalt = inhalt  + 
		"<table style=\"text-align:left\" border=\"0\"  width=\"" + tabellenBreite.toString() +"\"> ";
		//---
		getBerichtZeileHtml().getBerichtSpalten().get(0).setInhalt("Aktiengesellschaft,Sitz Kempten" +
				"<br>Register Kempten HRB 7539" +
				"<br>AR-Vorsitzender: Thomas Wäschle" +
				"<br>Vorstand Roland Hübner"); //erste Splate
		getBerichtZeileHtml().getBerichtSpalten().get(1).setInhalt(""); 
		getBerichtZeileHtml().getBerichtSpalten().get(2).setInhalt("Bankverbindung:" +
				"<br>Sparkasse Allgäu(BLZ 733 500 00)310 014 709) " +
				"<br>LB Baden-Württemberg(BLZ 600 501 01)454 185 7" +
				"<br>" +
				"");
		//Zeile drucken
		inhalt = inhalt  + getBerichtZeileHtml().getDatenZeile(null);
		
		inhalt = inhalt + "</table>";
		inhalt = inhalt + "</body></html>";
		return inhalt;
	}

	/**
	 * Liste erstellen aus (Anforderung und Anforderungspositionen)
	 * @param baBean
	 * @param baPosBeans
	 * @return
	 */
//	private String erzeugeEmailInhaltBestellung(BaBean baBean,
//			ArrayList<BaPosBean> baPosBeans) {
	private String erzeugeEmailInhaltBestellanforderung(BaBean baBean,
		    ArrayList<BaPosBean> baPosBeans) {
		String inhalt = "";

		
		// ************** Positionen **************
		getBerichtZeile().setSymbolFuerHorizontaleLinie("-");
		getBerichtZeile().setSymbolFuerVertikaleLinie("|");
		getBerichtZeile().getBerichtSpalten().clear();
		getBerichtZeile().setSpaltenHoehe(2);
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Pos","", 3," "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Menge","", 5, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("  ME","", 6, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte(" Artikelbezeichnung/ ","   Typ", 35, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Hersteller","", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Lieferant.","bestellnr.", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Katalog/","Seite", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Liefer-","Termin /KW", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("   KE-","ArtikelNr.", 10, " "));
		getBerichtZeile().getBerichtSpalten().add(new BerichtSpalte("Kosten-","stelle", 10, " "));
		
		//**************  Kopf ************** 
		if (baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().size()>0){
			inhalt = inhalt + "Abteilung:" + baBean.getAbsenderBenutzerBean().getBenutzerAbteilungBeans().get(0).getAbteilung().getAbteilungName() +"\n";
		}
		inhalt = inhalt + "Bearbeiter:" + baBean.getAbsenderBenutzerBean().getName().toString() + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
//		inhalt = inhalt + "---------------------------------------------------------------------"+ "\n";
		inhalt = inhalt + " " + "\n";
		inhalt = inhalt  + "Gesendet am :" + Bean.getAktuellesDatum().toString() + "\n";
		inhalt = inhalt  + "         an :" + baBean.getAnnehmerBenutzerBean().getEmail().toString()+"\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + "                  Bestellanforderung Nr." + baBean.getId() + "\n";
		inhalt = inhalt  + " " + "\n";
		inhalt = inhalt  + " " + "\n";
		
		//erste Linie über den Spalten
		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
		
		//Spalten-Bezeichnungen , erste Zeile
		inhalt = inhalt  + getBerichtZeile().getSpaltenNamenZeile(1) + "\n";
		
		//Spalten-Bezeichnungen, zweite Zeile
		inhalt = inhalt  + getBerichtZeile().getSpaltenNamenZeile(2) + "\n";
		
		//abschliessende  Linie unter den Spalten-Bezeichungen
		inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
		
		
//		for(BaPosBean baPosBean : baPosBeans){ //identisch mit den nächsten 2 Zeilen
		for(int i = 0; i<baPosBeans.size();i++){
			BaPosBean baPosBean = baPosBeans.get(i);

			//Alle Spalten mit Daten versorgen
			getBerichtZeile().getBerichtSpalten().get(0).setInhalt(new Integer(i+1).toString()); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(1).setInhalt(baPosBean.getBestellmenge().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(2).setInhalt(baPosBean.getMengenEinheitBean().getName()); //nächste Spalte
    		getBerichtZeile().getBerichtSpalten().get(3).setInhalt(baPosBean.getArtikelBean().getBezeichnung(). toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(4).setInhalt(baPosBean.getArtikelBean().getHersteller().getName().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(5).setInhalt(baPosBean.getLieferantenbestellnummer().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(6).setInhalt(baPosBean.getKatalogBean().getName().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(7).setInhalt(baPosBean.getLiefertermin().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(8).setInhalt(baPosBean.getArtikelBean().getKeg_nr().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(9).setInhalt(baPosBean.getKostenstelle(). toString()); //zweite Splate

			
			//Zeile drucken
			inhalt = inhalt  + getBerichtZeile().getDatenZeile() + "\n";
	
			
			//Alle Spalten mit Daten versorgen
			getBerichtZeile().getBerichtSpalten().get(0).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(1).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(2).setInhalt(""); //erste Splate
    		getBerichtZeile().getBerichtSpalten().get(3).setInhalt(
    				baPosBean.getArtikelBean().getTyp().toString()); //zweite Splate
			getBerichtZeile().getBerichtSpalten().get(4).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(5).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(6).setInhalt(
					baPosBean.getKatalogseite()); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(7).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(8).setInhalt(""); //erste Splate
			getBerichtZeile().getBerichtSpalten().get(9).setInhalt(""); //erste Splate

			//Zeile drucken
			inhalt = inhalt  + getBerichtZeile().getDatenZeile() + "\n";
			
			//abschliessende  Linie unter den Spalten-Daten
			inhalt = inhalt  + getBerichtZeile().getDurchgehendeLinie() + "\n";
		}
		

		
		
		
		//************** FUSS **************
		inhalt = inhalt + " " + "\n";
		inhalt = inhalt + " " + "\n";
		inhalt = inhalt + "Gewuenschter Liefertermin :  \n" ;
		inhalt = inhalt + "!!! BITTE SOFORT LIEFERN !!! \n" ;
		String linie = "1234567890123456789012345678901234567890\n" ;
		inhalt = inhalt + linie;
		inhalt = inhalt + linie.substring(0,11) + " , Lieferant, " +linie.substring(25); 
		return inhalt;
	}
	
	

	private BaBeanDB getBaBeanDB() {
		if (baBeanDB ==null){
			baBeanDB = new BaBeanDB();
		}
		return baBeanDB;
	}

	private BerichtBeanDB getBerichtBeanDB() {
		if (berichtBeanDB ==null){
			berichtBeanDB = new BerichtBeanDB();
		}
		return berichtBeanDB;
	}

	private BerichtBean getBerichtBean() {
		return berichtBean;
	}

	private void setBerichtBean(BerichtBean berichtBean) {
		this.berichtBean = berichtBean;
	}

	private BaBean getBaBean() {
		return baBean;
	}

	private void setBaBean(BaBean baBean) {
		this.baBean = baBean;
	}

	private ArrayList<BaPosBean> getBaPosBeans() {
		return baPosBeans;
	}

	private void setBaPosBeans(ArrayList<BaPosBean> baPosBeans) {
		this.baPosBeans = baPosBeans;
	}


//	private BaBean getBaBean2() {
//		return baBean2;
//	}
//
//	private void setBaBean2(BaBean baBean2) {
//		this.baBean2 = baBean2;
//	}

//	private ArrayList<BaPosBean> getBaPosBeans2() {
//		return baPosBeans2;
//	}
//
//	private void setBaPosBeans2(ArrayList<BaPosBean> baPosBeans2) {
//		this.baPosBeans2 = baPosBeans2;
//	}

}
