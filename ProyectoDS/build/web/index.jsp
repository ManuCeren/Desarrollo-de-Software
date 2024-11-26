<!-- webapp/index.jsp -->
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="ModeloDAO.ClienteDAO"%>
<%@page import="ModeloDAO.RegistroLlamadasDAO"%>
<%@page import="ModeloDAO.EncuestaDAO"%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sistema de Llamadas - Empresa Premium</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <!-- Iconos (Font Awesome) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: #f7f9fc; /* Fondo claro profesional */
            color: #2c3e50;
            margin: 0;
            line-height: 1.6;
        }

        nav {
            background: #1f2937; /* Azul oscuro elegante */
            box-shadow: 0 3px 8px rgba(0, 0, 0, 0.1);
        }

	nav ul {
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        nav ul li {
            margin: 0 20px;
        }

        nav ul li a {
            color: #e4e7eb; /* Gris claro */
            text-decoration: none;
            font-weight: 500;
            padding: 15px 20px;
            display: block;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        nav ul li a:hover {
            background-color: #374151; /* Azul intermedio */
            color: #ffffff;
        }
 	.dashboard {
            background: #ffffff; /* Fondo blanco para contraste */
            border-radius: 15px;
            padding: 40px;
            margin: 40px auto;
            width: 85%;
            max-width: 1200px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
        }

        .dashboard h1 {
            text-align: center;
            font-size: 2.5rem;
            color: #1f2937;
            margin-bottom: 30px;
            font-weight: 700;
        }

        .stats {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 20px;
        }
	.stat-card {
            background: linear-gradient(135deg, #1f2937, #374151); /* Fondo degradado azul oscuro */
            color: #e4e7eb; /* Texto claro */
            border-radius: 10px;
            padding: 30px;
            text-align: center;
            flex: 1;
            min-width: 260px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .stat-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
        }

        .stat-card i {
            font-size: 3.5rem;
            margin-bottom: 15px;
            color: #60a5fa; /* Azul claro */
        }

        .stat-card h2 {
            font-size: 1.8rem;
            margin: 15px 0;
        }
  	.quick-links {
            text-align: center;
            margin-top: 40px;
        }

        .button {
            display: inline-block;
            background: #2563eb; /* Azul profesional */
            color: #ffffff;
            text-decoration: none;
            padding: 15px 30px;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: bold;
            margin: 10px;
            transition: background 0.3s ease, transform 0.3s ease;
        }

        .button:hover {
            background: #1e40af; /* Azul más oscuro */
            transform: translateY(-3px);
        }
    </style>

</head>
<body>
    <!-- Barra de Navegación -->
    <nav>
        <ul>
            <li><a href="ControladorCliente?accion=clientes">Clientes</a></li>
            <li><a href="ControladorRegistro?accion=listar">Registro de Llamadas</a></li>
            <li><a href="ControladorEncuesta?accion=listar">Encuestas</a></li>
            <li><a href="ControladorAgente?accion=agente">Agentes</a></li>
            <li><a href="ControladorTicket?accion=listar">Tickets</a></li>
        </ul>
    </nav>
<!-- Contenido del Dashboard -->
    <section class="dashboard">
        <h1>Sistema de Llamadas</h1>
        <%
            RegistroLlamadasDAO dao1 = new RegistroLlamadasDAO();
            ClienteDAO dao = new ClienteDAO();
            EncuestaDAO dao2 = new EncuestaDAO();
                    
        %>

        <!-- Tarjetas de estadísticas -->
        <div class="stats">
            <div class="stat-card">
                <i class="fas fa-phone"></i>
                <h2>Total de Llamadas</h2>            
                <p><%= dao1.contarLlamadas() %></p>
            </div>

            <div class="stat-card">
                <i class="fas fa-users"></i>
                <h2>Total de Clientes</h2>
                <p><%= dao.contarClientes() %></p>
            </div>

            <div class="stat-card">
                <i class="fas fa-smile"></i>
                <h2>Satisfacción Promedio</h2>               
                <p><%= dao2.calcularSatisfaccionPromedio() %> %</p>
            </div>
        </div>

        <!-- Accesos Rápidos -->
        <div class="quick-links">
            <a href="ControladorCliente?accion=clientes" class="button">
                <i class="fas fa-user-cog"></i> Gestionar Clientes
            </a>
            <a href="ControladorRegistro?accion=listar" class="button">
                <i class="fas fa-phone-alt"></i> Registrar Nueva Llamada
            </a>
            <a href="ControladorEncuesta?accion=listar" class="button">
                <i class="fas fa-poll"></i> Ver Encuestas de Satisfacción
            </a>
            <a href="ControladorTikets?accion=tickets" class="button">
                <i class="fas fa-ticket-alt"></i> Gestionar Tickets
            </a>
        </div>
    </section>
</body>
</html>