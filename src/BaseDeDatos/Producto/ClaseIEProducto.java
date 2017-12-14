
package BaseDeDatos.Producto;

public class ClaseIEProducto {
    String id;
    int cant;

    public ClaseIEProducto(String id, int cant) {
        this.id = id;
        this.cant = cant;
    }


    public String getId() {
        return id;
    }

    public int getCant() {
        return cant;
    }
    
}
