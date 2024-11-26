<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Tickets</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        .btn {
            margin: 2px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Gestión de Tickets</h1>

    <!-- Botón para agregar ticket -->
    <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#agregarTicketModal">
        <i class="fas fa-plus"></i> Agregar Ticket
    </button>

    <!-- Tabla de tickets -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Fecha Ticket</th>
            <th>Prioridad</th>
            <th>Descripción</th>
            <th>Fecha Fin</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td>${ticket.idTicket}</td>
                <td>${ticket.fechaTicket}</td>
                <td>${ticket.prioridad}</td>
                <td>${ticket.descripcion}</td>
                <td>${ticket.fechaFin}</td>
                <td>${ticket.estado}</td>
                <td>
                    <button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#detallesTicketModal" onclick="cargarDetalles(${ticket.idTicket})">
                        <i class="fas fa-eye"></i> Ver
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal para agregar ticket -->
<div class="modal fade" id="agregarTicketModal" tabindex="-1" aria-labelledby="agregarTicketModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="ControladorTicket" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="agregarTicketModalLabel">Agregar Ticket</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="prioridad" class="form-label">Prioridad</label>
                        <select class="form-select" name="prioridad" required>
                            <option value="Alta">Alta</option>
                            <option value="Media">Media</option>
                            <option value="Baja">Baja</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea class="form-control" name="descripcion" rows="3" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="fechaFin" class="form-label">Fecha Fin</label>
                        <input type="date" class="form-control" name="fechaFin">
                    </div>
                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado</label>
                        <select class="form-select" name="estado" required>
                            <option value="Abierto">Abierto</option>
                            <option value="Cerrado">Cerrado</option>
                        </select>
                    </div>
                    <input type="hidden" name="accion" value="agregar">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
