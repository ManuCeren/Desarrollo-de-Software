<%-- 
    Document   : listarAgente
    Created on : 24 nov 2024, 22:37:28
    Author     : Charly Cimino
--%>

<!-- webapp/agentes.jsp -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Agentes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Listado de Agentes</h1>

    <!-- Tabla para listar agentes -->
    <table class="table table-bordered table-striped mt-3">
        <thead>
        <tr>
            <th>ID Agente</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Turno</th>
            <th>Departamento</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
                <c:forEach var="agente" items="${listarAgentes}">
                <tr>
                    <td>${agente.idAgente}</td>
                    <td>${agente.nombreAgente}</td>
                    <td>${agente.apellidoAgente}</td>
                    <td>${agente.turno}</td>
                    <td>${agente.nombreDepartamento}</td>
                    <td>
                        <!-- Botón para mostrar detalles -->
                        <a href="ControladorAgente?accion=verDetalles&id=${agente.idAgente}" class="btn btn-info btn-sm">
                            <i class="fas fa-eye"></i> Ver Detalles
                        </a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>   
    </div>
   <!-- Botón para regresar al inicio -->
    <div class="text-center mt-3">
        <a href="index.jsp" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Regresar al Inicio
        </a>
    </div>
</div>

<!-- Modal para mostrar detalles del agente -->
<div class="modal fade" id="detalleAgenteModal" tabindex="-1" aria-labelledby="detalleAgenteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="detalleAgenteModalLabel">Detalles del Agente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><strong>ID Agente:</strong> <span id="modalIdAgente"></span></p>
                <p><strong>Nombre:</strong> <span id="modalNombreAgente"></span></p>
                <p><strong>Apellido:</strong> <span id="modalApellidoAgente"></span></p>
                <p><strong>Turno:</strong> <span id="modalTurno"></span></p>
                <p><strong>Departamento:</strong> <span id="modalNombreDepartamento"></span></p>
                <p><strong>Clientes Atendidos:</strong></p>
                <ul id="modalClientesAtendidos"></ul>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- JavaScript para manejo de detalles -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Manejo del botón "Ver Detalles"
        document.querySelectorAll(".btn-detalle").forEach(button => {
            button.addEventListener("click", function () {
                const idAgente = this.getAttribute("data-id");
                fetch(`ControladorAgente?accion=detalles&idAgente=${idAgente}`)
                    .then(response => response.json())
                    .then(data => {
                        if (data.error) {
                            alert(data.error);
                        } else {
                            // Llenar el modal con los datos obtenidos
                            document.getElementById("modalIdAgente").textContent = data.idAgente;
                            document.getElementById("modalNombreAgente").textContent = data.nombreAgente;
                            document.getElementById("modalApellidoAgente").textContent = data.apellidoAgente;
                            document.getElementById("modalTurno").textContent = data.turno;
                            document.getElementById("modalDepartamento").textContent = data.nombreDepartamento;

                            // Llenar la lista de clientes atendidos
                            const clientesList = document.getElementById("modalClientesAtendidos");
                            clientesList.innerHTML = ""; // Limpiar lista previa
                            data.clientesAtendidos.forEach(cliente => {
                                const li = document.createElement("li");
                                li.textContent = cliente.nombreCliente;
                                clientesList.appendChild(li);
                            });

                            // Mostrar el modal
                            const modal = new bootstrap.Modal(document.getElementById("detalleAgenteModal"));
                            modal.show();
                        }
                    })
                    .catch(error => console.error("Error:", error));
            });
        });
    });
</script>
    
   
</body>
</html>

