
package Modelo;

import java.util.Date;

public class HistorialLlamadas {
    // Atributos
    private int idHistorial;
    private Date fechaRegistro;
    private String comentarios;
    private int idLlamada;

    // Constructor vacío
    public HistorialLlamadas() {}

    // Constructor con parámetros
    public HistorialLlamadas(int idHistorial, Date fechaRegistro, String comentarios, int idLlamada) {
        this.idHistorial = idHistorial;
        this.fechaRegistro = fechaRegistro;
        this.comentarios = comentarios;
        this.idLlamada = idLlamada;
    }

    // Métodos Getters y Setters
    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(int idLlamada) {
        this.idLlamada = idLlamada;
    }
}

