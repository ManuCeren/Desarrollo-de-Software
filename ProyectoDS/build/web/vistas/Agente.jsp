<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Agentes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Gestión de Agentes</h1>

    <!-- Botones de Acción -->
    <div class="d-flex justify-content-center mb-4">
        <!-- Botón para listar agentes -->
        <button class="btn btn-primary me-3" onclick="window.location.href='ControladorAgente?accion=listar'">
            <i class="fas fa-list"></i> Listar Agentes
        </button>

        <!-- Botón para mostrar detalles de agente -->
        <button class="btn btn-info me-3" data-bs-toggle="modal" data-bs-target="#detallesAgenteModal">
            <i class="fas fa-user"></i> Detalles de Agente
        </button>

        <!-- Botón para volver al inicio -->
        <button class="btn btn-secondary" onclick="window.location.href='index.jsp'">
            <i class="fas fa-home"></i> Volver al Inicio
        </button>
    </div>

    <!-- Modal para mostrar detalles del agente -->
    <div class="modal fade" id="detallesAgenteModal" tabindex="-1" aria-labelledby="detallesAgenteModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="detallesAgenteModalLabel">Detalles del Agente</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulario para buscar agente por ID -->
                    <form id="buscarAgenteForm" class="d-flex align-items-center mb-3">
                        <input type="text" id="idAgente" class="form-control me-2" placeholder="Buscar agente por ID" required>
                        <button type="button" id="buscarAgenteBtn" class="btn btn-outline-secondary">
                            <i class="fas fa-search"></i> Buscar
                        </button>
                    </form>

                    <!-- Detalles del agente -->
                    <div id="detallesAgente" style="display: none;">
                        <p><strong>ID Agente:</strong> <span id="modalIdAgente"></span></p>
                        <p><strong>Nombre:</strong> <span id="modalNombreAgente"></span></p>
                        <p><strong>Clientes Atendidos:</strong></p>
                        <ul id="modalClientesAtendidos"></ul>
                        <p><strong>Turno:</strong> <span id="modalTurno"></span></p>
                        <p><strong>Departamento:</strong> <span id="modalDepartamento"></span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- JavaScript para manejar el modal -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("buscarAgenteBtn").addEventListener("click", function () {
            const idAgente = document.getElementById("idAgente").value;

            // Llamada AJAX para obtener los detalles del agente
            fetch(`ControladorAgente?accion=buscar&idAgente=${idAgente}`)
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        alert(data.error);
                    } else {
                        // Mostrar los detalles en el modal
                        document.getElementById("modalIdAgente").textContent = data.idAgente;
                        document.getElementById("modalNombreAgente").textContent = data.nombreAgente;

                        // Llenar la lista de clientes atendidos
                        const clientesList = document.getElementById("modalClientesAtendidos");
                        clientesList.innerHTML = "";
                        data.clientesAtendidos.forEach(cliente => {
                            const li = document.createElement("li");
                            li.textContent = cliente;
                            clientesList.appendChild(li);
                        });

                        document.getElementById("modalTurno").textContent = data.turno;
                        document.getElementById("modalDepartamento").textContent = data.departamento;

                        document.getElementById("detallesAgente").style.display = "block";
                    }
                })
                .catch(error => console.error("Error:", error));
        });
    });
</script>
</body>
</html>

