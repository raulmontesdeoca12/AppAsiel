
package BaseDeDatos.Clientes;
import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Gest_cliente {

     public boolean Insertar(ClaseCliente cliente)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into clientes values ('"+cliente.getId()+"','"+cliente.getRazonsocial()+"','"+cliente.getDireccion()+"','"+cliente.getCorreo()+"','"+cliente.getContacto()+"',"
                + "'"+cliente.getTelefono()+"','"+cliente.getEstatus()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    

    
    public boolean Modificar(ClaseModCliente cliente)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE clientes SET  idclientes ='"+cliente.getId()+"', razon_social_cli='"+cliente.getRazonsocial()+"', direccion_cli='"+cliente.getDireccion()+"', correo_cli='"+cliente.getCorreo()+"',contacto_cli='"+cliente.getContacto()+"',"
                + "telefono_cli ='"+cliente.getTelefono()+"' Where idclientes='"+cliente.getIdv()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
   

public boolean Eliminar(ClaseEACliente Ecliente)throws SQLException, ClassNotFoundException
    {
        String status="Inactivo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE clientes SET estatus_cli='"+status+"' WHERE idclientes='"+Ecliente.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean Activar (ClaseEACliente Acliente)throws SQLException, ClassNotFoundException
    {
        String status="Activo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE clientes SET estatus_cli='"+status+"' WHERE idclientes='"+Acliente.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
}
