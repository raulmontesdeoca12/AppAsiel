/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Ventas;

public class clase_cotizacion_det {
    String producto;
    int cantidad;
    int cotizacion;
    float precio;
    float total;

    public clase_cotizacion_det(String producto, int cantidad, int cotizacion, float precio, float total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.cotizacion = cotizacion;
        this.precio = precio;
        this.total = total;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCotizacion() {
        return cotizacion;
    }

    public float getPrecio() {
        return precio;
    }

    public float getTotal() {
        return total;
    }
    
}
