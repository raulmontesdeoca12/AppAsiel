
package BaseDeDatos.Empleados;
import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Gest_contrato {
    
    
     public boolean Insertar(ClaseContrato contrato)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="Insert into contratos (idcontratos, fecha_ingreso, sueldo_inicial, sueldo_final, fk_horario, fk_empleado, fk_cargo,con_sueldo,con_ticket,con_comision,con_destajo) values ('"+contrato.getId()+"','"+contrato.getFecha()+"','"+contrato.getSinicial()+"','"+contrato.getSfinal()+"',"
                + "'"+contrato.getHorario()+"','"+contrato.getEmpleado()+"','"+contrato.getCargo()+"','"+contrato.getBase()+"','"+contrato.getAlimentacion()+"','"+contrato.getComsion()+"','"+contrato.getDestajo()+"')";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     
     
     public boolean Modificar(ClaseContrato contrato)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="UPDATE contratos SET fecha_ingreso='"+contrato.getFecha()+"', fecha_egreso='"+contrato.getFechae()+"',"
                + "sueldo_final ='"+contrato.getSfinal()+"',fk_horario='"+contrato.getHorario()+"', "
                + "fk_cargo='"+contrato.getCargo()+"',con_sueldo='"+contrato.getBase()+"',con_ticket='"+contrato.getAlimentacion()+"',"
                + "con_comision='"+contrato.getComsion()+"', con_destajo='"+contrato.getDestajo()+"' Where idcontratos='"+contrato.getId()+"'";
        boolean resul =st.execute(query);
        st.close();
        con.close();
        return resul; 
    }
     

     public boolean Eliminar(ClaseEAEmpleado contrato)throws SQLException, ClassNotFoundException
    {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st=con.createStatement();
        String query ="DELETE FROM contratos WHERE idcontratos='"+contrato.getId()+"'";
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
