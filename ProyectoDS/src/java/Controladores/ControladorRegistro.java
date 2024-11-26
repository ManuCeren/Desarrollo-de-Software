
/**
 *
 * @author Manuel Ceren
 */
package Controladores;

import Modelo.RegistroLlamadas;
import Modelo.Seguimiento;
import ModeloDAO.RegistroLlamadasDAO;
import ModeloDAO.SeguimientoDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.List;

@WebServlet(name = "ControladorRegistro", urlPatterns = {"/ControladorRegistro"})
public class ControladorRegistro extends HttpServlet {

    RegistroLlamadasDAO daoLlamada = new RegistroLlamadasDAO();
    SeguimientoDAO daoSeguimiento = new SeguimientoDAO();  // DAO para manejar seguimientos

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("listar".equalsIgnoreCase(accion)) {
            listar(request, response);
        } else if ("buscar".equalsIgnoreCase(accion)) {
            buscarPorId(request, response);
        } else if ("eliminar".equalsIgnoreCase(accion)) {
            eliminar(request, response);
        } else if ("seguimiento".equalsIgnoreCase(accion)) {
            // Acción para gestionar los seguimientos
            listarSeguimientos(request, response);
        } else {
            response.sendRedirect("vistas/Llamadas.jsp"); // Página predeterminada
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("guardar".equalsIgnoreCase(accion)) {
            guardar(request, response);
        } else if ("guardarSeguimiento".equalsIgnoreCase(accion)) {
            guardarSeguimiento(request, response);  // Acción para guardar un seguimiento
        } else {
            response.sendRedirect("ControladorRegistro?accion=listar");
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<RegistroLlamadas> lista = daoLlamada.listar();
        request.setAttribute("listar", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/Llamadas.jsp");
        dispatcher.forward(request, response);
    }

    private void buscarPorId(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int idLlamada = Integer.parseInt(request.getParameter("idLlamada"));
            RegistroLlamadas llamada = daoLlamada.buscarPorId(idLlamada);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            if (llamada != null) {
                out.write(String.format(
                        "{" +
                                "\"idLlamada\": %d," +
                                "\"fechaHoraLlamada\": \"%s\"," +
                                "\"horaInicio\": \"%s\"," +
                                "\"horaFinal\": \"%s\"," +
                                "\"motivoLlamada\": \"%s\"," +
                                "\"solucion\": \"%s\"," +
                                "\"estado\": \"%s\"" +
                                "}",
                        llamada.getIdLlamada(),
                        llamada.getFechaHoraLlamada() != null ? llamada.getFechaHoraLlamada().toString() : "",
                        llamada.getHoraInicio() != null ? llamada.getHoraInicio().toString() : "",
                        llamada.getHoraFinal() != null ? llamada.getHoraFinal().toString() : "",
                        llamada.getMotivoLlamada() != null ? llamada.getMotivoLlamada() : "",
                        llamada.getSolucion() != null ? llamada.getSolucion() : "",
                        llamada.getEstado() != null ? llamada.getEstado() : ""
                ));
            } else {
                out.write("{\"error\": \"Llamada no encontrada.\"}");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("{\"error\": \"ID inválido.\"}");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int idLlamada = Integer.parseInt(request.getParameter("id"));
            boolean eliminado = daoLlamada.eliminar(idLlamada);

            if (eliminado) {
                response.sendRedirect("ControladorRegistro?accion=listar");
            } else {
                request.setAttribute("mensaje", "Error al eliminar la llamada.");
                listar(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("ControladorRegistro?accion=listar");
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            RegistroLlamadas llamada = new RegistroLlamadas();
            llamada.setFechaHoraLlamada(Timestamp.valueOf(request.getParameter("fechaHoraLlamada")));
            llamada.setHoraInicio(Time.valueOf(request.getParameter("horaInicio")));
            llamada.setHoraFinal(Time.valueOf(request.getParameter("horaFinal")));
            llamada.setMotivoLlamada(request.getParameter("motivo"));
            llamada.setSolucion(request.getParameter("solucion"));
            llamada.setEstado(request.getParameter("estado"));
            llamada.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
            llamada.setIdAgente(Integer.parseInt(request.getParameter("idAgente")));
            llamada.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));

            boolean guardado = daoLlamada.agregar(llamada);

            if (guardado) {
                response.sendRedirect("ControladorRegistro?accion=listar");
            } else {
                request.setAttribute("mensaje", "Error al guardar la llamada.");
                listar(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("ControladorRegistro?accion=listar");
        }
    }

    // Método para listar los seguimientos de una llamada
    private void listarSeguimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idLlamada = Integer.parseInt(request.getParameter("idLlamada"));
        List<Seguimiento> listaSeguimientos = daoSeguimiento.listarPorLlamada(idLlamada);
        request.setAttribute("seguimientos", listaSeguimientos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/Seguimientos.jsp");
        dispatcher.forward(request, response);
    }

    // Método para guardar un seguimiento
    private void guardarSeguimiento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Seguimiento seguimiento = new Seguimiento();
            int idLlamada = Integer.parseInt(request.getParameter("idLlamada"));
            String acciones = request.getParameter("acciones");
            String resultadoFinal = request.getParameter("resultadoFinal");
            Timestamp fechaSeguimiento = new Timestamp(System.currentTimeMillis()); // Fecha actual

            seguimiento.setIdLlamada(idLlamada);
            seguimiento.setAcciones(acciones);
            seguimiento.setResultadoFinal(resultadoFinal);
            seguimiento.setFechaSeguimiento(fechaSeguimiento);

            boolean guardado = daoSeguimiento.agregar(seguimiento);

            if (guardado) {
                response.sendRedirect("ControladorRegistro?accion=seguimiento&idLlamada=" + idLlamada);
            } else {
                request.setAttribute("mensaje", "Error al guardar el seguimiento.");
                listarSeguimientos(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("ControladorRegistro?accion=seguimiento");
        }
    }
}
