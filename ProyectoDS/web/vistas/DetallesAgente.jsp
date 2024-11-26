
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalles del Agente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
   <%-- vistas/DetallesAgente.jsp --%>
    <h1>Detalles del Agente</h1>
    <p><strong>ID:</strong> ${agente.idAgente}</p>
    <p><strong>Nombre:</strong> ${agente.nombreAgente}</p>
    <p><strong>Apellido:</strong> ${agente.apellidoAgente}</p>
    <p><strong>Turno:</strong> ${agente.turno}</p>
    <p><strong>Departamento:</strong> ${agente.nombreDepartamento}</p>

    <h2>Historial de Llamadas</h2>
    <table>
        <thead>
            <tr>
                <th>ID Llamada</th>
                <th>Fecha y Hora</th>
                <th>Motivo</th>
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="llamada" items="${llamadas}">
                <tr>
                    <td>${llamada.idLlamada}</td>
                    <td>${llamada.fechaHoraLlamada}</td>
                    <td>${llamada.motivoLlamada}</td>
                    <td>${llamada.estado}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="ControladorAgente?accion=listar" class="btn btn-secondary">Volver a la Lista</a>
</div>
</body>
</html>
