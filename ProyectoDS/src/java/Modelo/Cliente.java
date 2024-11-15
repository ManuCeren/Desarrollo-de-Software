
package Modelo;

/**
 *
 * @author Manuel Ceren
 */
public class Cliente {
    int id_Cliente;
    String nombre_Cliente;
    String apellido_Cliente;
    String telefono;
    String correo;
    String direccion;
    String fecha_Registro;
    
    public Cliente(){}
    
    public Cliente(String nombre, String apellido, String tel, String email, String address, String fecha){
        this.nombre_Cliente = nombre;
        this.apellido_Cliente = apellido;
        this.telefono = tel;
        this.correo = email;
        this.direccion = address;
        this.fecha_Registro = fecha;
    }
    // Constructor con ID (para registros existentes)
    public Cliente(int id, String nombre, String apellido, String tel, String email, String address, String fecha) {
        this.id_Cliente = id;
        this.nombre_Cliente = nombre;
        this.apellido_Cliente = apellido;
        this.telefono = tel;
        this.correo = email;
        this.direccion = address;
        this.fecha_Registro = fecha;
    }

    // Getters y Setters
    public int getIdCliente() {
        return id_Cliente;
    }

    public void setIdCliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getNombreCliente() {
        return nombre_Cliente;
    }

    public void setNombreCliente(String nombre_Cliente) {
        this.nombre_Cliente = nombre_Cliente;
    }

    public String getApellidoCliente() {
        return apellido_Cliente;
    }

    public void setApellidoCliente(String apellido_Cliente) {
        this.apellido_Cliente = apellido_Cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaRegistro() {
        return fecha_Registro;
    }

    public void setFechaRegistro(String fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

}
