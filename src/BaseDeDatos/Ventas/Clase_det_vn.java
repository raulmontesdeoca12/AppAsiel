/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Ventas;

/**
 *
 * @author Asiel 77
 */
public class Clase_det_vn {
    
    String id, venta, producto, cantidad, precio, total;

    public Clase_det_vn(String venta, String producto, String cantidad, String precio, String total) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public Clase_det_vn(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getVenta() {
        return venta;
    }

    public String getProducto() {
        return producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public String getTotal() {
        return total;
    }
    
    
   
    
}
