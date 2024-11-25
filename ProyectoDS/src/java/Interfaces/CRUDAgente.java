
package Interfaces;

import Modelo.Agente;
import java.util.List;

public interface CRUDAgente {
     public boolean agregar(Agente agente);
     public List<Agente> listar();
     public boolean actualizar(Agente agente);
     public boolean eliminar(int idAgente);

}
