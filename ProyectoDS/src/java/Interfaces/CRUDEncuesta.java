
package Interfaces;

import Modelo.Encuesta;
import java.util.List;


public interface CRUDEncuesta {
    public List<Encuesta> listar();
    public boolean agregar(Encuesta encuesta); 
    public double calcularSatisfaccionPromedio();

}
