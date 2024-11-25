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
    <title>Gestión de Clientes</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class ="container">
        <h1>Lista de Clientes</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                    <th>Dirección</th>
                    <th>Fecha de Registro</th>
                    <th>Acciones</th>
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
                        <td class="text-center">
                                <a class="btn btn-primary" href="Controlador?accion=Ver&id=${cliente.idCliente}">Ver Direcciones</a>
                                <a class="btn btn-warning" href="Controlador?accion=editar&id=${cliente.idCliente}">Editar</a>
                                <a class="btn btn-danger" href="Controlador?accion=eliminar&id=${cliente.idCliente}>">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
         <!-- Botón para agregar nuevo cliente -->
        <button onclick="window.location.href='ControladorCliente?accion=add'">Agregar Cliente</button>    
    </div>
    
   
</body>
</html>
