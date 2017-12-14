
package obed77.Correo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import obed77.Ventas.Enviar_Correos;
import obed77.Ventas.Nueva_ventaml;
import obed77.Ventas.Ventana_Facturar_Ml;

/**
 *
 * @author Saito
 */
public class HiloEnviado extends Thread {
     private boolean continuar = true;
  public static  boolean  envioExito= false;
  
  public void detenElHilo()
  {
    this.continuar = false;
  }
  
     @Override
  public void run()
  {
    Enviando e = new Enviando();
    e.setVisible(true);
    while (this.continuar)
    {
      
      {
          Correo_Adjunto propio = new Correo_Adjunto(Enviar_Correo_Envio.from, Enviar_Correo_Envio.pass, Enviar_Correo_Envio.correo, Enviar_Correo_Envio.asunto, Enviar_Correo_Envio.mensaje, Enviar_Correo_Envio.adj);
          try
          {
              envioExito=propio.enviar();
              
               
                    if(envioExito)
                    {
                        JOptionPane.showMessageDialog(e,"Correo Enviado Correctamente","Enviar Correo",1,null);
                        
                    }else
                    {
                         JOptionPane.showMessageDialog(e,"Error al Enviar Correo","Enviar Correo",1,null);
                    }
                    e.dispose();
                    Enviar_Correos.validarbotones(); 
                    Enviar_Correos.btn_salir.setEnabled(true);
                   
                
                    
             
            
              
              
          }
          catch (MessagingException ex)
          {
              System.out.println("Error en hilo");
              Logger.getLogger(HiloEnviado.class.getName()).log(Level.SEVERE, null, ex);
              detenElHilo();
          }
      }
      detenElHilo();
    }
  
  }
  public boolean isListo(){return envioExito;}
    
}
