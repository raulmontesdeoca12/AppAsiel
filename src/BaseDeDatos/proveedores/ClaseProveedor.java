
package BaseDeDatos.proveedores;


public class ClaseProveedor {
    private String id;
    private String razonsocial;
    private String direccion;
    private String contacto;
    private String telefono;
    private String estatus;

    public ClaseProveedor(String id, String razonsocial, String direccion, String contacto, String telefono, String estatus) {
        this.id = id;
        this.razonsocial = razonsocial;
        this.direccion = direccion;
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
    
    
    
    
    
   

