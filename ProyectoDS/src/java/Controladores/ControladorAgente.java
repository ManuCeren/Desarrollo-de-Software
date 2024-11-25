
/**
 *
 * @author Manuel Ceren
 */
package Controladores;

import Modelo.Agente;
import ModeloDAO.AgenteDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ControladorAgente", urlPatterns = {"/ControladorAgente"})
public class ControladorAgente extends HttpServlet {

    String agente="vistas/Agente.jsp";
    String listar="vistas/listarAgente.jsp";
    String index="index.jsp";


    
    Agente agen = new Agente();
    AgenteDAO dao=new AgenteDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


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
                
                case "detalles":
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
                    break;

                
                case "regresar":
                    acceso = index;
                    break;

                case "listar":
                    // Llama al método listar de AgenteDAO
                    List<Agente> listarAgentes = dao.listar();
                    request.setAttribute("listarAgentes", listarAgentes);
                    acceso = listar;
                    break;

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
        /*String action = request.getParameter("accion");
        String acceso = "";

        if (action.equalsIgnoreCase("add")) {
            // Obtén los parámetros del formulario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String direccion = request.getParameter("direccion");
            String fechaRegistro = request.getParameter("fechaRegistro");

            // Crea un objeto Cliente
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombreCliente(nombre);
            nuevoCliente.setApellidoCliente(apellido);
            nuevoCliente.setTelefono(telefono);
            nuevoCliente.setCorreo(correo);
            nuevoCliente.setDireccion(direccion);
            nuevoCliente.setFechaRegistro(fechaRegistro);

            // Llama al método add del DAO
            boolean agregado = dao.add(nuevoCliente);

            // Redirige según el resultado
            if (agregado) {
                request.setAttribute("mensaje", "Cliente agregado exitosamente.");
            } else {
                request.setAttribute("mensaje", "Error al agregar el cliente.");
            }

            acceso = "vistas/listar.jsp"; // Después de agregar, se puede redirigir a la lista de clientes
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);*/
    }

   
    @Override
    public String getServletInfo() {
        return "Controlador de agentes";
    }// </editor-fold>

}

