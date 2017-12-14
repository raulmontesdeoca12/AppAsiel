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
public class Clase_vent_nor {
    String id,fecha, cliente,contacto,telefono,estado,factura,cobro,empresa,envio,fenvio,correo;

    public Clase_vent_nor(String id,String fecha, String cliente, String contacto, String telefono, String estado,String correo) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.contacto = contacto;
        this.telefono = telefono;
        this.estado = estado;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEstado() {
        return estado;
    }

    public String getFactura() {
        return factura;
    }

    public String getCobro() {
        return cobro;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEnvio() {
        return envio;
    }

    public String getFenvio() {
        return fenvio;
    }

    public String getCorreo() {
        return correo;
    }
    
    
    
}
