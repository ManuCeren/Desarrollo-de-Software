-- Insert tabla cliente
INSERT INTO cliente (nombre_Cliente, apellido_Cliente, telefono, correo, direccion, fecha_Registro)
VALUES 
('Juan', 'P�rez', '1234567890', 'juan.perez@example.com', 'Calle Falsa 123', '2024-11-01'),
('Ana', 'G�mez', '0987654321', 'ana.gomez@example.com', 'Avenida Central 45', '2024-11-02'),
('Luis', 'Mart�nez', '1122334455', 'luis.martinez@example.com', 'Colonia Jardines 78', '2024-11-03');


-- Insert tabla categorias

INSERT INTO categorias (descripcion)
VALUES 
('Soporte T�cnico'),
('Facturaci�n'),
('Consultas Generales');

-- Insert tabla Agente

INSERT INTO agente (nombre_Agente, apellido_Agente, turno, id_Departamento)
VALUES 
('Carlos', 'Ram�rez', 'Ma�ana', 1),
('Mar�a', 'L�pez', 'Tarde', 2),
('Andr�s', 'Salazar', 'Noche', 1);


-- Insert tabla registro de llamadas

INSERT INTO registroLlamadas (fecha_Hora_Llamada, hora_Inicio, hora_Final, motivoLlamada, solucion, estado, id_Cliente, id_Agente, id_Categoria)
VALUES 
('2024-11-15 10:30:00', '10:30:00', '10:45:00', 'Problema con la conexi�n', 'Se reinici� el router', 'Cerrada', 1, 1, 1),
('2024-11-16 14:20:00', '14:20:00', '14:35:00', 'Consulta sobre factura', 'Se explic� el cargo extra', 'Cerrada', 2, 2, 2),
('2024-11-17 09:15:00', '09:15:00', '09:30:00', 'Informaci�n general', 'Se proporcionaron datos de contacto', 'Abierta', 3, 3, 3);



-- Insert tabla departamento

INSERT INTO departamento (nombre_Departamento)
VALUES 
('Atenci�n al Cliente'),
('Soporte T�cnico'),
('Facturaci�n');

-- Insert tabla Historial de llamadas

INSERT INTO historialLlamada (fecha_Registro, comentarios, id_Llamada)
VALUES 
('2024-11-15', 'Cliente satisfecho con la soluci�n', 1),
('2024-11-16', 'Consulta aclarada correctamente', 2),
('2024-11-17', 'Cliente solicit� m�s informaci�n', 3);


-- Insert tabla seguimientos

INSERT INTO seguimientos (fecha_Seguimiento, acciones, resultado_Final, id_Llamada)
VALUES 
('2024-11-18', 'Se realiz� una llamada de seguimiento', 'Problema resuelto', 1),
('2024-11-19', 'Se envi� un correo con m�s informaci�n', 'Cliente satisfecho', 2),
('2024-11-20', 'Se asign� un t�cnico para revisi�n en sitio', 'Pendiente', 3);

-- Insert tabla encuesta
INSERT INTO encuesta (calificacion, comentarios, id_Llamada)
VALUES 
(5, 'Excelente atenci�n y soluci�n r�pida', 1),
(4, 'Buena atenci�n, pero tardaron en responder', 2),
(3, 'Falt� claridad en la explicaci�n', 3);

-- Insert tabla ticket
INSERT INTO ticket (fecha_Ticket, prioridad, descripcion, fecha_Fin, estado, id_Llamada, id_Cliente, id_Agente)
VALUES 
('2024-11-15 11:00:00', 'Alta', 'Problema de conexi�n urgente', '2024-11-15 13:00:00', 'Resuelto', 1, 1, 1),
('2024-11-16 14:45:00', 'Media', 'Consulta sobre cargos no reconocidos', '2024-11-16 16:00:00', 'Resuelto', 2, 2, 2),
('2024-11-17 10:00:00', 'Baja', 'Informaci�n sobre nuevos servicios', '2024-11-20 12:00:00', 'Pendiente', 3, 3, 3);

