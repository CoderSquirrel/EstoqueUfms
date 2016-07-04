package com.br.ufms.schirrel.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.EntradaPermanente;
import com.br.ufms.schirrel.classes.Usuario;
import com.br.ufms.schirrel.tabelas.ItemTableModel;

public class RelatorioPermanentes extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<EntradaPermanente> entradas;
	DAO dao;
	private JTable EntradaTable;
	@SuppressWarnings("unused")
	private Usuario USUARIO_LOGADO;
	public RelatorioPermanentes(DAO D, Usuario u) {
USUARIO_LOGADO = u;
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Itens", TitledBorder.LEADING, TitledBorder.CENTER, null, null));

		// { { "Item", "10", "Fornecedor", new Date(), new Date(), new
		// Integer(5), new Date(), new Integer(2) } };

		// Create the scroll pane and add the table to it.
		List<Object[]> a = CarregarLista();
		EntradaTable = new JTable(new ItemTableModel(a));
		PreencherTabela();

		EntradaTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(1).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(7).setPreferredWidth(15);
		
		JScrollPane scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 20, 780, 370);

		add(scrollPane);

	}

	List<Object[]> CarregarLista() {
		entradas = dao.ListarEntradasPermanentes();
		Object[] objs;
		List<Object[]> objetos = new ArrayList<Object[]>();

		for (int i = 0; i < entradas.size(); i++) {
			objs = new Object[8];
			objs[0] = entradas.get(i).getItem().getItem();
		//	objs[1] = entradas.get(i).getUnidade().getUnidade();
		//	objs[2] = entradas.get(i).getFabricante().getFabricante();
		//	objs[3] = entradas.get(i).getDataFabricacao();
		//	objs[4] = entradas.get(i).getDataValidade();
			objs[5] = entradas.get(i).getQtd();
			objs[6] = entradas.get(i).getDataEntrada();
		//	objs[7] = entradas.get(i).getRetirada();
			objetos.add(objs);
		}
		return objetos;
	}

	void PreencherTabela() {

		ItemTableModel it = (ItemTableModel) EntradaTable.getModel();

		
			it.RemoveAll();
			it.AddList(CarregarLista());
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dao.CadastrarFornecedor(tfFornecedor.getText().toString().trim());

	}

}