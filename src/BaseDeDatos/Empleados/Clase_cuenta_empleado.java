
package BaseDeDatos.Empleados;


public class Clase_cuenta_empleado {
    String id;
    String banco;
    String tipo;
    String desc;
    String empleado;

    public Clase_cuenta_empleado(String id) {
        this.id = id;
    }

    public Clase_cuenta_empleado(String id, String banco, String tipo, String desc, String empleado) {
        this.id = id;
        this.banco = banco;
        this.tipo = tipo;
        this.desc = desc;
        this.empleado = empleado;
    }

    public String getId() {
        return id;
    }

    public String getBanco() {
        return banco;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDesc() {
        return desc;
    }

    public String getEmpleado() {
        return empleado;
    }


   
}
