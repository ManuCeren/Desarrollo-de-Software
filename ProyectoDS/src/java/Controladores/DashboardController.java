
package Controladores;

import ModeloDAO.ClienteDAO;
import ModeloDAO.RegistroLlamadasDAO;
import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DashboardController", urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        try {
            if (action == null || action.equalsIgnoreCase("dashboard")) {
                // Llama al método de conteo de clientes
                ClienteDAO clienteDAO = new ClienteDAO();
                int totalClientes = clienteDAO.contarClientes();
                request.setAttribute("totalClientes", totalClientes);

                // Llama al método de conteo de llamadas (similar al anterior)
                /*RegistroLlamadasDAO llamadasDAO = new RegistroLlamadasDAO();
                int totalLlamadas = llamadasDAO.contarLlamadas();
                request.setAttribute("totalLlamadas", totalLlamadas);*/

                // Aquí puedes incluir más estadísticas si es necesario.

                // Redirige al JSP del dashboard
                RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/dashboard.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("vistas/error.jsp");
        }
    }
}