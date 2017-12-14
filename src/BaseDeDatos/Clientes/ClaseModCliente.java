
package BaseDeDatos.Clientes;



public class ClaseModCliente {
    String idv;
    String id;
    String razonsocial;
    String direccion;
    String correo;
    String contacto;
    String telefono;
    String estatus;

    public ClaseModCliente(String idv, String id, String razonsocial, String direccion, String correo, String contacto, String telefono) {
        this.idv= idv;
        this.id = id;
        this.razonsocial = razonsocial;
        this.direccion = direccion;
        this.correo = correo;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    public String getIdv() {
        return idv;
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

    
    
    
    
    
   

}
