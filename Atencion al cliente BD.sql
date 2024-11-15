-- DDL BASE DE DATOS DE ATENCION AL CLIENTE
create database LlamadasBD;
use LlamadasBD;

-- Tablas
-- 1
create table cliente(
    id_Cliente int primary key identity(1, 1),
    nombre_cliente varchar(100) not null,
    apellido_cliente varchar(100) not null,
    telefono varchar(15) not null, -- Aumentado el tamaño para posibles números internacionales
    correo varchar(100) not null, -- Aumentado el tamaño del correo
    direccion varchar(100) not null,
    fecha_Registro date
);

-- 2
create table registroLlamadas(
    id_Llamada int primary key identity(1, 1),
    fecha_Hora_Llamada datetime not null,
    hora_Inicio time not null,
    hora_Final time not null,
    motivoLlamada varchar(200) not null,
    solucion varchar(max) not null, -- Cambiado TEXT a VARCHAR(MAX) por compatibilidad
    estado varchar(50) not null,
    id_Cliente int not null,
    id_Agente int not null,
    id_Categoria int not null,
    constraint chk_estado_llamada CHECK (estado IN ('Pendiente', 'Completado', 'En Proceso')) -- Restricción en estado
);

-- 3
create table historialLlamada(
    id_Historial int primary key identity (1,1),
    fecha_Registro date not null,
    comentarios varchar(max),
    id_Llamada int not null -- Se agregó NOT NULL para asegurar la relación con la llamada
);

-- 4
create table encuesta(
    id_Encuesta int primary key identity (1,1),
    calificacion int not null,
    comentarios varchar(max),
    id_Llamada int not null, -- Se agregó NOT NULL para asegurar la relación con la llamada
    constraint chk_calificacion CHECK (calificacion BETWEEN 1 AND 5) -- Restricción de rango en calificación
);

-- 5
create table categorias(
    id_Categoria int primary key identity (1,1),
    descripcion varchar (50) not null
);

-- 6
create table seguimientos(
    id_Seguimiento int primary key identity (1,1),
    fecha_Seguimiento date not null,
    acciones varchar(max) not null,
    resultado_Final varchar(50) not null,
    id_Llamada int not null,
    constraint chk_resultado_final CHECK (resultado_Final IN ('Exitoso', 'Fallido')) -- Restricción en resultado final
);

-- 7
create table departamento(
    id_Departamento int primary key identity (1,1),
    nombre_Departamento varchar (50) not null
);

-- 8
create table agente(
    id_Agente int primary key identity (1,1),
    nombre_Agente varchar (100) not null,
    apellido_Agente varchar (100) not null,
    turno varchar (50) not null,
    id_Departamento int not null
);

-- 9
create table ticket(
    id_ticket int primary key identity(1,1),
    fecha_Ticket datetime not null,
    prioridad varchar (50) not null,
    descripcion varchar(max) not null,
    fecha_fin datetime not null,
    estado varchar (25) not null,
    id_Llamada int not null,
    id_Cliente int not null,
    id_Agente int not null,
    constraint chk_prioridad_ticket CHECK (prioridad IN ('Alta', 'Media', 'Baja')) -- Restricción en prioridad
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

