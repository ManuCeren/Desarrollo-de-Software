<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h1 class="text-center">Dashboard</h1>
        <div class="stats">
            <div class="stat-card">
                <h2>Total de Clientes</h2>
                <p>${totalClientes}</p>
            </div>
            <div class="stat-card">
                <h2>Total de Llamadas</h2>
                <p>${totalLlamadas}</p>
            </div>
        </div>
        <a href="ControladorCliente?accion=clientes" class="btn btn-primary mt-3">Gestionar Clientes</a>
    </div>
</body>
</html>

