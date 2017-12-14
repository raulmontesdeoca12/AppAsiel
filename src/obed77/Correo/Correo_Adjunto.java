/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.Correo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;


public class Correo_Adjunto {
  String miCorreo;
  String miPassword;
  String servidorSMTP;
  String puertoEnvio;
  String starttls;
  String auths;
  String destinatarios;
  String asunto = null;
  String cuerpo = null;
  String adj;
  int servidor;

    public Correo_Adjunto(String mailReceptor, String asunto, String cuerpo, String adj)
  {
    this.destinatarios = mailReceptor;
    this.asunto = asunto;
    this.cuerpo = cuerpo;
    this.adj = adj;
    this.servidor = servidor;
    configurarServidor();
  }
  
    public Correo_Adjunto(String user, String pass, String mailReceptor, String asunto, String cuerpo, String adj)
  {
    this(mailReceptor, asunto, cuerpo, adj);
    this.miCorreo = user;
    this.miPassword = pass;
  }

  public final void configurarServidor()
  {
      try {
          Properties pp = new Properties();
          pp.load(new BufferedReader(new FileReader("ajustes.properties")));
          this.servidorSMTP=pp.getProperty("host");
          this.puertoEnvio=pp.getProperty("port");
          this.starttls=pp.getProperty("starttls");
          this.auths=pp.getProperty("auth");
      } catch (IOException ex) {
          Logger.getLogger(Correo_Normal.class.getName()).log(Level.SEVERE, null, ex);
      }
   
  }
  
    public boolean enviar()
            throws MessagingException
    {
        boolean p = false;
        Properties props = new Properties();
        props.put("mail.smtp.user", this.miCorreo);
        props.put("mail.smtp.host", this.servidorSMTP);
        props.put("mail.smtp.port", this.puertoEnvio);
        props.put("mail.smtp.starttls.enable", this.starttls);
        props.put("mail.smtp.auth", this.auths);
        props.put("mail.smtp.socketFactory.port", this.puertoEnvio);
        props.setProperty("mail.smtp.port", this.puertoEnvio);

        SecurityManager security = System.getSecurityManager();


        Session session = Session.getInstance(props, new GMailAuthenticator(miCorreo,miPassword));
         
        BodyPart texto = new MimeBodyPart();
        texto.setText(this.cuerpo);
        texto.setContent(this.cuerpo,"text/html");
        
        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(
        new DataHandler(new FileDataSource(adj)));
        adjunto.setFileName("Recibo de Envío");
        
        MimeMultipart multiParte= new MimeMultipart();
        multiParte.addBodyPart(texto);
        multiParte.addBodyPart(adjunto);
        
        
        MimeMessage msg = new MimeMessage(session);
        msg.setSubject( this.asunto );
        msg.setFrom(new InternetAddress(this.miCorreo));
        msg.setRecipients(Message.RecipientType.TO, destinatarios);
        msg.setContent(multiParte);
        msg.setSentDate(new Date());
        
        Transport t = session.getTransport("smtp"); 
        t.connect(this.miCorreo, this.miPassword);
        t.sendMessage(msg, msg.getAllRecipients());

        Store store = session.getStore("imap");
        store.connect(this.servidorSMTP, this.miCorreo, this.miPassword );
        Folder folder = store.getFolder("INBOX.Sent");
        folder.open(Folder.READ_WRITE);
        msg.setFlag(Flags.Flag.SEEN, true);
        folder.appendMessages(new Message[] {msg});
        store.close();
        p=true;
        
            return p;
    }

private class GMailAuthenticator extends Authenticator {
	     String user;
	     String pw;
	     public GMailAuthenticator (String username, String password)
	     {
	        super();
	        this.user = username;
	        this.pw = password;
	     }
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	       return new PasswordAuthentication(user, pw);
	    }
	}

}
