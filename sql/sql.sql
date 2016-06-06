create table tb_usuarios
(
id int primary key DEFAULT nextval('seq_usuarios'),
usuario varchar(40) not null unique,
registro bigint not null unique,
senha varchar(40) not null
);

create table tb_fabricantes
(
id int primary key DEFAULT nextval('seq_fabricantes'),
fabricante varchar(100) not null
);

create table tb_unidades
(
id int primary key DEFAULT nextval('seq_unidades'),
unidade varchar(100) not null
);

create table tb_itens
(
id int primary key DEFAULT nextval('seq_itens'),
item varchar(255) not null
);

create table tb_entradas
(
id int primary key DEFAULT nextval('seq_entrada'),
item_id int ,
unidade_id int,
fabricante_id int,
usuario_id int,
validade date,
fabricacao date,
entrada date,
qtd int, 
qtd_retirada int,
FOREIGN KEY (item_id) REFERENCES tb_itens (id),
FOREIGN KEY (unidade_id) REFERENCES tb_unidades (id),
FOREIGN KEY (fabricante_id) REFERENCES tb_fabricantes (id),
FOREIGN KEY (usuario_id) REFERENCES tb_usuarios (id)
);

create table tb_retirada
(
id int primary key DEFAULT nextval('seq_retirada'),
entrada_id int,
usuario_id int,
qtd_retirada int,
FOREIGN KEY (usuario_id) REFERENCES tb_usuarios (id),
FOREIGN KEY (entrada_id) REFERENCES tb_entradas (id)
);
