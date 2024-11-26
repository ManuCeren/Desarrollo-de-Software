
package Controladores;

import Modelo.Agente;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ControladorTicket", urlPatterns = {"/ControladorTicket"})
public class ControladorTicket extends HttpServlet {
   
    String ticket="vistas/Ticket.jsp";
    String index="index.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        String acceso = "";
        String action = request.getParameter("accion");

        try {

            switch (action.toLowerCase()) {
                
                case "ticket":
                    acceso = ticket;
                    break;
                
                /*case "detalles":
                    try {
                        int idAgente = Integer.parseInt(request.getParameter("id"));
                        Agente agente = dao.buscarPorId(idAgente);

                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");

                        if (agente != null) {
                            // Crear JSON del agente
                            String json = String.format(
                                "{" +
                                    "\"idAgente\": %d," +
                                    "\"nombreAgente\": \"%s\"," +
                                    "\"apellidoAgente\": \"%s\"," +
                                    "\"turno\": \"%s\"," +
                                    "\"departamento\": \"%s\"" +
                                "}",
                                agente.getIdAgente(),
                                agente.getNombreAgente(),
                                agente.getApellidoAgente(),
                                agente.getTurno(),
                                agente.getNombreDepartamento()
                            );
                            response.getWriter().write(json);
                        } else {
                            response.getWriter().write("{\"error\": \"Agente no encontrado.\"}");
                        }
                    } catch (Exception e) {
                        response.getWriter().write("{\"error\": \"Error procesando la solicitud.\"}");
                    }
                    break;*/

                
                case "regresar":
                    acceso = index;
                    break;

                /*case "listar":
                    // Llama al método listar de AgenteDAO
                    List<Agente> listarAgentes = dao.listar();
                    request.setAttribute("listarAgentes", listarAgentes);
                    acceso = listar;
                    break;*/

                /*case "editar":
                    // Obtiene el ID del cliente a editar
                    request.setAttribute("idper", request.getParameter("id"));
                    acceso = edit;
                    break;*/

                default:
                    acceso = "index.jsp"; // Si la acción no es válida, redirige al inicio
            }
        } catch (Exception e) {
            // Manejo de error: Redirige a una página de error o muestra mensaje
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
