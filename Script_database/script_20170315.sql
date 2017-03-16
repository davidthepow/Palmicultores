-- Primera versión de la Base de Datos
-- Fecha de Creación: 15/03/2017
-- Última modificación: 15/03/2017

-- Definición de Tablas
create table estados 
(
	id serial primary key,
	clave numeric NOT NULL,
	nombre varchar(160)	NOT NULL
);

create table localidades
(
	id serial primary key,
	clave numeric NOT NULL,
	nombre varchar(160) NOT NULL,
	estado_id integer NOT NULL
);

create table municipios
(
	id serial primary key,
	clave numeric NOT NULL,
	nombre varchar(160) NOT NULL,
	estado_id integer NOT NULL
);


-- Definición de Llaves Foráneas
alter table localidades
  add constraint fk_estados_localidades
	foreign key (estado_id) 
		references estados (id);
		
alter table municipios
  add constraint fk_estados_municipios
	foreign key (estado_id) 
		references estados (id);
