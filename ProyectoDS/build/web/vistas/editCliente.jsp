

<%@page import="Modelo.Cliente"%>
<%@page import="ModeloDAO.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Modificar Cliente</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <%
                    ClienteDAO dao = new ClienteDAO();
                    int id = Integer.parseInt((String) request.getAttribute("idcli"));
                    Cliente p = dao.list(id);
                   
                %>
                <h1>Modificar Cliente</h1>
                <form action="ControladorCliente" 
                    Nombres:<br>
                    <input class="form-control" type="text" name="txtNom" value="<%= p.getNombreCliente() %>"><br>
                    Apellidos: <br>
                    <input class="form-control" type="text" name="txtApe" value="<%= p.getApellidoCliente() %>"><br>
                    Teléfono: <br>
                    <input class="form-control" type="text" name="txtTel" value="<%= p.getTelefono() %>"><br>
                    Correo: <br>
                    <input class="form-control" type="text" name="txtCorreo" value="<%= p.getCorreo() %>"><br>
                    Dirección: <br>
                    <input class="form-control" type="text" name="txtDireccion" value="<%= p.getDireccion() %>"><br>
                    Fecha de registro: <br>
                    <input class="form-control" type="text" name="txtFecha" value="<%= p.getFechaRegistro() %>"><br> <!-- Formato de fecha -->
                    
                    <!-- Campo oculto para pasar el id del cliente -->
                    <input type="hidden" name="txtid" value="<%= p.getIdCliente() %>">
                    <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 
                    <a href="ControladorCliente?accion=clientes">Regresar</a>
                </form>
            </div>
        </div>
    </body>
</html>


