<%-- 
    Document   : clientes
    Created on : 13 nov 2024, 15:54:37
    Author     : Charly Cimino
--%>

<!-- webapp/clientes.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Modelo.Cliente"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Clientes</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #eef2f3;
            color: #333333;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        h1 {
            color: #4caf50;
            text-align: center;
            margin-bottom: 20px;
        }

        .header-image {
            width: 100%;
            height: 250px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .table {
            background-color: #ffffff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

	.table th {
            background-color: #6c757d;
            color: #ffffff;
            text-transform: uppercase;
        }

        .table td {
            color: #495057;
        }

        .btn {
            border-radius: 15px;
            padding: 6px 20px;
            font-weight: bold;
            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #0056b3;
        }

        .btn-warning {
            background-color: #ffc107;
            border-color: #e0a800;
            color: #212529;
        }
	 .btn-danger {
            background-color: #f44336;
            border-color: #c62828;
        }

        button {
            background-color: #28a745;
            color: #ffffff;
            border: none;
            padding: 12px 25px;
            font-size: 16px;
            border-radius: 25px;
            cursor: pointer;
            margin-top: 20px;
            display: block;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class ="container">
          <!-- Imagen decorativa -->
        <img src="img/header-clientes.jpg" alt="Gestión de Clientes" class="header-image">
        
        <h1>Gestión de Clientes</h1>

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
                            <a class="btn btn-warning" href="ControladorCliente?accion=editar&id=${cliente.idCliente}">Editar</a>
                            <a class="btn btn-danger" href="ControladorCliente?accion=eliminar&id=${cliente.idCliente}">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="d-flex justify-content-center mb-4">
            <button onclick="window.location.href='ControladorCliente?accion=add'">
                <i class="fa-solid fa-user-plus"></i>Agregar Cliente
            </button>

            <button onclick="window.location.href='index.jsp'">
                <i class="fas fa-home"></i> Volver al Inicio
            </button>
        </div>
         <!-- Botón para agregar nuevo cliente -->
           
        
    </div>
    
   
</body>
</html>
