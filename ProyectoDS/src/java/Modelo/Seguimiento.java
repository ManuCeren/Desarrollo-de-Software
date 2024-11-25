
package Modelo;

import java.util.Date;

public class Seguimiento {
    private int idSeguimiento;
    private Date fechaSeguimiento;
    private String acciones;
    private String resultadoFinal;
    private int idLlamada;

    // Constructor vacío
    public Seguimiento() {}

    // Constructor con parámetros
    public Seguimiento(int idSeguimiento, Date fechaSeguimiento, String acciones, String resultadoFinal, int idLlamada) {
        this.idSeguimiento = idSeguimiento;
        this.fechaSeguimiento = fechaSeguimiento;
        this.acciones = acciones;
        this.resultadoFinal = resultadoFinal;
        this.idLlamada = idLlamada;
    }

    // Getters y Setters
    public int getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
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

    public int getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(int idLlamada) {
        this.idLlamada = idLlamada;
    }
}

