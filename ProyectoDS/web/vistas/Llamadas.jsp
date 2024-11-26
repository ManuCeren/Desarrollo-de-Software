<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Llamadas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Gestión de Llamadas</h1>

    <!-- Botón para agregar llamada -->
    <div class="mb-3">
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addLlamadaModal">Agregar Llamada</button>
    </div>

    <!-- Tabla para listar llamadas -->
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID Llamada</th>
            <th>Fecha y Hora</th>
            <th>Hora Inicio</th>
            <th>Hora Final</th>
            <th>Motivo</th>
            <th>Solución</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="llamada" items="${listar}">
            <tr>
                <td>${llamada.idLlamada}</td>
                <td>${llamada.fechaHoraLlamada}</td>
                <td>${llamada.horaInicio}</td>
                <td>${llamada.horaFinal}</td>
                <td>${llamada.motivoLlamada}</td>
                <td>${llamada.solucion}</td>
                <td>${llamada.estado}</td>
                <td>
                    <!-- Botón "Ver" para mostrar detalles en modal -->
                    <a href="ControladorRegistro?accion=verHistorial&idLlamada=${llamada.idLlamada}" 
                        class="btn btn-info btn-sm">
                        <i class="fas fa-eye"></i> Ver
                    </a>
                    <a href="ControladorRegistro?accion=editar&id=${llamada.idLlamada}" class="btn btn-warning btn-sm">
                        <i class="fas fa-pencil-alt"></i> Editar
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-secondary" onclick="window.location.href='index.jsp'">
        <i class="fas fa-home"></i> Volver al Inicio
    </button>

<!-- Modal para agregar una nueva llamada -->
<div class="modal fade" id="addLlamadaModal" tabindex="-1" aria-labelledby="addLlamadaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="ControladorRegistro" method="post">
                <input type="hidden" name="accion" value="guardar">

                <!-- Fecha y Hora -->
                <div class="mb-3">
                    <label for="fechaHoraLlamada" class="form-label">Fecha y Hora</label>
                    <input type="datetime-local" class="form-control" id="fechaHoraLlamada" name="fechaHoraLlamada" required>
                </div>

                <!-- Hora Inicio -->
                <div class="mb-3">
                    <label for="horaInicio" class="form-label">Hora Inicio</label>
                    <input type="time" class="form-control" id="horaInicio" name="horaInicio" required>
                </div>

                <!-- Hora Final -->
                <div class="mb-3">
                    <label for="horaFinal" class="form-label">Hora Final</label>
                    <input type="time" class="form-control" id="horaFinal" name="horaFinal" required>
                </div>

                <!-- Motivo -->
                <div class="mb-3">
                    <label for="motivo" class="form-label">Motivo</label>
                    <input type="text" class="form-control" id="motivo" name="motivo" required>
                </div>

                <!-- Solución -->
                <div class="mb-3">
                    <label for="solucion" class="form-label">Solución</label>
                    <textarea class="form-control" id="solucion" name="solucion"></textarea>
                </div>

                <!-- Estado -->
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado</label>
                    <select class="form-select" id="estado" name="estado" required>
                        <option value="Abierta">Abierta</option>
                        <option value="Cerrada">Cerrada</option>
                    </select>
                </div>

                <!-- ID Cliente -->
                <div class="mb-3">
                    <label for="idCliente" class="form-label">ID Cliente</label>
                    <input type="number" class="form-control" id="idCliente" name="idCliente" required>
                </div>

                <!-- ID Agente -->
                <div class="mb-3">
                    <label for="idAgente" class="form-label">ID Agente</label>
                    <input type="number" class="form-control" id="idAgente" name="idAgente" required>
                </div>

                <!-- ID Categoría -->
                <div class="mb-3">
                    <label for="idCategoria" class="form-label">ID Categoría</label>
                    <input type="number" class="form-control" id="idCategoria" name="idCategoria" required>
                </div>

                <!-- Botones -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

