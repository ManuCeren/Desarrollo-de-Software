
package Interfaces;

import Modelo.RegistroLlamadas;
import java.util.List;

public interface CRUDRegistro {
    public List<RegistroLlamadas> listar();
    public boolean agregar(RegistroLlamadas registro);
    public boolean eliminar(int idLlamada);
    public RegistroLlamadas buscarPorId(int idLlamada);
    public boolean actualizar(RegistroLlamadas llamada);
    

}
