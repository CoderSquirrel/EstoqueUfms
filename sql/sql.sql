CREATE SEQUENCE seq_usuarios
increment 1
minvalue 1
maxvalue 2147483647
start 1
cache 1
no cycle;

 
create table tb_usuarios
(
id int primary key DEFAULT nextval('seq_usuarios'),
usuario varchar(40) not null unique,
registro bigint not null unique,
senha varchar(40) not null
);

CREATE SEQUENCE seq_fabricantes
increment 1
minvalue 1
maxvalue 2147483647
start 1
cache 1
no cycle;

 
create table tb_fabricantes
(
id int primary key DEFAULT nextval('seq_fabricantes'),
fabricante varchar(100) not null
);

CREATE SEQUENCE seq_unidades
increment 1
minvalue 1
maxvalue 2147483647
start 1
cache 1
no cycle;

 
create table tb_unidades
(
id int primary key DEFAULT nextval('seq_unidades'),
unidade varchar(100) not null
);

CREATE SEQUENCE seq_itens
increment 1
minvalue 1
maxvalue 2147483647
start 1
cache 1
no cycle;

 
create table tb_itens
(
id int primary key DEFAULT nextval('seq_itens'),
item varchar(255) not null
);


CREATE SEQUENCE seq_entrada
increment 1
minvalue 1
maxvalue 2147483647
start 1
cache 1
no cycle;

create table tb_entradas
(
id int primary key DEFAULT nextval('seq_entrada'),
item_id int ,
unidade_id int,
fabricante_id int,
validade date,
fabricacao date,
entrada date,
qtd int, 
qtd_retirada int,
FOREIGN KEY (item_id) REFERENCES tb_itens (id),
FOREIGN KEY (unidade_id) REFERENCES tb_unidades (id),
FOREIGN KEY (fabricante_id) REFERENCES tb_fabricantes (id)
);