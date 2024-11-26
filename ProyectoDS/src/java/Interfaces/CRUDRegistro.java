
package Interfaces;

import Modelo.RegistroLlamadas;
import Modelo.Seguimiento;
import java.util.List;

public interface CRUDRegistro {
    public List<RegistroLlamadas> listar();
    public List<Seguimiento> listarPorLlamada(int idLlamada);
    public int contarLlamadas();
    public boolean agregar(RegistroLlamadas registro);
    public boolean eliminar(int idLlamada);
    public RegistroLlamadas buscarPorId(int idLlamada);
    public boolean actualizar(RegistroLlamadas llamada);
    public List<RegistroLlamadas> listarPorAgente(int idAgente);
    

}
