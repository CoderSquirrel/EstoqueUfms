
SELECT e.id_entrada, item_id, item, unidade_id, unidade, fabricante_id, fabricante, validade, fabricacao, entrada, qtd, qtd_retirada FROM TB_ENTRADAS e
INNER JOIN TB_ITENS i on e.item_id = i.id_item 
INNER JOIN TB_FABRICANTES f on e.fabricante_id = f.id_fabricante
INNER JOIN TB_UNIDADES u on e.unidade_id = u.id_unidade  WHERE  qtd_retirada < 1 order by e.id_entrada ASC;


SELECT p.id, item_id, item, unidade_id, unidade, fabricante_id, fabricante, entrada, qtd, deposito, laboratorio FROM TB_PERMANENTES p INNER JOIN TB_ITENS i on p.item_id = i.id INNER JOIN TB_FABRICANTES f on p.fabricante_id = f.id  INNER JOIN TB_UNIDADES u on p.unidade_id = u.id order by p.id ASC;


select * from tb_entradas order by ;


update tb_entradas set qtd_retirada = 3 where id =1

select re.entrada_id, it.item, fa.fabricante, en.entrada, en.validade, us.usuario from tb_retiradas re 
inner join tb_entradas en on en.id_entrada = re.entrada_id
inner join tb_itens it on en.item_id = it.id_item
inner join tb_usuarios us on re.usuario_id = us.id_usuario
inner join tb_fabricantes fa on en.fabricante_id = fa.id_fabricante
group by re.entrada_id, it.item,fa.fabricante, us.usuario , en.entrada, en. validade
order by re.entrada_id asc