
package Interfaces;

import Modelo.HistorialLlamadas;
import java.util.List;
import java.util.Date;

public interface CRUDHistorial {
    public boolean agregar(HistorialLlamadas historial);
    public List<HistorialLlamadas> listar();
    public boolean actualizar(HistorialLlamadas historial);
    public boolean eliminar(int idHistorial);
    

}
