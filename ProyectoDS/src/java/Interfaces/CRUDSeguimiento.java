
package Interfaces;

import Modelo.Seguimiento;
import java.util.List;

public interface CRUDSeguimiento {
    public List<Seguimiento> listarPorLlamada(int idLlamada);
    public boolean agregar(Seguimiento seguimiento);
}
