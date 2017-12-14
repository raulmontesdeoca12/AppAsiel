/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Empleados;

/**
 *
 * @author user
 */
public class Clase_telefono_empleado {
    
    String id;
    String tipo;
    String numero;
    String persona;

    public Clase_telefono_empleado(String id) {
        this.id = id;
    }

    public Clase_telefono_empleado(String id, String tipo, String numero, String persona) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
        this.persona = persona;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }

    public String getPersona() {
        return persona;
    }
    
    
    
}
