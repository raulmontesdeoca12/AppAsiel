
package BaseDeDatos.Empleados;

import BaseDeDatos.proveedores.*;



public class ClaseModEmpleado {
    String civ;
    String ci;
    String nombre;
    String apellido;
    String fecha;
    String edad;
    String estado;
    String correo;
    String dirección;

    public ClaseModEmpleado(String civ, String ci, String nombre, String apellido, String fecha, String edad,String estado, String correo, String dirección) {
        this.civ = civ;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.edad = edad;
        this.estado = estado;
        this.correo = correo;
        this.dirección = dirección;
    }

    public String getEstado() {
        return estado;
    }

    
    
    public String getFecha() {
        return fecha;
    }

    public String getEdad() {
        return edad;
    }

  

    public String getCiv() {
        return civ;
    }

    public String getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDirección() {
        return dirección;
    }
    

   

}
