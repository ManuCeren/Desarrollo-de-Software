
package Modelo;

import java.sql.Timestamp;

public class Seguimiento {
    private int idSeguimiento;
    private int idLlamada;  // Relación con la llamada
    private String acciones;  // Cambio de 'descripcion' a 'acciones'
    private String resultadoFinal;  // Nuevo atributo agregado
    private Timestamp fechaSeguimiento;

    public Seguimiento() {
    }

    
    // Constructor
    public Seguimiento(int idLlamada, String acciones, String resultadoFinal, Timestamp fechaSeguimiento) {
        this.idLlamada = idLlamada;
        this.acciones = acciones;
        this.resultadoFinal = resultadoFinal;
        this.fechaSeguimiento = fechaSeguimiento;
    }
    
    public Seguimiento(int idSeguimiento, int idLlamada, String acciones, String resultadoFinal, Timestamp fechaSeguimiento) {
        this.idSeguimiento = idSeguimiento;
        this.idLlamada = idLlamada;
        this.acciones = acciones;
        this.resultadoFinal = resultadoFinal;
        this.fechaSeguimiento = fechaSeguimiento;
    }

    // Métodos Getters y Setters
    public int getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public int getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(int idLlamada) {
        this.idLlamada = idLlamada;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

    public Timestamp getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Timestamp fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }
}

