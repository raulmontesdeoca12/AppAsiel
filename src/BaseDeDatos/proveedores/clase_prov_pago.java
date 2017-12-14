
package BaseDeDatos.proveedores;

public class clase_prov_pago {
    String fk_prov;
    String tipo;
    String desc;
    String id;

    public clase_prov_pago(String id) {
        this.id = id;
    }


    public clase_prov_pago(String id,String fk_prov, String tipo, String desc) {
        this.id = id;
        this.fk_prov = fk_prov;
        this.tipo = tipo;
        this.desc = desc;
    }

    public String getFk_prov() {
        return fk_prov;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDesc() {
        return desc;
    }


       public String getId() {
        return id;
    }
    
   
}
