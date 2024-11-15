<%-- 
    Document   : clientes
    Created on : 13 nov 2024, 15:54:37
    Author     : Charly Cimino
--%>

<!-- webapp/clientes.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gesti�n de Clientes</title>
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
                <th>Tel�fono</th>
                <th>Correo</th>
                <th>Direcci�n</th>
                <th>Fecha de Registro</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.nombreCliente}</td>
                    <td>${cliente.apellidoCliente}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.correo}</td>
                    <td>${cliente.direccion}</td>
                    <td>${cliente.fechaRegistro}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Bot�n para agregar nuevo cliente -->
    <button onclick="window.location.href='ControladorCliente?accion=add'">Agregar Cliente</button>
</body>
</html>
