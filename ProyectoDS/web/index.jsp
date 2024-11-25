<!-- webapp/index.jsp -->
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Llamadas</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <!-- Iconos (Font Awesome) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #f0f4f8, #d9e2ec);
            color: #333;
            margin: 0;
        }
	nav ul {
            list-style: none;
            padding: 10px;
            margin: 0;
            display: flex;
            justify-content: center;
            background: #ffffff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            color: #5d9cec;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease-in-out;
        }
        nav ul li a:hover {
            color: #4178a9;
        }

        .dashboard {
            background: #ffffff;
            border-radius: 15px;
            padding: 20px;
            margin: 30px auto;
            width: 90%;
            max-width: 1200px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }

        .dashboard h1 {
            text-align: center;
            font-size: 2.5rem;
            color: #5d9cec;
        }

        .stats {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin-top: 20px;
        }
        .stat-card {
            background: linear-gradient(135deg, #e3f2fd, #bbdefb);
            color: #333;
            border-radius: 10px;
            padding: 20px;
            text-align: center;
            width: 28%;
            margin: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        }

        .stat-card i {
            font-size: 3rem;
            margin-bottom: 10px;
            color: #5d9cec;
        }

        .quick-links {
            text-align: center;
            margin-top: 30px;
        }

       .button {
            display: inline-block;
            background: #5d9cec;
            color: #ffffff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: bold;
            margin: 10px;
            transition: background 0.3s ease-in-out;
        }

        .button:hover {
            background: #4178a9;
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
            <li><a href="agentes">Agentes</a></li>
            <li><a href="tickets">Tickets</a></li>
        </ul>
    </nav>
<!-- Contenido del Dashboard -->
    <section class="dashboard">
        <h1>Sistema de Llamadas</h1>

        <!-- Tarjetas de estadísticas -->
        <div class="stats">
            <div class="stat-card">
                <i class="fas fa-phone"></i>
                <h2>Total de Llamadas</h2>
                <p>${totalLlamadas}</p>
            </div>

            <div class="stat-card">
                <i class="fas fa-users"></i>
                <h2>Total de Clientes</h2>
                <p>Total de Clientes: ${totalClientes}</p>
            </div>

            <div class="stat-card">
                <i class="fas fa-smile"></i>
                <h2>Satisfacción Promedio</h2>
                <c:out value="${totalClientes}" />
                <p>${satisfaccionPromedio}%</p>
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