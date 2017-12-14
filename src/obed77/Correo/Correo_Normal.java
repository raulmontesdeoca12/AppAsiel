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
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Flags.Flag;
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
import javax.swing.JOptionPane;
import obed77.Ventas.Enviar_Correos;


public class Correo_Normal {

  String miCorreo;
  String miPassword;
  String servidorSMTP;
  String puertoEnvio;
  String starttls;
  String auths;
  String destinatarios;
  String asunto = null;
  String cuerpo = null;
  int servidor;
    public Correo_Normal(String mailReceptor, String asunto, String cuerpo)
  {
    this.destinatarios = mailReceptor;
    this.asunto = asunto;
    this.cuerpo = cuerpo;
    this.servidor = servidor;
    configurarServidor();
  }
    public Correo_Normal(String user, String pass, String mailReceptor, String asunto, String cuerpo)
  {
    this(mailReceptor, asunto, cuerpo);
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
            
            
            SMTPAuthenticator auth = new SMTPAuthenticator();
            Session session = Session.getDefaultInstance(props, auth);
            session.setDebug(false);
                   
            MimeMessage msg = new MimeMessage(session);
            msg.setText(this.cuerpo);
            msg.setContent(this.cuerpo,"text/html");
            msg.setSubject( this.asunto );
            msg.setFrom(new InternetAddress(this.miCorreo));
            msg.setRecipients(Message.RecipientType.TO, destinatarios);
            
            try{
               Transport t = session.getTransport("smtp"); 
               t.connect(this.miCorreo, this.miPassword);
               t.sendMessage(msg, msg.getAllRecipients());
               
                Store store = session.getStore("imap");
                store.connect(this.servidorSMTP, this.miCorreo, this.miPassword );
                Folder folder = store.getFolder("INBOX.Sent");
                folder.open(Folder.READ_WRITE);
                msg.setFlag(Flag.SEEN, true);
                folder.appendMessages(new Message[] {msg});
                store.close();
                p=true;
                
                
                
               System.out.println("Correo Enviado exitosamente!");
               t.close();
            }
            catch (AuthenticationFailedException ex)
            {
              JOptionPane.showMessageDialog(null, new Object[] { "Usuario o contraseña incorrecto" }, "Error de autentificacion", 0);
              throw new MessagingException();
            }
            return p;
    }
    
            private class autenticadorSMTP extends  Authenticator
            {
                private autenticadorSMTP(){}
                 public PasswordAuthentication getPasswordAuthentication()
                {
                  return new PasswordAuthentication(Correo_Normal.this.miCorreo, Correo_Normal.this.miPassword);
                }
            }
}
