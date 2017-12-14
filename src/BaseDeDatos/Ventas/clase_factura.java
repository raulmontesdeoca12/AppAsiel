
package BaseDeDatos.Ventas;

public class clase_factura {
    int cod;
    String ciudad;
    String fecha;
    String forma_pago;
    String vencimiento;
    String son;
    String estatus;
    String cliente;
    String dia, mes, anio;
    int id;
    double sub, iva, ex,tot;
    String venta;
    String estado;
    int montoAlicuota;

    public clase_factura(int cod, String ciudad, String fecha, String forma_pago, String vencimiento, String son, String dia,String mes, String anio,String estatus, String cliente,  double sub, double iva, double ex, double tot, String venta, int montoAlicuota) {
        this.cod = cod;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.forma_pago = forma_pago;
        this.vencimiento = vencimiento;
        this.son = son;
        this.estatus = estatus;
        this.cliente = cliente;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.sub = sub;
        this.iva = iva;
        this.ex = ex;
        this.tot = tot;
        this.venta = venta;
        this.montoAlicuota = montoAlicuota;
    }
    
     public clase_factura(int cod, int id, String estado) {
        this.cod = cod;
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }
     

    public String getVenta() {
        return venta;
    }

    public double getSub() {
        return sub;
    }

    public double getIva() {
        return iva;
    }

    public double getEx() {
        return ex;
    }

    public double getTot() {
        return tot;
    }

   
    public int getCod() {
        return cod;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public String getSon() {
        return son;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAnio() {
        return anio;
    }

    public int getMontoAlicuota() {
        return montoAlicuota;
    }

    public void setMontoAlicuota(int montoAlicuota) {
        this.montoAlicuota = montoAlicuota;
    }

    
}