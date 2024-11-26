package Controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet para manejar el inicio de sesión con soporte para múltiples usuarios
 */
public class ControladorLogin extends HttpServlet {

    // Mapa de usuarios y contraseñas
    private final Map<String, String> usuarios;

    // Constructor para inicializar los usuarios
    public ControladorLogin() {
        usuarios = new HashMap<>();
        usuarios.put("Astrid", "14072*");
        usuarios.put("ManuCeren", "150987#");
        usuarios.put("Marcela", "2002*");
        usuarios.put("Emeli", "2018*");
        usuarios.put("Manuel", "97856*"); // Usuario y contraseña Ingeniero Urrutia
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        // Validar credenciales
        if (validarCredenciales(usuario, password)) { 
            // Si las credenciales son válidas, iniciar sesión
            HttpSession session = request.getSession(); // Crear o recuperar la sesión
            session.setAttribute("usuario", usuario); // Guardar el usuario en la sesión
            response.sendRedirect("index.jsp"); // Redirigir al inicio
        } else {
            // Si las credenciales no son válidas, enviar mensaje de error
            request.setAttribute("mensaje", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("vistas/login.jsp").forward(request, response);
        }
    }

    /**
     * Método para validar credenciales
     */
    private boolean validarCredenciales(String usuario, String password) {
        // Validar si el usuario y contraseña coinciden
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(password);
    }
}
