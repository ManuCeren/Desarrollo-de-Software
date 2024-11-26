<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #007bff, #90caf9); /* Fondo degradado */
            color: #fff;
        }
        .login-container {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3); /* Sombra más profesional */
            width: 100%;
            max-width: 400px;
            text-align: center;
            color: #333; /* Color del texto interno */
        }
        .login-container h2 {
            margin-bottom: 20px;
            color: #007bff; /* Título con tono corporativo */
        }
        .login-container label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        .login-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }
        .login-container button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            transition: background-color 0.3s;
        }
        .login-container button:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            margin-top: 10px;
            font-size: 14px;
        }
        @media (max-width: 768px) {
            .login-container {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Iniciar Sesión</h2>
        <form action="ControladorLogin" method="POST">
            <label for="usuario">Usuario:</label>
            <input type="text" name="usuario" id="usuario" placeholder="Ingrese su usuario" required>
            <label for="password">Contraseña:</label>
            <input type="password" name="password" id="password" placeholder="Ingrese su contraseña" required>
            <button type="submit">Iniciar Sesión</button>
        </form>
        <c:if test="${not empty mensaje}">
            <p class="error-message">${mensaje}</p>
        </c:if>
    </div>
</body>
</html>
