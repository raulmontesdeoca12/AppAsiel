
package BaseDeDatos.Ventas;


public class clase_factura_det {
    String fk_prod;
    int cant_prod;
    int fk_pedido;
    float p_unitario;
    float total_prod;


    public clase_factura_det(String fk_prod, int cant_prod, int fk_pedido,float p_unitario, float total_prod) {
        this.fk_prod = fk_prod;
        this.cant_prod = cant_prod;
        this.fk_pedido = fk_pedido;
        this.p_unitario = p_unitario;
        this.total_prod = total_prod;
    }

    public float getP_unitario() {
        return p_unitario;
    }

    public float getTotal_prod() {
        return total_prod;
    }

    public String getFk_prod() {
        return fk_prod;
    }

    public int getCant_prod() {
        return cant_prod;
    }

    public int getFk_pedido() {
        return fk_pedido;
    }

   
}
