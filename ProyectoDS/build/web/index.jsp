<!-- webapp/index.jsp -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Sistema de Llamadas</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <!-- Barra de Navegación -->
    <nav>
        <ul>
            <li><a href="ControladorCliente?accion=clientes">Clientes</a></li>
            <li><a href="registroLlamadas">Registro de Llamadas</a></li>
            <li><a href="encuestas">Encuestas</a></li>
            <li><a href="agentes">Agentes</a></li>
            <li><a href="tickets">Tickets</a></li>
        </ul>
    </nav>

    <!-- Contenido del Dashboard -->
    <section>
        <h1>Dashboard - Sistema de Llamadas</h1>

        <!-- Tarjetas de estadísticas -->
        <div class="stats">
            <div class="stat-card">
                <h2>Total de Llamadas</h2>
                <p>${totalLlamadas}</p>
            </div>

            <div class="stat-card">
                <h2>Total de Clientes</h2>
                <p>${totalClientes}</p>
            </div>

            <div class="stat-card">
                <h2>Satisfacción Promedio</h2>
                <p>${satisfaccionPromedio}%</p>
            </div>
        </div>

        <!-- Enlaces a Secciones Clave -->
        <div class="quick-links">
            <h2>Accesos Rápidos</h2>
            <button onclick="window.location.href='ControladorCliente?accion=clientes'">Gestionar Clientes</button>
            <button onclick="window.location.href='registroLlamadas'">Registrar Nueva Llamada</button>
            <button onclick="window.location.href='encuestas'">Ver Encuestas de Satisfacción</button>
            <button onclick="window.location.href='tickets'">Gestionar Tickets</button>
        </div>
    </section>
</body>
</html>
