
package Modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class RegistroLlamadas {
    // Atributos
    private int idLlamada;
    private Timestamp fechaHoraLlamada;
    private Time horaInicio;
    private Time horaFinal;
    private String motivoLlamada;
    private String solucion;
    private String estado;
    private int idCliente;
    private int idAgente;
    private int idCategoria;

    // Constructor vacío
    public RegistroLlamadas() {}

    // Constructor completo
    public RegistroLlamadas(int idLlamada, Timestamp fechaHoraLlamada, Time horaInicio, Time horaFinal,
                            String motivoLlamada, String solucion, String estado, int idCliente,
                            int idAgente, int idCategoria) {
        this.idLlamada = idLlamada;
        this.fechaHoraLlamada = fechaHoraLlamada;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.motivoLlamada = motivoLlamada;
        this.solucion = solucion;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idAgente = idAgente;
        this.idCategoria = idCategoria;
    }

    // Constructor básico (sin ID)
    public RegistroLlamadas(Timestamp fechaHoraLlamada, Time horaInicio, Time horaFinal, String motivoLlamada,
                            String solucion, String estado, int idCliente, int idAgente, int idCategoria) {
        this.fechaHoraLlamada = fechaHoraLlamada;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.motivoLlamada = motivoLlamada;
        this.solucion = solucion;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idAgente = idAgente;
        this.idCategoria = idCategoria;
    }

    // Métodos Getters y Setters
    public int getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(int idLlamada) {
        this.idLlamada = idLlamada;
    }

    public Timestamp getFechaHoraLlamada() {
        return fechaHoraLlamada;
    }

    public void setFechaHoraLlamada(Timestamp fechaHoraLlamada) {
        this.fechaHoraLlamada = fechaHoraLlamada;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getMotivoLlamada() {
        return motivoLlamada;
    }

    public void setMotivoLlamada(String motivoLlamada) {
        this.motivoLlamada = motivoLlamada;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
