
package Interfaces;

import Modelo.Categoria;
import java.util.List;


public interface CRUDCategoria {
    public List<Categoria> listar();
    public boolean agregar(Categoria categoria);
    

}
