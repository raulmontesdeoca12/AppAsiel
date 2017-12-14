
package BaseDeDatos.Empleados;
import BaseDeDatos.proveedores.*;
import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Gest_empleado {
    
    
     public boolean Insertar(ClaseEmpleado empleado)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into empleados values ('"+empleado.getCi()+"','"+empleado.getNombre()+"','"+empleado.getApellido()+"','"+empleado.getFecha()+"','"+empleado.getEdad()+"','"+empleado.getEstado()+"','"+empleado.getCorreo()+"',"
                + "'"+empleado.getDireccion()+"','"+empleado.getEstatus()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     
     
     public boolean Modificar(ClaseModEmpleado empleado)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE empleados SET  ci_empleado ='"+empleado.getCi()+"', nombre='"+empleado.getNombre()+"', apellido='"+empleado.getApellido()+"',f_nac='"+empleado.getFecha()+"',edad='"+empleado.getEdad()+"',estado='"+empleado.getEstado()+"',correo='"+empleado.getCorreo()+"',"
                + "direccion ='"+empleado.getDirección()+"' Where ci_empleado='"+empleado.getCiv()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     
     public boolean Eliminar(ClaseEAEmpleado Eempleado)throws SQLException, ClassNotFoundException
    {
        String status="Inactivo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE empleados SET estatus='"+status+"' WHERE ci_empleado='"+Eempleado.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    public boolean Activar (ClaseEAEmpleado Aempleado)throws SQLException, ClassNotFoundException
    {
        String status="Activo";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE empleados SET estatus='"+status+"' WHERE ci_empleado='"+Aempleado.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
    
    
    
 public boolean InsertarCuenta(Clase_cuenta_empleado empleado)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into cuenta_be values ('"+empleado.getId()+"','"+empleado.getBanco()+"','"+empleado.getTipo()+"','"+empleado.getDesc()+"','"+empleado.getEmpleado()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     
     public boolean EliminarCuenta(Clase_cuenta_empleado empleado)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="DELETE FROM cuenta_be WHERE idcuenta_be='"+empleado.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     
     public boolean InsertarTelefono(Clase_telefono_empleado empleado)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into telefonos values ('"+empleado.getId()+"','"+empleado.getTipo()+"','"+empleado.getNumero()+"','"+empleado.getPersona()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     
     public boolean EliminarTelefono(Clase_telefono_empleado empleado)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="DELETE FROM telefonos WHERE idtelefonos='"+empleado.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }

}
