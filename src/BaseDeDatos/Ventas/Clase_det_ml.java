/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Ventas;

/**
 *
 * @author Saito
 */
public class Clase_det_ml {
    String ventas,producto,cantidad,precio,total;

    public Clase_det_ml(String ventas, String producto, String cantidad, String precio, String total) {
        this.ventas = ventas;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public Clase_det_ml(String ventas, String producto) {
        this.ventas = ventas;
        this.producto = producto;
    }
    

    public String getVentas() {
        return ventas;
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
