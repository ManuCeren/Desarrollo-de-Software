<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Cliente</title>
</head>
<body>
    <h1>Agregar Cliente</h1>
    <form action="ControladorCliente" method="POST">
        <input type="hidden" name="accion" value="add">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>
        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="telefono" required><br>
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" required><br>
        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" required><br>
        <label for="fechaRegistro">Fecha de Registro:</label>
        <input type="date" id="fechaRegistro" name="fechaRegistro" required><br>
        <button type="submit">Guardar Cliente</button>
    </form>
</body>
</html>
