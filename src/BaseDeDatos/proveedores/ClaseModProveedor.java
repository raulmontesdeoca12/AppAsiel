
package BaseDeDatos.proveedores;



public class ClaseModProveedor {
    String idv;
    String id;
    String razonsocial;
    String direccion;
    String contacto;
    String telefono;

    public ClaseModProveedor(String idv, String id, String razonsocial, String direccion, String contacto, String telefono) {
        this.idv = idv;
        this.id = id;
        this.razonsocial = razonsocial;
        this.direccion = direccion;
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

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    
    
    
   

}
