		CREATE VIEW  VW_LISTAR_RETIRADAS AS select re.entrada_id, it.item, fa.fabricante, en.entrada, en.validade, us.usuario, re.data_retirada, re.qtd_retirada from tb_retiradas re 
		inner join tb_entradas en on en.id_entrada = re.entrada_id
		inner join tb_itens it on en.item_id = it.id_item
		inner join tb_usuarios us on re.usuario_id = us.id_usuario
		inner join tb_fabricantes fa on en.fabricante_id = fa.id_fabricante
		group by re.entrada_id, it.item,fa.fabricante, us.usuario , en.entrada, en. validade, re.data_retirada, re.qtd_retirada
		order by re.entrada_id asc;


		CREATE VIEW VW_LISTAR_RETIRADAS_TOTAL AS select entrada_id, it.item, fa.fabricante,en.entrada, en.validade, sum(re.qtd_retirada) as retirada from tb_retiradas re
	inner join tb_entradas en on en.id_entrada = re.entrada_id
	inner join tb_itens it on en.item_id = it.id_item
	inner join tb_fabricantes fa on en.fabricante_id = fa.id_fabricante
	group by entrada_id,it.item, en.entrada, en.validade, fa.fabricante
	order by retirada DESC


	CREATE VIEW VW_LISTAR_ENTRADAS_TOTAL AS select entrada_id, it.item, fa.fabricante, en.fabricacao, en.validade, sum(nova.qtd) as total_entrada from tb_novaentradas nova
	inner join tb_entradas en on en.id_entrada = nova.entrada_id
	inner join tb_itens it on en.item_id = it.id_item
	inner join tb_fabricantes fa on en.fabricante_id = fa.id_fabricante
	group by entrada_id,it.item, en.fabricacao, en.validade, fa.fabricante
	order by total_entrada DESC

select * from VW_LISTAR_RETIRADAS_TOTAL