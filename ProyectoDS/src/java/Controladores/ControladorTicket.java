package Controladores;

import Modelo.Ticket;
import ModeloDAO.TicketDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControladorTicket", urlPatterns = {"/ControladorTicket"})
public class ControladorTicket extends HttpServlet {
    TicketDAO dao = new TicketDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("listar".equalsIgnoreCase(accion)) {
            List<Ticket> tickets = dao.listar();
            request.setAttribute("tickets", tickets);
            RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/Ticket.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("agregar".equalsIgnoreCase(accion)) {
            Ticket ticket = new Ticket();
            ticket.setPrioridad(request.getParameter("prioridad"));
            ticket.setDescripcion(request.getParameter("descripcion"));
            ticket.setFechaFin(java.sql.Date.valueOf(request.getParameter("fechaFin")));
            ticket.setEstado(request.getParameter("estado"));
            ticket.setIdLlamada(1); // Cambiar a valores dinámicos
            ticket.setIdCliente(1);
            ticket.setIdAgente(1);

            boolean agregado = dao.agregar(ticket);
            if (agregado) {
                response.sendRedirect("ControladorTicket?accion=listar");
            } else {
                response.getWriter().write("Error al guardar el ticket.");
            }
        }
    }
}
