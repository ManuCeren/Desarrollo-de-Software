<%-- 
    Document   : clientes
    Created on : 13 nov 2024, 15:54:37
    Author     : Charly Cimino
--%>

<!-- webapp/clientes.jsp -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Clientes</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h1>Lista de Clientes</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Teléfono</th>
                <th>Correo</th>
                <th>Dirección</th>
                <th>Fecha de Registro</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.apellido}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.correo}</td>
                    <td>${cliente.direccion}</td>
                    <td>${cliente.fechaRegistro}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Botón para agregar nuevo cliente -->
    <button onclick="window.location.href='agregarCliente.jsp'">Agregar Cliente</button>
</body>
</html>
