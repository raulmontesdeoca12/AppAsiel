
package obed77.Correo;

import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import obed77.Principal;
import obed77.Ventas.Enviar_Correos;
import obed77.Ventas.Nueva_ventaml;

/**
 *
 * @author Saito
 */
public class HiloImportante extends Thread {
     private boolean continuar = true;
  public static  boolean  envioExito= false;
  String vent;

    public HiloImportante(String vent) {
        this.vent = vent;
    }
  
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
          Correo_Normal propio = new Correo_Normal(Enviar_Correo_Importante.from, Enviar_Correo_Importante.pass, Enviar_Correo_Importante.correo, Enviar_Correo_Importante.asunto, Enviar_Correo_Importante.mensaje);
          try
          {
              envioExito=propio.enviar();
              
                if(vent.equals("ec"))
                {
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
                   
                }else
                    if(vent.equals("vm"))
                {
                     if(envioExito)
                    {
                        JOptionPane.showMessageDialog(e,"Correo Enviado Correctamente","Enviar Correo",1,null);
                        
                    }else
                    {
                         JOptionPane.showMessageDialog(e,"Error al Enviar Correo\n \"Puede intentar enviar el correo desde el panel de ventas de Mercadolibre\"","Enviar Correo",1,null);
                    }
                    e.dispose();
                    Nueva_ventaml.ch_evt.setSelected(true);
                    
                }else
                    {
                        
                    }
                        
                    
             
            
              
              
          }
          catch (MessagingException ex)
          {
              System.out.println("Error en hilo");
              Logger.getLogger(HiloImportante.class.getName()).log(Level.SEVERE, null, ex);
              detenElHilo();
          }
      }
      detenElHilo();
    }
  
  }
  public boolean isListo(){return envioExito;}
    
}
