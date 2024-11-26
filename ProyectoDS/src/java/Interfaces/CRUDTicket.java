
package Interfaces;

import Modelo.Ticket;
import java.util.List;

public interface CRUDTicket {
    
    public List<Ticket> listar();
    public boolean agregar(Ticket ticket);
    public Ticket buscarPorId(int id);
    public boolean eliminar(int idTicket);
    
}
