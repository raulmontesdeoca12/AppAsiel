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
public class Enviar_Correo_Envio {
   public static String from="",pass="", asunto, mensaje, correo, adj,ci,empresa,referencia,nota;
   public static boolean flag;
   
   public Enviar_Correo_Envio(String ci,String empresa,String referencia,String nota, String adj) {
        this.ci = ci;
        this.empresa = empresa;
        this.referencia = referencia;
        this.nota = nota;
        this.adj = adj;
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
        
        String sql2 = "SELECT * FROM clientes WHERE idclientes = '"+ci+"'";
        Class.forName(parametros.getDriver());
        Connection con2 = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st2= con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs2 = st2.executeQuery(sql2);
        if(rs2.last())
        {
            correo=rs2.getString("correo_cli");
            
        }else{
            System.out.println("Error...\n No se encuentra ningún dato");
        }
        
        asunto="Datos de Envío";
        mensaje = "<div id=\":ml\" class=\"a3s\" style=\"overflow: hidden;\">\n" +
"    <p>Buen d&iacute;a.</p>\n" +
"    <p>Le escribimos para notificarle que el <span style=\"background-color: #ffff99;\"><strong>env&iacute;o de su(s) producto(s) ya fue realizado</strong></span> y los datos del tr&aacute;mite son los siguientes:</p>\n" +
"    <p><strong>Empresa:</strong>"+empresa+"</p>\n" +
"    <p><strong>N&deg; de Gu&iacute;a:</strong>"+referencia+" </p>\n" +
"    <p>Este correo lleva anexado un escaneo del(los) comprobante(s) del env&iacute;o La factura de su compra fue enviada con su(s) Producto(s)</p>\n" +
"    <p><strong><span style=\"color: blue;\">IMPORTANTE: "+nota+"</span></strong></p>\n" +
"    <p><strong>PUEDE RASTREAR SU PAQUETE MEDIANTE :</strong>&nbsp;</p>\n" +
"    <p>www.grupozoom.com/tracking/tracking.php&nbsp; o www.domesa.com.ve/domesa/portal/menu/index/5</p>\n" +
"    <p>&nbsp;</p>\n" +
"    <p>Gracias por Preferirnos.</p>\n" +
"    <h2><strong><span style=\"background-color: #ffff00;\">Esperamos su calificaci&oacute;n como confirmaci&oacute;n de que recibi&oacute; el producto.</span> </strong></h2>\n" +
"</div>\n" +
"<p>Este es un correo autom&aacute;tico por favor <strong><span style=\"background-color: #ffff99;\">no responder</span></strong></p>"+
"    <p>\"Si desea contactarnos, comun&iacute;quese v&iacute;a correo a <a href=\"mailto:contacto@asiel77.com\" target=\"_blank\">contacto@asiel77.com</a> o por nuestro n&uacute;mero de tel&eacute;fono (0212-424.27.41)\"</p>\n" +
"<p>&nbsp;</p>\n" +
"<p><span class=\"HOEnZb\"><span style=\"color: #888888;\"><span style=\"color: #0000ff; font-family: comic sans ms,sans-serif;\"><span style=\"background-color: #eeeeee;\">Distribuidora Asiel 77 C.A<br />J-40479311-9 &nbsp;/ (0212) 424.27.41&nbsp;<br />\"Edificando Sue&ntilde;os\"</span></span></span></span></p>";
            
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Agregar_Envio.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
