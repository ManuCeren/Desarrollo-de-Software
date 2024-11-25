
/**
 *
 * @author Manuel Ceren
 */
package Controladores;

import Modelo.Encuesta;
import ModeloDAO.EncuestaDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControladorEncuesta", urlPatterns = {"/ControladorEncuesta"})
public class ControladorEncuesta extends HttpServlet {

    String listar = "vistas/Encuesta.jsp";
    String add = "vistas/AddEncuesta.jsp";
    String edit = "vistas/EditEncuesta.jsp";

    Encuesta encuesta = new Encuesta();
    EncuestaDAO dao = new EncuestaDAO();
    int id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        try {
            switch (action.toLowerCase()) {
                case "listar":
                    List<Encuesta> listaEncuesta = dao.listar(); // Llama al método listar del DAO
                    request.setAttribute("listar", listaEncuesta);
                    acceso = listar;
                    break;

                case "add":
                    acceso = add;
                    break;

                /*case "editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("encuesta", dao.buscarPorId(id));
                    acceso = edit;
                    break;*/

                default:
                    acceso = "index.jsp"; // Redirige al inicio por defecto
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
            acceso = "vistas/error.jsp";
        }

        // Redirige a la vista correspondiente
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

            if ("guardar".equalsIgnoreCase(action)) {
                try {
                    // Obtener datos del formulario
                    int calificacion = Integer.parseInt(request.getParameter("calificacion"));
                    String comentarios = request.getParameter("comentarios");
                    int idLlamada = Integer.parseInt(request.getParameter("idLlamada"));

                    // Crear el objeto Encuesta
                    Encuesta encuesta = new Encuesta();
                    encuesta.setCalificacion(calificacion);
                    encuesta.setComentarios(comentarios);
                    encuesta.setIdLlamada(idLlamada);

                    // Guardar en la base de datos
                    boolean agregado = dao.agregar(encuesta);

                    // Mensajes opcionales
                    if (agregado) {
                        System.out.println("Encuesta guardada con éxito.");
                    } else {
                        System.out.println("Error al guardar la encuesta.");
                    }

                    // Redirigir al listado de encuestas
                    response.sendRedirect("ControladorEncuesta?accion=listar");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/error.jsp");
                    dispatcher.forward(request, response);
                }
            }

    }

    @Override
    public String getServletInfo() {
        return "Controlador de Encuestas";
    }
}


