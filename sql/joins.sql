﻿
SELECT e.id, item_id, item, unidade_id, unidade, fabricante_id, fabricante, validade, fabricacao, entrada, qtd, qtd_retirada FROM TB_ENTRADAS e
INNER JOIN TB_ITENS i on e.item_id = i.id 
INNER JOIN TB_FABRICANTES f on e.fabricante_id = f.id
INNER JOIN TB_UNIDADES u on e.unidade_id = u.id  WHERE  qtd_retirada < 1 order by e.id ASC;


select * from tb_entradas order by ;


update tb_entradas set qtd_retirada = 3 where id =1