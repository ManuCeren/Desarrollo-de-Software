<!-- webapp/agregarCliente.jsp -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Cliente</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h1>Agregar Cliente</h1>
    <form action="clientes" method="POST">
        <label>Nombre:</label>
        <input type="text" name="nombre" required><br>

        <label>Apellido:</label>
        <input type="text" name="apellido" required><br>

        <label>Teléfono:</label>
        <input type="text" name="telefono" required><br>

        <label>Correo:</label>
        <input type="email" name="correo" required><br>

        <label>Dirección:</label>
        <input type="text" name="direccion" required><br>

        <button type="submit">Guardar</button>
    </form>
</body>
</html>

