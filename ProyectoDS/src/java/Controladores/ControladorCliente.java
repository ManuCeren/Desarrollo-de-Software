
/**
 *
 * @author Manuel Ceren
 */
package Controladores;

import Modelo.Cliente;
import ModeloDAO.ClienteDAO;
import ModeloDAO.RegistroLlamadasDAO;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ControladorCliente", urlPatterns = {"/ControladorCliente"})
public class ControladorCliente extends HttpServlet {

    String listar="vistas/listar.jsp";
    String cliente="vistas/clientes.jsp";
    String add="vistas/AddCliente.jsp";
    String edit="vistas/editCliente.jsp";

    
    Cliente c =new Cliente();
    ClienteDAO dao=new ClienteDAO();
    int id;
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
            if (action == null) {
                 ClienteDAO clienteDAO = new ClienteDAO();
                int totalClientes = clienteDAO.contarClientes();

                // Configura el atributo para hacerlo visible en el JSP
                request.setAttribute("totalClientes", totalClientes);
                RegistroLlamadasDAO llamadasDAO = new RegistroLlamadasDAO();
                
                int totalLlamadas = llamadasDAO.contarLlamadas();
                request.setAttribute("totalLlamadas", totalLlamadas);

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
                    // Llama al método listar de ClienteDAO
                    List<Cliente> listaClientes = dao.listar();
                    request.setAttribute("clientes", listaClientes);

                    // Llama al método de conteo de clientes
                    int totalClientes = dao.contarClientes();
                    request.setAttribute("totalClientes", totalClientes);

                    acceso = cliente;
                    break;

                case "editar":
                    // Obtiene el ID del cliente a editar
                    request.setAttribute("idcli", request.getParameter("id"));
                    acceso = edit;
                    break;
                    
                case "Actualizar":
                    // Obtiene el ID del cliente a editar
                        id=Integer.parseInt(request.getParameter("txtid"));
                        String nom=request.getParameter("txtNom");
                        String ape=request.getParameter("txtApe");
                        String tel=request.getParameter("txtTel");
                        String email=request.getParameter("txtCorreo");
                        String direc=request.getParameter("txtdireccion");
                        String fecha=request.getParameter("txtfecha");
                        c.setIdCliente(id);
                        c.setNombreCliente(nom);
                        c.setApellidoCliente(ape);
                        c.setTelefono(tel);
                        c.setCorreo(email);
                        c.setDireccion(direc);
                        c.setFechaRegistro(fecha);
                        dao.edit(c);
                        acceso=listar;
                    break;

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
        String action = request.getParameter("accion");
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

            acceso = "vistas/clientes.jsp"; // Después de agregar, se puede redirigir a la lista de clientes
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Controlador de clientes";
    }// </editor-fold>

}

