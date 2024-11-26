<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <style>
        body {
            background-color: #f9f9fb;
            font-family: 'Arial', sans-serif;
            color: #333333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        h1 {
            color: #4caf50;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            background-color: #ffffff;
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
	label {
            display: block;
            font-weight: bold;
            margin-top: 10px;
            color: #555555;
        }

        input {
            width: calc(100% - 10px);
            padding: 8px 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            font-size: 14px;
            background-color: #fdfdfd;
        }

        input:focus {
            outline: none;
            border-color: #4caf50;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.4);
        }
 	button {
            background-color: #4caf50;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
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
