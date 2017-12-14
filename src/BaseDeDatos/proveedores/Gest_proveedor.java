
package BaseDeDatos.proveedores;
import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Gest_proveedor {
    
     public boolean InsertarPago(clase_prov_pago proveedor)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into pago_prov values ('"+proveedor.getId()+"','"+proveedor.getTipo()+"','"+proveedor.getDesc()+"','"+proveedor.getFk_prov()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     
     public boolean EliminarPago(clase_prov_pago proveedor)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="DELETE FROM pago_prov WHERE idpago_prov='"+proveedor.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }

     public boolean Insertar(ClaseProveedor proveedor)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into proveedores values ('"+proveedor.getId()+"','"+proveedor.getRazonsocial()+"','"+proveedor.getDireccion()+"','"+proveedor.getContacto()+"',"
                + "'"+proveedor.getTelefono()+"','"+proveedor.getEstatus()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    

    
    public boolean Modificar(ClaseModProveedor proveedor)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE proveedores SET  idproveedores ='"+proveedor.getId()+"', razon_social_prov='"+proveedor.getRazonsocial()+"', direccion_prov='"+proveedor.getDireccion()+"',contacto_prov='"+proveedor.getContacto()+"',"
                + "telefono_prov ='"+proveedor.getTelefono()+"' Where idproveedores='"+proveedor.getIdv()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
   

public boolean Eliminar(ClaseEAProveedor Eproveedor)throws SQLException, ClassNotFoundException
    {
        String status="Inactivo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE proveedores SET estatus_prov='"+status+"' WHERE idproveedores='"+Eproveedor.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean Activar (ClaseEAProveedor Aproveedor)throws SQLException, ClassNotFoundException
    {
        String status="Activo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE proveedores SET estatus_prov='"+status+"' WHERE idproveedores='"+Aproveedor.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
}
