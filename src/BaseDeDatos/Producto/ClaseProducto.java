
package BaseDeDatos.Producto;


public class ClaseProducto {
    String id;
    String descripcion;
    double costo;
    double precio_si;
    double precio_ci;
    int cant_st;
    String proveedor;
    String tipo;
    double precio_ml;
    double tasa_calculo;
    double iva;
    double iva_ml;
    double precio_si_ml;
    
    
    
    public ClaseProducto(String id, String descripcion, double costo,double precio_si, double precio_ci, int cant_st,String proveedor, String tipo, double precio_ml, double tasa_calculo, double iva, double iva_ml, double precio_si_ml)
    {
        this.id=id;
        this.descripcion=descripcion;
        this.costo=costo;
        this.precio_si=precio_si;
        this.precio_ci=precio_ci;
        this.cant_st=cant_st;
        this.tipo=tipo;
        this.proveedor=proveedor;
        this.precio_ml=precio_ml;
        this.tasa_calculo=tasa_calculo;
        this.iva=iva;
        this.iva_ml=iva_ml;
        this.precio_si_ml=precio_si_ml;
    }

    public double getIva() {
        return iva;
    }

    public double getIva_ml() {
        return iva_ml;
    }

    public double getPrecio_si_ml() {
        return precio_si_ml;
    }

    public double getPrecio_ml() {
        return precio_ml;
    }

    public double getTasa_calculo() {
        return tasa_calculo;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public double getCosto() {
        return costo;
    }

    public double getPrecio_si() {
        return precio_si;
    }

    public double getPrecio_ci() {
        return precio_ci;
    }

    public int getCant_st() {
        return cant_st;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getTipo() {
        return tipo;
    }


}
