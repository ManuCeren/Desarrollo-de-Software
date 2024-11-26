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
    <style>
        body {
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: #fff;
        }

        .container {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 12px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
        }

        h1 {
            font-family: 'Poppins', sans-serif;
            font-weight: 600;
            color: #ffdd59;
        }

	button {
            font-family: 'Poppins', sans-serif;
            font-weight: 500;
        }

        .btn-primary {
            background: #ff6b6b;
            border: none;
        }

        .btn-primary:hover {
            background: #ff4757;
        }

        .btn-info {
            background: #1e90ff;
            border: none;
        }

        .btn-info:hover {
            background: #1c86ee;
        }

        .btn-secondary {
            background: #2ed573;
            border: none;
        }

        .btn-secondary:hover {
            background: #28c76f;
        }
 	.modal-content {
            background: #2f3542;
            color: #fff;
        }

        .modal-header {
            border-bottom: 1px solid #57606f;
        }

        .modal-footer {
            border-top: 1px solid #57606f;
        }

        input.form-control {
            background: rgba(255, 255, 255, 0.2);
            border: 1px solid #57606f;
            color: #fff;
        }

        input.form-control::placeholder {
            color: #dfe4ea;
        }

        input.form-control:focus {
            border-color: #1e90ff;
            box-shadow: none;
        }

        ul {
            list-style: none;
            padding-left: 0;
        }
	ul li::before {
            content: "•";
            color: #ffdd59;
            font-weight: bold;
            display: inline-block;
            width: 1em;
            margin-left: -1em;
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

        <button class="btn btn-info me-3" data-bs-toggle="modal" data-bs-target="#detallesAgenteModal">
            <i class="fas fa-user"></i> Detalles de Agente
        </button>

        <button class="btn btn-secondary" onclick="window.location.href='index.jsp'">
            <i class="fas fa-home"></i> Volver al Inicio
        </button>
    </div>
<div class="modal fade" id="detallesAgenteModal" tabindex="-1" aria-labelledby="detallesAgenteModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="detallesAgenteModalLabel">Detalles del Agente</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="buscarAgenteForm" class="d-flex align-items-center mb-3">
                        <input type="text" id="idAgente" class="form-control me-2" placeholder="Buscar agente por ID" required>
                        <button type="button" id="buscarAgenteBtn" class="btn btn-outline-light">
                            <i class="fas fa-search"></i> Buscar
                        </button>
                    </form>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("buscarAgenteBtn").addEventListener("click", function () {
            const idAgente = document.getElementById("idAgente").value;

            fetch(ControladorAgente?accion=buscar&idAgente=${idAgente})
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        alert(data.error);
                    } else {
                        document.getElementById("modalIdAgente").textContent = data.idAgente;
                        document.getElementById("modalNombreAgente").textContent = data.nombreAgente;
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

