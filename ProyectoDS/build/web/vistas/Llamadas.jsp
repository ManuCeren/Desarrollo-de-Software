
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
    
    <!-- Formulario de búsqueda por ID -->
    <div class="mb-3">
        <form id="buscarLlamadaForm" class="d-flex align-items-center">
            <input type="text" id="idLlamada" class="form-control me-2" placeholder="Buscar llamada por ID" required>
            <button class="btn btn-outline-secondary" type="button" id="buscarLlamadaBtn">
                <i class="fas fa-search"></i> Buscar
            </button>
        </form>
    </div>
    
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
                    <button class="btn btn-info btn-sm btn-ver" data-id="${llamada.idLlamada}">
                        <i class="fas fa-eye"></i>
                    </button>
                    <a href="ControladorRegistro?accion=editar&id=${llamada.idLlamada}" class="btn btn-warning btn-sm">
                        <i class="fas fa-pencil-alt"></i>
                    </a>
                    <a href="ControladorRegistro?accion=eliminar&id=${llamada.idLlamada}" class="btn btn-danger btn-sm">
                        <i class="fas fa-trash"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Modal para ver detalles de llamada -->
    <div class="modal fade" id="verLlamadaModal" tabindex="-1" aria-labelledby="verLlamadaModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="verLlamadaModalLabel">Detalles de Llamada</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p><strong>ID Llamada:</strong> <span id="modalIdLlamada"></span></p>
                    <p><strong>Fecha y Hora:</strong> <span id="modalFechaHora"></span></p>
                    <p><strong>Hora Inicio:</strong> <span id="modalHoraInicio"></span></p>
                    <p><strong>Hora Final:</strong> <span id="modalHoraFinal"></span></p>
                    <p><strong>Motivo:</strong> <span id="modalMotivo"></span></p>
                    <p><strong>Solución:</strong> <span id="modalSolucion"></span></p>
                    <p><strong>Estado:</strong> <span id="modalEstado"></span></p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- JavaScript para manejo de modal dinámico -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
    // Manejo del botón "Ver" para cada llamada
    document.querySelectorAll(".btn-ver").forEach(button => {
        button.addEventListener("click", function () {
            const idLlamada = this.getAttribute("data-id");
            const loading = document.getElementById("modalLoading");
            loading.style.display = "block"; // Mostrar indicador de carga

            fetch(`ControladorRegistro?accion=buscar&idLlamada=${idLlamada}`)
                .then(response => response.json())
                .then(data => {
                    loading.style.display = "none"; // Ocultar indicador de carga

                    if (data.error) {
                        alert(data.error);
                    } else {
                        // Llenar el modal con los datos obtenidos
                        document.getElementById("modalIdLlamada").textContent = data.idLlamada;
                        document.getElementById("modalFechaHora").textContent = data.fechaHoraLlamada;
                        document.getElementById("modalHoraInicio").textContent = data.horaInicio;
                        document.getElementById("modalHoraFinal").textContent = data.horaFinal;
                        document.getElementById("modalMotivo").textContent = data.motivoLlamada;
                        document.getElementById("modalSolucion").textContent = data.solucion;
                        document.getElementById("modalEstado").textContent = data.estado;

                        // Mostrar el modal
                        const modal = new bootstrap.Modal(document.getElementById("verLlamadaModal"));
                        modal.show();
                    }
                })
                .catch(error => {
                    loading.style.display = "none"; // Ocultar indicador de carga
                    console.error("Error:", error);
                });
        });
    });
});

</script>
</body>
</html>
