/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.Correo;

import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import obed77.Ventas.Agregar_Envio;

/**
 *
 * @author Asiel 77
 */
public class Enviar_Correo_Facturado {
   public static String from="",pass="", asunto, mensaje, correo;
   public static boolean flag;
    
     public Enviar_Correo_Facturado(String correo) {
        this.correo = correo;
    }
     public void AsignarDatos()
  {    
    try {
        String sql = "SELECT * FROM datos_extras";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        if(rs.last())
        {
            from=rs.getString("correo");
            pass=rs.getString("clave_c");
            
        }else{
            System.out.println("Error...\n No se encuentra ningún dato");
        }
        
        
        String dia;
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        GregorianCalendar gcal = new GregorianCalendar();
        cal.setTime(d);
        int i = gcal.get(Calendar.DAY_OF_WEEK);
        if (i==5 || i == 6 || i==7 || i == 1)
        {
            dia ="el día Lunes, de ser el Lunes Día Feriado se le enviará el día Hábil Siguiente";
        }else
        {
            dia ="en la ma&ntilde;ana del d&iacute;a h&aacute;bil siguiente a la confirmación del cobro.";
        }
            
            asunto="Confirmación De Cobro";
            mensaje ="<p>Buen d&iacute;a.</p>\n" +
                    "<p>\"Si desea contactarnos, comun&iacute;quese v&iacute;a correo a contacto@asiel77.com o por nuestro n&uacute;mero de tel&eacute;fono (0212-424.27.41)\"</p>\n" +
                    "<p></p>\n" +
                    "<p>Se le notifica que su pago ha sido validado y su pedido ha sido facturado</strong>.</p>\n" +
                    "<p><strong>Su producto ser&aacute; enviado "+dia+"</strong></p>\n" +
                    "<p>Por pol&iacute;ticas de la empresa y por la seguridad de su producto, <span style=\"background-color: #ffff99;\"><strong>NO REALIZAMOS ENV&Iacute;OS LOS DIAS VIERNES</strong></span></p>\n" +
                    "<p><span style=\"background-color: #ffff00;\"><strong>\"La empresa no se hace responsable por p&eacute;rdida o da&ntilde;os&nbsp;</strong><strong>ocasionados</strong></span><strong><span style=\"background-color: #ffff00;\">&nbsp;a la mercanc&iacute;a por acci&oacute;n de terceros, durante su recolecta, almacenaje, transporte, entrega y/o manipulaci&oacute;n en general.</span>\"&nbsp;</strong></p>\n" +
                    "<p><strong>AL REALIZARSE EL ENV&Iacute;O USTED RECIBIR&Aacute; UN CORREO DONDE SE LE INDICAR&Aacute; EL N&Uacute;MERO DE GU&Iacute;A Y RECIBIR&Aacute; UN ADJUNTO DE LA MISMA.<br /></strong></p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>Gracias por preferirnos.</p>\n" +
                    "<p>, Este es un correo autom&aacute;tico por favor <strong><span style=\"background-color: #ffff99;\">no responder</span></strong>&nbsp;</p>\n" +
                    "<p><span class=\"HOEnZb\"><span style=\"color: #888888;\"><span style=\"color: #0000ff; font-family: comic sans ms,sans-serif;\"><span style=\"background-color: #eeeeee;\">Distribuidora Asiel 77 C.A<br />J-40479311-9 &nbsp;/ (0212) 424.27.41&nbsp;<br />\"Edificando Sue&ntilde;os\"</span></span></span></span></p>";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agregar_Envio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Agregar_Envio.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
}
