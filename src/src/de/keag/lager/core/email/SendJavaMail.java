package de.keag.lager.core.email;
//http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
//http://java.sun.com/products/javamail/
//http://openbook.galileocomputing.de/javainsel8/javainsel_18_012.htm#mjb306e4632c440d0524d00f224d4fa1bb
//http://www.rgagnon.com/javadetails/java-0504.html

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.*; 

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.mail.util.ByteArrayDataSource;

import de.keag.lager.core.main.Run;
 
public class SendJavaMail 
{ 
  public static void postMailToGmx(String toEmail, 
                               String subject, 
                               String message, 
                               String fromEmail ,
                               String gmxUser,
                               String gmxPwd) 
    throws MessagingException 
  { 
    Properties props = new Properties(); 
    props.put( "mail.smtp.host", "mail.gmx.net" );  //props.put( "mail.smtp.host", "mail.mailserver.com" );
    props.put( "mail.smtp.user", "wladimir.stoll@gmx.de" );  
    props.put( "mail.smtp.port", (int)25 );  
    props.put( "mail.smtp.auth", true );  
    Session session = Session.getDefaultInstance( props ); 
    Message msg = new MimeMessage( session ); 
    InternetAddress addressFrom = new InternetAddress( fromEmail ); 
    msg.setFrom( addressFrom ); 
    InternetAddress addressTo = new InternetAddress( toEmail ); 
    msg.setRecipient( Message.RecipientType.TO, addressTo ); 
    msg.setSubject( subject ); 
    msg.setContent( message, "text/plain" );
    //ohne Passwort
    //    Transport.send( msg ); 
    //mit Passwort (z.B. GMX)
    Transport tr = session.getTransport("smtp");
    tr.connect("mail.gmx.net", gmxUser, gmxPwd);
    msg.saveChanges();      // don't forget this
    tr.sendMessage(msg, msg.getAllRecipients());
    tr.close();
  }
  
