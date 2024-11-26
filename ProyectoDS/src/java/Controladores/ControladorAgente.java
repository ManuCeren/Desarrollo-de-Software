
/**
 *
 * @author Manuel Ceren
 */
package Controladores;

import Modelo.Agente;
import Modelo.RegistroLlamadas;
import ModeloDAO.AgenteDAO;
import ModeloDAO.RegistroLlamadasDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControladorAgente", urlPatterns = {"/ControladorAgente"})
public class ControladorAgente extends HttpServlet {

    private final String agente = "vistas/Agente.jsp";
    private final String listar = "vistas/listarAgente.jsp";
    private final String index = "index.jsp";

    AgenteDAO daoAgente = new AgenteDAO();
    RegistroLlamadasDAO daoLlamadas = new RegistroLlamadasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        try {
            switch (action.toLowerCase()) {
                case "agente":
                    acceso = agente;
                    break;

                case "verdetalles":
                    verDetalles(request, response);
                    return;

                case "listar":
                    listarAgentes(request, response);
                    return;

                case "regresar":
                    acceso = index;
                    break;

                default:
                    acceso = "vistas/Agente.jsp"; // Redirige al inicio si la acción no es válida
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al procesar la solicitud.");
            acceso = "vistas/error.jsp";
        }

        // Redirige a la vista correspondiente
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implementa la lógica para manejar solicitudes POST si es necesario
    }

    private void listarAgentes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Agente> listarAgentes = daoAgente.listar();
            request.setAttribute("listarAgentes", listarAgentes);
            RequestDispatcher dispatcher = request.getRequestDispatcher(listar);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al listar los agentes.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void verDetalles(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            int idAgente = Integer.parseInt(request.getParameter("id"));

            // Obtener el agente por ID
            Agente agente = daoAgente.buscarPorId(idAgente);

            // Obtener las llamadas asociadas al agente
            List<RegistroLlamadas> llamadas = daoLlamadas.listarPorAgente(idAgente);

            // Pasar los datos a la vista
            request.setAttribute("agente", agente);
            request.setAttribute("llamadas", llamadas);

            // Redirigir a la vista de detalles
            RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/DetallesAgente.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la solicitud de detalles.");
            response.sendRedirect("ControladorAgente?accion=listar");
        }
    }


    @Override
    public String getServletInfo() {
        return "Controlador de agentes";
    }
}


