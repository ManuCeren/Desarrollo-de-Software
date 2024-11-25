
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Encuestas de Satisfacción</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .btn {
            display: inline-block;
            margin: 10px 5px;
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <h1>Encuestas de Satisfacción</h1>
    
     <!-- Mostrar mensajes -->
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success" role="alert">
                ${mensaje}
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>
    <!-- Tabla de Encuestas -->
    <table>
        <thead>
            <tr>
                <th>ID Encuesta</th>
                <th>Calificación</th>
                <th>Comentarios</th>
                <th>Fecha y Hora de Llamada</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="encuesta" items="${listar}">
                <tr>
                    <td>${encuesta.idEncuesta}</td>
                    <td>${encuesta.calificacion}</td>
                    <td>${encuesta.comentarios}</td>
                    <td>${encuesta.fechaHoraLlamada}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Botón para abrir el modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addEncuestaModal">
        Agregar Encuesta
    </button>

    <!-- Modal -->
    <div class="modal fade" id="addEncuestaModal" tabindex="-1" aria-labelledby="addEncuestaModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Encabezado del Modal -->
                <div class="modal-header">
                    <h5 class="modal-title" id="addEncuestaModalLabel">Agregar Encuesta</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- Cuerpo del Modal -->
                <div class="modal-body">
                    <form id="addEncuestaForm" method="POST" action="ControladorEncuesta">
                        <input type="hidden" name="accion" value="guardar">

                        <div class="mb-3">
                            <label for="calificacion" class="form-label">Calificación</label>
                            <input type="number" class="form-control" id="calificacion" name="calificacion" required min="1" max="5" placeholder="Ingrese la calificación (1-5)">
                        </div>

                        <div class="mb-3">
                            <label for="comentarios" class="form-label">Comentarios</label>
                            <textarea class="form-control" id="comentarios" name="comentarios" rows="3" placeholder="Ingrese comentarios"></textarea>
                        </div>

                        <div class="mb-3">
                            <label for="idLlamada" class="form-label">ID de Llamada</label>
                            <input type="number" class="form-control" id="idLlamada" name="idLlamada" required placeholder="Ingrese el ID de la llamada">
                        </div>

                        <button type="submit" class="btn btn-success">Guardar Encuesta</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>