/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Ventas;

/**
 *
 * @author user
 */
public class clase_ml {
    String fecha,cliente,telefono,producto,estado,ref,ref2,fechae,empresa,correo;
    int cantidad,factura,id;
    double precio,total;

    public clase_ml(String fecha, String cliente, String telefono, String ref, String ref2, int factura, int id, String fechae, String empresa, String correo) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.telefono = telefono;
        this.producto = producto;
        this.ref = ref;
        this.ref2 = ref2;
        this.cantidad = cantidad;
        this.factura = factura;
        this.id = id;
        this.precio = precio;
        this.total = total;
        this.fechae = fechae;
        this.empresa = empresa;
        this.correo = correo;
    }
    
    

    public clase_ml(String fecha, String cliente, String telefono, String estado, String correo) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.telefono = telefono;
        this.estado = estado;
        this.correo = correo;
    }

    public clase_ml(int id, String estado, String ref) {
        this.ref = ref;
        this.estado = estado;
        this.factura = factura;
        this.id = id;
    }

    public clase_ml( int id, String estado, String ref, String fecha, String empresa) {
        this.estado = estado;
        this.ref = ref;
        this.id = id;
        this.fecha = fecha;
        this.empresa = empresa;
    }

    public clase_ml(int id, String estado) {
        this.estado = estado;
        this.id = id;
    }
    
    public String getRef2() {
        return ref2;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFechae() {
        return fechae;
    }
    
    public String getRef() {
        return ref;
    }

    public int getFactura() {
        return factura;
    }

    public int getId() {
        return id;
    }
   
    public String getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getProducto() {
        return producto;
    }

    public String getEstado() {
        return estado;
    }
    

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getTotal() {
        return total;
    }
    
    
    
    
}
