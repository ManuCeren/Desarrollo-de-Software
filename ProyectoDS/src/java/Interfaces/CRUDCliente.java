
package Interfaces;

import Modelo.Cliente;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Manuel Ceren
 */
public interface CRUDCliente {
    public List listar();
    public int contarClientes();
    public boolean add(Cliente cliente);
    public Cliente list(int id);
    public boolean eliminar(int id);
    public boolean edit(Cliente cli);

}
