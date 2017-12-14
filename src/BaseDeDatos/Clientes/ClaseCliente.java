
package BaseDeDatos.Clientes;

import BaseDeDatos.Producto.*;


public class ClaseCliente {
    String id;
    String razonsocial;
    String direccion;
    String correo;
    String contacto;
    String telefono;
    String estatus;

    public ClaseCliente(String id, String razonsocial, String direccion, String correo, String contacto, String telefono, String estatus) {
        this.id = id;
        this.razonsocial = razonsocial;
        this.direccion = direccion;
        this.correo = correo;
        this.contacto = contacto;
        this.telefono = telefono;
        this.estatus = estatus;
    }

    public String getId() {
        return id;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEstatus() {
        return estatus;
    }
    
    
    
    
    
   

}
