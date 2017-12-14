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
import java.util.logging.Level;
import java.util.logging.Logger;
import obed77.Ventas.Agregar_Envio;

/**
 *
 * @author Asiel 77
 */
public class Enviar_Correo_Importante

{   
   public static String from="",pass="", asunto, mensaje, correo;
   public static boolean flag;

    public Enviar_Correo_Importante(String correo) {
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
           
        asunto="Información Importante";
        mensaje = "<p>Buen d&iacute;a</p>\n" +
        "<h2><strong><span style=\"background-color: #ffff00;\">POR FAVOR LEA DETENIDAMENTE EL CORREO COMPLETO, AL REALIZAR LA COMPRA ESTA ACEPTANDO LOS TERMINOS Y CONDICIONES DE LA EMPRESA.&nbsp;&nbsp;</span></strong>&nbsp; &nbsp;</h2>\n" +
        "<h2>&nbsp; &nbsp;</h2>\n" +
        "<p>Le informamos que estamos al tanto de su compra<br /><br /></p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p><span style=\"background-color: #ffff00;\"><strong>Los datos para realizar el depósito o transferencia son:</strong></span></p>\n" +
        "<p><strong>Banco:&nbsp;</strong>Provincial</p>\n" +
        "<p><strong>Nombre:&nbsp;</strong>Distribuidora Asiel 77 C.A.</p>\n" +
        "<p><strong>Tipo de Cuenta:&nbsp;</strong>Corriente</p>\n" +
        "<p><strong>N&uacute;mero de Cuenta:&nbsp;</strong>0108-0174-50-0100-156285</p>\n" +
        "<p><strong>RIF.:</strong> J-404793119</p>\n" +
        "<p><strong>&nbsp;</strong></p>\n" +
        "<p><span style=\"background-color: #ffff00;\"><strong>Nota Importante:&nbsp;</strong>Al momento de realizar la transferencia necesitaremos que nos envíe el <strong>número</strong> <strong>de cédula</strong> del titular de la cuenta de donde se hizo la transferencia. <strong>Por Favor RESPONDER A: contacto@asiel77.com</strong></span></p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p>SI su forma de pago es depósito necesitamos que nos indique el número de movimiento que le aparece en el recibo.</p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p><span style=\"background-color: #ffff00;\"><strong>Necesitamos que nos suministre estos datos para poder validar su pago</strong></span></p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p>&nbsp;</p>\n" +
        "<h4><strong>En caso de pagar con MERCADO PAGO Simplemente envíe los siguientes requisitos</strong></h4>\n" +
        "<p>&nbsp;</p>\n" +
        "<p>Necesitamos que nos facilite los siguientes datos: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p><strong>Para la Facturación: </strong>&nbsp; &nbsp; &nbsp;</p>\n" +
        "<p>Nombre:</p>\n" +
        "<p>Dirección:</p>\n" +
        "<p>Teléfonos:</p>\n" +
        "<p>Cédula de Identidad:</p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p><strong>Para el Envío:</strong></p>\n" +
        "<p>Empresa a Contratar:</p>\n" +
        "<p>Dirección Detallada(En caso de no ser enviado a una sucursal describa su dirección) :</p>\n" +
        "<p>&nbsp;</p>\n" +
        "<h2><span style=\"background-color: #ffff00;\">Nota Importante:&nbsp;Verificar si la agencia \"Destino\" es receptora de mercancía y cobro a destino, de no serlo se le enviará; el producto a la agencia receptora más cercana a la dirección ofrecida</span>...</h2>\n" +
        "<p>&nbsp;</p>\n" +
        "<p><strong>Datos del receptor</strong>&nbsp; &nbsp; &nbsp; &nbsp;</p>\n" +
        "<p>&nbsp; &nbsp;</p>\n" +
        "<p>Nombre y Apellido:</p>\n" +
        "<p>Cédula de Identidad:</p>\n" +
        "<p>Número de&nbsp;Tel&eacute;fono:</p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p><strong>Su Producto será enviado en la mañana del día hábil en el que se acredite el pago.</strong></p>\n" +
        "<p><strong>Los Envíos por ZOOM van asegurados (Costo del seguro 1.3% del Valor Neto del producto).</strong></p>\n" +
        "<p><strong>Los Envíos por DOMESA, Se asegurarán solo si el cliente lo solicita.</strong></p>\n" +
        "<p>&nbsp;</p>\n" +
        "<p><span style=\"background-color: #ffff00;\"><strong>\"La empresa no se hace responsable por pérdida o daños&nbsp;</strong><strong>ocasionados</strong></span><strong><span style=\"background-color: #ffff00;\">&nbsp;a la mercancía por acción de terceros, durante su recolecta, almacenaje, transporte, entrega y/o manipulación en general.</span>\"&nbsp;</strong></p>\n" +
        "<p><strong>\"El Cobro es a destino\"</strong></p>\n" +
        "<p>Gracias por preferirnos.</p>\n" +
        "<p>AL VALIDARSE SU PAGO USTED RECIBIRÁ; UN CORREO DONDE SE LE INDICARÁ; QUE SE HA FACTURADO SU PRODUCTO Y PROXIMAMENTE SERÁ ENVIADO</p>\n"
       +"<p>Este es un correo autom&aacute;tico por favor <strong><span style=\"background-color: #ffff99;\">no responder</span></strong>.</p>\n"+
        "<p>\"Si desea contactarnos, comun&iacute;quese v&iacute;a correo a contacto@asiel77.com o por nuestro n&uacute;mero de tel&eacute;fono (0212-424.27.41)\"</p>\n" +
        "<p><span class=\"HOEnZb\"><span style=\"color: #888888;\"><span style=\"color: #0000ff; font-family: comic sans ms,sans-serif;\"><span style=\"background-color: #eeeeee;\">Distribuidora Asiel 77 C.A<br />J-40479311-9 &nbsp;/ (0212) 424.27.41&nbsp;<br />\"Edificando Sue&ntilde;os\"</span></span></span></span><br /><br /></p>";
        
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Agregar_Envio.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Agregar_Envio.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
    
     
}
