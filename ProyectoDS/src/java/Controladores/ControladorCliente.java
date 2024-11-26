
/**
 *
 * @author Manuel Ceren
 */
package Controladores;

import Modelo.Cliente;
import ModeloDAO.ClienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControladorCliente", urlPatterns = {"/ControladorCliente"})
public class ControladorCliente extends HttpServlet {

    String cliente = "vistas/clientes.jsp";
    String add = "vistas/AddCliente.jsp";
    String edit = "vistas/editCliente.jsp";

    Cliente c = new Cliente();
    ClienteDAO dao = new ClienteDAO();
    int id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        try {
            if (action == null || action.isEmpty()) {
                // Redirige al index.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                return;
            }

            switch (action.toLowerCase()) {
                case "add":
                    acceso = add;
                    break;

                case "clientes":
                    // Cargar la lista de clientes
                    List<Cliente> listaClientes = dao.listar();
                    request.setAttribute("clientes", listaClientes);

                    acceso = cliente;
                    break;

                case "editar":
                    String idcliParam = request.getParameter("id");
                    if (idcliParam != null && !idcliParam.isEmpty()) {
                        request.setAttribute("idcli", idcliParam);
                        acceso = edit;
                    } else {
                        // Si idcli no es válido, redirigir a la lista de clientes o mostrar error
                        request.setAttribute("error", "ID de cliente no proporcionado o inválido.");
                        acceso = "vistas/error.jsp";
                    }
                    break;

                case "actualizar":
                    id = Integer.parseInt(request.getParameter("txtid"));
                    String nom = request.getParameter("txtNom");
                    String ape = request.getParameter("txtApe");
                    String tel = request.getParameter("txtTel");
                    String email = request.getParameter("txtCorreo");
                    String direc = request.getParameter("txtDireccion");
                    String fecha = request.getParameter("txtFecha");

                    c.setIdCliente(id);
                    c.setNombreCliente(nom);
                    c.setApellidoCliente(ape);
                    c.setTelefono(tel);
                    c.setCorreo(email);
                    c.setDireccion(direc);
                    c.setFechaRegistro(fecha);

                    boolean actualizado = dao.edit(c);

                    if (actualizado) {
                        // Si se actualizó correctamente, recargar lista de clientes
                        List<Cliente> listaActualizada = dao.listar();
                        request.setAttribute("clientes", listaActualizada);
                        acceso = cliente;
                    } else {
                        request.setAttribute("error", "Error al actualizar el cliente.");
                        acceso = "vistas/error.jsp";
                    }
                    break;

                case "eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);

                    // Recargar la lista de clientes
                    List<Cliente> listaDespuesEliminacion = dao.listar();
                    request.setAttribute("clientes", listaDespuesEliminacion);

                    acceso = cliente;
                    break;

                default:
                    acceso = "index.jsp"; // Si la acción no es válida, redirige al inicio
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
        String action = request.getParameter("accion");

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
                List<Cliente> listaClientesAgregados = dao.listar();
                request.setAttribute("clientes", listaClientesAgregados);
            } else {
                request.setAttribute("error", "Error al agregar el cliente.");
            }

            RequestDispatcher vista = request.getRequestDispatcher(cliente);
            vista.forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Controlador de clientes";
    }
}

