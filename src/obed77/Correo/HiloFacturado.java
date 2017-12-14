
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
public class HiloFacturado extends Thread {
     private boolean continuar = true;
  public static  boolean  envioExito= false;
  String vent;

    public HiloFacturado(String vent) {
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
          Correo_Normal propio = new Correo_Normal(Enviar_Correo_Facturado.from, Enviar_Correo_Facturado.pass, Enviar_Correo_Facturado.correo, Enviar_Correo_Facturado.asunto, Enviar_Correo_Facturado.mensaje);
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
                    if(vent.equals("fm"))
                {
                     if(envioExito)
                    {
                        JOptionPane.showMessageDialog(e,"Correo Enviado Correctamente","Enviar Correo",1,null);
                        
                    }else
                    {
                         JOptionPane.showMessageDialog(e,"Error al Enviar Correo\n \"Puede intentar enviar el correo desde el panel de ventas de Mercadolibre\"","Enviar Correo",1,null);
                    }
                    e.dispose();
                    Ventana_Facturar_Ml.ch_evt.setSelected(true);
                    
                }else
                    {
                        
                    }
                        
                    
             
            
              
              
          }
          catch (MessagingException ex)
          {
              System.out.println("Error en hilo");
              Logger.getLogger(HiloFacturado.class.getName()).log(Level.SEVERE, null, ex);
              detenElHilo();
          }
      }
      detenElHilo();
    }
  
  }
  public boolean isListo(){return envioExito;}
    
}
