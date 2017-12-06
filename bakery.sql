create database panaderia;
use panaderia;
create table materia(
	id_materia int not null auto_increment,
	nombre_materia varchar(50),
	cantidad float(20,4),
	precio float(20,4),
	primary key(id_materia)
	
);
create table cargo(
	id_cargo int not null auto_increment,
	nombre_cargo varchar(50),
	primary key(id_cargo)
);
create table usuarios2(
	id_usuario int not null auto_increment,
	id_cargo int not null,
	nombre varchar(50),
	app varchar(50),
	apm varchar(50),
	domicilio varchar(100),
	telefono varchar(50),	
	pass varchar(50),
	salario float(20,4),
	fecha_ingreso date,	
	primary key(id_usuario),
	foreign key(id_cargo) references cargo(id_cargo)
	
);
create table categoria_pan(
	id_categoria int not null auto_increment,
	nombre_categoria varchar(50),
	primary key(id_categoria)
);

create table producto(
	id_producto int not null auto_increment,
	id_categoria int not null,
	nombre varchar(50),
	descripcion varchar(50),	
	pventa float(20,4),
	fventa date,
	primary key(id_producto),
	foreign key(id_categoria) references categoria_pan(id_categoria)
);
create table venta(
	id_venta int not null auto_increment,
	id_categoria int not null,	
	id_pan int not null,
	precio_unitario float(20,2),	
	cantidad_piezas int,		
	fventa date,
	primary key(id_venta),
	foreign key(id_categoria) references categoria_pan(id_categoria),
	foreign key(id_pan) references panes(id_pan)
	
);
create table panes(
	id_pan int not null auto_increment,
	id_categoria int not null,
	nombre_pan varchar(50),			
	primary key(id_pan),
	foreign key(id_categoria) references categoria_pan(id_categoria)
);
create table productos(
	id_producto int not null auto_increment,
	id_categoria int not null,
	id_venta int not null,		
	primary key(id_producto),
	foreign key(id_categoria) references categoria_pan(id_categoria),
	foreign key(id_venta) references venta(id_venta)
);
CREATE TABLE imagenes (
id_imagen INT NOT NULL auto_increment,
id_pan int not null,
imagen LONGBLOB,
PRIMARY KEY (id_imagen),
foreign key(id_pan) references panes(id_pan)
);
insert into venta(id_categoria,id_pan,precio_unitario,cantidad_piezas,fventa)values(1,1,2.50,2,'2017-10-12');
insert into panes(id_categoria,nombre_pan) values(1,'Conchas'),(1,'Cuernos'),(1,'Cocoles'),(1,'Bigotes'),(1,'Cerillos'),(1,'Rebanadas'),(1,'Bailes'),(1,'Melon'),(1,'Casco'),(1,'Taco'),(1,'Pluma'),(1,'Tacón'),(1,'Volcán');
insert into panes(id_categoria,nombre_pan) values(2,'Banderilla'),(2,'Oreja'),(2,'Cuadro'),(2,'Moños'),(2,'Rollos de Chocolate'),(2,'Rollos de Mermelada'),(2,'Trenzado con glases');
insert into panes(id_categoria,nombre_pan) values(3,'Apastelada'),(3,'Pañuelos'),(3,'Piña'),(3,'Gusano'),(3,'Muela'),(3,'Rosca de Canela'),(3,'Panes rellenos de queso'),(3,'Panes rellenos de mermelada'),(3,'Panes rellenos de chocolate');
insert into panes(id_categoria,nombre_pan) values(4,'Roscas'),(4,'Hojaldras'),(4,'Pambazo'),(4,'Chancla'),(4,'Pelona'),(4,'Colorados'),(4,'Cocol de anis');
insert into panes(id_categoria,nombre_pan) values(5,'Volován'),(5,'Banderilla con salchicha'),(5,'Medias noches');
insert into panes(id_categoria,nombre_pan) values(6,'Cuerno'),(6,'Trenza'),(6,'Corazones de Canela'),(6,'Rosca'),(6,'Rosquete');
insert into panes(id_categoria,nombre_pan) values(7,'Telera');
insert into panes(id_categoria,nombre_pan) values(8,'Panque'),(8,'Bombón'),(8,'Piedra'),(8,'Yoyo'),(8,'Ojo');
insert into panes(id_categoria,nombre_pan) values(9,'Galleta'),(9,'Trocante'),(9,'Polvoron de Mantequilla');
insert into cargo(nombre_cargo) values ('Administrador');
insert into categoria_pan(nombre_categoria) values('Bizcocho');
insert into usuarios2(id_cargo,nombre,app,apm,domicilio,telefono,pass,salario,fecha_ingreso) 
values (1,'Luis','Luna','Vede','Calle oriente','2233455','password',2000.000,'12/10/2000');

 select u.id_usuario,u.nombre,u.app,u.apm,u.domicilio,u.telefono,c.nombre_cargo,u.pass,salario,u.fecha_ingreso,u.fecha_despido from usuarios as u inner join cargo as c on u.id_cargo=c.id_cargo;