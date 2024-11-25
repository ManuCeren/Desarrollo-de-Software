
package Modelo;

public class Agente {
    // Atributos
    private int idAgente;
    private String nombreAgente;
    private String apellidoAgente;
    private String turno;
    private int idDepartamento;
    private String nombreDepartamento;

    // Constructor vacío
    public Agente() {}


    // Constructor con parámetros
    public Agente(int idAgente, String nombreAgente, String apellidoAgente, String turno, int idDepartamento, String nombreDepartamento) {
        this.idAgente = idAgente;
        this.nombreAgente = nombreAgente;
        this.apellidoAgente = apellidoAgente;
        this.turno = turno;
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }
   

    // Métodos Getters y Setters
    public int getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }

    public String getNombreAgente() {
        return nombreAgente;
    }

    public void setNombreAgente(String nombreAgente) {
        this.nombreAgente = nombreAgente;
    }

    public String getApellidoAgente() {
        return apellidoAgente;
    }

    public void setApellidoAgente(String apellidoAgente) {
        this.apellidoAgente = apellidoAgente;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
        public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

}

