
package BaseDeDatos.Producto;

import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Gest_producto {

     public boolean Insertar(ClaseProducto producto)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into producto values ('"+producto.getId()+"','"+producto.getDescripcion()+"','"+producto.getCant_st()+"','"+producto.getCosto()+"','"+producto.getPrecio_si()+"',"
                + "'"+producto.getPrecio_ci()+"','"+producto.getProveedor()+"','"+producto.getTipo()+"','Activo','"+producto.getPrecio_ml()+"','"+producto.getTasa_calculo()+"','"+producto.getIva()+"','"+producto.getIva_ml()+"','"+producto.getPrecio_si_ml()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    

    
    public boolean Modificar(Clase_mod_Producto producto)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE producto SET  idproducto ='"+producto.getId()+"', descripcion_prod='"+producto.getDescripcion()+"', costo_prod='"+producto.getCosto()+"', precio_si='"+producto.getPrecio_si()+"',precio_ci='"+producto.getPrecio_ci()+"',"
                + "fk_proveedor ='"+producto.getProveedor()+"', fk_tipo_prod='"+ producto.getTipo()+"', iva='"+producto.getIva()+"',iva_ml='"+producto.getIva_ml()+"',precio_si_ml='"+producto.getPrecio_si_ml()+"', tasa_calculo='"+producto.getTasa_calculo()+"',precio_ml='"+producto.getPrecio_ml()+"' where idproducto='"+producto.getIdv()+"'";

        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean MovimientoStock(ClaseIEProducto producto)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE producto SET  cantidad_prod='"+producto.getCant()+"' where idproducto='"+producto.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }

public boolean Eliminar(ClaseEAProducto Eproducto)throws SQLException, ClassNotFoundException
    {
        String status="Inactivo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE producto SET estatus_prod='"+status+"' WHERE idproducto='"+Eproducto.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean Activar (ClaseEAProducto Aproducto)throws SQLException, ClassNotFoundException
    {
        String status="Activo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE producto SET estatus_prod='"+status+"' WHERE idproducto='"+Aproducto.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
}
