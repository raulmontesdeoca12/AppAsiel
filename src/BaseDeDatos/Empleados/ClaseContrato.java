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
public class ClaseContrato {
    String id;
    String fecha;
    String fechae;
    Double sinicial;
    Double sfinal;
    String horario;
    String empleado;
    String cargo;
    int base,alimentacion,comsion,destajo;

    public String getFechae() {
        return fechae;
    }

    public ClaseContrato(String id, String fecha, String fechae, Double sinicial, Double sfinal, String horario, String empleado, String cargo, int base, int alimentacion, int comsion, int destajo) {
        this.id = id;
        this.fecha = fecha;
        this.fechae = fechae;
        this.sinicial = sinicial;
        this.sfinal = sfinal;
        this.horario = horario;
        this.empleado = empleado;
        this.cargo = cargo;
        this.base = base;
        this.alimentacion = alimentacion;
        this.comsion = comsion;
        this.destajo = destajo;
    }

  

  
    
    public ClaseContrato(String id, String fecha, Double sinicial, Double sfinal, String horario, String empleado, String cargo, int base, int alimentacion, int comsion, int destajo) {
        this.id = id;
        this.fecha = fecha;
        this.sinicial = sinicial;
        this.sfinal = sfinal;
        this.horario = horario;
        this.empleado = empleado;
        this.cargo = cargo;
        this.base = base;
        this.alimentacion = alimentacion;
        this.comsion = comsion;
        this.destajo = destajo;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public Double getSinicial() {
        return sinicial;
    }

    public Double getSfinal() {
        return sfinal;
    }

    public String getHorario() {
        return horario;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getCargo() {
        return cargo;
    }

    public int getBase() {
        return base;
    }

    public int getAlimentacion() {
        return alimentacion;
    }

    public int getComsion() {
        return comsion;
    }

    public int getDestajo() {
        return destajo;
    }
    
    
    
    
}
