
package Interfaces;

import Modelo.Ticket;
import java.util.List;

public interface CRUDTicket {
    public boolean agregar(Ticket ticket);
    public List<Ticket> listar();
    public boolean actualizar(Ticket ticket);
    public boolean eliminar(int idTicket);
    
}
