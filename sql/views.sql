		CREATE VIEW  LISTA_SAIDA AS select re.entrada_id, it.item, fa.fabricante, en.entrada, en.validade, us.usuario, re.data_retirada, re.qtd_retirada from tb_retiradas re 
		inner join tb_entradas en on en.id_entrada = re.entrada_id
		inner join tb_itens it on en.item_id = it.id_item
		inner join tb_usuarios us on re.usuario_id = us.id_usuario
		inner join tb_fabricantes fa on en.fabricante_id = fa.id_fabricante
		group by re.entrada_id, it.item,fa.fabricante, us.usuario , en.entrada, en. validade, re.data_retirada, re.qtd_retirada
		order by re.entrada_id asc