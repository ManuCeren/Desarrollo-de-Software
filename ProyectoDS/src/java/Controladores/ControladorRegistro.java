
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

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
                
            case "editar":
                editar(request, response);
                break;
            case "verHistorial":
                verHistorial(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                response.sendRedirect("vistas/Llamadas.jsp");
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion.toLowerCase()) {
            case "guardar":
                guardar(request, response);
                break;
            case "guardarSeguimiento":
                guardarSeguimiento(request, response);
                break;
            case "actualizar":
                actualizar(request, response);
                break;
            default:
                response.sendRedirect("ControladorRegistro?accion=listar");
                break;
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
        // Ajustar el formato de fecha y hora
        String fechaHora = request.getParameter("fechaHoraLlamada").replace("T", " ") + ":00";

        RegistroLlamadas llamada = new RegistroLlamadas();
        llamada.setFechaHoraLlamada(Timestamp.valueOf(fechaHora)); // Convertir correctamente
        llamada.setHoraInicio(Time.valueOf(request.getParameter("horaInicio") + ":00")); // Agregar segundos
        llamada.setHoraFinal(Time.valueOf(request.getParameter("horaFinal") + ":00")); // Agregar segundos
        llamada.setMotivoLlamada(request.getParameter("motivo"));
        llamada.setSolucion(request.getParameter("solucion"));
        llamada.setEstado(request.getParameter("estado"));
        llamada.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        llamada.setIdAgente(Integer.parseInt(request.getParameter("idAgente")));
        llamada.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));

        // Llamar al DAO para guardar
        boolean guardado = daoLlamada.agregar(llamada);



            if (guardado) {
                response.sendRedirect("ControladorRegistro?accion=listar");
            } else {
                request.setAttribute("mensaje", "Error al guardar la llamada.");
                listar(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ControladorRegistro?accion=listar");
        }
    }
    
    private void verHistorial(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            // Obtener el ID de la llamada desde la solicitud
            int idLlamada = Integer.parseInt(request.getParameter("idLlamada"));

            // Obtener la llamada específica
            RegistroLlamadas llamada = daoLlamada.buscarPorId(idLlamada);

            // Obtener el historial de seguimientos para la llamada
            List<Seguimiento> historial = daoSeguimiento.listarPorLlamada(idLlamada);

            // Configurar los atributos para la vista
            request.setAttribute("llamada", llamada);
            request.setAttribute("historial", historial);

            // Redirigir a la vista del historial
            RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/HistorialLlamada.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al obtener el historial de la llamada.");
            response.sendRedirect("ControladorRegistro?accion=listar");
        }
    }
    private void editar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("editar".equalsIgnoreCase(accion)) {
            try {
                // Obtener el ID de la llamada desde los parámetros
                int idLlamada = Integer.parseInt(request.getParameter("id"));

                // Buscar la llamada en la base de datos
                RegistroLlamadas llamada = daoLlamada.buscarPorId(idLlamada);

                // Establecer la llamada como atributo para la vista
                request.setAttribute("llamada", llamada);

                // Redirigir a la vista de edición
                RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/EditarLlamada.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensaje", "Error al obtener los datos para editar.");
                response.sendRedirect("ControladorRegistro?accion=listar");
            }
        }
    }
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        try {
            // Obtener los datos enviados desde el formulario
            int idLlamada = Integer.parseInt(request.getParameter("idLlamada"));
            String estado = request.getParameter("estado"); // Valor del nuevo estado

            // Actualizar el estado de la llamada
            RegistroLlamadas llamada = daoLlamada.buscarPorId(idLlamada);
            llamada.setEstado(estado); // Actualizar el estado

            boolean actualizado = daoLlamada.actualizar(llamada);

            if (actualizado) {
                response.sendRedirect("ControladorRegistro?accion=listar");
            } else {
                request.setAttribute("mensaje", "Error al actualizar el estado de la llamada.");
                response.sendRedirect("ControladorRegistro?accion=editar&id=" + idLlamada);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
