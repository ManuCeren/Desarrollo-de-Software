<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Historial de Llamada</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Historial de Llamada</h1>
    
    <!-- Información de la llamada -->
    <div class="mb-4">
        <h4>Detalles de la Llamada</h4>
        <p><strong>ID:</strong> ${llamada.idLlamada}</p>
        <p><strong>Fecha y Hora:</strong> ${llamada.fechaHoraLlamada}</p>
        <p><strong>Motivo:</strong> ${llamada.motivoLlamada}</p>
        <p><strong>Estado:</strong> ${llamada.estado}</p>
    </div>

    <!-- Historial de seguimientos -->
    <h4>Seguimientos</h4>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Acciones</th>
                <th>Resultado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="seguimiento" items="${historial}">
                <tr>
                    <td>${seguimiento.fechaSeguimiento}</td>
                    <td>${seguimiento.acciones}</td>
                    <td>${seguimiento.resultadoFinal}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="ControladorRegistro?accion=listar" class="btn btn-secondary">Volver</a>
</div>
</body>
</html>

