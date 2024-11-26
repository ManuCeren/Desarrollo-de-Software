<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Llamada</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Editar Llamada</h1>
    
    <!-- Formulario para editar llamada -->
    <form action="ControladorRegistro" method="post">
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="idLlamada" value="${llamada.idLlamada}">
        
        <div class="mb-3">
            <label for="estado" class="form-label">Estado</label>
            <select class="form-select" name="estado" id="estado">
                <option value="Abierta" ${llamada.estado == 'Abierta' ? 'selected' : ''}>Abierta</option>
                <option value="Cerrada" ${llamada.estado == 'Cerrada' ? 'selected' : ''}>Cerrada</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        <a href="ControladorRegistro?accion=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