	public static void postMailToGmxLagerKEG(
			String toEmail, 
			String subject,
			String message,
			Boolean isHtml)
	throws MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.gmx.net"); // props.put(
														// "mail.smtp.host",
														// "mail.mailserver.com"
														// );
		props.put("mail.smtp.user", Run.getOneInstance().getLagerProperties().getLagerKegGmxUser());
		props.put("mail.smtp.port", (int) 25);
		props.put("mail.smtp.auth", true);
		Session session = Session.getDefaultInstance(props);
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(Run.getOneInstance().getLagerProperties().getLagerKegGmxUser());//fromEmail
		msg.setFrom(addressFrom);
		InternetAddress addressTo = new InternetAddress(toEmail);
		msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setSubject(subject);
		if (isHtml){
			msg.setContent(message, "text/html");
		}else{
			msg.setContent(message, "text/plain");
		}
		// ohne Passwort
		// Transport.send( msg );
		// mit Passwort (z.B. GMX)
		Transport tr = session.getTransport("smtp");
		tr.connect("mail.gmx.net", 
				Run.getOneInstance().getLagerProperties().getLagerKegGmxUser(), 
				Run.getOneInstance().getLagerProperties().getLagerKegGmxPassword()
				);
		msg.saveChanges(); // don't forget this
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}  
  
	public static void postMailToGmxAttachmentKEAG(String toEmail, String subject,
			String message,  String[] attachments, String htmlText)
			throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.gmx.net"); // props.put(
														// "mail.smtp.host",
														// "mail.mailserver.com"
														// );
		props.put("mail.smtp.user", Run.getOneInstance().getLagerProperties().getLagerKegGmxUser());
		
		props.put("mail.smtp.port", (int) 25);
		props.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(props);
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(Run.getOneInstance().getLagerProperties().getLagerKegGmxUser());//fromEmail
		msg.setFrom(addressFrom);
		InternetAddress addressTo = new InternetAddress(toEmail);
		msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setSubject(subject);
		
		//E-Mail - Grundlage erzeugen
		MimeMultipart content = new MimeMultipart();
		//Text der E-Mail verfassen
		MimeBodyPart text = new MimeBodyPart();
		text.setText( message + " \n Sehen Sie auch im Anhang." ); 
		content.addBodyPart( text );  
		
		//Anhang(e) anhängen
		for(int i=0;attachments!=null && i<attachments.length;i++){
			javax.activation.DataSource fileDataSource = new javax.activation.FileDataSource( attachments[i] );
			BodyPart messageBodyPart = new MimeBodyPart(); 
			messageBodyPart.setDataHandler(new javax.activation.DataHandler(fileDataSource) ); 
			messageBodyPart.setFileName( attachments[i] );     // gibt dem Anhang einen Namen 
			content.addBodyPart( messageBodyPart );		
		}
		if (htmlText!=null&&!"".equals(htmlText)){
			//HTML-Text wird in ein Attachment umgewandelt
			java.io.InputStream inputStream = new StringBufferInputStream(htmlText); 
			BodyPart messageBodyPart = new MimeBodyPart(inputStream);
			messageBodyPart.isMimeType("text/html");
//			messageBodyPart.isMimeType("application/xhtml+xml");
			content.addBodyPart(messageBodyPart);
		}
		
		//Message erzeugen.
		msg.setContent(content);
		
		//Message verschicken
		Transport tr = session.getTransport("smtp");
		tr.connect("mail.gmx.net", 
				Run.getOneInstance().getLagerProperties().getLagerKegGmxUser(), 
				Run.getOneInstance().getLagerProperties().getLagerKegGmxPassword()
				);
		
		msg.saveChanges(); // don't forget this
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}
	
	
	public static void postMailToGmxAttachment(String toEmail, String subject,
			String message, String fromEmail, String gmxUser, String gmxPwd, String[] attachments)
			throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.gmx.net"); // props.put(
														// "mail.smtp.host",
														// "mail.mailserver.com"
														// );
		props.put("mail.smtp.user", "wladimir.stoll@gmx.de");
		props.put("mail.smtp.port", (int) 25);
		props.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(props);
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(fromEmail);
		msg.setFrom(addressFrom);
		InternetAddress addressTo = new InternetAddress(toEmail);
		msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setSubject(subject);
		
		//E-Mail - Grundlage erzeugen
		MimeMultipart content = new MimeMultipart();
		//Text der E-Mail verfassen
		MimeBodyPart text = new MimeBodyPart();
		text.setText( message + " Sehen Sie auch im Anhang." ); 
		content.addBodyPart( text );  
		
		//Anhang(e) anhängen
		for(int i=0;attachments!=null && i<attachments.length;i++){
			javax.activation.DataSource fileDataSource = new javax.activation.FileDataSource( attachments[i] );
			BodyPart messageBodyPart = new MimeBodyPart(); 
			messageBodyPart.setDataHandler(new javax.activation.DataHandler(fileDataSource) ); 
			messageBodyPart.setFileName( attachments[i] );     // gibt dem Anhang einen Namen 
			content.addBodyPart( messageBodyPart );		
		}
		
		//Message erzeugen.
		msg.setContent(content);
		
		//Message verschicken
		Transport tr = session.getTransport("smtp");
		tr.connect("mail.gmx.net", gmxUser, gmxPwd);
		msg.saveChanges(); // don't forget this
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}
	
	public static void postMailToKdAttachment(String toEmail, String subject,
			String message, String fromEmail, String kdUser, String kdPwd, String[] attachments)
			throws MessagingException {
		System.out.println("postMailToKdAttachment");
		String host = "smtp.superkabel.de";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", kdUser);
		props.put("mail.smtp.port", (int) 25);
		props.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(props);
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(fromEmail);
		msg.setFrom(addressFrom);
		InternetAddress addressTo = new InternetAddress(toEmail);
		msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setSubject(subject);
		
		//E-Mail - Grundlage erzeugen
		MimeMultipart content = new MimeMultipart();
		//Text der E-Mail verfassen
		MimeBodyPart text = new MimeBodyPart();
		text.setText( message + " Sehen Sie auch im Anhang." ); 
		content.addBodyPart( text );  
		
		//Anhang(e) anhängen
		for(int i=0;attachments!=null && i<attachments.length;i++){
			javax.activation.DataSource fileDataSource = new javax.activation.FileDataSource( attachments[i] );
			BodyPart messageBodyPart = new MimeBodyPart(); 
			messageBodyPart.setDataHandler(new javax.activation.DataHandler(fileDataSource) ); 
			messageBodyPart.setFileName( attachments[i] );     // gibt dem Anhang einen Namen 
			content.addBodyPart( messageBodyPart );		
		}
		
		//Message erzeugen.
		msg.setContent(content);
		
		//Message verschicken
		Transport tr = session.getTransport("smtp");
		tr.connect(host, kdUser, kdPwd);
		msg.saveChanges(); // don't forget this
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
		System.out.println("postMailToKdAttachment OK");
	}
	
	
	
	
	
	public static void postMail(
			String host,
			int port,
			String toEmail, 
			String subject,
			String message, 
			String fromEmail, 
			String kdUser, 
			String kdPwd, 
			String[] attachments, 
			java.io.OutputStream outputStream)
			throws MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", kdUser);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(props);
		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(fromEmail);
		msg.setFrom(addressFrom);
		InternetAddress addressTo = new InternetAddress(toEmail);
		msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setSubject(subject);
		
		//E-Mail - Grundlage erzeugen
		MimeMultipart content = new MimeMultipart();
		//Text der E-Mail verfassen
		MimeBodyPart text = new MimeBodyPart();
		text.setText( message + " Sehen Sie auch im Anhang." ); 
		content.addBodyPart( text );  
		
		//Anhang(e) anhängen
		for(int i=0;attachments!=null && i<attachments.length;i++){
			javax.activation.DataSource fileDataSource = new javax.activation.FileDataSource( attachments[i] );
			BodyPart messageBodyPart = new MimeBodyPart(); 
			messageBodyPart.setDataHandler(new javax.activation.DataHandler(fileDataSource) ); 
			messageBodyPart.setFileName( attachments[i] );     // gibt dem Anhang einen Namen 
			content.addBodyPart( messageBodyPart );		
		}
		
		if(outputStream!=null){
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(outputStream.toString(),"application/pdf")));
			messageBodyPart.setFileName("PDF.pdf");
			content.addBodyPart(messageBodyPart);
		}
		
		//Message erzeugen.
		msg.setContent(content);
		
		//Message verschicken
		Transport tr = session.getTransport("smtp");
		tr.connect(host, kdUser, kdPwd);
		msg.saveChanges(); // don't forget this
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}
	
	
  
  
  
 
  public static void main( String[] args ) throws Exception 
  { 
    postMailToGmx( "Buchtest@mailserver.com", 
              "Tolles Buch", 
              "Wow. Das Buch ist schön zu lesen", 
              "info@mailserver.com",
              "wladimir.stoll@gmx.de",
              "ksutksut"
              ); 
  } 
}