
package Modelo;

public class Encuesta {
    private int idEncuesta;
    private int calificacion;
    private String comentarios;
    private int idLlamada;
    private String fechaHoraLlamada; // Información de la llamada

    public Encuesta() {
    }

    public Encuesta(int idEncuesta, int calificacion, String comentarios, int idLlamada, String fechaHoraLlamada) {
        this.idEncuesta = idEncuesta;
        this.calificacion = calificacion;
        this.comentarios = comentarios;
        this.idLlamada = idLlamada;
        this.fechaHoraLlamada = fechaHoraLlamada;
    }
    
    

    // Getters y Setters
    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
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
    

    public String getFechaHoraLlamada() {
        return fechaHoraLlamada;
    }

    public void setFechaHoraLlamada(String fechaHoraLlamada) {
        this.fechaHoraLlamada = fechaHoraLlamada;
    }
}

