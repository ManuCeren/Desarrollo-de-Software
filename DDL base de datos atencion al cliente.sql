-- DDL BASE DE DATOS DE ATENCION AL CLIENTE

CREATE DATABASE LlamadasBD;
USE LlamadasBD;

-- Tablas
-- 1
CREATE TABLE cliente(
	id_Cliente INT PRIMARY KEY IDENTITY(1, 1),
	nombre_Cliente VARCHAR(100) NOT NULL,
	apellido_Cliente VARCHAR(100) NOT NULL,
	telefono VARCHAR(10) NOT NULL,
	correo VARCHAR(100) NOT NULL,
	direccion VARCHAR(100) NOT NULL,
	fecha_Registro DATE
);

-- 2
CREATE TABLE registroLlamadas(
	id_Llamada INT PRIMARY KEY IDENTITY(1, 1),
	fecha_Hora_Llamada DATETIME NOT NULL,
	hora_Inicio TIME NOT NULL,
	hora_Final TIME NOT NULL,
	motivoLlamada VARCHAR(200) NOT NULL,
	solucion VARCHAR(MAX) NOT NULL,
	estado VARCHAR(100) NOT NULL,
	id_Cliente INT NOT NULL,
	id_Agente INT NOT NULL,
	id_Categoria INT NOT NULL
);

-- 3
CREATE TABLE historialLlamada(
	id_Historial INT PRIMARY KEY IDENTITY(1, 1),
	fecha_Registro DATE NOT NULL,
	comentarios VARCHAR(MAX),
	id_Llamada INT NOT NULL
);

-- 4
CREATE TABLE encuesta(
	id_Encuesta INT PRIMARY KEY IDENTITY(1, 1),
	calificacion INT NOT NULL,
	comentarios VARCHAR(MAX),
	id_Llamada INT NOT NULL
);

-- 5
CREATE TABLE categorias(
	id_Categoria INT PRIMARY KEY IDENTITY(1, 1),
	descripcion VARCHAR(50) NOT NULL
);

-- 6
CREATE TABLE seguimientos(
	id_Seguimiento INT PRIMARY KEY IDENTITY(1, 1),
	fecha_Seguimiento DATE NOT NULL,
	acciones VARCHAR(MAX) NOT NULL,
	resultado_Final VARCHAR(100) NOT NULL,
	id_Llamada INT NOT NULL
);

-- 7
CREATE TABLE departamento(
	id_Departamento INT PRIMARY KEY IDENTITY(1, 1),
	nombre_Departamento VARCHAR(50) NOT NULL
);

-- 8
CREATE TABLE agente(
	id_Agente INT PRIMARY KEY IDENTITY(1, 1),
	nombre_Agente VARCHAR(100) NOT NULL,
	apellido_Agente VARCHAR(100) NOT NULL,
	turno VARCHAR(50) NOT NULL,
	id_Departamento INT NOT NULL
);

-- 9
CREATE TABLE ticket(
	id_Ticket INT PRIMARY KEY IDENTITY(1, 1),
	fecha_Ticket DATETIME NOT NULL,
	prioridad VARCHAR(100) NOT NULL,
	descripcion VARCHAR(MAX) NOT NULL,
	fecha_Fin DATETIME NOT NULL,
	estado VARCHAR(25) NOT NULL,
	id_Llamada INT NOT NULL,
	id_Cliente INT NOT NULL,
	id_Agente INT NOT NULL
);

-- Llaves foráneas

alter table registroLlamadas add foreign key (id_Cliente) references cliente(id_Cliente);
alter table registroLlamadas add foreign key (id_Agente) references agente(id_Agente);
alter table registroLlamadas add foreign key (id_Categoria) references categorias(id_Categoria);
alter table historialLlamada add foreign key (id_Llamada) references registroLlamadas (id_Llamada);
alter table encuesta add foreign key (id_Llamada) references registroLlamadas (id_Llamada);
alter table seguimientos add foreign key (id_Llamada) references registroLlamadas (id_Llamada);
alter table agente add foreign key (id_Departamento) references departamento (id_Departamento);
alter table ticket add foreign key (id_Llamada) references registroLlamadas (id_Llamada);
alter table ticket add foreign key (id_Cliente) references cliente (id_Cliente);
alter table ticket add foreign key (id_Agente) references agente(id_Agente);
