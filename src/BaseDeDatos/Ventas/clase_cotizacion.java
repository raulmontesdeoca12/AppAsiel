/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Ventas;

public class clase_cotizacion {
    int idcot;
    String fecha;
    int vencimiento;
    String flete;
    String credito;
    String descuento_por;
    String apartado;
    String fk_cliente;
    float pre_total;
    float descuento;
    float sub_total;
    float iva;
    float total;
    int mostrar;

    public clase_cotizacion(int idcot, String fecha, int vencimiento, String flete, String credito, String descuento_por, String apartado, String fk_cliente, float pre_total, float descuento, float sub_total, float iva, float total, int mostrar) {
        this.idcot = idcot;
        this.fecha = fecha;
        this.vencimiento = vencimiento;
        this.flete = flete;
        this.credito = credito;
        this.descuento_por = descuento_por;
        this.apartado = apartado;
        this.fk_cliente = fk_cliente;
        this.pre_total = pre_total;
        this.descuento = descuento;
        this.sub_total = sub_total;
        this.iva = iva;
        this.total = total;
        this.mostrar = mostrar;
    }

    public int getMostrar() {
        return mostrar;
    }

    public int getIdcot() {
        return idcot;
    }

    public String getFecha() {
        return fecha;
    }

    public int getVencimiento() {
        return vencimiento;
    }

    public String getFlete() {
        return flete;
    }

    public String getCredito() {
        return credito;
    }

    public String getDescuento_por() {
        return descuento_por;
    }

    public String getApartado() {
        return apartado;
    }

    public String getFk_cliente() {
        return fk_cliente;
    }

    public float getPre_total() {
        return pre_total;
    }

    public float getDescuento() {
        return descuento;
    }

    public float getSub_total() {
        return sub_total;
    }

    public float getIva() {
        return iva;
    }

    public float getTotal() {
        return total;
    }
            
}
