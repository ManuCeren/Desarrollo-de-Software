
package Modelo;

import java.util.Date;

public class Ticket {
    private int id_Ticket;
    private Date fecha_Ticket; 
    private String prioridad;  
    private String descripcion;
    private Date fecha_Fin;
    private String estado; 
    private int id_Llamada;
    private int id_Cliente;
    private int id_Agente;

    public Ticket() {
    }

    public Ticket(int id_Ticket, Date fecha_Ticket, String prioridad, String descripcion, Date fecha_Fin, String estado, int id_Llamada, int id_Cliente, int id_Agente) {
        this.id_Ticket = id_Ticket;
        this.fecha_Ticket = fecha_Ticket;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.fecha_Fin = fecha_Fin;
        this.estado = estado;
        this.id_Llamada = id_Llamada;
        this.id_Cliente = id_Cliente;
        this.id_Agente = id_Agente;
    }

    public int getIdTicket() {
        return id_Ticket;
    }

    public void setIdTicket(int id_Ticket) {
        this.id_Ticket = id_Ticket;
    }

    public Date getFechaTicket() {
        return fecha_Ticket;
    }

    public void setFechaTicket(Date fecha_Ticket) {
        this.fecha_Ticket = fecha_Ticket;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaFin() {
        return fecha_Fin;
    }

    public void setFechaFin(Date fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdLlamada() {
        return id_Llamada;
    }

    public void setIdLlamada(int id_Llamada) {
        this.id_Llamada = id_Llamada;
    }

    public int getIdCliente() {
        return id_Cliente;
    }

    public void setIdCliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getIdAgente() {
        return id_Agente;
    }

    public void setIdAgente(int id_Agente) {
        this.id_Agente = id_Agente;
    }
    
    

}
