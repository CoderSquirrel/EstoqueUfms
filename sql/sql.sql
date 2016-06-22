create table tb_usuarios
(
id_usuario int primary key DEFAULT nextval('seq_usuarios'),
usuario varchar(40) not null unique,
registro bigint not null unique,
senha varchar(40) not null
);

create table tb_fabricantes
(
id_fabricante int primary key DEFAULT nextval('seq_fabricantes'),
fabricante varchar(100) not null
);

create table tb_unidades
(
id_unidade int primary key DEFAULT nextval('seq_unidades'),
unidade varchar(100) not null
);

create table tb_itens
(
id_item int primary key DEFAULT nextval('seq_itens'),
item varchar(255) not null
);


create table tb_entradas
(
id_entrada int primary key DEFAULT nextval('seq_entrada'),
item_id int ,
unidade_id int,
fabricante_id int,
usuario_id int,
validade date,
fabricacao date,
entrada date,
qtd int, 
qtd_retirada int,
FOREIGN KEY (item_id) REFERENCES tb_itens (id_item),
FOREIGN KEY (unidade_id) REFERENCES tb_unidades (id_unidade),
FOREIGN KEY (fabricante_id) REFERENCES tb_fabricantes (id_fabricante),
FOREIGN KEY (usuario_id) REFERENCES tb_usuarios (id_usuario)
);


create table tb_permanentes
(
id_permanente int primary key DEFAULT nextval('seq_permanente'),
item_id int ,
descricao text,
usuario_id int,
entrada date,
qtd int, 
deposito int,
laboratorio int,
obs text,
patrimonio text,
estado int,
FOREIGN KEY (item_id) REFERENCES tb_itens (id_item),
FOREIGN KEY (usuario_id) REFERENCES tb_usuarios (id_usuario)
);

create table tb_retiradas
(
id_retirada int primary key DEFAULT nextval('seq_retirada'),
entrada_id int,
usuario_id int,
qtd_retirada int,
FOREIGN KEY (usuario_id) REFERENCES tb_usuarios (id_usuario),
FOREIGN KEY (entrada_id) REFERENCES tb_entradas (id_entrada)
);
create table tb_novaentradas (
id_novaentrada int primary key DEFAULT nextval('seq_novaentrada'),
entrada_id int,
qtd int,
entrada date,
FOREIGN KEY (entrada_id) REFERENCES tb_entradas (id_entrada)
);