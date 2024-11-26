<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Agentes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <!-- Estilos personalizados -->
    <style>
        body {
            background-color: #f8f9fa;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
        .btn {
            margin: 2px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Gestión de Agentes</h1>

    <div class="d-flex justify-content-center mb-4">
        <button class="btn btn-primary me-3" onclick="window.location.href='ControladorAgente?accion=listar'">
            <i class="fas fa-list"></i> Listar Agentes
        </button>

        <!-- Botón para abrir el modal de detalles -->
        <button class="btn btn-info me-3" data-bs-toggle="modal" data-bs-target="#detallesAgenteModal">
            <i class="fas fa-user"></i> Detalles de Agente
        </button>

        <button class="btn btn-secondary" onclick="window.location.href='index.jsp'">
            <i class="fas fa-home"></i> Volver al Inicio
        </button>
    </div>
</div>

<!-- Modal para solicitar el ID del agente -->
<div class="modal fade" id="detallesAgenteModal" tabindex="-1" aria-labelledby="detallesAgenteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="detallesAgenteModalLabel">Buscar Agente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="buscarAgenteForm" action="ControladorAgente" method="get">
                    <input type="hidden" name="accion" value="verDetalles">
                    <div class="mb-3">
                        <label for="idAgente" class="form-label">ID del Agente:</label>
                        <div class="input-group">
                            <input type="number" class="form-control" id="idAgente" name="id" required>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-search"></i> Buscar
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

