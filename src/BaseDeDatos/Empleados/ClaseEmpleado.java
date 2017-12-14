
package BaseDeDatos.Empleados;



public class ClaseEmpleado {
    String ci;
    String nombre;
    String apellido;
    String fecha;
    String edad;
    String estado;
    String correo;
    String direccion;
    String estatus;

    public ClaseEmpleado(String ci, String nombre, String apellido, String fecha, String edad,String estado, String correo, String direccion, String estatus) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.edad = edad;
        this.estado = estado;
        this.correo = correo;
        this.direccion = direccion;
        this.estatus = estatus;
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

    public String getDireccion() {
        return direccion;
    }

    public String getEstatus() {
        return estatus;
    }

   
    }
    
    
    
    
    
   

