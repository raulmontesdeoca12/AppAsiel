/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Ventas;

import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Gest_ventas {
    

    public boolean InsertarDet(clase_factura_det detfactura)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into detalles_factura (fk_producto, cant_prod, fk_factura,p_unitario, total_prod)  values ('"+detfactura.getFk_prod()+"','"+detfactura.getCant_prod()+"','"+detfactura.getFk_pedido()+"','"+detfactura.getP_unitario()+"','"+detfactura.getTotal_prod()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean InsertarDetCot(clase_cotizacion_det detcotizacion)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into detalles_cotizacion (fk_producto, cant_prod, fk_cotizacion,p_unitario, total_prod)  values ('"+detcotizacion.getProducto()+"','"+detcotizacion.getCantidad()+"','"+detcotizacion.getCotizacion()+"','"+detcotizacion.getPrecio()+"','"+detcotizacion.getTotal()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     public boolean InsertarDetN(Clase_det_vn det)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into detalle_ventanml (fk_ventaml, fk_producto, cantidad,precio, total)  values ('"+det.getVenta()+"','"+det.getProducto()+"','"+det.getCantidad()+"','"+det.getPrecio()+"','"+det.getTotal()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean InsertarFac(clase_factura factura)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        System.out.println(factura.getTot());
        String query ="Insert into factura values ('"+factura.getCod()+"','"+factura.getCiudad()+"','"+factura.getFecha()+"','"+factura.getForma_pago()+"','"+factura.getVencimiento()+"','"+factura.getSon()+"','"+factura.getDia()+"','"+factura.getMes()+"','"+factura.getAnio()+"','"+factura.getEstatus()+"','"+factura.getCliente()+"',TRUNCATE('"+factura.getSub()+"',2),TRUNCATE('"+factura.getIva()+"',2),'"+factura.getEx()+"',TRUNCATE('"+factura.getTot()+"',2),'"+factura.getVenta()+"','"+factura.getMontoAlicuota()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean InsertarCot(clase_cotizacion cotizacion)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into cotizacion values ('"+cotizacion.getIdcot()+"','"+cotizacion.getFecha()+"','"+cotizacion.getVencimiento()+"','"+cotizacion.getFlete()+"','"+cotizacion.getCredito()+"','"+cotizacion.getDescuento_por()+"','"+cotizacion.getApartado()+"','"+cotizacion.getFk_cliente()+"','"+cotizacion.getPre_total()+"','"+cotizacion.getDescuento()+"','"+cotizacion.getSub_total()+"','"+cotizacion.getIva()+"','"+cotizacion.getTotal()+"','"+cotizacion.getMostrar()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean ModStock(clase_descontar des)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Update  producto set cantidad_prod='"+des.getCant()+"' where idproducto='"+des.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    
    
    
      public boolean ModStatusFac(clase_factura des)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Update  factura set estatus_fact='"+des.getEstatus()+"' where idfactura='"+des.getCod()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
      
      public boolean InsertarVenMl(clase_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into ventas_ml(fecha, cliente, telefono, estado, correo) "
                + "values ('"+ml.getFecha()+"','"+ml.getCliente()+"','"+ml.getTelefono()+"','"+ml.getEstado()+"','"+ml.getCorreo()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
      public boolean InsertarDetVenMl(Clase_det_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into detalles_ml(fkventas_ml, fkproducto, cantidad, precio, total) "
                + "values ('"+ml.getVentas()+"','"+ml.getProducto()+"','"+ml.getCantidad()+"','"+ml.getPrecio()+"','"+ml.getTotal()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
      public boolean EliminarDetVenMl(Clase_det_ml v)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="DELETE FROM detalles_ml WHERE fkventas_ml='"+v.getVentas()+"' AND fkproducto ='"+v.getProducto()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
      public boolean InsertarVenNml(Clase_vent_nor ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into ventas_nml(idventas_nml,fecha, fk_cliente,contacto, telefono,estado, correo) values ('"+ml.getId()+"','"+ml.getFecha()+"','"+ml.getCliente()+"','"+ml.getContacto()+"','"+ml.getTelefono()+"','"+ml.getEstado()+"','"+ml.getCorreo()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    
        public boolean InsertarCobro(clase_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE ventas_ml set estado ='"+ml.getEstado()+"', referencia_cobro = '"+ml.getRef()+"' WHERE idventas_ml = '"+ml.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
         public boolean InsertarFactura(clase_factura ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE ventas_ml set estado ='"+ml.getEstado()+"', fk_factura = '"+ml.getCod()+"' WHERE idventas_ml = '"+ml.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
        
        
        public boolean CambiarTransfer(clase_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE ventas_ml set estado = '"+ml.getEstado()+"' WHERE idventas_ml = '"+ml.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
        public boolean CambiarCi(clase_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE ventas_ml set fk_cliente = '"+ml.getEstado()+"' WHERE idventas_ml = '"+ml.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
            public boolean InsertarEnvio(clase_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE ventas_ml set estado ='"+ml.getEstado()+"', referencia_envio = '"+ml.getRef()+"', fecha_envio ='"+ml.getFecha()+"', empresa ='"+ml.getEmpresa()+"' WHERE idventas_ml = '"+ml.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
            
    public boolean ModVenMlSf(clase_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Update  ventas_ml set fecha='"+ml.getFecha()+"', cliente='"+ml.getCliente()+"', telefono='"+ml.getTelefono()+"', referencia_cobro='"+ml.getRef()+"', referencia_envio='"+ml.getRef2()+"', fecha_envio = '"+ml.getFechae()+"', empresa ='"+ml.getEmpresa()+"', correo ='"+ml.getCorreo()+"' where idventas_ml='"+ml.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    
    public boolean ModVenMlCf(clase_ml ml)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Update  ventas_ml set fecha='"+ml.getFecha()+"', cliente='"+ml.getCliente()+"', telefono='"+ml.getTelefono()+"', fk_factura='"+ml.getFactura()+"', referencia_cobro='"+ml.getRef()+"', referencia_envio='"+ml.getRef2()+"', fecha_envio = '"+ml.getFechae()+"', empresa ='"+ml.getEmpresa()+"', correo ='"+ml.getCorreo()+"' where idventas_ml='"+ml.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean EliminarProd(Clase_det_vn v)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="DELETE FROM detalle_ventanml WHERE iddetalle_ventanml='"+v.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
}
