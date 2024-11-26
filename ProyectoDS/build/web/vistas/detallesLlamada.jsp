<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalles de Llamada</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Detalles de la Llamada</h2>
    <p><strong>Motivo:</strong> <span>${llamada.motivoLlamada}</span></p>
    <p><strong>Solución:</strong> <span>${llamada.solucion}</span></p>
    <p><strong>Estado:</strong> <span>${llamada.estado}</span></p>

    <h3>Seguimientos</h3>
    <ul>
        <c:forEach var="seguimiento" items="${seguimientos}">
            <li>
                <p><strong>Acción:</strong> ${seguimiento.acciones}</p>
                <p><strong>Fecha:</strong> ${seguimiento.fechaSeguimiento}</p>
            </li>
        </c:forEach>
    </ul>

    <!-- Formulario para agregar un nuevo seguimiento -->
    <form action="ControladorLlamada" method="post">
        <input type="hidden" name="idLlamada" value="${llamada.idLlamada}">
        <div class="mb-3">
            <textarea name="descripcion" class="form-control" placeholder="Descripción del seguimiento" required></textarea>
        </div>
        <div class="mb-3">
            <button type="submit" name="accion" value="guardarSeguimiento" class="btn btn-primary">Guardar Seguimiento</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

